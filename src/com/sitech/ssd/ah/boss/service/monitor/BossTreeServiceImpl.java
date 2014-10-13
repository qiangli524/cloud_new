package com.sitech.ssd.ah.boss.service.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.util.PropertyUtil;
import com.sitech.ssd.ah.boss.dao.monitor.BossProcessMonitorDao;
import com.sitech.ssd.ah.boss.domain.monitor.BossMonitorObj;
import com.sitech.ssd.ah.boss.domain.monitor.BossTreeObj;

/**
 * <p>
 * Title: BossTreeServiceImpl
 * </p>
 * <p>
 * Description: Boss树服务接口实现类
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
 * @createtime 2014-8-25 上午11:16:25
 * 
 */
@Service("bossTreeService")
public class BossTreeServiceImpl implements BossTreeService {
	/** 打印日志 **/
	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(BossTreeServiceImpl.class);

	@Autowired
	private PropertyUtil unitedTreeIconProp;
	@Autowired
	BossProcessMonitorDao bossProcessMonitorDao;

	@Override
	public List queryBossTreeList(HttpServletRequest request) {
		List<BossTreeObj> listResult = new ArrayList<BossTreeObj>();
		String type = request.getParameter("type");// 当前节点的type
		String name = request.getParameter("name");
		String parent_name = request.getParameter("parent_name");// 当前节点的type
		// 第一次进入页面
		if ("".equals(type) || type == null) {
			// 根节点
			BossTreeObj obj = new BossTreeObj();
			// 判断是否有集群子节点
			List<BossMonitorObj> list = bossProcessMonitorDao
					.queryMonitorObjListf(new BossMonitorObj());
			if (list != null && list.size() > 0) {
				obj.setIsParent(true);
			}
			obj.setName("安徽");
			obj.setType("0");
			obj.setIcon(unitedTreeIconProp.getString("anhui"));
			listResult.add(obj);
		} else if ("0".equals(type)) {
			// 查询出集群
			List<BossMonitorObj> list = bossProcessMonitorDao
					.queryMonitorObjListf(new BossMonitorObj());
			for (BossMonitorObj foo : list) {
				BossTreeObj boo = new BossTreeObj();
				// 查询该集群下是否有子节点
				List lt = bossProcessMonitorDao.queryMonitorObjByClu(foo);
				if (lt != null && lt.size() > 0) {
					boo.setIsParent(true);
				}
				boo.setName(foo.getCluster_id());
				boo.setType("1");
				boo.setIcon(unitedTreeIconProp.getString("cluster"));
				listResult.add(boo);
			}
		} else if ("1".equals(type)) {
			Map<String, String> mapPool = new HashMap<String, String>();
			// 查询出该集群下的池子
			BossMonitorObj boo = new BossMonitorObj();
			boo.setCluster_id(name);
			List<BossMonitorObj> list = bossProcessMonitorDao.queryMonitorObjByClu(boo);
			// 对池子去重
			for (BossMonitorObj moo : list) {
				String program_name = moo.getProgram_name();
				int a = program_name.indexOf("_");
				String pname = program_name.substring(0, a);
				mapPool.put(pname, "");
			}
			Set<String> keys = mapPool.keySet();
			// 判断该集群下的池子节点是否具备子节点
			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
				String pname2 = i.next();
				BossTreeObj tobj = new BossTreeObj();
				BossMonitorObj boo2 = new BossMonitorObj();
				boo2.setCluster_id(name);
				boo2.setProgram_name(pname2 + "_");
				List list2 = bossProcessMonitorDao.queryMonitorObjByPoolAndClu(boo2);
				if (list2 != null && list2.size() > 0) {
					tobj.setIsParent(true);
				}
				tobj.setName(pname2);
				tobj.setParent_name(name);
				tobj.setType("2");
				tobj.setIcon(unitedTreeIconProp.getString("database"));
				listResult.add(tobj);
			}
		} else {
			BossMonitorObj boo = new BossMonitorObj();
			boo.setCluster_id(parent_name);
			boo.setProgram_name(name);
			// 查询出该池子下的主机集合
			List<BossMonitorObj> list = bossProcessMonitorDao.queryMonitorObjByPoolAndClu(boo);
			for (BossMonitorObj noo : list) {
				BossTreeObj tobj = new BossTreeObj();
				tobj.setType("3");
				tobj.setIcon(unitedTreeIconProp.getString("host"));
				tobj.setName(noo.getHost_ip());
				tobj.setParent_name(name);
				tobj.setGrand_name(parent_name);// 获取集群名
				listResult.add(tobj);
			}
		}
		return listResult;
	}
}
