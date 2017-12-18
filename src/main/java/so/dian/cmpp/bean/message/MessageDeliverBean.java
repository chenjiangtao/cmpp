package so.dian.cmpp.bean.message;

public class MessageDeliverBean extends MessageBean {

    /**
     * 信息标识。
     * 生成算法如下：
     * 采用64位（8字节）的整数：
     * 时间（格式为MMDDHHMMSS，即月日时分秒）：bit64~bit39，其中
     * bit64~bit61：月份的二进制表示；
     * bit60~bit56：日的二进制表示；
     * bit55~bit51：小时的二进制表示；
     * bit50~bit45：分的二进制表示；
     * bit44~bit39：秒的二进制表示；
     * 短信网关代码：bit38~bit17，把短信网关的代码转换为整数填写到该字段中；
     * 序列号：bit16~bit1，顺序增加，步长为1，循环使用。
     * 各部分如不能填满，左补零，右对齐。
     */
    private long msgId;//[8]
    /**
     * 目的号码。
     * SP的服务代码，一般4--6位，或者是前缀为服务代码的长号码；该号码是手机用户短消息的被叫号码
     */
    private String destId;//[21]

    //业务标识，是数字、字母和符号的组合。
    private String serviceId;//[10]

    private byte tPpid;//[1] GSM协议类型。

    private byte tPudhi;//[1] GSM协议类型
    /**
     * 0：ASCII串；
     * 3：短信写卡操作；
     * 4：二进制信息；
     * 8：UCS2编码；
     * 15：含GB汉字
     */
    private byte msgFmt;//[1]

    private String srcTerminalId;//[32] 源终端MSISDN号码（状态报告时填为CMPP_SUBMIT消息的目的终端号码)

    private byte srcTerminalType;//[1] 源终端号码类型，0：真实号码；1：伪码。
    /**
     * 是否为状态报告：
     * 0：非状态报告；
     * 1：状态报告
     */
    private byte registeredDelivery;//[1]

    private byte msgLength;//[1] 消息长度，取值大于或等于0。

    private String msgContent;//[MsgLength] 消息内容。

    private String linkID;//[20] 点播业务使用的LinkID，非点播类业务的MT流程不使用该字段

    private MessageDeliverReportBean mssageDeliverReportBean;


    public long getMsgId() {
        return msgId;
    }


    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }


    public String getDestId() {
        return destId;
    }


    public void setDestId(String destId) {
        this.destId = destId;
    }


    public String getServiceId() {
        return serviceId;
    }


    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }


    public byte gettPpid() {
        return tPpid;
    }


    public void settPpid(byte tPpid) {
        this.tPpid = tPpid;
    }


    public byte gettPudhi() {
        return tPudhi;
    }


    public void settPudhi(byte tPudhi) {
        this.tPudhi = tPudhi;
    }


    public byte getMsgFmt() {
        return msgFmt;
    }


    public void setMsgFmt(byte msgFmt) {
        this.msgFmt = msgFmt;
    }


    public String getSrcTerminalId() {
        return srcTerminalId;
    }


    public void setSrcTerminalId(String srcTerminalId) {
        this.srcTerminalId = srcTerminalId;
    }


    public byte getSrcTerminalType() {
        return srcTerminalType;
    }


    public void setSrcTerminalType(byte srcTerminalType) {
        this.srcTerminalType = srcTerminalType;
    }


    public byte getRegisteredDelivery() {
        return registeredDelivery;
    }


    public void setRegisteredDelivery(byte registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }


    public byte getMsgLength() {
        return msgLength;
    }


    public void setMsgLength(byte msgLength) {
        this.msgLength = msgLength;
    }


    public String getMsgContent() {
        return msgContent;
    }


    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }


    public String getLinkID() {
        return linkID;
    }


    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }


    public MessageDeliverReportBean getMssageDeliverReportBean() {
        return mssageDeliverReportBean;
    }


    public void setMssageDeliverReportBean(MessageDeliverReportBean mssageDeliverReportBean) {
        this.mssageDeliverReportBean = mssageDeliverReportBean;
    }


    @Override
    public String toString() {
        return "MessageDeliverBean [msgId=" + msgId + ", destId=" + destId + ", serviceId=" + serviceId + ", tPpid="
                + tPpid + ", tPudhi=" + tPudhi + ", msgFmt=" + msgFmt + ", srcTerminalId=" + srcTerminalId
                + ", srcTerminalType=" + srcTerminalType + ", registered_Delivery=" + registeredDelivery
                + ", msgLength=" + msgLength + ", msgContent=" + msgContent + ", linkID=" + linkID
                + ", mssageDeliverReportBean=" + mssageDeliverReportBean.toString() + ", toString()=" + super.toString() + "]";
    }


}
