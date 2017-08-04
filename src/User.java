
@SuppressWarnings("serial")
public class User implements java.io.Serializable{
	private String userName;
	private String firstName;
	private String lastName;
	public User()
	{
		userName = "";
		firstName = "";
		lastName = "";
	}
	public User(String firstName, String lastName, String userName)
	{
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public void enterUsername(String name) {
		userName = name;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setName(String first, String last)
	{
		firstName = first;
		lastName = last;
	}
}

