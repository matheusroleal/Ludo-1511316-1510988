package model;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.text.BadLocationException;

import Interface.Jogo;

public class Caminho{
	public Peao o1, o2;
	public Peao auxp = null;
	public Jogador auxj;
	public Boolean pode;
	public Vector<Jogador> jogadores;

	public Caminho() {
		o1 = null;
		o2 = null;
		pode = false;
		jogadores = new Vector<>();
	}

	public void AdicionaPeao(Peao p, Jogador j) throws FileNotFoundException, BadLocationException {
		if(o1 == null) {
			o1 = p;
		}
		else if(o2 == null) {
			o2 = p;

			// adiciona desenho do peao no estilo barreira / abrigo
			if(o2.getP1().a == o1.getP1().a) {
				o1.getP1().setBarreira(true);
				o2.getP1().setBarreira(true);
			}else {
				o1.getP1().setPode(true);
				o1.getP1().SetCorB(o2.getP1().a);
				o2.getP1().setPode(true);
				o2.getP1().SetCorB(o1.getP1().a);
			}

//			if(pode == true) {
//				o2 = p;
//			}
//			else { //como nao eh abrigo, o peao pode ser capturado
//				if(o1.getP1().ExibeP() != p.getP1().ExibeP()) { //captura de peao somente se for de cor diferente
//					auxp = o1;
//					auxj = jogadores.firstElement();
//					o1 = p;
//				}
//			}
		}

		jogadores.addElement(j);
	}

	public void RemovePeao(Peao p, Jogador j) throws FileNotFoundException, BadLocationException {
		// remove desenho do peao no estilo barreira / abrigo
		if(o1 != null && o2 != null) {
			if(o2.getP1().a == o1.getP1().a) {
				o1.getP1().setBarreira(false);
				o2.getP1().setBarreira(false);
			}else {
				o1.getP1().setPode(false);
				o2.getP1().setPode(false);
			}
		}

		// Faz a remocao do peao
		if(o1 == p) {
			o1 = o2;
			o2 = null;
		}
		else if(o2 == p){
			o2 = null;
		}

		if(!jogadores.isEmpty()) {
			jogadores.remove(jogadores.indexOf(j));
		}
	}
}
