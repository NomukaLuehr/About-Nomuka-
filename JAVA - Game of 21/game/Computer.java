package game;

public class Computer extends Player {
	int points;
	public Computer() {
		super();
	}
	
	public Computer(int num, int x, int y) {
		super(num, x, y);
	}
	public String toString() {
		return "Computer";
	}
}