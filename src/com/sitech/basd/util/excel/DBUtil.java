package com.sitech.basd.util.excel;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.jdbc.core.SqlParameter;

import com.sitech.basd.sxcloud.rsmu.dao.BaseDao;

public class DBUtil extends BaseDao {

	public static final int SHEET_NUMBER = 60000;

	public static Log log = LogFactory.getLog(DBUtil.class);

	/**
	 * 
	 * @param rs
	 *            结果
	 * @return long 后得到的是 count(1)
	 */
	public static long getCount(String sql, List sqlPara) {
		long cnt = -1;
		Connection conn = null;
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = (Connection) getJDBCInstance();
			sql = "select count(1) from (" + sql + ") a ";
			ps = conn.prepareStatement(sql);

			for (int i = 0; sqlPara != null && i < sqlPara.size(); i++) {
				SqlParameter sp = (SqlParameter) sqlPara.get(i);
				ps.setString(i + 1, sp.getTypeName().toString());
			}

			rs = ps.executeQuery();
			cnt = RSUtil.getCount(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}

		return cnt;
	}

	/**
	 * 
	 * @param sql
	 * @param sqlPara
	 * @return
	 */
	public static List getStrsListLabel(String sql, List sqlPara) {
		List ls = new ArrayList();
		java.sql.PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = (Connection) getJDBCInstance();

			ps = conn.prepareStatement(sql);

			for (int i = 0; sqlPara != null && i < sqlPara.size(); i++) {
				SqlParameter sp = (SqlParameter) sqlPara.get(i);
				ps.setString(i + 1, sp.getTypeName().toString());
			}

			rs = ps.executeQuery();
			ls = RSUtil.getStrsListLabel(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("执行DBUtil方法异常");
		}
		return ls;
	}

	public static boolean writeExcel2FileBySXSSF(OutputStream outstream, Map map, String mapKey,
			int rowMaxNum, int type) {

		long rowCount = 0;
		List sqlPara = new ArrayList();

		if (rowMaxNum == 0 || rowMaxNum > DBUtil.SHEET_NUMBER
				|| (DBUtil.SHEET_NUMBER % rowMaxNum) != 0) {
			return false;
		}

		/* Create workbook */
		HSSFWorkbook workbook = new HSSFWorkbook();

		try {
			Row row;
			Cell cell;

			/* Create Title Font */
			Font fontTitle = workbook.createFont();
			fontTitle.setFontHeightInPoints((short) 10);
			fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);

			/* Create Data Font */
			Font fontData = workbook.createFont();
			fontData.setFontHeightInPoints((short) 9);
			fontData.setBoldweight(Font.BOLDWEIGHT_NORMAL);

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

			/* Create Data Style */
			CellStyle cellStyleData = workbook.createCellStyle();
			cellStyleData.setBorderLeft(CellStyle.BORDER_THIN);
			cellStyleData.setBorderRight(CellStyle.BORDER_THIN);
			cellStyleData.setBorderTop(CellStyle.BORDER_THIN);
			cellStyleData.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyleData.setAlignment(CellStyle.ALIGN_RIGHT);
			cellStyleData.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyleData.setFont(fontData);

			/* ��ѯ��Ҫ����ļ�¼���� */
			SQLListPostInfo info = new SQLListPostInfo(mapKey, map, sqlPara, type);
			String sql = info.getQuerySQL().toString();
			rowCount = getCount(sql, sqlPara);

			long n = rowCount / rowMaxNum;
			long startPos = 0;
			long endPos = 0;
			long pageNum = 0;
			long expCount = 0;
			int sheetNum = (int) (rowCount / DBUtil.SHEET_NUMBER);
			int m = 0;

			for (m = 0; m <= sheetNum; m++) {
				Sheet st = workbook.createSheet("Result" + m);

				for (; pageNum <= n; pageNum++) {
					/* ��SQLƴװΪ��ҳSQLִ�� */
					startPos = pageNum * rowMaxNum;
					endPos = (pageNum + 1) * rowMaxNum;

					if (endPos > rowCount) {
						endPos = rowCount;
					}

					// String sqlQuery = info.getOraQuerySQL(startPos,
					// endPos).toString();

					List ls = getStrsListLabel(sql, sqlPara);
					int datarows = ls.size();
					int datacols = ((String[]) ls.get(0)).length;


					String[][] data = new String[datarows][datacols];
					for (int j = 0; j < datarows; j++) {
						data[j] = (String[]) ls.get(j);
					}

					// data head
					if (pageNum == 0 || (expCount % DBUtil.SHEET_NUMBER) == 0) {
						row = st.createRow(0);
						row.setHeightInPoints(30);
						for (int j = 0; j < datacols; j++) {
							cell = row.createCell(j);
							// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(cellStyleTitle);
							cell.setCellValue(data[0][j]);
						}
					}

					// data left & data
					for (int k = 1; k < datarows; k++) {
						long rowIdx = pageNum * rowMaxNum + k - (m * DBUtil.SHEET_NUMBER);

						row = st.createRow((int) rowIdx);
						row.setHeightInPoints(15);
						for (int j = 0; j < datacols; j++) {
							cell = row.createCell(j);
							// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
							cell.setCellStyle(cellStyleData);
							cell.setCellValue(data[k][j]);
						}

						expCount = expCount + 1;
					}

					data = null;

					if ((expCount % DBUtil.SHEET_NUMBER) == 0) {
						pageNum++;
						break;
					}
				}
			}

			workbook.write(outstream);
			log.debug("end writeExcel2FileSimple");

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {

		}

		return true;
	}
}
