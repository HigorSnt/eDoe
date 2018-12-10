package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.Validador;

/**
 * Classe que representa um Item, onde cada um possui uma descricao, um identificador unico,
 * uma certa quantidade, e tags que o definem.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class Item implements Serializable {

	//private static final long serialVersionUID = -1866678795534774606L;
	private String descricao;
	private List<String> tags;
	private int quantidade;
	private int id;
	private Validador validador = new Validador();

	/**
	 * Construtor de item, inicializa a lista e adiciona todas as tags, bem como
	 * inicializa os outros atributos.
	 * 
	 * @param descricao 	e a descricao do item.
	 * @param quantidade 	e a quantidade de itens.
	 * @param tags 			e um resumo do objeto.
	 * @param id 			e o identificador unico do item.
	 * 
	 */
	public Item(String descricao, int quantidade, String[] tags, int id) {
		this.validador.validaDescritor(descricao);
		this.validador.validaQuantidade(quantidade);
		
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.id = id;
		this.tags = new ArrayList<String>();
		
		for (String tag : tags) {
			this.tags.add(tag);
		}
	}
	
	/**
	 * altera a quantidade de itens disponiveis.
	 * 
	 * @param novaQuantidade é o novo valor que quantidade assumira.
	 * 
	 */
	public void setQuantidade(int novaQuantidade) {
		this.validador.validaQuantidade(novaQuantidade);
		
		this.quantidade = novaQuantidade;
	}
	
	/**
	 * adiciona as tags passadas como parametro na lista de tags que e contida no
	 * objeto.
	 * 
	 * @param tags e um resumo do objeto
	 */
	public void setTags(String[] tags) {
		this.tags = new ArrayList<>();
		
		for (String tag : tags) {
			this.tags.add(tag);
		}
	}
	
	/**
	 *
	 * @return Retorna as tags do item.
	 * 
	 */
	public List<String> getTags() {
		return this.tags;
	}
	
	/**
	 * 
	 * @return Retorna a quantidade do item.
	 * 
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	/**
	 * 
	 * @return Retorna o id do item.
	 * 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @return Retorna a descricao do item.
	 * 
	 */
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + this.tags.toString() + ", quantidade: " + this.quantidade; 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + descricao.hashCode();
		result = prime * result + tags.hashCode();
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
		Item other = (Item) obj;
		if (!descricao.equals(other.descricao))
			return false;
		if (!tags.equals(other.tags))
			return false;
		return true;
	}

}