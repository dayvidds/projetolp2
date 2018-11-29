package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.ControladorUsuario;
/**
 * Classe para teste do ControladorUsuarios
 * 
 * @author matheus augusto
 *
 */
class ControladorUsuarioTeste {

	private ControladorUsuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new ControladorUsuario();
		// Cadastrando usuarios doadores para futuros testes
		usuario.adicionaDoador("70513372911", "nome", "email", "000000000", "PESSOA_FISICA");
		usuario.adicionaDoador("08704413000240", "nome", "email", "000000000", "IGREJA");
		usuario.adicionaDoador("1000000000000", "nome", "email", "000000000", "ORGAO_PUBLICO_MUNICIPAL");
		usuario.adicionaDoador("2000000000000", "nome", "email", "000000000", "ORGAO_PUBLICO_ESTADUAL");
		usuario.adicionaDoador("3000000000000", "nome", "email", "000000000", "ORGAO_PUBLICO_FEDERAL");
		usuario.adicionaDoador("4000000000000", "nome", "email", "000000000", "ONG");
		usuario.adicionaDoador("5000000000000", "nome", "email", "000000000", "ASSOCIACAO");
		usuario.adicionaDoador("6000000000000", "nome", "email", "000000000", "SOCIEDADE");
		// Cadastrando usuarios receptores para testes futuros
		usuario.adicionaReceptor("70513654911", "nome", "email", "000000000", "PESSOA_FISICA");
		usuario.adicionaReceptor("08704413987240", "nome", "email", "000000000", "IGREJA");
		usuario.adicionaReceptor("0000000000010", "nome", "email", "000000000", "ORGAO_PUBLICO_MUNICIPAL");
		usuario.adicionaReceptor("0000000000020", "nome", "email", "000000000", "ORGAO_PUBLICO_ESTADUAL");
		usuario.adicionaReceptor("0000000000030", "nome", "email", "000000000", "ORGAO_PUBLICO_FEDERAL");
		usuario.adicionaReceptor("0000000000040", "nome", "email", "000000000", "ONG");
		usuario.adicionaReceptor("0000000000050", "nome", "email", "000000000", "ASSOCIACAO");
		usuario.adicionaReceptor("0000000000060", "nome", "email", "000000000", "SOCIEDADE");
		

	}
	@Test
	void testAdicionaDoador() {
		// Testando se o usuario etá sendo adcionado.
		assertEquals("70513372922",
				usuario.adicionaDoador("70513372922", "nome", "email", "000000000", "PESSOA_FISICA"));
		assertEquals("08704413000290",
				usuario.adicionaDoador("08704413000290", "nome", "email", "000000000", "IGREJA"));
		assertEquals("08704413009876",
				usuario.adicionaDoador("08704413009876", "nome", "email", "000000000", "ORGAO_PUBLICO_MUNICIPAL"));
		assertEquals("12344413000290", usuario.adicionaDoador("12344413000290", "nome", "email", "000000000", "ONG"));
		assertEquals("08704768500290",
				usuario.adicionaDoador("08704768500290", "nome", "email", "000000000", "ASSOCIACAO"));
		// Testando as Excecoes
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaDoador("08704768500290", " ", "email", "000000000", "ASSOCIACAO"));
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaDoador("08704768500290", " ", " ", "000000000", "ASSOCIACAO"));
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaDoador("08704768500290", "nome", "email", " ", " "));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaDoador(null, "nome", "email", "000000000", "ASSOCIACAO"));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaDoador("70513372922", null, null, "000000000", "PESSOA_FISICA"));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaDoador(null, "nome", "email", "000000000", "PESSOA_FISICA"));
	}

	@Test
	void testErroUsuarioJaExiste() {
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioJaExiste("08704413000240"));
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioJaExiste("70513372911"));
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioJaExiste("1000000000000"));
	}

	@Test
	void testErroUsuarioNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioNaoExiste("08704413000000"));
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioNaoExiste("70513372955"));
		assertThrows(IllegalArgumentException.class, () -> usuario.erroUsuarioNaoExiste("1000000000999"));
	}

	@Test
	void testAdicionaReceptor() {
		// Testando se o usuario etá sendo adcionado.
		assertEquals("70514322922",
				usuario.adicionaReceptor("70514322922", "nome", "email", "000000000", "PESSOA_FISICA"));
		assertEquals("08704987600290",
				usuario.adicionaReceptor("08704987600290", "nome", "email", "000000000", "IGREJA"));
		assertEquals("08704235409876",
				usuario.adicionaReceptor("08704235409876", "nome", "email", "000000000", "ORGAO_PUBLICO_MUNICIPAL"));
		assertEquals("12344091200290", 
				usuario.adicionaReceptor("12344091200290", "nome", "email", "000000000", "ONG"));
		assertEquals("087047019200290",
				usuario.adicionaReceptor("087047019200290", "nome", "email", "000000000", "ASSOCIACAO"));
		// Testando as Excecoes
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaReceptor("08704768500290", "nome", "email", "", "ASSOCIACAO"));
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaReceptor("08704768500290", " ", " ", "000000000", ""));
		assertThrows(IllegalArgumentException.class,
				() -> usuario.adicionaReceptor("08704768500290", "", "email", " ", " "));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaReceptor(null, "nome", "email", "000000000", "ASSOCIACAO"));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaReceptor("70513372922", null, null, "000000000", null));
		assertThrows(NullPointerException.class,
				() -> usuario.adicionaReceptor(null, "nome", "email", null, "PESSOA_FISICA"));
	}
	@Test
	void testPesquisaUsuarioPorId() {
		assertEquals("70513372922",
				usuario.adicionaDoador("70513372922", "nome", "email", "000000000", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> usuario.pesquisaUsuarioPorId(" "));
		assertThrows(NullPointerException.class, () -> usuario.pesquisaUsuarioPorId(null));
		assertEquals("nome/70513372922, email, 000000000, status: doador", usuario.pesquisaUsuarioPorId("70513372922"));	
	}
	
	@Test
	void testPesquisaUsuarioPorNome() {
		assertEquals("70513372922",
				usuario.adicionaDoador("70513372922", "matheus", "email", "000000000", "PESSOA_FISICA"));
		assertEquals("89013372732",
				usuario.adicionaDoador("89013372732", "matheus", "email", "000000000", "PESSOA_FISICA"));
		assertThrows(IllegalArgumentException.class, () -> usuario.pesquisaUsuarioPorNome(" "));
		assertThrows(NullPointerException.class, () -> usuario.pesquisaUsuarioPorNome(null));
		assertEquals("matheus/70513372922, email, 000000000, status: doador | matheus/89013372732, email, 000000000, status: doador", usuario.pesquisaUsuarioPorNome("matheus"));	
	}
	
	@Test
	void testAtualizaInformacaoDeUsuario() {
		assertThrows(IllegalArgumentException.class, () -> usuario.atualizaInformacaoDeUsuario(" ", "nome", "email", "celular"));
		assertThrows(NullPointerException.class, () -> usuario.atualizaInformacaoDeUsuario(null, "nome", "email", "celular"));
		assertEquals("matheus/70513372911, email, 98743-7455, status: doador", usuario.atualizaInformacaoDeUsuario("70513372911", "matheus", "email", "98743-7455"));
	} 

	@Test
	void testRemoveUsuario() {
		assertThrows(IllegalArgumentException.class, () -> usuario.removeUsuario(" "));
		assertThrows(NullPointerException.class, () -> usuario.removeUsuario(null));
		assertEquals(usuario.removeUsuario("70513372911"), true);
		assertEquals(usuario.removeUsuario("08704413000240"), true);

	}

	@Test
	void testAtualizaItem() {
		
		
	}

	@Test
	void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	void testListaItensNecessarios() {
		fail("Not yet implemented");
	}

}