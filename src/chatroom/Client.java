package chatroom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		try {
			Socket socket = new Socket("127.0.0.1",12345);
			System.out.println("���������ӳɹ���");
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			//��ʾ��¼����
			welcome();
//			String choice = in.nextLine();
//			//1����¼\t2��ע��\t3���˳� 
//			Message msg = new Message();
//			boolean flag = true;
//			while(flag) {
//				switch(choice) {
//				case "1" :
//					flag = false;
//					msg.setMsg_type(MessageType.LOGIN);
//					break;
//				case "2" :
//					flag = false;
//					msg.setMsg_type(MessageType.REGISTER);
//					break;
//				case "3" :
//					flag = false;
//					System.exit(0);
//					break;
//				default :
//					break;	
//				}
//			}
//			oos.writeObject(msg);
			//--------------------------------------------------//
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void welcome() {
		System.out.println("��ӭ����ChatRoom !");
		System.out.println("��ѡ��");
		System.out.println("1����¼\t2��ע��\t3���˳�");
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			String choice = in.nextLine();
			switch(choice) {
			case "1" :
				flag = false;
				loginUser();
				System.out.println("ʣ�๦����δʵ��");
				break;
			case "2" :
				flag = false;
				registerUser();
				welcome();
				break;
			case "3" :
				flag = false;
				System.exit(0);
				break;
			default :
				System.out.println("����������������룡");
				welcome();
				break;
			}
		}
	}


	public static void registerUser() {
		UserServer us = new UserServer();
		Scanner in = new Scanner(System.in);
		User user = new User();
		boolean flag = true;
		while(flag) {
			System.out.println("�������û�����");
			String username = in.nextLine();
			user.setName(username);
			System.out.println("���������룺");
			String psw = in.nextLine();
			user.setPsw(psw);
			if(!us.checkUserIsExist(user)) {
				flag = false;
			}
		}
		us.addUser(user);
		us.writeUser();
		
		
	}
	
	public static User loginUser() {
		boolean flag =true;
		UserServer us = new UserServer();
		us.readUser();
		Scanner in = new Scanner(System.in);
		User user = new User();
		while (flag) {
			System.out.println("�������û�����");
			String username = in.nextLine();
			user.setName(username);
			System.out.println("���������룺");
			String psw = in.nextLine();
			user.setPsw(psw);
			if (us.findUser(user)) {
				flag = false;
				user.setPsw(psw);
				us.readUser();
				return user;
			}

		}
		
		 return null;
	}
}

