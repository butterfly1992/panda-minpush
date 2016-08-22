package min.push.dao.impl;

import java.util.List;

import min.push.dao.ReqDao;
import min.push.entity.App;
import min.push.entity.Notice;
import min.push.entity.Param;
import min.push.entity.Soft;
import min.push.mapper.RequestMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("reqdaoimp")
public class ReqDaoImpl implements ReqDao {

	@Autowired
	private RequestMapper mapper;

	@Override
	public boolean mpswitch(Param param) {
		// TODO Auto-generated method stub
		App app = mapper.mpswitch(param);
		boolean flag = false;
		if (app != null) {
			int mps = app.getMpswitch();
			if (mps == 1) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public List<Soft> getResult(Param param) {
		// TODO Auto-generated method stub
		param.setTheme(String.valueOf(param.getMptype()));
		return mapper.softs(param);
	}

	@Override
	public List<Soft> getTestResult(Param param) {
		// TODO Auto-generated method stub
		param.setTheme(String.valueOf(param.getMptype()));
		return mapper.softinfo(param);
	}

	@Override
	public boolean update(Param param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Notice getnotice(Param param) {
		// TODO Auto-generated method stub
		return mapper.noticeinfo(param);
	}

	@Override
	public boolean updgrade(Param param) {
		// TODO Auto-generated method stub
		if (mapper.updgrade(param) != null) {
			return false;
		} else
			return true;
	}

}
