package pingiskhan;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import lejos.hardware.Button;
import lejos.utility.Delay;

public class VideoUsb extends Thread{

	private Storage stor;
	private VideoCapture vid;
	private CmdMotors cmdM;
	private final int width = 100, height = 80;
	private Mat dilate;
	private Mat erode;
	private final Scalar hsvMin = new Scalar(0, 218, 148, 0);// orange
	private final Scalar hsvMax = new Scalar(32, 255, 255, 0);// orange
	// private final Scalar hsvMin = new Scalar(0, 50, 50, 0);// red
	// private final Scalar hsvMax = new Scalar(6, 255, 255, 0);// red
	// private final Scalar hsvMin2 = new Scalar(165, 50, 50, 0);// red
	// private final Scalar hsvMax2 = new Scalar(180, 255, 255, 0);// red
	private final int mBlur = 5, moveRange = 10, radiusHit = 40, yHit = 65;
	private final double maxArea = 50;

	int rightdistance, leftdistance, x, y;
private boolean terminated = false;
	private Mat matImg, matImg2, sourceImg, hie;

	public VideoUsb(Storage stor, Motors motors) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		dilate = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
		erode = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));
		this.stor = stor;
		cmdM = new CmdMotors(stor, motors);
	}

	public void run() {
		terminated=false;
		sourceImg = new Mat();
		vid = new VideoCapture(0);
		vid.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, width);
		vid.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, height);
		vid.open(0);
		System.out.println("Camera open");
		while(!terminated){
			manipulateImg();
		}

	}

	public void startCmd() {
		cmdM.start();
	}

	public Mat videoCap() {
		vid.read(sourceImg);
		//System.out.println("jeereturn");

		return sourceImg;
	}


	public void terminate() {
		cmdM.terminate();
		terminated = true;
	}

	public void manipulateImg(/*Mat sourceImg*/) {
		sourceImg = videoCap();
		//System.out.println("jeemani");

		Imgproc.cvtColor(sourceImg, sourceImg, Imgproc.COLOR_BGR2HSV);
		matImg = new Mat();

		matImg2 = new Mat();

		Core.inRange(sourceImg, hsvMin, hsvMax, matImg);
		// Core.inRange(sourceImg, hsvMin2, hsvMax2, matImg2);
		// Core.bitwise_or(matImg, matImg2, matImg);
		Imgproc.medianBlur(matImg, matImg, mBlur);

		Imgproc.erode(matImg, matImg2, erode);
		Imgproc.erode(matImg, matImg2, erode);
		Imgproc.dilate(matImg, matImg2, dilate);
		Imgproc.dilate(matImg, matImg2, dilate);
		// Imgproc.dilate(matImg, matImg, dilate);
		// circ = new Mat();

		List<MatOfPoint> contours = new ArrayList();
		hie = new Mat();
		Imgproc.findContours(matImg2, contours, hie, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

		float[] radius = new float[1];
		Point center = new Point();
		for (int i = 0; i < contours.size(); i++) {
			MatOfPoint c = contours.get(i);
			if (Imgproc.contourArea(c) > maxArea) {
				MatOfPoint2f c2f = new MatOfPoint2f(c.toArray());
				Imgproc.minEnclosingCircle(c2f, center, radius);

			}
		}
		Core.circle(matImg2, center, (int) Math.round(radius[0]), new Scalar(180, 255, 255), 3);
		x = (int) Math.round(center.x);
		y = (int) Math.round(center.y);
		rightdistance = (int) (Math.round(Math.abs(((x) - (matImg2.width() / 2)))));
		leftdistance = (int) (Math.round(Math.abs(((matImg2.width() / 2) - (x)))));
		if (rightdistance > 10) {
			rightdistance *= 2;
		} else {
			rightdistance *= 1.5;
		}
		if (leftdistance > 10) {
			leftdistance *= 2;

		} else {
			leftdistance *= 1.5;
		}
		if (x != 0) {
			try {
				if (y > yHit) {
					stor.setCmd(4);// HIT
					stor.setCmd(3);
					// if (radiusHit < (int) Math.round(radius[0]))
					// {
					// stor.setCmd(4);//HIT
					// stor.setCmd(3);
				}
				else if (x + moveRange < (matImg2.width() / 2)) {
					stor.setCmd(1);// back
					stor.setDist(rightdistance);

				}
				else if ((x - moveRange > (matImg2.width() / 2))) {
					stor.setCmd(2); // for
					stor.setDist(leftdistance);

				} else
					stor.setCmd(3); // stop))
				// Highgui.imwrite("EV3pic.jpg", threshedImg);
				// Highgui.imwrite("EV3pic2.jpg", threshedImg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("videousb storcmd");
			} // back
		}
		try {
			stor.setCmd(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("videousb storcmd");
		}

	}

	// public static void main(String[] args) throws IOException {
	// VideoUsb main = new VideoUsb();
	// main.start();
	//
	// }

}
