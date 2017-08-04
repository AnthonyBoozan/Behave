import java.io.IOException;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;


@SuppressWarnings("serial")
public class Child extends User implements java.io.Serializable{
	private Mode childMode;
	private int numberOfTokensRedemption;
	private ArrayList<Token> listOfTokens;
	private ArrayList<Double> numberOfTokensGraphHelper;
	private ArrayList<String> dateGraphHelper;
	private transient Timer autoTokenAddTime;
	private boolean hasTimer;
	// creates the child object to keep track of information
	public Child()
	{
		childMode = new Mode();
		numberOfTokensRedemption = 0;
		listOfTokens = new ArrayList<Token>();
		autoTokenAddTime = new Timer();
		hasTimer = false;
		numberOfTokensGraphHelper = new ArrayList<Double>();
		dateGraphHelper = new ArrayList<String>();
		numberOfTokensGraphHelper.add(0.0);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		dateGraphHelper.add(formattedDate);
	}
	public ArrayList<Double> getNumberOfTokensGraphHelper()
	{
		return numberOfTokensGraphHelper;
	}
	public ArrayList<String> getDateGraphHelper()
	{
		return dateGraphHelper;
	}
	public Mode getMode()
	{
		return childMode;
	}
	public int getNumberOfTokens()
	{
		return listOfTokens.size();
	}
	public ArrayList<Token> getListOfTokens()
	{
		return listOfTokens;
	}
	//add place holder tokensf
	public void addAutoToken()
	{
		Token autoToken = new Token();
		autoToken.addAutoTokenNote();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);
		autoToken.setTokenTime(formattedDate);
		listOfTokens.add(autoToken);
		dateGraphHelper.add(formattedDate);
		numberOfTokensGraphHelper.add((double) listOfTokens.size());
	}
	public int getnumberOfTokensRedemption()
	{
		return numberOfTokensRedemption; 
	}
	public void setMode(Mode newMode)
	{
		childMode = newMode;
	}
	public void tokenInfo() throws IOException
	{
		Token tempToken = new Token();
		Scanner reader = new Scanner(System.in);
		int token = reader.nextInt();
		tempToken = listOfTokens.get(token);
		System.out.println("The note for token " + token + "is:");
		System.out.println(tempToken.getTokenNote());
		reader.close();
	}
	public void redeemItem()
	{
		if(childMode.getItem().size() == 0)
		{
			System.out.print("No items to redeem at this time.\n");
		}
		else
		{
			this.childMode.printRewardsInList();
			System.out.print("Which item would you like to redeem? Please type the reward you would like to redeem or type exit to quit\n");
			Scanner sc = new Scanner(System.in);
			String item = sc.nextLine();
			if(!item.equals("exit"))
			{
				for(int i = 0; i < childMode.getItem().size(); i++)
				{
					if(childMode.getItem().get(i).getName().equals(item))
					{
						if(childMode.getItem().get(i).getCost() <= listOfTokens.size())
						{
							if(listOfTokens.size() == 0)
							{
								System.out.print("No coins to redeem.\n");
							}
							else
							{
								for(int k = 0; k < childMode.getItem().get(i).getCost(); k++)
								{
									listOfTokens.remove(0);
								}
								System.out.print("Item has been redeemed!\n");
								numberOfTokensRedemption += childMode.getItem().get(i).getCost();
								Date date = new Date();
								SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
								String formattedDate = sdf.format(date);
								dateGraphHelper.add(formattedDate);
								numberOfTokensGraphHelper.add((double) listOfTokens.size());
							}
						}
						else
						{
							System.out.print("Not enough tokens to redeem this item!\n");
						}
					}
				}
			}
			else
			{
				System.out.print("Item redemption has been exited\n");
			}
		}
		
	}
	public void addToken()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("How many tokens would you like to add?\n");
		int choice = 0;
		while(choice == 0)
		{
			while(!sc.hasNextInt())
			{
				System.out.print("Incorrect input, please input an integer from the menu\n");
				sc.next();
			}
			choice = sc.nextInt();
		}
		if(choice != 0)
		{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
			String formattedDate = sdf.format(date);
			Token tokenAdd = new Token();
			tokenAdd.setTokenTime(formattedDate);
			System.out.print("Please include a note for the token(s).");
			tokenAdd.addTokenNote();
			if(choice > 1)
			{
				for(int i = 0; i < choice; i++)
				{
					listOfTokens.add(tokenAdd);
				}
				System.out.print(choice + " tokens added!\n");
				numberOfTokensGraphHelper.add((double) listOfTokens.size());
				dateGraphHelper.add(formattedDate);
			}
			else
			{
				listOfTokens.add(tokenAdd);
				numberOfTokensGraphHelper.add((double) listOfTokens.size());
				dateGraphHelper.add(formattedDate);
			}
		}
	}
	public Timer getAutoTokenAddTime() {
		return autoTokenAddTime;
	}
	public void setAutoTokenAddTime(Timer autoTokenAddTime, boolean t) {
		setHasTimer(t);
		this.autoTokenAddTime = autoTokenAddTime;
	}
	public boolean getHasTimer() {
		return hasTimer;
	}
	public void setHasTimer(boolean hasTimer) {
		this.hasTimer = hasTimer;
	}
	public void cancelTimer()
	{
		autoTokenAddTime.cancel();
		autoTokenAddTime.purge();
		hasTimer = false;
	}
	
}

