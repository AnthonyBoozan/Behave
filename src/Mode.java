import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


@SuppressWarnings("serial")
public class Mode implements java.io.Serializable{
	private ArrayList<ItemRedeem> itemList;
	int identifier;
	public Mode()
	{
		itemList = new ArrayList<ItemRedeem>();
		identifier = 0;
	}
	public void setIdentifier(int i)
	{
		identifier = i;
	}
	public int getIdentifier()
	{
		return identifier;
	}
	public ArrayList<ItemRedeem> getItem()
	{
		return itemList;
	}
	public Mode ConfigureMode(Scanner in)
	{
		
		boolean flag = true;
		Mode newMode = new Mode();
		while(flag)
		{
			System.out.println("Please choose one of the avilable options");
			System.out.println("1.) positive");
			System.out.println("2.) negative");
			System.out.println("3.) exit");
			int answer = 0;
			while(answer == 0)
			{
				while(!in.hasNextInt())
				{
					System.out.print("Incorrect input, please input an integer from the menu\n");
					in.next();
				}
				answer = in.nextInt();
			}
			switch (answer) 
			{
				case 1: newMode = new PositiveMode();
					flag = false;
					break;
					
				case 2: newMode = new NegativeMode();
					flag = false;
					break;
					
				case 3: flag = false;
					break;
				default: System.out.print("Incorrect input please try again.");
					break;
			}
		}
		return newMode;
	}
	public void addItemToList()
	{
		boolean flag = true;
		while(flag)
		{
			flag = false;
			System.out.print("Insert name of new Redeem item:  ");
			Scanner sc = new Scanner(System.in);
			String first = sc.nextLine();
			System.out.print("Insert cost of new Redeem item: ");
			int cost = 0;
			ItemRedeem newItem1 = new ItemRedeem();
			try{
				cost = sc.nextInt();
				
				
			}
			catch(InputMismatchException exception)
			{
			  //Print "This is not an integer"
			  //when user put other than integer
			  System.out.println("This is not an integer");
			  flag = true;
			  
			}
			if(flag == false)
			{
				newItem1.setCost(cost);
				newItem1.setName(first);
				itemList.add(newItem1);
				System.out.print("Item created!\n");
				
			}
		}
		
	}
	public void deleteItemInList()
	{
		boolean flag = true;
		if(itemList.size() == 0)
		{
			System.out.print("No items to delete\n");
			flag = false;
		}
		
		while(flag)
		{
			printRewardsInList();
			System.out.print("Insert name of item to delete or \"exit\" to escape");
			Scanner in = new Scanner(System.in);
			String answer = in.nextLine();
			if(!answer.equals("exit"))
			{
				for(int i = 0; i < itemList.size(); i++)
				{
					if(itemList.get(i).getName().equals(answer))
					{
						System.out.print(itemList.get(i).getName() + " was removed from list\n");
						itemList.remove(i);
						flag = false;
					}
					else if(i == itemList.size() - 1)
					{
						System.out.print("Item to remove was not found\n");
					}
				}
			}
			else
			{
				System.out.print("Item deletion was exited\n");
				flag = false;
			}
		}
		
	}
	public void printRewardsInList()
	{
		
		if(itemList.size() == 0)
		{
			System.out.print("No items added\n");
		}
		else
		{
			System.out.print("The items avilable are as follows: \n");
			for(int i = 0; i < itemList.size(); i++)
			{
				System.out.print(itemList.get(i).getName()+ ": costs " + itemList.get(i).getCost()+ "\n");
			}
		}
		
		
		
	}
	
}
