package Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import backend.Exceptions;
import entidade.Item;

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
	
	/**
	 * Inicializa o controlador principal do sistema e invoca os construtores do controlador usuario e controlador item.
	 */
	public Controlador() {
		controladorUsuario = new ControladorUsuario();
		controladorItem = new ControladorItem();
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param caminho
	 * @throws FileNotFoundException
	 */
	public void lerrecptor(String caminho) throws FileNotFoundException {
		controladorUsuario.lerrecptor(caminho);	
	}

	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @param classe
	 * @return
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controladorUsuario.adicionaDoador(id, nome, email, celular, classe);
	}

	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param id
	 * @return
	 */
	public String pesquisaUsuarioPorId(String id) {
		return controladorUsuario.pesquisaUsuarioPorId(id);
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param nome
	 * @return
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return controladorUsuario.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param id
	 * @param nome
	 * @param email
	 * @param celular
	 * @return
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controladorUsuario.atualizaInformacaoDeUsuario(id, nome, email, celular);
	}

	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param id
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
		Exceptions.checaNullOuVazio(idDoador, "id do usuario nao pode ser vazio ou nulo.");
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
	 * Metodo responsavel em invocar o controlador de item e usuario e remover determiado produto.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controladorUsuario.removeItem(idItem, idDoador);
		controladorItem.removeItem(idItem, "itemDoado");
		
	}
	/**
	 * Metodo responsavel por listar todos os itens disponiveis para doacao, juntando o toString do item e a concatenacao do nome/id do 
	 * usuario doador.
	 * 
	 * @return retorna uma string contendo todos os itens disponiveis para doacao e os seus respectivos doadores
	 */
	public String listaItensParaDoacao() {
		return this.controladorUsuario.listaItensParaDoacao(this.controladorItem.ordenaItensPorQuantidade());
	}
	
	/**
	 * Metodo responsavel invocar o controlador de item e...
	 * @return
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controladorItem.listaDescritorDeItensParaDoacao();
	}
	
	/**
	 * Metodo responsavel invocar o controlador de usuario e...
	 * @param descricao
	 * @return
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
}