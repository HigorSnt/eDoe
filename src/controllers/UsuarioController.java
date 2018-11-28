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
import java.util.stream.Collectors;

import aux.DescricaoComparator;
import aux.QuantidadeComparator;
import aux.Validador;
import models.Item;
import models.Usuario;
import models.UsuarioDoador;
import models.UsuarioReceptor;

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
	private final String ERROVALORIDITEM = "Entrada invalida: id do item nao pode ser negativo.";
	private final String ERROTAGS = "Entrada invalida: tags nao pode ser vazia ou nula.";
	private final String ERROTEXTODEPESQUISA = "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.";
	private final String ERRODESCRICAOJAEXISTENTE = "Descritor de Item ja existente: ";
	private Map<String, Usuario> usuarios;
	private Map<String, UsuarioReceptor> usuariosReceptores;
	private Validador validador;
	private HashMap<String, Integer> descricoes;
	private int cont;
	//At line 52:Line 52, file acceptance_tests/use_case_1.txt: Expected <Lucas Fernandes/85618414490, lucasoliveira@gmail.com, (83) 99845-9283, status: receptor | Lucas Fernandes/13507190272, lucas.fernandes@gmail.com, (83) 99982-9231, status: doador | Lucas Fernandes/10357071312, amigao@gmail.com, (83) 94813-4871, status: doador>,
	//but was 															 <Lucas Fernandes/13507190272, lucas.fernandes@gmail.com, (83) 99982-9231, status: doador | Lucas Fernandes/10357071312, amigao@gmail.com, (83) 94813-4871, status: doador | Lucas Fernandes/85618414490, lucasoliveira@gmail.com, (83) 99845-9283, status: receptor>
	public UsuarioController() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.usuariosReceptores = new LinkedHashMap<String, UsuarioReceptor>();
		this.validador = new Validador();
		this.descricoes = new HashMap<>();
		this.cont = 0;
	}
	
	/**
	 * Cadastra um novo usuario doador.
	 * 
	 * @param id CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome nome do usuario a ser cadastrado.
	 * @param email email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe classe do usuario a ser cadastrado.
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
		
		UsuarioDoador user = new UsuarioDoador(id, nome, email, celular, classe);
		if (this.usuarios.containsKey(id) || this.usuariosReceptores.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		
		this.usuarios.put(id, user);
		return id;
	}
	
	/**
	 * Cadastra um novo usuario receptor.
	 * 
	 * @param id CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome nome do usuario a ser cadastrado.
	 * @param email email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe classe do usuario a ser cadastrado.
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
		
		UsuarioReceptor user = new UsuarioReceptor(id, nome, email, celular, classe);
		if (!(this.usuariosReceptores.containsKey(id) || this.usuarios.containsKey(id))) {
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
			while(sc.hasNextLine()) {
				String linha = sc.nextLine();
				String array[] = new String[4];
				array = linha.split(",");
				String id = array[0];
				String nome = array[1];
				String email = array[2];
				String celular = array[3];
				String classe = array[4];
				if (this.usuariosReceptores.containsKey(id)) {
					this.alteraDadosReceptor(id, nome, email, celular);
				}else {
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
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE, STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaDado(id, this.ERROID);
		
		if(this.usuarios.containsKey(id)) {
			return this.usuarios.get(id).toString();
		}else if(this.usuariosReceptores.containsKey(id)) {
			return this.usuariosReceptores.get(id).toString();
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
	}
	
	/**
	 * Altera algum dado do usuario dado seu ID, e a informacao que deseja atualizar.
	 * @param id identificacao do usuario.
	 * @param nome nome do usuario.
	 * @param email email do usuario.
	 * @param celular celular do usuario.
	 * 
	 * @return retorna o toString do usuario com os novos dados.
	 */
	public String alteraDadosReceptor(String id, String nome, String email, String celular) {
		this.validador.validaDado(id, this.ERROID);
		
		if(!this.usuariosReceptores.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: "+ id + ".");
		}
		if (!(nome == null) && !nome.trim().equals("")) {
			this.usuariosReceptores.get(id).setNome(nome);
		}
		if (!(celular == null) && !celular.trim().equals("")) {
			this.usuariosReceptores.get(id).setCelular(celular);
		}
		if (!(email == null) && !email.trim().equals("")) {
			this.usuariosReceptores.get(id).setEmail(email);
		}
		
		return this.usuariosReceptores.get(id).toString(); 
	}

	/**
	 * Imprime o toString do usario dado seu nome.
	 * 
	 * @param nome nome do usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE, STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaDado(nome, this.ERRONOME);
		boolean flag = false;
		List<Usuario> lista = new ArrayList<>();
		
		for (String id : this.usuarios.keySet()) {
			if(this.usuarios.get(id).getNome().equals(nome)) {
				lista.add(this.usuarios.get(id));
				flag = true;
			}
		}
		
		for (String id : this.usuariosReceptores.keySet()) {
			if(this.usuariosReceptores.get(id).getNome().equals(nome)) {
				lista.add(this.usuariosReceptores.get(id));
				flag = true;
			}
		}
		
		if (flag) {
			return lista.stream().map(u -> u.toString()).collect(Collectors.joining(" | "));
		}
		throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
	}
	

	/**
	 * Altera algum dado do usuario dado seu ID, e a informacao que deseja atualizar.
	 * @param id identificacao do usuario.
	 * @param nome nome do usuario.
	 * @param email email do usuario.
	 * @param celular celular do usuario.
	 * 
	 * @return retorna o toString do usuario com os novos dados.
	 */
	public String alteraDadosDoador(String id, String nome, String email, String celular) {
		this.validador.validaDado(id, this.ERROID);
		
		if(!this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: "+ id + ".");
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
		
		if(this.usuarios.containsKey(id)) {
			this.usuarios.remove(id);
		}
	
		throw new IllegalArgumentException("Usuario nao encontrado: "+ id + ".");
	}

	public void adicionaDescritor(String descricao) {
		this.validador.validaDado(descricao,this.ERRODESCRITOR);
		if(this.descricoes.containsKey(descricao)) {
			throw new IllegalArgumentException(this.ERRODESCRICAOJAEXISTENTE + descricao + ".");
		}
		this.descricoes.put(descricao,0);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, 
			int quantidade, String tags) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (idDoador != null && !this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		this.cont++;
		return this.usuarios.get(idDoador).adicionaItemParaDoacao(descricaoItem, quantidade, tags, this.cont);
	}
	
	public String exibeItem (int id, String idDoador) {
		if (idDoador != null && !this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		return this.usuarios.get(idDoador).exibeItem(id);
	}

	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (idDoador != null && !this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		return this.usuarios.get(idDoador).atualizaItemParaDoacao(id, quantidade, tags);
	}
	
	public void removeItemParaDoacao (int id, String idDoador) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		this.usuarios.get(idDoador).removeItemParaDoacao(id);
	}

	public String listaDescritorDeItensParaDoacao() {
		String saida = "";
		
		for (String descricao: this.descricoes.keySet()) {
			saida += this.descricoes.get(descricao) + " - " + descricao + " | ";
		}
		
		return saida.substring(0, saida.length() - 3);
	}
	//At line 19:Line 19, file acceptance_tests/use_case_3.txt: Expected <6 - camiseta, tags: [outfit, algodao], quantidade: 25, doador: Cave Johnson/18304715242 | 7 - cadeira de praia, tags: [dobravel], quantidade: 15, doador: Elizabeth Ashe/70513372911 | 3 - cobertor, tags: [lencol, conforto], quantidade: 15, doador: Aramis Araujo/49847103331 | 4 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: Satya Vaswani/59238650111 | 8 - cadeira de alimentacao, tags: [35kg, infantil], quantidade: 5, doador: Cave Johnson/18304715242 | 2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Elizabeth Ashe/70513372911 | 5 - jaqueta de couro, tags: [outfit, couro de cobra], quantidade: 5, doador: Carlos Eduardo/12094912484 | 9 - cadeira reclinavel, tags: [couro], quantidade: 4, doador: Arthur Morgan/52641947613 | 11 - calca jeans, tags: [], quantidade: 3, doador: Arthur Morgan/52641947613 | 1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2, doador: Claudio Campelo/58791093499>, 
	//but was 															 <6 - camiseta, tags: [outfit, algodao], quantidade: 25, doador: Cave Johnson/18304715242 | 7 - cadeira de praia, tags: [dobravel], quantidade: 15, doador: Elizabeth Ashe/70513372911 | 3 - cobertor, tags: [lencol, conforto], quantidade: 15, doador: Aramis Araujo/49847103331 | 4 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: Satya Vaswani/59238650111 | 8 - cadeira de alimentacao, tags: [35kg, infantil], quantidade: 5, doador: Cave Johnson/18304715242 | 1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5, doador: Claudio Campelo/58791093499 | 2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5, doador: Elizabeth Ashe/70513372911 | 5 - jaqueta de couro, tags: [outfit, couro de cobra], quantidade: 5, doador: Carlos Eduardo/12094912484 | 9 - cadeira reclinavel, tags: [couro], quantidade: 4, doador: Arthur Morgan/52641947613 | 11 - calca jeans, tags: [], quantidade: 3, doador: Arthur Morgan/52641947613 | 12 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2, doador: Claudio Campelo/58791093499>
	public String listaItensParaDoacao() {
		Map<Integer, Usuario> ligaItemAoUsuario= new HashMap<>();
		List<Item> itens = new ArrayList<>();
	
		for (String id: this.usuarios.keySet()) {
			List<Item> itensDeUsuario = this.usuarios.get(id).pegaTodosOsItens();
			for (Item item: itensDeUsuario) {
				ligaItemAoUsuario.put(item.getId(), this.usuarios.get(id));
			}
			itens.addAll(itensDeUsuario);
		}
		
		
		Collections.sort(itens,new QuantidadeComparator());
		String saida = "";
		for (Item item: itens) {
			String representacaoDeUsuario = ligaItemAoUsuario.get(item.getId()).representacaoParaListagemDeDoacao(); 
			saida += item + ", " + representacaoDeUsuario + " | ";
		}
		return saida.substring(0, saida.length() - 3);
		
	}
	


	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		List<Item> itensComDescricao = new ArrayList<>();		
		String saida = "";
		for (String id: this.usuarios.keySet()) {
			itensComDescricao.addAll(this.usuarios.get(id).procuraItensComNome(descricao));
		}
		Collections.sort(itensComDescricao, new DescricaoComparator());
		
		for (Item item: itensComDescricao) {
			saida += item.toString() + " | ";
		}
		return saida.substring(0, saida.length() - 3);
	}
	
}