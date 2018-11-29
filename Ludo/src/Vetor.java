import java.awt.Color;
import java.util.Vector;

public class Vetor {
	private int x,y;
	private Vector<Peao> jogadores_na_casa = new Vector<>();

	public Vetor(int x,int y) {
		this.x=x;
		this.y=y;
		jogadores_na_casa.add(0, null);
		jogadores_na_casa.add(1, null);
	}
	
	public int RetornaX() {
		return this.x;
	}
	
	public int RetornaY() {
		return this.y;
	}
		
	public void AdicionaPeao(Peao p) {
		System.out.println(jogadores_na_casa.firstElement());
		System.out.println(jogadores_na_casa.lastElement());
		
		if(jogadores_na_casa.firstElement() == null) {
			jogadores_na_casa.add(0, p);
			System.out.println("add - " + jogadores_na_casa.elementAt(0) + " x: " + x + " y: " + y);
			System.out.println(jogadores_na_casa.firstElement());
		}else if(jogadores_na_casa.lastElement() == null){
			jogadores_na_casa.add(1, p);
			System.out.println("!!!!!!!2 ocupando mesma casa!!!!!!!");
		}else {
			System.out.print("Quantidade maxima de jogadores na casa");
		}
	}
	
	public void RemovePeao(Peao p) {
		if(VerificaElemento()) {
			System.out.print("Quantidade maxima de jogadores na casa, nao pode passar");
		}else {
			jogadores_na_casa.add(jogadores_na_casa.indexOf(p), null);
			System.out.println("remove - " + jogadores_na_casa.elementAt(0) + " x: " + x + " y: " + y);
		}
	}
	
	public Color ExibeCorPeao(int index) {
		return jogadores_na_casa.elementAt(index).p1.a;
	}
	
	public boolean VerificaElemento() {
		if(jogadores_na_casa.lastElement() != null) {
			return true;
		}
		return false;
	}

}
