package comparators;

import java.util.Comparator;

import models.Doacao;

public class DataComparator implements Comparator<Doacao> {

	@Override
	public int compare(Doacao d1, Doacao d2) {
		if (d1.getData().equals(d2.getData())) {
			return d1.getDescricaoItemDoado().compareTo(d2.getDescricaoItemDoado());
		}
		return d1.getData().compareTo(d2.getData());
	}

}
