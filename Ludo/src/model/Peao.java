package model;

import java.awt.Color;
import model.lista.Lista;
import view.Peca;


public class Peao {
	public Lista lst;
	Peca p;
	Peca p1;
	public int x_final, y_final;
	
	public Peao(String cor_jogador) {
		lst = new Lista();
		p = new Peca(); //peao q cobre o espaco branco na casa inicial
		p1 = new Peca(); //peao q anda pelo tabuleiro
		
		PintaP(cor_jogador); //pinta peca q anda pelo tabuleiro q inicialmente se encontra na casa vermelha
		PopulaLista(cor_jogador);
	}
	
	public Lista getLst() {
		return lst;
	}
	
	public Peca getP1() {
		return p1;		
	}
	
	public Peca getP() {
		return p;		
	}
	
	public int getXFinal() {
		return x_final;
	}
	
	public int getYFinal() {
		return y_final;
	}
	
	public int getPos() {
		return lst.pos;
	}
	
	public void setP1(int x, int y) {
		p1.x = x;
		p1.y = y;
	}
	
	private void PintaP(String cor_jogador) {
		p1.a = Color.RED;
	}
	
	private void PopulaLista(String cor_jogador) {
		switch(cor_jogador) {
		case "vermelho":
			lst.insIni(new Vetor(6,1)); // 1- casa inicio vermelho
			lst.insFin(new Vetor(6,2)); // 2
			lst.insFin(new Vetor(6,3)); // 3
			lst.insFin(new Vetor(6,4)); // 4
			lst.insFin(new Vetor(6,5)); // 5
			lst.insFin(new Vetor(5,6)); // 6
			lst.insFin(new Vetor(4,6)); // 7
			lst.insFin(new Vetor(3,6)); // 8
			lst.insFin(new Vetor(2,6)); // 9
			lst.insFin(new Vetor(1,6)); // 10- casa preta
			lst.insFin(new Vetor(0,6)); // 11
			lst.insFin(new Vetor(0,7)); // 12
			lst.insFin(new Vetor(0,8)); // 13
			lst.insFin(new Vetor(1,8)); // 14- casa inicio verde
			lst.insFin(new Vetor(2,8)); // 15
			lst.insFin(new Vetor(3,8)); // 16
			lst.insFin(new Vetor(4,8)); // 17
			lst.insFin(new Vetor(5,8)); // 18
			lst.insFin(new Vetor(6,9)); // 19
			lst.insFin(new Vetor(6,10)); // 20
			lst.insFin(new Vetor(6,11)); // 21
			lst.insFin(new Vetor(6,12)); // 22
			lst.insFin(new Vetor(6,13)); // 23- casa preta
			lst.insFin(new Vetor(6,14)); // 24
			lst.insFin(new Vetor(7,14)); // 25
			lst.insFin(new Vetor(8,14)); // 26
			lst.insFin(new Vetor(8,13)); // 27- casa inicio amarelo	
			lst.insFin(new Vetor(8,12)); // 28
			lst.insFin(new Vetor(8,11)); // 29
			lst.insFin(new Vetor(8,10)); // 30
			lst.insFin(new Vetor(8,9)); // 31
			lst.insFin(new Vetor(9,8)); // 32
			lst.insFin(new Vetor(10,8)); // 33
			lst.insFin(new Vetor(11,8)); // 34
			lst.insFin(new Vetor(12,8)); // 35
			lst.insFin(new Vetor(13,8)); // 36- casa preta
			lst.insFin(new Vetor(14,8)); // 37
			lst.insFin(new Vetor(14,7)); // 38
			lst.insFin(new Vetor(14,6)); // 39
			lst.insFin(new Vetor(13,6)); // 40- casa inicio azul
			lst.insFin(new Vetor(12,6)); // 41
			lst.insFin(new Vetor(11,6)); // 42
			lst.insFin(new Vetor(10,6)); // 43
			lst.insFin(new Vetor(9,6)); // 44
			lst.insFin(new Vetor(8,5)); // 45
			lst.insFin(new Vetor(8,4)); // 46
			lst.insFin(new Vetor(8,3)); // 47
			lst.insFin(new Vetor(8,2)); // 48
			lst.insFin(new Vetor(8,1)); // 49-  casa preta
			lst.insFin(new Vetor(8,0)); // 50
			lst.insFin(new Vetor(7,0)); // 51

			//caminho vermelho
			lst.insFin(new Vetor(7,1)); // 52
			lst.insFin(new Vetor(7,2)); // 53
			lst.insFin(new Vetor(7,3)); // 54
			lst.insFin(new Vetor(7,4)); // 55
			lst.insFin(new Vetor(7,5)); // 56
			
			//final
			lst.insFin(new Vetor(7,6)); // 57

			x_final = 7;
			y_final = 6;
			
			lst.posIni();
			
			break;
		case "amarelo":

			lst.insIni(new Vetor(8,13)); // 1- casa inicio amarelo
			lst.insFin(new Vetor(8,12)); // 2
			lst.insFin(new Vetor(8,11)); // 3
			lst.insFin(new Vetor(8,10)); // 4
			lst.insFin(new Vetor(8,9)); // 5
			lst.insFin(new Vetor(9,8)); // 6
			lst.insFin(new Vetor(10,8)); // 7
			lst.insFin(new Vetor(11,8)); // 8
			lst.insFin(new Vetor(12,8)); // 9
			lst.insFin(new Vetor(13,8)); // 10- casa preta
			lst.insFin(new Vetor(14,8)); // 11
			lst.insFin(new Vetor(14,7)); // 12
			lst.insFin(new Vetor(14,6)); // 13
			lst.insFin(new Vetor(13,6)); // 14- casa inicio azul
			lst.insFin(new Vetor(12,6)); // 15
			lst.insFin(new Vetor(11,6)); // 16
			lst.insFin(new Vetor(10,6)); // 17
			lst.insFin(new Vetor(9,6)); // 18
			lst.insFin(new Vetor(8,5)); // 19
			lst.insFin(new Vetor(8,4)); // 20
			lst.insFin(new Vetor(8,3)); // 21
			lst.insFin(new Vetor(8,2)); // 22
			lst.insFin(new Vetor(8,1)); // 23-  casa preta
			lst.insFin(new Vetor(8,0)); // 24
			lst.insFin(new Vetor(7,0)); // 25
			lst.insFin(new Vetor(6,0)); // 26
			lst.insFin(new Vetor(6,1)); // 27- casa inicio vermelho
			lst.insFin(new Vetor(6,2)); // 28
			lst.insFin(new Vetor(6,3)); // 29
			lst.insFin(new Vetor(6,4)); // 30
			lst.insFin(new Vetor(6,5)); // 31
			lst.insFin(new Vetor(5,6)); // 32
			lst.insFin(new Vetor(4,6)); // 33
			lst.insFin(new Vetor(3,6)); // 34
			lst.insFin(new Vetor(2,6)); // 35
			lst.insFin(new Vetor(1,6)); // 36- casa preta
			lst.insFin(new Vetor(0,6)); // 37
			lst.insFin(new Vetor(0,7)); // 38
			lst.insFin(new Vetor(0,8)); // 39
			lst.insFin(new Vetor(1,8)); // 40- casa inicio verde
			lst.insFin(new Vetor(2,8)); // 41
			lst.insFin(new Vetor(3,8)); // 42
			lst.insFin(new Vetor(4,8)); // 43
			lst.insFin(new Vetor(5,8)); // 44
			lst.insFin(new Vetor(6,9)); // 45
			lst.insFin(new Vetor(6,10)); // 46
			lst.insFin(new Vetor(6,11)); // 47
			lst.insFin(new Vetor(6,12)); // 48
			lst.insFin(new Vetor(6,13)); // 49- casa preta
			lst.insFin(new Vetor(6,14)); // 50
			lst.insFin(new Vetor(7,14)); // 51
			//caminho amarelo
			lst.insFin(new Vetor(7,13)); // 52
			lst.insFin(new Vetor(7,12)); // 53
			lst.insFin(new Vetor(7,11)); // 54
			lst.insFin(new Vetor(7,10)); // 55
			lst.insFin(new Vetor(7,9)); // 56

			//final
			lst.insFin(new Vetor(7,8)); // 57
			
			x_final = 7;
			y_final = 8;
			
			lst.posIni();	
			break;
		case "verde":
			lst.insIni(new Vetor(1,8)); // 1- casa inicio verde
			lst.insFin(new Vetor(2,8)); // 2
			lst.insFin(new Vetor(3,8)); // 3
			lst.insFin(new Vetor(4,8)); // 4
			lst.insFin(new Vetor(5,8)); // 5
			lst.insFin(new Vetor(6,9)); // 6
			lst.insFin(new Vetor(6,10)); // 7
			lst.insFin(new Vetor(6,11)); // 8
			lst.insFin(new Vetor(6,12)); // 8
			lst.insFin(new Vetor(6,13)); // 10- casa preta
			lst.insFin(new Vetor(6,14)); // 11
			lst.insFin(new Vetor(7,14)); // 12
			lst.insFin(new Vetor(8,14)); // 13
			lst.insFin(new Vetor(8,13)); // 14- casa inicio amarelo
			lst.insFin(new Vetor(8,12)); // 15
			lst.insFin(new Vetor(8,11)); // 16
			lst.insFin(new Vetor(8,10)); // 17
			lst.insFin(new Vetor(8,9)); // 18
			lst.insFin(new Vetor(9,8)); // 19
			lst.insFin(new Vetor(10,8)); // 20
			lst.insFin(new Vetor(11,8)); // 21
			lst.insFin(new Vetor(12,8)); // 22
			lst.insFin(new Vetor(13,8)); // 23- casa preta
			lst.insFin(new Vetor(14,8)); // 24
			lst.insFin(new Vetor(14,7)); // 25
			lst.insFin(new Vetor(14,6)); // 26
			lst.insFin(new Vetor(13,6)); // 27- casa inicio azul
			lst.insFin(new Vetor(12,6)); // 28
			lst.insFin(new Vetor(11,6)); // 29
			lst.insFin(new Vetor(10,6)); // 30
			lst.insFin(new Vetor(9,6)); // 31
			lst.insFin(new Vetor(8,5)); // 32
			lst.insFin(new Vetor(8,4)); // 33
			lst.insFin(new Vetor(8,3)); // 34
			lst.insFin(new Vetor(8,2)); // 35
			lst.insFin(new Vetor(8,1)); // 36-  casa preta
			lst.insFin(new Vetor(8,0)); // 37
			lst.insFin(new Vetor(7,0)); // 38
			lst.insFin(new Vetor(6,0)); // 39
			lst.insFin(new Vetor(6,1)); // 40- casa inicio vermelho
			lst.insFin(new Vetor(6,2)); // 41
			lst.insFin(new Vetor(6,3)); // 42
			lst.insFin(new Vetor(6,4)); // 43
			lst.insFin(new Vetor(6,5)); // 44
			lst.insFin(new Vetor(5,6)); // 45
			lst.insFin(new Vetor(4,6)); // 46
			lst.insFin(new Vetor(3,6)); // 47
			lst.insFin(new Vetor(2,6)); // 48
			lst.insFin(new Vetor(1,6)); // 49- casa preta
			lst.insFin(new Vetor(0,6)); // 50
			lst.insFin(new Vetor(0,7)); // 51

			//caminho verde
			lst.insFin(new Vetor(1,7)); // 52
			lst.insFin(new Vetor(2,7)); // 53
			lst.insFin(new Vetor(3,7)); // 54
			lst.insFin(new Vetor(4,7)); // 55
			lst.insFin(new Vetor(5,7)); // 56

			//final
			lst.insFin(new Vetor(6,7)); // 57
			
			x_final = 6;
			y_final = 7;

			lst.posIni();
			
			break;
		case "azul":
			lst.insIni(new Vetor(13,6)); // 1- casa inicio azul
			lst.insFin(new Vetor(12,6)); // 2
			lst.insFin(new Vetor(11,6)); // 3
			lst.insFin(new Vetor(10,6)); // 4
			lst.insFin(new Vetor(9,6)); // 5
			lst.insFin(new Vetor(8,5)); // 6
			lst.insFin(new Vetor(8,4)); // 7
			lst.insFin(new Vetor(8,3)); // 8
			lst.insFin(new Vetor(8,2)); // 9
			lst.insFin(new Vetor(8,1)); // 10-  casa preta
			lst.insFin(new Vetor(8,0)); // 11
			lst.insFin(new Vetor(7,0)); // 12
			lst.insFin(new Vetor(6,0)); // 13
			lst.insFin(new Vetor(6,1)); // 14- casa inicio vermelho
			lst.insFin(new Vetor(6,2)); // 15
			lst.insFin(new Vetor(6,3)); // 16
			lst.insFin(new Vetor(6,4)); // 17
			lst.insFin(new Vetor(6,5)); // 18
			lst.insFin(new Vetor(5,6)); // 19
			lst.insFin(new Vetor(4,6)); // 20
			lst.insFin(new Vetor(3,6)); // 21
			lst.insFin(new Vetor(2,6)); // 22
			lst.insFin(new Vetor(1,6)); // 23- casa preta
			lst.insFin(new Vetor(0,6)); // 24
			lst.insFin(new Vetor(0,7)); // 25
			lst.insFin(new Vetor(0,8)); // 26
			lst.insFin(new Vetor(1,8)); // 27- casa inicio verde
			lst.insFin(new Vetor(2,8)); // 28
			lst.insFin(new Vetor(3,8)); // 29
			lst.insFin(new Vetor(4,8)); // 30
			lst.insFin(new Vetor(5,8)); // 31
			lst.insFin(new Vetor(6,9)); // 32
			lst.insFin(new Vetor(6,10)); // 33
			lst.insFin(new Vetor(6,11)); // 34
			lst.insFin(new Vetor(6,12)); // 35
			lst.insFin(new Vetor(6,13)); // 36- casa preta
			lst.insFin(new Vetor(6,14)); // 37
			lst.insFin(new Vetor(7,14)); // 38
			lst.insFin(new Vetor(8,14)); // 39
			lst.insFin(new Vetor(8,13)); // 40- casa inicio amarelo
			lst.insFin(new Vetor(8,12)); // 41
			lst.insFin(new Vetor(8,11)); // 42
			lst.insFin(new Vetor(8,10)); // 43
			lst.insFin(new Vetor(8,9)); // 44
			lst.insFin(new Vetor(9,8)); // 45
			lst.insFin(new Vetor(10,8)); // 46
			lst.insFin(new Vetor(11,8)); // 47
			lst.insFin(new Vetor(12,8)); // 48
			lst.insFin(new Vetor(13,8)); // 49- casa preta
			lst.insFin(new Vetor(14,8)); // 50
			lst.insFin(new Vetor(14,7)); // 51

			//caminho azul
			lst.insFin(new Vetor(13,7)); // 52
			lst.insFin(new Vetor(12,7)); // 53
			lst.insFin(new Vetor(11,7)); // 54
			lst.insFin(new Vetor(10,7)); // 55
			lst.insFin(new Vetor(9,7)); // 56

			//final
			lst.insFin(new Vetor(8,7)); // 57
			
			x_final = 8;
			y_final = 7;
			
			lst.posIni();
			
			break;
		}
	}
}
