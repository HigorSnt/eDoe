package facade;

import controllers.ControllerGeral;
import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerGeral cg = new ControllerGeral();

	public static void main(String[] args) {
		args = new String[] {"facade.Facade", "acceptance_tests/use_case_1.txt"};
		EasyAccept.main(args);
	}
	
	public String adicionaDoador (String id, String nome, String email, String celular, String classe) {
		return this.cg.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public void lerReceptores (String caminho) {
		this.cg.lerReceptores(caminho);
	}
	
	public String pesquisaUsuarioPorId (String id) {
		return this.cg.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome (String nome) {
		return this.cg.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario (String id, String nome, String email, String celular) {
		return this.cg.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario (String id) {
		this.cg.removeUsuario(id);
	}

}
