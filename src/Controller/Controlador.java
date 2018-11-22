package Controller;

import entidade.Item;

public class Controlador {

	private ControladorUsuario controladorUsuario;
	private ControladorItem controladorItem;
	
	public Controlador() {
		controladorUsuario = new ControladorUsuario();
		controladorItem = new ControladorItem();
		
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controladorUsuario.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controladorUsuario.pesquisaUsuarioPorId(id);
	}

	public void adicionaDescritor(String descricao) {
		controladorItem.adicionaDescritor(descricao);
		
	}

	public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		int idItem = controladorItem.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
		Item item = controladorItem.getItemId(descricaoItem, idItem);
		controladorUsuario.adiconaItemParaDoacao(idDoador, idItem, item);
		
	}
}
