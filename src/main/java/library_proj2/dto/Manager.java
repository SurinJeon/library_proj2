package library_proj2.dto;

public class Manager {

	private String mngAccount;
	private String passwd;

	public Manager() {
		super();
	}

	public Manager(String mngAccount) {
		this.mngAccount = mngAccount;
	}

	public Manager(String mngAccount, String passwd) {
		this.mngAccount = mngAccount;
		this.passwd = passwd;
	}

	public String getMngAccount() {
		return mngAccount;
	}

	public void setMngAccount(String mngAccount) {
		this.mngAccount = mngAccount;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return String.format("Manager [%s, %s]", mngAccount, passwd);
	}

}
