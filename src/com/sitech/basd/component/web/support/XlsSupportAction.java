package com.sitech.basd.component.web.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.springframework.stereotype.Controller;

import com.sitech.basd.component.service.support.XlsSupportService;
import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.utils.servlet.PrintWriterOut;

/**
 * 
 * <p>
 * Title: XlsSupportAction
 * </p>
 * <p>
 * Description: 支持从excel文件导入主机，用户并生成部署实例
 * </p>
 * <p>
 * Company: si-tech
 * </p>
 * 
 * @author duangh
 * @date Jul 29, 2013
 */
@SuppressWarnings("serial")
@Controller("xlsSupportAction")
public class XlsSupportAction extends BaseAction implements ServletContextAware {
	@Resource
	private XlsSupportService xlsSupportService;
	/** result of execute(import) */
	private String result;
	/** excel file */
	private File file;
	/** struts2 interface */
	private ServletContext servletContext;

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

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 
	 * @Title: browse
	 * @Description:进入选择导入文件界面
	 * @author duangh
	 * @date Jul 29, 2013 7:12:02 PM
	 * @return
	 */
	public String browse() {
		return "browse";
	}

	/**
	 * 
	 * @Title: importFromXls
	 * @Description:从excel模板导入数据包括主机，用户和生成部署实例
	 * @author duangh
	 * @date Jul 29, 2013 4:14:11 PM
	 * @return
	 */
	public String importFromXls() throws Exception {
		/** 用js获取不到绝对路径，xxx */
		InputStream is = new FileInputStream(file);
		this.result = xlsSupportService.importFromXls(is);
		// PrintWriter p = response.getWriter();
		String callback = "<script>parent.importCallBack('" + result + "')</script>";
		// p.print(callback);
		// p.flush();
		// p.close();
		PrintWriterOut.printWirter(response, callback);
		return null;
		// return "import";
	}
}
