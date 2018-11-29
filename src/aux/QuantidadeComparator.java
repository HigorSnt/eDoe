package aux;

import java.util.Comparator;

import models.Item;

public class QuantidadeComparator implements Comparator<Item> {
	@Override
	public int compare(Item i1, Item i2) {
		if (i1.getQuantidade() == i2.getQuantidade()) {
			return i1.getDescricao().compareTo(i2.getDescricao());
		}
		if(i1.getQuantidade() > i2.getQuantidade()) {
			return -1;
		}
		return 1;
	}


}
