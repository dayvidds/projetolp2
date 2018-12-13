package controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entidades.Item;

/**
 * Classe que representa uma doacao.
 * @author Thiago Nascimento, Dayvid Daniel
 *
 */
public class Doacao {

	/**
	 * Concatenacao do nome e o id do doador.
	 */
	private String nomeIdDoador;
	
	/**
	 * Concatenacao do nome e o id do receptor.
	 */
	private String nomeIdReceptor;
	
	/**
	 * descritor dos itens.
	 */
	private String descritor;
	
	/**
	 * data da doacao.
	 */
	private LocalDate data;
	
	/**
	 * Quantidade de itens da doacao.
	 */
	private int quantidade;
	
	/**
	 * Formatacao da data.
	 */
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	/**
	 * Constroi uma doacao.
	 * Toda doacao possui o nome com id do doador e receptor, descritor dos itens e data.
	 * @param nomeDoador nome do doador.
	 * @param idDoador identificador do doador.
	 * @param nomeRecept nome do receptor.
	 * @param idRecept identificador do receptor.
	 * @param descritor descritor dos itens.
	 * @param data data da doacao.
	 */
	public Doacao(String nomeDoador, String idDoador, String nomeRecept, String idRecept, String descritor, String data) {
		this.nomeIdDoador = nomeDoador + "/" + idDoador;
		this.nomeIdReceptor = nomeRecept + "/" + idRecept;
		this.descritor = descritor;
		this.data = LocalDate.parse(data, formatoData);
	}

	/**
	 * Adiciona quantidade a doacao.
	 * 
	 * @param quantidadeDoada quantidade doada de itens. 
	 */
	public void adicionaQuantidade(int quantidadeDoada) {
		this.quantidade = quantidadeDoada;
		
	}
	
	/**
	 * Retorna o descritor.
	 * @return retorna o descritor.
	 */
	public String getDescritor() {
		return this.descritor;
		
	}
	
	/**
	 * Retorna a data da doacao.
	 * @return retorna a data da doacao.
	 */
	public LocalDate getData() {
		return this.data;
	}
	
	/**
	 * Representacao textual de uma doacao.
	 * Sera representada por "doador: nomeDoador/identificadorDoador, item: descritor, quantidade, receptor: nomeReceptor/idReceptor".
	 * 
	 * @return retorna a representacao textual da doacao.
	 */
	@Override
	public String toString() {
		return formatoData.format(data) + " - doador: " + nomeIdDoador + ", item: " + descritor + ", quantidade: " + quantidade + ", receptor: " + nomeIdReceptor;
		
	}

}
