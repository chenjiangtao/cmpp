package so.dian.cmpp.bean.message;

/**
 * SP向ISMG提交短信（CMPP_SUBMIT）bean
 * <p>
 * 字段 字节数 字段描述
 *
 * @author ningque
 */
public class MessageSubmitBean extends MessageBean {
    private int msgId;//8 信息标识。
    private byte pkTotal;//1 相同MsgId的信息总条数，从1开始。
    private byte pkNumber;//1 Unsigned byte 相同MsgId的信息序号，从1开始。
    private byte registeredDelivery; //1 是否要求返回状态确认报告：0：不需要；1：需要。
    private byte msgLevel;//1 信息级别。
    private String serviceId;//10 业务标识，是数字、字母和符号的组合。
    private byte feeUserType;//计费用户类型字段：0：对目的终端MSISDN计费；1：对源终端MSISDN计费；2：对SP计费；3：表示本字段无效，对谁计费参见FeeterminalId字段。
    private String feeTerminalId;//32 被计费用户的号码，当FeeUserType为3时该值有效，当FeeUserType为0、1、2时该值无意义。
    private byte feeTerminalType;//1 被计费用户的号码类型，0：真实号码；1：伪码。
    private byte tPpId; //1 GSM协议类型。详细是解释请参考GSM03.40中的9.2.3.9。
    private byte tPudhi; //1 GSM协议类型。详细是解释请参考GSM03.40中的9.2.3.23,仅使用1位，右对齐。
    /**
     * 信息格式：
     * 0：ASCII串；
     * 3：短信写卡操作；
     * 4：二进制信息；
     * 8：UCS2编码；
     * 15：含GB汉字。。。。。。
     */
    private byte msgFmt;//1
    private String msgSrc;//6 信息内容来源(SPId)。
    /**
     * 资费类别：
     * 01：对“计费用户号码”免费；
     * 02：对“计费用户号码”按条计信息费；
     * 03：对“计费用户号码”按包月收取信息费。
     */
    private String feeType;//2
    private String feeCode;//6 资费代码（以分为单位）。
    private String valIdTime;//17 存活有效期，格式遵循SMPP3.3协议。
    private String atTime;//17 定时发送时间，格式遵循SMPP3.3协议。
    private String srcId;// 21 源号码。SP的服务代码或前缀为服务代码的长号码, 网关将该号码完整的填到SMPP协议SubmitSM消息相应的sourceaddr字段，该号码最终在用户手机上显示为短消息的主叫号码。
    private byte destUsrtl;//1 接收信息的用户数量(小于100个用户)。
    private String destterminalId;// 32*DestUsrtl 接收短信的MSISDN号码。
    private byte destterminaltype;//1 接收短信的用户的号码类型，0：真实号码；1：伪码。
    private int msgLength;//1 信息长度(MsgFmt值为0时：<160个字节；其它<=140个字节)，取值大于或等于0。
    private String msgContent;// 信息内容。
    private String linkID;//20 点播业务使用的LinkID，非点播类业务的MT流程不使用该字段。

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public byte getPkTotal() {
        return pkTotal;
    }

    public void setPkTotal(byte pkTotal) {
        this.pkTotal = pkTotal;
    }

    public byte getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(byte pkNumber) {
        this.pkNumber = pkNumber;
    }

    public byte getRegisteredDelivery() {
        return registeredDelivery;
    }

    public void setRegisteredDelivery(byte registeredDelivery) {
        this.registeredDelivery = registeredDelivery;
    }

    public byte getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(byte msgLevel) {
        this.msgLevel = msgLevel;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public byte getFeeUserType() {
        return feeUserType;
    }

    public void setFeeUserType(byte feeUserType) {
        this.feeUserType = feeUserType;
    }

    public String getFeeTerminalId() {
        return feeTerminalId;
    }

    public void setFeeTerminalId(String feeTerminalId) {
        this.feeTerminalId = feeTerminalId;
    }

    public byte getFeeTerminalType() {
        return feeTerminalType;
    }

    public void setFeeTerminalType(byte feeTerminalType) {
        this.feeTerminalType = feeTerminalType;
    }

    public byte gettPpId() {
        return tPpId;
    }

    public void settPpId(byte tPpId) {
        this.tPpId = tPpId;
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

    public String getMsgSrc() {
        return msgSrc;
    }

    public void setMsgSrc(String msgSrc) {
        this.msgSrc = msgSrc;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getValIdTime() {
        return valIdTime;
    }

    public void setValIdTime(String valIdTime) {
        this.valIdTime = valIdTime;
    }

    public String getAtTime() {
        return atTime;
    }

    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public byte getDestUsrtl() {
        return destUsrtl;
    }

    public void setDestUsrtl(byte destUsrtl) {
        this.destUsrtl = destUsrtl;
    }

    public String getDestterminalId() {
        return destterminalId;
    }

    public void setDestterminalId(String destterminalId) {
        this.destterminalId = destterminalId;
    }

    public byte getDestterminaltype() {
        return destterminaltype;
    }

    public void setDestterminaltype(byte destterminaltype) {
        this.destterminaltype = destterminaltype;
    }

    public int getMsgLength() {
        return msgLength;
    }

    public void setMsgLength(int msgLength) {
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

    @Override
    public String toString() {
        return "MessageSubmitBean [msgId=" + msgId + ", pkTotal=" + pkTotal + ", pkNumber=" + pkNumber
                + ", registeredDelivery=" + registeredDelivery + ", msgLevel=" + msgLevel + ", serviceId=" + serviceId
                + ", feeUserType=" + feeUserType + ", feeTerminalId=" + feeTerminalId + ", feeTerminalType="
                + feeTerminalType + ", tPpId=" + tPpId + ", tPudhi=" + tPudhi + ", msgFmt=" + msgFmt + ", msgSrc="
                + msgSrc + ", feeType=" + feeType + ", feeCode=" + feeCode + ", valIdTime=" + valIdTime + ", atTime="
                + atTime + ", srcId=" + srcId + ", destUsrtl=" + destUsrtl + ", destterminalId=" + destterminalId
                + ", destterminaltype=" + destterminaltype + ", msgLength=" + msgLength + ", msgContent=" + msgContent
                + ", linkID=" + linkID + ", toString()=" + super.toString() + "]";
    }
}
