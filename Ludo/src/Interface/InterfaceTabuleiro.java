//package Interface;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//
//import org.json.JSONException;
//
//import controller.JogadoresController;
//import model.Dado;
//import model.Jogador;
//import model.Vetor;
//import view.Carregar;
//import view.Salvar;
//import view.Tabuleiro;
//
//public class InterfaceTabuleiro extends JFrame{
//	private static InterfaceTabuleiro xframe = null;
//	InterfaceTabuleiro painel;
//	
//	private InterfaceTabuleiro() {
//		
//		Tabuleiro t = new Tabuleiro();
//		
//		this.setBounds(0,0,960,800);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		
//		JButton vermelho = new JButton();
//		JButton verde = new JButton();
//		JButton amarelo = new JButton();
//		JButton azul = new JButton();
//		
//		vermelho.setLayout(null);
//		vermelho.setLocation(0,0);
//		vermelho.setSize(6*50, 6*50);
//		vermelho.setContentAreaFilled(false);
//	
//		verde.setLayout(null);
//		verde.setLocation(50*9,0);
//		verde.setSize(6*50, 6*50);
//		verde.setContentAreaFilled(false);
//		
//		amarelo.setLayout(null);
//		amarelo.setLocation(50*9,50*9);
//		amarelo.setSize(6*50, 6*50);
//		amarelo.setContentAreaFilled(false);
//		
//		azul.setLayout(null);
//		azul.setLocation(0,50*9);
//		azul.setSize(6*50, 6*50);
//		azul.setContentAreaFilled(false);
//		
//		this.getContentPane().add(vermelho);
//		this.getContentPane().add(verde);
//		this.getContentPane().add(amarelo);
//		this.getContentPane().add(azul);
//		
//		vermelho.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Jogador j1 = JogadoresController.getInstance().Jogadores.elementAt(0); 
//				
//				vermelho.setEnabled(false); 
//				
//				j1.SetPColor(Color.RED);
//				j1.SetPX(4);
//				j1.SetPY(4);
//				
//				repaint();
//				
//				InterfaceBotoes.getInterfaceBotoes().d.dado_btn.setEnabled(true);
//			}
//		});
//				
//		verde.addActionListener(new ActionListener() {
//			public void	 actionPerformed(ActionEvent e) {
//				Jogador j2 = JogadoresController.getInstance().Jogadores.elementAt(1); 
//
//				verde.setEnabled(false);
//				
//				j2.SetPColor(Color.GREEN);
//				j2.SetPX(1);
//				j2.SetPY(10);
//				
//				repaint();
//				InterfaceBotoes.getInterfaceBotoes().d.dado_btn.setEnabled(true);
//			}
//		});
//				
//		azul.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Jogador j3 = JogadoresController.getInstance().Jogadores.elementAt(2); 
//
//				azul.setEnabled(false); 
//								
//				j3.SetPColor(Color.BLUE);
//				j3.SetPX(13);
//				j3.SetPY(4);
//				
//				repaint();
//				InterfaceBotoes.getInterfaceBotoes().d.dado_btn.setEnabled(true);
//			}
//		});
//				
//		amarelo.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Jogador j4 = JogadoresController.getInstance().Jogadores.elementAt(3); 
//
//				amarelo.setEnabled(false); 
//								
//				j4.SetPColor(Color.YELLOW);
//				j4.SetPX(10);
//				j4.SetPY(13);
//				
//				repaint();
//				InterfaceBotoes.getInterfaceBotoes().d.dado_btn.setEnabled(true);
//			}
//		});
//		
//		this.getContentPane().add(t);
//	}
//	
//	public static InterfaceTabuleiro getInterfaceTabuleiro() {
//		if(xframe == null)
//			xframe = new InterfaceTabuleiro();
//		return xframe;
//	}
//}
