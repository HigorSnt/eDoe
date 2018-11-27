package controllers;

import aux.Validador;

public class ControllerGeral {
	
	private UsuarioController uc;
	private ItemController ic;
	private Validador validador;
	
	public ControllerGeral() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
		this.validador = new Validador();
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return this.uc.adicionaDoador(id, nome, email, celular, classe);
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

	public void adicionaDescritor(String descricao) {
		this.ic.adicionaDescritor(descricao);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, 
			int quantidade, String tags) {
		if (idDoador != null && !this.uc.contemUsuarioDoador(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		return this.ic.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags, 
				this.uc.getNome(idDoador) + "/" + idDoador);
	}
	
	public String exibeItem (int id, String idDoador) {
		if (idDoador != null && !this.uc.contemUsuarioDoador(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		return this.ic.exibeItem(id, idDoador);
	}

	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (idDoador != null && !this.uc.contemUsuarioDoador(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		return this.ic.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao (int id, String idDoador) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (idDoador != null && !this.uc.contemUsuarioDoador(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		this.ic.removeItemParaDoacao(id, idDoador);
	}

	public String listaDescritorDeItensParaDoacao() {
		return this.ic.listaDescritorDeItensParaDoacao();
	}

	public String listaItensParaDoacao() {
		return this.ic.listaItensParaDoacao();
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		
		return this.ic.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
}
