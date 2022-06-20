package videopoker.old;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InRank implements HandRule {
	private int[] values;

	/**
	 * @param value
	 */
	public InRank(String[] valueStrings) {
		values = new int[valueStrings.length];
		for(int i=0; i<valueStrings.length; i++) {
			values[i] = Integer.parseInt(valueStrings[i]);
		}
	}

	@Override
	public Boolean check(Object hand) {
		ArrayList<Integer> checked = new ArrayList<Integer>();
		ArrayList<Integer> frequencies = new ArrayList<>(hand.ranks.values());
		int idx;
		
		for(int v : values) {
			idx = frequencies.indexOf(v);
			
			if(idx == -1) return false;
			
			if(checked.indexOf(idx) != -1) {
				idx = frequencies.subList(idx+1, frequencies.size()).indexOf(v);
				if(idx == -1) return false;
			}
		}
		return true;
	}

}

