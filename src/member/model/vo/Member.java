//로그인 할 때 필요한 멤버 만들기
package member.model.vo;

import java.sql.Date;

public class Member {
	
	private String memberid;
	private String password;
	private String membername;
	private String phone;
	private String email;
	private String postal;
	private String address1;
	private String address2;
	private String gender;
	private Date enrolldate;
	private Date deletedate;
	private String status;
	
	public Member() {}
	
	public Member(String memberid, String password) {
		super();
		this.memberid = memberid;
		this.password = password;
	}
	

	public Member(String memberid, String membername, String email) {
		super();
		this.memberid = memberid;
		this.membername = membername;
		this.email = email;
	}

	public Member(String memberid, String membername, String phone, String email, String postal, String address1, String address2) {
		super();
		this.memberid = memberid;
		this.membername = membername;
		this.phone = phone;
		this.email = email;
		this.postal = postal;
		this.address1 = address1;
		this.address2 = address2;
	}
	public Member(String memberid, String password, String membername, String phone, String email, String postal, String address1, String address2, String gender) {
		super();
		this.memberid = memberid;
		this.password = password;
		this.membername = membername;
		this.phone = phone;
		this.email = email;
		this.postal = postal;
		this.address1 = address1;
		this.address2 = address2;
		this.gender = gender;
	}
	public Member(String memberid, String password, String membername, String phone, String email, String postal, String address1, String address2, String gender, Date enrolldate, Date deletedate, String status) {
		super();
		this.memberid = memberid;
		this.password = password;
		this.membername = membername;
		this.phone = phone;
		this.email = email;
		this.postal = postal;
		this.address1 = address1;
		this.address2 = address2;
		this.gender = gender;
		this.enrolldate = enrolldate;
		this.deletedate = deletedate;
		this.status = status;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public Date getDeletedate() {
		return deletedate;
	}

	public void setDeletedate(Date deletedate) {
		this.deletedate = deletedate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [memberid=" + memberid + ", password=" + password + ", membername=" + membername + ", phone="
				+ phone + ", email=" + email + ", postal=" + postal + ", address1=" + address1 + ", address2="
				+ address2 + ", gender=" + gender + ", enrolldate=" + enrolldate + ", deletedate=" + deletedate
				+ ", status=" + status + "]";
	}
	
	
}
