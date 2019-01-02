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
    int jogador_num;

	private FluxoDados(){

	}

	public static FluxoDados getFluxoDados(){
		if(fDados == null)
			fDados = new FluxoDados();
		return fDados;
	}

	public void SalvarPartida() {
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
				TextAreaLog.getTextAreaLog().printLog("Arquivo salvo!");
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
		BufferedReader reader = null;

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
			JogadoresController.getJogadoresController().setM(jsonObject.getBoolean("m"));

			for(int x = 0; x < 4; x++) {
			  JSONArray jsonArray = new JSONArray(jsonObject.getString("j"+x+"mov"));
			  JSONArray jsonArrayInterno = new JSONArray(jsonObject.getString("j"+x));
			  Jogador j_nova_pos = JogadoresController.getJogadoresController().getJogador(x);

			  jogador_num = x + 1;
			  JogadoresController.getJogadoresController().setCont(jogador_num, jsonObject.getInt("cont"+jogador_num));

			  for(int y = 0; y < 4; y++) {
				JSONObject jsonObjectInterno = new JSONObject(jsonArrayInterno.get(y).toString());
				int nova_pos = (int)jsonArray.get(y);

				j_nova_pos.setNumPeao(y);

				j_nova_pos.getPeao(y).setC(jogador_num, jsonObjectInterno.getBoolean("c"+jogador_num));
				j_nova_pos.getPeao(y).setCinco(jogador_num, jsonObjectInterno.getBoolean("cinco"+jogador_num));
				j_nova_pos.getPeao(y).setFim(jogador_num, jsonObjectInterno.getInt("fim"+jogador_num));
				j_nova_pos.getPeao(y).setY(jogador_num, jsonObjectInterno.getBoolean("y"+jogador_num));
				j_nova_pos.getPeao(y).setMd(jogador_num, jsonObjectInterno.getInt("md"+jogador_num));

				if(nova_pos > 0) {
				    j_nova_pos.SetPColor(Color.WHITE);
				    
				    if(j_nova_pos.getNumPeao() == 0) {
				    	j_nova_pos.SetPX(defineXInicial(jogador_num, 0));
		    			j_nova_pos.SetPY(defineYInicial(jogador_num, 0));
		    		}
		    		else if(j_nova_pos.getNumPeao() == 1) {
		    			j_nova_pos.SetPX(defineXInicial(jogador_num, 1));
		    			j_nova_pos.SetPY(defineYInicial(jogador_num, 1));
		    		}
		    		else if(j_nova_pos.getNumPeao() == 2) {
		    			j_nova_pos.SetPX(defineXInicial(jogador_num, 2));
		    			j_nova_pos.SetPY(defineYInicial(jogador_num, 2));
		    		}
		    		else {
		    			j_nova_pos.SetPX(defineXInicial(jogador_num, 3));
		    			j_nova_pos.SetPY(defineYInicial(jogador_num, 3));
		    		}
				    
					j_nova_pos.getPeao(y).setPosIni();

					for (int i = 1; i < nova_pos + 1 ; i++) {
						j_nova_pos.getPeao(y).getProx();
					}

					j_nova_pos.SetP1Color(PegaCor(jogador_num));

					Vetor v = (Vetor) j_nova_pos.getPeao(y).getPosCorr();

					int novo_x = v.RetornaX();
					int novo_y = v.RetornaY();

					j_nova_pos.SetP1X(novo_x);
					j_nova_pos.SetP1Y(novo_y);

					Jogo.getJogo().getCaminho(novo_x, novo_y).AdicionaPeao(j_nova_pos.getPeao(y), j_nova_pos);
				}
			  }
			}

			TextAreaLog.getTextAreaLog().printLog("Jogo carregado!");
    		ChamaProxJogador(JogadoresController.getJogadoresController().getJogadorTurno());

		}else {
			TextAreaLog.getTextAreaLog().printLog("Erro ao escolher arquivo!");
		}
	}
	
	private void ChamaProxJogador(int turno) throws FileNotFoundException, BadLocationException{
    	switch (turno) {
    	case 0:
    		TextAreaLog.getTextAreaLog().printLog("Vez do jogador vermelho!");
    		break;
        case 1:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador verde!");
    		break;
        case 2:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador amarelo!");
    		break;
        case 3:
        	TextAreaLog.getTextAreaLog().printLog("Vez do jogador azul!");
    		break;
    	}
    }

	private Color PegaCor (int jogador){
    	switch (jogador) {
        case 1:
        	return Color.RED;
        case 2:
        	return Color.GREEN;
        case 3:
        	return Color.YELLOW;
        case 4:
        	return Color.BLUE;
    	}
    	return null;
    }
	
	private int defineXInicial(int jogador, int peao){
    	switch (jogador) {
        case 1:
        	switch (peao) {
        	case 0:
        		return 4;
        	case 1:
        		return 4;
        	case 2:
        		return 1;
        	case 3:
        		return 1;
        	}
        case 2:
        	switch (peao) {
        	case 0:
        		return 1;
        	case 1:
        		return 1;
        	case 2:
        		return 4;
        	case 3:
        		return 4;
        	}
        case 3:
        	switch (peao) {
        	case 0:
        		return 10;
        	case 1:
        		return 10;
        	case 2:
        		return 13;
        	case 3:
        		return 13;
        	}
        case 4:
        	switch (peao) {
        	case 0:
        		return 13;
        	case 1:
        		return 13;
        	case 2:
        		return 10;
        	case 3:
        		return 10;
        	}
    	}
    	return 0;
    }

    private int defineYInicial(int jogador, int peao){
    	switch (jogador) {
    	case 1:
    		switch (peao) {
    		case 0:
    			return 1;
    		case 1:
    			return 4;
    		case 2:
    			return 1;
    		case 3:
    			return 4;
    		}
        case 2:
        	switch (peao) {
        	case 0:
        		return 10;
        	case 1:
        		return 13;
        	case 2:
        		return 10;
        	case 3:
        		return 13;
        	}
        case 3:
        	switch (peao) {
        	case 0:
        		return 13;
        	case 1:
        		return 10;
        	case 2:
        		return 13;
        	case 3:
        		return 10;
        	}
        case 4:
        	switch (peao) {
        	case 0:
        		return 4;
        	case 1:
        		return 1;
        	case 2:
        		return 4;
        	case 3:
        		return 1;
        	}
    	}
    	return 0;
    }
    
}
