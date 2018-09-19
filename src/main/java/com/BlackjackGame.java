package com;

import com.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.Dealer;

public class BlackjackGame {
	
	//For victor:
	//PLAYER is value 0
	//DEALER is value 1
	
	public static int PLAYER = 0;
	public static int DEALER = 1;
	
	public Player player;
	public Dealer dealer;
	public boolean gameOver;
	public int victor;
	
	List<String> deck;
	
	public BlackjackGame()
	{
		this.player = new Player();
		this.dealer = new Dealer();
		this.gameOver = false;
		this.victor = -1;
		
		this.deck = new ArrayList<String>();
		
		Scanner input = null;
		
		try {
			input = new Scanner(new File("./src/main/resources/deck"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (input.hasNextLine())
		{
			deck.add(input.nextLine());
		}
		
		input.close();
		
		Collections.shuffle(deck);
	}
	
	public void playOut()
	{
		if (this.dealer.hasBusted())
		{
			gameOver = true;
			victor = PLAYER;
			return;
		}
		
		if (this.player.hasBusted())
		{
			gameOver = true;
			victor = DEALER;
			return;
		}
		
		if (this.dealer.hasBlackjack())
		{
			gameOver = true;
			victor = DEALER;
			return;
		}
		
		if (this.player.hasBlackjack())
		{
			gameOver = true;
			victor = PLAYER;
		}
		
		if (this.player.score > this.dealer.score)
		{
			gameOver = true;
			victor = PLAYER;
		}
		
		if (this.dealer.score > this.player.score)
		{
			gameOver = true;
			victor = DEALER;
		}
		
		if (this.dealer.score == this.player.score)
		{
			gameOver = true;
			victor = DEALER;
		}
	}
}