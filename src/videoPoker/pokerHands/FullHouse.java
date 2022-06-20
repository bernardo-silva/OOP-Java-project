package videoPoker.pokerHands;

import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a full house.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class FullHouse extends PokerHand {

        /**
         * Constructs an object that checks full houses.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public FullHouse(String name, int[] payout) {
		super(name, payout);
	}

        /**
         * Returns true if the provided hand has a full house.
         *
         * @param hand a hand of cards
         * @return true if the hand has a full house
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		int[] frequencies = hand.getFrequencies();

		for(int i = 2; i<=14; i++)
			if(frequencies[i] == 1 || frequencies[i] == 4) return false;

		return true;
	}

}
