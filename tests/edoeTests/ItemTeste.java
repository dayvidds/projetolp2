package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidades.Item;

class ItemTeste {
	
	private Item item;
	
	@BeforeEach
	void setUp() {
		item = new Item("70328507407", 0, "Camiseta ", 2, "Colorida");
	}

	@Test
	void testGetQuantidade() {
		assertEquals(2, item.getQuantidade());
		item.atualizaItem(3, "");
		assertEquals(3, item.getQuantidade());

	}

	@Test
	void testGetIdUsuario() {
		assertEquals("70328507407", item.getIdUsuario());
	}

	@Test
	void testGetIdItem() {
		assertEquals(0, item.getIdItem());
	}

	@Test
	void testGetDescricaoItem() {
		assertEquals("camiseta", item.getDescricaoItem());
	}

	@Test
	void testSetQuantidade() {
		assertEquals(2, item.getQuantidade());
		item.setQuantidade(4);
		assertEquals(4, item.getQuantidade());

	}

	@Test
	void testAtualizaItem() {
		assertEquals(2, item.getQuantidade());
		item.atualizaItem(3, "Chique,Moderno");
		assertEquals("0 - camiseta, tags: [Chique, Moderno], quantidade: 3", item.toString());

	}

	@Test
	void testCompareTo() {
		Item item2 = new Item("12328507456", 1, "Camiseta", 4, "Colorida");
		assertEquals(2, item.compareTo(item2));
	}
	
	@Test
	void testHashCode() {
		Item item2 = new Item("12328507456", 1, "Camiseta", 4, "Colorida");
		assertEquals(item.hashCode(), item2.hashCode());
	}

	@Test
	void testEqualsObject() {
		Item item2 = new Item("12328507456", 1, "Camiseta", 4, "Colorida");
		assertEquals(true, item.equals(item2));
	}
	
	@Test
	void testEqualsEleMesmo() {
		assertEquals(true, item.equals(item));
	}
	
	@Test
	void testEqualsNull() {
		assertEquals(false, item.equals(null));
	}
	
	@Test
	void testEqualsObjetosDiferentes() {
		String teste = "";
		assertEquals(false, item.equals(teste));
	}
	
	@Test
	void testEqualsTagsNull() {
		Item item1 = new Item("12328507456", 1, "Camiseta", 4, null);
		Item item2 = new Item("12328507456", 1, "Camiseta", 4, "Colorida");
		assertEquals(false, item1.equals(item2));
	}
	
	@Test
	void testEqualsTagsNullOther() {
		Item item1 = new Item("12328507456", 1, "Camiseta", 4, "Colorida");
		Item item2 = new Item("12328507456", 1, "Camiseta", 4, null);
		assertEquals(false, item1.equals(item2));
	}

}
