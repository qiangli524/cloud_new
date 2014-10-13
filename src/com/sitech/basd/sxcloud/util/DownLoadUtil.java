package com.sitech.basd.sxcloud.util;

import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: DownLoadUtil</p>
 * <p>Description: 文件下载工具类</p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company: SI-TECH </p>
 * @author lipengpeng
 * @version 1.0
 * @createtime 2013-9-26 下午6:11:44
 *
 */
public class DownLoadUtil{
	
	/**
	 * @Title: downLoadFile
	 * @Description: 文件下载
	 * @param
	 * @return void
	 * @throws
	 * @author lipengpeng
	 * @version 1.0
	 * @createtime 2013-9-26 下午6:11:48
	 */
	public static void downLoadFile(String filePath , String fileName , HttpServletResponse response){
		FileInputStream inputStream = null;
		OutputStream out = null;
		try {
			response.setContentType("application/octet-stream;");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ java.net.URLEncoder.encode(fileName, "UTF-8")); 
			inputStream = new FileInputStream(filePath);
			out = response.getOutputStream();
			int readBytes = 0;
			byte buffer[] = new byte[1024];
			while ((readBytes = inputStream.read(buffer, 0, 1024)) > 0) {
				out.write(buffer, 0, readBytes);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
