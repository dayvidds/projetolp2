package entidade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um Usuario.
 * Um Usuario possui nome, email, celular, classe, documento, status e itens.
 * 
 * @author Matheus Augusto, Thiago Nascimento e Dayvid Daniel.
 *
 */
public class Usuario implements Serializable{
	
	/**
	 * Nome do usuario.
	 */
	private String nome;
	
	/**
	 * Email do usuario.
	 */
	private String email;
	
	/**
	 * Celular do usuario.
	 */
	private String celular;
	
	/**
	 * Classe do usuario.
	 */
	private TipoUsuario classe;
	
	/**
	 * Documento de identificacao do usuario.
	 */
	private String documento;
	
	/**
	 * Status do usuario.
	 */
	private Status status;
	
	/**
	 * Mapa de itens do usuario.
	 */
	private Map<Integer, Item> itens;

	/**
	 * Constroi um usuario.
	 * Um usuario possui um documento de identificacao unica, um nome, um email, um celular, uma classe e um status.
	 * 
	 * @param documento documento de identificacao do usuario.
	 * @param nome nome do usuario.
	 * @param email email do usuario.
	 * @param celular celular do usuario.
	 * @param classe classe do usuario.
	 * @param status status do usuario.
	 */
	public Usuario(String documento, String nome, String email, String celular, TipoUsuario classe, Status status) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.documento = documento;	
		this.status = status;
		this.itens = new HashMap<>();
    }
	
	/**
	 * Retorna o nome do usuario.
	 * @return retorna o nome do usuario.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o atributo nome do usuario.
	 * @param nome novo nome do usuario.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Altera o atributo email do usuario.
	 * @param email novo email do usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Altera o atributo celular do usuario.
	 * @param celular novo celular do usuario.
	 */
	public void setCelular(String celular) {
		this.celular = celular; 
	}

	/**
	 * Retorna o status do usuario.
	 * @return retorna o status do usuario.
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Retorna o identificador do usuario.
	 * @return retorna o identificador do usuario.
	 */
	public String getId() {
		return this.documento;
	}
	
	/**
	 * Retorna o mapa de itens do usuario.
	 * @return retorna o mapa de itens do usuario.
	 */
	public Map<Integer, Item> getItens() {
		return this.itens;
	}
	
	/**
	 * Metodo responsavel por concatenar nome e id do usuario.
	 * 
	 * @return uma string de concatenacao nome/usuario
	 */
	public String getNomeIdentificacao() {
		return this.nome + "/" + this.documento;
	}
	
	/**
	 * Adiciona um item ao mapa do usuario.
	 * @param idItem identificacao do item.
	 * @param item item.
	 */
	public void adicionaItem(int idItem, Item item) {
		itens.put(idItem, item);
	}
	
	/**
	 * Verifica se o item especifico existe no mapa.
	 * @param idItem identificacao do item.
	 * @return retorna true se o item existir e false caso contrario.
	 */
	public boolean verificaItem(int idItem) {
		return itens.containsKey(idItem);
	}

	/**
	 * Retorna as informacoes de um item expecifico.
	 * @param idItem identificacao do item.
	 * @return retorna uma string com as informacoes do item.
	 */
	public String exibeItem(int idItem) {
		return itens.get(idItem).toString();
	}

	/**
	 * Atualiza as informacoes de um item especifico do usuario.
	 * @param idItem identificacao do item.
	 * @param quantidade nova quantidade do item.
	 * @param tags novas tags do item.
	 * @return retorna a representacao atualizada do item.
	 */
	public String atualizaItem(int idItem, int quantidade, String tags) {
		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		itens.get(idItem).atualizaItem(quantidade, tags);
		return itens.get(idItem).toString();
	}

	/**
	 * Remove um determinado item do mapa de usuario.
	 * @param idItem identificacao do item.
	 */
	public void removeItem(int idItem) {
		if(this.itens.size() == 0) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!verificaItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		itens.remove(idItem);
		
	}	

	/**
	 * Cria uma representação, em inteiro, para Usuario atraves do seu documento de identificacao.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
		return result;
	}

	/**
	 * Metodo responsavel em comparar se objeto vindo como parametro e igual ao Usuario atual.
	 * Usuarios serao iguais caso tenham o mesmo documento de identificacao
	 * 
	 * @return retorna true se dois Usuarios forem iguais, false caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	/**
	 * Retorna uma representacao textual com as informacoes do Usuario.
	 * A representacao possui o formato: "nome/documento, email, celular, status: " 
	 * @return retorna a representacao textual do Usuario.
	 */
	@Override
	public String toString() {
		return nome + "/" + documento + ", " + email + ", " + celular + ", status: " +  status.getStatus();
	}
}
