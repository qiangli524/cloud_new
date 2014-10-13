package com.sitech.basd.sxcloud.rsmu.dao.softmanage;

import java.util.List;
import java.util.Map;

import com.sitech.basd.sxcloud.rsmu.domain.softmanage.TbSysAppObj;

public interface TbSysAppDao {

	/**
	 * @Title:根据应用部分信息查询匹配的所有应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryForListByObj(TbSysAppObj obj);

	/**
	 * @Title:查询出具体应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public TbSysAppObj queryByObj(TbSysAppObj obj);

	/**
	 * @Title:更新应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int updateByObj(TbSysAppObj obj);

	/**
	 * @Title:删除应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int deleteByObj(TbSysAppObj obj);

	/**
	 * @Title:插入应用信息
	 * @Copyright: Copyright (c) 201006
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public int insertByObj(TbSysAppObj obj);

	/**
	 * @Title:启动或停止服务状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int StartAndStopByObj(TbSysAppObj obj);

	/**
	 * @Title:更改应用部署状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int deployRequest(TbSysAppObj obj);

	/**
	 * @Title:查询出当前页面的应用信息
	 * @Copyright: Copyright (c) 201007
	 * @Company: si-tech
	 * @author yangwenchao
	 * @version 1.0
	 */
	public List queryListIDByObj(TbSysAppObj obj);

	/**
	 * @Title:更新捕获状态
	 * @Copyright: Copyright (c) 201008
	 * @Company: si-tech
	 * @author wangzca
	 * @version 1.0
	 */
	public int catchRequestByObj(TbSysAppObj obj);

	/**
	 * 
	 * @Title: updateOnlinePath
	 * @Description: 更新上线或回滚文件列表
	 * @param
	 * @return int
	 * @throws
	 * @author huojla
	 * @version 1.0
	 * @createtime 2013-4-15 下午2:38:47
	 */
	public int updateOnlinePath(TbSysAppObj obj);

	/**
	 * 
	 * @Title: queryForSheet
	 * @Description:导入主机数据前下载模板查询一些基准应用的基本信息写到excel的sheet页中
	 * @author duangh
	 * @date Jun 15, 2013 6:13:59 PM
	 * @return
	 */
	public List<Map<String, String>> queryForSheet();

	/**
	 * 根据id集合获取所有的基准应用
	 * 
	 * @author lipengpeng
	 * @param appIds
	 * @return
	 */
	public List<TbSysAppObj> queryForListByIds(String appid);

	/**
	 * 查询业务系统下的基准应用个数
	 * 
	 * @param bizsysidstr
	 * @return
	 */
	public int countApp(TbSysAppObj tbSysAppObj);

	/**
	 * @Title: queryForListUseIn
	 * @Description: 根据id的集合查询应用
	 * @param
	 * @return List<TbSysAppObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-8-18 下午9:08:30
	 */
	public List<TbSysAppObj> queryForListUseIn(TbSysAppObj tbSysAppObj);
}
