package videoPoker.pokerHands;

import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a flush.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class Flush extends PokerHand {

        /**
         * Constructs an object that checks flushes.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public Flush(String name, int[] payout) {
		super(name, payout);
	}

        /**
         * Returns true if the provided hand has a flush.
         *
         * @param hand a hand of cards
         * @return true if the hand has a flush
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		return hand.getUniqueSuits() == 1;
	}
}
