package videopoker;

public class Flush extends PokerHand {

	public Flush(String name, int[] payout) {
		super(name, payout);
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		return hand.uniqueSuits.length == 1;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}