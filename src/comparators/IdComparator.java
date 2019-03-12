package comparators;

import java.util.Comparator;

import models.Item;

/**
 * Compara os Itens pelo id.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS      - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS      - 118110774
 *
 */
public class IdComparator implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		return Integer.compare(o1.getId(), o2.getId());
	}

}
