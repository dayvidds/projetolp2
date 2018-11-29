package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import entidade.Status;
import entidade.TipoUsuario;
import entidade.Usuario;

class UsuarioTeste {

	@Test
	void testUsuario() {
		fail("Not yet implemented");
	}
 
	@Test
	void testGetItens() {
		fail("Not yet implemented");
	}


	@Test
	void testAdicionaItem() {
		fail("Not yet implemented");
	}

	@Test
	void testVerificaItem() {
		fail("Not yet implemented");
	}

	@Test
	void testExibeItem() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaItem() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveItem() {
		fail("Not yet implemented");
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
