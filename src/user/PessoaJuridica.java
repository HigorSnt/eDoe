package user;

public class PessoaJuridica extends Usuario{

	protected PessoaJuridica(String id, String nome, String celular, String email, String classe, boolean ehReceptor) {
		super(id, nome, celular, email, classe, ehReceptor);
	}
	
	private String formataCNPJ() {
		StringBuilder str = new StringBuilder(super.id);
		str.insert(2, ".");
		str.insert(6, ".");
		str.insert(10, "/");
		str.insert(15, "-");
		return str.toString();
	}
	
	public String toString() {
		return super.nome +"/"+formataCNPJ()+", "+super.celular+", "+super.email+", status: "+ (super.ehReceptor ? "receptor" : "doador");
	}
	
}
