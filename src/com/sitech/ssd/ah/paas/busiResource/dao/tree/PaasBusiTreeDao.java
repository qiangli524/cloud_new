package com.sitech.ssd.ah.paas.busiResource.dao.tree;

import java.util.List;

import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.domain.tree.PaasTreeObj;

/**
 * <p>Title: PaasBusiTreeDao</p>
 * <p>Description: TODO(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:33:18
 *
 */
public interface PaasBusiTreeDao {
/**
 * @Title: queryForPaasBusiTree
 * @Description: 查询资源树
 * @param
 * @return List<PaasBusiTreeObj>
 * @throws
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:46:57
 */
public List<PaasBusiTreeObj> queryForPaasBusiTree(PaasBusiTreeObj obj);
/**
 * @Title: insertByObj
 * @Description: 添加
 * @param
 * @return int
 * @throws
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:48:54
 */
public int insertByObj(PaasBusiTreeObj obj);

/**
 * @Title: updateByObj
 * @Description: 修改
 * @param
 * @return int
 * @throws
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:48:30
 */
public int updateByObj(PaasBusiTreeObj obj);

/**
 * @Title: deleteByObj
 * @Description: 删除
 * @param
 * @return int
 * @throws
 * @author qism
 * @version 1.0
 * @createtime 2014-7-24 下午5:48:14
 */
public int deleteByObj(PaasBusiTreeObj obj);
}
