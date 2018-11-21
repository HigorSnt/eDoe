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
	public ItemNecessario(String idPessoa, String descricao, int quantidade, String[] tags, int id, String nomePessoa) {
		super(idPessoa, descricao, quantidade, tags, id, nomePessoa);
		// TODO Auto-generated constructor stub
	}

	/**
	 * retorna uma representação em String do item que ele representa
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id + " - " + this.descricao + ", " + this.converteTagsEmString() + ", " + this.quantidade; 
	}

}
