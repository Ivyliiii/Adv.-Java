import java.util.ArrayList;
import java.util.HashSet;

public class CannibalState{
	int cannL;
	int cannR;
	int missL;
	int missR;
	boolean boatLeft = true;

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
	
	public boolean isSolved() {
		if(missL ==0 && cannL ==0) {
			return true;
		}
		return false;
	}
	
	public int generateHashcode() {
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
		for(int i = 0; i < Math.min(2, boatLeft ? missL : missR); i++) {
			for(int j = (i ==0 ? 1:0); j < Math.min(2-i, boatLeft ? cannL : cannR); j++) {
				CannibalState next;
				if(boatLeft) {
					next = new CannibalState(missL-i, cannL-j, missR + i, cannR + j, false);
				}
				else {
					next = new CannibalState(missL + i, cannL + j, missR -i, cannR -j, true);
				}
				if(next.isLegal()) {
					nextstate.add(next);
				}
			}
		}
		return nextstate;
	}

}
