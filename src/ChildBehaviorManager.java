import java.util.ArrayList;
import java.util.Scanner;


@SuppressWarnings("serial")
public class ChildBehaviorManager extends User implements java.io.Serializable {
	private ArrayList<Child> children;
	public ChildBehaviorManager()
	{
		children = new ArrayList<Child>();
	}
	public ArrayList<Child> getList()
	{
		return children;
	}
	public void addChild()
	{
		System.out.print("Insert first name of child: ");
		Scanner cbsc = new Scanner(System.in);
		String first = cbsc.nextLine();
		System.out.print("Insert last name of child: ");
		String last = cbsc.nextLine();
		Child newchild = new Child();
		newchild.setName(first, last);
		System.out.print("Please choose the mode option you would like to incorporate: ");
		Mode childMode = new Mode();
		childMode = childMode.ConfigureMode(cbsc);
		newchild.setMode(childMode);
		if(newchild.getMode().getIdentifier() == 0)
		{
			System.out.print("Child adding canceled.\n");
		}
		else
		{
			children.add(newchild);
		}
		
	}

	
	public void editMode(Child c)
	{
		Scanner in = new Scanner(System.in);
		boolean flag = true;
		Mode newMode = new Mode();
		while(flag)
		{
			System.out.println("Please choose one of the avilable options");
			System.out.println("1.) positive");
			System.out.println("2.) negative");
			System.out.println("3. exit");
			int answer = in.nextInt();
			switch (answer) 
			{
				case 1: newMode = new PositiveMode();
					flag = false;
					break;
					
				case 2: newMode = new NegativeMode();
					flag = false;
					break;
				case 3:
					flag = false;
					break;
				default: System.out.print("Incorrect input please try again.");
					break;
			}
		}
		c.setMode(newMode);
	}
	public void editName(Child c)
	{

		System.out.print("Insert first name of child: ");
		Scanner cbsc = new Scanner(System.in);
		String first = cbsc.nextLine();
		System.out.print("Insert last name of child: ");
		String last = cbsc.nextLine();
		c.setName(first, last);
	}
}
