package com;

import java.util.Scanner;
import com.BlackjackGame;

public class Main {
	
	public static void main(String[] args)
	{
		BlackjackGame game = new BlackjackGame();
		
		System.out.println("Starting the game!");
		
		Scanner reader = new Scanner(System.in);
		char c;
		while(true)
		{
			System.out.print("Enter either f or c: ");
			c = Character.toLowerCase(reader.next().charAt(0));
			if (c == 'f' || c == 'c')
			{
				break;
			}
			System.out.println("That was not one of the letters requested!");
		}
		
		switch(c)
		{
			case 'c':
			{
				game.player.hit(game.deck);
				game.player.hit(game.deck);
				game.dealer.hit(game.deck);
				game.dealer.hit(game.deck);
				
				if (game.dealer.hasBlackjack() || game.player.hasBlackjack())
				{
					game.playOut();
				}
				
				while(game.gameOver == false)
				{
					System.out.println("The player currently has the following cards:");
					System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
					
					System.out.println("The dealer's first card is:");
					System.out.println(game.dealer.hand.get(0));
					
					while (true)
					{
						System.out.print("Enter either h to hit or s to stand and end your turn: ");
						
						char playerChoice;
						
						playerChoice = Character.toLowerCase(reader.next().charAt(0));
						if (playerChoice == 'h')
						{
							game.player.hit(game.deck);
							System.out.println("You hit");
							//System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
							if (game.player.hasBusted())
							{
								game.playOut();
								break;
							}
						}
						else if (playerChoice == 's')
						{
							System.out.println("You stood. Your cards are: ");
							System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
							break;
						}
						else
						{
							System.out.println("That was not one of the values requested!");
						}
					}
					
					reader.close();
					
					game.playOut();
				}
				
				break;
			}
			case 'f':
			{
				break;
			}
		}
	}
}
