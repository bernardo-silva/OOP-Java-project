/**
 * 
 */
package videopoker;

import java.util.ArrayList;
import java.util.Arrays;

import playingCards.DeckOfCards;

/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;

	private ArrayList<PokerHand> hands;
	private ArrayList<PokerHand> optimalStrat;

	private boolean debugMode = false;

	private int bet = 0;

	public VideoPoker(String handFile, String stratFile, String deckFile, Player player, boolean debugMode) {
		this.player = player;
		this.debugMode = debugMode;

		deck = new DeckOfCards(deckFile);

		if (debugMode) {
			System.out.println("Deck is: " + deck);
			System.out.println();
		}

		optimalStrat = VideoPokerUtil.stratFromFile(stratFile);
		hands = new ArrayList<PokerHand>();

		hands = VideoPokerUtil.handsFromFile(handFile);
	}

	public VideoPoker(String handFile, String stratFile, Player player) {
		this.player = player;
		deck = new DeckOfCards();

		optimalStrat = VideoPokerUtil.stratFromFile(stratFile);
		hands = new ArrayList<PokerHand>();

		hands = VideoPokerUtil.handsFromFile(handFile);
		System.out.println(hands);
	}

	private int payout() {
		PokerHand highestHand = checkPlayerHand();
		if (highestHand == null) {
			if (debugMode) {
				System.out.println("Player lost.");
				System.out.println("Player cash is now " + player.getMoney());
				System.out.println();
			}
			return 0;
		}
		if (debugMode) {
			System.out.println("Player won with " + highestHand.getName() + ".");
			System.out.println("Player cash is now " + player.getMoney());
			System.out.println();
		}
		return highestHand.getPayout(bet);
	}

	public int[] advice() {
		return null;
	}

	private PokerHand checkPlayerHand() {
		int count = 10;
		for (PokerHand hand : hands) {
			if (hand.checkHand(player.getHand())) {
				player.addStatistic(count); // aqui adicionar metodo que adiciona 1 às estatisticas da mao que ganhou.
				return hand;
			}
			count--;
		}
		player.addStatistic(11);
		return null;
	}

	private boolean performAction(Action action) {
		switch (action.getAction()) {
		case 'b':
			if(debugMode) System.out.println("Performing action " + action + " " + action.getBet());

			if (action.getBet() != 0)
				bet = action.getBet();
			else if (bet == 0)
				bet = 5;

			if (bet < 1 || bet > 5) {
				System.out.println("Invalid bet amount: " + bet);
				System.out.println();
				bet = 5;
				return false;
			}

			player.credit(bet);
			if (debugMode)
				System.out.println("Player betted " + bet + ". Player cash is now " + player.getMoney());
			break;

		case 'd': //Deal cards
			if(debugMode) System.out.println("Performing action " + action);

			player.setHand(deck.deal(5));
			player.addStatistic(12);
			System.out.println("Dealt cards. Hand is " + player.getHand());
			break;

		case '$': //Show player money
			if(debugMode) System.out.println("Performing action " + action);
			System.out.println("Player cash is " + player.getMoney());
			break;

		case 'h': //Hold cards
			if(debugMode) System.out.println("Performing action " + action + " " + action.getPositions());

			ArrayList<Integer> positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
			ArrayList<Integer> holdPositions = action.getPositions();

			holdPositions.replaceAll(e -> e - 1); // Decrease all positions by 1
			positions.removeAll(holdPositions); // Get positions to remove from

			if (positions.size() == 0) {
				if(debugMode)
					System.out.println("Held all cards. Player hand is " + player.getHand());
				
				break;
			}

			player.replaceInHand(deck.deal(positions.size()), positions);
			if(debugMode)
				System.out.println("Dealt new cards. Player hand is now " + player.getHand());
			break;

		case 's': //Show statistics
			if(debugMode) System.out.println("Performing action " + action);

			player.printStatistics();
			break;
		}

		if(debugMode) System.out.println();
		return true;
	}

	public void playGame() {
		while (true) {
			if (!gamePhase('b'))
				break;
			if (!gamePhase('d'))
				break;
			if (!gamePhase('h'))
				break;
			player.payout(payout());
		}
		System.out.println("Game finished.");
	}

	private boolean gamePhase(char expectedAction) {
		Action action = player.askAction();
		if (action == null)
			return false;

		while (action.getAction() == '$' || action.getAction() == 'a' ||
		action.getAction() == 's') {

			performAction(action);

			action = player.askAction();
			if (action == null) return false;
		}

		if (action.getAction() != expectedAction) {
			System.out.println("Illegal action. Please provide a bet");
			gamePhase(expectedAction);
		}

		if (!performAction(action))
			gamePhase(expectedAction);

		return true;
	}
}
