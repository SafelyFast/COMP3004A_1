package com;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.BlackjackGame;

public class BlackjackTest extends TestCase{
	
	int DEALER = 1;
	int PLAYER = 0;

	public void testDraw()
	{
		BlackjackGame game = new BlackjackGame();
		
		////Requirement 36 Test
		game.player.drawCard("HK");
		
		assertEquals(10, game.player.score);
		
		game.player.clearHand();
		
		game.player.drawCard("HQ");
		
		assertEquals(10, game.player.score);
		
		game.player.clearHand();
		
		game.player.drawCard("HJ");
		
		assertEquals(10, game.player.score);
		
		//Requirement 32 Test
		game.player.drawCard("CA");
		
		assertEquals(21, game.player.score);
		
		game.player.clearHand();
		
		//Requirement 31 Test
		game.player.drawCard("HK");
		game.player.drawCard("CK");
		game.player.drawCard("CA");
		
		assertEquals(21, game.player.score);
		
		game.player.clearHand();
		
		//Requirement 35 Test
		game.player.drawCard("HK");
		game.player.drawCard("H3");
		game.player.drawCard("CA");
		game.player.drawCard("HA");
		
		assertEquals(15, game.player.score);
	}
	
	public void testBust()
	{
		BlackjackGame game = new BlackjackGame();
		
		//Make the player bust
		game.player.setScore(22);
		
		assertEquals(true, game.player.hasBusted());
		
		//Check to see if near bust's actually bust
		game.player.setScore(21);
		
		assertEquals(false, game.player.hasBusted());
		
		//Make the dealer bust
		game.dealer.setScore(22);
		
		assertEquals(true, game.dealer.hasBusted());
		
		//Check to see if near bust's actually bust
		game.dealer.setScore(21);
		
		assertEquals(false, game.dealer.hasBusted());
		
		game.dealer.clearHand();
		game.player.clearHand();
		
		game.dealer.drawCard("HK");
		game.dealer.drawCard("SK");
		game.dealer.drawCard("CK");
		
		game.playOut();
		
		assertEquals(true, game.gameOver == true && game.victor == PLAYER);
		
		game.dealer.clearHand();
		game.player.clearHand();
		
		game.player.drawCard("HK");
		game.player.drawCard("SK");
		game.player.drawCard("CK");
		
		game.playOut();
		
		assertEquals(true, game.gameOver == true && game.victor == DEALER);
	}
	
	public void testNumCardsInHand()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("CA");
		game.player.drawCard("HQ");
		
		assertEquals(2, game.player.hand.size());
	}
	
	public void testBlackjack()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("CA");
		game.player.drawCard("HQ");
		
		assertEquals(true, game.player.hasBlackjack());
		
		game.player.clearHand();
		
		game.player.drawCard("CK");
		game.player.drawCard("HQ");
		
		assertEquals(false, game.player.hasBlackjack());
		
		game.dealer.drawCard("CA");
		game.dealer.drawCard("HQ");
		
		assertEquals(true, game.dealer.hasBlackjack());
	}
	
	public void testBothBlackjack()
	{		
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("SK");
		game.player.drawCard("HA");
		
		game.dealer.drawCard("HQ");
		game.dealer.drawCard("CA");
		
		game.playOut();
		
		assertEquals(true, game.gameOver == true && game.victor == DEALER);
	}
	
	public void testPlayerBlackjack()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("SK");
		game.player.drawCard("HA");
		
		game.dealer.drawCard("H2");
		
		game.playOut();
		
		assertEquals(true, game.gameOver == true && game.victor == PLAYER);
	}
	
	public void testSoft17()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.dealer.drawCard("S6");
		game.dealer.drawCard("HA");
		
		assertEquals(true, game.dealer.hasSoft17());
		
		game.dealer.clearHand();
		
		game.dealer.drawCard("S8");
		game.dealer.drawCard("S9");
		
		assertEquals(false, game.dealer.hasSoft17());
	}
	
	public void testDealerMustHit()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.dealer.drawCard("S6");
		game.dealer.drawCard("HK");
		
		assertEquals(true, game.dealer.mustHit());
		
		game.dealer.clearHand();
		
		game.dealer.drawCard("S6");
		game.dealer.drawCard("HA");
		
		assertEquals(false, game.dealer.mustHit());
	}
	
	public void testDeckSize()
	{
		BlackjackGame game = new BlackjackGame();
		
		assertEquals(52, game.deck.size());
	}
	
	public void testDeckShuffled()
	{
		BlackjackGame game = new BlackjackGame();
		
		List<String> unshuffledDeck = new ArrayList<String>();
		
		Scanner input = null;
		
		try {
			input = new Scanner(new File("./src/main/resources/deck"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (input.hasNextLine())
		{
			unshuffledDeck.add(input.nextLine());
		}
		
		input.close();
		
		assertFalse(Arrays.equals(game.deck.toArray(), unshuffledDeck.toArray()));
	}
	
	public void testCanPlayerStand()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("HK");
		
		assertFalse(game.player.hasBusted());
	}
	
	public void testCanPlayerHit()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.hit(game.deck);
		
		assertEquals(1, game.player.hand.size());
	}
	
	public void testPlayerMultipleHit()
	{
		BlackjackGame game = new BlackjackGame();
		int i = 0;
		
		while(game.player.hasBusted() == false)
		{
			game.player.hit(game.deck);
			i++;
		}
		
		assertEquals(i, game.player.hand.size());
	}
	
	public void testDealerMultipleHit()
	{
		BlackjackGame game = new BlackjackGame();
		int i = 0;
		
		while(game.dealer.hasBusted() == false)
		{
			game.dealer.hit(game.deck);
			i++;
		}
		
		assertEquals(i, game.dealer.hand.size());
	}
}
