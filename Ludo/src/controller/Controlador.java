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
	
	public void getPosicao(int x, int y) throws BadLocationException, FileNotFoundException {
			
		if(JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).getIndex(jogo.getJogo().getO1(x, y)) != -1)
			JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno()).setNumPeao((jogo.getJogo().jogadores_na_casa[x][y].jogadores.firstElement()).getIndex(jogo.getJogo().getO1(x, y)));
		else
			TextAreaLog.getTextAreaLog().printLog("Este peao nao pertence a voce!");
		
		TextAreaLog.getTextAreaLog().printLog("aqui: " + (jogo.getJogo().jogadores_na_casa[x][y].jogadores.firstElement()).getIndex(jogo.getJogo().getO1(x, y)));
	}

	public void registra(Observador o) {
		observador = o;
		observado.add(observador);
	}
	
	public static Observado getObservado() {
		return observado;
	}
		
}