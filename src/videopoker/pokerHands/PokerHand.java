package videopoker.pokerHands;
import playingCards.HandOfCards;

public abstract class PokerHand{
	private String name;
	private int[] payout;
//	private ArrayList<HandRule> rules;
	/**
	 * @param name
	 * @param payout
	 */
	public PokerHand(String name, int[] payout) {//, ArrayList<HandRule> rules) {
		this.name = name;
		this.payout = payout;
//		this.rules = rules;
	}
	public abstract boolean checkHand(HandOfCards hand);

	public abstract boolean checkHandOffBy(HandOfCards hand, int n);

	public String getName() {
		return name;
	}
	public int getPayout(int betAmount) {
		return payout[betAmount-1];
	}
}