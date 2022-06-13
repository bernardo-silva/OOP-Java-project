package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class HandOfCards {
	
	protected ArrayList<Card> hand;

	public HandOfCards(int size){
		hand = new ArrayList<Card>(size);
		for(int i = 0; i<size; i++) {
			hand.add(null);
		}
	}

	@Override
	public String toString() {
		return "" + hand;
	}

	public HandOfCards(ArrayList<Card> _hand){
		hand = new ArrayList<Card>(_hand.size());
//		if(_hand.size() > 5) throw new IllegalArgumentException("The hand must have less than 5 cards");
		hand = _hand;
		sortCards();
	}
	
	public void sortCards(){
		Collections.sort(hand);
	}

	public void replaceCards(ArrayList<Card> cards, ArrayList<Integer> positions) {
    	for(int i = 0; i < positions.size(); i++){
			if(positions.get(i)>4 || positions.get(i)<0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.set(positions.get(i), cards.get(i));
    	}
    	sortCards();
    }
	
	public void replaceCards(ArrayList<Card> cards, int[] positions) {
    	for(int i = 0; i < positions.length; i++){
			if(positions[i]>4 || positions[i]<0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.set(positions[i],cards.get(i));
    	}
    	sortCards();
    }

	public void replaceCards(Card card, int position) {
		if(position>4 || position <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    	hand.set(position,card);
    	sortCards();
    }

    public void print() {
        System.out.println(hand);
    }
    
    public void discard(int[] positions) {
    	Arrays.sort(positions);
    	for(int i = 0; i < positions.length;i++){
			if(positions[i] > 4 || positions[i] <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.remove(positions[i]-i);
    	}
    }

	public void discard(int position) {
		if(position>4 || position <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    	hand.remove(position);
    }

	public void add(ArrayList<Card> cards) {
		if(hand.size() + cards.size() > 5) throw new IllegalArgumentException("The hand must have less than 5 cards");
		else{
			hand.addAll(cards);	
			sortCards();
		}
	}
	public void add(Card card) {
		if(hand.size() + 1 > 5) throw new IllegalArgumentException("The hand must have less than 5 cards");
		else{
			hand.add(card);
			sortCards();
		}
	}	
    public static void main(String[] args)
    { 
        DeckOfCards deckteste = new DeckOfCards("./files/card-file.txt");
        deckteste.reset();
        deckteste.shuffle();
        deckteste.print();
        HandOfCards mao = new HandOfCards(deckteste.deal(5));
        System.out.println(mao.hand);
        deckteste.print();
        
        int [] arrli = {0,1};
        
        mao.replaceCards(deckteste.deal(2),arrli);
        System.out.println(mao.hand);
		//mao.add(deckteste.deal(2));//to test exceptions
		mao.discard(arrli);
		System.out.println(mao.hand);
		mao.add(deckteste.deal(2));
		System.out.println(mao.hand);
		mao.discard(arrli);
		System.out.println(mao.hand);
		mao.add(deckteste.deal(1));
		System.out.println(mao.hand);
		mao.add(deckteste.deal(1));
		System.out.println(mao.hand);
		//deckteste.deal(-1);//to teste exceptions
		//Card exception = new Card(2,5);//to teste exceptions
    }
}
 