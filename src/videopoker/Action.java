package videopoker;

public class Action {
	
	private char action;
	private int amount;
	
	public Action(char _action, int _amount) {
		// TO DO: adicionar constructors compativeis com 'HOLD int1 int2 .. intn'
		action = _action;
		amount = _amount;
	}
	
	
	public String toString(){ 
		return "" + action + amount;
	}

}
