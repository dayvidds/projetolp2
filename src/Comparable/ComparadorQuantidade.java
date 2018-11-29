package Comparable;

import java.util.Comparator;

import entidade.Item;

/**
 *
 * @author Caroliny Regina.
 *
 */
public class ComparadorQuantidade implements Comparator<Item> {
	
	public int compare(Item item1, Item item2) {
		int comparacao = item1.compareTo(item2);
		if (comparacao == 0) {
			comparacao = item1.getDescricaoItem().compareTo(item1.getDescricaoItem());
		}
		return comparacao;
	}

}