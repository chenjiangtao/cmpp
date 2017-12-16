package so.dian.cmpp.constant;

import java.util.regex.Pattern;

public class DeviceConstant {
	
	public static final Pattern QRCodeUrl = Pattern.compile("^(http|https)://(b|bdev|bpre).dian.so/lhc/(d|b|p|c|l)/.+$");
    public static final Pattern QRCodeUrlSpliter = Pattern.compile("^(http|https)://(b|bdev|bpre).dian.so/lhc/");

	
	 public static final Integer MSG_DOWN = 5;//消息类型---消息下发
	 
	 public static final String SCHEMA_COUNT_DOWN = "6";
	 public static final String SCHEMA_POSITION_ID = "126";
	 public static final Integer STATUS_AVAILABLE = 0; // 在线
	 //盒子类型 deiviceInfo-device_type
	 public static final Integer TYPE_BOX = 8; // 小电盒子版本
	 public static final Integer TYPE_BOX_2G = 8; // 小电盒子(2G)
	 public static final Integer TYPE_BOX_4G = 18;  // 小电盒子(4G)
	 public static final Integer TYPE_VEHICLE_BOX = 14; // 车载盒子版本
	 
	 //厂测
	 public static final String SCHEMA_SWITCH = "1";//消息下发-开/关
	 public static final Integer STATUS_FAIL = 1;//扫码失败
	 public static final Integer STATUS_CHECK_NOITFY = 2;//绑定成功，需确认状态
	 public static final Integer STATUS_BOX_ACCEPT = 3;//已经绑定盒子，可以执行下一步
	 public static final Integer STATUS_VEHICLE_BOX_ACCEPT = 4;//已经绑定车载盒子，可以执行下一步
	 public static final String PROTOCOL_VER_BOX = "3.0";
	 
	 //设备类型
	 public static final Integer XD_DESKTOP = 1;//座充
	 public static final Integer XD_BOX = 2;//盒子
	 public static final Integer XD_VEHICLE_BOX = 4;
	 
	 public static final Integer XD_POWERBANK = 3;
	 public static final Integer TYPE_BLUETOOTH = 16;  // 蓝牙版
	 public static final Integer TYPE_BLUETOOTH_Z3 = 19;  // 蓝牙版(三代)
	 
	 
	 public static Integer POWER_TYPE_DC=0; // 直流电源供电
	 public static Integer POWER_TYPE_AC=1; // 交流电源供电

	 public static Integer STATUS_DELETED = -1; // 删除
	 public static Integer STATUS_IN_USE = 1; // 使用中
	 public static Integer STATUS_OFF_LINE = 2; // 离线
	 public static Integer STATUS_OFF_LINE_IN_USE = 3; // 使用中离线
	 public static Integer STATUS_UNREGISTER = 4; // 未注册
	 public static Integer STATUS_UNACTIVATED = 5; // 未激活
	 public static Integer STATUS_FAULT = 6; // 设备故障

}
