package models;

import java.util.List;

public class ItemAvaliado implements Comparable<ItemAvaliado>{

	private Item itemAvaliado;
	private Item itemNecessario;
	private int pontuacao;

	public ItemAvaliado(Item itemAvaliado, Item itemNecessario) {
		this.itemAvaliado = itemAvaliado;
		this.itemNecessario = itemNecessario;
		this.pontuacao = this.calculaPontuacao();

	}

	private int calculaPontuacao() {
		int pontuacao = 20;
		List<String> tagsItemNecessario = this.itemNecessario.getTags();
		List<String> tagsItemAvaliado = this.itemAvaliado.getTags();
		for (int i = 0; i < tagsItemAvaliado.size(); i++) {
			for (int j = 0; i < tagsItemNecessario.size(); i++) {
				if (tagsItemAvaliado.get(i).toLowerCase() == tagsItemNecessario.get(j).toLowerCase()) {
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

	public int getId() {
		return this.itemAvaliado.getId();
	}
	
	public int getPontuacao() {
		return this.pontuacao;
	}
	@Override
	public int compareTo(ItemAvaliado o) {
		return this.pontuacao - o.getPontuacao();
	}
}
