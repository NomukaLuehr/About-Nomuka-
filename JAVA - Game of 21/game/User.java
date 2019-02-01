package game;

import java.util.*;

public class User extends Player{
	Scanner userI = new Scanner(System.in);

	int rounds;
	
	int roundsWon;
	
	int bank;
	
	int pot; //total
	
	int currentPot; //current
	public User(int x, int y) {
		this.x = x;
		this.y = y;
		rounds = 0;
		
		roundsWon = 0;
		
		bank = 500;
		
		pot = 0;
		
		currentPot = 0;
	}
	public User() {
		
		rounds = 0;
		
		roundsWon = 0;
		
		bank = 500;
		
		pot = 0;
		
		currentPot = 0;
	}
	
	public String toString() {
		return "User";
	}
	public void userBet(){
		bank = bank - pot;
	}

}
