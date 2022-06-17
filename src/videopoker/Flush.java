package videopoker;

import playingCards.HandOfCards;

public class Flush extends PokerHand {

	public Flush(String name, int[] payout) {
		super(name, payout);
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		return hand.getUniqueSuits() == 1;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		return hand.getUniqueSuits() == 1 + n;
	}

}
