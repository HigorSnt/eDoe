package models;

import aux.Validador;

public class Usuario {
	
	private final String ERRONOME = "Entrada invalida: nome nao pode ser vazio ou nulo.";
	private final String ERROEMAIL = "Entrada invalida: email nao pode ser vazio ou nulo.";
	private final String ERROCELULAR = "Entrada invalida: celular nao pode ser vazio ou nulo.";
	private final String ERROCLASSE = "Entrada invalida: classe nao pode ser vazia ou nula.";
	private final String ERROOPCAOCLASSE = "Entrada invalida: opcao de classe invalida.";
	private final String ERROID = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	
	private String id;
	private String nome;
	private String celular;
	private String email;
	private String classe;
	private boolean ehReceptor;
	private Validador validator = new Validador();
	
	/**
	 * Constroi um novo usuario.
	 * 
	 * @param id CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome nome do usuario a ser cadastrado.
	 * @param email email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe classe do usuario a ser cadastrado.
	 * @param ehReceptor booleano que informa se o usuario e receptor ou doador.
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, boolean ehReceptor) {
		this.validator.validaDado(nome, this.ERRONOME);
		this.validator.validaDado(id, this.ERROID);
		this.validator.validaDado(celular, this.ERROCELULAR);
		this.validator.validaDado(email, this.ERROEMAIL);
		this.validator.validaDado(classe, this.ERROCLASSE);
		this.validator.validaClasse(classe, this.ERROOPCAOCLASSE);
		
		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.classe = classe;
		this.ehReceptor = ehReceptor;
	}

	/**
	 * retorna o nome do usuario.
	 * 
	 * @return nome do usuario.
	 */
	public String getNome() {
		return nome;
	}
	
	public boolean isEhReceptor() {
		return ehReceptor;
	}

	/**
	 * Seta um novo nome para o usuario.
	 * 
	 * @param nome nome do usuario.
	 */
	public void setNome(String nome) {
		this.validator.validaDado(nome, this.ERRONOME);
		this.nome = nome;
	}
	
	/**
	 * Seta um novo celular para o usuario.
	 * 
	 * @param celular celular do usuario.
	 */
	public void setCelular(String celular) {
		this.validator.validaDado(celular, this.ERROCELULAR);
		this.celular = celular;
	}
	
	/**
	 * Seta um novo email para o usuario.
	 * 
	 * @param email email do usuario.
	 */
	public void setEmail(String email) {
		this.validator.validaDado(email, this.ERROEMAIL);
		this.email = email;
	}
	
	/**
	 * Retorna um String que corresponde ao usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE, STATUS: xxxxxx
	 */
	public String toString() {
		return this.nome + "/" + this.id + ", " + this.email + ", " + this.celular + ", status: " + (this.ehReceptor ? "receptor" : "doador");
	}
	
}
