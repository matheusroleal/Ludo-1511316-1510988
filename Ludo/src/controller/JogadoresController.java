package controller;

import java.util.Vector;

import model.Jogador;

public class JogadoresController {
    private static volatile JogadoresController instance = null;

	public Vector<Jogador> Jogadores;
	int jogador_turno = 0;
	boolean cinco1, cinco2, cinco3, cinco4;
	boolean c1, c2, c3, c4;
	boolean y1, y2, y3, y4;
	int fim1, fim2, fim3, fim4;
	
    private JogadoresController() {
		Jogadores = new Vector<>();
		Jogadores.insertElementAt(new Jogador("vermelho"), 0);
		Jogadores.insertElementAt(new Jogador("verde"), 1);
		Jogadores.insertElementAt(new Jogador("amarelo"), 2);
		Jogadores.insertElementAt(new Jogador("azul"), 3);
    	
		cinco1 = cinco2 = cinco3 = cinco4 = c1 = c2 = c3 = c4 = false;
		y1 = y2 = y3 = y4 = true;
		fim1 = fim2 = fim3 = fim4 = -1;
    }
    
    public Jogador getJogador(int index) {
    	return Jogadores.elementAt(index);
    }
    
    public boolean getCinco(int i) {
    	switch(i) {
			case 1:
				return cinco1;
			case 2:
				return cinco2;
			case 3:
				return cinco3;
			case 4:
				return cinco4;
    	}
    	return false;
    }
    
    public void setCinco(int i, boolean val) {
    	switch(i) {
			case 1:
				cinco1 = val;
				break;
			case 2:
				cinco2 = val;
				break;
			case 3:
				cinco3 = val;
				break;
			case 4:
				cinco4 = val;
				break;
    	}
    }
    
    public boolean getC(int i) {
    	switch(i) {
			case 1:
				return c1;
			case 2:
				return c2;
			case 3:
				return c3;
			case 4:
				return c4;
    	}
    	return false;
    }
    
    public void setC(int i, boolean val) {
    	switch(i) {
			case 1:
				c1 = val;
				break;
			case 2:
				c2 = val;
				break;
			case 3:
				c3 = val;
				break;
			case 4:
				c4 = val;
				break;
    	}
    }
    
    public boolean getY(int i) {
    	switch(i) {
			case 1:
				return y1;
			case 2:
				return y2;
			case 3:
				return y3;
			case 4:
				return y4;
    	}
    	return false;
    }
    
    public void setY(int i, boolean val) {
    	switch(i) {
			case 1:
				y1 = val;
				break;
			case 2:
				y2 = val;
				break;
			case 3:
				y3 = val;
				break;
			case 4:
				y4 = val;
				break;
    	}
    }
    
    public int getFim(int i) {
    	switch(i) {
			case 1:
				return fim1;
			case 2:
				return fim2;
			case 3:
				return fim3;
			case 4:
				return fim4;
    	}
    	return -1;
    }
    
    public void setFim(int i, int val) {
    	switch(i) {
			case 1:
				fim1 = val;
				break;
			case 2:
				fim2 = val;
				break;
			case 3:
				fim3 = val;
				break;
			case 4:
				fim4 = val;
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
