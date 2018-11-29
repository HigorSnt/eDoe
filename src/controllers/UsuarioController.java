package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import aux.DescricaoComparator;
import aux.QuantidadeComparator;
import aux.Validador;
import models.Item;
import models.Usuario;

public class UsuarioController {
	private final String ERRONOME = "Entrada invalida: nome nao pode ser vazio ou nulo.";
	private final String ERROEMAIL = "Entrada invalida: email nao pode ser vazio ou nulo.";
	private final String ERROCELULAR = "Entrada invalida: celular nao pode ser vazio ou nulo.";
	private final String ERROCLASSE = "Entrada invalida: classe nao pode ser vazia ou nula.";
	private final String ERROOPCAOCLASSE = "Entrada invalida: opcao de classe invalida.";
	private final String ERROID = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	private final String ERROIDDOADOR = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	private final String ERRODESCRITOR = "Entrada invalida: descricao nao pode ser vazia ou nula.";

	private final String ERROVALORQTD = "Entrada invalida: quantidade deve ser maior que zero.";
	private final String ERROTEXTODEPESQUISA = "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.";
	private final String ERRODESCRICAOJAEXISTENTE = "Descritor de Item ja existente: ";
	
	private Map<String, Usuario> usuarios;
	private Validador validador;
	private Map<String, Integer> descricoes;
	private int cont;
	
	public UsuarioController() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.validador = new Validador();
		this.descricoes = new TreeMap<>();
		this.cont = 0;
	}

	/**
	 * Cadastra um novo usuario doador.
	 * 
	 * @param id      CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome    nome do usuario a ser cadastrado.
	 * @param email   email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe  classe do usuario a ser cadastrado.
	 * 
	 * @return o ID do usuario.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaDado(nome, this.ERRONOME);
		this.validador.validaDado(id, this.ERROID);
		this.validador.validaDado(celular, this.ERROCELULAR);
		this.validador.validaDado(email, this.ERROEMAIL);
		this.validador.validaDado(classe, this.ERROCLASSE);
		this.validador.validaClasse(classe, this.ERROOPCAOCLASSE);

		Usuario user = new Usuario(id, nome, email, celular, classe, false);
		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}

		this.usuarios.put(id, user);
		return id;
	}

	/**
	 * Cadastra um novo usuario receptor.
	 * 
	 * @param id      CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome    nome do usuario a ser cadastrado.
	 * @param email   email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe  classe do usuario a ser cadastrado.
	 * 
	 * @return o ID do usuario.
	 */
	public String adicionaReceptor(String id, String nome, String email, String celular, String classe) {
		this.validador.validaDado(nome, this.ERRONOME);
		this.validador.validaDado(id, this.ERROID);
		this.validador.validaDado(celular, this.ERROCELULAR);
		this.validador.validaDado(email, this.ERROEMAIL);
		this.validador.validaDado(classe, this.ERROCLASSE);
		this.validador.validaClasse(classe, this.ERROOPCAOCLASSE);

		Usuario user = new Usuario(id, nome, email, celular, classe, true);
		if (!(this.usuarios.containsKey(id))) {
			this.usuarios.put(id, user);
			return id;
		}
		throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}

	/**
	 * Ler os receptores apatir do caminho do arquivos onde estao armazenados.
	 * 
	 * @param caminho caminho do arquivo.
	 */
	public void lerReceptores(String caminho) {
		try {
			Scanner sc = new Scanner(new FileReader(caminho));
			sc.nextLine();
			while (sc.hasNextLine()) {
				String linha = sc.nextLine();
				String array[] = new String[4];
				array = linha.split(",");
				String id = array[0];
				String nome = array[1];
				String email = array[2];
				String celular = array[3];
				String classe = array[4];
				if (this.usuarios.containsKey(id)) {
					this.alteraDadosReceptor(id, nome, email, celular);
				} else {
					this.adicionaReceptor(id, nome, email, celular, classe);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Erro na leitura: caminho (" + caminho + ") não encontrado.");
		}
	}

	/**
	 * Imprime o toString do usario dado seu ID.
	 * 
	 * @param id identificacao do usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE,
	 *         STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaDado(id, this.ERROID);

		if (this.usuarios.containsKey(id)) {
			return this.usuarios.get(id).toString();
		} else if (this.usuarios.containsKey(id)) {
			return this.usuarios.get(id).toString();
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}

	/**
	 * Altera algum dado do usuario dado seu ID, e a informacao que deseja
	 * atualizar.
	 * 
	 * @param id      identificacao do usuario.
	 * @param nome    nome do usuario.
	 * @param email   email do usuario.
	 * @param celular celular do usuario.
	 * 
	 * @return retorna o toString do usuario com os novos dados.
	 */
	public String alteraDadosReceptor(String id, String nome, String email, String celular) {
		this.validador.validaDado(id, this.ERROID);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		if (!(nome == null) && !nome.trim().equals("")) {
			this.usuarios.get(id).setNome(nome);
		}
		if (!(celular == null) && !celular.trim().equals("")) {
			this.usuarios.get(id).setCelular(celular);
		}
		if (!(email == null) && !email.trim().equals("")) {
			this.usuarios.get(id).setEmail(email);
		}

		return this.usuarios.get(id).toString();
	}

	/**
	 * Imprime o toString do usario dado seu nome.
	 * 
	 * @param nome nome do usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE,
	 *         STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaDado(nome, this.ERRONOME);
		boolean flag = false;
		List<Usuario> lista = new ArrayList<>();

		for (String id : this.usuarios.keySet()) {
			if (this.usuarios.get(id).getNome().equals(nome)) {
				lista.add(this.usuarios.get(id));
				flag = true;
			}
		}

		if (flag) {
			return lista.stream().map(u -> u.toString()).collect(Collectors.joining(" | "));
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}

	/**
	 * Altera algum dado do usuario dado seu ID, e a informacao que deseja
	 * atualizar.
	 * 
	 * @param id      identificacao do usuario.
	 * @param nome    nome do usuario.
	 * @param email   email do usuario.
	 * @param celular celular do usuario.
	 * 
	 * @return retorna o toString do usuario com os novos dados.
	 */
	public String alteraDadosDoador(String id, String nome, String email, String celular) {
		this.validador.validaDado(id, this.ERROID);

		if (!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		if (!(nome == null) && !nome.trim().equals("")) {
			this.usuarios.get(id).setNome(nome);
		}
		if (!(celular == null) && !celular.trim().equals("")) {
			this.usuarios.get(id).setCelular(celular);
		}
		if (!(email == null) && !email.trim().equals("")) {
			this.usuarios.get(id).setEmail(email);
		}

		return this.usuarios.get(id).toString();
	}

	/**
	 * Remove um usuario apatir do seu ID
	 * 
	 * @param id identificacao do usuario.
	 */
	public void removeUsuario(String id) {
		this.validador.validaDado(id, this.ERROID);

		if (this.usuarios.containsKey(id)) {
			this.usuarios.remove(id);
		} else {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
	}

	public void adicionaDescritor(String descricao) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		
		descricao = descricao.toLowerCase();
		if (this.descricoes.containsKey(descricao)) {
			throw new IllegalArgumentException(this.ERRODESCRICAOJAEXISTENTE + descricao + ".");
		}
		this.descricoes.put(descricao, 0);
	}

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.validador.validaDado(idDoador, this.ERROIDDOADOR);		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		if(!this.usuarios.get(idDoador).isEhReceptor()) {
			return this.adicionaItem(idDoador, descricaoItem, quantidade, tags);
		}else {
			throw new IllegalArgumentException("Entrada invalida: usuario nao e doador.");
		}
	
	}

	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		this.validador.validaDado(idReceptor, this.ERROIDDOADOR);		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		if(this.usuarios.get(idReceptor).isEhReceptor()) {
			return this.adicionaItem(idReceptor, descricaoItem, quantidade, tags);
		}else {
			throw new IllegalArgumentException("Entrada invalida: usuario nao e receptor.");
		}
	
	}
	
	
	
	private int adicionaItem(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.validador.validaDado(descricaoItem, this.ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALORQTD);

		this.cont++;
		this.descricoes.put(descricaoItem.toLowerCase(), quantidade);
		return this.usuarios.get(idDoador).adicionaItemParaDoacao(descricaoItem, quantidade, tags, this.cont);
	}

	public String exibeItem(int id, String idDoador) {
		if (idDoador != null && !this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}

		return this.usuarios.get(idDoador).exibeItem(id);
	}

	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		return this.atualizaItem(id, idDoador, quantidade, tags);
	}

	public String atualizaItemNecessario(int id, String idReceptor, int quantidade, String tags) {
		return this.atualizaItem(id, idReceptor, quantidade, tags);
	}
	
	
	private String atualizaItem(int id, String idDoador, int quantidade, String tags) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		this.validador.validaValorPositivo(id, "Entrada invalida: id do item nao pode ser negativo.");
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		if (quantidade > 0) {
			String desc = this.usuarios.get(idDoador).getDescricaoItem(id);
			this.descricoes.put(desc,quantidade);
		}
		
		return this.usuarios.get(idDoador).atualizaItemParaDoacao(id, quantidade, tags);
	}

	public void removeItemParaDoacao(int id, String idDoador) throws Exception {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		this.validador.validaValorPositivo(id, "Entrada invalida: id do item nao pode ser negativo.");
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		String[] desc = this.usuarios.get(idDoador).removeItemParaDoacao(id).split(",");
		this.descricoes.put(desc[0], this.descricoes.get(desc[0]) - Integer.parseInt(desc[1]));
	}

	public String listaDescritorDeItensParaDoacao() {
		String saida = "";

		for (String descricao : this.descricoes.keySet()) {
			saida += this.descricoes.get(descricao) + " - " + descricao + " | ";
		}

		return saida.substring(0, saida.length() - 3);
	}

	public String listaItensParaDoacao() {
		Map<Integer, Usuario> ligaItemAoUsuario = new HashMap<>();
		List<Item> itens = new ArrayList<>();

		for (String id : this.usuarios.keySet()) {
			List<Item> itensDeUsuario = this.usuarios.get(id).pegaTodosOsItens();
			for (Item item : itensDeUsuario) {
				ligaItemAoUsuario.put(item.getId(), this.usuarios.get(id));
			}
			itens.addAll(itensDeUsuario);
		}

		Collections.sort(itens, new QuantidadeComparator());
		String saida = "";
		for (Item item : itens) {
			String representacaoDeUsuario = ligaItemAoUsuario.get(item.getId()).representacaoParaListagemDeDoacao();
			saida += item + ", " + representacaoDeUsuario + " | ";
		}
		return saida.substring(0, saida.length() - 3);

	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		this.validador.validaDado(descricao, this.ERROTEXTODEPESQUISA);
		List<Item> itensComDescricao = new ArrayList<>();
		String saida = "";
		for (String id : this.usuarios.keySet()) {
			itensComDescricao.addAll(this.usuarios.get(id).procuraItensComNome(descricao));
		}
		Collections.sort(itensComDescricao, new DescricaoComparator());

		for (Item item : itensComDescricao) {
			saida += item.toString() + " | ";
		}
		return saida.substring(0, saida.length() - 3);
	}

}