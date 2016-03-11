package pingiskhan;

public class Storage {
	/**
	 * Command and Dist stores the command as in which direction to go and distance to see how far the ball is.
	 */
	volatile int command,dist;

	/**
	 * Sees if theres a thread writing or reading command or distance.
	 */
	volatile boolean readgoingon = false, writegoingon= true;


	  /**
	 * Constructor sets dist and command to 0.
	 */
	public Storage()
	  {
		  dist=0;
		  command = 0; // 0 edustaa tyhj�� s�ili�t�
	  }


	 /**
	  * Getter for the command.
	 * @return Returns the value of command
	 * @throws InterruptedException
	 */
	public synchronized int getCmd() throws InterruptedException{
	    	while (command==0){
	     		wait();
	    	}

	    	int returnCmd = command;
		    	command = 0;

		    	//notifyAll();
		    	return returnCmd;
	  }
	  /**
	 * Getter for the distance.
	 * @return Returns the value of distance.
	 * @throws InterruptedException
	 */
	public synchronized int getDist() throws InterruptedException{
	    	while (dist==0){
	     		wait();
	    	}

	    	int returnDist = dist;
		    	dist = 0;

		    	return returnDist;
	  }

	  /**
	   * Setter for the command.
	 * @param n is the value to be set as the command.
	 * @throws InterruptedException
	 */
	public synchronized void setCmd(int n) throws InterruptedException{
		   	command = n;
		   	notifyAll();
	  }
	  /**
	   * Setter for the distance.
	 * @param n is the value to be set as the distance.
	 * @throws InterruptedException
	 */
	public synchronized void setDist(int n) throws InterruptedException{

		   	dist = n;
		   	notifyAll();
	  }
}


