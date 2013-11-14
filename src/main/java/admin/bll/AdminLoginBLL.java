package admin.bll;

public class AdminLoginBLL {
	public boolean checkusernameandpassword(String username, String password){
			if(username.equals("admin") && password.equals("admin")){
				return true;
			}
		return false;
	}
}
