package game;

import java.util.*;
import processing.core.PApplet;
import processing.core.PImage;

public class twenty1 extends PApplet{

	static int handsDealt;

	static ArrayList<Player> players; 

	static Cards cards;

	static Computer computer;
	static User user; 
	static Player p1;
	static Player p2;
	static Player p3;
	static Player p4;
	static Player p5;
	static String reason = "";

	static String button = "";

	PImage start;
	PImage clickStart;
	PImage currentCardImg;
	PImage defaultBG;
	PImage dealingBufferBar;
	PImage enterBetBufferBar;
	PImage hit;
	PImage stand;
	PImage circOutline;
	PImage recOutline;
	PImage hitOrStandBufferBar;
	PImage insufFuelBufferBar;

	float mX;
	float mY;

	int betButtonBox;
	int standOrHitBox;


	public static void main(String[] args) {
		PApplet.main(twenty1.class.getName());

		cards = new Cards();
		players = new ArrayList<Player>();

		computer = new Computer(6,167, 240);
		user = new User(51, 721);
		p1 = new Player(1, 190, 285);
		p2 = new Player(2, 190, p1.y + 32);
		p3 = new Player(3, 190, p2.y + 32);
		p4 = new Player(4, 190, p3.y + 32);
		p5 = new Player(5, 190, p4.y + 32);

		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(user);
		players.add(computer);


		cards.createDeck();
	}
	public void settings() {

		size(540,960);

		//for mouse interaction
		mX = width / 2;
		mY = height / 2;
	}
	public void setup() {

		textSize(28);

		fill(255,182,193);

		start = loadImage("cardsScreen/start.png");
		start.resize(540, 960);

		clickStart = loadImage("cardsScreen/clickStart.png");
		clickStart.resize(540, 960);

		defaultBG = loadImage("cardsScreen/defaultBG.png");
		defaultBG.resize(540, 960);

		dealingBufferBar = loadImage("cardsScreen/dealingBufferBar.png");
		dealingBufferBar.resize(455, 105);

		enterBetBufferBar = loadImage("cardsScreen/enterBetBufferBar.png");
		enterBetBufferBar.resize(455, 105);

		circOutline = loadImage("cardsScreen/circOutline.png");
		circOutline.resize(455, 105);

		recOutline = loadImage("cardsScreen/recOutline.png");
		recOutline.resize(455, 105);

		hit = loadImage("cardsScreen/hit.png");
		hit.resize(455, 105);

		stand = loadImage("cardsScreen/stand.png");
		stand.resize(455, 105);

		hitOrStandBufferBar = loadImage("cardsScreen/hitOrStandBufferBar.png");
		hitOrStandBufferBar.resize(455, 105);

		insufFuelBufferBar = loadImage("cardsScreen/insufFuelBufferBar.png");
		insufFuelBufferBar.resize(455, 105);
	}
	public void draw() {
		String keepGoing = "Yes";
		while (keepGoing.equals("Yes")){
			boolean checker = true;
			while (user.rounds <10 && user.bank > 25 && checker) {
				image(start, 0, 0);
				delay(1000);
				//ask user to click start
				image(clickStart, 0, 0);
				delay(1000);
				/*if (mouseX < 540 && mouseY < 960 && mousePressed) {
					checker = true;
				}*/
				image(enterBetBufferBar, 49, 83);
				delay (1500);
				//user must click on a bet token to start game
				user.userBet();
				//if (mouseX > mX - betButtonBox)

				//change background to default for rest of game
				image(defaultBG, 0, 0);

				//tell user you are dealing cards
				image(dealingBufferBar, 49, 83);
				delay (1500);
				//Deal twice and check
				for (Player p : players) {
					cards.deal(p);
					if (p instanceof User) {
						String img = "cards/"+cards.currentCard + ".png";
						currentCardImg = loadImage(img);
						currentCardImg.resize(108, 188);
						image(currentCardImg,p.x,p.y );
					}
					else {
						String img = "cardsScreen/"+cards.currentCard + ".png";
						currentCardImg = loadImage(img);
						currentCardImg.resize(48, 45);
						image(currentCardImg,p.x, p.y );
					}
				}
				handsDealt++;
				for (Player p : players) {
					cards.deal(p);
					if (p instanceof User) {
						String img = "cards/"+cards.currentCard + ".png";
						currentCardImg = loadImage(img);
						currentCardImg.resize(108, 188);
						image(currentCardImg,p.x+133,p.y);
					}
					else {
						if (!(p instanceof Computer)) {
							String img = "cardsScreen/"+cards.currentCard + ".png";
							currentCardImg = loadImage(img);
							currentCardImg.resize(48, 45);
							image(currentCardImg,p.x + 70,p.y );

						}
					}
				}
				handsDealt++;
				checker = check();
				if (!checker) {
					break;
				}

				//Hit or stand and check
				for (Player p : players) {
					if (!(p instanceof Computer)) {
						checker = hitOrStand(p);
						if (!checker) {
							break;
						}
					}
				}
				checker = hitOrStand(computer);
				if (!checker) {
					break;
				}

				roundResult();
			}

			payOut(reason);
		}

		gameResult();
	}
	
	//public void mousePressed() {
		
		//if (mouseX )
		
	//}
	/*public void mouseClicked() {
		// use location on grid to determine which button was clicked 
		button = "25";
		button = "50";
		button = "100";
		button = "hit";
		button = "stand";

	}*/

	public static boolean hitOrStand(Player player) {
		if (player instanceof User){
			String checking = user.userI.nextLine();
			while (checking.equalsIgnoreCase("hit")) {
				cards.deal(user);
				player.handsDealttoPlayer++;
				if (player.points > 21) {
					return check();
				}
			}
		}

		//hit only once rule
		if (player instanceof Computer || player instanceof Player) {
			if (player.points<17) {
				cards.deal(player);
				handsDealt++;
				if (player.points > 21) {
					return check();
				}
			}
		}
		return check();
	}
	public static boolean check() {
		if (user.points==21 || computer.points==21 && handsDealt == 2) {
			reason = "BlackJack";
			return false;
		}
		else if (user.points>21 || computer.points>21) {
			reason = "Bust";
			return false;
		}
		else if (user.bank<25) {
			reason = "No Fuel";
			return false;
		}
		return true; 
	}

	public static void payOut(String reason) {
		if (reason.equals("User won")) {
			user.bank += user.pot*2;
			user.roundsWon++;
			user.rounds++;
		}
		else if (reason.equals("BlackJack")) {
			if (user.points == 21) {
				if (computer.points != 21) {
					user.bank += user.pot + user.pot*1.5;
					user.roundsWon++;
					user.rounds++;
				}
				else {
					user.bank += user.pot;
					user.roundsWon++;
					user.rounds++;
				}
			}
			else {
				user.rounds++;
			}

		}
		else if (reason.equals("Bust")) {
			if (user.points > 21) {
				user.rounds++;
			}
			else {
				user.bank += user.pot*2;
				user.roundsWon++;
				user.rounds++;
			}

		}

	}
	public static String roundResult() {
		if (user.points < computer.points ) {
			return "Computer";
		}
		reason = "User won";
		return "User";
	}
	public static String gameResult() {
		if (user.bank > 0 && (user.roundsWon / user.rounds)*100 > 50) {
			return "User";
		}
		return "Computer";

	}

	public void run() {
		//As your stream implements Closeable, it is better to use a "try-with-resources"

	}
}

