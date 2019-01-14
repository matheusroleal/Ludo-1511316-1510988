package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import org.json.JSONException;

import controller.*;
import listeners.TratadorMouse;
import model.*;
import view.*;

public class Jogo extends JFrame implements Observador{
	private static Jogo xframe = null;
	public Dado d;
	private Observado observado;
	public Caminho[][] jogadores_na_casa = new Caminho[15][15];
	Salvar s;
	Carregar c;
	BotaoTeste bt1, bt2, bt3, bt4, bt5, bt6;
	JButton iniciar;


	private Jogo() throws BadLocationException {
		try {
			Controlador.getControlador().registra(this);
			observado = Controlador.getObservado();

			Tabuleiro t = new Tabuleiro();
			t.addMouseListener(new TratadorMouse());
			
			this.setBounds(0,0,960,800);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);

			Jogador j1 = JogadoresController.getJogadoresController().getJogador(0);
			Jogador j2 = JogadoresController.getJogadoresController().getJogador(1);
			Jogador j3 = JogadoresController.getJogadoresController().getJogador(2);
			Jogador j4 = JogadoresController.getJogadoresController().getJogador(3);

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
			jogadores_na_casa[6][1].pode = true; //casa inicio vermelho
			jogadores_na_casa[1][8].pode = true; //casa inicio verde
			jogadores_na_casa[8][13].pode = true; //casa inicio amarela
			jogadores_na_casa[13][6].pode = true; //casa inicio azul
			
			jogadores_na_casa[4][1].o1 = j1.getPeao(0);
			jogadores_na_casa[4][1].jogadores.add(j1);
			jogadores_na_casa[4][4].o1 = j1.getPeao(1);
			jogadores_na_casa[4][4].jogadores.add(j1);
			jogadores_na_casa[1][1].o1 = j1.getPeao(2);
			jogadores_na_casa[1][1].jogadores.add(j1);
			jogadores_na_casa[1][4].o1 = j1.getPeao(3);
			jogadores_na_casa[1][4].jogadores.add(j1);
			
			jogadores_na_casa[1][10].o1 = j2.getPeao(0);
			jogadores_na_casa[1][10].jogadores.add(j2);
			jogadores_na_casa[1][13].o1 = j2.getPeao(1);
			jogadores_na_casa[1][13].jogadores.add(j2);
			jogadores_na_casa[4][10].o1 = j2.getPeao(2);
			jogadores_na_casa[4][10].jogadores.add(j2);
			jogadores_na_casa[4][13].o1 = j2.getPeao(3);
			jogadores_na_casa[4][13].jogadores.add(j2);
			
			jogadores_na_casa[10][13].o1 = j3.getPeao(0);
			jogadores_na_casa[10][13].jogadores.add(j3);
			jogadores_na_casa[10][10].o1 = j3.getPeao(1);
			jogadores_na_casa[10][10].jogadores.add(j3);
			jogadores_na_casa[13][13].o1 = j3.getPeao(2);
			jogadores_na_casa[13][13].jogadores.add(j3);
			jogadores_na_casa[13][10].o1 = j3.getPeao(3);
			jogadores_na_casa[13][10].jogadores.add(j3);
			
			jogadores_na_casa[13][4].o1 = j4.getPeao(0);
			jogadores_na_casa[13][4].jogadores.add(j4);
			jogadores_na_casa[13][1].o1 = j4.getPeao(1);
			jogadores_na_casa[13][1].jogadores.add(j4);
			jogadores_na_casa[10][4].o1 = j4.getPeao(2);
			jogadores_na_casa[10][4].jogadores.add(j4);
			jogadores_na_casa[10][1].o1 = j4.getPeao(3);
			jogadores_na_casa[10][1].jogadores.add(j4);
	
			this.getContentPane().add(j4.getPeca(3));
			this.getContentPane().add(j4.getBasePeca(3));
			this.getContentPane().add(j4.getPeca(2));
			this.getContentPane().add(j4.getBasePeca(2));
			this.getContentPane().add(j4.getPeca(1));
			this.getContentPane().add(j4.getBasePeca(1));
			this.getContentPane().add(j4.getPeca(0));
			this.getContentPane().add(j4.getBasePeca(0));

			this.getContentPane().add(j3.getPeca(3));
			this.getContentPane().add(j3.getBasePeca(3));
			this.getContentPane().add(j3.getPeca(2));
			this.getContentPane().add(j3.getBasePeca(2));
			this.getContentPane().add(j3.getPeca(1));
			this.getContentPane().add(j3.getBasePeca(1));
			this.getContentPane().add(j3.getPeca(0));
			this.getContentPane().add(j3.getBasePeca(0));

			this.getContentPane().add(j2.getPeca(3));
			this.getContentPane().add(j2.getBasePeca(3));
			this.getContentPane().add(j2.getPeca(2));
			this.getContentPane().add(j2.getBasePeca(2));
			this.getContentPane().add(j2.getPeca(1));
			this.getContentPane().add(j2.getBasePeca(1));
			this.getContentPane().add(j2.getPeca(0));
			this.getContentPane().add(j2.getBasePeca(0));

			this.getContentPane().add(j1.getPeca(3));
			this.getContentPane().add(j1.getBasePeca(3));
			this.getContentPane().add(j1.getPeca(2));
			this.getContentPane().add(j1.getBasePeca(2));
			this.getContentPane().add(j1.getPeca(1));
			this.getContentPane().add(j1.getBasePeca(1));
			this.getContentPane().add(j1.getPeca(0));
			this.getContentPane().add(j1.getBasePeca(0));

			d = new Dado();
			s = new Salvar();
			c = new Carregar();

			this.getContentPane().add(d.dado_btn);
			this.getContentPane().add(s.btn);
			this.getContentPane().add(c.btn);

			d.dado_btn.setEnabled(false);

			d.dado_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(d.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			s.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FluxoDados.getFluxoDados().SalvarPartida();
				}
			});

			c.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						FluxoDados.getFluxoDados().CarregarPartida();
						repaint();
					} catch (IOException | JSONException | BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt1 = new BotaoTeste("1",752, 402,1);
			bt2 = new BotaoTeste("2",817, 402,2);
			bt3 = new BotaoTeste("3",882, 402,3);
			bt4 = new BotaoTeste("4",752, 452,4);
			bt5 = new BotaoTeste("5",817, 452,5);
			bt6 = new BotaoTeste("6",882, 452,6);

			this.getContentPane().add(bt1.btn);
			this.getContentPane().add(bt2.btn);
			this.getContentPane().add(bt3.btn);
			this.getContentPane().add(bt4.btn);
			this.getContentPane().add(bt5.btn);
			this.getContentPane().add(bt6.btn);

			bt1.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt1.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt2.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt2.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt3.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt3.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt4.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt4.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt5.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt5.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			bt6.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras().AplicaRegras(bt6.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			TextAreaLog.getTextAreaLog().getTextArea().setBounds(755, 502, 200, 200);

			this.getContentPane().add(TextAreaLog.getTextAreaLog().getTextArea());

			this.getContentPane().add(t);
			
    		TextAreaLog.getTextAreaLog().printLog("Vez do jogador vermelho!");
  	      	TextAreaLog.getTextAreaLog().printLog("Selecione o peao antes de jogar!");

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public Caminho getCaminho(int i, int j) {
		return jogadores_na_casa[i][j];
	}

	public Peao getO1(int i, int j) {
		return jogadores_na_casa[i][j].o1;
	}

	public Peao getO2(int i, int j) {
		return jogadores_na_casa[i][j].o2;
	}
	
	public void notify(int i, Observado o){
		if (i == 1) {
			repaint();
		}else if(i == 2) {
			d.dado_btn.setEnabled(true);
		}else if(i == 3) {
			d.dado_btn.setEnabled(false);
		}
	}

	public static Jogo getJogo() throws BadLocationException{
		if(xframe == null) {
			xframe = new Jogo();
		}
		return xframe;
	}

}
