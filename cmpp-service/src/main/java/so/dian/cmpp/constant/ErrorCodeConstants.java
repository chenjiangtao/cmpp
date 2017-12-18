package so.dian.cmpp.constant;
/**
 * 错误码定义
 * @author ningque
 *
 */
public interface ErrorCodeConstants {
	
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
	  *
	  */
	 public  interface status {
		 public static final String status_0=	"0"; 
		 public static final String status_1=	"1";
		 public static final String status_2=	"2";
		 public static final String status_3=	"3";
		 public static final String status_4=	"4";
	 }
	 /**
	  * cmpp_submit 错误码
	  * @author ningque
	  *
	  */
	 public interface submit_staut_remark{
		 public static final String submit_status_0=	"消息发送成功";
		 public static final String submit_status_1=	"消息发送失败,消息结构错";
		 public static final String submit_status_2=	"消息发送失败,命令字错";
		 public static final String submit_status_3=	"消息发送失败消息序号重复,消息序号重复";
		 public static final String submit_status_4=	"消息发送失败,消息长度错";
		 public static final String submit_status_5=	"消息发送失败,资费代码错";
		 public static final String submit_status_6=	"消息发送失败,超过最大信息长";
		 public static final String submit_status_7=	"消息发送失败,业务代码错";
		 public static final String submit_status_8=	"消息发送失败,流量控制错";
		 public static final String submit_status_9=	"消息发送失败,本网关不负责服务此计费号码";
		 public static final String submit_status_10=	"消息发送失败,Src_Id错误";
		 public static final String submit_status_11=	"消息发送失败,Msg_src错误";
		 public static final String submit_status_12=	"消息发送失败,Fee_terminal_Id错误";
		 public static final String submit_status_13=	"消息发送失败,Dest_terminal_Id错误";
	 }
	 /**
	  * cmpp_connect 错误码
	  * @author ningque
	  *
	  */
	 public interface connect_staut_remark{
		 public static final String connect_status_0=	"连接移动短信网关成功"; 
		 public static final String connect_status_1=	"连接移动短信网关失败,消息结构错"; 
		 public static final String connect_status_2=	"连接移动短信网关失败,非法源地址"; 
		 public static final String connect_status_3=	"连接移动短信网关失败,认证错"; 
		 public static final String connect_status_4=	"连接移动短信网关失败,版本太高";
		 public static final String connect_status_5=	"连接移动短信网关失败,其他错误"; 
		 public static final String connect_status_160=	"连接移动短信网关失败,平台发起请求登录的出口ip错误"; 
	 }
	 

}
