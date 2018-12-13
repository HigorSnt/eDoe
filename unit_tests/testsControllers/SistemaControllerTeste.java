package testsControllers;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import controller.SistemaController;

class SistemaControllerTeste {
	
	private SistemaController sisc = new SistemaController();
	
	@BeforeEach
	public void cadastrandoUsuarios() throws FileNotFoundException {
		this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA");
		this.sisc.adicionaDoador("08704413000240", "Paroquia de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA");
		this.sisc.adicionaDoador("49847103331", "Aramis Araujo", "aramis@gmail.com", "(83) 98249-1298", "PESSOA_FISICA");
		this.sisc.lerReceptores("arquivos_sistema/novosReceptores.csv");
		this.sisc.adicionaDescritor("travesseiro");
		this.sisc.adicionaDescritor("camiseta");
	}
	
	@BeforeEach
	public void adicionaItem() {
		this.sisc.adicionaItemNecessario("51567490000143", "travesseiro", 2, "travesseiro de pena,conforto,dormir");
		this.sisc.adicionaItemParaDoacao("70513372911", "camiseta", 5, "outfit,algodao");
		this.sisc.adicionaItemParaDoacao("70513372911", "travesseiro", 2, "travesseiro de pena");
		this.sisc.adicionaItemParaDoacao("08704413000240" ,"camiseta", 100, "estilo,poliester,outfit");
		this.sisc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto");
		this.sisc.adicionaItemNecessario("24875800037", "Sabonete", 8, "Higiene");
		this.sisc.adicionaItemNecessario("58090077080", "camiseta", 5, "poliester, outfit");
		this.sisc.adicionaItemParaDoacao("49847103331", "sabonete", 3, "Higiene");
	}
	
	@Test
	public void testAdicionaDoador() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null,"PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", null));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "       "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "PADARIA"));
		
		// Usuario ja cadastrado
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDoador("08704413000240", "Igreja de Nossa Senhora da Conceicao", 
				"paroquia.da.conceicao@gmail.com", "(83) 3321-3140", "IGREJA"));
	}
	
	@Test
	public void testAdicionaReceptor() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor(null, "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("      ", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", null, "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", null, 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "", 
				"(83) 92918-0211","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"        ","PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				null,"PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", null));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "       "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("70513372911", "Elizabeth Ashe", "elizabethcalamity@deadlock.com", 
				"(83) 92918-0211", "PADARIA"));
		// Usuario ja cadastrado
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaReceptor("84473712044", "Murilo Luiz Brito", "muriloluizbrito-81@ipmmi.org.br",
				"(31) 99776-7434", "PESSOA_FISICA"));
	}
	
	@Test
	public void testPesquisaUsuarioPorId() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorId(null));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorId("   "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorId("000000000000000"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", this.sisc.pesquisaUsuarioPorId("70513372911"));
		assertEquals("Paroquia de Nossa Senhora da Conceicao/08704413000240, paroquia.da.conceicao@gmail.com, (83) 3321-3140, status: doador", this.sisc.pesquisaUsuarioPorId("08704413000240"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", this.sisc.pesquisaUsuarioPorId("84473712044"));
		assertEquals("Marcos Vinicius/55570865000185, vicentenogueira@securitycontrol.com.br, (79) 98960-2983, status: receptor", this.sisc.pesquisaUsuarioPorId("55570865000185"));
	}
	
	@Test
	public void testPesquisaUsuarioPorNome() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorNome(null));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorNome("   "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorNome("Daenerys Targaryen"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@deadlock.com, (83) 92918-0211, status: doador", this.sisc.pesquisaUsuarioPorNome("Elizabeth Ashe"));
		assertEquals("Paroquia de Nossa Senhora da Conceicao/08704413000240, paroquia.da.conceicao@gmail.com, (83) 3321-3140, status: doador", this.sisc.pesquisaUsuarioPorNome("Paroquia de Nossa Senhora da Conceicao"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", this.sisc.pesquisaUsuarioPorNome("Murilo Luiz Brito"));
		assertEquals("Marcos Vinicius/55570865000185, vicentenogueira@securitycontrol.com.br, (79) 98960-2983, status: receptor", this.sisc.pesquisaUsuarioPorNome("Marcos Vinicius"));
		
		this.sisc.adicionaDoador("10357071312", "Lucas Fernandes", "amigao@gmail.com", "(83) 94813-4871", "PESSOA_FISICA");
		assertEquals("Lucas Fernandes/85618414490, lucasoliveira@gmail.com, (83) 99845-9283, status: receptor"+
			" | Lucas Fernandes/10357071312, amigao@gmail.com, (83) 94813-4871, status: doador", 
			this.sisc.pesquisaUsuarioPorNome("Lucas Fernandes"));
	}
	
	@Test
	public void testAlteraDadosDoador() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.alteraDadosDoador(null,"","",""));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.alteraDadosDoador("","","",""));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.alteraDadosDoador("000000000000000","","",""));
		
		//nome == null || nome == ""
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@mail.com, (83) 2918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", "", "elizabethcalamity@mail.com", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", null, "elizabethcalamity@gmail.com", "(83) 92918-0211"));
		// celular == null || celular == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@hotmail.com, (83) 92918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", "Elizabeth", "elizabethcalamity@hotmail.com", null));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", "Elizabeth Ashe", "elizabethcalamity@gmail.com", ""));
		// email == null || email == ""
		assertEquals("Elizabeth/70513372911, elizabethcalamity@gmail.com, (83) 2918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", "Elizabeth", "", "(83) 2918-0211"));
		assertEquals("Elizabeth Ashe/70513372911, elizabethcalamity@gmail.com, (83) 92918-0211, status: doador", 
				this.sisc.alteraDadosDoador("70513372911", "Elizabeth Ashe", null, "(83) 92918-0211"));
	}
	
	@Test
	public void testAlteraDadosReceptor() throws FileNotFoundException {
		assertEquals("Murilo Luiz Brito/84473712044, muriloluizbrito-81@ipmmi.org.br, (31) 99776-7434, status: receptor", this.sisc.pesquisaUsuarioPorId("84473712044"));
		this.sisc.lerReceptores("arquivos_sistema/atualizaReceptores.csv");
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", this.sisc.pesquisaUsuarioPorId("84473712044"));
		
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.alteraDadosReceptor("0000000000", "Murilo Luiz", "muriloluiz@ipmi.org.br", "(31) 9770-7474"));
		// nome == null || nome == ""
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmi.org.br, (31) 9770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", "  ", "muriloluiz@ipmi.org.br", "(31) 9770-7474"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", null, "muriloluiz@ipmmi.org.br", "(31) 99770-7474"));
		// celular == null  || celular == ""
		assertEquals("Murilo Luiz/84473712044, muriloluiz@mi.org.br, (31) 99770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", "Murilo Luiz", "muriloluiz@mi.org.br", "    "));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", "Murilo Luiz Brito", "muriloluiz@ipmmi.org.br", null));
		// e == null  || email == ""
		assertEquals("Murilo Luiz/84473712044, muriloluiz@ipmmi.org.br, (31) 9770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", "Murilo Luiz", null, "(31) 9770-7474"));
		assertEquals("Murilo Luiz Brito/84473712044, muriloluiz@ipmmi.org.br, (31) 99770-7474, status: receptor", 
					this.sisc.alteraDadosReceptor("84473712044", "Murilo Luiz Brito", "      ", "(31) 99770-7474"));
	}
	
	@Test
	public void testLerReceptores() {
		assertThrows(FileNotFoundException.class, ()-> this.sisc.lerReceptores("arquivos_sistema/atualiza.csv"));
	}
	
	@Test
	public void testRemoveUsuario() {
		this.sisc.removeUsuario("70513372911");
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeUsuario(null));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeUsuario(""));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeUsuario("00000000000"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaUsuarioPorId("70513372911"));
	}
	
	@Test
	public void testAdicionaDescritor() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDescritor("   "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDescritor(null));
		this.sisc.adicionaDescritor("Livro");
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaDescritor("liVrO"));
	}
	
	@Test
	public void testAdicionaItemNecessario() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario(null, "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario("  ", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario("70513372911", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario("1111111111111111", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", 0, "roda grande,80kg,conforto"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", -8, "roda grande,80kg,conforto"));
		assertEquals(5, this.sisc.adicionaItemNecessario("84473712044", "cAdEiRa de RoDaS", 7, "roda grande,80kg,conforto"));
	}
	
	@Test
	public void testAdicionaItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemParaDoacao(null, "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemParaDoacao("    ", "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemParaDoacao("50270271338", "camiseta", 5, "outfit,algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.adicionaItemParaDoacao("88770280096", "camiseta", 5, "outfit,algodao"));
		assertEquals(2, this.sisc.adicionaItemParaDoacao("70513372911", "camiseta", 5, "outfit,algodao"));
	}
	
	@Test
	public void testExibeItem() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.exibeItem(1, "58791093499"));
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5", this.sisc.exibeItem(2, "70513372911"));
		assertEquals("3 - travesseiro, tags: [travesseiro de pena], quantidade: 2", this.sisc.exibeItem(3, "70513372911"));
	}
	
	@Test
	public void testAtualizaItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.atualizaItemParaDoacao(2, "", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.atualizaItemParaDoacao(2, null, 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.atualizaItemParaDoacao(-1, "70513372911", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.atualizaItemParaDoacao(0, "70513372911", 4, "algodao"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.atualizaItemParaDoacao(2, "000000000", 4, "algodao"));
		assertEquals("2 - camiseta, tags: [algodao], quantidade: 6", this.sisc.atualizaItemParaDoacao(2, "70513372911", 6, "algodao"));
		assertEquals("2 - camiseta, tags: [algodao,  azul], quantidade: 6", this.sisc.atualizaItemParaDoacao(2, "70513372911", 0, "algodao, azul"));
		assertEquals("2 - camiseta, tags: [algodao], quantidade: 6", this.sisc.atualizaItemParaDoacao(2, "70513372911", -8, "algodao"));
	}
	
	@Test
	public void testAtualizaItemNecessario() {
		assertEquals("5 - cadeira de rodas, tags: [roda grande], quantidade: 7", this.sisc.atualizaItemNecessario(5, "84473712044", 7, "roda grande"));
	}
	
	@Test
	public void testRemoveItemParaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(0, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(-9, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(1, "    "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(1, null));
		
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5", this.sisc.exibeItem(2, "70513372911"));
		this.sisc.removeItem(2, "70513372911");
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.exibeItem(2, "70513372911"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(2, "00"));
	}
	
	@Test
	public void testListaDescritorDeItensParaDoacao() {
		assertEquals("7 - cadeira de rodas | 5 - camiseta | 3 - sabonete | 2 - travesseiro", this.sisc.listaDescritorDeItensParaDoacao());
	}
	
	@Test
	public void testListaItensParaDoacao() {
		this.sisc.adicionaItemParaDoacao("08704413000240", "cobertor", 5, "lencol,conforto");
		this.sisc.adicionaItemParaDoacao("49847103331", "camiseta", 4, "camiseta M");
		
		assertEquals("4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 100, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240 | "
				+ "2 - camiseta, tags: [outfit, algodao], quantidade: 5, doador: Elizabeth Ashe/70513372911 | "
				+ "9 - cobertor, tags: [lencol, conforto], quantidade: 5, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240 | "
				+ "10 - camiseta, tags: [camiseta M], quantidade: 4, doador: Aramis Araujo/49847103331 | "
				+ "8 - sabonete, tags: [Higiene], quantidade: 3, doador: Aramis Araujo/49847103331 | "
				+ "3 - travesseiro, tags: [travesseiro de pena], quantidade: 2, doador: Elizabeth Ashe/70513372911", this.sisc.listaItensParaDoacao());
	}
	
	@Test
	public void testPesquisaItemParaDoacaoPorDescricao() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaItemParaDoacaoPorDescricao(""));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.pesquisaItemParaDoacaoPorDescricao(null));
		this.sisc.adicionaItemParaDoacao("08704413000240", "camiseta", 8, "camisa bonita");
		assertEquals("2 - camiseta, tags: [outfit, algodao], quantidade: 5 | "
				+ "4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 100 | "
				+ "9 - camiseta, tags: [camisa bonita], quantidade: 8 | "
				+ "7 - camiseta, tags: [poliester,  outfit], quantidade: 5", this.sisc.pesquisaItemParaDoacaoPorDescricao("camiseta"));
	}
	
	@Test
	public void testListaItensNecessarios() {
		this.sisc.adicionaItemNecessario("51567490000143", "travesseiro", 2, "travesseiro de pena,conforto,dormir");
		assertEquals("1 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2, Receptor: Rafaela Beatriz/51567490000143 | "
				+ "5 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "6 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037 | "
				+ "7 - camiseta, tags: [poliester,  outfit], quantidade: 5, Receptor: Isabelly Alice Bernardes/58090077080", 
				this.sisc.listaItensNecessarios());
	}
	
	@Test
	public void testRemoveItemNecessario() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(0, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(-9, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(1, "    "));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(1, null));
		assertEquals("5 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7", this.sisc.exibeItem(5, "84473712044"));
		this.sisc.removeItem(5, "84473712044");
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.exibeItem(5, "84473712044"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.removeItem(45, "000"));
	}
	
	@Test
	public void testMatch() {
		assertEquals("4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 100, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240 | "
				+ "2 - camiseta, tags: [outfit, algodao], quantidade: 5, doador: Elizabeth Ashe/70513372911", this.sisc.match("58090077080", 7));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.match("1111111", 7));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.match("70513372911", 7));
	}
	
	@Test
	public void testRealizaDoacao() {
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.realizaDoacao(7, 3, "06/07/2021"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.realizaDoacao(10, 11, "09/12/2018"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.realizaDoacao(7, 4, ""));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.realizaDoacao(7, 4, null));
		
		assertEquals("1 - travesseiro, tags: [travesseiro de pena, conforto, dormir], quantidade: 2, Receptor: Rafaela Beatriz/51567490000143 | "
				+ "5 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "6 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037 | "
				+ "7 - camiseta, tags: [poliester,  outfit], quantidade: 5, Receptor: Isabelly Alice Bernardes/58090077080", this.sisc.listaItensNecessarios());
		
		assertEquals("4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 100", this.sisc.exibeItem(4, "08704413000240"));
		assertEquals("3 - travesseiro, tags: [travesseiro de pena], quantidade: 2", this.sisc.exibeItem(3, "70513372911"));
		assertEquals("8 - sabonete, tags: [Higiene], quantidade: 3", this.sisc.exibeItem(8, "49847103331"));
		
		assertEquals("06/07/2021 - doador: Paroquia de Nossa Senhora da Conceicao/08704413000240, "
				+ "item: camiseta, quantidade: 5, receptor: Isabelly Alice Bernardes/58090077080", this.sisc.realizaDoacao(7, 4, "06/07/2021"));
		assertEquals("4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 95", this.sisc.exibeItem(4, "08704413000240"));
		
		assertEquals("09/12/2018 - doador: Elizabeth Ashe/70513372911, "
				+ "item: travesseiro, quantidade: 2, receptor: Rafaela Beatriz/51567490000143", this.sisc.realizaDoacao(1, 3, "09/12/2018"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.exibeItem(3, "70513372911"));
		
		assertEquals("09/12/2018 - doador: Aramis Araujo/49847103331, "
				+ "item: sabonete, quantidade: 3, receptor: Sara Jennifer Vieira/24875800037", this.sisc.realizaDoacao(6, 8, "09/12/2018"));
		assertThrows(IllegalArgumentException.class, ()-> this.sisc.exibeItem(8, "49847103331"));
		
		assertEquals("5 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "6 - sabonete, tags: [Higiene], quantidade: 5, Receptor: Sara Jennifer Vieira/24875800037", this.sisc.listaItensNecessarios());
		
		assertEquals("09/12/2018 - doador: Aramis Araujo/49847103331, item: sabonete, quantidade: 3, receptor: Sara Jennifer Vieira/24875800037 | "
				+ "09/12/2018 - doador: Elizabeth Ashe/70513372911, item: travesseiro, quantidade: 2, receptor: Rafaela Beatriz/51567490000143 | "
				+ "06/07/2021 - doador: Paroquia de Nossa Senhora da Conceicao/08704413000240, item: camiseta, quantidade: 5, receptor: Isabelly Alice Bernardes/58090077080", 
				this.sisc.listaDoacoes());
	}
	
	@Test
	public void testPersistencia() throws IOException, ClassNotFoundException {
		this.sisc.realizaDoacao(7, 4, "06/07/2021");
		this.sisc.realizaDoacao(1, 3, "09/12/2018");
		
		this.sisc.finalizaSistema();
		this.sisc.iniciaSistema();
		assertEquals("4 - camiseta, tags: [estilo, poliester, outfit], quantidade: 95, doador: Paroquia de Nossa Senhora da Conceicao/08704413000240 | "
				+ "2 - camiseta, tags: [outfit, algodao], quantidade: 5, doador: Elizabeth Ashe/70513372911 | "
				+ "8 - sabonete, tags: [Higiene], quantidade: 3, doador: Aramis Araujo/49847103331", this.sisc.listaItensParaDoacao());
		
		assertEquals("5 - cadeira de rodas, tags: [roda grande, 80kg, conforto], quantidade: 7, Receptor: Murilo Luiz Brito/84473712044 | "
				+ "6 - sabonete, tags: [Higiene], quantidade: 8, Receptor: Sara Jennifer Vieira/24875800037", this.sisc.listaItensNecessarios());
		
		assertEquals("8 - sabonete, tags: [Higiene], quantidade: 3, doador: Aramis Araujo/49847103331", this.sisc.match("24875800037", 6));
		
		assertEquals("09/12/2018 - doador: Elizabeth Ashe/70513372911, item: travesseiro, quantidade: 2, receptor: Rafaela Beatriz/51567490000143 | "
				+ "06/07/2021 - doador: Paroquia de Nossa Senhora da Conceicao/08704413000240, item: camiseta, quantidade: 5, receptor: Isabelly Alice Bernardes/58090077080", 
				this.sisc.listaDoacoes());
	}
	
}