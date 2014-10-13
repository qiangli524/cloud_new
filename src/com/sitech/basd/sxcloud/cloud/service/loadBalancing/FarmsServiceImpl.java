package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;

import radware.AppDirector.Farms.Farm;
import service.RadwareService;

import com.sitech.basd.sxcloud.cloud.dao.loadBalancing.FarmsDao;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmObj;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.util.EncryptUtil;

public class FarmsServiceImpl extends BaseService implements FarmsService {

	public int deleteByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		FarmObj objall = this.queryByObj(obj);
		int ret = 0;
		Farm farm = LoadObjectSwitch.farmObjSwitch(objall);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.deleteFarm(farm);
			ret = farmsDao.deleteByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	/**
	 * 添加Farm
	 * 
	 */
	public int insertByObj(FarmObj obj) {
		int ret = 0;
		Farm farm = LoadObjectSwitch.farmObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.createFarm(farm);
			ret = farmsDao.insertByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public FarmObj queryByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		return farmsDao.queryByObj(obj);
	}

	public List queryForListByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		return farmsDao.queryForListByObj(obj);
	}

	public int updateByObj(FarmObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		Farm farm = LoadObjectSwitch.farmObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.updateFarm(farm);
			ret = farmsDao.updateByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int SynchronousFarms() {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			List remotelist = service.getAll_Farm();
			List locallist = farmsDao.queryForListByObj(new FarmObj());
			for (Object object : remotelist) {
				Farm farm = (Farm) object;
				for (Object object2 : locallist) {
					FarmObj farmobj = (FarmObj) object2;
					if (farmobj.getFarmName() == farm.getFarmName()) {
						BeanUtils.copyProperties(farmobj, farm);
						farmobj
								.setAdminStatus(farm.getAdminStatus()
										.toString());
						farmobj.setDispatchMethod(farm.getDispatchMethod()
								.toString());
						farmobj.setSessionsMode(farm.getSessionsMode()
								.toString());
						farmobj.setConnectivityCheckMethod(farm
								.getConnectivityCheckMethod().toString());
						this.updateByObj(farmobj);
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

	FarmsDao farmsDao;

	public void setFarmsDao(FarmsDao farmsDao) {
		this.farmsDao = farmsDao;
	}

}
