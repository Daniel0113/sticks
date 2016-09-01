package gameofsticks;

import java.util.Scanner;

public class GameManager 
{
	private AI[] bots;
	private Human[] humans;
	
	public GameManager()
	{
		bots = new AI[2];
		humans = new Human[2];
		
		bots[0] = new AI("BOT Dummy");
		bots[1] = new AI("BOT Genius");
		
		humans[0] = new Human("Player 1");
		humans[1] = new Human("Player 2");
		
	}
	
	public void beginLoop()
	{
		boolean finished = false;
		int choice;
		
		while (!finished)
		{
			choice = showOptions();
			switch (choice)
			{
				case 1:
					fightPlayer();
					break;
				case 2:
					fightDumbBot();
					break;
				case 3:
					fightSmartAI();
					break;
				case 4:
					if(bots[1].getWins() == 0 && bots[1].getLosses() == 0)
					{
						Scanner s = new Scanner(System.in);
						System.out.println("How many games do you want to train the smart AI with?");
						trainBot(s.nextInt());
					}
					printSmartAIRecords();
					break;
				case 5:
					finished = true;
					break;
				default:
					break;
			}
		}
		
		System.out.println("Goodbye!");
	}
	
	public int showOptions()
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("Welcome to Game of Sticks!");
		System.out.println("Options:");
		System.out.println("(1) Play with a buddy");
		System.out.println("(2) Play with a dumb bot");
		System.out.println("(3) Play with a smart bot");
		System.out.println("(4) View smart bot stats");
		System.out.println("(5) Quit");
		
		return s.nextInt();
		
		
	}
	
	private void trainBot(int numGames)
	{
		Player tempBot = new AI("BOT GENERIC");
		
		for(int i = 0; i < numGames; i++)
		{
			Game g = new Game(bots[1], tempBot, 1000, false);
			g.runGame();
		}
		
		System.out.println("Training " + bots[1].getName() + " complete. ");
	}
	
	private void fightPlayer() // This will be used with choice 1
	{
		Scanner s = new Scanner(System.in);
		boolean finished = false;
		int startingSticks;

		while(!finished)
		{
			System.out.println("How many starting sticks? ");
			startingSticks = s.nextInt();
			Game g = new Game(humans[0], humans[1], startingSticks, true);
			g.runGame();
			
			System.out.println("Play again? Yes(1) No(2)");
			
			if(s.nextInt() == 2)
				finished = true;
		}
	}
	
	private void fightDumbBot()
	{
		boolean finished = false;
		Scanner s = new Scanner (System.in);
		int startingSticks;
		
		while(!finished)
		{
			System.out.println("How many starting sticks? ");
			startingSticks = s.nextInt();
			
			Game g = new Game(humans[0], bots[0], startingSticks, true);
			g.runGame();
			
			System.out.println("Play again? Yes(1) No(2)");
			
			if(s.nextInt() == 2)
				finished = true;
		}
	}
	
	private void fightSmartAI() // this will be used with choice 3 
	{
		boolean finished = false;
		Scanner s = new Scanner (System.in);
		int startingSticks;
		
		if(bots[1].getWins() == 0 && bots[1].getLosses() == 0)
		{
			System.out.println("How many games do you want to train the smart AI with?");
			trainBot(s.nextInt());
		}
		
		while(!finished)
		{
			System.out.println("How many starting sticks? ");
			startingSticks = s.nextInt();
			
			Game g = new Game(humans[0], bots[1], startingSticks, true);
			g.runGame();
			
			System.out.println("Play again? Yes(1) No(2)");
			
			if(s.nextInt() == 2)
				finished = true;
		}
		
		bots[1].printRecords();
		
	}
	
	private void printSmartAIRecords()
	{
		bots[1].printRecords();
	}

}
