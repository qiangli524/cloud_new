package action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import service.ConvertService;

import com.sitech.basd.sxcloud.cloud.domain.globalconfig.TbGlobalConfigObj;
import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.domain.vmhost.VMHostObj;
import com.sitech.basd.sxcloud.cloud.service.globalconfig.TbGlobalConfigService;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.cloud.service.vmhost.VMHostService;
import com.sitech.basd.sxcloud.cloud.web.resource.form.HostManageForm;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.web.showVm.form.ShowVmForm;

import domain.ConvertObj;

@Component("convertAction")
@Scope("prototype")
public class ConvertAction extends BaseAction{
	@Autowired
	private ConvertService convertService;
	
	private ConvertObj convertObj;

	private HostManageForm theForm;
	
	private String eq_id;
	
	private ShowVmForm showVmForm;
	@Autowired
	private DataStoreService dataStoreService;
	@Autowired
	private XenStoreService xenStoreService;
	@Autowired
	private TbGlobalConfigService tbGlobalConfigService;
	@Autowired
	private VMHostService vmHostService;
	@Autowired
	private HostInfoService hostInfoService;
	

	public ConvertObj getConvertObj() {
		return convertObj;
	}
	public void setConvertObj(ConvertObj convertObj) {
		this.convertObj = convertObj;
	}
	List<ConvertObj> resultList;

	public List<ConvertObj> getResultList() {
		return resultList;
	}
	public void setResultList(List<ConvertObj> resultList) {
		this.resultList = resultList;
	}
	public HostManageForm getTheForm() {
		return theForm;
	}
	public void setTheForm(HostManageForm theForm) {
		this.theForm = theForm;
	}
	public String getEq_id() {
		return eq_id;
	}
	public void setEq_id(String eq_id) {
		this.eq_id = eq_id;
	}
	public ShowVmForm getShowVmForm() {
		return showVmForm;
	}
	public void setShowVmForm(ShowVmForm showVmForm) {
		this.showVmForm = showVmForm;
	}
	
	public String goConvertPage(){
		String dialogName = request.getParameter("dialogName");
		request.setAttribute("dialogName", dialogName);
		return "convert";
	}
	
	public String goBasePage(){
		ConvertObj obj = new ConvertObj();
		resultList = convertService.queryForList(obj);
		return "base";
	}
	
	public String saveConvert(){
		if(convertObj==null){
			convertObj = new ConvertObj();
		}
		int result = convertService.insertByObj(convertObj); 
		return "save";
	}
	
	public String deleteConvert(){
		if(convertObj==null){
			convertObj = new ConvertObj();
		}
		int result = convertService.deleteByObj(convertObj); 
		return "delete";
	}
	
	public String queryConvertData(){
		if(convertObj==null){
			convertObj = new ConvertObj();
		}
		resultList = convertService.queryForList(convertObj);
		return "base";
	}
	
	public String goUpdateConvert(){
		if(convertObj==null){
			convertObj = new ConvertObj();
		}
		List<ConvertObj> lst = convertService.queryForList(convertObj);
		convertObj = lst.get(0);
		return "update";
	}
	
	public String updateConvert(){
		if(convertObj==null){
			convertObj = new ConvertObj();
		}
		convertService.updateByObj(convertObj);
		return "save";
	}
	
	/**
	 * 
	 * @Title: queryHostStore
	 * @Description: 查询主机存储总量和空闲量
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-19 下午4:54:05
	 */
	public String queryHostStore(){
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		DataStoreObj obj = new DataStoreObj();
		hostObj.setEq_id(eq_id);
		List lst = hostInfoService.queryForListByObj(hostObj);
		if (lst != null && lst.size() > 0) {
			hostObj = (TbCloud2HostInfoObj) lst.get(0);
			obj.setHOST_ID(hostObj.getH_uuid());
		}
		convertObj = convertService.queryHostStore(obj);
		
		return "store";
	}
	
	/**
	 * 
	 * @Title: queryHostIpAddr
	 * @Description: 查询主机
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-2-19 下午4:47:50
	 */
	public String queryHostIpAddr(){
			if (theForm == null) {
				theForm = new HostManageForm();
			}
			TbCloud2HostInfoObj obj = new TbCloud2HostInfoObj();
			String hostID = request.getParameter("hostID");
			if (theForm.getHostUuids() != null
					&& !theForm.getHostUuids().equals("")) {
				obj.setHostUuids(theForm.getHostUuids());
			}
			if (null != hostID && !"".equals(hostID)) {
				obj.setHOST_POOL_ID(hostID.trim());
				theForm.setHOST_POOL_ID(hostID.trim());
			}
			if (theForm.getEq_id() != null && !theForm.getEq_id().equals("")) {
				obj.setEq_id(theForm.getEq_id().trim());
			}
			if (theForm.getEq_name() != null && !theForm.getEq_name().equals("")) {
				obj.setEq_name(theForm.getEq_name().trim());
			}
			if (theForm.getEq_ip() != null && !theForm.getEq_ip().equals("")) {
				obj.setEq_ip(theForm.getEq_ip().trim());
			}
			if (theForm.getHasvertual() != null
					&& !theForm.getHasvertual().equals("-1")) {
				obj.setHasvertual(theForm.getHasvertual());
			}
			if (theForm.getEq_type() != null && !theForm.getEq_type().equals("-1")) {
				obj.setEq_type(theForm.getEq_type());
			}
			if (theForm.getAllocated() != null
					&& !theForm.getAllocated().equals("")) {
				obj.setAllocated(theForm.getAllocated());
			}
			if (theForm.getSTATUS() != null && !"".equals(theForm.getSTATUS())) {
				obj.setSTATUS(theForm.getSTATUS());
			}

			String store_uuid = request.getParameter("store_uuid");
			try {
				if (store_uuid != null && !store_uuid.equals("")) {
					store_uuid = URLDecoder.decode(store_uuid, "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String hostUuids = "";
			if (store_uuid != null && !store_uuid.equals("")) {
				DataStoreObj storeObj = new DataStoreObj();
				storeObj.setStore_uuid(store_uuid);
				List storeList = dataStoreService.queryForHostIdsList(storeObj);
				if (storeList != null && storeList.size() > 0) {
					for (Object o : storeList) {
						storeObj = (DataStoreObj) o;
						if (storeObj.getHOST_ID() != null
								&& !storeObj.getHOST_ID().equals("")) {
							hostUuids += "'" + storeObj.getHOST_ID() + "'" + ",";
						}
					}
					if (hostUuids.length() > 1) {
						hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
					}
				}
				XenStoreObj xenStoreObj = new XenStoreObj();
				xenStoreObj.setStore_uuid(store_uuid);
				List xenStoreList = xenStoreService.queryForStoreList(xenStoreObj);
				if (xenStoreList != null && xenStoreList.size() > 0) {
					for (Object o : xenStoreList) {
						xenStoreObj = (XenStoreObj) o;
						if (xenStoreObj.getDependent_host_uuid() != null
								&& !xenStoreObj.getDependent_host_uuid().equals("")) {
							hostUuids += "'" + xenStoreObj.getDependent_host_uuid()
									+ "'" + ",";
						}
					}
					if (hostUuids.length() > 1) {
						hostUuids = hostUuids.substring(0, hostUuids.length() - 1);
					}
				}
				obj.setHostUuids(hostUuids);
				theForm.setHostUuids(hostUuids);
			}
			// 查询当前用户
			String userId = session.get("id").toString();
			if (userId != null && !"".equals(userId)) {
				// 若为超级管理员admin,无需根据用户进行过滤
				obj.setUSER_ID(userId);

			}
			List resultList = new ArrayList();
			obj.setPagination(this.getPaginater().initPagination(
					Struts2Utils.getRequest()));// 分页
			String account = session.get("account").toString();
			// 全局配置里边配置了几个用户的权限(在安徽移动，有几个用户需要具备管理员权限，即查看虚拟机和主机的全部列表)
			TbGlobalConfigObj global = new TbGlobalConfigObj();
			global.setKEY("user_auth");
			global = tbGlobalConfigService.queryByObj(global);
			try {
				if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
					obj.setUSER_ID(null);
				} else {// 对于普通登录用户，需要进行权限控制
					if (global.getVALUE().contains(account)) {
						obj.setUSER_ID(null);
					}
				}
				resultList = hostInfoService.queryAllHost(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}

			theForm.setResultList(resultList);
			HttpServletRequest request = Struts2Utils.getRequest();
			String flag = request.getParameter("flag");
			theForm.setFlag(flag);

			return "hostIpAddr";
	}
	/**
	 * 
	 * @Title: queryVmIpAddr
	 * @Description: 查询虚拟机
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 1, 2013 4:34:31 PM
	 */
	public String queryVmIpAddr() {
		if (showVmForm == null) {
			showVmForm = new ShowVmForm();
		}
		VMHostObj obj = new VMHostObj();
		if (showVmForm.getEQ_ID() != null && !showVmForm.getEQ_ID().equals("")) {
			obj.setEQ_ID(showVmForm.getEQ_ID());
		}
		if (showVmForm.getQueryName() != null && !showVmForm.getQueryName().equals("")) {
			obj.setVH_NAME(showVmForm.getQueryName());
		}
		if (showVmForm.getQueryVHIP() != null && !showVmForm.getQueryVHIP().equals("")) {
			obj.setVH_IP(showVmForm.getQueryVHIP());
		}
		if (showVmForm.getQueryHostIp() != null && !showVmForm.getQueryHostIp().equals("")
				&& !showVmForm.getQueryHostIp().equals("0")) {
			String hostIp = showVmForm.getQueryHostIp();
			TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
			hostObj.setEq_ip(hostIp);
			hostObj = hostInfoService.queryForIdByIp(hostObj);
			if (hostObj != null && hostObj.getEq_id() != null) {
				obj.setEQ_ID(hostObj.getEq_id());
			} else {
				// 根据Ip查找主机不存在
				obj.setEQ_ID("-1");
			}
		}
		if (showVmForm.getQueryType() != null && !showVmForm.getQueryType().equals("")) {
			String type = showVmForm.getQueryType();
			obj.setVH_TYPE(type);
		} else {
			obj.setVH_TYPE("0");
		}
		if (showVmForm.getQueryState() != null && !showVmForm.getQueryState().equals("")) {
			obj.setVH_STAT(showVmForm.getQueryState());
		}
		HttpServletRequest request = Struts2Utils.getRequest();
		String eq_id = request.getParameter("eq_id");
		// 主机列表页面到虚拟机、还是直接虚拟机列表页面的标识
		String flag = request.getParameter("flag");
		if (eq_id != null && !eq_id.equals("")) {
			obj.setEQ_ID(eq_id);
			showVmForm.setEQ_ID(eq_id);
			showVmForm.setFlag(flag);
		}
		// 查询当前用户
		// TbSysUserinfoObj userObj = (TbSysUserinfoObj)
		// Struts2Utils.getRequest().getSession()
		// .getAttribute(Constant.USER_SESSION_KEY);
		List vmList = new ArrayList();
		String userId = session.get("id").toString();
		String account = session.get("account").toString();
		if (userId != null && !"".equals(userId)) {
			obj.setUSER_ID(userId);
		}
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		// 全局配置里边配置了几个用户的权限(在安徽移动，有几个用户需要具备管理员权限，即查看虚拟机和主机的全部列表)
		TbGlobalConfigObj global = new TbGlobalConfigObj();
		global.setKEY("user_auth");
		global = tbGlobalConfigService.queryByObj(global);
		try {
			if ("1".equals(userId)) {// 对于admin用户，不需要分配权限
				obj.setUSER_ID(null);
			} else {// 对于普通登录用户，需要进行权限控制

				if (global.getVALUE().contains(account)) {
					obj.setUSER_ID(null);

				}
			}
			vmList = vmHostService.queryForVmList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		showVmForm.setResultList(vmList);
		return "vmIpAddr";
	}
}
