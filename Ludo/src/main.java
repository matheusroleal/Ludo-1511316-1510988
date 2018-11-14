import javax.swing.*;
import lista.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class main extends JFrame{
	
	Regras r = new Regras();
	Tabuleiro t = new Tabuleiro();
	Dado d = new Dado();
	Salvar s = new Salvar();
	Carregar c = new Carregar();
	
	Vector<Jogador> Jogadores;
	int jogador_turno = 0;

	public main() {
		setBounds(0,0,960,790);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		CriaJogadores();
		
		DesenhaBotoesCores();
		
		DesenhaBotoesJogo();
		
		DesenhaJogadores();
		
		getContentPane().add(t);

		d.dado_btn.setEnabled(false);
		
		d.dado_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int movimento = d.GeraValor();
				Jogador j = Jogadores.elementAt(jogador_turno); 
				
				r.inicio = true;

				if(r.regras(movimento) == true) {
					r.inicio=false;
					int m = d.GeraValor();
					
					for (int i = 1; i < m + 1 ; i++) {
						j.peoes_do_jogador.elementAt(j.num_peca).lst.prox();
					}
					
					Vetor testev =  (Vetor) j.peoes_do_jogador.elementAt(j.num_peca).lst.ShowIni();
	
					int novo_x = testev.RetornaX();
					int novo_y = testev.RetornaY();
					
					j.peoes_do_jogador.elementAt(j.num_peca).p1.x = novo_x;
					j.peoes_do_jogador.elementAt(j.num_peca).p1.y = novo_y;
					
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
	
	public void CriaJogadores() {
		Jogadores = new Vector<>();
		Jogadores.insertElementAt(new Jogador("vermelho"), 0);
		Jogadores.insertElementAt(new Jogador("verde"), 1);
		Jogadores.insertElementAt(new Jogador("amarelo"), 2);
		Jogadores.insertElementAt(new Jogador("azul"), 3);
	}
	
	public void MudaTurno() {
		if(jogador_turno == 3) {
			jogador_turno = 0;
		}
		jogador_turno += 1;
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
				Jogador j = Jogadores.elementAt(jogador_turno); 

				vermelho.setEnabled(false); 
				j.peoes_do_jogador.elementAt(j.num_peca).p.x = 4;
				j.peoes_do_jogador.elementAt(j.num_peca).p.y = 4;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.x = 6;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.y = 1;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
				
		verde.addActionListener(new ActionListener() {
			public void	 actionPerformed(ActionEvent e) {
				Jogador j = Jogadores.elementAt(jogador_turno); 

				verde.setEnabled(false);
								
				j.peoes_do_jogador.elementAt(j.num_peca).p.x = 1;
				j.peoes_do_jogador.elementAt(j.num_peca).p.y = 10;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.x = 1;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.y = 8;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
				
		azul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jogador j = Jogadores.elementAt(jogador_turno); 

				azul.setEnabled(false); 
								
				j.peoes_do_jogador.elementAt(j.num_peca).p.x = 13;
				j.peoes_do_jogador.elementAt(j.num_peca).p.y = 4;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.x = 13;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.y = 6;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
				
		amarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jogador j = Jogadores.elementAt(jogador_turno); 

				amarelo.setEnabled(false); 
								
				j.peoes_do_jogador.elementAt(j.num_peca).p.x = 10;
				j.peoes_do_jogador.elementAt(j.num_peca).p.y = 13;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.x = 8;
				j.peoes_do_jogador.elementAt(j.num_peca).p1.y = 13;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
		
	}
	
	public void DesenhaJogadores() {
		Jogador j = Jogadores.elementAt(jogador_turno); 

		getContentPane().add(j.peoes_do_jogador.elementAt(j.num_peca).p1);
		getContentPane().add(j.peoes_do_jogador.elementAt(j.num_peca).p);
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}
