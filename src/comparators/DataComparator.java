package comparators;

import java.util.Comparator;

import models.Doacao;

/**
 * Compara as doacoes pela data, se caso as datas sejam iguais e feita 
 * a comparacao pela descricao o item.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class DataComparator implements Comparator<Doacao> {

	@Override
	public int compare(Doacao d1, Doacao d2) {
		if (d1.getData().equals(d2.getData())) {
			return d1.getDescricaoItemDoado().compareTo(d2.getDescricaoItemDoado());
		}
		return d1.getData().compareTo(d2.getData());
	}

}
