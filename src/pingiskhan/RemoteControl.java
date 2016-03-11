package pingiskhan;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import lejos.robotics.subsumption.Behavior;

public class RemoteControl implements Behavior {

	private volatile boolean suppressed = false;;

	private Sockett soc;
	private Socket s;
	private DataInputStream dataIn;
	private Motors motors;

	private int data, length;

	public RemoteControl(Motors motors, Sockett soc) throws IOException {
		this.soc = soc;
		// this.s = s;
		s = new Socket();
		this.motors = motors;
	}

	@Override
	public boolean takeControl() {
		return soc.getControl();
	}

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
					//motors.stop();

				}
			} catch (IOException e) {

				System.out.println("remoteconttrolaction");
			}
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
		// TODO Auto-generated method stub

	}

}
