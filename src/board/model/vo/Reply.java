package board.model.vo;

import java.sql.Date;

public class Reply {
	private int rId;
	private String replyContent;
	private int refBid;
	private String replyWriter;
	private Date createDate;
	private Date modifyDate;
	private String status;
	
	public Reply() {}

	public Reply(int rId, String replyContent, int refBid, String replyWriter, Date createDate, Date modifyDate,
			String status) {
		super();
		this.rId = rId;
		this.replyContent = replyContent;
		this.refBid = refBid;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBid() {
		return refBid;
	}

	public void setRefBid(int refBid) {
		this.refBid = refBid;
	}

	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reply [rId=" + rId + ", replyContent=" + replyContent + ", refBid=" + refBid + ", replyWriter="
				+ replyWriter + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status
				+ "]";
	}
}
