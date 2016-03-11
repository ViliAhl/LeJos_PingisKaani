package pingiskhan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Sockett extends Thread {

	private static Socket s2;
	private ServerSocket ser;
	private boolean control = false;

	public Sockett() throws IOException {
		ser = new ServerSocket(1111);
	}

	public void run() {

		control = false;
		while (true) {
			try {
				s2 = ser.accept();
				while (!s2.isConnected()) {
					s2 = ser.accept();
					System.out.println("ser accept");
				}
				control = true;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ser not accpet");
			}
			while (s2.isConnected()) {
			}

			control = false;
			System.out.println("asd");

		}
	}

	public boolean getControl() {
		return control;
	}

	public Socket getSoc() {
		// TODO Auto-generated method stub
		return s2;
	}
}
