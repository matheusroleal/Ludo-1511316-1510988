import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lista.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class main extends JFrame{
	
	Regras r = new Regras();
	Tabuleiro t = new Tabuleiro();
	Dado d = new Dado();
	Salvar s = new Salvar();
	Carregar c = new Carregar();
	
	Vector<Jogador> Jogadores;
	int jogador_turno = 0;
	private boolean cinco1, cinco2, cinco3, cinco4;
	private boolean c1, c2, c3, c4;
	private boolean y1, y2, y3, y4;
	private int fim1, fim2, fim3, fim4;
	Vetor v1, v2, v3, v4;
	private int novo_x1, novo_y1, novo_x2, novo_y2, novo_x3, novo_y3, novo_x4, novo_y4;

	public main(){
		setBounds(0,0,960,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		CriaJogadores();
		
		DesenhaBotoesCores();
		
		DesenhaBotoesJogo();
		
		DesenhaJogadores();
		
		getContentPane().add(t);
		
		cinco1 = cinco2 = cinco3 = cinco4 = c1 = c2 = c3 = c4 = false;
		y1 = y2 = y3 = y4 = true;
		fim1 = fim2 = fim3 = fim4 = -1;

		d.dado_btn.setEnabled(false);
		
		d.dado_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int movimento = 0;
			
				Jogador j1 = Jogadores.elementAt(0);
				Jogador j2 = Jogadores.elementAt(1);
				Jogador j3 = Jogadores.elementAt(2);
				Jogador j4 = Jogadores.elementAt(3);
				
				if(jogador_turno == 0) {					
					if(cinco1 == false) {
						movimento = d.GeraValor();
						
						System.out.println("jogador: " + jogador_turno + " numero: " + movimento);
						
						if(movimento == 5) {
							j1.peoes_do_jogador.elementAt(j1.num_peao).p1.a = Color.RED;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x = 6;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y = 1;
							
							repaint();

							cinco1 = true;
							c1 = true;
						}
					}
				
					if(c1 == true) {
						movimento = d.GeraValor();
						
						if(fim1 != -1) {
							fim1 = j1.peoes_do_jogador.elementAt(j1.num_peao).y_final - ((Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr()).RetornaY();
							System.out.println("fim1 - (2): " + fim1);
							
							if((fim1 - movimento) == 0 || movimento < fim1) 
								y1 = true;
								
							else
								y1 = false;
						}
						
						if(y1 == true) {
							System.out.println("jogador: " + jogador_turno + " numero - (2): " + movimento);
							
							for (int i = 1; i < movimento + 1 ; i++) {
								j1.peoes_do_jogador.elementAt(j1.num_peao).lst.prox();
							}
							
							v1 =  (Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr();	
							
							novo_x1 = v1.RetornaX();
							novo_y1 = v1.RetornaY();
							
							j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x = novo_x1;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y = novo_y1;
						
							repaint();
						}	
						
						for(int i=0; i<6; i++) {
							if(novo_x1==7 && novo_y1==1+i){			
																
								fim1 = j1.peoes_do_jogador.elementAt(j1.num_peao).y_final - ((Vetor) j1.peoes_do_jogador.elementAt(j1.num_peao).lst.posCorr()).RetornaY();
								System.out.println("fim1: " + fim1);
								
								if((fim1 - movimento) == 0 || movimento < fim1) 
									y1 = true;
								else 
									y1 = false;
							}	
						}
						
					}
					
					if((j1.peoes_do_jogador.elementAt(j1.num_peao).p1.x == 7 && j1.peoes_do_jogador.elementAt(j1.num_peao).p1.y ==6) && j1.num_peao != 3) {
					
						j1.mudaPeao();
						
						cinco1 = false;
						c1 = false;
						fim1 = -1;
						y1 = true;
						
						j1.peoes_do_jogador.elementAt(j1.num_peao).p.a = Color.RED;
						j1.peoes_do_jogador.elementAt(j1.num_peao).p1.a = Color.RED;
						
						if(j1.num_peao == 1) {
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.x = 4;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.y = 1;
						}
						else if(j1.num_peao == 2) {
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.x = 1;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.y = 4;
						}
						else{
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.x = 1;
							j1.peoes_do_jogador.elementAt(j1.num_peao).p.y = 1;
						}	
						
						
					}
					
				}
				else if(jogador_turno == 1) {
					
					if(cinco2 == false) {
						movimento = d.GeraValor();
						
						System.out.println("jogador: " + jogador_turno + " numero: " + movimento);
						
						if(movimento == 5) {
							j2.peoes_do_jogador.elementAt(j2.num_peao).p1.a = Color.GREEN;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x = 1;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y = 8;
							
							repaint();
							
							cinco2 = true;
							c2 = true;
						}
					}
				
					if(c2 == true) {
						movimento = d.GeraValor();
						
						if(fim2 != -1) {
							fim2 = j2.peoes_do_jogador.elementAt(j2.num_peao).x_final - ((Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr()).RetornaX();
							System.out.println("fim2 - (2): " + fim2);
							
							if((fim2 - movimento) == 0 || movimento < fim2) 
								y2 = true;
							else
								y2 = false;
						}
						
						if(y2 == true) {
							System.out.println("jogador: " + jogador_turno + " numero - (2): " + movimento);
							
							for (int i = 1; i < movimento + 1 ; i++) 
								j2.peoes_do_jogador.elementAt(j2.num_peao).lst.prox();
							
							v2 =  (Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr();
							
							novo_x2 = v2.RetornaX();
							novo_y2 = v2.RetornaY();
							
							j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x = novo_x2;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y = novo_y2;
													
							repaint();

						}	
						
						for(int i=0; i<6; i++) {
							if(novo_y2==7 && novo_x2==1+i){			
																
								fim2 = j2.peoes_do_jogador.elementAt(j2.num_peao).x_final - ((Vetor) j2.peoes_do_jogador.elementAt(j2.num_peao).lst.posCorr()).RetornaX();
								System.out.println("fim2: " + fim2);
								
								if((fim2 - movimento) == 0 || movimento < fim2) 
									y2 = true;
								else 
									y2 = false;
							}	
						}
					}
					
					if((j2.peoes_do_jogador.elementAt(j2.num_peao).p1.x == 6 && j2.peoes_do_jogador.elementAt(j2.num_peao).p1.y ==7) && j2.num_peao != 3) {
						
						j2.mudaPeao();
					
						cinco2 = false;
						c2 = false;
						fim2 = -1;
						y2 = true;
				
						j2.peoes_do_jogador.elementAt(j2.num_peao).p.a = Color.GREEN;
						j2.peoes_do_jogador.elementAt(j2.num_peao).p1.a = Color.GREEN;
						
						if(j2.num_peao == 1) {
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.x = 1;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.y = 13;
						}
						else if(j2.num_peao == 2) {
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.x = 4;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.y = 10;
						}
						else if(j2.num_peao == 3) {
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.x = 4;
							j2.peoes_do_jogador.elementAt(j2.num_peao).p.y = 13;
						}					
					}	
				}
				else if(jogador_turno == 2) {
					if(cinco3 == false) {
						movimento = d.GeraValor();
						
						System.out.println("jogador: " + jogador_turno + " numero: " + movimento);
						
						if(movimento == 5) {
							j3.peoes_do_jogador.elementAt(j3.num_peao).p1.a = Color.YELLOW;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x = 8;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y = 13;
							
							repaint();
							
							cinco3 = true;
							c3 = true;
						}
					}
					
					if(c3 == true) {
						movimento = d.GeraValor();
						
						if(fim3 != -1) {
							fim3 = ((Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr()).RetornaY() - j3.peoes_do_jogador.elementAt(j3.num_peao).y_final;
							System.out.println("fim3 - (2): " + fim3);
							
							if((fim3 - movimento) == 0 || movimento < fim3) 
								y3 = true;
							else
								y3 = false;
						}
					
						if(y3 == true) {
							System.out.println("jogador: " + jogador_turno + " numero - (2): " + movimento);
							
							for (int i = 1; i < movimento + 1 ; i++)
								j3.peoes_do_jogador.elementAt(j3.num_peao).lst.prox();
							
							v3 =  (Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr();
							
							novo_x3 = v3.RetornaX();
							novo_y3 = v3.RetornaY();	
							
							j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x = novo_x3;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y = novo_y3;

							repaint();
						}	
						
						for(int i=0; i<6; i++) {
							if(novo_x3==7 && novo_y3==8+i){
								
								fim3 = ((Vetor) j3.peoes_do_jogador.elementAt(j3.num_peao).lst.posCorr()).RetornaY() - j3.peoes_do_jogador.elementAt(j3.num_peao).y_final;
								System.out.println("fim3 - (2): " + fim3);
								
								if((fim3 - movimento) == 0 || movimento < fim3) 
									y3 = true;
								else
									y3 = false;
							}	
						}
					}
					
					if((j3.peoes_do_jogador.elementAt(j3.num_peao).p1.x == 7 && j3.peoes_do_jogador.elementAt(j3.num_peao).p1.y ==8) && j3.num_peao != 3) {
						
						j3.mudaPeao();
					
						cinco3 = false;
						c3 = false;
						fim3 = -1;
						y3 = true;
				
						j3.peoes_do_jogador.elementAt(j3.num_peao).p.a = Color.YELLOW;
						j3.peoes_do_jogador.elementAt(j3.num_peao).p1.a = Color.YELLOW;
						
						if(j3.num_peao == 1) {
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.x = 10;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.y = 10;
						}
						else if(j3.num_peao == 2) {
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.x = 13;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.y = 13;
						}
						else if(j3.num_peao == 3) {
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.x = 13;
							j3.peoes_do_jogador.elementAt(j3.num_peao).p.y = 10;
						}					
					}
					
				}
				else {
					if(cinco4 == false) {
						movimento = d.GeraValor();
						
						System.out.println("jogador: " + jogador_turno + " numero: " + movimento);
						
						if(movimento == 5) {
							j4.peoes_do_jogador.elementAt(j4.num_peao).p1.a = Color.BLUE;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x = 13;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y = 6;
							
							repaint();
							
							cinco4 = true;
							c4 = true;
						}
					}
					
					if(c4 == true) {
						movimento = d.GeraValor();
						
						if(fim4 != -1) {
							fim4 = ((Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr()).RetornaX() - j4.peoes_do_jogador.elementAt(j4.num_peao).x_final;
							System.out.println("fim4 - (2): " + fim4);
							
							if((fim4 - movimento) == 0 || movimento < fim4) 
								y4 = true;
							else
								y4 = false;
						}
						
						if(y4 == true) {
							System.out.println("jogador: " + jogador_turno + " numero - (2): " + movimento);
							
							for (int i = 1; i < movimento + 1 ; i++)  
								j4.peoes_do_jogador.elementAt(j4.num_peao).lst.prox();
							
							v4 =  (Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr();
							
							novo_x4 = v4.RetornaX();
							novo_y4 = v4.RetornaY();
							
							j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x = novo_x4;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y = novo_y4;
							
							repaint();
						}

						for(int i=0; i<6; i++) {
							if(novo_y4==7 && novo_x4==8+i){

								fim4 = ((Vetor) j4.peoes_do_jogador.elementAt(j4.num_peao).lst.posCorr()).RetornaX() - j4.peoes_do_jogador.elementAt(j4.num_peao).x_final;
								System.out.println("fim4 - (2): " + fim4);
								
								if((fim4 - movimento) == 0 || movimento < fim4) 
									y4 = true;
								else
									y4 = false;
							}	
						}	
					
					}
					
					if((j4.peoes_do_jogador.elementAt(j4.num_peao).p1.x == 8 && j4.peoes_do_jogador.elementAt(j4.num_peao).p1.y ==7) && j4.num_peao != 3) {
						
						j4.mudaPeao();
					
						cinco4 = false;
						c4 = false;
						fim4 = -1;
						y4 = true;
				
						j4.peoes_do_jogador.elementAt(j4.num_peao).p.a = Color.BLUE;
						j4.peoes_do_jogador.elementAt(j4.num_peao).p1.a = Color.BLUE;
						
						if(j4.num_peao == 1) {
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.x = 13;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.y = 1;
						}
						else if(j4.num_peao == 2) {
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.x = 10;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.y = 4;
						}
						else if(j4.num_peao == 3) {
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.x = 10;
							j4.peoes_do_jogador.elementAt(j4.num_peao).p.y = 1;
						}					
					}
					
				}
				
				MudaTurno();	
			}
		});

		s.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.SalvaJogo(jogador_turno,Jogadores,c1,c2,c3,c4,fim1,fim2,fim3,fim4,y1,y2,y3,y4,cinco1,cinco2,cinco3,cinco4);
			}
		});

		c.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogador_turno = c.CarregaTurno();
					
					c1 = c.CarregaDado("c1");
					c2 = c.CarregaDado("c2");
					c3 = c.CarregaDado("c3");
					c4 = c.CarregaDado("c4");
					
					fim1 = c.CarregaFim("fim1");
					fim2 = c.CarregaFim("fim2");
					fim3 = c.CarregaFim("fim3");
					fim4 = c.CarregaFim("fim4");
					
					cinco1 = c.CarregaDado("cinco1");
					cinco2 = c.CarregaDado("cinco2");
					cinco3 = c.CarregaDado("cinco3");
					cinco4 = c.CarregaDado("cinco4");
					
					y1 = c.CarregaDado("y1");
					y2 = c.CarregaDado("y2");
					y3 = c.CarregaDado("y3");
					y4 = c.CarregaDado("y4");
					
					CarregaJogadores();

				} catch (JSONException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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

		getContentPane().add(j4.peoes_do_jogador.elementAt(3).p1);
		getContentPane().add(j4.peoes_do_jogador.elementAt(3).p);
		getContentPane().add(j4.peoes_do_jogador.elementAt(2).p1);
		getContentPane().add(j4.peoes_do_jogador.elementAt(2).p);
		getContentPane().add(j4.peoes_do_jogador.elementAt(1).p1);
		getContentPane().add(j4.peoes_do_jogador.elementAt(1).p);
		getContentPane().add(j4.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j4.peoes_do_jogador.elementAt(0).p);
		
		getContentPane().add(j3.peoes_do_jogador.elementAt(3).p1);
		getContentPane().add(j3.peoes_do_jogador.elementAt(3).p);
		getContentPane().add(j3.peoes_do_jogador.elementAt(2).p1);
		getContentPane().add(j3.peoes_do_jogador.elementAt(2).p);
		getContentPane().add(j3.peoes_do_jogador.elementAt(1).p1);
		getContentPane().add(j3.peoes_do_jogador.elementAt(1).p);
		getContentPane().add(j3.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j3.peoes_do_jogador.elementAt(0).p);
		
		getContentPane().add(j2.peoes_do_jogador.elementAt(3).p1);
		getContentPane().add(j2.peoes_do_jogador.elementAt(3).p);
		getContentPane().add(j2.peoes_do_jogador.elementAt(2).p1);
		getContentPane().add(j2.peoes_do_jogador.elementAt(2).p);
		getContentPane().add(j2.peoes_do_jogador.elementAt(1).p1);
		getContentPane().add(j2.peoes_do_jogador.elementAt(1).p);
		getContentPane().add(j2.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j2.peoes_do_jogador.elementAt(0).p);
		
		getContentPane().add(j1.peoes_do_jogador.elementAt(3).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(3).p);
		getContentPane().add(j1.peoes_do_jogador.elementAt(2).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(2).p);
		getContentPane().add(j1.peoes_do_jogador.elementAt(1).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(1).p);
		getContentPane().add(j1.peoes_do_jogador.elementAt(0).p1);
		getContentPane().add(j1.peoes_do_jogador.elementAt(0).p);
	}

	public void CarregaJogadores() throws IOException, JSONException {
		Vector<Color> cores = new Vector<>();
	    JSONObject jsonObject;
	    Jogador j_nova_pos;
	    
		cores.insertElementAt(Color.RED, 0);
		cores.insertElementAt(Color.GREEN, 1);
		cores.insertElementAt(Color.YELLOW, 2);
		cores.insertElementAt(Color.BLUE, 3);
		
		String json_str = c.CarregaJogo();
		
		jsonObject = new JSONObject(json_str);
		
	    for(int x = 0; x < 4; x++) {
    	    JSONArray jsonArray = new JSONArray(jsonObject.getString("j"+x));
 
	    	j_nova_pos = Jogadores.elementAt(x);
    	    
	    	for(int y = 0; y < 4; y++) {
	    		int nova_pos = (int)jsonArray.get(y);
	    		if(nova_pos > 0) {
		    		j_nova_pos.peoes_do_jogador.elementAt(y).lst.posIni();
		    		
					for (int i = 1; i < nova_pos + 1 ; i++) {
						j_nova_pos.peoes_do_jogador.elementAt(y).lst.prox();
					}
					
					Vetor v = (Vetor) j_nova_pos.peoes_do_jogador.elementAt(y).lst.posCorr();
					
					int novo_x = v.RetornaX();
					int novo_y = v.RetornaY();
					
					j_nova_pos.peoes_do_jogador.elementAt(y).p1.a = cores.elementAt(x);
					j_nova_pos.peoes_do_jogador.elementAt(y).p1.x = novo_x;
					j_nova_pos.peoes_do_jogador.elementAt(y).p1.y = novo_y;
					
					repaint();	
	    		}
	    	}
	    }
	    
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}
