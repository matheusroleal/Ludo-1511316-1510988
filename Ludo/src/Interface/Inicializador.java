package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.JSONException;

import controller.*;
import model.*;
import view.*;

public class Inicializador extends JFrame{
	private static Inicializador xframe = null;
	public Dado d;
	Salvar s;
	Carregar c;
	
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
						Regras.getRegras(null).AplicaRegras();
					} catch (FileNotFoundException e1) {
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
			
//			JPanel ConsoleLog = new JPanel();
//			ConsoleLog.setLayout(null);
			TextAreaLog.getTextAreaLog().getTextArea().setBounds(755, 502, 200, 200);
			
//			ConsoleLog.add(TextAreaLog.getTextAreaLog().getTextArea());
			this.getContentPane().add(TextAreaLog.getTextAreaLog().getTextArea());
//			this.getContentPane().add(ConsoleLog);
			
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
