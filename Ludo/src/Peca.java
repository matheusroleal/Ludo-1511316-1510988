import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Peca extends JPanel {
	int x;
	int y;
	Color a;
	private Object [][]casas = new Object[15][15];
	
	public Peca(){
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
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		Ellipse2D e=new Ellipse2D.Double();
		
		e.setFrame(50*y, 50*x, 50, 50);
		g2d.setPaint(a);
		g2d.fill(e);
		
		if(x==6 && y==1 || x==1 && y==8 || x==13 && y==6 || x==8 && y==13) {
			g2d.setPaint(Color.BLACK);
			g2d.draw(e);
		}
		
		for(int i=0; i<6; i++) {
			if(x==7 && y==1+i || y==7 && x==1+i || x==7 && y==8+i || y==7 && x==8+i) {
				g2d.setPaint(Color.BLACK);
				g2d.draw(e);
			}
			
		}
	}
}
