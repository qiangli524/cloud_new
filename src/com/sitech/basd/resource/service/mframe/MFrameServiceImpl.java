package com.sitech.basd.resource.service.mframe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.mframe.MFrameDao;
import com.sitech.basd.resource.domain.mframe.MFrameObj;

@Service("MFrameService")
public class MFrameServiceImpl implements MFrameService {

	@Autowired
	private MFrameDao mFrameDao;

	/**
	 * 
	 * @Title: ConfigInfoList
	 * @Description: 查询配置文件列表
	 * @param
	 * @return List
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime May 22, 2013 10:04:03 AM
	 */

	@Override
	public List<MFrameObj> queryFrameList(MFrameObj obj) {
		return mFrameDao.queryFrameList(obj);
	}

	/**
	 * 
	 * @Title: FrameList
	 * @Description: 增加一条机框记录
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int insertFrame(MFrameObj obj) {
		return mFrameDao.insertFrame(obj);
	}

	/**
	 * 
	 * @Title: FrameList
	 * @Description: 修改机框信息
	 * @param
	 * @return List
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 */
	public int updateFrame(MFrameObj obj) {
		return mFrameDao.updateFrame(obj);
	}

	/**
	 * 
	 * @Title: deleteFrame
	 * @Description: 删除一条机框记录
	 * @param
	 * @return int
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime jul 15, 2013 4:41:09 PM
	 */
	public int deleteFrame(MFrameObj obj) {
		return mFrameDao.deleteFrame(obj);
	}
}
