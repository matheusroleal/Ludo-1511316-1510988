import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConsoleLog {
	JTextArea textArea;
	JPanel p = new JPanel();
	
	public ConsoleLog() {
		textArea = new JTextArea();
//		textArea.setLocation(752, 402);
//		p.add(textArea);
		p.setSize(10, 10);
		Color bg = null;
		p.setBackground(bg.BLACK);
		p.setLocation(752, 402);
	}
	
	public void adicionar_texto(String t) {
		textArea.append(t);
	}
}
