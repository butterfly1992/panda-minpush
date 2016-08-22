package min.push.entity;
/**
 * 用户请求记录
 * @author Administrator
 *
 */
public class Req {
	private String imei;
	private String imsi;
	private String lookindex;
	private String clickindex;
	private String downindex;
	private String setupindex;
	private String date;

	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	 
	public String getClickindex() {
		return clickindex;
	}
	public void setClickindex(String clickindex) {
		this.clickindex = clickindex;
	}
	public String getDownindex() {
		return downindex;
	}
	public void setDownindex(String downindex) {
		this.downindex = downindex;
	}
	public String getSetupindex() {
		return setupindex;
	}
	public void setSetupindex(String setupindex) {
		this.setupindex = setupindex;
	}
	public String getLookindex() {
		return lookindex;
	}
	public void setLookindex(String lookindex) {
		this.lookindex = lookindex;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
