
@SuppressWarnings("serial")
public class PositiveMode extends Mode implements java.io.Serializable{
	public PositiveMode()
	{
		this.setIdentifier(1);
	}
	public int getIdentifier()
	{
		return identifier;
	}
	public void redeemReward(Child c)
	{
		c.redeemItem();
	}
}
