/**
 * 
 */
package videopoker;

import java.util.ArrayList;

import cards.DeckOfCards;
import cards.HandOfCards;
import cards.Card;

/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;
	
//	private List<String> hands;
	private ArrayList<PokerHand> hands;
	private ArrayList<PokerHand> optimalStrat;
	
	private boolean shuffle = true;
	
	public VideoPoker(String handFile, String stratFile, String deckFile, boolean shuffle, Player player) {
		this.player = player;
		this.shuffle = shuffle;

		deck = new DeckOfCards(deckFile);
		
		optimalStrat = VideoPokerUtil.stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		hands = VideoPokerUtil.handsFromFile(handFile);
		System.out.println(hands);
	}

	public VideoPoker(String handFile, String stratFile, Player player) {
		this.player = player;
		deck = new DeckOfCards();
		
		optimalStrat = VideoPokerUtil.stratFromFile(stratFile);
		hands= new ArrayList<PokerHand>();

		hands = VideoPokerUtil.handsFromFile(handFile);
		System.out.println(hands);
	}

	public int payout(int bet) {
		PokerHand highestHand = checkPlayerHand();
		if(highestHand == null) return 0;
		return highestHand.getPayout(bet);
	}

	public int[] advice(){
		return null;
	}

	private PokerHand checkPlayerHand(){
		for(PokerHand hand : hands) {
			if (hand.checkHand(player.getHand())) return hand;
		}
		return null;
	}
	private void performAction(Action action) {
		
	}

	public void playGame() {
		int bet;
		Action action;
		while(true) {
			bet = player.askBet();
			if(bet < 1 || bet > 5) {
				System.out.println("Invalid bet amount");
				break;
			}

			player.setHand(deck.deal(5));
			action = player.askAction();
			performAction(action);
			payout(bet);
		}
	}
}
