package videopoker;

public class Straight extends PokerHand {

	public Straight(String name, int[] payout) {
		super(name, payout);
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
