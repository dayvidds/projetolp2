package Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import Comparable.ComparadorData;
import Comparable.ComparadorId;
import Comparable.ComparadorQuantidade;
import backend.Exceptions;
import entidade.Item;

/**
 * Classe que representa o controlador de item. Gerencia todas as extensões diretas do item.
 * @author Thiago Nascimento, Dayvid Daniel e Caroliny Regina.
 *
 */
public class ControladorItem {

	/**
	 * Mapa de todos os itens do doados, mapea para um mapeamento de itens a partir de um descritor.
	 */
	private Map<String, Map<Integer, Item>> itensDoados;
	
	/**
	 * Mapa de todos os itens do necessarios, mapea para um mapeamento de itens a partir de um descritor.
	 */
	private Map<String, Map<Integer, Item>> itensNecessarios;
	
	private List<Doacao> doacoes;
	
	/**
	 * Representacao unica do item, seu id.
	 */
	private int idItem;
	
	private Exceptions exceptions;
	
	public ControladorItem() {
		itensDoados = new TreeMap<>();
		itensNecessarios = new TreeMap<>();
		doacoes = new ArrayList<>();
		idItem = 0;
		exceptions = new Exceptions();
	}
	
	/**
	 * Metodo responsavel em adicionar um descritor nos mapas de itens doados e necessarios.
	 * Um descritor é um identificador generico de determinados itens.
	 * @param descricao descritor que sera adicionado.
	 */
	public void adicionaDescritor(String descricao) {
		exceptions.checaNullOuVazio(descricao, "descricao nao pode ser vazia ou nula.");
		if (itensDoados.containsKey(descricao.trim().toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao.trim().toLowerCase() + ".");
		}
		
		Map<Integer,Item> mapaDoados = new HashMap<>();
		Map<Integer,Item> mapaNecessarios = new HashMap<>();
		itensDoados.put(descricao.trim().toLowerCase(), mapaDoados);
		itensNecessarios.put(descricao.trim().toLowerCase(), mapaNecessarios);
	}
	
	/**
	 * Metodo responsavel em avaliar um descritor.
	 * Caso o descritor nao exista nos mapas de itens ele ira invocar o metodo adicionaDescritor para adicionar a descricao.
	 * @param descricaoItem descricao do item.
	 * @param mapaDoItem mapa do item.
	 */
	private void avaliaDescritor(String descricaoItem, Map<String, Map<Integer, Item>> mapaDoItem) {
		if (!mapaDoItem.containsKey(descricaoItem.trim().toLowerCase())) {
			this.adicionaDescritor(descricaoItem.trim().toLowerCase());
		}
	}
	
	/**
	 * Metodo responsavel em avaliar um item.
	 * Caso o item ja exista no mapa ele ira apenas atualizar a quantidade do item.
	 * @param descricaoItem descricao do item.
	 * @param item item.
	 * @param quantidade quantidade do item especifico.
	 * @param mapaDoItem mapa do item.
	 * @return retorna true caso o item ja exista no mapa e false caso contrario.
	 */
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
	
	/**
	 * Metodo responsavel em adicionar um item ao mapa que ele pertence.
	 * @param idUsuario identificacao do usuario.
	 * @param descricaoItem descritor do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags para identificacao do item.
	 * @param tipoItem tipo do item, se ele é item doado ou item necessario.
	 * @return retorna a identificao do item.
	 */
	public int adicionaItem(String idUsuario, String descricaoItem, int quantidade, String tags, String tipoItem) {
		exceptions.checaNullOuVazio(descricaoItem, "descricao nao pode ser vazia ou nula.");
		exceptions.verificaQuantidadeItem(quantidade, "quantidade deve ser maior que zero.");
		
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
		} else {
			if (mapa.get(descricaoItem.trim().toLowerCase()).containsValue(item)) {
				for (Item itens: mapa.get(descricaoItem.trim().toLowerCase()).values()) {
					if (itens.equals(item)) {
						return itens.getIdItem();
					}
				}
			}
		}
		
		idItem += 1;
		return idItem - 1;
	}

	/**
	 * Metodo responsavel em excluir um item do mapa que o item pertence.
	 * @param idItem identificacao do item.
	 * @param tipoItem tipo do item, se ele é item doado ou item necessario.
	 */
	public void removeItem(int idItem, String tipoItem) {
		Map<String, Map<Integer, Item>> mapa;
		
		if (tipoItem.equals("itemDoado")) {
			mapa = this.itensDoados;
		} else if (tipoItem.equals("itemNecessario")){
			mapa = this.itensNecessarios;
		} else {
			throw new IllegalArgumentException("Nao compativel com tipo de Item");
		}
		
		for (Map<Integer, Item> mapa2 : mapa.values()) {
			for (Item item : mapa2.values()) {
				if (item.getIdItem() == idItem) {
					mapa2.remove(idItem);
					break;		
				}
			}
		}
	}

	/**
	 * Metodo responsavel em retornar o item de acordo com seu id.
	 * @param descricaoItem descritor do item.
	 * @param id identificacao do item.
	 * @param tipoItem tipo do item, se ele é item doado ou item necessario.
	 * @return
	 */
	public Item getItemId(String descricaoItem, int id, String tipoItem) {
		if (tipoItem.equals("itemDoado")) {
			return itensDoados.get(descricaoItem.trim().toLowerCase()).get(id);
		} else {
			return itensNecessarios.get(descricaoItem.trim().toLowerCase()).get(id);
		}
	}
	
	private Item getItemId(int id) {
		for (Map<Integer, Item> mapa : itensNecessarios.values()) {
			for (int i : mapa.keySet()) {
				if (i == id) {
					return mapa.get(i);
				}	
			}
		}
		for (Map<Integer, Item> mapa : itensDoados.values()) {
			for (int i : mapa.keySet()) {
				if (i == id) {
					return mapa.get(i);
				}	
			}
		}
		throw new IllegalArgumentException(); // TODO
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
	   	 exceptions.checaNullOuVazio(descricao, "texto da pesquisa nao pode ser vazio ou nulo.");
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

	public List<Item> match(String idReceptor, int idItemNecessario) {
		Item item = this.getItemId(idItemNecessario);
		Map<Integer, List<Item>> mapa = new TreeMap<>();
		return adicionaMatch(mapa, item);
	}

	private List<Item> adicionaMatch(Map<Integer, List<Item>> mapa, Item item) {
		for (Item i : itensDoados.get(item.getDescricaoItem()).values()) {
			if (!mapa.containsKey(quebraTags(i, item))) {
				List<Item> lista = new ArrayList<>();
				mapa.put(quebraTags(i, item), lista);
			} 
			mapa.get(quebraTags(i, item)).add(i);
	
		}
		List<List<Item>> listaAux = new ArrayList<>();
		List<Item> retorno = new ArrayList<>();	
		for (List<Item> itens : mapa.values()) {
			Collections.sort(itens, new ComparadorId());
			listaAux.add(itens);
		}

		for (int i = listaAux.size() - 1; i >= 0; i --) {
			for(int j = 0; j < listaAux.get(i).size(); j++) {
					retorno.add(listaAux.get(i).get(j));
				}
			}
		return retorno;
		
	}

	private int quebraTags(Item itemBuscado, Item item) {
		String[] maior;
		String[] menor;
		int quantidade = 0;
		
		if (itemBuscado.getTags().split(",").length >= item.getTags().split(",").length ) {
			maior = itemBuscado.getTags().split(",");
			menor = item.getTags().split(",");

		} else {
			menor = itemBuscado.getTags().split(",");
			maior = item.getTags().split(",");
		}
		
		for (int i = 0; i < maior.length; i++) {
			for (int e = 0; e < menor.length; e++) {
				if (maior[i].equals(menor[e])) {
					if (i == e) {
						quantidade += 10;
					} else {
						quantidade += 5;
					}
				}
			}
		}
		return quantidade + 20;

	}
	
	public String realizaDoacao(int idItemNec, String nomeReceptor, int idItemDoado, String nomeDoador, String data) {
		int quantidadeDoada = 0;
		Item itemDoado = this.getItemId(idItemDoado);
		Item itemNec = this.getItemId(idItemNec);
		Doacao doacao = new Doacao(nomeDoador, itemDoado.getIdUsuario(), nomeReceptor, itemNec.getIdUsuario(), itemNec.getDescricaoItem(), data);
		if (itemDoado.getQuantidade() > itemNec.getQuantidade()) {
			quantidadeDoada = itemNec.getQuantidade();
			itemDoado.setQuantidade(itemDoado.getQuantidade() - itemNec.getQuantidade());
			itensNecessarios.get(itemNec.getDescricaoItem()).remove(itemNec.getIdItem());
			
		} else if (itemDoado.getQuantidade() < itemNec.getQuantidade()) {
			quantidadeDoada = itemDoado.getQuantidade();
			itemNec.setQuantidade(itemNec.getQuantidade() - itemDoado.getQuantidade());
			itensDoados.get(itemDoado.getDescricaoItem()).remove(itemDoado.getIdItem());
			
		} else {
			quantidadeDoada = itemNec.getQuantidade();
			itensNecessarios.get(itemNec.getDescricaoItem()).remove(itemNec.getIdItem());
			itensDoados.get(itemDoado.getDescricaoItem()).remove(itemDoado.getIdItem());
		}
		doacao.adicionaQuantidade(quantidadeDoada);
		doacoes.add(doacao);
		return doacao.toString();
	}

	public String listaDoacoes() {
		String retorno = "";
		Collections.sort(doacoes, new ComparadorData());
		for (int i = 0; i < doacoes.size(); i ++) {
			if (i != doacoes.size() - 1) {
				retorno += doacoes.get(i).toString() + " | ";
			} else {
				retorno += doacoes.get(i).toString();
			}
		}
		return retorno;
	}

	public void verificaExclusaoItem(int idItemNec, int idItemDoado) {
		Item itemDoado = this.getItemId(idItemDoado);
		Item itemNec = this.getItemId(idItemNec);
		
	}
}