package controller;

import javax.swing.text.BadLocationException;

import Interface.Inicializador;


public class main {

	public main() throws BadLocationException{
		
		Inicializador.getInicializador().setVisible(true);

	}

	public static void main(String[] args) throws BadLocationException {
		main f = new main();
	}
}
