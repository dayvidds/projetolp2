package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.ControladorItem;
import entidade.Item;

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
		try {
			ci.adicionaDescritor("cadeira");
		} catch (IllegalArgumentException ia){
			
		}
		
	}
	

	@Test
	void testAdicionaItemSemDescritor() {
		ci.adicionaItem("10046019482", "cadeira", 3, "praia" , "itemDoado");
		Item item = new Item("10046019482", 7, "cadeira", 3, "praia");
		assertEquals(true, item.equals(ci.getItemId("cadeira", 6, "itemDoado")));
		
	}
	
	@Test
	void testAdicionaMesmoItem() {
		ci.adicionaItem("10046019482", " REMEDIOS", 3, "analgésico", "itemNecessario");
		ci.adicionaItem("10046019482", " REMEDIOS", 2, "analgésico", "itemNecessario");
		assertEquals("6 - remedios, tags: [analgésico], quantidade: 2", ci.getItemId("remedios", 6, "itemNecessario").toString());

	}

	@Test
	void testRemoveItemParaNecessarioDoacao() {
		ci.removeItem(1, "itemNecessario");
		assertEquals(null, ci.getItemId("Cama, Mesa, Banho ", 1, "itemNecessario"));
		
	}
	
	@Test
	void testRemoveItemFalsoParaDoacao() {
		try {
			ci.removeItem(0, "teste");
		} catch (IllegalArgumentException ia){
			
		}
		
	}

	@Test
	void testGetItemId() {
		fail("Not yet implemented");
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		assertEquals("12 - cadeira | 0 - cama, mesa, banho | 8 - camiseta | 5 - lencol", ci.listaDescritorDeItensParaDoacao());
		ci.removeItem(3, "itemDoado");
		assertEquals("12 - cadeira | 0 - cama, mesa, banho | 8 - camiseta | 0 - lencol", ci.listaDescritorDeItensParaDoacao());
	}

	@Test
	void testOrdenaItensPorQuantidade() {
		ArrayList<Item> itensTeste = new ArrayList<>();
		itensTeste.add(new Item("52234712580", 4,"cadeira", 10, ""));
		itensTeste.add(new Item("21036587452", 5,"camiseta", 8, "algodao,preta"));
		itensTeste.add(new Item("10036985741", 3, "lencol", 5, "branco,fino"));
		itensTeste.add(new Item("10046019482", 0, "cadeira", 2, "confortavel,seminova"));
		assertEquals(itensTeste, ci.ordenaItensPorQuantidade());
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		assertEquals("0 - cadeira, tags: [confortavel, seminova], quantidade: 2 | 4 - cadeira, tags: [], quantidade: 10", ci.pesquisaItemParaDoacaoPorDescricao("CADEIRa"));
		assertEquals("3 - lencol, tags: [branco, fino], quantidade: 5", ci.pesquisaItemParaDoacaoPorDescricao("lencol"));
		assertThrows(IllegalArgumentException.class, () -> ci.pesquisaItemParaDoacaoPorDescricao(""));
	}

}
