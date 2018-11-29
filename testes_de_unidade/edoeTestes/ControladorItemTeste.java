package edoeTestes;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.ControladorItem;

class ControladorItemTeste {

	private ControladorItem ci;
	
	@BeforeEach
	void setUp() {
		ci = new ControladorItem();
		ci.adicionaDescritor(" CadeirA");
		ci.adicionaDescritor("Cama, Mesa, Banho ");
		ci.adicionaItem("10046019482", "cadeira", 2, "confortavel,seminova" , "itemDoado");
		ci.adicionaItem("10046019480123", "Cama, Mesa, Banho ", 1, "lar doce lar" , "itemNecessario");
		ci.adicionaItem("11846019480123", "Cama, Mesa, Banho ", 2, "confortavel" , "itemNecessario");

	}

	@Test
	void testAdicionaDescritor() {
		fail("Not yet implemented");
		
	}

	@Test
	void testRemoveItemParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testGetItemId() {
		fail("Not yet implemented");
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testOrdenaItensPorQuantidade() {
		fail("Not yet implemented");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		fail("Not yet implemented");
	}

	@Test
	void testGetIdDoador() {
		fail("Not yet implemented");
	}

}
