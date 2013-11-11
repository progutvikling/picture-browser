package test.common;

import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.dal.Image;

/**
 * 
 * Unit tests for common.dal.Image
 * @author Stian Sandve
 *
 */

public class ImageTest {
	
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
		Image img = new Image("myurl", 1, "mydescription", "mykeyword", sqlDate);
		String json = img.toJson();

		JsonParser parser = new JsonParser();
		JsonObject obj = parser.parse(json).getAsJsonObject();

		assertEquals("URL should be set in json",
					 obj.get("url").getAsString(), "myurl");

		assertEquals("Id should be set in json",
					 obj.get("id").getAsLong(), 1L);

		assertEquals("Description should be set in json:",
					 obj.get("description").getAsString(), "mydescription");

		assertEquals("Date should be set in JSON",
					 obj.get("createdTime").getAsString(), dateTime);
	}
	
	@Test
	public void givenJsonCheckThatReturnedImagesAreValid() {
		Date sqlDate1 = new Date(0);
		String dateTime1 = sdf.format(sqlDate1);
		Image img1 = new Image("url1", 1, "description1", "keyword1", sqlDate1);
		
		Date sqlDate2 = new Date(1000);
		String dateTime2 = sdf.format(sqlDate2);
		Image img2 = new Image("url2", 2, "description2", "keyword2", sqlDate2);
		
		String json = "[{\"url\":\"url1\",\"id\":1,\"description\":" +
				"\"description1\",\"createdTime\":\"" + dateTime1 + "\"}" + 
				",{\"url\":\"url2\",\"id\":2,\"description\":" +
						"\"description2\",\"createdTime\":\"" + dateTime2 + "\"}]";
		
		ArrayList<Image> images = Image.createImagesFromJson(json);
		Image parsedImg1 = images.get(0);
		Image parsedImg2 = images.get(1);
		
		assertEquals(img1, parsedImg1);
		assertEquals(img2, parsedImg2);
	}
}
