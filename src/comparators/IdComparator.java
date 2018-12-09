package comparators;

import java.util.Comparator;

import models.Item;

public class IdComparator implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		if(o1.getId() > o2.getId()) {
			return 1;
		}
		return -1;
	}

}
