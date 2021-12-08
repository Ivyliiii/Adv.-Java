import java.util.ArrayList;

public class JosephusCase {
	int num = 10;
	
	/*public int JosephusCase(int num) {
		int start = 0;
		int curr = 0;
		for(int i = 0; i < num; i++) {
			joseph.add(i, i);
		}
		while(joseph.size() > 1) {
			/*if(joseph.size() % 2 == curr) {
				start = 1;
			}
			else {
				start = 0;
			}
			System.out.println("curr " + curr);
			joseph = takeEven(joseph, curr);
			curr = start;
			joseph = takeEven(joseph);
			System.out.println(joseph.size());
		}
		return joseph.get(0);
	}*/
	
	
	/*public ArrayList<Integer> takeEven(ArrayList<Integer> arr){
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i < arr.size(); i+=2) {
			if(i == joseph.size()) {
				temp.add(arr.get(i),0);
				break;
			}
			temp.add(arr.get(i));
		}
		return temp;
	}*/
	
	public int DumbMethod(ArrayList<Integer> arr) {
		ArrayList<Integer> temp;
		while(arr.size() > 1) {
			temp = new ArrayList<Integer>();
			for(int i = 0; i < arr.size(); i+=2) {
				if(i == arr.size()-1){
					System.out.println("yee");
					temp.add(0,arr.remove(arr.size()-1));
				}
				else {
					temp.add(arr.get(i));	
				}
			}
			System.out.println(temp.size());
			arr = temp;
		}
		return arr.get(0);
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> joseph = new ArrayList<Integer>();
		int num = 1000000;
		for(int i = 0; i < num; i++) {
			joseph.add(i, i);
		}
		JosephusCase run = new JosephusCase();
		System.out.println(run.DumbMethod(joseph));
	}

}
