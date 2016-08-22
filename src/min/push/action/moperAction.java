package min.push.action;

import min.push.biz.OperService;
import min.push.entity.Param;
import min.push.tool.MemcacheUtil;
import min.push.tool.Utils;
import min.push.tool.Variable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.danga.MemCached.MemCachedClient;

/**
 * 操作action入口
 * 
 * @author Administrator
 * 
 */
@Controller
public class moperAction {
	private OperService operserimp;

	@RequestMapping(value = "/moper", method = { RequestMethod.POST })
	public @ResponseBody
	Object operation(Param param) {// 产品操作返回数据统计
		Object o = null;
		if (Utils.isNULL(param.getImei()) || Utils.isNULL(param.getAppid())
				|| Utils.isNULL(param.getVersion())
				|| Utils.isNULL(param.getOper())
				|| Utils.isNULL(param.getSid())
				|| Utils.isNULL(param.getHeadkey())) {
			Utils.log.error("[appid为" + param.getAppid() + "；imei为"
					+ param.getImei() + "；版本为" + param.getVersion() + "；oper为"
					+ param.getOper() + "；sindex为" + param.getSindex()
					+ "；headkey为 " + param.getHeadkey() + ";]");
			return Variable.errorJson;
		}
		if (!param.getHeadkey().equals(Utils.getMD5(param.getImei()))) {
			Utils.log.error("[headkey值错误;]");
			return Variable.errorJson;
		}
		if (Utils.isNULL(param.getSindex())) {
			String sid = operserimp.getsoftid(param);
			param.setSid(sid);
		}
		param.setTime(Utils.DateTime());
		o = operserimp.recodeoper(param);
		Utils.log.info("result：" + o);
		return o;
	}

	@RequestMapping(value = "/mact", method = { RequestMethod.POST })
	public @ResponseBody
	Object activeuser(Param param) {// 统计活跃量
		Object o = null;
		if (Utils.isNULL(param.getImei()) || Utils.isNULL(param.getAppid())
				|| Utils.isNULL(param.getVersion())
				|| Utils.isNULL(param.getHeadkey())) {
			Utils.log.error("[appid为" + param.getAppid() + "；imei为"
					+ param.getImei() + "；版本为" + param.getVersion()
					+ "；headkey为 " + param.getHeadkey() + ";]");
			return Variable.errorJson;
		}
		if (!param.getHeadkey().equals(Utils.getMD5(param.getImei()))) {
			Utils.log.error("[headkey值错误;]");
			return Variable.errorJson;
		}

		param.setTime(Utils.DateTime());
		o = operserimp.totalActiveu(param);
		Utils.log.info("actuser --End--『imei:" + param.getImei() + ";appid："
				+ param.getAppid() + ";res:" + o + "』");
		return o;
	}

	@RequestMapping(value = "/mnooper", method = { RequestMethod.POST })
	public @ResponseBody
	Object noticeoper(Param param) {// 通知栏的操作数据
		Object o = null;
		if (Utils.isNULL(param.getImei()) || Utils.isNULL(param.getAppid())
				|| Utils.isNULL(param.getVersion())
				|| Utils.isNULL(param.getOper()) || (param.getNid() == null)
				|| Utils.isNULL(param.getHeadkey())) {
			Utils.log.error("[appid为" + param.getAppid() + "；imei为"
					+ param.getImei() + "；版本为" + param.getVersion() + "；oper为"
					+ param.getOper() + "；headkey为 " + param.getHeadkey()
					+ "；nid为 " + param.getNid() + ";]");
			return Variable.errorJson;
		}
		if (!param.getHeadkey().equals(Utils.getMD5(param.getImei()))) {
			Utils.log.error("[headkey值错误;]");
			return Variable.errorJson;
		}
		param.setTime(Utils.DateTime());
		o = operserimp.noticeoper(param);
		Utils.log.info("result：" + o);
		return o;
	}

	@RequestMapping(value = "/memdel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object memdel(Param param) {// 通知栏的操作数据
		MemCachedClient mcc = MemcacheUtil.getInstance();
		boolean a1 = mcc.delete("mp_status" + param.getImei());
		return Variable.correntJson + "a1:" + a1;
	}

	@RequestMapping(value = "/memtestdel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object memtestdel(Param param) {// 通知栏的操作数据
		MemCachedClient mcc = MemcacheUtil.getInstance();
		boolean amem1 = mcc.delete("notice_" + param.getImei());
		boolean amem2 = mcc.delete("softs_" + param.getImei());
		return Variable.correntJson + "amem1:" + amem1 + "amem2:" + amem2;
	}

	@Autowired
	public void setOperserimp(OperService operserimp) {
		this.operserimp = operserimp;
	}

}
