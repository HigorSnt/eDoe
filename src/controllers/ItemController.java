package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import aux.QuantidadeComparator;
import aux.Validador;
import models.Item;
import models.ItemDoado;
import models.ItemNecessario;

public class ItemController {
	
	private final String ERROIDDOADOR = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	private final String ERRODESCRITOR = "Entrada invalida: descricao nao pode ser vazia ou nula.";
	private final String ERROVALORQTD = "Entrada invalida: quantidade deve ser maior que zero.";
	private final String ERROVALORIDITEM = "Entrada invalida: id do item nao pode ser negativo.";
	private final String ERROTAGS = "Entrada invalida: tags nao pode ser vazia ou nula.";
	private final String ERROTEXTODEPESQUISA = "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.";
	private Map<String, List<Item>> itensDoados;
	private Map<String, List<Item>> itensNecessarios;
	private Map<String, List<Item>> itensDoadosPorUsuario;
	private int cont;
	
	private Validador validador = new Validador();

	public ItemController() {
		this.itensDoados = new TreeMap<>();
		this.itensNecessarios = new LinkedHashMap<>();
		this.itensDoadosPorUsuario = new LinkedHashMap<>();

		this.cont = 0;
	}

	public void adicionaDescritor(String descricao) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		
		descricao = descricao.toLowerCase();
		
		if (!(this.itensDoados.containsKey(descricao) || this.itensNecessarios.containsKey(descricao))) {
			this.itensDoados.put(descricao, new ArrayList<>());
		}else {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao + ".");
		}
	}

	public int adicionaItemParaDoacao(String idDoador, String descricao, int quantidade, String tags) {
		this.validador.validaDado(idDoador, this.ERROIDDOADOR);
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALORQTD);
		
		this.cont++;
		Item aSerAdcionado = new ItemDoado(descricao, quantidade, tags.split(","), this.cont);
		
		if (this.itensDoados.containsKey(descricao)) {
			for (Item item : this.itensDoados.get(descricao)) {
				if (item.converteTagsEmString().equals(aSerAdcionado.converteTagsEmString())) {
					item.setQuantidade(quantidade);
					return item.getId();
				}
			}
			this.itensDoados.get(descricao).add(aSerAdcionado);
		} else {
			this.adicionaDescritor(descricao);
			this.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
		}
		
		if (this.itensDoadosPorUsuario.containsKey(idDoador)) {
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		} else {
			this.itensDoadosPorUsuario.put(idDoador, new ArrayList<>());
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		}

		return this.cont;
	}
	
	public int cadastraItemNecessario(String idDoador, String descricao, int quantidade, String tags, String nomeDoador) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALORQTD);
		this.validador.validaDado(tags, this.ERROTAGS);
		
		this.cont++;
		Item aSerAdcionado = new ItemNecessario(descricao, quantidade, tags.split(","), this.cont);
		
		if (this.itensNecessarios.containsKey(descricao)) {
			for (Item item : this.itensDoados.get(descricao)) {
				if (item.converteTagsEmString().equals(aSerAdcionado.converteTagsEmString())) {
					item.setQuantidade(quantidade);
					return item.getId();
				}
			}
			this.itensNecessarios.get(descricao).add(aSerAdcionado);
		} else {
			throw new IllegalArgumentException("Descricao de item nao existente: " + descricao);
		}
		
		if (this.itensDoadosPorUsuario.containsKey(idDoador)) {
			for (Item item : this.itensDoados.get(descricao)) {
				if (item.converteTagsEmString().equals(aSerAdcionado.converteTagsEmString())) {
					item.setQuantidade(quantidade);
					return item.getId();
				}
			}
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		} else {
			this.itensDoadosPorUsuario.put(idDoador, new ArrayList<>());
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		}

		return this.cont;
	}
	
	public String atualizaItemParaDoacao(int id, String idDoador, int quantidade, String tags) {
		this.validador.validaValorPositivo(id, ERROVALORIDITEM);
		
		if(!this.itensDoadosPorUsuario.containsKey(idDoador)) {
			throw new IllegalArgumentException("Usuario nao encontrado: "+ idDoador + ".");
		}
		
		for (Item item : this.itensDoadosPorUsuario.get(idDoador)) {
			if (item.getId() == id) {
				if(quantidade > 0) {
					item.setQuantidade(quantidade);
				}
				if (tags != null && !(tags.trim().equals(""))) {
					item.setTags(tags.split(","));
				}
				return item.toString();
			}
		}
		
		throw new IllegalArgumentException("Item nao encontrado: "+ id + ".");
	}
	
	public String exibeItem (int id, String idDoador) {
		this.validador.validaDado(idDoador, "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		List<Item> lista = new ArrayList<>(this.itensDoadosPorUsuario.get(idDoador));	
		
		for (Item item : lista) {
			if (item.getId() == id) {
				return item.toString();
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}
	
	public void removeItemParaDoacao (int id, String idDoador) {
		this.validador.validaValorPositivo(id, this.ERROVALORIDITEM);
		if (!this.itensDoadosPorUsuario.containsKey(idDoador) || this.itensDoadosPorUsuario.get(idDoador).size() == 0) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		List<Item> itens = new ArrayList<>();
		itens = this.itensDoadosPorUsuario.get(idDoador);
		
		for (Iterator<Item> iterator = itens.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getId() == id) {
				String desc = item.getDescricao();
				iterator.remove();
				this.removeItensDoados(desc, id);
				return;
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}
	
	private void removeItensDoados(String descricao, int id) {
		List<Item> itens = new ArrayList<>(this.itensDoados.get(descricao));
		for (Iterator<Item> iterator = itens.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getId() == id) {
				iterator.remove();
			}
		} 
	}

	public String listaDescritorDeItensParaDoacao() {
		String msg = "";
		int qnt = 0;
		for (String descritor : this.itensDoados.keySet()) {
			qnt = 0;
			for(Item item : this.itensDoados.get(descritor)) {
				qnt += item.getQuantidade();
			}
			msg += qnt + " - " + descritor + " | ";
		}
		return msg.substring(0, msg.length()-3);
	}
	
	private ArrayList<Item> ordenaItens(){
		ArrayList<Item> itens = new ArrayList<>();
		for(String descritor : this.itensDoados.keySet()) {
			if (this.itensDoados.get(descritor).size() > 0) {
				for(Item item : this.itensDoados.get(descritor)) {
					itens.add(item);
				}
			}
		}
		Collections.sort(itens, new QuantidadeComparator());
		return itens;
	}
	
	public String listaItensParaDoacao() {
		String msg = "";
		for (Item item : this.ordenaItens()) {
			msg += item.toString() + " | ";
		}
		if (msg.length() > 0) {
			return msg.substring(0, msg.length() - 3);
		}else {
			return msg;
		}
	}

	private List<Item> procuraItensComNome(String descricao){
		List<Item> itens = new ArrayList<>();
		
		for (String desc: this.itensDoados.keySet()) {
			boolean descricaoPresente = false;
			String[] palavrasChaves = desc.split(" ");
			for (String palavra: palavrasChaves) {
				if (palavra.equals(descricao)) {
					descricaoPresente = true;
				}
			}
			if (descricaoPresente) {
				itens.addAll(this.itensDoados.get(desc));
			}
			
		}
		
		return itens;
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		this.validador.validaDado(descricao, this.ERROTEXTODEPESQUISA);
		List<Item> itensComDescricao = this.procuraItensComNome(descricao);
		String saida = "";
		for(Item item: itensComDescricao) {
			saida += item + " | ";
		}
		
		return saida.substring(0, saida.length() - 3);
	}

}
