package so.dian.cmpp.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import so.dian.cmpp.bean.message.MessageBean;
import so.dian.cmpp.bean.message.MessageConnectBean;
import so.dian.cmpp.bean.message.MessageDeliverRespBean;
import so.dian.cmpp.bean.message.MessageSubmitBean;
import so.dian.cmpp.bean.message.MessageSubmitRepBean;
import so.dian.cmpp.constant.CMPPConstants;
import so.dian.cmpp.constant.CommandIdConstans;
import so.dian.cmpp.thread.CmppActiveTestThread;
import so.dian.cmpp.thread.CmppResponseThread;
/**
 * 将消息对象 根据 cmpp协议 转为移动网关的消息包
 * @author ningque
 *
 */
@Component
public class MessageUtils {

	private static Logger logger = LoggerFactory.getLogger(MessageUtils.class);

	@Autowired
	CmppActiveTestThread activeTestThread;
	@Autowired
	CmppResponseThread cmppResponseThread;
	@Autowired
	ByteUtils byteUtils;

	/**
	 * 将消息bean 转为 字节
	 * 
	 * @param message
	 * @return
	 */
	public  byte[] toBytes(MessageBean message) {

		byte[] b = new byte[message.getTotalLength()];
		ByteBuffer bb = ByteBuffer.wrap(b, 0, message.getTotalLength());

		switch (message.getCommandId()) {

		case CommandIdConstans.CMPP_CONNECT://登录类型消息
			MessageConnectBean connectMessage = (MessageConnectBean) message;
			logger.info("开始发送 CMPP_CONNECT 消息={}", connectMessage.toString());
			bb.putInt(connectMessage.getTotalLength());// [4]
			bb.putInt(connectMessage.getCommandId());// [4]
			bb.putInt(connectMessage.getSequenceId());// [4]

			bb.put(connectMessage.getSourceAddr().getBytes());
			bb.put(connectMessage.getAuthenticatorSource());
			bb.put(connectMessage.getVersion());
			bb.putInt(connectMessage.getTimestamp());
			break;
		case CommandIdConstans.CMPP_ACTIVE_TEST://链路检测类型消息
			bb.putInt(message.getTotalLength());
			bb.putInt(message.getCommandId());
			bb.putInt(message.getSequenceId());
			logger.info("开始发送 CMPP_ACTIVE_TEST 消息={}", message.toString());
			break;
		case CommandIdConstans.CMPP_SUBMIT://sp 向 短信网关发送消息
			MessageSubmitBean submitMessage = (MessageSubmitBean) message;
			logger.info("开始发送 CMPP_SUBMIT 消息={}", submitMessage.toString());
			bb.putInt(submitMessage.getTotalLength().intValue());// [4]
			bb.putInt(submitMessage.getCommandId().intValue());// [4]
			bb.putInt(submitMessage.getSequenceId().intValue());// [4]

			byte[] msgIdByte = byteUtils.writeLong(submitMessage.getMsgId());
			bb.put(msgIdByte);// [8]

			bb.put(submitMessage.getPkTotal());// [1]
			bb.put(submitMessage.getPkNumber());// [1]
			bb.put(submitMessage.getRegisteredDelivery());// [1]
			bb.put(submitMessage.getMsgLevel());// [1]
			byte[] serviceIdByte = byteUtils.writeFully(10, submitMessage.getServiceId());
			bb.put(serviceIdByte);// [10]
			bb.put(submitMessage.getFeeUserType());// [1]
			byte[] feeTerminalId = byteUtils.writeFully(32, submitMessage.getFeeTerminalId());
			bb.put(feeTerminalId);// [32]
			bb.put(submitMessage.getFeeTerminalType());// [1]
			bb.put(submitMessage.gettPpId());// [1]
			bb.put(submitMessage.gettPudhi());// [1]
			bb.put(submitMessage.getMsgFmt());// [1]

			byte[] msgSrcByte = byteUtils.writeFully(6, submitMessage.getMsgSrc());
			bb.put(msgSrcByte);// [6]

			byte[] feeTypeByte = byteUtils.writeFully(2, submitMessage.getFeeType());
			bb.put(feeTypeByte);// [2]

			byte[] feeCodeByte = byteUtils.writeFully(6, submitMessage.getFeeCode());
			bb.put(feeCodeByte);// [6]

			byte[] valIdTimeByte = byteUtils.writeFully(17, submitMessage.getValIdTime());
			bb.put(valIdTimeByte);// [17]

			byte[] atTimeByte = byteUtils.writeFully(17, submitMessage.getAtTime());
			bb.put(atTimeByte);// [17]

			byte[] srcIdByte = byteUtils.writeFully(21, submitMessage.getSrcId());
			bb.put(srcIdByte);// [21]

			bb.put(submitMessage.getDestUsrtl());// [1]

			byte[] destterminalIdByte = byteUtils.writeFully(32, submitMessage.getDestterminalId());
			bb.put(destterminalIdByte);// [32*DestUsr_tl]

			bb.put(submitMessage.getDestterminaltype());// [1]
			bb.put((byte) submitMessage.getMsgLength());// [1]

			byte[] msgContentByte = byteUtils.writeFully(submitMessage.getMsgLength(),
					submitMessage.getMsgContent());
			bb.put(msgContentByte);// [msgLength]
			byte[] linkIDByte = byteUtils.writeFully(20, submitMessage.getLinkID());
			bb.put(linkIDByte);// [20]
			break;
		case CommandIdConstans.CMPP_DELIVER_RESP://sp 向 短信网关发送响应消息
			MessageDeliverRespBean deliverRespBean = (MessageDeliverRespBean) message;
			logger.info("开始发送 CMPP_DELIVER_RESP 消息={}", deliverRespBean.toString());
			bb.putInt(deliverRespBean.getTotalLength());//[4]
			bb.putInt(deliverRespBean.getCommandId());//[4]
			bb.putInt(deliverRespBean.getSequenceId());//[4]
			bb.putLong(deliverRespBean.getMsgId());//[8]
			bb.putInt(deliverRespBean.getResult());//[4]
			break;
		}
		return b;
	}

	/**
	 * 构造CMPP_CONNECT 协议数据包
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	public  MessageBean getCmppConnect(String spid, String pwd, byte version) throws Exception {
		MessageConnectBean connectMessage = new MessageConnectBean();
		// 消息头
		connectMessage.setCommandId(CommandIdConstans.CMPP_CONNECT);
		connectMessage.setSequenceId(CMPPUtils.getSequenceId());
		connectMessage.setTotalLength(4 + 4 + 4 + 6 + 16 + 1 + 4);

		// 消息体
		connectMessage.setSourceAddr(spid);// 设置企业代码[6]

		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
		String timeStamp = sdf.format(new Date());
		Integer timeStamps = Integer.valueOf(timeStamp);
		connectMessage.setTimestamp(timeStamps);// 时间戳[4]

		byte[] bytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
		String authenticator = spid + new String(bytes) + pwd + timeStamps;
		connectMessage.setAuthenticatorSource(byteUtils.getMd5ByteByString(authenticator));// 设置鉴别源地址[16]

		connectMessage.setVersion(version);// 设置版本[1]

		return connectMessage;
	}

	public static String getActiveConnResp(InputStream in) throws Exception {
		DataInputStream dis = new DataInputStream(in);
		int totalLength = dis.readInt();
		byte statut = -1;
		if (0 != totalLength) {
			byte[] data = new byte[totalLength - 4];
			dis.readFully(data);
			InputStream ii = new ByteArrayInputStream(data);
			DataInputStream diss = new DataInputStream(ii);
			int commonId = diss.readInt();
			int squeId = diss.readInt();
			statut = diss.readByte();
			logger.info("链路检测响应报文:totalLength={},commonId={},squeId={},statut={}", totalLength, commonId, squeId,
					statut);
		}
		return String.valueOf(statut);
	}

	public String getConnResp(InputStream in) throws Exception {
		new Thread(activeTestThread).start();
		;
		DataInputStream dis = new DataInputStream(in);
		int totalLength = dis.readInt();
		int statut = -1;
		if (0 != totalLength) {
			byte[] data = new byte[totalLength - 4];
			dis.readFully(data);
			InputStream ii = new ByteArrayInputStream(data);
			DataInputStream diss = new DataInputStream(ii);
			int commonId = byteUtils.readInt(diss);
			int squeId = byteUtils.readInt(diss);
			statut = byteUtils.readInt(diss);
			int authenticatorISMG = byteUtils.readInt(diss);
			int version = byteUtils.readInt(diss);
			logger.info("连接socket响应报文:totalLength={},commonId={},squeId={},statut={},authenticatorISMG={},version={}",
					totalLength, commonId, squeId, statut, authenticatorISMG, version);
		}
		if (CMPPConstants.status.status_0.equals(String.valueOf(statut))) {
			logger.info("登录移动短信网关成功,staut={}", statut);
			CmppActiveTestThread.running = true;
			new Thread(activeTestThread).start();
			CmppResponseThread.running = true;
			new Thread(cmppResponseThread).start();
		} else {
			logger.info("登录移动短信网关失败,staut={}", statut);
			CmppActiveTestThread.running = false;
		}
		return String.valueOf(statut);
	}
	/**
	 * 初始化短信提交到网关信息
	 * 
	 * @return
	 */
	public static MessageSubmitBean intoSubmit(MessageSubmitBean bean) {
		MessageSubmitBean submit = new MessageSubmitBean();
		int sequenceId = CMPPUtils.getSequenceId();
		int msgId = 0;
		byte pkTotal = 1;
		byte pkNumber = 1;
		byte resisteredDelivery = 1;
		byte msgLevel = 1;
		String serviceId = bean.getServiceId();
		byte feeUserType = 0x00;
		String feeTerminalId = "";
		byte feeTerminalType = 0;
		byte tpPid = 0;
		byte tpUdhi = 0;
		byte msgFmt = 0;
		String msgSrc = bean.getMsgSrc();
		String feeType = "01";
		String feeCode = "00030";
		String validTime = "";
		String atTime = "";
		String srcId = bean.getSrcId();
		byte destusrTl = 1;
		String destTerminalId = bean.getDestterminalId();
		byte destTerminalType = 0;
		// 信息内容
		String msgContent = bean.getMsgContent();
		int msgLen = msgContent.getBytes().length;
		String LINKID = "1";
		int totalLen = 12 + 8 + 1 + 1 + 1 + 1 + 10 + 1 + 32 + 1 + 1 + 1 + 1 + 6 + 2 + 6 + 17 + 17 + 21 + 1 + 32 + 1 + 1
				+ msgLen + 20;
		submit.setTotalLength(totalLen + 1);// [4]
		submit.setMsgLength(msgLen);// [4]
		submit.setSequenceId(sequenceId);// [4]
		submit.setCommandId(CommandIdConstans.CMPP_SUBMIT);// [4]
		submit.setMsgId(msgId);
		submit.setPkTotal(pkTotal);
		submit.setPkNumber(pkNumber);
		submit.setRegisteredDelivery(resisteredDelivery);
		submit.setMsgLevel(msgLevel);
		submit.setServiceId(serviceId);
		submit.setFeeUserType(feeUserType);
		submit.setFeeTerminalId(feeTerminalId);
		submit.setFeeTerminalType(feeTerminalType);
		submit.settPpId(tpPid);
		submit.settPudhi(tpUdhi);
		submit.setMsgFmt(msgFmt);
		submit.setMsgSrc(msgSrc);
		submit.setFeeType(feeType);
		submit.setFeeCode(feeCode);
		submit.setValIdTime(validTime);
		submit.setAtTime(atTime);
		submit.setSrcId(srcId);
		submit.setDestUsrtl(destusrTl);
		submit.setDestterminalId(destTerminalId);
		submit.setDestterminaltype(destTerminalType);
		submit.setMsgLength((byte) msgLen);
		submit.setMsgContent(msgContent);
		submit.setLinkID(LINKID);
		return submit;
	}

	public static MessageBean praseMsg(byte[] bytes) throws Exception {
		logger.info("将byte包转为bean 对象");
		MessageSubmitRepBean messageBean = new MessageSubmitRepBean();
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		messageBean.setTotalLength(24);
		messageBean.setCommandId(dis.readInt());
		messageBean.setSequenceId(dis.readInt());
		messageBean.setMsgId(dis.readLong());
		messageBean.setResult(dis.readInt());
		return messageBean;
	}

}
