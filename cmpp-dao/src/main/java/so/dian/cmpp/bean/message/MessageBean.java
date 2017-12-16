package so.dian.cmpp.bean.message;

public abstract class MessageBean {
	/**
	 * 消息头
	 */
	private Integer totalLength;
	private Integer commandId;
	private Integer sequenceId;
	
	public Integer getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(Integer totalLength) {
		this.totalLength = totalLength;
	}
	public Integer getCommandId() {
		return commandId;
	}
	public void setCommandId(Integer commandId) {
		this.commandId = commandId;
	}
	public Integer getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	@Override
	public String toString() {
		return "MessageBean [totalLength=" + totalLength + ", commandId=" + commandId + ", sequenceId=" + sequenceId
				+ "]";
	}
}
