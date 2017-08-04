
@SuppressWarnings("serial")
public class ItemRedeem implements java.io.Serializable{
	private int cost;
	private String name;
	public ItemRedeem()
	{
		cost = 0;
		name = "";
	}
	public int getCost()
	{
		return cost;
	}
	public String getName()
	{
		return name;
	}
	public void setCost(int newCost)
	{
		cost = newCost;
	}
	public void setName(String newName)
	{
		name = newName;
	}
}
