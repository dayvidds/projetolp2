package Controller;

import java.util.HashMap;
import java.util.Map;

import entidade.Item;

public class ControladorItem {

	private Map<String, Map<Integer, Item>> itensDoados;
	
	public ControladorItem() {
		itensDoados = new HashMap<>();
		
	}

	public void adicionaDescritor(String descricao) {
		Map<Integer,Item> mapa = new HashMap<>();
		itensDoados.put(descricao, mapa);
		
	}

	public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		Item item = new Item(idDoador, descricaoItem, quantidade, tags);
		Map<Integer, Item> itens = new HashMap<>();
		itens.put(quantidade, item);
		itensDoados.put(descricaoItem.trim().toLowerCase(), itens);
		
	}
	
}
