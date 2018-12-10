package enums;

import java.util.Arrays;
import java.util.List;

public enum Classe {
	PESSOA_FISICA, IGREJA, ORGAO_PUBLICO_MUNICIPAL, ORGAO_PUBLICO_ESTADUAL, ORGAO_PUBLICO_FEDERAL, ONG, ASSOCIACAO, SOCIEDADE;
	
	/**
	 * Verifica se um dado pode ser tratado como uma classe.
	 * 
	 * @param dado e o que sera verificado.
	 * 
	 */
	public static void verificaClasse (String dado) {
		if (dado == null || dado.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		}
		
		List<Classe> classes = Arrays.asList(Classe.values());
		if (!classes.stream().anyMatch(c -> c.toString().equals(dado.trim().toUpperCase()))) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
	}
}
