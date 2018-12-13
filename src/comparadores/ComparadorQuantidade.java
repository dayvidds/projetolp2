package comparadores;

import java.util.Comparator;

import entidades.Item;

/**
 * Classe responsavel por comparar dois itens doados de acordo com sua quantidade registrada no sistema. 
 * @author Caroliny Regina.
 *
 */
public class ComparadorQuantidade implements Comparator<Item> {
	
	
	/**
	 * Compara os itens de acordo com sua quantidade.
	 * Se o item1 estiver em menor quantidade que o item2, e retornado um numero menor que 0.
	 * Se o item1 estiver em maior quantidade que o item2, e retornado um numero maior que 0.
	 * Caso os dois itens tenham a mesma quantidade, ambos serao comparados pelos seus descritores para assim serem organizados em ordem alfabetica.
	 */
	public int compare(Item item1, Item item2) {
		int comparacao = item1.compareTo(item2);
		if (comparacao == 0) {
			comparacao = item1.getDescricaoItem().compareTo(item1.getDescricaoItem());
		}
		return comparacao;
	}

}