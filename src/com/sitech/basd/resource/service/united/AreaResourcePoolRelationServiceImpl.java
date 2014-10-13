package com.sitech.basd.resource.service.united;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.util.PropertyUtil;

@Service("areaResourcePoolRelationService")
public class AreaResourcePoolRelationServiceImpl implements AreaResourcePoolRelationService {
	@Autowired
	private PropertyUtil areaResourcePoolRelation;
	
	private PropertyUtil propertyUtil;
	
	private static final Logger log = LoggerFactory
			.getLogger(AreaResourcePoolRelationServiceImpl.class);

	/**
	 * 
	 * @Title: getResourcePoolInfo
	 * @Description: 通过资源池的唯一标示来查询其对应的地域标识（地域标识为数表中的id）
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-11-13 上午11:16:59
	 */
	public String getAreaId(String resourceCode,String pid) {
		String areaId = "";
		try {
			areaId = areaResourcePoolRelation.getString(resourceCode);
			if (areaId == null || "".equals(areaId)) {
				areaId = "1";
			}
			log.info("资源池标识为：" + resourceCode + "," + "对应的地域标识为：" + resourceCode);
		} catch (Exception e) {
			log.info("资源池标识为：" + areaId + "," + "对应的地域标识为：" + resourceCode);
			/**
			 * 赋值xen测试数据中心节点的id作为其父节点的
			 */
			areaId = pid;
			e.printStackTrace();
		}
		return areaId;
	}

	/**
	 * 
	 * @Title: getConnectCodeList
	 * @Description:通过地域ID查询其对应的vCenter标示
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-4-17 下午7:01:17
	 */
	public String getConnectCodeList(String areaId) {
		String connectId = "";
		try {
			connectId = areaResourcePoolRelation.getString(areaId);
			log.info("资源池标识为：" + connectId + "," + "对应的地域标识为：" + areaId);
		} catch (Exception e) {
			log.error("资源池标识为：" + connectId + "," + "对应的地域标识为：" + areaId);
			e.printStackTrace();
		}
		return connectId;
	}
}
