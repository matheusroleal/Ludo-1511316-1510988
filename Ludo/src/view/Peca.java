package view;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Peca extends JPanel {
	public int x;
	public int y;
	public Color a;
	Boolean pode = false;
	
	public Peca(){
		setOpaque(false);
		setBackground(new Color(0,0,0,0));
		setSize(750,750);
	}
	
	public void PintaP (Color cor_jogador) {
		this.a = cor_jogador;
	}
	
	public Color ExibeP () {
		return this.a;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d=(Graphics2D) g;
		Ellipse2D e=new Ellipse2D.Double();
		
		e.setFrame(0,0,50,50);
		g2d.setPaint(Color.RED);
		g2d.fill(e);
		
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
		
		if(pode == true)
			System.out.println("funcionei!");
	}
}
