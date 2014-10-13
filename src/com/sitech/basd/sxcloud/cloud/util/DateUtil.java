package com.sitech.basd.sxcloud.cloud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {
	/**
	 * 
	* @Title:获取下一个月字符串 yyyyMM
	* @Copyright: Copyright (c) 2012-3-14
	* @Company: si-tech
	* @author liys
	* @version 1.0
	 */
	public final static String getNextMonth() {
		java.util.Date today=new java.util.Date();
		SimpleDateFormat sdfyearmonth = new SimpleDateFormat("yyyyMM");
		Date nextMonthFirstDay=new Date(today.getYear(),today.getMonth()+1,1,1,0,0);
		String nextMonth=sdfyearmonth.format(nextMonthFirstDay);
		return nextMonth;
	}
	/**
	* @Title:对当前日期进行格式化，传入格式化参数如 yyyyMMdd
	* @Copyright: Copyright (c) 2012-3-14
	* @Company: si-tech
	* @author liys
	* @version 1.0
	 */
	public final static String getFormatTime(String formatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		return formatter.format(new java.util.Date());
	}
	
	/**
	 * @Title: getFormatTime
	 * @Description: 日期格式化，将date用formatType格式化
	 * @param
	 * @return String
	 * @throws
	 * @author jiangdi
	 * @version 1.0
	 * @createtime 2014-8-8 上午9:35:18
	 */
	public final static String getFormatTime(Date date, String formatType) {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		return formatter.format(date);
	}
	
	/**
	* @Title:从当前时间开始前推或后推N天，传入日期格式化参数 yyyyMMdd
	* n 为正整数 表示向前推n天，n为负整数则表示向后退n天
	* 举例:当前时间为20120314，n=1，则返回20120315，n=-1，返回20120313
	* @Copyright: Copyright (c) 2012-3-14
	* @Company: si-tech
	* @author liys
	* @version 1.0
	 */
   public final static  String getFormatNDate(String formatType,int n){
		  SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		  Calendar calendar=Calendar.getInstance(); 
		  calendar.add(calendar.DATE, n);
		  String date = formatter.format(calendar.getTime());
		  return date;
	  }
   
   public final static  String getFormatNDate(Date datetime,String formatType,int n){
		  SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		  Calendar calendar=Calendar.getInstance(); 
		  calendar.setTime(datetime);
		  calendar.add(calendar.DATE, n);
		  String date = formatter.format(calendar.getTime());
		  return date;
	  }
	/**
	* @Title:从当前时间开始前推或后推N天，传入日期格式化参数 yyyyMM
	* n 为正整数 表示向前推n天，n为负整数则表示向后退n天
	* 举例:当前时间为201203，n=1，则返回201204，n=-1，返回201202
	* @Copyright: Copyright (c) 2012-3-14
	* @Company: si-tech
	* @author liys
	* @version 1.0
	 */
   public final static  String getFormatNMonth(String formatType,int n){
		  SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		  Calendar calendar=Calendar.getInstance(); 
		  calendar.add(calendar.MONTH, n);
		  String date = formatter.format(calendar.getTime());
		  return date;
	  }
   
   public final static  String getFormatNMonth(Date datetime,String formatType,int n){
		  SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		  Calendar calendar=Calendar.getInstance(); 
		  calendar.setTime(datetime);
		  calendar.add(calendar.MONTH, n);
		  String date = formatter.format(calendar.getTime());
		  return date;
	  }
	/**
	* @Title:从当前时间开始前推或后推N年，传入日期格式化参数 yyyy
	* n 为正整数 表示向前推年，n为负整数则表示向后退n年
	* 举例:当前时间为2012，n=1，则返回2013，n=-1，返回2011
	* @Copyright: Copyright (c) 2012-3-14
	* @Company: si-tech
	* @author liys
	* @version 1.0
	 */
   public final static  String getFormatNYear(String formatType,int n){
		  SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		  Calendar calendar=Calendar.getInstance(); 
		  calendar.add(calendar.YEAR, n);
		  String date = formatter.format(calendar.getTime());
		  return date;
	  }
   
   /**  
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int daysBetween(Date smdate,Date bdate) throws Exception    
   {    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
       smdate=sdf.parse(sdf.format(smdate));  
       bdate=sdf.parse(sdf.format(bdate));  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(smdate);    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(bdate);    
       long time2 = cal.getTimeInMillis();         
       long between_days=(time2-time1)/(1000*3600*24);  
           
      return Integer.parseInt(String.valueOf(between_days));           
   }  
   
   /**  
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int monthsBetween(Date smdate,Date bdate) throws Exception    
   {    
	   Calendar c1 = Calendar.getInstance();    
       c1.setTime(smdate);
       Calendar c2 = Calendar.getInstance();    
       c2.setTime(bdate);          
      return  (c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR))*12+(c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH));          
   } 
   public static void main(String []args){
	   Calendar c= Calendar.getInstance();
	   c.add(Calendar.MONTH,-1); 
   }
}
