package common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String removeEmojis(String string) {
        String utf8string = "";
        try {
            byte[] utf8Bytes = string.getBytes("UTF-8");

            utf8string = new String(utf8Bytes, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Pattern unicodeOutliers = Pattern.compile("[^\\x00-\\x7F]",
                Pattern.UNICODE_CASE | Pattern.CANON_EQ
                        | Pattern.CASE_INSENSITIVE);
        Matcher unicodeOutlierMatcher = unicodeOutliers.matcher(utf8string);
        
        utf8string = unicodeOutlierMatcher.replaceAll(" ");
        
        return utf8string;
    }
}
