package videopoker.pokerHands;

import playingCards.HandOfCards;

public class StraightFlush extends PokerHand {

	public StraightFlush(String name, int[] payout) {
		super(name, payout);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		PokerHand straight = new Straight("S",null);
		PokerHand flush = new Flush("F", null);
		return straight.checkHand(hand) && flush.checkHand(hand);
	}

}
