package test;

import static org.junit.Assert.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import bll.utils.ImageParser;
import dal.admin.Image;

/**
 * 
 * Unit tests for bll.admin.ImageParser
 * @author Stian Sandve
 *
 */

public class ImageParserTest {
	
	String format;
	SimpleDateFormat sdf;
	
	Date sqlDate;
	String dateTime;
	
	Date sqlDate1;
	String dateTime1;
	
	Date sqlDate2;
	String dateTime2;
	
	@Before
	public void init() {
		format = "yyyy-MM-dd HH:mm:ss";
		sdf = new SimpleDateFormat(format);
		
		sqlDate = new Date(0);
		dateTime = sdf.format(sqlDate);
		
		sqlDate1 = new Date(0);
		dateTime1 = sdf.format(sqlDate1);
		
		sqlDate2 = new Date(1000);
		dateTime2 = sdf.format(sqlDate2);
	}
	
	@Test
	public void givenImageCeckThatReturnedJsonIsValid() {
		Image img = new Image("url", 1, "keyword", "description", sqlDate);
		String json = ImageParser.getJsonFromImage(img);
		assertEquals(json, "{\"url\":\"url\",\"id\":1,\"description\":" +
				"\"description\",\"createdTime\":\"" + dateTime + "\",\"internalId\":" + 0 + "}");
	}
	
	@Test
	public void givenArrayListOfImagesCeckThatReturnedJsonIsValid() {
		Image img1 = new Image("url1", 1, "keyword", "description1", sqlDate1);
		
		Image img2 = new Image("url2", 2, "keyword", "description2", sqlDate2);
		
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(img1);
		images.add(img2);
		
		String json = ImageParser.getJsonFromImage(images);
		assertEquals(json, "[{\"url\":\"url1\",\"id\":1,\"description\":" +
				"\"description1\",\"createdTime\":\"" + dateTime1 + "\",\"internalId\":" + 0 + "}" + 
				",{\"url\":\"url2\",\"id\":2,\"description\":" +
						"\"description2\",\"createdTime\":\"" + dateTime2 + "\",\"internalId\":" + 0 + "}]");
	}
	
	@Test
	public void givenJsonCheckThatReturnedImagesAreValid() {
		Date sqlDate1 = new Date(0);
		String dateTime1 = sdf.format(sqlDate1);
		Image img1 = new Image("url1", 1, "keyword", "description1", sqlDate1);
		
		Date sqlDate2 = new Date(1000);
		String dateTime2 = sdf.format(sqlDate2);
		Image img2 = new Image("url2", 2, "keyword", "description2", sqlDate2);
		
		String json = "[{\"url\":\"url1\",\"id\":1,\"description\":" +
				"\"description1\",\"createdTime\":\"" + dateTime1 + "\"}" + 
				",{\"url\":\"url2\",\"id\":2,\"description\":" +
						"\"description2\",\"createdTime\":\"" + dateTime2 + "\"}]";
		
		ArrayList<Image> images = ImageParser.getImageFromJson(json);
		Image parsedImg1 = images.get(0);
		Image parsedImg2 = images.get(1);
		
		assertEquals(img1, parsedImg1);
		assertEquals(img2, parsedImg2);
		
	}
}
