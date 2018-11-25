package controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import aux.Validador;
import models.Item;
import models.ItemDoado;
import models.ItemNecessario;

public class ItemController {
	
	private final String ERROIDDOADOR = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	private final String ERRODESCRITOR = "Entrada invalida: descricao nao pode ser vazia ou nula.";
	private final String ERROVALOR = "Entrada invalida: quantidade deve ser maior que zero.";
	private final String ERROTAGS = "Entrada invalida: tags nao pode ser vazia ou nula.";
	
	private Map<String, List<Item>> itensDoados;
	private Map<String, List<Item>> itensNecessarios;
	private Map<String, List<Item>> itensDoadosPorUsuario;
	private int cont;
	
	private Validador validador = new Validador();

	public ItemController() {
		this.itensDoados = new LinkedHashMap<>();
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
		this.validador.validaValorPositivo(quantidade, this.ERROVALOR);
		
		Item aSerAdcionado = new ItemDoado(descricao, quantidade, tags.split(","), this.cont);
		
		if (this.itensDoados.containsKey(descricao)) {
			this.cont++;
			this.itensDoados.get(descricao).add(aSerAdcionado);
		} else {
			this.adicionaDescritor(descricao);
			this.adicionaItemParaDoacao(idDoador, descricao, quantidade, tags);
		}
		
		if (this.itensDoadosPorUsuario.containsKey(idDoador)) {
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		}

		return this.cont;
	}
	
	public int cadastraItemNecessario(String idDoador, String descricao, int quantidade, String tags, String nomeDoador) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALOR);
		this.validador.validaDado(tags, this.ERROTAGS);
		
		Item aSerAdcionado = new ItemNecessario(descricao, quantidade, tags.split(","), this.cont);
		
		if (this.itensNecessarios.containsKey(descricao)) {
			this.cont++;
			this.itensNecessarios.get(descricao).add(aSerAdcionado);
		} else {
			throw new IllegalArgumentException("Descricao de item nao existente: " + descricao);
		}
		
		if (this.itensDoadosPorUsuario.containsKey(idDoador)) {
			this.itensDoadosPorUsuario.get(idDoador).add(aSerAdcionado);
		}

		return this.cont;
	}
	
	public String exibeItem (int id, String idDoador) {
		this.validador.validaDado(idDoador, "Usuario nao encontrado: " + idDoador + ".");
		List<Item> lista = new ArrayList<>(this.itensDoadosPorUsuario.get(idDoador));
		
		for (Item item : this.itensDoadosPorUsuario.get(idDoador)) {
			if (item.getId() == id) {
				return item.toString();
			}
		}
		throw new IllegalArgumentException("Item nao encontrado: " + id + ".");
	}

}
