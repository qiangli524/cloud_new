package com.sitech.basd.bol.service.program;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.program.BolProgramLibDao;
import com.sitech.basd.bol.domain.nodetask.BolNodeTaskVO;
import com.sitech.basd.bol.domain.process.BolProcessVO;
import com.sitech.basd.bol.domain.program.BolProgramLibVO;
import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VmRelationObj;
import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
@Service("bolProgramLibService")
public class BolProgramLibServiceImpl extends BaseDao implements BolProgramLibService {
	
	@Autowired
	private BolProgramLibDao bolProgramLibDao;
	
	/**
	 * 
	 * @Title: queryByObj
	 * @Description: 查询能力库
	 * @param
	 * @return BolProgramLibVO
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:59:41
	 */
	public BolProgramLibVO queryByObj(BolProgramLibVO obj) {
		return bolProgramLibDao.queryByObj(obj);
	}
	
	/**
	 * 
	 * @Title: queryForListByObj
	 * @Description: 查询能力库列表
	 * @param
	 * @return List<BolProcessVO>
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 上午11:59:57
	 */
	public List<BolProgramLibVO> queryForListByObj(BolProgramLibVO obj) {
		return bolProgramLibDao.queryForListByObj(obj);
	}
	
	/**
	 * 
	 * @Title: insertByObj
	 * @Description: 插入能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:12
	 */
	public int insertByObj(BolProgramLibVO obj) {
		return bolProgramLibDao.insertByObj(obj);
	}
	
	/**
	 * 
	 * @Title: updateByObj
	 * @Description: 更新能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:25
	 */
	public int updateByObj(BolProgramLibVO obj) {
		return bolProgramLibDao.updateByObj(obj);
	}
	
	/**
	 * 
	 * @Title: deleteByObj
	 * @Description:删除能力库
	 * @param
	 * @return int
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2014-3-7 下午12:00:46
	 */
	public int deleteByObj(BolProgramLibVO obj) {
		return bolProgramLibDao.deleteByObj(obj);
	}
	
}
