package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import org.json.JSONException;

import controller.*;
import model.*;
import view.*;

public class Inicializador extends JFrame{
	private static Inicializador xframe = null;
	public Dado d;
	Salvar s;
	Carregar c;
	BotaoTeste bt1, bt2, bt3, bt4, bt5, bt6;
	
	
	private Inicializador() {
		try {
		
			Tabuleiro t = new Tabuleiro();
			
			this.setBounds(0,0,960,800);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			JButton vermelho = new JButton();
			JButton verde = new JButton();
			JButton amarelo = new JButton();
			JButton azul = new JButton();
			
			vermelho.setLayout(null);
			vermelho.setLocation(0,0);
			vermelho.setSize(6*50, 6*50);
			vermelho.setContentAreaFilled(false);
		
			verde.setLayout(null);
			verde.setLocation(50*9,0);
			verde.setSize(6*50, 6*50);
			verde.setContentAreaFilled(false);
			
			amarelo.setLayout(null);
			amarelo.setLocation(50*9,50*9);
			amarelo.setSize(6*50, 6*50);
			amarelo.setContentAreaFilled(false);
			
			azul.setLayout(null);
			azul.setLocation(0,50*9);
			azul.setSize(6*50, 6*50);
			azul.setContentAreaFilled(false);
			
			this.getContentPane().add(vermelho);
			this.getContentPane().add(verde);
			this.getContentPane().add(amarelo);
			this.getContentPane().add(azul);
			
			vermelho.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Jogador j1 = JogadoresController.getJogadoresController().getJogador(0); 
					
					vermelho.setEnabled(false); 
					
					j1.SetPColor(Color.RED);
					j1.SetPX(4);
					j1.SetPY(4);
					
					repaint();
					
					 d.dado_btn.setEnabled(true);
				}
			});
					
			verde.addActionListener(new ActionListener() {
				public void	 actionPerformed(ActionEvent e) {
					Jogador j2 = JogadoresController.getJogadoresController().getJogador(1); 
	
					verde.setEnabled(false);
					
					j2.SetPColor(Color.GREEN);
					j2.SetPX(1);
					j2.SetPY(10);
					
					repaint();
					 d.dado_btn.setEnabled(true);
				}
			});
					
			azul.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Jogador j3 = JogadoresController.getJogadoresController().getJogador(2); 
	
					azul.setEnabled(false); 
									
					j3.SetPColor(Color.BLUE);
					j3.SetPX(13);
					j3.SetPY(4);
					
					repaint();
					 d.dado_btn.setEnabled(true);
				}
			});
					
			amarelo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Jogador j4 = JogadoresController.getJogadoresController().getJogador(3); 
	
					amarelo.setEnabled(false); 
									
					j4.SetPColor(Color.YELLOW);
					j4.SetPX(10);
					j4.SetPY(13);
					
					repaint();
					 d.dado_btn.setEnabled(true);
				}
			});
			
			
			Jogador j1 = JogadoresController.getJogadoresController().getJogador(0); 
			Jogador j2 = JogadoresController.getJogadoresController().getJogador(1);
			Jogador j3 = JogadoresController.getJogadoresController().getJogador(2); 
			Jogador j4 = JogadoresController.getJogadoresController().getJogador(3);
	
			this.getContentPane().add(j4.getPeao(3));
			this.getContentPane().add(j4.getBasePeao(3));
			this.getContentPane().add(j4.getPeao(2));
			this.getContentPane().add(j4.getBasePeao(2));
			this.getContentPane().add(j4.getPeao(1));
			this.getContentPane().add(j4.getBasePeao(1));
			this.getContentPane().add(j4.getPeao(0));
			this.getContentPane().add(j4.getBasePeao(0));
			
			this.getContentPane().add(j3.getPeao(3));
			this.getContentPane().add(j3.getBasePeao(3));
			this.getContentPane().add(j3.getPeao(2));
			this.getContentPane().add(j3.getBasePeao(2));
			this.getContentPane().add(j3.getPeao(1));
			this.getContentPane().add(j3.getBasePeao(1));
			this.getContentPane().add(j3.getPeao(0));
			this.getContentPane().add(j3.getBasePeao(0));
			
			this.getContentPane().add(j2.getPeao(3));
			this.getContentPane().add(j2.getBasePeao(3));
			this.getContentPane().add(j2.getPeao(2));
			this.getContentPane().add(j2.getBasePeao(2));
			this.getContentPane().add(j2.getPeao(1));
			this.getContentPane().add(j2.getBasePeao(1));
			this.getContentPane().add(j2.getPeao(0));
			this.getContentPane().add(j2.getBasePeao(0));
			
			this.getContentPane().add(j1.getPeao(3));
			this.getContentPane().add(j1.getBasePeao(3));
			this.getContentPane().add(j1.getPeao(2));
			this.getContentPane().add(j1.getBasePeao(2));
			this.getContentPane().add(j1.getPeao(1));
			this.getContentPane().add(j1.getBasePeao(1));
			this.getContentPane().add(j1.getPeao(0));
			this.getContentPane().add(j1.getBasePeao(0));
			
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
						Regras.getRegras(null).AplicaRegras(d.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
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
					} catch (IOException | JSONException e1) {
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
					}
				}
			});
			
			bt3.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras(null).AplicaRegras(bt2.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			bt4.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Regras.getRegras(null).AplicaRegras(bt3.GeraValor());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (BadLocationException e1) {
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
	
	public static Inicializador getInicializador() {
		if(xframe == null)
			xframe = new Inicializador();
		return xframe;
	}
}
