

import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class EnglishTranslator {
	HashMap<String, String> dic;
	Scanner reader = new Scanner(System.in);
	File file = new File("EnglishToArabicDictionary2");

	public void map() {
		dic = new HashMap<String, String>();
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(file));
		    for(String s = in.readLine(); s != null; s = in.readLine()) {
		    	dic.put(s,in.readLine());
		    }
		}
	    catch(IOException e){
			e.printStackTrace();
	    }
	}
	
	public String userInput() {
		String n = reader.nextLine();
		String s = dic.getOrDefault(n, "DNE");
		return s;
	}
	
	public static void main(String args[]) {
		EnglishTranslator run = new EnglishTranslator();
		run.map();
		while(true) {
			System.out.println("Please Enter a word you want to translate to Arabic");
			run.userInput();
			System.out.println(run.userInput());
			
		}
	}
 

}
