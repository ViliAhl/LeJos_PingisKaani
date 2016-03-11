package pingiskhan;

import lejos.robotics.subsumption.Behavior;

public class SelfDriveBeh implements Behavior {

	private volatile boolean suppressed = false;
	private VideoUsb video;

	public SelfDriveBeh(VideoUsb video) {
		this.video = video;

	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		while (!suppressed) {
			suppressed = false;
			if (!video.isAlive()) {
				video.startCmd();
				video.start();
			}
		}

	}

	@Override
	public void suppress() {
		video.terminate();
		suppressed = true;

	}
}
