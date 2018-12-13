package comparadores;

import java.util.Comparator;

import entidades.Usuario;
/**
 * Classe comparadora, aqui ira organizar os usuarios cadastrados e retornar em ordem alfabetica
 * 
 * @author Matheus Augusto
 *
 */
public class ComparadorUsuario implements Comparator<Usuario> {
	
	public int compare(Usuario usuario1, Usuario usuario2) {
		return usuario1.getNome().toLowerCase().compareTo(usuario2.getNome().toLowerCase());
	}
}
