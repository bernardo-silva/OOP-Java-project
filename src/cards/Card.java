package cards;

//import java.util.Arrays;

public class Card implements Comparable<Card>{
    
	//static final String[] Suit = {" ","C","S","H","D"};
	//static final String[] Face = {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	static final char[] Suit = {' ','C','S','H','D'};
	static final char[] Face = {' ','A','2','3','4','5','6','7','8','9','T','J','Q','K'};

	private int cardFace; // face of card
	private int cardSuit; // suit of card

//constructors
	public Card(int face, int suit)//using ints 
	{   
		if(face>13||face<1) throw new IllegalArgumentException("Card rank is not valid.");
		if(suit>4||suit<1) throw new IllegalArgumentException("Card suit is not valid.");
		cardFace = face; // initialize face of card
		cardSuit = suit; // initialize suit of card
	}

	public Card(String card)//using a 2 char string as in the files the teacher provided 
	{   
		for (int i=1; i<Suit.length;i++) {
			if(Suit[i] == card.charAt(1)) {
				cardSuit=i;
				break;
			}
			else {
				cardSuit=-1;
			}
		}
				
		for (int i=1; i<Face.length;i++) {
			if(Face[i] == card.charAt(0)) {
				cardFace=i;
				break;
				}
			else {
				cardFace=-1;
			}
		}
		
		if(cardSuit==-1) throw new IllegalArgumentException("Card suit is not valid.");
		if(cardFace==-1) throw new IllegalArgumentException("Card rank is not valid.");
		
		//cardFace = Arrays.asList(Face).indexOf(card.substring(0,1)); // initialize face of card
		//cardSuit =  Arrays.asList(Suit).indexOf(card.substring(0)); // initialize suit of card
		
		/*System.out.println(card.charAt(0));
		System.out.println(Arrays.asList(Face).indexOf(card.charAt(0)));
		cardFace = Arrays.asList(Face).indexOf(card.charAt(0)); // initialize face of card
		cardSuit =  Arrays.asList(Suit).indexOf(card.charAt(1)); // initialize suit of card*/
	}

	public int getFace(){ 
		return cardFace; 
	}
	
	public int getSuit(){ 
		return cardSuit; 
	}
	
	public String toString(){ 
		return "" + Face[cardFace] + Suit[cardSuit];
	}

	public int compareTo(Card other) {
		if(cardSuit > other.getSuit()) return 1;
		else if (cardSuit == other.getSuit()) {
			if(cardFace > other.getFace()) return 1;
			else if (cardFace == other.getFace()) return 0;
			else return -1;
		}
		else return -1;
	}
	
	
/*	
public int hashCode() {
	int s=1;
	switch(suit) {
	case 'S': s=1;
	case 'H': s=2;
	case 'D': s=3;
	case 'C': s=4;
		
	}
	switch(face) {
	case 'T': return s*10;
	case 'J': return s*11;
	case 'Q': return s*12;
	case 'K': return s*13;
	case 'A': return s*14;
	default:return s*Integer.valueOf(face);
	}
}
*/

}