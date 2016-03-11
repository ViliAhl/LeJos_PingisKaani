package pingiskhan;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.robotics.subsumption.Behavior;
/**
* Remote control behavior. Controlled from a computer JavaFX application.
*
* @author M2ko, eetz1, Willy
* @version 1.0
*/
public class RemoteControl implements Behavior {
	/**
	* Suppresses the behavior if command comes from the computer.
	*/
	private volatile boolean suppressed = false;;

	private Sockett soc;
	private Socket s;
	private DataInputStream dataIn;
	private Motors motors;
	/**
	* These are used to see if theres any data in the InputStream.
	*/
	private int data, length;

	/**
	 * Constructor
	 * @param motors Motors of the robot.
	 * @param soc Socket thread that waits for the connection from the computer.
	 * @throws IOException Throws IOException.
	 */
	public RemoteControl(Motors motors, Sockett soc) throws IOException {
		this.soc = soc;
		s = new Socket();
		this.motors = motors;
	}
	/**
	* Takes control of the behavior.
	*
	* @return If return is true this behavior will start.
	*/
	@Override
	public boolean takeControl() {
		return soc.getControl();
	}
	/**
	* The main method of the behavior. Behavior will run this until it ends.
	*
	*/
	@Override
	public void action() {

		try {
			dataIn = new DataInputStream(soc.getSoc().getInputStream());
			System.out.println("connected?");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (!suppressed) {
			try {
				length = dataIn.available();

				if (length > 0) {
					data = dataIn.read();

					switch (data) {
					case 1:
						motors.forward(0);
						break;
					case 2:
						motors.backward(0);
						break;
					case 3:
						motors.hit();
						break;
					case 4:
						suppress();
						break;

				case 5:
					motors.stop();
					break;
				}
					data=0;

				}
			} catch (IOException e) {

				System.out.println("remoteconttrolaction");
			}
		}
	}
	/**
	* Changes suppressed = true, which ends the behavior.
	*
	*/
	@Override
	public void suppress() {
		suppressed = true;
		// TODO Auto-generated method stub

	}

}
