package pingKhaComp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Controller {

	private final int left = 1, right = 2, hit = 3, kill = 4,stop=5 ;
	Socket s;
	private DataOutputStream dataOut;

	public Controller() {

	}

    public void vasen() throws Exception {
    	dataOut.writeInt(left);

}
    public void oikea() throws Exception{
    	dataOut.writeInt(right);
    }

    public void lyonti() throws Exception{
    	dataOut.writeInt(hit);
}
    public void stop() throws Exception{
    	dataOut.writeInt(stop);
}

    public void lopeta() throws Exception {
    	dataOut.writeInt(kill);
    	s.shutdownOutput();
    	s.close();
    }
    public void ohjaus() throws UnknownHostException, IOException,Exception {
    	s = new Socket("10.0.1.1", 1111);
    	if (s.isConnected() == true) {
    		System.out.println("Connected");
    	}
    	dataOut = new DataOutputStream(s.getOutputStream());
    }
}
