package videopoker.pokerHands;

import playingCards.Card;
import playingCards.HandOfCards;

public class NPairOf extends PokerHand {
	private final int n;
	private  Card minCard = null;
	private  Card maxCard = null;

	public NPairOf(String name, int[] payout, int n, Card minCard, Card maxCard) {
		super(name, payout);
		this.n = n;
		this.minCard = minCard;
		this.maxCard = maxCard;
	}

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

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
