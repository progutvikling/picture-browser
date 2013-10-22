package gui.client;

import java.awt.image.BufferedImage;

public interface SlideshowHandler {
	
	public void start();
	public void stop();
	public BufferedImage next();
	public BufferedImage previous();
	public int getDelay();
	
}
