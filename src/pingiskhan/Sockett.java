package pingiskhan;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
* Thread where robot checks if the computer is trying to make a bluetooth connection.
*
* @author M2ko, eetz1, Willy
* @version 1.0
*/
public class Sockett extends Thread {

	private static Socket s2;
	private ServerSocket ser;
	/**
	* Used to check if computer has the control or not.
	*/
	private boolean control = false;
	/**
	* Constructor creates new ServerSocket.
	*
	*/
	public Sockett() throws IOException {
		ser = new ServerSocket(1111);
	}
	/**
	* The main loop for the thread where
	*
	*/
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

	/**
	 * @return Returns the value of control which is used to see if the computer is connected.
	 */
	public boolean getControl() {
		return control;
	}

	/**
	 * @return Returns the Socket that is used for the bluetooth connection between computer and lejos.
	 */
	public Socket getSoc() {
		// TODO Auto-generated method stub
		return s2;
	}
}
