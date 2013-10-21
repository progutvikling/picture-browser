package dal.admin;

import java.util.ArrayList;

public interface IKeywordsStore {
	/**
	 * Returns a list of all keywords.
	 */
	public ArrayList<String> getKeywords();

	/**
	 * Adds a keyword and returns true if success.
	 * Returns false if the keyword could not be added or it already
	 * exists.
	 */
	public boolean addKeyword(String keyword);

	/**
	 * Deletes a keyword and returns true if success.
	 */
	public boolean deleteKeyword(String keyword);
}
