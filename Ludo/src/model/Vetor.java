package model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Vetor {
	private int x,y;
	private Vector<Peao> jogadores_na_casa = new Vector<>();

	public Vetor(int x,int y) {
		this.x=x;
		this.y=y;
		jogadores_na_casa.add(0, null);;
		jogadores_na_casa.add(1, null);
	}
	
	public int RetornaX() {
		return this.x;
	}
	
	public int RetornaY() {
		return this.y;
	}
		
	public void AdicionaPeao(Peao p) throws FileNotFoundException {
		if(jogadores_na_casa.firstElement() == null) {
			jogadores_na_casa.add(0, p);
		}else if(jogadores_na_casa.lastElement() == null){
			jogadores_na_casa.add(1, p);
		}else {
			TextAreaLog.getTextAreaLog().printLog("Quantidade maxima de jogadores na casa");
		}
	}
	
	public void RemovePeao(int index) throws FileNotFoundException {
		if(VerificaElemento()) {
			TextAreaLog.getTextAreaLog().printLog("Quantidade maxima de jogadores na casa, nao pode passar");
		}else {
			jogadores_na_casa.add(index, null);
		}
	}
	
	public Color ExibeCorPeao(int index) {
		return jogadores_na_casa.elementAt(index).p1.ExibeP();
	}
	
	public boolean VerificaElemento() {
		if(jogadores_na_casa.lastElement() != null) {
			return true;
		}
		return false;
	}

}
