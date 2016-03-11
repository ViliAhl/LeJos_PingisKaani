package pingiskhan;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Motors {

	RegulatedMotor leftm;
	RegulatedMotor rightm;
	private RegulatedMotor hit;

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

	public void forward(int delay) {
		leftm.startSynchronization();
		leftm.forward();
		rightm.forward();
		leftm.endSynchronization();
		Delay.msDelay(delay);

	}

	public void backward(int delay) {
		leftm.startSynchronization();
		leftm.backward();
		rightm.backward();
		leftm.endSynchronization();
		Delay.msDelay(delay);
	}

	/*
	 * public void turn() { int x = (int)(Math.random() * 3000 + 1000);
	 * //leftm.startSynchronization(); if (x<=1500) { leftm.forward();
	 * rightm.backward(); Delay.msDelay(x); stop(); }else{ leftm.backward();
	 * rightm.forward(); Delay.msDelay(x); stop(); } //
	 * leftm.endSynchronization();
	 *
	 * }
	 */
	public void hit() {
		hit.rotate(-35);
		hit.rotate(35);
	}

	public void stop() {
		leftm.startSynchronization();
		leftm.stop();
		rightm.stop();
		leftm.endSynchronization();

	}

}