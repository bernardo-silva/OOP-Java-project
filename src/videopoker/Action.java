package videopoker;

import java.util.ArrayList;

public class Action {

	private char action;
	private int bet;
	private ArrayList<Integer> positions; // Q: list or arraylist?

	public Action(char _action) {
		action = _action;

		if (action == 'h')
			positions = new ArrayList<Integer>();
	}

	public void addPosition(int position) {
		positions.add(position);
	}

	public char getAction() {
		return action;
	}

	public int getBet() {
		return bet;
	}

	public ArrayList<Integer> getPositions() {
		return positions;
	}

	public void setBet(int _bet) {
		bet = _bet;
	}

	public void setPositions(ArrayList<Integer> positions) {
		this.positions = positions;
	}

	public String toString() {
		return "" + action; // + amount;
	}

}
