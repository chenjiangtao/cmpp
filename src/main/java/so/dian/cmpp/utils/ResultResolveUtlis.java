package so.dian.cmpp.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import so.dian.cmpp.bean.message.MessageActiveRespBean;
import so.dian.cmpp.bean.message.MessageBean;
import so.dian.cmpp.bean.message.MessageConnectRespBean;
import so.dian.cmpp.bean.message.MessageDeliverBean;
import so.dian.cmpp.bean.message.MessageDeliverReportBean;
import so.dian.cmpp.bean.message.MessageSubmitRepBean;
import so.dian.cmpp.bean.message.MsgRespThreadBean;
import so.dian.cmpp.constant.CMPPConstants;
import so.dian.cmpp.constant.CommandIdConstans;
import so.dian.cmpp.thread.CmppActiveTestThread;

/**
 * 对返回的结果进行处理
 * 
 * @author ningque
 *
 */
@Component
public class ResultResolveUtlis {

	private static Logger logger = LoggerFactory.getLogger(ResultResolveUtlis.class);

	@Autowired
	CmppActiveTestThread activeTestThread;
	
	@Autowired
	ByteUtils byteUtils;

	/**
	 * 0：正确 1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误
	 * 
	 * @param result
	 */
	public static String getMsgConnectRemark(int status) {
		String result = "";
		switch (status) {
		case 0:
			result = CMPPConstants.connect_staut_remark.connect_status_0;
			break;
		case 1:
			result = CMPPConstants.connect_staut_remark.connect_status_1;
			break;
		case 2:
			result = CMPPConstants.connect_staut_remark.connect_status_2;
			break;
		case 3:
			result = CMPPConstants.connect_staut_remark.connect_status_3;
			break;
		case 4:
			result = CMPPConstants.connect_staut_remark.connect_status_4;
			break;
		case 5:
			result = CMPPConstants.connect_staut_remark.connect_status_5;
			break;
		case 160:
			result = CMPPConstants.connect_staut_remark.connect_status_160;
			break;
		default:
			result = CMPPConstants.connect_staut_remark.connect_status_5;
			break;
		}
		return result;
	}

	/**
	 * 结果： 0：正确； 1：消息结构错； 2：命令字错； 3：消息序号重复； 4：消息长度错； 5：资费代码错； 6：超过最大信息长； 7：业务代码错；
	 * 8：流量控制错； 9：本网关不负责服务此计费号码； 10：Src_Id错误； 11：Msg_src错误； 12：Fee_terminal_Id错误；
	 * 13：Dest_terminal_Id错误；
	 * 
	 * @param status
	 */
	public static String getMsgSubmitRemark(int status) {
		String result = "";
		switch (status) {
		case 0:
			result = CMPPConstants.submit_staut_remark.submit_status_0;
			break;
		case 1:
			result = CMPPConstants.submit_staut_remark.submit_status_1;
			break;
		case 2:
			result = CMPPConstants.submit_staut_remark.submit_status_2;
			break;
		case 3:
			result = CMPPConstants.submit_staut_remark.submit_status_3;
			break;
		case 4:
			result = CMPPConstants.submit_staut_remark.submit_status_4;
			break;
		case 5:
			result = CMPPConstants.submit_staut_remark.submit_status_5;
			break;
		case 6:
			result = CMPPConstants.submit_staut_remark.submit_status_6;
			break;
		case 7:
			result = CMPPConstants.submit_staut_remark.submit_status_7;
			break;
		case 8:
			result = CMPPConstants.submit_staut_remark.submit_status_8;
			break;
		case 9:
			result = CMPPConstants.submit_staut_remark.submit_status_9;
			break;
		case 10:
			result = CMPPConstants.submit_staut_remark.submit_status_10;
			break;
		case 11:
			result = CMPPConstants.submit_staut_remark.submit_status_11;
			break;
		case 12:
			result = CMPPConstants.submit_staut_remark.submit_status_12;
			break;
		case 13:
			result = CMPPConstants.submit_staut_remark.submit_status_13;
			break;
		default:
			result = "其他错误";
			break;
		}
		return result;
	}
    /**
     * 对响应消息进行统一结果打印
     * @param bean
     * @return
     */
	public static String cmppMessageResult(MessageBean bean) {
		String result = "";
		int status = -1;
		StringBuffer stringBuffer = new StringBuffer("最终返回结果:");

		switch (bean.getCommandId()) {
		case CommandIdConstans.CMPP_CONNECT_RESP:
			MessageConnectRespBean messageConnectRespBean = (MessageConnectRespBean) bean;
			status = messageConnectRespBean.getStatus();
			result = getMsgConnectRemark(status);
			break;
		case CommandIdConstans.CMPP_ACTIVE_TEST_RESP:
			MessageActiveRespBean beaneActiveRespBean=(MessageActiveRespBean) bean;
			status=beaneActiveRespBean.getStatu();
			result=status==0?"链路检测成功":"链路检测失败";
			break;
		case CommandIdConstans.CMPP_SUBMIT_RESP:
			MessageSubmitRepBean messageSubmitRepBean = (MessageSubmitRepBean) bean;
			status = messageSubmitRepBean.getResult();
			result = getMsgSubmitRemark(status);
			break;
		default:
			result = "其他类型不予处理, commonId="+bean.getCommandId();
		}
		stringBuffer.append(result).append(", commondId").append(bean.getCommandId()).append(", status=").append(status);
		return stringBuffer.toString();
	}

	/**
	 * 登录响应消息解析
	 * @param respThreadBean
	 * @throws Exception
	 */
	public String cmppConnectResp(MsgRespThreadBean respThreadBean) throws Exception {
		DataInputStream dis=respThreadBean.getDis();
		int statut = dis.readInt();
		int authenticatorISMG = dis.readInt();
		int version = dis.readInt();
		int totalLength=respThreadBean.getTotalLength();
		int commandId=respThreadBean.getCommandId();
		int sequenceId=respThreadBean.getSequenceId();
		logger.info("连接socket响应报文:totalLength={}, commonId={}, squeId={}, statut={}, authenticatorISMG={}, version={}",totalLength,commandId, sequenceId, statut, authenticatorISMG, version);
		if (CMPPConstants.status.status_0.equals(String.valueOf(statut))) {
			logger.info("开始启动链路检测线程");
			CmppActiveTestThread.running = true;
			new Thread(activeTestThread).start();
		} else {
			CmppActiveTestThread.running = false;
		}
		MessageConnectRespBean connRepBean=new MessageConnectRespBean();
		connRepBean.setTotalLength(totalLength);
		connRepBean.setCommandId(commandId);
		connRepBean.setSequenceId(sequenceId);
		connRepBean.setStatus(statut);
		return cmppMessageResult(connRepBean);
		
	}
	/**
	 * 链路检测消息响应解析
	 * @param respThreadBean
	 * @return
	 * @throws Exception
	 */
	public String CmppActiveConnResp(MsgRespThreadBean respThreadBean)throws Exception {
		DataInputStream dis=respThreadBean.getDis();
		byte statut=dis.readByte();
		MessageActiveRespBean bean=new MessageActiveRespBean();
		bean.setTotalLength(respThreadBean.getTotalLength());
		bean.setCommandId(respThreadBean.getCommandId());
		bean.setSequenceId(respThreadBean.getSequenceId());
		bean.setStatu(statut);
		return cmppMessageResult(bean);
	}
	/**
	 * ismg 向 sp发送消息响应解析
	 * @param respThreadBean
	 * @return
	 * @throws Exception
	 */
	public String cmppSubmitResp(MsgRespThreadBean respThreadBean)throws Exception{
		logger.info("将byte包转为bean 对象");
		DataInputStream dis=respThreadBean.getDis();
		MessageSubmitRepBean bean=new MessageSubmitRepBean();
		bean.setTotalLength(respThreadBean.getTotalLength());
		bean.setCommandId(respThreadBean.getCommandId());
		bean.setSequenceId(respThreadBean.getSequenceId());
		bean.setMsgId(dis.readLong());
		bean.setResult(dis.readInt());
		return cmppMessageResult(bean);
	}
	/**
	 * sp 向 移动短信网关发送响应消息解析
	 * 该是 sp 向ismg发送消息，ismg收到消息后会再给sp 消息响应 这时候会调用此方法
	 * @param respThreadBean
	 * @return
	 * @throws Exception
	 */
	public MessageDeliverBean cmppDeliver(MsgRespThreadBean respThreadBean)throws Exception {
		MessageDeliverBean bean=new MessageDeliverBean();
		DataInputStream dis=respThreadBean.getDis();
		
		bean.setTotalLength(respThreadBean.getTotalLength());
		bean.setCommandId(respThreadBean.getCommandId());
		bean.setSequenceId(respThreadBean.getSequenceId());
		long msgId=dis.readLong();
		bean.setMsgId(msgId);//[8]
		
		bean.setDestId(byteUtils.getStringByByte(21, dis));//[21]
		bean.setServiceId(byteUtils.getStringByByte(10, dis));//[10]
		bean.settPpid(dis.readByte());//[1]
		bean.settPudhi(dis.readByte());//[1]
		byte msgF=dis.readByte();
		bean.setMsgFmt(msgF);//[1]
		bean.setSrcTerminalId(byteUtils.getStringByByte(32, dis));//[32]
		bean.setSrcTerminalType(dis.readByte());//[1]
		byte isReport=dis.readByte();
		bean.setRegisteredDelivery(isReport);//[1]
		
		byte msgLength=dis.readByte();//消息长度
		bean.setMsgLength(msgLength);//[1]
		
		byte[] msgContextByte=new byte[msgLength];//msgContent
		dis.readFully(msgContextByte);
		bean.setLinkID(byteUtils.getStringByByte(20, dis));//[20]
		
		String formart=msgF==8?"utf-8":"gbk";
		logger.info("sequenceId={}, 是否是报告状态="+(isReport==0?"不是":"是,totalLength={},msgContextLength={}"),respThreadBean.getSequenceId(),respThreadBean.getTotalLength(),msgLength);
		if(0==isReport) {//非报告
			bean.setMsgContent(new String(msgContextByte, formart));
			respThreadBean.setStatus(0);//消息正确 对移动短信网关的响应
		}else if(1==isReport) {//报告状态
			if(msgLength==8+7+10+10+32+4) {
				MessageDeliverReportBean reportBean=new MessageDeliverReportBean();
				InputStream reportIn=new ByteArrayInputStream(msgContextByte);
				DataInputStream reportDis=new DataInputStream(reportIn);
				reportBean.setMsgId(reportDis.readLong());
				reportBean.setStat(byteUtils.getStringByByte(7, reportDis,formart));
				reportBean.setSubmitTime(byteUtils.getStringByByte(10, reportDis, formart));
				reportBean.setDoneTime(byteUtils.getStringByByte(10, reportDis, formart));
				reportBean.setDestTerminalId(byteUtils.getStringByByte(32, reportDis, formart));
				reportBean.setsMSCSequence(reportDis.readInt());
				bean.setMssageDeliverReportBean(reportBean);
				respThreadBean.setStatus(0);//消息正确 对移动短信网关的响应
				reportDis.close();
				reportIn.close();
			}else {
				logger.error("消息结构错误");
				respThreadBean.setStatus(1);//消息结构错误 对移动短信网关的响应
			}
		}else {
			logger.error("未知的报告状态");
		}
		respThreadBean.setMsgId(msgId);//对移动短信网关的响应
		
		return bean;
	}
}
