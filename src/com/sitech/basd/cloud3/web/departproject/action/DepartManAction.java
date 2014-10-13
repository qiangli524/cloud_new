/*
 * 科室
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
import com.sitech.basd.cloud3.service.departproject.DepartManService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.basd.sxcloud.util.Struts2Utils;
import com.sitech.basd.util.excel.ExcelUtils;

@Component("departManAction")
@Scope("prototype")
public class DepartManAction extends BaseAction {
	private DepartManObj obj;
	private List resultList;
	private String result;
	private String flag;
	private String departId;
	private DepartManObj userAllObj;
	private DepartManObj userUsedObj;
	@Autowired
	private DepartManService departManService;


	/**
	 * 
	 * @Title: listDepart
	 * @Description: 获取科室列表
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午9:00:12
	 */
	public String listDepart() {
		if (obj == null) {
			obj = new DepartManObj();
		}
		if (obj.getId() != null) {
			obj.setId(null);
		}
		obj.setType("2");
		obj.setPagination(this.getPaginater().initPagination(Struts2Utils.getRequest()));// 分页
		resultList = departManService.queryForDepartListByObj(obj);
		return "list";
	}

	/**
	 * 
	 * @Title: editDepart
	 * @Description: 进入编辑页面
	 * @param
	 * @return void
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午9:03:02
	 */
	public String editDepart() {
		if (obj == null) {
			obj = new DepartManObj();
		}
		if (obj.getId() != null && !"".equals(obj.getId())) {
			flag = "edit";
			obj = departManService.queryDepartByObj(obj);
		}else{
			flag = "add";
		}
		return "edit";
	}

	/**
	 * 
	 * @Title: saveDepart
	 * @Description: 保存科室信息
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午9:05:36
	 */
	public String saveDepart() {
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
			obj.setType("2");
			ret = departManService.insertByObj(obj);
		}
		result = ret + "";
		return "result";
	}

	/**
	 * 
	 * @Title: deleleDepart
	 * @Description: 删除一条记录
	 * @param
	 * @return String
	 * @throws
	 * @author taoxue
	 * @version 1.0
	 * @createtime 2013-10-31 上午9:07:24
	 */
	public String deleteDepart() {
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
	public String departResourceRate(){
		DepartManObj departObj = new DepartManObj();
		if(departId!=null&&!"".equals(departId)){
			departObj.setId(departId);
			userUsedObj = departManService.queryUsedResourceForDepart(departObj);
			 userAllObj = departManService.queryAllResourceForDepart(departObj);
		}
		if(userUsedObj == null){
			userUsedObj = new DepartManObj();
		}
		return "projectResourceRate";
	}
	
	/**
	 * 
	 * @Title: exportDepartExcel
	 * @Description: 科室下资源导出报表
	 * @param
	 * @return String
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws IOException 
	 * @createtime 2013-9-9
	 */
	public String exportDepartExcel() throws IOException{
		obj.setType("2");//科室
		List<DepartManObj> all = departManService.queryAllResourceListForDepart(obj);
		List<DepartManObj> used = departManService.queryUsedResourceListForDepart(obj);
		List<String[]> rowsList = new ArrayList<String[]>();
		DecimalFormat df = new DecimalFormat("0.00");
		for(int i = 0;i<all.size();i++){
			for(int j = 0;j<used.size();j++){
				if(used.get(j).getName() != null && used.get(j).getName().equals(all.get(i).getName())){
					
					String cpuUsedCount = used.get(j).getCPU_USED_COUNT()==null?"0":used.get(j).getCPU_USED_COUNT();
					String cpuAllCount = all.get(i).getCPU_ALL_COUNT()==null?"0":all.get(i).getCPU_ALL_COUNT();
					String memAllMb = all.get(i).getMEM_ALL_MB()==null?"0":all.get(i).getMEM_ALL_MB();
					String memUsedMb = used.get(j).getMEM_USED_MB()==null?"0":used.get(j).getMEM_USED_MB();
					String storageAllMb = all.get(i).getSTORAGE_ALL_MB()==null?"0":all.get(i).getSTORAGE_ALL_MB();
					String storageUsedMb = used.get(j).getSTORAGE_USED_MB()==null?"0":used.get(j).getSTORAGE_USED_MB();
					
					rowsList.add(new String[]{used.get(j).getName(),cpuAllCount,cpuUsedCount,df.format(Integer.parseInt(cpuUsedCount)*100.00/Integer.parseInt(cpuAllCount)).toString(),
							df.format(Integer.parseInt(memAllMb)/1024.00),df.format(Integer.parseInt(memUsedMb)/1024.00),df.format(Integer.parseInt(memUsedMb)*100.00/Integer.parseInt(memAllMb)).toString(),
							df.format(Integer.parseInt(storageAllMb)/1024.00/1024.00),df.format(Integer.parseInt(storageUsedMb)/1024.00/1024.00),df.format(Integer.parseInt(storageUsedMb)*100.00/Integer.parseInt(storageAllMb)).toString()
					});
					break;
				}else{
					if(j == used.size()-1){
						String memAllMb = all.get(i).getMEM_ALL_MB()==null?"0":all.get(i).getMEM_ALL_MB();
						String storageAllMb = all.get(i).getSTORAGE_ALL_MB()==null?"0":all.get(i).getSTORAGE_ALL_MB();
						
						rowsList.add(new String[]{used.get(j).getName(),all.get(i).getCPU_ALL_COUNT(),"0","0.00",
								df.format(Integer.parseInt(memAllMb)/1024.00),"0.00","0.00",
								df.format(Integer.parseInt(storageAllMb)/1024.00/1024.00),"0.00","0.00"
						});
					}else{
						continue;
					}
				}
			}
		}
		//获取采集指标数据
		String[] title = new String[]{"科室名称","CPU预算总量","CPU使用量","CPU分配率(%)","内存预算总量(G)","内存使用量(G)","内存分配率(%)","存储预算总量(T)","存储使用量(T)","存储分配率(%)"};
		OutputStream os = null;
		try{
			response.setContentType("application/msexcel");
			response.setHeader("Content-disposition", "attachment;filename=projectInfo.xls");//定义文件名
			os = response.getOutputStream();
			StringBuilder sb = new StringBuilder();
			sb.append("科室预算报表");
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
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getDepartId() {
		return departId;
	}

	public void setDepartId(String departId) {
		this.departId = departId;
	}

}
