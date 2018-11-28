package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Comparable.ComparadorId;
import backend.Exceptions;
import entidade.Item;
import entidade.Status;
import entidade.TipoUsuario;
import entidade.Usuario;

/**
 * Classe que representa um controlador de Usuarios.
 * 
 * @author Matheus Augusto
 *
 */
public class ControladorUsuario {

	private Map<String, Usuario> usuarios;

	public ControladorUsuario() {
		usuarios = new LinkedHashMap<String, Usuario>();
	}

	/**
	 * Metodo que ler os arquivos e identifica o funçao do arquivo e o manda para o
	 * metodo certo
	 * 
	 * @param caminho Caminho do arquivo
	 * 
	 *                caso o caminho leve para u arquivo aceitavel ira mandalo para
	 *                seu respctivo metodo.
	 */
	public void lerrecptor(String caminho) throws FileNotFoundException {

		if (caminho.equals("arquivos_sistema/novosReceptores.csv")) {
			novosReceptores(caminho);
		} else if (caminho.equals("arquivos_sistema/atualizaReceptores.csv")) {
			atualizaReceptores(caminho);
		}
	}

	/**
	 * Metodo que ler o arquivo de novos receptores.
	 * 
	 * @param caminho Caminho do arquivo
	 * @throws FileNotFoundException
	 * 
	 *                               caso os paramentros passado nesse arquivo sejam
	 *                               aceitaveis, ira cadastralo no LinkedHashMap
	 */
	public void novosReceptores(String caminho) throws FileNotFoundException {
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

	/**
	 * Metodo que ler o arquivo que vai atualizar as informaçoes dos receptores.
	 * 
	 * @param caminho Caminho do arquivo
	 * @throws FileNotFoundException
	 * 
	 *                               caso os paramentros passado nesse arquivo seja
	 *                               aceitavel ira atualizar suas informaçoes no
	 *                               metodo atualizaInformacaoDeUsuario.
	 */
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

	/**
	 * Metodo que verifica se o Usuario existe, passando uma String que pode ser o
	 * Id de usuario ou nome.
	 * 
	 * @param String String que representa o Id ou nome de Usuario
	 * 
	 *               Caso o Id ou nome passado nao sejam validos, retorna um erro,
	 *               informando que o Usuario já está cadastrado, e assim impedinto
	 *               o cadastro de um mesmo usuario diversas vezes.
	 * 
	 */
	public void erroUsuarioJaExiste(String string) {
		if (usuarios.containsKey(string)) {
			throw new IllegalArgumentException("Usuario ja existente: " + string + ".");
		}
	}

	/**
	 * Metodo que verifica se o Usuario nao existe, passando uma String que pode ser
	 * o Id de usuario ou nome.
	 * 
	 * @param String String que representa o Id ou nome de Usuario
	 * 
	 *               Caso o Id ou nome passaddo nao sejam validos, retorno um erro.
	 * 
	 */
	public void erroUsuarioNaoExiste(String string) {
		if (!usuarios.containsKey(string)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + string + ".");
		}
	}

	// <CRUD USUARIOS>

	/**
	 * Metodo que adiciona um Usuarios doadores ao LinkedHashMap
	 * 
	 * @param id      Id do Usuario, representado por cfp e cnpj, representado como
	 *                String
	 * @param nome    Nome do Usuario representado como String
	 * @param email   Email do Usuario representado como String
	 * @param celular Celular do Usuario representado como String
	 * @param classe  Classe do Usuario, que representa sua classe de acordo com o a
	 *                "Classe Enum"
	 * @return id do Usuario.
	 * 
	 *         Metodo antes de cadastrar o Usuario doador, manda todas as suas
	 *         informaçoes para a classe exceptions para testar e ver se tem algum
	 *         paramentro nao aceitavel. Ex: nome null ou vazio.
	 */

	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		Exceptions.verificaEntradaControladorUsuario(id, nome, email, celular, classe);
		erroUsuarioJaExiste(id);

		usuarios.put(id, new Usuario(id, nome, email, celular, TipoUsuario.valueOf(classe), Status.valueOf("DOADOR")));
		return id;
	}
	// <CRUD USUARIOS>

	/**
	 * Metodo que adiciona um Usuarios RECEPTOR passado pelo arquivo de configuração
	 * do sistema ao LinkedHashMap
	 * 
	 * @param id      Id do Usuario, representado por cfp e cnpj, representado como
	 *                String
	 * @param nome    Nome do Usuario representado como String
	 * @param email   Email do Usuario representado como String
	 * @param celular Celular do Usuario representado como String
	 * @param classe  Classe do Usuario, que representa sua classe de acordo com o a
	 *                "Classe Enum"
	 * @return id do Usuario.
	 * 
	 *         Metodo antes de cadastrar o Usuario RECEPTOR, manda todas as suas
	 *         informaçoes para a classe exceptions para testar e ver se tem algum
	 *         paramentro nao aceitavel. Ex: nome null ou vazio.
	 */
	public String adicionaReceptor(String id, String nome, String email, String celular, String classe) {
		Exceptions.verificaEntradaControladorUsuario(id, nome, email, celular, classe);
		erroUsuarioJaExiste(id);
		usuarios.put(id,
				new Usuario(id, nome, email, celular, TipoUsuario.valueOf(classe), Status.valueOf("RECEPTOR")));
		return id;
	}

	/**
	 * Metodo que pesquisa usuario pelo seu ID, caso nao ache ira lança uma mensagem
	 * de erro. Caso ache ira retornar seu String de Usuario.
	 * 
	 * @param Id de Usuario representado como String
	 * 
	 * @return toString do Usuario
	 */
	public String pesquisaUsuarioPorId(String id) {
		Exceptions.checaNullOuVazio(id, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(id);
		Usuario usuario = usuarios.get(id);
		return usuario.toString();
	}

	/**
	 * Metodo que pesquisa Usuario pelo seu Nome, caso nao ache ira lança uma
	 * mensagem de erro. Caso ache ira retornar seu String de Usuario.
	 * 
	 * @param Nome de Usuario representado como String
	 * 
	 * @return toString do Usuario
	 */
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
		saida = saida.substring(0, saida.length() - 3);

		return saida;
	}

	/**
	 * Metodo para editar Usuario
	 * 
	 * @param nome      Nome do Usuario representado como String
	 * @param atributos Atributos que vao ser editados entre eles: nome, email e
	 *                  celular.
	 * @return Novo toString do Usuario, caso conclua com sucesso.
	 *
	 *         Caso tente passar um atributo que nao seja nem nome, email ou celular
	 *         ira lancar um mensargem de erro. Caso tente mudar um usuario que nao
	 *         exista no cadastrado ele ira lancar outra mensargem de erro.
	 */
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

	/**
	 * Metodo para remover Usuario do LinkedHashMap
	 * 
	 * @param id Id do Usuario representado como String
	 * 
	 *           Caso tente passar um Id que nao esteja cadastrado ira retorno um
	 *           mensargem de erro. Caso passe com sucesso, ira remover o Usuario.
	 */
	public void removeUsuario(String id) {
		Exceptions.checaNullOuVazio(id, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(id);
		usuarios.remove(id);
	}

	/**
	 * Metodo responsavel por resgatar a concatenacao do nome e id de um usuario.
	 * 
	 * @param id id do usuario que queremos resgatar a concatenacao
	 * @return uma string nome/id
	 */
	public String getNomeIdentificacao(String id) {
		return this.usuarios.get(id).getNomeIdentificacao();
	}

	public void adicionaItem(String idUsuario, int idItem, Item item, String tipoItem) {
		Exceptions.checaNullOuVazio(idUsuario, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(idUsuario);
		
		if (tipoItem.equals("itemDoado")) {
			if (usuarios.get(idUsuario).getStatus().equals(Status.valueOf("DOADOR"))) {
				usuarios.get(idUsuario).adicionaItem(idItem, item);
			} else {
				// TODO EXCEPTION
			}

		} else if (tipoItem.equals("itemNecessario")) {
			if (usuarios.get(idUsuario).getStatus().equals(Status.valueOf("RECEPTOR"))) {
				usuarios.get(idUsuario).adicionaItem(idItem, item);
			} else {
				// TODO EXCEPTION
			}
		}
	}

	public String exibeItem(String idUsuario, int idItem) {
		erroUsuarioNaoExiste(idUsuario);
		if (!usuarios.get(idUsuario).verificaItem(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		return usuarios.get(idUsuario).exibeItem(idItem);
	}

	public String atualizaItem(int idItem, String idUsuario, int quantidade, String tags) {
		Exceptions.verificaValorIdItem(idItem);
		Exceptions.checaNullOuVazio(idUsuario, "id do usuario nao pode ser vazio ou nulo.");
		erroUsuarioNaoExiste(idUsuario);
		return usuarios.get(idUsuario).atualizaItem(idItem, quantidade, tags);
	}

	public void removeItem(int idItem, String idUsuario) {
		Exceptions.verificaValorIdItem(idItem);
		Exceptions.checaNullOuVazio(idUsuario, "id do usuario nao pode ser vazio ou nulo.");
		
		if (!this.usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		}
		usuarios.get(idUsuario).removeItem(idItem);
	}

	public String listaItensNecessarios() {
		String retorno = "";
		List<Item> itensRetorno = new ArrayList<Item>();
		
		for(Usuario usuario: this.usuarios.values()) {
			if (usuario.getStatus().equals(Status.valueOf("RECEPTOR"))) {
				Map<Integer, Item> itemUsuario = usuario.getItens();
				for (Item item : itemUsuario.values()) {
					if(item != null) {
						itensRetorno.add(item);
					}	
				}
			}
		}
		
		Collections.sort(itensRetorno, new ComparadorId());
		
		String info = "";
		
		for (int i = 0; i < itensRetorno.size(); i++) {
			for (Usuario user : this.usuarios.values()) {
				if (user.getItens().containsValue(itensRetorno.get(i))) {
					info = user.getNome() + "/" + user.getIdentificacao();
					if (i < itensRetorno.size() - 1) {
						retorno += itensRetorno.get(i).toString() + ", Receptor: " + info + " | ";
					} else {
						retorno += itensRetorno.get(i).toString() + ", Receptor: " + info;
					}	
 				}
			}
		}
		return retorno;
	
	}
}
