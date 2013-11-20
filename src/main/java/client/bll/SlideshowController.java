package client.bll;

import java.awt.image.BufferedImage;

import client.gui.Slideshow;
import client.gui.SlideshowHandler;
import client.gui.SlideshowPanel;

/**
 * Controller class for the slide show
 * 
 * @author Stian Sandve <stian@sandve.org>
 *
 */

public class SlideshowController implements SlideshowHandler {
	
	private SlideshowPanel view;
	private IImageLoader il;
	private Slideshow show;
	
	public SlideshowController() {
		view = new SlideshowPanel();
		show = new Slideshow(view, this);
		il = ImageLoaderFactory.build();
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