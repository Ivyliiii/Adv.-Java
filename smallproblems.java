import java.util.ArrayList;

public class smallproblems {
	ArrayList<Integer> arr = new ArrayList<Integer>();
	public int Small_Problems(int n) {
		String num = String.valueOf(n);
		int change = 0;
		boolean smaller = false;
		int pre = Integer.parseInt(Character.toString(num.charAt(num.length()-1)));
		for(int i = num.length()-1; i >= 0; i--) {
			if(Integer.parseInt(Character.toString(num.charAt(i))) > pre) {
				smaller = true;
				change = i;
				break;
			}
			pre = Integer.parseInt(Character.toString(num.charAt(i)));
		}
		if(smaller) {
			String root = num.substring(0, change);
			String work = num.substring(change);
			for(int i = 0; i < work.length(); i++) {
				arr.add(Integer.parseInt(Character.toString(work.charAt(i))));
			}
			rearrange();
			for(int i = 0; i < arr.size(); i++) {
				if(arr.get(i) == Integer.parseInt(Character.toString(num.charAt(change)))) {
					root+=arr.get(i-1);
					arr.remove(i-1);
					for(int j = arr.size()-1; j >= 0; j--) {
						root+=arr.get(j);
					}
				}
			}
			return Integer.parseInt(root);
		}
		else {
			return -1;
		}	
	}
	
	public void rearrange() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int size = arr.size();
		for(int i = 0; i < size; i++) {
			temp.add(getSmallest());
		}
		arr = temp;
	}
	
	public int getSmallest() {
		int smallest = arr.get(0);
		int smallIndex = 0;
		for(int i = 1; i < arr.size(); i++) {
			if(arr.get(i) < smallest) {
				smallest = arr.get(i);
				smallIndex = i;
			}
		}
		arr.remove(smallIndex);
		return smallest;
	}
	
	public ArrayList<String> directions(ArrayList<String> input) {
		ArrayList<String> out = new ArrayList<String>();
		int up = 0;
		int right = 0;
		for(int i = 0; i < input.size(); i++) {
			if(input.get(i) == "North") {
				up ++;
			}
			else if(input.get(i) == "South") {
				up--;
			}
			else if(input.get(i) == "West") {
				right--;
			}
			else {
				right++;
			}
		}
		for(int i = 0; i < Math.abs(up); i++) {
			if(up > 0) {
				out.add("North");
			}
			else {
				out.add("South");
			}
		}
		for(int i = 0; i < Math.abs(right); i++) {
			if(right > 0) {
				out.add("East");
			}
			else {
				out.add("West");
			}
		}
		return out;
	}
	
	public static void main(String[] args) {
		smallproblems run = new smallproblems();
		System.out.println(run.Small_Problems(9775649));
		ArrayList<String> inp = new ArrayList<String>();
		inp.add("North");
		inp.add("South");
		inp.add("South");
		inp.add("East");
		inp.add("West");
		inp.add("North");
		inp.add("West");
		ArrayList<String> out = run.directions(inp);
		for(int i = 0; i < out.size(); i++) {
			System.out.println(out.get(i));
		}
	}
	
	

}
