package LinkedList;

import java.util.Scanner; // import scanner class

public class War {
	LinkedList<Card> list = new LinkedList<Card>();
	LinkedList<Card> playerOne = new LinkedList<Card>();
	LinkedList<Card> playerTwo = new LinkedList<Card>();
	Scanner reader = new Scanner(System.in);
	
	int nums = 14; // number of different numbers in a deck of cards
	
	// the code for entering cards into one linkedlist
	public void DeckMaking() {
		for(int i = 1; i < nums; i++) {
			Card temp = null;
			for(int j = 0; j < 4; j++) {
				if(j==0) {
					temp = new Card("Spade", i);
				}
				else if(j==1){
					temp = new Card("Clubs", i);
				}
				else if(j==2) {
					temp = new Card("Diamond", i);
				}
				else {
					temp = new Card("Heart", i);
				}
				list.add(temp,list.sizes()-1);
			}
		}
	}
	
	// shuffling for the cards
	public void Shuffle() {
		for(int i = 0; i < list.sizes(); i++) {
			int num = (int)(Math.random()*list.sizes());
			System.out.println(list.sizes() +" "+num);
			Card info = list.remove(num);
			list.add(info, (int)(Math.random()*list.sizes())-1);
		}
	}
	
	// this is the main method where everything goes
	public void Play() {
		DeckMaking();
		Shuffle();
		
		int runTimes = list.sizes()/2; // random variable
		
		// dividing the deck of cards into player one and two
		for(int i = 0; i < runTimes; i++) {
			System.out.println(i);
			playerOne.add(list.remove(i), playerOne.sizes()-1);
		}
		
		playerTwo = list; // every card left will be part of the playerTwo list
		
		int round = 0; // keeps track of which round we are on
		Card temp1; // temp variable to keep the first card
		Card temp2; 
		boolean con = true; // for the user press enter thing
		
		// the game only keeps on going if everyone has cards and the user pressed enter
		// main method for running the game and comparign the numbers
		while(playerOne.sizes() != 0 && playerTwo.sizes() != 0 && con) { 
			con = false;
			round++;
			temp1 = playerOne.remove(0);
			temp2 = playerTwo.remove(0);
			System.out.println("Round " + round);
			System.out.println("Player One.... " + temp1);
			System.out.println("Player Two.... " + temp2);
			
			// happens if playerOne has a bigger card
			if(temp1.getnum() > temp2.getnum()) {
				playerOne.add(temp1,playerOne.sizes()-2);
				playerOne.add(temp2,playerOne.sizes()-2);
				System.out.println("Congratulations Player 1!!! You have won Round " + round);
			}
			
			// happens if player Two has a bigger card
			else if(temp1.getnum() < temp2.getnum()) {
				playerTwo.add(temp1, playerTwo.sizes()-2);
				playerTwo.add(temp2, playerTwo.sizes()-2);
				System.out.println("Congratulations Player 2!!! You have won Round " + round);
			}
			
			// tieee
			else {
				System.out.println("TIEEE!!");
				System.out.println("Let us flip 4 more cards from each player!");
				System.out.println("Player One's 5th Card is..." + playerOne.get(3).getnum());
				System.out.println("Player Two's 5th Card is..." + playerTwo.get(3).getnum());
				int winner = Tie();
				if(winner == 1) {
					System.out.println("Congratulations Player 1, you win round " + round);
					playerOne.add(temp1);
					playerOne.add(temp2);
				}
				else if(winner == 2) {
					System.out.println("Congratulations Player 2, you win round " + round);
					playerTwo.add(temp1);
					playerTwo.add(temp2);
				}
				else {
					System.out.println("Another TIEEE");
					Tie();
				}
			}
			
			System.out.println("Player 1, you have " + playerOne.sizes() + " cards");
			System.out.println("Player 2, you have " + playerTwo.sizes() + " cards");
			// waiting for the user to start the next round
			String n = reader.nextLine();
			
			System.out.println("Are you ready for the next round? If so, press enter!");
			if(n.equals("")) {
				System.out.println("great");
				con = true;
			}
			else {
				System.out.println("Noo");
			}
		}
		System.out.println("GAME OVERR!");
	}
	
	// this is the program that is called when the two numbers are tied
	// it will get the fifth number in the player lists and compare them
	// it will also be responsible for the movement of cards to one player
	public int Tie() {
		int num = 4;
		if(playerOne.sizes() <= num) { // make sure both of their length are larger than 5
			return 2; // they ran out of cards so the other wins
		}
		else if(playerTwo.sizes() <= num) {
			return 1;
		}
		else {
			Card temp1 = playerOne.get(3); // we have already removed 0, so the 5th card is at index 3
			Card temp2 = playerTwo.get(3);
			if(temp1.getnum() > temp2.getnum()) { 
				for(int i = 0; i < num; i++) {
					playerOne.add(playerOne.remove(0));
					playerOne.add(playerTwo.remove(0));
				}
				return 1;
			}
			else if(temp1.getnum() < temp2.getnum()) { 
				for(int i = 0; i < num; i++) {
					playerTwo.add(playerOne.remove(0));
					playerTwo.add(playerTwo.remove(0));
				}
				return 2;
			}
			else {
				for(int i = 0; i < num; i++) {
					playerOne.add(playerOne.remove(0));
					playerTwo.add(playerTwo.remove(0));
				}
				return 0; // no one wins again
			}
		}
	}
	
	public static void main(String[] args) {
		// running everything
		War run = new War();
		run.Play();
	}
}
