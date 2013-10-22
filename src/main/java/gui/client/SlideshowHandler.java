package gui.client;

import java.awt.image.BufferedImage;

public interface SlideshowHandler {
	
	public BufferedImage next();
	public BufferedImage previous();
	public int getDelay();
	
}
