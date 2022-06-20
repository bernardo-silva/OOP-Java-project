package videoPoker;

import java.util.ArrayList;

import playingCards.Card;
import playingCards.HandOfCards;

/**
 * 
 *
 */

public abstract class Player {

//	protected final int money0; //seria melhor guardar no stats? nao seria final nesse caso.
	protected int money;
	protected int lastBet = 0;
	protected HandOfCards hand;

	protected int[] stats = new int[15]; 
	protected ArrayList<Integer> advised_positions;

	public Player(int _money) {
		money = _money;
//		money0 = _money;
		hand = new HandOfCards();
	}

	/**
	 * @param cards
	 */
	public void setHand(ArrayList<Card> cards) {
		if (cards.size() != 5)
			throw new IllegalArgumentException("The hand must have 5 cards");
		int[] positions = { 0, 1, 2, 3, 4 };
		hand.replaceCards(cards, positions);
	}


	/**
	 * 
	 * @param card
	 * @param positions
	 */
	public void replaceInHand(ArrayList<Card> card, ArrayList<Integer> positions) {
		hand.replaceCards(card, positions);
	}

	/**
	 * Gets the hand of the card
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
	 * Gets the player's amount of money
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
                       + "\n Jacks or Better         " + (float)stats[0]/(float)stats[12]
                       + "\n Two Pair                " + (float)stats[1]/(float)stats[12]
                       + "\n Three of a Kind         " + (float)stats[2]/(float)stats[12]
                       + "\n Straight                " + (float)stats[3]/(float)stats[12]
                       + "\n Flush                   " + (float)stats[4]/(float)stats[12]
                       + "\n Full house              " + (float)stats[5]/(float)stats[12]
                       + "\n Four of a Kind          " + (float)(stats[6] + stats[7] + stats[8])/(float)stats[12]
                       + "\n Straight Flush          " + (float)stats[9]/(float)stats[12]
                       + "\n Royal Flush             " + (float)stats[10]/(float)stats[12]
                       + "\n Other                   " + (float)stats[11]/(float)stats[12]
                       + "\n___________________________"
                       + "\n Total                   " + stats[12]
                       + "\n___________________________"
                       + "\n Credit                " + money + " (" + String.format("%.2f",((float)stats[13]/(float)stats[14]*100)) + "%)");
		
	}


	/**
	 * Increases by a defined amount a statistic metric of the game
	 * @param idx index of the statistic metric to increase
	 * @param amount amount to increase
	 */
	public void addStatistic(int idx, int amount){
		stats[idx] += amount;
	}

	/**
	 * Sets the array containing the positions of the cards that are advised to be held
	 * @param array with the indexes of the cards advised to be held
	 */
	public void setAdvicePositions(ArrayList<Integer> positions) {		
		advised_positions = positions;
	}
}
