import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Salvar extends Botao{

	public Salvar() {
		super("Salvar",752,202);
	}
	
	public void SalvaJogo(int jogador_turno, Vector<Jogador> Jogadores) throws JSONException {
		
	    String json_str = "{}";
	    JSONObject jsonObject = new JSONObject(json_str);
	    JSONArray jsonArray = new JSONArray();
	    Jogador j;
	    
	    for(int x = 0; x < 4; x++) {
	    	j = Jogadores.elementAt(x);
	    	for(int y = 0; y < 4; y++) {
	    		 jsonArray.put(y, j.peoes_do_jogador.elementAt(y).lst.pos);
	    	}
	    	jsonObject.put("j"+(x+1), jsonArray.toString());
	    }
	    
	    jsonObject.put("turno", jogador_turno);
	    	    
	    EscreverJogo(jsonObject);
	    	    
	}
	
	private void EscreverJogo(JSONObject jsonObject) {
	    Writer output = null;
	    
	    try {
			output = new BufferedWriter(new FileWriter("jogos_salvos/salvo.json"));
			output.write(jsonObject.toString());
		    output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
