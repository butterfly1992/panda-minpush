package min.push.entity;

import java.io.Serializable;

public class Notice implements Serializable {

	/**
	 * 通知栏通知主题
	 */
	private static final long serialVersionUID = 1L;
	private String logo;
	private String title;
	private String summary;
	private String bgimg;
	private int theme;
	private String titleimg;
	private int times;
	private int nid;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getBgimg() {
		return bgimg;
	}

	public void setBgimg(String bgimg) {
		this.bgimg = bgimg;
	}

 

	public int getTheme() {
		return theme;
	}

	public void setTheme(int theme) {
		this.theme = theme;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getTitleimg() {
		return titleimg;
	}

	public void setTitleimg(String titleimg) {
		this.titleimg = titleimg;
	}

	
}
