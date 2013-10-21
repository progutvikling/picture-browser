package bll.utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dal.admin.Image;

/**
 * 
 * A utility to convert dal.admin.Image to JSON
 * and vice versa
 * @author Stian Sandve
 *
 */

public class ImageParser {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	public static String getJsonFromImage(Image image) {
		Gson gson = new GsonBuilder()
		   .setDateFormat(DATE_FORMAT).create();
		String json = gson.toJson(image);
		return json;
	}
	
	public static String getJsonFromImage(ArrayList<Image> images) {
		Gson gson = new GsonBuilder()
		   .setDateFormat(DATE_FORMAT).create();
		String json = gson.toJson(images);
		return json;
	}
	
	public static ArrayList<Image> getImageFromJson(String json) {
		
		Gson gson = new GsonBuilder()
		   .setDateFormat(DATE_FORMAT).create();
		
		Image[] img = gson.fromJson(json, Image[].class);
		return new ArrayList<Image>(Arrays.asList(img));
		
	}
}
