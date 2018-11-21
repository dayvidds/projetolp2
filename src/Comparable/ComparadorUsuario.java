package Comparable;

import java.util.Comparator;

import entidade.Usuario;

public class ComparadorUsuario implements Comparator<Usuario> {
	
	public int compare(Usuario usuario1, Usuario usuario2) {
		return usuario1.getNome().toLowerCase().compareTo(usuario2.getNome().toLowerCase());
	}
}
