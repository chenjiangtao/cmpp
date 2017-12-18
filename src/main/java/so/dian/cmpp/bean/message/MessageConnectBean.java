package so.dian.cmpp.bean.message;

import java.util.Arrays;

public class MessageConnectBean extends MessageBean {

    /**
     * 消息体
     */
    //源地址，此处为SP_Id，即SP的企业代码
    private String sourceAddr;
    /**
     * 用于鉴别源地址。其值通过单向MD5 hash计算得出，表示如下：
     * AuthenticatorSource =
     * MD5（Source_Addr+9 字节的0 +shared secret+timestamp）
     * Shared secret 由中国移动与源地址实体事先商定，timestamp格式为：MMDDHHMMSS，即月日时分秒，10位。
     */
    private byte[] authenticatorSource;
    //双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号)，对于3.0的版本，高4bit为3，低4位为0
    private byte version;
    //时间戳的明文,由客户端产生,格式为MMDDHHMMSS，即月日时分秒，10位数字的整型，右对齐 。
    private int timestamp;

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public byte[] getAuthenticatorSource() {
        return authenticatorSource;
    }

    public void setAuthenticatorSource(byte[] authenticatorSource) {
        this.authenticatorSource = authenticatorSource;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MessageConnectBean [sourceAddr=" + sourceAddr + ", authenticatorSource="
                + Arrays.toString(authenticatorSource) + ", version=" + version + ", timestamp=" + timestamp
                + ", toString()=" + super.toString() + "]";
    }
}
