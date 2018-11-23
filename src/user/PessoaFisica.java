package user;

public class PessoaFisica extends Usuario{
	
	protected PessoaFisica(String id, String nome, String celular, String email, String classe, boolean ehReceptor) {
		super(id, nome, celular, email, classe, ehReceptor);
	}
	
	private String formataCPF() {
		StringBuilder str = new StringBuilder(super.id);
		str.insert(3, ".");
		str.insert(7, ".");
		str.insert(11, "-");
		return str.toString();
	}
	
	public String toString() {
		return super.nome + "/" + formataCPF() + ", " + super.celular + ", " + super.email + ", status: " + (super.ehReceptor ? "receptor" : "doador");
	}
}
