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

	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		if (itensDoados.containsKey(descricaoItem.trim().toLowerCase())) {
			Item item = new Item(idDoador, idItem, descricaoItem, quantidade, tags);
			if (itensDoados.get(descricaoItem.trim().toLowerCase()).containsValue(item)) {
				for (Item itens: itensDoados.get(descricaoItem.trim().toLowerCase()).values()) {
					if (itens.equals(item)) {
						itens.setQuantidade(quantidade);
						break;
					}	
				}
			} else {
				itensDoados.get(descricaoItem.trim().toLowerCase()).put(idItem, item);
				
			}
			
		} else {
			this.adicionaDescritor(descricaoItem.trim().toLowerCase());
			Item item = new Item(idDoador, idItem, descricaoItem, quantidade, tags);
			itensDoados.get(descricaoItem.trim().toLowerCase()).put(idItem, item);
		}
		int retorno = idItem;
		idItem += 1;
		return retorno;
		
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
