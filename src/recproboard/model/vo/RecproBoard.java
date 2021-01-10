package recproboard.model.vo;

import java.sql.Date;

public class RecproBoard {
	private int recpro_no;
	private String recpro_title;
	private String recpro_content;
	private String recpro_writer;
	private Date recpro_date;
	private String status;
	
	public RecproBoard() {}

	public RecproBoard(int recpro_no, String recpro_title, String recpro_content, String recpro_writer,
			Date recpro_date, String status) {
		super();
		this.recpro_no = recpro_no;
		this.recpro_title = recpro_title;
		this.recpro_content = recpro_content;
		this.recpro_writer = recpro_writer;
		this.recpro_date = recpro_date;
		this.status = status;
	}

	public RecproBoard(int recpro_no, String recpro_title, String recpro_writer, Date recpro_date, String status) {
		super();
		this.recpro_no = recpro_no;
		this.recpro_title = recpro_title;
		this.recpro_writer = recpro_writer;
		this.recpro_date = recpro_date;
		this.status = status;
	}

	public RecproBoard(String recpro_title, String recpro_content, String recpro_writer) {
		super();
		this.recpro_title = recpro_title;
		this.recpro_content = recpro_content;
		this.recpro_writer = recpro_writer;
	}

	public RecproBoard(int recpro_no, String recpro_title, String recpro_content, String recpro_writer,
			Date recpro_date) {
		super();
		this.recpro_no = recpro_no;
		this.recpro_title = recpro_title;
		this.recpro_content = recpro_content;
		this.recpro_writer = recpro_writer;
		this.recpro_date = recpro_date;
	}

	public int getRecpro_no() {
		return recpro_no;
	}

	public void setRecpro_no(int recpro_no) {
		this.recpro_no = recpro_no;
	}

	public String getRecpro_title() {
		return recpro_title;
	}

	public void setRecpro_title(String recpro_title) {
		this.recpro_title = recpro_title;
	}

	public String getRecpro_content() {
		return recpro_content;
	}

	public void setRecpro_content(String recpro_content) {
		this.recpro_content = recpro_content;
	}

	public String getRecpro_writer() {
		return recpro_writer;
	}

	public void setRecpro_writer(String recpro_writer) {
		this.recpro_writer = recpro_writer;
	}

	public Date getRecpro_date() {
		return recpro_date;
	}

	public void setRecpro_date(Date recpro_date) {
		this.recpro_date = recpro_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RecproBoard [recpro_no=" + recpro_no + ", recpro_title=" + recpro_title + ", recpro_content="
				+ recpro_content + ", recpro_writer=" + recpro_writer + ", recpro_date=" + recpro_date + ", status="
				+ status + "]";
	}
	
	
}
