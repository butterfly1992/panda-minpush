package min.push.action;

import min.push.biz.ReqService;
import min.push.entity.Param;
import min.push.tool.Utils;
import min.push.tool.Variable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 请求action入口
 * @author Administrator
 *
 */
@Controller
public class mreqAction {
	private ReqService reqserimp;

	@RequestMapping(value = "/mreq", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object validate(Param param) {// 请求返回数据方法
		Object object = null;
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
//			return Variable.errorJson;
		}
		Utils.log.info("========req_start=============");
		// 测试id返回数据
		if (Variable.testId.contains(param.getAppid())
				|| Variable.testImei.contains(param.getImei())) {
			object = reqserimp.getTestResult(param);
			Utils.log.info("【result:" + object + " 】");
			if (object == null)
				return Variable.errorJson;
			return object;
		}
		boolean mpswitch = reqserimp.mpswitch(param);
		if (!mpswitch) {
			Utils.log.info("【mpswitch: close :" + param.getAppid() + "】");
			return Variable.errorJson;
		}
		object = reqserimp.getResult(param);
		Utils.log.info("【result:" + object + " 】");
		if (object == null)
			return Variable.errorJson;
		return object;
	}

	@RequestMapping(value = "/mupd", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	Object activeuser(Param param) {// 更新
		Object o = null;
		if (Utils.isNULL(param.getImei()) || Utils.isNULL(param.getAppid())
				|| Utils.isNULL(param.getVersion())
				|| Utils.isNULL(param.getId())) {
			Utils.log.error("[appid为" + param.getAppid() + "；imei为"
					+ param.getImei() + "；版本为" + param.getVersion() + "；Flag为 "
					+ param.getId() + ";]");
			return Variable.errorJson;
		}
		boolean update = reqserimp.updgrade(param);
		o = (update) ? Variable.correntJson : Variable.errorJson;
		Utils.log.info("『appid：" + param.getAppid() + "flag:" + param.getId()
				+ "imei:" + param.getImei() + "res:" + o);
		return o;
	}

	@Autowired
	public void setReqserimp(ReqService reqserimp) {
		this.reqserimp = reqserimp;
	}

}
