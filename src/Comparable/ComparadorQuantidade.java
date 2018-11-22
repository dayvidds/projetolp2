package Comparable;

import java.util.Comparator;

import entidade.Item;

public class ComparadorQuantidade implements Comparator<Item> {
	
	public int compare(Item item1, Item item2) {
		return item1.compareTo(item2);
	}

}
