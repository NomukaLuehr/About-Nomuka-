package game;

import java.util.ArrayList;

public class Player{
	int points;
	int x;
	int y;
	String name;
	ArrayList<Integer>hand;
	int handsDealttoPlayer;
	
	public Player () {
		name = "Player";
		hand = new ArrayList<Integer>();
		x = 0;
		y = 0;
		points = 0;
		handsDealttoPlayer = 0;
	}
	public Player (int num, int x, int y) {
		name = "Player"+ num;
		hand = new ArrayList<Integer>();
		this. x = x; 
		this. y = y;
		points = 0;
		handsDealttoPlayer = 0;
	}
	public Player (int x, int y) {
		name = "Player";
		hand = new ArrayList<Integer>();
		x = 0;
		y = 0;
		points = 0;
		handsDealttoPlayer = 0;
	}
	public String toString() {
		return name;
	}

}