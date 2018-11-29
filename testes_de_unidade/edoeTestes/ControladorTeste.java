package edoeTestes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.ControladorItem;
import Controller.ControladorUsuario;

class ControladorTeste {

	private ControladorItem ci;
	private ControladorUsuario cu;
	
	@BeforeEach
	void setUp() {
		ci = new ControladorItem();
		cu = new ControladorUsuario();
		ci.adicionaDescritor(" CadeirA");
		ci.adicionaDescritor("Cama, Mesa, Banho ");
		cu.adicionaDoador("10046019482", "Thiago", "thiago123@hotmail.com", "(83) 92918-0211", "PESSOA_FISICA");
		ci.adicionaItem("10046019482", "cadeira", 2, "confortavel,seminova" , "itemDoado");
		cu.adicionaItem("10046019482", 0, ci.getItemId("Cadeira", 0, "itemDoado"), "itemDoado");
		cu.adicionaReceptor("10046019480123", "Instituto Rafael", "renovar@hotmail.com", "(84) 92456-1231", "ONG");
		ci.adicionaItem("10046019480123", "Cama, Mesa, Banho ", 1, "lar doce lar" , "itemNecessario");
		cu.adicionaItem("10046019480123", 1, ci.getItemId("Cama, Mesa, Banho ", 1, "itemNecessario"), "itemNecessario");
		cu.adicionaReceptor("11846019480123", "Instituto Rafael", "renovar@hotmail.com", "(84) 92456-1231", "ONG");
		ci.adicionaItem("11846019480123", "Cama, Mesa, Banho ", 2, "confortavel" , "itemNecessario");
		cu.adicionaItem("11846019480123", 2, ci.getItemId("Cama, Mesa, Banho ", 2, "itemNecessario"), "itemNecessario");
		
	}

	@Test
	void testAdicionaItemParaDoacao() {
		assertEquals("0 - cadeira, tags: [confortavel, seminova], quantidade: 2", cu.exibeItem("10046019482", 0));
	}
	
	@Test
	void testRemoveItemParaDoacao() {
		cu.removeItem(0, "10046019482");
		try {
			cu.exibeItem("10046019482", 0);
		} catch (IllegalArgumentException ie){
			
		}	
	}

	@Test
	void testListaItensParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testListaDescritorDeItensParaDoacao() {
		fail("Not yet implemented");
	}

	@Test
	void testPesquisaItemParaDoacaoPorDescricao() {
		fail("Not yet implemented");
	}

	@Test
	void testAdicionaItemNecessario() {
		assertEquals("1 - cama, mesa, banho, tags: [lar doce lar], quantidade: 1", cu.exibeItem("10046019480123", 1));
	}

	@Test
	void testListaItensNecessarios() {
		assertEquals("1 - cama, mesa, banho, tags: [lar doce lar], quantidade: 1, Receptor: Instituto Rafael/10046019480123 | "
				+ "2 - cama, mesa, banho, tags: [confortavel], quantidade: 2, Receptor: Instituto Rafael/11846019480123", cu.listaItensNecessarios());
	}

	@Test
	void testRemoveItemNecessario() {
		fail("Not yet implemented");
	}

}
