package com;

import com.Player;
import com.Dealer;

public class BlackjackGame {
	
	public static void main(String[] args)
	{
		System.out.println("Starting the game!");
	}
	
	//For victor:
	//PLAYER is value 0
	//DEALER is value 1
	
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
}