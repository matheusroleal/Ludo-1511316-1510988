package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Interface.Inicializador;
import model.Jogador;
import model.Vetor;

public class FluxoDados {

	private static FluxoDados fDados = null;

	private FluxoDados(){

	}

	public static FluxoDados getFluxoDados(){
		if(fDados == null)
			fDados = new FluxoDados();
		return fDados;
	}

	public void SalvarPartida(){
	    String json_str = "{}";
	    JSONObject jsonObject;
	    JSONArray jsonArray = new JSONArray();
	    Jogador j;
	    
		try {
			jsonObject = new JSONObject(json_str);
		    for(int x = 0; x < 4; x++) {
		    	j = JogadoresController.getJogadoresController().getJogador(x);
		    	for(int y = 0; y < 4; y++) {
		    		 jsonArray.put(y, j.peoes_do_jogador.elementAt(y).getPos());
		    	}
		    	jsonObject.put("j"+x, jsonArray.toString());
		    }
		    
		    jsonObject.put("turno", JogadoresController.getJogadoresController().getJogadorTurno());
		    
		    jsonObject.put("c1", JogadoresController.getJogadoresController().getC(1));
		    jsonObject.put("c2", JogadoresController.getJogadoresController().getC(2));
		    jsonObject.put("c3", JogadoresController.getJogadoresController().getC(3));
		    jsonObject.put("c4", JogadoresController.getJogadoresController().getC(4));
		    
		    jsonObject.put("fim1", JogadoresController.getJogadoresController().getFim(1));
		    jsonObject.put("fim2", JogadoresController.getJogadoresController().getFim(2));
		    jsonObject.put("fim3", JogadoresController.getJogadoresController().getFim(3));
		    jsonObject.put("fim4", JogadoresController.getJogadoresController().getFim(4));
		    
		    jsonObject.put("cinco1",JogadoresController.getJogadoresController().getCinco(1));
		    jsonObject.put("cinco2",JogadoresController.getJogadoresController().getCinco(2));
		    jsonObject.put("cinco3",JogadoresController.getJogadoresController().getCinco(3));
		    jsonObject.put("cinco4",JogadoresController.getJogadoresController().getCinco(4));
			
		    jsonObject.put("y1",JogadoresController.getJogadoresController().getY(1));
		    jsonObject.put("y2",JogadoresController.getJogadoresController().getY(2));
		    jsonObject.put("y3",JogadoresController.getJogadoresController().getY(3));
		    jsonObject.put("y4",JogadoresController.getJogadoresController().getY(4));

		    	    
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

	public void CarregarPartida() throws IOException, JSONException{
	    JSONObject jsonObject;
	    String json_str;
		Vector<Color> cores = new Vector<>();
		BufferedReader reader = null;

	    Jogador j_nova_pos;
	    
		cores.insertElementAt(Color.RED, 0);
		cores.insertElementAt(Color.GREEN, 1);
		cores.insertElementAt(Color.YELLOW, 2);
		cores.insertElementAt(Color.BLUE, 3);
		
		    
		File file = new File("jogos_salvos/salvo.json");
		reader = new BufferedReader(new FileReader(file));
		
		json_str = reader.readLine();

	    reader.close();

		jsonObject = new JSONObject(json_str);
		
		JogadoresController.getJogadoresController().jogador_turno = jsonObject.getInt("turno");
				
	    for(int x = 0; x < 4; x++) {
    	    JSONArray jsonArray = new JSONArray(jsonObject.getString("j"+x));
	    	j_nova_pos = JogadoresController.getJogadoresController().getJogador(x);
	    	
	    	int pos = x + 1;
    	    JogadoresController.getJogadoresController().setC(x, jsonObject.getBoolean("c"+pos));
    	    JogadoresController.getJogadoresController().setCinco(x, jsonObject.getBoolean("cinco"+pos));
    	    JogadoresController.getJogadoresController().setFim(x, jsonObject.getInt("fim"+pos));
    	    JogadoresController.getJogadoresController().setY(x, jsonObject.getBoolean("y"+pos));
    	    
	    	for(int y = 0; y < 4; y++) {
	    		int nova_pos = (int)jsonArray.get(y);
	    		if(nova_pos > 0) {
		    		j_nova_pos.peoes_do_jogador.elementAt(y).lst.posIni();
		    		
					for (int i = 1; i < nova_pos + 1 ; i++) {
						j_nova_pos.peoes_do_jogador.elementAt(y).lst.prox();
					}
					
					Vetor v = (Vetor) j_nova_pos.peoes_do_jogador.elementAt(y).lst.posCorr();
					
					int novo_x = v.RetornaX();
					int novo_y = v.RetornaY();
					
					j_nova_pos.getPeao(y).a = cores.elementAt(x);
					j_nova_pos.getPeao(y).x = novo_x;
					j_nova_pos.getPeao(y).y = novo_y;
					
					Inicializador.getInicializador().repaint();
	    		}
	    	}
	    }
	    	
	}

}