import java.util.Vector;

public class Jogador {
	Vector<Peao> peoes_do_jogador;
	int num_peao;
	
	public Jogador(String cor_jogador) {
		peoes_do_jogador = new Vector<>();
		num_peao = 0;
		
		CriaPeoes(cor_jogador);
	}
	
	private void CriaPeoes(String cor_jogador) {
		for(int index = 0; index < 4; index++) {
			peoes_do_jogador.insertElementAt(new Peao(cor_jogador), index);
		}
	}
	
	public void mudaPeao() {
		if(num_peao < 4)
			num_peao++;
	}
}
