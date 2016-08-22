package min.push.biz;

import min.push.entity.Param;




public interface OperService {

	/**
	 * 记录活跃用户
	 * @param param
	 * @return
	 */
	 public Object totalActiveu(Param param);

	 /**
	  * 产品统计操作
	  * @param param
	  * @return
	  */
	public Object recodeoper(Param param);

	/**
	 * 通知栏统计操作
	 * @param param
	 * @return
	 */
	public Object noticeoper(Param param);

	/**
	 * 根据索引查询id
	 * @param param
	 * @return
	 */
	public String getsoftid(Param param);
 
	 
}
