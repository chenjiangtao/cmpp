package so.dian.cmpp.utils;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import so.dian.cmpp.service.CMPPClientService;
import so.dian.cmpp.service.CMPPSocketService;

@Component
public class CMPPUtils {

    public static int seq = 0;
    @Autowired
    CMPPSocketService cmppSocketService;
    @Autowired
    CMPPClientService cmppClientService;

    private static Logger logger = LoggerFactory.getLogger(CMPPUtils.class);

    /**
     * 获取消息流水号
     * 消息流水号,顺序累加,步长为1,循环使用（一对请求和应答消息的流水号必须相同）
     *
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

    /**
     * 异常重试
     */
    public void retryException() {
        logger.error("SocketException 异常次数已大于5次,开始重新登录移动网关");
        try {

            if (null != cmppSocketService.getInputStream()) {
                logger.info("开始关闭原输入流");
                cmppSocketService.getInputStream().close();
            }
            if (null != cmppSocketService.getOutputStream()) {
                logger.info("开始关闭原输出流");
                cmppSocketService.getOutputStream().close();
            }
            logger.info("开始重新连接移动网关");
            cmppClientService.login();
        } catch (Exception e) {
            logger.error("重新连接移动网关失败,异常信息={}", ExceptionUtils.getFullStackTrace(e));
        }
    }


}
