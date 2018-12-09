package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Doacao implements Serializable {
	
	private String usuarioDoador;
	private String usuarioReceptor;
	private String descricaoItemDoado;
	private int quantidadeItemDoado;
	private int quantidadeItemNecessario;
	private LocalDate data;
	
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
	
	public LocalDate getData() {
		return data;
	}
	
	public String getDescricaoItemDoado() {
		return descricaoItemDoado;
	}
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return formatter.format(this.data) + " - doador: " + usuarioDoador + ", item: " + descricaoItemDoado + 
				", quantidade: " + Integer.min(quantidadeItemDoado, quantidadeItemNecessario)+
				", receptor: " + usuarioReceptor;
	}
	
}
