package so.dian.cmpp.bean;

import java.io.InputStream;
import java.io.OutputStream;

public class CMPPSocketBean {
    private String ip;
    private String report;
    private OutputStream outputStream;
    private InputStream inputStream;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
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

    public CMPPSocketBean(String ip, String report) {
        super();
        this.ip = ip;
        this.report = report;
    }

    public CMPPSocketBean() {
        super();
        // TODO Auto-generated constructor stub
    }

}
