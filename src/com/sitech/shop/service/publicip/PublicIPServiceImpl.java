package com.sitech.shop.service.publicip;

import ip.IpType;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sitech.basd.sxcloud.cloud.dao.net.TbIpDao;
import com.sitech.basd.sxcloud.cloud.domain.net.TbCloud2IpInfoObj;
import com.sitech.basd.sxcloud.rsmu.web.util.LogHelper;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.shop.dao.publicIP.PublicIPDao;
import com.sitech.shop.domain.ip.PublicIPObj;
import com.sitech.ssd.ah.nas.dao.NasFileSysDao;
import com.sitech.ssd.ah.nas.domain.VmIpObj;
import com.sitech.utils.publicShop.PublicCloudConstant;
import com.sitech.utils.publicShop.PublicCloudSshUtil;
import com.sitech.utils.randomid.RandomUUID;

/**
 * @Title PublicIPServiceImpl
 * @Description 公网IP业务层实现类
 * @author lipp
 * @date 2014-4-25 下午1:19:56
 * @version 1.0
 */
@Service("publicIPService")
public class PublicIPServiceImpl implements PublicIPService {
	private static final Logger logger = LoggerFactory.getLogger(PublicIPServiceImpl.class);
	@Autowired
	private PublicIPDao publicIPDao;

	@Autowired
	private TbIpDao intranetIPDao;
	@Autowired
	private PropertyUtil sshProp;
	@Autowired
	private NasFileSysDao nasFileSysDao;

	/**
	 * @Title: list
	 * @Description: 展现列表
	 * @throws
	 * @Date 2014-4-25 下午1:59:48
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public List<PublicIPObj> list(PublicIPObj ipObj) {
		return publicIPDao.queryForListUnionTablesByObj(ipObj);
	}

	/**
	 * @Title: apply
	 * @Description: 申请公网ip
	 * @throws
	 * @Date 2014-4-25 下午2:25:36
	 * @author lipp
	 * @param ipObj
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int apply(PublicIPObj ipObj) {
		int ret = 0;
		// 查询闲置公网ip
		TbCloud2IpInfoObj ip = new TbCloud2IpInfoObj();
		ip.setIP_TYPE(IpType.public_ip);// 公网IP
		ip.setISUSED("0");// 未使用的
		List ipList = intranetIPDao.queryForListByObj(ip);
		List<TbCloud2IpInfoObj> needList = new ArrayList<TbCloud2IpInfoObj>();
		if (ipList != null && ipList.size() >= ipObj.getIpNums()) {// 有足够的闲置的公网IP
			needList = ipList.subList(0, ipObj.getIpNums());
			for (TbCloud2IpInfoObj t : needList) {
				PublicIPObj ipo = new PublicIPObj();
				ipo.setUser_id(ipObj.getUser_id());
				ipo.setStatus(0);
				ipo.setDescription(ipObj.getDescription());
				ipo.setRegion_id(ipObj.getRegion_id());
				ipo.setIpaddress(t.getIPADDRESS());
				// 占用公网IP
				t.setISUSED("1");
				intranetIPDao.updateIPByObj(t);
				// 建立公网IP和用户的关系
				publicIPDao.insertByObj(ipo);
			}
		} else {
			ret = -1;
			LogHelper.error("申请公网ip失败：没有足够的闲置的公网IP");
		}

		return ret;
	}

	/**
	 * @Title: release
	 * @Description: 释放对公网ip的持有
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:13:09
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public int release(PublicIPObj ipObj) {
		// 批量释放公网IP
		int ret = 0;
		try {
			String idarr = ipObj.getIpaddress();
			List<PublicIPObj> list = new ArrayList<PublicIPObj>();
			List<TbCloud2IpInfoObj> ipList = new ArrayList<TbCloud2IpInfoObj>();
			String[] arr = idarr.split(",");
			for (String string : arr) {
				TbCloud2IpInfoObj obj = new TbCloud2IpInfoObj();
				obj.setIPADDRESS(string);
				obj.setISUSED("0");
				ipList.add(obj);
			}
			ret = intranetIPDao.updateIPStateForBatch(ipList);// 更新IP表的相应IP地址的状态
			// 并且将相应的公网IP的状态更新为未使用状态
			String[] ids = ipObj.getId().split(",");
			for (String id : ids) {
				PublicIPObj obj = new PublicIPObj();
				obj.setId(id);
				list.add(obj);
			}
			publicIPDao.releaseBatch(list);// 删除关系表的相应数据

		} catch (Exception e) {
			e.printStackTrace();
			ret = -1;
		}

		return ret;
	}

	/**
	 * @Title: bind
	 * @Description: 虚拟机绑定到ip
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:30:19
	 * @author lipp
	 * @param ipObj
	 * @return
	 */

	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int bind(PublicIPObj ipObj) throws Exception {
		int ret = 0;
		try {
			// 0.查询改虚拟机用于映射公网的内网IP地址
			VmIpObj inner_ip = this.getIntranetIP(ipObj);
			if (inner_ip == null) {
				return ret = -1;
			}
			String cmd = sshProp.getString("path") + " " + sshProp.getString("bind") + " "
					+ ipObj.getIpaddress() + " " + inner_ip.getIp();
			// 1.调用脚本绑定公网IP地址
			logger.info("执行绑定公网IP脚本：" + cmd);
			String result = PublicCloudSshUtil.executeSshCmd(sshProp.getString("ip"),
					sshProp.getString("username"), sshProp.getString("password"),cmd);
			if (PublicCloudConstant.SUCCESS.equals(result)) {
				// 2.将公网IP地址中心的部分信息进行更新
				logger.info("执行绑定公网IP脚本：成功");
				ipObj.setStatus(1);
				ipObj.setIntranet_ip(inner_ip.getIp());
				publicIPDao.updateByObj(ipObj);// 更新被绑定的公网IP地址的记录
			}else {
				logger.error("执行绑定公网IP脚本：失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return ret;
	}

	public VmIpObj getIntranetIP(PublicIPObj obj) {
		VmIpObj ip = new VmIpObj();
		ip.setIpType(IpType.intranet_ip);
		ip.setConnectid(obj.getConnect_id());
		ip.setVm_uuid(obj.getEntity_id());
		List<VmIpObj> list = nasFileSysDao.queryVmIpRelation(ip);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * @Title: unbind
	 * @Description: 解除绑定
	 * @return int
	 * @throws
	 * @Date 2014-4-25 下午3:33:39
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	//@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int unbind(PublicIPObj ipObj) throws Exception {
		int ret = -1;
		try {
			// 调用脚本解除绑定关系
			List<PublicIPObj> list = publicIPDao.queryForListByObj(ipObj);
			if (list != null && list.size() > 0) {
				ipObj = list.get(0);
			} else {
				return ret;
			}
			// 1.调用脚本解除绑定公网IP地址
			String cmd = sshProp.getString("path") + " " + sshProp.getString("unbind") + " "
					+ ipObj.getIpaddress() + " " + ipObj.getIntranet_ip();
			logger.info("执行解绑定公网IP脚本：" + cmd);
			String result = PublicCloudSshUtil.executeSshCmd(sshProp.getString("ip"),
					sshProp.getString("username"), sshProp.getString("password"),cmd);
			
			if (PublicCloudConstant.SUCCESS.equals(result)) {
				logger.info("执行解绑公网IP脚本：成功");
				ret = publicIPDao.releaseByObj(ipObj);// 释放公网IP和虚拟机的绑定关系
			}else{
				logger.error("执行解绑公网IP脚本：失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return ret;
	}
	
	/**
		释放公网IP和虚拟机的绑定关系
	 */
	public Boolean releaseByPublicIp(PublicIPObj ipObj){
		return publicIPDao.releaseByObj(ipObj) > 0;
	}

	/**
	 * @Title: update
	 * @Description: 修改ip
	 * @throws
	 * @Date 2014-4-26 上午10:50:42
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public PublicIPObj update(PublicIPObj ipObj, String userId) {
		List<PublicIPObj> list = new ArrayList<PublicIPObj>();
		if (!"1".equals(userId)) {
			list = publicIPDao.queryForListByObj(ipObj);
		} else {
			list = publicIPDao.queryForListUnionTablesByObj(ipObj);
		}
		if (list.size() > 0) {
			ipObj = list.get(0);
		} else {
			ipObj = new PublicIPObj();
		}
		return ipObj;
	}

	/**
	 * @Title: save
	 * @Description: 保存修改
	 * @throws
	 * @Date 2014-4-28 上午10:31:22
	 * @author lipp
	 * @return
	 */
	@Override
	public void save(PublicIPObj ipObj, String oper) {
		if ("add".equals(oper)) {
			String uuid = RandomUUID.getUuid();
			ipObj.setId(uuid);
			ipObj.setStatus(0);
			publicIPDao.insertByObj(ipObj);
		} else {
			publicIPDao.updateByObj(ipObj);
		}
	}

	/**
	 * @Title: delete
	 * @Description: 删除记录
	 * @return int
	 * @throws
	 * @Date 2014-4-29 下午3:01:54
	 * @author lipp
	 * @param ipObj
	 * @return
	 */
	@Override
	public int delete(PublicIPObj ipObj) {
		return publicIPDao.deleteByObj(ipObj);
	}

	/**
	 * 
	 * @Title: bindPublicIP
	 * @Description: 绑定公网IP地址 更新tb_public_ip intranet_ip
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:07:36
	 */
	public Boolean bindPublicIP(String path, PublicIPObj ip) {
		Boolean result = false;
		// 调用脚本 成功后 更新result 值为true
		if (result) {
			result = publicIPDao.updateByObj(ip) > 0 ? true : false;
		}

		return result;
	}

	/**
	 * 
	 * @Title: cancelBindPublicIP
	 * @Description: 取消绑定公网IP 更新tb_public_ip表 intranet_ip = Null
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:09:52
	 */
	public Boolean cancelBindPublicIP(String path, PublicIPObj ip) {
		Boolean result = false;
		// 调用脚本 成功后 更新result 值为true
		if (result) {
			result = publicIPDao.cancelBindByObj(ip) > 0 ? true : false;
		}
		return result;
	}

	/**
	 * 
	 * @Title: addInnerIPMapping
	 * @Description: 在已绑定公网IP做内网出口映射
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:19:17
	 */
	public Boolean addInnerIPMapping(String path, PublicIPObj ip) {
		Boolean result = false;
		return result;
	}

	/**
	 * 
	 * @Title: cancelInnerIPMapping
	 * @Description: 取消在已绑定公网IP做内网出口映射
	 * @param
	 * @return Boolean
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-1 上午10:19:21
	 */
	public Boolean cancelInnerIPMapping(String path, PublicIPObj ip) {
		Boolean result = false;
		return result;
	}


	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: queryForCount
	 * </p>
	 * <p>
	 * Description:根据地域，用户，查询公网IP数量
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param obj
	 * @return
	 * @see service.ip.PublicIPService#queryForCount(domain.ip.PublicIPObj)
	 */
	@Override
	public Integer queryForCount(PublicIPObj obj) {
		return publicIPDao.queryForCount(obj);
	}

	/**
	 * (非 Javadoc)
	 * <p>
	 * Title: updateStatusByIp
	 * </p>
	 * <p>
	 * Description: 根据公网iP地址修改IP的状态
	 * </p>
	 * 
	 * @author wanglei_bj@si-tech.com.cn
	 * @param publicIPObj
	 * @return
	 * @see service.ip.PublicIPService#updateStatusByIp(domain.ip.PublicIPObj)
	 */
	@Override
	public Boolean updateStatusByIp(PublicIPObj publicIPObj) {
		Boolean result = false;
		result = publicIPDao.updateStatusByIp(publicIPObj) == 0 ? true : false;
		return result;
	}


	/** (非 Javadoc) 
	* <p>Title: queryVlanofVM</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param ipObj
	* @return 
	* @see service.ip.PublicIPService#queryVlanofVM(domain.ip.PublicIPObj) 
	*/
	@Override
	public Integer queryVlanofVM(PublicIPObj ipObj) {
		// 查询改虚拟机用于映射公网的内网IP地址
		VmIpObj inner_ip = this.getIntranetIP(ipObj);
		if (inner_ip == null) {
			return 0;
		}
		String ip = inner_ip.getIp();
		String[] str = ip.split("\\.");
		return Integer.valueOf(str[2]);
	}

	/** (非 Javadoc) 
	* <p>Title: queryForObj</p> 
	* <p>Description: </p> 
	* @author wanglei_bj@si-tech.com.cn 
	* @param ipObj
	* @return 
	* @see com.sitech.shop.service.publicip.PublicIPService#queryForObj(com.sitech.shop.domain.ip.PublicIPObj) 
	*/
	@Override
	public PublicIPObj queryForObj(PublicIPObj ipObj) {
		return publicIPDao.queryForObj(ipObj);
	}
}
