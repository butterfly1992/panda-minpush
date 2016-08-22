package min.push.biz.impl;

import min.push.biz.OperService;
import min.push.dao.OperDao;
import min.push.entity.Param;
import min.push.entity.Req;
import min.push.tool.Utils;
import min.push.tool.Variable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("operserimp")
@Transactional
public class OperServiceImpl implements OperService {

	private OperDao operdaoimp;

	@Override
	public Object totalActiveu(Param param) {
		// TODO Auto-generated method stub
		if (operdaoimp.totalActiveu(param) != null) {
			return Variable.correntJson;
		} else
			return Variable.errorJson;
	}

	@Override
	public Object recodeoper(Param param) {// 统计操作
		// TODO Auto-generated method stub
		int result = 0;
		String oper = param.getOper();
		Utils.log.info("『appid:" + param.getAppid() + "；sids:"
				+ param.getSindex() + "；oper:" + param.getOper() + ";imei："
				+ param.getImei() + "』");
		boolean record = true;//默认尚未记录过
		if (!Variable.testImei.contains(param.getImei())) {// 正式用户记录用户索引信息
			param = Utils.analyzeImsi(param);
			param.setSindex("p" + param.getSindex());
			if (oper.equals("1")) {
				param.setLindex(param.getSindex());
			} else if (oper.equals("2")) {
				param.setCindex(param.getSindex());
			} else if (oper.equals("3")) {
				param.setDindex(param.getSindex());
			} else if (oper.equals("4")) {
				param.setSetindex(param.getSindex());
			}
			record = validsindex(param, oper);
			if (record) {// 尚未记录过
				if (oper.equals("4")) {
					result = operdaoimp.update(param);
					if (result == 0) {
						result = operdaoimp.insert(param);
					}
				}
			}
		}
		if (record) {
			result = operdaoimp.updateSoft(param);
			if (result == 0) {
				if (oper.equals("1")) {
					param.setLindex("1");
				} else if (oper.equals("2")) {
					param.setCindex("1");
				} else if (oper.equals("3")) {
					param.setDindex("1");
				} else if (oper.equals("4")) {
					param.setSetindex("1");
				}
				result = operdaoimp.insertSoft(param);
			}
		}
		if (result > 0) {
			return Variable.correntJson;
		} else
			return Variable.errorJson;
	}

	/**
	 * 验证此索引是否有效
	 * 
	 * @param param
	 * @param oper
	 * @return
	 */
	private boolean validsindex(Param param, String oper) {
		// TODO Auto-generated method stub
		boolean flag = true;
		Req req = operdaoimp.findindex(param);
		if (req != null) {
			if (oper.equals("4") && req.getSetupindex() != null
					&& req.getSetupindex().contains(param.getSindex())) {
				flag = false;
			}
		}
		return flag;
	}

	@Autowired
	public void setOperdaoimp(OperDao operdaoimp) {
		this.operdaoimp = operdaoimp;
	}

	@Override
	public Object noticeoper(Param param) {
		// TODO Auto-generated method stub
		String oper = param.getOper();
		if (!(oper.equals("-1") || oper.equals("0"))) {
			Utils.log.error("oper:参数错误：" + oper);
			return Variable.errorJson;
		}
		int flag = operdaoimp.updateNotice(param);
		if (flag == 0) {
			flag = operdaoimp.insertNotice(param);
		}
		Utils.log.info("『notice:" + param.getNid() + ";oper:" + oper
				+ "（-1:通知栏，0：全屏）』");
		if (flag > 0) {
			return Variable.correntJson;
		} else
			return Variable.errorJson;
	}

	@Override
	public String getsoftid(Param param) {
		// TODO Auto-generated method stub
		return null;
	}
}
