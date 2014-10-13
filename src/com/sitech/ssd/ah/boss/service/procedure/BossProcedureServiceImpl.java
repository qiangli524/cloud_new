package com.sitech.ssd.ah.boss.service.procedure;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.boss.dao.procedure.BossProcedureDao;
import com.sitech.ssd.ah.boss.domain.procedure.ProcedureObj;

/**
 * <p>
 * Title: BossProcedureServiceImpl
 * </p>
 * <p>
 * Description: boss应用进程服务接口实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author qism
 * @version 1.0
 * @createtime 2014-8-1 下午4:19:37
 * 
 */
@Service("bossProcedureService")
public class BossProcedureServiceImpl implements BossProcedureService {
	@Autowired
	BossProcedureDao bossProcedureDao;

	@Override
	public List queryBossProcedure(ProcedureObj obj) {
		return bossProcedureDao.queryBossProcedure(obj);
	}

	@Override
	public String saveProcedure(ProcedureObj obj) {
		int a = 0;
		a = bossProcedureDao.insertByObj(obj);
		if (a == 0) {
			return "1";
		} else {
			return "-1";
		}
	}

	@Override
	public String checkIsExist(ProcedureObj obj) {
		int a = 0;
		a = bossProcedureDao.queryForIpAndAppCount(obj);
		if (a == 0) {
			return "";
		} else if (a > 0) {
			return "该名称已存在！";
		} else {
			return "验证出错！";
		}
	}

	@Override
	public ProcedureObj queryBossProcedureByUid(ProcedureObj obj) {
		return bossProcedureDao.queryProcedureByUid(obj);
	}

	@Override
	public String updateProcedureObj(ProcedureObj obj) {
		int a = 0;
		a = bossProcedureDao.updateProcedureObj(obj);
		if (a != 0) {
			return "-1";
		} else {
			return "";
		}
	}

	@Override
	public String unloadProcedureObj(ProcedureObj obj) {
		int a = 0;
		a = bossProcedureDao.unloadProcedureObj(obj);
		if (a != 0) {
			return "-1";
		} else {
			return "";
		}
	}

	@Override
	public List queryProcedureListByCluAndPool(ProcedureObj obj) {
		return bossProcedureDao.queryProcedureListByCluAndPool(obj);
	}
}
