/**
 * Classe Labirinto.java extendida de Personagem.
 * Configura o caminho que o fantasma irá seguir e seleciona
 * a imagem que deverá ser imprimido na tela.
 *  
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.Image;
import java.util.Random;

public class Opponent extends Personagem {
	//número de id do fantasma
	int id_fant;

	//delay do fantasma
	static int delayMove;

	/**
	 * Construtor do fantasma, chama o construtor da super classe
	 * e passa o ID do fantasma.
	 * @param id	identifica o fantasma com um inteiro
	 */
	public Opponent(int id) {
		super(0, 0);
		id_fant = id;
	}

	/**
	 * Faz random na direção que o fantasma vai, verificando para
	 * ele não ir pra direção contraria de que já está indo. 
	 * @return int, inteiro do movimento aleatorio que o
	 * 			fantasma deve seguir
	 */
	private int random() {
		Random rand = new Random();
		int move;
		do {
			move = rand.nextInt(4);
		} while ((movBuffer == Const.ESQUERDA && move == 2)
				|| (movBuffer == Const.DIREITA && move == 3)
				|| (movBuffer == Const.SOBE && move == 0)
				|| (movBuffer == Const.DESCE && move == 1));

		return move;
	}

	/**
	 * Movimenta o fantasma dependendo do nível e o imprime na tela
	 */
	public void move() {
		Grafo labirinto = Game.labirinto;
		boolean moveu = false;
		boolean seguir = false;

		labirinto.matriz_no[X][Y].imprFant[id_fant] = false;
		labirinto.matriz_no[X][Y].repaint();

		if (Player.nivel == 2) {
			if ((labirinto.pacman.X <= X + 3) && (labirinto.pacman.X >= X - 3)) {
				if ((labirinto.pacman.Y <= Y + 3)
						&& (labirinto.pacman.Y >= Y - 3)) {
					seguir = true;
				}
			}
		}

		if ((Player.ehSuper <= 0) && ((Player.nivel == 3) || (seguir))) {
			if (Y < labirinto.pacman.Y) {
				if (labirinto.matriz_no[X][Y].pont_dw) {
					movBuffer = Const.DESCE;
					Y++;
					moveu = true;
				}
			}
			if ((Y > labirinto.pacman.Y) && (!moveu)) {
				if (labirinto.matriz_no[X][Y].pont_up) {
					movBuffer = Const.SOBE;
					Y--;
					moveu = true;
				}
			}
			if ((X < labirinto.pacman.X) && (!moveu)) {
				if (labirinto.matriz_no[X][Y].pont_rt) {
					movBuffer = Const.DIREITA;
					X++;
					moveu = true;
				}
			}
			if ((X > labirinto.pacman.X) && (!moveu)) {
				if (labirinto.matriz_no[X][Y].pont_lf) {
					movBuffer = Const.ESQUERDA;
					X--;
					moveu = true;
				}
			}
		}

		if (!moveu) {
			do {
				switch (random()) {
				default:
					break;
				case 0:
					if (labirinto.matriz_no[X][Y].pont_dw) {
						Y++;
						movBuffer = Const.DESCE;
						moveu = true;
					}
					break;
				case 1:
					if (labirinto.matriz_no[X][Y].pont_up) {
						Y--;
						movBuffer = Const.SOBE;
						moveu = true;
					}
					break;
				case 2:
					if (labirinto.matriz_no[X][Y].pont_rt) {
						movBuffer = Const.DIREITA;
						X++;
						moveu = true;
					}
					break;
				case 3:
					if (labirinto.matriz_no[X][Y].pont_lf) {
						movBuffer = Const.ESQUERDA;
						X--;
						moveu = true;
					}
				}
			} while (!moveu);
		}

		verificaCoordenada();

		labirinto.matriz_no[X][Y].imprFant[id_fant] = true;
		labirinto.matriz_no[X][Y].repaint();

	}

	/**
	 * Seleciona  a figura do fantasma que deverá ser impresso
	 * @return Image, imagem que pertence ao determinado fantasma 
	 */
	public Image getImage() {
		if (Player.ehSuper < 0) {
			switch (id_fant) {
			default:
				return null;
			case 0:
				return Gfx.fantasma0;
			case 1:
				return Gfx.fantasma1;
			case 2:
				return Gfx.fantasma2;
			case 3:
				return Gfx.fantasma3;
			}
		} else {
			switch (id_fant) {
			default:
				return null;
			case 0:
				return Gfx.fantasma0_super;
			case 1:
				return Gfx.fantasma1_super;
			case 2:
				return Gfx.fantasma2_super;
			case 3:
				return Gfx.fantasma3_super;
			}
		}
	}

}