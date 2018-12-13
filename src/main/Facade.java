package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.SistemaController;
import easyaccept.EasyAccept;

public class Facade {
	
	private SistemaController sisc = new SistemaController();

	public static void main(String[] args) {
		args = new String[] {
				"main.Facade", "acceptance_tests/use_case_1.txt", 
				"acceptance_tests/use_case_2.txt",
				"acceptance_tests/use_case_3.txt", 
				"acceptance_tests/use_case_4.txt", 
				"acceptance_tests/use_case_5.txt", 
				"acceptance_tests/use_case_6.txt",
				"acceptance_tests/use_case_7.txt"
				};
		EasyAccept.main(args);
	}
	
	public String adicionaDoador (String id, String nome, String email, String celular, String classe) {
		return this.sisc.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public void lerReceptores (String caminho) throws FileNotFoundException {
		this.sisc.lerReceptores(caminho);
	}
	
	public String pesquisaUsuarioPorId (String id) {
		return this.sisc.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome (String nome) {
		return this.sisc.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario (String id, String nome, String email, String celular) {
		return this.sisc.alteraDadosDoador(id, nome, email, celular);
	}
	
	public void removeUsuario (String id) {
		this.sisc.removeUsuario(id);
	}
	
	public void adicionaDescritor (String descricao) {
		this.sisc.adicionaDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return this.sisc.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem (int id, String idDoador) {
		return this.sisc.exibeItem(id, idDoador);
	}
	
	public String atualizaItemParaDoacao (int id, String idDoador, int quantidade, String tags) {
		return this.sisc.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao (int id, String idDoador) {
		this.sisc.removeItem(id, idDoador);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return this.sisc.listaDescritorDeItensParaDoacao();
	}
	
	public String listaItensParaDoacao() {
		return this.sisc.listaItensParaDoacao();
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return this.sisc.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return this.sisc.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	public String listaItensNecessarios() {
		return this.sisc.listaItensNecessarios();
	}
	
	public String atualizaItemNecessario(String idReceptor, int id, int quantidade, String tags) {
		return this.sisc.atualizaItemNecessario(id, idReceptor, quantidade, tags);
	}
	
	public void removeItemNecessario(String idReceptor, int idItem) {
		this.sisc.removeItem(idItem, idReceptor);
	}
	
	public String match(String idReceptor, int idItemNecessario) {
		return this.sisc.match(idReceptor, idItemNecessario);
	}
	
	public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
		return this.sisc.realizaDoacao(idItemNec, idItemDoado, data);
	}
	
	public String listaDoacoes() {
		return this.sisc.listaDoacoes();
	}
	
	public void finalizaSistema() throws IOException {
		this.sisc.finalizaSistema();
	}
	
	public void iniciaSistema() throws ClassNotFoundException, IOException {
		this.sisc.iniciaSistema();
	}

}