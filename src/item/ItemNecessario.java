package item;

/**
 * Classe responsavel para representar a entidade de Item necessario.
 * @author kyouma
 *
 */
public class ItemNecessario extends Item {

	/**
	 * Construtor de item necessario, apenas usa o construtor de Item
	 * @param idPessoa
	 * @param descricao
	 * @param quantidade
	 * @param tags
	 * @param id
	 * @param nomePessoa
	 */
	public ItemNecessario(String descricao, int quantidade, String[] tags, int id) {
		super(descricao, quantidade, tags, id);
	}

	/**
	 * retorna uma representação em String do item que ele representa
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", " + this.converteTagsEmString() + ", " + this.quantidade; 
	}

}
