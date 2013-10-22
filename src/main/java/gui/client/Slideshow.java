package gui.client;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Slideshow extends TimerTask {
	
	Canvas canvas;
	SlideshowHandler handler;
	Timer timer;
	ScheduledExecutorService exec;

	public Slideshow(Canvas canvas, SlideshowHandler handler) {
		this.canvas = canvas;
		this.handler = handler;
		exec = Executors.newSingleThreadScheduledExecutor();
		timer = new Timer();
	}
	
	public void start() {
		exec.scheduleAtFixedRate(new Runnable() {
			  @Override
			  public void run() {
			    canvas.setImage(handler.next());
			  }
			}, 0, handler.getDelay(), TimeUnit.SECONDS);
	}
	
	public void stop() {
		exec.shutdown();
	}

	@Override
	public void run() {
		canvas.setImage(handler.next());
	}
	
}
