package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

//import org.json.JSONException;

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
			
			Jogador j1 = JogadoresController.getJogadoresController().getJogador(0); 
			Jogador j2 = JogadoresController.getJogadoresController().getJogador(1);
			Jogador j3 = JogadoresController.getJogadoresController().getJogador(2); 
			Jogador j4 = JogadoresController.getJogadoresController().getJogador(3);
			
			//botoes vermelhos
			JButton vm1 = new JButton();
			JButton vm2 = new JButton();
			JButton vm3 = new JButton();
			JButton vm4 = new JButton();
			
			vm1.setBorder(null);
			vm1.setLocation(1*50, 4*50);
			vm1.setSize(50,50);
			vm1.setContentAreaFilled(false);
			vm2.setBorder(null);
			vm2.setLocation(4*50, 4*50);
			vm2.setSize(50,50);
			vm2.setContentAreaFilled(false);
			vm3.setBorder(null);
			vm3.setLocation(1*50, 1*50);
			vm3.setSize(50,50);
			vm3.setContentAreaFilled(false);
			
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
					j1.setNumPeao(0);
					
					d.dado_btn.setEnabled(true);
				}
			});
					
			vm2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					j1.setNumPeao(1);	
					
					d.dado_btn.setEnabled(true);
				}
			});
			
			vm3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					j1.setNumPeao(2);	
		
					d.dado_btn.setEnabled(true);
					
				}
			});
			
			vm4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					j1.setNumPeao(3);	
					
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
//					FluxoDados.getFluxoDados().SalvarPartida();
				}
			});
	
			c.btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					try {
//						FluxoDados.getFluxoDados().CarregarPartida();
//					} catch (IOException | JSONException e1) {
//						e1.printStackTrace();
//					}
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
						Regras.getRegras(null).AplicaRegras(bt3.GeraValor());
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
						Regras.getRegras(null).AplicaRegras(bt4.GeraValor());
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
