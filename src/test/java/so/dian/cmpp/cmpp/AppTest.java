package so.dian.cmpp.cmpp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase
{
   
	public void test1() {
		byte[] b = new byte[100];
		ByteBuffer bb = ByteBuffer.wrap(b, 0, 100);
		bb.put("张".getBytes());
		bb.put("兴".getBytes());
		bb.put("生".getBytes());
		bb.put("end".getBytes());
		String a=new String(b);
		System.out.println(a);
	}
	public void test2() {
		String str="0hello";
		System.out.println(str.getBytes().length);
		byte []b=new byte[4];
		InputStream in=new ByteArrayInputStream(str.getBytes());
		DataInputStream dis=new DataInputStream(in);
		try {
			dis.read(b, 0, 1);
			System.out.println(new String(b));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void test4() {
		int i=0;
		while(true) {
			try {
				System.out.println(i++);
				Integer.valueOf("asdasda");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				if(3==i) {
					break;
				}
			}
		}
	}
	public void test5() {
		String src="2";
		byte[]b=new byte[10];
		ByteBuffer bb = ByteBuffer.wrap(b, 0, 10);
		try {
			bb.put(src.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(int i=0;i<b.length;i++) {
			System.out.print(b[i]+"-");
		}
		System.out.println(b.length);
	}
	public void test6() {
		int len=10;
		String s="2";
		if (s == null) {
			s = "";
		}
		byte[] rb = new byte[len];
		byte[] sb = s.getBytes();
		for (int i = sb.length; i < rb.length; i++) {
			rb[i] = 0;
		}
		if (sb.length == len) {
			System.out.println("=============");
		} else {
			for (int i = 0; i < sb.length && i < len; i++) {
				rb[i] = sb[i];
			}
			for(int i=0;i<rb.length;i++) {
				System.out.print(rb[i]+"-");
			}
			System.out.println(rb.length);
		}
	}
	public void test7() {
		int a=2;
		String s="132342";
		byte t=(byte) s.getBytes().length;
		
		InputStream in=null;
		byte[]b=new byte[1];
		try {
			in = new ByteArrayInputStream(String.valueOf(a).getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataInputStream dis=new DataInputStream(in);
		try {
			dis.read(b);
			for(int i=0;i<b.length;i++) {
				System.out.println(b[i]);
			}
			System.out.println(b.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


}
