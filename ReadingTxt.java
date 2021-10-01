import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ReadingTxt {
	HashMap<Character, Integer> frq = new HashMap<Character, Integer>();
	
	public void readFile() {
		File file = new File("file");
		FileReader in;
		try {
			in = new FileReader(file);
			int times = 0;
			for(int i = in.read(); i!=-1; i = in.read()) {
				times++;
				char letter = (char)i;
				if (frq.get(letter) == null) {
					frq.put(letter, 1);
				}
				else {
					frq.put(letter, frq.get(letter)+1);
				}
			}
			
			for(char c : frq.keySet()) {
				System.out.println(c);
				System.out.println(frq.get(c));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String args[]) {
		ReadingTxt run = new ReadingTxt();
		run.readFile();
	}
}
