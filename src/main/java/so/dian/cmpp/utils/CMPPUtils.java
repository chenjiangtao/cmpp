package so.dian.cmpp.utils;

public class CMPPUtils {
	
	
	public static int seq = 0;
	/**
	 * 获取消息流水号
	 * 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）
	 * @return
	 */
	 public static int getSequenceId() {
		 
		 synchronized (CMPPUtils.class) {
			 seq++;
				if (seq >= Integer.MAX_VALUE)
					seq = 1;
		}
		return seq;
	}

	
}
