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
		itensNecessarios = new TreeMap<>();
		idItem = 0;
		
	}

	public void adicionaDescritor(String descricao) {
		Exceptions.checaNullOuVazio(descricao, "descricao nao pode ser vazia ou nula.");
		if (itensDoados.containsKey(descricao.trim().toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.trim().toLowerCase() + ".");
		}
		
		Map<Integer,Item> mapa = new HashMap<>();
		itensDoados.put(descricao.trim().toLowerCase(), mapa);
		itensNecessarios.put(descricao.trim().toLowerCase(), mapa);
	}
	
	private void avaliaDescritor(String descricaoItem, Map<String, Map<Integer, Item>> mapaDoItem) {
		if (!mapaDoItem.containsKey(descricaoItem.trim().toLowerCase())) {
			this.adicionaDescritor(descricaoItem.trim().toLowerCase());
		}
		
	}
	
	private boolean avaliaItem(String descricaoItem, Item item, int quantidade, Map<String, Map<Integer, Item>> mapaDoItem) {
		if (mapaDoItem.get(descricaoItem.trim().toLowerCase()).containsValue(item)) {
			for (Item itens: mapaDoItem.get(descricaoItem.trim().toLowerCase()).values()) {
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
	
	public int adicionaItem(String idUsuario, String descricaoItem, int quantidade, String tags, String tipoItem) {
		Exceptions.checaNullOuVazio(descricaoItem, "descricao nao pode ser vazia ou nula.");
		Exceptions.verificaQuantidadeItem(quantidade, "quantidade deve ser maior que zero.");
		
		Map<String, Map<Integer, Item>> mapa;
		Item item = new Item(idUsuario, idItem, descricaoItem, quantidade, tags);
		
		if (tipoItem.equals("itemDoado")) {
			mapa = this.itensDoados;
		} else {
			mapa = this.itensNecessarios;
		}
		
		this.avaliaDescritor(descricaoItem, mapa);
		
		if (!this.avaliaItem(descricaoItem, item, quantidade, mapa)) {
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
	
	/**
	 * Metodo responsavel por listar os descritores cadastrados no sistema em ordem alfabetica. 
	 * 
	 * @return uma string contendo os descritores e o numero de itens para doacao que cada um guarda no momento
	 */
	public String listaDescritorDeItensParaDoacao() {
		Set<String> descritores = itensDoados.keySet();
		String listaDescritores = "";
		int contador = 0;
		for (String descritor : descritores) {
			Set<Integer> idItens = this.itensDoados.get(descritor).keySet();
			int quantItens = 0;
			for (int idItem : idItens) {
				quantItens += this.itensDoados.get(descritor).get(idItem).getQuantidade();
			}
			listaDescritores += quantItens + " - " + descritor;
			contador += 1;
			if (contador < descritores.size()) {
				listaDescritores += " | ";
			}
		}
		return listaDescritores;
	}
	
	/**
	 * Metodo responsavel por pegar todos os itens para docao cadastrados no sistema e ordena-los pela quantidade em ordem decrescente.
	 * 
	 * @return um ArrayList de itens ja ordenados.
	 */
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
	
	/**
	 * Metodo responsavel por listar todos os itens que contem uma certa descricao.
	 * 
	 * @param descricao descricao que ira ser usada para pesquisar os itens
	 * @return uma string com o toString de todos os itens que contem a string dada no param em sua descricao
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String descricao) {
	   	 Exceptions.checaNullOuVazio(descricao, "texto da pesquisa nao pode ser vazio ou nulo.");
	   	 ArrayList<Item> itens = new ArrayList<>();
	   	 Set<String> descritores = itensDoados.keySet();
	   	 for (String descritor : descritores) {
	   		 if (descritor.contains(descricao.toLowerCase())) {
	   			 Set<Integer> idItens = this.itensDoados.get(descritor).keySet();
	   			 for (int idItem : idItens) {
	   				 itens.add(this.itensDoados.get(descritor).get(idItem));
	   			 }
	   		 }
	   	 }
	   	 
	   	 String listaItens = "";
	   	 int contador = 0;
	   	 for (Item item : itens) {
	   		 listaItens += item.toString();
	   		 contador += 1;
	   		 if (contador < itens.size()) {
	   			 listaItens += " | ";
	   		 }
	   	 }
	   	 
	   	 return  listaItens;
	    }
	
	public String getIdDoador(String descritor, int idItem) {
		return this.itensDoados.get(descritor).get(idItem).getIdDoador();
	}

	
}