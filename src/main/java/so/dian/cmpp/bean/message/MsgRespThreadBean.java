package so.dian.cmpp.bean.message;

import java.io.DataInputStream;

public class MsgRespThreadBean extends MessageBean{
	
	private DataInputStream dis;
	
	//cmpp_delvier
	private Integer status;
	private long msgId;

	public DataInputStream getDis() {
		return dis;
	}

	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	
	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "MsgRespThreadBean [dis=" + dis + ", status=" + status + ", msgId=" + msgId + ", toString()="
				+ super.toString() + "]";
	}

}
