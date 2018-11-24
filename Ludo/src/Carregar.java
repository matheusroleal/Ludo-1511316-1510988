import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Carregar extends Botao{
	Vector<Jogador> Jogadores;
	
	public Carregar() {
		super("Carregar",752, 302);
	}
	
	public String CarregaJogo() throws IOException {		
		BufferedReader reader = null;
	    String line;

		    
		File file = new File("jogos_salvos/salvo.json");
		reader = new BufferedReader(new FileReader(file));
		
		line = reader.readLine();

	    reader.close();
	    
		return line;

	}
	
	public int CarregaTurno() throws JSONException, IOException {
		String json_str = CarregaJogo();
		
	    JSONObject jsonObject;
	    int jogador_turno = 0;

		jsonObject = new JSONObject(json_str);
		
	    jogador_turno = jsonObject.getInt("turno");
	    	
		return jogador_turno;
	
	}
	
	public boolean CarregaDado(String data) throws JSONException, IOException {
		String json_str = CarregaJogo();
		
	    JSONObject jsonObject;
	    boolean result;

		jsonObject = new JSONObject(json_str);
		
	    result = jsonObject.getBoolean(data);
	    	
		return result;
	
	}
	
}
