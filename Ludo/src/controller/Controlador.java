package controller;

import java.io.*;

import javax.swing.text.BadLocationException;

import Interface.Jogo;
import model.TextAreaLog;


public class Controlador {
	
	private static Controlador controlador = null;
	private static Observado observado = null;
	private static Observador observador = null;
	Jogo jogo;
	
	private Controlador() {
		try {
			observado = Regras.getRegras();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Controlador getControlador() {
		if(controlador == null)
			controlador = new Controlador();
		return controlador;
	}
	
	public void getPosicao(int x, int y) throws BadLocationException, FileNotFoundException {
		
		Regras.getRegras().numPeao = jogo.getJogo().jogadores_na_casa[x][y].numPeao;
		
		TextAreaLog.getTextAreaLog().printLog("x: " + x);
		TextAreaLog.getTextAreaLog().printLog("y: " + y);	
		TextAreaLog.getTextAreaLog().printLog("aqui: " + jogo.getJogo().jogadores_na_casa[x][y].numPeao);
	}

	public void registra(Observador o) {
		observador = o;
		observado.add(observador);
	}
	
	public static Observado getObservado() {
		return observado;
	}
		
}