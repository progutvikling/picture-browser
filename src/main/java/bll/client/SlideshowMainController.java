package bll.client;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import gui.client.MainWindow;
import javax.swing.JFrame;

public class SlideshowMainController {
	
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];

	public static void main(String[] args) {
		SlideshowController slideshow = new SlideshowController();
		
		MainWindow wnd = new MainWindow(slideshow.getView());
		slideshow.start();
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wnd.pack();
		wnd.setVisible(true);
		device.setFullScreenWindow(wnd);
	}
}
