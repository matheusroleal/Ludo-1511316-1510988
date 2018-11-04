import java.util.Vector;

public class Vetor {
	private int x,y;
	private Vector<Integer> jogadores_na_casa = new Vector<>();

	public Vetor(int x,int y) {
		this.x=x;
		this.y=y;
		jogadores_na_casa.add(0, 0);
		jogadores_na_casa.add(1, 0);
	}
	
	public int show_pos_x() {
		return this.x;
	}
	
	public int show_pos_y() {
		return this.y;
	}
		
	public void add_jogadores_na_casa(int j) {
		if(jogadores_na_casa.firstElement() == 0) {
			jogadores_na_casa.add(0, j);
		}else if(jogadores_na_casa.lastElement() == 0){
			jogadores_na_casa.add(1, j);
		}else {
			System.out.print("Quantidade m�xima de jogadores na pe�a");
		}
	}
	
	public void remove_jogadores_na_casa() {
		if(check_last_element_jogadores_na_casa()) {
			System.out.print("Quantidade m�xima de jogadores na pe�a, n�o pode passar");
		}else {
			jogadores_na_casa.add(0, 0);
		}
	}
	
	public boolean check_last_element_jogadores_na_casa() {
		if(jogadores_na_casa.lastElement() != 0) {
			return true;
		}
		return false;
	}

}
