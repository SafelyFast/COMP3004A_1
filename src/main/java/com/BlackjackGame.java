package com;

import com.Player;
import com.Dealer;

import java.util.Scanner;

public class BlackjackGame {
	
	public static void main(String[] args)
	{
		System.out.println("Starting the game!");
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		char c;
		while(true)
		{
			System.out.println("Enter either f or c: ");
			c = reader.next().charAt(0); // Scans the next token of the input as an int.
			if (c == 'f' || c == 'c')
			{
				break;
			}
			System.out.println("That was not one of the numbers requested!");
		}
		//once finished
		reader.close();
		System.out.println(c);
	}
	
	//For victor:
	//PLAYER is value 0
	//DEALER is value 1
	
	public int PLAYER = 0;
	public int DEALER = 1;
	
	public Player player;
	public Dealer dealer;
	public boolean gameOver;
	public int victor;
	
	public BlackjackGame()
	{
		this.player = new Player();
		this.dealer = new Dealer();
		this.gameOver = false;
		this.victor = -1;
	}
	
	public void isGameDone()
	{
		if (this.player.hasBlackjack())
		{
			gameOver = true;
			if (this.dealer.hasBlackjack())
			{
				victor = DEALER;
				return;
			}
			victor = PLAYER;
		}
	}
}