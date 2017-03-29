package com.projeto.view.nativo.auxiliar;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class IconeFinanca {
	
	public static Image getIconeImage() {
		URL resource = IconeFinanca.class.getClassLoader().getResource("com/projeto/view/nativo/auxiliar/financa.png");
		return new ImageIcon(resource).getImage();
	}
}
