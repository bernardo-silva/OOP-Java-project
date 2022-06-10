package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HandOfCards {
	
	protected ArrayList<Card> hand = new ArrayList<Card>(5);

	public HandOfCards(ArrayList<Card> _hand){
		hand = _hand;
		sortCards();
	}
	
	public void sortCards(){
		Collections.sort(hand);
	}
	
	public void replaceCards(ArrayList<Card> cards, int[] positions) {
    	Arrays.sort(positions);
    	for(int i = 0; i < positions.length;i++){
    		hand.remove(positions[i]-i);
    		hand.add(cards.get(i));
    	}
    	sortCards();
    }
	
    public void print() {
        System.out.println(hand);
    }
    
    public void discard(int[] positions) {
    	Arrays.sort(positions);
    	for(int i = 0; i < positions.length;i++){
    		hand.remove(positions[i]-i);
    	}
    }
    
	public void add(ArrayList<Card> cards) {
		hand.addAll(cards);
	}
	
    public static void main(String[] args)
    { 
        DeckOfCards deckteste = new DeckOfCards("../files/card-file.txt");
        deckteste.reset();
        deckteste.shuffle();
        deckteste.print();
        HandOfCards mao = new HandOfCards(deckteste.deal(5));
        System.out.println(mao.hand);
        deckteste.print();
        
        int [] arrli = {0,1};
        
        mao.replaceCards(deckteste.deal(2),arrli);
        System.out.println(mao.hand);
    }
}
 