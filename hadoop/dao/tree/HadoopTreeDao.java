package dao.tree;

import java.util.List;

import domain.service.ServiceObj;
import domain.tree.HadoopTreeObj;

@SuppressWarnings("all")
public interface HadoopTreeDao {
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-1-4 下午8:53:20
	 */
	public List queryForListByObj(HadoopTreeObj obj);
	
	/**
	 * @Title: insertByObj
	 * @Description: 插入一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:36
	 */
	public int insertByObj(HadoopTreeObj hadoopTreeObj);
	
	/**
	 * @Title: updateByObj
	 * @Description: 更新一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:07:59
	 */
	public int updateByObj(HadoopTreeObj hadoopTreeObj);
	
	/**
	 * @Title: deleteByObj
	 * @Description: 删除一条记录
	 * @param
	 * @return int
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-5 下午2:08:14
	 */
	public int deleteByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * 
	 * @Title: querybyObj
	 * @Description: 查询一条父节点记录
	 * @param
	 * @return HadoopTreeObj
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-1-7 上午10:49:48
	 */
	public HadoopTreeObj queryByObj(HadoopTreeObj hadoopTreeObj);

	/**
	 * @Title: queryForServiceList
	 * @Description: 查询服务集合
	 * @param
	 * @return List<ServiceNodeObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2014-2-20 下午4:08:59
	 */
	public List<ServiceObj> queryForServiceList(HadoopTreeObj treeObj);
}
