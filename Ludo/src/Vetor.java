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
	
	public int RetornaX() {
		return this.x;
	}
	
	public int RetornaY() {
		return this.y;
	}
		
	public void AdicionaPeca(int j) {
		if(jogadores_na_casa.firstElement() == 0) {
			jogadores_na_casa.add(0, j);
		}else if(jogadores_na_casa.lastElement() == 0){
			jogadores_na_casa.add(1, j);
		}else {
			System.out.print("Quantidade maxima de jogadores na peca");
		}
	}
	
	public void RemovePeca() {
		if(VerificaUltimoElemento()) {
			System.out.print("Quantidade maxima de jogadores na peca, nao pode passar");
		}else {
			jogadores_na_casa.add(0, 0);
		}
	}
	
	public boolean VerificaUltimoElemento() {
		if(jogadores_na_casa.lastElement() != 0) {
			return true;
		}
		return false;
	}

}
