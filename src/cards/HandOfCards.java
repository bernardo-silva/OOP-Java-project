package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;

public class HandOfCards {
	protected ArrayList<Card> hand;
	private LinkedHashSet<Integer> uniqueSuits;
	private LinkedHashSet<Integer> uniqueFaces;
	private int[] cardFrequencies;

	private int cardDistances;

	public HandOfCards(ArrayList<Card> _hand){
//		hand = new ArrayList<Card>(_hand.size());
//		if(_hand.size() > 5) throw new IllegalArgumentException("The hand must have less than 5 cards");
		hand = _hand;
		uniqueSuits = new LinkedHashSet<Integer>(5);
		uniqueFaces = new LinkedHashSet<Integer>(5);
		cardFrequencies = new int[15];
		sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public HandOfCards(int size){
		hand = new ArrayList<Card>(size);
		for(int i = 0; i<size; i++) {
			hand.add(null);
		}
		uniqueSuits = new LinkedHashSet<Integer>();
		uniqueFaces = new LinkedHashSet<Integer>();
		cardFrequencies = new int[15];
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
	
	private void countFrequencies() {
		Arrays.fill(cardFrequencies, 0);
		for(Card card : hand) {
			cardFrequencies[card.getFace()]++;
		}
	}
	private void countUniqueFacesSuits() {
		for(Card card : hand) {
			uniqueSuits.add(card.getSuit());
			uniqueFaces.add(card.getFace());
		}
	}

	public void discard(int position) {
		if(position>4 || position <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    	hand.remove(position);
    }
	public void discard(int[] positions) {
    	Arrays.sort(positions);
    	for(int i = 0; i < positions.length;i++){
			if(positions[i] > 4 || positions[i] <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.remove(positions[i]-i);
    	}
    }
	public int getCardDistances() {
		return cardDistances;
	}
	public int[] getFrequencies() {
		return cardFrequencies;
	}
	public int getLowCard() {
		return hand.get(0).getFace();
	}

	public int getUniqueFaces() {
		return uniqueFaces.size();
	}
	
	public int getUniqueSuits() {
		System.out.println("AAA" + uniqueSuits);
		return uniqueSuits.size();
	}

	private int measureCardDistances() {
		int distance = 0;
		for(int i=1; i<hand.size(); i++) {
			distance += hand.get(i).getFace() - hand.get(i-1).getFace();
		}
		return distance;
	}

    public void print() {
        System.out.println(hand);
    }
    
    public void replaceCards(ArrayList<Card> cards, ArrayList<Integer> positions) {
    	for(int i = 0; i < positions.size(); i++){
			if(positions.get(i)>4 || positions.get(i)<0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.set(positions.get(i), cards.get(i));
    	}
    	sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
    }

	public void replaceCards(ArrayList<Card> cards, int[] positions) {
    	for(int i = 0; i < positions.length; i++){
			if(positions[i]>4 || positions[i]<0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    		hand.set(positions[i],cards.get(i));
    	}
    	sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
	}

	public void replaceCards(Card card, int position) {
		if(position>4 || position <0)throw new IllegalArgumentException("position is 0 for the first card and 4 for the last.");
    	hand.set(position,card);
    	sortCards();
		countUniqueFacesSuits();
		countFrequencies();
		cardDistances = measureCardDistances();
    }
	public void sortCards(){
		Collections.sort(hand);
	}	
    @Override
	public String toString() {
		return "" + hand;
	}
}
 