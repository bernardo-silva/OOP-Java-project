package videoPoker.pokerHands;
import playingCards.HandOfCards;

/**
 * Abstract class that represents a poker hand by name, value of payout for
 * different bets and a method that checks if a hand of cards contains the
 * respective poker hand.
 *
 * @author Bernardo Silva
 * @author Miguel Madeira
 * @author Vicente Silvestre
 */
public abstract class PokerHand{
	private String name;
	private int[] payout;
	/**
         * Constructs a Poker hand from a name and an array of payouts.
	 * @param name name of the Poker hand
	 * @param payout array of payout values
	 */
	public PokerHand(String name, int[] payout) {
		this.name = name;
		this.payout = payout;
	}
	/**
         * Abstract method that returns true if the provided hand satisfies the
         * rules to have the respective Poker Hand.
	 * @param hand a hand of cards
         * @return true if the provided hand has the respective poker hand
	 */
	public abstract boolean checkHand(HandOfCards hand);

	/**
         * Returns the name of the poker hand
         * @return the poker hand name
	 */
	public String getName() {
		return name;
	}
	/**
         * Returns the payout for a given bet (between 1 and 5)
         * @param betAmount the credits being betted
         * @return the poker hand payout
	 */
	public int getPayout(int betAmount) {
		return payout[betAmount-1];
	}
}
