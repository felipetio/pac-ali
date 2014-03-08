/**
 * Classe Player.java extendida de Personagem. Move com o pacman, apartir do
 * selecionado nas setas, guarda informações principais do pacman, seleciona a
 * imagem que será exibida e verifica se o pacman comeu o fantasma ou vice
 * versa.
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.Image;

public class Player extends Personagem {

	//Quantidade de vidas do pacman
	int vidas;

	//Pontuação do jogador
	int pontos;

	//Tempo em que o personagem pode ser super pacman
	static int ehSuper;

	//Movimento anterior do pacman
	int lastMove;

	//Numero da fase em que o jogador está
	int fase;

	//Numero do nivel em que o jogador está
	static int nivel;

	/**
	 * Construtor do pacman, chama o construtor da super classe, seleciona a
	 * fase que será iniciado, e o level. Inicia as variaveis de informaçoes do
	 * pacman.
	 * 
	 * @param fase
	 *            numero da fase em que o pacman inicia o jogo
	 * @param nivelInicio
	 *            numero do level em que o pacman inicia
	 */

	public Player(int fase, int nivelInicio) {
		super(0, 0);
		this.fase = fase;
		nivel = nivelInicio;
		movBuffer = 37;
		vidas = Const.NUM_VIDAS;
		pontos = 0;
		ehSuper = 0;
	}

	/**
	 * Verifica se o pacman e algum fantasma estão no mesmo nó do grafo caso
	 * esteja, verifica se o pacman é super. Caso sim ele come o fantasma, caso
	 * não ele é comido pelo fantasma. Verifica tambem se o pacman comeu uma cherry
	 * 
	 * @param i
	 *            inteiro que identifica os fantasmas
	 * @return int, inteiro 1 se o pacman morreu e o jogo acabou 2 se o pacman
	 *         morreu mas ainda tem vidas sobrando 0 caso ele não morra
	 */
	public int comer(int i) {
		Grafo labirinto = Game.labirinto;
		
		if(Cherry.inGame){
			if ((labirinto.cherry.X == X) && (labirinto.cherry.Y == Y)){
				labirinto.matriz_no[X][Y].imprCherry = false;
				labirinto.matriz_no[X][Y].repaint();
				Cherry.inGame= false;
				pontos += 100;	
			}
		}

		if ((labirinto.fant[i].X == X) && (labirinto.fant[i].Y == Y)) {
			if (ehSuper > 0) {
				labirinto.matriz_no[X][Y].imprFant[i] = false;
				labirinto.matriz_no[X][Y].repaint();
				labirinto.fant[i].reset();
				labirinto.matriz_no[labirinto.fant[i].X][labirinto.fant[i].Y].imprFant[i] = true;
				labirinto.matriz_no[labirinto.fant[i].X][labirinto.fant[i].Y]
						.repaint();
				pontos += 50;
			} else {
				vidas--;
				pontos /= 2;
				if (vidas <= 0) {
					return 1;
				} else {
					labirinto.matriz_no[X][Y].imprPac = false;
					labirinto.matriz_no[X][Y].repaint();
					reset();
					labirinto.matriz_no[X][Y].imprPac = true;
					labirinto.matriz_no[X][Y].repaint();

					for (int j = 0; j < Const.NUM_FANT; j++) {
						labirinto.matriz_no[labirinto.fant[j].X][labirinto.fant[j].Y].imprFant[j] = false;
						labirinto.matriz_no[labirinto.fant[j].X][labirinto.fant[j].Y]
								.repaint();
						labirinto.fant[j].reset();
						labirinto.matriz_no[labirinto.fant[j].X][labirinto.fant[j].Y].imprFant[j] = true;
						labirinto.matriz_no[labirinto.fant[j].X][labirinto.fant[j].Y]
								.repaint();
					}
					return 2;
				}
			}
		}
		return 0;
	}

	/**
	 * Move o pacman na tela, e come as vitaminas em seu caminho dependendo da
	 * seta digitada. Caso seja digitado F2 reinicia o jogo, caso F5 entra o
	 * cheat do jogo, que permite incrementar o numero de vidas e ficar super.
	 */
	public void move() {
		Grafo labirinto = Game.labirinto;
		int aux;

		labirinto.matriz_no[X][Y].imprPac = false;
		labirinto.matriz_no[X][Y].repaint();

		lastMove = movBuffer;

		if (movBuffer == Const.SOBE) {
			if (labirinto.matriz_no[X][Y].pont_up)
				Y--;
		} else if (movBuffer == Const.DIREITA) {
			if (labirinto.matriz_no[X][Y].pont_rt)
				X++;
		} else if (movBuffer == Const.DESCE) {
			if (labirinto.matriz_no[X][Y].pont_dw)
				Y++;
		} else if (movBuffer == Const.ESQUERDA) {
			if (labirinto.matriz_no[X][Y].pont_lf)
				X--;
		} else if (movBuffer == 116) {
			//CHEAT
			ehSuper = 21;
			vidas++;
		} else if (movBuffer == 113) {
			//Novo jogo
			labirinto.reset();
			return;
		}

		verificaCoordenada();

		if (labirinto.matriz_no[X][Y].vitamina == 2) {
			labirinto.labirinto.totalBolinhas--;
			labirinto.pacman.pontos += 20;
			ehSuper = 21;
		} else if (labirinto.matriz_no[X][Y].vitamina == 1) {
			labirinto.labirinto.totalBolinhas--;
			labirinto.pacman.pontos += 5;
		}
		ehSuper--;

		labirinto.matriz_no[X][Y].vitamina = 0;
		labirinto.matriz_no[X][Y].imprPac = true;
		labirinto.matriz_no[X][Y].repaint();

	}

	/**
	 * Seleciona a imagem que deve ser impresso dependendo do movimento do
	 * pacman.
	 * 
	 * @return Image, imagem do pacman que deverá ser impresso
	 */
	public Image getImage() {
		if (movBuffer == 37) {
			return Gfx.pacmamLf;
		} else if (movBuffer == 38) {
			if (lastMove == Const.ESQUERDA)
				return Gfx.pacmamUp_Lf;
			else
				return Gfx.pacmamUp_Rt;
		} else if (movBuffer == 39) {
			return Gfx.pacmamRt;
		} else if (movBuffer == 40) {
			if (lastMove == Const.ESQUERDA)
				return Gfx.pacmamDw_Lf;
			else
				return Gfx.pacmamDw_Rt;

		} else
			return null;
	}
}