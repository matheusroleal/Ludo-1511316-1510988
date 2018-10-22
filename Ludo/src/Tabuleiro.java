import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

// 15 quadradinhos altura&largura no tabuleiro todo

public class Tabuleiro extends JPanel {
	private double larguraTela;
	private double alturaTela;
	
	public Tabuleiro(double w, double h) {
		larguraTela = w;
		alturaTela = h;
	}
	
	public void CasaInicial(Graphics2D g2d, Object[][] casas, Color a, int l, int c) {
		for(int i=l; i<6+l; i++) {
			for(int j=c; j<6+c; j++) {
				g2d.setPaint(a);
				g2d.fill((Shape) casas[i][j]);
			}
		}
	}
	
	public void RetaFinal(Graphics2D g2d, Object[][]casas, Color a, int l, int c) {
		if(l == 7) {
			for(int i=c; i<c+5; i++) {
				g2d.setPaint(a);
				g2d.fill((Shape) casas[l][i]);
			}
		}
		
		if(c == 7) {
			for(int i=l; i<l+5; i++) {
				g2d.setPaint(a);
				g2d.fill((Shape) casas[i][c]);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		Object [][]casas = new Object[15][15];
				
		rt=new Rectangle2D.Double(0,0,larguraTela, alturaTela);
		g2d.draw(rt);
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				casas[i][j] = new Rectangle2D.Double(50*j,50*i,50,50);
			}
		}	
		
		g2d.setPaint(Color.BLACK);
		g2d.fill((Shape) casas[8][1]);
		g2d.fill((Shape) casas[1][6]);
		g2d.fill((Shape) casas[6][13]);
		g2d.fill((Shape) casas[13][8]);
		
		RetaFinal(g2d, casas, Color.RED,7,1);
		RetaFinal(g2d, casas, Color.YELLOW,7,9);
		RetaFinal(g2d, casas, Color.GREEN,1,7);
		RetaFinal(g2d, casas, Color.BLUE,9,7);
		
		g2d.setPaint(Color.BLACK);
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				g2d.draw((Shape) casas[i][j]);
			}
		}
		
		CasaInicial(g2d, casas, Color.RED, 0,0);
		CasaInicial(g2d, casas, Color.GREEN, 0, 9);
		CasaInicial(g2d, casas, Color.BLUE, 9, 0);		
		CasaInicial(g2d, casas, Color.YELLOW,9,9);
		
//		casas[6][6]= new Triangle2D.Double(50,50,50);
//		g2d.draw=((Shape) casas[6][6]);
	}
	
	
}
