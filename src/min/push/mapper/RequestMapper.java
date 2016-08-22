package min.push.mapper;

import java.util.List;

import min.push.entity.App;
import min.push.entity.Notice;
import min.push.entity.Param;
import min.push.entity.Soft;
/**
 * 请求映射接口
 * @author Administrator
 *
 */
public interface RequestMapper extends SqlMapper {
	public App mpswitch(Param param);

	public Notice noticeinfo(Param param);

	public List<Soft> softinfo(Param param);

	public List<Soft> softs(Param param);

	public String getsetupindex(Param param);

	public Param updgrade(Param param);
}
