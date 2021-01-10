package product.model.vo;

import java.sql.Date;

public class Review {
	private int rNo;
	private String mId;
	private String pId;
	private String rTitle;
	private String rContent;
	private String rFileName;
	private Date rDate;
	
	public Review() {}

	public Review(int rNo, String mId, String pId, String rTitle, String rContent, String rFileName, Date rDate) {
		super();
		this.rNo = rNo;
		this.mId = mId;
		this.pId = pId;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rFileName = rFileName;
		this.rDate = rDate;
	}

	public int getrNo() {
		return rNo;
	}

	public void setrNo(int rNo) {
		this.rNo = rNo;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public String getrFileName() {
		return rFileName;
	}

	public void setrFileName(String rFileName) {
		this.rFileName = rFileName;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	@Override
	public String toString() {
		return "Review [rNo=" + rNo + ", mId=" + mId + ", pId=" + pId + ", rTitle=" + rTitle + ", rContent=" + rContent
				+ ", rFileName=" + rFileName + ", rDate=" + rDate + "]";
	}
}
