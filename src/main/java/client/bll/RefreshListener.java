package client.bll;

/**
 * Implement this interface if you want to register
 * your class as a refresh listener
 * 
 * @author Stian Sandve <stian@sandve.org>
 *
 */

public interface RefreshListener {
	public void refreshPerformed(RefreshEvent e);
}
