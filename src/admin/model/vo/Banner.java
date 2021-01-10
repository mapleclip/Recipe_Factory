package admin.model.vo;

import java.sql.Date;

public class Banner {
	private int bannerId;
	private String bannerTitle;
	private String bannerContent;
	private Date createDate;
	
	public Banner() {}

	public Banner(int bannerId, String bannerTitle, String bannerContent, Date createDate) {
		super();
		this.bannerId = bannerId;
		this.bannerTitle = bannerTitle;
		this.bannerContent = bannerContent;
		this.createDate = createDate;
	}

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getBannerContent() {
		return bannerContent;
	}

	public void setBannerContent(String bannerContent) {
		this.bannerContent = bannerContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", bannerTitle=" + bannerTitle + ", bannerContent=" + bannerContent
				+ ", createDate=" + createDate + "]";
	}
	
	

}
