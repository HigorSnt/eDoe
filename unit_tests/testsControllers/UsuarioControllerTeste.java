package testsControllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import controllers.UsuarioController;

class UsuarioControllerTeste {
	
	private UsuarioController uc = new UsuarioController();
	
	@BeforeEach
	void cadastrandoUsuarios() {
		uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA");
		uc.adicionaDoador("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA");
		uc.lerReceptores("arquivos_sistema/novosReceptores.csv");
	}
	
	@Test
	void testAdicionaDoador() {
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null,"PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", null));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "       "));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "PADARIA"));
		
		// Usuario ja cadastrado
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDoador("08704413000240", "Igreja de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA"));
		assertTrue(uc.contemUsuarioDoador("70513372911"));
		assertTrue(uc.contemUsuarioDoador("08704413000240"));
		assertFalse(uc.contemUsuarioDoador("0000000000000"));
		// id de Receptor
		assertFalse(uc.contemUsuarioDoador("84473712044"));
	}
	
	@Test
	void testAdicionaReceptor() {
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null,"PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", null));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "       "));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "PADARIA"));
		// Usuario ja cadastrado
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaReceptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br",
				"(31) 99776-7434", "PESSOA_FISICA"));
		
	}
	
	@Test
	void testPesquisaUsuarioPorId() {
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorId(null));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorId("   "));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorId("000000000000000"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", uc.pesquisaUsuarioPorId("70513372911"));
		assertEquals("Paroquia de Nossa Senhora da Conceicao/08704413000240, paroquia.da.conceicao@gmail.com, (83) 3321-3140, status: doador", uc.pesquisaUsuarioPorId("08704413000240"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", uc.pesquisaUsuarioPorId("84473712044"));
		assertEquals("Marcos Vinicius/55570865000185, vicentenogueira@securitycontrol.com.br, (79) 98960-2983, status: receptor", uc.pesquisaUsuarioPorId("55570865000185"));
	}
	
	@Test
	void testPesquisaUsuarioPorNome() {
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorNome(null));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorNome("   "));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorNome("Daenerys Targaryen"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", uc.pesquisaUsuarioPorNome("Elizabeth Ashe"));
		assertEquals("Paroquia de Nossa Senhora da Conceicao/08704413000240, paroquia.da.conceicao@gmail.com, (83) 3321-3140, status: doador", uc.pesquisaUsuarioPorNome("Paroquia de Nossa Senhora da Conceicao"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", uc.pesquisaUsuarioPorNome("Murilo Luiz Brito"));
		assertEquals("Marcos Vinicius/55570865000185, vicentenogueira@securitycontrol.com.br, (79) 98960-2983, status: receptor", uc.pesquisaUsuarioPorNome("Marcos Vinicius"));
		
		uc.adicionaDoador("10357071312", "Lucas Fernandes", "amigao@gmail.com", "(83) 94813-4871", "PESSOA_FISICA");
		assertEquals("Lucas Fernandes/85618414490, lucasoliveira@gmail.com, (83) 99845-9283, status: receptor"+
			" | Lucas Fernandes/10357071312, amigao@gmail.com, (83) 94813-4871, status: doador", 
			uc.pesquisaUsuarioPorNome("Lucas Fernandes"));
	}
	/**
	 * uc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA");
	 * 
	 */
	@Test
	void testAlteraDados() {
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", uc.pesquisaUsuarioPorId("84473712044"));
		uc.lerReceptores("arquivos_sistema/atualizaReceptores.csv");
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", uc.pesquisaUsuarioPorId("84473712044"));
		
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDados(null,"","",""));
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDados("","","",""));
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDados("000000000000000","","",""));
		//nome == null || nome == ""
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@mail.com, (83) 2918-0211, status: doador", 
				uc.alteraDados("70513372911", "", "elizabethcalamity@mail.com", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDados("70513372911", null, "elizabethcalamity@gmail.com", "(83) 92918-0211"));
		// celular == null || celular == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@hotmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDados("70513372911", "Elizabeth", "elizabethcalamity@hotmail.com", null));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDados("70513372911", "Elizabeth Ashe", "elizabethcalamity@gmail.com", ""));
		// email == null || email == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@gmail.com, (83) 2918-0211, status: doador", 
				uc.alteraDados("70513372911", "Elizabeth", "", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDados("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211"));
	}
	
	
	/*@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	void test() {
		exceptionRule.expect(FileNotFoundException.class);
		uc.lerReceptores("arquivos_sistema/atualiza.csv");
	}*/
	
	@Test
	void testRemoveUsuario() {
		uc.removeUsuario("70513372911");
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario(null));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario(""));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario("00000000000"));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorId("70513372911"));
	}

}
