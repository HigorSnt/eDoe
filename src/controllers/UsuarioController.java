package controllers;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import aux.Validador;
import models.Usuario;

public class UsuarioController {
	
	private final String ERRONOME = "Entrada invalida: nome nao pode ser vazio ou nulo.";
	private final String ERROEMAIL = "Entrada invalida: email nao pode ser vazio ou nulo.";
	private final String ERROCELULAR = "Entrada invalida: celular nao pode ser vazio ou nulo.";
	private final String ERROCLASSE = "Entrada invalida: classe nao pode ser vazia ou nula.";
	private final String ERROOPCAOCLASSE = "Entrada invalida: opcao de classe invalida.";
	private final String ERROID = "Entrada invalida: id do usuario nao pode ser vazio ou nulo.";
	
	private Map<String, Usuario> usuarios;
	private Validador validador;
	
	public UsuarioController() {
		this.usuarios = new LinkedHashMap<>();
		this.validador = new Validador();
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
	public String cadastraDoador(String id, String nome, String email, String celular, String classe) {
		this.validador.validaDado(nome, this.ERRONOME);
		this.validador.validaDado(id, this.ERROID);
		this.validador.validaDado(celular, this.ERROCELULAR);
		this.validador.validaDado(email, this.ERROEMAIL);
		this.validador.validaDado(classe, this.ERROCLASSE);
		this.validador.validaClasse(classe, this.ERROOPCAOCLASSE);
		
		Usuario user = new Usuario(id, nome, email, celular, classe, false);
		if (!this.usuarios.containsKey(id)) {
			this.usuarios.put(id, user);
			return id;
		}
		throw new IllegalAccessError("Usuario ja existente: " + id + ".");
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
	public String cadastroReceptor(String id, String nome, String email, String celular, String classe) {
		this.validador.validaDado(nome, this.ERRONOME);
		this.validador.validaDado(id, this.ERROID);
		this.validador.validaDado(celular, this.ERROCELULAR);
		this.validador.validaDado(email, this.ERROEMAIL);
		this.validador.validaDado(classe, this.ERROCLASSE);
		this.validador.validaClasse(classe, this.ERROOPCAOCLASSE);
		
		Usuario user = new Usuario(id, nome, email, celular, classe, true);
		if (!this.usuarios.containsKey(id)) {
			this.usuarios.put(id, user);
			return id;
		}
		throw new IllegalAccessError("Usuario ja existente: " + id + ".");
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
		}
		throw new IllegalAccessError("Usuario nao encontrado: " + id + ".");
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
		
		if (flag) {
			return lista.stream().map(u -> u.toString()).collect(Collectors.joining(" | "));
		}
		
		throw new IllegalAccessError("Usuario nao encontrado: " + nome + ".");
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
	public String alteraDados(String id, String nome, String email, String celular) {
		this.validador.validaDado(id, this.ERROID);
		
		if(!this.usuarios.containsKey(id)) {
			throw new IllegalAccessError("Usuario nao encontrado: "+ id + ".");
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
		
		if(!this.usuarios.containsKey(id)) {
			throw new IllegalAccessError("Usuario nao encontrado: "+ id + ".");
		}
		this.usuarios.remove(id);
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
				if (this.usuarios.containsKey(id)) {
					this.alteraDados(id, nome, email, celular);
				}else {
					this.cadastroReceptor(id, nome, email, celular, classe);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Erro na leitura: caminho (" + caminho + ") não encontrado.");
		}
	}
	
	/**
	 * Verifica se determinado usuario foi cadastrado.
	 * 
	 * @param id identifica um usuario
	 * 
	 * @return Um boolean informando se contem ou nao.
	 */
	public boolean contemUsuarioDoador (String id) {
		if (this.usuarios.containsKey(id)) {
			return !this.usuarios.get(id).isEhReceptor();
		}
		return false;
	}
}
