package so.dian.cmpp.thread;

import java.io.OutputStream;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import so.dian.cmpp.constant.CMPPConstants;
import so.dian.cmpp.service.CMPPSocketService;
import so.dian.cmpp.utils.MessageUtils;
/**
 * 该进程只用于发送cmpp_submit 类型的消息
 * @author ningque
 *
 */
@Component
public class CmppSubmitMsgThread implements Runnable {

	@Autowired
	MessageUtils messageUtils;

	@Autowired
	private CMPPSocketService socketService;
	public static ConcurrentLinkedQueue<byte[]> queue = new ConcurrentLinkedQueue<>();
	public static boolean running = false;

	private static Logger logger = LoggerFactory.getLogger(CmppSubmitMsgThread.class);
	private int count = 0;
	private volatile long startTime = System.currentTimeMillis();

	@Override
	public void run() {
		while (running) {
			try {
				sendSubmitMessage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param message
	 * @throws Exception
	 */
	public void sendSubmitMessage() throws Exception {

		synchronized (queue) {
			long testStartTime = System.currentTimeMillis();
			if ((System.currentTimeMillis() - startTime) >= 1000) {
				count = 0;
				startTime = System.currentTimeMillis();
			}
			if (count <= CMPPConstants.sendCount) {
				byte[] queues = queue.poll();
				if (null != queues) {
					OutputStream outputStream = socketService.getOutputStream();
					outputStream.write(queues);// 发送消息
					outputStream.flush();
					count++;
					logger.info("----------------------------发送消息耗时={},count={}",
					System.currentTimeMillis() - testStartTime, count);
				}
			}
		}
	}
}
