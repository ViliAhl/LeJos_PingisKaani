package pingiskhan;

public class Storage {
	volatile int command,dist;
	volatile boolean readgoingon = false, writegoingon= true;


	  public Storage()
	  {
		  dist=0;
		  command = 0; // 0 edustaa tyhj�� s�ili�t�
	  }


	 public synchronized int getCmd() throws InterruptedException{
	    	while (command==0){
	     		wait();
	    	}

	    	int returnCmd = command;
		    	command = 0;

		    	//notifyAll();
		    	return returnCmd;
	  }
	  public synchronized int getDist() throws InterruptedException{
	    	while (dist==0){
	     		wait();
	    	}

	    	int returnDist = dist;
		    	dist = 0;

		    	//notifyAll();
		    	return returnDist;
	  }

	  public synchronized void setCmd(int n) throws InterruptedException{
//
//		   	while (!(command==0)){
//		   		wait();
//		   	}
		   	command = n;
		   	notifyAll();
	  }
	  public synchronized void setDist(int n) throws InterruptedException{

//		   	while (!(dist==0)){
//		   		wait();
//		   	}
		   	dist = n;
		   	notifyAll();
	  }
//public void setCmd2(int n) throws InterruptedException{
//	while(readgoingon){}
//		writegoingon=true;
//	System.out.println("setcmd2  "+n);
//	command=n;
//	writegoingon=false;
//}
//
//	public  int getCmd2() throws InterruptedException{
//	while(writegoingon){
//		}
//	readgoingon=true;
//	System.out.println("getcmd2  "+command);
//	int c = command;
//	readgoingon=false;
//	return c;
//}
}


