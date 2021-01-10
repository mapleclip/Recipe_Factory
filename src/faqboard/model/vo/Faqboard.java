package faqboard.model.vo;

public class Faqboard {
	private int fboard_no;
	private String fboard_writer;
	private String fboard_title;
	private String fboard_content;
	private String status;
	
	public Faqboard() {
		super();
	}

	public Faqboard(int fboard_no, String fboard_writer, String fboard_title, String fboard_content,String status) {
		super();
		this.fboard_no = fboard_no;
		this.fboard_writer = fboard_writer;
		this.fboard_title = fboard_title;
		this.fboard_content = fboard_content;
		this.status = status;
	}

	public Faqboard(String fboard_writer, String fboard_title, String fboard_content) {
		super();
		this.fboard_writer = fboard_writer;
		this.fboard_title = fboard_title;
		this.fboard_content = fboard_content;
	}

	public int getFboard_no() {
		return fboard_no;
	}

	public void setFboard_no(int fboard_no) {
		this.fboard_no = fboard_no;
	}

	public String getFboard_writer() {
		return fboard_writer;
	}

	public void setFboard_writer(String fboard_writer) {
		this.fboard_writer = fboard_writer;
	}

	public String getFboard_title() {
		return fboard_title;
	}

	public void setFboard_title(String fboard_title) {
		this.fboard_title = fboard_title;
	}

	public String getFboard_content() {
		return fboard_content;
	}

	public void setFboard_content(String fboard_content) {
		this.fboard_content = fboard_content;
	}

	@Override
	public String toString() {
		return "Faqboard [fboard_no=" + fboard_no + ", fboard_writer=" + fboard_writer + ", fboard_title="
				+ fboard_title + ", fboard_content=" + fboard_content + ", status=" + status + "]";
	}

	
	
	
}
