package playingCards;

//import java.util.Arrays;

public class Card implements Comparable<Card>{
    
	//static final String[] Suit = {" ","C","S","H","D"};
	//static final String[] Face = {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	//static final char[] Suit = {' ','C','S','H','D'};
	enum Suit{
		C,
		S,
		H,
		D,
		ANY,
		SOME
	}
	static final char[] Face = {' ',' ','2','3','4','5','6','7','8','9','T','J','Q','K','A'};

	private int cardFace; // face of card
	private Suit cardSuit; // suit of card

//constructors
	public Card(int face, int suit)//using ints 
	{   
		if(face>13 || face<1) throw new IllegalArgumentException("Card rank is not valid.");
		if(suit>5 || suit<0) throw new IllegalArgumentException("Card suit is not valid.");
		cardFace = face; // initialize face of card
		cardSuit = Suit.values()[suit]; // initialize suit of card
	}

	public Card(String card)//using a 2 char string as in the files the teacher provided 
	{   
		cardFace = -1;
		for (int i=2; i<Face.length;i++) {
			if(Face[i] == card.charAt(0)) {
				cardFace = i;
				break;
			}
		}
		
		if(card.length() == 1) {
			cardSuit = Suit.ANY;
			return;
		}

		if(card.charAt(1) == '*')
			cardSuit = Suit.ANY;
		else if(card.charAt(1) == 'X')
			cardSuit = Suit.SOME;
		else
			cardSuit = Suit.valueOf(card.substring(1,2));

		
//		if(cardSuit==-1) throw new IllegalArgumentException("Card suit is not valid.");
		if(cardFace==-1) throw new IllegalArgumentException("Card rank is not valid.");
	}

	public int getFace(){ 
		return cardFace; 
	}
	
	public int getSuit(){ 
		return cardSuit.ordinal(); 
	}
	
	public String toString(){ 
		return "" + Face[cardFace] + cardSuit;
	}

	public int compareTo(Card other) {
			if(cardFace > other.getFace()) return 1;
			else if (cardFace == other.getFace()) return 0;
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