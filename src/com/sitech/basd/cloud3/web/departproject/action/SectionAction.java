/*
 * 部门
 * */
package com.sitech.basd.cloud3.web.departproject.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sitech.basd.cloud3.domain.departproject.DepartManObj;
import com.sitech.basd.cloud3.domain.departproject.DepartProjectObj;
import com.sitech.basd.cloud3.domain.departproject.RelationObj;
import com.sitech.basd.cloud3.service.departproject.DepartManService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.excel.ExcelUtils;

@Component("sectionAction")
@Scope("prototype")
public class SectionAction extends BaseAction {

	private DepartManObj obj;
	private List resultList;
	private String result;
	private String flag;
	private String sectionId; //部门id
	@Autowired
	private DepartManService departManService;
	private DepartManObj userAllObj;
	private DepartManObj userUsedObj;
	
	/**
	 * 
	 * @Title: listSection
	 * @Description: 获取部门列表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-14 
	 */
	public String listSection() {
		if (obj == null) {
			obj = new DepartManObj();
		}
		if (obj.getId() != null) {
			obj.setId(null);
		}
		//表示为部门
		obj.setType("1");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = departManService.queryForSectionListByObj(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: editDepart
	 * @Description: 进入编辑页面
	 * @param
	 * @return void
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-14 
	 */
	public String editSection() {
		if (obj == null) {
			obj = new DepartManObj();
		}
		if (obj.getId() != null && !"".equals(obj.getId())) {
			flag = "edit";
			obj = departManService.queryBySectionObj(obj);
		}else{
			flag = "add";
		}
		return "edit";
	}

	/**
	 * 
	 * @Title: saveDepart
	 * @Description: 保存部门信息
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-14 
	 */
	public String saveSection() {
		int ret = 0;
		if(obj.getMem()>0){//转化为Mb存到数据库中,保证数据单位一致性
			obj.setMem(obj.getMem()*1024);
		}
		if(obj.getStore()>0){//转化为Mb存到数据库中,保证数据单位一致性
			obj.setStore(obj.getStore()*1024);
		}
		if (obj.getId() != null && !"".equals(obj.getId()) && !"null".equals(obj.getId())) {
			ret = departManService.updateByObj(obj);
		} else {
			obj.setType("1");
			ret = departManService.insertByObj(obj);
		}
		result = ret + "";
		return "result";
	}

	/**
	 * 
	 * @Title: deleteSection
	 * @Description: 删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-11-14 
	 */
	public String deleteSection() {
		if (obj == null) {
			obj = new DepartManObj();
		}
		int ret = departManService.deleteByObj(obj);
		result = ret + "";
		return "result";
	}
	
	/**
	 * 
	 * @Title: projectResourceRate
	 * @Description: 部门下资源分配率
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @createtime 2013-9-9
	 */
	public String sectionResourceRate(){
		DepartManObj departObj = new DepartManObj();
		if(sectionId!=null&&!"".equals(sectionId)){
			departObj.setId(sectionId);
			userUsedObj = departManService.queryUsedResourceForSection(departObj);
			 userAllObj = departManService.queryAllResourceForSection(departObj);
		}
		if(userUsedObj == null){
			userUsedObj = new DepartManObj();
		}
		return "projectResourceRate";
	}
	
	/**
	 * 
	 * @Title: exportSectionExcel
	 * @Description: 部门下资源导出报表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws IOException 
	 * @createtime 2013-9-9
	 */
	public String exportSectionExcel() throws IOException{
		obj.setType("1");//部门
		obj.setName(java.net.URLDecoder.decode(obj.getName(), "UTF-8")) ;
		obj.setLeaderName(java.net.URLDecoder.decode(obj.getLeaderName(), "UTF-8")) ;
		List<DepartManObj> all = departManService.queryAllResourceListForSection(obj);
		List<DepartManObj> used = departManService.queryUsedResourceListForSection(obj);
		List<String[]> rowsList = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		for(int i = 0;i<all.size();i++){
			for(int j = 0;j<used.size();j++){
				System.out.println();
			}
		}
		for(int i = 0;i<all.size();i++){
			for(int j = 0;j<used.size();j++){
				if(used.get(j).getName() != null && used.get(j).getName().equals(all.get(i).getName())){
					
					String cpuUsedCount = used.get(j).getCPU_USED_COUNT()==null?"0":used.get(j).getCPU_USED_COUNT();
					String cpuAllCount = all.get(i).getCPU_ALL_COUNT()==null?"0":all.get(i).getCPU_ALL_COUNT();
					String memAllMb = all.get(i).getMEM_ALL_MB()==null?"0":all.get(i).getMEM_ALL_MB();
					String memUsedMb = used.get(j).getMEM_USED_MB()==null?"0":used.get(j).getMEM_USED_MB();
					String storageAllMb = all.get(i).getSTORAGE_ALL_MB()==null?"0":all.get(i).getSTORAGE_ALL_MB();
					String storageUsedMb = used.get(j).getSTORAGE_USED_MB()==null?"0":used.get(j).getSTORAGE_USED_MB();
					String ipUsedCount = used.get(j).getIP_USED_COUNT()==null?"0":used.get(j).getIP_USED_COUNT() ;
					String ipAllCount = all.get(i).getIP_ALL_COUNT()==null?"0":all.get(i).getIP_ALL_COUNT();
					rowsList.add(new String[]{all.get(i).getName(),all.get(j).getLeaderName(),cpuAllCount,cpuUsedCount,df.format((Integer.parseInt(cpuUsedCount)*100.00)/Integer.parseInt(cpuAllCount)).toString(),
							df.format(Integer.parseInt(memAllMb)/1024.00),df.format(Integer.parseInt(memUsedMb)/1024.00),df.format(Integer.parseInt(memUsedMb)*100.00/Integer.parseInt(memAllMb)).toString(),
							df.format(Integer.parseInt(storageAllMb)/1024.00/1024.00),df.format(Integer.parseInt(storageUsedMb)/1024.00/1024.00),df.format(Integer.parseInt(storageUsedMb)*100.00/Integer.parseInt(storageAllMb)).toString(),
							ipAllCount,ipUsedCount,df.format((Integer.parseInt(ipUsedCount)*100.00)/Integer.parseInt(ipAllCount)).toString()
					});
					break;
				}else{
					if(j == used.size()-1){
						String memAllMb = all.get(i).getMEM_ALL_MB()==null?"0":all.get(i).getMEM_ALL_MB();
						String storageAllMb = all.get(i).getSTORAGE_ALL_MB()==null?"0":all.get(i).getSTORAGE_ALL_MB();
						rowsList.add(new String[]{all.get(i).getName(),all.get(i).getLeaderName(),all.get(i).getCPU_ALL_COUNT(),"0","0.00",
								df.format(Integer.parseInt(memAllMb)/1024.00),"0.00","0.00",
								df.format(Integer.parseInt(storageAllMb)/1024.00/1024.00),"0.00","0.00",
								all.get(i).getIP_ALL_COUNT(),"0","0.00"
						});
					}else{
						continue;
					}
				}
				
			}
			
		}
		//获取采集指标数据
		String[] title = new String[]{"部门名称","部门负责人","CPU预算总量","CPU使用量","CPU分配率(%)","内存预算总量(G)","内存使用量(G)","内存分配率(%)","存储预算总量(T)","存储使用量(T)","存储分配率(%)","IP预算总量(个)","IP使用量(个)","IP分配率(%)"};
		OutputStream os = null;
		try{
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment;filename=SectionInfo.xls");//定义文件名
			os = response.getOutputStream();
			StringBuilder sb = new StringBuilder();
			sb.append("部门预算报表");
			ExcelUtils.writeDataExcel("sheet",sb.toString(),title,rowsList,os);
		} catch (IOException e) {
			
		}finally{
			if(os != null){
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		}
		return null;
	}
	
	public DepartManObj getObj() {
		return obj;
	}
	public void setObj(DepartManObj obj) {
		this.obj = obj;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public DepartManObj getUserAllObj() {
		return userAllObj;
	}

	public void setUserAllObj(DepartManObj userAllObj) {
		this.userAllObj = userAllObj;
	}

	public DepartManObj getUserUsedObj() {
		return userUsedObj;
	}

	public void setUserUsedObj(DepartManObj userUsedObj) {
		this.userUsedObj = userUsedObj;
	}
	
}
