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

			//se os dois peoes forem da mesma cor
			if(p.p1.a == o1.p1.a) {
				o2 = p;
			}

			//se for true trata-se de alguma casa em q podemos ter mais de um peao (abrigo)
			if(pode == true) {
				o2 = p;
			}

		}
		else {
			TextAreaLog.getTextAreaLog().printLog("Quantidade maxima de peões na casa");
		}
	}

	public void RemovePeao(Peao p) throws FileNotFoundException, BadLocationException {
		if(o1.getP1().getX() == p.getP1().getX() && o1.getP1().getY() == p.getP1().getY()) {
			o1 = o2;
			o2 = null;
		}else if (o2.getP1().getX() == p.getP1().getX() && o2.getP1().getY() == p.getP1().getY()) {
			o2 = null;
		}else {
			TextAreaLog.getTextAreaLog().printLog("Peão não encontrado");
		}
	}
}
