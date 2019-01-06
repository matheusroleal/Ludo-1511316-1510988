package controller;

import java.io.*;

import javax.swing.text.BadLocationException;

import Interface.Jogo;
import model.Jogador;
import model.TextAreaLog;


public class Controlador {

	private static Controlador controlador = null;
	private static Observado observado = null;
	private static Observador observador = null;
	Jogo jogo;

	private Controlador() {
		observado = Regras.getRegras();
	}

	public static Controlador getControlador() {
		if(controlador == null)
			controlador = new Controlador();
		return controlador;
	}

	public void getPosicao(int x, int y) throws BadLocationException, FileNotFoundException, InterruptedException {
		
		if(JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).getIndex(Jogo.getJogo().getO1(x, y)) != -1){
			JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).setNumPeao((Jogo.getJogo().jogadores_na_casa[x][y].jogadores.firstElement()).getIndex(Jogo.getJogo().getO1(x, y)));
			TextAreaLog.getTextAreaLog().printLog("Voce selecionou o peao : " + ((Jogo.getJogo().jogadores_na_casa[x][y].jogadores.firstElement()).getIndex(Jogo.getJogo().getO1(x, y)) + 1));
			Regras.getRegras().aplicaClick();
		}
		else if(JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).getIndex(Jogo.getJogo().getO2(x, y)) != -1){
			JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).setNumPeao((Jogo.getJogo().jogadores_na_casa[x][y].jogadores.lastElement()).getIndex(Jogo.getJogo().getO2(x, y)));
			TextAreaLog.getTextAreaLog().printLog("Voce selecionou o peao : " + ((Jogo.getJogo().jogadores_na_casa[x][y].jogadores.lastElement()).getIndex(Jogo.getJogo().getO2(x, y)) + 1));
			Regras.getRegras().aplicaClick();
		}
		else{
			TextAreaLog.getTextAreaLog().printLog("Esta peca nao pertence a voce!");
		}

	}

	public void registra(Observador o) {
		observador = o;
		observado.add(observador);
	}

	public static Observado getObservado() {
		return observado;
	}

}
