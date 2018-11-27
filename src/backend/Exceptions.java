package backend;

import entidade.TipoUsuario;

public class Exceptions {

	public static void checaNullOuVazio(String string, String mensagem) {
		if (string == null) {
			throw new NullPointerException("Entrada invalida: " + mensagem);
		}else if (string.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: " + mensagem);
		}

	}
	
	public static void verificaQuantidadeItem(int quantidade, String mensagem) {
		if (quantidade < 1) {
			throw new IllegalArgumentException("Entrada invalida: " + mensagem);
		}
		
	}
	
	public static void verificaEntradaControladorUsuario(String id, String nome, String email, String celular,String classe) {
		checaNullOuVazio(id,"id do usuario nao pode ser vazio ou nulo.");
		checaNullOuVazio(nome,"nome nao pode ser vazio ou nulo." );
		checaNullOuVazio(email,"email nao pode ser vazio ou nulo.");
		checaNullOuVazio(celular,"celular nao pode ser vazio ou nulo.");
		checaNullOuVazio(classe, "classe nao pode ser vazia ou nula.");
		verificaTipoUsuario(classe);
		
	}

	public static void verificaInformacoesDeUsuario(String id, String nome, String email, String celular) {
		checaNullOuVazio(id,"id do usuario nao pode ser vazio ou nulo.");
		checaNullOuVazio(nome,"nome nao pode ser vazio ou nulo." );
		checaNullOuVazio(email,"email nao pode ser vazio ou nulo.");
		checaNullOuVazio(celular,"celular nao pode ser vazio ou nulo.");
		
	}
	public static void verificaTipoUsuario(String classe) {
		boolean entrou = false;
		for(TipoUsuario tipo: TipoUsuario.values()) {
			if (classe.equals(tipo.getClasse())) {
				entrou = true;
			}
		}
		
		if (!entrou) {
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
	}
}