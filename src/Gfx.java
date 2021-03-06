/**
 * Classe Gfx.java faz upload das imagens utilizadas no jogo, guardando em
 * variaveis.
 * 
 * @author Felipe Luis Souza Vieira
 * @author Matheus Victor Brum Soares
 * @author Tatiane Escobar Gava
 * @author Thiago Santana Carrazeiro
 */

import java.awt.Image;
import java.awt.Toolkit;

public class Gfx {

	//Imagem do pacman
	public static Image pacmamLf;

	public static Image pacmamRt;

	public static Image pacmamUp_Lf;

	public static Image pacmamUp_Rt;

	public static Image pacmamDw_Lf;

	public static Image pacmamDw_Rt;

	//Imagem dos Fantasma
	public static Image fantasma0;

	public static Image fantasma0_super;

	public static Image fantasma1;

	public static Image fantasma1_super;

	public static Image fantasma2;

	public static Image fantasma2_super;

	public static Image fantasma3;

	public static Image fantasma3_super;

	//Imagem da Cherry
	public static Image cherry;
	
	//Imagem das vitaminas
	public static Image vitamina;

	public static Image vitamina_super;

	//Imagem do fundo dos nos
	public static Image block;

	public static Image up_dw_lf;

	public static Image up_dw_rt;

	public static Image up_lf_rt;

	public static Image dw_lf_rt;

	public static Image up_dw;

	public static Image up_lf;

	public static Image up_rt;

	public static Image dw_lf;

	public static Image dw_rt;

	public static Image lf_rt;

	public static Image up;

	public static Image dw;

	public static Image lf;

	public static Image rt;

	public static Image vazio;

	public static Image prisao_lf;

	public static Image prisao_rt;

	/**
	 * Construtor da classe que pra cada variavel, faz upload da respectiva
	 * imagem no diretorio Gfx.
	 */

	public static void uploadImage() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// TODO: get project path automatically
		String project_path = "/Users/felipe/Projects/pac/src/";

		//Fazendo upload de todas imagens...

		pacmamLf = toolkit.getImage(project_path + "Gfx/pac_Lf.GIF");
		pacmamRt = toolkit.getImage(project_path + "Gfx/pac_Rt.GIF");
		pacmamDw_Lf = toolkit.getImage(project_path + "Gfx/pac_Dw_Lf.GIF");
		pacmamDw_Rt = toolkit.getImage(project_path + "Gfx/pac_Dw_Rt.GIF");
		pacmamUp_Lf = toolkit.getImage(project_path + "Gfx/pac_Up_Lf.GIF");
		pacmamUp_Rt = toolkit.getImage(project_path + "Gfx/pac_Up_Rt.GIF");

		fantasma0 = toolkit.getImage(project_path + "Gfx/azul.GIF");
		fantasma0_super = toolkit.getImage(project_path + "Gfx/azul_super.GIF");
		fantasma1 = toolkit.getImage(project_path + "Gfx/verde.GIF");
		fantasma1_super = toolkit.getImage(project_path + "Gfx/verde_super.GIF");
		fantasma2 = toolkit.getImage(project_path + "Gfx/laranja.GIF");
		fantasma2_super = toolkit.getImage(project_path + "Gfx/laranja_super.GIF");
		fantasma3 = toolkit.getImage(project_path + "Gfx/rosa.GIF");
		fantasma3_super = toolkit.getImage(project_path + "Gfx/rosa_super.GIF");

		cherry = toolkit.getImage(project_path + "Gfx/cherry.GIF");
		vitamina = toolkit.getImage(project_path + "Gfx/vit.GIF");
		vitamina_super = toolkit.getImage(project_path + "Gfx/vit_super.GIF");

		block = toolkit.getImage(project_path + "Gfx/block.GIF");
		up_dw_lf = toolkit.getImage(project_path + "Gfx/up_dw_lf.GIF");
		up_dw_rt = toolkit.getImage(project_path + "Gfx/up_dw_rt.GIF");
		up_lf_rt = toolkit.getImage(project_path + "Gfx/up_lf_rt.GIF");
		dw_lf_rt = toolkit.getImage(project_path + "Gfx/dw_lf_rt.GIF");
		up_dw = toolkit.getImage(project_path + "Gfx/up_dw.GIF");
		up_lf = toolkit.getImage(project_path + "Gfx/up_lf.GIF");
		up_rt = toolkit.getImage(project_path + "Gfx/up_rt.GIF");
		dw_lf = toolkit.getImage(project_path + "Gfx/dw_lf.GIF");
		dw_rt = toolkit.getImage(project_path + "Gfx/dw_rt.GIF");
		lf_rt = toolkit.getImage(project_path + "Gfx/lf_rt.GIF");
		up = toolkit.getImage(project_path + "Gfx/up.GIF");
		dw = toolkit.getImage(project_path + "Gfx/dw.GIF");
		lf = toolkit.getImage(project_path + "Gfx/lf.GIF");
		rt = toolkit.getImage(project_path + "Gfx/rt.GIF");
		vazio = toolkit.getImage(project_path + "Gfx/vazio.GIF");
		prisao_lf = toolkit.getImage(project_path + "Gfx/prisao_lf.GIF");
		prisao_rt = toolkit.getImage(project_path + "Gfx/prisao_rt.GIF");

	}

}