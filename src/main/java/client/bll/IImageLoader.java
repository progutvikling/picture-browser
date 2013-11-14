package client.bll;

import java.awt.image.BufferedImage;

public interface IImageLoader {

	public abstract BufferedImage getNext();

	public abstract int getSlideshowDelay();

}