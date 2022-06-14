package videopoker;

import java.util.ArrayList;
import cards.Card;
import cards.HandOfCards;

public abstract class Player {
	//private int credits;
	protected int money;
	protected int lastBet = 0;
	//private String strategy;
	protected HandOfCards hand;
	
//	public Player(int _money, String _strategy, String _hand) {
	public Player(int _money) {	
		money = _money;
		hand = new HandOfCards(5);
		//strategy = _strategy;	
		//hand = _hand;
	}
	
	//NOTE: instead of performAction() in the UML, i implemented separately the methods
	//______________Should we create another class called Action ?
	
	public void setHand(ArrayList<Card> cards) {
		if(cards.size() != 5) throw new IllegalArgumentException("The hand must have 5 cards");
		int[] positions = {0,1,2,3,4};
		hand.replaceCards(cards, positions);
	}

	public void replaceInHand(ArrayList<Card> card, ArrayList<Integer> positions) {
		hand.replaceCards(card, positions);
	}

	public HandOfCards getHand() {
		return hand;
	}

	public abstract Action askAction();
//	public abstract int askBet();

	public int getMoney() {
		return money;

	}
	public void credit(int amount) {
		money -= amount;
	}
	public void payout(int amount) {
		money += amount;
	}
		
		
//	public void bet(int amount) {
//		money -= amount;
//		lastBet = amount;
//	}
//
//	public void bet() {
//		if(lastBet==0) {
//			money -= 5;
//			lastBet = 5;
//			return;
//		}
//		money -= lastBet;		
//	}
		
	
	public void	statistics() {
		// prints average statistics of the game
        int N0 = 0;
        
        System.out.println(" Hand                    Nb"
                       + "\n __________________________" 
                       + "\n Jacks or Better         " + N0 
                       + "\n Two Pair                " + N0
                       + "\n Three of a Kind         " + N0
                       + "\n Straight                " + N0
                       + "\n Flush                   " + N0
                       + "\n Full house              " + N0
                       + "\n Four of a Kind          " + N0
                       + "\n Straight Flush          " + N0
                       + "\n Royal Flush             " + N0
                       + "\n Other                   " + N0
                       + "\n___________________________"
                       + "\n Total                   " + N0
                       + "\n___________________________"
                       + "\n Credit                " + money + " (" + N0 + "%)");
		
	}
	
	public void	hold() {
		// discards the cards that aren't hold and swaps them with new cards from the deck
		
		// prints current hand
		
	}
	
}
