package com.sitech.ssd.sc.os.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.sc.os.dao.OsHostDao;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;
import com.sitech.ssd.sc.os.domain.OsGroupModel;
import com.sitech.ssd.sc.os.domain.OsGroupUserModel;
import com.sitech.ssd.sc.os.domain.OsPartModel;
import com.sitech.ssd.sc.os.domain.OsSoftModel;
import com.sitech.ssd.sc.os.domain.OsUserModel;
import com.sitech.ssd.sc.os.domain.OsVolGroupModel;
import com.sitech.utils.exception.RabbitMQException;
import com.sitech.utils.rabbitmq.RabbitMQUtil;

@Service("osHostService")
public class OsHostServiceImpl implements OsHostService {

	@Autowired
	private OsHostDao osHostDao;
	@Autowired
	private RabbitMQUtil rabbitMQUtil;
	@Autowired
	private OsUserService osUserService;
	@Autowired
	private OsFileSystemService osFileSystemService;
	@Autowired
	private OsGroupService osGroupService;
	@Autowired
	private OsGroupUserService osGroupUserService;
	@Autowired
	private OsSoftService osSoftService;
	@Autowired
	private OsPartService osPartService;
	@Autowired
	private OsVolGroupService osVolGroupService;

	@Override
	public List<HostModel> listOsHost(HostModel host) {
		return osHostDao.queryForList(host);
	}

	@Override
	public int deleteOsHost(HostModel host) {
		// OsUser OsPart 与 OsHost外键关系，需级联删除
		OsUserModel user = new OsUserModel();
		user.setOs_host_id(host.getId());
		osUserService.deleteOsUser(user);
		OsFileSystemModel model = new OsFileSystemModel();
		model.setOs_host_id(host.getId());
		osFileSystemService.deleteOsFileSystem(model);
		return osHostDao.deleteHost(host);
	}

	@Override
	public int addOsHost(HostModel host) {
		osHostDao.insertHost(host);
		return 0;
	}

	@Override
	public HostModel queryForObject(HostModel host) {
		return osHostDao.queryForObject(host);
	}

	@Override
	public int modifyOsHost(HostModel host) {
		return osHostDao.updateOsHost(host);
	}

	@Override
	public int updateInstallState(HostModel host) {
		return osHostDao.updateInstallState(host);
	}

	@SuppressWarnings("finally")
	@Override
	public String customedInstall(HostModel host) {
		String _ret = "";
		try {
			rabbitMQUtil.publishMessage("", "app.os.install.queue", host.getEq_id());
			_ret = "OK";
		} catch (RabbitMQException e) {
			
		} finally{
			if("OK".equals(_ret)){
				if(updateInstallState(host) <= 0){
					_ret = "更新安装状态失败！";
				}
			}else{
				_ret = "安装不成功：通过MQ发送消息失败";
			}
			return _ret;
		}
	}

	@Override
	public int clearOsHostConfig(HostModel host) {

		OsFileSystemModel model = new OsFileSystemModel();
		model.setOs_host_id(host.getId());
		osFileSystemService.deleteOsFileSystemList(model);

		OsGroupModel group = new OsGroupModel();
		group.setOs_host_id(host.getId());
		osGroupService.deleteOsGroupList(group);

		OsUserModel user = new OsUserModel();
		user.setOs_host_id(host.getId());
		osUserService.deleteOsUser(user);

		OsGroupUserModel groupUser = new OsGroupUserModel();
		groupUser.setOs_host_id(host.getId());
		osGroupUserService.deleteOsGroupUser(groupUser);

		OsSoftModel soft = new OsSoftModel();
		soft.setOs_host_id(host.getId());
		osSoftService.deleteAllOsSoft(soft);
		
		OsPartModel part = new OsPartModel();
		part.setOs_host_id(host.getId());
		osPartService.deleteAllOsPart(part);
		
		OsVolGroupModel vg = new OsVolGroupModel();
		vg.setOs_host_id(host.getId());
		osVolGroupService.deleteAllOsVolGroup(vg);
		
		host.setInstall_state("0");
		updateInstallState(host);
		return 1;
	}

	public String batchCustomedInstall(String ids) {
		String idArray[] = ids.split(",");
		String eq_ids = "";
		HostModel hostobj = null;
		for (int i = 0; i < idArray.length; i++) {
			HostModel _host = new HostModel();
			_host.setId(idArray[i]);
			hostobj = queryForObject(_host);
			if (i == 0) {
				eq_ids = eq_ids + hostobj.getEq_id();
			} else {
				eq_ids = eq_ids + "," + hostobj.getEq_id();
			}
			_host.setInstall_state("1");
			updateInstallState(_host);
		}
		try {
			rabbitMQUtil.publishMessage("", "app.os.install.queue", eq_ids);
		} catch (RabbitMQException e) {
			e.printStackTrace();
		}
		return "success";
	}

	public Map queryHostBuss(HostModel host) {
		return osHostDao.queryHostBuss(host);
	}
}
