import java.util.Vector;

public class Jogador {
	Vector<Peao> peoes_do_jogador;
	int num_peca;
	
	public Jogador(String cor_jogador) {
		peoes_do_jogador = new Vector<>();
		num_peca = 0;
		
		CriaPeoes(cor_jogador);
	}
	
	private void CriaPeoes(String cor_jogador) {
		for(int index = 0; index < 4; index++) {
			peoes_do_jogador.insertElementAt(new Peao(cor_jogador), index);
		}
	}
	
	public void ProxPeca() {
		if(num_peca < 4) {
			num_peca += 1;
		}
	}
}
