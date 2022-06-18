package videopoker.pokerHands;

import playingCards.HandOfCards;

public class FullHouse extends PokerHand {

	public FullHouse(String name, int[] payout) {
		super(name, payout);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		int[] frequencies = hand.getFrequencies();

		for(int i = 2; i<=14; i++) 
			if(frequencies[i] == 1 || frequencies[i] == 4) return false;
		
		return true;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
