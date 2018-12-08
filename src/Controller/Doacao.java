package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import entidade.Item;

public class Doacao {

	private String nomeIdDoador;
	private String nomeIdReceptor;
	private String descritor;
	private LocalDate data;
	DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private int quantidade;

	public Doacao(String nomeDoador, String idDoador, String nomeRecept, String idRecept, String descritor, String data) {
		this.nomeIdDoador = nomeDoador + "/" + idDoador;
		this.nomeIdReceptor = nomeRecept + "/" + idRecept;
		this.descritor = descritor;
		this.data = LocalDate.parse(data, formatoData);
	}

	public void adicionaQuantidade(int quantidadeDoada) {
		this.quantidade = quantidadeDoada;
		
	}
	
	public String getDescritor() {
		return this.descritor;
		
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	@Override
	public String toString() {
		return formatoData.format(data) + " - doador: " + nomeIdDoador + ", item: " + descritor + ", quantidade: " + quantidade + ", receptor: " + nomeIdReceptor;
		
	}

}
