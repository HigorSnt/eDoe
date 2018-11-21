package Aux;

public class Validator {
	
	public void validaDado(String dado, String err) {
		if (dado == null || dado.trim().equals("")) {
			throw new IllegalArgumentException(err);
		}
	}

}
