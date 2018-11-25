package aux;

public class Validador {
	
	/**
	 * Verifica se algum dado nao pode ser tratado.
	 * 
	 * @param dado Informacao que necessita de validacao.
	 * @param err O erro que sera mostrado caso nao seja valido.
	 * 
	 */
	public void validaDado(String dado, String err) {
		if (dado == null || dado.trim().equals("")) {
			throw new IllegalArgumentException(err);
		}
	}
	
	/**
	 * Verifica se a classe passada eh permitida.
	 * 
	 * @param dado Informacao que necessita de validacao.
	 * @param err O erro que sera mostrado caso nao seja valido.
	 * 
	 */
	public void validaClasse (String dado, String err) {
		dado = dado.trim().toUpperCase();
		if (!"PESSOA_FISICA".equals(dado) && !"IGREJA".equals(dado) && !"ORGAO_PUBLICO_MUNICIPAL".equals(dado)
				&& !"ORGAO_PUBLICO_ESTADUAL".equals(dado) && !"ORGAO_PUBLICO_FEDERAL".equals(dado) && 
				!"ONG".equals(dado) && !"ASSOCIACAO".equals(dado) && !"SOCIEDADE".equals(dado)) {
			throw new IllegalArgumentException(err);
		}
	}
	
	/**
	 * Verifica se o valor passado eh negativo.
	 * 
	 * @param valor Informacao que necessita de validacao.
	 * @param err O erro que sera mostrado caso nao seja valido.
	 * 
	 */
	public void validaValorPositivo(double valor, String err) {
		if (valor <= 0) {
			throw new IllegalArgumentException(err);
		}		
	}

}
