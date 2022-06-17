package videopoker;

import java.util.ArrayList;
import java.util.List;

public class Action {
	
	private char action;
	private int bet;
	private ArrayList<Integer> positions;	// Q: list or arraylist?
	
	public Action(char _action) {
		action = _action;
		
		if(action=='h')
			positions = new ArrayList<Integer>();
	}
	
	public char getAction() {
		return action;
	}

	public void setBet(int _bet) {
		bet = _bet;
	}
	
	public void addPosition(int position) {
		positions.add(position);
	}
	
	public void addPositions(int positions[]) {
		for (int i = 0; i < positions.length; i++) {
			addPosition(positions[i]);
		}
	}
	
	
	public String toString(){ 
		return "" + action; // + amount;
	}

	public int getBet() {
		return bet;
	}
	
	public ArrayList<Integer> getPositions(){
		return positions;
	}

}
