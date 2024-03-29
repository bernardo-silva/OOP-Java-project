package videoPoker.pokerHands;

import playingCards.Card;

/**
 * Factory class that returns a {@link PokerHand} given a name and an array of
 * parameters.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class PokerHandFactory {
        /**
         * Returns a {@link PokerHand} given a name and an array of parameters
         *
         * @param name the name to give to the {@link PokerHand}
         * @param params parameters necessary to create the poker hand
         * @return an object that checks the desired poker hand
         * @exception IllegalArgumentException if the parameters are invalid.
         */
	public static PokerHand createPokerHand(String name, String[] params) {
		if(params.length < 2)
			throw new IllegalArgumentException("The hand should have a name, payout and optional arguments.");
		PokerHand hand;

		String handType = params[0];
		int[] payouts = stringArrToIntArr(params[params.length-1].split(",",0));

		Card minCard, maxCard;
		String[] cards;

		switch(handType) {
		case "RoyalFlush":
			hand = new RoyalFlush(name, payouts);
			break;
		case "StraightFlush":
			hand = new StraightFlush(name, payouts);
			break;
		case "FourOfAKind":
			if(params.length != 3)
				throw new IllegalArgumentException("The hand " + name + " expects 3 parameters: Name, card range and payouts.");

			cards = params[1].split("-",2);
			minCard = new Card(cards[0]);
			maxCard = (cards.length==2)?new Card(cards[1]):minCard;

			hand = new FourOfAKind(name, payouts, minCard, maxCard);
			break;
		case "FullHouse":
			hand = new FullHouse(name, payouts);
			break;
		case "Flush":
			hand = new Flush(name, payouts);
			break;
		case "Straight":
			hand = new Straight(name, payouts);
			break;
		case "ThreeOfAKind":
			if(params.length != 3)
				throw new IllegalArgumentException("The hand " + name + " expects 3 parameters: Name, card range and payouts.");

			cards = params[1].split("-",2);
			minCard = new Card(cards[0]);
			maxCard = (cards.length==2)?new Card(cards[1]):minCard;

			hand = new ThreeOfAKind(name, payouts, minCard, maxCard);
			break;
		case "NPairOf":
			if(params.length != 4)
				throw new IllegalArgumentException("The hand " + name + " expects 4 parameters: Name, number of pairs, card range and payouts.");

			int N = Integer.parseInt(params[1]);
			cards = params[2].split("-",2);
			minCard = new Card(cards[0]);
			maxCard = (cards.length==2)?new Card(cards[1]):minCard;

			hand = new NPairOf(name, payouts, N, minCard, maxCard);
			break;
		default:
			throw new IllegalArgumentException("The name " + name + " does not correspond to any implemented PokerHand.");
		}

		return hand;
	}

	private static int[] stringArrToIntArr(String[] split) {
		int[] payouts = new int[split.length];

		for(int i=0;i<split.length;i++) {
			payouts[i] = Integer.parseInt(split[i]);
		}
		return payouts;
	}
}
