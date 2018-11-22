package entidade;

public class Item implements Comparable<Item>{

	private String idDoador;
	private String descricaoItem;
	private int quantidade;
	private String tags;
	
	public Item(String idDoador, String descricaoItem, int quantidade, String tags){
		this.idDoador = idDoador;
		this.descricaoItem = descricaoItem;
		this.quantidade = quantidade;
		this.tags = tags;
		
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public String getIdDoador() {
		return this.idDoador;
	}

	@Override
	public int compareTo(Item o) {
		int retorno = o.getQuantidade() - this.getQuantidade();
		return retorno;
	}

}
