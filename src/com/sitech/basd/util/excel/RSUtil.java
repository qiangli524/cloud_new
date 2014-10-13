package com.sitech.basd.util.excel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 把数据库中的数据取出，用一定的数据结构存到内存当中，以便于在数据库连接断开之后， 还可以使用这些数据。
 * 
 * @author iceblade
 * @date 2006-06-21
 * 
 */
public class RSUtil {
	/**
	 * 
	 * @param rs
	 *            二维结果
	 * @return list.get() 后得到的是 String[] 第一行是字段的Label ，第二行开始才是数据
	 */

	public static List getStrsListLabel(ResultSet rs) throws SQLException {
		List ls = new ArrayList();
		int cols = rs.getMetaData().getColumnCount();
		String strs1[] = new String[cols];
		for (int i = 0; i < cols; i++) {
			strs1[i] = rs.getMetaData().getColumnLabel(i + 1);
		}
		ls.add(strs1);
		while (rs.next()) {
			String strs[] = new String[cols];
			for (int i = 1; i <= cols; i++) {

				strs[i - 1] = rs.getString(i);
				// add by iceblade for Double类型转换丢失小数前面的零 20070720
				if (rs.getMetaData().getColumnType(i) == 8) {
					strs[i - 1] = String.valueOf(rs.getBigDecimal(i));
				}
			}
			ls.add(strs);
		}
		rs.close();
		return ls;

	}

	/**
	 * 
	 * @param rs
	 *            select count(1) 的结果集(只有一条记录)
	 * @return long 得到的是长整型的数数据
	 */
	public static long getCount(ResultSet rs) throws SQLException {
		long i = 0;
		while (rs.next()) {
			i = rs.getLong(1);
		}
		rs.close();
		return i;
	}

}
