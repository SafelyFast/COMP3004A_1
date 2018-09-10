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
}