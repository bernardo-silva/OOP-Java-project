package videopoker;
public class FourOfAKind extends PokerHand {
	private  Card minCard = null;
	private  Card maxCard = null;

	public FourOfAKind(String name, int[] payout){
		super(name, payout);
	}
	public FourOfAKind(String name, int[] payout, Card minCard, Card maxCard) {
		super(name, payout);
		this.cards = cards;
	}

	@Override
	public boolean checkHand(HandOfCards hand) {
		return fals;
	}

	@Override
	public boolean checkHandOffBy(HandOfCards hand, int n) {
		// TODO Auto-generated method stub
		return false;
	}

}
