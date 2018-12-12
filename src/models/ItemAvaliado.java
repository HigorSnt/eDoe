package models;

import java.io.Serializable;
import java.util.List;

/**
 * Entidade usada para representar o item avaliado com uma pontuação referente, e com um comparator para ordenar
 * de acordo com a pontuação.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 * 
 */
public class ItemAvaliado implements Comparable<ItemAvaliado>, Serializable {

	
	private static final long serialVersionUID = 4597676319071710958L;
	private Item itemAvaliado;
	private Item itemNecessario;
	private int pontuacao;

	/**
	 * Construtor de item avaliado, para tal, ele recebe o item que vai ser avaliado e o item necessario que serve
	 * como parametro como comparação.
	 * 
	 * @param itemAvaliado   e o item avaliado.
	 * @param itemNecessario e o item necessario.
	 *  
	 */
	public ItemAvaliado(Item itemAvaliado, Item itemNecessario) {
		this.itemAvaliado = itemAvaliado;
		this.itemNecessario = itemNecessario;
		this.pontuacao = this.calculaPontuacao(itemAvaliado.getTags(), this.itemNecessario.getTags());
	}

	/**
	 * Metodo que é usado no construtor para calcular a pontuacao a partir das tags do item
	 * avaliado e do item necessario.
	 * 
	 * @param tagsItemAvaliado   as tags do item avaliado.
	 * @param tagsItemNecessario as tags do item necessario.
	 * 
	 * @return a pontuacao.
	 * 
	 */
	private int calculaPontuacao(List<String> tagsItemAvaliado, List<String> tagsItemNecessario) {
		int pontuacao = 20;
		
		for (int i = 0; i < tagsItemAvaliado.size(); i++) {
			for (int j = 0; j < tagsItemNecessario.size(); j++) {
				if (tagsItemAvaliado.get(i).equalsIgnoreCase(tagsItemNecessario.get(j))) {
					if (i == j) {
						pontuacao += 10;
					} else {
						pontuacao += 5;
					}
				}
			}

		}

		return pontuacao;
	}

	/**
	 * 
	 * @return retorna o id do item avaliado.
	 * 
	 */
	public int getId() {
		return this.itemAvaliado.getId();
	}
	
	/**
	 * 
	 * @return retorna a pontuacao.
	 * 
	 */
	public int getPontuacao() {
		return this.pontuacao;
	}
	
	/**
	 * retorna a representação do item avaliado.
	 */
	@Override
	public String toString() {
		return this.itemAvaliado.toString();
	}

	/**
	 * compareTo, usado para ordenar da maior pontuacao para a menor.
	 */
	@Override
	public int compareTo(ItemAvaliado o) {
		if (this.pontuacao - o.getPontuacao() == 0) {
			return Integer.compare(this.itemAvaliado.getId(), o.getId());
		}
		return Integer.compare(o.getPontuacao(), this.pontuacao);
	}
}
