package models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aux.Validador;

/**
 * Classe que tem como propósito ser uma entidade abstrata que representa um
 * item.
 * 
 * @author kyouma
 *
 */
public class Item {
	
	private final String ERRODESCRITOR = "Entrada invalida: descricao nao pode ser vazia ou nula.";
	private final String ERROVALOR = "Entrada invalida: quantidade deve ser maior que zero.";

	protected String descricao;
	protected List<String> tags;
	protected int quantidade;
	protected int id;
	private String data;
	private String etiqueta;
	private Validador validador = new Validador();

	/**
	 * Construtor de item, inicializa a lista e adciona todas as tags, bem como
	 * inicializa os outros atributos.
	 * 
	 * @param idPessoa
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @param id
	 */
	public Item(String descricao, int quantidade, String[] tags, int id) {
		this.validador.validaDado(descricao, ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALOR);
		
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
	
	public String getEtiqueta() {
		return etiqueta;
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
	 * @param novaQuantidade
	 */
	public void setQuantidade(int novaQuantidade) {
		this.validador.validaValorPositivo(novaQuantidade, this.ERROVALOR);
		
		this.quantidade = novaQuantidade;
	}
	
	/**
	 * adiciona as tags passadas como parametro na lista de tags que e contida no
	 * objeto.
	 * 
	 * @param tags
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}


}