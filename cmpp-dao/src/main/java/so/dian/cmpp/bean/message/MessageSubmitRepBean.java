package so.dian.cmpp.bean.message;

public class MessageSubmitRepBean extends MessageBean{
	
	private long msgId;
	private int result;
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "MessageSubmitRepBean [msgId=" + msgId + ", result=" + result + ", toString()=" + super.toString() + "]";
	}
}
