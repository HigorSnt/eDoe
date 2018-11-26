package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Usuario;

class UsuarioTeste {
	
	private Usuario us1;
	private Usuario us2;
	private Usuario us3;
	
	@BeforeEach
	void cadastraUsuario() {
		us1 = new Usuario ("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", false);
		us2 = new Usuario ("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA", false);
		us3 = new Usuario ("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", true);
	}

	@Test
	void testConstrutor() {
		assertThrows(IllegalArgumentException.class, ()-> new Usuario(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ","PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null,"PESSOA_FISICA", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", null, false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "       ", false));
		assertThrows(IllegalArgumentException.class, ()-> new Usuario("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "PADARIA", false));
		
		assertFalse(us2.isEhReceptor());
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
				"paroquia.da.conceicao@gmail.com", "(83) 99982-9231", "PESSOA_FISICA", false);
		Usuario u2 = new Usuario("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@hotmail.com", "(83) 3321-3140", "PESSOA_FISICA", false);
		Usuario u3 = new Usuario("08704413000240", "Igreja de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "PESSOA_FISICA", false);
		Usuario u = new Usuario ("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA", false);
		
		assertFalse(us2.equals(u1));
		assertFalse(us2.equals(u2));
		assertFalse(us2.equals(u3));
		assertTrue(us2.equals(u));
	}

}
