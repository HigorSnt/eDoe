package models;

import java.util.List;

public class ItemAvaliado implements Comparable<ItemAvaliado> {

	private Item itemAvaliado;
	private Item itemNecessario;
	private int pontuacao;

	public ItemAvaliado(Item itemAvaliado, Item itemNecessario) {
		this.itemAvaliado = itemAvaliado;
		this.itemNecessario = itemNecessario;
		this.pontuacao = this.calculaPontuacao(itemAvaliado.getTags(), this.itemNecessario.getTags());

	}

	public String toString() {
		return this.itemAvaliado.toString();// + "pontuacao: " + this.pontuacao;
	}

	private int calculaPontuacao(List<String> tagsItemAvaliado, List<String> tagsItemNecessario) {
		int pontuacao = 20;
		for (int i = 0; i < tagsItemAvaliado.size(); i++) {
			for (int j = 0; j < tagsItemNecessario.size(); j++) {
				if (tagsItemAvaliado.get(i).toLowerCase().equals(tagsItemNecessario.get(j).toLowerCase())) {
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
		if (this.pontuacao - o.getPontuacao() == 0) {
			return this.itemAvaliado.getId() - o.getId();
		}else {
			return o.getPontuacao() - this.pontuacao;
		}
		//return this.pontuacao - o.getPontuacao();
	}
}
