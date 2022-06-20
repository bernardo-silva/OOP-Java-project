package videoPoker.pokerHands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a straight.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class Straight extends PokerHand {

        /**
         * Constructs an object that checks straights.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public Straight(String name, int[] payout) {
		super(name, payout);
	}

        /**
         * Returns true if the provided hand has a straight.
         *
         * @param hand a hand of cards
         * @return true if the hand has a straight
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		if( hand.getUniqueFaces() !=  5) return false;

		ArrayList<Integer> freqArr = hand.getFrequenciesArr();

		List<Integer> pattern = Arrays.asList(1,1,1,1,1);
		int subArrIdx = Collections.indexOfSubList(freqArr, pattern);
		return subArrIdx != -1;
	}
}
