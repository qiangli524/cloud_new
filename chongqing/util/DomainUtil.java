package util;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.util.session.UserSession;


/**
 * <p>Title: DomainUtil</p>
 * <p>Description: 分权分域工具类
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: SI-TECH </p>
 * @author chenyu
 * @version 1.0
 * @createtime 2014-8-5 上午10:47:54
 *
 */
public class DomainUtil {

	/**
	 * @Title: setDomainToMap
	 * @Description: 将登录人的所属域放入map中
	 * @param map
	 * @return void
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-8-5 上午10:51:13
	 */
	public static void setDomainToMap(Map<String, Object> map) {
		if (null == map) {
			map = new HashMap<String, Object>();
		}
		Object domain = getSessionDomain();
		if (null != domain) {
			map.put("domain", domain);
		}
		
	}
	
	/**
	 * @Title: setDomainToMap
	 * @Description: 将登录人的所属域放入map中
	 * @param map
	 * @return void
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-8-5 上午10:51:13
	 */
	public static void setDomainToStringMap(Map<String, String> map) {
		if (null == map) {
			map = new HashMap<String, String>();
		}
		String domain = getSessionStringDomain();
		if (null != domain) {
			map.put("domain", domain);
		}
		
	}
	
	public static <T> Map<String, Object> setDomainToObjMap(Map<String, T> map){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (null != map && map.size() > 0) {
			for (Entry<String, T> entry : map.entrySet()) {
				paramMap.put(entry.getKey(), entry.getValue());
			}
		}
		setDomainToMap(paramMap);
		return paramMap;
	}
	
	/**
	 * 
	 * @Title: getSessionDomain
	 * @Description: 获取session中的域信息
	 * @return
	 * @return Object
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-8-5 下午3:36:28
	 */
	public static Object getSessionDomain(){
		Object domain = UserSession.getHttpSession()==null?null:UserSession.getHttpSession().getAttribute(Constant.USER_DOMAIN);
		return domain;
	}
	
	/**
	 * @Title: getSessionStringDomain
	 * @Description: 获取session中的域信息,并将其转换为String类型
	 * @return
	 * @return String
	 * @throws
	 * @author chenyu
	 * @version 1.0
	 * @createtime 2014-8-5 下午3:39:25
	 */
	public static String getSessionStringDomain(){
		Object domain = getSessionDomain();
		String domain_str = null;
		if(null!=domain){
			domain_str = domain.toString();
		} 
		return domain_str;
	}
	
}
