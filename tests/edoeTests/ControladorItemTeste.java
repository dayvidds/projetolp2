package edoeTests;

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
		ci.adicionaDescritor("CAMISETA");
		ci.adicionaDescritor("lencol");
		ci.adicionaItem("10046019482", "cadeira", 2, "confortavel,seminova" , "itemDoado");
		ci.adicionaItem("10046019480123", "Cama, Mesa, Banho ", 1, "lar doce lar" , "itemNecessario");
		ci.adicionaItem("11846019480123", "Cama, Mesa, Banho ", 2, "confortavel" , "itemNecessario");
		ci.adicionaItem("10036985741", "lencol", 5, "branco,fino", "itemDoado");
		ci.adicionaItem("52234712580", "CADEIRA", 10, "", "itemDoado");
		ci.adicionaItem("21036587452", "camiseta", 8, "algodao,preta", "itemDoado");
		

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
		assertEquals("12 - cadeira | 0 - cama, mesa, banho | 8 - camiseta | 5 - lencol", ci.listaDescritorDeItensParaDoacao());
		ci.removeItem(2, "itemDoado");
		assertEquals("12 - cadeira | 0 - cama, mesa, banho | 8 - camiseta | 0 - lencol", ci.listaDescritorDeItensParaDoacao());
	}

	@Test
	void testOrdenaItensPorQuantidade() {
		assertEquals("[4 - cadeira, tags: [], quantidade: 10, 5 - camiseta, tags: [algodao, preta], quantidade: 8, 3 - lencol, tags: [branco, fino], quantidade: 5, 0 - cadeira, tags: [confortavel, seminova], quantidade: 2]", ci.ordenaItensPorQuantidade());
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		assertEquals("0 - cadeira, tags: [confortavel, seminova], quantidade: 2 | 4 - cadeira, tags: [], quantidade: 10", ci.pesquisaItemParaDoacaoPorDescricao("CADEIRa"));
		assertEquals("3 - lencol, tags: [branco, fino], quantidade: 5", ci.pesquisaItemParaDoacaoPorDescricao("lencol"));
		assertThrows(IllegalArgumentException.class, () -> ci.pesquisaItemParaDoacaoPorDescricao(""));
	}

}
