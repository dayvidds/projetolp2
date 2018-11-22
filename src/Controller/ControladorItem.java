package Controller;

import java.util.HashMap;
import java.util.Map;

import entidade.Item;

public class ControladorItem {

	private Map<String, Map<Integer, Item>> itensDoados;
	private int idItem;
	
	public ControladorItem() {
		itensDoados = new HashMap<>();
		idItem = 0;
		
	}

	public void adicionaDescritor(String descricao) {
		Map<Integer,Item> mapa = new HashMap<>();
		itensDoados.put(descricao, mapa);
		
	}

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		Item item = new Item(idDoador, descricaoItem, quantidade, tags);
		Map<Integer, Item> itens = new HashMap<>();
		itens.put(idItem, item);
		itensDoados.put(descricaoItem.trim().toLowerCase(), itens);
		int retorno = idItem;
		idItem += 1;
		return retorno;
		
	}

	public Item getItemId(String descricaoItem, int id) {
		return itensDoados.get(descricaoItem).get(id);
		
	}
	
}
