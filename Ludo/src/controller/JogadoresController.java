package controller;

import java.util.Vector;

import model.Jogador;
import model.Caminho;

public class JogadoresController {
    private static volatile JogadoresController instance = null;

	public Vector<Jogador> Jogadores;
	int jogador_turno = 0;
	Caminho[][] jogadores_na_casa = new Caminho[15][15]; 
	boolean cinco1, cinco2, cinco3, cinco4;
	boolean c1, c2, c3, c4;
	boolean y1, y2, y3, y4;
	int fim1, fim2, fim3, fim4;
	int md1, md2, md3, md4;
	boolean m;
	int cont1, cont2, cont3, cont4;
	
    private JogadoresController() {
		Jogadores = new Vector<>();
		Jogadores.insertElementAt(new Jogador("vermelho"), 0);
		Jogadores.insertElementAt(new Jogador("verde"), 1);
		Jogadores.insertElementAt(new Jogador("amarelo"), 2);
		Jogadores.insertElementAt(new Jogador("azul"), 3);
    	
		cinco1 = cinco2 = cinco3 = cinco4 = c1 = c2 = c3 = c4 = false; //variaveis que controlam se o jogador pode sair da casa inicial (cincoX), e se pode andar pelo tabuleiro (cX); inicialmente igual a false ja que ngm pode andar sem seguir as regras do jogo
		y1 = y2 = y3 = y4 = true;
		fim1 = fim2 = fim3 = fim4 = -1;
		m = true; //variavel que controla a mudanca de turno, inicialmente igual a true
		md1 = md2 = md3 = md4 = 0; //variavel para ver se jogador excedeu vantagem ao tirar 6 no dado
		cont1 = cont2 = cont3 = cont4 = 0; //variavel q controla se as 4 pecas do jogador ja rodaram o tabuleiro
		
		//peoes da matriz jogadores_na_casa inicializados com null
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				jogadores_na_casa[i][j] = new Caminho();
			}
		}
		
		//setando casas em q se pode ter mais de um peao como true
		jogadores_na_casa[1][6].pode = true; //casa preta
		jogadores_na_casa[6][13].pode = true; //casa preta
		jogadores_na_casa[13][8].pode = true; //casa preta
		jogadores_na_casa[8][1].pode = true; //casa preta
		jogadores_na_casa[6][1].pode = true; //casa de saida vermelha
		jogadores_na_casa[1][8].pode = true; //casa de saida verde
		jogadores_na_casa[8][13].pode = true; //casa de saida amarela
		jogadores_na_casa[13][6].pode = true; //casa de saida azul		
		
    }
    
    public Jogador getJogador(int index) {
    	return Jogadores.elementAt(index);
    }
    
    public Caminho getCaminho(int i, int j) {
    	return jogadores_na_casa[i][j];
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
    	return -2;
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
    
    public int getMd(int i) {
    	switch(i) {
			case 1:
				return md1;
			case 2:
				return md2;
			case 3:
				return md3;
			case 4:
				return md4;
    	}
    	return -1;
    }
    
    public void setMd(int i, int val) {
    	switch(i) {
			case 1:
				if(val != 0)
					md1++;
				else
					md1 = val;
				break;
			case 2:
				if(val != 0)
					md2++;
				else
					md2 = val;
				break;
			case 3:
				if(val != 0)
					md3++;
				else
					md3 = val;
				break;
			case 4:
				if(val != 0)
					md4++;
				else
					md4 = val;
				break;
    	}
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
    
    public void setCont(int i) {
    	switch(i) {
			case 1:
				cont1++;
				break;
			case 2:
				cont2++;
				break;
			case 3:
				cont3++;
				break;
			case 4:
				cont4++;
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
