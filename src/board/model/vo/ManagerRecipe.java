package board.model.vo;

public class ManagerRecipe {
	private int m_R_NO;
	private String memberId;
	private String p_Id;
	private String m_R_Title;
	private String m_R_Thumbnail;
	private String m_R_Explanation;
	private int m_R_Capacity;
	private int m_R_price;
	private String m_R_Origin;
	private int m_R_count;
	private String status; 
	
	public ManagerRecipe() {
		
	}

	public ManagerRecipe(int m_R_NO, String memberId, String p_Id, String m_R_Title, String m_R_Thumbnail,
			String m_R_Explanation, int m_R_Capacity, int m_R_price, String m_R_Origin, int m_R_count, String status) {
		super();
		this.m_R_NO = m_R_NO;
		this.memberId = memberId;
		this.p_Id = p_Id;
		this.m_R_Title = m_R_Title;
		this.m_R_Thumbnail = m_R_Thumbnail;
		this.m_R_Explanation = m_R_Explanation;
		this.m_R_Capacity = m_R_Capacity;
		this.m_R_price = m_R_price;
		this.m_R_Origin = m_R_Origin;
		this.m_R_count = m_R_count;
		this.status = status;
	}

	public int getM_R_NO() {
		return m_R_NO;
	}

	public void setM_R_NO(int m_R_NO) {
		this.m_R_NO = m_R_NO;
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

	public String getM_R_Title() {
		return m_R_Title;
	}

	public void setM_R_Title(String m_R_Title) {
		this.m_R_Title = m_R_Title;
	}

	public String getM_R_Thumbnail() {
		return m_R_Thumbnail;
	}

	public void setM_R_Thumbnail(String m_R_Thumbnail) {
		this.m_R_Thumbnail = m_R_Thumbnail;
	}

	public String getM_R_Explanation() {
		return m_R_Explanation;
	}

	public void setM_R_Explanation(String m_R_Explanation) {
		this.m_R_Explanation = m_R_Explanation;
	}

	public int getM_R_Capacity() {
		return m_R_Capacity;
	}

	public void setM_R_Capacity(int m_R_Capacity) {
		this.m_R_Capacity = m_R_Capacity;
	}

	public int getM_R_price() {
		return m_R_price;
	}

	public void setM_R_price(int m_R_price) {
		this.m_R_price = m_R_price;
	}

	public String getM_R_Origin() {
		return m_R_Origin;
	}

	public void setM_R_Origin(String m_R_Origin) {
		this.m_R_Origin = m_R_Origin;
	}

	public int getM_R_count() {
		return m_R_count;
	}

	public void setM_R_count(int m_R_count) {
		this.m_R_count = m_R_count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ManagerRecipe [m_R_NO=" + m_R_NO + ", memberId=" + memberId + ", p_Id=" + p_Id + ", m_R_Title="
				+ m_R_Title + ", m_R_Thumbnail=" + m_R_Thumbnail + ", m_R_Explanation=" + m_R_Explanation
				+ ", m_R_Capacity=" + m_R_Capacity + ", m_R_price=" + m_R_price + ", m_R_Origin=" + m_R_Origin
				+ ", m_R_count=" + m_R_count + ", status=" + status + "]";
	}
	
	

}
