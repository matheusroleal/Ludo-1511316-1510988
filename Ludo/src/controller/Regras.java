package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

import Interface.Jogo;
import model.Jogador;
import model.Peao;
import model.TextAreaLog;
import model.Vetor;

public class Regras implements Observado {
	private static volatile Regras instance;
    Jogador j1, j2, j3, j4;
    Vetor v;
    int novo_x, novo_y;
    int antigo_x, antigo_y;
    int movimento;
    int jogador_num;
    int auxn;
    Jogador j;
    private Observador obs;

    private Regras(){

    	movimento = 0;

    	j1 = JogadoresController.getJogadoresController().getJogador(0);
    	j2 = JogadoresController.getJogadoresController().getJogador(1);
    	j3 = JogadoresController.getJogadoresController().getJogador(2);
    	j4 = JogadoresController.getJogadoresController().getJogador(3);
    	
    }

    public void AplicaRegras(int mv) throws FileNotFoundException, BadLocationException, InterruptedException {
    	
    	
    	jogador_num = JogadoresController.getJogadoresController().getJogadorTurno() + 1;

    	j = JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno());

    	movimento = mv;

    	// Se cincoX for false quer dizer que o jogador ainda n tirou o numero 5 no dado para poder sair da casa inicial
    	if (j.getPeao(j.getNumPeao()).getCinco(jogador_num) == false){

    		// Se o movimento for 5, jogador pode colocar peca na casa de saida
    		checaCinco(movimento);
    	}
    	// Quando cinco for true, quer dizer que o peao ja esta no tabuleiro e vai andar por ele
    	else{

    		// Necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
    		removePeaoCaminho();

    		// Se movimento for 6, jogador pode jogar novamente
    		checaSeis(movimento);

    		// Caso o peao esteja na reta final, verirficar se eh possivel move-lo
				if(j.getPeao(j.getNumPeao()).getFim(jogador_num) != -1) {
    			checaFinalAntes();
    		}

    		// Checa se e possivel movimentar a peca
    		if(j.getPeao(j.getNumPeao()).getY(jogador_num) == true && checaBarreira(movimento) && checaAbrigo(movimento)) {
    			// Move o peao de acordo com o valor do movimento
    			movePeao(movimento);
        	}

    		checaFinalDepois();

    	}

    	// Se o peao tiver chegado na casa final e nao for o ultimo, mudamos o peao do jogador para o seguinte
    	if((j.getX() == defineXFinal(jogador_num) && j.getY() == defineYFinal(jogador_num)) && JogadoresController.getJogadoresController().getCont(jogador_num) != 3){
    		peaoNoFinal();
    	}

    	if(JogadoresController.getJogadoresController().getM() == true){
    		JogadoresController.getJogadoresController().MudaTurno();
    		ChamaProxJogador(JogadoresController.getJogadoresController().getJogadorTurno());
  	      	TextAreaLog.getTextAreaLog().printLog("Selecione o peao antes de jogar!");
    	}
    }

    private void captura(int captura_x, int captura_y) throws BadLocationException, FileNotFoundException {
	    Peao p_comido = Jogo.getJogo().getCaminho(captura_x, captura_y).o1;     
	    int jogador_num_remover = getJogadorNum(Jogo.getJogo().getCaminho(captura_x, captura_y).o1.getP1().ExibeCor());
	    Jogador j_remover = JogadoresController.getJogadoresController().getJogador(jogador_num_remover - 1);
	    
	    j_remover.SetP1Color(new Color(0,0,0,0));
		j_remover.getPeao(j_remover.getNumPeao()).setPosIni();
		
		j_remover.setNumPeao( (Jogo.getJogo().jogadores_na_casa[captura_x][captura_y].jogadores.firstElement()).getIndex(Jogo.getJogo().getO1(captura_x, captura_y)));
	
		j_remover.SetPColor(PegaCor(jogador_num_remover));
	
		if(j_remover.getNumPeao() == 0) { //se for o primeiro peao
			j_remover.SetPX(defineXInicial(jogador_num_remover, 0));
			j_remover.SetPY(defineYInicial(jogador_num_remover, 0));
			j_remover.SetP1X(defineXInicial(jogador_num_remover, 0));
			j_remover.SetP1Y(defineYInicial(jogador_num_remover, 0));
		}
		else if(j_remover.getNumPeao() == 1) {
			j_remover.SetPX(defineXInicial(jogador_num_remover, 1));
			j_remover.SetPY(defineYInicial(jogador_num_remover, 1));
			j_remover.SetP1X(defineXInicial(jogador_num_remover, 1));
			j_remover.SetP1Y(defineYInicial(jogador_num_remover, 1));
		}
		else if(j_remover.getNumPeao() == 2) {
			j_remover.SetPX(defineXInicial(jogador_num_remover, 2));
			j_remover.SetPY(defineYInicial(jogador_num_remover, 2));
			j_remover.SetP1X(defineXInicial(jogador_num_remover, 2));
			j_remover.SetP1Y(defineYInicial(jogador_num_remover, 2));
		}
		else {
			j_remover.SetPX(defineXInicial(jogador_num_remover, 3));
			j_remover.SetPY(defineYInicial(jogador_num_remover, 3));
			j_remover.SetP1X(defineXInicial(jogador_num_remover, 3));
			j_remover.SetP1Y(defineYInicial(jogador_num_remover, 3));
		}
	
	    //reiniciando as variaveis
	    p_comido.setCinco(jogador_num_remover, false);
	    p_comido.setC(jogador_num_remover, false);
	    p_comido.setFim(jogador_num_remover, -1);
	    p_comido.setY(jogador_num_remover, true);
	    p_comido.setMd(jogador_num_remover,0);
	
	    // Necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
	    Jogo.getJogo().getCaminho(captura_x, captura_y).RemovePeao(p_comido, j_remover);
	
	    //jogador q fizer uma captura pode andar mais 6
	    movePeao(6);
	    
	    TextAreaLog.getTextAreaLog().printLog("Peca comida!");
    }

    private void checaSeis(int mov) throws FileNotFoundException, BadLocationException{

    	if(mov == 6) {
    		JogadoresController.getJogadoresController().setM(false);
    		j.getPeao(j.getNumPeao()).setMd(jogador_num,1);

    		if(j.getPeao(j.getNumPeao()).getMd(jogador_num) == 3) {
    			if(novo_x!=7 && novo_y!=1 || novo_x!=7 && novo_y!=2 || novo_x!=7 && novo_y!=3 || novo_x!=7 && novo_y!=4 || novo_x!=7 && novo_y!=5 || novo_x!=7 && novo_y!=6 || novo_y!=7 && novo_x!=1 || novo_y!=7 && novo_x!=2 || novo_y!=7 && novo_x!=3 || novo_y!=7 && novo_x!=4 || novo_y!=7 && novo_x!=5 || novo_y!=7 && novo_x!=6 || novo_x!=7 && novo_y!=8 || novo_x!=7 && novo_y!=9 || novo_x!=7 && novo_y!=10 || novo_x!=7 && novo_y!=11 || novo_x!=7 && novo_y!=12 || novo_x!=7 && novo_y!=13 || novo_y!=7 && novo_x!=8 || novo_y!=7 && novo_x!=9 || novo_y!=7 && novo_x!=10 || novo_y!=7 && novo_x!=11 || novo_y!=7 && novo_x!=12 || novo_y!=7 && novo_x!=13){
    				j.SetP1Color(new Color(0,0,0,0));
    				j.getPeao(j.getNumPeao()).setPosIni();

    				j.SetPColor(PegaCor(jogador_num));

    				if(j.getNumPeao() == 0) { //se for o primeiro peao
    					j.SetPX(defineXInicial(jogador_num, 0));
    					j.SetPY(defineYInicial(jogador_num, 0));
    					j.SetP1X(defineXInicial(jogador_num, 0));
    					j.SetP1Y(defineYInicial(jogador_num, 0));
    				}
    				else if(j.getNumPeao() == 1) {
    					j.SetPX(defineXInicial(jogador_num, 1));
    					j.SetPY(defineYInicial(jogador_num, 1));
    					j.SetP1X(defineXInicial(jogador_num, 1));
    					j.SetP1Y(defineYInicial(jogador_num, 1));
    				}
    				else if(j.getNumPeao() == 2) {
    					j.SetPX(defineXInicial(jogador_num, 2));
    					j.SetPY(defineYInicial(jogador_num, 2));
    					j.SetP1X(defineXInicial(jogador_num, 2));
    					j.SetP1Y(defineYInicial(jogador_num, 2));
    				}
    				else {
    					j.SetPX(defineXInicial(jogador_num, 3));
    					j.SetPY(defineYInicial(jogador_num, 3));
    					j.SetP1X(defineXInicial(jogador_num, 3));
    					j.SetP1Y(defineYInicial(jogador_num, 3));
    				}

    				j.getPeao(j.getNumPeao()).setMd(jogador_num,0);
    				j.getPeao(j.getNumPeao()).setCinco(jogador_num, false);
    				j.getPeao(j.getNumPeao()).setC(jogador_num, false);
    				j.getPeao(j.getNumPeao()).setY(jogador_num, false);
    				JogadoresController.getJogadoresController().setM(true);

    				obs.notify(1, this);

    			}
    			else {
    				JogadoresController.getJogadoresController().setM(true);
    				j.getPeao(j.getNumPeao()).setMd(jogador_num,0);
    			}
    		}
    	}
    	else {
    		JogadoresController.getJogadoresController().setM(true);
    		j.getPeao(j.getNumPeao()).setMd(jogador_num,0);
    	}
    }

    private Boolean checaBarreira(int mov) throws BadLocationException, FileNotFoundException{
		int pos_corr = j.getPeao(j.getNumPeao()).getLst().pos;
		int i = 1;
		int caminho_barreira_x, caminho_barreira_y;
		boolean flag_barreira = true;

		v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

		caminho_barreira_x = v.RetornaX();
		caminho_barreira_y = v.RetornaY();

		// move o peao pela lista, checando casa a casa se existe barreiras no caminho
		while(i < (mov +1) && flag_barreira){

			//Checa se existe peoes da mesma cor na mesma casa
			if (Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o1 != null && Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o2 != null) {
				if(Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o1.getP1().ExibeCor() == Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o2.getP1().ExibeCor()) {
					flag_barreira = false;
				}
			}

			j.getPeao(j.getNumPeao()).getProx();

			v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

			caminho_barreira_x = v.RetornaX();
			caminho_barreira_y = v.RetornaY();

			i++;

		}

		// Adiciona na posicao anterior a checagem
		j.getPeao(j.getNumPeao()).getLst().posIni();
		movePeao(pos_corr);
		removePeaoCaminho();

		if(flag_barreira) {
			return true;
		}else {
	      TextAreaLog.getTextAreaLog().printLog("Existe uma barreira no caminho!");
		}

		return false;
    }
    
    private Boolean checaAbrigo(int mov) throws BadLocationException, FileNotFoundException{
    	int caminho_abrigo_x, caminho_abrigo_y;
    	int pos_corr = j.getPeao(j.getNumPeao()).getLst().pos;
    	boolean flag_abrigo = true;
    	  
    	for (int i = 1; i < mov + 1 ; i++) {
    		j.getPeao(j.getNumPeao()).getProx();
    	}

    	v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

    	caminho_abrigo_x = v.RetornaX();
    	caminho_abrigo_y = v.RetornaY();

    	//Checa se e possivel adicionar o peao naquela casa
    	if (Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).o1 != null && Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).o1.getP1().a != j.getPeao(j.getNumPeao()).getP1().a) {
    		// Nesse caso a captura eh executada
    		if(!Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).pode) {
    			captura(caminho_abrigo_x,caminho_abrigo_y);
    		}else {
    			if(corCasaInicial(caminho_abrigo_x, caminho_abrigo_y) != Color.BLACK && Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).o1.getP1().a != corCasaInicial(caminho_abrigo_x, caminho_abrigo_y) && j.getPeao(j.getNumPeao()).getP1().a != corCasaInicial(caminho_abrigo_x, caminho_abrigo_y)){
    				flag_abrigo = false;
    			}
    		}
    	}

    	// Adiciona na posicao anterior a checagem
    	j.getPeao(j.getNumPeao()).getLst().posIni();
    	movePeao(pos_corr);
    	removePeaoCaminho();

		if (flag_abrigo) {
		  return true;
		}else {
		  TextAreaLog.getTextAreaLog().printLog("Um peao de outra cor se encontra no lugar");
		  return false;
		}
    }

    private void movePeao(int mov) throws FileNotFoundException, BadLocationException{
    	for (int i = 1; i < mov + 1 ; i++) {
    		j.getPeao(j.getNumPeao()).getProx();
    	}

    	v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

    	novo_x = v.RetornaX();
    	novo_y = v.RetornaY();

    	j.SetP1X(novo_x);
    	j.SetP1Y(novo_y);

    	Jogo.getJogo().getCaminho(novo_x, novo_y).AdicionaPeao(j.getPeao(j.getNumPeao()), j);

    	obs.notify(1, this);
    }

    private void removePeaoCaminho() throws FileNotFoundException, BadLocationException{
  		v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

  		antigo_x = v.RetornaX();
  		antigo_y = v.RetornaY();

  		Jogo.getJogo().getCaminho(antigo_x, antigo_y).RemovePeao(j.getPeao(j.getNumPeao()), j);
    }

    private void peaoNoFinal(){
    	j.mudaPeao();
    	JogadoresController.getJogadoresController().setCont(jogador_num,(1+JogadoresController.getJogadoresController().getCont(jogador_num)));

    	// Reiniciando as variavies para novo peao
    	j.getPeao(j.getNumPeao()).setCinco(jogador_num, false);
    	j.getPeao(j.getNumPeao()).setC(jogador_num, false);
    	j.getPeao(j.getNumPeao()).setFim(jogador_num, -1);
    	j.getPeao(j.getNumPeao()).setY(jogador_num, true);
    	j.getPeao(j.getNumPeao()).setMd(jogador_num, 0);
    	JogadoresController.getJogadoresController().setM(true);
    }

    private void checaFinalAntes(){
    	if(jogador_num == 1)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getYFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY());
		else if(jogador_num == 2)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getXFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX());
		else if(jogador_num == 3)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY() - j.getYFinal());
		else
			j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX() - j.getXFinal());

		if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num))
			j.getPeao(j.getNumPeao()).setY(jogador_num, true);
		else
			j.getPeao(j.getNumPeao()).setY(jogador_num, false);
    }

    private void checaFinalDepois() {
    	for(int i=0; i<6; i++) {
			if(jogador_num == 1) {
				if(novo_x==7 && novo_y==1+i){

					j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getYFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY());

					if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num))
						j.getPeao(j.getNumPeao()).setY(jogador_num, true);
					else
						j.getPeao(j.getNumPeao()).setY(jogador_num, false);
				}
			}
			else if(jogador_num==2) {
				if(novo_y==7 && novo_x==1+i){

					j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getXFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX());

					if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num))
						j.getPeao(j.getNumPeao()).setY(jogador_num, true);
					else
						j.getPeao(j.getNumPeao()).setY(jogador_num, false);
				}
			}
			else if(jogador_num==3) {
				if(novo_x==7 && novo_y==8+i){

					j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY() - j.getYFinal());

					if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num))
						j.getPeao(j.getNumPeao()).setY(jogador_num,true);
					else
						j.getPeao(j.getNumPeao()).setY(jogador_num,false);
				}
			}
			else {
				if(novo_y==7 && novo_x==8+i){

					j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX() - j.getXFinal());

					if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num))
						j.getPeao(j.getNumPeao()).setY(jogador_num,true);
					else
						j.getPeao(j.getNumPeao()).setY(jogador_num,false);
				}
			}
    	}
    }

    private void checaCinco(int mov) throws FileNotFoundException, BadLocationException{
    	if(mov == 5) {
    		j.SetPColor(Color.WHITE);

    		if(j.getNumPeao() == 0) {
    			j.SetPX(defineXInicial(jogador_num, 0));
    			j.SetPY(defineYInicial(jogador_num, 0));
    		}
    		else if(j.getNumPeao() == 1) {
    			j.SetPX(defineXInicial(jogador_num, 1));
    			j.SetPY(defineYInicial(jogador_num, 1));
    		}
    		else if(j.getNumPeao() == 2) {
    			j.SetPX(defineXInicial(jogador_num, 2));
    			j.SetPY(defineYInicial(jogador_num, 2));
    		}
    		else {
    			j.SetPX(defineXInicial(jogador_num, 3));
    			j.SetPY(defineYInicial(jogador_num, 3));
    		}

    		// Colorindo peao que anda pelo tabuleiro
    		j.SetP1Color(PegaCor(jogador_num));

    		v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

    		novo_x = v.RetornaX();
    		novo_y = v.RetornaY();

    		j.SetP1X(novo_x);
    		j.SetP1Y(novo_y);

    		obs.notify(1, this);

    		j.getPeao(j.getNumPeao()).setCinco(jogador_num, true);
    		j.getPeao(j.getNumPeao()).setY(jogador_num, true);

    		Jogo.getJogo().getCaminho(novo_x, novo_y).AdicionaPeao(j.getPeao(j.getNumPeao()), j);
    	}
    }

    private Color PegaCor (int jogador){
    	switch (jogador) {
        case 1:
        	return Color.RED;
        case 2:
        	return Color.GREEN;
        case 3:
        	return Color.YELLOW;
        case 4:
        	return Color.BLUE;
    	}
    	return null;
    }
    
    private int getJogadorNum(Color cor) {
    	if (cor == Color.RED) {
        	return 1;
    	}else if (cor == Color.GREEN) {
    		return 2;
    	}else if (cor == Color.YELLOW) {
    		return 3;
    	}else if (cor == Color.BLUE) {
    		return 4;
    	}
    	return 0;
    }

    private void ChamaProxJogador(int turno) throws FileNotFoundException, BadLocationException{
    	switch (turno) {
    	case 0:
    		TextAreaLog.getTextAreaLog().printLog("Vez do jogador vermelho!");
    		break;
        case 1:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador verde!");
    		break;
        case 2:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador amarelo!");
    		break;
        case 3:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador azul!");
    		break;
    	}
    }

    private int defineXFinal (int jogador){
    	switch (jogador) {
        case 1:
        	return 7;
        case 2:
        	return 6;
        case 3:
        	return 7;
        case 4:
        	return 8;
    	}
    	return 0;
    }

    private int defineYFinal (int jogador){
    	switch (jogador) {
        case 1:
        	return 6;
        case 2:
        	return 7;
        case 3:
        	return 8;
        case 4:
        	return 7;
    	}
    	return 0;
    }

    private int defineXInicial(int jogador, int peao){
    	switch (jogador) {
        case 1:
        	switch (peao) {
        	case 0:
        		return 4;
        	case 1:
        		return 4;
        	case 2:
        		return 1;
        	case 3:
        		return 1;
        	}
        case 2:
        	switch (peao) {
        	case 0:
        		return 1;
        	case 1:
        		return 1;
        	case 2:
        		return 4;
        	case 3:
        		return 4;
        	}
        case 3:
        	switch (peao) {
        	case 0:
        		return 10;
        	case 1:
        		return 10;
        	case 2:
        		return 13;
        	case 3:
        		return 13;
        	}
        case 4:
        	switch (peao) {
        	case 0:
        		return 13;
        	case 1:
        		return 13;
        	case 2:
        		return 10;
        	case 3:
        		return 10;
        	}
    	}
    	return 0;
    }

    private int defineYInicial(int jogador, int peao){
    	switch (jogador) {
    	case 1:
    		switch (peao) {
    		case 0:
    			return 1;
    		case 1:
    			return 4;
    		case 2:
    			return 1;
    		case 3:
    			return 4;
    		}
        case 2:
        	switch (peao) {
        	case 0:
        		return 10;
        	case 1:
        		return 13;
        	case 2:
        		return 10;
        	case 3:
        		return 13;
        	}
        case 3:
        	switch (peao) {
        	case 0:
        		return 13;
        	case 1:
        		return 10;
        	case 2:
        		return 13;
        	case 3:
        		return 10;
        	}
        case 4:
        	switch (peao) {
        	case 0:
        		return 4;
        	case 1:
        		return 1;
        	case 2:
        		return 4;
        	case 3:
        		return 1;
        	}
    	}
    	return 0;
    }
    
    public Color corCasaInicial(int caminho_abrigo_x, int caminho_abrigo_y){
    	  if (caminho_abrigo_x == 6 && caminho_abrigo_y == 1){
    	    return Color.RED;
    	  }else if(caminho_abrigo_x == 1 && caminho_abrigo_y == 8){
    	    return Color.GREEN;
    	  }else if(caminho_abrigo_x == 8 && caminho_abrigo_y == 13){
    	    return Color.YELLOW;
    	  }else if(caminho_abrigo_x == 13 && caminho_abrigo_y == 6){
    	    return Color.BLUE;
    	  }else{
    	    return Color.BLACK;
    	  }
    }

    public void add(Observador o) {
    	obs = o;
    }

    public void remove(Observador o) {
    	obs = null;
    }

    public static Regras getRegras(){
    	if (instance == null) {
    		instance = new Regras();
    	}
    	return instance;
    }
}
