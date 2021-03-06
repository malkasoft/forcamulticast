package model;

import java.util.ArrayList;

/**
 * Classe que contém as palavras pré-estabelecidas para o jogo da Forca 
 * 
 *
 */
public class Palavra {


	public ArrayList<String> palavras = new ArrayList<String>();
	
	public Palavra() {
		preenchePalavras();
	}

	private void preenchePalavras() {
		palavras.add("banana");
		palavras.add("polenta");
		palavras.add("ovelha");
		palavras.add("coca");
		palavras.add("ganso");
	}

	public ArrayList<String> getPalavras() {
		return palavras;
	}

	public void setPalavras(ArrayList<String> palavras) {
		this.palavras = palavras;
	}
	
}
