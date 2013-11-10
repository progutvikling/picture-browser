package bll.client;

import java.awt.image.BufferedImage;
import gui.client.Slideshow;
import gui.client.SlideshowHandler;
import gui.client.SlideshowPanel;

public class SlideshowController implements SlideshowHandler, RefreshListener {
	
	private static final String SLIDESHOW_DELAY_KEY = "slideshow_delay";
	
	private SlideshowPanel view;
	private ImageLoader il;
	private Slideshow show;
	private Refresher refresher;
	private int slideshowDelay = 3;
	
	public SlideshowController() {
		view = new SlideshowPanel();
		show = new Slideshow(view, this);
		il = new ImageLoader();
		refresher = new Refresher();
		refresher.start();
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
		return slideshowDelay;
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

	@Override
	public void refreshPerformed(RefreshEvent e) {
		String delay = (String) e.getConfigs().get(SLIDESHOW_DELAY_KEY);
		slideshowDelay = Integer.parseInt(delay);
	}

}