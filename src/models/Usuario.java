package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import enums.Classe;
import util.Validador;

public class Usuario {

	private String id;
	private String nome;
	private String celular;
	private String email;
	private Classe classe;
	private boolean receptor;
	private Validador validador = new Validador();
	private Map<String, List<Item>> itens;

	/**
	 * Constroi um novo usuario.
	 * 
	 * @param id         CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome       nome do usuario a ser cadastrado.
	 * @param email      email do usuario a ser cadastrado.
	 * @param celular    celular do usuario a ser cadastrado.
	 * @param classe     classe do usuario a ser cadastrado.
	 * @param receptor	 booleano que informa se o usuario e receptor ou doador.
	 * 
	 */
	public Usuario(String id, String nome, String email, String celular, Classe classe, boolean receptor) {
		this.validador.validaCadastro(id, nome, email, celular);

		this.itens = new LinkedHashMap<>();
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.classe = classe;
		this.receptor = receptor;
	}

	/**
	 * Retorna o nome do usuario.
	 * 
	 * @return nome do usuario.
	 * 
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Altera o nome do usuario.
	 * 
	 * @param novoNome novo nome do usuario.
	 * 
	 */
	public void setNome(String novoNome) {
		this.validador.validaNome(novoNome);
		
		this.nome = novoNome;
	}
	
	/**
	 * Informa se o usuario e receptor (true) ou doador (false).
	 * 
	 * @return um booleano informando se e receptor ou doador.
	 * 
	 */
	public boolean isReceptor() {
		return receptor;
	}
	
	/**
	 * @return retorna id do usuario.
	 * 
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return retorna o celular do usuario.
	 * 
	 */
	public String getCelular() {
		return this.celular;
	}
	
	/**
	 * Altera o celular do usuario.
	 * 
	 * @param novoCelular novo celular do usuario.
	 * 
	 */
	public void setCelular(String novoCelular) {
		this.validador.validaCelular(novoCelular);
		this.celular = novoCelular;
	}

	/**
	 * @return retorna o email do usuario.
	 * 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Altera o email do usuario.
	 * 
	 * @param novoEmail novo email do usuario.
	 * 
	 */
	public void setEmail(String novoEmail) {
		this.validador.validaEmail(novoEmail);
		this.email = novoEmail;
	}

	/**
	 * Adiciona item para descricao.
	 * 
	 * @param descricaoItem descricao do item.
	 * @param quantidade    quantidade do item.
	 * @param tags          tags do item.
	 * @param idItem      	id que representa o item.
	 * 
	 * @return retorna o id do item.
	 * 
	 */
	public int adicionaItemParaDoacao(String descricaoItem, int quantidade, String tags, int idItem) {
		Item novoItem = new Item(descricaoItem, quantidade, tags.split(","), idItem);
		
		if (this.itens.containsKey(descricaoItem)) {
			for (Item i : this.itens.get(descricaoItem)) {
				if (i.equals(novoItem)) {
					i.setQuantidade(quantidade);
					return i.getId();
				}
			}
			
			this.itens.get(descricaoItem).add(novoItem);
		} else {
			this.itens.put(descricaoItem, new ArrayList<>());
			this.itens.get(descricaoItem).add(novoItem);
		}

		return idItem;
	}

	/**
	 * Procura os itens com determina descricao.
	 * 
	 * @param descricao descricao procurada.
	 * 
	 * @return retorna todos itens com a descricao procurada.
	 */
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

	/**
	 * 
	 * @return retorna uma lista com todos itens.
	 * 
	 */
	public List<Item> pegaTodosOsItens() {
		List<Item> itens = new ArrayList<>();
		
		this.itens.values().forEach(valor -> itens.addAll(valor));
		
		return itens;
	}

	/**
	 * 
	 * @return retorna a representacao do usuario.
	 * 
	 */
	public String representacaoParaListagemDeDoacao() {
		return (this.isReceptor()) ? ("Receptor: " + this.getNome() + "/" + this.getId()) :
			("doador: " + this.getNome() + "/" + this.getId());
	}

	/**
	 * Atualiza um determinado item.
	 * 
	 * @param id         id do item.
	 * @param quantidade quantidade do item.
	 * @param tags       tags do item.
	 * 
	 * @return retorna a representacao do item.
	 * 
	 */
	public String atualizaItem(int id, int quantidade, String tags) {
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

	public Item pegaItem(int id) {
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					return item;
				}
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	/**
	 * Remove determinado item.
	 * 
	 * @param id id do item.
	 * 
	 * @return retorna a descricao e quantidade do item.
	 * 
	 */
	public String removeItem(int id) {
		if (this.itens.isEmpty()) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}

		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					int quantidade = item.getQuantidade();
					this.itens.get(descricao).remove(item);
					return descricao + "," + quantidade;
				}
			}
		}

		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	/**
	 * Exibe um item.
	 * 
	 * @param id id do item.
	 * @return 
	 * @return 
	 * 
	 * @return retorna representacao do item.
	 * 
	 */
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

	/**
	 * Retorna descricao do item.
	 * 
	 * @param id id do item.
	 * 
	 * @return retorna descricao do item.
	 * 
	 */
	public String getDescricaoItem(int id) {
		for (List<Item> valor : this.itens.values()) {
			for (Item item : valor) {
				if (item.getId() == id) {
					return item.getDescricao();
				}
			}
		}
		
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	public List<Item> pegaTodosOsItensComDescricao(String descricao) {
		if (this.itens.containsKey(descricao)) {
			return this.itens.get(descricao);
		} else
			return new ArrayList<>();
	}
	
	/**
	 * Retorna um String que corresponde ao usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, STATUS: xxxxxx.
	 * 
	 */
	@Override
	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular()
				+ (this.receptor ? ", status: receptor" : ", status: doador");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + celular.hashCode();
		result = prime * result + email.hashCode();
		result = prime * result + nome.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (!celular.equals(other.celular))
			return false;
		if (!email.equals(other.email))
			return false;
		if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
