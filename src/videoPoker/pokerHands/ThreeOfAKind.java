package videoPoker.pokerHands;

import playingCards.Card;
import playingCards.HandOfCards;

/**
 * Implementation of {@link PokerHand} that checks the hand for a three of a kind
 * between two given cards (or any card if none are provided).
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public class ThreeOfAKind extends PokerHand {
	private  Card minCard = null;
	private  Card maxCard = null;

        /**
         * Constructs an object that checks three of a kind of any type of card.
         *
         * @param name name of the hand
         * @param payout array of payout values
         */
	public ThreeOfAKind(String name, int[] payout){
		super(name, payout);
	}
        /**
         * Constructs an object that checks three of a kind of cards between
         * minCard and maxCard. If these are the same, it checks only that card.
         *
         * @param name name of the hand
         * @param payout array of payout values
         * @param minCard lowest card to check
         * @param maxCard highest card to check
         */
	public ThreeOfAKind(String name, int[] payout, Card minCard, Card maxCard) {
		super(name, payout);
		this.minCard = minCard;
		this.maxCard = maxCard;
	}

	@Override
        /**
         * Returns true if the provided hand has a three of a kind in the
         * specified range.
         *
         * @param hand a hand of cards
         * @return true if the hand has a three of a kind
         */
	public boolean checkHand(HandOfCards hand) {
		int[] frequencies = hand.getFrequencies();
		int start, end;

		if(minCard == null && maxCard == null) {
			start = 2;
			end = 14;
		}
		else {
			start = minCard.getFace();
			end = maxCard.getFace();
		}

		for(int i = start; i<=end; i++)
			if(frequencies[i] == 3) return true;

		return false;
	}

}
