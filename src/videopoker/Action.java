package videopoker;

import java.util.ArrayList;
import java.util.List;

public class Action {
	
	private char action;
	private int money;
	private List<Integer> positions;	// Q: list or arraylist?
	
	public Action(char _action) {
		action = _action;
		
		if(action=='h')
			positions = new ArrayList<Integer>();
	}
	
	public void add_money(int _money) {
		money = _money;
	}
	
	public void add_positions(int _position) {
		positions.add(_position);
	}
	
	
	public String toString(){ 
		return "" + action; // + amount;
	}

}
