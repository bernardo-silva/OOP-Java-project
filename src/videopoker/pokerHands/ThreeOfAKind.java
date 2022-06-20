package videopoker.pokerHands;

import playingCards.Card;
import playingCards.HandOfCards;

public class ThreeOfAKind extends PokerHand {
	private  Card minCard = null;
	private  Card maxCard = null;

	public ThreeOfAKind(String name, int[] payout){
		super(name, payout);
	}
	public ThreeOfAKind(String name, int[] payout, Card minCard, Card maxCard) {
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
			if(frequencies[i] == 3) return true;

		return false;
	}

}
