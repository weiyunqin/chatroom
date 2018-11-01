package chatroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	
	public static void main(String[] args) {
		Vector<UserThread> vector = new Vector<>();
		ExecutorService es = Executors.newFixedThreadPool(5);
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("�����������������ڵȴ�����...");
			while(true) {
				Socket socket = server.accept();
				UserThread user = new UserThread(socket, vector);
				es.execute(user);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
/**
 * �ͻ��˴�����߳�
 * @author Administrator
 *
 */
class UserThread implements Runnable{
	private String name;
	private Socket socket;
	private Vector<UserThread> vector;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	 boolean flag = true;
	
	public UserThread(Socket socket,Vector<UserThread> vector) {
		this.socket = socket;
		this.vector = vector;
		vector.add(this);
	}

	@Override
	public void run() {
		
		try {
			System.out.println("�ͻ���"+socket.getInetAddress().getHostAddress()+"������");
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			 while(flag) {
				//��ȡ��Ϣ����
				Message msg = (Message) ois.readObject();
				//�ж���Ϣ����
				MessageType type = msg.getMsg_type();
				switch(type) {
				case REGISTER :
					System.out.println("�ù�����δʵ��");
					break;
				case LOGIN :
					name = msg.getFrom();
					msg.setInfo("��ӭ��:"+name);
					oos.writeObject(msg);
					break;
				case SEND :
					
					break;
				case SYSTEM :
					System.out.println("�ù�����δʵ��");
					break;
				}
				
				
				
			}
			
		}catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
	














































/**/