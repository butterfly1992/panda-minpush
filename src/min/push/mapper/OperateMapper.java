package min.push.mapper;

import min.push.entity.Actuser;
import min.push.entity.Param;
import min.push.entity.Req;
/**
 * 操作映射接口
 * @author Administrator
 *
 */
public interface OperateMapper extends SqlMapper {

	public Object totalActiveu(Param param);

	public Actuser valid(Param param);

	public int recordActuser(Param param);

	public Req findindex(Param param);

	public int update(Param param);

	public int insertop(Param param);

	public int updateSoft(Param param);

	public int insertSoft(Param param);

	public Param getsoftid(Param param);

	/**
	 * 更新通知栏操作
	 * 
	 * @param param
	 * @return
	 */
	public int updateNotice(Param param);

	/**
	 * 添加通知栏操作
	 * 
	 * @param param
	 * @return
	 */
	public int insertNotice(Param param);
}
