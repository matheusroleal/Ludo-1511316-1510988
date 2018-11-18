
public class Regras {
	boolean inicio;
	
	public boolean regras(int movimento) {
		if(movimento == 5 && inicio == true) {
			return true;
		}
		
		return false;
	}
}
