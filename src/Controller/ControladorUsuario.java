package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import backend.Exceptions;
import entidade.Item;
import entidade.Status;
import entidade.TipoUsuario;
import entidade.Usuario;

public class ControladorUsuario {

	private Map<String, Usuario> usuarios;

	public ControladorUsuario() {
		usuarios = new LinkedHashMap<String, Usuario>();
	}
	
	public void novosReceptores(String caminho) throws FileNotFoundException  {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		linha = sc.nextLine();
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			String[] novosUsuarios = linha.split(",");
			adicionaReceptor(novosUsuarios[0], novosUsuarios[1], novosUsuarios[2], novosUsuarios[3], novosUsuarios[4]);
		}
		sc.close();
	}
	public void atualizaReceptores(String caminho) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		linha = sc.nextLine();
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			String[] novosUsuarios = linha.split(",");
			atualizaInformacaoDeUsuario(novosUsuarios[0], novosUsuarios[1], novosUsuarios[2], novosUsuarios[3]);
		}
		sc.close();

		
	}
	public void lerrecptor(String caminho) throws FileNotFoundException{
		
		if (caminho.equals("arquivos_sistema/novosReceptores.csv")) {
			novosReceptores(caminho);
		}else if (caminho.equals("arquivos_sistema/atualizaReceptores.csv")){
			atualizaReceptores(caminho);
		}
			
		}

	public void erroUsuarioJaExiste(String string) {
		if (usuarios.containsKey(string)) {
			throw new IllegalArgumentException("Usuario ja existente: " + string + ".");
		}
	}

	public void erroUsuarioNaoExiste(String string) {
		if (!usuarios.containsKey(string)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + string + ".");
		}
	}

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		Exceptions.verificaEntradaControladorUsuario(id, nome, email, celular, classe);
		erroUsuarioJaExiste(id);

		usuarios.put(id, new Usuario(id, nome, email, celular, TipoUsuario.valueOf(classe), Status.valueOf("DOADOR")));
		return id;
	}

	public String adicionaReceptor(String id, String nome, String email, String celular, String classe) {
		Exceptions.verificaEntradaControladorUsuario(id, nome, email, celular, classe);
		erroUsuarioJaExiste(id);
		usuarios.put(id,
				new Usuario(id, nome, email, celular, TipoUsuario.valueOf(classe), Status.valueOf("RECEPTOR")));
		return id;
	}

	public String pesquisaUsuarioPorId(String id) {
		Exceptions.checaNullOuVazio(id, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(id);
		Usuario usuario = usuarios.get(id);
		return usuario.toString();
	}

	public String pesquisaUsuarioPorNome(String nome) {
		Exceptions.checaNullOuVazio(nome, "nome nao pode ser vazio ou nulo.");
	
		String saida = "";
		int contador = 0;
		for (Usuario usuario : usuarios.values()) {
			if (usuario.getNome().equals(nome)) {
				saida += usuario.toString();
				contador += 1;
				if (contador < usuarios.size()) {
						saida += " | ";
				}
			}
		}
		if (saida.isEmpty()) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		}
		saida = saida.substring(0, saida.length()-3);
		
		return saida;
	}

	public String atualizaInformacaoDeUsuario(String id, String nome, String email, String celular) {
		Exceptions.checaNullOuVazio(id, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(id);
		Usuario usuario = usuarios.get(id);
	
		if (nome != null && !nome.isEmpty()) {
			usuario.setNome(nome);
		}
		if (email != null && !email.isEmpty()) {
			usuario.setEmail(email);
		}
		if (celular != null && !celular.isEmpty()) {
			usuario.setCelular(celular);
		}
		return usuario.toString();
	}

	public void removeUsuario(String id) {
		Exceptions.checaNullOuVazio(id, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(id);
		usuarios.remove(id);
		
	}
	
	public void adiconaItemParaDoacao(String idDoador, int idItem, Item item) {
		usuarios.get(idDoador).adicionaItemDoado(idItem, item);
		
	}
	
	public String getNomeIdentificacao(String id) {
		return this.usuarios.get(id).getNomeIdentificacao();
	}

	public String exibeItem(String idDoador, int idItem) {
		return usuarios.get(idDoador).exibeItem(idItem);
		
	}

	public String atualizaItemParaDoacao(int idItem, String idDoador) {
		return usuarios.get(idDoador).atualizaItemParaDoacao(idItem);
	}

	public void removeItemParaDoacao(int idItem, String idDoador) {
		usuarios.get(idDoador).removeItemParaDoacao(idItem);
		
}
	}

