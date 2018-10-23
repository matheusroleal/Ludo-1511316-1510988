import java.awt.*;

import javax.swing.*;

public class Dado extends JFrame {
	public JButton dado_btn;
	private ImageIcon dado_img;

	public Dado() {
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado1.png"));
		dado_btn = new JButton(dado_img);
		
		dado_btn.setLayout(null);
		dado_btn.setLocation(752,0);
		dado_btn.setSize(100, 100);
	}
	
	public int get_new_dado_value() {
		int dado_val = (int)(Math.random() * 6) + 1;
				
		change_image(dado_val);
		
		return dado_val;
	}
	
	private void change_image(int num) {
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado"+ num +".png"));

		dado_btn.setIcon(dado_img);
	}
}
