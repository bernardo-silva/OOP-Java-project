package videopoker;

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

	public void setHand(ArrayList<Card> cards) {
		if (cards.size() != 5)
			throw new IllegalArgumentException("The hand must have 5 cards");
		int[] positions = { 0, 1, 2, 3, 4 };
		hand.replaceCards(cards, positions);
	}

	public void replaceInHand(ArrayList<Card> card, ArrayList<Integer> positions) {
		hand.replaceCards(card, positions);
	}

	/**
	 * 
	 * @return the hand of cards
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
