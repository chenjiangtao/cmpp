package so.dian.cmpp.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CMPPSocketService {

    private static Logger logger = LoggerFactory.getLogger(CMPPSocketService.class);

    public static Socket socket;
    public OutputStream outputStream;
    public InputStream inputStream;


    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    /**
     * 初始化socket连接,设定超时时间为30秒 <br>
     * 使用cmpp协议各命令之前,必须调用此方法
     *
     * @throws CMPPException 封装连接时抛出的UnknownHostException以及IOException
     */
    public void initialSock(String host, int port) {
        try {
            if (null == socket) {
                socket = new Socket(host, port);
            }
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            //这个方法所设置的超时时间还未结束的时候，可以通过socket.getInputStream()获得的InputStream对象进行二次读取
            socket.setSoTimeout(30 * 1000);
            logger.info("成功建立起和网关的socket连接");
        } catch (IOException e) {
            logger.error("建立socket IO异常:" + ExceptionUtils.getFullStackTrace(e));
        }

    }


    public OutputStream getOutputStream() {
        return outputStream;
    }


    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    public InputStream getInputStream() {
        return inputStream;
    }


    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
