package com.carrol.http;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionTest {
	
	/**
	 * httpUrlConnection.setDoOutput(true）
	 * httpUrlConnection.setDoInput(true)
	 * 这两个方法在develope的httpUrlConnection方法中找不到的。
	 * 一般情况是：
          HttpURLConnection conn = (HttpURLConnection)url.openConnection();
          url.openConnection()得到的是URLConnection对象，此类有setDoOutput(）和setDoInput()
	 * httpUrlConnection.setDoOutput(false);以后就可以使用conn.getOutputStream().write()  
	 * httpUrlConnection.setDoInput(true);以后就可以使用conn.getInputStream().read();  
	 * get请求用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false。  
	 * post请求（比如：文件上传）需要往服务区传输大量的数据，这些数据是放在http的body里面的，因此需要在建立连接以后，往服务端写数据。  
	 * 因为总是使用conn.getInputStream()获取服务端的响应，因此默认值是true。
	 * public void setDoInput(boolean doinput)将此 URLConnection 的 doInput 字段的值设置为指定的值。    
	 * URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输入，则将 DoInput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 true。  
	 * public void setDoOutput(boolean dooutput)将此 URLConnection 的 doOutput 字段的值设置为指定的值。    
	 * URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 false。
	 * 简单一句话：get请求的话默认就行了，post请求需要setDoOutput(true)，这个默认是false的。
	 */
	public static void main(String[] args) {
		
		try {
			String path = "http://www.baidu.com";//定义路径
			URL url = new URL(path);//设置资源定位符
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();//打开链接
			//装参数
			conn.setRequestMethod("GET");   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);//设置连接超时
            conn.setDoInput(true);//是否打开输入流 ， 此方法默认为true，用于接收响应内容
            conn.setDoOutput(true);//是否打开输出流， 此方法默认为false，可用于输出body中的各种参数
            
            conn.setRequestProperty("Content-type", "text/html; charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("Charsert", "UTF-8"); //设置请求编码
            //执行链接
            conn.connect();//表示连接
			
            //之后就开始判断是否连接成功
            int code = conn.getResponseCode();
            if(code!=200) {
            	
            	System.out.println("failed");
            	return;
            }
            //printForPrintWriter( conn);
            printForIntput( conn);
            //printForBuffer( conn);
            
            conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("success");
		}
	}
	
	public static boolean printForBuffer(HttpURLConnection conn){
		
		try {
			//获取输入的响应数据
	        InputStream is = conn.getInputStream();//输入流，接收响应内容
	        //将获取的响应数据保存文件
	        String name = conn.getURL().toString().substring(conn.getURL().toString().lastIndexOf(".")+1);//截取一段链接作为保存文件的名字
	        System.out.println("name = " + name);//打印保存的文件名
	        FileOutputStream fos = new FileOutputStream("F:\\"+name+".html");//输出流，保存文件
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	        
	        String lines;
	        while ((lines = reader.readLine()) != null) {
	        	System.out.println(new String(lines.getBytes("utf-8")));
	        	byte[] b = lines.getBytes("utf-8");
	        	fos.write(b, 0, b.length);
	        }
	        
	        reader.close();
	        is.close();
	        fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public static boolean printForIntput(HttpURLConnection conn){
		
		try {
			//获取输入的响应数据
	        InputStream is = conn.getInputStream();//输入流，接收响应内容
	        //将获取的响应数据保存文件
	        String name = conn.getURL().toString().substring(conn.getURL().toString().lastIndexOf(".")+1);//截取一段链接作为保存文件的名字
	        System.out.println("name = " + name);//打印保存的文件名
	        FileOutputStream fos = new FileOutputStream("F:\\"+name+".html");//输出流，保存文件
	        
	        byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer))!=-1) {
                fos.write(buffer, 0, len);
            }
	        is.close();
	        fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean printForPrintWriter(HttpURLConnection conn){
		
		try {
			//获取输入的响应数据
	        InputStream is = conn.getInputStream();//输入流，接收响应内容
	        //将获取的响应数据保存文件
	        String name = conn.getURL().toString().substring(conn.getURL().toString().lastIndexOf(".")+1);//截取一段链接作为保存文件的名字
	        System.out.println("name = " + name);//打印保存的文件名
	        FileOutputStream fos = new FileOutputStream("F:\\"+name+".html");//输出流，保存文件
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
	        
	        String lines;
	        while ((lines = reader.readLine()) != null) {
	        	System.out.println(new String(lines.getBytes("utf-8")));
	        	byte[] b = lines.getBytes("utf-8");
	        	fos.write(b, 0, b.length);
	        }
	        Map<String, List<String>> map = conn.getHeaderFields();
	        System.out.println(map.size());
	        reader.close();
	        is.close();
	        fos.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * HTTPURLConnection post请求方式处理数据
	 */
	public void postURL(){
		try {
			String path = "http://www.baidu.com";//定义路径
			URL url = new URL(path);//设置资源定位符
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();//打开链接
			//装参数
			conn.setRequestMethod("POST");   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
            conn.setConnectTimeout(5000);//设置连接超时
            conn.setDoInput(true);//是否打开输入流 ， 此方法默认为true，用于接收响应内容
            conn.setDoOutput(true);//是否打开输出流， 此方法默认为false，可用于输出body中的各种参数
            
            conn.setRequestProperty("Content-type", "text/html; charset=utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("Charsert", "UTF-8"); //设置请求编码
            //带请求参数
            conn.setRequestProperty("param", "name=13334");//定义post中的参数
            conn.addRequestProperty("param", "name=13334");//可以通过此种方式累加
            
            //执行链接
            conn.connect();//表示连接
			
            //之后就开始判断是否连接成功
            int code = conn.getResponseCode();
            if(code!=200) {
            	System.out.println("failed");
            	return ;
            }
            //printForPrintWriter( conn);
            printForIntput( conn);
            //printForBuffer( conn);
            
            conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("success");
		}
	}
	
}
