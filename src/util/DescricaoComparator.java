package util;

import java.util.Comparator;

import models.Item;

public class DescricaoComparator implements Comparator<Item> {

	@Override
	public int compare(Item i1, Item i2) {
		return i1.getDescricao().compareTo(i2.getDescricao());
	}

}
