package gui.client;

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

public class SlideshowPanel extends JPanel {

	private static final long serialVersionUID = -3943809441036747592L;
	
	private BufferedImage bi;
	private int marginX = 0;
	private int marginY = 0;

	public SlideshowPanel() {
		//Sets the panels preferred size to be the same as the screens size
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
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
	
	private int setMarginX() {
		int panelWidth = this.getWidth();
		int marginX = (panelWidth-bi.getWidth())/2;
		return marginX;
	}
	
	private int setMarginY() {
		int panelHeight = this.getHeight();
		int marginY = (panelHeight-bi.getHeight())/2;
		return marginY;
	}
}