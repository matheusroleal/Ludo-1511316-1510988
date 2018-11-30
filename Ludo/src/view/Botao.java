package view;

import javax.swing.JButton;

public class Botao {
	public JButton btn;
	
	public Botao(String name, int x, int y) {
		btn = new JButton(name);
		
		btn.setLayout(null);
		btn.setLocation(x,y);
		btn.setSize(200, 100);
	}
	
	
}
