package com;

import junit.framework.TestCase;
import com.BlackjackGame;

public class BlackjackTest extends TestCase{

	public void testPlayerDraw()
	{
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("HK");
		
		assertEquals(10, game.player.score);
		
		game.player.drawCard("CA");
		
		assertEquals(21, game.player.score);
		
		game.player.clearHand();
		
		game.player.drawCard("HK");
		game.player.drawCard("CK");
		game.player.drawCard("CA");
		
		assertEquals(21, game.player.score);
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
		int DEALER = 1;
		
		BlackjackGame game = new BlackjackGame();
		
		game.player.drawCard("SK");
		game.player.drawCard("HA");
		
		game.dealer.drawCard("HQ");
		game.dealer.drawCard("CA");
		
		game.isGameDone();
		
		assertEquals(true, game.gameOver == true && game.victor == DEALER);
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
}
