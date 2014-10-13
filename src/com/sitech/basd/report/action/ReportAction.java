package com.sitech.basd.report.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Controller;

import com.sitech.basd.report.service.ReportService;
import com.sitech.basd.sxcloud.config.Constant;
import com.sitech.basd.util.session.UserSession;

@Controller("reportAction")  
@RemotingDestination(channels = { "my-amf"})  
public class ReportAction{

	final Logger log = Logger.getLogger(ReportAction.class);

	@Autowired
	private ReportService resourceReportService;

	
	/**
	 * @Title: getResourceOccupancyRate
	 * @Description: 统计资源总体占用情况，查询出总量，占用量，占用率
	 * @param
	 * @return List（Map（String，String））</br>
	 * map中的key说明如下：</br>
	 * type：统计类型   如内存大小</br>
	 * value：当前占用量</br>
	 * percent：占用率百分比，保留2位小数</br>
	 * total：总量</br>					
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-6-13 下午4:53:12
	 */
	@RemotingInclude
	public List<Map<String, String>> getResourceOccupancyRate() throws SQLException {
		String userDomain = getLoginUserDomain();
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		try {
			list = resourceReportService.getResourceOccupancyRate(userDomain);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * @Title: getResourceTrend
	 * @Description: 统计资源占用趋势，默认查询一年，时间粒度为月
	 * @param type	占用类型</br>
	 * 1、cpu：cpu  gmz频率</br>
	 * 2、memory：内存大小/G</br>
	 * 3、store：存储大小/G</br>
	 * 4、ip：ip个数</br>
	 * @return	List（Map（String，String））</br>
	 * map中的key说明如下：</br>
	 * month：统计类型   如内存大小</br>
	 * value：当前占用率</br>
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-6-13 下午5:09:11
	 */
	@RemotingInclude
	public List<Map<String, String>> getResourceTrend(String type,String startDate,String endDate) throws SQLException{
		String userDomain = getLoginUserDomain();
		List<Map<String, String>> list = null;
		try {
			list = resourceReportService.getResourceTrend(userDomain,type,startDate,endDate);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @Title: getResourceAlarmCount
	 * @Description: 告警数量统计，统计未清除告警的数量
	 * @return	List（Map（String，Integer））</br>
	 * map中的key说明如下：</br>
	 * critical：严重告警数量</br>
	 * major	：重要告警数量</br>
	 * normal：一般告警数量</br>
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @throws SQLException 
	 * @createtime 2014-6-13 下午5:23:48
	 */
	public Map<String, String> getResourceAlarmCount() throws SQLException{
		Map<String, String> list = null;
		try {
			list = resourceReportService.getResourceAlarmCount();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * @Title: getResourceAlarmHistoryCount
	 * @Description: 告警数量统计，统计已清除告警的数量
	 * @return		List（Map（String，Long））</br>
	 *  type：告警级别。critical=严重告警，major=重要告警，normal=一般告警</br>
	 *  value：统计数量
	 *  percent：占总告警数量的百分比，保留2位小数</br>
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-6-14 上午10:21:52
	 */
	@RemotingInclude
	public List<Map<String, Long>>getResourceAlarmHistoryCount(){
		
		return null;
	}

	/**
	 * 得到当前登录用户Domain
	 * @return
	 */
	private String getLoginUserDomain(){
		HttpSession session = UserSession.getHttpSession(); 
//		return session.getAttribute(Constant.USER_DOMAIN)==null ? "" : session.getAttribute(Constant.USER_DOMAIN).toString();
		return "domain.type.IT";
	}

	/**
	 * 得到当前登录帐号
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getLoginUserAccount(){
		HttpSession session = UserSession.getHttpSession(); 
		return session.getAttribute("account")==null ? "" : session.getAttribute("account").toString();
	}
}
