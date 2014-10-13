package com.sitech.basd.yicloud.util.zhejiang;

import java.util.*;
import java.text.*;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;


public class Formater
{
	public  final static String INVALIDDATE = "1950-01-01";
	private final static String SQLTYPE="ORACLE";
	
	//格式化给顶字符串长度，前面补零
	public final static String getFixLengthStr(String sequence,int length_s) throws Exception{
    try{
    	 String return_s=null;
    	 if(sequence.length()>length_s){
    	 	  return_s=sequence.substring(0,length_s);
    	 }else if(sequence.length()<length_s){
    	 	  String temp="";
    	 	  int temp_i=length_s-sequence.length();
    	 	  for(int i=0;i<temp_i;i++)
    	 	  {
    	 	  	temp=temp+"0";
    	 	  }
    	 	  return_s=temp+sequence;
    	 }else{
    	 	  return_s=sequence;
    	 }
    	 return return_s;
    }catch(Exception e){
      throw e;
    }
  }

 /**
 * 计算百分比 ，根据给定的精确数量返回结果
 * @param numerator --除数分子
 * @param denominator --除数分母
 * @param fix_num   --精确到小数点位数
 * @return    --返回float 型字符串
 */
  public final static String formatFixPercent(int numerator,int denominator,int fix_num){
  	float result_f=0.0f;
  	if(denominator>0){
  	  result_f=((float)numerator/denominator)*100;
    }
  	BigDecimal big_decimal = new BigDecimal(result_f);
    result_f = big_decimal.setScale(fix_num, BigDecimal.ROUND_HALF_UP).floatValue();
  	return String.valueOf(result_f);
  }
	
	public final static String getFormatTime(String formatType) {
		SimpleDateFormat formatter= new  SimpleDateFormat (formatType);
		return  formatter.format(new java.util.Date());
	}
	
	public final static String getFormatTime(String formatType,java.util.Date formatDate) {
		SimpleDateFormat formatter= new  SimpleDateFormat (formatType);
		return  formatter.format(formatDate);
	}
	
	//根据给的字符串和规定长度，小于该长度的加空格，大于该长度的截断
  public final static String formatFixStr(String in_s,int length_i){
  	String result_s="";
  	if(in_s!=null){
  		 String target_s="";
  		 try{
  		    target_s= new String(in_s.getBytes("GBK"),"ISO8859_1");    //如果为中文，则把长度变为2
  		    }catch   (UnsupportedEncodingException   ex)   {
  		    	ex.printStackTrace();
  		    }   
  	   if(target_s.length()<length_i){
  	   	int temp=length_i-target_s.length();
  	   	result_s=target_s;
  	   	for(int i=0;i<temp;i++)
  	   	{
  	   		result_s=result_s+" ";
  	   	}
  	   }else if(target_s.length()>length_i){
  	   	 result_s=target_s.substring(0,length_i);
  	   }else{
  	   	 result_s=target_s;
  	   }
  	 }else{
  	 	  for(int i=0;i<length_i;i++)
  	   	{
  	   		result_s=result_s+" ";
  	   	}
  	 }
  	return result_s;
  }
  //判断日期字符串(yyyymmdd)是否正确(不是很严禁！没有对日期的30、31、28、29进行判断)
	public final static boolean isRightDate(String date_s) {
		boolean isRight=true;
		if(date_s.length()!=8){
			isRight=false;
		}else{
			String year_s=date_s.substring(0,4);
			String month_s=date_s.substring(4,6);
			String date_ss=date_s.substring(6,8);
			try{
				 int year_i=Integer.parseInt(year_s);
				 int month_i=Integer.parseInt(month_s);
				 int date_i=Integer.parseInt(date_ss);
				 if(year_i<1000){
				 	 isRight=false;
				 }
				 if(month_i<1 || month_i>12){
				 	 isRight=false;
				 }
				 if(date_i<1 || date_i>31){
				 	 isRight=false;
				 }
			}catch(Exception e){
				  isRight=false;
          e.printStackTrace();
      }
		}
		return  isRight;
	}
	
	public final static String getNowTime() {
		SimpleDateFormat formatter1
			= new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		return  formatter1.format(new Date());
	}

	public final static String getToday(){
		SimpleDateFormat formatter1
		= new  SimpleDateFormat ("yyyyMMdd");
		return  formatter1.format(new Date());
	}
	
	public final static String getSybaseFormatDate (String cDate){
		return cDate;		
	}
	
	public final static String getSybaseFormatDate (Date dDate)throws ParseException{
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("yyyy-MM-dd");
                return  formatter1.format(dDate);
	}

	public final static String getSybaseFormatDateTime (Date dDate)throws ParseException{
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                return  formatter1.format(dDate);
	}

/*modified by wangxj on 2002/08/02
	如果时间为默认值INVALIDDATE,则在前台显示""
*/
	public final static String dateToString(Date dDate)throws ParseException{
		if( null==dDate ){
			return "";
		}
		
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("yyyy-MM-dd");
                if(formatter1.format(dDate).equals(INVALIDDATE)){
                	return "";
		}
                return  formatter1.format(dDate);
	}
	
/*modified by zhuzf on 2002/08/05
	如果时间为默认值INVALIDDATE,则在前台显示""
*/	public final static String datetimeToString(Date dDate)throws ParseException{
		if( null==dDate ){
			return "";
		}
		if(dateToString(dDate).equals("")){
			return "";
		}
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                return  formatter1.format(dDate);
	}
	
/*modified by wangxj on 2002/08/02*/
	public final static String dateOnWeb(Date dDate)throws ParseException{
				if( null==dDate ){
					return "";
				}
				String cDate = dateToString(dDate);
				if(cDate.equals("")){
					return "";
				}
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("yy-MM-dd HH:mm");
                return  formatter1.format(dDate);
	}
/*modified by xielj on 2002/08/11*/
	public final static String timeOnWeb(Date dDate)throws ParseException{
				if( null==dDate ){
					return "";
				}
				String cDate = datetimeToString(dDate);
				if(cDate.equals("")){
					return "";
				}
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("HH:mm");
                return  formatter1.format(dDate);
	}

	public final static String timeOnWeb(String cDate)throws ParseException{
				if(cDate.equals("")){
					return "";
				}
                SimpleDateFormat formatter1
                = new  SimpleDateFormat ("HH:mm");
                java.util.Date dDate = stringToDateTime(cDate);
                return  formatter1.format(dDate);
	}

	public final static Date stringToDate(String cDate,String cFormat) throws ParseException{
              	SimpleDateFormat formatter1 = new  SimpleDateFormat (cFormat);
               	return  formatter1.parse(cDate);
	}

	public final static Date stringToDate(String cDate) throws ParseException{
                try{
                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd");
                	return  formatter1.parse(cDate);
                }
                catch(ParseException e){
	                try{
	                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	                	return  formatter1.parse(cDate);
	                }
	                catch(ParseException ee){
	                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd HH:mm");
	                	return  formatter1.parse(cDate);
					}
                }
	}

	public final static Date stringToDateTime(String cDate) throws ParseException{
                try{
                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
                	return  formatter1.parse(cDate);
                }
                catch(ParseException e){
	                try{
	                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd HH:mm");
	                	return  formatter1.parse(cDate);
	                }
	                catch(ParseException ee){
	                	SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd");
	                	return  formatter1.parse(cDate);
					}
                }
	}

        public final static String toHTMLString(String chs){
        	if(chs.equals("")){
        		return chs;
        	}else
        	{
			    StringTokenizer st = new StringTokenizer(chs,"\n");
                String rt = new String("");
                String cHTMLEnter = "<br>";
                while (st.hasMoreTokens()) {
                        rt = rt + st.nextToken() + cHTMLEnter;
                }
				int i = 0;
				while(true){
					if(rt.charAt(i)==32){
						i++;
					}
					else
						break;
				}
				if(i>0){
					String cSpace = "";
					for(int j=0;j<i;j++){
						cSpace = cSpace + "&nbsp;";
					}
					rt = cSpace + rt.substring(i);
				}
                return rt;
              }
        }

        public final static String ltrimtoHTMLString(String chs){

                StringTokenizer st = new StringTokenizer(chs,"\n");
                String rt = new String("");
                String cHTMLEnter = "<br>";
                while (st.hasMoreTokens()) {
                        rt = rt + st.nextToken() + cHTMLEnter;
                }
                return rt;
        }

	@SuppressWarnings("deprecation")
	public final static String toHTMLURL(String chs){
		return java.net.URLEncoder.encode(chs);
	}
	
	public final static String toURLChar(String chs){
		String ts = "";
		if(chs==null)
			return ts;
		
		try{
			char[] chsb = new String(chs.getBytes("gb2312"),"ISO8859_1").toCharArray();

			for(int i=0;i<chsb.length;i++){
				if(chsb[i]>128){
					ts = ts + "%" + Integer.toHexString(chsb[i]);
				}
			}
		}
		catch(Exception e){
		}
		return ts;
	}
	
	

	public final static String fromSybaseString(String chs)throws java.io.UnsupportedEncodingException{
		if(null == chs)
			return "";
//		String temp = new String(chs.getBytes("ISO8859_1"),"GBK");
		return chs;
	}

        public final static String fromDBString(String chs)throws java.io.UnsupportedEncodingException{
                if(null == chs)
                        return "";
                String temp = chs;//new String(chs.getBytes("ISO8859_1"),"GBK");
                return temp;
        }


	public final static String toSybaseString(String chs)throws java.io.UnsupportedEncodingException{
     	if(null == chs)
       		return "";
//		String temp = new String(chs.getBytes("GBK"),"ISO8859_1");
        return chs;
	}

        public final static String toDBString(String chs)throws java.io.UnsupportedEncodingException{
        if(null == chs)
                return "";
	String temp = chs;//new String(chs.getBytes("GBK"),"ISO8859_1");
        return temp;
        }

	public final static String toOSString(String chs)throws java.io.UnsupportedEncodingException{
	        String temp = chs;//new String(chs.getBytes("GBK"),OSCHARSET);
	        return temp;
	}

	private final static String toOracleDate(Date dDate) throws ParseException{
		SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd");
		return  " to_date('" + formatter1.format(dDate) + "','yyyy-mm-dd') ";
	}

	public final static String toOracleDateTime(Date dDate) throws ParseException{
		SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		return  " to_date('" + formatter1.format(dDate) + "','yyyy-MM-dd HH24:mi:ss') ";
	}


	private final static String toSybaseDate(Date dDate) throws ParseException{
		SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd");
		return  " '" + formatter1.format(dDate) + "' ";
	}

	private final static String toSybaseDateTime(Date dDate) throws ParseException{
		SimpleDateFormat formatter1 = new  SimpleDateFormat ("yyyy-MM-dd");
		return  " '" + formatter1.format(dDate) + "' ";
	}

	public final static String toDBDate(Date dDate) throws ParseException{
		if(null==dDate){
			dDate = stringToDate(INVALIDDATE);
		}
		String cSQLDate = null;
		if(SQLTYPE.equals("ORACLE"))
			cSQLDate = toOracleDate(dDate);
		if(SQLTYPE.equals("SYBASE"))
			cSQLDate = toSybaseDate(dDate);
			return cSQLDate;
	}

	public final static String toDBDate(String cDate) throws ParseException{
		if(null==cDate){
			cDate = INVALIDDATE;
		}
		return toDBDate(stringToDate(cDate));
	}


	public final static String toDBDateTime(Date dDate) throws ParseException{
		if(null==dDate){
			dDate = stringToDate(INVALIDDATE);
		}
		String cSQLDate = null;
		if(SQLTYPE.equals("ORACLE"))
			cSQLDate = toOracleDateTime(dDate);
		if(SQLTYPE.equals("SYBASE"))
			cSQLDate = toSybaseDateTime(dDate);
			return cSQLDate;
	}

	public final static String toDBDateTime(String cDate) throws ParseException{
		if(null==cDate){
			cDate = INVALIDDATE;
		}
		return toDBDateTime(stringToDateTime(cDate));
	}

	//将字符串中所有的"字符前均加上\
	public static String toDBStringInv(String str){
		int iLength = 0;
		try {
			String cBeginStr = "";
			while(str.indexOf(34,iLength)!=-1) {
				cBeginStr = str.substring(0,str.indexOf(34,iLength));
				str = str.substring(0,str.indexOf(34,iLength))+"\\\""+str.substring(str.indexOf(34,iLength)+1);
				iLength = cBeginStr.length()+4;
			}
		}
		catch(Exception e) {
		}
		return str;
	}


	@SuppressWarnings("unchecked")
	public static ArrayList split(String str,String sp){
		int ip=0;
		ArrayList al = new ArrayList ();
		ip = str.indexOf(sp);
		if (ip!=-1){
			al.add(str.substring(0,ip));
			str=str.substring(ip+sp.length());
			if (str.length() !=0){
				al.addAll(split(str,sp));
			}
		}
		else {
			al.add(str);
		}
		return al;
	}

	public static String[] split2Array(String str,String sp){
		ArrayList al = split(str,sp);
		
		Object[] os = al.toArray();
		String[] ss = new String[os.length];
		for (int i=0;i<os.length ;i++){
			ss[i] = (String)os[i];
		}
		return ss;
	}
   	public final static String toStr(String strSy) throws java.io.UnsupportedEncodingException{
		if(null==strSy)
			return "";

		 String strDB = new String(strSy.getBytes("ISO8859_1"),"GB2312");
		 return strDB;

	}
    	public final static String toBackStr(String strSy) throws java.io.UnsupportedEncodingException{
		if(null==strSy)
			return "";

		 String strDB = new String(strSy.getBytes("GB2312"),"ISO8859_1");
		 return strDB;

	}

/**
 * (1) parameter description
 * @param date
 * @param type
 * @param timeInterval
 * @return
 * (2)function description
 * realize the time plus and minus
 * (3) loop condition
 *y: year
 * m: month
 * d: date
 * h:hour
 * f: minute(fenzhong)
 * s: second
 */
    public static Date computeDate(Date date, char type, int timeInterval){
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int Time_year = cal.get(Calendar.YEAR);
        int Time_month = cal.get(Calendar.MONTH);
        int Time_day = cal.get(Calendar.DAY_OF_MONTH);
        int   Time_hour = cal.get(Calendar.HOUR_OF_DAY);
        int  Time_minute =cal.get(Calendar.MINUTE);
        int Time_second = cal.get(Calendar.SECOND);

        switch(type){
            case'y':
                {
                    Time_year = Time_year + timeInterval;
                    cal.set(Calendar.YEAR,Time_year);
                }
                break;

            case'm':
                {
                    Time_month = Time_month + timeInterval;
                    cal.set(Calendar.MONTH,Time_month);
                }
                break;

            case'd':
                {
                    Time_day = Time_day + timeInterval;
                    cal.set(Calendar.DAY_OF_MONTH,Time_day);
                }
                break;

            case'h':
               {
                    Time_hour = Time_hour + timeInterval;
                    cal.set(Calendar.HOUR_OF_DAY,Time_hour);
                }
                break;

            case'f':
                {
                    Time_minute = Time_minute + timeInterval;
                    cal.set(Calendar.MINUTE,Time_minute);
                }
                break;

            case's':
                {
                    Time_second = Time_second + timeInterval;
                    cal.set(Calendar.SECOND,Time_second);
                }
                break;
            default:
                break;
        }
                date =    cal.getTime();
                return  date;
    }
    public static Date computeDate(String cDate, char type, int timeInterval){
		java.util.Date dDate = null;
		try{
			dDate = stringToDateTime(cDate);
		}
		catch(ParseException e){
			dDate = new Date();
		}
		return computeDate(dDate, type, timeInterval);
	}
    
	public final static String getHISTableName(Date dDate) throws ParseException {
		if (null == dDate) {
			dDate = new Date();
		}
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
		return formatter1.format(dDate);
	}
	public static double desr(double d1, double d2) {
		BigDecimal bd1 = new BigDecimal(Double.toString(d1));
		BigDecimal bd2 = new BigDecimal(Double.toString(d2));

		return bd1.subtract(bd2).doubleValue();
	}
	/**
	 * 对于数值型数据进行统一处理
	 * 
	 * 如果是整数，那就不用处理了 如果是浮点数，那么精确到小数点后两位。如果精确后的小数点后两位为 00,如 5.00，或5.0情况。那么直接返回整数
	 * 
	 */
	public static String formatDecimalKpivalue(String decimalValue) {

		if (decimalValue.indexOf('.') < 0) {
			// 没小数点，认为是整数，直接返回
			return decimalValue;
		} else {

			int scale = 2;
			String scale_value = Formater.getByScale(decimalValue, scale);

			// 去除可能出现的小数点后面的两个0
			if (scale_value.lastIndexOf("00") == (scale_value.length() - 2)) {
				scale_value = scale_value
						.substring(0, scale_value.length() - 3);
			}

			return scale_value;
		}
	}
	/**
	 * 将数值类型的value其小数点精确到第n位,四舍五入 例如： value=14.345, n=2 , 返回值为14.35
	 * 
	 * @param double_value
	 * @return 如果不是数值类型，那么返回原来的字符串。[尽量不要用此特性来做控制，因为是通过异常来控制的，开销大]
	 */
	static public String getByScale(String double_value, int scale) {

		java.math.BigDecimal x = null;
		try {
			x = new java.math.BigDecimal(double_value);
			x = x.setScale(scale, java.math.BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			e.printStackTrace();
			return double_value;
		}

		return x.toString();
	}
	public static String neatenunitid(String str) {
		if (str.indexOf("-") != -1) {
			ArrayList al = split(str, "-");

			Object[] os = al.toArray();
			String ss = "";
			for (int i = 0; i < os.length; i++) {
				ss = ss + (String) os[i] + "_";
			}
			return ss.substring(0, ss.length() - 1);
		} else {
			return str;
		}
	}
	public static void main(String[] args) throws Exception{
		//java.util.Date dDate = stringToDate(getNowTime());
		//String cDate = dateOnWeb(dDate);
	}

}
