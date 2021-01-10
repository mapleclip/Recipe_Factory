package board.model.vo;

import java.sql.Date;

public class MRFAttachment {
	
	private int fileId;
	private int m_R_NO;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private int downloadCount;
	private String status;
	
	public MRFAttachment() {
		
	}

	public MRFAttachment(int fileId, int m_R_NO, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, int downloadCount, String status) {
		super();
		this.fileId = fileId;
		this.m_R_NO = m_R_NO;
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

	public int getM_R_NO() {
		return m_R_NO;
	}

	public void setM_R_NO(int m_R_NO) {
		this.m_R_NO = m_R_NO;
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
		return "MRFAttachment [fileId=" + fileId + ", m_R_NO=" + m_R_NO + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel
				+ ", downloadCount=" + downloadCount + ", status=" + status + "]";
	}
	
	

}
