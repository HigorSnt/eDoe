package item;

/**
 * Entidade que representa um Item doado.
 * @author kyouma
 *
 */
public class ItemDoado extends Item{

	/**
	 * Construtor de Item doado
	 * @param idPessoa
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @param id
	 * @param nomePessoa
	 */
	public ItemDoado(String descricao, int quantidade, String[] tags, int id) {
		super(descricao, quantidade, tags, id);
	}

	
	/**
	 * retorna uma representação em String do item doado.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", " + this.converteTagsEmString() + ", " + this.quantidade; 
	}


	
}
