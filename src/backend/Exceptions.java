package backend;

import entidade.TipoUsuario;
	/**
	 * Classe de exceções, essa classe serve para testar todos 
	 * os possiveis erros que podem acontecer no código 
	 * 
	 * @author matheus
	 */
public class Exceptions {
	
	public Exceptions() {
		
	}
	
	/**
	 * Metodo checaNullOuVazio: Esse metodo recebe qualquer paramentro no formato de String 
	 * e lança uma exceçao caso ela seja nula ou vazia
	 * 
	 * @param string Qualquer paramentro no formato de String Ex: Id, nome, email, entre varios outros 
	 * @param mensagem Mensagem de exceçao
	 * 
	 */
	public void checaNullOuVazio(String string, String mensagem) {
		if (string == null) {
			throw new NullPointerException("Entrada invalida: " + mensagem);
		} else if (string.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: " + mensagem);
		}
	}
	/**
	 * Metodo verificaQuantidadeItem: caso a quantidade seja menor de 1, uma mensagem de erro e lançada
	 * 
	 * @param quantidade Quantidade de itens
	 * @param mensagem   Mensagem de erro
	 */
	public void verificaQuantidadeItem(int quantidade, String mensagem) {
		if (quantidade < 1) {
			throw new IllegalArgumentException("Entrada invalida: " + mensagem);
		}
	}
	/**
	 * Metod verificaValorIdItem: Caso tente passar um Id negativo uma mensagem de erro e lançada
	 * 
	 * @param valor Valor do Id
	 */
	public void verificaValorIdItem(int valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		}
	}
	/**
	 * Metodo validador de cadastra usuario, caso tente passar um parametro vazio ou nulo esse metodo
	 * chama o metodo checaNullOuVazio, passando cada parametro um por um e testa se ele passa sem lançar
	 * uma exceçao.
	 * 
	 * @param id      Id do usuario
	 * @param nome    Nome do usuario
	 * @param email   Email do usuario
	 * @param celular Celular do usuario
	 * @param classe  Classe de identificaçao do usuario
	 */
	public void verificaEntradaControladorUsuario(String id, String nome, String email, String celular,String classe) {
		checaNullOuVazio(id,"id do usuario nao pode ser vazio ou nulo.");
		checaNullOuVazio(nome,"nome nao pode ser vazio ou nulo." );
		checaNullOuVazio(email,"email nao pode ser vazio ou nulo.");
		checaNullOuVazio(celular,"celular nao pode ser vazio ou nulo.");
		checaNullOuVazio(classe, "classe nao pode ser vazia ou nula.");
		verificaTipoUsuario(classe);
	}
	/**
	 * Metodo verificador de todos os parametros de usuario, este metodo chama outro metodo
	 * passando cada um dos paramentros e o metodo checaNullOuVazio fica responsavel por testar cada um
	 * desses parametros
	 * 
	 * @param id      Id do Usuario
	 * @param nome    Nome do Usuario
	 * @param email   Email do Usuario 
	 * @param celular Celular do Usuario
	 */
	public void verificaInformacoesDeUsuario(String id, String nome, String email, String celular) {
		checaNullOuVazio(id,"id do usuario nao pode ser vazio ou nulo.");
		checaNullOuVazio(nome,"nome nao pode ser vazio ou nulo." );
		checaNullOuVazio(email,"email nao pode ser vazio ou nulo.");
		checaNullOuVazio(celular,"celular nao pode ser vazio ou nulo.");
	}
	/**
	 * Metodo verificador de qual tipo a classe de Usuario pertence
	 * Caso a classe n esteja catalogada uma exceçao e lançada
	 * 
	 * @param classe   Classe do Usuario
	 */
	public void verificaTipoUsuario(String classe) {
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