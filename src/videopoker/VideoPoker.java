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
	
	private int bet = 0;
	
	public VideoPoker(String handFile, String stratFile, String deckFile, boolean shuffle, Player player) {
		this.player = player;
		this.shuffle = shuffle;

		deck = new DeckOfCards(deckFile);
		
		deck.print();
		
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

	public int payout() {
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

	private boolean performAction(Action action) {
		switch(action.getAction()) {
		case 'b':
			if(action.getBet() != 0) bet = action.getBet();
			else if(bet == 0) bet = 5;
			
			if(bet < 1 || bet > 5) {
				System.out.println("Invalid bet amount: " + bet);
				bet = 5;
				return false;
			}
			player.credit(bet);
			System.out.println("Betted " + bet + ". Player cash is " + player.getMoney());
			break;

		case 'd':
			player.setHand(deck.deal(5));
			System.out.println("Dealt cards. Hand is " + player.getHand());
			break;
		case '$':
			System.out.println("Player cash is " + player.getMoney());
			break;

		case 'h':
			ArrayList<Integer> positions = new ArrayList<Integer>();

			for(int i=0; i<5; i++) positions.add(i);

			ArrayList<Integer> holdPositions = action.getPositions();
			holdPositions.replaceAll(e -> e - 1);
			positions.removeAll(holdPositions);

			player.replaceInHand(deck.deal(positions.size()), positions);
			System.out.println("Dealt new cards. Hand is " + player.getHand());
			break;
		}
		return true;
	}

	public void playGame() {
		while(true) {
			if(!gamePhase('b')) break;
			if(!gamePhase('d')) break;
			if(!gamePhase('h')) break;
			payout();
		}
		System.out.println("Game finished.");
	}

	private boolean gamePhase(char expectedAction) {
		Action action = player.askAction();
		if(action == null) return false;

		while(action.getAction() == '$') {
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
}
