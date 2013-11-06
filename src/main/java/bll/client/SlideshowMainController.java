package bll.client;

import gui.client.MainWindow;
import javax.swing.JFrame;

public class SlideshowMainController {
	
	

	public static void main(String[] args) {
		SlideshowController slideshow = new SlideshowController();
		
		MainWindow wnd = new MainWindow(slideshow.getView());
		wnd.setUndecorated(true);
		slideshow.start();
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wnd.pack();
		wnd.setVisible(true);
		//wnd.startFullscreen();	
	}
}
