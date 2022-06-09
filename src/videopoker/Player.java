package videopoker;

public class Player {
	private int credits;
	private int money;
	private int last_bet;
	private String strategy;
	private String hand;
	
//	public Player(int _money, String _strategy, String _hand) {
	public Player(int _money) {	
		money = _money;
		//strategy = _strategy;	
		//hand = _hand;
	}
	
	//NOTE: instead of performAction() in the UML, i implemented separately the methods
	//______________Should we create another class called Action ?
	public void credit(int i) {
		money += i;
	}
		
	public void bet(int i) {
		money -= i;
		last_bet = i;
	}

	public void bet() {
		if(last_bet==0) {
			money = money - 5;
			last_bet = 5;
			return;
		}
		money = money - last_bet;		
	}
		
	public void advice() {
		// Q: THIS IS IMPLEMENTED ON VIDEO POKER??
		// prints the next action the player should take
		
	}
	
	public void	statistics() {
		// prints average statistics of the game
        int N0 = 0;
        
        System.out.println(" Hand                    Nb"
                       + "\n __________________________" 
                       + "\n Jacks or Better         " + N0 
                       + "\n Two Pair                " + N0
                       + "\n Three of a Kind         " + N0
                       + "\n Straight                " + N0
                       + "\n Flush                   " + N0
                       + "\n Full house              " + N0
                       + "\n Four of a Kind          " + N0
                       + "\n Straight Flush          " + N0
                       + "\n Royal Flush             " + N0
                       + "\n Other                   " + N0
                       + "\n___________________________"
                       + "\n Total                   " + N0
                       + "\n___________________________"
                       + "\n Credit                " + money + " (" + N0 + "%)");
		
	}
	
	public void	hold() {
		// discards the cards that aren't hold and swaps them with new cards from the deck
		
		// prints current hand
		
	}
	
}
