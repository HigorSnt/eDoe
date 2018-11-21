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
	public ItemDoado(String idPessoa, String descricao, int quantidade, String[] tags, int id, String nomePessoa) {
		super(idPessoa, descricao, quantidade, tags, id, nomePessoa);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * retorna uma representação em String do item doado.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", " + this.converteTagsEmString() + ", " + this.quantidade; 
	}


	
}
