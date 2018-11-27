package models;

/**
 * Entidade que representa um Item doado.
 * @author kyouma
 *
 */
public class ItemDoado extends Item{
	
	public ItemDoado(String descricao, int quantidade, String[] tags, int id, String etiquetaDoador) {
		super(descricao, quantidade, tags, id, etiquetaDoador);
	}
	
	/**
	 * retorna uma representação em String do item doado.
	 */
	@Override
	public String toString() {
		return this.id + " - " + this.descricao + ", tags: " + this.converteTagsEmString() + ", quantidade: " + this.quantidade; 
	}
	
}
