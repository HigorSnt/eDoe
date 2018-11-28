package models;

public class UsuarioReceptor extends Usuario{

	public UsuarioReceptor(String id, String nome, String email, String celular, String classe) {
		super(id, nome, email, celular, classe);
	}

	public String toString() {
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + ", status: receptor";
	}
}
