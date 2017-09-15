package bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String _username) {
		username = _username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String _password) {
		password = _password;
	}
}