package min.push.biz.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import min.push.biz.ReqService;
import min.push.dao.ReqDao;
import min.push.entity.Notice;
import min.push.entity.Param;
import min.push.entity.Soft;
import min.push.tool.MemcacheUtil;
import min.push.tool.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.danga.MemCached.MemCachedClient;

@Service("reqserimp")
@Transactional
public class ReqServiceImpl implements ReqService {

	private ReqDao reqdaoimp;
	private MemCachedClient mcc =MemcacheUtil.getInstance();
	//Date expiryd = getDefinedDateTime(23, 59, 59, 0);// 当天的23:59:59:00

	@Override
	public boolean mpswitch(Param param) {
		// TODO Auto-generated method stub
		return reqdaoimp.mpswitch(param);
	}

	@Override
	// 正式id返回的数据
	public Map<String, Object> getResult(Param param) {
		// TODO Auto-generated method stub
		Map<String, Object> object = null;
		int hour = Integer.parseInt(Utils.DateTime("HH"));// 获取当前请求时间
		Utils.log.info("『imei:" + param.getImei() + ";hour：" + hour + ";appid："
				+ param.getAppid() + "』");
		if (hour >= 0 && hour < 5) {// 此时间段不返回数据
			return null;
		}
		int[] mpstatus = initmps(param);// 初始化有效次数
		if (mpstatus == null) {
			return null;
		}
		if (hour >= 6 && hour < 13 && mpstatus[0] == 0) {// 此时间段不返回数据
			object = getreqResult(param, 0, mpstatus);
		} else if (hour >= 13 && hour < 18 && mpstatus[1] == 0) {// 此时间段不返回数据
			object = getreqResult(param, 1, mpstatus);
		} else if (hour >= 18 && hour <= 23) {
			int flag = -1;
			for (int i = 0; i < mpstatus.length; i++) {
				if (mpstatus[i] == 0) {
					flag = i;
					break;
				}
			}
			if (flag >= 0) {
				object = getreqResult(param, flag, mpstatus);
			}
		}
		if (true) {
			String str = "『";
			for (int i : mpstatus) {
				str += i + ",";
			}
			Utils.log.error("『imei:" + param.getImei() + ";hour：" + hour
					+ ";mpstatus：" + str + "』");
		}
		return object;
	}

	/**
	 *  返回请求数据
	 * 
	 * @param param
	 * @param i
	 * @param mpstatus
	 * @return
	 */
	private Map<String, Object> getreqResult(Param param, int i, int[] mpstatus) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = null;
		Notice notice = null;
		int type = 1;
		if (mcc.get("mp_theme" + param.getImei()) != null) {// 如果记录过就直接提取
			type = (Integer) mcc.get("mp_theme" + param.getImei());
			mcc.set("mp_theme" + param.getImei(), type + 1);
		} else {
			mcc.set("mp_theme" + param.getImei(), type + 1);
		}
		if (mcc.get("mp_notice" + param.getImei()) != null) {// 获取通知栏对象
			notice = (Notice) mcc.get("mp_notice" + param.getImei());
		}
		if (notice != null) {
			if (type != notice.getTheme()) {
				param.setMptype(type);
				notice = reqdaoimp.getnotice(param);
			}
		} else {// 如果通知栏对象为null，则重新获取
			param.setMptype(1);
			type = 1;
			mcc.set("mp_theme" + param.getImei(), 2);
			notice = reqdaoimp.getnotice(param);
		}
		param.setMptype(type);
		maps = extractjson(maps, notice);
		param = Utils.analyzeImsi(param);
		List<Soft> softs = reqdaoimp.getResult(param);
		if (softs != null) {
			maps.put("result", softs);
			mpstatus[i] = 1;
			Date expiryd = getDefinedDateTime(23, 59, 59, 0);
			mcc.set("mp_status" + param.getImei(), mpstatus, expiryd);
			Utils.log.info("『imei:" + param.getImei() + "；appid:"
					+ param.getAppid() + ";number：" + i + "；type:"
					+ notice.getTheme() + "(1.游戏，2.应用)；len:" + softs.size()
					+ "』");
		}

		return maps;
	}

	/**
	 * 初始化有效请求次数
	 * 
	 * @param param
	 * @return
	 */
	private int[] initmps(Param param) {
		// TODO Auto-generated method stub
		int[] mpstatus = null;
		Date expiryd = getDefinedDateTime(23, 59, 59, 0);
		Utils.log.info("『imei:" + param.getImei() + " init』");
		int type = 1;
		if (mcc.get("mp_theme" + param.getImei()) != null) {// 如果记录过就直接提取
			type = (Integer) mcc.get("mp_theme" + param.getImei());
		}
		if (mcc.get("mp_status" + param.getImei()) != null) {// 从mem中获取用户的标识
			mpstatus = (int[]) mcc.get("mp_status" + param.getImei());
			Utils.log.info("『imei:" + param.getImei() + "；mpstatus:（mem）"
					+ mpstatus[0] + "," + mpstatus[1]+"』");
		} else {
			param.setMptype(type);
			Notice notice = reqdaoimp.getnotice(param);
			if (notice != null) {
				mpstatus = new int[notice.getTimes()];
				mcc.set("mp_status" + param.getImei(), mpstatus, expiryd);
				mcc.set("mp_notice" + param.getImei(), notice, getDefinedHour());
				mcc.set("mp_theme" + param.getImei(), notice.getTheme());
			} else {
				mcc.delete("mp_theme" + param.getImei());
				mpstatus = new int[2];
				mcc.set("mp_status" + param.getImei(), mpstatus, expiryd);
			}
			Utils.log.info("『imei:" + param.getImei() + "；mpstatus:(new)"
					+ mpstatus[0] + "," + mpstatus[1]+"』");
		}
	
		return mpstatus;
	}

	@Override
	// 测试id返回的数据
	public Map<String, Object> getTestResult(Param param) {
		// TODO Auto-generated method stub
		Map<String, Object> maps = null;
		Date expiryd = getDefinedDateTime(23, 59, 59, 0);
		int type = 1;// 默认通知栏类型从1开始
		if (mcc.get("tn_type_" + param.getImei()) != null) {
			type = (Integer) mcc.get("tn_type_" + param.getImei());
			mcc.set("tn_type_" + param.getImei(), type + 1, expiryd);
		} else {
			mcc.set("tn_type_" + param.getImei(), type + 1, expiryd);
		}
		param.setMptype(type);
		Notice notice = null;
		if (mcc.get("notice_" + param.getMptype()) != null) {
			notice = (Notice) mcc.get("notice_" + param.getMptype());
		} else {
			notice = reqdaoimp.getnotice(param);
			mcc.set("notice_" + param.getMptype(), notice, expiryd);
		}
		if (notice == null) {// 如果查询的通知栏没有数据时，直接删除存储通知栏的mem,return回去，重新开始
			mcc.delete("tn_type_" + param.getImei());
			return null;
		}
		if (notice != null) {
			maps = extractjson(maps, notice);
			List<Soft> softs = null;
			if (mcc.get("softs_" + param.getMptype()) != null) {
				softs = (List<Soft>) mcc.get("softs_" + param.getMptype());
			} else {
				softs = reqdaoimp.getTestResult(param);
				mcc.set("softs_" + param.getMptype(), softs, expiryd);
			}
			Utils.log.info("『type:" + notice.getTheme() + "；len:"
					+ softs.size() + "』");
			maps.put("result", softs);
		}
		return maps;
	}

	private Map<String, Object> extractjson(Map<String, Object> maps,
			Notice notice) {
		// TODO Auto-generated method stub
		maps = new HashMap<String, Object>();
		maps.put("flag", "1");
		maps.put("logo", notice.getLogo());
		maps.put("title", notice.getTitle());
		maps.put("summary", notice.getSummary());
		maps.put("bgimg", notice.getBgimg());
		maps.put("nid", notice.getNid());
		maps.put("timg", notice.getTitleimg());
		return maps;
	}

	@Override
	public boolean update(Param param) {
		// TODO Auto-generated method stub
		return false;
	}

	public Date getDefinedDateTime(int hour, int minute, int second,
			int milliSecond) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, milliSecond);
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}

	public Date getDefinedHour() {
		Calendar cal = Calendar.getInstance();
		/* HOUR_OF_DAY 指示一天中的小时 */
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 2);
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}

	@Autowired
	public void setReqdaoimp(ReqDao reqdaoimp) {
		this.reqdaoimp = reqdaoimp;
	}

	@Override
	public boolean updgrade(Param param) {
		// TODO Auto-generated method stub
		return reqdaoimp.updgrade(param);
	}

}
