package com.sitech.basd.sxcloud.rsmu.web.util.page;

import java.util.ArrayList;
import java.util.List;

import com.sitech.basd.sxcloud.rsmu.dao.system.TbSysFunctionsDao;
import com.sitech.basd.sxcloud.rsmu.domain.system.TbSysFunctionsObj;

public class NaviDisplay {
	private TbSysFunctionsDao tbSysFunctionsDao;

	public String getNaviURLA(String funcId, boolean haslastPartURL,
			String target) throws Exception {
		int level = funcId.indexOf("00");
		if (level % 2 != 0) {
			level++;
		}
		level = level / 2;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < level; i++) {
			if (i != 0)
				sb.append(",");
			sb.append(funcId.substring(0, i * 2 + 2));
			for (int j = 0; j < 5 - i - 1; j++)
				sb.append("00");
		}
		String midSb = sb.toString();
		List paramNav = null;
		paramNav = split(midSb, ",");
		StringBuffer sbURL = new StringBuffer();
		List funcList = tbSysFunctionsDao.getNaviParam(paramNav);
		if (haslastPartURL) {
			for (int i = 0; i < funcList.size(); i++) {
				TbSysFunctionsObj obj = (TbSysFunctionsObj) funcList.get(i);
				if (i > 0) {
					sbURL.append("->");
				}
				// sbURL.append("<a href=\"/rightCheck.jsp?modulId=");
				// sbURL.append("<a href=\""+obj.get("MODULE_PATH").toString());
				sbURL.append(obj.getFUNCREQUEST().toString());
				// sbURL.append("?module_id="+obj.get("MODULE_ID").toString());
				// sbURL.append("\"");
				if (target != null)
					// sbURL.append(" target=\""+target+"\"");
					// sbURL.append(">");
					sbURL.append(obj.getFUNNAME().toString());
				// sbURL.append("</a>");
			} // end for
		} else {
			for (int i = 0; i < funcList.size(); i++) {
				TbSysFunctionsObj obj = (TbSysFunctionsObj) funcList.get(i);

				if (i == funcList.size() - 1) {
					sbURL.append(obj.getFUNNAME().toString());
				} else {
					// sbURL.append("<a href=\"/rightCheck.jsp?modulId=");
					// sbURL.append("<a
					// href=\""+obj.get("MODULE_PATH").toString());
					// sbURL.append(obj.get("MODULE_PATH").toString());
					// sbURL.append("?module_id="+obj.get("MODULE_ID").toString());
					// sbURL.append("\"");
					if (target != null)
						// sbURL.append(" target=\""+target+"\"");
						// sbURL.append(">");
						sbURL.append(obj.getFUNNAME().toString());
					// sbURL.append("</a>");
					sbURL.append("->");
				}
			} // end for

		} // end if

		return sbURL.toString();
	}

	public static List split(String str, String sp) {
		int ip = 0;
		ArrayList al = new ArrayList();
		ip = str.indexOf(sp);
		if (ip != -1) {
			al.add(str.substring(0, ip));
			str = str.substring(ip + sp.length());
			if (str.length() != 0) {
				al.addAll(split(str, sp));
			}
		} else {
			al.add(str);
		}
		return al;
	}

	public static void main(String[] args) throws Exception {
		NaviDisplay naviDisplay1 = new NaviDisplay();
	}

	public TbSysFunctionsDao getTbSysFunctionsDao() {
		return tbSysFunctionsDao;
	}

	public void setTbSysFunctionsDao(TbSysFunctionsDao tbSysFunctionsDao) {
		this.tbSysFunctionsDao = tbSysFunctionsDao;
	}
}
