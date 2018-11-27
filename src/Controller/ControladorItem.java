package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import Comparable.ComparadorQuantidade;
import backend.Exceptions;
import entidade.Item;

public class ControladorItem {

	private Map<String, Map<Integer, Item>> itensDoados;
	private Map<String, Map<Integer, Item>> itensNecessarios;
	private int idItem;
	
	public ControladorItem() {
		itensDoados = new TreeMap<>();
		idItem = 0;
		
	}

	public void adicionaDescritor(String descricao) {
		Exceptions.checaNullOuVazio(descricao, "descricao nao pode ser vazia ou nula.");
		if (itensDoados.containsKey(descricao.trim().toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.trim().toLowerCase() + ".");
		}
		Map<Integer,Item> mapa = new HashMap<>();
		itensDoados.put(descricao.trim().toLowerCase(), mapa);
		
	}
	
	private void adicionaDescritor(String descricao, Map<String, Map<Integer, Item>> mapaDoItem) {
		Map<Integer,Item> mapa = new HashMap<>();
		mapaDoItem.put(descricao.trim().toLowerCase(), mapa);
		
	}
	
	private void avaliaDescritor(String descricaoItem, Map<String, Map<Integer, Item>> mapaDoItem) {
		if (!mapaDoItem.containsKey(descricaoItem.trim().toLowerCase())) {
			this.adicionaDescritor(descricaoItem.trim().toLowerCase(), mapaDoItem);
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
	
	public int adicionaItem(String idDoador, String descricaoItem, int quantidade, String tags, String tipoItem) {
		Exceptions.checaNullOuVazio(descricaoItem, "descricao nao pode ser vazia ou nula.");
		Exceptions.verificaQuantidadeItem(quantidade, "quantidade deve ser maior que zero.");
		Map<String, Map<Integer, Item>> mapa;
		Item item = new Item(idDoador, idItem, descricaoItem, quantidade, tags);
		
		if (tipoItem.equals("itemDoado")) {
			mapa = this.itensDoados;
		} else {
			mapa = this.itensNecessarios;
		}
		
		this.avaliaDescritor(descricaoItem, mapa);
		
		if (!this.avaliaItem(descricaoItem, item, quantidade)) {
			mapa.get(descricaoItem.trim().toLowerCase()).put(idItem, item);
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
	
	public ArrayList<Item> ordenaItensPorQuantidade() {
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
	
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
		Exceptions.checaNullOuVazio(descricao, "texto da pesquisa nao pode ser vazio ou nulo.");
		ArrayList<String> itensToString = new ArrayList<>();
		Set<String> descritores = itensDoados.keySet();
		for (String descritor : descritores) {
			if (descritor.contains(descricao.toLowerCase())) {
				
			}
		}
		return  "";
	}
	
	
}