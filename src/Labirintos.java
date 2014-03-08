/** * Classe Labirinto.java cria o labirinto selecionado, e guarda quantidade de vitaminas * existentes em jogo. *  * @author Felipe Luis Souza Vieira * @author Matheus Victor Brum Soares * @author Tatiane Escobar Gava * @author Thiago Santana Carrazeiro */
public class Labirintos {

	//Total de vitaminas existentes no labirinto
	public int totalBolinhas;

	/**
	 * 
	 * @uml.property name="labirinto"
	 * @uml.associationEnd multiplicity="(0 1)"
	 */
	//Grafo em forma de matriz que representa o labirinto
	Grafo labirinto;

	/**
	 * Construtor da classe que cria a fase selecionada e inicia
	 * as vitaminas.
	 * @param fase  inteiro que decide qual fase deve ser criada
	 * @param labirinto  Grafo que será alterado de acordo com a fase escolhida
	 */
	public Labirintos(int fase, Grafo labirinto) {
		this.labirinto = labirinto;

		totalBolinhas = Const.TAMANHO * Const.TAMANHO;
		colocaBolinhas();

		switch (fase) {
		default:
			break;
		case 1:
			faseP1_1();
			break;
		case 2:
			faseP1_2();
			break;
		case 3:
			faseP2();
			break;
		case 4:
			fasePSub();
			break;
		}

		//Tirar bolinha da posição que o pacman e a prisao estão
		totalBolinhas -= 3;
	}

	/**
	 * Atribui uma string para cada tipo de peça para facilitar a atualizacao
	 * dos nós de acordo com o labirinto escolhido.
	 * @param matriz_no  No_grafo que será atualizado
	 * @param arg  String que determina o tipo de alteração nos pont_** do nó
	 * @param y  inteiro que determina posição em y do nó
	 * @param x  inteiro que determina posição em x do nó
	 * @see No_grafo.java
	 */
	public void pecas(No_grafo matriz_no[][], String arg, int y, int x) {
		if (arg.compareTo("dw") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("dw_lt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("dw_rt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("dw_rt_lt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("dw_up_lt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("lt_rt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("lt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("null") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("rt") == 0) {
			matriz_no[x][y].pont_up = true;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("up") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("up_dw") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("up_dw_rt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("up_dw_rt_lt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = false;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = false;
			matriz_no[x][y].vitamina = 0;
			totalBolinhas--;
		} else if (arg.compareTo("up_lt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = true;
		} else if (arg.compareTo("up_rt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = true;
			matriz_no[x][y].pont_rt = false;
		} else if (arg.compareTo("up_rt_lt") == 0) {
			matriz_no[x][y].pont_up = false;
			matriz_no[x][y].pont_dw = true;
			matriz_no[x][y].pont_lf = false;
			matriz_no[x][y].pont_rt = false;
		}
	}

	/**
	 * Espelha o labirinto
	 * @param matriz_no  No_grafo que será atualizado
	 */
	public void espelho(No_grafo matriz_no[][]) {
		int x;
		for (int i = 0; i < Const.TAMANHO; i++) {
			x = 0;
			for (int j = Const.TAMANHO - 1; j > Const.TAMANHO / 2 - 1; j--) {
				matriz_no[j][i].pont_up = matriz_no[x][i].pont_up;
				matriz_no[j][i].pont_dw = matriz_no[x][i].pont_dw;
				matriz_no[j][i].pont_lf = matriz_no[x][i].pont_rt;
				matriz_no[j][i].pont_rt = matriz_no[x][i].pont_lf;
				matriz_no[j][i].prisao = matriz_no[x][i].prisao;
				x++;
				if ((!matriz_no[j][i].pont_up) && (!matriz_no[j][i].pont_dw)
						&& (!matriz_no[j][i].pont_lf)
						&& (!matriz_no[j][i].pont_rt)) {
					matriz_no[j][i].vitamina = 0;
					totalBolinhas--;
				}
			}
		}
	}

	/**
	 * Faz os ajustes de posições iniciais do pacman, dos fantasmas e da prisao.
	 * @param pacX  inteiro que diz posição X inicial do pacman
	 * @param pacY  inteiro que diz posição Y inicial do pacman
	 * @param fantX  vetor de inteiros que diz posições X iniciais dos fantasmas
	 * @param fantY  vetor de inteiros que diz posições Y iniciais dos fantasmas
	 */
	public void ajustePos(int pacX, int pacY, int fantX[], int fantY[]) {

		labirinto.pacman.inicialX = pacX;
		labirinto.pacman.inicialY = pacY;
		labirinto.pacman.reset();
		labirinto.matriz_no[pacX][pacY].imprPac = true;
		labirinto.matriz_no[pacX][pacY].vitamina = 0;

		for (int i = 0; i < 4; i++) {
			labirinto.fant[i].inicialX = fantX[i % 2];
			labirinto.fant[i].inicialY = fantY[i % 2];
			labirinto.fant[i].reset();
			labirinto.matriz_no[labirinto.fant[i].X][labirinto.fant[i].Y].imprFant[i] = true;
			labirinto.matriz_no[labirinto.fant[i].X][labirinto.fant[i].Y].vitamina = 0;
			labirinto.matriz_no[labirinto.fant[i].X][labirinto.fant[i].Y].prisao = true;
		}
	}

	/**
	 * Coloca vitaminass, e super vitaminass no cenario. Nos 4 cantos serão colocados
	 * super vitaminas, o resto receberá vitaminas normais.
	 */
	public void colocaBolinhas() {

		for (int j = 0; j < Const.TAMANHO; j++) {
			for (int i = 0; i < Const.TAMANHO; i++) {
				labirinto.matriz_no[i][j].imprPac = false;

				for (int k = 0; k < Const.NUM_FANT; k++)
					labirinto.matriz_no[i][j].imprFant[k] = false;

				if ((i == 0 && j == 0) || (i == 0 && j == Const.TAMANHO - 1)
						|| (i == Const.TAMANHO - 1 && j == 0)
						|| (i == Const.TAMANHO - 1 && j == Const.TAMANHO - 1)) {
					labirinto.matriz_no[i][j].vitamina = 2;
				} else {
					labirinto.matriz_no[i][j].vitamina = 1;
				}
			}
		}
	}

	/**
	 * Cria metade do cenario da P1_1, chama a função de espelho que criará
	 * a outra metade, e arruma as posições dos Personagens.
	 */
	public void faseP1_1() {

		//no 0,0
		pecas(labirinto.matriz_no, "up_dw", 0, 0);

		//no 0,1
		pecas(labirinto.matriz_no, "up_dw", 0, 1);

		//no 0,2
		pecas(labirinto.matriz_no, "up_rt", 0, 2);

		//no 0,3
		pecas(labirinto.matriz_no, "up_lt", 0, 3);

		//no 0,4
		pecas(labirinto.matriz_no, "up_dw", 0, 4);

		//no 1,0
		pecas(labirinto.matriz_no, "up_lt", 1, 0);

		//no 1,1
		pecas(labirinto.matriz_no, "up_dw", 1, 1);

		//no 1,2
		pecas(labirinto.matriz_no, "dw", 1, 2);

		//no 1,3
		pecas(labirinto.matriz_no, "null", 1, 3);

		//no 1,4
		pecas(labirinto.matriz_no, "up_rt", 1, 4);

		//no 2,0
		pecas(labirinto.matriz_no, "lt_rt", 2, 0);

		//no 2,1
		pecas(labirinto.matriz_no, "up_lt", 2, 1);

		//no 2,2
		pecas(labirinto.matriz_no, "up_dw", 2, 2);

		//no 2,3
		pecas(labirinto.matriz_no, "rt", 2, 3);

		//no 2,4
		pecas(labirinto.matriz_no, "dw_lt", 2, 4);

		//no 3,0
		pecas(labirinto.matriz_no, "dw_lt", 3, 0);

		//no 3,1
		pecas(labirinto.matriz_no, "dw", 3, 1);

		//no 3,2
		pecas(labirinto.matriz_no, "up_rt", 3, 2);

		//no 3,3
		pecas(labirinto.matriz_no, "lt", 3, 3);

		//no 3,4
		pecas(labirinto.matriz_no, "up_dw", 3, 4);

		//no 4,0
		pecas(labirinto.matriz_no, "up_lt", 4, 0);

		//no 4,1
		pecas(labirinto.matriz_no, "up_dw", 4, 1);

		//no 4,2
		pecas(labirinto.matriz_no, "rt", 4, 2);

		//no 4,3
		pecas(labirinto.matriz_no, "lt_rt", 4, 3);

		//no 4,4
		pecas(labirinto.matriz_no, "dw_lt", 4, 4);

		//no 5,0
		pecas(labirinto.matriz_no, "dw_lt", 5, 0);

		//no 5,1
		pecas(labirinto.matriz_no, "up_rt", 5, 1);

		//no 5,2
		pecas(labirinto.matriz_no, "lt", 5, 2);

		//no 5,3
		pecas(labirinto.matriz_no, "dw", 5, 3);

		//no 5,4
		pecas(labirinto.matriz_no, "up_dw", 5, 4);

		//no 6,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 6, 0);

		//no 6,1
		pecas(labirinto.matriz_no, "lt", 6, 1);

		//no 6,2
		pecas(labirinto.matriz_no, "dw", 6, 2);

		//no 6,3
		pecas(labirinto.matriz_no, "up_dw", 6, 3);

		//no 6,4
		pecas(labirinto.matriz_no, "up_rt", 6, 4);

		//no 7,0
		pecas(labirinto.matriz_no, "up_dw", 7, 0);

		//no 7,1
		pecas(labirinto.matriz_no, "null", 7, 1);

		//no 7,2
		pecas(labirinto.matriz_no, "up_rt", 7, 2);

		//no 7,3
		pecas(labirinto.matriz_no, "up_lt", 7, 3);

		//no 7,4
		pecas(labirinto.matriz_no, "dw", 7, 4);

		//no 8,0
		pecas(labirinto.matriz_no, "up_lt", 8, 0);

		//no 8,1
		pecas(labirinto.matriz_no, "dw_rt", 8, 1);

		//no 8,2
		pecas(labirinto.matriz_no, "lt", 8, 2);

		//no 8,3
		pecas(labirinto.matriz_no, "dw", 8, 3);

		//no 8,4
		pecas(labirinto.matriz_no, "up_rt", 8, 4);

		//no 9,0
		pecas(labirinto.matriz_no, "dw_lt", 9, 0);

		//no 9,1
		pecas(labirinto.matriz_no, "up_dw", 9, 1);

		//no 9,2
		pecas(labirinto.matriz_no, "dw", 9, 2);

		//no 9,3
		pecas(labirinto.matriz_no, "up_dw", 9, 3);

		//no 9,4
		pecas(labirinto.matriz_no, "dw", 9, 4);

		espelho(labirinto.matriz_no);

		int FantX[] = { 4, 5 };
		int FantY[] = { 4, 4 };

		ajustePos(5, 5, FantX, FantY);
	}

	/**
	 * Cria metade do cenario da P1_2, chama a função de espelho que criará
	 * a outra metade, e arruma as posições dos Personagens.
	 */
	public void faseP1_2() {

		//no 0,0
		pecas(labirinto.matriz_no, "up_lt", 0, 0);

		//no 0,1
		pecas(labirinto.matriz_no, "up_dw", 0, 1);

		//no 0,2
		pecas(labirinto.matriz_no, "up", 0, 2);

		//no 0,3
		pecas(labirinto.matriz_no, "up_dw", 0, 3);

		//no 0,4
		pecas(labirinto.matriz_no, "up_rt", 0, 4);

		//no 1,0
		pecas(labirinto.matriz_no, "lt_rt", 1, 0);

		//no 1,1
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 1, 1);

		//no 1,2
		pecas(labirinto.matriz_no, "lt_rt", 1, 2);

		//no 1,3
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 1, 3);

		//no 1,4
		pecas(labirinto.matriz_no, "lt_rt", 1, 4);

		//no 2,0
		pecas(labirinto.matriz_no, "lt", 2, 0);

		//no 2,1
		pecas(labirinto.matriz_no, "up_dw", 2, 1);

		//no 2,2
		pecas(labirinto.matriz_no, "null", 2, 2);

		//no 2,3
		pecas(labirinto.matriz_no, "up", 2, 3);

		//no 2,4
		pecas(labirinto.matriz_no, "dw", 2, 4);

		//no 3,0
		pecas(labirinto.matriz_no, "dw_lt", 3, 0);

		//no 3,1
		pecas(labirinto.matriz_no, "up_dw", 3, 1);

		//no 3,2
		pecas(labirinto.matriz_no, "rt", 3, 2);

		//no 3,3
		pecas(labirinto.matriz_no, "dw_lt", 3, 3);

		//no 3,4
		pecas(labirinto.matriz_no, "up_rt", 3, 4);

		//no 4,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 4, 0);

		//no 4,1
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 4, 1);

		//no 4,2
		pecas(labirinto.matriz_no, "lt_rt", 4, 2);

		//no 4,3
		pecas(labirinto.matriz_no, "up_lt", 4, 3);

		//no 4,4
		pecas(labirinto.matriz_no, "dw", 4, 4);

		//no 5,0
		pecas(labirinto.matriz_no, "up_dw", 5, 0);

		//no 5,1
		pecas(labirinto.matriz_no, "up_dw", 5, 1);

		//no 5,2
		pecas(labirinto.matriz_no, "null", 5, 2);

		//no 5,3
		pecas(labirinto.matriz_no, "rt", 5, 3);

		//no 5,4
		pecas(labirinto.matriz_no, "dw_lt", 5, 4);

		//no 6,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 6, 0);

		//no 6,1
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 6, 1);

		//no 6,2
		pecas(labirinto.matriz_no, "lt_rt", 6, 2);

		//no 6,3
		pecas(labirinto.matriz_no, "lt", 6, 3);

		//no 6,4
		pecas(labirinto.matriz_no, "up_dw", 6, 4);

		//no 7,0
		pecas(labirinto.matriz_no, "up_lt", 7, 0);

		//no 7,1
		pecas(labirinto.matriz_no, "up_dw", 7, 1);

		//no 7,2
		pecas(labirinto.matriz_no, "null", 7, 2);

		//no 7,3
		pecas(labirinto.matriz_no, "dw", 7, 3);

		//no 7,4
		pecas(labirinto.matriz_no, "up_rt", 7, 4);

		//no 8,0
		pecas(labirinto.matriz_no, "dw_lt", 8, 0);

		//no 8,1
		pecas(labirinto.matriz_no, "up_rt", 8, 1);

		//no 8,2
		pecas(labirinto.matriz_no, "lt", 8, 2);

		//no 8,3
		pecas(labirinto.matriz_no, "up", 8, 3);

		//no 8,4
		pecas(labirinto.matriz_no, "dw", 8, 4);

		//no 9,0
		pecas(labirinto.matriz_no, "up_dw", 9, 0);

		//no 9,1
		pecas(labirinto.matriz_no, "dw", 9, 1);

		//no 9,2
		pecas(labirinto.matriz_no, "dw_rt", 9, 2);

		//no 9,3
		pecas(labirinto.matriz_no, "dw_lt", 9, 3);

		//no 9,4
		pecas(labirinto.matriz_no, "up_dw", 9, 4);

		espelho(labirinto.matriz_no);

		int FantX[] = { 4, 5 };
		int FantY[] = { 5, 5 };

		ajustePos(5, 8, FantX, FantY);
	}

	/**
	 * Cria metade do cenario da P2, chama a função de espelho que criará
	 * a outra metade, e arruma as posições dos Personagens.
	 */
	public void faseP2() {

		//no 0,0
		pecas(labirinto.matriz_no, "up_lt", 0, 0);

		//no 0,1
		pecas(labirinto.matriz_no, "up_dw", 0, 1);

		//no 0,2
		pecas(labirinto.matriz_no, "up_rt", 0, 2);

		//no 0,3
		pecas(labirinto.matriz_no, "up_lt", 0, 3);

		//no 0,4
		pecas(labirinto.matriz_no, "up_dw", 0, 4);

		//no 1,0
		pecas(labirinto.matriz_no, "dw_lt", 1, 0);

		//no 1,1
		pecas(labirinto.matriz_no, "up", 1, 1);

		//no 1,2
		pecas(labirinto.matriz_no, "null", 1, 2);

		//no 1,3
		pecas(labirinto.matriz_no, "dw", 1, 3);

		//no 1,4
		pecas(labirinto.matriz_no, "up", 1, 4);

		//no 2,0
		pecas(labirinto.matriz_no, "up_dw", 2, 0);

		//no 2,1
		pecas(labirinto.matriz_no, "rt", 2, 1);

		//no 2,2
		pecas(labirinto.matriz_no, "dw_lt", 2, 2);

		//no 2,3
		pecas(labirinto.matriz_no, "up", 2, 3);

		//no 2,4
		pecas(labirinto.matriz_no, "dw_rt", 2, 4);

		//no 3,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 3, 0);

		//no 3,1
		pecas(labirinto.matriz_no, "lt", 3, 1);

		//no 3,2
		pecas(labirinto.matriz_no, "up_dw", 3, 2);

		//no 3,3
		pecas(labirinto.matriz_no, "null", 3, 3);

		//no 3,4
		pecas(labirinto.matriz_no, "up_dw", 3, 4);

		//no 4,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 4, 0);

		//no 4,1
		pecas(labirinto.matriz_no, "lt_rt", 4, 1);

		//no 4,2
		pecas(labirinto.matriz_no, "up_lt", 4, 2);

		//no 4,3
		pecas(labirinto.matriz_no, "rt", 4, 3);

		//no 4,4
		pecas(labirinto.matriz_no, "dw_lt", 4, 4);

		//no 5,0
		pecas(labirinto.matriz_no, "up_dw", 5, 0);

		//no 5,1
		pecas(labirinto.matriz_no, "null", 5, 1);

		//no 5,2
		pecas(labirinto.matriz_no, "dw_rt", 5, 2);

		//no 5,3
		pecas(labirinto.matriz_no, "dw_lt", 5, 3);

		//no 5,4
		pecas(labirinto.matriz_no, "up", 5, 4);

		//no 6,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 6, 0);

		//no 6,1
		pecas(labirinto.matriz_no, "lt", 6, 1);

		//no 6,2
		pecas(labirinto.matriz_no, "up_dw", 6, 2);

		//no 6,3
		pecas(labirinto.matriz_no, "up", 6, 3);

		//no 6,4
		pecas(labirinto.matriz_no, "dw_rt", 6, 4);

		//no 7,0
		pecas(labirinto.matriz_no, "up_lt", 7, 0);

		//no 7,1
		pecas(labirinto.matriz_no, "dw", 7, 1);

		//no 7,2
		pecas(labirinto.matriz_no, "up", 7, 2);

		//no 7,3
		pecas(labirinto.matriz_no, "dw", 7, 3);

		//no 7,4
		pecas(labirinto.matriz_no, "up", 7, 4);

		//no 8,0
		pecas(labirinto.matriz_no, "lt_rt", 8, 0);

		//no 8,1
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 8, 1);

		//no 8,2
		pecas(labirinto.matriz_no, "lt_rt", 8, 2);

		//no 8,3
		pecas(labirinto.matriz_no, "up_lt", 8, 3);

		//no 8,4
		pecas(labirinto.matriz_no, "dw_rt", 8, 4);

		//no 9,0
		pecas(labirinto.matriz_no, "dw_lt", 9, 0);

		//no 9,1
		pecas(labirinto.matriz_no, "up_dw", 9, 1);

		//no 9,2
		pecas(labirinto.matriz_no, "dw", 9, 2);

		//no 9,3
		pecas(labirinto.matriz_no, "dw", 9, 3);

		//no 9,4
		pecas(labirinto.matriz_no, "up_dw", 9, 4);

		espelho(labirinto.matriz_no);

		int FantX[] = { 4, 5 };
		int FantY[] = { 4, 4 };

		ajustePos(5, 7, FantX, FantY);
	}

	/**
	 * Cria metade do cenario da P Sub, chama a função de espelho que criará
	 * a outra metade, e arruma as posições dos Personagens.
	 */
	public void fasePSub() {

		//no 0,0
		pecas(labirinto.matriz_no, "up_lt", 0, 0);

		//no 0,1
		pecas(labirinto.matriz_no, "up", 0, 1);

		//no 0,2
		pecas(labirinto.matriz_no, "up_dw", 0, 2);

		//no 0,3
		pecas(labirinto.matriz_no, "null", 0, 3);

		//no 0,4
		pecas(labirinto.matriz_no, "up_dw", 0, 4);

		//no 1,0
		pecas(labirinto.matriz_no, "lt_rt", 1, 0);

		//no 1,1
		pecas(labirinto.matriz_no, "lt", 1, 1);

		//no 1,2
		pecas(labirinto.matriz_no, "up_dw", 1, 2);

		//no 1,3
		pecas(labirinto.matriz_no, "dw", 1, 3);

		//no 1,4
		pecas(labirinto.matriz_no, "up_rt", 1, 4);

		//no 2,0
		pecas(labirinto.matriz_no, "lt_rt", 2, 0);

		//no 2,1
		pecas(labirinto.matriz_no, "dw_lt", 2, 1);

		//no 2,2
		pecas(labirinto.matriz_no, "up_rt", 2, 2);

		//no 2,3
		pecas(labirinto.matriz_no, "up_lt", 2, 3);

		//no 2,4
		pecas(labirinto.matriz_no, "rt", 2, 4);

		//no 3,0
		pecas(labirinto.matriz_no, "dw_lt", 3, 0);

		//no 3,1
		pecas(labirinto.matriz_no, "up", 3, 1);

		//no 3,2
		pecas(labirinto.matriz_no, "dw", 3, 2);

		//no 3,3
		pecas(labirinto.matriz_no, "rt", 3, 3);

		//no 3,4
		pecas(labirinto.matriz_no, "dw_lt", 3, 4);

		//no 4,0
		pecas(labirinto.matriz_no, "up_dw_rt_lt", 4, 0);

		//no 4,1
		pecas(labirinto.matriz_no, "lt", 4, 1);

		//no 4,2
		pecas(labirinto.matriz_no, "up_rt", 4, 2);

		//no 4,3
		pecas(labirinto.matriz_no, "lt_rt", 4, 3);

		//no 4,4
		pecas(labirinto.matriz_no, "up_lt", 4, 4);

		//no 5,0
		pecas(labirinto.matriz_no, "up_lt", 5, 0);

		//no 5,1
		pecas(labirinto.matriz_no, "dw_rt", 5, 1);

		//no 5,2
		pecas(labirinto.matriz_no, "dw_lt", 5, 2);

		//no 5,3
		pecas(labirinto.matriz_no, "null", 5, 3);

		//no 5,4
		pecas(labirinto.matriz_no, "rt", 5, 4);

		//no 6,0
		pecas(labirinto.matriz_no, "lt", 6, 0);

		//no 6,1
		pecas(labirinto.matriz_no, "up", 6, 1);

		//no 6,2
		pecas(labirinto.matriz_no, "up_dw", 6, 2);

		//no 6,3
		pecas(labirinto.matriz_no, "dw_rt", 6, 3);

		//no 6,4
		pecas(labirinto.matriz_no, "lt_rt", 6, 4);

		//no 7,0
		pecas(labirinto.matriz_no, "lt_rt", 7, 0);

		//no 7,1
		pecas(labirinto.matriz_no, "lt_rt", 7, 1);

		//no 7,2
		pecas(labirinto.matriz_no, "up_lt", 7, 2);

		//no 7,3
		pecas(labirinto.matriz_no, "up_dw", 7, 3);

		//no 7,4
		pecas(labirinto.matriz_no, "rt", 7, 4);

		//no 8,0
		pecas(labirinto.matriz_no, "lt_rt", 8, 0);

		//no 8,1
		pecas(labirinto.matriz_no, "dw_lt", 8, 1);

		//no 8,2
		pecas(labirinto.matriz_no, "null", 8, 2);

		//no 8,3
		pecas(labirinto.matriz_no, "up", 8, 3);

		//no 8,4
		pecas(labirinto.matriz_no, "dw", 8, 4);

		//no 9,0
		pecas(labirinto.matriz_no, "dw_lt", 9, 0);

		//no 9,1
		pecas(labirinto.matriz_no, "up_dw", 9, 1);

		//no 9,2
		pecas(labirinto.matriz_no, "dw_rt", 9, 2);

		//no 9,3
		pecas(labirinto.matriz_no, "lt_rt", 9, 3);

		//no 9,4
		pecas(labirinto.matriz_no, "dw_lt", 9, 4);

		espelho(labirinto.matriz_no);

		int FantX[] = { 4, 5 };
		int FantY[] = { 9, 9 };

		ajustePos(5, 3, FantX, FantY);
	}

}