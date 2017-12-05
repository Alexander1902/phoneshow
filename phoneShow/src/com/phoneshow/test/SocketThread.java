package com.phoneshow.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

class SocketThread extends Thread{
	Socket client = null;
	String string=null;
	public SocketThread(Socket s){
		this.client=s;
		
	}
	public SocketThread(Socket s,String str){
		this.client=s;
		this.string=str;
	}
	@Override
	public void run() {
		BufferedReader buf=null;
		PrintStream out = null;
		try {
			 //缓存
			  buf=new BufferedReader(new InputStreamReader(client.getInputStream()));
			  out=new PrintStream(client.getOutputStream());
			  String str=null;
			  while ((str=buf.readLine())!=null) {
				  System.out.println(str);
				  
			}
			  if(string!=null){
				  out.println(string);
			  }else {
				  out.println("我是服务器,我给作为客户端的你传了数据");
			}
			 
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			 
				try {
					if(buf!=null)
						buf.close();
					 if(out!=null)
						  out.close();
					 if(client!=null)
						 client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 
		}
	}
	
}