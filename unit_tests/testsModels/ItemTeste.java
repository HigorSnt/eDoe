package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Item;

class ItemTeste {

	private Item item1;
	
	@BeforeEach
	public void cadastraItem() {
		item1 = new Item("camiseta", 8, "camiseta,tamanho G,bonita".split(","), 1);
	}
	
	@Test
	public void testConstrutor() {
		assertThrows(IllegalArgumentException.class, ()-> new Item("   ", 4, "".split(""), 1));
		assertThrows(IllegalArgumentException.class, ()-> new Item(null, 4, "".split(""), 1));
		assertThrows(IllegalArgumentException.class, ()-> new Item("camiseta", 0, "camiseta".split(","), 1));
		assertThrows(IllegalArgumentException.class, ()-> new Item("camiseta", -7, "camiseta".split(","), 1));
		assertEquals(1, item1.getId());
		assertEquals("camiseta", item1.getDescricao());
		assertEquals(8, item1.getQuantidade());
		assertEquals("1 - camiseta, tags: [camiseta, tamanho G, bonita], quantidade: 8", item1.toString());
	}
	
	@Test
	public void testSetters() {
		assertThrows(IllegalArgumentException.class, ()-> item1.setQuantidade(0));
		assertThrows(IllegalArgumentException.class, ()-> item1.setQuantidade(-8));
		
		item1.setQuantidade(15);
		item1.setTags("camiseta,azul,gola polo".split(","));
		assertEquals("1 - camiseta, tags: [camiseta, azul, gola polo], quantidade: 15", item1.toString());
		assertEquals("[camiseta, azul, gola polo]", item1.getTags().toString());
	}
	
	@Test
	public void testHashCode() {
		assertEquals(-458894630, item1.hashCode());
	}
	
	@Test
	public void testEquals() {
		assertTrue(item1.equals(item1));
		assertFalse(item1.equals(null));
		assertFalse(item1.equals("Teste"));
		assertFalse(item1.equals(new Item("cadeira de rodas", 2, "roda grande,cadeira".split(","), 4)));
		assertFalse(item1.equals(new Item("camiseta", 2, "roda grande,cadeira".split(","), 4)));
		assertFalse(item1.equals(new Item("camiseta", 2, "camiseta,tamanho P,bonita".split(","), 4)));
		assertTrue(item1.equals(new Item("camiseta", 8, "camiseta,tamanho G,bonita".split(","), 5)));
		
	}

}
