package User;

public abstract class Usuario {
	protected String id;
	protected String nome;
	protected String celular;
	protected String email;
	protected String classe;
	protected boolean ehReceptor;
	
	protected Usuario(String id, String nome, String celular, String email, String classe, boolean ehReceptor) {
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
		this.nome = nome;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
