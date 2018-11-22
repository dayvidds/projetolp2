package entidade;

public class Item {

	private String idDoador;
	private String descricaoItem;
	private int quantidade;
	private String tags;
	
	public Item(String idDoador, String descricaoItem, int quantidade, String tags) {
		this.idDoador = idDoador;
		this.descricaoItem = descricaoItem;
		this.quantidade = quantidade;
		this.tags = tags;
		
	}

}
