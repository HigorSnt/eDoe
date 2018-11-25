package controllers;

public class ControllerGeral {
	
	private UsuarioController uc;
	private ItemController ic;
	
	public ControllerGeral() {
		this.uc = new UsuarioController();
		this.ic = new ItemController();
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
		
		return this.ic.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem (int id, String idDoador) {
		if (idDoador != null && !this.uc.contemUsuarioDoador(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		return this.ic.exibeItem(id, idDoador);
	}
	
}
