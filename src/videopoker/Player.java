package videopoker;

public class Player {
	private int credits;
	private int money;
	private int last_bet;
	private String strategy;
	
	public Player(int _money, String _strategy, String _hand) {
		money = _money;
		strategy = _strategy;		
	}
		
	public void bet(int i) {
	// A b command typed after the
	//	deal and before the end of the dealer’s turn is illegal, and so it should be printed in the terminal
	//	‘‘b: illegal command’’.
		money = money-i;
		last_bet = i;
	}

	public void bet() {
		if(last_bet==0) {
			money = money - 5;
			last_bet = 5;
		}
		money = money - last_bet;		
	}
		
}
