package view;

import javax.swing.JButton;

public class BotaoTeste {
	public JButton btn;
	private int Botao_valor;
	
	public BotaoTeste(String name, int x, int y, int bv) {
		btn = new JButton(name);
		
		btn.setLayout(null);
		btn.setLocation(x,y);
		btn.setSize(65, 50);

		Botao_valor = bv;
	}
	
	public int GeraValor() {
		return Botao_valor;
	}

}
