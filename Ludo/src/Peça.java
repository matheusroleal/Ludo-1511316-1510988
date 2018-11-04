import java.awt.*;
import java.awt.geom.*;

public class Peça extends Tabuleiro {	
	int x;
	int y;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println("peça");
		System.out.println(x);
		System.out.println(y);
		
		Graphics2D g2d=(Graphics2D) g;
		Tabuleiro t = new Tabuleiro();
		Ellipse2D e=new Ellipse2D.Double();

		t.casas[x][y] = new Ellipse2D.Double();
		e.setFrame(50*y, 50*x, 50, 50);
		g2d.setPaint(Color.RED);
		g2d.fill(e);
		g2d.setPaint(Color.BLACK);
		g2d.draw(e);

		g2d.setPaint(Color.RED);
		g2d.fill((Shape) casas[4][4]);
		g2d.draw((Shape) casas[4][4]);
	}
}
