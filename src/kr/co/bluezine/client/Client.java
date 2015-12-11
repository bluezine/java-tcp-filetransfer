package kr.co.bluezine.client;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client {
	Socket socket;
	DataInputStream dis;
	File in;
	FileOutputStream fos;
	
	public static void main(String args[]) throws Exception {
		new Client().go();
	}
	
	public void go() throws Exception {
		socket = new Socket("localhost", 65000);
		dis = new DataInputStream(socket.getInputStream());
		in = new File("in.jpg");
		fos = new FileOutputStream(in);
		
		int data;
		while((data = dis.read()) != -1) {
			fos.write(data);
		}
		fos.flush();
		fos.close();
		dis.close();
	}
}
