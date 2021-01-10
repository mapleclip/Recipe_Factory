package product.model.vo;

public class PAttachment {
	private int fileId;
	private String pId;
	private String originName;
	private String changeName;
	private String filePath;
	private int fileLevel;
	
	public PAttachment() {}

	public PAttachment(int fileId, String pId, String originName, String changeName, String filePath, int fileLevel) {
		super();
		this.fileId = fileId;
		this.pId = pId;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.fileLevel = fileLevel;
	}

	
	public PAttachment(String pId, String changeName) {
		super();
		this.pId = pId;
		this.changeName = changeName;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	@Override
	public String toString() {
		return "Attachment [fileId=" + fileId + ", pId=" + pId + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", fileLevel=" + fileLevel + "]";
	}
}
