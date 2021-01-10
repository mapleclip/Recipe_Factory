package qnaboard.model.vo;

import java.sql.Date;


public class QnaBoard {
	private int qboardNo;
	private String qboard_title;
	private String qboard_content;
	private String qboard_writer;
	private Date qboard_date;
	public QnaBoard(int qboardNo, String qboard_title, String qboard_content, String qboard_writer, Date qboard_date,
			String status, String rep_content, String rep_writer) {
		super();
		this.qboardNo = qboardNo;
		this.qboard_title = qboard_title;
		this.qboard_content = qboard_content;
		this.qboard_writer = qboard_writer;
		this.qboard_date = qboard_date;
		this.status = status;
		this.rep_content = rep_content;
		this.rep_writer = rep_writer;
	}

	private String status;
	private String rep_content;
	private String rep_writer;
	
	public String getRep_writer() {
		return rep_writer;
	}

	public void setRep_writer(String rep_writer) {
		this.rep_writer = rep_writer;
	}

	public String getRep_content() {
		return rep_content;
	}

	public void setRep_content(String rep_content) {
		this.rep_content = rep_content;
	}

	

	public QnaBoard(int qboardNo, String qboard_title, String qboard_content, String qboard_writer, Date qboard_date,
			String rep_content, String rep_writer) {
		super();
		this.qboardNo = qboardNo;
		this.qboard_title = qboard_title;
		this.qboard_content = qboard_content;
		this.qboard_writer = qboard_writer;
		this.qboard_date = qboard_date;
		this.rep_content = rep_content;
		this.rep_writer = rep_writer;
	}

	public QnaBoard() {}

	public QnaBoard(int qboardNo, String qboard_title, String qboard_writer, Date qboard_date) {
		super();
		this.qboardNo = qboardNo;
		this.qboard_title = qboard_title;
		this.qboard_writer = qboard_writer;
		this.qboard_date = qboard_date;
	}

	



	public QnaBoard(int qboardNo, String qboard_title, String qboard_writer, String rep_content) {
		super();
		this.qboardNo = qboardNo;
		this.qboard_title = qboard_title;
		this.qboard_writer = qboard_writer;
		this.rep_content = rep_content;
	}

	public QnaBoard(int qboardNo, String qboard_title, String qboard_content, String qboard_writer, Date qboard_date) {
		super();
		this.qboardNo = qboardNo;
		this.qboard_title = qboard_title;
		this.qboard_content = qboard_content;
		this.qboard_writer = qboard_writer;
		this.qboard_date = qboard_date;
	}

	
	public QnaBoard(String qboard_title, String qboard_content) {
		super();
		this.qboard_title = qboard_title;
		this.qboard_content = qboard_content;
	}

	
	public QnaBoard(String qboard_title, String qboard_content, String qboard_writer) {
		super();
		this.qboard_title = qboard_title;
		this.qboard_content = qboard_content;
		this.qboard_writer = qboard_writer;
	}

	public int getQboardNo() {
		return qboardNo;
	}

	public void setQboardNo(int qboardNo) {
		this.qboardNo = qboardNo;
	}

	public String getQboard_title() {
		return qboard_title;
	}

	public void setQboard_title(String qboard_title) {
		this.qboard_title = qboard_title;
	}

	public String getQboard_content() {
		return qboard_content;
	}

	public void setQboard_content(String qboard_content) {
		this.qboard_content = qboard_content;
	}

	public String getQboard_writer() {
		return qboard_writer;
	}

	public void setQboard_writer(String qboard_writer) {
		this.qboard_writer = qboard_writer;
	}

	public Date getQboard_date() {
		return qboard_date;
	}

	public void setQboard_date(Date qboard_date) {
		this.qboard_date = qboard_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QnaBoard [qboardNo=" + qboardNo + ", qboard_title=" + qboard_title + ", qboard_content="
				+ qboard_content + ", qboard_writer=" + qboard_writer + ", qboard_date=" + qboard_date + ", status="
				+ status + "]";
	}
	
	
}
