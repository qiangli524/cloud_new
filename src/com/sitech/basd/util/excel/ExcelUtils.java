package com.sitech.basd.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtils {
	/** excel数据项的，sheet名称 */
	public static final String SHEET_NAME_KEY = "SHEET_NAME_KEY";
	/** excel数据项的，sheet标题，对应sheet的第二行 */
	public static final String SHEET_TITLE_KEY = "SHEET_TITLE_KEY";
	/** excel数据项的，sheet标题，对应sheet的第一行 */
	public static final String SHEET_FIRST_ROW_TITLE_KEY = "SHEET_FIRST_ROW_TITLE_KEY";
	/** excel数据项的，sheet标题，对应sheet的第二行后的数据行 */
	public static final String SHEET_DATA_KEY = "SHEET_DATA_KEY";
	/** 对第一列进行分组,目前只支持第一列进行分组，true，进行分组 */
	public static final String SHEET_FIRST_COLUMN_GROUP_KEY = "SHEET_FIRST_COLUMN_GROUP_KEY";
	
	/**
	 * @Title: writeDataMultipleSheet
	 * @Description: 多sheet导出，注意传递的数据格式
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-31 下午1:58:24
	 */
	@SuppressWarnings("unchecked")
	public static void writeDataMultipleSheet(Collection<Map<String,Object>> sheetData,OutputStream os){
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			Row row = null;
			String sheetName = null,sheetFirstRowTitle = null;
			String[] sheetTitle = null;
			List<Object[]> sheetRowData = null;
			CellStyle cellStyleTitle = getCellStyleTitle(workbook);
			CellStyle cellStyleData = getCellStyleData(workbook);
			CellStyle cellStyleFirstTitle = getCellStyleFirstTitle(workbook);
			Iterator<Map<String,Object>> iter = sheetData.iterator();
			Boolean firstColumnGroup  = false;
			Map<Object,CellRangeAddress> rangeAddrMap = new HashMap<Object,CellRangeAddress>(0);
			for(;iter.hasNext();){
				rangeAddrMap.clear();
				Map<String,Object> data = iter.next();
				sheetName = String.class.cast(data.get(SHEET_NAME_KEY));
				sheetTitle = (String[])data.get(SHEET_TITLE_KEY);
				sheetFirstRowTitle = String.class.cast(data.get(SHEET_FIRST_ROW_TITLE_KEY));
				sheetRowData = (List<Object[]>)data.get(SHEET_DATA_KEY);
				firstColumnGroup = data.containsKey(SHEET_FIRST_COLUMN_GROUP_KEY)?Boolean.class.cast(data.get(SHEET_FIRST_COLUMN_GROUP_KEY)):false;
				Sheet st = workbook.createSheet(sheetName);
				//表头
				row = st.createRow(0);
				row.setHeightInPoints(30);
				fillRow(row,new String[]{sheetFirstRowTitle},cellStyleFirstTitle);
				st.addMergedRegion(new CellRangeAddress(0, 0, 0, sheetTitle.length-1));
				//数据列标题
				row = st.createRow(1);
				row.setHeightInPoints(30);
				fillRow(row,sheetTitle,cellStyleTitle);
				//数据行初始化
				Object[] dateArray = null;
				int startRow = 2;
				for (int k = 0; k < sheetRowData.size(); k++) {
					dateArray = sheetRowData.get(k);
					row = st.createRow((int)startRow);
					row.setHeightInPoints(15);
					fillRow(row,dateArray,cellStyleData);
					if(firstColumnGroup){
						findMergeRow(st,dateArray[0],startRow,rangeAddrMap);
					}
					startRow ++;
				}
				//是否设置第一列分组，传递数据有序分组
				if(firstColumnGroup){
					processGroupMerge(st,rangeAddrMap);
				}
			}
			workbook.write(os);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 
	 * @Title: processGroupMerge
	 * @Description: 合并，待合并的区域
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-31 上午11:49:31
	 */
	private static void processGroupMerge(Sheet st,Map<Object,CellRangeAddress> rangeAddrMap){
		if(rangeAddrMap != null && !rangeAddrMap.values().isEmpty()){
			Iterator<CellRangeAddress> iter = rangeAddrMap.values().iterator();
			while(iter.hasNext()){
				st.addMergedRegion(iter.next());
			}
		}
	}
	/**
	 * 
	 * @Title: findMergeRow
	 * @Description: 查找分组合并
	 * @param
	 * @return void
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-31 上午11:49:55
	 */
	private static void findMergeRow(Sheet st,Object currVal,int currRow,Map<Object,CellRangeAddress> rangeAddrMap){
		if(currRow > 2){
			if(currVal instanceof String){
				String val = StringUtils.trim(String.class.cast(currVal));
				if(val.equals(StringUtils.trim(st.getRow(currRow-1).getCell(0).getStringCellValue()))){
					if(rangeAddrMap.containsKey(val)){
						rangeAddrMap.get(val).setLastRow(currRow);
					}else{
						rangeAddrMap.put(val, new CellRangeAddress(currRow-1, currRow, 0, 0));
					}
				}
			}
		}
	} 
	
	private static void fillRow(Row row,Object[] rowData,CellStyle cellStyle){
		if(row != null && rowData != null && rowData.length > 0){
			Cell cell = null;
			for (int j = 0; j < rowData.length; j++) {
				cell = row.createCell(j);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyle);
				if(rowData[j] instanceof Integer){
					cell.setCellValue((Integer)rowData[j]);
				}else if(rowData[j] instanceof Double){
					cell.setCellValue((Double)rowData[j]);
				}else{
					cell.setCellValue((String)rowData[j]);
				}
				
			}
		}
	}
	
	public static void writeDataExcel(String sheetName,String t1,String[] title,List<String[]> data,OutputStream os) throws IOException{
		/* Create workbook */
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			Row row;
			Cell cell;
			CellStyle cellStyleTitle = getCellStyleTitle(workbook);
			CellStyle cellStyleData = getCellStyleData(workbook);
			
			Sheet st = workbook.createSheet(sheetName);
			//////////////////////////////表头
			st.addMergedRegion(new CellRangeAddress(0, 0, 0, title.length));
			
			row = st.createRow(0);
			row.setHeightInPoints(30);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyleTitle);
			cell.setCellValue(t1);
			////////////////////////////////////
			row = st.createRow(1);
			row.setHeightInPoints(30);
			for (int j = 0; j < title.length; j++) {
				cell = row.createCell(j);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellStyle(cellStyleTitle);
				cell.setCellValue(title[j]);
			}
			String[] dateArray = null;
			int startRow = 2;
			for (int k = 0; k < data.size(); k++) {
				dateArray = data.get(k);
				row = st.createRow((int)startRow);
				row.setHeightInPoints(15);
				for (int j = 0; j < dateArray.length; j++) {
					cell = row.createCell(j);
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(cellStyleData);
					cell.setCellValue(dateArray[j]);
				}
				startRow ++;
			}
			workbook.write(os);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}
	
	
	/**
	 * @Title: getCellStyleTitle
	 * @Description: 获取标题样式
	 * @param
	 * @return CellStyle
	 * @throws
	 * @author liudan
	 * @version 1.0
	 * @createtime 2013-12-31 上午8:41:44
	 */
	private static CellStyle getCellStyleTitle(HSSFWorkbook workbook){
		/* Create Title Font */
		Font fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 10);
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		/* Create Title Style */
		CellStyle cellStyleTitle = workbook.createCellStyle();
		cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);
		return cellStyleTitle;
	}
	
	private static  CellStyle getCellStyleData(HSSFWorkbook workbook){
		/* Create Data Font */
		Font fontData = workbook.createFont();
		fontData.setFontHeightInPoints((short) 9);
		fontData.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		
		/* Create Data Style */
		CellStyle cellStyleData = workbook.createCellStyle();
		cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleData.setAlignment(CellStyle.ALIGN_RIGHT);
		cellStyleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyleData.setFont(fontData);
		return cellStyleData;
	}
	private static CellStyle getCellStyleFirstTitle(HSSFWorkbook workbook){
		/* Create Title Font */
		Font fontTitle = workbook.createFont();
		fontTitle.setFontHeightInPoints((short) 10);
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		/* Create Title Style */
		CellStyle cellStyleTitle = workbook.createCellStyle();
		cellStyleTitle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleTitle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyleTitle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyleTitle.setWrapText(true);
		cellStyleTitle.setFont(fontTitle);
		return cellStyleTitle;
	}
	
}
