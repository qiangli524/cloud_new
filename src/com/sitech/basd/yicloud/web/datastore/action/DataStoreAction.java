package com.sitech.basd.yicloud.web.datastore.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj;
import com.sitech.basd.sxcloud.cloud.service.resource.HostInfoService;
import com.sitech.basd.sxcloud.rsmu.web.CRUDBaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.yicloud.domain.datastore.DataStoreObj;
import com.sitech.basd.yicloud.domain.datastore.StoreDeviceObj;
import com.sitech.basd.yicloud.domain.xenstore.XenStoreObj;
import com.sitech.basd.yicloud.service.datastore.DataStoreService;
import com.sitech.basd.yicloud.service.datastore.StoreDeviceService;
import com.sitech.basd.yicloud.service.vmman.VMManagerService;
import com.sitech.basd.yicloud.service.xenstore.XenStoreService;
import com.sitech.basd.yicloud.web.datastore.form.DataStoreForm;
import com.sitech.basd.yicloud.web.vmsyndata.action.DataCompare;
import com.sitech.basd.yicloud.web.vmsyndata.form.DataConstant;
import com.sitech.utils.randomid.RandomUUID;

@SuppressWarnings("all")
public class DataStoreAction extends CRUDBaseAction {
	private DataStoreService dataStoreService;
	private DataStoreForm theForm;
	private VMManagerService vmManagerService;
	private HostInfoService hostInfoService;
	private XenStoreService xenStoreService;
	private StoreDeviceService storeDeviceService;
	private List resultList;
	private StoreDeviceObj storeDeviceObj;
	private String dialogId;
	private String storeDeviceId;
	private String operType;
	private String storeUuid;
	private List<TbCloud2HostInfoObj> hostList;

	/**
	 * 
	 * @Title: listDataStore
	 * @Description: 获取数据存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:38:09 AM
	 */
	public String listDataStore() {
		if (theForm == null) {
			theForm = new DataStoreForm();
		}
		DataStoreObj obj = new DataStoreObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		HttpServletRequest request = Struts2Utils.getRequest();
		String ID = Struts2Utils.getParameter("id");
		/*
		 * if (Integer.parseInt(ID) != 0) { EntityTreeObj en = new
		 * EntityTreeObj(); en.setId(Integer.parseInt(ID)); en =
		 * vmManagerService.queryTreeNode(en);
		 * obj.setID(Integer.parseInt(en.getEntityId())); }
		 */
		obj.setID(Integer.parseInt(ID));
		List<DataStoreObj> list = dataStoreService.queryForListByObj(obj);
		DataStoreObj d = list.get(0);

		double capacity = Double.parseDouble(d.getCAPACITY()) / (1024 * 1024 * 1024);
		double free = Double.parseDouble(d.getFREE_SPACE()) / (1024 * 1024 * 1024);
		DecimalFormat nf = new DecimalFormat("0.00");
		d.setCAPACITY(nf.format(capacity));
		d.setFREE_SPACE(nf.format(free));

		request.setAttribute("NAME", d.getNAME());
		request.setAttribute("CAPACITY", d.getCAPACITY());
		request.setAttribute("FREE_SPACE", d.getFREE_SPACE());
		request.setAttribute("TYPE", d.getTYPE());
		return LIST;
	}

	/**
	 * 
	 * <p>查询存储块被哪些主机使用</p>
	 *
	 * @Createtime Jul 3, 2014,10:38:48 AM
	 * @author xugang
	 * @version 1.0
	 * @return
	 */
	public String listStoreHost(){
		DataStoreObj obj = new DataStoreObj();
		obj.setStore_uuid(storeUuid);
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		hostList = dataStoreService.queryStoreHostList(obj);
		return "listStoreHost";
	}
	
	/**
	 * 
	 * @Title: listHostDs
	 * @Description: 获取主机下的存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:38:09 AM
	 */
	public String listHostDs() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 主机code唯一标识
		String hostName = request.getParameter("name");
		new DataCompare().dataCompare(DataConstant.TYPE_HOST_STORAGE, hostName, id);
		DataStoreObj obj = new DataStoreObj();
		obj.setHOST_ID(id);
		List<DataStoreObj> resultList = dataStoreService.queryForListByObj(obj);
		if (resultList != null && resultList.size() > 0) {
			for (DataStoreObj tempObj : resultList) {
				DecimalFormat nf = new DecimalFormat("0.00");
				double capacity = Double.parseDouble(tempObj.getCAPACITY());
				double free_space = Double.parseDouble(tempObj.getFREE_SPACE());
				tempObj.setCAPACITY(nf.format(capacity));
				tempObj.setFREE_SPACE(nf.format(free_space));
			}
		}
		if (theForm == null) {
			theForm = new DataStoreForm();
		}
		request.setAttribute("id", id);
		request.setAttribute("name", hostName);
		theForm.setResultList(resultList);
		return "listHostDs";
	}

	/**
	 * 
	 * @Title: addNFSPage
	 * @Description: 进入添加网络存储的界面
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:38:09 AM
	 */
	public String addNFSPage() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 主机 Id ,此时为code
		request.setAttribute("id", id);
		return "addNFSPage";
	}

	/**
	 * 
	 * @Title: saveNFS
	 * @Description: 保存添加的NFS存储
	 * @param
	 * @return String
	 * @throws
	 * @author duangh
	 * @version 1.0
	 * @createtime Jul 13, 2012 10:38:09 AM
	 */
	public String saveNFS() {
		HttpServletRequest request = Struts2Utils.getRequest();
		String id = request.getParameter("id");// 主机Id,code
		String name = request.getParameter("name");
		String remotehost = request.getParameter("remotehost");
		String remotepath = request.getParameter("remotepath");
		String readOnly = request.getParameter("readOnly");
		DataStoreObj obj = new DataStoreObj();
		obj.setHOST_ID(id);
		obj.setREMOTE_HOST(remotehost);
		obj.setREMOTE_PATH(remotepath);
		obj.setNAME(name);
		obj.setReadOnly(readOnly);
		String json = dataStoreService.saveNFS(obj);
		try {
			PrintWriter p = Struts2Utils.getResponse().getWriter();
			p.print(json);
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: liststorage
	 * @Description: 存储列表展示，查询存储
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 1:20:56 PM
	 */
	public String liststorage() {
		if (theForm == null) {
			theForm = new DataStoreForm();
		}
		DataStoreObj obj = new DataStoreObj();
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getQueryName() != null && !theForm.getQueryName().equals("")) {
			obj.setNAME(theForm.getQueryName());
		}
		if (theForm.getQueryState() != null && !theForm.getQueryState().equals("")) {
			obj.setSTATE(theForm.getQueryState());
		}
		if (theForm.getQueryType() != null && !theForm.getQueryType().equals("")) {
			obj.setTYPE(theForm.getQueryType());
		}
		if (theForm.getDeviceId() != null && !"".equals(theForm.getDeviceId())) {
			obj.setDeviceId(theForm.getDeviceId());
		}
		String sDeviceId = request.getParameter("sDeviceId");
		if (sDeviceId != null && !sDeviceId.equals("")) {
			theForm.setDeviceId(sDeviceId);
			obj.setDeviceId(sDeviceId);
		}
		String eq_id = request.getParameter("eq_id");
		if (eq_id != null && !eq_id.equals("")) {
			theForm.setHostId(eq_id);
			queryHostStore(eq_id, theForm, obj);
		} else {
			if (theForm.getHostId() != null && !theForm.getHostId().equals("")) {
				obj.setHOST_ID(theForm.getHostId());
				queryHostStore(theForm.getHostId(), theForm, obj);
			} else {
				queryNoRepeatStore(theForm, obj);
			}
		}
		setFormTotalStorage(theForm);
		return "liststorage";
	}

	/**
	 * 
	 * @Title: queryNoRepeatStore
	 * @Description: 查询不重复的存储
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 3:54:25 PM
	 */
	public void queryNoRepeatStore(DataStoreForm theForm, DataStoreObj obj) {
		List lst = dataStoreService.queryAllDataStore(obj);
		DecimalFormat format = new DecimalFormat("0");
		DecimalFormat format2 = new DecimalFormat("0.00");
		Double capacity = 0.0;
		Double free_space = 0.0;
		Double surplus = 0.0;
		if (lst != null && lst.size() > 0) {
			for (int i = 0; i < lst.size(); i++) {
				obj = (DataStoreObj) lst.get(i);
				capacity = Double.parseDouble(obj.getCAPACITY());
				free_space = Double.parseDouble(obj.getFREE_SPACE());
				surplus = capacity - free_space;
				obj.setCAPACITY(format.format(capacity / 1024));
				obj.setFREE_SPACE(format.format(free_space / 1024));
				obj.setCONHOSTNUM("1");// /暂时写死，后续需要修改
				if (surplus <= 0) {
					obj.setUse_per("0.0");
				} else {
					// String use_per = format2.format((capacity - free_space) /
					// capacity);
					String use_per = format
							.format(Double.parseDouble(obj.getUse_per() == null ? "0" : obj
									.getUse_per()) * 100);
					obj.setUse_per(use_per);
				}
			}
		}
		theForm.setResultList(lst);
	}

	/**
	 * 
	 * @Title: queryHostStore
	 * @Description: 查询主机下的存储
	 * @param
	 * @return void
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 3:54:55 PM
	 */
	public void queryHostStore(String eq_id, DataStoreForm theForm, DataStoreObj obj) {
		TbCloud2HostInfoObj hostObj = new TbCloud2HostInfoObj();
		hostObj.setEq_id(eq_id);
		List lst = hostInfoService.queryForListByObj(hostObj);
		if (lst != null && lst.size() > 0) {
			hostObj = (TbCloud2HostInfoObj) lst.get(0);
			obj.setHOST_ID(hostObj.getH_uuid());
			List storeLst = new ArrayList();
			DecimalFormat format = new DecimalFormat("0");
			DecimalFormat format2 = new DecimalFormat("0.00");
			Double capacity = 0.0;
			Double free_space = 0.0;
			Double surplus = 0.0;
			if (hostObj.getHasvertual() != null) {
				if (hostObj.getHasvertual().equals("3")) {
					// xen的存储
					XenStoreObj xenStoreObj = new XenStoreObj();
					xenStoreObj.setPagination(this.getPaginater().initPagination(
							Struts2Utils.getRequest()));// 分页
					xenStoreObj.setDependent_host_uuid(hostObj.getH_uuid());
					xenStoreObj.setName(obj.getNAME());
					xenStoreObj.setType(obj.getTYPE());
					xenStoreObj.setState(obj.getSTATE());
					List xenStoreLst = xenStoreService.queryForListByObj(xenStoreObj);
					if (xenStoreLst != null && xenStoreLst.size() > 0) {
						for (int i = 0; i < xenStoreLst.size(); i++) {
							xenStoreObj = (XenStoreObj) xenStoreLst.get(i);
							// 查询集群关联的主机数
							XenStoreObj queryStore = new XenStoreObj();
							queryStore.setStore_uuid(xenStoreObj.getStore_uuid());
							int num = xenStoreService
									.queryForConnectStoreHostCountByObj(queryStore);
							long srSize = Long.parseLong(xenStoreObj.getSr_size() == null ? "0"
									: xenStoreObj.getSr_size());
							long freeSize = Long.parseLong(xenStoreObj.getFree_size() == null ? "0"
									: xenStoreObj.getFree_size());
							srSize = srSize > 0 ? srSize / 1024 : 0L;
							freeSize = freeSize > 0 ? freeSize / 1024 : 0L;
							DataStoreObj addObj = new DataStoreObj();
							addObj.setCONHOSTNUM(num + "");
							addObj.setCAPACITY(format.format(srSize));
							addObj.setFREE_SPACE(format.format(freeSize));
							addObj.setNAME(xenStoreObj.getName());
							addObj.setSTORAGE_URL(xenStoreObj.getSr_url() == null ? ""
									: xenStoreObj.getSr_url());
							addObj.setTYPE(xenStoreObj.getType());
							addObj.setSTATE(xenStoreObj.getState());
							addObj.setDeviceName(xenStoreObj.getDeviceName() == null ? ""
									: xenStoreObj.getDeviceName());
							addObj.setDeviceType(xenStoreObj.getDeviceType() == null ? ""
									: xenStoreObj.getDeviceType());
							if (xenStoreObj.getUse_per() == null) {
								addObj.setUse_per("0.0");
							} else {
								addObj.setUse_per(xenStoreObj.getUse_per());
							}
							storeLst.add(addObj);
						}
					}
				} else if (hostObj.getHasvertual().equals("4")) {
					// vmware的存储
					storeLst = dataStoreService.queryForListByObj(obj);
					if (storeLst != null && storeLst.size() > 0) {
						for (int i = 0; i < storeLst.size(); i++) {
							obj = (DataStoreObj) storeLst.get(i);
							// 查询集群关联的主机数
							DataStoreObj queryStore = new DataStoreObj();
							queryStore.setSTORAGE_URL(obj.getSTORAGE_URL());
							int num = dataStoreService
									.queryForConnectStoreHostCountByObj(queryStore);
							obj.setCONHOSTNUM(num + "");
							capacity = Double.parseDouble(obj.getCAPACITY()) / 1024;
							free_space = Double.parseDouble(obj.getFREE_SPACE()) / 1024;
							surplus = capacity - free_space;
							obj.setCAPACITY(format.format(capacity));
							obj.setFREE_SPACE(format.format(free_space));
							if (surplus <= 0) {
								obj.setUse_per("0.0");
							} else {
								String use_per = format2.format((capacity - free_space) / capacity);
								use_per = format.format(Double.parseDouble(use_per) * 100);
								obj.setUse_per(use_per);
							}
						}
					}
				}
				theForm.setResultList(storeLst);
			} else {
				theForm.setResultList(new ArrayList());
			}
		} else {
			theForm.setResultList(new ArrayList());
		}
	}

	/**
	 * 
	 * @Title: setFormTotalStorage
	 * @Description: 查询存储总量，赋值给Form
	 * @param
	 * @return DataStoreForm
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime Mar 4, 2013 1:51:06 PM
	 */
	public DataStoreForm setFormTotalStorage(DataStoreForm theForm) {
		DataStoreObj obj = new DataStoreObj();
		// 统计指定设备的存储总量、分布情况
		obj.setDeviceId(theForm.getDeviceId());
		Double totalStore = 0.0;
		Double totalUseStore = 0.0;
		Double totalFreeStore = 0.0;
		Double totalUserper = 0.0;
		DecimalFormat format = new DecimalFormat("0");
		List lst = dataStoreService.queryAllDataStore(obj);
		if (lst != null && lst.size() > 0) {
			for (Object o : lst) {
				obj = (DataStoreObj) o;
				if (obj.getCAPACITY() != null && obj.getCAPACITY().length() > 0) {
					totalStore += Double.parseDouble(obj.getCAPACITY());
				}
				if (obj.getFREE_SPACE() != null && obj.getFREE_SPACE().length() > 0) {
					totalFreeStore += Double.parseDouble(obj.getFREE_SPACE());
				}
			}
			totalUseStore = totalStore - totalFreeStore;
			totalUserper = totalUseStore / totalStore;
			theForm.setTotalStore(format.format(totalStore / 1024));
			theForm.setTotalUserper(format.format(totalUserper * 100));
			theForm.setTotalUseStore(format.format(totalUseStore / 1024));
			theForm.setTotalFreeStore(format.format(totalFreeStore / 1024));
		} else {
			theForm.setTotalStore("0");
			theForm.setTotalUserper("0");
			theForm.setTotalUseStore("0");
			theForm.setTotalFreeStore("0");
		}
		return null;
	}

	/**
	 * 
	 * @Title: listStoreDevice
	 * @Description: 查询存储列表
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午6:31:30
	 */
	public String listStoreDevice() {
		if (storeDeviceObj == null) {
			storeDeviceObj = new StoreDeviceObj();
		}
		if (storeDeviceObj.getQueryName() != null && !"".equals(storeDeviceObj.getQueryName())) {
			storeDeviceObj.setName(storeDeviceObj.getQueryName());
		}
		if (storeDeviceObj.getQueryType() != null && !"".equals(storeDeviceObj.getQueryType())) {
			storeDeviceObj.setType(storeDeviceObj.getQueryType());
		}
		storeDeviceObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = storeDeviceService.queryForListByObj(storeDeviceObj);
		return "listStoreDevice";
	}

	/**
	 * 
	 * @Title: addStoreDevice
	 * @Description: 跳转到添加存储设备页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午7:16:23
	 */
	public String addStoreDevice() {
		return "addSDevice";
	}

	/**
	 * 
	 * @Title: delStoreDevice
	 * @Description: 删除存储设备
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-12 下午12:42:35
	 */
	public String delStoreDevice() {
		String id = request.getParameter("id");
		StoreDeviceObj obj = new StoreDeviceObj();
		obj.setId(id);
		storeDeviceService.deleteByObj(obj);
		return "delSDevice";
	}

	/**
	 * 
	 * @Title: saveStoreDevice
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-11 下午7:18:44
	 */
	public String saveStoreDevice() {
		if (storeDeviceObj == null) {
			storeDeviceObj = new StoreDeviceObj();
		}
		if (storeDeviceObj.getId() != null && !"".equals(storeDeviceObj.getId())) {
			storeDeviceService.updateByObj(storeDeviceObj);
		} else {
			String id = RandomUUID.getUuid();
			storeDeviceObj.setId(id);
			storeDeviceService.insertStoreDevice(storeDeviceObj);
		}
		return "saveSDevice";
	}

	/**
	 * 
	 * @Title: modStoreDevice
	 * @Description: 跳转到修改存储设备页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-12 下午12:42:57
	 */
	public String modStoreDevice() {
		String id = request.getParameter("id");
		StoreDeviceObj storeobj = new StoreDeviceObj();
		storeobj.setId(id);
		storeDeviceObj = (StoreDeviceObj) storeDeviceService.queryByObj(storeobj);
		storeDeviceObj
				.setCapacity(StringUtils.isNotEmpty(storeDeviceObj.getCapacity()) ? storeDeviceObj
						.getCapacity().replaceAll(",", "") : null);
		storeDeviceObj
				.setValid_space(StringUtils.isNotEmpty(storeDeviceObj.getValid_space()) ? storeDeviceObj
						.getValid_space().replaceAll(",", "") : null);
		return "modSDevice";
	}

	/**
	 * 
	 * @Title: relevanceStoreDevice
	 * @Description: 跳转到存储设备关联存储块页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 上午11:52:12
	 */
	public String relevanceStoreDevice() {
		if (theForm == null) {
			theForm = new DataStoreForm();
		}
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getQueryName() != null && !"".equals(theForm.getQueryName())) {
			dataStoreObj.setNAME(theForm.getQueryName());
		}
		if (theForm.getQueryState() != null && !"".equals(theForm.getQueryState())) {
			dataStoreObj.setSTATE(theForm.getQueryState());
		}
		if (theForm.getQueryType() != null && !"".equals(theForm.getQueryType())) {
			dataStoreObj.setTYPE(theForm.getQueryType());
		}
		resultList = dataStoreService.queryNoRelevanceStoreDevice(dataStoreObj);
		return "relevanceSDevice";
	}

	/**
	 * 
	 * @Title: cancleRelevance
	 * @Description: 跳转到取消关联的页面
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 下午1:08:21
	 */
	public String cancleRelevance() {
		if (theForm == null) {
			theForm = new DataStoreForm();
		}
		DataStoreObj dataStoreObj = new DataStoreObj();
		dataStoreObj.setDeviceId(storeDeviceId);
		dataStoreObj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		if (theForm.getQueryName() != null && !"".equals(theForm.getQueryName())) {
			dataStoreObj.setNAME(theForm.getQueryName());
		}
		if (theForm.getQueryState() != null && !"".equals(theForm.getQueryState())) {
			dataStoreObj.setSTATE(theForm.getQueryState());
		}
		if (theForm.getQueryType() != null && !"".equals(theForm.getQueryType())) {
			dataStoreObj.setTYPE(theForm.getQueryType());
		}
		resultList = dataStoreService.queryRelevanceStoreDevice(dataStoreObj);
		return "cancleRelevance";
	}

	/**
	 * 
	 * @Title: saveRelevance
	 * @Description: 关联或取消关联存储块
	 * @param
	 * @return String
	 * @throws
	 * @author hehui
	 * @version 1.0
	 * @createtime 2013-9-13 下午12:44:08
	 */
	public String saveRelevance() {
		String operType = request.getParameter("operType");
		String operIds = request.getParameter("operIds");
		String sDeviceId = request.getParameter("storeDeviceId");
		if (operType.equals("relevance")) {
			String[] operIdAndTypes = operIds.split("~");
			for (String idAndType : operIdAndTypes) {
				String[] idType = idAndType.split("@");
				if (idType[1].equals("xen")) {
					XenStoreObj xenStoreObj = new XenStoreObj();
					xenStoreObj.setStore_uuid(idType[0]);
					xenStoreObj.setDeviceId(sDeviceId);
					xenStoreService.relevanceDataStore(xenStoreObj);
				} else if (idType[1].equals("vmware")) {
					DataStoreObj storeObj = new DataStoreObj();
					storeObj.setStore_uuid(idType[0]);
					storeObj.setDeviceId(sDeviceId);
					dataStoreService.relevanceDataStore(storeObj);
				}
			}
		} else {
			String[] operIdAndTypes = operIds.split("~");
			for (String idAndType : operIdAndTypes) {
				String[] idTypes = idAndType.split("@");
				if (idTypes[1].equals("xen")) {
					XenStoreObj xenStoreObj = new XenStoreObj();
					xenStoreObj.setStore_uuid(idTypes[0]);
					xenStoreObj.setDeviceId("");
					xenStoreService.relevanceDataStore(xenStoreObj);
				} else if (idTypes[1].equals("vmware")) {
					DataStoreObj storeObj = new DataStoreObj();
					storeObj.setStore_uuid(idTypes[0]);
					storeObj.setDeviceId("");
					dataStoreService.relevanceDataStore(storeObj);
				}
			}
		}
		return "saveRelevance";
	}
	
	/**
	 * 
	 * @Title: addHotBackDiskPage
	 * @Description: 进入创建热备盘页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-27 下午2:14:18
	 */
	public String addHotBackDiskPage() {
		return "host_back";
	}

	/**
	 * 
	 * @Title: addDiskGroupPage
	 * @Description: 进入创建磁盘组页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-27 下午2:15:18
	 */
	public String addDiskGroupPage() {
		return "disk_group";
	}

	/**
	 * 
	 * @Title: addLUNPage
	 * @Description: 进入创建LUN页面
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2014-5-27 下午2:16:06
	 */
	public String addLUNPage() {
		return "add_lun";
	}

	public String getStoreDeviceId() {
		return storeDeviceId;
	}

	public void setStoreDeviceId(String storeDeviceId) {
		this.storeDeviceId = storeDeviceId;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getDialogId() {
		return dialogId;
	}

	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public StoreDeviceObj getStoreDeviceObj() {
		return storeDeviceObj;
	}

	public void setStoreDeviceObj(StoreDeviceObj storeDeviceObj) {
		this.storeDeviceObj = storeDeviceObj;
	}

	public void setStoreDeviceService(StoreDeviceService storeDeviceService) {
		this.storeDeviceService = storeDeviceService;
	}

	public void setXenStoreService(XenStoreService xenStoreService) {
		this.xenStoreService = xenStoreService;
	}

	public void setHostInfoService(HostInfoService hostInfoService) {
		this.hostInfoService = hostInfoService;
	}

	public void setVmManagerService(VMManagerService vmManagerService) {
		this.vmManagerService = vmManagerService;
	}

	public DataStoreForm getTheForm() {
		return theForm;
	}

	public void setTheForm(DataStoreForm theForm) {
		this.theForm = theForm;
	}

	public void setDataStoreService(DataStoreService dataStoreService) {
		this.dataStoreService = dataStoreService;
	}

	/**
	 *
	 * @return the storeUuid
	 */
	public String getStoreUuid() {
		return storeUuid;
	}

	/**
	 *
	 * @param storeUuid the storeUuid to set
	 */
	public void setStoreUuid(String storeUuid) {
		this.storeUuid = storeUuid;
	}

	/**
	 *
	 * @return the hostList
	 */
	public List<TbCloud2HostInfoObj> getHostList() {
		return hostList;
	}

	/**
	 *
	 * @param hostList the hostList to set
	 */
	public void setHostList(List<TbCloud2HostInfoObj> hostList) {
		this.hostList = hostList;
	}

}
