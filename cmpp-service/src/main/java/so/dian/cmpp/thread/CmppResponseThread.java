package so.dian.cmpp.thread;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import so.dian.cmpp.bean.message.MessageDeliverBean;
import so.dian.cmpp.bean.message.MsgRespThreadBean;
import so.dian.cmpp.constant.CommandIdConstans;
import so.dian.cmpp.service.CMPPClientService;
import so.dian.cmpp.service.CMPPSocketService;
import so.dian.cmpp.utils.CMPPUtils;
import so.dian.cmpp.utils.ResultResolveUtlis;

/**
 * 处理移动短信网关发来的消息线程
 * 
 * @author ningque
 *
 */
@Component
public class CmppResponseThread implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(CmppResponseThread.class);
	@Autowired
	CMPPSocketService cMPPSocketService;
	@Autowired
	CMPPClientService cMPPClientService;
	@Autowired
	ResultResolveUtlis resultResolveUtlis;
	@Autowired
	CMPPUtils cMPPUtils;

	public static boolean running = false;
	private static int errorCount = 0;
	private long[]exceptionTime=null;//存储异常时间的数组

	@Override
	public void run() {
		logger.info("------------------开始接收短信网关消息---------------");
		while (running) {
			try {
				//接收短信网关消息
				MsgRespThreadBean bean=receptionISMGMsg();
				if(null!=bean) {
					CmppResponse(bean);
				}
			} catch (Exception e) {
				logger.error("接收响应消息异常,异常信息={}", ExceptionUtils.getFullStackTrace(e));
				//java.net.SocketException: Socket is closed,java.net.SocketException: Connection reset
				//java.net.SocketException: Connection refused: connect,java.net.SocketException: Broken pipe。
				if (e instanceof SocketException) {
					if(null==exceptionTime) {
						exceptionTime=new long[4];
					}
					//保存当前异常的时间
					exceptionTime[errorCount]=System.currentTimeMillis();
					errorCount++;
					logger.error("接收响应消息异常,异常类型={},异常次数={}", "SocketException", errorCount);
					// 如果socket 异常一分钟内出现大于5次，就重新登录
					if (errorCount >= 5||exceptionTime.length>=5) {
						long topTime=exceptionTime[0];
						if((System.currentTimeMillis()-topTime)>=1000) {
							cMPPUtils.retryException();
							errorCount = 0;
							exceptionTime=null;
						}
					}
				}
			}
		}
	}

	/**
	 * 从短信网关接收短息
	 * @return
	 */
	public MsgRespThreadBean receptionISMGMsg() {
		byte[] data=null;
		MsgRespThreadBean bean=null;
		try {
			synchronized (cMPPSocketService.getInputStream()) {
				InputStream in = cMPPSocketService.getInputStream();
				DataInputStream dis = new DataInputStream(in);
				int totalLength = dis.readInt();
				if (0 == totalLength) {
					return null;
				}
				logger.info("移动网关有消息响应");
				bean=new MsgRespThreadBean();
				data = new byte[totalLength - 4];
				dis.readFully(data);
				bean.setByteMsg(data);
				bean.setTotalLength(totalLength);
			}
		} catch (Exception e) {
			logger.error("从短信网关接收短息异常={}",ExceptionUtils.getFullStackTrace(e));
		}
		return bean;
	}

	public void CmppResponse(MsgRespThreadBean bean) throws Exception {
		Integer totalLength=bean.getTotalLength();
		InputStream ins = new ByteArrayInputStream(bean.getByteMsg());
		DataInputStream diss = new DataInputStream(ins);
		int commandId = diss.readInt();
		int sequenceId = diss.readInt();

		MsgRespThreadBean respThreadBean = new MsgRespThreadBean();
		respThreadBean.setCommandId(commandId);
		respThreadBean.setTotalLength(totalLength);
		respThreadBean.setSequenceId(sequenceId);
		respThreadBean.setDis(diss);
		String printInfo = "";
		switch (commandId) {
		case CommandIdConstans.CMPP_CONNECT_RESP:
			printInfo = resultResolveUtlis.cmppConnectResp(respThreadBean);
			break;
		case CommandIdConstans.CMPP_ACTIVE_TEST_RESP:
			printInfo = resultResolveUtlis.CmppActiveConnResp(respThreadBean);
			break;
		case CommandIdConstans.CMPP_SUBMIT_RESP:
			printInfo = resultResolveUtlis.cmppSubmitResp(respThreadBean);
			break;
		case CommandIdConstans.CMPP_DELIVER:
			MessageDeliverBean deliverBean = resultResolveUtlis.cmppDeliver(respThreadBean);
			StringBuffer deliverBuffer = new StringBuffer("totalLength=").append(totalLength).append(", commonId=")
					.append(commandId).append(", sequenceId=").append(sequenceId).append(", msgId=")
					.append(deliverBean.getMsgId()).append(", 是否是消息回复=");
			if (deliverBean.getRegisteredDelivery() == 0) {// 设备向短信平台发送的消息
				deliverBuffer.append("不是, 消息内容是=").append(deliverBean.getMsgContent()).append(", 来信号码是=").append(deliverBean.getSrcTerminalId());
			} else if (deliverBean.getRegisteredDelivery() == 1) {// imsg 向短信平台回复消息
				deliverBuffer.append("是, 发送短信的应答结果=").append(deliverBean.getMssageDeliverReportBean().getStat())
						.append(", 消息内容是=").append(deliverBean.getMsgContent()).append(", 目的手机号是=")
						.append(deliverBean.getMssageDeliverReportBean().getDestTerminalId());
			} else {
				deliverBuffer.append("状态报告为未知类型");
			}
			logger.info(deliverBuffer.toString());
			// 发送给移动短息网关响应
			cMPPClientService.cmppDdeliverResp(respThreadBean);
			break;
		default:
			logger.error("其他类型不予处理,commonId=", commandId);
		}
		logger.error(printInfo);
	}

}
