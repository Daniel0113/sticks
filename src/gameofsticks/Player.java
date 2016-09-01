package gameofsticks;

public abstract class Player 
{
	private int wins;
	private int losses;
	private String name;
	
	// boilerplate funtimes
	protected Player()
	{
		wins = 0;
		losses = 0;
		name = "";
	}
	
	protected Player(String playerName)
	{
		wins = 0;
		losses = 0;
		name = playerName;
	}
	
	// Standard get/set methods, aka more boilerplate
	public int getWins()
	{
		return wins;
	}
	
	public int getLosses()
	{
		return losses;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String newName)
	{
		name = newName;
	}
	
	public void incrementWins()
	{
		wins++;
	}
	
	public void incrementLosses()
	{
		losses++;
	}
	
	//public void printRecords()
	//{
		//System.out.println(name + "'s Wins: " + wins);
		//System.out.println(name + "'s Losses: " + losses);
	//}
	// ABSTRACT METHODS
	
	// takeSticks will be the output used to determine what amount of sticks are being taken
	// Bots will have their own way of choosing the amount of sticks they're taking. While humans use console input.
	
	public abstract int takeSticks(int sticksLeft); 
	public abstract void win();
	public abstract void lose();
	public abstract void printRecords();
	
	
	
}
