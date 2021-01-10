package cart.model.vo;

import java.sql.Date;

public class Buy {

	
	private int orderno;
	private String memberid;
	private String p_id;
	private String p_name;
	private int amount;
	private String orderName;
	private String orderAddress;
	private String orderPhone;
	private String orderRequire;
	private int totalPrice;
	private Date orderDate;
	private String buy_status;
	private String deli_status;
	
	
	
	public Buy() {
		// TODO Auto-generated constructor stub
	}

	
	

	public Buy(String memberid) {
		super();
		this.memberid = memberid;
	}
	
	



	public Buy(String memberid, String p_id, String p_name, int amount, String orderName, String orderAddress,
			String orderPhone, String orderRequire, int totalPrice) {
		super();
		this.memberid = memberid;
		this.p_id = p_id;
		this.p_name = p_name;
		this.amount = amount;
		this.orderName = orderName;
		this.orderAddress = orderAddress;
		this.orderPhone = orderPhone;
		this.orderRequire = orderRequire;
		this.totalPrice = totalPrice;
	}




	public Buy(int orderno, String memberid, String p_id, String p_name, int amount, String orderName,
			String orderAddress, String orderPhone, String orderRequire, int totalPrice, Date orderDate,
			String buy_status, String deli_status) {
		super();
		this.orderno = orderno;
		this.memberid = memberid;
		this.p_id = p_id;
		this.p_name = p_name;
		this.amount = amount;
		this.orderName = orderName;
		this.orderAddress = orderAddress;
		this.orderPhone = orderPhone;
		this.orderRequire = orderRequire;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.buy_status = buy_status;
		this.deli_status = deli_status;
	}




	public int getOrderno() {
		return orderno;
	}


	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}


	public String getMemberid() {
		return memberid;
	}


	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}


	public String getP_id() {
		return p_id;
	}


	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	
	

	public String getP_name() {
		return p_name;
	}




	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	
	public int getAmount() {
		return amount;
	}




	public void setAmount(int amount) {
		this.amount = amount;
	}




	public String getOrderName() {
		return orderName;
	}


	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public String getOrderAddress() {
		return orderAddress;
	}


	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}


	public String getOrderPhone() {
		return orderPhone;
	}


	public void setOrderPhone(String orderPhone) {
		this.orderPhone = orderPhone;
	}


	public String getOrderRequire() {
		return orderRequire;
	}


	public void setOrderRequire(String orderRequire) {
		this.orderRequire = orderRequire;
	}


	public int getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	
	

	public String getDeli_status() {
		return deli_status;
	}


	public void setDeli_status(String deli_status) {
		this.deli_status = deli_status;
	}



	public String getBuy_status() {
		return buy_status;
	}



	public void setBuy_status(String buy_status) {
		this.buy_status = buy_status;
	}




	@Override
	public String toString() {
		return "Buy [orderno=" + orderno + ", memberid=" + memberid + ", p_id=" + p_id + ", p_name=" + p_name
				+ ", amount=" + amount + ", orderName=" + orderName + ", orderAddress=" + orderAddress + ", orderPhone="
				+ orderPhone + ", orderRequire=" + orderRequire + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", buy_status=" + buy_status + ", deli_status=" + deli_status + "]";
	}



	


	
	

	
	
	
}
