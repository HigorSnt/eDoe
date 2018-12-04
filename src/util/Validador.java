package util;

public class Validador {
	
	public void validaNome(String nome) {
		if (nome == null || nome.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaId (String id) {
		if (id == null || id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaCelular (String celular) {
		if (celular == null || celular.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaEmail(String email) {
		if (email == null || email.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaPesquisa (String texto) {
		if (texto == null || texto.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
	}
	
	public void validaCadastro(String id, String nome, String email, String celular) {
		this.validaNome(nome);
		this.validaId(id);
		this.validaCelular(celular);
		this.validaEmail(email);
	}
	
	public void validaDescritor (String descricao) {
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
	}
	
	/**
	 * Verifica se o valor passado eh negativo.
	 * 
	 * @param valor Informacao que necessita de validacao.
	 * @param err O erro que sera mostrado caso nao seja valido.
	 * 
	 */
	public void validaIdItem (int valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
	}
	
	public void validaQuantidade (int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		}
	}

}
