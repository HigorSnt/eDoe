package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.ItemDoado;

class itemDoadoTeste {
	
	private ItemDoado item1;
	private ItemDoado item2;
	
	/*@BeforeEach
	void cadastraItens() {
		item1 = new ItemDoado("cadeira de rodas", 2, "roda grande,cadeira".split(","), 1);
		item2 = new ItemDoado("colchao", 4, "colchao kingsize,conforto,dormir".split(","), 2);
	}
	
	@Test
	void testItemDoado() {
		assertThrows(IllegalArgumentException.class, ()-> new ItemDoado(null, 5, "roda grande,cadeira".split(","), 1));
		assertThrows(IllegalArgumentException.class, ()-> new ItemDoado("    ", 5, "roda grande,cadeira".split(","), 1));
		assertThrows(IllegalArgumentException.class, ()-> new ItemDoado("cadeira de rodas", -5, "roda grande,cadeira".split(","), 1));
		assertThrows(IllegalArgumentException.class, ()-> new ItemDoado("cadeira de rodas", 0, "roda grande,cadeira".split(","), 1));
		
		assertEquals(1, item1.getId());
		assertEquals(2, item2.getId());
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 2", item1.toString());
		assertEquals("2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 4", item2.toString());
	}
	
	@Test
	void testSetters() {
		assertThrows(IllegalArgumentException.class, ()-> item1.setQuantidade(0));
		assertThrows(IllegalArgumentException.class, ()-> item1.setQuantidade(-8));
		
		item1.setQuantidade(8);
		item1.setTags("roda grande,cadeira,confortavel".split(","));
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira, confortavel], quantidade: 8", item1.toString());
	}*/

}
