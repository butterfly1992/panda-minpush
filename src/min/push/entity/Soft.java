package min.push.entity;

import java.io.Serializable;

/**
 * 产品广告
 * 
 * @author Administrator
 * 
 */
public class Soft implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String logo;
	private String name;
	private String apkurl;
	private String sindex;
	private String pck;
	private String sizes;
	private String info2;
	private Integer downs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApkurl() {
		return apkurl;
	}

	public void setApkurl(String apkurl) {
		this.apkurl = apkurl;
	}

	public String getSindex() {
		return sindex;
	}

	public void setSindex(String sindex) {
		this.sindex = sindex;
	}

	public String getPck() {
		return pck;
	}

	public void setPck(String pck) {
		this.pck = pck;
	}

	public String getSizes() {
		return sizes;
	}

	public void setSizes(String sizes) {
		this.sizes = sizes;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public Integer getDowns() {
		return downs;
	}

	public void setDowns(Integer downs) {
		this.downs = downs;
	}

	
}
