package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;

import radware.AppDirector.Layer4Policies.L4Policy;
import service.RadwareService;

import com.sitech.basd.sxcloud.cloud.dao.loadBalancing.L4_policyDao;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.L4PolicyObj;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.util.EncryptUtil;

public class L4_policyServiceImpl extends BaseService implements
		L4_policyService {

	public int deleteByObj(L4PolicyObj obj) {
		// TODO Auto-generated method stub
		L4PolicyObj objall = this.queryByObj(obj);
		int ret = 0;
		L4Policy l4Policy = LoadObjectSwitch.l4ObjSwitch(objall);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.deleteL4Policy(l4Policy);
			ret = l4policyDao.deleteByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int insertByObj(L4PolicyObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		L4Policy l4Policy = LoadObjectSwitch.l4ObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.createL4Policy(l4Policy);
			ret = l4policyDao.insertByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public L4PolicyObj queryByObj(L4PolicyObj obj) {
		// TODO Auto-generated method stub
		return l4policyDao.queryByObj(obj);
	}

	public List queryForListByObj(L4PolicyObj obj) {
		// TODO Auto-generated method stub
		return l4policyDao.queryForListByObj(obj);
	}

	public int updateByObj(L4PolicyObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		L4Policy l4Policy = LoadObjectSwitch.l4ObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.updateL4Policy(l4Policy);
			ret = l4policyDao.updateByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int SynchronousL4Policys() {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			List remotelist = service.getAll_L4Policy();
			List locallist = l4policyDao.queryForListByObj(new L4PolicyObj());
			for (Object object : remotelist) {
				L4Policy l4Policy = (L4Policy) object;
				for (Object object2 : locallist) {
					L4PolicyObj l4PolicyObj = (L4PolicyObj) object2;
					if (l4PolicyObj.getVirtualIP() == l4Policy.getVirtualIP()
							&& l4PolicyObj.getL4Protocol() == l4Policy
									.getL4Protocol().toString()
							&& l4PolicyObj.getL4Port() == l4Policy.getL4Port()
							&& l4PolicyObj.getSourceIPFrom() == l4Policy
									.getSourceIPFrom()) {
						BeanUtils.copyProperties(l4PolicyObj, l4Policy);
						l4PolicyObj.setApplication(l4Policy.getApplication()
								.toString());
						l4PolicyObj.setL4Protocol(l4Policy.getL4Protocol()
								.toString());
						l4PolicyObj.setRedundancyStatus(l4Policy
								.getRedundancyStatus().toString());
						this.updateByObj(l4PolicyObj);
						break;
					}
				}
			}

		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	L4_policyDao l4policyDao;

	public void setL4policyDao(L4_policyDao dao) {
		l4policyDao = dao;
	}

}
