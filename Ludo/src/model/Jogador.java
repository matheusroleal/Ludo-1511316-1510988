package model;

import java.awt.Color;
import java.util.Vector;

import view.Peca;

public class Jogador {
	public Vector<Peao> peoes_do_jogador;
	int num_peao;

	public Jogador(String cor_jogador) {
		peoes_do_jogador = new Vector<>();
		num_peao = 0;

		CriaPeoes(cor_jogador);
	}

	private void CriaPeoes(String cor_jogador) {
		for(int index = 0; index < 4; index++) {
			peoes_do_jogador.insertElementAt(new Peao(cor_jogador), index);
		}
	}
	
	//pecas p, p1
	public Peca getPeca(int index) {
		return peoes_do_jogador.elementAt(index).getP1();
	}

	public Peca getBasePeca(int index) {
		return peoes_do_jogador.elementAt(index).getP();
	}

	public int getX() {
		return peoes_do_jogador.elementAt(num_peao).p1.x;
	}

	public int getY() {
		return peoes_do_jogador.elementAt(num_peao).p1.y;
	}

	public int getXFinal() {
		return peoes_do_jogador.elementAt(num_peao).x_final;
	}

	public int getYFinal() {
		return peoes_do_jogador.elementAt(num_peao).y_final;
	}
	
	public void SetPX(int x) {
		peoes_do_jogador.elementAt(num_peao).p.x = x;
	}

	public void SetPY(int y) {
		peoes_do_jogador.elementAt(num_peao).p.y = y;
	}

	public void SetPColor(Color c) {
		peoes_do_jogador.elementAt(num_peao).p.a = c;
	}

	public void SetP1X(int x) {
		peoes_do_jogador.elementAt(num_peao).p1.x = x;
	}

	public void SetP1Y(int y) {
		peoes_do_jogador.elementAt(num_peao).p1.y = y;
	}

	public void SetP1Color(Color c) {
		peoes_do_jogador.elementAt(num_peao).p1.a = c;
	}
	
	//peoes
	public Peao getPeao(int index) {
		return peoes_do_jogador.elementAt(index);
	}
	
	public int getNumPeao() {
		return num_peao;
	}
	
	public void setNumPeao(int i) {
		num_peao = i;
	}

	public void mudaPeao() {
		if(num_peao < 4)
			num_peao++;
	}

	//lista
	public Vetor getPosCorr(int index) {
		return (Vetor)peoes_do_jogador.elementAt(index).getLst().posCorr();
	}

	public void getPosIni(int index) {
		peoes_do_jogador.elementAt(index).getLst().posIni();
	}
	
	public void getProx(int index) {
		peoes_do_jogador.elementAt(index).getLst().prox();
	}

}

