package JavaGraphics;

public class QandA {

	public static void main(String[] args) {
		
		
		System.out.println("What did you do over the summer?");
		sleep(500);
		System.out.println("I did not actulaly do a whole lot during this summer. \n Before sumemr started"
				+ "I had planned to travel around a bit, but I ended up not leaving the city that I was in once. \n"
				+ "I did play some tennis and hall out with some of my friends.");
		sleep(500);
		System.out.println("What was the highlight of your summer?");
		sleep(500);
		System.out.println("The highlight of my summer was hanging out with my friends and visiting a mall so many times \n that"
				+ "we have literally tried every single resteraut.");
		sleep(500);
		System.out.println("What do you wish you have done over summer but did get to do?");
		sleep(500);
		System.out.println("One thing that I wished I did oversummer was just actually travel more. \n My mom tried to make"
				+ "my summer a bit more interesting, but I just kept pushing it off because I was really lazy. \n Another thing is that it made the conversation "
				+ "really difficult in school when teachers asked about the highlight of my summer \n because it would literally have been my flight to the US. \n"
				+ "Also, I really wished that I kept on coding during summer because I literally forgot everything at this point.");
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
