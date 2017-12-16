package so.dian.cmpp.bean;

public class MessageSendSmsBean {
	//设备Id、开关("1":true)、仓位("126":num)
	//{"protocol":5,"t":1504540796,"data":{"devId":"b129ad7866873023044855","dps":{"1":true,"126":4}}}
	private String devId;//设备编号
	private boolean trun;//是否打开
	private String billId;//发送消息的目标号码
	
	private int squeId;//发送消息的sque编号
	private String msgContext;//发送的消息内容
	private int soltId;
	
	public int getSoltId() {
		return soltId;
	}

	public void setSoltId(int soltId) {
		this.soltId = soltId;
	}

	public String getMsgContext() {
		return msgContext;
	}

	public void setMsgContext(String msgContext) {
		this.msgContext = msgContext;
	}

	public int getSqueId() {
		return squeId;
	}

	public void setSqueId(int squeId) {
		this.squeId = squeId;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public boolean isTrun() {
		return trun;
	}

	public void setTrun(boolean trun) {
		this.trun = trun;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	@Override
	public String toString() {
		return "MessageSendSmsBean [devId=" + devId + ", trun=" + trun + "]";
	}
}
