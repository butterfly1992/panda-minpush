package min.push.dao;

import java.util.List;

import min.push.entity.Notice;
import min.push.entity.Param;
import min.push.entity.Soft;

public interface ReqDao {
	/**
	 * 判断应用是否开启开关
	 * 
	 * @param param
	 * @return
	 */
	public boolean mpswitch(Param param);

	/**
	 * 返回请求结果
	 * 
	 * @param param
	 * @return
	 */
	public List<Soft> getResult(Param param);

	/**
	 * 测试返回数据
	 * 
	 * @param param
	 * @return
	 */
	public List<Soft> getTestResult(Param param);

	/**
	 * sdk更新
	 * 
	 * @param param
	 * @return
	 */
	public boolean update(Param param);

	/**
	 * 返回通知栏信息
	 * 
	 * @param param
	 * @return
	 */
	public Notice getnotice(Param param);

	/**
	 * 验证apk是否需要更新
	 * 
	 * @param param
	 * @return
	 */
	public boolean updgrade(Param param);
}
