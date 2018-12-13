package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe que representa uma doacao. Cada doacao possui o nome do usuario doador e o seu identificador,
 * o nome do usuario receptor e o seu identificador, a descricao do item que sera doado, a quantidade do item
 * que sera doado e do item necessario, alem da data de quando ocorreu a doacao.
 * 
 * @author GABRIEL DE OLIVEIRA MEIRA NOBREGA - 118110276
 * @author HIGOR SANTOS DE BRITO DANTAS 	 - 118110808
 * @author JOAO FELIPE DA SILVA FREITAS		 - 118110774
 *
 */
public class Doacao implements Serializable {
	
	private static final long serialVersionUID = -2221771936520558029L;
	private String usuarioDoador;
	private String usuarioReceptor;
	private String descricaoItemDoado;
	private int quantidadeItemDoado;
	private int quantidadeItemNecessario;
	private LocalDate data;
	
	/**
	 * Constroi uma doacao
	 * 
	 * @param usuarioDoador				e o nome usuario que esta doando e o seu identificador.
	 * @param usuarioReceptor			e o nome usuario que recebendo a doacao e o seu identificador.
	 * @param descricaoItemDoado		e a descricao do item doado.
	 * @param quantidadeItemDoado		e a quantidade do item doado.
	 * @param quantidadeItemNecessario	e a quantidade do item necessario.
	 * @param data						e a data de quando ocorreu a doacao.
	 * 
	 */
	public Doacao(String usuarioDoador, String usuarioReceptor, String descricaoItemDoado,
			int quantidadeItemDoado, int quantidadeItemNecessario, String data) {
		this.usuarioDoador = usuarioDoador;
		this.usuarioReceptor = usuarioReceptor;
		this.descricaoItemDoado = descricaoItemDoado;
		this.quantidadeItemDoado = quantidadeItemDoado;
		this.quantidadeItemNecessario = quantidadeItemNecessario;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data = LocalDate.parse(data, formatter);
	}
	
	/**
	 * 
	 * @return Retorna a data que ocorreu a doacao.
	 * 
	 */
	public LocalDate getData() {
		return this.data;
	}
	
	/**
	 * 
	 * @return Retorna a descricao do item doado.
	 * 
	 */
	public String getDescricaoItemDoado() {
		return descricaoItemDoado;
	}
	
	/** Uma representacao da Doacao na formatacao: DATA - DOADOR - ITEM - QUANTIDADE - RECEPTOR.
	 * 
	 * @return uma representacao da Doacao.
	 * 
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(this.data) + " - doador: " + usuarioDoador + ", item: " + descricaoItemDoado + 
				", quantidade: " + Integer.min(quantidadeItemDoado, quantidadeItemNecessario)+
				", receptor: " + usuarioReceptor;
	}
	
}
