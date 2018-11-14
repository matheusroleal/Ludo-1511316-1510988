import java.awt.*;

import javax.swing.*;

public class Dado extends JPanel {
	public JButton dado_btn;
	private ImageIcon dado_img;

	public Dado() {
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado1.png"));
		dado_btn = new JButton(dado_img);
		
		dado_btn.setLayout(null);
		dado_btn.setLocation(752,0);
		dado_btn.setSize(200, 200);
	}
	
	public int GeraValor() {
		int dado_val = (int)(Math.random() * 6) + 1;
				
		MudaImagem(dado_val);
		
		return dado_val;
	}
	
	private void MudaImagem(int num) {
		dado_img = new ImageIcon(getClass().getResource("/Imagens/Dado"+ num +".png"));

		dado_btn.setIcon(dado_img);
	}
}
