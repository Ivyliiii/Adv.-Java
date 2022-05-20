import java.util.ArrayList;
import java.util.HashSet;

public class TowerState {
	int disc;
	int column;
	ArrayList<ArrayList<Integer>> HanoiState = new ArrayList<ArrayList<Integer>>();
	HashSet<TowerState> previousStates = new HashSet<TowerState>();
	public TowerState(int d, int col) {
		disc = d;
		column = col;
	}
	
	public TowerState move(int colStart, int colEnd) {
		if(HanoiState.get(colStart).size() > 0 && HanoiState.get(colEnd).size() > 0) {
			int i = HanoiState.get(colStart).remove(HanoiState.get(colStart).size()-1);
			HanoiState.get(colEnd).add(i);
			return this;
		}
		return null;
	}
	
	public boolean equals(Object other) {
		TowerState o = (TowerState) other;
		if(o.HanoiState.equals(this.HanoiState)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isLegal() {
		for(int i = 0; i < column; i++) {
			for(int j = 1; j < HanoiState.get(i).size(); j++) {
				if(HanoiState.get(i).get(j) < HanoiState.get(i).get(j-1)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void HashCode() {
		int code = 0;
		for(int i = 0; i < HanoiState.size(); i++) {
			for(int j = 0; j < HanoiState.get(i).size(); j++) {
				code += HanoiState.get(i).get(j) * Math.pow(10, i*HanoiState.size() + j);
			}
		}
	}
	
	public ArrayList<TowerState> nextState(TowerState ts) {
		ArrayList<TowerState> nextStates = new ArrayList<TowerState>();
		for(int i = 0; i < column; i++) {
			for(int j = 0; j < column; j++) {
				if(i != j) {
					TowerState current = move(i, j);
					if(current != null) {
						if(current.isLegal() && previousStates.contains(current)) {
							nextStates.add(current);
						}
					}
				}
			}
		}
		return nextStates;
	}
	
	
	public ArrayList<TowerState> solve(int Depth, HashSet<TowerState> previous, ArrayList<TowerState> solution){
		System.out.println("wt");

		if(isSolved()) {
			return solution;
		}
		if (Depth>= 1000){
			return null;
		}
		for(TowerState ts : nextState(this)) {
			if(previous.contains(ts)) {
				continue;
			}
			else {
				previous.add(ts);
			}
			ArrayList<TowerState> state = ts.solve(Depth +1, previous, solution);
			if(state != null) {
				solution.add(this);
				return solution;
			}
		}

		return null;
	}
	
	public boolean isSolved() {
		for(int i = 1; i < HanoiState.size(); i++) {
			if(HanoiState.get(i).size() == disc) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String out = "";
		for(int i = 0; i < HanoiState.size(); i++) {
			for(int j = 0; j < HanoiState.get(i).size(); j++) {
				out += HanoiState.get(i).get(j);
			}
			out += "  || ";
		}
		return out;
	}
	
	public static void main(String[] args) {
		TowerState tower = new TowerState(4, 3);
		HashSet<TowerState> previous = new HashSet<TowerState>();
		ArrayList<TowerState> s = new ArrayList<TowerState>();
		tower.solve(1, previous, s);
		System.out.println(tower);
	}
}
