package Controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import entidade.Item;

public class Controlador {

	private ControladorUsuario controladorUsuario;
	private ControladorItem controladorItem;
	
	public Controlador() {
		controladorUsuario = new ControladorUsuario();
		controladorItem = new ControladorItem();
		
	}
	public void lerrecptor(String caminho) throws FileNotFoundException {
		controladorUsuario.lerrecptor(caminho);	
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controladorUsuario.adicionaDoador(id, nome, email, celular, classe);
	}

	public String pesquisaUsuarioPorId(String id) {
		return controladorUsuario.pesquisaUsuarioPorId(id);
	}
	public String pesquisaUsuarioPorNome(String nome) {
		return controladorUsuario.pesquisaUsuarioPorNome(nome);
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controladorUsuario.atualizaInformacaoDeUsuario(id, nome, email, celular);
		
	}

	public void removeUsuario(String id) {
		controladorUsuario.removeUsuario(id);
		
	}

	public void adicionaDescritor(String descricao) {
		controladorItem.adicionaDescritor(descricao);
		
	}

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		int idItem = controladorItem.adicionaItem(idDoador, descricaoItem, quantidade, tags, "itemDoado");
		controladorUsuario.adicionaItem(idDoador, idItem, controladorItem.getItemId(descricaoItem, idItem), "itemDoado");
		return idItem;
		
	}
	
	public String exibeItem(String idDoador, int idItem) {
		return controladorUsuario.exibeItem(idDoador, idItem);
		
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador) {
		return controladorUsuario.atualizaItem(idItem, idDoador);
		
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		controladorItem.removeItemParaDoacao(idItem);
		controladorUsuario.removeItem(idItem, idDoador);
		
	}
	
	public String listaItensParaDoacao() {

		ArrayList<Item> itens = this.controladorItem.listaItensParaDoacao();
		String listaItens = "";
		int contador = 0;
		for (Item item : itens) {
			String idDoador = item.getIdDoador(); 
			String doador = this.controladorUsuario.getNomeIdentificacao(idDoador);
			listaItens += item.toString() + ", doador: " + doador;
			contador += 1;
			if (contador < itens.size()) {
				listaItens += " | ";
			}

		}
		return listaItens;
	}
	
	public int adicionaItemNecessario(String idDoador, String descricaoItem, int quantidade, String tags) {
		int idItem = controladorItem.adicionaItem(idDoador, descricaoItem, quantidade, tags, "itemNecessario");
		controladorUsuario.adicionaItem(idDoador, idItem, controladorItem.getItemId(descricaoItem, idItem), "itemNecessario");
		return idItem;
	}
}