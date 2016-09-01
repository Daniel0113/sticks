package gameofsticks;

import java.security.SecureRandom;

public class Game 
{
	private int sticksLeft;
	private Player p1, p2;
	private Player currentPlayer;
	private boolean allowPrinting;
	
	public Game(Player player1, Player player2, int numSticks, boolean allowPrint)
	{
		// This gives the choice of printing because bots may need to play thousands of games, and printing them all is a waste.
		allowPrinting = allowPrint; 
		p1 = player1;
		p2 = player2;
		// make sure the number of sticks is between 10 and 1000, else set them to either of the two depending on whether the input is higher or lower
		if(numSticks >= 10 && numSticks <= 1000)
		{
			sticksLeft = numSticks;
			if (allowPrinting)
				System.out.println("The game shall begin with " + sticksLeft + " sticks" );
		}
		else if (numSticks < 10)
		{
			sticksLeft = 10;
			System.out.println("Too few sticks, set to 10 sticks.");
		}
		else
		{
			sticksLeft = 1000;
			System.out.println("Too many sticks, set to 1000 sticks.");
		}
		
		if (flipCoin())
		{
			currentPlayer = p1;
			if (allowPrinting)
			{
				System.out.println("The first player will be " + currentPlayer.getName());
				System.out.println("----------------");
			}
		}
		else 
		{
			currentPlayer = p2;
			if (allowPrinting)
			{	
				System.out.println("The first player will be " + currentPlayer.getName());
				System.out.println("----------------");
			}
		}
	}
	
	public void runGame()
	{
		while(sticksLeft > 0)
		{
			if (allowPrinting)
			{
				if (sticksLeft > 1)
					System.out.println("There are " + sticksLeft + " sticks left.");
				else
					System.out.println("There is 1 stick left.");
				System.out.println("It is " + currentPlayer.getName() + "'s turn.");
			}
			
			int sticksTaken = currentPlayer.takeSticks(sticksLeft);
			sticksLeft -= sticksTaken;
			
			if (allowPrinting)
			{
				System.out.println(currentPlayer.getName() + " has taken " + sticksTaken + " stick(s).");
				System.out.println("----------------");
			}
			switchPlayer(); // Get ready for next turn, aka the next iteration of this loop.
			
		}
		currentPlayer.win(); // When the loop ends, the player who didn't cause the stick amount to be 0 will be the winner
		if (allowPrinting)
			System.out.println(currentPlayer.getName() + " has won!");
		switchPlayer();
		currentPlayer.lose(); // obviously, the other player will be the loser
		
	}

	private static boolean flipCoin() // returns a 1 or 0
	{
		SecureRandom r = new SecureRandom();
		if (r.nextInt(2) == 0) 
			return false; 
		else 
			return true;
	}
	
	private void switchPlayer()
	{
		if (currentPlayer == p1)
			currentPlayer = p2;
		else
			currentPlayer = p1;
	}
}
