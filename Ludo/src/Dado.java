import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Dado extends JFrame {
	Container c = getContentPane();
	ImageIcon dado_img;
	public JButton dado_btn;

	public Dado() {
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado1.png"));
		dado_btn = new JButton(dado_img);
		dado_btn.setPreferredSize(new Dimension(10, 10));
		
		c.add(dado_btn);
		
		setSize(150,150);
		setVisible(true);
	}
	
	public int get_new_dado_value() {
		int dado_val = (int)(Math.random() * 6) + 1;
		
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado"+ dado_val +".png"));

		dado_btn.setIcon(dado_img);
		
		c.removeAll();
		c.add(dado_btn);
		
		return dado_val;
	}
	
}
