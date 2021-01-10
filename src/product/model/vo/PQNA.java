package product.model.vo;

import java.sql.Date;

public class PQNA {
	private int pqbNo;
	private String pId;
	private String mId;
	private String pqbTitle;
	private String pqbContent;
	private String pqbAnswer;
	private Date pqbDate;
	private Date pqbADate;
	
	public PQNA() {}

	public PQNA(int pqbNo, String pId, String mId, String pqbTitle, String pqbContent, String pqbAnswer, Date pqbDate,
			Date pqbADate) {
		super();
		this.pqbNo = pqbNo;
		this.pId = pId;
		this.mId = mId;
		this.pqbTitle = pqbTitle;
		this.pqbContent = pqbContent;
		this.pqbAnswer = pqbAnswer;
		this.pqbDate = pqbDate;
		this.pqbADate = pqbADate;
	}

	public int getPqbNo() {
		return pqbNo;
	}

	public void setPqbNo(int pqbNo) {
		this.pqbNo = pqbNo;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getPqbTitle() {
		return pqbTitle;
	}

	public void setPqbTitle(String pqbTitle) {
		this.pqbTitle = pqbTitle;
	}

	public String getPqbContent() {
		return pqbContent;
	}

	public void setPqbContent(String pqbContent) {
		this.pqbContent = pqbContent;
	}

	public String getPqbAnswer() {
		return pqbAnswer;
	}

	public void setPqbAnswer(String pqbAnswer) {
		this.pqbAnswer = pqbAnswer;
	}

	public Date getPqbDate() {
		return pqbDate;
	}

	public void setPqbDate(Date pqbDate) {
		this.pqbDate = pqbDate;
	}

	public Date getPqbADate() {
		return pqbADate;
	}

	public void setPqbADate(Date pqbADate) {
		this.pqbADate = pqbADate;
	}

	@Override
	public String toString() {
		return "PQNA [pqbNo=" + pqbNo + ", pId=" + pId + ", mId=" + mId + ", pqbTitle=" + pqbTitle + ", pqbContent="
				+ pqbContent + ", pqbAnswer=" + pqbAnswer + ", pqbDate=" + pqbDate + ", pqbADate=" + pqbADate + "]";
	}
}
