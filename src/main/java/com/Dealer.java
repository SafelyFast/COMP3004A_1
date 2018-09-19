package com;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	public int score;
	List<String> hand;
	
	public Dealer()
	{
		this.score = 0;
		this.hand = new ArrayList<String>();
	}
	
	public void setScore(int newScore)
	{
		this.score = newScore;
	}
	
	public boolean hasBusted()
	{
		if (score > 21)
		{
			return true;
		}
		return false;
	}
	
	public void drawCard(String newCard)
	{
		this.score += this.determineCardValue(newCard);
		this.hand.add(newCard);
	}
	
	private int determineCardValue(String card)
	{
		String cardValue = card.substring(1);
		int value = 0;
		
		switch(cardValue)
		{
			case "J": case "Q": case "K":
				value = 10;
				break;
			case "A":
				if (this.score + 11 > 21)
				{
					value = 1;
				}
				else
				{
					value = 11;
				}
				break;
			default:
				value = Integer.parseInt(cardValue);
		}
		
		return value;
	}
	
	public void clearHand()
	{
		this.score = 0;
		this.hand.clear();
	}
	
	public boolean hasBlackjack()
	{
		boolean hasAce = false;
		boolean has10 = false;
		
		if (!(this.hand.size() == 2))
		{
			return false;
		}
		
		for(int i = 0; i < hand.size(); i++)
		{
			String currentCardValue = this.hand.get(i).substring(1);
			
			switch(currentCardValue)
			{
				case "J": case "Q": case "K": case "10":
					has10 = true;
					break;
				case "A":
					hasAce = true;
					break;
			}
		}
		
		return (hasAce && has10);
	}
	
	public boolean hasSoft17()
	{
		boolean hasAce = false;
		
		for(int i = 0; i < this.hand.size(); i++)
		{
			if (this.hand.get(i).substring(1).equals("A"))
			{
				hasAce = true;
			}
		}
		
		return hasAce;
	}
	
	public boolean mustHit()
	{	
		return (this.score <= 16);
	}
	
	public void hit(List<String> deck)
	{
		 drawCard(deck.remove(deck.size() - 1));
	}
}