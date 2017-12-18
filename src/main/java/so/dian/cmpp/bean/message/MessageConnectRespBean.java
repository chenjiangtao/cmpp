package so.dian.cmpp.bean.message;

public class MessageConnectRespBean extends MessageBean {
    /**
     * 返回的状态,1位 Unsigned Integer 状态0：正确1：消息结构错 2：非法源地址 3：认证错 4：版本太高 5~ ：其他错误
     */
    public int status;

    /**
     * 返回的认证码 16 Octet String ISMG认证码，用于鉴别ISMG。 其值通过单向MD5
     * hash计算得出，表示如下：AuthenticatorISMG =MD5（Status+AuthenticatorSource+shared
     * secret），Shared secret
     * 由中国移动与源地址实体事先商定，AuthenticatorSource为源地址实体发送给ISMG的对应消息CMPP_Connect中的值。
     * 认证出错时，此项为空
     */
    public String authenticaion;

    /**
     * ismg返回的协议版本
     */
    public int version;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAuthenticaion() {
        return authenticaion;
    }

    public void setAuthenticaion(String authenticaion) {
        this.authenticaion = authenticaion;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MessageConnectRespBean [status=" + status + ", authenticaion=" + authenticaion + ", version=" + version
                + ", toString()=" + super.toString() + "]";
    }

}
