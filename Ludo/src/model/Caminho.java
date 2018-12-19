package model;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.text.BadLocationException;

public class Caminho{
	public Peao o1, o2;
	public Boolean pode, barreira;
	public Peao aux = null;
	public Color ant;
	public int numPeao;
	
	public Caminho() {
		o1 = null;
		o2 = null;
		pode = false;
		barreira = false;
	}
	
	public void AdicionaPeao(Peao p) throws FileNotFoundException, BadLocationException {		
		if(o1 == null) {
			o1 = p;
		}
		else if(o2 == null) {
			/*
			if(p.p1.a == o1.p1.a) //se os dois peoes forem da mesma cor
				o2 = p;
			
			if(pode == true) { //se for true trata-se de alguma casa em q podemos ter mais de um peao (abrigo)
				o2 = p;
			} 
			else if(pode!=true) {
			//	if(barreira != true) {
					aux = o1;
					o1 = p;
				//}
			}*/	
		}
		else {
			System.out.print("Quantidade maxima de jogadores na casa.");
		}
	}
	
	public void RemovePeao(Peao p) throws FileNotFoundException, BadLocationException {
		
	}
}
