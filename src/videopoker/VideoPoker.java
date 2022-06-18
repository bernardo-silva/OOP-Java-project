/**
 * 
 */
package videopoker;

import java.util.ArrayList;
import java.util.Arrays;

import playingCards.DeckOfCards;
import videopoker.pokerHands.PokerHand;

/**
 * @author bs
 *
 */
public class VideoPoker {
	Player player;
	DeckOfCards deck;

	private ArrayList<PokerHand> hands;
	private Strategy strategy;

	private boolean debugMode = false;

	private int bet = 0;

	public VideoPoker(String handFile, String deckFile, Player player, Strategy strategy, boolean debugMode) {
		this.player = player;
		this.strategy = strategy;
		this.debugMode = debugMode;

		deck = new DeckOfCards(deckFile);

		if (debugMode) {
			System.out.println("Deck is: " + deck);
			System.out.println();
		}


		hands = new ArrayList<PokerHand>();
		hands = VideoPokerUtil.handsFromFile(handFile);
	}

	public VideoPoker(String handFile, Player player, Strategy strategy) {
		this.player = player;
		this.strategy = strategy;

		deck = new DeckOfCards();
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
			}
			return 0;
		}
		if (debugMode) {
			System.out.println("Player won with " + highestHand.getName() + ".");
			System.out.println("Player cash is now " + player.getMoney());
		}
		return highestHand.getPayout(bet);
	}

	private boolean advice() {
		if(debugMode) System.out.println("Performing action a");

		ArrayList<Integer> positions = strategy.getOptimalStrategy(player.getHand());
		player.setAdvicePositions(positions);

		if(positions == null)
			if(debugMode) System.out.println("Player should discard all cards");

		if(debugMode) System.out.println("Player should hold cards " + positions);
		return true;
	}

	private boolean bet(Action action) {
		if(debugMode) System.out.println("Performing action b " + action.getBet());

		if (action.getBet() != 0)
			bet = action.getBet();
		else if (bet == 0)
			bet = 5;

		if (bet < 1 || bet > 5) {
			System.out.println("Invalid bet amount: " + bet);
			bet = 5;
			return false;
		}
		player.credit(bet);
		if (debugMode)
			System.out.println("Player betted " + bet + ". Player cash is now " + player.getMoney());
		return true;
	}

	private boolean credit() {
			if(debugMode) System.out.println("Performing action $");
			System.out.println("Player cash is " + player.getMoney());
			return true;
	}

	private boolean deal() {
		if(debugMode) System.out.println("Performing action d");

		player.setHand(deck.deal(5));
		player.addStatistic(12);

		if(debugMode) System.out.println("Dealt cards. Hand is " + player.getHand());
		return true;
	}

	private boolean hold(Action action) {
		if(debugMode) System.out.println("Performing action " + action + " " + action.getPositions());

		ArrayList<Integer> positions = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4));
		ArrayList<Integer> holdPositions = action.getPositions();

		holdPositions.replaceAll(e -> e - 1); // Decrease all positions by 1
		positions.removeAll(holdPositions); // Get positions to remove from

		if (positions.size() == 0) { //hold all cards
			if(debugMode)
				System.out.println("Held all cards. Player hand is " + player.getHand());
			return true;
		}

		player.replaceInHand(deck.deal(positions.size()), positions);
		if(debugMode)
			System.out.println("Dealt new cards. Player hand is now " + player.getHand());
		return true;
	}

	private boolean statistics() {
		if(debugMode) System.out.println("Performing action s");

		player.printStatistics();

		return true;
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
		case 'a':
			return advice();
		case 'b':
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
		return false;
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
			if(debugMode) System.out.println();

			performAction(action);

			action = player.askAction();
			if (action == null) return false;
		}

		if (action.getAction() != expectedAction) {
			System.out.println("Illegal action. Please provide a bet");
			gamePhase(expectedAction);
		}

		if(debugMode) System.out.println();
		if (!performAction(action))
			gamePhase(expectedAction);

		return true;
	}
}
