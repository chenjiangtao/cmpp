package so.dian.cmpp.utils;

import org.springframework.stereotype.Component;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

@Component
public class ByteUtils {

    public static byte[] writeFully(int length, String src) {
        byte[] b = new byte[length];
        ByteBuffer bb = ByteBuffer.wrap(b, 0, length);
        try {
            bb.put(src.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static String getStringByByte(int len, DataInputStream dis) throws Exception {
        byte[] bytes = new byte[len];
        dis.readFully(bytes);
        return new String(bytes);
    }

    public static String getStringByByte(int len, DataInputStream dis, String formart) throws Exception {
        byte[] bytes = new byte[len];
        dis.readFully(bytes);
        return new String(bytes, formart);
    }

    public static byte[] writeInt(int i) {
        byte[] b = new byte[4];
        ByteBuffer bb = ByteBuffer.wrap(b, 0, 4);
        bb.putInt(i);
        return b;
    }

    public static byte[] writeLong(int i) {
        byte[] b = new byte[8];
        ByteBuffer bb = ByteBuffer.wrap(b, 0, 8);
        bb.putInt(i);
        return b;
    }

    public static int readInt(DataInputStream din) throws IOException {
        byte[] b = new byte[4];
        din.read(b);
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.put(b);
        buf.flip();
        int bs = getInt(b, 0);
        return bs;
    }

    public static int getInt(byte[] bb, int index) {
        return (int) ((((bb[index + 0] & 0xff) << 24) | ((bb[index + 1] & 0xff) << 16) | ((bb[index + 2] & 0xff) << 8)
                | ((bb[index + 3] & 0xff) << 0)));
    }

    /**
     * 将字符串转为md5 byte[]
     *
     * @param str
     * @return
     * @throws Exception
     */
    public byte[] getMd5ByteByString(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes("UTF8"));
        byte[] temp;
        temp = md5.digest("".getBytes("UTF8"));
        return temp;
    }

}
