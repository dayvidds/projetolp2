package backend;
import Controller.Controlador;
import easyaccept.EasyAccept;

public class Facade {
	
	private Controlador controlador = new Controlador(); 
	
	public static void main(String[] args) {
		args = new String[] { "backend.Facade", "testes_aceitacao/use_case_1.txt"};
		EasyAccept.main(args);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controlador.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controlador.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return "";
	}

	public void removeUsuario(String id) {

	}
	
	// TODO METODO ATUALIZA USUARIO

	public void adicionaDescritor(String descricao) {
		controlador.adicionaDescritor(descricao);
		
	}

	public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		controlador.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
		
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
