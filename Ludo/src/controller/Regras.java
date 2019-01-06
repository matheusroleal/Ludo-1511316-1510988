package controller;

import java.awt.Color;
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
    Jogador j;
    private Observador obs;
    boolean flag_malandra;

    private Regras(){
    	flag_malandra = false;

    	movimento = 0;

    	j1 = JogadoresController.getJogadoresController().getJogador(0);
    	j2 = JogadoresController.getJogadoresController().getJogador(1);
    	j3 = JogadoresController.getJogadoresController().getJogador(2);
    	j4 = JogadoresController.getJogadoresController().getJogador(3);

    }

    public void AplicaRegras(int mv) throws FileNotFoundException, BadLocationException, InterruptedException {
    	flag_malandra = false;

    	jogador_num = JogadoresController.getJogadoresController().getJogadorTurno() + 1;

    	j = JogadoresController.getJogadoresController().getJogador(JogadoresController.getJogadoresController().getJogadorTurno());

    	movimento = mv;

    	// Se cincoX for false quer dizer que o jogador ainda n tirou o numero 5 no dado para poder sair da casa inicial
    	if (j.getPeao(j.getNumPeao()).getCinco(jogador_num) == false && checaPossibilidadeSair()){
    		boolean flag_move_seis = checaComeParaSair();
			
    		// Se o movimento for 5, jogador pode colocar peao na casa de saida
    		checaCinco(movimento);
    		
    		if(flag_move_seis) {
	    		// Necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
	    		removePeaoCaminho();
	    		
    			checaCaptura(6);

    			// Move o peao de acordo com o valor do movimento
    			movePeao(6);
    		}
    	}
    	// Se ele tirar mais um cinco, devemos botar um peao na casa de saida
    	else if(movimento == 5 && checaPossibilidadeSair()) {
    		int y = 0;

    		// Encontra um peao que nao foi usado ainda
    		while (y < 4 && j.getPeao(y).getCinco(jogador_num) == true) {
    			y++;
    		}

    		if(y < 4) {
        		j.setNumPeao(y);

    			// Caso o jogador jogue apos ter tirado 6
				JogadoresController.getJogadoresController().setM(true);
    			j.getPeao(j.getNumPeao()).setMd(jogador_num,0);
    			        		
    			boolean flag_move_seis = checaComeParaSair();
    			
    			// Se exite peao para sair, jogador pode colocar peao na casa de saida
        		checaCinco(movimento);
        		
        		if(flag_move_seis) {
    	    		// Necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
    	    		removePeaoCaminho();
    	    		
        			checaCaptura(6);

        			// Move o peao de acordo com o valor do movimento
        			movePeao(6);
        		}
    		}
    		//Caso nao haja peao nao utilizado, usamos o peao pre selecionado
    		else {
	    		// Necessario remover o peao da casa que estava antes para adiciona-lo a casa nova
	    		removePeaoCaminho();

	    		// Se movimento for 6, jogador pode jogar novamente
	    		checaSeis(movimento);

	    		// Caso o peao esteja na reta final, verirficar se eh possivel move-lo
				if(j.getPeao(j.getNumPeao()).getFim(jogador_num) != -1) {
	    			checaFinalAntes();
	    		}

	    		// Checa se e possivel movimentar a peca
	    		if(j.getPeao(j.getNumPeao()).getY(jogador_num) == true ) {

	    				if(checaBarreira(movimento) && checaAbrigo(movimento) && checaCasa(movimento)) {

	            			checaCaptura(movimento);

	            			// Move o peao de acordo com o valor do movimento
	            			movePeao(movimento);
	    				}
	        	}
	    		
	    		checaFinalDepois();

    		}

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
    		if(j.getPeao(j.getNumPeao()).getY(jogador_num) == true ) {

    				if(checaBarreira(movimento) && checaAbrigo(movimento) && checaCasa(movimento)) {

            			checaCaptura(movimento);

            			// Move o peao de acordo com o valor do movimento
            			movePeao(movimento);
    				}
        	}

    		checaFinalDepois();

    	}

    	// Se o peao tiver chegado na casa final
    	if((j.getX() == defineXFinal(jogador_num) && j.getY() == defineYFinal(jogador_num))){

    		// Se nao for o ultimo, mudamos o peao do jogador para o seguinte
    		if(JogadoresController.getJogadoresController().getCont(jogador_num) != 3){
        		peaoNoFinal();
    		}else {
    			defineVencedor(JogadoresController.getJogadoresController().getJogadorTurno());
    		}
    	}

    	if(JogadoresController.getJogadoresController().getM() == true){

    		JogadoresController.getJogadoresController().MudaTurno();
    		ChamaProxJogador(JogadoresController.getJogadoresController().getJogadorTurno());
  	      	TextAreaLog.getTextAreaLog().printLog("Selecione o peao antes de jogar!");
			obs.notify(3, this);
    	}

    }

    public void aplicaClick() throws FileNotFoundException, BadLocationException, InterruptedException {
		obs.notify(2, this);
		if(flag_malandra) {
			AplicaRegras(6);
		}
    }
    
    private boolean checaComeParaSair() throws FileNotFoundException, BadLocationException {
		if(Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1 != null && Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1.getP1().ExibeCor() != corCasaInicial(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)) && Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o2 == null){
			Peao p_comido = Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1;
		    int jogador_num_remover = getJogadorNum(Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1.getP1().ExibeCor());
		    Jogador j_remover = JogadoresController.getJogadoresController().getJogador(jogador_num_remover - 1);

			j_remover.setNumPeao((Jogo.getJogo().jogadores_na_casa[defineXCasaInicial(jogador_num)][defineYCasaInicial(jogador_num)].jogadores.firstElement()).getIndex(Jogo.getJogo().getO1(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num))));
			j_remover.getPeao(j_remover.getNumPeao()).setPosIni();

			j_remover.SetP1Color(new Color(0,0,0,0));
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

		    // Necessario remover o peao da casa que estava antes para adiciona-lo ao inicio
		    Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).RemovePeao(p_comido, j_remover);

		    TextAreaLog.getTextAreaLog().printLog("Peca comida!");

		    //jogador q fizer uma captura pode andar mais 6
		    if(checaBarreira(6) && checaAbrigo(6) && checaCasa(6)) {
		    	return true;
		    }else {
				removePeaoCaminho();
		    	return false;
		    }	    
		}
		return false;
    }

    private void checaCaptura(int mov) throws BadLocationException, FileNotFoundException {
    	int caminho_abrigo_x, caminho_abrigo_y;
    	int pos_corr = j.getPeao(j.getNumPeao()).getLst().pos;

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
    			Peao p_comido = Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).o1;
    		    int jogador_num_remover = getJogadorNum(Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).o1.getP1().ExibeCor());
    		    Jogador j_remover = JogadoresController.getJogadoresController().getJogador(jogador_num_remover - 1);

    			j_remover.setNumPeao((Jogo.getJogo().jogadores_na_casa[caminho_abrigo_x][caminho_abrigo_y].jogadores.firstElement()).getIndex(Jogo.getJogo().getO1(caminho_abrigo_x, caminho_abrigo_y)));
				j_remover.getPeao(j_remover.getNumPeao()).setPosIni();

				j_remover.SetP1Color(new Color(0,0,0,0));
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

    		    // Necessario remover o peao da casa que estava antes para adiciona-lo ao inicio
    		    Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).RemovePeao(p_comido, j_remover);

    		    //jogador q fizer uma captura pode andar mais 6
    		    if(checaBarreira(6) && checaAbrigo(6) && checaCasa(6)) {
        			checaCaptura(6);
        			movimento = movimento + 6;
    		    }else {
    		    	removePeaoCaminho();
    		    }

    		    TextAreaLog.getTextAreaLog().printLog("Peca comida!");
    		}
    	}

    	// Adiciona na posicao anterior a checagem
    	j.getPeao(j.getNumPeao()).getLst().posIni();
    	movePeao(pos_corr);
    	removePeaoCaminho();

    }

    private void checaSeis(int mov) throws FileNotFoundException, BadLocationException{

    	if(mov == 6 && j.getPeao(j.getNumPeao()).getFim(jogador_num) == -1) {
    		checaDesmontaBarreira();
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

    private void checaDesmontaBarreira() throws FileNotFoundException, BadLocationException {
    	int y = 0;
		//Verifica se existe barreira para ser desmontada
		while (y < 4 && j.getPeao(y).getP1().getBarreira() == false) {
			y++;
		}

		if(y < 4) {
			int num_corr = j.getNumPeao();
			adicionaPeaoCaminho();
    		j.setNumPeao(y);
    		if(checaBarreira(6) && checaAbrigo(6) && checaCasa(6)) {
    			j.getPeao(num_corr).setMd(jogador_num, 0);
		    }else {
	    		j.setNumPeao(num_corr);
	    		removePeaoCaminho();
		    }
		}
    }

    private Boolean checaBarreira(int mov) throws BadLocationException, FileNotFoundException{
		int pos_corr = j.getPeao(j.getNumPeao()).getLst().pos;
		int i = 1;
		int caminho_barreira_x, caminho_barreira_y;
		boolean flag_barreira = true;

		// move o peao pela lista, checando casa a casa se existe barreiras no caminho
		while(i < (mov +1) && flag_barreira){

			j.getPeao(j.getNumPeao()).getProx();

			v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

			caminho_barreira_x = v.RetornaX();
			caminho_barreira_y = v.RetornaY();
			
			//Checa se existe peoes da mesma cor na mesma casa
			if(!((j.getX() == defineXFinal(jogador_num) && j.getY() == defineYFinal(jogador_num)))) {
				if (Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o1 != null && Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o2 != null) {
					if(Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o1.getP1().ExibeCor() == Jogo.getJogo().getCaminho(caminho_barreira_x, caminho_barreira_y).o2.getP1().ExibeCor()) {
						flag_barreira = false;
					}
				}	
			}

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
			adicionaPeaoCaminho();
			return false;
		}

    }

	private void adicionaPeaoCaminho() throws FileNotFoundException, BadLocationException{
			v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

			novo_x = v.RetornaX();
			novo_y = v.RetornaY();

			j.SetP1X(novo_x);
			j.SetP1Y(novo_y);

			Jogo.getJogo().getCaminho(novo_x, novo_y).AdicionaPeao(j.getPeao(j.getNumPeao()), j);
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
    		if(Jogo.getJogo().getCaminho(caminho_abrigo_x, caminho_abrigo_y).pode) {
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
			adicionaPeaoCaminho();
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

    private void peaoNoFinal() throws FileNotFoundException, BadLocationException{
    	JogadoresController.getJogadoresController().setCont(jogador_num,(1+JogadoresController.getJogadoresController().getCont(jogador_num)));
    	
    	// Reiniciando as variavies para novo peao
    	JogadoresController.getJogadoresController().setM(false);
		TextAreaLog.getTextAreaLog().printLog("Selecione um peao e ande 6!");
		flag_malandra = true;
    }

    private void defineVencedor(int turno) throws FileNotFoundException, BadLocationException {
    	switch (turno) {
    	case 0:
    		TextAreaLog.getTextAreaLog().printLog("Jogador vermelho venceu!");
    		defineOutrasColocacoes(turno);
    		break;
        case 1:
        	TextAreaLog.getTextAreaLog().printLog("Jogador verde venceu!");
    		defineOutrasColocacoes(turno);
    		break;
        case 2:
        	TextAreaLog.getTextAreaLog().printLog("Jogador amarelo venceu!");
    		defineOutrasColocacoes(turno);
    		break;
        case 3:
        	TextAreaLog.getTextAreaLog().printLog("Jogador azul venceu!");
    		defineOutrasColocacoes(turno);
    		break;
    	}
    }

	private void defineOutrasColocacoes(int turno) throws FileNotFoundException, BadLocationException {
		int distancia_segundo,distancia_terceiro,distancia_quarto;
		int jogador_segundo,jogador_terceiro,jogador_quarto;

		jogador_segundo = jogador_terceiro = jogador_quarto = 0;
		distancia_segundo = distancia_terceiro = distancia_quarto = 0;

		for(int i = 0; i < 4; i++) {
			if(i != turno) {
				Jogador j_outra_colocacao = JogadoresController.getJogadoresController().getJogador(i);
				int distancia = 0;

				for(int j = 0; j < 4; j++) {
					distancia += j_outra_colocacao.getPeao(j).getPos();
				}

				if(distancia > distancia_segundo) {
					distancia_quarto = distancia_terceiro;
					distancia_terceiro = distancia_segundo;
					distancia_segundo = distancia;

					jogador_quarto = jogador_terceiro;
					jogador_terceiro = jogador_segundo;
					jogador_segundo = i + 1;
				}else if(distancia > distancia_terceiro) {
					distancia_quarto = distancia_terceiro;
					distancia_terceiro = distancia;

					jogador_quarto = jogador_terceiro;
					jogador_terceiro = i + 1;
				}else {
					distancia_quarto = distancia;

					jogador_quarto = i + 1;
				}
			}
		}
    	TextAreaLog.getTextAreaLog().printLog("Jogador "+PegaNomeCor(jogador_segundo)+" ficou em segundo!");
    	TextAreaLog.getTextAreaLog().printLog("Jogador "+PegaNomeCor(jogador_terceiro)+" ficou em terceiro!");
    	TextAreaLog.getTextAreaLog().printLog("Jogador "+PegaNomeCor(jogador_quarto)+" ficou em quarto!");
	}

    private void checaFinalAntes() throws FileNotFoundException, BadLocationException{
    	if(jogador_num == 1)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getYFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY());
		else if(jogador_num == 2)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getXFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX());
		else if(jogador_num == 3)
			j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY() - j.getYFinal());
		else
			j.getPeao(j.getNumPeao()).setFim(jogador_num, ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaX() - j.getXFinal());

		if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num)) {
			j.getPeao(j.getNumPeao()).setY(jogador_num, true);
		}
		else {
			adicionaPeaoCaminho();
			j.getPeao(j.getNumPeao()).setY(jogador_num, false);

		}
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

    private String PegaNomeCor (int jogador){
    	switch (jogador) {
        case 1:
        	return "Vermelho";
        case 2:
        	return "Verde";
        case 3:
        	return "Amarelo";
        case 4:
        	return "Azul";
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

    private int defineXCasaInicial (int jogador){
    	switch (jogador) {
        case 1:
        	return 6;
        case 2:
        	return 1;
        case 3:
        	return 8;
        case 4:
        	return 13;
    	}
    	return 0;
    }

    private int defineYCasaInicial (int jogador){
    	switch (jogador) {
        case 1:
        	return 1;
        case 2:
        	return 8;
        case 3:
        	return 13;
        case 4:
        	return 6;
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

	private boolean checaPossibilidadeSair() throws BadLocationException{
		if(Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1 == null && Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o2 == null){
			return true;
		}else if(Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o1.getP1().ExibeCor() != corCasaInicial(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)) && Jogo.getJogo().getCaminho(defineXCasaInicial(jogador_num), defineYCasaInicial(jogador_num)).o2 == null){
			return true;
		}else{
			// Caso voce erroneamente selecione um peao que nao esta em jogo
			if(j.getPeao(j.getNumPeao()).getCinco(jogador_num) == false) {
				int y = 0;
	    		// Encontra um peao que foi usado
	    		while (y < 4 && j.getPeao(y).getCinco(jogador_num) == false) {
	    			y++;
	    		}

	    		if(y < 4) {
	        		j.setNumPeao(y);
	    		}
			}
			return false;
		}
	}

	private boolean checaCasa(int mov) throws BadLocationException, FileNotFoundException{
		int pos_corr = j.getPeao(j.getNumPeao()).getPos();
		for (int i = 1; i < mov + 1 ; i++) {
			j.getPeao(j.getNumPeao()).getProx();
		}

		v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

		int casa_x = v.RetornaX();
		int casa_y = v.RetornaY();
		
    	// Adiciona na posicao anterior a checagem
    	j.getPeao(j.getNumPeao()).getLst().posIni();
    	movePeao(pos_corr);
    	removePeaoCaminho();

		if(Jogo.getJogo().getCaminho(casa_x, casa_y).o1 != null && Jogo.getJogo().getCaminho(casa_x, casa_y).o2 != null){
			adicionaPeaoCaminho();
        	TextAreaLog.getTextAreaLog().printLog("Barreira na casa!");
			return false;
		}

		return true;
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
