package videoPoker.pokerHands;

import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a straight flush.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class StraightFlush extends PokerHand {

        /**
         * Constructs an object that checks straight flushes.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public StraightFlush(String name, int[] payout) {
		super(name, payout);
	}

        /**
         * Returns true if the provided hand has a straight flush.
         *
         * @param hand a hand of cards
         * @return true if the hand has a straight flush
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		PokerHand straight = new Straight("S",null);
		PokerHand flush = new Flush("F", null);
		return straight.checkHand(hand) && flush.checkHand(hand);
	}

}
