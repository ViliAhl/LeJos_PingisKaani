package pingiskhan;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

/**
* All the motor methods are here.
*
* @author M2ko, eetz1, willy
* @version 1.0
*/
public class Motors {

	RegulatedMotor leftm;
	RegulatedMotor rightm;
	private RegulatedMotor hit;

	/**
	 * Creates the motor objects and sets the speed to max and synchronizes them.
	 */
	public Motors() {
		leftm = new EV3LargeRegulatedMotor(MotorPort.B);
		rightm = new EV3LargeRegulatedMotor(MotorPort.C);
		hit = new EV3LargeRegulatedMotor(MotorPort.A);
		hit.setSpeed((int) hit.getMaxSpeed());
		leftm.setSpeed((int) leftm.getMaxSpeed());
		rightm.setSpeed((int) leftm.getMaxSpeed());
		leftm.synchronizeWith(new RegulatedMotor[] { rightm });
		System.out.println("motorsrdy");
	}
	/**
	* Motors go forward.
	*
	* @param delay delays the motors depending on the distance of the ball.
	*/
	public void forward(int delay) {
		leftm.startSynchronization();
		leftm.forward();
		rightm.forward();
		leftm.endSynchronization();
		Delay.msDelay(delay);

	}
	/**
	* Motors go backward.
	*
	* @param delay delays the motors depending on the distance of the ball.
	*/
	public void backward(int delay) {
		leftm.startSynchronization();
		leftm.backward();
		rightm.backward();
		leftm.endSynchronization();
		Delay.msDelay(delay);
	}
	/**
	* Rotates the motor that controls the hit mechanism.
	*
	*/
	public void hit() {
		hit.rotate(-35);
		hit.rotate(35);
	}
	/**
	* Stops the motors.
	*
	*/
	public void stop() {
		leftm.startSynchronization();
		leftm.stop();
		rightm.stop();
		leftm.endSynchronization();

	}

}