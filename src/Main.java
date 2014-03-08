/*
Autor:
	Vinicius Camara Anselmo #5117962
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JApplet {
	public static final long serialVersionUID = 24362462L;
	
	Container janela1;
	Game janela2;
	
	public void init()
	{
		janela2 = new Game();
		Gfx.uploadImage();
		janela2.setVisible(true);
		janela1 = getContentPane();
		janela1.setLayout(new BorderLayout());
		janela1.add("Center", janela2);
		janela2.run();
	}
}
