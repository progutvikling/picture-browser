package gui.admin;

import java.util.List;

public interface ManageKeywordsPanelHandler {
	/**
	 * Should return true if the keyword is accepted and should be
	 * added to the list.
	 */
	public boolean addKeyword(String keyword);

	/**
	 * Should return true if the keyword is accepted and should be
	 * added to the list
	 */
	public boolean deleteKeyword(String keyword);

	/**
	 * Should return a list of all keywords.
	 */
	public List<String> getKeywords();
}
