package board.model.vo;

import java.sql.Date;

public class Board {
	private int boardId;
	private int boardType;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int boardCount;
	private Date createDate;
	private Date modifyDate;
	private String status;

	public Board() {}

	public Board(int boardId, int boardType, String boardTitle, String boardContent, String boardWriter, int boardCount,
			Date createDate, Date modifyDate, String status) {
		super();
		this.boardId = boardId;
		this.boardType = boardType;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.boardCount = boardCount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
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
		return "Board [boardId=" + boardId + ", boardType=" + boardType + ", boardTitle=" + boardTitle
				+ ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", boardCount=" + boardCount
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}

	
	
}
