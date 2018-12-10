package testsModels;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Doacao;

class DoacaoTeste {

	Doacao d1;
	
	@BeforeEach
	public void cadastrandoDoacao() {
		d1 = new Doacao("Aramis Araujo/49847103331", "Antonella Sonia Moraes/32719454000103", 
				"jaqueta de couro", 5, 3, "30/06/2013");
	}
	
	@Test
	public void testConstrutor() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse("30/06/2013", formatter);
		assertEquals(data, d1.getData());
		
		assertEquals("jaqueta de couro", d1.getDescricaoItemDoado());
		assertEquals("30/06/2013 - doador: Aramis Araujo/49847103331, "
				+ "item: jaqueta de couro, quantidade: 3, receptor: "
				+ "Antonella Sonia Moraes/32719454000103", d1.toString());
	}

}
