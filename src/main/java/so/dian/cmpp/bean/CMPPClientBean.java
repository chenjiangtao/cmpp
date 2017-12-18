package so.dian.cmpp.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CMPPClientBean {

    private String smsGatewayIp;        // 服务端ip
    private int smsGatewayReport;       // 服务端口
    private String businessUserName;    // 鉴权账号
    private String businessUserPwd;     // 鉴权密码
    private String spId;                // 企业代码
    private String businessCode;        // 服务Id
    private String smsAccessCodes;      // 短信接入码，显示到接收手机端的主叫号码

    public String getSmsAccessCodes() {
        return smsAccessCodes;
    }

    @Value("${sms_access_codes}")
    public void setSmsAccessCodes(String smsAccessCodes) {
        this.smsAccessCodes = smsAccessCodes;
    }

    public String getSmsGatewayIp() {
        return smsGatewayIp;
    }

    @Value("${sms_gateway_ip}")
    public void setSmsGatewayIp(String smsGatewayIp) {
        this.smsGatewayIp = smsGatewayIp;
    }

    public int getSmsGatewayReport() {
        return smsGatewayReport;
    }

    @Value("${sms_gateway_report}")
    public void setSmsGatewayReport(int smsGatewayReport) {
        this.smsGatewayReport = Integer.valueOf(smsGatewayReport).intValue();
    }

    public String getBusinessUserName() {
        return businessUserName;
    }

    @Value("${business_user_name}")
    public void setBusinessUserName(String businessUserName) {
        this.businessUserName = businessUserName;
    }

    public String getBusinessUserPwd() {
        return businessUserPwd;
    }

    @Value("${business_user_pwd}")
    public void setBusinessUserPwd(String businessUserPwd) {
        this.businessUserPwd = businessUserPwd;
    }

    public String getSpId() {
        return spId;
    }

    @Value("${sp_id}")
    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    @Value("${business_code}")
    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public CMPPClientBean(String smsGatewayIp, int smsGatewayReport, String businessUserName, String businessUserPwd,
                          String spId, String businessCode) {
        super();
        this.smsGatewayIp = smsGatewayIp;
        this.smsGatewayReport = smsGatewayReport;
        this.businessUserName = businessUserName;
        this.businessUserPwd = businessUserPwd;
        this.spId = spId;
        this.businessCode = businessCode;
    }

    public CMPPClientBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "CMPPClientBean [smsGatewayIp=" + smsGatewayIp + ", smsGatewayReport=" + smsGatewayReport
                + ", businessUserName=" + businessUserName + ", businessUserPwd=" + businessUserPwd + ", spId=" + spId
                + ", businessCode=" + businessCode + ",smsAccessCodes=" + smsAccessCodes
                + "]";
    }
}
