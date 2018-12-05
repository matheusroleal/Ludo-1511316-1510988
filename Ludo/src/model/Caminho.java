package model;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.text.BadLocationException;

public class Caminho{
	public Peao o1, o2;
	public Boolean pode;
	public Peao aux = null;
	public Color ant;
	
	public Caminho() {
		o1 = null;
		o2 = null;
		pode = false;
	}
	
	public void AdicionaPeao(Peao p) throws FileNotFoundException, BadLocationException {
		if(o1 == null) {
			o1 = p;
		}
		else if(o2 == null) {
			if(pode == true) { //se for true trata-se de alguma casa em q podemos ter mais de um peao
				TextAreaLog.getTextAreaLog().printLog("funcionei!");
				
				o2 = p;
				//ant = o1.p1.a; //salvando a cor do peao q estava na casa primeiramente
			} 
			else {
				aux = o1;
				o1 = p;
			}	
		}
		else {
			System.out.print("Quantidade maxima de jogadores na casa.");
		}
	}
	
	public void RemovePeao(Peao p) {
		if(o2 == null) {
			o1 = null;
		}
		else {
			if(o1 == p) {
				o1 = null;
				o1 = o2;
				o2 = null;
			}
			else {
				o2 = null;
			}
		}
		
			
		//if(jogadores_na_casa.lastElement() != null) {//VerificaElemento() == true) {
				//	System.out.print("Quantidade maxima de jogadores na casa, nao pode passar");
				//}else {
				//	jogadores_na_casa.add(jogadores_na_casa.indexOf(p), null);
				//	System.out.println("remove - " + jogadores_na_casa.elementAt(0));
				//}
	}


	/*
	public void RemovePeao(Peao p) {
		System.out.println(jogadores_na_casa.indexOf(p));
		
		jogadores_na_casa.add(jogadores_na_casa.indexOf(p), null);
		System.out.println("remove - " + jogadores_na_casa.elementAt(jogadores_na_casa.indexOf(p)));
		
		
		
		//if(jogadores_na_casa.lastElement() != null) {//VerificaElemento() == true) {
		//	System.out.print("Quantidade maxima de jogadores na casa, nao pode passar");
		//}else {
		//	jogadores_na_casa.add(jogadores_na_casa.indexOf(p), null);
		//	System.out.println("remove - " + jogadores_na_casa.elementAt(0));
		//}
	}
	
	//public boolean VerificaElemento() {
		//if(jogadores_na_casa.lastElement() != null) {
			//return true;
		//}
		//return false;
	//}*/
}
