package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import Controller.ControladorUsuario;
import entidade.Usuario;

/**
 * Classe responsavel por salvar os dados da execucao atual e carregar os dados
 * de execucoes passadas.
 * 
 * Projeto de Laboratorio de Progamacao 2 - 2018.2
 * 
 * @author Matheus
 *
 */
public class Persistencia {
	/**
	 * Salva os dados da atual execucao na memoria da maquina para que possam ser
	 * usadas em execucoes futuras.
	 * 
	 * @param map
	 * 			o mapa de usuarios que foram cadastrado no sistema.
	 */
	public void salvar(Map<String, Usuario> map) {
		try {
			FileOutputStream fos = new FileOutputStream("dados.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(map);
			oos.close();

		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Carrega os dados de execucoes passadas para a atual execucao do programa.
	 * Cada controlador executa um setData a partir da leituras dos objetos
	 * armazenadas nas execucoes passadas.
	 * 
	 * @param controlUsuario 
	 * 						controlador de usuarios.
	 */
	@SuppressWarnings("unchecked")
	public void carregar(ControladorUsuario controlUsuario) {
		ObjectInputStream ois = null;
		try {
			if (!new File("dados.txt").exists()) {
				FileOutputStream fos = new FileOutputStream("dados.txt");
				fos.close();
			}

			FileInputStream fis = new FileInputStream("dados.txt");

			if (fis.available() > 0) {
				ois = new ObjectInputStream(fis);
				controlUsuario.setData((LinkedHashMap<String, Usuario>) ois.readObject());
				ois.close();
			}

		} catch (IOException | ClassNotFoundException ioecnfe) {
			ioecnfe.printStackTrace();
		}

	}

}