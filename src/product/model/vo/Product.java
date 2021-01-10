package product.model.vo;

public class Product {
	private String pId;
	private String pName;
	private String pPrice;
	private String pCategory;
	private String pDescription;
	private String pOrigin;
	private String pCapacity;
	
	public Product() {}
	
	public Product(String pId, String pName, String pPrice, String pCategory, String pDescription, String pOrigin,
			String pCapacity) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pCategory = pCategory;
		this.pDescription = pDescription;
		this.pOrigin = pOrigin;
		this.pCapacity = pCapacity;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpPrice() {
		return pPrice;
	}

	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public String getpOrigin() {
		return pOrigin;
	}

	public void setpOrigin(String pOrigin) {
		this.pOrigin = pOrigin;
	}

	public String getpCapacity() {
		return pCapacity;
	}

	public void setpCapacity(String pCapacity) {
		this.pCapacity = pCapacity;
	}

	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", pPrice=" + pPrice + ", pCategory=" + pCategory
				+ ", pDescription=" + pDescription + ", pOrigin=" + pOrigin + ", pCapacity=" + pCapacity + "]";
	}
}
