package min.push.entity;

/**
 * 请求参数
 * 
 * @author Administrator
 * 
 */
public class Param {
	private String imei;// 手机串码
	private String imsi;// 手机卡串码
	private String appid;// 应用id
	private String version;// sdk版本
	private String headkey;/*以上是必备参数*/

	private int mptype;//通知栏通知产品类型
	private String theme;//主题
	private Integer operation;// 运营商
	private String imsitab;//用户表
//	private String setindex;//安装过的产品索引
	/*请求所需参数*/

	private String sid;// 产品id
	private String oper;// 操作(与通知栏操作共用)
	private String sindex;//产品索引
	private String lindex;
	private String cindex;
	private String dindex;
	private String setindex;
	private String time;
	/*操作所需参数*/
	
	private Integer nid;//通知栏id
	
	private String id;//更新apk所需区分运营和开发者的参数，0：运营，1：开发
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getSindex() {
		return sindex;
	}

	public void setSindex(String sindex) {
		this.sindex = sindex;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	public String getHeadkey() {
		return headkey;
	}

	public void setHeadkey(String headkey) {
		this.headkey = headkey;
	}

	public int getMptype() {
		return mptype;
	}

	public void setMptype(int mptype) {
		this.mptype = mptype;
	}

	public String getImsitab() {
		return imsitab;
	}

	public void setImsitab(String imsitab) {
		this.imsitab = imsitab;
	}

	public String getLindex() {
		return lindex;
	}

	public void setLindex(String lindex) {
		this.lindex = lindex;
	}

	public String getCindex() {
		return cindex;
	}

	public void setCindex(String cindex) {
		this.cindex = cindex;
	}

	public String getDindex() {
		return dindex;
	}

	public void setDindex(String dindex) {
		this.dindex = dindex;
	}

	public String getSetindex() {
		return setindex;
	}

	public void setSetindex(String setindex) {
		this.setindex = setindex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
 
}
