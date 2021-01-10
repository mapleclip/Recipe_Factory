package board.model.vo;

public class Member_recipe {
	private int mem_R_No;
	private String memberId;
	private String p_Id;
	private String mem_R_Title;
	private String mem_R_Exp;
	private int mem_R_Cap;
	private int mem_R_Price;
	private String mem_R_Origin;
	
	public Member_recipe() {}

	public Member_recipe(int mem_R_No, String memberId, String p_Id, String mem_R_Title, String mem_R_Exp,
			int mem_R_Cap, int mem_R_Price, String mem_R_Origin) {
		super();
		this.mem_R_No = mem_R_No;
		this.memberId = memberId;
		this.p_Id = p_Id;
		this.mem_R_Title = mem_R_Title;
		this.mem_R_Exp = mem_R_Exp;
		this.mem_R_Cap = mem_R_Cap;
		this.mem_R_Price = mem_R_Price;
		this.mem_R_Origin = mem_R_Origin;
	}

	public int getMem_R_No() {
		return mem_R_No;
	}

	public void setMem_R_No(int mem_R_No) {
		this.mem_R_No = mem_R_No;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getP_Id() {
		return p_Id;
	}

	public void setP_Id(String p_Id) {
		this.p_Id = p_Id;
	}

	public String getMem_R_Title() {
		return mem_R_Title;
	}

	public void setMem_R_Title(String mem_R_Title) {
		this.mem_R_Title = mem_R_Title;
	}

	public String getMem_R_Exp() {
		return mem_R_Exp;
	}

	public void setMem_R_Exp(String mem_R_Exp) {
		this.mem_R_Exp = mem_R_Exp;
	}

	public int getMem_R_Cap() {
		return mem_R_Cap;
	}

	public void setMem_R_Cap(int mem_R_Cap) {
		this.mem_R_Cap = mem_R_Cap;
	}

	public int getMem_R_Price() {
		return mem_R_Price;
	}

	public void setMem_R_Price(int mem_R_Price) {
		this.mem_R_Price = mem_R_Price;
	}

	public String getMem_R_Origin() {
		return mem_R_Origin;
	}

	public void setMem_R_Origin(String mem_R_Origin) {
		this.mem_R_Origin = mem_R_Origin;
	}

	@Override
	public String toString() {
		return "Member_recipe [mem_R_No=" + mem_R_No + ", memberId=" + memberId + ", p_Id=" + p_Id + ", mem_R_Title="
				+ mem_R_Title + ", mem_R_Exp=" + mem_R_Exp + ", mem_R_Cap=" + mem_R_Cap + ", mem_R_Price=" + mem_R_Price
				+ ", mem_R_Origin=" + mem_R_Origin + "]";
	}
	
	
}



