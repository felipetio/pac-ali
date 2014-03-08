/**
 * Classe Grafo.java cria a matriz onde é guardado o grafo, instancia os
 * personagens, e cria o primeiro labirinto.
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.GridLayout;
import javax.swing.JPanel;

public class Grafo extends JPanel {

	//Matriz com os nós do grafo.
	public No_grafo matriz_no[][] = new No_grafo[Const.TAMANHO][Const.TAMANHO];

	//Personagem pacman
	public Player pacman;

	//Vetor de personagens fantasmas
	public Opponent fant[] = new Opponent[Const.NUM_FANT];

	//Personagem cherry
	public Cherry cherry;

	//Modifica o grafo de acordo com as fases e guarda informaçoes de cada fase.
	public Labirintos labirinto;

	/**
	 * Construtor da classe que instancia os nós do grafo, e os personagens.
	 * Cria o primeiro labirinto.
	 * @see Labirintos.java
	 * @see Player.java
	 * @see Opponent.java
	 */

	public Grafo() {

		setLayout(new GridLayout(Const.TAMANHO, Const.TAMANHO));

		for (int j = 0; j < Const.TAMANHO; j++) {
			for (int i = 0; i < Const.TAMANHO; i++) {
				matriz_no[i][j] = new No_grafo();
				add(matriz_no[i][j]);
			}
		}

		reset();
	}
	
	/**
	 * Inicia os personagens e o labirinto.
	 */
	
	public void reset(){
		pacman = new Player(1, 1);

		fant[0] = new Opponent(0);

		fant[1] = new Opponent(1);

		fant[2] = new Opponent(2);

		fant[3] = new Opponent(3);

		labirinto = new Labirintos(pacman.fase, this);
	}
}