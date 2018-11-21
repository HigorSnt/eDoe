package item;

import java.util.ArrayList;
import java.util.List;

import Aux.Validator;

/**
 * Classe que tem como propósito ser uma entidade abstrata que representa um
 * item.
 * 
 * @author kyouma
 *
 */
public abstract class Item {

	protected String descricao;
	protected List<String> tags;
	protected int quantidade;
	protected String idPessoa;
	protected int id;
	private String data;
	protected String nomePessoa;

	/**
	 * Construtor de item, inicializa a lista e adciona todas as tags, bem como
	 * inicializa os outros atributos.
	 * 
	 * @param idPessoa
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @param id
	 * @param nomePessoa
	 */
	public Item(String idPessoa, String descricao, int quantidade, String[] tags, int id, String nomePessoa) {
		Validator validador = new Validator();
		String err = "Entrada invalida: ";
		validador.validaDado(idPessoa, err + "id do usuario nao pode ser vazio ou nulo.");
		validador.validaDado(nomePessoa, err + "nome do usuario nao pode ser vazio ou nulo.");
		validador.validaValorPositivo(id, err + "id do item nao pode ser negativo.");
		this.idPessoa = idPessoa;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.nomePessoa = nomePessoa;
		this.id = id;
		this.data = data;
		this.tags = new ArrayList<String>();
		for (String tag : tags) {
			this.tags.add(tag);
		}
	}

	/**
	 * so definindo a presença do toString();
	 */
	public abstract String toString();

	/**
	 * altera a quantidade de itens disponíveis.
	 * 
	 * @param novaQuantidade
	 */
	public void setQuantidade(int novaQuantidade) {
		this.quantidade = novaQuantidade;
	}

	/**
	 * adciona as tags passadas como parametro na lista de tags que é contida no
	 * objeto.
	 * 
	 * @param tags
	 */
	public void setTags(String[] tags) {
		for (String tag : tags) {
			if (!this.tags.contains(tag)) {
				this.tags.add(tag);
			}
		}
	}

	/**
	 * retorna o nome da pessoa que doou o item.
	 * 
	 * @return
	 */
	public String getPessoa() {
		return this.nomePessoa;
	}

	protected String converteTagsEmString() {
		String saida = "[";
		
		for (String tag: this.tags) {
			saida += tag + ",";
		}
		saida += "]";
		return saida;
	}

}