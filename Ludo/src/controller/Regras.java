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
	Vetor v;
	int novo_x1, novo_y1, novo_x2, novo_y2, novo_x3, novo_y3, novo_x4, novo_y4;
	int movimento;

    private Regras() {
    	
		movimento = 0;
		
		j1 = JogadoresController.getJogadoresController().getJogador(0);
		j2 = JogadoresController.getJogadoresController().getJogador(1);
		j3 = JogadoresController.getJogadoresController().getJogador(2);
		j4 = JogadoresController.getJogadoresController().getJogador(3);
		
	}
    
    public void AplicaRegras(int mv) throws FileNotFoundException, BadLocationException {
    	movimento = mv;
    	
    	if(JogadoresController.getJogadoresController().getJogadorTurno() == 0) {	
    		
    		//mostrando novamente no tabuleiro peca sem a outra em cima
    		if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.RED) {
					JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
					j2.SetP1Color(Color.GREEN);
				}
	  		}
    		else if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.RED) {
					JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
					j3.SetP1Color(Color.YELLOW);
				}
	  		}
    		else if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.RED) {
					JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				}
	  		}
    		  		
			if(JogadoresController.getJogadoresController().getCinco(1) == false) { //se cincoX for false quer dizer que o jogador ainda n tirou numero 5 no dado para poder sair da casa inicial
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) { //se movimento for 5, jogador pode colocar peca na casa de saida
					
					j1.SetPColor(Color.RED);
					
					if(j1.getNumPeao() == 0) { //se for o primeiro peao 
						j1.SetPX(4); //colorem-se essas casas
						j1.SetPY(1);
					}
					else if(j1.getNumPeao() == 1) {
						j1.SetPX(4);
						j1.SetPY(4);
					}
					else if(j1.getNumPeao() == 2) {
						j1.SetPX(1);
						j1.SetPY(1);
					}
					else {
						j1.SetPX(1);
						j1.SetPY(4);
					}
					
					j1.SetP1Color(Color.RED); //colorindo peao q anda pelo tabuleiro
					
					v = (Vetor) j1.getPosCorr(j1.getNumPeao());
					
					novo_x1 = v.RetornaX(); //x da casa de saida
					novo_y1 = v.RetornaY(); //y da casa de saida
					
					j1.SetP1X(novo_x1);
					j1.SetP1Y(novo_y1);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao())); //adiciona peao a casa de saida; peao o1 preenchido 

					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(1, true); //sai do cincoX = false ja q o jogador tirou 5
				}
			}
		
			if(JogadoresController.getJogadoresController().getC(1) == true) {//quando c1 for true, quer dizer q o peao ja esta no tabuleiro e vai andar por ele
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				//eh necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
				JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).RemovePeao(j1.getPeao(j1.getNumPeao()));
				
				if(movimento == 6) {//se movimento for 6, jogador pode jogar novamente					
					JogadoresController.getJogadoresController().m = false; //m vira false para nao mudar o turno
					JogadoresController.getJogadoresController().setMd(1,1); //aumentando a variavel md
					
					if(JogadoresController.getJogadoresController().getMd(1) == 3) {//se md for igual a 3, quer dizer que o jogador ja excedeu o maximo de vezes q pode jogar ao tirar 6
						JogadoresController.getJogadoresController().m = true; //turno pode ser mudado
						JogadoresController.getJogadoresController().setMd(1, 0); //variavel volta a ser 0
					}
				}
				else { //se o movimento nao for 6, muda-se o turno
					JogadoresController.getJogadoresController().m = true;
					JogadoresController.getJogadoresController().setMd(1, 0);		
				}
				
				if(JogadoresController.getJogadoresController().getFim(1) != -1) {
					JogadoresController.getJogadoresController().setFim(1, j1.getYFinal() - ((Vetor) j1.getPosCorr(j1.getNumPeao())).RetornaY());
					
					if((JogadoresController.getJogadoresController().getFim(1) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(1)) 
						JogadoresController.getJogadoresController().setY(1, true);
					else
						JogadoresController.getJogadoresController().setY(1, false);
				}
				
				if(JogadoresController.getJogadoresController().getY(1) == true) {

					for (int i = 1; i < movimento + 1 ; i++) {
						j1.getProx(j1.getNumPeao());
					}
					
					v =  (Vetor) j1.getPosCorr(j1.getNumPeao());
					
					novo_x1 = v.RetornaX();
					novo_y1 = v.RetornaY();
					
					j1.SetP1X(novo_x1);
					j1.SetP1Y(novo_y1);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao()));
					
					//captura
					if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).aux != null) {
						
						if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); //faz o peao em campo ser transparente
							j2.getPosIni(j2.getNumPeao()); //volta com sua lista
							j2.SetPColor(Color.WHITE);
							
							if(j2.getNumPeao() == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
							}
							else if(j2.getNumPeao() == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
							}
							else if(j2.getNumPeao() == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
							}
							
							//reiniciando as variaveis
							JogadoresController.getJogadoresController().setCinco(2, false);
							JogadoresController.getJogadoresController().setC(2, false);
							JogadoresController.getJogadoresController().setFim(2, -1);
							JogadoresController.getJogadoresController().setY(2, true);
							JogadoresController.getJogadoresController().setMd(2, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPosIni(j3.getNumPeao());
							j3.SetPColor(Color.WHITE);
							
							if(j3.getNumPeao() == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
							}
							else if(j3.getNumPeao() == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
							}
							else if(j3.getNumPeao() == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
							}
							
							//reiniciando as variaveis
							JogadoresController.getJogadoresController().setCinco(3, false);
							JogadoresController.getJogadoresController().setC(3, false);
							JogadoresController.getJogadoresController().setFim(3, -1);
							JogadoresController.getJogadoresController().setY(3, true);
							JogadoresController.getJogadoresController().setMd(3, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPosIni(j4.getNumPeao());
							j4.SetPColor(Color.WHITE);
							
							if(j4.getNumPeao() == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
							}
							else if(j4.getNumPeao() == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
							}
							else if(j4.getNumPeao() == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
							}
							
							//reiniciando as variaveis
							JogadoresController.getJogadoresController().setCinco(4, false);
							JogadoresController.getJogadoresController().setC(4, false);
							JogadoresController.getJogadoresController().setFim(4, -1);
							JogadoresController.getJogadoresController().setY(4, true);
							JogadoresController.getJogadoresController().setMd(4, 0);		
						}
							
						JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).aux = null; //transformando variavel auxiliar em null novamente
					}
					
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x1==7 && novo_y1==1+i){			
														
						JogadoresController.getJogadoresController().setFim(1, j1.getYFinal() - ((Vetor) j1.getPosCorr(j1.getNumPeao())).RetornaY());
						TextAreaLog.getTextAreaLog().printLog("fim1: " + JogadoresController.getJogadoresController().getFim(1));
						
						if((JogadoresController.getJogadoresController().getFim(1) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(1)) 
							JogadoresController.getJogadoresController().setY(1, true);
						else 
							JogadoresController.getJogadoresController().setY(1, false);
					}	
				}
				
			}
			
			//caso de dois peoes na mesma casa
			if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o2 != null) {
				JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().pode = true;
				JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().b = j1.getPeao(j1.getNumPeao()).getP1().a;
			}
			
			if((j1.getX() == 7 && j1.getY() == 6) && JogadoresController.getJogadoresController().getCont(1) != 3) {//se o peao tiver chegado na casa final e nao for o ultimo, mudamos o peao do jogador para o seguinte
			
				j1.mudaPeao();
				JogadoresController.getJogadoresController().setCont(1);
				
				//reiniciando as variavies para novo peao
				JogadoresController.getJogadoresController().setCinco(1, false);
				JogadoresController.getJogadoresController().setC(1, false);
				JogadoresController.getJogadoresController().setFim(1, -1);
				JogadoresController.getJogadoresController().setY(1, true);
				JogadoresController.getJogadoresController().setMd(1, 0);
				JogadoresController.getJogadoresController().setM(true);
			}
			
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 1) {
		
			if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.GREEN) 
					JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.GREEN) {
					JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
					j3.SetP1Color(Color.YELLOW);
				}
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.GREEN){
					JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				} 
	  		}
		 				
			if(JogadoresController.getJogadoresController().getCinco(2) == false) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);

				if(movimento == 5) { 
				
					j2.SetPColor(Color.GREEN);
					
					if(j2.getNumPeao() == 0) { 
						j2.SetPX(1); 
						j2.SetPY(10);
					}
					else if(j2.getNumPeao() == 1) {
						j2.SetPX(1);
						j2.SetPY(13);
					}
					else if(j2.getNumPeao() == 2) {
						j2.SetPX(4);
						j2.SetPY(10);
					}
					else {
						j2.SetPX(4);
						j2.SetPY(13);
					}
					
					j2.SetP1Color(Color.GREEN); 
					
					v = (Vetor) j2.getPosCorr(j2.getNumPeao());
					
					novo_x2 = v.RetornaX(); 
					novo_y2 = v.RetornaY(); 
					
					j2.SetP1X(novo_x2);
					j2.SetP1Y(novo_y2);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao())); 
		
					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(2, true); 
				}
			}	
		
			if(JogadoresController.getJogadoresController().getC(2) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).RemovePeao(j2.getPeao(j2.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					JogadoresController.getJogadoresController().setMd(2,1); 
						
					if(JogadoresController.getJogadoresController().getMd(2) == 3) {
						JogadoresController.getJogadoresController().m = true;
						JogadoresController.getJogadoresController().setMd(2, 0);
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					JogadoresController.getJogadoresController().setMd(2, 0);		
				}
				
				if(JogadoresController.getJogadoresController().getFim(2) != -1) {
					JogadoresController.getJogadoresController().setFim(2, j2.getXFinal() - ((Vetor) j2.getPosCorr(j2.getNumPeao())).RetornaX());
					
					if((JogadoresController.getJogadoresController().getFim(2) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(2)) 
						JogadoresController.getJogadoresController().setY(2, true);
					else
						JogadoresController.getJogadoresController().setY(2, false);
				}
				
				if(JogadoresController.getJogadoresController().getY(2) == true) {

					for (int i = 1; i < movimento + 1 ; i++) 
						j2.getProx(j2.getNumPeao());
					
					v =  (Vetor) j2.getPosCorr(j2.getNumPeao());
					
					novo_x2 = v.RetornaX();
					novo_y2 = v.RetornaY();
					
					j2.SetP1X(novo_x2);
					j2.SetP1Y(novo_y2);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao()));
					
					if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).aux != null) {
						if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.RED) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPosIni(j1.getNumPeao());
							j1.SetPColor(Color.WHITE);
							
							if(j1.getNumPeao() == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
							}
							else if(j1.getNumPeao() == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
							}
							else if(j1.getNumPeao() == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
							}
							
							JogadoresController.getJogadoresController().setCinco(1, false);
							JogadoresController.getJogadoresController().setC(1, false);
							JogadoresController.getJogadoresController().setFim(1, -1);
							JogadoresController.getJogadoresController().setY(1, true);
							JogadoresController.getJogadoresController().setMd(1, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPosIni(j3.getNumPeao());
							j3.SetPColor(Color.WHITE);
							
							if(j3.getNumPeao() == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
							}
							else if(j3.getNumPeao() == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
							}
							else if(j3.getNumPeao() == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
							}
							
							JogadoresController.getJogadoresController().setCinco(3, false);
							JogadoresController.getJogadoresController().setC(3, false);
							JogadoresController.getJogadoresController().setFim(3, -1);
							JogadoresController.getJogadoresController().setY(3, true);
							JogadoresController.getJogadoresController().setMd(3, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPosIni(j4.getNumPeao());
							j4.SetPColor(Color.WHITE);
							
							if(j4.getNumPeao() == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
							}
							else if(j4.getNumPeao() == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
							}
							else if(j4.getNumPeao() == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
							}
							
							JogadoresController.getJogadoresController().setCinco(4, false);
							JogadoresController.getJogadoresController().setC(4, false);
							JogadoresController.getJogadoresController().setFim(4, -1);
							JogadoresController.getJogadoresController().setY(4, true);
							JogadoresController.getJogadoresController().setMd(4, 0);		
						}
						
						JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).aux = null;
						
					}
											
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_y2==7 && novo_x2==1+i){			
														
						JogadoresController.getJogadoresController().setFim(2, j2.getXFinal() - ((Vetor) j2.getPosCorr(j2.getNumPeao())).RetornaX());
						TextAreaLog.getTextAreaLog().printLog("fim2: " + JogadoresController.getJogadoresController().getFim(2));
						
						if((JogadoresController.getJogadoresController().getFim(2) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(2)) 
							JogadoresController.getJogadoresController().setY(2, true);
						else 
							JogadoresController.getJogadoresController().setY(2, false);
					}	
				}
			}
			
			if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o2 != null) {
				JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().pode = true;
				JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().b = j2.getPeao(j2.getNumPeao()).getP1().a;	
				
				if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.RED)
					j2.SetP1Color(new Color(0,0,0,0));
			}
			
			if((j2.getX() == 6 && j2.getY() == 7) && j2.getNumPeao() != 3) {
				
				j2.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(2);
				
				JogadoresController.getJogadoresController().setCinco(2, false);
				JogadoresController.getJogadoresController().setC(2, false);
				JogadoresController.getJogadoresController().setFim(2, -1);
				JogadoresController.getJogadoresController().setY(2, true);
				JogadoresController.getJogadoresController().setMd(2, 0);
				JogadoresController.getJogadoresController().setM(true);
			}
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 2) {
			
			if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.YELLOW) 
					JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.YELLOW) 
					JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.YELLOW){
					JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				} 
	  		}
			
			if(JogadoresController.getJogadoresController().getCinco(3) == false) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) { 
					
					j3.SetPColor(Color.YELLOW);
					
					if(j3.getNumPeao() == 0) { 
						j3.SetPX(10); 
						j3.SetPY(13);
					}
					else if(j3.getNumPeao() == 1) {
						j3.SetPX(10);
						j3.SetPY(10);
					}
					else if(j3.getNumPeao() == 2) {
						j3.SetPX(13);
						j3.SetPY(13);
					}
					else {
						j3.SetPX(13);
						j3.SetPY(10);
					}
					
					j3.SetP1Color(Color.YELLOW); 
					
					v = (Vetor) j3.getPosCorr(j3.getNumPeao());
					
					novo_x3 = v.RetornaX(); 
					novo_y3 = v.RetornaY(); 
					
					j3.SetP1X(novo_x3);
					j3.SetP1Y(novo_y3);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao())); 
		
					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(3, true); 
				}
			}
			
			if(JogadoresController.getJogadoresController().getC(3) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).RemovePeao(j3.getPeao(j3.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					JogadoresController.getJogadoresController().setMd(3,1); 
								
					if(JogadoresController.getJogadoresController().getMd(3) == 3) {
						JogadoresController.getJogadoresController().m = true;
						JogadoresController.getJogadoresController().setMd(3, 0);
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					JogadoresController.getJogadoresController().setMd(3, 0);		
				}
				
				if(JogadoresController.getJogadoresController().getFim(3) != -1) {
					JogadoresController.getJogadoresController().setFim(3,((Vetor) j3.getPosCorr(j3.getNumPeao())).RetornaY() - j3.getYFinal());
					
					if((JogadoresController.getJogadoresController().getFim(3) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(3)) 
						JogadoresController.getJogadoresController().setY(3,true);
					else
						JogadoresController.getJogadoresController().setY(3,false);
				}
			
				if(JogadoresController.getJogadoresController().getY(3) == true) {
					
					for (int i = 1; i < movimento + 1 ; i++)
						j3.getProx(j3.getNumPeao());
					
					v =  (Vetor) j3.getPosCorr(j3.getNumPeao());
					
					novo_x3 = v.RetornaX();
					novo_y3 = v.RetornaY();
					
					j3.SetP1X(novo_x3);
					j3.SetP1Y(novo_y3);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
					
					if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).aux != null) {
						
						if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.RED) {
TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPosIni(j1.getNumPeao());
							j1.SetPColor(Color.WHITE);
							
							if(j1.getNumPeao() == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
							}
							else if(j1.getNumPeao() == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
							}
							else if(j1.getNumPeao() == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
							}
							
							JogadoresController.getJogadoresController().setCinco(1, false);
							JogadoresController.getJogadoresController().setC(1, false);
							JogadoresController.getJogadoresController().setFim(1, -1);
							JogadoresController.getJogadoresController().setY(1, true);
							JogadoresController.getJogadoresController().setMd(1, 0);
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); 
							j2.getPosIni(j2.getNumPeao());
							j2.SetPColor(Color.WHITE);
							
							if(j2.getNumPeao() == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
							}
							else if(j2.getNumPeao() == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
							}
							else if(j2.getNumPeao() == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
							}
							
							JogadoresController.getJogadoresController().setCinco(2, false);
							JogadoresController.getJogadoresController().setC(2, false);
							JogadoresController.getJogadoresController().setFim(2, -1);
							JogadoresController.getJogadoresController().setY(2, true);
							JogadoresController.getJogadoresController().setMd(2, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPosIni(j4.getNumPeao());
							j4.SetPColor(Color.WHITE);
							
							if(j4.getNumPeao() == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
							}
							else if(j4.getNumPeao() == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
							}
							else if(j4.getNumPeao() == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
							}
							
							JogadoresController.getJogadoresController().setCinco(4, false);
							JogadoresController.getJogadoresController().setC(4, false);
							JogadoresController.getJogadoresController().setFim(4, -1);
							JogadoresController.getJogadoresController().setY(4, true);
							JogadoresController.getJogadoresController().setMd(4, 0);		
						}
						
						JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).aux = null;
							
					}
					
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x3==7 && novo_y3==8+i){
						
						JogadoresController.getJogadoresController().setFim(3,((Vetor) j3.getPosCorr(j3.getNumPeao())).RetornaY() - j3.getYFinal());
						TextAreaLog.getTextAreaLog().printLog("fim3: " + JogadoresController.getJogadoresController().getFim(3));
						
						if((JogadoresController.getJogadoresController().getFim(3) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(3)) 
							JogadoresController.getJogadoresController().setY(3,true);
						else
							JogadoresController.getJogadoresController().setY(3,false);
					}	
				}
			}
			
			if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o2 != null) {
				JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().pode = true;
				JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().b = j3.getPeao(j3.getNumPeao()).getP1().a;	
				
				if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.RED || JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.GREEN)
					j3.SetP1Color(new Color(0,0,0,0));
			}
			
			if((j3.getX() == 7 && j3.getY() == 8) && j3.getNumPeao() != 3) {
				
				j3.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(3);
				
				JogadoresController.getJogadoresController().setCinco(3, false);
				JogadoresController.getJogadoresController().setC(3, false);
				JogadoresController.getJogadoresController().setFim(3, -1);
				JogadoresController.getJogadoresController().setY(3, true);
				JogadoresController.getJogadoresController().setMd(3, 0);
				JogadoresController.getJogadoresController().setM(true);				
			}
			
		}
		else {
			
			if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.BLUE) 
					JogadoresController.getJogadoresController().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.BLUE) 
					JogadoresController.getJogadoresController().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
	  		}
			else if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o2 != null){
				if(JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.BLUE)
					JogadoresController.getJogadoresController().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
	  		}
			
			if(JogadoresController.getJogadoresController().getCinco(4) == false) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) { 
					
					j4.SetPColor(Color.BLUE);
					
					if(j4.getNumPeao() == 0) { 
						j4.SetPX(13); 
						j4.SetPY(4);
					}
					else if(j4.getNumPeao() == 1) {
						j4.SetPX(13);
						j4.SetPY(1);
					}
					else if(j4.getNumPeao() == 2) {
						j4.SetPX(10);
						j4.SetPY(4);
					}
					else {
						j4.SetPX(10);
						j4.SetPY(1);
					}
					
					j4.SetP1Color(Color.BLUE); 
					
					v = (Vetor) j4.getPosCorr(j4.getNumPeao());
					
					novo_x4 = v.RetornaX(); 
					novo_y4 = v.RetornaY(); 
					
					j4.SetP1X(novo_x4);
					j4.SetP1Y(novo_y4);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao())); 
		
					Inicializador.getInicializador().repaint();
					
					JogadoresController.getJogadoresController().setCinco(4, true); 

				}
			}
			
			if(JogadoresController.getJogadoresController().getC(4) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).RemovePeao(j4.getPeao(j4.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					JogadoresController.getJogadoresController().setMd(4,1); 
			
					if(JogadoresController.getJogadoresController().getMd(4) == 3) {
						JogadoresController.getJogadoresController().m = true;
						JogadoresController.getJogadoresController().setMd(4, 0);
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					JogadoresController.getJogadoresController().setMd(4, 0);		
				}
				
				if(JogadoresController.getJogadoresController().getFim(4) != -1) {
					JogadoresController.getJogadoresController().setFim(4, ((Vetor) j4.getPosCorr(j4.getNumPeao())).RetornaX() - j4.getXFinal());
					
					if((JogadoresController.getJogadoresController().getFim(4) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(4)) 
						JogadoresController.getJogadoresController().setY(4,true);
					else
						JogadoresController.getJogadoresController().setY(4,false);
				}
				
				if(JogadoresController.getJogadoresController().getY(4) == true) {
					
					for (int i = 1; i < movimento + 1 ; i++)  
						j4.getProx(j4.getNumPeao());
					
					v =  (Vetor) j4.getPosCorr(j4.getNumPeao());
					
					novo_x4 = v.RetornaX();
					novo_y4 = v.RetornaY();
					
					j4.SetP1X(novo_x4);
					j4.SetP1Y(novo_y4);
					
					JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao()));
					
					if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).aux != null) {
						if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.RED) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPosIni(j1.getNumPeao());
							j1.SetPColor(Color.WHITE);
							
							if(j1.getNumPeao() == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
							}
							else if(j1.getNumPeao() == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
							}
							else if(j1.getNumPeao() == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
							}
							
							JogadoresController.getJogadoresController().setCinco(1, false);
							JogadoresController.getJogadoresController().setC(1, false);
							JogadoresController.getJogadoresController().setFim(1, -1);
							JogadoresController.getJogadoresController().setY(1, true);
							JogadoresController.getJogadoresController().setMd(1, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); 
							j2.getPosIni(j2.getNumPeao());
							j2.SetPColor(Color.WHITE);
							
							if(j2.getNumPeao() == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
							}
							else if(j2.getNumPeao() == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
							}
							else if(j2.getNumPeao() == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
							}
							
							JogadoresController.getJogadoresController().setCinco(2, false);
							JogadoresController.getJogadoresController().setC(2, false);
							JogadoresController.getJogadoresController().setFim(2, -1);
							JogadoresController.getJogadoresController().setY(2, true);
							JogadoresController.getJogadoresController().setMd(2, 0);
							
						}
						else if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPosIni(j3.getNumPeao());
							j3.SetPColor(Color.WHITE);
							
							if(j3.getNumPeao() == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
							}
							else if(j3.getNumPeao() == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
							}
							else if(j3.getNumPeao() == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
							}
							
							JogadoresController.getJogadoresController().setCinco(3, false);
							JogadoresController.getJogadoresController().setC(3, false);
							JogadoresController.getJogadoresController().setFim(3, -1);
							JogadoresController.getJogadoresController().setY(3, true);
							JogadoresController.getJogadoresController().setMd(3, 0);
							
						}
						
						JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).aux = null;
						
					}
					
					Inicializador.getInicializador().repaint();
				}

				for(int i=0; i<6; i++) {
					if(novo_y4==7 && novo_x4==8+i){

						JogadoresController.getJogadoresController().setFim(4, ((Vetor) j4.getPosCorr(j4.getNumPeao())).RetornaX() - j4.getXFinal());
						TextAreaLog.getTextAreaLog().printLog("fim4: " + JogadoresController.getJogadoresController().getFim(4));
						
						if((JogadoresController.getJogadoresController().getFim(4) - movimento) == 0 || movimento < JogadoresController.getJogadoresController().getFim(4)) 
							JogadoresController.getJogadoresController().setY(4,true);
						else
							JogadoresController.getJogadoresController().setY(4,false);
					}	
				}		
			}
			
			if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o2 != null) {
				JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().pode = true;
				JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().b = j4.getPeao(j4.getNumPeao()).getP1().a;	
				
				if(JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.RED || JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.GREEN || JogadoresController.getJogadoresController().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.YELLOW)
					j4.SetP1Color(new Color(0,0,0,0));
			}
			
			if((j4.getX() == 8 && j4.getY() == 7) && j4.getNumPeao() != 3) {
				
				j4.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(4);
				
				JogadoresController.getJogadoresController().setCinco(4, false);
				JogadoresController.getJogadoresController().setC(4, false);
				JogadoresController.getJogadoresController().setFim(4, -1);
				JogadoresController.getJogadoresController().setY(4, true);
				JogadoresController.getJogadoresController().setMd(4, 0);
				JogadoresController.getJogadoresController().setM(true);				
			}
			
		}
		if(JogadoresController.getJogadoresController().getM() == true) 
			JogadoresController.getJogadoresController().MudaTurno();

    	//quando cincoX for = true, quer dizer que o jogador tirou 5 podendo colocar um peao na casa de saida
		//para o jogador poder andar pelo tabuleiro, a variavel cX tem q ser true
		//o jogador so pode andar pelo tabuleiro quando for sua vez, por isso deve-se fazer cX = true depois de mudar o turno
		if(JogadoresController.getJogadoresController().getCinco(1) == true)
			JogadoresController.getJogadoresController().setC(1, true);
		
		if(JogadoresController.getJogadoresController().getCinco(2) == true)
			JogadoresController.getJogadoresController().setC(2, true);

		if(JogadoresController.getJogadoresController().getCinco(3) == true)
			JogadoresController.getJogadoresController().setC(3, true);
		
		if(JogadoresController.getJogadoresController().getCinco(4) == true)
			JogadoresController.getJogadoresController().setC(4, true);
    	
    }
    	
    public static Regras getRegras(String value) {
		if (instance == null) {
		    instance = new Regras();
		}
		return instance;
    }
}
