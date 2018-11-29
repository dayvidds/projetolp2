package entidade;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	private String nome;
	private String email;
	private String celular;
	private TipoUsuario classe;
	private String documento;
	private Status status;
	private Map<Integer, Item> itens;

	public Usuario(String documento, String nome, String email, String celular, TipoUsuario classe, Status status) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.documento = documento;	
		this.status = status;
		this.itens = new HashMap<>();
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emial) {
		this.email = emial;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular; 
	}

	public TipoUsuario getClasse() {
		return classe;
	}

	public void setClasse(TipoUsuario classe) {
		this.classe = classe;
	}

	public String getIdentificacao() {
		return documento;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setIdentificacao(String identificacao) {
		this.documento = identificacao;
	}
	
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
	
	public void adicionaItem(int idItem, Item item) {
		itens.put(idItem, item);
	}
	
	public boolean verificaItem(int idItem) {
		return itens.containsKey(idItem);
	}

	public String exibeItem(int idItem) {
		return itens.get(idItem).toString();
	}

	public String atualizaItem(int idItem, int quantidade, String tags) {
		if (!itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		itens.get(idItem).atualizaItem(quantidade, tags);
		return itens.get(idItem).toString();
	}

	public void removeItem(int idItem) {
		if(this.itens.size() == 0) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!verificaItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		if(!this.itens.containsKey(idItem)) {
			String idItemString = "" + this.itens.get(idItem).getId();
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		itens.remove(idItem);
		
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((documento == null) ? 0 : documento.hashCode());
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
		Usuario other = (Usuario) obj;
		if (documento == null) {
			if (other.documento != null)
				return false;
		} else if (!documento.equals(other.documento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome + "/" + documento + ", " + email + ", " + celular + ", status: " +  status.getStatus();
	}
}
