package min.push.entity;
/**
 * 活跃用户
 * @author Administrator
 *
 */
public class Actuser {
	private String imei;// 手机串码
	private String imsi;// 手机卡串码
	private String appid;// 应用id
	private String version;// sdk版本
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	

}
