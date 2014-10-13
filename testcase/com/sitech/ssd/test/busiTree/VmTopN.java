package com.sitech.ssd.test.busiTree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.sitech.basd.busimanager.dao.busitree.BusiManagerTreeDao;
import com.sitech.basd.fusioncharts.vo.Data;
import com.sitech.ssd.test.base.spring.AppContext;

public class VmTopN {
	
	@Test
	public void getCpu(){
		BusiManagerTreeDao dao = AppContext.getBean("BusiManagerTreeDao", BusiManagerTreeDao.class);
		String parent_id = "3";
		int top_num =2;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parent_id", parent_id);
//		if(top_num)
		map.put("top_num", top_num);
		try {
			List<Data> list = dao.queryVmCpuTopN(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
