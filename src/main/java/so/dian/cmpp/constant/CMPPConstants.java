package so.dian.cmpp.constant;

public interface CMPPConstants {
    //短信网关ip,也就是服务端ip
    public static final String smsGatewayIp = "sms_gateway_ip";
    //短信网关端口
    public static final String smsGatewayReport = "sms_gateway_report";
    //短信接入码
    public static final String smsAccessCodes = "sms_access_codes";
    //企业代码
    public static final String spId = "sp_id";
    //业务代码
    public static final String businessCode = "business_code";
    //应用平台ip（客户端机器ip,必须要提供一个平台ip，这个ip是配置到短信接入参数里面的，用于网关校验）
    public static final String applicationPlatformId = "application_platformId";
    //短信流量限制
    public static final String smsFolwConfine = "sms_folw_confine";
    //业务网管登录用户名
    public static final String businessUserName = "business_user_name";
    //业务网关登录密码
    public static final String businessUserPwd = "business_user_pwd";
    //消息总长度
    // public static final String messageTotalLength="200";
    //cmpp版本
    public static final byte cmppVersion = 3;

    /**
     * reponse 状态定义
     *
     * @author ningque
     * 0：正确
     * 1：消息结构错
     * 2：非法源地址
     * 3：认证错
     * 4：版本太高
     * 5~ ：其他错误
     */
    public interface status {
        public static final String status_0 = "0";
        public static final String status_1 = "1";
        public static final String status_2 = "2";
        public static final String status_3 = "3";
        public static final String status_4 = "4";
    }

    public interface submit_staut_remark {
        public static final String submit_status_0 = "消息发送成功";
        public static final String submit_status_1 = "消息发送失败,消息结构错";
        public static final String submit_status_2 = "消息发送失败,命令字错";
        public static final String submit_status_3 = "消息发送失败消息序号重复,消息序号重复";
        public static final String submit_status_4 = "消息发送失败,消息长度错";
        public static final String submit_status_5 = "消息发送失败,资费代码错";
        public static final String submit_status_6 = "消息发送失败,超过最大信息长";
        public static final String submit_status_7 = "消息发送失败,业务代码错";
        public static final String submit_status_8 = "消息发送失败,流量控制错";
        public static final String submit_status_9 = "消息发送失败,本网关不负责服务此计费号码";
        public static final String submit_status_10 = "消息发送失败,Src_Id错误";
        public static final String submit_status_11 = "消息发送失败,Msg_src错误";
        public static final String submit_status_12 = "消息发送失败,Fee_terminal_Id错误";
        public static final String submit_status_13 = "消息发送失败,Dest_terminal_Id错误";
    }

    public interface connect_staut_remark {
        public static final String connect_status_0 = "连接移动短信网关成功";
        public static final String connect_status_1 = "连接移动短信网关失败,消息结构错";
        public static final String connect_status_2 = "连接移动短信网关失败,非法源地址";
        public static final String connect_status_3 = "连接移动短信网关失败,认证错";
        public static final String connect_status_4 = "连接移动短信网关失败,版本太高";
        public static final String connect_status_5 = "连接移动短信网关失败,其他错误";
        public static final String connect_status_160 = "连接移动短信网关失败,平台发起请求登录的出口ip错误";
    }

}
