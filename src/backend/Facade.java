package backend;


import java.io.FileNotFoundException;
import Controller.Controlador;
import easyaccept.EasyAccept;


/**
 * Representa a fachada com todos os metodos existentes no controlador pricipal do sistema.
 * @author Dayvid Daniel, Thiago Nascimento, Matheus Augusto e Caroliny Regina.
 *
 */
public class Facade {
	
	/**
	 * Controlador pricipal.
	 */
	private Controlador controlador = new Controlador(); 
	
	/**
	 * Executa os testes de aceitacao do easyaccept
	 * @param args testes que serão executados.
	 */
	public static void main(String[] args) throws FileNotFoundException {		
		Facade fc = new Facade();
		fc.iniciaSistema();
		args = new String[] {"backend.Facade", "arquivos_sistema/use_case_1.txt", "arquivos_sistema/use_case_2.txt", "arquivos_sistema/use_case_3.txt", "arquivos_sistema/use_case_4.txt", "arquivos_sistema/use_case_5.txt", "arquivos_sistema/use_case_6.txt","arquivos_sistema/use_case_7.txt"};
		EasyAccept.main(args);
	}
	
	/**
	 * Invoca o controlador para ler o arquivo com as informacoes dos receptores.
	 * @param caminho caminho do arquivo.
	 * @throws FileNotFoundException Exceçao.
	 */
	public void lerReceptores(String caminho) throws FileNotFoundException {
		controlador.lerrecptor(caminho);
	}
	
	/**
	 * Invoca o controlador para adicionar um usuario doador ao sistema.
	 * @param id identificacao do doador.
	 * @param nome nome do doador.
	 * @param email email do doador.
	 * @param celular celular do doador.
	 * @param classe classe a qual o doador pertece.
	 * @return retorna uma string com a identificacao do doador.
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controlador.adicionaDoador(id, nome, email, celular, classe);
	}

	/**
	 * Invoca o controlador para pesquisar por um usuario especifico atraves do id.
	 * @param id identificacao do usuario.
	 * @return retorna as informacoes do usuario.
	 */
	public String pesquisaUsuarioPorId(String id) {
		return controlador.pesquisaUsuarioPorId(id);
	}

	/**
	 * Invoca o controlador para pesquisar por um usuario espercifico atraves do nome.
	 * @param nome nome do usuario.
	 * @return retorna as informacoes do usuario
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		return controlador.pesquisaUsuarioPorNome(nome);
	}

	/**
	 * Invoca o controlador para remover determinado usuario.
	 * @param id identificacao do usuario.
	 */
	public void removeUsuario(String id) {
		controlador.removeUsuario(id);
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
		return controlador.atualizaUsuario(id, nome, email, celular);
	}
	
	/**
	 * Invoca o controlador para adicionar um descritor ao sistema.
	 * @param descricao descritor.
	 */
	public void adicionaDescritor(String descricao) {
		controlador.adicionaDescritor(descricao);
	}

	/**
	 * Invoca o controlador para adicionar um item para doacao.
	 * @param idDoador identificacao do doador.
	 * @param descricaoItem descritor do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * @return retorna um inteiro que e a identificacao do item.
	 */
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controlador.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}

	/**
	 * Invoca o controlador para exibir um item de determinado doador.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 * @return retorna uma string com a representacao do item.
	 */
	public String exibeItem(int idItem, String idDoador) {
		return controlador.exibeItem(idDoador, idItem);
	}

	/**
	 * Invoca o controlador para atualizar um determinado item de um doador.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 * @param quantidade nova quantidade do item.
	 * @param tags novas tags para o item.
	 * @return retorna a representacao do item com as informacoes atualizadas.
	 */
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controlador.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}

	/**
	 * Invoca o controlador para remover um determinado item de um doador.
	 * @param idItem identificacao do item.
	 * @param idDoador identificacao do doador.
	 */
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controlador.removeItemParaDoacao(idItem, idDoador);
	}

	/**
	 * Invoca o controlador para listar os descritores de itens para doacao.
	 * @return retorna todos os descritores de itens para doacao.
	 */
	public String listaDescritorDeItensParaDoacao() {
		return controlador.listaDescritorDeItensParaDoacao();
	}
	
	/**
	 * Invoca o controlador para listar todos os itens para doacao.
	 * @return retorna informacoes de todos os itens para doacao.
	 */
	public String listaItensParaDoacao() {
		return controlador.listaItensParaDoacao();
	}

	/**
	 * Invoca o controlador para pesquisar itens para doacao de determinado descritor.
	 * @param descricao descritor do item.
	 * @return retorna informacoes dos itens que possue o descritor especifico.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return controlador.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	/**
	 * Invoca o controlador para adicionar um item necessario ao sistema.
	 * @param idReceptor identificacao do receptor.
	 * @param descricaoItem descritor do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags do item.
	 * @return retorna um inteiro que e a identificacao do item.
	 */
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return controlador.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	/**
	 * Invoca o controlador para atualizar informacoes de um item de determinado receptor.
	 * @param idReceptor identificacao do receptor.
	 * @param idItem identificacao do item.
	 * @param quantidade nova quantidade do item.
	 * @param novasTags novas tags para o item.
	 * @return retorna a representacao do item com as informacoes atualizadas.
	 */
	public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String novasTags) {
		return controlador.atualizaItemNecessario(idItem, idReceptor, quantidade, novasTags);
	}
	
	/**
	 * Invoca o controlador para listar todos os itens necessarios adicionados ao sistema.
	 * @return retorna as informacoes de todos os itens. 
	 */
	public String listaItensNecessarios() {
		return controlador.listaItensNecessarios();
	}
	
	/**
	 * Invoca o controlador para remover um item de determinado receptor.
	 * @param idReceptor identificador do receptor.
	 * @param idItem identificador do item.
	 */
	public void removeItemNecessario(String idReceptor, int idItem) {
		controlador.removeItemNecessario(idReceptor, idItem);
	}
	
	/**
	 * Invoca o controlador para verificar os itens doados que sao semelhantes ao item necessario especifico.
	 * @param idReceptor identificador do receptor.
	 * @param idItemNecessario identificador do item necessario.
	 * @return retorna uma representacao dos itens semelhantes.
	 */
	public String match(String idReceptor, int idItemNecessario) {
		return controlador.match(idReceptor, idItemNecessario);
	}
	
	/**
	 * Invoca o controlador para realizar uma doacao.
	 * @param idItemNec identificador do item necessario.
	 * @param idItemDoado identificador do item doado.
	 * @param data data da doacao.
	 * @return retorna a representacao textual da doacao.
	 */
	public String realizaDoacao(int idItemNec, int idItemDoado, String data) {
		return controlador.realizaDoacao(idItemNec, idItemDoado, data);
	}
	
	/**
	 * Invoca o controlador para listar todas doacoes realizadas no sistema.
	 * @return retorna a representacao textual de todas doacoes.
	 */
	public String listaDoacoes() {
		return controlador.listaDoacoes();
	}

	/**
	 * Invoca o controlador para inicializar o sistema carregando dados de execucoes passadas.
	 * 
	 */
	public void iniciaSistema() {
		controlador.iniciarSistema();

	}
	/**
	 * Invoca o controlador para fechar o sistema salvando dados para execucoes futuras.
	 * 
	 */
	public void finalizaSistema() {
		controlador.fecharSistema();
	}
}
