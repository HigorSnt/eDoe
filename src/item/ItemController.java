package item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import aux.Validador;

public class ItemController {
	
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

	public void cadastraDescritor(String descricao) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		
		if (!(this.itensDoados.containsKey(descricao) || this.itensNecessarios.containsKey(descricao))) {
			this.itensDoados.put(descricao, new ArrayList<>());
		}else {
			throw new IllegalArgumentException("Descritor ja existente: " + descricao);
		}
	}

	public int cadastraItemDoado(String idDoador, String descricao, int quantidade, String tags, String nomeDoador) {
		this.validador.validaDado(descricao, this.ERRODESCRITOR);
		this.validador.validaValorPositivo(quantidade, this.ERROVALOR);
		this.validador.validaDado(tags, this.ERROTAGS);
		
		Item aSerAdcionado = new ItemDoado(descricao, quantidade, tags.split(","), this.cont);
		
		if (this.itensDoados.containsKey(descricao)) {
			this.cont++;
			this.itensDoados.get(descricao).add(aSerAdcionado);
		} else {
			throw new IllegalArgumentException("Descricao de item nao existente: " + descricao);
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

}
