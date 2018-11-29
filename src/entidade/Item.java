package entidade;

import java.time.LocalDate;

/**
 * Classe que representa um item. Um item possui id do usuario, id do item, descritor, quantidade, tags e data.
 * @author Thiago Nascimento, Dayvid Daniel e Caroliny Regina.
 *
 */
public class Item implements Comparable<Item>{

	/**
	 * Identificacao do Usuario.
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
	
	/**
	 * Atualiza as informacoes do item. Apenas a quantidade e as tags podem ser alteradas.
	 * Caso o parametro quantidade seja 0, a quantidade do item nao sera alterada.
	 * Caso o parametro tags seja null ou vazio, as tags do item nao serao alteradas.
	 * @param quantidade nova quantidade do item.
	 * @param tags novas tags do item.
	 */
	public void atualizaItem(int quantidade, String tags) {
		if (tags != null && !tags.equals("")) {
			this.tags = tags;
		}
		if (quantidade > 0 ) {
			this.quantidade = quantidade;
		}
	}
	
	/**
	 * Separa as tags para usar na representacao textual.
	 * @return retorna uma string com as tags separadas.
	 */
	private String separaTag() {
		return this.tags.replace(",", ", ");
	}

	/**
	 * Metodo responsavel em comparar os itens atraves da quantidade.
	 */
	@Override
	public int compareTo(Item o) {
		int retorno = o.getQuantidade() - this.getQuantidade();
		return retorno;
	}

	/**
	 * Cria uma representação, em inteiro, para Item atraves do descritor e tags.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoItem == null) ? 0 : descricaoItem.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/**
	 * Metodo responsavel em comparar se objeto vindo como parametro e igual ao item atual.
	 * Itens serao iguais caso tenham o mesmo descritor e as mesmas tags.
	 * @return retorna true se os dois itens forem iguais, false caso contrario.
	 */
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
	
	/**
	 * Retorna uma representacao textual com as informacoes do item.
	 * A representacao possui o formato: "nome - descricaoItem, + tags: [], quantidade: " 
	 * @return retorna a representacao textual do item.
	 */
	@Override
	public String toString() {
		return idItem + " - " + descricaoItem + ", tags: [" + separaTag() + "], quantidade: " + quantidade;
	}
}