package service.kpi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.HadoopConstant;

import com.sitech.basd.util.data.DataImportUtil;
import com.sitech.utils.randomid.RandomUUID;

import dao.kpi.HadoopKpiDao;
import domain.kpi.HadoopKpiObj;

@Service("hadoopKpiService")
public class HadoopKpiServiceImpl implements HadoopKpiService {
	@Autowired
	private HadoopKpiDao hadoopKpiDao;
	
	/**
	 * 
	 * @Title: importFromXls
	 * @Description: 导入数据
	 * @param
	 * @return String
	 * @throws
	 * @author yanggl
	 * @version 1.0
	 * @createtime 2014-4-25 上午9:15:46
	 */
	public String importFromXls(String file) {
		String result = "";
		try {
			List listArray = DataImportUtil.readExcelFile(file);
			if (listArray != null && listArray.size() > 0) {
				for (int i = 0; i < listArray.size(); i++) {// column
					String[] str = (String[]) listArray.get(i);
					String id = RandomUUID.getUuid();
					HadoopKpiObj obj = new HadoopKpiObj();
					obj.setId(id);
					obj.setKpi_id(str[0]);
					obj.setDescription(str[1]);
					obj.setUnit(str[2]);
					obj.setThreshold(str[3]);
					if (HadoopConstant.LINEGRAPH.equals(str[4])) {
						obj.setShape(HadoopConstant.linegraph);
					} else {
						obj.setShape(HadoopConstant.planegraph);
					}
					if (HadoopConstant.EFFECTIVE.equals(str[5])) {
						obj.setIsEffect(HadoopConstant.effective);
					} else {
						obj.setIsEffect(HadoopConstant.invalid);
					}
					if (HadoopConstant.HIGHT.equals(str[6])) {
						obj.setLevel(HadoopConstant.hight);
					} else if (HadoopConstant.MIDDLE.equals(str[6])) {
						obj.setLevel(HadoopConstant.middle);
					} else {
						obj.setLevel(HadoopConstant.low);
					}
					hadoopKpiDao.insertHadoopKpi(obj);
				}
			}
			result = "success";
		} catch (Exception e) {
			result = "error";
			e.printStackTrace();
		}
		return result;
	}
}
