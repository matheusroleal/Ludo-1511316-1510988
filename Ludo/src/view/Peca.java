package view;

import java.awt.*;
import java.awt.geom.*;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import model.TextAreaLog;

public class Peca extends JPanel {
	public int x;
	public int y;
	public Color a;
	public Boolean pode = false, barreira = false;
	public Color b;
	
	public Peca(){
		setOpaque(false);
		setBackground(new Color(0,0,0,0));
		setSize(750,750);
	}
	
	public void PintaP (Color cor_jogador) {
		this.a = cor_jogador;
	}
	
	public void setX(int novo_x) {
		x = novo_x;
	}
	
	public void setY(int novo_y) {
		y = novo_y;
	}
	
	public void setBarreira(boolean nova_barreira) {
		barreira = nova_barreira;
	}
	
	public void setPode(boolean nova_pode) {
		pode = nova_pode;
	}
	
	public Color ExibeP () {
		return this.a;
	}
	
	public void SetCorB(Color cor_jogador) {
		this.b = cor_jogador;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		Ellipse2D e = new Ellipse2D.Double();
		Ellipse2D e1=new Ellipse2D.Double();
		Ellipse2D e2 = new Ellipse2D.Double();
		Ellipse2D e3 = new Ellipse2D.Double();;
		
		e.setFrame(0,0,50,50);
		g2d.setPaint(Color.RED);
		g2d.fill(e);
		
		e.setFrame(50*y+5, 50*x+5, 40, 40);
		g2d.setPaint(a);
		g2d.fill(e);
		
		// Para barreira
		e2.setFrame(50*y+5,50*x+5,40,40);
		g2d.setPaint(a);
		g2d.fill(e2);
		
		e3.setFrame(50*y+10, 50*x+10,30,30);
		g2d.setPaint(a);
		g2d.fill(e3);
		
		// Para abrigo		
		e1.setFrame(50*y+7.5,50*x+7.5,35,35);
		g2d.setPaint(a);
		g2d.fill(e1);
				
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
		
		if(pode == true) {		
			e1.setFrame(50*y+10,50*x+10,30,30);
			g2d.setPaint(b);
			g2d.fill(e1);
		
		}
		
		if(barreira == true) {
			e2.setFrame(50*y+5,50*x+5,40,40);
			g2d.setPaint(Color.WHITE);
			g2d.fill(e2);
			
			e3.setFrame(50*y+10, 50*x+10,30,30);
			g2d.setPaint(a);
			g2d.fill(e3);
			
		}
		
	}
}
