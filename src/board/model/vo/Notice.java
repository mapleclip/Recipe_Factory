package board.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String notoceContent;
	private String noticeWriter;
	private int noticeCount;
	private Date noticeDate;
	private String status;
	
	
	public Notice() {}


	public Notice(String noticeTitle, String notoceContent, String noticeWriter, Date noticeDate) {
		super();
		this.noticeTitle = noticeTitle;
		this.notoceContent = notoceContent;
		this.noticeWriter = noticeWriter;
		this.noticeDate = noticeDate;
	}


	public Notice(int noticeNo, String noticeTitle, String notoceContent, String noticeWriter, int noticeCount,
			Date noticeDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.notoceContent = notoceContent;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeDate = noticeDate;
	}


	public Notice(int noticeNo, String noticeTitle, String notoceContent, String noticeWriter, int noticeCount,
			Date noticeDate, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.notoceContent = notoceContent;
		this.noticeWriter = noticeWriter;
		this.noticeCount = noticeCount;
		this.noticeDate = noticeDate;
		this.status = status;
	}


	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}


	public String getNoticeTitle() {
		return noticeTitle;
	}


	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}


	public String getNotoceContent() {
		return notoceContent;
	}


	public void setNotoceContent(String notoceContent) {
		this.notoceContent = notoceContent;
	}


	public String getNoticeWriter() {
		return noticeWriter;
	}


	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}


	public int getNoticeCount() {
		return noticeCount;
	}


	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}


	public Date getNoticeDate() {
		return noticeDate;
	}


	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", notoceContent=" + notoceContent
				+ ", noticeWriter=" + noticeWriter + ", noticeCount=" + noticeCount + ", noticeDate=" + noticeDate
				+ ", status=" + status + "]";
	}
	
	
}
