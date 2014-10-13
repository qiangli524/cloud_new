package com.sitech.basd.util.excel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * �ļ��������,���಻���������޸�
 * 
 * @author puwh
 * @version 1.0 , 2009/9/16
 * @since JDK1.5
 */
public final class FileUtils {

	private FileUtils() {
	}

	// Ĭ�ϵ�buffer size
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	private static Log log = LogFactory.getLog("FileUtil.class");

	/**
	 * ͳһת��Ϊ��׼�ļ���ʽ������"/"��Ϊ�ָ�
	 * 
	 * @author puwh
	 * @param strPath
	 * @return
	 */
	public static String convertPath(String strPath) {
		// ʹ��������ʽ�滻
		return strPath.replaceAll("\\\\", "/");
	}

	/**
	 * ���Ŀ¼��,�ļ����������·��.
	 * 
	 * @author puwh
	 * @param strPath
	 * @param strFileName
	 * @return ����·��
	 */
	public static String assembleFilePath(String strPath, String strFileName) {
		String path = convertPath(strPath);
		if (path.charAt(path.length() - 1) != '/') {
			path = path + "/";
		}
		return (path + strFileName).replaceAll("//", "/");
	}

	/**
	 * ����ļ�������
	 * 
	 * @author puwh
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			}
			if (file.canRead() == false) {
				throw new IOException("File '" + file + "' cannot be read");
			}
		} else {
			throw new FileNotFoundException("File '" + file + "' does not exist");
		}
		return new FileInputStream(file);
	}

	/**
	 * �ر�������
	 * 
	 * @author puwh
	 * @param input
	 */
	public static void closeInputStream(InputStream input) {
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	/**
	 * ����ļ������
	 * 
	 * @author puwh
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static FileOutputStream openOutputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file + "' exists but is a directory");
			}
			if (file.canWrite() == false) {
				throw new IOException("File '" + file + "' cannot be written to");
			}
		} else {
			File parent = file.getParentFile();
			if (parent != null && parent.exists() == false) {
				if (parent.mkdirs() == false) {
					throw new IOException("File '" + file + "' could not be created");
				}
			}
		}
		return new FileOutputStream(file);
	}

	/**
	 * �ر������
	 * 
	 * @author puwh
	 * @param output
	 */
	public static void closeOutputStream(OutputStream output) {
		try {
			if (output != null) {
				output.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}

	/**
	 * ��byte����д��OutputStream ��
	 * 
	 * @author puwh
	 * @param data
	 * @param output
	 * @throws IOException
	 */
	public static void write(byte[] data, OutputStream output) throws IOException {
		if (data != null) {
			output.write(data);
		}
	}

	/**
	 * ��byte����д���ļ���
	 * 
	 * @author puwh
	 * @param data
	 * @param file
	 * @throws IOException
	 */
	public static void write(byte[] data, File file) throws IOException {
		OutputStream out = null;
		try {
			out = openOutputStream(file);
			write(data, out);
		} finally {
			closeOutputStream(out);
		}
	}

	/**
	 * ��Stringд��OutputStream ��
	 * 
	 * @author puwh
	 * @param data
	 * @param output
	 * @throws IOException
	 */
	public static void write(String data, OutputStream output) throws IOException {
		if (data != null) {
			output.write(data.getBytes());
		}
	}

	/**
	 * ��String��ָ�������ʽд��OutputStream ��
	 * 
	 * @author puwh
	 * @param data
	 * @param output
	 * @param encoding
	 * @throws IOException
	 */
	public static void write(String data, OutputStream output, String encoding) throws IOException {
		if (data != null) {
			if (encoding == null) {
				write(data, output);
			} else {
				output.write(data.getBytes(encoding));
			}
		}
	}

	/**
	 * ��String��ָ�������ʽд���ļ���
	 * 
	 * @author puwh
	 * @param data
	 * @param file
	 * @param encoding
	 * @throws IOException
	 */
	public static void write(String data, File file, String encoding) throws IOException {
		OutputStream out = null;
		try {
			out = openOutputStream(file);
			write(data, out, encoding);
		} finally {
			closeOutputStream(out);
		}
	}

	/**
	 * ��Stringд��ָ���ļ���
	 * 
	 * @author puwh
	 * @param data
	 * @param file
	 * @throws IOException
	 */
	public static void write(String data, File file) throws IOException {
		write(data, file, null);
	}

	/**
	 * ��InputStream�е�����д��OuputStream�У�������ʵ��д����ֽ���
	 * 
	 * @author puwh
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public static long write(InputStream input, OutputStream output) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * ���ļ��е�����д��OutputSteam�У�������ʵ��д����ֽ���
	 * 
	 * @author puwh
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 */
	public static long write(File input, OutputStream output) throws IOException {
		InputStream in = null;
		try {
			in = openInputStream(input);
			return write(in, output);
		} finally {
			closeInputStream(in);
		}
	}

	/**
	 * ��InputStream�е�����д���ļ��У�������ʵ��д����ֽ���
	 * 
	 * @author puwh
	 * @param input
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static long write(InputStream input, File file) throws IOException {
		OutputStream out = null;
		try {
			out = openOutputStream(file);
			return write(input, out);
		} finally {
			closeOutputStream(out);
		}
	}

	/**
	 * ��String detailд�����·��URL��fileAbsolutePathUrl���ļ�
	 * 
	 * @author puwh
	 * @param detail
	 *            Ҫд���ļ�������
	 * @param fileAbsolutePathUrl
	 *            �ļ��ľ��·��URL
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static void write(String detail, URL fileAbsolutePathUrl) throws URISyntaxException,
			IOException {
		File file = new File(new URI(fileAbsolutePathUrl.toString()));
		write(detail, file);
	}

	/**
	 * ��ȡInputStream�е����ݣ�����byte���顣
	 * 
	 * @author puwh
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public static byte[] readToByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		write(input, output);
		return output.toByteArray();
	}

	/**
	 * ��ȡ�ļ��е����ݣ�����byte���顣
	 * 
	 * @author puwh
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] readToByteArray(File file) throws IOException {
		InputStream in = null;
		try {
			in = openInputStream(file);
			return readToByteArray(in);
		} finally {
			closeInputStream(in);
		}
	}

	/**
	 * �г�Ŀ¼�µ��ļ���recursiveΪtrueʱ��ѭ����ȡ��Ŀ¼�µ��ļ���
	 * 
	 * @author puwh
	 * @param files
	 * @param directory
	 * @param recursive
	 */
	private static void innerListFiles(Collection files, File directory) {
		File[] found = directory.listFiles();
		if (found != null) {
			for (int i = 0; i < found.length; i++) {
				if (found[i].isDirectory()) {
					innerListFiles(files, found[i]);
				} else {
					files.add(found[i]);
				}
			}
		}
	}

	/**
	 * ɾ���ļ���Ŀ¼������Ŀ¼�����ļ�����Ŀ¼����һ��ɾ��
	 * 
	 * @author puwh
	 * @param file
	 *            �ļ���Ŀ¼
	 * @throws IOException
	 */
	public static void forceDelete(File file) throws IOException {
		if (file == null || !file.exists())
			return;

		if (file.isDirectory()) {
			deleteDirectory(file);
		} else {
			if (!file.delete()) {
				String message = "Unable to delete file: " + file;
				throw new IOException(message);
			}
		}
	}

	/**
	 * ���Ŀ¼�µ���Ŀ¼���ļ�
	 * 
	 * @author puwh
	 * @param directory
	 * @throws IOException
	 */
	public static void cleanDirectory(File directory) throws IOException {
		if (directory == null || !directory.exists() || !directory.isDirectory())
			return;

		File[] files = directory.listFiles();
		if (files == null) { // null if security restricted
			throw new IOException("Failed to list contents of " + directory);
		}

		IOException exception = null;
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			try {
				forceDelete(file);
			} catch (IOException ioe) {
				exception = ioe;
			}
		}

		if (null != exception) {
			throw exception;
		}
	}

	/**
	 * ɾ��Ŀ¼�Լ���Ŀ¼�µ���Ŀ¼���ļ�
	 * 
	 * @author puwh
	 * @param directory
	 * @throws IOException
	 */
	public static void deleteDirectory(File directory) throws IOException {
		if (directory == null || !directory.exists()) {
			return;
		}

		cleanDirectory(directory);
		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			throw new IOException(message);
		}
	}

	/**
	 * �õ�Ŀ¼�Լ�����Ŀ¼�µ������ļ���
	 * 
	 * @author puwh
	 * @param directory
	 * @param recursive
	 * @return
	 */
	public static Collection listFiles(File directory) {
		Collection files = new ArrayList();
		innerListFiles(files, directory);
		return files;
	}

	/**
	 * �õ��������
	 * 
	 * @author puwh
	 * @return
	 */
	private static ClassLoader getClassLoader() {
		return FileUtils.class.getClassLoader();
	}

	/**
	 * �õ�FileUtils.class���ڵ�ClassLoader��Classpath�ľ��·��,�˷����ڴ���δ��jar����ʱ��Ч
	 * URL��ʽ��
	 * 
	 * @author puwh
	 * @return
	 */
	private static String getAbsolutePathOfFileUtilsClass() {
		log.info(getClassLoader().getResource("").toString());
		return getClassLoader().getResource("").toString();
	}

	/**
	 * ��ȡ��Դ�������classpath��·��
	 * 
	 * @author puwh
	 * @param resource
	 * @return URL
	 */
	private static URL getResource(String resource) {
		log.info("input relative path of classpath ��" + resource);
		return getClassLoader().getResource(resource);
	}

	/**
	 * ����source�ַ��������ٸ�dest�ַ����ذ�ĸ���
	 * 
	 * @author puwh
	 * @param source
	 * @param dest
	 * @return
	 * 
	 */
	private static int containSum(String source, String dest) {
		int containSum = 0;
		int destLength = dest.length();
		while (source.indexOf(dest) > -1) {
			containSum = containSum + 1;
			source = source.substring(destLength);
		}
		return containSum;
	}

	/**
	 * ��source�ַ��а�dest�ַ���ǰ��num����ַ�
	 * 
	 * @author puwh
	 * @param source
	 * @param dest
	 * @param num
	 * @return
	 */
	private static String cutLastString(String source, String dest, int num) {
		// String cutSource = null;
		for (int i = 0; i < num; i++) {
			source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
		}
		return source;
	}

	/**
	 * �����Դ�����classpath��·�����õ���Ӧ����Դ�ľ��·��URL. relativePath
	 * ���봫����Դ�����·�����������classpath��·����
	 * �����Ҫ����classpath�ⲿ����Դ����Ҫʹ��../4����
	 * 
	 * @author puwh
	 * @param relativePath
	 *            �������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ��../4����
	 * @return URL ��Դ�ľ��URL
	 * @throws MalformedURLException
	 */
	public static URL getAbsoluteURL(String relativePath) throws MalformedURLException {
		log.info("input relative path ��" + relativePath);
		// log.info(Integer.valueOf(relativePath.indexOf("../")));
		if (relativePath.indexOf("../") == -1) {
			return getResource(relativePath);
		}
		String classPathAbsolutePath = getAbsolutePathOfFileUtilsClass();
		if (relativePath.substring(0, 1).equals("/")) {
			relativePath = relativePath.substring(1);
		}
		String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
		relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
		int containSum = containSum(wildcardString, "../");
		classPathAbsolutePath = cutLastString(classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = classPathAbsolutePath + relativePath;
		log.info("absolute path is " + resourceAbsolutePath);
		URL resourceAbsoluteURL = new URL(resourceAbsolutePath);
		return resourceAbsoluteURL;
	}

	/**
	 * ���URL�õ�������
	 * 
	 * @author puwh
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream getInputStream(URL url) throws IOException {
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
	}

	/**
	 * �����Դ�����classpath��·�����õ�������.
	 * relativePath���봫����Դ�����·�����������classpath��·����
	 * �����Ҫ����classpath�ⲿ����Դ����Ҫʹ�á�../4����
	 * 
	 * @author puwh
	 * @param relativePath
	 *            ���봫����Դ�����·��
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */

	public static InputStream getInputStreamByRelativePath(String relativePath)
			throws MalformedURLException, IOException {
		return getInputStream(getAbsoluteURL(relativePath));
	}

	/**
	 * 
	 * <pre>
	 * &lt;b&gt;˵��:&lt;/b&gt;			��ȡ�ļ����ݵ�List�У�����ȡÿһ�е�ǰ20λ
	 * &lt;b&gt;����:&lt;/b&gt;			Oct 13, 2006			
	 * &#064;param fileName		�ļ�·�����ı��ļ���
	 * &#064;return
	 * &#064;author				chenwq
	 * 
	 */
	public static List readTxtFile(String fileName) {
		File f_in = new File(fileName);
		ArrayList ls = new ArrayList();
		FileReader fr_in = null;
		BufferedReader br_in = null;
		try {
			String sline = "";
			fr_in = new FileReader(f_in);
			// ��ȡ�ļ�
			br_in = new BufferedReader(fr_in);
			while (sline != null) {
				sline = br_in.readLine();
				if (sline == null || sline.length() == 0) {
					continue;
				} else {
					if (sline.length() >= 20) {
						ls.add(sline.substring(0, 20));
					} else {
						ls.add(sline);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br_in.close();
			} catch (Exception e) {
			}
			try {
				fr_in.close();
			} catch (Exception e) {
			}
		}
		return ls;
	}

	/**
	 * <pre>
	 * &lt;b&gt;˵��:&lt;/b&gt;			��ȡ�ļ����ݵ�List�У���ÿһ�е�������ӵ�List��
	 * &lt;b&gt;����:&lt;/b&gt;		    
	 * &#064;param file_name		�ļ�·�����ı��ļ���
	 * &#064;return              ����List
	 * &#064;author
	 * 
	 */
	public static List readTxtFile2(String file_name) {
		File f_in = new File(file_name);
		ArrayList ls = new ArrayList();
		FileReader fr_in = null;
		BufferedReader br_in = null;
		try {
			String sline = "";
			fr_in = new FileReader(f_in);
			// ��ȡ�ļ�
			br_in = new BufferedReader(fr_in);
			while (sline != null) {
				sline = br_in.readLine();
				if (sline == null || sline.length() == 0) {
					continue;
				} else {
					if (sline.trim().length() == 0) {
						continue;
					} else {
						ls.add(sline.trim());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br_in.close();
			} catch (Exception e) {
			}
			try {
				fr_in.close();
			} catch (Exception e) {
			}
		}
		return ls;
	}

	/**
	 * 
	 * <pre>
	 * &lt;b&gt;˵��:&lt;/b&gt;			��ȡ�ļ����ݵ�List�У�����ȡÿһ�е�һ��Ԫ���ǰ20λ
	 * &lt;b&gt;����:&lt;/b&gt;			Oct 13, 2006			
	 * &#064;param file_name		�ļ�·����EXCEL�ļ���
	 * &#064;return
	 * &#064;author				chenwq
	 * 
	 */
	public static List readXlsFile(String file_name) {
		// ����EXCEL�Ļ��
		InputStream is = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		List resultList = new ArrayList();
		try {
			is = new FileInputStream(file_name);
			workbook = new HSSFWorkbook(is);
			sheet = workbook.getSheetAt(0);
			String cellValue = ""; // ���EXCEL��Ԫ���ֵ
			for (int i = 0;; i++) {
				row = sheet.getRow(i);// ��
				if (row == null)
					break;
				cell = row.getCell((short) 0);// ��Ԫ��
				if (cell == null)
					break;
				int cellType = cell.getCellType();
				// ȡ�õ�Ԫ���ֵ
				switch (cellType) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					cellValue = String.valueOf((long) cell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					cellValue = cell.getStringCellValue();
					break;
				}
				if (cellValue == null || cellValue.length() == 0) {
					continue;
				} else {
					if (cellValue.length() >= 20) {
						resultList.add(cellValue.substring(0, 20));
					} else {
						resultList.add(cellValue);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}
		return resultList;
	}

	/**
	 * 
	 * <pre>
	 * &lt;b&gt;˵��:&lt;/b&gt;			 �����ذ��imei��listת��ΪString[];
	 * &lt;b&gt;����:&lt;/b&gt;			Oct 13, 2006			
	 * &#064;param ls
	 * &#064;return
	 * &#064;author				chenwq
	 * 
	 */
	public static String[] getList2Strs(List ls) {
		String[] strs = null;
		if (ls.size() > 0) {
			strs = new String[ls.size()];
			for (int i = 0; i < ls.size(); i++) {
				strs[i] = (String) ls.get(i);
			}
		}
		return strs;
	}
}
