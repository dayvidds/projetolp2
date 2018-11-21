package backend;

import entidade.TipoUsuario;

public class Exceptions {
	
	public void verificaEntradaControladorUsuario(String id, String nome, String email, String celular, String classe) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		} else if (nome == null || nome.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		
		} else if (email == null || email.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		
		} else if (celular == null || celular.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		
		} else if (classe == null || classe.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		} else if (TipoUsuario.valueOf(classe) == null) {
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		} 
	}
	public void ErroUsuarioJaExiste(String id) {
		throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
	}
}
