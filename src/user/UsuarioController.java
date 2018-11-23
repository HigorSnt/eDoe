package user;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UsuarioController {
	private Map<String, Usuario> usuarios;
	
	public UsuarioController() {
		usuarios = new HashMap<>();
	}
	
	/**
	 * Cadastra um novo usuario doador.
	 * @param id CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome nome do usuario a ser cadastrado.
	 * @param email email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe classe do usuario a ser cadastrado.
	 * @return o ID do usuario.
	 */
	public String cadastraUsuario(String id, String nome, String email, String celular, String classe) {
		Usuario user = new Usuario(id, nome, email, celular, classe, false);
		if (!this.usuarios.containsKey(id)) {
			this.usuarios.put(id, user);
			return id;
		}
		throw new IllegalAccessError("Usuario ja cadastrado.");
	}
	
	/**
	 * Cadastra um novo usuario receptor.
	 * @param id CPF/CNPJ do usuario a ser cadastrado.
	 * @param nome nome do usuario a ser cadastrado.
	 * @param email email do usuario a ser cadastrado.
	 * @param celular celular do usuario a ser cadastrado.
	 * @param classe classe do usuario a ser cadastrado.
	 * @return o ID do usuario.
	 */
	public String cadastroReceptor(String id, String nome, String email, String celular, String classe) {
		Usuario user = new Usuario(id, nome, email, celular, classe, true);
		if (!this.usuarios.containsKey(id)) {
			this.usuarios.put(id, user);
			return id;
		}
		throw new IllegalAccessError("Usuario ja cadastrado.");
	}
	
	/**
	 * Imprime o toString do usario dado seu ID.
	 * @param id identificacao do usuario.
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE, STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorId(String id) {
		if(this.usuarios.containsKey(id)) {
			return this.usuarios.get(id).toString();
		}
		throw new IllegalAccessError("Usuario nao encontrado.");
	}
	
	/**
	 * Imprime o toString do usario dado seu nome.
	 * @param nome nome do usuario.
	 * @return retorna uma string no formato: NOME/ID, EMAIL, CELULAR, CLASSE, STATUS: xxxxxx
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		for (String id : this.usuarios.keySet()) {
			if(this.usuarios.get(id).getNome().equals(nome)) {
				return this.usuarios.get(id).toString();
			}
		}
		throw new IllegalAccessError("Usuario nao encontrado.");
	}
	
	/**
	 * Altera algum dado do usuario dado seu ID, e a informacao que deseja atualizar.
	 * @param id identificacao do usuario.
	 * @param nome nome do usuario.
	 * @param email email do usuario.
	 * @param celular celular do usuario.
	 * @return retorna um booleano True caso a alteracao seja um sucesso.
	 */
	public boolean alteraDados(String id, String nome, String email, String celular) {
		if(!this.usuarios.containsKey(id)) {
			throw new IllegalAccessError("Usuario nao encontrado");
		}if(!nome.equals("")) {
			this.usuarios.get(id).setNome(nome);
		}if(!celular.equals("")) {
			this.usuarios.get(id).setCelular(celular);
		}if(!email.equals("")) {
			this.usuarios.get(id).setEmail(email);
		}return true;
	}
	
	/**
	 * Remove um usuario apatir do seu ID
	 * @param id identificacao do usuario.
	 * @return retorna um booleano True caso a remocao seja um sucesso.
	 */
	public boolean removeUsuario(String id) {
		if(!this.usuarios.containsKey(id)) {
			throw new IllegalAccessError("Usuario nao encontrado");
		}
		this.usuarios.remove(id);
		return true;
	}
	
	/**
	 * Ler os receptores apatir do caminho do arquivos onde estao armazenados.
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
			System.err.println("Erro na leitura.");
		}
	}
}
