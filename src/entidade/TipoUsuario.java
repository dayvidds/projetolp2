package entidade;

public enum TipoUsuario {
	
	/* Não é permitido criar novas instâncias com a palavra chave new.
	 * Seguindo a convenção, por serem objetos constantes e imutáveis (static final), 
	 * os nomes declarados recebem todas as letras em MAIÚSCULAS;
	 * As instâncias dos tipos enum devem obrigatoriamente ter apenas um nome;
	*/
	
	PESSOA_FISICA("PESSOA_FISICA"), IGREJA("IGREJA"), ORGAO_PUBLICO_MUNICIPAL("ORGAO_PUBLICO_MUNICIPAL"), 
	ORGAO_PUBLICO_ESTADUAL("ORGAO_PUBLICO_ESTADUAL"), ORGAO_PUBLICO_FEDERAL("ORGAO_PUBLICO_FEDERAL"), 
	ONG("ONG"), ASSOCIACAO("ASSOCIACAO"), SOCIEDADE("SOCIEDADE");
	
	private String classe;
	
	
	TipoUsuario(String classe) {
		
		this.classe = classe;
	}


	public String getClasse() {
		return classe;
	}
}

