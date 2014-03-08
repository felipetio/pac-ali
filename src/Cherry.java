/**
 * Classe Cherry.java extendida de Personagem. Personagem bonus se move aleatoriamente
 * pelo labirinto
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.Image;
import java.util.Random;

public class Cherry extends Personagem {

	//Se a cherry esta no jogo eh true
	static boolean inGame= false;
		
	/**
	 * Construtor da cereja, chama o construtor da super classe, e seta a flag
	 * inGame como true.
	 * @param pX inteiro a posição x da cereja
	 * @param pY inteiro a posição y da cereja
	 */
	public Cherry(int pX, int pY) {
		super(pX, pY);
		inGame= true;
	}

	/**
	 * Faz random na direção a cherry pretende ir
	 * @return int, inteiro do movimento aleatorio que a cherry deve seguir
	 */
	private int random() {
		Random rand = new Random();
		return rand.nextInt(4);
	}

	/**
	 * Movimenta a cereja aleatoriamente
	 */
	public void move() {
		Grafo labirinto = Game.labirinto;

		labirinto.matriz_no[X][Y].imprCherry = false;
		labirinto.matriz_no[X][Y].repaint();

		switch (random()) {
		default:
			break;
		case 0:
			if (labirinto.matriz_no[X][Y].pont_dw) {
				Y++;
				movBuffer = Const.DESCE;
			}
			break;
		case 1:
			if (labirinto.matriz_no[X][Y].pont_up) {
				Y--;
				movBuffer = Const.SOBE;
			}
			break;
		case 2:
			if (labirinto.matriz_no[X][Y].pont_rt) {
				movBuffer = Const.DIREITA;
				X++;
			}
			break;
		case 3:
			if (labirinto.matriz_no[X][Y].pont_lf) {
				movBuffer = Const.ESQUERDA;
				X--;
			}
		}

		verificaCoordenada();

		labirinto.matriz_no[X][Y].imprCherry = true;
		labirinto.matriz_no[X][Y].repaint();
		
	}
	
	/**
	 * Seleciona a imagem a ser impressa
	 * 
	 * @return Image, imagem da Cherry
	 */
	public Image getImage() {
		return Gfx.cherry;
	}

}