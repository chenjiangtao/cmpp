package so.dian.cmpp.thread;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import so.dian.cmpp.service.CMPPClientService;
/**
 * 链路检测线程
 * @author ningque
 *
 */
@Component
public class CmppActiveTestThread implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(CmppActiveTestThread.class);

	public static boolean running = false;
	@Autowired
	CMPPClientService clientService;

	@Override
	public void run() {

		while (running) {
			try {
				logger.info("开始链路心跳检测................");
				Thread.sleep(1000*59);
				 clientService.cmppActiveTest();
			} catch (Exception e) {
				logger.info("链路心跳检测异常,异常信息={}", ExceptionUtils.getFullStackTrace(e));
			}
		}

	}
}
