package bll.client;

import java.awt.DisplayMode;

import gui.client.MainWindow;
import javax.swing.JFrame;

public class SlideshowMainController {
	
	private static SlideshowController slideshow;
	private static MainWindow wnd;
	
	public static void main(String[] args) {
		initializeSlideshow();
		setFullScreenMode();
		setVisible();
		startSlideshow();
	}
	
	private static void initializeSlideshow() {
		slideshow = new SlideshowController();
		wnd = new MainWindow(slideshow.getView());
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private static void setVisible() {
		wnd.pack();
		wnd.setVisible(true);
	}
	
	private static void setFullScreenMode() {
		DisplayMode dm=new DisplayMode(800, 600, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		wnd.setFullScreenMode(dm);
	}
	
	private static void startSlideshow() {
		slideshow.start();
	}
}
