package videopoker;

import java.util.ArrayList;

import playingCards.HandOfCards;

public interface Strategy {

	ArrayList<Integer> getOptimalStrategy(HandOfCards hand);

}