package pingiskhan;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
/**
* Gives commands to motors in a thread while the automated detection behavior is running.
*
* @author M2ko, eetz1, Willy
* @version 1.0
*/

public class CmdMotors extends Thread {
	private int cmd;
	private Storage stor;
	private Motors motors;
	/**
	* Thread ends when done is changed to true in terminate() method.
	*/
	private boolean done = false;
	private RegulatedMotor leftm;
	private RegulatedMotor rightm;
	private RegulatedMotor hit;

	/**
	 * Constructor
	 * @param stor Storage is to set and get motor commands and distances.
	 * @param motors Motors are motors of the robot.
	 */
	public CmdMotors(Storage stor,Motors motors) {
		this.stor = stor;
		this.motors = motors;

	}
	/**
	* Thread loops in this method as long as done is not true.
	*
	*/
	public void run() {
		System.out.println("cmdmotors run");

			try {
				cmd = stor.getCmd();
				while (cmd > -1 && !done) {

					switch (cmd) {
					case 1:
							motors.backward(stor.getDist());
						break;
					case 2:
							motors.forward(stor.getDist());
						break;
					case 3:

							motors.stop();
						break;

					case 4:
						motors.hit();
						break;

					}
					cmd = stor.getCmd();
				}
			} catch (Exception e) {
			}


	}

	/**
	* Rotates the motor that controls the hit mechanism.
	*
	*/
	public void hit() {
		hit.rotate(35);
		hit.rotate(-35);
	}


	/**
	* Stops the motors.
	*
	*/
	public void stopp() {
		leftm.startSynchronization();
		leftm.stop();
		rightm.stop();
		leftm.endSynchronization();

	}
	/**
	* Terminates the thread.
	*
	*/
	public void terminate() {
		done = true;
	}

}
