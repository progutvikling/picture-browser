package fetcher.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


public class InstagramClient implements IInstagramClient {
	/**
	 * Client id registered on Knut Hellands Twitter account.
	 */
	private String CLIENTID = "71ca80aebf5a40dbbc1fb72fc320cad5";
	// private String CLIENT_SECRET = "176a039df41f4f828a140a27d9087503";

	public String search(String keyword, long maxId) {
		String query = "";
		try {
			query = URLEncoder.encode(keyword, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		
		String maxTagId = "";
		if (maxId > 0) {
			maxTagId = "max_tag_id=" + maxId;
		}

		try {
			URL url = new URL("https://api.instagram.com/v1/tags/" + query + "/media/recent?" + maxTagId + "&client_id=" + CLIENTID);
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			return slurpStream(connection.getInputStream());
		} catch (IOException e) {
			System.out.println("IO exception in communication with Twitter API.");
			e.printStackTrace();
			return null;
		}        
	}

	
	/**
	 * Helper method to slurp a input stream and return as string.
	 */
	private static String slurpStream(InputStream in) {
		try {
			StringBuffer response = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			reader.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}
