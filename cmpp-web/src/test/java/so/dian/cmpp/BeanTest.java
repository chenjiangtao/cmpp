package so.dian.cmpp;

import so.dian.cmpp.bean.message.MessageActiveRespBean;

public class BeanTest {
	
	public static void main(String[] args) {
		MessageActiveRespBean bean=new MessageActiveRespBean();
		BeanTest t=new BeanTest();
		String r=t.a(bean);
		System.out.println(r);
		System.out.println(bean.getCommandId()+"========");
		System.out.println(bean.getSequenceId()+"=======");
	}
	public String a(MessageActiveRespBean bean) {
		bean.setCommandId(11111111);
		bean.setSequenceId(342342);
		return "1231";
	}

}
