package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entidade.Item;
import entidade.Status;
import entidade.TipoUsuario;
import entidade.Usuario;

class UsuarioTeste {

	private Usuario usuario;
	private Item item;
	
	@BeforeEach
	void setUp() {
		usuario = new Usuario("70328507407", "lucao", "aaaa", "98878", TipoUsuario.PESSOA_FISICA, Status.DOADOR);
		item = new Item("70328507407", 0, "mochila", 2, "sport");
		usuario.adicionaItem(0, item);
		
	}
	
	@Test
	void testAdicionaItem() {
		assertEquals(true, usuario.verificaItem(0));
		
	}

	@Test
	void testExibeItem() {
		assertEquals("0 - mochila, tags: [sport], quantidade: 2", usuario.exibeItem(0));
	}

	@Test
	void testAtualizaItem() {
		assertEquals("0 - mochila, tags: [sport], quantidade: 2", usuario.exibeItem(0));
		usuario.atualizaItem(0, 4, "");
		assertEquals("0 - mochila, tags: [sport], quantidade: 4", usuario.exibeItem(0));
		usuario.atualizaItem(0, 3, "Tendencia");
		assertEquals("0 - mochila, tags: [Tendencia], quantidade: 3", usuario.exibeItem(0));

	}

	@Test
	void testRemoveItem() {
		usuario.removeItem(0);
		assertEquals(false, usuario.verificaItem(0));
		
	}

	@Test
	void testEqualsObject() {
		Usuario usuario1 = new Usuario("70328507407", "lucao", "aaaa", "98878", TipoUsuario.PESSOA_FISICA, Status.DOADOR);
		Usuario usuario2 = new Usuario("70328507407", "lalalala", "sdgdvdf", "dfgdfgdf", TipoUsuario.ONG, Status.RECEPTOR);
		Usuario usuario3 = new Usuario("58064075490", "lucao", "aaaa", "98878", TipoUsuario.PESSOA_FISICA, Status.DOADOR);
		
		assertTrue(usuario1.equals(usuario2));
		assertFalse(usuario1.equals(usuario3));
	}
}
