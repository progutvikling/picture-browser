package bll.client;

import java.awt.image.BufferedImage;
import gui.client.Slideshow;
import gui.client.SlideshowHandler;
import gui.client.SlideshowPanel;

public class SlideshowController implements SlideshowHandler {
	
	private SlideshowPanel view;
	private ImageLoader il;
	private Slideshow show;
	
	public SlideshowController() {
		view = new SlideshowPanel();
		show = new Slideshow(view, this);
		il = new ImageLoader();
	}

	@Override
	public BufferedImage next() {
		return il.getNext();
	}

	@Override
	public BufferedImage previous() {
		//not yet implemented
		return null;
	}

	@Override
	public int getDelay() {
		return il.getSlideshowDelay();
	}
	
	public SlideshowPanel getView() {
		return view;
	}

	@Override
	public void start() {
		show.start();
	}

	@Override
	public void stop() {
		show.stop();
	}

}