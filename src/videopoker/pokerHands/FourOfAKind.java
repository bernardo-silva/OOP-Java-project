package videopoker.pokerHands;

import playingCards.Card;
import playingCards.HandOfCards;

public class FourOfAKind extends PokerHand {
	private  Card minCard = null;
	private  Card maxCard = null;

	public FourOfAKind(String name, int[] payout){
		super(name, payout);
	}
	public FourOfAKind(String name, int[] payout, Card minCard, Card maxCard) {
		super(name, payout);
		this.minCard = minCard;
		this.maxCard = maxCard;
	}

	@Override
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
			if(frequencies[i] == 4) return true;

		return false;
	}

}
