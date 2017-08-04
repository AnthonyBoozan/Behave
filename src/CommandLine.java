import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.renderer.xy.*;


@SuppressWarnings("serial")
public class CommandLine implements java.io.Serializable{
	public static void main(String[] args)
	{
		File file = new File("savedata.ser");
		Scanner sc = new Scanner(System.in);
		ChildBehaviorManager theUser = null;
		if (file.exists())
		{
			try{
				FileInputStream fileIn = new FileInputStream(file);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				theUser = (ChildBehaviorManager) in.readObject();
				in.close();
				fileIn.close();
			}
			catch(IOException i){
				i.printStackTrace();
				return;
			}
			catch(ClassNotFoundException c){
				System.out.println("Class not found");
				c.printStackTrace();
				return;
			}
			System.out.println("Welcome back" + " " + theUser.getFirstName() + " " + theUser.getLastName());
			
		}
		else
		{
			theUser = new ChildBehaviorManager();
			System.out.println("First time user please insert information. ");
			System.out.print("Please input username: ");
			String username = sc.nextLine();
			theUser.enterUsername(username);
			System.out.print("Please input first name: ");
			String firstName = sc.nextLine();
			System.out.print("Please input last name: ");
			String lastName = sc.nextLine();
			theUser.setName(firstName, lastName);
			
			System.out.println("Hello and welcome " + firstName + " " + lastName + " to the Behave! application demo!");
			System.out.println("First we need to add a child to make sure they Behave!");
			theUser.addChild();
		}
		
		int choice = 0;
		boolean trigger = true;
		while(trigger)
		{
			System.out.println("Which user would you like to access");
			System.out.println("1.) ChildManager User");
			System.out.println("2.) Child User");
			System.out.println("3.) Exit");
			choice = 0;
			while(choice == 0)
			{
				while(!sc.hasNextInt())
				{
					System.out.print("Incorrect input, please input an integer from the menu\n");
					sc.next();
				}
				choice = sc.nextInt();
			}
			switch (choice) {
			case 1: 
				boolean flag = true;
				while(flag)
				{
					System.out.println("Please choose which option you would like to take from the menu");
					System.out.println("*** MAIN MENU ***");
					System.out.println("1: Add/Remove Child");
					System.out.println("2: Add/remove redeemable item to a child");
					System.out.println("3: Redeem item for child");
					System.out.println("4: Add token(s) to child");
					System.out.println("5: Edit child info");
					System.out.println("6: Print child info");
					System.out.println("7: Add/edit auto token to a child");
					System.out.println("8: Get graph");
					System.out.println("9: exit");
					choice = 0;
					while(choice == 0)
					{
						while(!sc.hasNextInt())
						{
							System.out.print("Incorrect input, please input an integer from the menu\n");
							sc.next();
						}
						choice = sc.nextInt();
					}

					switch (choice) {
					
					case 1:
						//This case is for the user input and output of adding and removing a child
						System.out.println("1.) Add child");
						System.out.println("2.) Remove child");
						System.out.println("3.) exit");
						choice = 0;
						while(choice == 0)
						{
							while(!sc.hasNextInt())
							{
								System.out.print("Incorrect input, please input an integer from the menu\n");
								sc.next();
							}
							choice = sc.nextInt();
						}
						switch(choice)
						{
						case 1: 
							//Adds child to the user
							theUser.addChild();
							break;
						case 2:
							//Removes child from the user
							if(theUser.getList().size() == 0)
							{
								System.out.print("No children avilable\n");
								break;
							}
							System.out.print("Choose which child to remove: \n");
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
							}
							sc = new Scanner(System.in);
							System.out.print("First name: \n");
							String fname = sc.nextLine();
							System.out.print("Last name: \n");
							String lname = sc.nextLine();
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
								{
									theUser.getList().remove(i);
								}
								else if (i == theUser.getList().size() - 1)
								{
									System.out.print("Child not found please try again\n");
								}
							}
							break;
						default: break;
						}
						
						break;
					case 2:
						//Adds items to redeem to child
						if(theUser.getList().size() == 0)
						{
							System.out.println("No children avilable");
							break;
						}
						System.out.println("Choose which child to add/delete an item too/from: ");
						for(int i = 0; i < theUser.getList().size(); i++)
						{
							System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
						}
						
						boolean flag2 = true;
						while(flag2)
						{
							//change-
							sc = new Scanner(System.in);
							System.out.print("First name: ");
							String fname = sc.nextLine();
							System.out.print("Last name: ");
							String lname = sc.nextLine();
							//-change
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
								{
									System.out.println("What would you like to edit?");
									System.out.println("1.) Add item");
									System.out.println("2.) remove item");
									System.out.println("3.) exit");
									//change-
									choice = 0;
									while(choice == 0)
									{
										while(!sc.hasNextInt())
										{
											System.out.print("Incorrect input, please input an integer from the menu\n");
											sc.next();
										}
										choice = sc.nextInt();
									}
									//-change
									switch(choice)
									{
									case 1: theUser.getList().get(i).getMode().addItemToList();
									//Adds item
									break;
									case 2: 
										theUser.getList().get(i).getMode().deleteItemInList();
									//Removes item
									break;
									}
									flag2 = false;
								}
								else if (i == theUser.getList().size() - 1 && !(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName())))
								{
									System.out.print("Child not found please try again\n");
								}
							}
						}
						break;
					case 3:
						//Redeems item for child
						if(theUser.getList().size() == 0)
						{
							System.out.print("No children avilable\n");
							break;
						}
						System.out.println("Choose which child to redeem an item too:");
						for(int i = 0; i < theUser.getList().size(); i++)
						{
							System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + ": Number of items " + theUser.getList().get(i).getMode().getItem().size()+ "\n");
						}
						boolean flag3 = true;
						while(flag3)
						{
							sc = new Scanner(System.in);
							System.out.print("First name: \n");
							String fname = sc.nextLine();
							System.out.print("Last name: \n");
							String lname = sc.nextLine();
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
								{
									theUser.getList().get(i).redeemItem();
									flag3 = false;
									break;
								}
								if (i == theUser.getList().size() - 1 && !(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName())))
								{
									System.out.print("Child not found please try again\n");
								}
							}
						}
						break;
					case 4:
						//add tokens to a child
						if(theUser.getList().size() == 0)
						{
							System.out.print("No children avilable\n");
							break;
						}
						else
						{
							System.out.print("Choose which child add tokens too: \n");
							
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + ": " + theUser.getList().get(i).getNumberOfTokens() + "\n");
							}
							//change
							sc = new Scanner(System.in);
							System.out.print("First name: ");
							String fname = sc.nextLine();
							System.out.print("Last name: ");
							String lname = sc.nextLine();
							//change
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
								{
									theUser.getList().get(i).addToken();
									break;
								}
								if (i == theUser.getList().size() - 1 && !(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName())) )
								{
									System.out.print("Child not found please try again\n");
								}
							}
						}
						
						break;
					case 5: 
						//Edit child info
						if(theUser.getList().size() == 0)
						{
							System.out.print("No children avilable\n");
							break;
						}
						System.out.print("Choose which child to edit: \n");
						
						for(int i = 0; i < theUser.getList().size(); i++)
						{
							System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + ": " + theUser.getList().get(i).getNumberOfTokens() + "\n");
						}
						// change
						sc = new Scanner(System.in);
						System.out.print("First name: \n");
						String finame = sc.nextLine();
						System.out.print("Last name: \n");
						String laname = sc.nextLine();
						// change
						for(int i = 0; i < theUser.getList().size(); i++)
						{
							if(finame.equals(theUser.getList().get(i).getFirstName()) &&  laname.equals(theUser.getList().get(i).getLastName()) )
							{
								System.out.print("What would you like to edit?\n");
								System.out.print("1.) Mode\n");
								System.out.print("2.) Childs name\n");
								System.out.println("3.) exit");
								//change-
								choice = 0;
								while(choice == 0)
								{
									while(!sc.hasNextInt())
									{
										System.out.print("Incorrect input, please input an integer from the menu\n");
										sc.next();
									}
									choice = sc.nextInt();
								}
								//-change
								switch(choice)
								{
									case 1:
										theUser.editMode(theUser.getList().get(i));
										System.out.print("Mode has been edited");
										
										break;
									case 2:
										theUser.editName(theUser.getList().get(i));
										System.out.print("Mode has been edited");
										break;
									case 3:
										break;
									default: break;
								}
								
								
							}
							finame.equals(theUser.getList().get(i).getFirstName());
							laname.equals(theUser.getList().get(i).getLastName());
							if (i == theUser.getList().size() - 1 && !(finame.equals(theUser.getList().get(i).getFirstName()) &&  laname.equals(theUser.getList().get(i).getLastName())) )
							{
								System.out.print("Child not found please try again\n");
							}
						}
						break;
					case 6:
						//print child info
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								
								System.out.print("Name: " + theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
								System.out.print("Number of Tokens redeemed: " + theUser.getList().get(i).getnumberOfTokensRedemption() + "\n");
								System.out.print("Number of Tokens: " + theUser.getList().get(i).getListOfTokens().size() + "\n");
								//change
								//remove String mode = new String();
								//change
								if(theUser.getList().get(i).getMode().getIdentifier() == 1)
								{
									System.out.print("Mode: positive\n");
								}
								else if(theUser.getList().get(i).getMode().getIdentifier() == 2)
								{
									System.out.print("Mode: negative\n");
								}
								for(int j = 0; j < theUser.getList().get(i).getListOfTokens().size(); j++)
								{
									System.out.print("Note for token " + (j + 1) + ": " + theUser.getList().get(i).getListOfTokens().get(j).getTokenNote() + " ;time acquired " +  theUser.getList().get(i).getListOfTokens().get(j).getTimeStamp() + "\n");
								}
								System.out.print("-----------------------------\n");
							}
							break;
					case 7: 
						//add automatic tokens 
						boolean flagCase7 = true;
						while(flagCase7)
						{
							if(theUser.getList().size() == 0)
							{
								System.out.print("No children avilable\n");
								break;
							}
							System.out.println("Choose which child to edit: ");
							
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								System.out.print(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
							}
							//change
							sc = new Scanner(System.in);
							System.out.print("First name: ");
							finame = sc.nextLine();
							System.out.print("Last name: ");
							laname = sc.nextLine();
							//change
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								if(finame.equals(theUser.getList().get(i).getFirstName()) &&  laname.equals(theUser.getList().get(i).getLastName()) )
								{
									long totalTime = 0;
									long hours = 0;
									long minutes = 0;
									long seconds = 0;
									try{
										System.out.print("Please input the information needed. to clear the autoadd put 0 for all input areas\n");
										System.out.print("Please input the number of hours\n");
										hours = sc.nextInt();
										hours = hours * 3600000;
										System.out.print("Please input the number of minutes\n");
										minutes = sc.nextInt();
										minutes = minutes * 60000;
										System.out.print("Please input the number of seconds\n");
										seconds = sc.nextInt();
										seconds = seconds * 1000;
										totalTime = seconds + minutes + hours;
										if(totalTime == 0 && (theUser.getList().get(i).getHasTimer()) == true)
										{
											theUser.getList().get(i).cancelTimer();
											Timer ntime = new Timer();
											ntime.schedule(new AutoAddTokens(theUser.getList().get(i), totalTime, false ), totalTime);
											theUser.getList().get(i).setHasTimer(false);
											System.out.println("Auto token canceled");
										}
										else if(totalTime == 0 && theUser.getList().get(i).getHasTimer() == false)
										{
											System.out.println("Auto token add canceled");
										}
										else
										{
											Timer ntime = new Timer();
											ntime.schedule(new AutoAddTokens(theUser.getList().get(i), totalTime,true ), totalTime);
											theUser.getList().get(i).setAutoTokenAddTime(ntime, true);
											System.out.println("Auto token timer added!");
										}
										
										
										flagCase7 = false;
									}
									catch(InputMismatchException exception)
									{
										System.out.print("Please only input integers.\n");
									}
									
								}
								else if(i == theUser.getList().size() - 1 && flagCase7 == true)
								{
									System.out.print("Child not found please try again\n");
								}
							}
						}
						break;
					case 8:
						ChartHandler ch = new ChartHandler();
						if(theUser.getList().size() == 0)
						{
							System.out.print("No children avilable\n");
							break;
						}
						else
						{
							System.out.println("Choose which child to redeem an item too:");
							for(int i = 0; i < theUser.getList().size(); i++)
							{
								System.out.println(theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName());
							}
							
							flag2 = true;
							while(flag2)
							{
								sc = new Scanner(System.in);
								System.out.print("First name: \n");
								String fname = sc.nextLine();
								System.out.print("Last name: \n");
								String lname = sc.nextLine();
								for(int i = 0; i < theUser.getList().size(); i++)
								{
									if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
									{
										if(theUser.getList().get(i).getListOfTokens().size() == 0)
										{
											System.out.println("No tokens to graph");
										}
										else
										{
											JFreeChart chart = ch.CreateChart(theUser.getList().get(i));
											ChartPanel chartPanel = new ChartPanel(chart);
											chartPanel.setDomainZoomable(true);
											
											
											JPanel jPanel4 = new JPanel();
									        jPanel4.setLayout(new BorderLayout());
									        jPanel4.add(chartPanel, BorderLayout.NORTH);

									        JFrame frame = new JFrame();
									        frame.add(jPanel4);
									        frame.pack();
									        frame.setVisible(true);
										}
										flag2 = false;
										break;
									}
									if (i == theUser.getList().size() - 1 && !(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName())))
									{
										System.out.print("Child not found please try again\n");
									}
								}
							}
						}
						
						break;
					case 9:
						flag = false;
						break;
					default: 
						System.out.print("Invalid option.\n");
						break;
					}
					
				}
				break;
			case 2:
				//access the child menu
				if(theUser.getList().size() == 0)
				{
					System.out.print("No children avilable\n");
					break;
				}
				//change
				// remove boolean flags = true;
				//change
				System.out.print("Which child would like to access their account \n");
				for(int i = 0; i < theUser.getList().size(); i++)
				{
					System.out.print(i + 1 + ".) " + theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
				}
				boolean flag2 = true;
				while(flag2)
				{
					//change
					sc = new Scanner(System.in);
					System.out.print("First name: \n");
					String fname = sc.nextLine();
					System.out.print("Last name: \n");
					String lname = sc.nextLine();
					//change
					for(int i = 0; i < theUser.getList().size(); i++)
					{
						if(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName()) )
						{
							if(theUser.getList().get(i).getMode().getIdentifier() == 1)
							{
								flag = true;
								while(flag)
								{
									System.out.println("Please choose which option you would like to take from the menu");
									System.out.println("*** MAIN MENU ***");
									System.out.println("1.) Redeem Item");
									System.out.println("2.) View status");
									System.out.println("3.) exit");
									//change-
									choice = 0;
									while(choice == 0)
									{
										while(!sc.hasNextInt())
										{
											System.out.print("Incorrect input, please input an integer from the menu\n");
											sc.next();
										}
										choice = sc.nextInt();
									}
									//-change
									switch(choice){
									case 1: theUser.getList().get(i).redeemItem();
									//redeems items for child
										break;
									case 2:
										// view info about child
										System.out.print("Name: " + theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
										System.out.print("Number of Tokens redeemed: " + theUser.getList().get(i).getnumberOfTokensRedemption() + "\n");
										System.out.print("Number of Tokens: " + theUser.getList().get(i).getListOfTokens().size() + "\n");
										String mode = new String();
										if(theUser.getList().get(i).getMode().getIdentifier() == 1)
										{
											System.out.print("Mode: positive\n");
										}
										else if(theUser.getList().get(i).getMode().getIdentifier() == 2)
										{
											System.out.print("Mode: negative\n");
										}
										for(int j = 0; j < theUser.getList().get(i).getListOfTokens().size(); j++)
										{
											System.out.print("Note for token " + (j + 1) + ": " + theUser.getList().get(i).getListOfTokens().get(j).getTokenNote() + " ;time acquired " +  theUser.getList().get(i).getListOfTokens().get(j).getTimeStamp() + "\n");
										}
										System.out.print("\n");
										break;
									case 3:
										//exit
										flag = false;
										break;
									default: 
										System.out.print("Invalid option.\n");
										break;
									}
								}
							}
							else
							{
								flag = true;
								while(flag)
								{
									System.out.println("Please choose which option you would like to take from the menu");
									System.out.println("*** MAIN MENU ***");
									System.out.println("1.) View bad consequences bad boy.");
									System.out.println("2.) View status");
									System.out.println("3.): exit");
									choice = 0;
									while(choice == 0)
									{
										while(!sc.hasNextInt())
										{
											System.out.print("Incorrect input, please input an integer from the menu\n");
											sc.next();
										}
										choice = sc.nextInt();
									}
									switch(choice){
									case 1: theUser.getList().get(i).getMode().printRewardsInList();
									//gets reward for child
										break;
									case 2:
										//view status of child
										System.out.print("Name: " + theUser.getList().get(i).getFirstName() + " " + theUser.getList().get(i).getLastName() + "\n");
										System.out.print("Number of Tokens redeemed: " + theUser.getList().get(i).getnumberOfTokensRedemption() + "\n");
										System.out.print("Number of Tokens: " + theUser.getList().get(i).getListOfTokens().size() + "\n");
										//change-
										// remove String mode = new String();
										//-change
										if(theUser.getList().get(i).getMode().getIdentifier() == 1)
										{
											System.out.print("Mode: positive\n");
										}
										else if(theUser.getList().get(i).getMode().getIdentifier() == 2)
										{
											System.out.print("Mode: negative\n");
										}
										for(int j = 0; j < theUser.getList().get(i).getListOfTokens().size(); j++)
										{
											System.out.print("Note for token " + (j + 1) + ": " + theUser.getList().get(i).getListOfTokens().get(j).getTokenNote() + " ;time acquired " +  theUser.getList().get(i).getListOfTokens().get(j).getTimeStamp() + "\n");
										}
										System.out.print("\n");
										break;
									case 3:
										//exit
										flag = false;
										break;
									default: 
										System.out.print("Invalid option.\n");
										break;
									}
								}
								
							}
							flag2 = false;
						}
						else if (i == theUser.getList().size() - 1 && !(fname.equals(theUser.getList().get(i).getFirstName()) &&  lname.equals(theUser.getList().get(i).getLastName())))
						{
							System.out.print("Child not found please try again\n");
						}
					}
					
				}
				break;
			case 3:
				//exit
				System.out.println("Thank you for using the application!");
				trigger = false;
				try {
					FileOutputStream fileOut = new FileOutputStream(file);
					if (!file.exists())
					{
						file.createNewFile();
					}
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(theUser);
					out.close();
					fileOut.close();
					System.out.println("Data saved!");
				}
				catch(IOException i)
				{
					i.printStackTrace();
				}
				sc.close();
				break;
			default: 
				System.out.print("Invalid option.\n");
				break;
			}
		}
	}
}
