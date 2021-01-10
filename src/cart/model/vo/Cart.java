package cart.model.vo;

public class Cart {
	
	private int cart_no;
	private String p_id;
	private String member_id;
	private String p_name;
	private int cart_price;
	private int amount;
	private String status;
	
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}


	
	
	
	public Cart(int cart_no) {
		super();
		this.cart_no = cart_no;
	}


	public Cart(int cart_no, String p_id, String member_id, String p_name, int cart_price, int amount, String status) {
		super();
		this.cart_no = cart_no;
		this.p_id = p_id;
		this.member_id = member_id;
		this.p_name = p_name;
		this.cart_price = cart_price;
		this.amount = amount;
		this.status = status;
	}





	public int getCart_no() {
		return cart_no;
	}





	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}





	public String getP_id() {
		return p_id;
	}





	public void setP_id(String p_id) {
		this.p_id = p_id;
	}





	public String getMember_id() {
		return member_id;
	}





	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}





	public String getP_name() {
		return p_name;
	}





	public void setP_name(String p_name) {
		this.p_name = p_name;
	}





	public int getCart_price() {
		return cart_price;
	}





	public void setCart_price(int cart_price) {
		this.cart_price = cart_price;
	}





	public int getAmount() {
		return amount;
	}





	public void setAmount(int amount) {
		this.amount = amount;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	@Override
	public String toString() {
		return "Cart [cart_no=" + cart_no + ", p_id=" + p_id + ", member_id=" + member_id + ", p_name=" + p_name
				+ ", cart_price=" + cart_price + ", amount=" + amount + ", status=" + status + "]";
	}

	
	
	
}