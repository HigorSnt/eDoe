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
public abstract class Item {
	
	private final String ERRODESCRITOR = "Entrada invalida: descricao nao pode ser vazia ou nula.";
	private final String ERROVALOR = "Entrada invalida: quantidade deve ser maior que zero.";

	protected String descricao;
	protected List<String> tags;
	protected int quantidade;
	protected int id;
	private String data;
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
	 * so definindo a presença do toString()
	 */
	public abstract String toString();
	
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

	public String converteTagsEmString() {
		return "[" + this.tags.stream().map(t -> t.toString()).collect(Collectors.joining(", ")) + "]";
	}

}
