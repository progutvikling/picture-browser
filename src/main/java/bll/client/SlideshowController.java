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
	private RecentImages ri;
	private Slideshow show;
	
	public SlideshowController() {
		view = new SlideshowPanel();
		show = new Slideshow(view, this);
		ri = new RecentImages();
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
		Map<String, String> configs = ConfigsParser.getMapFromJson(json);
		String delay = configs.get("slideshow_delay");
		return Integer.parseInt(delay);
	}
	
	public SlideshowPanel getView() {
		return view;
	}

}

