package Controller;

import java.util.HashMap;
import java.util.Map;

import backend.Exceptions;
import entidade.Status;
import entidade.TipoUsuario;
import entidade.Usuario;

public class ControladorUsuario {
	private Map<String, Usuario> usuarios;

	public ControladorUsuario() {
		usuarios = new HashMap<String, Usuario>();
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
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
		
		} else if (usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		}
		
		usuarios.put(id, new Usuario(id, nome, email, celular, TipoUsuario.valueOf(classe),Status.valueOf("DOADOR")));
		return id;
	}
	
	public String pesquisaUsuarioPorId(String id) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else if (!usuarios.containsKey(id)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		}
		Usuario usuario = usuarios.get(id);
		return usuario.toString();
	}
}
