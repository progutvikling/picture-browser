package client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
		BufferedImage scaledImage = scaleImageToFitPanel(bi, screenSize);
		this.bi = scaledImage;
		this.setMargins();
		this.revalidate();
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bi, marginX, marginY, null);
	}
	
	private static double getScaleFactor(int currentSize, int targetSize) {    
		double scale = 1;
		scale = (double) targetSize / (double) currentSize;
		return scale;    
	}

	private static double getScaleFactorToFit(Dimension original, Dimension toFit) {    
		double scale = 1.0;

		if (original != null && toFit != null) {    
			double scaleWidth = getScaleFactor(original.width, toFit.width);
			double scaleHeight = getScaleFactor(original.height, toFit.height);

			scale = Math.min(scaleHeight, scaleWidth);
		}    
		return scale;
	}

	private static BufferedImage scaleImageToFitPanel(BufferedImage src, Dimension targetDimension) {

		if (src == null)
			throw new IllegalArgumentException("src cannot be null");
		if (targetDimension.width < 0)
			throw new IllegalArgumentException("target width must be >= 0");
		if (targetDimension.height < 0)
			throw new IllegalArgumentException("target height must be >= 0");

		double scaleFactor = getScaleFactorToFit(new Dimension(src.getWidth(),
				src.getHeight()), targetDimension);

		int scaleWidth = (int) Math.round(src.getWidth() * scaleFactor);
		int scaleHeight = (int) Math.round(src.getHeight() * scaleFactor);

		BufferedImage result = new BufferedImage(scaleWidth, scaleHeight, src.getType());
		Graphics2D resultGraphics = result.createGraphics();

		resultGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		resultGraphics.drawImage(src, 0, 0, scaleWidth, scaleHeight, null);

		// Just to be clean, explicitly dispose our temporary graphics object
		resultGraphics.dispose();

		return result;
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