package eDoe;

import controllers.ControllerGeral;
import easyaccept.EasyAccept;

public class Facade {
	
	private ControllerGeral cg = new ControllerGeral();

	public static void main(String[] args) {
		args = new String[] {"eDoe.Facade", "acceptance_tests/use_case_1.txt", 
				"acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt"};
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
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return this.cg.pesquisaItemParaDoacaoPorDescricao(descricao);
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
	
	public void removeItemParaDoacao (int id, String idDoador) {
		this.cg.removeItemParaDoacao(id, idDoador);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return this.cg.listaDescritorDeItensParaDoacao();
	}
	
	public String listaItensParaDoacao() {
		return this.cg.listaItensParaDoacao();
	}

}
