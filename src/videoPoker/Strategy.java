package videoPoker;

import java.util.ArrayList;

import playingCards.HandOfCards;

public interface Strategy {

	public ArrayList<Integer> getOptimalStrategy(HandOfCards hand);

}