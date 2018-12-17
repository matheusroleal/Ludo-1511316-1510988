package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import org.json.JSONException;

import controller.*;
import model.*;
import view.*;

public class Jogo extends JFrame implements ObservadoIF{
	private static Jogo xframe = null;
	public Dado d;
	Salvar s;
	Carregar c;
	BotaoTeste bt1, bt2, bt3, bt4, bt5, bt6;
	public JButton vm1, vm2, vm3, vm4, vd1, vd2, vd3, vd4, am1, am2, am3, am4, az1, az2, az3, az4;
	JButton iniciar;
	ObservadorIF obs;
	public int numPeao;
	public Caminho[][] jogadores_na_casa = new Caminho[15][15];


	private Jogo() throws BadLocationException {
		try {

			TextAreaLog.getTextAreaLog().printLog("Jogadores, por favor, escolham seus peoes.");

			Tabuleiro t = new Tabuleiro();

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
			
			//botoes vermelhos
			vm1 = new JButton();
			vm2 = new JButton();
			vm3 = new JButton();
			vm4 = new JButton();

			vm1.setName("vm1");
			vm1.setBorder(null);
			vm1.setLocation(1*50, 4*50);
			vm1.setSize(50,50);
			vm1.setContentAreaFilled(false);
			vm2.setName("vm2");
			vm2.setBorder(null);
			vm2.setLocation(4*50, 4*50);
			vm2.setSize(50,50);
			vm2.setContentAreaFilled(false);
			vm3.setName("vm3");
			vm3.setBorder(null);
			vm3.setLocation(1*50, 1*50);
			vm3.setSize(50,50);
			vm3.setContentAreaFilled(false);
			vm4.setName("vm4");
			vm4.setBorder(null);
			vm4.setLocation(4*50, 1*50);
			vm4.setSize(50,50);
			vm4.setContentAreaFilled(false);

			this.getContentPane().add(vm1);
			this.getContentPane().add(vm2);
			this.getContentPane().add(vm3);
			this.getContentPane().add(vm4);

			vm1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j1, 0);
						JogadoresController.getJogadoresController().setCont(1,(1+JogadoresController.getJogadoresController().getCont(1)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					d.dado_btn.setEnabled(true);
				}
			});

			vm2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras(null).inicio(j1, 1);
						JogadoresController.getJogadoresController().setCont(1,(1+JogadoresController.getJogadoresController().getCont(1)));
						notifyObservador(e);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			vm3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras(null).inicio(j1, 2);
						JogadoresController.getJogadoresController().setCont(1,(1+JogadoresController.getJogadoresController().getCont(1)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			vm4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j1, 3);
						JogadoresController.getJogadoresController().setCont(1,(1+JogadoresController.getJogadoresController().getCont(1)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			//botoes verdes
			vd1 = new JButton();
			vd2 = new JButton();
			vd3 = new JButton();
			vd4 = new JButton();

			vd1.setName("vd1");
			vd1.setBorder(null);
			vd1.setLocation(10*50, 1*50);
			vd1.setSize(50,50);
			vd1.setContentAreaFilled(false);
			vd2.setName("vd2");
			vd2.setBorder(null);
			vd2.setLocation(13*50, 1*50);
			vd2.setSize(50,50);
			vd2.setContentAreaFilled(false);
			vd3.setName("vd3");
			vd3.setBorder(null);
			vd3.setLocation(10*50, 4*50);
			vd3.setSize(50,50);
			vd3.setContentAreaFilled(false);
			vd4.setName("vd4");
			vd4.setBorder(null);
			vd4.setLocation(13*50, 4*50);
			vd4.setSize(50,50);
			vd4.setContentAreaFilled(false);

			this.getContentPane().add(vd1);
			this.getContentPane().add(vd2);
			this.getContentPane().add(vd3);
			this.getContentPane().add(vd4);

			vd1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j2, 0);
						JogadoresController.getJogadoresController().setCont(2,(1+JogadoresController.getJogadoresController().getCont(2)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					d.dado_btn.setEnabled(true);
				}
			});

			vd2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j2, 1);
						JogadoresController.getJogadoresController().setCont(2,(1+JogadoresController.getJogadoresController().getCont(2)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			vd3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j2, 2);
						JogadoresController.getJogadoresController().setCont(2,(1+JogadoresController.getJogadoresController().getCont(2)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			vd4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j2, 3);
						JogadoresController.getJogadoresController().setCont(2,(1+JogadoresController.getJogadoresController().getCont(2)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			//botoes amarelos
			am1 = new JButton();
			am2 = new JButton();
			am3 = new JButton();
			am4 = new JButton();

			am1.setName("am1");
			am1.setBorder(null);
			am1.setLocation(13*50, 10*50);
			am1.setSize(50,50);
			am1.setContentAreaFilled(false);
			am2.setName("am2");
			am2.setBorder(null);
			am2.setLocation(10*50, 10*50);
			am2.setSize(50,50);
			am2.setContentAreaFilled(false);
			am3.setName("am3");
			am3.setBorder(null);
			am3.setLocation(13*50, 13*50);
			am3.setSize(50,50);
			am3.setContentAreaFilled(false);
			am4.setName("am4");
			am4.setBorder(null);
			am4.setLocation(10*50, 13*50);
			am4.setSize(50,50);
			am4.setContentAreaFilled(false);

			this.getContentPane().add(am1);
			this.getContentPane().add(am2);
			this.getContentPane().add(am3);
			this.getContentPane().add(am4);

			am1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j3, 0);
						JogadoresController.getJogadoresController().setCont(3,(1+JogadoresController.getJogadoresController().getCont(3)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					d.dado_btn.setEnabled(true);
				}
			});

			am2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j3, 1);
						JogadoresController.getJogadoresController().setCont(3,(1+JogadoresController.getJogadoresController().getCont(3)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			am3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j3, 2);
						JogadoresController.getJogadoresController().setCont(3,(1+JogadoresController.getJogadoresController().getCont(3)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			am4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j3, 3);
						JogadoresController.getJogadoresController().setCont(3,(1+JogadoresController.getJogadoresController().getCont(3)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			//botoes azuis
			az1 = new JButton();
			az2 = new JButton();
			az3 = new JButton();
			az4 = new JButton();

			az1.setName("az1");
			az1.setBorder(null);
			az1.setLocation(4*50, 13*50);
			az1.setSize(50,50);
			az1.setContentAreaFilled(false);
			az2.setName("az2");
			az2.setBorder(null);
			az2.setLocation(1*50, 13*50);
			az2.setSize(50,50);
			az2.setContentAreaFilled(false);
			az3.setName("az3");
			az3.setBorder(null);
			az3.setLocation(4*50, 10*50);
			az3.setSize(50,50);
			az3.setContentAreaFilled(false);
			az4.setName("az4");
			az4.setBorder(null);
			az4.setLocation(1*50, 10*50);
			az4.setSize(50,50);
			az4.setContentAreaFilled(false);

			this.getContentPane().add(az1);
			this.getContentPane().add(az2);
			this.getContentPane().add(az3);
			this.getContentPane().add(az4);

			az1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j4, 0);
						JogadoresController.getJogadoresController().setCont(4,(1+JogadoresController.getJogadoresController().getCont(4)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					d.dado_btn.setEnabled(true);
				}
			});

			az2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j4, 1);
						JogadoresController.getJogadoresController().setCont(4,(1+JogadoresController.getJogadoresController().getCont(4)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			az3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j4, 2);
						JogadoresController.getJogadoresController().setCont(4,(1+JogadoresController.getJogadoresController().getCont(4)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

			az4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Regras.getRegras(null).inicio(j4, 3);
						JogadoresController.getJogadoresController().setCont(4,(1+JogadoresController.getJogadoresController().getCont(4)));
						notifyObservador(e);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					d.dado_btn.setEnabled(true);
				}
			});

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

			d.dado_btn.setEnabled(true);

			d.dado_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras(null).AplicaRegras(d.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt1.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt2.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt3.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt4.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt5.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt6.GeraValor());
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

	@Override
	public void add(ObservadorIF o) {
		obs = o;
	}

	@Override
	public void remove(ObservadorIF o) {
		obs = null;
	}

	@Override
	public void notifyObservador(ActionEvent e) throws InterruptedException, FileNotFoundException, BadLocationException {
		obs.update(e);
	}

	public static Jogo getJogo() throws BadLocationException{
		if(xframe == null) {
			xframe = new Jogo();
		}
		return xframe;
	}

}
