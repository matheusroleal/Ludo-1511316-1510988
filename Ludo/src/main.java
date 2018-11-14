import javax.swing.*;
import lista.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame{
	
	Jogador j1 = new Jogador("vermelho");
	Regras r = new Regras();
	Tabuleiro t = new Tabuleiro();
	Dado d = new Dado();
	Salvar s = new Salvar();
	Carregar c = new Carregar();

	public main() {
		setBounds(0,0,960,790);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		DesenhaBotoesCores();
		
		DesenhaBotoesJogo();
		
		DesenhaJogadores();
		
		getContentPane().add(t);

		d.dado_btn.setEnabled(false);

		/*
		p.a = Color.GREEN;
		
		verde.addActionListener(new ActionListener() {
			public void	 actionPerformed(ActionEvent e) {
				verde.setEnabled(false);
				
				p1.a = Color.GREEN;
				
				p.x = 1;
				p.y = 10;
				p1.x = 1;
				p1.y = 8;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
		
		p.a = Color.BLUE;
		
		azul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				azul.setEnabled(false); 
				
				p1.a = Color.BLUE;
				
				p.x = 13;
				p.y = 4;
				p1.x = 13;
				p1.y = 6;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
		
		p.a = Color.YELLOW;
		
		amarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				amarelo.setEnabled(false); 
				
				p1.a = Color.YELLOW;
				
				p.x = 10;
				p.y = 13;
				p1.x = 8;
				p1.y = 13;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
		*/
		
		d.dado_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int movimento = d.GeraValor();
				
				r.inicio = true;

				if(r.regras(movimento) == true) {
					r.inicio=false;
					int m = d.GeraValor();
					
					for (int i = 1; i < m + 1 ; i++) {
						j1.peoes_do_jogador.elementAt(j1.num_peca).lst.prox();
					}
					
					Vetor testev =  (Vetor) j1.peoes_do_jogador.elementAt(j1.num_peca).lst.ShowIni();
	
					int novo_x = testev.RetornaX();
					int novo_y = testev.RetornaY();
					
					j1.peoes_do_jogador.elementAt(j1.num_peca).p1.x = novo_x;
					j1.peoes_do_jogador.elementAt(j1.num_peca).p1.y = novo_y;
					
					repaint();
				}
			}
		});

		s.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.SalvaJogo();
			}
		});

		c.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.CarregaJogo();
			}
		});
	}
	
	public void DesenhaBotoesJogo() {
		getContentPane().add(d.dado_btn);
		getContentPane().add(s.btn);
		getContentPane().add(c.btn);
	}
	
	public void DesenhaBotoesCores() {
		JButton vermelho = new JButton();
		vermelho.setLayout(null);
		vermelho.setLocation(0,0);
		vermelho.setSize(6*50, 6*50);
		vermelho.setContentAreaFilled(false);
	
		JButton verde = new JButton();
		verde.setLayout(null);
		verde.setLocation(50*9,0);
		verde.setSize(6*50, 6*50);
		verde.setContentAreaFilled(false);
		
		JButton amarelo = new JButton();
		amarelo.setLayout(null);
		amarelo.setLocation(50*9,50*9);
		amarelo.setSize(6*50, 6*50);
		amarelo.setContentAreaFilled(false);
		
		JButton azul = new JButton();
		azul.setLayout(null);
		azul.setLocation(0,50*9);
		azul.setSize(6*50, 6*50);
		azul.setContentAreaFilled(false);
		
		getContentPane().add(vermelho);
		getContentPane().add(verde);
		getContentPane().add(amarelo);
		getContentPane().add(azul);
		
		vermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vermelho.setEnabled(false); 
				j1.peoes_do_jogador.elementAt(j1.num_peca).p.x = 4;
				j1.peoes_do_jogador.elementAt(j1.num_peca).p.y = 4;
				j1.peoes_do_jogador.elementAt(j1.num_peca).p1.x = 6;
				j1.peoes_do_jogador.elementAt(j1.num_peca).p1.y = 1;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
	}
	
	public void DesenhaJogadores() {
		getContentPane().add(j1.peoes_do_jogador.elementAt(j1.num_peca).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(j1.num_peca).p);
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}
