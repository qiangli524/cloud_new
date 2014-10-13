package com.sitech.basd.util.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;

public class WriterExcel {
	private HSSFWorkbook wb;

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:生成一个新的excel
	 * </p>
	 */
	public WriterExcel() {
		wb = new HSSFWorkbook();
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:读取一个已经存在的excel文件到HSSFWorkbook对象
	 * </p>
	 * 
	 * @param path,excel文件的绝对路径
	 * @throws Exception
	 */
	public WriterExcel(String path) throws Exception {
		try {
			wb = new HSSFWorkbook(new FileInputStream(new File(path)));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:将excel文件流写入到HSSFWorkbook对象
	 * </p>
	 * 
	 * @param excel文件流
	 * @throws Exception
	 */
	public WriterExcel(InputStream is) throws Exception {
		try {
			wb = new HSSFWorkbook(is);
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * @title 向excel中写入数据
	 * @param sheet页名称,如果多个sheet页，名称不能相同
	 * @param list
	 * @author duangh
	 * @throws Exception
	 */
	public <T, K, V> HSSFWorkbook writeExcel(String sheet_name, List<T> list) throws Exception {
		// HSSFWorkbook wb = new HSSFWorkbook();
		try {
			HSSFSheet sheet = wb.createSheet(sheet_name);
			if (!CollectionUtils.isEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					HSSFRow row = sheet.createRow(i);
					T obj = (T) list.get(i);
					if (obj instanceof Map) {// 传入的为Map
						Map<String, String> map = (Map<String, String>) obj;
						Set<String> set = map.keySet();
						String[] array = new String[set.size()];
						set.toArray(array);
						for (int j = 0; j < array.length; j++) {
							row.createCell(j).setCellValue(String.valueOf(map.get(array[j])));
						}
					} else {
						Field[] fields = obj.getClass().getDeclaredFields();
						for (int j = 0; j < fields.length; j++) {
							String fieldName = fields[j].getName();// 属性名
							String first = fieldName.charAt(0) + "";// 得到属性的首字母
							// 得到类的所有getter方法
							String getMethodName = "get" + first.toUpperCase() + fieldName.substring(1);
							Method method = obj.getClass().getMethod(getMethodName, new Class[] {});
							Object value = method.invoke(obj, new Object[] {});
							String getterValue = String.valueOf(value);
							row.createCell(j).setCellValue(getterValue);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return wb;
	}
}
