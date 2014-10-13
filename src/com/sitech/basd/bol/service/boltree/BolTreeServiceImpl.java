package com.sitech.basd.bol.service.boltree;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.basd.bol.dao.boltree.BolTreeDao;
import com.sitech.basd.bol.dao.nodestatus.BolNodeStatusDao;
import com.sitech.basd.bol.domain.boltree.BolTreeObj;
import com.sitech.basd.bol.domain.nodestatus.BolNodeStatusObj;
import com.sitech.vo.util.BolConstant;

/**
 * <p>
 * Title: RolTreeService
 * </p>
 * <p>
 * Description: 资源视图树逻辑层实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SI-TECH
 * </p>
 * 
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-10-31 上午11:45:06
 * 
 */
@Service("bolTreeService")
public class BolTreeServiceImpl implements BolTreeService {

	@Autowired
	private BolTreeDao bolTreeDao;
	@Autowired
	private BolNodeStatusDao bolNodeStatusDao;


	/**
	 * @Title: queryForRolTreeList
	 * @Description: 查询树对象集合
	 * @param
	 * @return List<RolTreeObj>
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-11-2 上午11:19:35
	 */
	@Override
	public List<BolTreeObj> queryForBolTreeList(HttpServletRequest request) {
		List<BolTreeObj> list = new ArrayList<BolTreeObj>();
		BolTreeObj bolTreeObj = new BolTreeObj();
		BolTreeObj tempObj = new BolTreeObj();
		String id = request.getParameter("id");
//		String name = request.getParameter("name");
		if (id == null || "".equals(id)) {
			bolTreeObj.setType(BolConstant.ROOT);
		} else {
			bolTreeObj.setParent_id(id);
		}
//		if (name != null && !"".equals(name)) {
//			bolTreeObj.setName(name);
//		}
		List<BolTreeObj> resultList = bolTreeDao.queryForBolTreeList(bolTreeObj);
		for (BolTreeObj bol : resultList) {
			tempObj.setParent_id(bol.getId());
			List<BolTreeObj> slist = bolTreeDao.queryForBolTreeList(tempObj);
			bol.setParent_id(bol.getParent_id());
			if (slist.size() == 0) {
				bol.setIsParent(false);
			}
			if(bol.getType().equals("1")){
				BolNodeStatusObj statusObj = new BolNodeStatusObj();
				statusObj.setNodeCode(bol.getUuid());
				statusObj.setNodeField("STATUS");
				bol.setState(bolNodeStatusDao.queryNodeStatus(statusObj).getNodeValue());
			}
			list.add(bol);
		}
		return list;
	}

}
