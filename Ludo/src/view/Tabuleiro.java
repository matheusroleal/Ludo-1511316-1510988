package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Tabuleiro extends JPanel {
	private double larguraTela = 750;
	private double alturaTela = 750;
	public Object [][]casas = new Object[15][15];
	
	public void CasaInicial(Graphics2D g2d, Color a, int l, int c) {
		for(int i=l; i<6+l; i++) {
			for(int j=c; j<6+c; j++) {
				g2d.setPaint(a);
				g2d.fill((Shape) casas[i][j]);
			}
		}
	}
	
	public Ellipse2D Inicio(Graphics2D g2d, double x, double y) {
		Ellipse2D e=new Ellipse2D.Double();
		e.setFrame(x, y, 50, 50);
		g2d.setPaint(Color.WHITE);	
		g2d.fill(e);
		g2d.setPaint(Color.BLACK);
		g2d.draw(e);
		
		return e;
	}
	
	public void RetaFinal(Graphics2D g2d, Color a, int l, int c) {
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
		int[] cordx = new int[3];
		int[] cordy = new int[3];
				
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
		
		RetaFinal(g2d, Color.RED,7,1);
		RetaFinal(g2d, Color.YELLOW,7,9);
		RetaFinal(g2d, Color.GREEN,1,7);
		RetaFinal(g2d, Color.BLUE,9,7);
		
		g2d.setPaint(Color.RED);
		g2d.fill((Shape) casas[6][1]);		
		g2d.setPaint(Color.GREEN);
		g2d.fill((Shape) casas[1][8]);
		g2d.setPaint(Color.YELLOW);
		g2d.fill((Shape) casas[8][13]);
		g2d.setPaint(Color.BLUE);
		g2d.fill((Shape) casas[13][6]);
		
		g2d.setPaint(Color.BLACK);
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				g2d.draw((Shape) casas[i][j]);
			}
		}

		//casa final
		cordx[0] = 50*6;
		cordy[0] = 50*6;
		cordx[1] = 50*7 + 50/2;
		cordy[1] = 50*7 + 50/2;
		cordx[2] = 50*6;
		cordy[2] = 50*9;
		
		casas[6][6] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.RED);
		g2d.fill((Shape) casas[6][6]);
		
		cordx[0] = 50*6;
		cordy[0] = 50*6;
		cordx[1] = 50*9;
		cordy[1] = 50*6;
		cordx[2] = 50*7 + 50/2;
		cordy[2] = 50*7 + 50/2;
		
		casas[6][8] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.GREEN);
		g2d.fill((Shape) casas[6][8]);
		
		cordx[0] = 50*6;
		cordy[0] = 50*9;
		cordx[1] = 50*9;
		cordy[1] = 50*9;
		cordx[2] = 50*7 + 50/2;
		cordy[2] = 50*7 + 50/2;
		
		casas[8][6] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.BLUE);
		g2d.fill((Shape) casas[8][6]);
		
		cordx[0] = 50*9;
		cordy[0] = 50*6;
		cordx[1] = 50*9;
		cordy[1] = 50*9;
		cordx[2] = 50*7 + 50/2;	
		cordy[2] = 50*7 + 50/2;
		
		casas[8][8] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.YELLOW);
		g2d.fill((Shape) casas[8][8]);
		
		cordx[0] = 50*6;
		cordy[0] = 50*6;
		cordx[1] = 50*6;
		cordy[1] = 50*9;
		
		g2d.setPaint(Color.BLACK);
		g2d.draw((Shape) new Polygon(cordx, cordy, 2));
		
		cordx[0] = 50*6;
		cordy[0] = 50*6;
		cordx[1] = 50*9;
		cordy[1] = 50*6;
		
		g2d.setPaint(Color.BLACK);
		g2d.draw((Shape) new Polygon(cordx, cordy, 2));
		
		//casas iniciais
		CasaInicial(g2d, Color.RED, 0,0); 
		CasaInicial(g2d, Color.GREEN, 0,9);
		CasaInicial(g2d, Color.BLUE, 9,0);		
		CasaInicial(g2d, Color.YELLOW, 9,9);
		
		//locais onde pe�es ficam no in�cio do jogo
		casas[1][1] = new Ellipse2D.Double();
		casas[1][1] = Inicio(g2d, 50, 50);
		casas[1][4] = new Ellipse2D.Double();
		casas[1][4] = Inicio(g2d, 50*4, 50);
		casas[4][1] = new Ellipse2D.Double();
		casas[4][1] = Inicio(g2d, 50, 50*4);
		casas[4][4] = new Ellipse2D.Double();
		casas[4][4] = Inicio(g2d, 50*4, 50*4);
		casas[1][10] = new Ellipse2D.Double();
		casas[1][10] = Inicio(g2d, 50*10, 50);
		casas[1][13] = new Ellipse2D.Double();
		casas[1][13] = Inicio(g2d, 50*13, 50);
		casas[4][10] = new Ellipse2D.Double();
		casas[4][10] = Inicio(g2d, 50*10, 50*4);
		casas[4][13] = new Ellipse2D.Double();
		casas[4][13] = Inicio(g2d, 50*13, 50*4);
		casas[10][1] = new Ellipse2D.Double();
		casas[10][1] = Inicio(g2d, 50, 50*10);
		casas[10][4] = new Ellipse2D.Double();
		casas[10][4] = Inicio(g2d, 50*4, 50*10);
		casas[13][1] = new Ellipse2D.Double();
		casas[13][1] = Inicio(g2d, 50, 50*13);
		casas[13][4] = new Ellipse2D.Double();
		casas[13][4] = Inicio(g2d, 50*4, 50*13);
		casas[10][10] = new Ellipse2D.Double();
		casas[10][10] = Inicio(g2d, 50*10, 50*10);
		casas[10][13] = new Ellipse2D.Double();
		casas[10][13] = Inicio(g2d, 50*13, 50*10);
		casas[13][10] = new Ellipse2D.Double();
		casas[13][10] = Inicio(g2d, 50*10, 50*13);
		casas[13][13] = new Ellipse2D.Double();
		casas[13][13] = Inicio(g2d, 50*13, 50*13);
		
		//casas de sa�da
		cordx[0] = 50+10;
		cordy[0] = 50*6+10;
		cordx[1] = 50*2-10;
		cordy[1] = 50*6 + 50/2;
		cordx[2] = 50+10;
		cordy[2] = 50*7-10;
		
		casas[6][1] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.WHITE);
		g2d.fill((Shape) casas[6][1]);
		
		cordx[0] = 50*8+10;
		cordy[0] = 60;
		cordx[1] = 50*9-10;
		cordy[1] = 60;
		cordx[2] = 50*8 + 50/2;
		cordy[2] = 50*2-10;
		
		casas[1][8] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.WHITE);
		g2d.fill((Shape) casas[1][8]);
		
		cordx[0] = 50*14-10;
		cordy[0] = 50*8+10;
		cordx[1] = 50*14-10;
		cordy[1] = 50*9-10;
		cordx[2] = 50*13+10;
		cordy[2] = 50*8+ 50/2;
		
		casas[8][13] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.WHITE);
		g2d.fill((Shape) casas[8][13]);
		
		cordx[0] = 50*6+10;
		cordy[0] = 50*14-10;
		cordx[1] = 50*7-10;
		cordy[1] = 50*14-10;
		cordx[2] = 50*6 + 50/2;
		cordy[2] = 50*13+10;
		
		casas[13][6] = new Polygon(cordx, cordy, 3);
		g2d.setPaint(Color.WHITE);
		g2d.fill((Shape) casas[13][6]);
		
		Ellipse2D vm1, vm2, vm3, vm4;
		vm1 = vm2 = vm3 = vm4 =new Ellipse2D.Double();
		vm1.setFrame(50+5,50+5,40,40);
		g2d.setPaint(Color.RED);
		g2d.fill(vm1);
		vm2.setFrame(50*4+5,50+5,40,40);
		g2d.fill(vm2);
		vm3.setFrame(50+5,50*4+5,40,40);
		g2d.fill(vm3);
		vm4.setFrame(50*4+5,50*4+5,40,40);
		g2d.fill(vm4);
		
		Ellipse2D vd1, vd2, vd3, vd4;
		vd1 = vd2 = vd3 = vd4 =new Ellipse2D.Double();
		vd1.setFrame(50*10+5,50+5,40,40);
		g2d.setPaint(Color.GREEN);
		g2d.fill(vd1);
		vd2.setFrame(50*13+5,50+5,40,40);
		g2d.fill(vd2);
		vd3.setFrame(50*10+5,50*4+5,40,40);
		g2d.fill(vd3);
		vd4.setFrame(50*13+5,50*4+5,40,40);
		g2d.fill(vd4);
		
		Ellipse2D am1, am2, am3, am4;
		am1 = am2 = am3 = am4 =new Ellipse2D.Double();
		am1.setFrame(50*10+5,50*10+5,40,40);
		g2d.setPaint(Color.YELLOW);
		g2d.fill(am1);
		am2.setFrame(50*13+5,50*10+5,40,40);
		g2d.fill(am2);
		am3.setFrame(50*10+5,50*13+5,40,40);
		g2d.fill(am3);
		am4.setFrame(50*13+5,50*13+5,40,40);
		g2d.fill(am4);
		
		Ellipse2D az1, az2, az3, az4;
		az1 = az2 = az3 = az4 =new Ellipse2D.Double();
		az1.setFrame(50+5,50*10+5,40,40);
		g2d.setPaint(Color.BLUE);
		g2d.fill(az1);
		az2.setFrame(50*4+5,50*10+5,40,40);
		g2d.fill(az2);
		az3.setFrame(50+5,50*13+5,40,40);
		g2d.fill(az3);
		az4.setFrame(50*4+5,50*13+5,40,40);
		g2d.fill(az4);
	}
	
}
