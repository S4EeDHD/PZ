package ir.s4eed_hd.pz;

public class runner implements Runnable{
	
	Thread M;
	runner() {
	    M = new Thread(this, "Thread");
	    M.start();
	  }
	
	@Override
	public void run() {
		main.runMethod();
	}

}
