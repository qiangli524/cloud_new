package com.sitech.basd.yicloud.dao.xenconn;

import java.util.List;

import com.sitech.basd.yicloud.domain.xenconn.ConnectionInfo;

public interface ConnInfoDao {

	/**
	 * xcp资源池的连接信息
	 * <p>
	 * Title: insertConnInfoByObj
	 * </p>
	 * 
	 * @return int
	 * @author: shijincheng;
	 * @throws
	 */
	public int insertConnInfoByObj(ConnectionInfo obj);

	/**
	 * 查询链接通过pooUuid
	 * <p>
	 * Title: queryConnInfoByObj
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return ConnectionInfo
	 * @author: shijincheng;
	 * @throws
	 */
	public ConnectionInfo queryConnInfoByObj(ConnectionInfo obj);
	
	/**
	 * 
	 * @Title: queryAllConn
	 * @Description: 查询所有链接信息
	 * @param
	 * @return List
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-1-22 下午02:02:25
	 */
	public List queryAllConn(ConnectionInfo obj);

	/**
	 * 通过pooluuid更新链接信息
	 * <p>
	 * Title: queryConnInfoByObj
	 * </p>
	 * <p>
	 * Description:TODO
	 * </p>
	 * 
	 * @return ConnectionInfo
	 * @author: shijincheng;
	 * @throws
	 */
	public int updateConnInfoByObj(ConnectionInfo obj);
}
