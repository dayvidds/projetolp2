package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import Comparable.ComparadorQuantidade;
import entidade.Item;

public class ControladorItem {

	private Map<String, Map<Integer, Item>> itensDoados;
	private int idItem;
	
	public ControladorItem() {
		itensDoados = new TreeMap<>();
		idItem = 0;
		
	}

	public void adicionaDescritor(String descricao) {
		Map<Integer,Item> mapa = new HashMap<>();
		itensDoados.put(descricao, mapa);
		
	}
	
	private void avaliaDescritor(String descricaoItem) {
		if (!itensDoados.containsKey(descricaoItem.trim().toLowerCase())) {
			this.adicionaDescritor(descricaoItem.trim().toLowerCase());
		}
		
	}
	
	private boolean avaliaItem(String descricaoItem, Item item, int quantidade) {
		if (itensDoados.get(descricaoItem.trim().toLowerCase()).containsValue(item)) {
			for (Item itens: itensDoados.get(descricaoItem.trim().toLowerCase()).values()) {
				if (itens.equals(item)) {
					itens.setQuantidade(quantidade);
					break;
				}
			}
			return true;
		} else {
			return false;
			
		}	
	}

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.avaliaDescritor(descricaoItem);
		Item item = new Item(idDoador, idItem, descricaoItem, quantidade, tags);
		
		if (!this.avaliaItem(descricaoItem, item, quantidade)) {
			itensDoados.get(descricaoItem.trim().toLowerCase()).put(idItem, item);
		}					
		idItem += 1;
		return idItem - 1;
		
	}
	
	public void removeItemParaDoacao(int idItem) {
		for (Map<Integer, Item> mapa : itensDoados.values()) {
			for (Item item : mapa.values()) {
				if (item.getId() == idItem) {
					mapa.remove(idItem);
					break;		
				}
			}	
		}
		
	}

	public Item getItemId(String descricaoItem, int id) {
		return itensDoados.get(descricaoItem).get(id);
		
	}
	
	public String listaDescritorDeItensParaDoacao() {
		Set<String> descritores = itensDoados.keySet();
		String listaDescritores = "";
		int contador = 0;
		for (String descritor : descritores) {
			listaDescritores += this.itensDoados.get(descritor).size() + " - " + descritor;
			contador += 1;
			if (contador < descritores.size()) {
				listaDescritores += " | ";
			}
		}
		return listaDescritores;
	}
	
	public ArrayList<Item> listaItensParaDoacao() {
		ArrayList<Item> itens = new ArrayList<>();
		Set<String> descritores = itensDoados.keySet();
		for (String descritor : descritores) {
			Set<Integer> idItens = this.itensDoados.get(descritor).keySet();
			for (int idItem : idItens) {
				itens.add(this.itensDoados.get(descritor).get(idItem));
			}
		}
		
		Collections.sort(itens, new ComparadorQuantidade());

		return itens;
	}
	
}