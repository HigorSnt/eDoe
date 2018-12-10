package comparators;

import java.util.Comparator;

import models.Item;

/**
 * Compara os Itens pela descricao.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class DescricaoComparator implements Comparator<Item> {

	@Override
	public int compare(Item i1, Item i2) {
		return i1.getDescricao().compareTo(i2.getDescricao());
	}

}
