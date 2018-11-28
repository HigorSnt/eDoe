package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDoador extends Usuario {
	private Map<String, List<Item>> itens;

	public UsuarioDoador(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
		this.itens = new LinkedHashMap<>();

	}
	
	public String getDescricaoItem(int id){
		for (List<Item> valor : this.itens.values()) {
			for (Item item : valor) {
				if (item.getId() == id) {
					return item.getDescricao();
				}
			}
		}
		throw new IllegalArgumentException();
	}
	
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + ", status: doador";
	}

	public List<Item> procuraItensComNome(String descricao) {
		List<Item> itens = new ArrayList<>();

		for (String desc : this.itens.keySet()) {
			boolean descricaoPresente = false;
			String[] palavrasChaves = desc.split(" ");
			for (String palavra : palavrasChaves) {
				if (palavra.equals(descricao)) {
					descricaoPresente = true;
				}
			}
			if (descricaoPresente) {
				itens.addAll(this.itens.get(desc));
			}

		}

		return itens;
	}

	public List<Item> pegaTodosOsItens() {
		List<Item> itens = new ArrayList<>();
		for (Item item : itens) {
			itens.add(item);
		}

		return itens;
	}

	public String representacaoParaListagemDeDoacao() {
		return this.getNome() + "/" + this.getId();
	}

	public String atualizaItemParaDoacao(int id, int quantidade, String tags) {
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					if (quantidade > 0) {
						item.setQuantidade(quantidade);
					}
					if (tags != null && !(tags.trim().equals(""))) {
						item.setTags(tags.split(","));
					}
					return item.toString();
				}
			}
		}

		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	public void removeItemParaDoacao(int id) {
		if (this.itens.size() == 0) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					this.itens.get(descricao).remove(item);
					return;
				}
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	public String exibeItem(int id) {
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					return item.toString();
				}
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	public int adicionaItemParaDoacao(String descricaoItem, int quantidade, String tags, int cont) {
		Item itemNovo = new Item(descricaoItem, quantidade, tags.split(","), cont);
		
		if (this.itens.containsKey(descricaoItem)) {
			for (Item item : this.itens.get(descricaoItem)) {
				if (item.converteTagsEmString().equals(itemNovo.converteTagsEmString())) {
					item.setQuantidade(quantidade);
					return item.getId();
				}
			}
			this.itens.get(descricaoItem).add(itemNovo);
		}else {
			this.itens.put(descricaoItem, new ArrayList<>()) ;
			this.itens.get(descricaoItem).add(new Item(descricaoItem, quantidade, tags.split(","), cont));

		}
			
		return cont;
	}
}
