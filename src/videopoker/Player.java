package videopoker;

import java.util.ArrayList;

import playingCards.Card;
import playingCards.HandOfCards;

public abstract class Player {

	protected final int money0; //seria melhor guardar no stats? nao seria final nesse caso.
	protected int money;
	protected int lastBet = 0;
	protected HandOfCards hand;

	protected int[] stats = new int[13]; 
	protected int advised_positions[];

	public Player(int _money) {
		money = _money;
		money0 = _money;
		hand = new HandOfCards(5);
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

	public HandOfCards getHand() {
		return hand;
	}

	public abstract Action askAction();

	public int getMoney() {
		return money;

	}

	public void credit(int amount) {
		money -= amount;
	}

	public void payout(int amount) {
		money += amount;
	}

	public void printStatistics() {
		// prints average statistics of the game

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
                       + "\n Credit                " + money + " (" + ((float)money/(float)money0*100) + "%)");
		
	}

	public void addStatistic(int i){
		stats[i] = stats[i] + 1;
	}

	public void setAdvicePositions(int _positionsFromAdvice[]) {		
		advised_positions = _positionsFromAdvice.clone();
	}
}
