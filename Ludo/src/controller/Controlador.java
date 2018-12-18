package controller;

import java.io.*;

import javax.swing.text.BadLocationException;

public class Controlador {
	
	private static Controlador controlador = null;
	private static Observado observado = null;
	private static Observador observador = null;
	
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

	public void registra(Observador o) {
		observador = o;
		observado.add(observador);
	}
	
	public static Observado getObservado() {
		return observado;
	}
		
}