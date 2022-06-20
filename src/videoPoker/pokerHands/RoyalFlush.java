package videoPoker.pokerHands;

import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a royal flush.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class RoyalFlush extends PokerHand {

        /**
         * Constructs an object that checks royal flushes.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public RoyalFlush(String name, int[] payout) {
		super(name, payout);
	}

        /**
         * Returns true if the provided hand has a royal flush.
         *
         * @param hand a hand of cards
         * @return true if the hand has a royal flush
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		PokerHand straight = new Straight("Straight", null);
		PokerHand flush = new Flush("Flush", null);

		return (hand.getLowCard() == 10) && (flush.checkHand(hand)) &&
		(straight.checkHand(hand));
	}

}
