package bll.client;

import java.awt.image.BufferedImage;
import java.util.Map;

import bll.utils.ConfigsParser;

import dal.client.Fetcher;
import gui.client.Slideshow;
import gui.client.SlideshowHandler;
import gui.client.SlideshowPanel;

public class SlideshowController implements SlideshowHandler {
	
	private SlideshowPanel view;
	private ImageLoader ri;
	private Slideshow show;
	
	public SlideshowController() {
		view = new SlideshowPanel();
		show = new Slideshow(view, this);
		ri = new ImageLoader();
	}

	@Override
	public BufferedImage next() {
		return ri.getNext();
	}

	@Override
	public BufferedImage previous() {
		//not yet implemented
		return null;
	}

	@Override
	public int getDelay() {
		String json = Fetcher.fetchConfigsFromServer();
		Map<String, Object> configs = ConfigsParser.getMapFromJson(json);
		String delay = (String) configs.get("slideshow_delay");
		return Integer.parseInt(delay);
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