package gameofsticks;
import java.util.Scanner;

public class Human extends Player
{
	public Human(String username)
	{
		super(username);
	}
	
	
	// ABSTRACT METHODS
	
	// This method outputs the user's choice in how many sticks they pick
	// the while loop enforces the allowance of only 1-3 sticks
	public int takeSticks(int sticksLeft)
	{
		boolean successful = false;
		int output = 0;
		Scanner s = new Scanner(System.in);
		
		while(!successful)
		{	
			output = s.nextInt();
			
			if(output >= 1 && output <= 3 && output <= sticksLeft)
				successful = true;
			else
				System.out.println("Insufficient number of sticks. ");
		}
		
		return output;
		
	}
	
	public void win()
	{
		incrementWins();
	}
	
	public void lose()
	{
		incrementLosses();
	}
	
	public void printRecords()
	{
		System.out.println(getName() + "'s Wins: " + getWins());
		System.out.println(getName() + "'s Losses: " + getLosses());
	}

}
