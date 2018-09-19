package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.BlackjackGame;

public class Main {
	
	static int DEALER = 1;
	
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
					System.out.println("Somebody got a blackjack!");
					System.out.println((game.dealer.hasBlackjack() ? "Dealer" : "Player") + " has blackjack!");
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
							System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
							if (game.player.hasBusted())
							{
								System.out.println("You busted!");
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
							System.out.println("That was not one of the letters requested!");
						}
					}
					
					if (game.gameOver == false)
					{
						System.out.println("The dealer reveals his cards. They are: ");
						System.out.println(game.dealer.hand.toString().substring(1, game.dealer.hand.toString().length() - 1));
						
						while (true)
						{
							if (game.dealer.mustHit() || game.dealer.hasSoft17())
							{
								System.out.println("Dealer is hitting");
								game.dealer.hit(game.deck);
								System.out.println("Dealer received a " + game.dealer.hand.get(game.dealer.hand.size() - 1));
								if (game.dealer.hasBusted())
								{
									System.out.println("Dealer busts!");
									game.playOut();
									break;
								}
							}
							else
							{
								System.out.println("Dealer stands. His hand is: ");
								System.out.println(game.dealer.hand.toString().substring(1, game.dealer.hand.toString().length() - 1));
								break;
							}
						}
					}
					
					reader.close();
					
					game.playOut();
					
					System.out.println((game.victor == DEALER ? "Dealer" : "Player") + " wins!");
				}
				
				break;
			}
			case 'f':
			{
				String file = null;
				Scanner inputFile = null;
				List<String> deck = new ArrayList<String>();
				String drawnCard = null;
				
				while (true)
				{
					System.out.print("Enter the name of the file (and extension) that you would like to open. It MUST be in the src/main/resources directory: ");
					file = reader.next();
					try 
					{
						inputFile = new Scanner(new File("./src/main/resources/" + file));
						while (inputFile.hasNextLine())
						{
							deck.add(inputFile.nextLine());
						}
						break;
					}
					catch(FileNotFoundException e)
					{
						System.out.println("The file you specified does not exist");
					}
				}
				
				inputFile.close();
				
				game.player.hit(deck);
				game.player.hit(deck);
				game.dealer.hit(deck);
				game.dealer.hit(deck);
				
				System.out.println("The player currently has the following cards:");
				System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
				
				System.out.println("The dealer's first card is:");
				System.out.println(game.dealer.hand.get(0));
				
				//MAKE PLAYER TURN AND DEALER TURN
				
				if (game.dealer.hasBlackjack() || game.player.hasBlackjack())
				{
					System.out.println("Somebody got a blackjack!");
					System.out.println((game.dealer.hasBlackjack() ? "Dealer" : "Player") + " has blackjack!");
					game.playOut();
				}
				
				while(true)
				{
					if (deck.isEmpty())
					{
						break;
					}
					drawnCard = deck.remove(0);
					if (drawnCard.equals("H"))
					{
						game.player.hit(deck);
						System.out.println("You hit");
						System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
						if (game.player.hasBusted())
						{
							System.out.println("You busted!");
							game.playOut();
							break;
						}
					}
					else if (drawnCard.equals("S"))
					{
						System.out.println("You stood. Your cards are: ");
						System.out.println(game.player.hand.toString().substring(1, game.player.hand.toString().length() - 1));
						break;
					}
				}
				
				if (game.gameOver == false)
				{
					System.out.println("The dealer reveals his cards. They are: ");
					System.out.println(game.dealer.hand.toString().substring(1, game.dealer.hand.toString().length() - 1));
					
					while (true)
					{
						if (deck.isEmpty() == true)
						{
							System.out.println("Dealer stands. His hand is: ");
							System.out.println(game.dealer.hand.toString().substring(1, game.dealer.hand.toString().length() - 1));
							break;
						}
						System.out.println("Dealer is hitting");
						game.dealer.hit(deck);
						System.out.println("Dealer received a " + game.dealer.hand.get(game.dealer.hand.size() - 1));
						if (game.dealer.hasBusted())
						{
							System.out.println("Dealer busts!");
							game.playOut();
							break;
						}
					}
				}
				
				game.playOut();
				
				System.out.println((game.victor == DEALER ? "Dealer" : "Player") + " wins!");
				
				break;
			}
		}
	}
}
