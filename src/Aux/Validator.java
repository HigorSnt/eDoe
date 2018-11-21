package Aux;

public class Validator {
	
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

}
