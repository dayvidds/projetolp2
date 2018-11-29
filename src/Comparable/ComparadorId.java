package Comparable;

import java.util.Comparator;

import entidade.Item;

public class ComparadorId implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getIdItem() < o2.getIdItem()) {
			return -1;
		} else if (o1.getIdItem() > o2.getIdItem()) {
			return 1;
		}
		return 0;
	}
}
