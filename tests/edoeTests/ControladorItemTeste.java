package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controladores.ControladorItem;
import entidades.Item;

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
	
	@Test
	void testGetItensSemelhantes() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho", 2, "confortavel,seminova" , "itemDoado");
		String teste = "";
		for (Item i : ci.match(2)) {
			teste += i.toString();
		}
		assertEquals("6 - cama, mesa, banho, tags: [confortavel, seminova], quantidade: 2", teste);
	}
	
	@Test
	void testGetItensSemelhantes2() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho", 2, "confortavel,seminova" , "itemDoado");
		ci.adicionaItem("11846019480123", "Cama, Mesa, Banho ", 2, "linda,estampada,confortavel" , "itemNecessario");
		String teste = "";
		for (Item i : ci.match(7)) {
			teste += i.toString();
		}
		assertEquals("6 - cama, mesa, banho, tags: [confortavel, seminova], quantidade: 2", teste);
	}
	
	@Test
	void testGetItensSemelhantesInexistente() {
		String teste = "";
		for (Item i : ci.match(2)) {
			teste += i.toString();
		}
		assertEquals("", teste);
	}
	
	@Test
	void testeRealizaDoacao() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho ", 3, "confortavel,lar doce lar" , "itemDoado");
		ci.realizaDoacao(1, "Receptor", 6, "Doador", "12/07/2018");
		assertEquals("12/07/2018 - doador: Doador/10046019482, item: cama, mesa, banho, quantidade: 1, receptor: Receptor/10046019480123", ci.listaDoacoes());
		
	}
	
	@Test
	void testeRealizaDoacaoDescricaoDiferente() {
		ci.adicionaItem("10046019482", "Skates", 3, "radical" , "itemDoado");
		try {
			ci.realizaDoacao(1, "Receptor", 6, "Doador", "11/11/2011");
		} catch (IllegalArgumentException ia) {
			
		}		
	}
	
	@Test
	void testeRealizaDoacaoIdFalso() {
		try {
			ci.realizaDoacao(1, "Receptor", 100, "Doador", "11/11/2011");
		} catch (IllegalArgumentException ia) {
			
		}		
	}
	
	@Test
	void testeRealizaDoacaoQuantidades1() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho", 1, "Cheiroso" , "itemDoado");
		ci.realizaDoacao(2, "Receptor", 6, "Doador", "10/10/1911");
		assertEquals("10/10/1911 - doador: Doador/10046019482, item: cama, mesa, banho, quantidade: 1, receptor: Receptor/11846019480123", ci.listaDoacoes());

	}
	
	@Test
	void testeRealizaDoacaoQuantidades2() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho", 2, "Cheiroso" , "itemDoado");
		ci.realizaDoacao(2, "Receptor", 6, "Doador", "10/10/1911");
		assertEquals("10/10/1911 - doador: Doador/10046019482, item: cama, mesa, banho, quantidade: 2, receptor: Receptor/11846019480123", ci.listaDoacoes());

	}
	
	@Test
	void testeVerificaItensExclusao() {
		ci.adicionaItem("10046019482", "Skates", 3, "radical" , "itemDoado");
		try {
			ci.verificaItensExclusao(1, 6);
		} catch (IllegalArgumentException ia) {
			
		}		
	}
	
	@Test
	void testListaDoacao() {
		ci.adicionaItem("10046019482", "Cama, Mesa, Banho", 2, "Cheiroso" , "itemDoado");
		ci.realizaDoacao(2, "Receptor", 6, "Doador", "10/10/1911");
		ci.adicionaItem("10046019482", "Skate", 3, "radical, bem loko" , "itemDoado");
		ci.adicionaItem("10046019480123", "skate ", 2, "Novinho, radical" , "itemNecessario");
		ci.realizaDoacao(8, "Receptor", 7, "Doador", "10/10/1911");
		assertEquals("10/10/1911 - doador: Doador/10046019482, item: cama, mesa, banho, quantidade: 2, receptor: Receptor/11846019480123 |"
				+ " 10/10/1911 - doador: Doador/10046019482, item: skate, quantidade: 2, receptor: Receptor/10046019480123",ci.listaDoacoes());
	}
	
	@Test
	void testVerificaItensExclusao() {
		ci.adicionaItem("10046019482", "Skate", 3, "radical, bem loko" , "itemDoado");
		ci.adicionaItem("10046019480123", "skate ", 2, "Novinho, radical" , "itemNecessario");
		List<Integer> lista = new ArrayList<>();
		lista.add(7);
		assertEquals(lista, ci.verificaItensExclusao(6, 7));
	}
	
	@Test
	void testVerificaItensExclusaoQuantidade1() {
		ci.adicionaItem("10046019482", "Skate", 2, "radical, bem loko" , "itemDoado");
		ci.adicionaItem("10046019480123", "skate ", 6, "Novinho, radical" , "itemNecessario");
		List<Integer> lista = new ArrayList<>();
		lista.add(6);
		assertEquals(lista, ci.verificaItensExclusao(6, 7));
	}
	
	@Test
	void testVerificaItensExclusaoQuantidade2() {
		ci.adicionaItem("10046019482", "Skate", 4, "radical, bem loko" , "itemDoado");
		ci.adicionaItem("10046019480123", "skate ", 4, "Novinho, radical" , "itemNecessario");
		List<Integer> lista = new ArrayList<>();
		lista.add(6);
		lista.add(7);
		assertEquals(lista,ci.verificaItensExclusao(6, 7));
	}
}
