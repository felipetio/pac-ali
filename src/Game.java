/**
 * Classe Game.java extendida de JFrame que implementa KeyListener Essa é a
 * principal classe do projeto que possui o main que instancia a mesma.
 * Configura os principais atributos da janela, faz a leitura do teclado, e
 * implementa as funcoes mais basicas do jogo.
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Game extends JPanel implements KeyListener {

	//Barra de status
	private JTextField typingArea;

	//Grafo em forma de matriz que representa o jogo
	public static Grafo labirinto = new Grafo();
	
	/**
	 * Construtor da classe que configura a janela a ser instanciada e adciona
	 * ao layout a var. labirinto e a barra de status.
	 */
	public Game() {
		// Configurando a resolução dfa tela
		Dimension resolucao = Toolkit.getDefaultToolkit().getScreenSize();
		resolucao.setSize(60 * (Const.TAMANHO), 60 * (Const.TAMANHO + 1) - 20);

		// Area de pontos
		typingArea = new JTextField(1);
		typingArea.addKeyListener(this);
		typingArea.setEditable(false);

		// Configurando a janela
		// setTitle("Pacman");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 50);
		setSize(resolucao.width, resolucao.height);
		// setResizable(false);
		setLayout(new BorderLayout());

		// Adicionando na janela
		add(labirinto, BorderLayout.CENTER);
		add(typingArea, BorderLayout.SOUTH);

		typingArea.setText("Vidas: " + labirinto.pacman.vidas + " / Score: "
				+ labirinto.pacman.pontos + " / Bolinhas restantes: "
				+ labirinto.labirinto.totalBolinhas
				+ " / Digite F2 para recomeçar o jogo");
		Opponent.delayMove = 1;
	}

	
	/**
	 * Verifica se o jogo terminou
	 */
	private boolean gameOver() {
		if((labirinto.pacman.vidas != 0) && !((labirinto.pacman.fase == Const.NUM_FASES) && (labirinto.labirinto.totalBolinhas == 0))){
			return false;
		}
		return true;
	}
	/**
	 * Inicia o jogo apos 2 segundos, atualizando a cada 0,35 segundos, até que
	 * o jogo termine.
	 */
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		
		while (!gameOver()) {
			gameRefresh();
			try {
				Thread.sleep(350);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * Atualiza os movimentos na tela, para cada personagem do jogo acionando o
	 * metodo move. A cada movimento verifica se o jogo terminou ou se passou de
	 * fase.Adiciona cherry ao labirinto.
	 * 
	 * @see Player.java
	 * @see Opponent.java
	 * @see Cherry.java
	 * @see Labirinto.java
	 */
	private void gameRefresh() {
		if (labirinto.labirinto.totalBolinhas == Const.TAMANHO * 4) {
			labirinto.cherry = new Cherry(labirinto.pacman.inicialX,
					labirinto.pacman.inicialY);
		}

		if (Cherry.inGame) {
			labirinto.cherry.move();
			labirinto.pacman.comer(1);
		}

		labirinto.pacman.move();

		if (labirinto.labirinto.totalBolinhas == 0) {
			typingArea.setText("GANHOU!!!!!! / Score: "
					+ labirinto.pacman.pontos + " / Bolinhas restantes: "
					+ labirinto.labirinto.totalBolinhas + " / Nivel: "
					+ Player.nivel + " / Digite F2 para recomeçar o jogo");
			for (int i = 0; i < Const.NUM_FANT; i++) {
				labirinto.matriz_no[labirinto.fant[i].inicialX][labirinto.fant[i].inicialY].prisao = false;
				labirinto.matriz_no[labirinto.fant[i].inicialX][labirinto.fant[i].inicialY]
						.repaint();
			}
			labirinto.pacman.fase++;
			labirinto.pacman.vidas++;
			Player.ehSuper = 0;
			labirinto.pacman.pontos += 150;

			if (labirinto.pacman.fase <= 2) {
				labirinto.labirinto = new Labirintos(labirinto.pacman.fase,
						labirinto);
			} else {
				if (Player.nivel == 3) {
					labirinto.pacman.pontos += 100 * labirinto.pacman.vidas;
					typingArea.setText("GANHOU O JOGO!!!!!! / Score: "
							+ labirinto.pacman.pontos
							+ " / Digite F2 para recomeçar o jogo");
					typingArea.removeKeyListener(this);
				} else {
					labirinto.pacman.pontos += 200;
					Player.nivel++;
					labirinto.labirinto = new Labirintos(labirinto.pacman.fase,
							labirinto);
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return;
		}

		//Antes da movimentaçao dos Fantasmas verifica se morreu.
		for (int i = 0; i < Opponent.delayMove / 2; i++) {
			int x = labirinto.pacman.comer(i);
			if (x == 1) {
				typingArea.setText("PERDEU!!!!!! / Score: "
						+ labirinto.pacman.pontos + " / Bolinhas restantes: "
						+ labirinto.labirinto.totalBolinhas + " / Nivel: "
						+ Player.nivel + " / Digite F2 para recomeçar o jogo");
				//typingArea.removeKeyListener(this);
				return;
			} else if (x == 2) {
				Opponent.delayMove = 0;
				return;
			}
		}

		//Movimenta os Fantasmas e verifica se morreu
		for (int i = 0; i < Opponent.delayMove / 2; i++) {
			labirinto.fant[i].move();
			int x = labirinto.pacman.comer(i);
			if (x == 1) {
				typingArea.setText("PERDEU!!!!!! / Score: "
						+ labirinto.pacman.pontos + " / Bolinhas restantes: "
						+ labirinto.labirinto.totalBolinhas + " / Nivel: "
						+ Player.nivel + " / Digite F2 para recomeçar o jogo");
				//typingArea.removeKeyListener(this);
				return;
			} else if (x == 2) {
				Opponent.delayMove = 0;
				return;
			}
		}

		typingArea.setText("Vidas: " + labirinto.pacman.vidas + " / Score: "
				+ labirinto.pacman.pontos + " / Bolinhas restantes: "
				+ labirinto.labirinto.totalBolinhas + " / Nivel: "
				+ Player.nivel + " / Digite F2 para recomeçar o jogo");

		if (Opponent.delayMove != 8) {
			Opponent.delayMove++;
		}

	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Ao ser pressionado a tecla, passa o codigo para o pacman se mover.
	 * 
	 * @param e
	 *            Guarda atributos da tecla
	 * @see Personagem.java
	 */
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode() == 113) || (e.getKeyCode() == 116)
				|| (e.getKeyCode() == Const.SOBE)
				|| (e.getKeyCode() == Const.DESCE)
				|| (e.getKeyCode() == Const.DIREITA)
				|| (e.getKeyCode() == Const.ESQUERDA)) {
			labirinto.pacman.movBuffer = e.getKeyCode();
		}
		if ((gameOver()) && (e.getKeyCode() == 113)) {
			labirinto.pacman.move();
			this.run();
			//run();			
		}
	}
}