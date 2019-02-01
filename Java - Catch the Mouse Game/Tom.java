package game;

//3)gives Tom "lives" and "score" value to be changed during the game 
public class Tom {

	//keeps track of Tom's score
	float score;
	//keeps track of how many lives Tom has, minus one if user Tom doesn't click where Jerry is
	float lives; 

	public Tom(){
		//initialize lives and score for Tom
		lives = 10;
		score = 0;
	}

}
