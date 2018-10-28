import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame{
	
	public main() {
		setBounds(0,0,960,756);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		
		Tabuleiro t = new Tabuleiro(750,750);
		Dado d = new Dado();
		Salvar s = new Salvar();
		Carregar c = new Carregar();
		
		getContentPane().add(d.dado_btn);
		getContentPane().add(s.btn);
		getContentPane().add(c.btn);
		getContentPane().add(t);
	
		d.dado_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				d.get_new_dado_value();
			}
		});
		
		s.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s.salvar_estado_jogo();
			}
		});
		
		c.btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.carregar_estado_jogo();
			}
		});
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}