package com.sitech.basd.bol.service.program;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.program.BolProgramDao;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Service("bolProgramService")
public class BolProgramServiceImpl extends BaseDao implements BolProgramService {
	
	@Autowired
	private BolProgramDao bolProgramDao;
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序
	 * @param
	 * @return BolProgramVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:25
	 */
	public BolProgramVO queryByObj(BolProgramVO obj) {
		return bolProgramDao.queryByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序列表
	 * @param
	 * @return List<BolProgramVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:21:51
	 */
	public List<BolProgramVO> queryForListByObj(BolProgramVO obj) {
		return bolProgramDao.queryForListByObj(obj);
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:19
	 */
	public int insertByObj(BolProgramVO obj) {
		return bolProgramDao.insertByObj(obj);
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:36
	 */
	public int updateByObj(BolProgramVO obj) {
		return bolProgramDao.updateByObj(obj);
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:22:50
	 */
	public int deleteByObj(BolProgramVO obj) {
		return bolProgramDao.deleteByObj(obj);
	}
	
}
