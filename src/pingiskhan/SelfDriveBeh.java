package pingiskhan;

import lejos.robotics.subsumption.Behavior;
/**
* Automated object tracking and motor control behavior.
*
* @author M2ko, eetz1, Willy
* @version 1.0
*/
public class SelfDriveBeh implements Behavior {
	/**
	* Suppresses the behavior if command comes from the computer.
	*/
	private volatile boolean suppressed = false;
	private VideoUsb video;

	/**
	 * Constructor
	 * @param video Video capturing thread.
	 */
	public SelfDriveBeh(VideoUsb video) {
		this.video = video;

	}
	/**
	* Takes control of the behavior.
	*
	* @return If return is true this behavior will start.
	*/
	@Override
	public boolean takeControl() {
		return true;
	}
	/**
	* The main method of the behavior. Behavior will run this until it ends.
	*
	*/
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
	/**
	* Changes suppressed = true, which ends the behavior and terminates the video capturing thread.
	*
	*/
	@Override
	public void suppress() {
		video.terminate();
		suppressed = true;

	}
}
