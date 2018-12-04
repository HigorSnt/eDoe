package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import util.Validador;

/**
 * Classe que tem como propósito ser uma entidade abstrata que representa um
 * item.
 * 
 * @author kyouma
 *
 */
public class Item {

	private String descricao;
	private List<String> tags;
	private int quantidade;
	private int id;
	private String data;
	private Validador validador = new Validador();

	/**
	 * Construtor de item, inicializa a lista e adciona todas as tags, bem como
	 * inicializa os outros atributos.
	 * 
	 * @param descricao e a descricao do item
	 * @param quantidade e a quantidade de itens
	 * @param tags e um resumo do objeto
	 * @param id e o id unico do item
	 */
	public Item(String descricao, int quantidade, String[] tags, int id) {
		this.validador.validaDescritor(descricao);
		this.validador.validaQuantidade(quantidade);
		
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.id = id;
		//this.data = data;
		this.tags = new ArrayList<String>();
		
		for (String tag : tags) {
			this.tags.add(tag);
		}
	}

	/**
	 * retorna uma representação em String do item que ele representa
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + this.converteTagsEmString() + ", quantidade: " + this.quantidade; 
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * altera a quantidade de itens disponiveis.
	 * 
	 * @param novaQuantidade é o novo valor que quantidade assumirá.
	 */
	public void setQuantidade(int novaQuantidade) {
		this.validador.validaQuantidade(novaQuantidade);
		
		this.quantidade = novaQuantidade;
	}
	
	/**
	 * adiciona as tags passadas como parametro na lista de tags que e contida no
	 * objeto.
	 * 
	 * @param tags é um resumo do objeto
	 */
	public void setTags(String[] tags) {
		this.tags = new ArrayList<>();
		
		for (String tag : tags) {
			this.tags.add(tag);
		}
	}

	private String converteTagsEmString() {
		return "[" + this.tags.stream().map(t -> t.toString()).collect(Collectors.joining(", ")) + "]";
	}
	
	public int getQuantidade() {
		return this.quantidade;
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