package videopoker;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
