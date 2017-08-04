import java.util.Scanner;


@SuppressWarnings("serial")
public class Token implements java.io.Serializable{
	private String timeStamp;
	private String note;
	public Token()
	{
		timeStamp = "";
		note = "";
	}
	public String getTokenNote() {
		return note;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTokenTime(String time)
	{
		timeStamp = time;
	}
	public void addTokenNote() 
	{
		Scanner sc = new Scanner(System.in);
		note = sc.nextLine();
	}
	public void addAutoTokenNote()
	{
		note = "Automatically generated Token";
	}
}
