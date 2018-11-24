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
	
	public void SalvaJogo(int jogador_turno, Vector<Jogador> Jogadores, boolean c1,boolean c2,boolean c3,boolean c4,boolean fim1,boolean fim2,boolean fim3,boolean fim4) {
	    String json_str = "{}";
	    JSONObject jsonObject;
	    JSONArray jsonArray = new JSONArray();
	    Jogador j;
	    
		try {
			jsonObject = new JSONObject(json_str);
		    for(int x = 0; x < 4; x++) {
		    	j = Jogadores.elementAt(x);
		    	for(int y = 0; y < 4; y++) {
		    		 jsonArray.put(y, j.peoes_do_jogador.elementAt(y).lst.pos);
		    	}
		    	jsonObject.put("j"+x, jsonArray.toString());
		    }
		    
		    jsonObject.put("turno", jogador_turno);
		    
		    jsonObject.put("c1", c1);
		    jsonObject.put("c2", c2);
		    jsonObject.put("c3", c3);
		    jsonObject.put("c4", c4);
		    
		    jsonObject.put("fim1", fim1);
		    jsonObject.put("fim2", fim2);
		    jsonObject.put("fim3", fim3);
		    jsonObject.put("fim4", fim4);		    

		    	    
		    EscreverJogo(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		} 	    
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
