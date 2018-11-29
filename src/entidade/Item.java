package entidade;

import java.time.LocalDate;

public class Item implements Comparable<Item>{

	/**
	 * Identificacao do doador.
	 */
	private String idUsuario;
	
	/**
	 * Identificacao do item.
	 */
	private int idItem;
	
	/**
	 * Descricao do item.
	 */
	private String descricaoItem;
	
	/**
	 * Quantidade do item.
	 */
	private int quantidade;
	
	/**
	 * Tags para identificacao do item.
	 */
	private String tags;
	
	/**
	 * Data que o item foi inserido no sistema.
	 */
	private LocalDate data;
	
	/**
	 * Constroi um item. Todo item possue um idUsuario, idItem, descricao, quantidade e tags.
	 * @param idUsuario identificacao do Usuario.
	 * @param idItem identificacao do item.
	 * @param descricaoItem descricao do item.
	 * @param quantidade quantidade do item.
	 * @param tags tags para identificacao do item.
	 */
	public Item(String idUsuario, int idItem, String descricaoItem, int quantidade, String tags){
		this.idUsuario = idUsuario;
		this.idItem = idItem;
		this.descricaoItem = descricaoItem.trim().toLowerCase();
		this.quantidade = quantidade;
		this.tags = tags;
		this.data = LocalDate.now();
		
	}
	
	/**
	 * Retorna a quantidade do item.
	 * @return retorna a quantidade do item.
	 */
	public int getQuantidade() {
		return this.quantidade;
	}
	
	/**
	 * Retorna o idUsuario.
	 * @return retorna o idUsuario.
	 */
	public String getIdUsuario() {
		return this.idUsuario;
	}
	
	/**
	 * Retorna o idItem.
	 * @return retorna o idItem.
	 */
	public int getIdItem() {
		return this.idItem;
	}
	
	/**
	 * Retorna a descricao do item.
	 * @return retorna a descricao do item.
	 */
	public String getDescricaoItem() {
		return this.descricaoItem;
	}
	
	/**
	 * Altera o atributo quantidade.
	 * @param quantidade nova quantidade do item.
	 */
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