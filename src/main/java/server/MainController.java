package server;

import server.bll.ImageServer;

public class MainController {
	public static void main(String[] args) {
		ImageServer server = new ImageServer();
		server.start();
		System.out.println("Server is now running");
	}
}
