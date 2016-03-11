package pingiskhan;

import java.io.IOException;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main2 {

	public static void main(String[] args) {

		boolean pressed = false;

		//Socket s = new Socket();
		Sockett soc = null;
		try {
			soc = new Sockett();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("socckeketkkte new");
		}
		Storage stor = new Storage();
		Motors m = new Motors();
		//CmdMotors cmd = new CmdMotors(stor, m);
		VideoUsb usb = new VideoUsb(stor, m);


		Behavior b1 = null;
		try {
			b1 = new RemoteControl(m, soc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("sremotecontrol new");
		}
		Behavior b2 = new SelfDriveBeh(usb);

		while (!pressed) {
			if (0 != Button.waitForAnyPress()) {
				System.out.println("btnpressed");
				pressed = true;
			}
		}
//		usb.start();
		soc.start();
		Behavior[] bArray = { b1, b2};
		Arbitrator arby = new Arbitrator(bArray);
		arby.go();
	}

}
