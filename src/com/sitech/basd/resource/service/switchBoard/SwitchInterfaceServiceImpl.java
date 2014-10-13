package com.sitech.basd.resource.service.switchBoard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import publiccloud.PublicCloudConstant;

import com.sitech.basd.resource.dao.switchBoard.SwitchInterfaceDao;
import com.sitech.basd.resource.domain.switchBoard.RouterConfigObj;
import com.sitech.basd.resource.domain.switchBoard.SwitchInterfaceObj;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.utils.publicShop.PublicCloudSshUtil;

@Service("switchInterfaceService")
public class SwitchInterfaceServiceImpl implements SwitchInterfaceService {

	@Autowired
	private SwitchInterfaceDao switchInterfaceDao;
	@Autowired
	private PropertyUtil sshProp;

	@Override
	public List<SwitchInterfaceObj> queryInterfaceList(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.queryInterfaceList(obj);
	}

	@Override
	public int insertInterface(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.insertInterface(obj);
	}

	@Override
	public int updateInterface(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.updateInterface(obj);
	}

	@Override
	public int deleteInterface(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.deleteInterface(obj);
	}

	@Override
	public int updateVlanId(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.updateVlanId(obj);
	}

	@Override
	public int updateVlanIdNull(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.updateVlanIdNull(obj);
	}

	@Override
	public int deleteVlanByInterfaceId(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.deleteVlanByInterfaceId(obj);
	}

	@Override
	public SwitchInterfaceObj queryVlanIDandInterName(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.queryVlanIDandInterName(obj);
	}

	@Override
	public List<SwitchInterfaceObj> queryInterList(SwitchInterfaceObj obj) {
		// TODO Auto-generated method stub
		return switchInterfaceDao.queryInterList(obj);
	}

	/**
	 * 
	 * @Title: staticRouterConfig
	 * @Description: 静态路由配置
	 * @param
	 * @return int
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-6-1 下午3:34:23
	 */
	public int staticRouterConfig(RouterConfigObj router) {
		int ret = -1;
		// 调用脚本完成交换机上的静态路由配置
		try {
			String cmd = "sh " + sshProp.getString("static_router");
			String result = PublicCloudSshUtil
					.executeSshCmd(sshProp.getString("switch_ip"),
							sshProp.getString("switch_username"),
							sshProp.getString("switch_password"), cmd);// 参数分别为IP，用户名，密码，命令（包括传递的参数）
			if (PublicCloudConstant.SUCCESS.equals(result)) {
				ret = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;

	}
}
