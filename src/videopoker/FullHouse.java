package videopoker;

import cards.HandOfCards;

public class FullHouse extends PokerHand {

	public FullHouse(String name, int[] payout) {
		super(name, payout);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
