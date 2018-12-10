package controller;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Interface.Jogo;
import model.Jogador;
import model.TextAreaLog;
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
			    JSONArray jsonArrayInterno = new JSONArray();

		    	j = JogadoresController.getJogadoresController().getJogador(x);
		    	
		    	for(int y = 0; y < 4; y++) {
				    JSONObject jsonObjectInterno = new JSONObject(json_str);

		    		
					jsonArray.put(y, j.peoes_do_jogador.elementAt(y).getPos());	
					
				    jsonObjectInterno.put("c1", j.getPeao(y).getC(1));
				    jsonObjectInterno.put("c2", j.getPeao(y).getC(2));
				    jsonObjectInterno.put("c3", j.getPeao(y).getC(3));
				    jsonObjectInterno.put("c4", j.getPeao(y).getC(4));
				    
				    jsonObjectInterno.put("fim1", j.getPeao(y).getFim(1));
				    jsonObjectInterno.put("fim2", j.getPeao(y).getFim(2));
				    jsonObjectInterno.put("fim3", j.getPeao(y).getFim(3));
				    jsonObjectInterno.put("fim4", j.getPeao(y).getFim(4));
				    
				    jsonObjectInterno.put("cinco1",j.getPeao(y).getCinco(1));
				    jsonObjectInterno.put("cinco2",j.getPeao(y).getCinco(2));
				    jsonObjectInterno.put("cinco3",j.getPeao(y).getCinco(3));
				    jsonObjectInterno.put("cinco4",j.getPeao(y).getCinco(4));
					
				    jsonObjectInterno.put("y1",j.getPeao(y).getY(1));
				    jsonObjectInterno.put("y2",j.getPeao(y).getY(2));
				    jsonObjectInterno.put("y3",j.getPeao(y).getY(3));
				    jsonObjectInterno.put("y4",j.getPeao(y).getY(4));
				   
				    jsonObjectInterno.put("md1",j.getPeao(y).getMd(1));
				    jsonObjectInterno.put("md2",j.getPeao(y).getMd(2));
				    jsonObjectInterno.put("md3",j.getPeao(y).getMd(3));
				    jsonObjectInterno.put("md4",j.getPeao(y).getMd(4));
				    
				    jsonArrayInterno.put(y, jsonObjectInterno);
		    		 
		    	}
		    	jsonObject.put("j"+x, jsonArrayInterno.toString());

		    	jsonObject.put("j"+x+"mov", jsonArray.toString());
		    }
		    
		    jsonObject.put("turno", JogadoresController.getJogadoresController().getJogadorTurno());
		    jsonObject.put("m", JogadoresController.getJogadoresController().getM());
		    		    	    
		    jsonObject.put("cont1", JogadoresController.getJogadoresController().getCont(1));
		    jsonObject.put("cont2", JogadoresController.getJogadoresController().getCont(2));
		    jsonObject.put("cont3", JogadoresController.getJogadoresController().getCont(3));
		    jsonObject.put("cont4", JogadoresController.getJogadoresController().getCont(4));

		    EscreverJogo(jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		} 	
	}
	
	private void EscreverJogo(JSONObject jsonObject) {
	    Writer output = null;
	    JFileChooser salvandoArquivo = new JFileChooser();
	    int resultado = salvandoArquivo.showSaveDialog(null);
		try {
		    if (resultado == JFileChooser.APPROVE_OPTION) {
			    File salvarArquivoEscolhido = salvandoArquivo.getSelectedFile();
				output = new BufferedWriter(new FileWriter(salvarArquivoEscolhido.getAbsolutePath()));
				output.write(jsonObject.toString());
			    output.close();
				TextAreaLog.getTextAreaLog().printLog("Arquivo salvado!");
		    }else {
				TextAreaLog.getTextAreaLog().printLog("Erro ao salvar arquivo!");
			}
		} catch (BadLocationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void CarregarPartida() throws IOException, JSONException, BadLocationException{
	    JSONObject jsonObject;
	    String json_str;
		Vector<Color> cores = new Vector<>();
		BufferedReader reader = null;

	    Jogador j_nova_pos;
	    
		cores.insertElementAt(Color.RED, 0);
		cores.insertElementAt(Color.GREEN, 1);
		cores.insertElementAt(Color.YELLOW, 2);
		cores.insertElementAt(Color.BLUE, 3);
		
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Escolha um arquivo para carregar");
		fc.setFileFilter(new FileNameExtensionFilter("Arquivos de Texto", "json"));
		int returnVal = fc.showOpenDialog(Jogo.getJogo());
		
		if(returnVal==JFileChooser.APPROVE_OPTION){
			File file = fc.getSelectedFile();
			reader = new BufferedReader(new FileReader(file.getPath()));
			
			json_str = reader.readLine();

		    reader.close();

			jsonObject = new JSONObject(json_str);

			JogadoresController.getJogadoresController().jogador_turno = jsonObject.getInt("turno");
//			JogadoresController.getJogadoresController().setM(jsonObject.getBoolean("m"));


		    for(int x = 0; x < 4; x++) {
	    	    JSONArray jsonArray = new JSONArray(jsonObject.getString("j"+x+"mov"));
	    	    JSONArray jsonArrayInterno = new JSONArray(jsonObject.getString("j"+x));
	    	    
	    		JogadoresController.getJogadoresController().setCont((x+1), jsonObject.getInt("cont"+(x+1)));
	    	    
		    	j_nova_pos = JogadoresController.getJogadoresController().getJogador(x);

		    	for(int y = 0; y < 4; y++) {
		    		int nova_pos = (int)jsonArray.get(y);

		    		if(nova_pos > 0) {

		    			int pos = y + 1;

					    JSONObject jsonObjectInterno = new JSONObject(jsonArrayInterno.get(y).toString());

		    			j_nova_pos.getPeao(y).setC(y, jsonObjectInterno.getBoolean("c"+pos));
		        	    j_nova_pos.getPeao(y).setCinco(y, jsonObjectInterno.getBoolean("cinco"+pos));
		        	    j_nova_pos.getPeao(y).setFim(y, jsonObjectInterno.getInt("fim"+pos));
		        	    j_nova_pos.getPeao(y).setY(y, jsonObjectInterno.getBoolean("y"+pos));
		        	    j_nova_pos.getPeao(y).setMd(y, jsonObjectInterno.getInt("md"+pos));
		    			
			    		j_nova_pos.peoes_do_jogador.elementAt(y).lst.posIni();
			    		
						for (int i = 1; i < nova_pos + 1 ; i++) {
							j_nova_pos.peoes_do_jogador.elementAt(y).lst.prox();
						}
						
						Vetor v = (Vetor) j_nova_pos.peoes_do_jogador.elementAt(y).lst.posCorr();
						
						int novo_x = v.RetornaX();
						int novo_y = v.RetornaY();

						Jogo.getJogo().getCaminho(novo_x, novo_y).AdicionaPeao(j_nova_pos.getPeao(y));
						Jogo.getJogo().getCaminho(novo_x, novo_y).numPeao = y;
						
						j_nova_pos.getPeao(y).getP1().PintaP(cores.elementAt(x));
						j_nova_pos.getPeao(y).getP1().setX(novo_x);
						j_nova_pos.getPeao(y).getP1().setY(novo_y);

						Jogo.getJogo().repaint();

		    		}
		    	}
		    }
		}else {
			TextAreaLog.getTextAreaLog().printLog("Erro ao escolher arquivo!");
		}
	}
}