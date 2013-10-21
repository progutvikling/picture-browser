package dal.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * This class is responsible for fetching images and
 * configuration values from the server.
 * @author Stian Sandve
 *
 */

public class ImageClient {
	
	public static String fetchImagesFromServer() {
		URL url = null;
		URLConnection conn = null;
		String response = "";
		try {
			url = new URL("http://localhost:8000/images");
			conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
}
