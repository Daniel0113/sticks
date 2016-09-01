package gameofsticks;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class AI extends Player
{
	// These will be the hats the AI uses to randomly select a stick
	// This is an array of arraylists because the game will be limited to max 1000 sticks, but will have varied entries in each hat.
	private ArrayList<Integer>[] hats;// = new ArrayList[1000];     
	private int[] takenAmounts;// = new int[1000];
	
	public AI(String username)
	{
		super(username);
		
		hats = new ArrayList[1000];
		takenAmounts = new int[1000];
		for(int i = 0; i < 1000; i++)
		{
			hats[i] = new ArrayList<Integer>();
			// a new AI will have an equal chance of choosing 1, 2, or 3 sticks.
			hats[i].add(1);
			hats[i].add(2);
			hats[i].add(3);
		}
		
		hats[0].remove(2);
		hats[0].remove(1);
		hats[1].remove(2); 
		// These three indices are removed to prevent the bot from removing more sticks than possible.
		// e.g. removing 2 sticks when there is 1 stick left
	}
	
	
	private void clearTakenAmounts()
	{
		for(int i = 0; i < 1000; i++)
		{
			takenAmounts[i] = 0;
		}
	}

	// ABSTRACT METHODS
	
	public int takeSticks(int sticksLeft) 
	{
		SecureRandom r = new SecureRandom();
		int size = hats[sticksLeft - 1].size(); // hats goes from 0-999, so 4 sticks left for example would be index 3 in hats.
		int choice = r.nextInt(size);
		int sticksToBeTaken = hats[sticksLeft - 1].get(choice);
		
		takenAmounts[sticksLeft - 1] = sticksToBeTaken;
		return sticksToBeTaken;
	}
	
	public void win()
	{
		incrementWins();
		
		for(int i = 0; i < 1000; i++)
		{
			if (takenAmounts[i] != 0)
			{
				hats[i].add(takenAmounts[i]);
			}
		}
		
		clearTakenAmounts();
		
	}
	
	public void lose()
	{
		incrementLosses();
		clearTakenAmounts();
	}
	
	public void printRecords()
	{
		Scanner s = new Scanner(System.in);
		int answer;
		System.out.println(getName() + "'s Wins: " + getWins());
		System.out.println(getName() + "'s Losses: " + getLosses());
		
		System.out.println("Would you like to print out the bot's hats?");
		System.out.println("Warning, this will result in a massive print output.");
		System.out.println("Yes (1) No (2)");
		
		answer = s.nextInt();
		if (answer == 1)
		{
			for(int i = 0; i < hats.length; i++)
			{
				System.out.print("Hat " + (i+1) + ": ");
				for(int j = 0; j < hats[i].size(); j++)
				{
					System.out.print(hats[i].get(j) + " ");
				}
				System.out.println("\n");
			}
		}
		
	}
	
}
