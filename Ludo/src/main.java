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
	boolean cinco, c1, c2, c3, c4;
	boolean fim1, fim2, fim3, fim4;
	boolean sim;
	int fim;
	Vetor v1, v2, v3, v4;
	int novo_x1, novo_y1, novo_x2, novo_y2, novo_x3, novo_y3, novo_x4, novo_y4;

	public main() {
		setBounds(0,0,960,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		CriaJogadores();
		
		DesenhaBotoesCores();
		
		DesenhaBotoesJogo();
		
		DesenhaJogadores();
		
		getContentPane().add(t);
		
		r.inicio = true;
		cinco = false;
		c1 = false;
		c2 = false;
		c3 = false;
		c4 = false;
		fim1 = false;
		fim2 = false;
		fim3 = false;
		fim4 = false;

		d.dado_btn.setEnabled(false);
		
		d.dado_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int movimento = d.GeraValor();
				
				System.out.println("Jogador " + jogador_turno + "Tirou: " + movimento);
				Jogador j1 = Jogadores.elementAt(0);
				Jogador j2 = Jogadores.elementAt(1);
				Jogador j3 = Jogadores.elementAt(2);
				Jogador j4 = Jogadores.elementAt(3);

				if(r.regras(movimento) == true && cinco == false) {
					if(jogador_turno == 0) {
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.a = Color.RED;
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x = 6;
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y = 1;
						c1 = true;
					}
					else if(jogador_turno == 1) {	
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.a = Color.GREEN;
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x = 1;
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y = 8;
						c2 = true;
					}
					else if(jogador_turno == 2) {
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.a = Color.YELLOW;
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x = 8;
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y = 13;
						c3 = true;
					}
					else {
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.a = Color.BLUE;
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x = 13;
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y = 6;
						c4 = true;
					}
				
					repaint();
				}
				
				if(c1 == true && jogador_turno == 0) {		
//					movimento = d.GeraValor();
					System.out.println("Peao " + j1.num_peao + "c1 :" + movimento);
					
					if(movimento == 6)
						sim = false;
					else
						sim = true; 
					
					for (int i = 1; i < movimento + 1 ; i++) {
						j1.peoes_do_jogador.elementAt(j1.num_peao).lst.prox();
					}
					
					v1 =  (Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr();	
					
					novo_x1 = v1.RetornaX();
					novo_y1 = v1.RetornaY();
					v1.AdicionaPeca(jogador_turno);
					
					j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x = novo_x1;
					j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y = novo_y1;
					
					for(int i=0; i<6; i++) {
						if(novo_x1==7 && novo_y1==1+i){
							c1 = false;
							fim1 = true;
						}	
					}			

					repaint();
				}
				else if(c2 == true && jogador_turno == 1) {
//					movimento = d.GeraValor();
					System.out.println("Peao " + j2.num_peao + "c2 :" + movimento);
					
					if(movimento == 6)
						sim = false;
					else
						sim = true; 
					
					for (int i = 1; i < movimento + 1 ; i++) 
						j2.peoes_do_jogador.elementAt(j2.num_peao).lst.prox();
					
					v2 =  (Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr();
					v2.AdicionaPeca(jogador_turno);
					
					novo_x2 = v2.RetornaX();
					novo_y2 = v2.RetornaY();
					
					j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x = novo_x2;
					j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y = novo_y2;
					
					for(int i=0; i<6; i++) {
						if(novo_y2==7 && novo_x2==1+i){
							c2 = false;
							fim2 = true;
						}	
					}	
					
					repaint();
				}
				else if(c3 == true && jogador_turno == 2) {
//					movimento = d.GeraValor();
					System.out.println("Peao " + j3.num_peao + "c3 :" + movimento);
					
					if(movimento == 6)
						sim = false;
					else
						sim = true; 
					
					for (int i = 1; i < movimento + 1 ; i++)
						j3.peoes_do_jogador.elementAt(j3.num_peao).lst.prox();
					
					v3 =  (Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr();	
					v3.AdicionaPeca(jogador_turno);
					
					novo_x3 = v3.RetornaX();
					novo_y3 = v3.RetornaY();
					
					j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x = novo_x3;
					j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y = novo_y3;
					
					for(int i=0; i<6; i++) {
						if(novo_x3==7 && novo_y3==8+i){
							c3 = false;
							fim3 = true;
						}	
					}	
					
					repaint();
				}
				else if(c4 == true && jogador_turno == 3){	
//					movimento = d.GeraValor();
					System.out.println("Peao " + j4.num_peao + "c4 :" + movimento);	
					
					if(movimento == 6)
						sim = false;
					else
						sim = true; 
					
					for (int i = 1; i < movimento + 1 ; i++)  
						j4.peoes_do_jogador.elementAt(j4.num_peao).lst.prox();
					
					v4 =  (Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr();
					v4.AdicionaPeca(jogador_turno);
					
					novo_x4 = v4.RetornaX();
					novo_y4 = v4.RetornaY();
					
					j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x = novo_x4;
					j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y = novo_y4;
					
					for(int i=0; i<6; i++) {
						if(novo_y4==7 && novo_x4==8+i){
							c4 = false;
							fim4 = true;
						}	
					}	
					
					repaint();
				}
										
				if(fim1 == true && jogador_turno == 0) {
					movimento = d.GeraValor();
					
					fim = j1.peoes_do_jogador.elementAt(j1.num_peao).y_final - ((Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr()).RetornaY();
					System.out.println("fim1: " + fim);

					if((fim - movimento) == 0 || movimento < fim) {
						for (int i = 1; i < movimento + 1 ; i++) {
							j1.peoes_do_jogador.elementAt(j1.num_peao).lst.prox();
						}
						
						v1 = (Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr();	
						
						novo_x1 = v1.RetornaX();
						novo_y1 = v1.RetornaY();
						
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x = novo_x1;
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y = novo_y1;
						
					}
					
					if(novo_x1 == j1.peoes_do_jogador.elementAt(j1.num_peao).x_final && novo_y1 == j1.peoes_do_jogador.elementAt(j1.num_peao).y_final) {
						int novo_peao; 
						
						fim1 = false;
						
						j1.mudaPeao(j1.num_peao+1);
									
						novo_peao = j1.num_peao;
						
						getContentPane().add(j1.peoes_do_jogador.elementAt(novo_peao).p1);
						getContentPane().add(j1.peoes_do_jogador.elementAt(novo_peao).p);
					}
					
					repaint();
				}
				
				if(fim2 == true && jogador_turno == 1) {
					movimento = d.GeraValor();
					
					fim = j2.peoes_do_jogador.elementAt(j2.num_peao).x_final - ((Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr()).RetornaX();
					System.out.println("fim2: " + fim);
					
					if((fim - movimento) == 0 || movimento < fim) {
						for (int i = 1; i < movimento + 1 ; i++) {
							j2.peoes_do_jogador.elementAt(j2.num_peao).lst.prox();
						}
						
						v2 = (Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr();
						
						novo_x2 = v2.RetornaX();
						novo_y2 = v2.RetornaY();
						
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x = novo_x2;
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y = novo_y2;
					}
					
					if(novo_x2 == j2.peoes_do_jogador.elementAt(j2.num_peao).x_final && novo_y2 == j2.peoes_do_jogador.elementAt(j2.num_peao).y_final) {
						int novo_peao; 
						
						fim2 = false;
						
						j4.mudaPeao(j2.num_peao+1);
									
						novo_peao = j2.num_peao;
						
						getContentPane().add(j2.peoes_do_jogador.elementAt(novo_peao).p1);
						getContentPane().add(j2.peoes_do_jogador.elementAt(novo_peao).p);
					}
					
					repaint();
				}
				
				if(fim3 == true && jogador_turno == 2) {
					movimento = d.GeraValor();
					
					fim = ((Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr()).RetornaY() - j3.peoes_do_jogador.elementAt(j3.num_peao).y_final;
					System.out.println("fim3: " + fim);
					
					if((fim - movimento) == 0 || movimento < fim) {
						for (int i = 1; i < movimento + 1 ; i++) {
							j3.peoes_do_jogador.elementAt(j3.num_peao).lst.prox();
						}
						
						v3 = (Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr();	
						
						novo_x3 = v3.RetornaX();
						novo_y3 = v3.RetornaY();
						
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x = novo_x3;
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y = novo_y3;
					}
					
					if(novo_x3 == j3.peoes_do_jogador.elementAt(j3.num_peao).x_final && novo_y3 == j3.peoes_do_jogador.elementAt(j3.num_peao).y_final) {
						int novo_peao; 
						
						fim3 = false;
						
						j3.mudaPeao(j3.num_peao+1);
									
						novo_peao = j3.num_peao;
						
						getContentPane().add(j3.peoes_do_jogador.elementAt(novo_peao).p1);
						getContentPane().add(j3.peoes_do_jogador.elementAt(novo_peao).p);
					}
					
					repaint();
				
				}
				
				if(fim4 == true && jogador_turno == 3) {
					movimento = d.GeraValor();
					
					fim = ((Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr()).RetornaX() - j4.peoes_do_jogador.elementAt(j4.num_peao).x_final;
					System.out.println("fim4: " + fim);
					
					if((fim - movimento) == 0 || movimento < fim) {
						for (int i = 1; i < movimento + 1 ; i++) {
							j4.peoes_do_jogador.elementAt(j4.num_peao).lst.prox();
						}
						
						v4 = (Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr();
						
						novo_x4 = v4.RetornaX();
						novo_y4 = v4.RetornaY();
						
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x = novo_x4;
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y = novo_y4;
						
					}
					
					if(novo_x4 == j4.peoes_do_jogador.elementAt(j4.num_peao).x_final && novo_y4 == j4.peoes_do_jogador.elementAt(j4.num_peao).y_final) {
						int novo_peao; 
						
						fim4 = false;
						
						j4.mudaPeao(j4.num_peao+1);
									
						novo_peao = j4.num_peao;
						
						getContentPane().add(j4.peoes_do_jogador.elementAt(novo_peao).p1);
						getContentPane().add(j4.peoes_do_jogador.elementAt(novo_peao).p);
					}
										
					repaint();
						
				}
				
				MudaTurno();
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
		jogador_turno += 1;
		
		if(jogador_turno == 4) {
			jogador_turno = 0;
		}
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
		
		/*
		if(jogador_turno == 0)
			getContentPane().add(vermelho);
		else if(jogador_turno == 1)
			getContentPane().add(verde);
		else if(jogador_turno == 2)
			getContentPane().add(amarelo);
		else
			getContentPane().add(azul);
		*/
		
		getContentPane().add(vermelho);
		getContentPane().add(verde);
		getContentPane().add(amarelo);
		getContentPane().add(azul);
		
		vermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jogador j1 = Jogadores.elementAt(0); 
				
				vermelho.setEnabled(false); 
				
				j1.peoes_do_jogador.elementAt(j1.num_peao).p.a = Color.RED;
				j1.peoes_do_jogador.elementAt(j1.num_peao).p.x = 4;
				j1.peoes_do_jogador.elementAt(j1.num_peao).p.y = 4;
				
				repaint();
				
				d.dado_btn.setEnabled(true);
			}
		});
				
		verde.addActionListener(new ActionListener() {
			public void	 actionPerformed(ActionEvent e) {
				Jogador j2 = Jogadores.elementAt(1); 

				verde.setEnabled(false);
				
				j2.peoes_do_jogador.elementAt(j2.num_peao).p.a = Color.GREEN;
				j2.peoes_do_jogador.elementAt(j2.num_peao).p.x = 1;
				j2.peoes_do_jogador.elementAt(j2.num_peao).p.y = 10;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
				
		azul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jogador j3 = Jogadores.elementAt(2); 

				azul.setEnabled(false); 
								
				j3.peoes_do_jogador.elementAt(j3.num_peao).p.a = Color.BLUE;
				j3.peoes_do_jogador.elementAt(j3.num_peao).p.x = 13;
				j3.peoes_do_jogador.elementAt(j3.num_peao).p.y = 4;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
				
		amarelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jogador j4 = Jogadores.elementAt(3); 

				amarelo.setEnabled(false); 
								
				j4.peoes_do_jogador.elementAt(j4.num_peao).p.a = Color.YELLOW;
				j4.peoes_do_jogador.elementAt(j4.num_peao).p.x = 10;
				j4.peoes_do_jogador.elementAt(j4.num_peao).p.y = 13;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
		
	}
	
	public void DesenhaJogadores() {
		Jogador j1 = Jogadores.elementAt(0); 
		Jogador j2 = Jogadores.elementAt(1);
		Jogador j3 = Jogadores.elementAt(2); 
		Jogador j4 = Jogadores.elementAt(3);

		getContentPane().add(j4.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j4.peoes_do_jogador.elementAt(0).p);
		getContentPane().add(j3.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j3.peoes_do_jogador.elementAt(0).p);
		getContentPane().add(j2.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j2.peoes_do_jogador.elementAt(0).p);
		getContentPane().add(j1.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(0).p);
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}
