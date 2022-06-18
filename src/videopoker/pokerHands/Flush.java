package videopoker.pokerHands;

import playingCards.HandOfCards;

public class Flush extends PokerHand {

	public Flush(String name, int[] payout) {
		super(name, payout);
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		return hand.getUniqueSuits() == 1;
	}
}
