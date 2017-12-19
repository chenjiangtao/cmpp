package so.dian.cmpp.service;

import java.io.OutputStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import so.dian.cmpp.bean.CMPPClientBean;
import so.dian.cmpp.bean.message.MessageActiveBean;
import so.dian.cmpp.bean.message.MessageBean;
import so.dian.cmpp.bean.message.MessageDeliverRespBean;
import so.dian.cmpp.bean.message.MessageSubmitBean;
import so.dian.cmpp.bean.message.MsgRespThreadBean;
import so.dian.cmpp.constant.CMPPConstants;
import so.dian.cmpp.constant.CommandIdConstans;
import so.dian.cmpp.thread.CMPPActiveTestThread;
import so.dian.cmpp.thread.CMPPResponseThread;
import so.dian.cmpp.utils.ByteUtils;
import so.dian.cmpp.utils.CMPPUtils;
import so.dian.cmpp.utils.MessageUtils;

@Service
public class CMPPClientService {

    @Autowired
    private CMPPClientBean cmppClientBean;
    @Autowired
    private CMPPSocketService socketService;
    @Autowired
    MessageUtils messageUtils;
    @Autowired
    CMPPResponseThread cmppResponseThread;
    @Autowired
    ByteUtils byteUtils;


    private static Logger logger = LoggerFactory.getLogger(CMPPClientService.class);

    /**
     * 发送短信
     *
     * @param servicenumber 显示到接收手机端的主叫号码
     * @param msgContent    发送的短信内容
     * @param receiveList   接收短信的号码集合
     */
    public String sendNotifySms(String msgContent, String receiveList) throws Exception {
        String result = "";
        //链路检测正常，可以发送消息
        if (CMPPActiveTestThread.running) {
            submitMessage(msgContent, receiveList);
        }
        return result;
    }

    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws Exception
     */
    @PostConstruct
    public void login() throws Exception {

        logger.info("开始连接移动短信网关..................");
        // 初始化socket
        socketService.initialSock(cmppClientBean.getSmsGatewayIp(), cmppClientBean.getSmsGatewayReport());
        String userName = cmppClientBean.getBusinessUserName();
        String userPwd = cmppClientBean.getBusinessUserPwd();
        logger.info("用户名={}，用户密码={}，cmpp版本={}", userName, userPwd, CMPPConstants.cmppVersion);
        // 获取登录的消息
        MessageBean connectMessage = messageUtils.getCmppConnect(userName, userPwd, CMPPConstants.cmppVersion);
        // 获取登录状态 0为登录成功
        sendMessage(connectMessage);
        logger.info("开始启动接收短信线程");
        CMPPResponseThread.running = true;
        new Thread(cmppResponseThread).start();//启动接收短信线程
    }

    /**
     * 链路检测
     * <p>
     * 本操作仅适用于通信双方采用长连接通信方式时用于保持连接
     */
    public void cmppActiveTest() throws Exception {
        MessageActiveBean activeBean = new MessageActiveBean();
        activeBean.setTotalLength(4 + 4 + 4);
        activeBean.setCommandId(Integer.valueOf(CommandIdConstans.CMPP_ACTIVE_TEST));
        activeBean.setSequenceId(CMPPUtils.getSequenceId());
        // 获取登录状态 0为登录成功
        sendMessage(activeBean);
    }

    /**
     * cmpp_submit 消息
     *
     * @param msgContent
     * @param receiveList
     * @throws Exception
     */
    public void submitMessage(String msgContent, String receiveList) throws Exception {
        MessageSubmitBean bean = new MessageSubmitBean();
        bean.setServiceId(cmppClientBean.getBusinessCode());//业务代码
        bean.setMsgContent(msgContent);//消息内容
        bean.setMsgSrc(cmppClientBean.getSpId());//企业编码
        bean.setSrcId(cmppClientBean.getSmsAccessCodes());//短息接入码
        bean.setCommandId(CommandIdConstans.CMPP_SUBMIT);//发送的消息类型
        bean.setDestterminalId(receiveList);//发送的目标号码
        //初始化cmpp_submit 消息包
        MessageSubmitBean initMessageBean = MessageUtils.intoSubmit(bean);
        sendMessage(initMessageBean);
    }

    /**
     * 发送消息，改方法是所有发送消息的入口
     *
     * @param message
     * @throws Exception
     */
    public void sendMessage(MessageBean message) throws Exception {
        //将消息对象 根据cmpp协议 转为移动短息网关的消息包
        byte[] bytes = messageUtils.toBytes(message);
        //真正发送消息
        synchronized (socketService.getOutputStream()) {
            OutputStream outputStream = socketService.getOutputStream();
            outputStream.write(bytes);// 发送消息
            outputStream.flush();
        }
    }

    /**
     * 对短信网关发来的信息进行相应
     *
     * @param respThreadBean sequenceId 使用(CMPP_DELIVER)移动端发来信息的sequenceId msgId
     *                       使用(CMPP_DELIVER)移动端发来信息的msgId result 在CMPP_DELIVER 中设置
     */
    public void cmppDdeliverResp(MsgRespThreadBean respThreadBean) throws Exception {
        MessageDeliverRespBean deliverRespBean = new MessageDeliverRespBean();
        deliverRespBean.setTotalLength(24);
        deliverRespBean.setCommandId(CommandIdConstans.CMPP_DELIVER_RESP);
        deliverRespBean.setSequenceId(respThreadBean.getSequenceId());
        deliverRespBean.setMsgId(respThreadBean.getMsgId());
        deliverRespBean.setResult(respThreadBean.getStatus());
        sendMessage(deliverRespBean);
    }
}
