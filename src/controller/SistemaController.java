package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

import comparators.DataComparator;
import comparators.DescricaoComparator;
import comparators.IdComparator;
import comparators.QuantidadeComparator;
import enums.Classe;
import javafx.util.Pair;
import models.Doacao;
import models.Item;
import models.ItemAvaliado;
import models.Usuario;
import util.Validador;

/**
 * Classe que controla os usuarios e as suas acoes.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class SistemaController {
	
	private Map<String, Usuario> usuarios;
	private Validador validador;
	private Map<String, Integer> descricoes;
	private List<Doacao> doacoes;
	private int geradorIdItens;
	
	/**
	 * 
	 * Inicializa todas as variaveis do Controller.
	 * 
	 */
	public SistemaController() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.validador = new Validador();
		this.descricoes = new TreeMap<>();
		this.geradorIdItens = 0;
		this.doacoes = new ArrayList<>();
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
	 * 
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastro(id, nome, email, celular);
		Classe.verificaClasse(classe);

		Usuario usuario = new Usuario(id, nome, email, celular, Classe.valueOf(classe), false);
		
		if (this.usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}

		this.usuarios.put(id, usuario);
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
	 * 
	 */
	public String adicionaReceptor(String id, String nome, String email, String celular, String classe) {
		this.validador.validaCadastro(id, nome, email, celular);
		Classe.verificaClasse(classe);
		
		Usuario usuario = new Usuario(id, nome, email, celular, Classe.valueOf(classe), true);
		if (!(this.usuarios.containsKey(id))) {
			this.usuarios.put(id, usuario);
			return id;
		}
		throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}

	/**
	 * Ler os receptores a partir do caminho dos arquivos onde estao armazenados.
	 * 
	 * @param caminho caminho do arquivo.
	 * 
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
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro na leitura: caminho (" + caminho + ") não encontrado.");
		}
	}

	/**
	 * Imprime o toString de um usuario dado seu ID.
	 * 
	 * @param id identificacao do usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE,
	 *         STATUS: xxxxxx.
	 * 
	 */
	public String pesquisaUsuarioPorId(String id) {
		this.validador.validaId(id);

		if (this.usuarios.containsKey(id)) {
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
	 * 
	 */
	public String alteraDadosReceptor(String id, String nome, String email, String celular) {
		this.validador.validaId(id);

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
	 * Imprime o toString de um usuario dado seu nome.
	 * 
	 * @param nome nome do usuario.
	 * 
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE,
	 *         STATUS: xxxxxx
	 * 
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaNome(nome);
		
		List<Usuario> lista = new ArrayList<>();
		
		this.usuarios.values().forEach(u -> {
			if (u.getNome().equals(nome))
				lista.add(u);
		});

		if (lista.size() <= 0) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}
		
		return lista.stream().map(u -> u.toString()).collect(Collectors.joining(" | "));
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
	 * 
	 */
	public String alteraDadosDoador(String id, String nome, String email, String celular) {
		this.validador.validaId(id);

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
	 * Remove um usuario a partir do seu ID.
	 * 
	 * @param id identificacao do usuario.
	 */
	public void removeUsuario(String id) {
		this.validador.validaId(id);

		if (this.usuarios.containsKey(id)) {
			this.usuarios.remove(id);
		} else {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
	}

	/**
	 * 
	 * @param descricao descricao geral dos itens.
	 * 
	 */
	public void adicionaDescritor(String descricao) {
		this.validador.validaDescritor(descricao);
		
		descricao = descricao.toLowerCase();
		
		if (this.descricoes.containsKey(descricao)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao + ".");
		}
		
		this.descricoes.put(descricao, 0);
	}

	/**
	 * Adiciona novo item para doacao.
	 * 
	 * @param idDoador 		id do doador.
	 * @param descricaoItem descricao do item.
	 * @param quantidade 	quantidade do item.
	 * @param tags 			tags do item.
	 * 
	 * @return retorna o Id do item.
	 * 
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.validador.validaId(idDoador);
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		if(!this.usuarios.get(idDoador).isReceptor()) {
			return this.adicionaItem(idDoador, descricaoItem, quantidade, tags);
		}
		
		throw new IllegalArgumentException("Entrada invalida: usuario nao e doador.");
	}

	/**
	 * Adiciona item necessario.
	 * 
	 * @param idReceptor 		id do receptor.
	 * @param descricaoItem 	descricao do item.
	 * @param quantidade 		quantidade do item.
	 * @param tags 				tags do item.
	 * 
	 * @return retorna id do item.
	 * 
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		this.validador.validaId(idReceptor);
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		if(!this.usuarios.get(idReceptor).isReceptor()) {
			throw new IllegalArgumentException("Entrada invalida: usuario nao e receptor.");
		}
		
		return this.adicionaItem(idReceptor, descricaoItem, quantidade, tags);
	}
	
	/**
	 * Metodo mais geral que adiciona qualquer item.
	 * 
	 * @param id 			id do item.
	 * @param descricaoItem descricao do item.
	 * @param quantidade 	quantidade do item.
	 * @param tags 			tags do item.
	 * 
	 * @return retorna id do item.
	 * 
	 */
	private int adicionaItem(String id, String descricaoItem, int quantidade, String tags) {
		this.validador.validaDescritor(descricaoItem);
		this.validador.validaQuantidade(quantidade);

		this.geradorIdItens++;
		descricaoItem = descricaoItem.toLowerCase();
		this.descricoes.put(descricaoItem, quantidade);
		
		return this.usuarios.get(id).adicionaItem(descricaoItem, quantidade, tags, this.geradorIdItens);
	}

	/**
	 * Exibe item.
	 * 
	 * @param id 		id do item.
	 * @param idDoador 	id do doador.
	 * 
	 * @return retorna a representacao do item.
	 * 
	 */
	public String exibeItem(int id, String idDoador) {
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}

		return this.usuarios.get(idDoador).exibeItem(id);
	}

	/**
	 * Atualiza item para doacao.
	 * 
	 * @param id 			id do item.
	 * @param idDoador 		id do doador.
	 * @param quantidade 	quantidade do item.
	 * @param tags 			tags do item.
	 * 
	 * @return retorna a representacao do item.
	 * 
	 */
	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		return this.atualizaItem(id, idDoador, quantidade, tags);
	}

	/**
	 * Atualiza item necessario.
	 * 
	 * @param id 		   id do item.
	 * @param idReceptor   id do receptor.
	 * @param quantidade   quantidade do item.
	 * @param tags 		   tags do item.
	 * 
	 * @return retorna a representacao do item.
	 * 
	 */
	public String atualizaItemNecessario(int id, String idReceptor, int quantidade, String tags) {
		return this.atualizaItem(id, idReceptor, quantidade, tags);
	}
	
	
	/**
	 * Atualiza qualquer item.
	 * 
	 * @param id 			id do item.
	 * @param idUsuario 	id do usuario ligado ao item.
	 * @param quantidade 	quantidade do item.
	 * @param tags 			tags do item.
	 * 
	 * @return retorna a representacao do item.
	 * 
	 */
	private String atualizaItem(int id, String idUsuario, int quantidade, String tags) {
		this.validador.validaId(idUsuario);
		this.validador.validaIdItem(id);
		
		if (!this.usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
		
		if (quantidade > 0) {
			String descricao = this.usuarios.get(idUsuario).getDescricaoItem(id);
			this.descricoes.put(descricao,quantidade);
		}
		
		return this.usuarios.get(idUsuario).atualizaItem(id, quantidade, tags);
	}

	/**
	 * Remove um item.
	 * 
	 * @param id 		id do item.
	 * @param idUsuario id de um usuario.
	 * 
	 */
	public void removeItem(int id, String idUsuario) {
		this.validador.validaId(idUsuario);
		this.validador.validaIdItem(id);
		
		if (!this.usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
		
		String[] descricaoEQuantidade = this.usuarios.get(idUsuario).removeItem(id).split(",");
		String descricao = descricaoEQuantidade[0];
		int novaQuantidade = this.descricoes.get(descricaoEQuantidade[0]) - Integer.parseInt(descricaoEQuantidade[1]);
		
		this.descricoes.put(descricao, novaQuantidade);
	}

	/**
	 * 
	 * @return retorna todos descritores de itens para doacao.
	 * 
	 */
	public String listaDescritorDeItensParaDoacao() {
		return this.descricoes.keySet().stream().map(descritor -> 
			this.descricoes.get(descritor) + " - " + descritor).collect(Collectors.joining(" | "));
	}
	
	/**
	 * Verifica se o id passado e de um usuario ja cadastrado e se o mesmo e um receptor.
	 * 
	 * @param idUsuario e o id do usuario a ser verificado.
	 * 
	 */
	private void verificaUsuarioReceptor(String idUsuario) {
		if (!this.usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
		
		if (!this.usuarios.get(idUsuario).isReceptor()) {
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idUsuario + ".");
		}
	}
	
	/**
	 * Encontra matches entre itens a serem doados e itens necessarios.
	 * 
	 * @param idReceptor 		e o id do usuario que possui interesse em receber algum item.
	 * @param idItemNecessario	e o item que se deseja obter match.
	 * 
	 * @return a representacao de todos os itens candidatos de match, ordenados de maneira 
	 * 		   crescente em relacao a pontuacao.
	 * 
	 */
	public String match(String idReceptor, int idItemNecessario) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItemNecessario);
		
		Map<Integer, Usuario> itensEUsuarios = new HashMap<>();
		this.verificaUsuarioReceptor(idReceptor);
		Item itemNecessario = this.usuarios.get(idReceptor).pegaItem(idItemNecessario);
		List<ItemAvaliado> itensComMatch = new ArrayList<>();
		
		
		pegaItensParaMatch(itensEUsuarios, itemNecessario, itensComMatch);
		
		Collections.sort(itensComMatch);
		
		return itensComMatch.stream().map(item -> item.toString() + ", " + 
				itensEUsuarios.get(item.getId()).representacaoParaListagemDeDoacao()).collect(Collectors.joining(" | "));
	}
	
	/**
	 * Metodo que recupera todos os itens com matches possiveis.
	 * 
	 * @param itensEUsuarios 	um mapa que liga cada item a um usuario que o possui.
	 * @param itemNecessario 	o item que desejo obter um match.
	 * @param itensComMatch 	uma lista com todos os itens que possuem match com o item passado.
	 * 
	 */
	private void pegaItensParaMatch(Map<Integer, Usuario> itensEUsuarios, Item itemNecessario,
		List<ItemAvaliado> itensComMatch) {
		
		for (String id: this.usuarios.keySet()) {
			List<Item> itens = this.usuarios.get(id).pegaTodosOsItensComDescricao(itemNecessario.getDescricao());			
			
			if (itens.size() == 0 || this.usuarios.get(id).isReceptor()) {
				continue;
			}
			
			itens.forEach(item -> {
				itensComMatch.add(new ItemAvaliado(item, itemNecessario));
				Usuario usuario = this.usuarios.get(id);
				itensEUsuarios.put(item.getId(), usuario);
			});
		}
	}

	/**
	 * 
	 * @return retorna a representacao de todos itens para doacao.
	 * 
	 */
	public String listaItensParaDoacao() {
		Map<Integer, Usuario> itemEUsuario = new HashMap<>();
		List<Item> itens = new ArrayList<>();

		selecionaItens(itemEUsuario, itens, true);

		Collections.sort(itens, new QuantidadeComparator());
		
		return itens.stream().map(item -> item + ", " + 
			itemEUsuario.get(item.getId()).representacaoParaListagemDeDoacao()).collect(Collectors.joining(" | "));
	}

	/**
	 * Pesquisa itens de doacao por descricao.
	 * 
	 * @param descricao a descricao que esta sendo pesquisada.
	 * 
	 * @return retorna todos itens com determinada descricao.
	 * 
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		this.validador.validaPesquisa(descricao);
		
		List<Item> itensComDescricao = new ArrayList<>();
		
		for (String id : this.usuarios.keySet()) {
			itensComDescricao.addAll(this.usuarios.get(id).procuraItensComNome(descricao));
		}
		
		Collections.sort(itensComDescricao, new DescricaoComparator());

		return itensComDescricao.stream().map(item -> item.toString()).collect(Collectors.joining(" | "));
	}

	/**
	 * 
	 * @return retorna a representacao de todos itens necessarios.
	 * 
	 */
	public String listaItensNecessarios() {
		Map<Integer, Usuario> itemEUsuario = new HashMap<>();
		List<Item> itens = new ArrayList<>();

		selecionaItens(itemEUsuario, itens, false);

		Collections.sort(itens, new IdComparator());
		
		return itens.stream().map(item -> item + ", " + 
			itemEUsuario.get(item.getId()).representacaoParaListagemDeDoacao()).collect(Collectors.joining(" | "));
	}
	
	/**
	 * Metodo que coloca todos os itens sejam ele para doacao ou necessario dentro 
	 * de uma lista e de um mapa a fim de facilitar a listagem de todos os itens.
	 * 
	 * @param itemEUsuario 		e uma mapa que liga cada item ao usuario que o possui.
	 * @param itens 			e uma lista com todos os itens que se deseja buscar.
	 * @param identificador 	auxilia a identificar se deseja obter itens doados ou
	 * 							necessarios por meio de uma operacao XOR.
	 * 
	 */
	private void selecionaItens(Map<Integer, Usuario> itemEUsuario, List<Item> itens, boolean identificador) {
		for (String id : this.usuarios.keySet()) {
			if (identificador ^ this.usuarios.get(id).isReceptor()) {
				Usuario usuario = this.usuarios.get(id);
				List<Item> itensDeUsuario = this.usuarios.get(id).pegaTodosOsItens();
				
				itensDeUsuario.forEach(item -> itemEUsuario.put(item.getId(), usuario));
				itens.addAll(itensDeUsuario);
			}
		}
	}

	/**
	 * Realiza uma doacao.
	 * 
	 * @param idItemNecessario 		id do item necessario.
	 * @param idItemDoado 			id do item doado.
	 * @param data 					data da doacao.
	 * 
	 * @return retorna uma representacao da doacao no formado: DATA - DOADOR - ITEM - QUANTIDADE - RECEPTOR.
	 * 
	 */
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		this.validador.validaIdItem(idItemNecessario);
		this.validador.validaIdItem(idItemDoado);
		this.validador.validaData(data);
		
		Pair <Item, Usuario> parItemEUsuario;
		
		parItemEUsuario = pegaItemEUsuarioParaDoacao(idItemNecessario, false);
		Item itemNecessario = parItemEUsuario.getKey();
		Usuario usuarioReceptor = parItemEUsuario.getValue();
		
		parItemEUsuario = pegaItemEUsuarioParaDoacao(idItemDoado, true);
		Item itemDoado = parItemEUsuario.getKey();
		Usuario usuarioDoador = parItemEUsuario.getValue();
		
		if(!itemDoado.getDescricao().equalsIgnoreCase(itemNecessario.getDescricao())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}
		
		Doacao doacao = new Doacao(usuarioDoador.getNome()+ "/" + usuarioDoador.getId(),usuarioReceptor.getNome()+ "/" + usuarioReceptor.getId(),
				itemDoado.getDescricao(), itemDoado.getQuantidade(), itemNecessario.getQuantidade(), data);
		
		this.doacoes.add(doacao);
		
		atualizaAposDoacao(idItemNecessario, idItemDoado, itemNecessario, usuarioReceptor, itemDoado, usuarioDoador);
		return doacao.toString();
	}
	
	/**
	 * Apos uma operação de match se torna necessario realizar atualizacoes nos itens, esse
	 * metodo serve para isso.
	 * 
	 * @param idItemNecessario 	e o identificador de um item necessario.
	 * @param idItemDoado		e o identificador de um item doado.
	 * @param itemNecessario 	e um item necessario.
	 * @param usuarioReceptor 	e um usuario receptor.
	 * @param itemDoado 		e um item para doacao.
	 * @param usuarioDoador 	e um usuario doador.
	 * 
	 */
	private void atualizaAposDoacao(int idItemNecessario, int idItemDoado, Item itemNecessario, Usuario usuarioReceptor,
			Item itemDoado, Usuario usuarioDoador) {
		if(itemDoado.getQuantidade() > itemNecessario.getQuantidade()) {
			itemDoado.setQuantidade(itemDoado.getQuantidade() - itemNecessario.getQuantidade());
			this.removeItem(idItemNecessario, usuarioReceptor.getId());
		}else if(itemNecessario.getQuantidade() > itemDoado.getQuantidade()) {
			itemNecessario.setQuantidade(itemNecessario.getQuantidade() - itemDoado.getQuantidade());
			this.removeItem(idItemDoado, usuarioDoador.getId());
		}else {
			this.removeItem(idItemNecessario, usuarioReceptor.getId());
			this.removeItem(idItemDoado, usuarioDoador.getId());
		}
	}
	
	/**
	 * Ao se realizar uma doacao e necessario ter o item e o usuario, esse metodo
	 * auxilia a realizar isso.
	 * 
	 * @param idItem 			e o id do item.
	 * @param identificador 	auxilia a identificar se deseja obter usuario doador ou
	 * 							receptor por meio de uma operacao XOR.
	 * 
	 * @return Um par com o item encontrado e o usuario.
	 * 
	 */
	private Pair<Item, Usuario> pegaItemEUsuarioParaDoacao(int idItem, boolean identificador) {
		Item itemNecessario;
		
		for(Usuario usuario : this.usuarios.values()) {
			if(identificador ^ usuario.isReceptor()) {
				try {
					itemNecessario = usuario.pegaItem(idItem);
					return new Pair<>(itemNecessario, usuario);
				}catch (IllegalArgumentException iae) {
					continue;
				}
			}
		}
		
		throw new IllegalArgumentException("Item nao encontrado: " + idItem +".");
	}

	/**
	 * Lista o historico de doacoes.
	 * 
	 * @return todas doacoes feitas ate o momento.
	 * 
	 */
	public String listaDoacoes() {
		Collections.sort(this.doacoes, new DataComparator());
		
		return this.doacoes.stream().map(d -> d.toString()).collect(Collectors.joining(" | "));
	}
	
	/**
	 * Realiza a inicializacao do sistema com todos itens, usuarios e doacoes
	 * cadastradas anteriormente.
	 * 
	 * @throws IOException Este metodo pode lancar uma excecao do tipo IOException.
	 * @throws ClassNotFoundException Este metodo pode lancar uma excecao do tipo ClassNotFoundException.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void iniciaSistema() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("logs/sistema.dat");
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.geradorIdItens = (int)ois.readObject();
		this.descricoes = (Map<String, Integer>) ois.readObject();
		this.usuarios = (Map<String, Usuario>) ois.readObject();
		this.doacoes = (List<Doacao>) ois.readObject();
		fis.close();
	}
	
	/**
	 * Metodo que realiza a persistencia do sistema, ou seja, salva todos os dados cadastrados
	 * ao longo da execucao.
	 * 
	 * @throws IOException Este metodo pode lancar uma excecao do tipo IOException.
	 * 
	 */
	public void finalizaSistema() throws IOException {
		FileOutputStream fos = new FileOutputStream("logs/sistema.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.geradorIdItens);
		oos.writeObject(this.descricoes);
		oos.writeObject(this.usuarios);
		oos.writeObject(this.doacoes);
		fos.close();
	}

}