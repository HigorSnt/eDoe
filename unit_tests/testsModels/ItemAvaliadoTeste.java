package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Item;
import models.ItemAvaliado;

class ItemAvaliadoTeste {
	
	ItemAvaliado item1;
	ItemAvaliado item2;
	
	@BeforeEach
	public void cadastrandoItemAvaliado() {
		Item i1 = new Item("cadeira de rodas", 5, "roda grande,cadeira".split(","), 1);
		Item i2 = new Item("cadeira de rodas", 7, "roda grande,80kg,conforto".split(","), 2);
		Item i3 = new Item("cadeira de rodas", 5, "cadeira,roda grande".split(","), 3);
		item1 = new ItemAvaliado(i1, i2);
		item2 = new ItemAvaliado(i3, i1);
	}
	
	@Test
	public void testConstrutor() {
		Item i1 = new Item("cadeira de rodas", 7, "roda grande,80kg,conforto".split(","), 2);
		
		assertThrows(IllegalArgumentException.class, ()-> new ItemAvaliado(null, i1));
		assertThrows(IllegalArgumentException.class, ()-> new ItemAvaliado(i1, null));
		assertEquals(1, item1.getId());
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", item1.toString());
	}
	
	@Test
	public void testCalculaPontuacao() {
		assertEquals(30, item1.getPontuacao());
		assertEquals(30, item2.getPontuacao());
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(-1, item1.compareTo(item2));
		
		Item i1 = new Item("cadeira de rodas", 5, "80kg,conforto".split(","), 1);
		Item i2 = new Item("cadeira de rodas", 7, "roda grande".split(","), 2);
		assertEquals(-1, item1.compareTo(new ItemAvaliado(i1, i2)));
	}

}
