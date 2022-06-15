package videopoker;

import java.util.ArrayList;
import java.util.LinkedList;

public class SimulationPlayer extends Player{
	private ArrayList<Integer> advicePositions;
	// Adicionar metodo "setAdvicePositions na classe player
	
	//private LinkedList<Action> actions;
	private int bet;
	private int nbdeals;
	private int i;
	private int current_nbdeals;
	private int[] hold_positions;
	Action action;
	

	public SimulationPlayer(int credit, int _bet, int _nbdeals) {
		super(credit);
		bet = _bet;
		nbdeals = _nbdeals;
				
	}


	@Override
	public Action askAction() {
		// TODO Auto-generated method stub
		switch(i) {
		case 0:
			// Player bets defined amount
			action = new Action('b');
			action.setBet(bet);
			i++;
			return action;
			
		case 1:
			// Player deals
			i++;
			current_nbdeals++;
			return new Action('d');
		
		case 2:
			// Player asks advice and stores indexes
			i++;
			return new Action('a');
			// how to get indexes? 
			
		case 3:
			// Player holds indexes given by advice
			
			// if(current_nbdeals == nbdeals) return null; ?? HOW TO MAKE THIS RETURN NULL? 
			
			i = 0; 	// starts a new game cycle of b-d-a-h 
			action = new Action('h');
			
			// !! falta : ADD HOLD POSITIONS GIVEN BY ADVICE
			//action.addPosition(int position_from_advice, Action action or ListActions ???????); 
			return action;
			
		}
				
	}

}


////player = new SimulationPlayer(credit, bet, nbdeals);
//game = new VideoPoker(args[3], stratfile, player);
//
