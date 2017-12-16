package so.dian.cmpp.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import so.dian.cmpp.annotation.MeidaRequestData;
import so.dian.cmpp.bean.MessageSendSmsBean;
import so.dian.cmpp.service.CMPPClientService;
/**
 * 消息发送层，提供http接口
 * @author ningque
 *
 */
@RestController
public class SendMsmController {
	private static Logger logger = LoggerFactory.getLogger(SendMsmController.class);
	@Autowired
	CMPPClientService clientService;
	/**
	 * 发送消息
	 * @param version cmpp 版本
	 * @return
	 */
	@RequestMapping(value = "/{version}/sendSms")
	public String sendSms(@PathVariable String version,@MeidaRequestData MessageSendSmsBean bean) {
		String devId=bean.getDevId();
		String billId=bean.getBillId();
		if(StringUtils.isBlank(devId)) {
			return "设备编号不能为空";
		}
		if(StringUtils.isBlank(billId)) {
			return "发送的目标号码不能为空";
		}
		try {
			clientService.sendNotifySms(bean);
		} catch (Exception e) {
			logger.error("发送消息失败,目标号码={},设备编号={},异常信息={}",billId,bean.getDevId(),ExceptionUtils.getFullStackTrace(e));
			return "";
		}
		return "";
	}
	

}
