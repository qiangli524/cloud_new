package com.sitech.basd.util;

import java.util.List;
import java.util.Random;

import com.sitech.basd.resource.domain.resourcehisrecord.ResourceHisRecordObj;

/**
 * <p>Title: FusionChartUtil</p>
 * <p>Description: 饼图xml数据源生成工具</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-15 上午9:45:33
 *
 */
public class FusionChartUtil {
	/**
	 * @Title: buildPieXml
	 * @Description: 创建3D饼图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-10-21 上午9:32:14
	 */
	public static String buildPieXml(List<List<Object>> dataSetList,String caption,String subcaption,boolean isLink){
		StringBuffer xmlData = new StringBuffer();
		xmlData.append("<chart baseFont='宋体' baseFontSize='13' baseFontColor='#3300FF' unescapeLinks='0' caption='"+caption+"' " +
				"subcaption='" + subcaption + "' bgColor='FFFFFF' bgAlpha='9' formatNumberScale='0' " +
						"enableSmartLabels='1' enableRotation='0' bgRatio='0,100' bgAngle='360' showBorder='0'" +
						" startingAngle='300' legendBorderAlpha  ='0' legendShadow ='0' pieRadius='80' >");
		for (int i = 0; i < dataSetList.size(); i++) {
			List<Object> list = dataSetList.get(i);
			String link = "";
			if (isLink) {
				link = " link=\\\"JavaScript:myJS('" + list.get(0) + "','" + list.get(1) + "');\\\" ";
			}
			xmlData.append("<set label='" + list.get(0) + "' value='" + list.get(1) + "'  " + link + "/>");
		}
		xmlData.append("<styles><definition><style name='CaptionFont' type='font' size='12'/></definition>" +
				"<application><apply toObject='CAPTION' styles='CaptionFont' /></application></styles>");
		xmlData.append("</chart>");
		return xmlData.toString();
	}

	/**
	 * @Title: buildLine2DXml
	 * @Description: 创建2D线状图
	 * @param
	 * @return String
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @param timeLength 
	 * @createtime 2013-10-21 上午9:32:30
	 */
	public static String buildLine2DXml(
			List<List<ResourceHisRecordObj>> dataList,String caption,String subcaption,boolean isLink, Integer timeLength) {
		StringBuffer xmlData = new StringBuffer();
		xmlData.append("<graph baseFontSize='12' caption='"+caption+"' subcaption='"+subcaption+"' xAxisName='time' yAxisName='历史量'" +
				" showNames='1' showValues='0' rotateNames='1' animation='0' numVdivlines='0' numdivlines='3' " +
				" decimalPrecision='0' formatNumberScale='0' hovercapborder='F47E00' hovercapbg='FFECAA' ><categories>");
		List<ResourceHisRecordObj> list = dataList.get(0);
		int i =0;
		for (ResourceHisRecordObj resourceHisRecordObj : list) {
			i++;
			if (timeLength == 2) {//天
				if(i%24==0){
					xmlData.append("<category name='"+ resourceHisRecordObj.getCOLL_TIME() +"' />");
				} else {
					xmlData.append("<category name='' />");
				}
			} else if (timeLength == 3) {//周
				if(i%(24*7)==0){
					xmlData.append("<category name='"+ resourceHisRecordObj.getCOLL_TIME() +"' />");
				} else {
					xmlData.append("<category name='' />");
				}
			} else if (timeLength == 4) {//月
				if(i%(24*30)==0){
					xmlData.append("<category name='"+ resourceHisRecordObj.getCOLL_TIME() +"' />");
				} else {
					xmlData.append("<category name='' />");
				}
			}
		}
		xmlData.append("</categories>");
		
		String color = "0099FF"; 
		for (List<ResourceHisRecordObj> listr : dataList) {
			if (listr.size() > 0) {
				ResourceHisRecordObj recordObj = listr.get(0);
				color = randomColorHex();
				xmlData.append("<dataset seriesName='"+ recordObj.getKPI_ID() +"' color='"+color+"' >");
				i = 0;
				for (ResourceHisRecordObj resourceHisRecordObj : listr) {
					i ++ ;
					if (timeLength == 2) {//天
//						if(i%24==0){
							xmlData.append("<set value='"+ resourceHisRecordObj.getKPI_VALUE() +"'/>");
//						}/
					} else if (timeLength == 3) {//周
//						if(i%(24*7)==0){
							xmlData.append("<set value='"+ resourceHisRecordObj.getKPI_VALUE() +"'/>");
//						}
					} else if (timeLength == 4) {//月
//						if(i%(24*30)==0){
							xmlData.append("<set value='"+ resourceHisRecordObj.getKPI_VALUE() +"'/>");
//						}
					}
				}
				xmlData.append("</dataset>");
			}
		}
		xmlData.append("</graph>");
		return xmlData.toString();
	}
	
	private static String randomColorHex(){
		char[] arr = {'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		String color = "";
		int len = arr.length;
		int index = 0;
		for (int i = 0; i < 6; i++) {
			index = new Random().nextInt(len);
			color += arr[index];
		}
		return color;
	}
	
//	public static void main(String[] args) {
//		System.out.println(randomColorHex());
//	}
}
