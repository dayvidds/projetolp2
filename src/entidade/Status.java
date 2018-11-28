package entidade;

public enum Status {
	/* Não é permitido criar novas instâncias com a palavra chave new.
	 * Seguindo a convenção, por serem objetos constantes e imutáveis (static final), 
	 * os nomes declarados recebem todas as letras em MAIÚSCULAS;
	 * As instâncias dos tipos enum devem obrigatoriamente ter apenas um nome;
	*/
	
	RECEPTOR("receptor"),DOADOR("doador");
	
	private String status;
	
	Status(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}


