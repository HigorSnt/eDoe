package controllers;

import item.ItemController;
import user.UsuarioController;

public class ControllerGeral {
	
	private UsuarioController uc;
	private ItemController ic;
	
	public ControllerGeral() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.cadastraDoador(id, nome, email, celular, classe);
	}
	
	public void lerReceptores(String caminho) {
		this.uc.lerReceptores(caminho);
	}

	public String pesquisaUsuarioPorId(String id) {
		return this.uc.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return this.uc.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return this.uc.alteraDados(id, nome, email, celular);
	}

	public void removeUsuario(String id) {
		this.uc.removeUsuario(id);
	}
}
