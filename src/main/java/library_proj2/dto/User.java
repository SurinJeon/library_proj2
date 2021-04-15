package library_proj2.dto;

import java.util.Date;
import java.util.List;

public class User {

	private int userNo;
	private String userName;
	private Date userBirth;
	private String account;
	private String tel;
	private String phone;
	private String address;

	public User() {
		super();
	}

	public User(int userNo) {
		this.userNo = userNo;
	}

	public User(String userName) {
		this.userName = userName;
	}

	public User(String account, String phone) {
		this.account = account;
		this.phone = phone;
	}

	public User(Date userBirth) {
		this.userBirth = userBirth;
	}

	public User(int userNo, String userName, String tel, String phone) {
		this.userNo = userNo;
		this.userName = userName;
		this.tel = tel;
		this.phone = phone;
	}

	public User(int userNo, String userName, Date userBirth) {
		this.userNo = userNo;
		this.userName = userName;
		this.userBirth = userBirth;
	}

	public User(int userNo, String userName, Date userBirth, String account, String tel, String phone, String address) {
		this.userNo = userNo;
		this.userName = userName;
		this.userBirth = userBirth;
		this.account = account;
		this.tel = tel;
		this.phone = phone;
		this.address = address;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("User %d, %s, %s, %s, %s, %s, %s", userNo, userName, userBirth, account, tel, phone,
				address);
	}

}
