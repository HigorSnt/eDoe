package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.Classe;
import models.Item;
import models.Usuario;

class UsuarioTeste {
	
	private Usuario us1;
	private Usuario us2;
	private Usuario us3;
	
	@BeforeEach
	void cadastraUsuario() {
		us1 = new Usuario ("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false);
		us2 = new Usuario ("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", Classe.valueOf("IGREJA") , false);
		us3 = new Usuario ("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), true);
	}

	@Test
	void testConstrutor() {
		assertThrows(IllegalArgumentException.class, ()-> new Usuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211",  Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ", Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null, Classe.valueOf("PESSOA_FISICA"), false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", Classe.valueOf("PADARIA"), false));
		
		assertFalse(us2.isReceptor());
		assertEquals("Paroquia de Nossa Senhora da Conceicao", us2.getNome());
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", us1.toString());
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: receptor", us3.toString());
		assertEquals("Paroquia de Nossa Senhora da Conceicao/08704413000240, paroquia.da.conceicao@gmail.com, (83) 3321-3140, status: doador", us2.toString());
		assertEquals(-1349747326, us1.hashCode());
		assertEquals(-258594019, us2.hashCode());
	}
	
	@Test
	void testSetters() {
		assertThrows(IllegalArgumentException.class, ()-> us1.setCelular(null));
		assertThrows(IllegalArgumentException.class, ()-> us1.setCelular(""));
		assertThrows(IllegalArgumentException.class, ()-> us1.setNome(null));
		assertThrows(IllegalArgumentException.class, ()-> us1.setNome(" "));
		assertThrows(IllegalArgumentException.class, ()-> us1.setEmail(null));
		assertThrows(IllegalArgumentException.class, ()-> us1.setEmail(" "));
		
		us1.setCelular("(83) 4002-8922");
		us1.setEmail("elizabethcalamity@gmail.com");
		us1.setNome("Elizabeth");
		assertEquals("Elizabeth/70513372911, elizabethcalamity@gmail.com, (83) 4002-8922, status: doador", us1.toString());
	}
	
	@Test
	void testEquals() {
		assertTrue(us2.equals(us2));
		assertFalse(us2.equals(null));
		assertFalse(us2.equals("TESTE"));
		
		Usuario u1 = new Usuario("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 99982-9231", Classe.valueOf("PESSOA_FISICA"), false);
		Usuario u2 = new Usuario("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@hotmail.com", "(83) 3321-3140", Classe.valueOf("PESSOA_FISICA"), false);
		Usuario u3 = new Usuario("08704413000240", "Igreja de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", Classe.valueOf("PESSOA_FISICA"), false);
		Usuario u = new Usuario ("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", Classe.valueOf("IGREJA"), false);
		
		assertFalse(us2.equals(u1));
		assertFalse(us2.equals(u2));
		assertFalse(us2.equals(u3));
		assertTrue(us2.equals(u));
	}
	
	@BeforeEach
	void cadastrandoItens() {
		us1.adicionaItem("cadeira de rodas", 5, "roda grande,cadeira", 1);
		us1.adicionaItem("colchao", 5, "colchao kingsize,conforto,dormir", 2);
		us1.adicionaItem("calca jeans", 3, "", 3);
	}
	
	@Test
	void testAdicionaItemParaDoacao() {
		assertEquals("2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5", us1.exibeItem(2));
		us1.adicionaItem("colchao", 10, "colchao kingsize,conforto,dormir", 2);
		assertEquals("2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 10", us1.exibeItem(2));
		us1.adicionaItem("jaqueta de couro", 5, "outfit,couro de jacare", 4);
		us1.adicionaItem("jaqueta de couro", 5, "outfit,couro de cobra", 5);
		assertEquals("5 - jaqueta de couro, tags: [outfit, couro de cobra], quantidade: 5", us1.exibeItem(5));
	}
	
	@Test
	void testProcuraItensComNome() {
		List<Item> lista = new ArrayList<>();
		lista = us1.procuraItensComNome("colchao");
		assertEquals("2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5", lista.stream().map(i -> i.toString()).collect(Collectors.joining(" | ")));
	}
	
	@Test
	void testPegaTodosOsItens() {
		List<Item> itens = new ArrayList<>();
		itens = us1.pegaTodosOsItens();
		
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5 | "
				+ "2 - colchao, tags: [colchao kingsize, conforto, dormir], quantidade: 5 | "
				+ "3 - calca jeans, tags: [], quantidade: 3", itens.stream().map(i -> i.toString()).collect(Collectors.joining(" | ")));
	}
	
	@Test
	void testRepresentacaoParaListagemDeDoacao() {
		assertEquals("doador: Elizabeth Ashe/70513372911", us1.representacaoParaListagemDeDoacao());
		assertEquals("Receptor: Elizabeth Ashe/70513372911", us3.representacaoParaListagemDeDoacao());
	}
	
	@Test
	void testAtualizaItem() {
		assertThrows(IllegalArgumentException.class, ()-> us1.atualizaItem(10, 5, ""));
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", us1.exibeItem(1));
		us1.atualizaItem(1, 10, "");
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 10", us1.exibeItem(1));
		us1.atualizaItem(1, 0, "roda grande");
		assertEquals("1 - cadeira de rodas, tags: [roda grande], quantidade: 10", us1.exibeItem(1));
		us1.atualizaItem(1, 0, null);
		assertEquals("1 - cadeira de rodas, tags: [roda grande], quantidade: 10", us1.exibeItem(1));
	}
	
	@Test
	void testRemoveItem() {
		assertThrows(IllegalArgumentException.class, ()-> us1.removeItem(-4));
		assertThrows(IllegalArgumentException.class, ()-> us2.removeItem(1));
		assertEquals("1 - cadeira de rodas, tags: [roda grande, cadeira], quantidade: 5", us1.exibeItem(1));
		us1.removeItem(1);
		assertThrows(IllegalArgumentException.class, ()-> us1.exibeItem(1));
	}
	
	@Test
	void testGetDescricaoItem() {
		assertThrows(IllegalArgumentException.class, ()-> us1.getDescricaoItem(10));
		assertEquals("calca jeans", us1.getDescricaoItem(3));
	}

}
