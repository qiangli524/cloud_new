package com.sitech.ssd.ah.boss.action.monitor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.ah.boss.domain.monitor.BossTreeObj;
import com.sitech.ssd.ah.boss.service.monitor.BossTreeService;

/**
 * <p>
 * Title: BossTreeAction
 * </p>
 * <p>
 * Description: Boss进程启停树
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
 * @createtime 2014-8-25 上午10:56:27
 * 
 */
@SuppressWarnings("unchecked")
@Controller("bossTreeAction")
@Scope("prototype")
public class BossTreeAction extends BaseAction {
	/** 打印日志 **/
	private static final Logger logger = LoggerFactory.getLogger(BossTreeAction.class);
	@Autowired
	BossTreeService bossTreeService;
	private List BossTreeObjList;
	private BossTreeObj obj;

	public List getBossTreeObjList() {
		return BossTreeObjList;
	}

	public void setBossTreeObjList(List bossTreeObjList) {
		BossTreeObjList = bossTreeObjList;
	}

	public BossTreeObj getObj() {
		return obj;
	}

	public void setObj(BossTreeObj obj) {
		this.obj = obj;
	}

	/**
	 * @Title: listZookeeperTree
	 * @Description: 跳转到启停树
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 上午10:58:07
	 */
	public String listBossTree() {
		return "list";
	}

	/**
	 * @Title: asyncForTree
	 * @Description: 展开树节点
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 上午10:58:52
	 */
	public String asyncForTree() {
		if (obj == null) {
			obj = new BossTreeObj();
			obj.setType("0");
		}
		BossTreeObjList = bossTreeService.queryBossTreeList(request);
		return "tree";
	}

	/**
	 * @Title: tabs
	 * @Description: 跳转到tab页
	 * @param
	 * @return String
	 * @throws
	 * @author qism
	 * @version 1.0
	 * @createtime 2014-8-25 下午7:12:12
	 */
	public String tabs() {
		return "tabs";
	}
}
