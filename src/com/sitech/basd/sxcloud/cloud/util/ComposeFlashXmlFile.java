/**
 * Copyright: Copyright (c) 200903 
 * @author zengls
 * @version 1.0
 */
package com.sitech.basd.sxcloud.cloud.util;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

public class ComposeFlashXmlFile {
	 //基础颜色集
	 static String[] colorBaseCollection = {"AFD8F8","F6BD0F","8BBA00","FF8E46","008E8E","D64646","8E468E",
		 "588526","B3AA00","008ED6","9D080D","A186BE"};
	 //颜色集合
	 static String[] colorCollection ;
	 //设置图形文字编码
	 static String CharacterEncoding = "GB2312";
	 //颜色（文件名）产生随机字符
	 static String randCharacter="0123456789ABCDEF";
	 //XML文件生成路径
	 static String filePath = "" ;
	 //设置图形字体
	 static String baseFont = "楷体" ;
	 //设置图形字体大小
	 static String baseFontSize = "12" ;
	/* 数据组装成XML文件
	 * @paraMap图形展示属性Map（
	 * fileName：XML文件名 
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @showType FCF_Column3D 三维柱状图，  FCF_Line 折线图, FCF_Pie3D 三维饼图
	 * @return 文件名
	 */
	public static String composeXmlFileData(Map paraMap,List xyAxisList,String showType){
		if(xyAxisList==null){
			return "" ;
		}
		int dataLength = xyAxisList==null?0:xyAxisList.size();
		setColorCollection(dataLength);
		if(showType!=null&&showType.equals("FCF_Column3D")){
			return composeXmlFileDateForFCF_Column3D( paraMap, xyAxisList) ;
		}else if(showType!=null&&showType.equals("FCF_Line")){
			return composeXmlFileDateForFCF_Line( paraMap, xyAxisList);
		}else if(showType!=null&&showType.equals("CombinationFCF_Line")){
			return composeXmlFileDateForCombinationFCF_Line( paraMap, xyAxisList);
		}else if(showType!=null&&showType.equals("FCF_Pie3D")){
			return composeXmlFileDateForFCF_Pie3D( paraMap, xyAxisList);
		}else if(showType!=null&&showType.equals("FCF_MSLine")){
			return composeXmlFileDateForFCF_MSLine( paraMap, xyAxisList);
		}
		return "" ;
	}
	/* 数据组装成立体柱状图XML数据文件
	 * @paraMap图形展示属性Map（
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @return 文件名
	 */
	public static String composeXmlFileDateForFCF_Column3D(Map paraMap,List xyAxisList){
		String titleName = paraMap==null||paraMap.get("titleName")==null?"":(String)paraMap.get("titleName");
		String xAxisName = paraMap==null||paraMap.get("xAxisName")==null?"":(String)paraMap.get("xAxisName");
		String yAxisName = paraMap==null||paraMap.get("yAxisName")==null?"":(String)paraMap.get("yAxisName");
		String decimalPrecision = paraMap==null||paraMap.get("decimalPrecision")==null?"0":(String)paraMap.get("decimalPrecision");
		StringBuffer string_buffer = new StringBuffer();	
	String  startString = "<graph caption='"
		+titleName+"' xAxisName='"
		+xAxisName+"' yAxisName='"
		+yAxisName+"' decimalPrecision='"
		+decimalPrecision+"' formatNumberScale='0' baseFont='"+baseFont+"' baseFontSize='"+baseFontSize+"' >" ;
	String  endString = "</graph>" ;
	string_buffer.append(startString) ;
	String centerString = "";
	int counter = 0 ;
	if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map xyMap = (Map)xyAxisList.get(i);
			String tempX = ""+xyMap.get("X");
			String tempY = ""+xyMap.get("Y");
			if(tempY.equals("0")){
				counter ++ ;
			}
			centerString = centerString +"<set name='"+tempX+"' value='"+tempY+"' color='"+colorCollection[i]+"'/>" ;
		}
		if(counter==xyAxisList.size()){
			string_buffer.append("") ;
		}else{
		string_buffer.append(centerString) ;
		}
	}
	string_buffer.append(endString) ;
	return createXmlFile(string_buffer);
	}
	/* 数据组装成折线图XML数据文件
	 * @paraMap图形展示属性Map（
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @return 文件名
	 */
	public static String composeXmlFileDateForFCF_Line(Map paraMap,List xyAxisList){
		String titleName = paraMap==null||paraMap.get("titleName")==null?"":(String)paraMap.get("titleName");
		String xAxisName = paraMap==null||paraMap.get("xAxisName")==null?"":(String)paraMap.get("xAxisName");
		String yAxisName = paraMap==null||paraMap.get("yAxisName")==null?"":(String)paraMap.get("yAxisName");
		String decimalPrecision = paraMap==null||paraMap.get("decimalPrecision")==null?"0":(String)paraMap.get("decimalPrecision");
		StringBuffer string_buffer = new StringBuffer();
		String  startString = "<graph caption='"+titleName+"'" +
				" xAxisName='"+xAxisName+"' yAxisName='"
				+yAxisName+"' " +
				" decimalPrecision='"+decimalPrecision+"'" +
				" formatNumberScale='0' showNames='1' showValues='0'  showAlternateHGridColor='1' " +
				"  AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' baseFont='"+baseFont+"' baseFontSize='"+baseFontSize+"' >" ;
		string_buffer.append(startString) ;
		int counter = 0 ;
		String centerString = "" ;
		String centerStringNull = "";
		if(xyAxisList!=null&&xyAxisList.size()>0){
			for(int i=0;i<xyAxisList.size();i++){
				Map xyMap = (Map)xyAxisList.get(i);
				String tempX = (String)xyMap.get("X");
				String tempY = (String)xyMap.get("Y");
				//增加了两种判断，但判断的形式还是不够好
				if("0".equals(tempY) || "0.0".equals(tempY) || "0.00".equals(tempY)) {
					counter ++;
				}
//				char[] tempChar = tempY.toCharArray();
//				int counterTemp = 0;
//				for (int k = 0; k < tempChar.length; k ++) {
//					if ("0".equals(tempChar[k]) || ".".equals(tempChar[k])) {
//						counterTemp ++;
//					}
//				}
//				if (tempY.length() == counterTemp) {
//					counter ++ ;
//				}
				centerString = centerString + "<set name='"+tempX+"' value='"+tempY+"' hoverText='"+tempX+"'/>" ;
				//当结果全部为0时，将X轴附0.000001这个值。使之能在页面显示。
				centerStringNull +=  "<set name='" + tempX + "' value='" + "0.000001" + "' hoverText='" + tempX + "'/>" ;
			}
		}
		if(counter == xyAxisList.size()){
			string_buffer.append(centerStringNull) ;
		} else {
			string_buffer.append(centerString) ;
		}
		String  endString = "</graph>" ;
		string_buffer.append(endString) ;

		return createXmlFile(string_buffer);
	}
	
	/* 数据组装成两条折线图XML数据文件
	 * @auther zhangff 2011.6.8
	 * @paraMap图形展示属性Map（
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：纵坐标列表（横纵坐标以Map形式   Y1：纵坐标值1 Y2：纵坐标值2）
	 * @return 文件名
	 */
	public static String composeXmlFileDateForCombinationFCF_Line(Map paraMap,List xyAxisList){
		String titleName = paraMap==null||paraMap.get("titleName")==null?"":(String)paraMap.get("titleName");
		String xAxisName = paraMap==null||paraMap.get("xAxisName")==null?"":(String)paraMap.get("xAxisName");
		String yAxisName = paraMap==null||paraMap.get("yAxisName")==null?"":(String)paraMap.get("yAxisName");
		String y1Name = paraMap==null||paraMap.get("y1Name")==null?"":(String)paraMap.get("y1Name");
		String y2Name = paraMap==null||paraMap.get("y2Name")==null?"":(String)paraMap.get("y2Name");
		String decimalPrecision = paraMap==null||paraMap.get("decimalPrecision")==null?"0":(String)paraMap.get("decimalPrecision");
		StringBuffer string_buffer = new StringBuffer();
		String  startString = "<graph caption='"+titleName+"'" +
				" xAxisName='"+xAxisName+"' yAxisName='"+yAxisName+"' "+
				" decimalPrecision='"+decimalPrecision+"'" +
				" formatNumberScale='0'  " +
				"  AlternateHGridColor='ff5904' divLineColor='ff5904' divLineAlpha='20' alternateHGridAlpha='5' baseFont='"+baseFont+"' " +
						"baseFontSize='"+baseFontSize+"' >" ;
		string_buffer.append(startString) ;
		int counter = 0 ;
		int counter1 = 0 ;
		String centerString= "" ;
		centerString = centerString+ "<categories>";
		if(xyAxisList!=null&&xyAxisList.size()>0){
			for(int i=0;i<xyAxisList.size();i++){
				Map xyMap = (Map)xyAxisList.get(i);
				String tempX = (String)xyMap.get("X");
				if(tempX.equals("0")){
					counter1 ++ ;
				}
				centerString = centerString+ "<category name='"+tempX+"'/>" ;
			}
		}
		centerString = centerString+ "</categories>";
		centerString = centerString+ "<dataset seriesName='"+y1Name+"'  color='1D8BD1' anchorSides='10' " +
				"anchorRadius='3' anchorBorderColor='AFD8F8'>";
		if(xyAxisList!=null&&xyAxisList.size()>0){
			for(int i=0;i<xyAxisList.size();i++){
				Map xyMap = (Map)xyAxisList.get(i);
				String tempY = (String)xyMap.get("Y1");
				//zhangff 2011.10.8 修改 原因：原来计算两个Y值，用同一个counter
				if(tempY.equals("0")){
					counter1 ++ ;
				}
				centerString = centerString+ "<set value='"+tempY+"'/>" ;
			}
		}
		centerString = centerString+ "</dataset>";
		
		centerString = centerString+ "<dataset seriesName='"+y2Name+"'  color='2AD62A' anchorSides='10' " +
				"anchorRadius='3' anchorBorderColor='F6BD0F'>";
		if(xyAxisList!=null&&xyAxisList.size()>0){
			for(int i=0;i<xyAxisList.size();i++){
				Map xyMap = (Map)xyAxisList.get(i);
				String tempY = (String)xyMap.get("Y2");
				if(tempY.equals("0")){
					counter ++ ;
				}
				centerString = centerString+ "<set value='"+tempY+"'/>" ;
			}
		}
		centerString = centerString+ "</dataset>";
		if(counter==xyAxisList.size()||counter1==xyAxisList.size()){
			string_buffer.append("") ;
		}else{
		string_buffer.append(centerString) ;
		}
		String  endString = "</graph>" ;
		string_buffer.append(endString) ;
		return createXmlFile(string_buffer);
	}
	
	/* 数据组装成饼图XML数据文件
	 * @paraMap图形展示属性Map（
	 * titleName：图形标题   
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @return 文件名
	 */
	public static String composeXmlFileDateForFCF_Pie3D(Map paraMap,List xyAxisList){
		String titleName = paraMap==null||paraMap.get("titleName")==null?"":(String)paraMap.get("titleName");
		String decimalPrecision = paraMap==null||paraMap.get("decimalPrecision")==null?"0":(String)paraMap.get("decimalPrecision");
		StringBuffer string_buffer = new StringBuffer();
		String  startString = "<graph caption='"+titleName+"'" +
				" shadowYShift='5' showNames='1'pieRadius='150' pieSliceDepth='40' showNames='1' showValues='0' animation='1' showShadow='1'" +
				" canvasBorderColor='99CCFF'  pieBorderThickness='2' pieYscale='50' showhovercap='1' decimalPrecision='"+decimalPrecision+"'" +
				"  baseFont='"+baseFont+"' baseFontSize='"+baseFontSize+"' >" ;
		string_buffer.append(startString) ;
		String centerString = "";
		int counter = 0 ;
		if (xyAxisList != null && xyAxisList.size() > 0) {
			for(int i=0;i<xyAxisList.size();i++){
				Map xyMap = (Map)xyAxisList.get(i);
				String tempX = (String)xyMap.get("X");
				String tempY = (String)xyMap.get("Y");
				if(tempY.equals("0")){
					counter ++;
				}
				centerString = centerString + "<set name='"+tempX+"' value='"+tempY+"'/>" ;
			}
		}
		if(counter == xyAxisList.size()) {
			string_buffer.append("") ;
		} else {
			string_buffer.append(centerString);
		}
		String  endString = "</graph>" ;
		string_buffer.append(endString) ;
		return createXmlFile(string_buffer);
	}
	
	/* 数据组装成饼图XML数据文件
	 * @paraMap图形展示属性Map（
	 * titleName：图形标题   
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：
	 * @return 文件名
	 */
	public static String composeXmlFileDateForFCF_MSLine(Map paraMap,List xyAxisList){
		String titleName = paraMap==null||paraMap.get("titleName")==null?"":(String)paraMap.get("titleName");
		String xAxisName = paraMap==null||paraMap.get("xAxisName")==null?"":(String)paraMap.get("xAxisName");
		String yAxisName = paraMap==null||paraMap.get("yAxisName")==null?"":(String)paraMap.get("yAxisName");
		String decimalPrecision = paraMap==null||paraMap.get("decimalPrecision")==null?"0":(String)paraMap.get("decimalPrecision");
		StringBuffer string_buffer = new StringBuffer();
		String  startString = "<graph yAxisMinValue='0' yAxisMaxValue='5' caption='"+titleName+"'" +
				" xAxisName='"+xAxisName+"' yAxisName='"
				+yAxisName+"' " +
				" decimalPrecision='"+decimalPrecision+"'" +
				" formatNumberScale='0'" +
				"  showvalues='0' numdivlines='5' numVdivlines='0'" +
				" hovercapbg='FFECAA' hovercapborder='F47E00'" +
				"  baseFont='"+baseFont+"' baseFontSize='"+baseFontSize+"' >" ;
		string_buffer.append(startString) ;
		String centerString= "" ;
		String categoriesString = "<categories >" ;
		if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map map = (Map)xyAxisList.get(i) ;
			String xAxisKey = (String)map.get("KEY");
			List  vList = (List) map.get("VALUE") ;
			String str = "<dataset seriesName='"+xAxisKey+"' color='"+colorCollection[i]+"' anchorBorderColor='"
			+colorCollection[i]+"' anchorBgColor='"+colorCollection[i]+"'>" ;
			if(vList!=null&&vList.size()>0){
				for(int j=0;j<vList.size();j++){
					Map tempMap = (Map)vList.get(j) ;
					if(i==0){
						categoriesString = categoriesString + "<category name='" + tempMap.get("X") + "' />";
					}
					str = str + "<set value='" + tempMap.get("Y") + "' />";
				}
			}
			str = str +  "</dataset>" ;
			centerString = centerString + str ;
		}
		}

		categoriesString = categoriesString + "</categories>" ;
		string_buffer.append(categoriesString) ;
		string_buffer.append(centerString) ;
		String  endString = "</graph>" ;
		string_buffer.append(endString) ;
		return createXmlFile(string_buffer);
	}
	
	
	
	/*组装画图XY坐标数据列表
	 * @xDataList  横轴数据列表
	 * @yDataList  纵轴数据列表
	*/
	public static List composeListData(List xDataList, List yDataList) {
		if(xDataList==null||yDataList==null||xDataList.size()>yDataList.size())
			return null ;
		List xyAxisList = new ArrayList();
		for(int i=0;i<xDataList.size();i++){
			Map tempMap = new HashMap() ;
			tempMap.put("X", ""+xDataList.get(i));
			tempMap.put("Y", ""+yDataList.get(i));
			xyAxisList.add(tempMap);
		}
		return xyAxisList;
	}
	/*
	 * 生成文件
	 * @fileName ：文件路径+文件名称
	 * @string_buffer 文件内容
	 * @return 文件名
	*/
	private static String createXmlFile(StringBuffer string_buffer) {		
		 String fileName = createRandStr(8) ;
		 fileName = fileName + ".xml";
		 ResourceBundle LoginInfo =   ResourceBundle.getBundle("FusionChartsFree");
		 filePath  = LoginInfo.getString("webdomainPath")+"FusionChartsFree/Data/";
		byte[] bytes = null;
		try {
			bytes = string_buffer.toString().getBytes(CharacterEncoding);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			File myFilePath = new File(filePath+fileName);
			myFilePath.createNewFile();
			DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filePath+fileName, false)));
			dataOutputStream.write(bytes, 0, bytes.length);
			dataOutputStream.flush();
			dataOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName ;
	}
	/*产生随机字符串
	 * @length 产生字符串长度
	 * @return 随机字符串
	*/
	private static String createRandStr(int length){
		String  randArray = "" ;
		Random rand=new Random();
		for(int i=0;i<length;i++)
		{
		char tempStr=randCharacter.charAt(rand.nextInt(randCharacter.length()));
		randArray += tempStr ;
		}
		return randArray ;
	}
	/*设置颜色集合
	 * @length  设定颜色集的长度
	*/
	private static void setColorCollection(int length){
		if(length==0||length<=colorBaseCollection.length){
			colorCollection = colorBaseCollection ;
		}else{
			colorCollection = new String[length];
			for(int i=0;i<colorBaseCollection.length;i++){
				colorCollection[i]=colorBaseCollection[i];
			}
			for(int i=colorBaseCollection.length;i<length;i++){
				colorCollection[i]=createRandStr(6);
			}
		}
	}
}
