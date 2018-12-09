package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

import Interface.Inicializador;
import model.Jogador;
import model.TextAreaLog;
import model.Vetor;

public class Regras implements ObservadorIF {
    private static volatile Regras instance;
	Jogador j1 , j2, j3, j4;
	Vetor v;
	int novo_x1, novo_y1, novo_x2, novo_y2, novo_x3, novo_y3, novo_x4, novo_y4;
	int movimento;
	boolean t = false;

    private Regras() {
    	
		movimento = 0;
		
		j1 = JogadoresController.getJogadoresController().getJogador(0);
		j2 = JogadoresController.getJogadoresController().getJogador(1);
		j3 = JogadoresController.getJogadoresController().getJogador(2);
		j4 = JogadoresController.getJogadoresController().getJogador(3);
		
	}
    
    public void AplicaRegras(int mv) throws FileNotFoundException, BadLocationException, InterruptedException {
    	movimento = mv;
    	
     	if(JogadoresController.getJogadoresController().getJogadorTurno() == 0) {
    		
    		//mostrando novamente no tabuleiro peca sem a outra em cima   		
    		if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2 != null) {
    			Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false;
    			Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2.getP1().pode = false;
    			
    			j1.SetP1Color(Color.RED);
    			
    			if(j2.getPeao(j2.getNumPeao()).getCinco(2) != false)
    				j2.SetP1Color(Color.GREEN);
    			else if(j3.getPeao(j3.getNumPeao()).getCinco(3) != false)
    				j3.SetP1Color(Color.YELLOW);
    			else if(j4.getPeao(j4.getNumPeao()).getCinco(4) != false)
    				j4.SetP1Color(Color.BLUE);
    		}
    		else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2 != null) {
				if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.RED) {
					Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
					j2.SetP1Color(Color.GREEN);
				}
	  		}
    		else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.RED) {
					Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
					j3.SetP1Color(Color.YELLOW);
				}
	  		}
    		else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.RED) {
					Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				}
	  		}
    		  	
    		if(j1.getPeao(j1.getNumPeao()).getCinco(1) == false){ //se cincoX for false quer dizer que o jogador ainda n tirou numero 5 no dado para poder sair da casa inicial
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				if(movimento == 5) { //se movimento for 5, jogador pode colocar peca na casa de saida
					//while()
					//TextAreaLog.getTextAreaLog().printLog("Jogador, por favor, escolha o peao.");
					//while(t!=true)
						//TextAreaLog.getTextAreaLog().printLog("teste");
					
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
					
					v = (Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr();
					
					novo_x1 = v.RetornaX(); //x da casa de saida
					novo_y1 = v.RetornaY(); //y da casa de saida
					
					j1.SetP1X(novo_x1);
					j1.SetP1Y(novo_y1);

					if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1 != null) {
						Inicializador.getInicializador().jogadores_na_casa[6][1].pode = false;
						
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao())); //adiciona peao a casa de saida; peao o1 preenchido 
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
						
						//captura
						if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux != null) {
							
							if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.GREEN) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j2.SetP1Color(new Color(0,0,0,0)); //faz o peao em campo ser transparente
								j2.getPeao(j2.getNumPeao()).setPosIni(); //volta com sua lista
								j2.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
									j2.SetPX(1); 
									j2.SetPY(10);
									j2.SetP1X(1); 
									j2.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
									j2.SetPX(1);
									j2.SetPY(13);
									j2.SetP1X(1); 
									j2.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
									j2.SetPX(4);
									j2.SetPY(10);
									j2.SetP1X(4); 
									j2.SetP1Y(10);
								}
								else {
									j2.SetPX(4);
									j2.SetPY(13);
									j2.SetP1X(4); 
									j2.SetP1Y(13);
								}
								
								//reiniciando as variaveis
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(2, -1);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(2, true);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(2,0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.YELLOW) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j3.SetP1Color(new Color(0,0,0,0));
								j3.getPeao(j3.getNumPeao()).setPosIni();
								j3.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
									j3.SetPX(10); 
									j3.SetPY(13);
									j3.SetP1X(10); 
									j3.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
									j3.SetPX(10);
									j3.SetPY(10);
									j3.SetP1X(10); 
									j3.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
									j3.SetPX(13);
									j3.SetPY(13);
									j3.SetP1X(13); 
									j3.SetP1Y(13);
								}
								else {
									j3.SetPX(13);
									j3.SetPY(10);
									j3.SetP1X(13); 
									j3.SetP1Y(10);
								}
								
								//reiniciando as variaveis
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(3, -1);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(3, true);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(3,0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.BLUE) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j4.SetP1Color(new Color(0,0,0,0));
								j4.getPeao(j4.getNumPeao()).setPosIni();
								j4.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
									j4.SetPX(13); 
									j4.SetPY(4);
									j4.SetP1X(13); 
									j4.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
									j4.SetPX(13);
									j4.SetPY(1);
									j4.SetP1X(13); 
									j4.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
									j4.SetPX(10);
									j4.SetPY(4);
									j4.SetP1X(10); 
									j4.SetP1Y(4);
								}
								else {
									j4.SetPX(10);
									j4.SetPY(1);
									j4.SetP1X(10); 
									j4.SetP1Y(1);
								}
								
								//reiniciando as variaveis
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(4, -1);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(4, true);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(4,0);
							}
								
							Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux = null; //transformando variavel auxiliar em null novamente
							
							Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).RemovePeao(j1.getPeao(j1.getNumPeao()));
					
							//jogador q fizer uma captura pode andar mais 6
							for (int i = 1; i < 6; i++) {
								j1.getPeao(j1.getNumPeao()).getProx();
							}
							
							v = (Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr();
							
							novo_x1 = v.RetornaX();
							novo_y1 = v.RetornaY();
							
							j1.SetP1X(novo_x1);
							j1.SetP1Y(novo_y1);
							
							Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao()));
							Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
							TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
							TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
						
						}
					
					}else {
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao())); //adiciona peao a casa de saida; peao o1 preenchido 
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
					}
								
					Inicializador.getInicializador().repaint();
					
					j1.getPeao(j1.getNumPeao()).setCinco(1, true); //sai do cincoX = false ja q o jogador tirou 5
					j1.getPeao(j1.getNumPeao()).setY(1, true);
				}
			}			
    		
    		Inicializador.getInicializador().jogadores_na_casa[6][1].pode = true;
			
    		if(j1.getPeao(j1.getNumPeao()).getC(1) == true){//quando c1 for true, quer dizer q o peao ja esta no tabuleiro e vai andar por ele
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				//eh necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
				Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).RemovePeao(j1.getPeao(j1.getNumPeao()));
				
				if(movimento == 6) {//se movimento for 6, jogador pode jogar novamente	
					JogadoresController.getJogadoresController().m = false; 
					j1.getPeao(j1.getNumPeao()).setMd(1, 1);
					
					if(j1.getPeao(j1.getNumPeao()).getMd(1) == 3){
						if(novo_x1!=7 && novo_y1!=1 || novo_x1!=7 && novo_y1!=2 || novo_x1!=7 && novo_y1!=3 || novo_x1!=7 && novo_y1!=4 || novo_x1!=7 && novo_y1!=5 || novo_x1!=7 && novo_y1!=6){

							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPeao(j1.getNumPeao()).setPosIni();
							
							j1.SetPColor(Color.WHITE);
							
							if(j1.getNumPeao() == 0) { //se for o primeiro peao 
								j1.SetPX(4); //colorem-se essas casas
								j1.SetPY(1);
								j1.SetP1X(4);
								j1.SetP1Y(1);
							}
							else if(j1.getNumPeao() == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
								j1.SetP1X(4);
								j1.SetP1Y(4);
							}
							else if(j1.getNumPeao() == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
								j1.SetP1X(1);
								j1.SetP1Y(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
								j1.SetP1X(1);
								j1.SetP1Y(4);
							}
							
							j1.getPeao(j1.getNumPeao()).setMd(1,0);
							j1.getPeao(j1.getNumPeao()).setCinco(1, false);
							j1.getPeao(j1.getNumPeao()).setC(1, false);
							j1.getPeao(j1.getNumPeao()).setY(1, false);
							JogadoresController.getJogadoresController().m = true;
							
							Inicializador.getInicializador().repaint();	
						}
						else {
							JogadoresController.getJogadoresController().m = true;
							j1.getPeao(j1.getNumPeao()).setMd(1,0);
						}
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					j1.getPeao(j1.getNumPeao()).setMd(1,0);		
				}
				
				if(j1.getPeao(j1.getNumPeao()).getFim(1) != -1) {
					j1.getPeao(j1.getNumPeao()).setFim(1, j1.getYFinal() - ((Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr()).RetornaY());
					
					if((j1.getPeao(j1.getNumPeao()).getFim(1) - movimento) == 0 || movimento < j1.getPeao(j1.getNumPeao()).getFim(1)) 
						j1.getPeao(j1.getNumPeao()).setY(1, true);
					else
						j1.getPeao(j1.getNumPeao()).setY(1, false);
				}
				
				if(j1.getPeao(j1.getNumPeao()).getY(1) == true) {

					for (int i = 1; i < movimento + 1 ; i++) {
						j1.getPeao(j1.getNumPeao()).getProx();
					}
					
					v = (Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr();
					
					novo_x1 = v.RetornaX();
					novo_y1 = v.RetornaY();
					
					j1.SetP1X(novo_x1);
					j1.SetP1Y(novo_y1);
					
					Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao()));
					Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
					TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
					TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
					
					//captura
					if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux != null) {
						
						if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); //faz o peao em campo ser transparente
							j2.getPeao(j2.getNumPeao()).setPosIni(); //volta com sua lista
							j2.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
								j2.SetP1X(1); 
								j2.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
								j2.SetP1X(1); 
								j2.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
								j2.SetP1X(4); 
								j2.SetP1Y(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
								j2.SetP1X(4); 
								j2.SetP1Y(13);
							}
							
							//reiniciando as variaveis
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(2, -1);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(2, true);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(2,0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPeao(j3.getNumPeao()).setPosIni();
							j3.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
								j3.SetP1X(10); 
								j3.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
								j3.SetP1X(10); 
								j3.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
								j3.SetP1X(13); 
								j3.SetP1Y(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
								j3.SetP1X(13); 
								j3.SetP1Y(10);
							}
							
							//reiniciando as variaveis
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(3, -1);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(3, true);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(3,0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPeao(j4.getNumPeao()).setPosIni();
							j4.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
								j4.SetP1X(13); 
								j4.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
								j4.SetP1X(13); 
								j4.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
								j4.SetP1X(10); 
								j4.SetP1Y(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
								j4.SetP1X(10); 
								j4.SetP1Y(1);
							}
							
							//reiniciando as variaveis
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setCinco(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setC(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setFim(4, -1);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setY(4, true);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao).setMd(4,0);
						}
							
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).aux = null; //transformando variavel auxiliar em null novamente
						
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).RemovePeao(j1.getPeao(j1.getNumPeao()));
				
						//jogador q fizer uma captura pode andar mais 6
						for (int i = 1; i < 6; i++) {
							j1.getPeao(j1.getNumPeao()).getProx();
						}
						
						v = (Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr();
						
						novo_x1 = v.RetornaX();
						novo_y1 = v.RetornaY();
						
						j1.SetP1X(novo_x1);
						j1.SetP1Y(novo_y1);
						
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao()));
						Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
					
					}
				
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x1==7 && novo_y1==1+i){			
														
						j1.getPeao(j1.getNumPeao()).setFim(1, j1.getYFinal() - ((Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr()).RetornaY());
						
						TextAreaLog.getTextAreaLog().printLog("fim1: " + j1.getPeao(j1.getNumPeao()).getFim(1));
						
						if((j1.getPeao(j1.getNumPeao()).getFim(1) - movimento) == 0 || movimento < j1.getPeao(j1.getNumPeao()).getFim(1)) 
							j1.getPeao(j1.getNumPeao()).setY(1, true);
						else 
							j1.getPeao(j1.getNumPeao()).setY(1, false);
					}	
				}
				
			}
			
			if(j1.getNumPeao() == 0)
				Inicializador.getInicializador().vm1.setLocation(50*novo_y1, 50*novo_x1);
			else if(j1.getNumPeao() == 1)
				Inicializador.getInicializador().vm2.setLocation(50*novo_y1, 50*novo_x1);
			else if(j1.getNumPeao() == 2)
				Inicializador.getInicializador().vm3.setLocation(50*novo_y1, 50*novo_x1);
			else if(j1.getNumPeao() == 3)
				Inicializador.getInicializador().vm4.setLocation(50*novo_y1, 50*novo_x1);				
			
			//caso de dois peoes na mesma casa
			if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2 != null) {	
				if(novo_x1==1 && novo_y1==8) {
					TextAreaLog.getTextAreaLog().printLog("verde");
					
					if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.GREEN)
						Inicializador.getInicializador().getCaminho(1, 8).o1.getP1().pode = true;
					else
						Inicializador.getInicializador().getCaminho(1, 8).o1.getP1().pode = false;
				}
				

				Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().pode = true;
				Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().b = j1.getPeao(j1.getNumPeao()).getP1().a;
				
			}
			
			
			if((j1.getX() == 7 && j1.getY() == 6) && JogadoresController.getJogadoresController().getCont(1) != 3) {//se o peao tiver chegado na casa final e nao for o ultimo, mudamos o peao do jogador para o seguinte
			
				j1.mudaPeao();
				JogadoresController.getJogadoresController().setCont(1, (JogadoresController.getJogadoresController().getCont(1) + 1));
				
				//jogador pode andar mais 6
				for (int i = 1; i < 6; i++) {
					j1.getPeao(j1.getNumPeao()).getProx();
				}
				
				v = (Vetor) j1.getPeao(j1.getNumPeao()).getPosCorr();
				
				novo_x1 = v.RetornaX();
				novo_y1 = v.RetornaY();
				
				j1.SetP1X(novo_x1);
				j1.SetP1Y(novo_y1);
				
				Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).AdicionaPeao(j1.getPeao(j1.getNumPeao()));
				Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).numPeao = j1.getNumPeao();
				TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x1, novo_y1));
				TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x1, novo_y1));
				
				//reiniciando as variavies para novo peao
				j1.getPeao(j1.getNumPeao()).setCinco(1, false);
				j1.getPeao(j1.getNumPeao()).setC(1, false);
				j1.getPeao(j1.getNumPeao()).setFim(1, -1);
				j1.getPeao(j1.getNumPeao()).setY(1, true);
				j1.getPeao(j1.getNumPeao()).setMd(1, 0);
				JogadoresController.getJogadoresController().setM(true);
			}
			
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 1) {
			
			if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.GREEN) { 
					Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
					j1.SetP1Color(Color.RED);
				}
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2 != null) {
    			Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false;
    			Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2.getP1().pode = false;
    			
    			j2.SetP1Color(Color.GREEN);
    			
    			if(j1.getPeao(j1.getNumPeao()).getCinco(1) != false)
    				j1.SetP1Color(Color.RED);
    			else if(j3.getPeao(j3.getNumPeao()).getCinco(3) != false)
    				j3.SetP1Color(Color.YELLOW);
    			else if(j4.getPeao(j4.getNumPeao()).getCinco(4) != false)
    				j4.SetP1Color(Color.BLUE);
			}
			else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.GREEN) {
					Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
					j3.SetP1Color(Color.YELLOW);
				}
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.GREEN){
					Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				} 
	  		}
		 				
			if(j2.getPeao(j2.getNumPeao()).getCinco(2) == false) {
				
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
					
					v = (Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr();
					
					novo_x2 = v.RetornaX(); 
					novo_y2 = v.RetornaY(); 
					
					j2.SetP1X(novo_x2);
					j2.SetP1Y(novo_y2);
					
					if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1 != null) {
						Inicializador.getInicializador().jogadores_na_casa[1][8].pode = false;
					
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao())); 
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
			
						if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux != null) {
							if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.RED) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j1.SetP1Color(new Color(0,0,0,0));
								j1.getPeao(j1.getNumPeao()).setPosIni();
								j1.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
									j1.SetPX(4); 
									j1.SetPY(1);
									j1.SetP1X(4); 
									j1.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
									j1.SetPX(4);
									j1.SetPY(4);
									j1.SetP1X(4); 
									j1.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
									j1.SetPX(1);
									j1.SetPY(1);
									j1.SetP1X(1); 
									j1.SetP1Y(1);
								}
								else {
									j1.SetPX(1);
									j1.SetPY(4);
									j1.SetP1X(1); 
									j1.SetP1Y(4);
								}
								
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(1, -1);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(1, true);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(1, 0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.YELLOW) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j3.SetP1Color(new Color(0,0,0,0));
								j3.getPeao(j3.getNumPeao()).setPosIni();
								j3.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
									j3.SetPX(10); 
									j3.SetPY(13);
									j3.SetP1X(10); 
									j3.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
									j3.SetPX(10);
									j3.SetPY(10);
									j3.SetP1X(10); 
									j3.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
									j3.SetPX(13);
									j3.SetPY(13);
									j3.SetP1X(13); 
									j3.SetP1Y(13);
								}
								else {
									j3.SetPX(13);
									j3.SetPY(10);
									j3.SetP1X(13); 
									j3.SetP1Y(10);
								}
								
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(3, -1);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(3, true);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(3, 0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.BLUE) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j4.SetP1Color(new Color(0,0,0,0));
								j4.getPeao(j4.getNumPeao()).setPosIni();
								j4.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
									j4.SetPX(13); 
									j4.SetPY(4);
									j4.SetP1X(13); 
									j4.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
									j4.SetPX(13);
									j4.SetPY(1);
									j4.SetP1X(13); 
									j4.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
									j4.SetPX(10);
									j4.SetPY(4);
									j4.SetP1X(10); 
									j4.SetP1Y(4);
								}
								else {
									j4.SetPX(10);
									j4.SetPY(1);
									j4.SetP1X(10); 
									j4.SetP1Y(1);
								}
								
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(4, -1);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(4, true);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(4, 0);		
							}
							
							Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux = null;
							
							Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).RemovePeao(j2.getPeao(j2.getNumPeao()));
							
							//jogador q fizer uma captura pode andar mais 6
							for (int i = 1; i < 6; i++) {
								j2.getPeao(j2.getNumPeao()).getProx();
							}
							
							v = (Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr();
							
							novo_x2 = v.RetornaX();
							novo_y2 = v.RetornaY();
							
							j2.SetP1X(novo_x2);
							j2.SetP1Y(novo_y2);
							
							Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao()));
							Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
							TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x2, novo_y2));
							TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x2, novo_y2));
						}
					
					}else {
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao())); 
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x2, novo_y2));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x2, novo_y2));

					}
					
					Inicializador.getInicializador().repaint();
					
					j2.getPeao(j2.getNumPeao()).setCinco(2, true);
					j2.getPeao(j2.getNumPeao()).setY(2, true);
				}
			}	
			
			Inicializador.getInicializador().jogadores_na_casa[1][8].pode = true;
		
			if(j2.getPeao(j2.getNumPeao()).getC(2) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).RemovePeao(j2.getPeao(j2.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					j2.getPeao(j2.getNumPeao()).setMd(2,1); 
						
					if(j2.getPeao(j2.getNumPeao()).getMd(2) == 3) {
						if(novo_y2!=7 && novo_x2!=1 || novo_y2!=7 && novo_x2!=2 || novo_y2!=7 && novo_x2!=3 || novo_y2!=7 && novo_x2!=4 || novo_y2!=7 && novo_x2!=5 || novo_y2!=7 && novo_x2!=6){

							j2.SetP1Color(new Color(0,0,0,0));
							j2.getPeao(j2.getNumPeao()).setPosIni();
							
							j2.SetPColor(Color.WHITE);
							
							if(j2.getNumPeao() == 0) { //se for o primeiro peao 
								j2.SetPX(1); //colorem-se essas casas
								j2.SetPY(10);
								j2.SetP1X(1);
								j2.SetP1Y(10);
							}
							else if(j2.getNumPeao() == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
								j2.SetP1X(1);
								j2.SetP1Y(13);
							}
							else if(j2.getNumPeao() == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
								j2.SetP1X(4);
								j2.SetP1Y(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
								j2.SetP1X(4);
								j2.SetP1Y(13);
							}
							
							j2.getPeao(j2.getNumPeao()).setMd(2,0);
							j2.getPeao(j2.getNumPeao()).setCinco(2, false);
							j2.getPeao(j2.getNumPeao()).setC(2, false);
							j2.getPeao(j2.getNumPeao()).setY(2, false);
							JogadoresController.getJogadoresController().m = true;
							
							Inicializador.getInicializador().repaint();
						}
						else {
							JogadoresController.getJogadoresController().m = true;
							j2.getPeao(j2.getNumPeao()).setMd(2,0);
						}
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					j2.getPeao(j2.getNumPeao()).setMd(2, 0);		
				}
				
				if(j2.getPeao(j2.getNumPeao()).getFim(2) != -1) {
					j2.getPeao(j2.getNumPeao()).setFim(2, j2.getXFinal() - ((Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr()).RetornaX());
					
					if((j2.getPeao(j2.getNumPeao()).getFim(2) - movimento) == 0 || movimento < j2.getPeao(j2.getNumPeao()).getFim(2)) 
						j2.getPeao(j2.getNumPeao()).setY(2, true);
					else
						j2.getPeao(j2.getNumPeao()).setY(2, false);
				}
				
				if(j2.getPeao(j2.getNumPeao()).getY(2) == true) {

					for (int i = 1; i < movimento + 1 ; i++)
						j2.getPeao(j2.getNumPeao()).getProx();
					
					v = (Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr();
					
					novo_x2 = v.RetornaX();
					novo_y2 = v.RetornaY();
					
					j2.SetP1X(novo_x2);
					j2.SetP1Y(novo_y2);
					
					Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao()));
					Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
					
					if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux != null) {
						if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.RED) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPeao(j1.getNumPeao()).setPosIni();
							j1.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
								j1.SetP1X(4); 
								j1.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
								j1.SetP1X(4); 
								j1.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
								j1.SetP1X(1); 
								j1.SetP1Y(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
								j1.SetP1X(1); 
								j1.SetP1Y(4);
							}
							
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(1, -1);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(1, true);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(1, 0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPeao(j3.getNumPeao()).setPosIni();
							j3.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
								j3.SetP1X(10); 
								j3.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
								j3.SetP1X(10); 
								j3.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
								j3.SetP1X(13); 
								j3.SetP1Y(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
								j3.SetP1X(13); 
								j3.SetP1Y(10);
							}
							
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(3, -1);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(3, true);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(3, 0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPeao(j4.getNumPeao()).setPosIni();
							j4.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
								j4.SetP1X(13); 
								j4.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
								j4.SetP1X(13); 
								j4.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
								j4.SetP1X(10); 
								j4.SetP1Y(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
								j4.SetP1X(10); 
								j4.SetP1Y(1);
							}
							
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setCinco(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setC(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setFim(4, -1);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setY(4, true);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao).setMd(4, 0);		
						}
						
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).aux = null;
						
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).RemovePeao(j2.getPeao(j2.getNumPeao()));
						
						//jogador q fizer uma captura pode andar mais 6
						for (int i = 1; i < 6; i++) {
							j2.getPeao(j2.getNumPeao()).getProx();
						}
						
						v = (Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr();
						
						novo_x2 = v.RetornaX();
						novo_y2 = v.RetornaY();
						
						j2.SetP1X(novo_x2);
						j2.SetP1Y(novo_y2);
						
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao()));
						Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x2, novo_y2));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x2, novo_y2));
					}
											
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_y2==7 && novo_x2==1+i){			
														
						j2.getPeao(j2.getNumPeao()).setFim(2, j2.getXFinal() - ((Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr()).RetornaX());
						TextAreaLog.getTextAreaLog().printLog("fim2: " + j2.getPeao(j2.getNumPeao()).getFim(2));
						
						if((j2.getPeao(j2.getNumPeao()).getFim(2) - movimento) == 0 || movimento < j2.getPeao(j2.getNumPeao()).getFim(2)) 
							j2.getPeao(j2.getNumPeao()).setY(2, true);
						else 
							j2.getPeao(j2.getNumPeao()).setY(2, false);
					}	
				}
			}
			
			if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2 != null) {
				Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().pode = true;
				Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().b = j2.getPeao(j2.getNumPeao()).getP1().a;	
				
				if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.RED)
					j2.SetP1Color(new Color(0,0,0,0));
			}
			
			if((j2.getX() == 6 && j2.getY() == 7) && j2.getNumPeao() != 3) {
				
				j2.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(2,(JogadoresController.getJogadoresController().getCont(2) + 1));
				
				//jogador q fizer uma captura pode andar mais 6
				for (int i = 1; i < 6; i++) {
					j2.getPeao(j2.getNumPeao()).getProx();
				}
				
				v = (Vetor) j2.getPeao(j2.getNumPeao()).getPosCorr();
				
				novo_x2 = v.RetornaX();
				novo_y2 = v.RetornaY();
				
				j2.SetP1X(novo_x2);
				j2.SetP1Y(novo_y2);
				
				Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).AdicionaPeao(j2.getPeao(j2.getNumPeao()));
				Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).numPeao = j2.getNumPeao();
				TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x2, novo_y2));
				TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x2, novo_y2));
				
				j2.getPeao(j2.getNumPeao()).setCinco(2, false);
				j2.getPeao(j2.getNumPeao()).setC(2, false);
				j2.getPeao(j2.getNumPeao()).setFim(2, -1);
				j2.getPeao(j2.getNumPeao()).setY(2, true);
				j2.getPeao(j2.getNumPeao()).setMd(2, 0);
				JogadoresController.getJogadoresController().setM(true);
			}
		}
		else if(JogadoresController.getJogadoresController().getJogadorTurno() == 2) {
			
			if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.YELLOW) 
					Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.YELLOW) 
					Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2 != null) {
    			Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false;
    			Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2.getP1().pode = false;
    			
    			j3.SetP1Color(Color.YELLOW);
    			
    			if(j1.getPeao(j1.getNumPeao()).getCinco(1) != false)
    				j1.SetP1Color(Color.RED);
    			else if(j2.getPeao(j2.getNumPeao()).getCinco(3) != false)
    				j2.SetP1Color(Color.GREEN);
    			else if(j4.getPeao(j4.getNumPeao()).getCinco(4) != false)
    				j4.SetP1Color(Color.BLUE);
    		}
			else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.YELLOW){
					Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false; 
					j4.SetP1Color(Color.BLUE);
				} 
	  		}
			
			if(j3.getPeao(j3.getNumPeao()).getCinco(3) == false) {
				
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
					
					v = (Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr();
					
					novo_x3 = v.RetornaX(); 
					novo_y3 = v.RetornaY(); 
					
					j3.SetP1X(novo_x3);
					j3.SetP1Y(novo_y3);
					
					
					if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1 != null) {
						Inicializador.getInicializador().jogadores_na_casa[8][13].pode = false;

						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
						
						if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux != null) {
							
							if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.RED) {
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j1.SetP1Color(new Color(0,0,0,0));
								j1.getPeao(j1.getNumPeao()).setPosIni();
								j1.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
									j1.SetPX(4); 
									j1.SetPY(1);
									j1.SetP1X(4); 
									j1.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
									j1.SetPX(4);
									j1.SetPY(4);
									j1.SetP1X(4);
									j1.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
									j1.SetPX(1);
									j1.SetPY(1);
									j1.SetP1X(1);
									j1.SetP1Y(1);
								}
								else {
									j1.SetPX(1);
									j1.SetPY(4);
									j1.SetP1X(1);
									j1.SetP1Y(4);
								}
								
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(1, -1);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(1, true);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(1, 0);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.GREEN) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j2.SetP1Color(new Color(0,0,0,0)); 
								j2.getPeao(j2.getNumPeao()).setPosIni();
								j2.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
									j2.SetPX(1); 
									j2.SetPY(10);
									j2.SetP1X(1); 
									j2.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
									j2.SetPX(1);
									j2.SetPY(13);
									j2.SetP1X(1); 
									j2.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
									j2.SetPX(4);
									j2.SetPY(10);
									j2.SetP1X(4); 
									j2.SetP1Y(10);
								}
								else {
									j2.SetPX(4);
									j2.SetPY(13);
									j2.SetP1X(4); 
									j2.SetP1Y(13);
								}
								
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(2, -1);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(2, true);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(2, 0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.BLUE) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j4.SetP1Color(new Color(0,0,0,0));
								j4.getPeao(j4.getNumPeao()).setPosIni();
								j4.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
									j4.SetPX(13); 
									j4.SetPY(4);
									j4.SetP1X(13); 
									j4.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
									j4.SetPX(13);
									j4.SetPY(1);
									j4.SetP1X(13); 
									j4.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
									j4.SetPX(10);
									j4.SetPY(4);
									j4.SetP1X(10); 
									j4.SetP1Y(4);
								}
								else {
									j4.SetPX(10);
									j4.SetPY(1);
									j4.SetP1X(10); 
									j4.SetP1Y(1);
								}
								
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(4, false);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(4, -1);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(4, true);
								j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(4, 0);		
							}
							
							Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux = null;
							
							Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).RemovePeao(j3.getPeao(j3.getNumPeao()));
							
							//jogador q fizer uma captura pode andar mais 6
							for (int i = 1; i < 6; i++) {
								j3.getPeao(j3.getNumPeao()).getProx();
							}
							
							v = (Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr();
							
							novo_x3 = v.RetornaX();
							novo_y3 = v.RetornaY();
							
							j3.SetP1X(novo_x3);
							j3.SetP1Y(novo_y3);
							
							Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
							Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
							TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x3, novo_y3));
							TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x3, novo_y3));
								
						}
					}
					else{
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao())); 
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
					}
					
					Inicializador.getInicializador().repaint();
					
					j3.getPeao(j3.getNumPeao()).setCinco(3, true);
					j3.getPeao(j3.getNumPeao()).setY(3, true);
				}
			}
			
			Inicializador.getInicializador().jogadores_na_casa[8][13].pode = true;
			
			if(j3.getPeao(j3.getNumPeao()).getC(3) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).RemovePeao(j3.getPeao(j3.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					j3.getPeao(j3.getNumPeao()).setMd(3,1); 
								
					if(j3.getPeao(j3.getNumPeao()).getMd(3) == 3) {
						if(novo_x3!=7 && novo_y3!=8 || novo_x3!=7 && novo_y3!=9 || novo_x3!=7 && novo_y3!=10 || novo_x3!=7 && novo_y3!=11 || novo_x3!=7 && novo_y3!=12 || novo_x3!=7 && novo_y3!=13){
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPeao(j3.getNumPeao()).setPosIni();
							
							j3.SetPColor(Color.WHITE);
							
							if(j3.getNumPeao() == 0) { //se for o primeiro peao 
								j3.SetPX(10); //colorem-se essas casas
								j3.SetPY(13);
								j3.SetP1X(10);
								j3.SetP1Y(13);
							}
							else if(j3.getNumPeao() == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
								j3.SetP1X(10);
								j3.SetP1Y(10);
							}
							else if(j3.getNumPeao() == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
								j3.SetP1X(13);
								j3.SetP1Y(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
								j3.SetP1X(13);
								j3.SetP1Y(10);
							}
							
							j3.getPeao(j3.getNumPeao()).setMd(3,0);
							j3.getPeao(j3.getNumPeao()).setCinco(3, false);
							j3.getPeao(j3.getNumPeao()).setC(3, false);
							j3.getPeao(j3.getNumPeao()).setY(3, false);
							JogadoresController.getJogadoresController().m = true;
							
							Inicializador.getInicializador().repaint();
						}
						else {
							JogadoresController.getJogadoresController().m = true;
							j3.getPeao(j3.getNumPeao()).setMd(3,0);
						}
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					j3.getPeao(j3.getNumPeao()).setMd(3, 0);		
				}
				
				if(j3.getPeao(j3.getNumPeao()).getFim(3) != -1) {
					j3.getPeao(j3.getNumPeao()).setFim(3,((Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr()).RetornaY() - j3.getYFinal());
					
					if((j3.getPeao(j3.getNumPeao()).getFim(3) - movimento) == 0 || movimento < j3.getPeao(j3.getNumPeao()).getFim(3)) 
						j3.getPeao(j3.getNumPeao()).setY(3,true);
					else
						j3.getPeao(j3.getNumPeao()).setY(3,false);
				}
			
				if(j3.getPeao(j3.getNumPeao()).getY(3) == true) {
					
					for (int i = 1; i < movimento + 1 ; i++)
						j3.getPeao(j3.getNumPeao()).getProx();
					
					v = (Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr();
					
					novo_x3 = v.RetornaX();
					novo_y3 = v.RetornaY();
					
					j3.SetP1X(novo_x3);
					j3.SetP1Y(novo_y3);
					
					Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
					Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
					
					if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux != null) {
						
						if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.RED) {
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPeao(j1.getNumPeao()).setPosIni();
							j1.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
								j1.SetP1X(4); 
								j1.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
								j1.SetP1X(4); 
								j1.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
								j1.SetP1X(1); 
								j1.SetP1Y(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
								j1.SetP1X(1); 
								j1.SetP1Y(4);
							}
							
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(1, -1);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(1, true);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(1, 0);
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); 
							j2.getPeao(j2.getNumPeao()).setPosIni();
							j2.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
								j2.SetP1X(1); 
								j2.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
								j2.SetP1X(1); 
								j2.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
								j2.SetP1X(4); 
								j2.SetP1Y(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
								j2.SetP1X(4); 
								j2.SetP1Y(13);
							}
							
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(2, -1);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(2, true);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(2, 0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux.getP1().ExibeP() == Color.BLUE) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPeao(j4.getNumPeao()).setPosIni();
							j4.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 0) { 
								j4.SetPX(13); 
								j4.SetPY(4);
								j4.SetP1X(13); 
								j4.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
								j4.SetP1X(13); 
								j4.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
								j4.SetP1X(10); 
								j4.SetP1Y(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
								j4.SetP1X(10); 
								j4.SetP1Y(1);
							}
							
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setCinco(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setC(4, false);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setFim(4, -1);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setY(4, true);
							j4.getPeao(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao).setMd(4, 0);		
						}
						
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).aux = null;
						
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).RemovePeao(j3.getPeao(j3.getNumPeao()));
						
						//jogador q fizer uma captura pode andar mais 6
						for (int i = 1; i < 6; i++) {
							j3.getPeao(j3.getNumPeao()).getProx();
						}
						
						v = (Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr();
						
						novo_x3 = v.RetornaX();
						novo_y3 = v.RetornaY();
						
						j3.SetP1X(novo_x3);
						j3.SetP1Y(novo_y3);
						
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
						Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x3, novo_y3));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x3, novo_y3));
							
					}
					
					Inicializador.getInicializador().repaint();
				}	
				
				for(int i=0; i<6; i++) {
					if(novo_x3==7 && novo_y3==8+i){
						
						j3.getPeao(j3.getNumPeao()).setFim(3,((Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr()).RetornaY() - j3.getYFinal());
						TextAreaLog.getTextAreaLog().printLog("fim3: " + j3.getPeao(j3.getNumPeao()).getFim(3));
						
						if((j3.getPeao(j3.getNumPeao()).getFim(3) - movimento) == 0 || movimento < j3.getPeao(j3.getNumPeao()).getFim(3)) 
							j3.getPeao(j3.getNumPeao()).setY(3,true);
						else
							j3.getPeao(j3.getNumPeao()).setY(3,false);
					}	
				}
			}
			
			if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2 != null) {
				Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().pode = true;
				Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().b = j3.getPeao(j3.getNumPeao()).getP1().a;	
				
				if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.RED || Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.GREEN)
					j3.SetP1Color(new Color(0,0,0,0));
			}
			
			if((j3.getX() == 7 && j3.getY() == 8) && j3.getNumPeao() != 3) {
				
				j3.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(3,(JogadoresController.getJogadoresController().getCont(3) + 1));
				
				//jogador q fizer uma captura pode andar mais 6
				for (int i = 1; i < 6; i++) {
					j3.getPeao(j3.getNumPeao()).getProx();
				}
				
				v = (Vetor) j3.getPeao(j3.getNumPeao()).getPosCorr();
				
				novo_x3 = v.RetornaX();
				novo_y3 = v.RetornaY();
				
				j3.SetP1X(novo_x3);
				j3.SetP1Y(novo_y3);
				
				Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).AdicionaPeao(j3.getPeao(j3.getNumPeao()));
				Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).numPeao = j3.getNumPeao();
				TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x3, novo_y3));
				TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x3, novo_y3));
				
				j3.getPeao(j3.getNumPeao()).setCinco(3, false);
				j3.getPeao(j3.getNumPeao()).setC(3, false);
				j3.getPeao(j3.getNumPeao()).setFim(3, -1);
				j3.getPeao(j3.getNumPeao()).setY(3, true);
				j3.getPeao(j3.getNumPeao()).setMd(3, 0);
				JogadoresController.getJogadoresController().setM(true);				
			}
			
		}
		else {
			
			if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().a == Color.BLUE) 
					Inicializador.getInicializador().getCaminho(novo_x1, novo_y1).o1.getP1().pode = false; 
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().a == Color.BLUE) 
					Inicializador.getInicializador().getCaminho(novo_x2, novo_y2).o1.getP1().pode = false; 
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o2 != null){
				if(Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().a == Color.BLUE)
					Inicializador.getInicializador().getCaminho(novo_x3, novo_y3).o1.getP1().pode = false; 
	  		}
			else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2 != null) {
    			Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().pode = false;
    			Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2.getP1().pode = false;
    			
    			j4.SetP1Color(Color.BLUE);
    			
    			if(j1.getPeao(j1.getNumPeao()).getCinco(1) != false)
    				j1.SetP1Color(Color.RED);
    			else if(j2.getPeao(j2.getNumPeao()).getCinco(3) != false)
    				j2.SetP1Color(Color.GREEN);
    			else if(j3.getPeao(j3.getNumPeao()).getCinco(4) != false)
    				j3.SetP1Color(Color.YELLOW);
    			
    		}
			
			if(j4.getPeao(j4.getNumPeao()).getCinco(4) == false) {
				
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
					
					v = (Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr();
					
					novo_x4 = v.RetornaX(); 
					novo_y4 = v.RetornaY(); 
					
					j4.SetP1X(novo_x4);
					j4.SetP1Y(novo_y4);
					
					if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1 != null) {
						Inicializador.getInicializador().jogadores_na_casa[13][6].pode = false;
						
						if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux != null) {
							if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.RED) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j1.SetP1Color(new Color(0,0,0,0));
								j1.getPeao(j1.getNumPeao()).setPosIni();
								j1.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
									j1.SetPX(4); 
									j1.SetPY(1);
									j1.SetP1X(4); 
									j1.SetP1Y(1);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
									j1.SetPX(4);
									j1.SetPY(4);
									j1.SetP1X(4); 
									j1.SetP1Y(4);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
									j1.SetPX(1);
									j1.SetPY(1);
									j1.SetP1X(1); 
									j1.SetP1Y(1);
								}
								else {
									j1.SetPX(1);
									j1.SetPY(4);
									j1.SetP1X(1); 
									j1.SetP1Y(4);
								}
								
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(1, false);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(1, -1);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(1, true);
								j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(1, 0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.GREEN) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j2.SetP1Color(new Color(0,0,0,0)); 
								j2.getPeao(j2.getNumPeao()).setPosIni();
								j2.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
									j2.SetPX(1); 
									j2.SetPY(10);
									j2.SetP1X(1); 
									j2.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
									j2.SetPX(1);
									j2.SetPY(13);
									j2.SetP1X(1); 
									j2.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
									j2.SetPX(4);
									j2.SetPY(10);
									j2.SetP1X(4); 
									j2.SetP1Y(10);
								}
								else {
									j2.SetPX(4);
									j2.SetPY(13);
									j2.SetP1X(4); 
									j2.SetP1Y(13);
								}
								
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(2, false);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(2, -1);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(2, true);
								j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(2, 0);
								
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.YELLOW) {
								
								TextAreaLog.getTextAreaLog().printLog("peca comida!");
								
								j3.SetP1Color(new Color(0,0,0,0));
								j3.getPeao(j3.getNumPeao()).setPosIni();
								j3.SetPColor(Color.WHITE);
								
								if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
									j3.SetPX(10); 
									j3.SetPY(13);
									j3.SetP1X(10); 
									j3.SetP1Y(13);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
									j3.SetPX(10);
									j3.SetPY(10);
									j3.SetP1X(10); 
									j3.SetP1Y(10);
								}
								else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
									j3.SetPX(13);
									j3.SetPY(13);
									j3.SetP1X(13); 
									j3.SetP1Y(13);
								}
								else {
									j3.SetPX(13);
									j3.SetPY(10);
									j3.SetP1X(13); 
									j3.SetP1Y(10);
								}
								
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(3, false);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(3, -1);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(3, true);
								j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(3, 0);
								
							}
							
							Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux = null;
							
							Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).RemovePeao(j4.getPeao(j4.getNumPeao()));
							
							//jogador q fizer uma captura pode andar mais 6
							for (int i = 1; i < 6; i++) {
								j4.getPeao(j4.getNumPeao()).getProx();
							}
							
							v = (Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr();
							
							novo_x4 = v.RetornaX();
							novo_y4 = v.RetornaY();
							
							j4.SetP1X(novo_x4);
							j4.SetP1Y(novo_y4);
							
							Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao()));
							Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao = j4.getNumPeao();
							TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x4, novo_y4));
							TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x4, novo_y4));
						
						}
						
					}
					else {
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao())); 
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao = j4.getNumPeao();
			
					}
					
					Inicializador.getInicializador().repaint();
					
					j4.getPeao(j4.getNumPeao()).setCinco(4, true); 
					j4.getPeao(j4.getNumPeao()).setY(4, true);

				}
			}
			
			Inicializador.getInicializador().jogadores_na_casa[13][6].pode = true;
			
			if(j4.getPeao(j4.getNumPeao()).getC(4) == true) {
				
				TextAreaLog.getTextAreaLog().printLog("jogador: " + JogadoresController.getJogadoresController().getJogadorTurno() + " numero: " + movimento);
				
				Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).RemovePeao(j4.getPeao(j4.getNumPeao()));
				
				if(movimento == 6) {
					JogadoresController.getJogadoresController().m = false; 
					j4.getPeao(j4.getNumPeao()).setMd(4,1); 
			
					if(j4.getPeao(j4.getNumPeao()).getMd(4) == 3) {
						if(novo_y4!=7 && novo_x4!=8 || novo_y4!=7 && novo_x4!=9 || novo_y4!=7 && novo_x4!=10 || novo_y4!=7 && novo_x4!=11 || novo_y4!=7 && novo_x4!=12 || novo_y4!=7 && novo_x4!=13){
							j4.SetP1Color(new Color(0,0,0,0));
							j4.getPeao(j4.getNumPeao()).setPosIni();
							
							j4.SetPColor(Color.WHITE);
							
							if(j4.getNumPeao() == 0) { //se for o primeiro peao 
								j4.SetPX(13); //colorem-se essas casas
								j4.SetPY(4);
								j4.SetP1X(14);
								j4.SetP1Y(4);
							}
							else if(j4.getNumPeao() == 1) {
								j4.SetPX(13);
								j4.SetPY(1);
								j4.SetP1X(13);
								j4.SetP1Y(1);
							}
							else if(j4.getNumPeao() == 2) {
								j4.SetPX(10);
								j4.SetPY(4);
								j4.SetP1X(10);
								j4.SetP1Y(4);
							}
							else {
								j4.SetPX(10);
								j4.SetPY(1);
								j4.SetP1X(10);
								j4.SetP1Y(1);
							}
							
							j4.getPeao(j4.getNumPeao()).setMd(4,0);
							j4.getPeao(j4.getNumPeao()).setCinco(4, false);
							j4.getPeao(j4.getNumPeao()).setC(4, false);
							j4.getPeao(j4.getNumPeao()).setY(4, false);
							JogadoresController.getJogadoresController().m = true;
							
							Inicializador.getInicializador().repaint();
						}
						else {
							JogadoresController.getJogadoresController().m = true;
							j4.getPeao(j4.getNumPeao()).setMd(4,0);
						}
					}
				}
				else { 
					JogadoresController.getJogadoresController().m = true;
					j4.getPeao(j4.getNumPeao()).setMd(4, 0);		
				}
				
				if(j4.getPeao(j4.getNumPeao()).getFim(4) != -1) {
					j4.getPeao(j4.getNumPeao()).setFim(4, ((Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr()).RetornaX() - j4.getXFinal());
					
					if((j4.getPeao(j4.getNumPeao()).getFim(4) - movimento) == 0 || movimento < j4.getPeao(j4.getNumPeao()).getFim(4)) 
						j4.getPeao(j4.getNumPeao()).setY(4,true);
					else
						j4.getPeao(j4.getNumPeao()).setY(4,false);
				}
				
				if(j4.getPeao(j4.getNumPeao()).getY(4) == true) {
					
					for (int i = 1; i < movimento + 1 ; i++)
						j4.getPeao(j4.getNumPeao()).getProx();
					
					v = (Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr();
					
					novo_x4 = v.RetornaX();
					novo_y4 = v.RetornaY();
					
					j4.SetP1X(novo_x4);
					j4.SetP1Y(novo_y4);
					
					Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao()));
					Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao = j4.getNumPeao();
					
					if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux != null) {
						if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.RED) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j1.SetP1Color(new Color(0,0,0,0));
							j1.getPeao(j1.getNumPeao()).setPosIni();
							j1.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
								j1.SetPX(4); 
								j1.SetPY(1);
								j1.SetP1X(4); 
								j1.SetP1Y(1);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
								j1.SetPX(4);
								j1.SetPY(4);
								j1.SetP1X(4); 
								j1.SetP1Y(4);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
								j1.SetPX(1);
								j1.SetPY(1);
								j1.SetP1X(1); 
								j1.SetP1Y(1);
							}
							else {
								j1.SetPX(1);
								j1.SetPY(4);
								j1.SetP1X(1); 
								j1.SetP1Y(4);
							}
							
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(1, false);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(1, -1);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(1, true);
							j1.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(1, 0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.GREEN) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j2.SetP1Color(new Color(0,0,0,0)); 
							j2.getPeao(j2.getNumPeao()).setPosIni();
							j2.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
								j2.SetPX(1); 
								j2.SetPY(10);
								j2.SetP1X(1); 
								j2.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
								j2.SetPX(1);
								j2.SetPY(13);
								j2.SetP1X(1); 
								j2.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
								j2.SetPX(4);
								j2.SetPY(10);
								j2.SetP1X(4); 
								j2.SetP1Y(10);
							}
							else {
								j2.SetPX(4);
								j2.SetPY(13);
								j2.SetP1X(4); 
								j2.SetP1Y(13);
							}
							
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(2, false);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(2, -1);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(2, true);
							j2.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(2, 0);
							
						}
						else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux.getP1().ExibeP() == Color.YELLOW) {
							
							TextAreaLog.getTextAreaLog().printLog("peca comida!");
							
							j3.SetP1Color(new Color(0,0,0,0));
							j3.getPeao(j3.getNumPeao()).setPosIni();
							j3.SetPColor(Color.WHITE);
							
							if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 0) { 
								j3.SetPX(10); 
								j3.SetPY(13);
								j3.SetP1X(10); 
								j3.SetP1Y(13);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 1) {
								j3.SetPX(10);
								j3.SetPY(10);
								j3.SetP1X(10); 
								j3.SetP1Y(10);
							}
							else if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao == 2) {
								j3.SetPX(13);
								j3.SetPY(13);
								j3.SetP1X(13); 
								j3.SetP1Y(13);
							}
							else {
								j3.SetPX(13);
								j3.SetPY(10);
								j3.SetP1X(13); 
								j3.SetP1Y(10);
							}
							
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setCinco(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setC(3, false);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setFim(3, -1);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setY(3, true);
							j3.getPeao(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao).setMd(3, 0);
							
						}
						
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).aux = null;
						
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).RemovePeao(j4.getPeao(j4.getNumPeao()));
						
						//jogador q fizer uma captura pode andar mais 6
						for (int i = 1; i < 6; i++) {
							j4.getPeao(j4.getNumPeao()).getProx();
						}
						
						v = (Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr();
						
						novo_x4 = v.RetornaX();
						novo_y4 = v.RetornaY();
						
						j4.SetP1X(novo_x4);
						j4.SetP1Y(novo_y4);
						
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao()));
						Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao = j4.getNumPeao();
						TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x4, novo_y4));
						TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x4, novo_y4));
					}
					
					Inicializador.getInicializador().repaint();
				}

				for(int i=0; i<6; i++) {
					if(novo_y4==7 && novo_x4==8+i){

						j4.getPeao(j4.getNumPeao()).setFim(4, ((Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr()).RetornaX() - j4.getXFinal());
						TextAreaLog.getTextAreaLog().printLog("fim4: " + j4.getPeao(j4.getNumPeao()).getFim(4));
						
						if((j4.getPeao(j4.getNumPeao()).getFim(4) - movimento) == 0 || movimento < j4.getPeao(j4.getNumPeao()).getFim(4)) 
							j4.getPeao(j4.getNumPeao()).setY(4,true);
						else
							j4.getPeao(j4.getNumPeao()).setY(4,false);
					}	
				}		
			}
			
			if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o2 != null) {
				Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().pode = true;
				Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().b = j4.getPeao(j4.getNumPeao()).getP1().a;	
				
				if(Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.RED || Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.GREEN || Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).o1.getP1().a == Color.YELLOW) {
					j4.SetP1Color(new Color(0,0,0,0));
				}
			}
			
			if((j4.getX() == 8 && j4.getY() == 7) && j4.getNumPeao() != 3) {
				
				j4.mudaPeao();
				//JogadoresController.getJogadoresController().setCont(4,(JogadoresController.getJogadoresController().getCont(4) + 1));
				
				//jogador q fizer uma captura pode andar mais 6
				for (int i = 1; i < 6; i++) {
					j4.getPeao(j4.getNumPeao()).getProx();
				}
				
				v = (Vetor) j4.getPeao(j4.getNumPeao()).getPosCorr();
				
				novo_x4 = v.RetornaX();
				novo_y4 = v.RetornaY();
				
				j4.SetP1X(novo_x4);
				j4.SetP1Y(novo_y4);
				
				Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).AdicionaPeao(j4.getPeao(j4.getNumPeao()));
				Inicializador.getInicializador().getCaminho(novo_x4, novo_y4).numPeao = j4.getNumPeao();
				TextAreaLog.getTextAreaLog().printLog("o1: "+Inicializador.getInicializador().getO1(novo_x4, novo_y4));
				TextAreaLog.getTextAreaLog().printLog("o2: "+Inicializador.getInicializador().getO2(novo_x4, novo_y4));
				
				j4.getPeao(j4.getNumPeao()).setCinco(4, false);
				j4.getPeao(j4.getNumPeao()).setC(4, false);
				j4.getPeao(j4.getNumPeao()).setFim(4, -1);
				j4.getPeao(j4.getNumPeao()).setY(4, true);
				j4.getPeao(j4.getNumPeao()).setMd(4, 0);
				JogadoresController.getJogadoresController().setM(true);				
			}
			
		}
		if(JogadoresController.getJogadoresController().getM() == true) 
			JogadoresController.getJogadoresController().MudaTurno();

    	//quando cincoX for = true, quer dizer que o jogador tirou 5 podendo colocar um peao na casa de saida
		//para o jogador poder andar pelo tabuleiro, a variavel cX tem q ser true
		//o jogador so pode andar pelo tabuleiro quando for sua vez, por isso deve-se fazer cX = true depois de mudar o turno
		if(j1.getPeao(j1.getNumPeao()).getCinco(1) == true)
			j1.getPeao(j1.getNumPeao()).setC(1, true);
		
		if(j2.getPeao(j2.getNumPeao()).getCinco(2) == true)
			j2.getPeao(j2.getNumPeao()).setC(2, true);
		
		if(j3.getPeao(j3.getNumPeao()).getCinco(3) == true)
			j3.getPeao(j3.getNumPeao()).setC(3, true);
		
		if(j4.getPeao(j4.getNumPeao()).getCinco(4) == true)
			j4.getPeao(j4.getNumPeao()).setC(4, true);
    	
    }
    	
    public static Regras getRegras(String value) {
		if (instance == null) {
		    instance = new Regras();
		    
		    Inicializador.getInicializador().add(instance);
		}
		return instance;
    }

	@Override
	public void update(ActionEvent e) throws InterruptedException, FileNotFoundException, BadLocationException {
		// TODO Auto-generated method stub
		try {
			TextAreaLog.getTextAreaLog().printLog("observador!!!");
			t = true;
		} catch (FileNotFoundException | BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
