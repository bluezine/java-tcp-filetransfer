package kr.co.bluezine.server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	public static void main(String args[]) throws Exception {
		new Server().go();
	}
	
	public void go() throws Exception {
		ServerSocket socket = new ServerSocket(65000);
		while(true) {
			Socket client = socket.accept();
			new Client(client).start();
		}
	}
	
	class Client extends Thread {
		
		Socket socket;
		DataOutputStream dos;
		FileInputStream fis;
		File output;
		
		public Client(Socket socket) throws Exception {
			this.socket = socket;
			dos = new DataOutputStream(this.socket.getOutputStream());
			output = new File("out.jpg");
			fis = new FileInputStream(output);
		}

		@Override
		public void run() {
			int data;
			try {
				while((data = fis.read()) != -1) {
					dos.write(data);
				}
				dos.flush();
				dos.close();
				fis.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
