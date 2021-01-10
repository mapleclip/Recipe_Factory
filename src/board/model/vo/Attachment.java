package board.model.vo;

import java.sql.Date;

public class Attachment {
	private int fileId; // 사진에 대한 pk (시퀀스로 만들어 겹치지 않게 함)
	private int boardId; // 첨부파일이 들어간 것에 대한 게시판 번호
	private String originName; // 사진파일명 
	private String changeName; // 바뀐사진파일명
	private String filePath;
	private Date uploadDate;
	private int fileLevel; // 섬네일이다(0) 내용에들어갈 사진이다(1)
	private int downloadCount;
	private String status;
	
	public Attachment() {}

	public Attachment(int boardId, String changeName) {
		super();
		this.boardId = boardId;
		this.changeName = changeName;
	}

	public Attachment(int fileId, int boardId, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status) {
		super();
		this.fileId = fileId;
		this.boardId = boardId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.downloadCount = downloadCount;
		this.status = status;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [fileId=" + fileId + ", boardId=" + boardId + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", downloadCount=" + downloadCount + ", status=" + status + "]";
	}
}
