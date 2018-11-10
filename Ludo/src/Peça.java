import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Peça extends JPanel {
	int x;
	int y;
	Object [][]casas = new Object[15][15];
	Color a;
	
	public Peça(){
		setOpaque(false);
		setBackground(new Color(0,0,0,0));
		setSize(750,750);
		
		for(int i=0; i<15; i++) {
			for(int j=0; j<15; j++) {
				casas[i][j] = new Rectangle2D.Double(50*j,50*i,50,50);
			}
		}
	}
	

	public void paintComponent(Graphics g) {
		System.out.println("entrei");
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		Ellipse2D e=new Ellipse2D.Double();
		
		e.setFrame(50*y, 50*x, 50, 50);
		g2d.setPaint(a);
		g2d.fill(e);
		
		for(int i=0; i<7; i++) {
			if(x==7 && y==1+i || y==7 && x==1+i || x==7 && y==9+i || y==7 && x==9+i) {
				g2d.setPaint(Color.BLACK);
				g2d.draw(e);
			}
			
		}
	}
}
