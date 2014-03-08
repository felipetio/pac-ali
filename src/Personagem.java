/**
 * Classe Personagem.java guarda posi�oes iniciais e atuais dos personagems, e 
 * buffer de movimenta��o.
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

public class Personagem {
	
	//Posicao X e Y inicial do personagem
	int inicialX;
	//Posicao X e Y inicial do personagem
	int inicialY;
	
	//Posicao X e Y atual do personagem
	int X;
	//Posicao X e Y atual do personagem
	int Y;
	
	//Buffer de movimenta��o
	int movBuffer;
	
	/**
	 * Construtor da classe que inicia as posicoes atuais e iniciais como passado
	 * pelos parametros. Inicia Buffer para esquerda.
	 * @param pX inteiro de posi��o X do personagem.
	 * @param pY inteiro de posi��o Y do personagem.
	 */
	public Personagem(int pX, int pY){
		inicialX= pX; 
		inicialY= pY;  
		X= pX;
		Y= pY;
		movBuffer= Const.ESQUERDA;
	}

	/**
	 * Volta personagem pra posi��o inicial
	 */
	public void reset(){
		X= inicialX; 
		Y= inicialY; 
	}
	
	/**
	 * Tratamendo da passagem do tunel
	 */
	public void verificaCoordenada() {
		if (X < 0) {
			X = Const.TAMANHO - 1;
		} else {
			X = X % Const.TAMANHO;
		}

		if (Y < 0) {
			Y = Const.TAMANHO - 1;
		} else {
			Y = Y % Const.TAMANHO;
		}
	}
}
