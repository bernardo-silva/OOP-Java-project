package videopoker;

public class DistanceToFlush implements HandRule {
	int distance;
	
	public DistanceToFlush(int dist) {
		distance = dist;
	}

	@Override
	public Boolean check(Object hand) {
		return (hand.distanceToFlush == distance);
		return null;
	}

}
