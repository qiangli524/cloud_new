package com.sitech.ssd.sc.os.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.service.OsXlsSupportService;
import com.sitech.utils.randomid.RandomUUID;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: OsXlsSupportAction
 * </p>
 * <p>
 * Description: 支持从excel文件导入设备序列号，型号，mac地址等
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author threej
 * @date 2014年7月4日09:06:35
 */
@SuppressWarnings("serial")
@Controller("osXlsSupportAction")
public class OsXlsSupportAction extends BaseAction implements
		ServletContextAware {
	/** result of execute(import) */
	private String result;
	/** excel file */
	private File file;
	/** struts2 interface */
	private ServletContext servletContext;
	@Resource
	private OsXlsSupportService osXlsSupportService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * @Title: excel_browse
	 * @Description:进入选择导入文件界面
	 * @author threej
	 * @date 2014年7月4日09:08:43
	 * @return
	 */
	public String excel_browse() {
		return "excel_browse";
	}

	/**
	 * 
	 * @Title: importFromXls
	 * @Description:从excel模板导入设备序列号,类型,型号等
	 * @author threej
	 * @date 2014年7月3日17:05:19
	 * @return
	 */
	public String importFromXls() throws Exception {
		/** 用js获取不到绝对路径，xxx */

		InputStream is = new FileInputStream(file);
		List<HostModel> list = new ArrayList<HostModel>();

		Workbook hwb = WorkbookFactory.create(is);
		// 选择第一个工作表
		Sheet sheet1 = hwb.getSheetAt(0);
		int rows = sheet1.getLastRowNum();
		if (rows >= 8) {
			for (int rownum = 8; rownum <= rows; rownum++) {
				Row row = sheet1.getRow(rownum);
				if (row != null) {
					HostModel hostModel = new HostModel();
					hostModel.setId(RandomUUID.getUuid());
					if (row.getCell(0) != null) {
						hostModel.setSerial_num(row.getCell(0).toString());
					}
					if (row.getCell(1) != null
							&& "IBM小型机".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("1");
					}
					if (row.getCell(1) != null
							&& "IBM X86刀片".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("2");
					}
					if (row.getCell(1) != null
							&& "IBM PC服务器".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("3");
					}
					if (row.getCell(1) != null
							&& "HP X86刀片".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("4");
					}
					if (row.getCell(1) != null
							&& "机架服务器".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("5");
					}
					if (row.getCell(1) != null
							&& "HP PC服务器".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("6");
					}
					if (row.getCell(1) != null
							&& "HUAWEI PC服务器".equals(row.getCell(1).toString())) {
						hostModel.setEq_type("7");
					}
					if (row.getCell(2) != null) {
						hostModel.setHost_type_num(row.getCell(2).toString());
					}
					if (row.getCell(3) != null) {
						String cpu_cl = row.getCell(3).toString();
						if (cpu_cl.contains(".")) {
							cpu_cl = cpu_cl.substring(0,
									cpu_cl.lastIndexOf("."));
						}
						hostModel.setCpu_cl(cpu_cl);
					}
					if (row.getCell(4) != null) {
						String memory = row.getCell(4).toString();
						if (memory.contains(".")) {
							memory = memory.substring(0,
									memory.lastIndexOf("."));
						}
						// 换算成M存库
						memory = String.valueOf(Integer.valueOf(memory) * 1024);
						hostModel.setMemory(memory);
					}
					if (row.getCell(5) != null) {
						String store = row.getCell(5).toString();
						if (store.contains(".")) {
							store = store.substring(0, store.lastIndexOf("."));
						}
						// 换算成M存库
						store = String.valueOf(Integer.valueOf(store) * 1024);
						hostModel.setStore(store);
					}
					int mac_num = 0;
					if (row.getCell(6) != null && !"".equals(row.getCell(6).toString())) {
						hostModel.setEq_mac1(row.getCell(6).toString());
						mac_num += 1;
					}
					if (row.getCell(7) != null && !"".equals(row.getCell(7).toString())) {
						hostModel.setEq_mac2(row.getCell(7).toString());
						mac_num += 1;
					}
					if (row.getCell(8) != null && !"".equals(row.getCell(8).toString())) {
						hostModel.setEq_mac3(row.getCell(8).toString());
						mac_num += 1;
					}
					if (row.getCell(9) != null && !"".equals(row.getCell(9).toString())) {
						hostModel.setEq_mac4(row.getCell(9).toString());
						mac_num += 1;
					}
					hostModel.setNic_num(mac_num);
					list.add(hostModel);
				}
			}
		}
		// 选择第二个工作表
		Sheet sheet2 = hwb.getSheetAt(1);
		int row2 = sheet2.getLastRowNum();
		if (row2 >= 8) {
			String serial_num = "";
			for (int i = 8; i <= row2; i++) {
				Row row = sheet2.getRow(i);
				if (row != null) {
					if (row.getCell(0) != null) {
						serial_num = row.getCell(0).toString();
						for (int j = 0; j < list.size(); j++) {
							HostModel hostModel = list.get(j);
							if (serial_num.equals(hostModel.getSerial_num())) {
								if (row.getCell(1) != null
										&& "府青机房".equals(row.getCell(1)
												.toString())) {
									hostModel.setStay_machroom("1");
								}
								if (row.getCell(1) != null
										&& "西区机房".equals(row.getCell(1)
												.toString())) {
									hostModel.setStay_machroom("2");
								}
								if (row.getCell(1) != null
										&& "上海电信".equals(row.getCell(1)
												.toString())) {
									hostModel.setStay_machroom("3");
								}
								if (row.getCell(2) != null) {
									hostModel.setHost_physical_position(row
											.getCell(2).toString());
								}
								if (row.getCell(3) != null) {
									hostModel.setBlade_groove(row.getCell(3)
											.toString());
								}
								if (row.getCell(4) != null) {
									hostModel.setMge_console_ip(row.getCell(4)
											.toString());
								}
								if (row.getCell(5) != null) {
									hostModel.setMge_console_username(row
											.getCell(5).toString());
								}
								if (row.getCell(6) != null) {
									hostModel.setMge_console_pass(row
											.getCell(6).toString());
								}
								break;
							}
						}
					}
				}
			}
		}
		result = osXlsSupportService.insert(list);
		String callback = "<script>parent.importCallBack('" + result
				+ "')</script>";
		PrintWriterOut.printWirter(response, callback);
		return null;
	}

	public void setOsXlsSupportService(OsXlsSupportService osXlsSupportService) {
		this.osXlsSupportService = osXlsSupportService;
	}

	public OsXlsSupportService getOsXlsSupportService() {
		return osXlsSupportService;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
