/**
 * Classe No_grafo.java extende JPanel, representa o nó do grafo e é a classe
 * responsavel por imprimir na tela o jogo. 
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.*;
import javax.swing.JPanel;

public class No_grafo extends JPanel {

	//As variaveis pont_** é true se no grafo existe um caminho para os nós
	//acima (up), abaixo (dw), esquerda (lf) e direita (rt).

	//Se pode se mover pra cima
	boolean pont_up;

	//Se pode se mover pra baixo
	boolean pont_dw;

	//Se pode se mover pra esquerda
	boolean pont_lf;

	//Se pode se mover pra direita
	boolean pont_rt;

	//Se o nó eh uma prisao pros fantasmas
	boolean prisao;

	//Se tem vitamina (1), super-vitamina (2), ou nada (0)
	int vitamina;

	//Verifica se é para imprimir pacman
	boolean imprPac;

	//Verifica se é para imprimir os fantasmas
	boolean imprFant[] = new boolean[Const.NUM_FANT];
	
	//Verifica se é para imprimir Cherry
	boolean imprCherry;

	/**
	 * Construtor da classe que inicia suas variaveis.
	 */
	public No_grafo() {
		vitamina = 1;
		prisao = false;
		pont_up = true;
		pont_dw = true;
		pont_lf = true;
		pont_rt = true;
	}

	/**
	 * Seleciona a imagem da vitamina, simples (1), super (2) ou
	 * nao retorna (0).
	 * @return Image, imagem da respectiva vitamina.
	 * 
	 * @uml.property name="vitamina"
	 */
	private Image getVitamina() {
		if (vitamina == 1) {
			return Gfx.vitamina;
		} else if (vitamina == 2) {
			return Gfx.vitamina_super;
		}
		return null;
	}

	/**
	 * Seleciona a imagem de acordo com as variaveis pont_**.
	 * @return Image, imagem do respectivo fundo do labirinto.
	 */
	private Image getFundo() {
		if (!pont_up) {
			if (!pont_dw) {
				if (!pont_lf) {
					if (!pont_rt) {
						//Nao pode se mover
						return Gfx.block;
					} else {
						//Somente para a direita
						return Gfx.up_dw_lf;
					}
				} else {
					if (!pont_rt) {
						//Somente para a esquerda
						return Gfx.up_dw_rt;
					} else {
						//Esquerda e direita
						return Gfx.up_dw;
					}
				}
			} else {
				if (!pont_lf) {
					if (!pont_rt) {
						//Somente para baixo
						return Gfx.up_lf_rt;
					} else {
						//Baixo e direita
						return Gfx.up_lf;
					}
				} else {
					if (!pont_rt) {
						//Baixo e esquerda
						return Gfx.up_rt;
					} else {
						//Baixo, esquerda e direita
						return Gfx.up;
					}
				}
			}
		} else {
			if (!pont_dw) {
				if (!pont_lf) {
					if (!pont_rt) {
						//Somente para cima
						return Gfx.dw_lf_rt;
					} else {
						if (!prisao) {
							//Cima e direita
							return Gfx.dw_lf;
						} else {
							return Gfx.prisao_lf;
						}
					}
				} else {
					if (!pont_rt) {
						if (!prisao) {
							//Cima e esquerda
							return Gfx.dw_rt;
						} else {
							return Gfx.prisao_rt;
						}
					} else {
						//Cima, esquerda e direita
						return Gfx.dw;
					}
				}
			} else {
				if (!pont_lf) {
					if (!pont_rt) {
						//Cima e baixo
						return Gfx.lf_rt;
					} else {
						//Cima, baixo e direita
						return Gfx.lf;
					}
				} else {
					if (!pont_rt) {
						//Cima, baixo e esquerda
						return Gfx.rt;
					} else {
						//Cima, baixo, esquerda e direita
						return Gfx.vazio;
					}
				}
			}
		}
	}

	/**
	 * Metodo derivado de swing que imprime os componentes do nó e pega as imagens
	 * dos personagens.
	 * @param g
	 * @see Player.java
	 * @see Opponent.java
	 * @see Cherry.java
	 */
	public void paint(Graphics g) {
		Grafo labirinto = Game.labirinto;

		g.drawImage(getFundo(), 0, 0, 60, 60, this);

		if (vitamina != 0)
			g.drawImage(getVitamina(), 21, 21, this);

		if (imprPac)
			g.drawImage(labirinto.pacman.getImage(), 5, 5, this);

		for (int i = 0; i < Const.NUM_FANT; i++) {
			if (imprFant[i])
				g.drawImage(labirinto.fant[i].getImage(), 5, 5, this);
		}
		if (imprCherry)
			g.drawImage(labirinto.cherry.getImage(), 10, 10, this);
	}
}