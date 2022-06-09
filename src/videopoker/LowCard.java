package videopoker;

public class LowCard implements HandRule {
	String lowCard;
	
	public LowCard(String card) {
		lowCard = card;
	}

	@Override
	public Boolean check(Object hand) {
		return (hand.lowCard() == lowCard);
		return null;
	}

}
