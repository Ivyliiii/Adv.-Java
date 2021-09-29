package LinkedList;

public class Card {
	public String suit;
	public int num;
		
	public Card(String suit, int num) {
		this.suit = suit;
		this.num = num;
	}
		
	public int getnum(){
		return num;
	}
		
	public String getsuit() {
		return suit;
	}
		
	public String toString() {
		return num + ", " + suit;
	}

}
