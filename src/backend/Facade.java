package backend;


import java.io.FileNotFoundException;

import Controller.Controlador;
import easyaccept.EasyAccept;

public class Facade {
	private Controlador controlador = new Controlador(); 
	
	public static void main(String[] args) throws FileNotFoundException {
		args = new String[] {"backend.Facade", "arquivos_sistema/use_case_1.txt", "arquivos_sistema/use_case_2.txt", "arquivos_sistema/use_case_3.txt"};
		EasyAccept.main(args);
	}
	
	public void lerReceptores(String caminho) throws FileNotFoundException {
		controlador.lerrecptor(caminho);
		
	}
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controlador.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controlador.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return controlador.pesquisaUsuarioPorNome(nome);
	}

	public void removeUsuario(String id) {
		controlador.removeUsuario(id);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controlador.atualizaUsuario(id, nome, email, celular);
	}
	
	public void adicionaDescritor(String descricao) {
		controlador.adicionaDescritor(descricao);
		
	}

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controlador.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
		
	}

	public String exibeItem(int idItem, String idDoador) {
		return controlador.exibeItem(idDoador, idItem);
		
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controlador.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
		
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		controlador.removeItemParaDoacao(idItem, idDoador);
		
	}

	public String listaDescritorDeItensParaDoacao() {
		return controlador.listaDescritorDeItensParaDoacao();
	}

	public String listaItensParaDoacao() {
		return controlador.listaItensParaDoacao();
	}

	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		return controlador.pesquisaItemParaDoacaoPorDescricao(descricao);
	}
	
	public int adicionaItemNecessario(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controlador.adicionaItemNecessario(idDoador, descricaoItem, quantidade, tags);
	}

}
