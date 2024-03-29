package videoPoker;

/**
 * Implements a {@link Player} that always asks for the optimal strategy and
 * performs it.
 *
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class SimulationPlayer extends Player{

	/**
	 * bet amount to be used during all simulations
	 */
	private int bet;
	/**
	 * total number of deals of the game
	 */
	private int nbDeals;
	/**
	 * current number of deals of the game
	 */
	private int currentNbDeals = 0;
	
	/**
	 * possible game stages
	 */
	private enum gameStage{
		BET,
		DEAL,
		ADVICE,
		HOLD,
		END;
	}
	
	/**
	 * holds the current stage of the game. Initialized to the first game stage: bet
	 */
	private gameStage stage = gameStage.BET;

	/**
	 * Constructor method that initialises the simulation player attributes given on the terminal
	 * @param credit initial balance of the player
	 * @param _bet the bet with the value to bet during all simulations
	 * @param _nbdeals the total number of deals of the game
	 */
	public SimulationPlayer(int credit, int _bet, int _nbdeals) {
		super(credit);
		bet = _bet;
		nbDeals = _nbdeals;
	}


	/**
	 * Asks for an action to perform.
	 * @return the action to perform
	 */
	@Override
	public Action askAction() {
		Action action;

		if(currentNbDeals == nbDeals && stage != gameStage.END) {
			stage = gameStage.END;
			return new Action('s');
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
			// Player asks advice
			stage = gameStage.HOLD;
			return new Action('a');
			
		case HOLD:
			// Player holds cards according with the received advice
			action = new Action('h');

			action.setPositions(advised_positions); // add a flag to control the execution of this?

			stage = gameStage.BET; //Restart cycle
			currentNbDeals++;

			return action;

		case END:
			// The game has reached its end
			return null;
			
		default:
			return null;
		}
	}
}


