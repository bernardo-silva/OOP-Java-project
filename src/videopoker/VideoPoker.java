/**
 * 
 */
package videopoker;

import java.util.ArrayList;

import playingCards.Card;
import playingCards.DeckOfCards;
import playingCards.HandOfCards;

/**
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class VideoPoker {
	/**
	 * Player that will dictate the actions of the game, may be a simulationPlayer or a debugPlayer
	 */
	Player player;
	/**
	 * Deck of cards to be used during the game
	 */
	DeckOfCards deck;
	/**
	 * Hand combinations that correspond to a payout for this videoPoker game
	 */
	private ArrayList<PokerHand> hands;
	/**
	 * Chosen strategy for this videoPoker game
	 */
	private Strategy strategy;
	/**
	 * Boolean that chooses the game mode 
	 */
	private boolean debugMode = false;
	/**
	 * Amount of money to bet
	 */
	private int bet = 0;
	/**
	 * Constructor method that obtains the hand combinations that correspond to a payout from the file @param handFile ,
	 * the deck from the file @param deckFile , 
	 * initializes the player @param player and the strategy @param strategy and picks the game mode, 
	 * debug mode if @param debugMode = true and simulation mode otherwise.
	 */
	public VideoPoker(String handFile, String deckFile, Player player,
						Strategy strategy, boolean debugMode) {
		this.player = player;
		this.shuffle = shuffle;

		deck = new DeckOfCards(deckFile);
		
		deck.print();
		
		optimalStrat = VideoPokerUtil.stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		hands = VideoPokerUtil.handsFromFile(handFile);
	}
	/**
	 * Constructor method that obtains the hand combinations that correspond to a payout from the file @param handFile ,
	 * creates a default deck, initializes the player @param player 
	 * and the strategy @param strategy and prepares the game for Simulation mode.
	 */
	public VideoPoker(String handFile, Player player, Strategy strategy) {
		this.player = player;
		deck = new DeckOfCards();
		if(!debugMode) deck.shuffle();

		hands = new ArrayList<PokerHand>();
		readHandsFromFile(handFile);
	}
	/**
	 * Reads hand combinations that correspond to a payout for this videoPoker game from the file @param handFile .
	 * Each line corresponds to a hand that should have a name, payout and optional arguments, namely card range and/or number of pairs.
	 */
	private void readHandsFromFile(String handFile) {
		File f = new File(handFile);
		Scanner scan;
		String[] params;
		
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				params = scan.nextLine().split("\\s+", 0); //reads file line and splits its collumns to a string array
				hands.add(PokerHandFactory.createPokerHand(params)); 
			}
			scan.close();

		} catch (FileNotFoundException e) {
			System.out.println("File " + handFile + " not found.");
			System.out.println("Exiting.");
			System.exit(1);
			
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("Exiting.");
			System.exit(1);
		}
	}
	/**
	 * Checks the @param player hand, @param hand ,  
	 * @return found combination with the highest payout. When no combination is found null is returned instead.
	 */
	private PokerHand checkPlayerHand() {
		int count = hands.size() - 1;
		for (PokerHand hand : hands) {
			if (hand.checkHand(player.getHand())) {
				player.addStatistic(count, 1);
				return hand;
			}
			count--;
		}
		//No hand matched. Player lost.
		player.addStatistic(11, 1);
		return null;
	}
	/**
	 * Uses the method {@link #checkPlayerhand() checkPlayerhand} to find combination with the highest payout. 
	 * @return the highest payout and prints the combination the player had.
	 * When no combination is found the player loses and returns 0.
	 */
	private int payout() {
		PokerHand highestHand = checkPlayerHand();
		if(highestHand == null) {
			System.out.println("Player lost.");
			System.out.println("Player money: " + player.getMoney());
			return 0;
		}
		System.out.println("Player won with " + highestHand.getName() + ".");
		System.out.println("Player money: " + player.getMoney());
		return highestHand.getPayout(bet);
	}
	/**
	 * Determines the cards to hold from @param strategy
	 * sets the positions according to the chosen strategy.
	 */
	private boolean advice() {
		if(debugMode) System.out.println("Performing action a");

		ArrayList<Integer> positions = strategy.getOptimalStrategy(player.getHand());
		player.setAdvicePositions(positions);

		if(positions == null) {
			if(debugMode) System.out.println("Player should discard all cards");
			player.setAdvicePositions(new ArrayList<Integer>());
		}

		if(debugMode) System.out.println("Player should hold cards " + positions);
		return true;
	}
	/**
	 * Performs action bet.
	 * Sets @param bet according to @param action while checking if the betted amount is valid.
	 * Removes betted amount from the player's credit. In debugMode prints the betted amount.
	 * @return true if the betted amount is valid, returns false otherwise.
	 */
	private boolean bet(Action action) {
		if(debugMode) System.out.println("Performing action b " + action.getBet());

		if (action.getBet() != 0)// checks if betted amount is 0
			bet = action.getBet();
		else if (bet == 0)// if betted amount is 0 sets bet to 5
			bet = 5;

		if (bet < 1 || bet > 5) { //checks if betted amount is valid
			System.out.println("Invalid bet amount: " + bet);
			bet = 5;
			return false;
		}
		player.credit(bet); // removes betted amount from player credit
		player.addStatistic(14, bet);
		if (debugMode)
			System.out.println("Player betted " + bet + ". Player cash is now " + player.getMoney());
		return true;
	}
	/**
	 *Prints the player's money
	 */
	private boolean credit() {
			if(debugMode) System.out.println("Performing action $");
			System.out.println("Player cash is " + player.getMoney());
			return true;
	}
	/**
	 *Deals five cards. In debugMode it also prints them. 
	 */
	private boolean deal() {
		if(debugMode) System.out.println("Performing action d");

		player.setHand(deck.deal(5));
		player.addStatistic(12, 1);

		if(debugMode) System.out.println("Dealt cards. Hand is " + player.getHand());
		return true;
	}
	/**
	 *Performs action hold.
	 *Replaces all cards except those in previously picked positions stored in @param action . 
	 *In debugMode it also prints the new hand. 
	 */
	private boolean hold(Action action) {
		if(debugMode) System.out.println("Performing action " + action + " " + action.getPositions());

			for(int i=0; i<5; i++) positions.add(i);

			ArrayList<Integer> holdPositions = action.getPositions();
			holdPositions.replaceAll(e -> e - 1);
			positions.removeAll(holdPositions);
			
			if (positions.size() == 0) break;

			player.replaceInHand(deck.deal(positions.size()), positions);
			System.out.println("Dealt new cards. Hand is " + player.getHand());
			break;
		case 's':
			player.statistics();
			break;
		default:
			System.out.println("Peformed " + action.getAction());
		}

		player.replaceInHand(deck.deal(positions.size()), positions);
		if(debugMode)
			System.out.println("Dealt new cards. Player hand is now " + player.getHand());
		return true;
	}
	/**
	 *Prints game statistics at the moment the function is called.
	 */
	private boolean statistics() {
		if(debugMode) System.out.println("Performing action s");

		player.printStatistics();

		return true;
	}
	/**
	 *Performs @param action by calling the corresponding method
	 *@return false if the actions is different from the implemented methods
	 */
	private boolean performAction(Action action) {
		
		switch (action.getAction()) {
		case 'a'://Advice
			return advice();
		case 'b'://Bet
			return bet(action);
		case '$': //Show player money
			return credit();
		case 'd': //Deal cards
			return deal();
		case 'h': //Hold cards
			return hold(action);
		case 's': //Show statistics
			return statistics();
		}
		System.out.println("Game finished.");
	}
	/**
	 *The game usually follows the sequence: bet -> deal -> hold. 
	 *Other actions, such as $ can be called in between.
	 *This method handles actions that are out of the ordinary sequence.
	 *@return false when the next action is null. If all tests are sucessful returns true.
	 */
	private boolean gamePhase(char expectedAction) {
		Action action = player.askAction();
		if(action == null) return false;

		while(action.getAction() == '$' || action.getAction() == 'a') {
			System.out.println("Performing action " + action);
			performAction(action);
			action = player.askAction();
		}

		if(action.getAction() != expectedAction) {
			 System.out.println("Illegal action. Please provide a bet");
			 gamePhase(expectedAction);
		}

		System.out.println("Performing action " + action);
		if(!performAction(action)) gamePhase(expectedAction);
		
		return true;
	}
	/**
	 *Begins the game. The game usually follows and repeats the sequence: bet -> deal -> hold.
	 *The game is played until there are no more actions or an error occurs that leads {@link #gamePhase(char)} to return false
	 *breaking the infinite loop and doing a print. In simulation mode the deck is always reset and shuffled at the end of each round.
	 */
	public void playGame() {
		int payout;
		while (true) {
			if (!gamePhase('b'))
				break;
			if (!gamePhase('d'))
				break;
			if (!gamePhase('h'))
				break;

			payout = payout();
			player.addStatistic(13, payout);
			player.payout(payout);

			if(debugMode)
				System.out.println("Player cash is now " + player.getMoney());
			if(!debugMode) {
				deck.reset();
				deck.shuffle();
			}
		}
		System.out.println();
		System.out.println("♦♣♥♠ GAME FINISHED ♠♥♣♦");
	}

}
