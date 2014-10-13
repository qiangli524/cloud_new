package com.sitech.ssd.ah.nas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitech.ssd.ah.nas.dao.NasKpiDao;
import com.sitech.ssd.ah.nas.domain.NasKpiObj;


@Service("nasKpiService")
public class NasKpiServiceImpl implements NasKpiService {

	@Autowired
	private NasKpiDao nasKpiDao;
	

	/**
	 * @Title: getNasKpi
	 * @Description: 获取nasKpi
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	public NasKpiObj getNasKpi(NasKpiObj obj) {
		List<NasKpiObj> list = new ArrayList<NasKpiObj>();
		list = queryNasKpiList(obj);
		
		NasKpiObj kpis = new NasKpiObj();
		for(NasKpiObj kpi : list){
			/*待改进*/
			if(kpi.getKpiname().equals("storesize")){
				kpis.setStoresize(kpi.getKpivalue());
			}else if(kpi.getKpiname().equals("allfilesize")){
				kpis.setAllfilesize(kpi.getKpivalue());
			}else if(kpi.getKpiname().equals("usedfilesize")){
				kpis.setUsedfilesize(kpi.getKpivalue());
			}else if(kpi.getKpiname().equals("filenum")){
				kpis.setFilenum(kpi.getKpivalue());
			}else if(kpi.getKpiname().equals("filepoolnum")){
				kpis.setFilepoolnum(kpi.getKpivalue());
			}
		}
		return kpis;
	}
	
	
	/**
	 * @Title: getNasKpi
	 * @Description: 获取nasKpi
	 * @param
	 * @return 
	 * @throws
	 * @author siweichao
	 * @version 1.0
	 * @throws Exception 
	 * @createtime 2014年4月7日15:48:04
	 */
	private List<NasKpiObj> queryNasKpiList(NasKpiObj obj){
		return nasKpiDao.queryNasKpiByObj(obj);
	}
}
