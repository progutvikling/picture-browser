package gui.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Simple panel for viewing images.
 * The image will be drawn at the center of the panel
 * 
 * @author Stian Sandve <stian@sandve.org>
 */

public class SlideshowPanel extends JPanel implements Canvas {

	private static final long serialVersionUID = -3943809441036747592L;
	
	private BufferedImage bi;
	private int marginX = 0;
	private int marginY = 0;
	private Dimension screenSize;

	public SlideshowPanel() {
		//Sets the panels preferred size to be the same as the screens size
		this.screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
		this.setPreferredSize(screenSize);
		this.setBackground(Color.BLACK);
	}

	/**
	 * Draws the specified image on the center of the panel
	 * @param bi the specified image to be drawn. 
	 * This method does nothing if bi is null.
	 */
	public void setImage(BufferedImage bi) {
		this.bi = bi;
		this.setMargins();
		this.revalidate();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, marginX, marginY, null);
	}
	
	//This method will make sure that the image is drawn on the center
	//of the panel
	private void setMargins() {
		setMarginX();
		setMarginY();
	}
	
	private void setMarginX() {
		marginX = ((screenSize.width)-(bi.getWidth()))/2;
	}
	
	private void setMarginY() {
		marginY = ((screenSize.height)-(bi.getHeight()))/2;
	}
}