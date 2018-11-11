import javax.swing.*;
import lista.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{

	Lista lst = new Lista();

	public Main() {
		setBounds(0,0,960,790);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		PopulaLista();
		
		Regras r = new Regras();
		Tabuleiro t = new Tabuleiro();
		Dado d = new Dado();
		Salvar s = new Salvar();
		Carregar c = new Carregar();
		Peça p, p1;
		
		p = new Peça();
		p1 = new Peça();
		
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
		getContentPane().add(d.dado_btn);
		getContentPane().add(s.btn);
		getContentPane().add(c.btn);
		getContentPane().add(p1);
		getContentPane().add(p);
		getContentPane().add(t);

		p.a = Color.RED;
		p1.a = Color.RED;
		
		d.dado_btn.setEnabled(false);
		vermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vermelho.setEnabled(false); 
				p.x = 4;
				p.y = 4;
				p1.x = 6;
				p1.y = 1;
				
				repaint();
				d.dado_btn.setEnabled(true);
			}
		});
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
				int movimento = d.get_new_dado_value();
				System.out.println(movimento);
				
				r.inicio = true;

				if(r.regras(movimento) == true) {
					r.inicio=false;
					int m = d.get_new_dado_value();
					
					for (int i = 1; i < m + 1 ; i++) {
						lst.prox();
					}
					
					Vetor testev = (Vetor) lst.ShowIni();
	
					int novo_x = testev.show_pos_x();
					int novo_y = testev.show_pos_y();
					
					p1.x = novo_x;
					p1.y = novo_y;
					
					repaint();
				}
			}
		});

		s.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.salvar_estado_jogo();
			}
		});

		c.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.carregar_estado_jogo();
			}
		});
	}

	public void PopulaLista() {

		lst.insIni(new Vetor(6,1)); // 1- casa inicio vermelho
		lst.insFin(new Vetor(6,2)); // 2
		lst.insFin(new Vetor(6,3)); // 3
		lst.insFin(new Vetor(6,4)); // 4
		lst.insFin(new Vetor(6,5)); // 5
		lst.insFin(new Vetor(5,6)); // 6
		lst.insFin(new Vetor(4,6)); // 7
		lst.insFin(new Vetor(3,6)); // 8
		lst.insFin(new Vetor(2,6)); // 9
		lst.insFin(new Vetor(1,6)); // 10- casa preta
		lst.insFin(new Vetor(0,6)); // 11
		lst.insFin(new Vetor(0,7)); // 12
		lst.insFin(new Vetor(0,8)); // 13
		lst.insFin(new Vetor(1,8)); // 14- casa inicio verde
		lst.insFin(new Vetor(2,8)); // 15
		lst.insFin(new Vetor(3,8)); // 16
		lst.insFin(new Vetor(4,8)); // 17
		lst.insFin(new Vetor(5,8)); // 18
		lst.insFin(new Vetor(6,9)); // 19
		lst.insFin(new Vetor(6,10)); // 20
		lst.insFin(new Vetor(6,11)); // 21
		lst.insFin(new Vetor(6,12)); // 22
		lst.insFin(new Vetor(6,13)); // 23- casa preta
		lst.insFin(new Vetor(6,14)); // 24
		lst.insFin(new Vetor(7,14)); // 25
		lst.insFin(new Vetor(8,14)); // 26
		lst.insFin(new Vetor(8,13)); // 27- casa inicio amarelo
		lst.insFin(new Vetor(8,12)); // 28
		lst.insFin(new Vetor(8,11)); // 29
		lst.insFin(new Vetor(8,10)); // 30
		lst.insFin(new Vetor(8,9)); // 31
		lst.insFin(new Vetor(9,8)); // 32
		lst.insFin(new Vetor(10,8)); // 33
		lst.insFin(new Vetor(11,8)); // 34
		lst.insFin(new Vetor(12,8)); // 35
		lst.insFin(new Vetor(13,8)); // 36- casa preta
		lst.insFin(new Vetor(14,8)); // 37
		lst.insFin(new Vetor(14,7)); // 38
		lst.insFin(new Vetor(14,6)); // 39
		lst.insFin(new Vetor(13,6)); // 40- casa inicio azul
		lst.insFin(new Vetor(12,6)); // 41
		lst.insFin(new Vetor(11,6)); // 42
		lst.insFin(new Vetor(10,6)); // 43
		lst.insFin(new Vetor(9,6)); // 44
		lst.insFin(new Vetor(8,5)); // 45
		lst.insFin(new Vetor(8,4)); // 46
		lst.insFin(new Vetor(8,3)); // 47
		lst.insFin(new Vetor(8,2)); // 48
		lst.insFin(new Vetor(8,1)); // 49-  casa preta
		lst.insFin(new Vetor(8,0)); // 50
		lst.insFin(new Vetor(7,0)); // 51
		lst.insFin(new Vetor(6,0)); // 52

		//caminho vermelho
		lst.insFin(new Vetor(7,1)); // 53
		lst.insFin(new Vetor(7,2)); // 54
		lst.insFin(new Vetor(7,3)); // 55
		lst.insFin(new Vetor(7,4)); // 56
		lst.insFin(new Vetor(7,5)); // 57

		//caminho verde
		lst.insFin(new Vetor(1,7)); // 58
		lst.insFin(new Vetor(2,7)); // 59
		lst.insFin(new Vetor(3,7)); // 60
		lst.insFin(new Vetor(4,7)); // 61
		lst.insFin(new Vetor(5,7)); // 62

		//caminho amarelo
		lst.insFin(new Vetor(7,13)); // 63
		lst.insFin(new Vetor(7,12)); // 64
		lst.insFin(new Vetor(7,11)); // 65
		lst.insFin(new Vetor(7,10)); // 66
		lst.insFin(new Vetor(7,9)); // 67

		//caminho azul
		lst.insFin(new Vetor(13,7)); // 68
		lst.insFin(new Vetor(12,7)); // 69
		lst.insFin(new Vetor(11,7)); // 70
		lst.insFin(new Vetor(10,7)); // 71
		lst.insFin(new Vetor(9,7)); // 72

		//final
		lst.insFin(new Vetor(7,6)); // 73
		lst.insFin(new Vetor(6,7)); // 74
		lst.insFin(new Vetor(7,8)); // 75
		lst.insFin(new Vetor(8,7)); // 76
		
		lst.posIni();

	}

	public static void main(String[] args) {
		Main f=new Main();
		f.setVisible(true);
	}
}
