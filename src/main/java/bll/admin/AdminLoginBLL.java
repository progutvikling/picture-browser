package bll.admin;

public class AdminLoginBLL {
	public boolean checkusernameandpassword(String password, String username){
			if(password.equals("admin") && username.equals("admin")){
				return true;
			}
		return false;
	}
}
