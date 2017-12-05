package com.phoneshow.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketService {

	public static void main(String[] args) throws IOException {
		ServerSocket server=null;
		Socket client=null;
		server=new ServerSocket(8888);
		System.out.println("服务器等待连接");
		while(true)
		{
			///Scanner scanner =new Scanner(System.in);
			//String str=scanner.nextLine();
			client = server.accept();
			new SocketThread(client).start();
		}
	}

}
