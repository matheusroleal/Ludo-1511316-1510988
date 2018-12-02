package controller;

import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

import Interface.Inicializador;
import model.Jogador;
import model.TextAreaLog;
import model.Vetor;

public class Regras {
    private static volatile Regras instance;
	Jogador j1 , j2, j3, j4;
	Vetor v1, v2, v3, v4;
	int novo_x1, novo_y1, novo_x2, novo_y2, novo_x3, novo_y3, novo_x4, novo_y4;
	int movimento;

    private Regras() {
    	
    	
		movimento = 0;
		
		j1 = JogadoresController.getJogadoresController().getJogador(0);
		j2 = JogadoresController.getJogadoresController().getJogador(1);
		j3 = JogadoresController.getJogadoresController().getJogador(2);
		j4 = JogadoresController.getJogadoresController().getJogador(3);
		
	}
    
    public void AplicaRegras() throws FileNotFoundException, BadLocationException {
    	if(JogadoresController.getJogadoresController().getJogadorTurno() == 0) {					
			if(JogadoresController.getJogadoresController().getCinco(1) == false) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) {
					j1.SetP1Color(Color.RED);
					j1.SetP1X(6);
					j1.SetP1Y(1);

					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(1, true);
					
					JogadoresController.getJogadoresController().setC(1, true);

				}
			}
		
			if(JogadoresController.getJogadoresController().getC(1) == true) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				if(JogadoresController.getJogadoresController().getFim(1) != -1) {
					JogadoresController.getJogadoresController().setFim(1, j1.getYFinal() - ((Vetor) j1.peoes_do_jogador.elementAt(j1.getNumPeao()).lst.posCorr()).RetornaY());
					TextAreaLog.getTextAreaLog().printLog("fim1 - (2): " + JogadoresController.getJogadoresController().getFim(1));
					
					if((JogadoresController.getJogadoresController().getFim(1) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(1)) 
						JogadoresController.getJogadoresController().setY(1, true);
					else
						JogadoresController.getJogadoresController().setY(1, false);
				}
				
				if(JogadoresController.getJogadoresController().getY(1) == true) {
					TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero - (2): " + movimento);
					
					for (int i = 1; i < movimento + 1 ; i++) {
						j1.peoes_do_jogador.elementAt(j1.getNumPeao()).lst.prox();
					}
					
					v1 =  (Vetor) j1.peoes_do_jogador.elementAt(j1.getNumPeao()).lst.posCorr();	
					
					novo_x1 = v1.RetornaX();
					novo_y1 = v1.RetornaY();
					
					
					j1.SetP1X(novo_x1);
					j1.SetP1Y(novo_y1);
				
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x1==7 && novo_y1==1+i){			
														
						JogadoresController.getJogadoresController().setFim(1, j1.getYFinal() - ((Vetor) j1.peoes_do_jogador.elementAt(j1.getNumPeao()).lst.posCorr()).RetornaY());
						TextAreaLog.getTextAreaLog().printLog("fim1: " + JogadoresController.getJogadoresController().getFim(1));
						
						if((JogadoresController.getJogadoresController().getFim(1) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(1)) 
							JogadoresController.getJogadoresController().setY(1, true);
						else 
							JogadoresController.getJogadoresController().setY(1, false);
					}	
				}
				
			}

			if((j1.getX() == 7 && j1.getY() ==6) && j1.getNumPeao() != 3) {
			
				j1.mudaPeao();
				
				JogadoresController.getJogadoresController().setCinco(1, false);
				JogadoresController.getJogadoresController().setC(1, false);
				JogadoresController.getJogadoresController().setFim(1, -1);
				JogadoresController.getJogadoresController().setY(1, true);
				
				j1.SetP1Color(Color.RED);
				j1.SetPColor(Color.RED);
				
				if(j1.getNumPeao() == 1) {
					j1.SetPX(4);
					j1.SetPY(1);
				}
				else if(j1.getNumPeao() == 2) {
					j1.SetPX(1);
					j1.SetPY(4);
				}
				else{
					j1.SetPX(1);
					j1.SetPY(1);
				}	
				
				
			}
			
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 1) {
			
			if(JogadoresController.getJogadoresController().getCinco(2) == false) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) {
					j2.SetP1Color(Color.GREEN);
					j2.SetP1X(1);
					j2.SetP1Y(8);
					
					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(2, true);
					JogadoresController.getJogadoresController().setC(2, true);

				}
			}
		
			if(JogadoresController.getJogadoresController().getC(2) == true) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				if(JogadoresController.getJogadoresController().getFim(2) != -1) {
					JogadoresController.getJogadoresController().setFim(2, j2.getXFinal() - ((Vetor) j2.peoes_do_jogador.elementAt(j2.getNumPeao()).lst.posCorr()).RetornaX());
					TextAreaLog.getTextAreaLog().printLog("fim2 - (2): " + JogadoresController.getJogadoresController().getFim(2));
					
					if((JogadoresController.getJogadoresController().getFim(2) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(2)) 
						JogadoresController.getJogadoresController().setY(2, true);
					else
						JogadoresController.getJogadoresController().setY(2, false);
				}
				
				if(JogadoresController.getJogadoresController().getY(2) == true) {
					TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero - (2): " + movimento);
					
					for (int i = 1; i < movimento + 1 ; i++) 
						j2.peoes_do_jogador.elementAt(j2.getNumPeao()).lst.prox();
					
					v2 =  (Vetor) j2.peoes_do_jogador.elementAt(j2.getNumPeao()).lst.posCorr();
					
					novo_x2 = v2.RetornaX();
					novo_y2 = v2.RetornaY();
					
					j2.SetP1X(novo_x2);
					j2.SetP1Y(novo_y2);
											
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_y2==7 && novo_x2==1+i){			
														
						JogadoresController.getJogadoresController().setFim(2, j2.getXFinal() - ((Vetor) j2.peoes_do_jogador.elementAt(j2.getNumPeao()).lst.posCorr()).RetornaX());
						TextAreaLog.getTextAreaLog().printLog("fim2: " + JogadoresController.getJogadoresController().getFim(2));
						
						if((JogadoresController.getJogadoresController().getFim(2) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(2)) 
							JogadoresController.getJogadoresController().setY(2, true);
						else 
							JogadoresController.getJogadoresController().setY(2, false);
					}	
				}
			}
			
			if((j2.getX() == 6 && j2.getY() ==7) && j2.getNumPeao() != 3) {
				
				j2.mudaPeao();
			
				JogadoresController.getJogadoresController().setCinco(2, false);
				JogadoresController.getJogadoresController().setC(2, false);
				JogadoresController.getJogadoresController().setFim(2, -1);
				JogadoresController.getJogadoresController().setY(2, true);
				
				j2.SetP1Color(Color.GREEN);
				j2.SetPColor(Color.GREEN);
				
				if(j2.getNumPeao() == 1) {
					j2.SetPX(1);
					j2.SetPY(13);
				}
				else if(j2.getNumPeao() == 2) {
					j2.SetPX(4);
					j2.SetPY(10);
				}
				else if(j2.getNumPeao() == 3) {
					j2.SetPX(4);
					j2.SetPY(13);
				}					
			}	
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 2) {
			if(JogadoresController.getJogadoresController().getCinco(3) == false) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) {
					j3.SetP1Color(Color.YELLOW);
					j3.SetP1X(8);
					j3.SetP1Y(13);

					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(3, true);
					JogadoresController.getJogadoresController().setC(3, true);
				}
			}
			
			if(JogadoresController.getJogadoresController().getC(3) == true) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				if(JogadoresController.getJogadoresController().getFim(3) != -1) {
					JogadoresController.getJogadoresController().setFim(3,((Vetor) j3.peoes_do_jogador.elementAt(j3.getNumPeao()).lst.posCorr()).RetornaY() - j3.getYFinal());
					TextAreaLog.getTextAreaLog().printLog("fim3 - (2): " + JogadoresController.getJogadoresController().getFim(3));
					
					if((JogadoresController.getJogadoresController().getFim(3) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(3)) 
						JogadoresController.getJogadoresController().setY(3,true);
					else
						JogadoresController.getJogadoresController().setY(3,false);
				}
			
				if(JogadoresController.getJogadoresController().getY(3) == true) {
					TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero - (2): " + movimento);
					
					for (int i = 1; i < movimento + 1 ; i++)
						j3.peoes_do_jogador.elementAt(j3.getNumPeao()).lst.prox();
					
					v3 =  (Vetor) j3.peoes_do_jogador.elementAt(j3.getNumPeao()).lst.posCorr();
					
					novo_x3 = v3.RetornaX();
					novo_y3 = v3.RetornaY();
					
					j3.SetP1X(novo_x3);
					j3.SetP1Y(novo_y3);

					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x3==7 && novo_y3==8+i){
						
						JogadoresController.getJogadoresController().setFim(3,((Vetor) j3.peoes_do_jogador.elementAt(j3.getNumPeao()).lst.posCorr()).RetornaY() - j3.getYFinal());
						TextAreaLog.getTextAreaLog().printLog("fim3 - (2): " + JogadoresController.getJogadoresController().getFim(3));
						
						if((JogadoresController.getJogadoresController().getFim(3) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(3)) 
							JogadoresController.getJogadoresController().setY(3,true);
						else
							JogadoresController.getJogadoresController().setY(3,false);
					}	
				}
			}
			
			if((j3.getX() == 7 && j3.getY() ==8) && j3.getNumPeao() != 3) {
				
				j3.mudaPeao();
				
				JogadoresController.getJogadoresController().setCinco(3,false);
				JogadoresController.getJogadoresController().setC(3,false);
				JogadoresController.getJogadoresController().setFim(3,-1);
				JogadoresController.getJogadoresController().setY(3,true);
				
				j3.SetPColor(Color.YELLOW);
				j3.SetP1Color(Color.YELLOW);
				
				if(j3.getNumPeao() == 1) {
					j3.SetPX(10);
					j3.SetPY(10);
				}
				else if(j3.getNumPeao() == 2) {
					j3.SetPX(13);
					j3.SetPY(13);
				}
				else if(j3.getNumPeao() == 3) {
					j3.SetPX(13);
					j3.SetPY(10);
				}					
			}
			
		}
		else {
			if(JogadoresController.getJogadoresController().getCinco(4) == false) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) {
					j4.SetP1Color(Color.BLUE);
					j4.SetP1X(13);
					j4.SetP1Y(6);
					
					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(4, true);
					JogadoresController.getJogadoresController().setC(4,true);

				}
			}
			
			if(JogadoresController.getJogadoresController().getC(4) == true) {
				movimento = Inicializador.getInicializador().d.GeraValor();
				
				if(JogadoresController.getJogadoresController().getFim(4) != -1) {
					JogadoresController.getJogadoresController().setFim(4, ((Vetor) j4.peoes_do_jogador.elementAt(j4.getNumPeao()).lst.posCorr()).RetornaX() - j4.getXFinal());
					TextAreaLog.getTextAreaLog().printLog("fim4 - (2): " + JogadoresController.getJogadoresController().getFim(4));
					
					if((JogadoresController.getJogadoresController().getFim(4) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(4)) 
						JogadoresController.getJogadoresController().setY(4,true);
					else
						JogadoresController.getJogadoresController().setY(4,false);
				}
				
				if(	JogadoresController.getJogadoresController().getY(4) == true) {
					TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero - (2): " + movimento);
					
					for (int i = 1; i < movimento + 1 ; i++)  
						j4.peoes_do_jogador.elementAt(j4.getNumPeao()).lst.prox();
					
					v4 =  (Vetor) j4.peoes_do_jogador.elementAt(j4.getNumPeao()).lst.posCorr();
					
					novo_x4 = v4.RetornaX();
					novo_y4 = v4.RetornaY();
					
					j4.SetP1X(novo_x4);
					j4.SetP1Y(novo_y4);
					
					Inicializador.getInicializador().repaint();
				}

				for(int i=0; i<6; i++) {
					if(novo_y4==7 && novo_x4==8+i){

						JogadoresController.getJogadoresController().setFim(4, ((Vetor) j4.peoes_do_jogador.elementAt(j4.getNumPeao()).lst.posCorr()).RetornaX() - j4.getXFinal());
						TextAreaLog.getTextAreaLog().printLog("fim4 - (2): " + JogadoresController.getJogadoresController().getFim(4));
						
						if((JogadoresController.getJogadoresController().getFim(4) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(4)) 
							JogadoresController.getJogadoresController().setY(4,true);
						else
							JogadoresController.getJogadoresController().setY(4,false);
					}	
				}	
			
			}
			
			if((j4.getX() == 8 && j4.getY() ==7) && j4.getNumPeao() != 3) {
				
				j4.mudaPeao();
				
				JogadoresController.getJogadoresController().setCinco(4,false);
				JogadoresController.getJogadoresController().setY(4,true);
				JogadoresController.getJogadoresController().setFim(4,-1);
				JogadoresController.getJogadoresController().setC(4,false);
		
				j4.SetP1Color(Color.BLUE);
				j4.SetPColor(Color.BLUE);
				
				if(j4.getNumPeao() == 1) {
					j4.SetPX(13);
					j4.SetPY(1);
				}
				else if(j4.getNumPeao() == 2) {
					j4.SetPX(10);
					j4.SetPY(4);				
				}
				else if(j4.getNumPeao() == 3) {
					j4.SetPX(10);
					j4.SetPY(1);
				}					
			}
			
		}
		
		JogadoresController.getJogadoresController().MudaTurno();

    }
    	
    public static Regras getRegras(String value) {
		if (instance == null) {
		    instance = new Regras();
		}
		return instance;
    }
}
