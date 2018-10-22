import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame{
	
	public main() {
		setBounds(0,0,770,790);
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
		Tabuleiro t = new Tabuleiro(750,750);
		getContentPane().add(t);
	}

	public static void main(String[] args) {
		main f=new main();
		f.setVisible(true);
	}
}