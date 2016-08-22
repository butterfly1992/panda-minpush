package min.push.dao;

import min.push.entity.Param;
import min.push.entity.Req;

public interface OperDao {
	/**
	 * 统计活跃用户
	 * 
	 * @param param
	 * @return
	 */
	public Object totalActiveu(Param param);

	/**
	 * 验证索引是否有效
	 * 
	 * @param param
	 * @return
	 */
	public Req findindex(Param param);

	/**
	 * 更新索引
	 * 
	 * @param param
	 * @return
	 */
	public int update(Param param);

	/**
	 * 插入新用户
	 * 
	 * @param param
	 * @return
	 */
	public int insert(Param param);

	/**
	 * 更新产品操作
	 * @param param
	 * @return
	 */
	public int updateSoft(Param param);

	/**
	 * 添加产品操作
	 * @param param
	 * @return
	 */
	public int insertSoft(Param param);

	/**
	 * 更新通知栏操作
	 * @param param
	 * @return
	 */
	public int updateNotice(Param param);

	/**
	 * 添加通知栏操作
	 * @param param
	 * @return
	 */
	public int insertNotice(Param param);
	
	/**
	 * 根据索引查询id
	 * @param param
	 * @return
	 */
	public String getsoftid(Param param);
  
}
