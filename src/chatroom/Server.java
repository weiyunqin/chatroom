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
			System.out.println("服务器已启动，正在等待链接...");
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
 * 客户端处理的线程
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
			System.out.println("客户端"+socket.getInetAddress().getHostAddress()+"已连接");
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			 while(flag) {
				//读取消息对象
				Message msg = (Message) ois.readObject();
				//判断消息类型
				MessageType type = msg.getMsg_type();
				switch(type) {
				case REGISTER :
					System.out.println("该功能尚未实现");
					break;
				case LOGIN :
					name = msg.getFrom();
					msg.setInfo("欢迎你:"+name);
					oos.writeObject(msg);
					break;
				case SEND :
					
					break;
				case SYSTEM :
					System.out.println("该功能尚未实现");
					break;
				}
				
				
				
			}
			
		}catch(IOException|ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
	














































/**/