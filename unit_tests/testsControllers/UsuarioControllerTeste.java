package testsControllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
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
		uc.adicionaDescritor("travesseiro");
		uc.adicionaDescritor("camiseta");
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
	
	@Test
	void testAlteraDadosDoador() {
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDadosDoador(null,"","",""));
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDadosDoador("","","",""));
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDadosDoador("000000000000000","","",""));
		
		//nome == null || nome == ""
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@mail.com, (83) 2918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", "", "elizabethcalamity@mail.com", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", null, "elizabethcalamity@gmail.com", "(83) 92918-0211"));
		// celular == null || celular == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@hotmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", "Elizabeth", "elizabethcalamity@hotmail.com", null));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@gmail.com", ""));
		// email == null || email == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@gmail.com, (83) 2918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", "Elizabeth", "", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				uc.alteraDadosDoador("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211"));
	}
	
	@Test
	void testAlteraDadosReceptor() {
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", uc.pesquisaUsuarioPorId("84473712044"));
		uc.lerReceptores("arquivos_sistema/atualizaReceptores.csv");
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", uc.pesquisaUsuarioPorId("84473712044"));
		
		assertThrows(IllegalArgumentException.class, ()-> uc.alteraDadosReceptor("0000000000", "Murilo Luiz", "muriloluiz@ipmi.org.br", "(31) 9770-7474"));
		// nome == null || nome == ""
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmi.org.br, (31) 9770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", "  ", "muriloluiz@ipmi.org.br", "(31) 9770-7474"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", null, "muriloluiz@ipmmi.org.br", "(31) 99770-7474"));
		// celular == null  || celular == ""
		assertEquals("Murilo Luiz/84473712044, muriloluiz@mi.org.br, (31) 99770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", "Murilo Luiz", "muriloluiz@mi.org.br", "    "));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", "Murilo Luiz Brito", "muriloluiz@ipmmi.org.br", null));
		// e == null  || email == ""
		assertEquals("Murilo Luiz/84473712044, muriloluiz@ipmmi.org.br, (31) 9770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", "Murilo Luiz", null, "(31) 9770-7474"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					uc.alteraDadosReceptor("84473712044", "Murilo Luiz Brito", "      ", "(31) 99770-7474"));
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	void testLerReceptores() {
		exceptionRule.expect(FileNotFoundException.class);
		uc.lerReceptores("arquivos_sistema/atualiza.csv");
	}
	
	@Test
	void testRemoveUsuario() {
		uc.removeUsuario("70513372911");
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario(null));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario(""));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeUsuario("00000000000"));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaUsuarioPorId("70513372911"));
	}
	
	@Test
	void testAdicionaDescritor() {
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDescritor("   "));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDescritor(null));
		uc.adicionaDescritor("Livro");
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaDescritor("liVrO"));
	}
	
	@Test
	void testAdicionaItemNecessario() {
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemNecessario(null, "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemNecessario("  ", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemNecessario("70513372911", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemNecessario("1111111111111111", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertEquals(4, uc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
	}
	
	@Test
	void testAdicionaItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemParaDoacao(null, "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemParaDoacao("    ", "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemParaDoacao("50270271338", "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.adicionaItemParaDoacao("88770280096", "camiseta", 5, "outfit,algodao"));
		assertEquals(2, uc.adicionaItemParaDoacao("70513372911", "camiseta", 5, "outfit,algodao"));
	}
	
	@BeforeEach
	void adicionaItem() {
		uc.adicionaItemNecessario("51567490000143", "travesseiro", 2, "travesseiro de pena,conforto,dormir");
		uc.adicionaItemParaDoacao("70513372911", "camiseta", 5, "outfit,algodao");
		uc.adicionaItemParaDoacao("70513372911", "travesseiro", 10, "travesseiro de pena");
		uc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto");
		uc.adicionaItemNecessario("24875800037", "Sabonete", 8, "Higiene");
	}
	
	@Test
	void testExibeItem() {
		assertThrows(IllegalArgumentException.class, ()-> uc.exibeItem(1, "58791093499"));
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5", uc.exibeItem(2, "70513372911"));
		assertEquals("3 - travesseiro, tags: [travesseiro de pena], quantidade: 10", uc.exibeItem(3, "70513372911"));
	}
	
	@Test
	void testAtualizaItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> uc.atualizaItemParaDoacao(2, "", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.atualizaItemParaDoacao(2, null, 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.atualizaItemParaDoacao(-1, "70513372911", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.atualizaItemParaDoacao(0, "70513372911", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> uc.atualizaItemParaDoacao(2, "000000000", 4, "algodao"));
		assertEquals("2 - camiseta, tags: [algodao], quantidade: 6", uc.atualizaItemParaDoacao(2, "70513372911", 6, "algodao"));
		assertEquals("2 - camiseta, tags: [algodao,  azul], quantidade: 6", uc.atualizaItemParaDoacao(2, "70513372911", 0, "algodao, azul"));
		assertEquals("2 - camiseta, tags: [algodao], quantidade: 6", uc.atualizaItemParaDoacao(2, "70513372911", -8, "algodao"));
	}
	
	@Test
	void testAtualizaItemNecessario() {
		assertEquals("4 - cadeira de rodas, tags: [roda grande], quantidade: 7", uc.atualizaItemNecessario(4, "84473712044", 7, "roda grande"));
	}
	
	@Test
	void testRemoveItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(0, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(-9, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(1, "    "));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(1, null));
		
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5", uc.exibeItem(2, "70513372911"));
		uc.removeItemParaDoacao(2, "70513372911");
		assertThrows(IllegalArgumentException.class, ()-> uc.exibeItem(2, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(2, "00"));
	}
	
	@Test
	void testeListaDescritorDeItensParaDoacao() {
		assertEquals("7 - cadeira de rodas | 5 - camiseta | 8 - sabonete | 10 - travesseiro", uc.listaDescritorDeItensParaDoacao());
	}
	
	@Test
	void testeListaItensParaDoacao() {
		uc.adicionaItemParaDoacao("08704413000240", "cobertor", 10, "lencol,conforto");
		uc.adicionaItemParaDoacao("08704413000240", "camiseta", 4, "camiseta M");
		//uc.adicionaItemParaDoacao("08704413000240", "cobertor", 10, "lencol,conforto");
		assertEquals("6 - cobertor, tags: [lencol, conforto], quantidade: 10, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240 | "
				+ "3 - travesseiro, tags: [travesseiro de pena], quantidade: 10, doador: Elizabeth Ashe/70513372911 | "
				+ "2 - camiseta, tags: [outfit, algodao], quantidade: 5, doador: Elizabeth Ashe/70513372911 | "
				+ "7 - camiseta, tags: [camiseta M], quantidade: 4, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240", uc.listaItensParaDoacao());
	}
	
	@Test
	void testePesquisaItemParaDoacaoPorDescricao() {
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaItemParaDoacaoPorDescricao(""));
		assertThrows(IllegalArgumentException.class, ()-> uc.pesquisaItemParaDoacaoPorDescricao(null));
		uc.adicionaItemParaDoacao("08704413000240", "camiseta", 8, "camisa bonita");
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5 | "
				+ "6 - camiseta, tags: [camisa bonita], quantidade: 8", uc.pesquisaItemParaDoacaoPorDescricao("camiseta"));
	}
	
	@Test
	void testeListaItensNecessarios() {
		uc.adicionaItemNecessario("51567490000143", "travesseiro", 2, "travesseiro de pena,conforto,dormir");
		assertEquals("1 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2, Receptor: Rafaela Beatriz/51567490000143 | "
				+ "4 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "5 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037", uc.listaItensNecessarios());
	}
	
	@Test
	void testeRemoveItemNecessario() {
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(0, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(-9, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(1, "    "));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemParaDoacao(1, null));
		assertEquals("4 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7", uc.exibeItem(4, "84473712044"));
		uc.removeItemNecessario("84473712044", 4);
		assertThrows(IllegalArgumentException.class, ()-> uc.exibeItem(4, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> uc.removeItemNecessario("000", 45));
	}
	
}