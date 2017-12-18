package so.dian.cmpp.constant;

public interface CMPPConstants {
	//短信网关ip,也就是服务端ip
	 public static final String smsGatewayIp="sms_gateway_ip";
	//短信网关端口
	 public static final String smsGatewayReport="sms_gateway_report";
	 //短信接入码
	 public static final String smsAccessCodes="sms_access_codes";
	 //企业代码
	 public static final String spId="sp_id";
	 //业务代码
	 public static final String businessCode="business_code";
	 //应用平台ip（客户端机器ip,必须要提供一个平台ip，这个ip是配置到短信接入参数里面的，用于网关校验）
	 public static final String applicationPlatformId ="application_platformId";
	 //短信流量限制
	 public static final String smsFolwConfine="sms_folw_confine";
	 //业务网管登录用户名
	 public static final String businessUserName="business_user_name";
	 //业务网关登录密码
	 public static final String businessUserPwd="business_user_pwd";
	 //cmpp版本
	 public static final byte cmppVersion=3;
	 //每秒发送的消息次数
	 public static final int sendCount=13;
	 
}
