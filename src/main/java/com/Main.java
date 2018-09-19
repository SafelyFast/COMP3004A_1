package com;

import java.util.Scanner;
import com.BlackjackGame;

public class Main {
	
	public static int PLAYER = 0;
	public static int DEALER = 1;
	
	public static void main(String[] args)
	{
		BlackjackGame game = new BlackjackGame();
		
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
		System.out.println(DEALER);
	}
}
