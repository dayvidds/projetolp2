package Controller;

import entidade.Item;

public class Doacao {

	private Item itemDoa;
	private Item itemNec;
	private String data;

	public Doacao(Item itemDoa, Item itemNec, String data) {
		this.itemDoa = itemDoa;
		this.itemNec = itemNec;
		this.data = data;
	}

}
