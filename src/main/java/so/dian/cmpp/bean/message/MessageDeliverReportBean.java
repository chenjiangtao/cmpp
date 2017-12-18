package so.dian.cmpp.bean.message;

public class MessageDeliverReportBean {
    /**
     * 信息标识。
     * SP提交短信（CMPP_SUBMIT）操作时，与SP相连的ISMG产生的Msg_Id
     */
    private long msgId;//[8]
    /**
     * 发送短信的应答结果，含义详见表一。SP根据该字段确定CMPP_SUBMIT消息的处理状态
     */
    private String stat;//[7]
    /**
     * YYMMDDHHMM（YY为年的后两位00-99，MM：01-12，DD：01-31，HH：00-23，MM：00-59
     */
    private String submitTime;//[10]
    /**
     * YYMMDDHHM
     */
    private String doneTime;//[10]
    /**
     * 目的终端MSISDN号码(SP发送CMPP_SUBMIT消息的目标终端)
     */
    private String destTerminalId;//[32]
    /**
     * 取自SMSC发送状态报告的消息体中的消息标识
     */
    private Integer sMSCSequence;//[4]

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    public String getDestTerminalId() {
        return destTerminalId;
    }

    public void setDestTerminalId(String destTerminalId) {
        this.destTerminalId = destTerminalId;
    }

    public Integer getsMSCSequence() {
        return sMSCSequence;
    }

    public void setsMSCSequence(Integer sMSCSequence) {
        this.sMSCSequence = sMSCSequence;
    }

    @Override
    public String toString() {
        return "MessageDeliverReportBean [msgId=" + msgId + ", stat=" + stat + ", submitTime=" + submitTime
                + ", doneTime=" + doneTime + ", destTerminalId=" + destTerminalId + ", sMSCSequence=" + sMSCSequence
                + "]";
    }
}
