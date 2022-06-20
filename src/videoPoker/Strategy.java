package videoPoker;

import java.util.ArrayList;

import playingCards.HandOfCards;
/**
 * @author Bernardo Silva 
 * @author Miguel Madeira
 * @author Vicente Silvestre
 *
 * This interface allows the use of different strategies without changing anything else in the program.
 * Defines the methods to be overridden for each game strategy/variant.
 */
public interface Strategy {
	/**
	 * @return the positions of the cards to hold from @param hand following a given strategy
	 */
	public ArrayList<Integer> getOptimalStrategy(HandOfCards hand);

}