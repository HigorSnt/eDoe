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
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import enums.Classe;
import models.Item;
import models.ItemAvaliado;
import models.Usuario;
import util.DescricaoComparator;
import util.IdComparator;
import util.QuantidadeComparator;
import util.Validador;

public class UsuarioController {
	
	private Map<String, Usuario> usuarios;
	private Validador validador;
	private Map<String, Integer> descricoes;
	private Map<Integer, String> histDoacoes;
	private int geradorIdItens;
	
	public UsuarioController() {
		this.usuarios = new LinkedHashMap<String, Usuario>();
		this.validador = new Validador();
		this.descricoes = new TreeMap<>();
		this.geradorIdItens = 0;
		this.histDoacoes = new TreeMap<>();
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
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		this.validador.validaNome(nome);
		
		List<Usuario> lista = new ArrayList<>();
		
		this.usuarios.values().forEach(u -> {
			if (u.getNome().equals(nome))
				lista.add(u);
		});

		if (lista.size() > 0) {
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
	 * @param descricao descricao dos itens
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
	 * @param idDoador id do doador.
	 * @param descricaoItem descricao do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * 
	 * @return retorna o Id do item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.validador.validaId(idDoador);		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		if(!this.usuarios.get(idDoador).isReceptor()) {
			return this.adicionaItem(idDoador, descricaoItem, quantidade, tags);
		}else {
			throw new IllegalArgumentException("Entrada invalida: usuario nao e doador.");
		}
	
	}

	/**
	 * Adiciona item necessario.
	 * 
	 * @param idReceptor id do receptor.
	 * @param descricaoItem descricao do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * 
	 * @return retorna id do item.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		this.validador.validaId(idReceptor);		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
				if(this.usuarios.get(idReceptor).isReceptor()) {
			return this.adicionaItem(idReceptor, descricaoItem, quantidade, tags);
		}else {
			throw new IllegalArgumentException("Entrada invalida: usuario nao e receptor.");
		}
	
	}
	
	/**
	 * Adiciona qualquer item.
	 * 
	 * @param id id do consagrado.
	 * @param descricaoItem descricao do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
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
		return this.usuarios.get(id).adicionaItemParaDoacao(descricaoItem, quantidade, tags, this.geradorIdItens);
	}

	/**
	 * Exibe item.
	 * 
	 * @param id id do item.
	 * @param idDoador id do doador.
	 * 
	 * @return retorna a representacao do item.
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
	 * @param id id do item.
	 * @param idDoador id do doador.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * 
	 * @return retorna a representacao do item.
	 */
	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		return this.atualizaItem(id, idDoador, quantidade, tags);
	}

	/**
	 * Atualiza item necessario.
	 * 
	 * @param id id do item.
	 * @param idReceptor id do receptor.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * 
	 * @return retorna a representacao do item.
	 */
	public String atualizaItemNecessario(int id, String idReceptor, int quantidade, String tags) {
		return this.atualizaItem(id, idReceptor, quantidade, tags);
	}
	
	
	/**
	 * Atualiza qualquer item.
	 * 
	 * @param id id do item.
	 * @param idUsuario id do usuario ligado ao item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * 
	 * @return retorna a representacao do item.
	 */
	private String atualizaItem(int id, String idUsuario, int quantidade, String tags) {
		this.validador.validaId(idUsuario);
		this.validador.validaIdItem(id);
		
		if (!this.usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
		
		if (quantidade > 0) {
			String desc = this.usuarios.get(idUsuario).getDescricaoItem(id);
			this.descricoes.put(desc,quantidade);
		}
		
		return this.usuarios.get(idUsuario).atualizaItem(id, quantidade, tags);
	}

	/**
	 * Remove item para doacao.
	 * 
	 * @param id id do item.
	 * @param idDoador id do doador.
	 */
	public void removeItemParaDoacao(int id, String idDoador) {
		this.validador.validaId(idDoador);
		this.validador.validaIdItem(id);
		
		if (!this.usuarios.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		}
		
		String[] desc = this.usuarios.get(idDoador).removeItem(id).split(",");
		this.descricoes.put(desc[0], this.descricoes.get(desc[0]) - Integer.parseInt(desc[1]));
	}

	/**
	 * @return retorna todos descritores de itens para doacao.
	 */
	public String listaDescritorDeItensParaDoacao() {
		String saida = "";

		for (String descricao : this.descricoes.keySet()) {
			saida += this.descricoes.get(descricao) + " - " + descricao + " | ";
		}

		return saida.substring(0, saida.length() - 3);
	}
	
	private void verificaUsuarioReceptor(String idReceptor) {
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		if (!this.usuarios.get(idReceptor).isReceptor()) {
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idReceptor + ".");
		}
	}
	
	public String match(String idReceptor, int idItemNecessario) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItemNecessario);
		Map<Integer, Usuario> ligaItemAoUsuario = new HashMap<>();
		this.verificaUsuarioReceptor(idReceptor);
		Item itemNecessario = this.usuarios.get(idReceptor).pegaItem(idItemNecessario);
		List<ItemAvaliado> itensDoMatch= new ArrayList<>();
		
		
		for (String id: this.usuarios.keySet()) {
			if (this.usuarios.get(id).isReceptor()) {
				continue;
			}
			List<Item> itens = this.usuarios.get(id).pegaTodosOsItensComDescricao(itemNecessario.getDescricao());			
			if (itens.size() == 0) {
				continue;
			}
			for (Item item: itens) {
				itensDoMatch.add(new ItemAvaliado(item, itemNecessario));
				ligaItemAoUsuario.put(item.getId(), this.usuarios.get(id));
			}
		}
		
		Collections.sort(itensDoMatch);
		String saida = "";
		for (ItemAvaliado item: itensDoMatch) {
			saida += item.toString() + ", " + ligaItemAoUsuario.get(item.getId()).representacaoParaListagemDeDoacao() + " | ";
		}
		if (saida.equals("")) {
			return saida;
		}
		return saida.substring(0, saida.length() - 3);
		
		
	}

	/**
	 * @return retorna a representacao de todos itens apra doacao.
	 */
	public String listaItensParaDoacao() {
		Map<Integer, Usuario> ligaItemAoUsuario = new HashMap<>();
		List<Item> itens = new ArrayList<>();

		for (String id : this.usuarios.keySet()) {
			if (!this.usuarios.get(id).isReceptor()) {
				List<Item> itensDeUsuario = this.usuarios.get(id).pegaTodosOsItens();
				for (Item item : itensDeUsuario) {
					ligaItemAoUsuario.put(item.getId(), this.usuarios.get(id));
				}
				itens.addAll(itensDeUsuario);
			}
		}

		Collections.sort(itens, new QuantidadeComparator());
		String saida = "";
		for (Item item : itens) {
			String representacaoDeUsuario = ligaItemAoUsuario.get(item.getId()).representacaoParaListagemDeDoacao();
			saida += item + ", " + representacaoDeUsuario + " | ";
		}
		return saida.substring(0, saida.length() - 3);

	}

	/**
	 * Pesquisa itens de doacao por descricao.
	 * 
	 * @param descricao a descricao que esta sendo pesquisada.
	 * 
	 * @return retorna todos itens com determinada descricao.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		this.validador.validaPesquisa(descricao);
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

	/**
	 * @return retorna a representacao de todos itens necessarios.
	 */
	public String listaItensNecessarios() {
		Map<Integer, Usuario> ligaItemAoUsuario = new HashMap<>();
		List<Item> itens = new ArrayList<>();

		for (String id : this.usuarios.keySet()) {
			if(this.usuarios.get(id).isReceptor()) {
				List<Item> itensDeUsuario = this.usuarios.get(id).pegaTodosOsItens();
				for (Item item : itensDeUsuario) {
					ligaItemAoUsuario.put(item.getId(), this.usuarios.get(id));
				}
				itens.addAll(itensDeUsuario); 
			}
			
		}
		Collections.sort(itens, new IdComparator());
		String saida = "";
		for (Item item : itens) {
			String representacaoDeUsuario = ligaItemAoUsuario.get(item.getId()).representacaoParaListagemDeDoacao();
			saida += item + ", " + representacaoDeUsuario + " | ";
		}
		return saida.substring(0, saida.length() - 3);
	}

	/**
	 * Remove item necessario.
	 * 
	 * @param idReceptor id do receptor.
	 * @param idItem id do item.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		this.validador.validaId(idReceptor);
		this.validador.validaIdItem(idItem);
		
		if (!this.usuarios.containsKey(idReceptor)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idReceptor + ".");
		}
		
		String[] desc = this.usuarios.get(idReceptor).removeItem(idItem).split(",");
		this.descricoes.put(desc[0], this.descricoes.get(desc[0]) - Integer.parseInt(desc[1]));
	}

	/**
	 * Realiza uma doacao.
	 * @param idItemNec id do item necessario.
	 * @param idItemDoado id do item doado.
	 * @param data data da doacao.
	 * @return retorna uma representacao da doacao no formado: DATA - DOADOR - ITEM - QUANTIDADE - RECEPTOR
	 */
	public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
		this.validador.validaIdItem(idItemNec);
		this.validador.validaIdItem(idItemDoado);
		this.validador.validaData(data);
		
		Item nec = null;
		Usuario recep = null;
		boolean flagN = false;
		for(Usuario user : this.usuarios.values()) {
			if(user.isReceptor()) {
				try {
					nec = user.pegaItem(idItemNec);
					recep = user;
					flagN = true;
					break;
				}catch (IllegalArgumentException e) {
				}			
			}
		}
		if(!flagN) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItemNec +".");
		}
		
		Item doado = null;
		Usuario doador = null;
		boolean flagD = false;
		for(Usuario user : this.usuarios.values()) {
			if(!user.isReceptor()) {
				try {
					doado = user.pegaItem(idItemDoado);
					doador = user;
					flagD = true;
					break;
				}catch (IllegalArgumentException e) {
				}		
			}
		}
		String msg = "";
		
		if(!flagD) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItemDoado +".");
		}
		
		if(!doado.getDescricao().equals(nec.getDescricao())) {
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		}
		if(doado.getQuantidade() > nec.getQuantidade()) {
			doado.setQuantidade(doado.getQuantidade() - nec.getQuantidade());
			this.removeItemNecessario(recep.getId(), idItemNec);
			msg = data + " - doador: " + doador.getNome()+"/"+doador.getId()+", item: "+doado.getDescricao()+", quantidade: "+nec.getQuantidade()+", receptor: "+recep.getNome()+"/"+recep.getId();
		}else if(nec.getQuantidade() > doado.getQuantidade()) {
			nec.setQuantidade(nec.getQuantidade() - doado.getQuantidade());
			this.removeItemParaDoacao(idItemDoado, doador.getId());
			msg = data + " - doador: " + doador.getNome()+"/"+doador.getId()+", item: "+doado.getDescricao()+", quantidade: "+doado.getQuantidade()+", receptor: "+recep.getNome()+"/"+recep.getId();
		}else {
			this.removeItemNecessario(recep.getId(), idItemNec);
			this.removeItemParaDoacao(idItemDoado, doador.getId());
			msg = data + " - doador: " + doador.getNome()+"/"+doador.getId()+", item: "+doado.getDescricao()+", quantidade: "+nec.getQuantidade()+", receptor: "+recep.getNome()+"/"+recep.getId();
		}
		
		String data2 = data.replace("/", "");
		data2 = new StringBuilder(data2).reverse().toString();
		int dataint = Integer.parseInt(data2);
		
		if(this.histDoacoes.containsKey(dataint)) {
			String aux = this.histDoacoes.get(dataint);
			String desc = aux.substring(aux.indexOf("item: ")+6, aux.indexOf("item: ")+9);
			if(nec.getDescricao().compareTo(desc) > 1) {
				this.histDoacoes.put(dataint+1, msg);
			}else if(nec.getDescricao().compareTo(desc) < 1){
				this.histDoacoes.put(dataint-1, msg);
			}
		}else{
			this.histDoacoes.put(dataint, msg);
		}
		
		return msg;
		
	}

	/**
	 * Lista o historico de doacoes.
	 * @return todas doacoes feitas ate o momento.
	 */
	public String listaDoacoes() {
		String msg = "";
		for(String a : this.histDoacoes.values()) {
			msg += a + " | ";
		}
		if (msg.length()>1) {
			return msg.substring(0, msg.length()-3);
		}return msg;
	}

}



