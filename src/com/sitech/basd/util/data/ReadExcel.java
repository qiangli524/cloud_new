package com.sitech.basd.util.data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {
	/**
	 * @title 读取指定的excel文件
	 * @param 文件路径
	 * @return 返回excel中sheet页的数组,List的数组
	 * @author duangh
	 * @throws Exception
	 */
	public static List<List<String>>[] readExcelFile(InputStream is) throws Exception {
		List<List<String>>[] listArray = null;
		try {
			// HSSFWorkbook hwb = new HSSFWorkbook(is);
			Workbook hwb = WorkbookFactory.create(is);
			int num = hwb.getNumberOfSheets();
			listArray = new ArrayList[num];
			for (int sheetnum = 0; sheetnum < num; sheetnum++) {
				List<List<String>> list = new ArrayList<List<String>>();
				Sheet sheet = hwb.getSheetAt(sheetnum);
				if (sheet == null) {
					continue;
				}
				// 循环行
				for (int rownum = 0; rownum <= sheet.getLastRowNum(); rownum++) {
					// 循环列
					List<String> cell = new ArrayList<String>();
					Row row = sheet.getRow(rownum);
					for (int colnum = 0; colnum < row.getLastCellNum(); colnum++) {
						cell.add(row.getCell(colnum).toString());
					}
					list.add(cell);
				}
				listArray[sheetnum] = list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return listArray;
	}

	public static void main(String[] args) {
		List<List<String>>[] lists;
		// try {
		// lists = readExcelFile("D:\\test.xls");
		//
		// for (int i = 0; i < lists.length; i++) {
		// List<List<String>> list = lists[i];
		// for (int j = 0; j < list.size(); j++) {
		// List<String> string = list.get(j);
		// for (String str : string) {
		// }
		// }
		// }
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
