package main;

import controllers.UsuarioController;
import easyaccept.EasyAccept;

public class Facade {
	
	private UsuarioController cg = new UsuarioController();

	public static void main(String[] args) {
		args = new String[] {"main.Facade", "acceptance_tests/use_case_1.txt", 
				"acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt", "acceptance_tests/use_case_4.txt", "acceptance_tests/use_case_5.txt"};
		EasyAccept.main(args);
	}
	///us1
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
		return this.cg.alteraDadosDoador(id, nome, email, celular);
	}
	
	public void removeUsuario (String id) {
		this.cg.removeUsuario(id);
	}
	/// fim da us1
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return this.cg.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	public String atualizaItemNecessario(String idReceptor, int id, int quantidade, String tags) {
		return this.cg.atualizaItemNecessario(id, idReceptor, quantidade, tags);
	}
	public String match(String idReceptor, int idItemNecessario) {
		return this.cg.match(idReceptor, idItemNecessario);
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return this.cg.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	public void adicionaDescritor (String descricao) {
		this.cg.adicionaDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return this.cg.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem (int id, String idDoador) {
		return this.cg.exibeItem(id, idDoador);
	}
	
	public String atualizaItemParaDoacao (int id, String idDoador, int quantidade, String tags) {
		return this.cg.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao (int id, String idDoador) throws Exception {
		this.cg.removeItemParaDoacao(id, idDoador);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return this.cg.listaDescritorDeItensParaDoacao();
	}
	
	public String listaItensParaDoacao() {
		return this.cg.listaItensParaDoacao();
	}
	
	public String listaItensNecessarios() {
		return this.cg.listaItensNecessarios();
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) throws Exception {
		this.cg.removeItemNecessario(idReceptor, idItem);
	}

}