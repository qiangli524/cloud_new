/**
 * Copyright: Copyright (c) 200903 
 * @author zengls
 * @version 1.0
 */
package com.sitech.basd.sxcloud.cloud.util;

import java.util.List;
import java.util.Map;

public class BuildXmlString {  
	
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
	public static StringBuffer BuildXmlData(Map paraMap,List xyAxisList,String showType){
		if(xyAxisList==null){
			return new StringBuffer() ;
		}
		//int dataLength = xyAxisList.size();
		//setColorCollection(dataLength);
		if(showType!=null&&(showType.equals("MSLine")||showType.equals("ZoomLine"))){	
			return composeXmlDataForMSLine( paraMap, xyAxisList,showType) ;
		}else if(showType!=null&&showType.equals("Line")){
			return composeXmlDataForLine( paraMap, xyAxisList);
		}//else if(showType!=null&&showType.equals("CombinationFCF_Line")){
//			return composeXmlFileDateForCombinationFCF_Line( paraMap, xyAxisList);
//		}else if(showType!=null&&showType.equals("FCF_Pie3D")){
//			return composeXmlFileDateForFCF_Pie3D( paraMap, xyAxisList);
//		}else if(showType!=null&&showType.equals("FCF_MSLine")){
//			return composeXmlFileDateForFCF_MSLine( paraMap, xyAxisList);
//		}
		return new StringBuffer();
	}
	/* 数据组装成双线XML格式数据
	 * @paraMap图形展示属性Map（F
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @return 文件名
	 */
	public static StringBuffer composeXmlDataForMSLine(Map paraMap,List xyAxisList,String showType){
		String caption = paraMap==null||paraMap.get("caption")==null?"":(String)paraMap.get("caption");
		String seriesName1 = paraMap==null||paraMap.get("seriesName1")==null?"":(String)paraMap.get("seriesName1");
		String seriesName2 = paraMap==null||paraMap.get("seriesName2")==null?"":(String)paraMap.get("seriesName2");
		String color1 = paraMap==null||paraMap.get("color1")==null?"":(String)paraMap.get("color1");
		String anchorBorderColor1 = paraMap==null||paraMap.get("anchorBorderColor1")==null?"":(String)paraMap.get("anchorBorderColor1");
		String anchorBgColor1 = paraMap==null||paraMap.get("anchorBgColor1")==null?"":(String)paraMap.get("anchorBgColor1");
		String color2 = paraMap==null||paraMap.get("color2")==null?"":(String)paraMap.get("color2");
		String anchorBorderColor2 = paraMap==null||paraMap.get("anchorBorderColor2")==null?"":(String)paraMap.get("anchorBorderColor2");
		String anchorBgColor2 = paraMap==null||paraMap.get("anchorBgColor2")==null?"":(String)paraMap.get("anchorBgColor2");
		String step = paraMap==null||paraMap.get("step")==null?"1":(String)paraMap.get("step");
		StringBuffer string_buffer = new StringBuffer();
		String  startString = null;
		if("ZoomLine".equals(showType)){
			startString = "<chart caption=\""+caption+"\" btnSwitchToZoomModeTooltext=\"放大模式\" btnSwitchToPinModeTooltext=\"引脚模式\" btnZoomOutTooltext=\"缩小\" btnResetChartTooltext=\"重置\"   btnSwitchtoZoomModeTitle=\"Z\" btnSwitchToPinModeTitle=\"P\" btnResetChartTitle=\"R\" btnZoomOutTitle=\"O\"  subcaption=\"\" lineThickness=\"1\" numberSuffix=\"%\" showNames=\"0\" showValues=\"0\" yAxisMaxValue=\"50\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\"  divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"" +step + "\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\">" ;
		}else if("MSLine".equals(showType)){
			startString = "<chart caption=\""+caption+"\"  subcaption=\"\" lineThickness=\"1\" numberSuffix=\"%\" showNames=\"0\" showValues=\"0\" yAxisMaxValue=\"50\" formatNumberScale=\"0\" anchorRadius=\"1\"   divLineAlpha=\"20\"  divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"" +step + "\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \"showBorder =\"0\">" ;
		}
	string_buffer.append(startString) ;
	StringBuffer centerString= new StringBuffer() ;
	centerString.append("<categories>");
	if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map xyMap = (Map)xyAxisList.get(i);
			String tempX = (String)xyMap.get("X");
			centerString.append("<category label=\"" + tempX + "\" />") ;
		}
	}else{
		centerString.append("<category label=\"" +  "\" />") ;
	}
	centerString.append("</categories>");
	centerString.append("<dataset seriesName=\""+seriesName1+"\" color=\""+color1+"\" anchorBorderColor=\""+anchorBorderColor1+"\" anchorBgColor=\""+anchorBgColor1+"\">");
	if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map xyMap = (Map)xyAxisList.get(i);
			String tempY = (String)xyMap.get("Y1");
			centerString.append("<set value=\""+tempY+"\"/>") ;
		}
	}
	centerString.append("</dataset>");
	
	centerString.append("<dataset seriesName=\""+seriesName2+"\" color=\""+color2+"\" anchorBorderColor=\""+anchorBorderColor2+"\" anchorBgColor=\""+anchorBgColor2+"\">");
	if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map xyMap = (Map)xyAxisList.get(i);
			String tempY = (String)xyMap.get("Y2");
			centerString.append("<set value=\""+tempY+"\"/>") ;
		}
	}
	centerString.append("</dataset>"); 
	string_buffer.append(centerString) ; 
	String endString = "</chart>" ;
	string_buffer.append(endString) ; 
	return string_buffer;
	}

	/* 数据组装成单线XML格式数据
	 * @paraMap图形展示属性Map（F
	 * titleName：图形标题   
	 * xAxisName：X轴
	 * yAxisName：Y轴
	 * decimalPrecision：图形数据保留到小数点后几位
	 * ）
	 * @xyAxisList：横纵坐标列表（横纵坐标以Map形式   X：横坐标值  Y：纵坐标值）
	 * @return 文件名
	 */
	public static StringBuffer composeXmlDataForLine(Map paraMap,List xyAxisList){
		String caption = paraMap==null||paraMap.get("caption")==null?"":(String)paraMap.get("caption");
		String step = paraMap==null||paraMap.get("step")==null?"1":(String)paraMap.get("step");
		String numberSuffix = paraMap==null||paraMap.get("numberSuffix")==null?"":(String)paraMap.get("numberSuffix");
		String paletteColors = paraMap==null||paraMap.get("paletteColors")==null?"":(String)paraMap.get("paletteColors");
		StringBuffer string_buffer = new StringBuffer();	
	String  startString = "<chart caption=\""+caption+"\" subcaption=\"\" lineThickness=\"1\" numberSuffix=\""+numberSuffix+"\" showValues=\"0\" yAxisMaxValue=\"50\" formatNumberScale=\"0\" anchorRadius=\"1.1\"   divLineAlpha=\"20\" divLineColor=\"CC3300\" divLineIsDashed=\"1\" showAlternateHGridColor=\"1\" alternateHGridColor=\"CC3300\" shadowAlpha=\"40\" labelStep=\"" +step + "\" numvdivlines=\"5\" chartRightMargin=\"35\" bgColor=\"FFFFFF,CC3300\" bgAngle=\"270\" bgAlpha=\"10,10\" alternateHGridAlpha=\"5\"  legendPosition =\"RIGHT \" showBorder =\"0\" anchorBorderColor=\"F1683C\" anchorBgColor=\"F1683C\" paletteColors=\""+paletteColors+"\">" ;
	
	string_buffer.append(startString) ;
	StringBuffer centerString= new StringBuffer() ;
	if(xyAxisList!=null&&xyAxisList.size()>0){
		for(int i=0;i<xyAxisList.size();i++){
			Map xyMap = (Map)xyAxisList.get(i);
			String tempX = (String)xyMap.get("X");
			String tempY = (String)xyMap.get("Y");
			centerString.append("<set label=\"" + tempX + "\" value=\"" + tempY + "\" color=\"F1683C\" />") ;
		}
	}else{
		centerString.append("<set label=\"" + "\" value=\"" + "\" color=\"F1683C\" />") ;
	}
	string_buffer.append(centerString) ;
	String endString = "</chart>" ;
	string_buffer.append(endString) ; 
	return string_buffer;
	}

	 
}
