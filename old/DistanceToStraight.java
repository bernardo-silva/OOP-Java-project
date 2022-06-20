package videopoker.old;

public class DistanceToStraight implements HandRule {
	int distance;
	
	public DistanceToStraight(int dist) {
		distance = dist;
	}

	@Override
	public Boolean check(Object hand) {
		return (hand.distanceToStraight == distance);
		return null;
	}

}
