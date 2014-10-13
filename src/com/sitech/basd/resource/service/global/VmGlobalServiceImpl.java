package com.sitech.basd.resource.service.global;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.resource.dao.global.VmGlobalDao;
import com.sitech.basd.resource.domain.global.VmGlobalObj;
import com.sitech.basd.resource.domain.top.TopTargetObj;

@Service("vmGlobalService")
public class VmGlobalServiceImpl implements VmGlobalService {

	@Autowired
	private VmGlobalDao vmGlobalDao;

	/**
	 * 
	 * @Title: countHost
	 * @Description: 统计当前集群下主机的个数
	 * @param
	 * @return integer
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime Jul 31, 2013 15:06 PM
	 */
	public Integer countVm(VmGlobalObj obj) {
		return vmGlobalDao.countVm(obj);
	}

	/**
	 * @Title: queryForListByHost
	 * @Description: 根据主机查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByHost(VmGlobalObj vmGlobalObj) {
		return vmGlobalDao.queryForListByHost(vmGlobalObj);
	}
	/**
	 * @Title: queryForListByCluster
	 * @Description: 根据集群查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCluster(VmGlobalObj vmGlobalObj) {
		return vmGlobalDao.queryForListByCluster(vmGlobalObj);
	}
	/**
	 * @Title: queryForListByCenter
	 * @Description: 根据数据中心查询记录
	 * @param
	 * @return List<VmGlobalObj>
	 * @throws
	 * @author lipp
	 * @version 1.0
	 * @createtime 2013-12-1 下午7:05:41
	 */
	public List<VmGlobalObj> queryForListByCenter(VmGlobalObj vmGlobalObj) {
		return vmGlobalDao.queryForListByCenter(vmGlobalObj);
	}

	/**
	 * @Title: queryForTopList
	 * @Description: 查询topN
	 * @param
	 * @return List<TopTargetObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2014-1-26 下午2:08:04
	 */
	@Override
	public List<TopTargetObj> queryForTopList(Map<String, Object> paramMap) {
		List<TopTargetObj> list = new ArrayList<TopTargetObj>();
		String resourceType = (String) paramMap.get("resourceType");
		if ("1".equals(resourceType)) {
			list = vmGlobalDao.queryCPUListVM(paramMap);
		} else {
			list = vmGlobalDao.queryMemListVM(paramMap);
		}
		return list;
	}

}
