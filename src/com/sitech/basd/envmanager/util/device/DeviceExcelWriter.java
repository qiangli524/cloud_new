package com.sitech.basd.envmanager.util.device;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceExcelWriter {
	private String URL = "";
	private String USERNAME = "";
	private String PASSWORD = "";
	private String TNAME = "";
	private Logger logger = LoggerFactory.getLogger(getClass());
	public DeviceExcelWriter(String url, String uname, String pwd, String tname) {
		URL = url;
		USERNAME = uname;
		PASSWORD = pwd;
		TNAME = tname;
	}

	public String writeExcel(String xmlPath, File filePath) throws Exception {
		OutputStream os = null;
		String msg = "";
		try {
			os = new FileOutputStream(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(os);

			WritableFont wfTitle = new jxl.write.WritableFont(WritableFont
					.createFont("宋体"), 9, WritableFont.BOLD, false);
			WritableCellFormat wcfTitle = new WritableCellFormat(wfTitle);

			wcfTitle.setAlignment(Alignment.CENTRE);
			wcfTitle.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfTitle.setWrap(true);
			wcfTitle.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

			writeSheet(xmlPath, wwb, wcfTitle);

			wwb.write();
			wwb.close();
			os.close();
			msg = "导出excel成功!";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "写excel失败!";

		}
		return msg;

	}

	private void writeSheet(String xmlPath, WritableWorkbook wwb,
			WritableCellFormat wcfTitle) throws RowsExceededException,
			WriteException {
		XmlReader xr = new XmlReader();
		HashMap smap = xr.readXML(xmlPath);
		Set sset = smap.keySet();
		int count = 0;

		WritableSheet sheet = null;

		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.LEFT);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setWrap(true);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		WritableFont rwf = new WritableFont(WritableFont.createFont("宋体"), 9,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.RED);
		WritableCellFormat redwcf = new WritableCellFormat(rwf);
		redwcf.setAlignment(Alignment.LEFT);
		redwcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		redwcf.setWrap(true);
		redwcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

		for (Iterator i = sset.iterator(); i.hasNext();) {
			String skey = (String) i.next();
			sheet = writeHead(wwb, wcfTitle, sheet, smap, skey, count);

			writeContents(sheet, wcf, redwcf);

			count++;
		}
	}

	private void writeContents(WritableSheet sheet, WritableCellFormat wcf,
			WritableCellFormat redwcf) {
		DeviceOraCon oc = new DeviceOraCon(URL, USERNAME, PASSWORD, TNAME);
		ArrayList lobj = oc.getResult();
		DeviceExcelObject eobj = null;
		int scol = 0;
		int srow = 0;
		int ecol = 0;
		int erow = 0;
		HashMap kmap = new HashMap();
		int row = 2;

		WritableCellFormat fontstyle = null;
		try {
			for (int i = 0; i < lobj.size(); i++) {
				eobj = (DeviceExcelObject) lobj.get(i);

				if (eobj.getUSE_DEPARTMENT() != null
						&& eobj.getUSE_DEPARTMENT().equals("预留"))
					fontstyle = redwcf;
				else
					fontstyle = wcf;

				kmap = setFlags(sheet, wcf, redwcf, kmap, "CAPITAL_TYPE", eobj
						.getCAPITAL_TYPE(), eobj.getUSE_DEPARTMENT(), 0, 2, 0,
						2);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "MECH_ID", eobj
						.getMECH_ID(), eobj.getUSE_DEPARTMENT(), 1, 2, 1, 2);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "CAPITAL_ID", eobj
						.getCAPITAL_ID(), eobj.getUSE_DEPARTMENT(), 2, 2, 2, 2);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "MECH_TYPE", eobj
						.getMECH_TYPE(), eobj.getUSE_DEPARTMENT(), 3, 2, 3, 2);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "MECH_CONF", eobj
						.getMECH_CONF(), eobj.getUSE_DEPARTMENT(), 4, 2, 4, 2);
				kmap = setFlagsByType(sheet, wcf, redwcf, kmap, "SYS_SYSTEM",
						eobj.getSYS_SYSTEM(), eobj.getUSE_DEPARTMENT(), 5, 2,
						5, 2, eobj.getCAPITAL_TYPE());

				writelines(sheet, fontstyle, eobj.getSYS_HOSTNAME(), 6, row);
				writelines(sheet, fontstyle, eobj.getSYS_VM(), 7, row);

				kmap = setFlags(sheet, wcf, redwcf, kmap, "IP_PHYSICS", eobj
						.getIP_PHYSICS(), eobj.getUSE_DEPARTMENT(), 8, 2, 8, 2);
				writelines(sheet, fontstyle, eobj.getIP_VIRTUAL(), 9, row);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "IP_ILO", eobj
						.getIP_ILO(), eobj.getUSE_DEPARTMENT(), 10, 2, 10, 2);

				writelines(sheet, fontstyle, eobj.getPWD_SYSTEM(), 11, row);
				writelines(sheet, fontstyle, eobj.getPWD_CONSOLE(), 12, row);

				kmap = setFlags(sheet, wcf, redwcf, kmap, "STORE", eobj
						.getSTORE(), eobj.getUSE_DEPARTMENT(), 13, 2, 13, 2);
				writelines(sheet, fontstyle, eobj.getUSE_DOMAN(), 14, row);
				kmap = setFlags(sheet, wcf, redwcf, kmap, "USE_RES", eobj
						.getUSE_RES(), eobj.getUSE_DEPARTMENT(), 15, 2, 15, 2);

				writelines(sheet, fontstyle, eobj.getUSE_DEPARTMENT(), 16, row);
				writelines(sheet, fontstyle, eobj.getUSE_DESCRIP(), 17, row);
				writelines(sheet, fontstyle, eobj.getMANAG_PERSON(), 18, row);
				writelines(sheet, fontstyle, eobj.getMANAG_RECORD(), 19, row);
				writelines(sheet, fontstyle, eobj.getMANAG_REPAIR(), 20, row);
				writelines(sheet, fontstyle, eobj.getMANAG_USE(), 21, row);
				writelines(sheet, fontstyle, eobj.getMANAG_USABLE(), 22, row);
				writelines(sheet, fontstyle, eobj.getMANAG_DESCRIP(), 23, row);

				row++;
			}

			Set kset = kmap.keySet();
			for (Iterator j = kset.iterator(); j.hasNext();) {
				String skey = (String) j.next();
				String[] flags = (String[]) kmap.get(skey);

				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]);
				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				Label labelCell = new Label(scol, srow, flags[0], wcf);
				sheet.addCell(labelCell);
			}

		} catch (Exception e) {
			logger.debug("写文件内容失败!" + e.getMessage());
			e.printStackTrace();
		}

	}

	private void writelines(WritableSheet sheet, WritableCellFormat wcf,
			String content, int col, int row) throws RowsExceededException,
			WriteException {
		Label labelCell = new Label(col, row, content, wcf);
		sheet.addCell(labelCell);
	}

	private void writeNumber(WritableSheet sheet, WritableCellFormat wcf,
			Object obj, int col, int row) throws RowsExceededException,
			WriteException {
		if (obj == null) {
			Label labelCell = new Label(col, row, "", wcf);
			sheet.addCell(labelCell);
		} else {
			jxl.write.Number labelN = new jxl.write.Number(col, row, Double
					.parseDouble(obj.toString()), wcf);
			sheet.addCell(labelN);
		}
	}

	// 公式，百分比
	private void writeFormula(WritableSheet sheet, String formula, int col,
			int row) throws RowsExceededException, WriteException {
		NumberFormat nf = new NumberFormat("#.00%");
		WritableFont wf = new WritableFont(WritableFont.createFont("宋体"), 9,
				WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		WritableCellFormat wcfN = new WritableCellFormat(wf, nf);
		wcfN.setAlignment(Alignment.LEFT);
		wcfN.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcfN.setWrap(true);
		wcfN.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		jxl.write.Formula f = new jxl.write.Formula(col, row, formula, wcfN);
		sheet.addCell(f);
	}

	// 公式
	private void writeNumberFormula(WritableSheet sheet,
			WritableCellFormat wcf, String formula, int col, int row)
			throws RowsExceededException, WriteException {
		jxl.write.Formula f = new jxl.write.Formula(col, row, formula, wcf);
		sheet.addCell(f);
	}

	private HashMap setFlags(WritableSheet sheet, WritableCellFormat wcf,
			WritableCellFormat redwcf, HashMap kmap, String key,
			String content, String fontvalue, int scol, int srow, int ecol,
			int erow) throws RowsExceededException, WriteException {
		if (content == null)
			content = "";
		if (kmap.containsKey(key)) {
			String[] flags = (String[]) kmap.get(key);
			if (content.equals(flags[0])) {
				flags[4] = "" + (Integer.parseInt(flags[4]) + 1);
				flags[6] = fontvalue;
			} else {
				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]);
				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				if (flags[5] != null && flags[6] != null
						&& flags[5].equals("预留") && flags[5].equals(flags[6])) {
					Label labelCell = new Label(scol, srow, flags[0], redwcf);
					sheet.addCell(labelCell);
				} else {
					Label labelCell = new Label(scol, srow, flags[0], wcf);
					sheet.addCell(labelCell);
				}

				flags[0] = content;
				flags[2] = "" + (erow + 1);
				flags[4] = "" + (erow + 1);
				flags[5] = fontvalue;
				flags[6] = fontvalue;
				kmap.put(key, flags);
			}
		} else {
			String[] flags = new String[7];
			flags[0] = content;
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			flags[5] = fontvalue;
			flags[6] = fontvalue;
			kmap.put(key, flags);
		}
		return kmap;
	}

	private HashMap setFlagsByType(WritableSheet sheet, WritableCellFormat wcf,
			WritableCellFormat redwcf, HashMap imap, String key,
			String content, String fontvalue, int scol, int srow, int ecol,
			int erow, String type) throws RowsExceededException, WriteException {
		if (content == null)
			content = "";
		if (imap.containsKey(key)) {
			String[] flags = (String[]) imap.get(key);
			if (content.equals(flags[0]) && type.equals(flags[5])) {
				flags[4] = "" + (Integer.parseInt(flags[4]) + 1);
				flags[7] = fontvalue;
			} else {
				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]);
				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				if (flags[6] != null && flags[7] != null
						&& flags[6].equals("预留") && flags[6].equals(flags[7])) {
					Label labelCell = new Label(scol, srow, flags[0], redwcf);
					sheet.addCell(labelCell);
				} else {
					Label labelCell = new Label(scol, srow, flags[0], wcf);
					sheet.addCell(labelCell);
				}

				flags[0] = content.toString();
				flags[2] = "" + (erow + 1);
				flags[4] = "" + (erow + 1);
				flags[5] = type;
				flags[6] = fontvalue;
				flags[7] = fontvalue;
				imap.put(key, flags);
			}
		} else {
			String[] flags = new String[8];
			flags[0] = content.toString();
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			flags[5] = type;
			flags[6] = fontvalue;
			flags[7] = fontvalue;
			imap.put(key, flags);
		}
		return imap;
	}

	private HashMap setFlagsByIpFile(WritableSheet sheet,
			WritableCellFormat wcf, HashMap imap, String key, Object content,
			int scol, int srow, int ecol, int erow, String ip, String files,
			boolean isNum) throws RowsExceededException, WriteException {
		if (content == null)
			content = "";
		if (files == null)
			files = "";
		if (imap.containsKey(key)) {
			String[] flags = (String[]) imap.get(key);
			if (content.toString().equals(flags[0]) && ip.equals(flags[5])
					&& files.equals(flags[6])) {
				flags[4] = "" + (Integer.parseInt(flags[4]) + 1);
			} else {
				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]);
				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				if (isNum && !flags[0].equals("")) {
					jxl.write.Number labelN = new jxl.write.Number(scol, srow,
							Double.parseDouble(flags[0]), wcf);
					sheet.addCell(labelN);
				} else {
					Label labelCell = new Label(scol, srow, flags[0], wcf);
					sheet.addCell(labelCell);
				}

				flags[0] = content.toString();
				flags[2] = "" + (erow + 1);
				flags[4] = "" + (erow + 1);
				flags[5] = ip;
				flags[6] = files;
				imap.put(key, flags);
			}
		} else {
			String[] flags = new String[7];
			flags[0] = content.toString();
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			flags[5] = ip;
			flags[6] = files;
			imap.put(key, flags);
		}
		return imap;
	}

	private WritableSheet writeHead(WritableWorkbook wwb,
			WritableCellFormat wcfTitle, WritableSheet sheet, HashMap smap,
			String skey, int count) {
		int scol, srow, ecol, erow = 0;
		int col, row = 0;
		Label titleCell = null;
		Label labelCell = null;

		sheet = wwb.createSheet(skey, count);
		ArrayList clist = (ArrayList) smap.get(skey);
		try {
			for (int j = 0; j < clist.size(); j++) {
				Element node = (Element) clist.get(j);
				String merge = node.attributeValue("merge");
				if (merge != null && merge.equals("true")) {
					scol = Integer.parseInt(node.attributeValue("scol"));
					srow = Integer.parseInt(node.attributeValue("srow"));
					ecol = Integer.parseInt(node.attributeValue("ecol"));
					erow = Integer.parseInt(node.attributeValue("erow"));
					sheet.mergeCells(scol, srow, ecol, erow);
					titleCell = new Label(scol, srow, node
							.attributeValue("name"), wcfTitle);
					sheet.addCell(titleCell);
				}
				if (merge != null && merge.equals("false")) {
					col = Integer.parseInt(node.attributeValue("col"));
					row = Integer.parseInt(node.attributeValue("row"));
					labelCell = new Label(col, row,
							node.attributeValue("name"), wcfTitle);
					sheet.addCell(labelCell);
				}
			}
		} catch (Exception e) {
			logger.debug("写文件头失败!" + e.getMessage());
		} finally {
			return sheet;
		}
	}
}
