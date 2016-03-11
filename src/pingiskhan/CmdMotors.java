package pingiskhan;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class CmdMotors extends Thread {
	private int cmd;
	private Storage stor;
	private Motors motors;
	private boolean done = false;
	private RegulatedMotor leftm;
	private RegulatedMotor rightm;
	private RegulatedMotor hit;

	public CmdMotors(Storage stor,Motors motors) {
		this.stor = stor;
		/*
		 * leftm = new EV3LargeRegulatedMotor(MotorPort.B); rightm = new
		 * EV3LargeRegulatedMotor(MotorPort.C); hit = new
		 * EV3MediumRegulatedMotor(MotorPort.A); hit.setSpeed((int)
		 * hit.getMaxSpeed()); leftm.setSpeed((int) leftm.getMaxSpeed());
		 * rightm.setSpeed((int) leftm.getMaxSpeed()); leftm.synchronizeWith(new
		 * RegulatedMotor[] { rightm });
		 */
		this.motors = motors;

	}

	public void run() {
		System.out.println("cmdmotors run");

			try {
				cmd = stor.getCmd();
				while (cmd > -1 && !done) {
					// synchronized (this) {
					// while (cmd==1){
					// backward();
					// cmd = stor.getCmd();
					// }
					// while (cmd ==2){
					// forward();
					// cmd = stor.getCmd();
					// }
					// while(cmd==3){
					// stopp();
					// cmd = stor.getCmd();
					// }
					switch (cmd) {
					case 1:
//						while (cmd == 1) {
							motors.backward(stor.getDist());
//							cmd = stor.getCmd();
//						}
						break;
					case 2:
//						while (cmd == 2) {
							motors.forward(stor.getDist());
//							cmd = stor.getCmd();

//						}
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

	public void hit() {
		hit.rotate(35);
		hit.rotate(-35);
	}

	/*
	 * public void forward() { System.out.println("etee");
	 * leftm.startSynchronization(); leftm.forward(); rightm.forward();
	 * leftm.endSynchronization(); try { Delay.msDelay((int)(stor.getDist()));
	 * stopp(); } catch (InterruptedException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } }
	 */

	/*
	 * public void backward() { System.out.println("taa");
	 * leftm.startSynchronization(); leftm.backward(); rightm.backward();
	 * leftm.endSynchronization(); try { Delay.msDelay((int)(stor.getDist()));
	 * stopp(); } catch (InterruptedException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } }
	 */

	public void stopp() {
		leftm.startSynchronization();
		leftm.stop();
		rightm.stop();
		leftm.endSynchronization();

	}

	public void terminate() {
		done = true;
	}

}
