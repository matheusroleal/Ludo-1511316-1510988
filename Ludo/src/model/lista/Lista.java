package model.lista;

public class Lista {
	private int tam=0;
	private No ini=null;
	private No fin=null;
	private No corr=null;
	public int pos = 0;

	public boolean vazio() {
		if(tam != 0) {
			return false;
		}
		return true;
	}
	
	public void insIni(Object x) {
		No aux = new No(x,ini);
		
		ini = aux;
		
		if(tam == 0) {
			fin = ini;
		}
		
		tam++;
	}
	
	public void insFin(Object x) {
		if(tam == 0) {
			insIni(x);
			return;
		}
		
		No ult = new No(x,null);
		
		fin.setProx(ult);
		fin = ult;
		
		tam++;
	}
	
	public Object posCorr() {
		if(tam == 0) {
			return null;
		}
		
		Object elem = corr.getElem();
		
		return elem;
	}
	
	public void posIni() {
		pos = 0;
		corr = ini;
	}
	
	public Object prox() {
		if(corr == null) {
			return null;
		}
		
		pos += 1;

		Object o = corr.getElem();
		corr = corr.getProx();
		
		return o;
	}

}
