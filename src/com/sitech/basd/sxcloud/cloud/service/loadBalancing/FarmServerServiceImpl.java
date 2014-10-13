package com.sitech.basd.sxcloud.cloud.service.loadBalancing;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;

import radware.AppDirector.Farms.FarmServer;
import service.RadwareService;

import com.sitech.basd.sxcloud.cloud.dao.loadBalancing.FarmsServerDao;
import com.sitech.basd.sxcloud.cloud.domain.loadBalancing.FarmServerObj;
import com.sitech.basd.sxcloud.rsmu.config.Constant;
import com.sitech.basd.sxcloud.rsmu.service.BaseService;
import com.sitech.basd.sxcloud.util.EncryptUtil;

public class FarmServerServiceImpl extends BaseService implements
		FarmServerService {

	public int deleteByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		FarmServerObj objall = this.queryByObj(obj);
		int ret = 0;
		FarmServer farmServer = LoadObjectSwitch.serviceObjSwitch(objall);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.deleteFarmServer(farmServer);
			ret = farmsServerDao.deleteByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int insertByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		FarmServer farmServer = LoadObjectSwitch.serviceObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.createFarmServer(farmServer);
			ret = farmsServerDao.insertByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public FarmServerObj queryByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		return farmsServerDao.queryByObj(obj);
	}

	public List queryForListByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		return farmsServerDao.queryForListByObj(obj);
	}

	public int updateByObj(FarmServerObj obj) {
		// TODO Auto-generated method stub
		int ret = 0;
		FarmServer farmServer = LoadObjectSwitch.serviceObjSwitch(obj);
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			service.updateFarmServer(farmServer);
			ret = farmsServerDao.updateByObj(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			ret = -1;
		} catch (ServiceException e) {
			e.printStackTrace();
			ret = -1;
		}
		return ret;
	}

	public int SynchronousFarmServers() {
		// TODO Auto-generated method stub
		int ret = 0;
		try {
			RadwareService service = new RadwareService(
					Constant.Radware_Address, Constant.Radware_UserName,
					EncryptUtil.decode(Constant.Radware_Password));
			List remotelist = service.getAll_FarmServers();
			List locallist = farmsServerDao
					.queryForListByObj(new FarmServerObj());
			for (Object object : remotelist) {
				FarmServer server = (FarmServer) object;
				for (Object object2 : locallist) {
					FarmServerObj serverobj = (FarmServerObj) object2;
					if (serverobj.getFarmName() == server.getFarmName()
							&& serverobj.getServerAddress() == server
									.getServerAddress()
							&& serverobj.getServerPort() == server
									.getServerPort()) {
						BeanUtils.copyProperties(serverobj, server);
						serverobj.setAdminStatus(server.getAdminStatus()
								.toString());
						serverobj.setType(server.getType().toString());
						serverobj.setOperationMode(server.getOperationMode()
								.toString());
						serverobj
								.setClientNAT(server.getClientNAT().toString());
						serverobj.setADServerBackupPreemption(server
								.getADServerBackupPreemption().toString());
						this.updateByObj(serverobj);
						break;
					}
				}
			}

		} catch (RemoteException e) {
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
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	private FarmsServerDao farmsServerDao;

	public void setFarmsServerDao(FarmsServerDao farmsServerDao) {
		this.farmsServerDao = farmsServerDao;
	}

}
