package videopoker;

public class numberRank implements handRule {
	private String[] ranks;
	private int value;

	/**
	 * @param ranks
	 * @param value
	 */
	public numberRank(String[] ranks, int value) {
		this.ranks = ranks;
		this.value = value;
	}

	@Override
	public Boolean check(Object hand) {
		for(String r : ranks) {
			if(hand.ranks.get(r) == value) return true;
		}
		return false;
	}

}
