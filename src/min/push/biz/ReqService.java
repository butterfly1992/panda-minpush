package min.push.biz;

import java.util.Map;

import min.push.entity.Param;


public interface ReqService {
	/**
	 * 判断应用是否开启开关
	 * @param param
	 * @return
	 */
	public boolean mpswitch(Param param);
 
	/**
	 * 返回请求结果
	 * @param param
	 * @return
	 */
	public Map<String, Object> getResult(Param param);
	/**
	 * 测试返回数据,一轮一轮的来，中间有次没有数据是在清空，下次从头来
	 * @param param
	 * @return
	 */
	public Map<String, Object> getTestResult(Param param);
	/**
	 * sdk更新
	 * @param param
	 * @return
	 */
	public boolean update(Param param);

	/**
	 * 判断apk是否更新
	 * @param param
	 * @return
	 */
	public boolean updgrade(Param param);
}
