package controller;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.text.BadLocationException;

import model.Jogador;
import model.Peao;
import model.TextAreaLog;
import model.Caminho;

public class JogadoresController {
    private static volatile JogadoresController instance = null;

	public Vector<Jogador> Jogadores;
	int jogador_turno = 0; 
	boolean m;
	int cont1, cont2, cont3, cont4;
	
    private JogadoresController() {
		Jogadores = new Vector<>();
		Jogadores.insertElementAt(new Jogador("vermelho"), 0);
		Jogadores.insertElementAt(new Jogador("verde"), 1);
		Jogadores.insertElementAt(new Jogador("amarelo"), 2);
		Jogadores.insertElementAt(new Jogador("azul"), 3);
    	
		m = true; //variavel que controla a mudanca de turno, inicialmente igual a true
		cont1 = cont2 = cont3 = cont4 = 0; //variavel q controla se as 4 pecas do jogador ja rodaram o tabuleiro	
    }
    
    public Jogador getJogador(int index) {
    	return Jogadores.elementAt(index);
    }
    
    public boolean getM() {
    	return this.m;
    }
    
    public void setM(boolean i) {
    	this.m = i;
    }
        
    public int getCont(int i) {
    	switch(i) {
			case 1:
				return cont1;
			case 2:
				return cont2;
			case 3:
				return cont3;
			case 4:
				return cont4;
    	}
    	return -1;
    }
    
    public void setCont(int i, int val) {
    	switch(i) {
			case 1:
				cont1 = val;
				break;
			case 2:
				cont2 = val;
				break;
			case 3:
				cont3 = val;
				break;
			case 4:
				cont4 = val;
				break;
    	}
    }
    
    public int getJogadorTurno() {
    	return jogador_turno;
    }
    
	public void MudaTurno() {
		jogador_turno += 1;
		
		if(jogador_turno == 4) {
			jogador_turno = 0;
		}
	}
    	
    public static JogadoresController getJogadoresController() {
        if (instance == null) {
        	instance = new JogadoresController();
        }
        return instance;
    }
}
