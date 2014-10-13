package com.sitech.ssd.ah.paas.busiResource.service.tree;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sitech.basd.util.PropertyUtil;
import com.sitech.ssd.ah.paas.busiResource.dao.tree.PaasBusiTreeDao;
import com.sitech.ssd.ah.paas.busiResource.domain.tree.PaasBusiTreeObj;
import com.sitech.ssd.ah.paas.busiResource.util.paasBusiConstant;
import com.sitech.ssd.ah.paas.util.PaasConstant;
/**
 * <p>Title: PaasBusiTreeServiceImpl</p>
 * <p>Description: paas资源树节点数据服务实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author qism
 * @version 1.0
 * @createtime 2014-7-28 上午11:46:51
 *
 */
@Service("paasBusiTreeService")
public class PaasBusiTreeServiceImpl implements PaasBusiTreeService{
	@Autowired
	PaasBusiTreeDao paasBusiTreeDao;
	@Autowired
	private PropertyUtil unitedTreeIconProp;
	@Autowired
	private PropertyUtil bsTreeIconProp;
	@Override
	public List<PaasBusiTreeObj> queryForPaasBusiTree(HttpServletRequest request) {
		List<PaasBusiTreeObj> resultList = new ArrayList<PaasBusiTreeObj>();
		List<PaasBusiTreeObj> list = new ArrayList<PaasBusiTreeObj>();
		PaasBusiTreeObj obj = new PaasBusiTreeObj();
		String id = request.getParameter("uuid");//当前节点的id
		if("".equals(id) || id==null){//第一次进入页面
			obj.setNode_type(paasBusiConstant.ROOT);
		}else{
			obj.setParent_id(id);
		}
		resultList = paasBusiTreeDao.queryForPaasBusiTree(obj);
		//对节点进行属性处理，得到全新集合
		for(PaasBusiTreeObj memObj : resultList){
			PaasBusiTreeObj tObj = new PaasBusiTreeObj();
			tObj.setUuid(memObj.getUuid());
			tObj.setName(memObj.getName());
			//tObj.setParent_id(id);
			tObj.setEntity_id(memObj.getEntity_id());
			tObj.setNode_type(memObj.getNode_type());
			tObj.setServer_type(memObj.getServer_type());
			//判断是否为父节点
			if(paasBusiConstant.COST_HOST.equals(tObj.getNode_type())){
				tObj.setIsParent(false);
			}else{
				//判断是否有子节点
				List<PaasBusiTreeObj> childList = new ArrayList<PaasBusiTreeObj>();
				PaasBusiTreeObj checkObj = new PaasBusiTreeObj();
				checkObj.setParent_id(memObj.getUuid());
				childList =paasBusiTreeDao.queryForPaasBusiTree(checkObj);
				if (childList == null || childList.size() == 0) {
					tObj.setIsParent(false);
				}else{
					//对子节点为物理主机,虚拟机
					if(paasBusiConstant.COST_HOST.equals(childList.get(0).getNode_type())||paasBusiConstant.VM_HOST.equals(childList.get(0).getNode_type())){
						tObj.setIsParent(false);
					}
				}
			}
			//设置图标
			String type = memObj.getNode_type();
			if(paasBusiConstant.ROOT.equals(type)){
				tObj.setIcon(unitedTreeIconProp.getString("anhui"));// 根节点
				tObj.setNocheck(true);
			}else if(paasBusiConstant.CHILD_SYSTEM.equals(type)){
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.center.png"));// 子系统
				tObj.setNocheck(true);
			}else if(paasBusiConstant.BUSINESS.equals(type)){
				tObj.setIcon(bsTreeIconProp.getString("busi.sys.png"));// 业务
				tObj.setNocheck(true);
			}else if(paasBusiConstant.SERVICE.equals(type)){
				tObj.setIcon(unitedTreeIconProp.getString("servicenode"));// 服务
				tObj.setNocheck(true);
			}else if(paasBusiConstant.COST_HOST.equals(type)){
				tObj.setIcon(unitedTreeIconProp.getString("hostnode"));// 主机
				tObj.setNocheck(true);
			}else if(paasBusiConstant.DATABASE_INSTANCE.equals(type)){
				tObj.setIcon(bsTreeIconProp.getString("app.deploy.png"));// 实例
				tObj.setNocheck(true);
			}
			list.add(tObj);
		}
		return list;
	}

	@Override
	public String insertByObj(PaasBusiTreeObj obj) {
		int ret = 0;
		String result = paasBusiConstant.FAIL;
		ret = paasBusiTreeDao.insertByObj(obj);
		if (ret == 0) {
			result = paasBusiConstant.SUCCESS;
		}
		return result;
	}
	@Override
	public String toSaveHosts(String parent_id, String eq_ids,String h_names,String server_type) {
		int ret = 0;
		String[] eq_id_arr = eq_ids.split(",");
		String[] h_name_arr = h_names.split(",");
		String result = paasBusiConstant.FAIL;
		int i=0;
		for(String eq_id : eq_id_arr){
			PaasBusiTreeObj obj =new PaasBusiTreeObj();
			obj.setEntity_id(eq_id);
			obj.setName(h_name_arr[i]);
			obj.setNode_type(paasBusiConstant.COST_HOST);
			obj.setParent_id(parent_id);
			obj.setServer_type(server_type);
			ret = paasBusiTreeDao.insertByObj(obj);
			++i;
		}
		if (ret == 0) {
			result = paasBusiConstant.SUCCESS;
		}
		return result;
	}
	@Override
	public String updateByObj(PaasBusiTreeObj obj) {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public String deleteByObj(PaasBusiTreeObj obj) {
		int ret = 0;
		String result = paasBusiConstant.FAIL;
		ret = paasBusiTreeDao.deleteByObj(obj);
		if (ret == 0) {
			result = paasBusiConstant.SUCCESS;
		}
		return result;
	}

	@Override
	public String toSaveVmHosts(String parent_id, String vm_ids,
			String vm_names, String server_type) {
		int ret = 0;
		String[] vm_id_arr = vm_ids.split(",");
		String[] vm_name_arr = vm_names.split(",");
		String result = paasBusiConstant.FAIL;
		int i=0;
		for(String vm_id : vm_id_arr){
			PaasBusiTreeObj obj =new PaasBusiTreeObj();
			obj.setEntity_id(vm_id);
			obj.setName(vm_name_arr[i]);
			obj.setNode_type(paasBusiConstant.VM_HOST);//虚拟机
			obj.setParent_id(parent_id);
			obj.setServer_type(server_type);
			ret = paasBusiTreeDao.insertByObj(obj);
			++i;
		}
		if (ret == 0) {
			result = paasBusiConstant.SUCCESS;
		}
		return result;
	}


}
