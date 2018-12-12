package Controller;

import java.io.FileNotFoundException;
import backend.Exceptions;
import backend.Persistencia;
import entidade.Status;

/**
 * Classe que representa um controlador principal do sistema. O controlador principal do sistema gerencia o controlador de usuario e o controlador de item.
 * @author 
 *
 */
public class Controlador {

	/**
	 * Controlador de usuario.
	 */
	private ControladorUsuario controladorUsuario;
	
	/**
	 * Controlador de item.
	 */
	private ControladorItem controladorItem;
	
	private Exceptions exceptions;
	
	/**
	 * Inicializa o controlador principal do sistema e invoca os construtores do controlador usuario e controlador item.
	 */
	public Controlador() {
		controladorUsuario = new ControladorUsuario();
		controladorItem = new ControladorItem();
		exceptions = new Exceptions(); 
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario e realizar a leitura dos receptores
	 * que foram arquivados.
	 * @param caminho caminho do arquivo.
	 * @throws FileNotFoundException Excecao.
	 */
	public void lerrecptor(String caminho) throws FileNotFoundException {
		controladorUsuario.lerrecptor(caminho);	
	}

	/**
	 * Invoca o controlador de usuario para adicionar um novo doador ao sistema.
	 * @param id identificacao do doador.
	 * @param nome nome do doador.
	 * @param email email do doador.
	 * @param celular celular do doador.
	 * @param classe classe a qual o doador pertece.
	 * @return retorna uma string com a identificacao do doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controladorUsuario.adicionaDoador(id, nome, email, celular, classe);
	}

	/**
	 * Invoca o controlador para pesquisar por um usuario especifico atraves do id.
	 * @param id identificacao do usuario.
	 * @return retorna as informacoes do usuario.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return controladorUsuario.pesquisaUsuarioPorId(id);
	}
	
	/**
	 * Invoca o controlador para pesquisar por um usuario espercifico atraves do nome.
	 * @param nome nome do usuario.
	 * @return retorna as informacoes do usuario
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return controladorUsuario.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Invoca o controlador para atualizar informacoes de determinado usuario.
	 * @param id identificacao do usuario.
	 * @param nome novo nome para o usuario
	 * @param email novo email para o usuario.
	 * @param celular novo celular para o usuario.
	 * @return retorna a representacao do usuario com as informacoes atualizadas.
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controladorUsuario.atualizaInformacaoDeUsuario(id, nome, email, celular);
	}

	/**
	 * Invoca o controlador para remover determinado usuario.
	 * @param id identificacao do usuario.
	 */
	public void removeUsuario(String id) {
		controladorUsuario.removeUsuario(id);
	}
	
	/**
	 * Metodo responsavel em invocar o controlador de item e adicionar um descritor ao sistema.
	 * @param descricao descritor de item.
	 */
	public void adicionaDescritor(String descricao) {
		controladorItem.adicionaDescritor(descricao);
	}

	/**
	 * Metodo responsavel em adicionar um item para doacao no controlador de item e no controlador de usuario. 
	 * @param idDoador identificacao do doador.
	 * @param descricaoItem descritor do item.
	 * @param quantidade quantidade do item que sera adicionado.
	 * @param tags tags para identificacao do item.
	 * @return retorna a identificacao do item que foi cadastrado no sistema.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		exceptions.checaNullOuVazio(idDoador, "id do usuario nao pode ser vazio ou nulo.");
		controladorUsuario.erroUsuarioNaoExiste(idDoador);
		int idItem = controladorItem.adicionaItem(idDoador, descricaoItem, quantidade, tags, "itemDoado");
		controladorUsuario.adicionaItem(idDoador, idItem, controladorItem.getItemId(descricaoItem, idItem, "itemDoado"), "itemDoado");
		return idItem;
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario para listar o item especifico de determinado doador.
	 * @param idDoador identificacao do doador.
	 * @param idItem identificacao do item.
	 * @return retorna as informacoes do item.
	 */
	public String exibeItem(String idDoador, int idItem) {
		return controladorUsuario.exibeItem(idDoador, idItem);
	}

	/**
	 * Metodo responsavel em invocar o controlador de usuario para alterar informacoes de um item especifico de determinado doador.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 * @param quantidade nova quantidade de itens.
	 * @param tags novas tags para o item.
	 * @return retorna a representacao atualizada do item. 
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controladorUsuario.atualizaItem(idItem, idDoador, quantidade, tags);
	}

	/**
	 * Metodo responsavel em invocar o controlador de item e usuario e remover determinado produto.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controladorUsuario.removeItem(idItem, idDoador);
		controladorItem.removeItem(idItem, "itemDoado");
		
	}
	/**
	 * Metodo responsavel por invocar o controlador de item e listar todos os itens disponiveis para doacao e seus respectivos doadores.
	 * 
	 * @return retorna uma string contendo todos os itens disponiveis para doacao e os seus respectivos doadores.
	 */
	public String listaItensParaDoacao() {
		return this.controladorUsuario.listaItensParaDoacao(this.controladorItem.ordenaItensPorQuantidade());
	}
	
	/**
	 * Metodo responsavel por invocar o controlador de item e listar todos os itens oferecidos para doacao de acordo com seus descritores.
	 * 
	 * @return retorna uma string contendo todos os descritores e a quantidade itens contidos nos mesmos, organizados em ordem alfabetica.
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controladorItem.listaDescritorDeItensParaDoacao();
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario e listar todos os itens que contem uma determina descricao passada como parametro.
	 * 
	 * @param descricao descricao a ser procurada no mapa de itens doados.
	 * @return retorna uma string contendo os itens que contem a descricao passada como parametro.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return controladorItem.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	/**
	 * Metodo responsavel em adicionar um item necessario no controlador de item e no controlador de usuario. 
	 * @param idReceptor identificador do receptor.
	 * @param descricaoItem descritor do item.
	 * @param quantidade quantidade de item que sera adicionado.
	 * @param tags tags para identificacao do item.
	 * @return retorna a identificacao do item.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		exceptions.checaNullOuVazio(idReceptor, "id do usuario nao pode ser vazio ou nulo.");
		controladorUsuario.erroUsuarioNaoExiste(idReceptor);
		int idItem = controladorItem.adicionaItem(idReceptor, descricaoItem, quantidade, tags, "itemNecessario");
		controladorUsuario.adicionaItem(idReceptor, idItem, controladorItem.getItemId(descricaoItem, idItem, "itemNecessario"), "itemNecessario");
		return idItem;
	}
	
	/**
	 * Metodo responsavel em invocar o controlador de usuario para alterar informacoes de um item especifico de determinado receptor.
	 * @param idItem identificacao do item.
	 * @param idReceptor identificacao do receptor.
	 * @param quantidade nova quantidade do item.
	 * @param novasTags novas tags do item.
	 * @return retorna a representacao atualizada do item. 
	 */
	public String atualizaItemNecessario(int idItem, String idReceptor, int quantidade, String novasTags) {
		return controladorUsuario.atualizaItem(idItem, idReceptor, quantidade, novasTags);
	}
	
	/**
	 * Metodo responsavel em invocar o controlador de usuario para pegar as informacoes de todos os itens necessarios.
	 * @return retorna uma string com as informacoes de todos os itens necessarios.
	 */
	public String listaItensNecessarios() {
		return controladorUsuario.listaItensNecessarios();
	}
	
	/**
	 * Metodo responsavel em invocar o controlador de item e usuario e remover determiado produto.
	 * @param idReceptor identificacao do receptor.
	 * @param idItem identificacao do item.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		controladorUsuario.removeItem(idItem, idReceptor);
		controladorItem.removeItem(idItem, "itemNecessario");
	}

	public String match(String idReceptor, int idItemNecessario) {
		if (idReceptor == null || idReceptor.length() == 0) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		controladorUsuario.erroUsuarioNaoExiste(idReceptor);
		if (!controladorUsuario.getStatusUsuario(idReceptor).equals(Status.valueOf("RECEPTOR"))) {
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idReceptor + ".");
		}
		
		if (idItemNecessario < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		return controladorUsuario.matchItemDoador(controladorItem.match(idReceptor, idItemNecessario));
		
	}

	public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
		if (idItemNec < 0 || idItemDoado < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
		if (data == null || data.length() == 0) {
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		}
		String nomeDoador = controladorUsuario.getNomeUsuarioIdItem(idItemDoado);
		String nomeReceptor = controladorUsuario.getNomeUsuarioIdItem(idItemNec);
		controladorUsuario.removeItemMatch(controladorItem.verificaItensExclusao(idItemNec, idItemDoado));
		String retorno = controladorItem.realizaDoacao(idItemNec, nomeReceptor, idItemDoado, nomeDoador, data);
		return retorno;
	}

	public String listaDoacoes() {
		return controladorItem.listaDoacoes();
	}
	
	public void iniciarSistema() {
		Persistencia persistencia = new Persistencia();
		persistencia.carregar(controladorUsuario);
	}

	public void fecharSistema() {
		Persistencia persistencia = new Persistencia();
		persistencia.salvar(controladorUsuario.mapUsuario());
	}
}






