package min.push.dao.impl;

import min.push.dao.OperDao;
import min.push.entity.Actuser;
import min.push.entity.Param;
import min.push.entity.Req;
import min.push.mapper.OperateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("operdaoimp")
public class OperDaoImpl implements OperDao {

	@Autowired
	private OperateMapper mapper;

	@Override
	public Object totalActiveu(Param param) {
		// TODO Auto-generated method stub
		Actuser act = mapper.valid(param);
		Object flag = null;
		if (act == null) {// 尚未记录过
			flag = mapper.recordActuser(param);
		}
		return flag;
	}

	@Override
	public Req findindex(Param param) {
		// TODO Auto-generated method stub
		return mapper.findindex(param);
	}

	@Override
	public int update(Param param) {
		// TODO Auto-generated method stub
		return mapper.update(param);
	}

	@Override
	public int insert(Param param) {
		// TODO Auto-generated method stub
		return mapper.insertop(param);
	}

	@Override
	public int updateSoft(Param param) {
		// TODO Auto-generated method stub
		return mapper.updateSoft(param);
	}

	@Override
	public int insertSoft(Param param) {
		// TODO Auto-generated method stub
		return mapper.insertSoft(param);
	}

	@Override
	public int updateNotice(Param param) {
		// TODO Auto-generated method stub
		return mapper.updateNotice(param);
	}

	@Override
	public int insertNotice(Param param) {
		// TODO Auto-generated method stub
		return mapper.insertNotice(param);
	}

	@Override
	public String getsoftid(Param param) {
		// TODO Auto-generated method stub
		Param pm = mapper.getsoftid(param);
		if (pm != null) {
			return pm.getSid();
		} else
			return null;
	}

}
