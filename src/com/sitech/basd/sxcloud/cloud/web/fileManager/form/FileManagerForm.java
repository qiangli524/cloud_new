package com.sitech.basd.sxcloud.cloud.web.fileManager.form;

import java.io.File;
import java.util.List;

/**
 * 帮助管理
 * 
 * @author dsfcm
 * 
 */
public class FileManagerForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String FILE_NAME = null;

	private String FILE_DIR = null;

	private String TYPE_NAME = null;

	private String AUTH_CODE = null;

	private String ENABLE = null;

	private String FLAG = null;

	private String USER_ID = null;

	private List resultList = null;

	private int PAGINATIONSTART = 0;

	private int PAGINATIONEND = 0;

	private String LIST_NAME = null;

	private String LIST_ID = null;

	private String OLD_LIST_NAME = null;

	private String OLD_FILE_DIR = null;

	private String[] TEMP_TYPE_ID = null;

	private String COMMAND = null;

	private String TYPE_ID = null;

	private String SEARCH_TYPE_NAME = null;

	private String FILE_ID = null;

	private List FILETYPELIST = null;

	private List DIRECTORYLIST = null;

	private File file = null;// 附件
	private String fileContentType; //附件类型
	private String fileFileName;//附件名

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private String FILE_DESC = null;

	private List SI_LIST = null;

	private String SI_ID = null;

	private String ROOT = null;

	public String VERSION = null;

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String version) {
		VERSION = version;
	}

	public List getDIRECTORYLIST() {
		return DIRECTORYLIST;
	}

	public void setDIRECTORYLIST(List directorylist) {
		DIRECTORYLIST = directorylist;
	}

	public List getFILETYPELIST() {
		return FILETYPELIST;
	}

	public void setFILETYPELIST(List filetypelist) {
		FILETYPELIST = filetypelist;
	}

	public String getFILE_ID() {
		return FILE_ID;
	}

	public void setFILE_ID(String file_id) {
		FILE_ID = file_id;
	}

	public String getCOMMAND() {
		return COMMAND;
	}

	public void setCOMMAND(String command) {
		COMMAND = command;
	}

	public String[] getTEMP_TYPE_ID() {
		return TEMP_TYPE_ID;
	}

	public void setTEMP_TYPE_ID(String[] temp_type_id) {
		TEMP_TYPE_ID = temp_type_id;
	}

	public String getSEARCH_TYPE_NAME() {
		return SEARCH_TYPE_NAME;
	}

	public void setSEARCH_TYPE_NAME(String search_type_name) {
		SEARCH_TYPE_NAME = search_type_name;
	}

	public int getPAGINATIONEND() {
		return PAGINATIONEND;
	}

	public void setPAGINATIONEND(int paginationend) {
		PAGINATIONEND = paginationend;
	}

	public int getPAGINATIONSTART() {
		return PAGINATIONSTART;
	}

	public void setPAGINATIONSTART(int paginationstart) {
		PAGINATIONSTART = paginationstart;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String file_name) {
		FILE_NAME = file_name;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getAUTH_CODE() {
		return AUTH_CODE;
	}

	public void setAUTH_CODE(String auth_code) {
		AUTH_CODE = auth_code;
	}

	public String getENABLE() {
		return ENABLE;
	}

	public void setENABLE(String enable) {
		ENABLE = enable;
	}

	public String getTYPE_ID() {
		return TYPE_ID;
	}

	public void setTYPE_ID(String type_id) {
		TYPE_ID = type_id;
	}

	public String getTYPE_NAME() {
		return TYPE_NAME;
	}

	public void setTYPE_NAME(String type_name) {
		TYPE_NAME = type_name;
	}

	public String getFLAG() {
		return FLAG;
	}

	public void setFLAG(String flag) {
		FLAG = flag;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String user_id) {
		USER_ID = user_id;
	}

	public String getLIST_NAME() {
		return LIST_NAME;
	}

	public void setLIST_NAME(String list_name) {
		LIST_NAME = list_name;
	}

	public String getLIST_ID() {
		return LIST_ID;
	}

	public void setLIST_ID(String list_id) {
		LIST_ID = list_id;
	}

	public String getFILE_DIR() {
		return FILE_DIR;
	}

	public void setFILE_DIR(String file_dir) {
		FILE_DIR = file_dir;
	}

	public String getFILE_DESC() {
		return FILE_DESC;
	}

	public void setFILE_DESC(String file_desc) {
		FILE_DESC = file_desc;
	}

	public String getOLD_LIST_NAME() {
		return OLD_LIST_NAME;
	}

	public void setOLD_LIST_NAME(String old_list_name) {
		OLD_LIST_NAME = old_list_name;
	}

	public String getROOT() {
		return ROOT;
	}

	public void setROOT(String root) {
		ROOT = root;
	}

	public String getOLD_FILE_DIR() {
		return OLD_FILE_DIR;
	}

	public void setOLD_FILE_DIR(String old_file_dir) {
		OLD_FILE_DIR = old_file_dir;
	}

	public List getSI_LIST() {
		return SI_LIST;
	}

	public void setSI_LIST(List si_list) {
		SI_LIST = si_list;
	}

	public String getSI_ID() {
		return SI_ID;
	}

	public void setSI_ID(String si_id) {
		SI_ID = si_id;
	}

}
