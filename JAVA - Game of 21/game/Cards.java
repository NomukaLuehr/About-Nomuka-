package game;

import java.util.HashMap;
import java.util.Random;
import java.util.*;

public class Cards {

	HashMap < Integer, Integer> twentyOneDeck;

	HashMap < Integer, Integer> deck;

	HashMap < Integer, Integer> disposableDeck;

	int currentNumInDeck;

	int cardsDealt;

	int currentCard;
	
	Random rand;

	public Cards() {
		
		disposableDeck = new HashMap < Integer, Integer>();
		twentyOneDeck = new HashMap < Integer, Integer>();
		deck = new HashMap <Integer, Integer>();
		currentNumInDeck = 52;
		cardsDealt = 0;
		rand = new Random();

	}

	public void createDeck(){
		int index = 1;
		while (index < 53) {
			//for 4 suits
			for (int j = 1; j<5; j++) {
				//set index and number for each of 13 cards
				for (int i = 1; i<14; i++) { 
					//put in deck that holds key: 1->52 value: 1-> 13
					deck.put(index, i);
					//put in deck to pull cards from key: 1->52  value:1->13
					disposableDeck.put(index, i);

					//deck to pull value in twenty one game (1->11)
					//key: 1->52   value:2->11
					if (i == 1) {
						//set ace to 11 
						twentyOneDeck.put(i, 11);
					}
					if (i<10) {
						twentyOneDeck.put(index, i);
					}
					else {
						twentyOneDeck.put(index,  10);

					}
					index++;
				}
			}
		}
	}

	public void dealAll(ArrayList<Player>players) {
		for (Player p: players) {
			deal(p);
		}
	}

	public void deal(Player player) {

		currentCard = rand.nextInt(currentNumInDeck-1) + 1;

		while (disposableDeck.get(currentCard) == null) {
			
			currentCard = rand.nextInt(currentNumInDeck-1) + 1;
		}
		//add new card to player hand
		player.hand.add(player.hand.size(), currentCard);

		//add to points
		player.points += twentyOneDeck.get(currentCard);
		disposableDeck.put(currentCard,  null);

		/*if (currentCard == 1 || currentCard == 14 || currentCard == 27 || currentCard == 40 ) {
			//check for ace 
			disposableDeck.put(currentCard,  null);
		} 
		else { */

		//if (player.handsDealttoPlayer == 1 && player instance of Computer) {

		currentNumInDeck--;
		cardsDealt++;
		player.handsDealttoPlayer++;
		currentCard = currentCard;
	}
}