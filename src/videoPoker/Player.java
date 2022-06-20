package videoPoker;

import java.util.ArrayList;

import playingCards.Card;
import playingCards.HandOfCards;

/**
 * Abstract implementation of a Player. All players to be used in the {@link VideoPoker}
 * game should inherit from this class.
 * <p>
 * The player has a hand of cards and credits. It collects useful statistics
 * about the game and stores the last advised positions.
 *
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */

public abstract class Player {
	/**
	 * Stores the player's money
	 */
	protected int money;
	/**
	 * Stores the last bet amount
	 */
	protected int lastBet = 0;
	/**
	 * Player's hand of cards
	 */
	protected HandOfCards hand;
	/**
	 * Array that stores the statistics of the game
	 */
	protected int[] stats = new int[15]; 
	/**
	 * Array that stores the advised hold positions
	 */
	protected ArrayList<Integer> advised_positions;

	/**
	 * Constructor method that sets the player's money and creates a new empty 
         * hand of cards.
	 * @param _money player's starting money
	 */
	public Player(int _money) {
		money = _money;
//		money0 = _money;
		hand = new HandOfCards();
	}

	/**
	 * Sets the player's hand with the provided cards.
	 * @param cards array of cards to be added to the hand
	 * @throws IllegalArgumentException if the array of cards does not contain 5 elements
	 */
	public void setHand(ArrayList<Card> cards) {
		if (cards.size() != 5)
			throw new IllegalArgumentException("The hand must have 5 cards");
		int[] positions = { 0, 1, 2, 3, 4 };
		hand.replaceCards(cards, positions);
	}


	/**
	 * Replaces the cards at the specified positions in the hand with new specified cards.
	 * @param card array of new cards to add to the hand
	 * @param positions array with the indexes of the cards to replace
	 */
	public void replaceInHand(ArrayList<Card> card, ArrayList<Integer> positions) {
		hand.replaceCards(card, positions);
	}

	/**
	 * Gets the hand of the card.
	 * @return the hand of the card
	 */
	public HandOfCards getHand() {
		return hand;
	}

	/**
	 * Asks for an action to perform.
	 * @return the action to perform
	 */
	public abstract Action askAction(); 
	
	/**
	 * Gets the player's amount of money.
	 * @return amount of money
	 */
	public int getMoney() {
		return money;

	}

	/**
	 * Places money on the game, discounting on the player account.
	 * @param amount amount of money to credit on the game
	 */
	public void credit(int amount) {
		money -= amount;
	}

	/**
	 * Rewards the payout money, adding it on the player account.
	 * @param amount of payout money
	 */
	public void payout(int amount) {
		money += amount;
	}

	/**
	 * Prints the average statistics on the terminal.
	 */
	public void printStatistics() {

		System.out.println();
		System.out.println("----- GAME STATISTICS -----");
        System.out.println(" Hand                    Nb"
                       + "\n __________________________" 
                       + "\n Jacks or Better         " + stats[0]
                       + "\n Two Pair                " + stats[1]
                       + "\n Three of a Kind         " + stats[2]
                       + "\n Straight                " + stats[3]
                       + "\n Flush                   " + stats[4]
                       + "\n Full house              " + stats[5]
                       + "\n Four of a Kind          " + (stats[6] + stats[7] + stats[8])
                       + "\n Straight Flush          " + stats[9]
                       + "\n Royal Flush             " + stats[10]
                       + "\n Other                   " + stats[11]
                       + "\n___________________________"
                       + "\n Total                   " + stats[12]
                       + "\n___________________________"
                       + "\n Credit                " + money + " (" + String.format("%.2f",((float)stats[13]/(float)stats[14]*100)) + "%)");
		
	}


	/**
	 * Increases by a defined amount a statistic metric of the game.
	 * @param idx index of the statistic metric to increase
	 * @param amount amount to increase
	 */
	public void addStatistic(int idx, int amount){
		stats[idx] += amount;
	}

	/**
	 * Sets the array containing the positions of the cards that are advised to be held.
	 * @param positions array with the indexes of the cards advised to be held
	 */
	public void setAdvicePositions(ArrayList<Integer> positions) {		
		advised_positions = positions;
	}
}
