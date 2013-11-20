package client.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * This class is responsible for fetching images and
 * configuration values from the server.
 * @author Stian Sandve
 *
 */

public class Fetcher implements IFetcher {

	public String fetchImagesFromServer() {
		return httpGet("http://localhost:8000/images");
	}

	public String fetchConfigsFromServer() {
		return httpGet("http://localhost:8000/configs");
	}

	private static String httpGet(String urlString) {
		URL url = null;
		URLConnection conn = null;
		String response = "";
		try {
			url = new URL(urlString);
			conn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = in.readLine();
		}catch(ConnectException e){
			return "";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
