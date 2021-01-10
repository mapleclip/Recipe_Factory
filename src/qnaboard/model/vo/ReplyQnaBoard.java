package qnaboard.model.vo;

import java.sql.Date;

public class ReplyQnaBoard {
	private int reply_qna_no;
	private String reply_qna_title;
	private String reply_qna_content;
	private String reply_qna_writer;
	private Date reply_qna_date;
	private String status;
	public ReplyQnaBoard() {}
	
	
	public ReplyQnaBoard(int reply_qna_no, String reply_qna_title, String reply_qna_content, String reply_qna_writer,
			Date reply_qna_date, String status) {
		super();
		this.reply_qna_no = reply_qna_no;
		this.reply_qna_title = reply_qna_title;
		this.reply_qna_content = reply_qna_content;
		this.reply_qna_writer = reply_qna_writer;
		this.reply_qna_date = reply_qna_date;
		this.status = status;
	}


	public ReplyQnaBoard(int reply_qna_no, String reply_qna_title, String reply_qna_content, String reply_qna_writer,
			Date reply_qna_date) {
		super();
		this.reply_qna_no = reply_qna_no;
		this.reply_qna_title = reply_qna_title;
		this.reply_qna_content = reply_qna_content;
		this.reply_qna_writer = reply_qna_writer;
		this.reply_qna_date = reply_qna_date;
	}


	public int getReply_qna_no() {
		return reply_qna_no;
	}


	public void setReply_qna_no(int reply_qna_no) {
		this.reply_qna_no = reply_qna_no;
	}


	public String getReply_qna_title() {
		return reply_qna_title;
	}


	public void setReply_qna_title(String reply_qna_title) {
		this.reply_qna_title = reply_qna_title;
	}


	public String getReply_qna_content() {
		return reply_qna_content;
	}


	public void setReply_qna_content(String reply_qna_content) {
		this.reply_qna_content = reply_qna_content;
	}


	public String getReply_qna_writer() {
		return reply_qna_writer;
	}


	public void setReply_qna_writer(String reply_qna_writer) {
		this.reply_qna_writer = reply_qna_writer;
	}


	public Date getReply_qna_date() {
		return reply_qna_date;
	}


	public void setReply_qna_date(Date reply_qna_date) {
		this.reply_qna_date = reply_qna_date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ReplyQnaBoard [reply_qna_no=" + reply_qna_no + ", reply_qna_title=" + reply_qna_title
				+ ", reply_qna_content=" + reply_qna_content + ", reply_qna_writer=" + reply_qna_writer
				+ ", reply_qna_date=" + reply_qna_date + ", status=" + status + "]";
	}
	
	
}
