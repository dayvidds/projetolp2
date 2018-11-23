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

	// TODO METODO ATUALIZA USUARIO

	public void removeUsuario(String id) {
		controlador.removeUsuario(id);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controlador.atualizaUsuario(id, nome, email, celular);
	}
	public void adicionaDescritor(String descricao) {

	}

	public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {

	}

	public String exibeItem(String idItem, String idDoador) {
		return "";
	}

	// TODO METODO ATUALIZAR ITEM

	public void removeItemParaDoacao(String idItem, String idDoador) {

	}

	public String listaDescritorDeItensParaDoacao() {
		return "";
	}

	public String listaItensParaDoacao() {
		return "";
	}

	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return "";
	}

}
