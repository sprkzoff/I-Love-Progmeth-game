package logic;

import java.util.ArrayList;

public class Logic {
	
	//Create player
	
	Player player1 = new Player();
	Player player2 = new Player();
	
	public void selectCharacter() {
		for(int i=0;i<6;i++)
		{
			if(i%2==0)
			{
				//player1 select character
			}
			else
			{
				//player2 select character
			}
		}
	}
	
	//start game
	public void startGame() {
		int turn = 1; 
		while(!player1.All_dead() && !player2.All_dead())
		{
			if(player1.All_dead())
			{
				//player2 win
				break;
			}
			else if(player2.All_dead())
			{
				//player1 win
				break;
			}
			else // no one win the game yet
			{
				if(turn % 2 == 1) // player1 turn
				{
					//player1 do something
				}
				else // player2 turn
				{
					//player2 do something
				}
			}
			turn+=1;
		}
	}
	//end game
}
