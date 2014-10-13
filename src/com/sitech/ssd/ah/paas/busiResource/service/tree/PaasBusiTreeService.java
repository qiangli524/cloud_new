package com.sitech.ssd.ah.paas.busiResource.service.tree;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
/**
 * 
 * <p>Title: PaasBusiTreeService</p>
 * <p>Description: paas资源树节点数据服务</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-28 上午11:50:26
 *
 */
public interface PaasBusiTreeService {
	/**
	 * 
	 * @Title: queryForPaasBusiTree
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return List<PaasBusiTreeObj>
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午5:59:31
	 */
	public List<PaasBusiTreeObj> queryForPaasBusiTree(HttpServletRequest request);
	/**
	 * @Title: insertByObj
	 * @Description: 插入
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午6:00:14
	 */
	public String insertByObj(PaasBusiTreeObj obj);
	/**
	 * @Title: toSaveHosts
	 * @Description:用于保存关联主机
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-25 下午5:39:07
	 */
	public String toSaveHosts(String parent_id,String h_uuids,String h_names,String server_type);
	/**
	 * @Title: updateByObj
	 * @Description: 修改
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午6:00:21
	 */
	public String updateByObj(PaasBusiTreeObj obj);
	/**
	 * @Title: deleteByObj
	 * @Description: 删除
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-24 下午6:00:27
	 */
	public String deleteByObj(PaasBusiTreeObj obj);
	/**
	 * @Title: toSaveVmHosts
	 * @Description: 保存虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-7-30 下午5:28:07
	 */
	public String toSaveVmHosts(String parent_id,String vm_ids,String vm_names,String server_type);
}
