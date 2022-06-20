package videoPoker.pokerHands;

import playingCards.HandOfCards;

public class RoyalFlush extends PokerHand {

	public RoyalFlush(String name, int[] payout) {
		super(name, payout);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		PokerHand straight = new Straight("Straight", null);
		PokerHand flush = new Flush("Flush", null);

		return (hand.getLowCard() == 10) && (flush.checkHand(hand)) &&
		(straight.checkHand(hand));
	}

}
