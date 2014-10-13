package com.sitech.basd.bol.service.program;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.program.BolProgramCompositeDao;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramCompositeVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Service("bolProgramCompositeService")
public class BolProgramCompositeServiceImpl extends BaseDao implements BolProgramCompositeService {
	
	@Autowired
	private BolProgramCompositeDao bolProgramCompositeDao;
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询应用程序组成
	 * @param
	 * @return BolProgramCompositeVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:34
	 */
	public BolProgramCompositeVO queryByObj(BolProgramCompositeVO obj) {
		return bolProgramCompositeDao.queryByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询应用程序组成列表
	 * @param
	 * @return List<BolProgramCompositeVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:39:53
	 */
	public List<BolProgramCompositeVO> queryForListByObj(BolProgramCompositeVO obj) {
		return bolProgramCompositeDao.queryForListByObj(obj);
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:20
	 */
	public int insertByObj(BolProgramCompositeVO obj) {
		return bolProgramCompositeDao.insertByObj(obj);
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:40:41
	 */
	public int updateByObj(BolProgramCompositeVO obj) {
		return bolProgramCompositeDao.updateByObj(obj);
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description: 删除应用程序组成信息
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:41:01
	 */
	public int deleteByObj(BolProgramCompositeVO obj) {
		return bolProgramCompositeDao.deleteByObj(obj);
	}
	
}
