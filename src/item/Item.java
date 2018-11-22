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
	protected int id;
	private String data;

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
	public Item(String descricao, int quantidade, String[] tags, int id) {
		Validator validador = new Validator();
		String err = "Entrada invalida: ";
		validador.validaValorPositivo(id, err + "id do item nao pode ser negativo.");
		this.descricao = descricao;
		this.quantidade = quantidade;
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



	protected String converteTagsEmString() {
		String saida = "[";
		
		for (String tag: this.tags) {
			saida += tag + ",";
		}
		saida += "]";
		return saida;
	}

}
