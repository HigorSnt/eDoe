package facade;

import easyaccept.EasyAccept;

public class facade {
	
	ControllerGeral cg = new ControllerGeral();

	public static void main(String[] args) {
		/*args = new String[] {"controllers.Facade", "acceptance_tests/use_case_1.txt", "acceptance_tests/use_case_2.txt",
		"acceptance_tests/use_case_3.txt"};
		EasyAccept.main(args);*/
	}
	
	public String adicionaDoador (int id, String nome, String email, String celular, String classe) {
		return this.cg.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId (int id) {
		return this.cg.pesquisaUsuarioPorId(id);
	}

}
