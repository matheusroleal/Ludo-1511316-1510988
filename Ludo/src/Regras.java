
public class Regras {
	boolean inicio;
	
	public boolean regras(int movimento) {
		if(inicio == true) {
			if(movimento != 6)
				return false;
			else
				return true;
		}
		
		return true;
	}
}
