package videoPoker.pokerHands;

import playingCards.Card;
import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a specified
 * amount of pairs between two given cards.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class NPairOf extends PokerHand {
	private final int n;
	private  Card minCard = null;
	private  Card maxCard = null;

        /**
         * Constructs an object that checks four of a kind of any type of card.
         *
         * @param name name of the hand
         * @param payout array of payout values
         * @param n number of pairs to check (1 or 2)
         * @param minCard lowest card to check
         * @param maxCard highest card to check
         */
	public NPairOf(String name, int[] payout, int n, Card minCard, Card maxCard) {
		super(name, payout);
		this.n = n;
		this.minCard = minCard;
		this.maxCard = maxCard;
	}

        /**
         * Returns true if the provided hand has the desired number of pairs
         * inside the specified range.
         *
         * @param hand a hand of cards
         * @return true if the hand has a four of a kind
         */
	@Override
	public boolean checkHand(HandOfCards hand) {
		int[] frequencies = hand.getFrequencies();
		int start, end;
		int nFound = 0;

		if(minCard == null && maxCard == null) {
			start = 2;
			end = 14;
		}
		else {
			start = minCard.getFace();
			end = maxCard.getFace();
		}

		for(int i = start; i<=end; i++)
			if(frequencies[i] == 2) nFound ++;

		return nFound == n;
	}

}
