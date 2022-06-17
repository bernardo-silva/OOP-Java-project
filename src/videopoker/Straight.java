package videopoker;

import playingCards.HandOfCards;

public class Straight extends PokerHand {

	public Straight(String name, int[] payout) {
		super(name, payout);
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		return hand.getUniqueFaces() == 5 && hand.getCardDistances() == 4;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
