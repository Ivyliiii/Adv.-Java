import java.util.ArrayList;
import java.util.HashSet;

public class CannibalState{
	int cannL;
	int cannR;
	int missL;
	int missR;
	int depth = 0;
	boolean boatLeft = true;
	HashSet<CannibalState> previousStates = new HashSet<CannibalState>();
	public CannibalState(int cannibalL, int cannibalR, int missionaryL, int missionaryR, boolean boatL) {
		cannL = cannibalL;
		cannR = cannibalR;
		missL = missionaryL;
		missR = missionaryR;
		boatLeft = boatL;
	}
		
	public boolean isLegal() {
		if(!(cannL >= 0 && missL >= 0 && cannR >= 0 && missR >= 0)) {
			return false;
		}
		if(cannL > missL && missL > 0) {
			return false;
		}
		if(cannR > missR && missR > 0) {
			return false;
		}
		return true;
	}
	
	public ArrayList<CannibalState> solve(int Depth, HashSet<CannibalState> previous, ArrayList<CannibalState> solution) {
		if(missL ==0 && cannL ==0) {
			solution.add(this);
			return solution;
		}
		if(Depth>=10000) {
			return null;
		}
		for(CannibalState z: nextState()) {
			if(previous.contains(z)) {
				continue;
			}
			else {
				previous.add(z);
			}
			ArrayList<CannibalState> state = z.solve(Depth +1, previous, solution);
			
			if(state != null) {
				solution.add(this);
				return solution;
			}
		}
		return null;
	}
	
	public int hashCode() {
		System.out.println("No hashcode");
		if(boatLeft) {
			return 10 * missL + 1000 * cannL + 100000 * missR + 10000000*cannR + 1000000000*1;
		}
		else {
			return 10 * missL + 1000 * cannL + 100000 * missR + 10000000*cannR + 1000000000*2;
		}
	}
	
	public String toString() {
		return missL + " " + cannL + "||" + missR + " " + cannR + boatLeft;
	}
	
	public HashSet<CannibalState> nextState(){
		HashSet<CannibalState> nextstate = new HashSet<CannibalState>();
		System.out.println(missL);
		System.out.println(cannL);
		for(int i = 0; i < Math.min(2, boatLeft ? missL : missR); i++) {
			for(int j = (i == 0 ? 1:0); j < Math.min(2-i, boatLeft ? cannL : cannR); j++) {
				System.out.println(i);
				System.out.println(j);
				CannibalState next;
				if(boatLeft) {
					next = new CannibalState(cannL-j, cannR + j, missL-i, missR + i,false);
					System.out.println(next);
				}
				else {
					System.out.println("heee");
					next = new CannibalState(cannL + j, cannR -j, missL + i, missR -i,true);
				}
				if(next.isLegal()) {
					nextstate.add(next);
				}
			}
		}
		System.out.println(nextstate.size());
		return nextstate;
	}
	
	public static void main(String[] args) {
		CannibalState runner = new CannibalState(3,0,3, 0, true);
		HashSet<CannibalState> previous = new HashSet<CannibalState>();
		ArrayList<CannibalState> solution = new ArrayList<CannibalState>();
		System.out.println(runner.solve(1, previous, solution));
	}

}
