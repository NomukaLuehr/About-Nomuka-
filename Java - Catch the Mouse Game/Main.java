package game;

import processing.core.PApplet;
import processing.core.PImage;
/* Help Tom catch Jerry by clicking the cursor on Jerry as he randomly appears in the living room
 * 1)The Main class starts the game by giving the rules, sets the background, sets picture of Tom as the cursor, and score board
 * 1a)The settings() method in Main loads the needed images and resizes them
 * 1b)The setUp() method sets the frameRate of the game and the font size of the score and lives printed onto the score board
 * 
 * 2)The class Jerry represents the mouse character Jerry and initiates Jerry's position (var xJ, var yJ) at 0,0
 * 2a)In the draw methodDraw() Jerry's x and y values are randomly generated and are constrained to stay near the floor and within the canvas
 * 
 * 3)The class Tom represents the user and keeps track of user's points (var score) and chances left (var lives)
 * 3a)If user's score reaches 10 before running out of lives, user wins and winner image is displayed
 * 3b)If user uses up the 10 chances before reaching 10 points, user loses and loser image is displayed
 * 
 * 4)The mousePressed method in Main checks if the user was able to click on the mouse Jerry 
 * 4a)If the distance between the position of Jerry and the cursor (represented by mouseX, mouseY) is less than 400, then user has "caught" Jerry and gains a point
 * 4b)If the distance between the position of Jerry and the cursor is not less than 400, then user failed to catch Jerry and loses a point
 * 
 */
public class Main extends PApplet{
	//start image starts game, shows rules, and disappears after a count down
	PImage start;
	//count down till game starts
	PImage three;
	PImage two;
	PImage one;
	PImage zero;
	
	//background image of living room
	PImage background;
	//score board picture to print score and lives on
	PImage scoreBoard;
	
	//will replace the cursor with an image of Tom
	PImage cursorTom;
	//Pic of jerry
	PImage jerryRun;

	//displayed if user doesn't score 10 points before running out of 5 lives
	PImage lost;
	//displayed if user gets 10 points 
	PImage won;;
	
    //Tom object that keeps track of user lives and score
	Tom tom;
	//Jerr object that initializes Jerry's position
	Jerry jerry;
	
	
	
	//used to check distance of "Tom", the cursor, from "Jerry" to determine if user "caught" Jerry
	float d;
	
	//main method
	public static void main(String[] args) {

		PApplet.main(Main.class.getName());
	}
	
	//sets up scene and resizes images to fit the screen correctly
	public void settings() {
		//create new tom object of type Tom
		tom = new Tom();
		//create new jerry object, a PImage of jerry running
		jerry = new Jerry();
		//jerry = new Jerry(this,"jerry_run.png");

		//canvas size 
		size(1333, 750);
		//loads start image and resizes it to fit screen
		start = loadImage("begin.PNG");
		start.resize(1333, 750);
		
		//loads background image, no need to resize
		background = loadImage("background.png"); 
		
		//load score board image 
		scoreBoard = loadImage("score.PNG");
		//load countdown images
		three = loadImage("3.png");
		two = loadImage("2.png");
		one = loadImage("1.png");
		zero = loadImage("0.png");
		
		//load cursor image of tom
		cursorTom = loadImage("tom.png");
		cursorTom.resize(400,200);
		
		//load jerry image and resize to ideal
		jerryRun = loadImage("jerry_run.png");
		jerryRun.resize(200,90);
		
		//load lost image and resize 
		lost = loadImage("lostalife.PNG");
		lost.resize(1333, 750);
		
		//load won image and resize 
		won = loadImage("winner.png");
		won.resize(1333, 750);

	}

	public void setup() {
		//sets frame rate 
		frameRate(1);
		//sets text size of score and lives to be printed on score board 
		textSize(28);
	}

	public void draw(){
		//starts game by showing rules page for 10 seconds 
		image(start,0,0);
		
		//counts down until game starts
		if (frameCount == 2) {
			image(three,800,630);	
		}
		if (frameCount == 4) {
			image(two,800,630);	
		}
		if (frameCount == 6) {
			image(one,800,630);
		}
		if (frameCount == 8) {
			image(zero,800,630);
		}
		
		//starts game after 10 seconds
		if (frameCount>8) {			
			//sets cursor to pic of Jerry if mouse within canvas
			if(mouseX < 1333 && mouseY<750) {
				cursor(cursorTom, 0, 0);
				//image(mouseCursor,mouseX,mouseY,30,30);
			}
			
			//background image of livingroom
			image(background,0,0);
			
			//put up scoreBoard
			image(scoreBoard,10,10);
			//print score and lives on scoreboard
			text(str(tom.score),140,42);
			text(str(tom.lives),120,78);
			
			//variable for Jerry's position
			//values are within the range constraint to keep Jerry from going out of bounds
			jerry.xJ = random(200,1133);
			//keep jerry on the ground by limiting random of y
			jerry.yJ = random(400,660);
			//place jerry at random location 
			image(jerryRun,jerry.xJ,jerry.yJ);
			
			//Jerry width = 200
			//if user clicks within 400 distance of jerry's new point, player wins 2 points
			d = dist(mouseX, mouseY, jerry.xJ, jerry.yJ);

			//tells user she lost if tom runs out of lives
			if(tom.lives<1) {
				//display image
				image(lost,0,0);
			}
			//tells user she won if tom wins 10 points 
			if(tom.score>=10) {
				image(won,0,0);
			}

		}
	}
	//checks if mouse pressed on Jerry
	public void mousePressed() {
		//compare d to determine if user clicked on Jerry
		if (d<300){
			//increase score if clicked
			tom.score++;
		}
		else {
			//decrease lives if not clicked correctly
			tom.lives--;
		}
	}

}

