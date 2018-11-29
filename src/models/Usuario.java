package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import aux.Validador;

public class Usuario {

	private final String ERRONOME = "Entrada invalida: nome nao pode ser vazio ou nulo.";
	private final String ERROEMAIL = "Entrada invalida: email nao pode ser vazio ou nulo.";
	private final String ERROCELULAR = "Entrada invalida: celular nao pode ser vazio ou nulo.";
	private final String ERROCLASSE = "Entrada invalida: classe nao pode ser vazia ou nula.";
	private final String ERROOPCAOCLASSE = "Entrada invalida: opcao de classe invalida.";
	private final String ERROID = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	
	private String id;
	private String nome;
	private String celular;
	private String email;
	private String classe;
	private boolean ehReceptor;
	private Validador validator = new Validador();
	private Map<String, List<Item>> itens;

	/**
	 * Constroi um novo usuario.
	 * 
	 * @param id         CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome       nome do usuario a ser cadastrado.
	 * @param email      email do usuario a ser cadastrado.
	 * @param celular    celular do usuario a ser cadastrado.
	 * @param classe     classe do usuario a ser cadastrado.
	 * @param ehReceptor booleano que informa se o usuario e receptor ou doador.
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, boolean ehReceptor) {
		this.validator.validaDado(nome, this.ERRONOME);
		this.validator.validaDado(id, this.ERROID);
		this.validator.validaDado(celular, this.ERROCELULAR);
		this.validator.validaDado(email, this.ERROEMAIL);
		this.validator.validaDado(classe, this.ERROCLASSE);
		this.validator.validaClasse(classe, this.ERROOPCAOCLASSE);
		this.itens = new LinkedHashMap<>();

		this.id = id;
		this.nome = nome;
		this.celular = celular;
		this.email = email;
		this.classe = classe;
		this.ehReceptor = ehReceptor;
	}

	/**
	 * retorna o nome do usuario.
	 * 
	 * @return nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	public boolean isEhReceptor() {
		return ehReceptor;
	}

	/**
	 * Seta um novo nome para o usuario.
	 * 
	 * @param nome nome do usuario.
	 */
	public void setNome(String nome) {
		this.validator.validaDado(nome, this.ERRONOME);
		this.nome = nome;
	}

	/**
	 * Seta um novo celular para o usuario.
	 * 
	 * @param celular celular do usuario.
	 */
	public void setCelular(String celular) {
		this.validator.validaDado(celular, this.ERROCELULAR);
		this.celular = celular;
	}

	/**
	 * Seta um novo email para o usuario.
	 * 
	 * @param email email do usuario.
	 */
	public void setEmail(String email) {
		this.validator.validaDado(email, this.ERROEMAIL);
		this.email = email;
	}

	/**
	 * Retorna um String que corresponde ao usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE,
	 *         STATUS: xxxxxx
	 */
	public String toString() {
		String status;
		if (ehReceptor) {
			status = ", status: receptor";
		} else {
			status = ", status: doador";
		}
		return this.getNome() + "/" + this.getId() + ", " + this.getEmail() + ", " + this.getCelular() + status;
	}

	public String getId() {
		return this.id;
	}

	public String getCelular() {
		return this.celular;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + celular.hashCode();
		result = prime * result + email.hashCode();
		result = prime * result + nome.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (!celular.equals(other.celular))
			return false;
		if (!email.equals(other.email))
			return false;
		if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public List<Item> procuraItensComNome(String descricao) {
		List<Item> itens = new ArrayList<>();

		for (String desc : this.itens.keySet()) {
			boolean descricaoPresente = false;
			String[] palavrasChaves = desc.split(" ");
			for (String palavra : palavrasChaves) {
				if (palavra.equals(descricao)) {
					descricaoPresente = true;
				}
			}
			if (descricaoPresente) {
				itens.addAll(this.itens.get(desc));
			}

		}

		return itens;
	}

	public List<Item> pegaTodosOsItens() {
		List<Item> itens = new ArrayList<>();
		for (String descricao : this.itens.keySet()) {
			itens.addAll(this.itens.get(descricao));
		}

		return itens;
	}

	public String representacaoParaListagemDeDoacao() {
		return "doador: " + this.getNome() + "/" + this.getId();
	}

	public String atualizaItemParaDoacao(int id, int quantidade, String tags) {
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					if (quantidade > 0) {
						item.setQuantidade(quantidade);
					}
					if (tags != null && !(tags.trim().equals(""))) {
						item.setTags(tags.split(","));
					}
					return item.toString();
				}
			}
		}

		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

	public String removeItemParaDoacao(int id) throws Exception {
		if (this.itens.isEmpty()) {
			throw new Exception("O Usuario nao possui itens cadastrados.");
		}
		
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					int qtd = item.getQuantidade();
					this.itens.get(descricao).remove(item);
					return descricao + "," + qtd;
				}
			}
		}
		throw new Exception("Item nao encontrado: " + id + ".");
	}

	public String exibeItem(int id) {
		for (String descricao : this.itens.keySet()) {
			for (Item item : this.itens.get(descricao)) {
				if (item.getId() == id) {
					return item.toString();
				}
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");

	}

	public int adicionaItemParaDoacao(String descricaoItem, int quantidade, String tags, int cont) {
		Item aSerAdcionado = new Item(descricaoItem, quantidade, tags.split(","), cont);
		if (this.itens.containsKey(descricaoItem)) {
			boolean achouItemIgual = false;
			for (Item i : this.itens.get(descricaoItem)) {
				if (i.equals(aSerAdcionado)) {
					i.setQuantidade(quantidade);
					achouItemIgual = true;
				}
			}
			if (!achouItemIgual) {
				this.itens.get(descricaoItem).add(aSerAdcionado);
			}
		} else {
			this.itens.put(descricaoItem, new ArrayList<>());
			this.itens.get(descricaoItem).add(new Item(descricaoItem, quantidade, tags.split(","), cont));

		}

		return cont;
	}

	public String getDescricaoItem(int id){
		for (List<Item> valor : this.itens.values()) {
			for (Item item : valor) {
				if (item.getId() == id) {
					return item.getDescricao();
				}
			}
		}
		throw new IllegalArgumentException();
	}
}
