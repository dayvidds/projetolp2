package entidade;

public class Item implements Comparable<Item>{

	private String idDoador;
	private int idItem;
	private String descricaoItem;
	private int quantidade;
	private String tags;
	
	public Item(String idDoador, int idItem, String descricaoItem, int quantidade, String tags){
		this.idDoador = idDoador;
		this.idItem = idItem;
		this.descricaoItem = descricaoItem.trim().toLowerCase();
		this.quantidade = quantidade;
		this.tags = tags;
		
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public String getIdDoador() {
		return this.idDoador;
	}
	
	public int getId() {
		return this.idItem;
	}
	
	public String getDescricaoItem() {
		return this.descricaoItem;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
		
	}
	
	public void atualizaItem(int quantidade, String tags) {
		if (tags != null && !tags.equals("")) {
			this.tags = tags;
		} 
		
		if (quantidade > 0 ) {
			this.quantidade = quantidade;
		}
		
	}
	
	private String separaTag() {
		String[] tagsLista = tags.split(",");
		String retorno = "";
		for (int i = 0; i < tagsLista.length; i ++) {
			if (i == tagsLista.length - 1) {
				retorno += tagsLista[i];
			} else {
				retorno += tagsLista[i] + ", ";
			}
		}
		return retorno;
		
	}

	@Override
	public int compareTo(Item o) {
		int retorno = o.getQuantidade() - this.getQuantidade();
		return retorno;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricaoItem == null) {
			if (other.descricaoItem != null)
				return false;
		} else if (!descricaoItem.equals(other.descricaoItem))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return idItem + " - " + descricaoItem + ", tags: [" + separaTag() + "], quantidade: " + quantidade;
		
	}
}