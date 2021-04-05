package NetExam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 
 * 프로젝트명   DataSenedReceive
 * 
 *  파일명  Client.java
 *  작성자  박건웅
 *  작성일자 2021 04 05 
 *  
 * 
 * 
 * @author ITSC
 *
 */
public class Server {

	public static void main(String[] args) {
	BufferedWriter bw = null;
	BufferedReader br =null;
	Scanner sc = null;
	Socket client = null;
	ServerSocket server =null;
	
	try {
		server = new ServerSocket();
		server.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(),1234));
		
		
    	
		client = server.accept();
		InetSocketAddress isa = (InetSocketAddress) client.getRemoteSocketAddress();
		System.out.println("Connected to " +isa.getHostString());
		br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8"));
		bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"));
    	sc = new Scanner(System.in);
		
		while(true) {
			String fromClient = br.readLine();
			if(fromClient == null) {
				System.out.println("Disconnected");
				break;
			}System.out.println("From Client <<<<< " + fromClient);
			
			System.out.println("To Client >>>>");
			String toClient = sc.nextLine();
			bw.write(toClient + "\n");
			bw.flush();
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(bw != null) bw.close();
			if(br != null) br.close();
			if(!server.isClosed()) server.close();
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	}

}
