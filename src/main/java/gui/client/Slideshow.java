package gui.client;

import java.util.Timer;
import java.util.TimerTask;

public class Slideshow extends TimerTask {
	
	Canvas canvas;
	SlideshowHandler handler;
	Timer timer;

	public Slideshow(Canvas canvas, SlideshowHandler handler) {
		this.canvas = canvas;
		this.handler = handler;
		timer = new Timer();
	}
	
	public void start() {
        timer.schedule(this, handler.getDelay()*1000);
	}
	
	public void stop() {
		timer.cancel();
	}

	@Override
	public void run() {
		canvas.setImage(handler.next());
	}
	
}
