import java.util.Vector;

public class Vetor {
	private double x,y;
	private Vector<Integer> jogadores_na_casa = new Vector<>();

	
	public Vetor(double x,double y) {
		this.x=x;
		this.y=y;
		jogadores_na_casa.add(0, 0);
		jogadores_na_casa.add(1, 0);
	}
	
	public void add_jogadores_na_casa(int j) {
		if(jogadores_na_casa.firstElement() == 0) {
			jogadores_na_casa.add(0, j);
		}else if(jogadores_na_casa.lastElement() == 0){
			jogadores_na_casa.add(1, j);
		}else {
			System.out.print("Quantidade máxima de jogadores na peça");
		}
	}
	
	public void remove_jogadores_na_casa() {
		if(jogadores_na_casa.firstElement() == 0) {
			jogadores_na_casa.removeElementAt(0);
		}else {
			jogadores_na_casa.removeElementAt(1);
		}
	}

}
