package backend;
import Controller.ControladorItem;
import Controller.ControladorUsuario;
import easyaccept.EasyAccept;

public class Facade {
	
	private ControladorUsuario controleUsuario = new ControladorUsuario();
	private ControladorItem controleItem = new ControladorItem(); 
	
	public static void main(String[] args) {
		args = new String[] { "backend.Facade", "testes_aceitacao/use_case_1.txt"};
		EasyAccept.main(args);
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controleUsuario.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controleUsuario.pesquisaUsuarioPorId(id);
	}

	public String pesquisaUsuarioPorNome(String nome) {
		return "";
	}

	public void removeUsuario(String id) {

	}
	
	// TODO METODO ATUALIZA USUARIO

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