package util;

import models.Item;

/**
 * Classe que realiza todas as validações de dados passados para o sistema.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class Validador {

	/**
	 * Valida o nome de um usuario.
	 * 
	 * @param nome e o nome de um usuario.
	 * 
	 */
	public static void validaNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o id de um usuario.
	 * 
	 * @param id e o id de um usuario.
	 * 
	 */
	public static void validaId (String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o celular de um usuario.
	 * 
	 * @param celular e o celular de um usuario.
	 * 
	 */
	public static void validaCelular (String celular) {
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o email de um usuario.
	 * 
	 * @param email e o email de um usuario.
	 * 
	 */
	public static void validaEmail(String email) {
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o texto de uma pesquisa de itens feita.
	 * 
	 * @param texto e a descricao passada que interessa na pesquisa.
	 * 
	 */
	public static void validaPesquisa (String texto) {
		if (texto == null || texto.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
	}
	
	/**
	 * Valida o cadastro de um usuario.
	 * 
	 * @param id e o id de um usuario.
	 * @param nome e o nome de um usuario.
	 * @param email e o email de um usuario.
	 * @param celular e o celular de um usario.
	 * 
	 */
	public static void validaCadastro(String id, String nome, String email, String celular) {
		validaNome(nome);
		validaId(id);
		validaCelular(celular);
		validaEmail(email);
	}
	
	/**
	 * Valida a descricao de um item.
	 * 
	 * @param descricao e uma descricao de item. 
	 * 
	 */
	public static void validaDescritor (String descricao) {
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
	}
	
	/**
	 * Verifica se o valor passado e negativo.
	 * 
	 * @param valor Informacao que necessita de validacao.
	 * 
	 */
	public static void validaIdItem (int valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
	}
	
	/**
	 * Valida a quantidade de um item.
	 * 
	 * @param quantidade é a quantidade de um item.
	 * 
	 */
	public static void validaQuantidade (int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}
	
	/**
	 * Valida uma data passada onde ocorreu uma doacao.
	 *
	 * @param data e a data que necessita ser verificada se foi passada como nula ou vazia.
	 * 
	 */
	public static void validaData(String data) {
		if(data == null || data.equals("")) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}
	}
	
	public static void validaItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Entrada invalida: item não pode ser nulo.");
		}
	}

}
