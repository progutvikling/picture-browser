package test.admin;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import admin.bll.AdminLoginBLL;

public class AdminLoginBLLTest {
	@Test
	public void testCheckUsernameAndPassword() {
		AdminLoginBLL controller = new AdminLoginBLL();
		
		assertTrue(controller.checkusernameandpassword("admin", "admin"));
		assertFalse(controller.checkusernameandpassword("admin", "err"));
		assertFalse(controller.checkusernameandpassword("err", "admin"));
	}
}
