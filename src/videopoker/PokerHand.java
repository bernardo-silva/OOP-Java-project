package videopoker;

import java.util.ArrayList;

public class PokerHand implements Comparable<PokerHand>{
	private String name;
	private int payout;
	private ArrayList<HandRule> rules;
	/**
	 * @param name
	 * @param payout
	 * @param rules
	 */
	public PokerHand(String name, int payout, ArrayList<HandRule> rules) {
		this.name = name;
		this.payout = payout;
		this.rules = rules;
	}
	public boolean checkHand(Object hand) {
		for(HandRule rule : rules) {
			if(!rule.check(hand)) return false;
		}
		return true;
	}
	public String getName() {
		return name;
	}
	public int getPayout() {
		return payout;
	}
	@Override
	public int compareTo(PokerHand o) {
		return (payout > o.getPayout())?1:0;
	}
}