package com.sitech.ssd.sc.os.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.sitech.basd.sxcloud.rsmu.web.BaseAction;
import com.sitech.ssd.sc.common.domain.TbSysDictObj;
import com.sitech.ssd.sc.common.service.TbSysDictService;
import com.sitech.ssd.sc.os.domain.HostModel;
import com.sitech.ssd.sc.os.domain.OsFileSystemModel;
import com.sitech.ssd.sc.os.service.OsFileSystemService;
import com.sitech.ssd.sc.os.service.OsHostService;
import com.sitech.ssd.sc.ostempl.domain.OsTemplate;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateFileSystem;
import com.sitech.ssd.sc.ostempl.domain.OsTemplateVolGroup;
import com.sitech.ssd.sc.ostempl.service.OsTemplateComponetService;
import com.sitech.ssd.sc.ostempl.service.OsTemplateService;

@Controller("osFileSystemAction")
public class OsFileSystemAction extends BaseAction {

	@Resource
	private OsFileSystemService osFileSystemService;
	@Resource
	private TbSysDictService tbSysDictService;
	@Resource
	private OsHostService osHostService;
	@Resource
	private OsTemplateService osTemplateService;
	@Resource
	private OsTemplateComponetService osTemplateComponetService;

	private static final long serialVersionUID = 1L;
	private OsFileSystemForm theForm;
	private List<OsFileSystemModel> list;

	public String queryFileSystem() {

		HostModel host = new HostModel(theForm.getOs_host_id());
		host = osHostService.queryForObject(host);

		String _templId = host.getOs_template();
		if (_templId != null && !"".equals(_templId)) {
			OsTemplateFileSystem obj = new OsTemplateFileSystem("", _templId);
			theForm.setOtfsList(osTemplateComponetService.queryOsTemplateFileSystemList(obj));
		} else {
			theForm.setOtfsList(null);
		}

		OsFileSystemModel model = new OsFileSystemModel(host.getId());
		theForm.setOfsList(osFileSystemService.queryOsFileSystemList(model));

		theForm.setTemplate_id(host.getOs_template());
		theForm.setTemplList(osTemplateService.queryOsTemplateList(new OsTemplate()));
		return "osFileSystem";
	}

	/**
	 * @Title: 涓氬姟閫昏緫 1銆佹牴鎹畂s_host_ide锛宖ilesystem_name鏌ユ壘鏂囦欢鐩綍鏄惁瀛樺湪
	 *         2銆佽嫢瀛樺湪锛屼笉鍐嶅悜琛ㄦ彃鍏ユ暟鎹�锛岃繑鍥炴枃浠剁洰褰曞凡瀛樺湪鐨勬彁绀�
	 *         鑻ヤ笉瀛樺湪锛屽悜琛ㄦ彃鍏ユ暟鎹紝杩斿洖娣诲姞鎴愬姛鐨勬彁绀�
	 * @Description:
	 * @Author: JamTau
	 * @Date 2014-7-15 涓婂崍09:04:41
	 * @return String
	 */
	public String addFileSystem() {
		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(tbSysDictService.queryTbSysDictEffectList(dict));
		OsTemplateVolGroup vg = new OsTemplateVolGroup();
		vg.setTemplate_id(theForm.getTemplate_id());
		theForm.setVgList(osTemplateComponetService.queryOsTemplateVolGroupList(vg));

		return "addOsFileSystem";
	}

	public String saveFileSystem() {

		OsFileSystemModel model = new OsFileSystemModel();
		BeanUtils.copyProperties(theForm, model);
		osFileSystemService.addOsFileSystem(model);

		resetForm(theForm);
		return null;
	}

	/**
	 * 
	 * @Title: validFileSystem
	 * @Description: 楠岃瘉鏂囦欢鐩綍鏄惁宸茬粡瀛樺湪锛� 涓嶈兘鍒涘缓鍚屽悕鐨勬枃浠剁洰褰�
	 * @param 杈撳叆鍙傛暟
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	public String validFileSystem() {
		int msg = 0;

		OsFileSystemModel model = new OsFileSystemModel();
		model.setFilesys_name(theForm.getFilesys_name());
		model.setOs_host_id(theForm.getOs_host_id());
		if (osFileSystemService.queryOsFileSystemList(model).size() > 0) {
			msg = -1;
		} else {
			OsTemplateFileSystem tfs = new OsTemplateFileSystem();
			tfs.setTemplate_id(theForm.getTemplate_id());
			tfs.setFilesystem_name(theForm.getFilesys_name());
			// 杩欓噷鍙互浼樺寲
			tfs = osTemplateComponetService.queryOsTemplateFileSystem(tfs);
			if (tfs.getId() != null && !"".equals(tfs.getId())) {
				msg = -2;
			}
		}
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Title: deleteFileSystem
	 * @Description:
	 * @Author: JamTau
	 * @Date 2014-7-15 涓婂崍10:17:34
	 * @return String
	 */
	public String deleteFileSystem() {
		OsFileSystemModel model = new OsFileSystemModel();
		model.setId(theForm.getId());
		osFileSystemService.deleteOsFileSystem(model);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: updateFileSystem
	 * @Description: 淇敼鏂囦欢绯荤粺
	 * @param 杈撳叆鍙傛暟
	 * @return String 杩斿洖绫诲瀷
	 * @throws
	 */
	public String updateFileSystem() {

		OsFileSystemModel model = new OsFileSystemModel();
		model.setId(theForm.getId());
		model = osFileSystemService.queryOsFileSystem(model);
		BeanUtils.copyProperties(model, theForm);

		TbSysDictObj dict = new TbSysDictObj("OS_FILESYSTEM_FORMAT");
		theForm.setSdList(tbSysDictService.queryTbSysDictEffectList(dict));

		OsTemplateVolGroup vg = new OsTemplateVolGroup();
		vg.setTemplate_id(theForm.getTemplate_id());
		theForm.setVgList(osTemplateComponetService.queryOsTemplateVolGroupList(vg));

		return "updateOsFileSystem";
	}

	public String modifyFileSystem() {
		OsFileSystemModel model = new OsFileSystemModel();
		BeanUtils.copyProperties(theForm, model);
		osFileSystemService.modifyOsFileSystem(model);
		resetForm(theForm);
		return null;
	}

	private void resetForm(OsFileSystemForm theForm) {
		theForm.setFilesys_name("");
		theForm.setFilesys_size("");
		theForm.setFilesys_type("");
	}

	public OsFileSystemForm getTheForm() {
		return theForm;
	}

	public void setTheForm(OsFileSystemForm theForm) {
		this.theForm = theForm;
	}

	public List<OsFileSystemModel> getList() {
		return list;
	}

	public void setList(List<OsFileSystemModel> list) {
		this.list = list;
	}
}
