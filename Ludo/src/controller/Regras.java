package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.text.BadLocationException;

import Interface.Jogo;
import model.Jogador;
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
   
    private Regras() {
    	
    	movimento = 0;

    	j1 = JogadoresController.getJogadoresController().getJogador(0);
    	j2 = JogadoresController.getJogadoresController().getJogador(1);
    	j3 = JogadoresController.getJogadoresController().getJogador(2);
    	j4 = JogadoresController.getJogadoresController().getJogador(3);
    	
    }

    public void AplicaRegras(int mv) throws FileNotFoundException, BadLocationException, InterruptedException {

    	jogador_num = defineNumJogador(JogadoresController.getJogadoresController().getJogadorTurno());

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
    		v = (Vetor) j.getPeao(j.getNumPeao()).getPosCorr();

    		antigo_x = v.RetornaX();
    		antigo_y = v.RetornaY();

    		Jogo.getJogo().getCaminho(antigo_x, antigo_y).RemovePeao(j.getPeao(j.getNumPeao()), j);

    		// Se movimento for 6, jogador pode jogar novamente
    		checaSeis(movimento);

    		// Caso o peao esteja na reta final, verirficar se é possível mover o peao
    		checaFinal();

    		if(j.getPeao(j.getNumPeao()).getY(jogador_num) == true) {

    			// Move o peao de acordo com o valor do movimento
    			movePeao(movimento);
    		}

    		// Se o peao tiver chegado na casa final e nao for o ultimo, mudamos o peao do jogador para o seguinte
    		if((j.getX() == defineXFinal(jogador_num) && j.getY() == defineYFinal(jogador_num)) && JogadoresController.getJogadoresController().getCont(jogador_num) != 3) {
    			peaoNoFinal();
    		}

    	}

    	if(JogadoresController.getJogadoresController().getM() == true){
    		JogadoresController.getJogadoresController().MudaTurno();
    	}

    }

    private void checaSeis(int mov){
      if(mov == 6) {
        JogadoresController.getJogadoresController().setM(false);
        j.getPeao(j.getNumPeao()).setMd(jogador_num,1);

        if(j.getPeao(j.getNumPeao()).getMd(jogador_num) == 3) {
          if(novo_x != 7 && novo_y != 8 || novo_x != 7 && novo_y != 9 || novo_x != 7 && novo_y != 10 || novo_x != 7 && novo_y != 11 || novo_x != 7 && novo_y != 12 || novo_x != 7 && novo_y != 13){
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
     // Jogo.getJogo().getCaminho(novo_x, novo_y).numPeao = j.getNumPeao();

      obs.notify(1, this);
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

    private void checaFinal(){
      if(j.getPeao(j.getNumPeao()).getFim(jogador_num) != -1) {
        j.getPeao(j.getNumPeao()).setFim(jogador_num, j.getYFinal() - ((Vetor) j.getPeao(j.getNumPeao()).getPosCorr()).RetornaY());
        if((j.getPeao(j.getNumPeao()).getFim(jogador_num) - movimento) == 0 || movimento < j.getPeao(j.getNumPeao()).getFim(jogador_num)){
          j.getPeao(j.getNumPeao()).setY(jogador_num, true);
        }else{
          j.getPeao(j.getNumPeao()).setY(jogador_num, false);
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

    private int defineNumJogador(int turno){
    	switch (turno) {
    	case 0:
    		try {
    			TextAreaLog.getTextAreaLog().printLog("Vez do jogador vermelho!");
    			return turno + 1;
    		} catch (FileNotFoundException | BadLocationException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        case 1:
        	try {
        		TextAreaLog.getTextAreaLog().printLog("Vez do jogador verde!");
        		return turno + 1;
        	} catch (FileNotFoundException | BadLocationException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        case 2:
        	try {
        		TextAreaLog.getTextAreaLog().printLog("Vez do jogador amarelo!");
        		return turno + 1;
        	} catch (FileNotFoundException | BadLocationException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        case 3:
        	try {
        		TextAreaLog.getTextAreaLog().printLog("Vez do jogador azul!");
        		return turno + 1;
        	} catch (FileNotFoundException | BadLocationException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}
    	return turno + 1;
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

    public void add(Observador o) {
      obs = o;
    }

    public void remove(Observador o) {
      obs = null;
    }

    public static Regras getRegras() throws BadLocationException {
      if (instance == null) {
        instance = new Regras();
      }
      return instance;
    }
  }
