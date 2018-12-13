package comparadores;

import java.util.Comparator;

import entidades.Item;

/**
 * Classe responsavel em comparar itens atraves do seu identificador.
 * @author Dayvid Daniel.
 *
 */
public class ComparadorId implements Comparator<Item> {

	/**
	 * Compara dois itens atraves do seu identificador.
	 * Se o id do item 1 for menor que o id item 2 sera retornado o valor -1;
	 * Se o id do item 1 for maior que o id do item 2 sera retornado o valor 1;
	 * Se os dois itens tivem os identificadoes iguais sera retornado 0;
	 */
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
