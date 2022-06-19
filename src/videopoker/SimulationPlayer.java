package videopoker;

public class SimulationPlayer extends Player{
	private int bet;
	private int nbDeals;
	private int currentNbDeals = 0;
	
	private enum gameStage{
		BET,
		DEAL,
		ADVICE,
		HOLD,
		END;
	}
	private gameStage stage = gameStage.BET;

	public SimulationPlayer(int credit, int _bet, int _nbdeals) {
		super(credit);
		bet = _bet;
		nbDeals = _nbdeals;
	}


	@Override
	public Action askAction() {
		Action action;

		if(currentNbDeals == nbDeals && stage != gameStage.END) {
			stage = gameStage.END;
			return new Action('s');   // "This method must return a result of type Action" -> return null é mesmo legit????
		}

		switch(stage) {
		case BET:
			// Player bets defined amount
			action = new Action('b');
			action.setBet(bet);
			stage = gameStage.DEAL;
			return action;

		case DEAL:
			// Player deals
			stage = gameStage.ADVICE;
			return new Action('d');

		case ADVICE:
			// Player asks advice and stores indexes
			stage = gameStage.HOLD;
			return new Action('a');
			
		case HOLD:
			// Player holds indexes given by advice
			action = new Action('h');
			action.setPositions(advised_positions); // add a flag to control the execution of this?

			stage = gameStage.BET; //Restart cyle
			currentNbDeals++;
			return action;

		case END:
			return null;
			
		default:
			return null;
		}
	}
}


