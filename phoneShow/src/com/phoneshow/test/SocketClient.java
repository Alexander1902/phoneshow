package com.phoneshow.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 8888);
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw=new PrintWriter(outputStream);
			pw.write("我是客户端，我的IP是127.0.0.1");
			pw.flush();
			socket.shutdownOutput();
			//获取服务器的输入流
			InputStream inputStream = socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
			String string=null;
			while((string=br.readLine())!=null)
			{
				System.out.println(string);
			}
			pw.close();
			inputStream.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}