import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("serial")
public class AutoAddTokens extends TimerTask implements java.io.Serializable{

	private Child timerChild;
	private long time;
	boolean stop;
	
	AutoAddTokens(Child s, long t, boolean e)
	{
		timerChild = s;
		time = t;
		stop = e;
	}
	public void setChild(Child s)
	{
		timerChild = s;
	}
	public Child getChild()
	{
		return timerChild;
	}
	
	@Override
	//adds tokens automatically to child based on a specified time
	public void run() {
		// TODO Auto-generated method stub
		Timer timer2 = new Timer();
		if(!(this.getChild().getHasTimer()))
		{
			timer2.cancel();
			this.cancel();
		}
		else
		{
			timer2.schedule(new AutoAddTokens(timerChild, time, stop), time);
			timerChild.addAutoToken();
		}
	}

}
