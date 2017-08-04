
@SuppressWarnings("serial")
public class NegativeMode extends Mode implements java.io.Serializable{
	public NegativeMode()
	{
		this.setIdentifier(2);
	}
	public int getIdentifier()
	{
		return identifier;
	}
	public void viewItems()
	{
		printRewardsInList();
	}
}
