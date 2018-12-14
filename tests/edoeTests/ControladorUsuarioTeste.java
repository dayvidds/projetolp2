package edoeTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controladores.ControladorItem;
import controladores.ControladorUsuario;
import entidades.Item;
/**
 * Classe para teste do ControladorUsuarios
 * 
 * @author matheus augusto
 *
 */
class ControladorUsuarioTeste {

	private ControladorUsuario usuario;
	private ControladorItem item;

	@BeforeEach
	void setUp() {
		usuario = new ControladorUsuario();
		item = new ControladorItem();
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
		// Cadastrando itens aos usuarios para futuros testes
		item.adicionaDescritor(" CadeirA");
		item.adicionaDescritor("Cama, Mesa, Banho ");
		item.adicionaDescritor("CAMISETA");
		item.adicionaDescritor("lencol");
		item.adicionaItem("70513372911", "cadeira", 2, "confortavel,seminova" , "itemDoado");
		item.adicionaItem("2000000000000", "Cama, Mesa, Banho ", 1, "lar doce lar" , "itemNecessario");
		item.adicionaItem("3000000000000", "Cama, Mesa, Banho ", 2, "confortavel" , "itemNecessario");
		item.adicionaItem("08704413000240", "lencol", 5, "branco,fino", "itemDoado");
		item.adicionaItem("5000000000000", "CADEIRA", 10, "", "itemDoado");
		item.adicionaItem("5000000000000", "camiseta", 8, "algodao,preta", "itemDoado");
		

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
	void testListaItensParaDoacao() {
		usuario.adicionaItem("70513372911", 0, item.getItemId("cadeira", 0, "itemDoado"), "itemDoado");
		usuario.adicionaItem("08704413000240", 3, item.getItemId("lencol", 3,"itemDoado"), "itemDoado");
		assertEquals("3 - lencol, tags: [branco, fino], quantidade: 5, doador: nome/08704413000240 | 0 - cadeira, tags: [confortavel, seminova], quantidade: 2, doador: nome/70513372911", usuario.listaItensParaDoacao(item.ordenaItensPorQuantidade()));
		
	}
	
	@Test
	void testMatchItemDoador() {
		List<Item> lista = new ArrayList<Item>();

		lista.add(item.getItemId("cadeira", 0, "itemDoado"));
		lista.add(item.getItemId("lencol", 3, "itemDoado"));
		assertEquals("0 - cadeira, tags: [confortavel, seminova], quantidade: 2, doador: nome/70513372911 | 3 - lencol, tags: [branco, fino], quantidade: 5, doador: nome/08704413000240", usuario.matchItemDoador(lista));
		
	}
	
	@Test
	void testGetNomeUsuario() {
		Item item = this.item.getItemId("cadeira", 0, "itemDoado");
		usuario.adicionaItem("70513372911", 0, item, "itemDoado");
		assertEquals("nome", usuario.getNomeUsuarioIdItem(0));
	}
	
	@Test
	void testGetNomeUsuarioFalso() {
		assertEquals("", usuario.getNomeUsuarioIdItem(100));
	}
	
	@Test
	void testRemoveItemMatch() {
		Item item = this.item.getItemId("cadeira", 0, "itemDoado");
		usuario.adicionaItem("70513372911", 0, item, "itemDoado");
		assertEquals("0 - cadeira, tags: [confortavel, seminova], quantidade: 2", usuario.exibeItem("70513372911", 0));
		List<Integer> lista = new ArrayList<>();
		lista.add(0);
		usuario.removeItemMatch(lista);
		try {
			assertEquals("", usuario.exibeItem("70513372911", 0));
		} catch (IllegalArgumentException ia) {
			
		}
	}

}