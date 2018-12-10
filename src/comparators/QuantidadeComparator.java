package comparators;

import java.util.Comparator;

import models.Item;

/**
 * Compara os Itens pela quantidade, se caso possuam quantidades iguais
 * e feita a comparacao pela descricao.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
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
