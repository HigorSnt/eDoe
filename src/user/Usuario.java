package user;

import aux.Validador;

public abstract class Usuario {
	
	private final String ERRONOME = "Entrada invalida: nome nao pode ser vazio ou nulo.";
	private final String ERROEMAIL = "Entrada invalida: email nao pode ser vazio ou nulo.";
	private final String ERROCELULAR = "Entrada invalida: celular nao pode ser vazio ou nulo.";
	private final String ERROCLASSE = "Entrada invalida: classe nao pode ser vazia ou nula.";
	private final String ERROOPCAOCLASSE = "Entrada invalida: opcao de classe invalida.";
	private final String ERROID = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	
	protected String id;
	protected String nome;
	protected String celular;
	protected String email;
	protected String classe;
	protected boolean ehReceptor;
	private Validador validator = new Validador();
	
	protected Usuario(String id, String nome, String celular, String email, String classe, boolean ehReceptor) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validator.validaDado(nome, this.ERRONOME);
		this.nome = nome;
	}

	public void setCelular(String celular) {
		this.validator.validaDado(celular, this.ERROCELULAR);
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.validator.validaDado(email, this.ERROEMAIL);
		this.email = email;
	}
	
}
