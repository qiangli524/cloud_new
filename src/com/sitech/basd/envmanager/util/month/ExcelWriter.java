package com.sitech.basd.envmanager.util.month;

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


public class ExcelWriter {

	private String URL = "";
	private String USERNAME = "";
	private String PASSWORD = "";
	private String TNAME = "";
	private Logger logger = LoggerFactory.getLogger(getClass());
	public ExcelWriter(String url, String uname, String pwd, String tname) {
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

		for (Iterator i = sset.iterator(); i.hasNext();) {
			String skey = (String) i.next();
			sheet = writeHead(wwb, wcfTitle, sheet, smap, skey, count);

			if (skey.equals("简明汇总")) {
				sheet.setColumnView(0, 20);
				sheet.setColumnView(1, 20);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 20);
				writeContents(sheet, wcf); // 简明汇总
			} else {
				String domainname = getDomainName(skey);
				writeContents(sheet, domainname, wcf);
			}

			count++;
		}
	}

	// 简明汇总
	private void writeContents(WritableSheet sheet, WritableCellFormat wcf) {
		OraCon oc = new OraCon(URL, USERNAME, PASSWORD, TNAME);
		ArrayList lobj = oc.getSum();
		SumObject sobj = null;
		int scol = 0;
		int srow = 0;
		int ecol = 0;
		int erow = 0;
		HashMap kmap = new HashMap();
		int row = 2;
		try {
			for (int i = 0; i < lobj.size(); i++) {
				sobj = (SumObject) lobj.get(i);

				if (kmap.containsKey("CF_PRODUCT")) {
					String[] content = (String[]) kmap.get("CF_PRODUCT");
					if (content[0] != null
							&& !content[0].equals(sobj.getPRODUCT())) {
						writelines(sheet, wcf, "汇总", 1, row);
						writeNumberFormula(sheet, wcf, "SUM(C" + (row - 2)
								+ ":C" + (row), 2, row);
						writeNumberFormula(sheet, wcf, "SUM(D" + (row - 2)
								+ ":D" + (row), 3, row);
						writeFormula(sheet, "D" + (row + 1) + "/C" + (row + 1),
								4, row);
						writeNumberFormula(sheet, wcf, "SUM(F" + (row - 2)
								+ ":F" + (row), 5, row);

						row++;
					}
				}

				kmap = setSumFlags(sheet, wcf, kmap, "CF_PRODUCT", sobj
						.getPRODUCT(), 0, row, 0, row);

				writelines(sheet, wcf, getSumDomain(sobj.getDOMAIN()), 1, row);
				writeNumber(sheet, wcf, sobj.getAPPLY(), 2, row);
				writeNumber(sheet, wcf, sobj.getUSED(), 3, row);
				writeFormula(sheet, "D" + (row + 1) + "/C" + (row + 1), 4, row);
				writeNumber(sheet, wcf, sobj.getMEM(), 5, row);

				row++;
			}

			Set kset = kmap.keySet();
			for (Iterator j = kset.iterator(); j.hasNext();) {
				String skey = (String) j.next();
				String[] flags = (String[]) kmap.get(skey);

				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]) + 1;

				writelines(sheet, wcf, "汇总", 1, erow);
				writeNumberFormula(sheet, wcf, "SUM(C" + (erow - 2) + ":C"
						+ (erow), 2, erow);
				writeNumberFormula(sheet, wcf, "SUM(D" + (erow - 2) + ":D"
						+ (erow), 3, erow);
				writeFormula(sheet, "D" + (erow + 1) + "/C" + (erow + 1), 4,
						erow);
				writeNumberFormula(sheet, wcf, "SUM(F" + (erow - 2) + ":F"
						+ (erow), 5, erow);

				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				Label labelCell = new Label(scol, srow, flags[0], wcf);
				sheet.addCell(labelCell);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String getSumDomain(String skey) {
		String domainname = skey;
		if (skey.equals("研发中心(含商务项目的研发)"))
			domainname = "开发环境";
		if (skey.equals("测试中心"))
			domainname = "测试环境";
		if (skey.equals("演示环境"))
			domainname = "演示环境";
		return domainname;
	}

	private void writeContents(WritableSheet sheet, String domainname,
			WritableCellFormat wcf) {
		OraCon oc = new OraCon(URL, USERNAME, PASSWORD, TNAME);
		ArrayList lobj = oc.getResult(domainname);
		ExcelObject eobj = null;
		int scol = 0;
		int srow = 0;
		int ecol = 0;
		int erow = 0;
		HashMap kmap = new HashMap();
		int row = 3;
		try {
			for (int i = 0; i < lobj.size(); i++) {
				eobj = (ExcelObject) lobj.get(i);
				if (i == 0) {
					scol = 0;
					srow = 3;
					ecol = 0;
					erow = lobj.size() + 2;
					sheet.mergeCells(scol, srow, ecol, erow);
					Label labelCell = new Label(scol, srow, eobj.getDOMAIN(),
							wcf);
					sheet.addCell(labelCell);
				}

				kmap = setFlags(sheet, wcf, kmap, "CF_SOURCE", eobj
						.getRESOURCE(), 1, 3, 1, 3);
				kmap = setFlags(sheet, wcf, kmap, "CF_CLASS", eobj.getCLASS(),
						2, 3, 2, 3);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_HOSTTYPE", eobj
						.getHOSTTYPE(), 3, 3, 3, 3, eobj.getRESOURCE(), eobj
						.getCLASS(), false);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_HOSTNUM", eobj
						.getHOSTNUM(), 4, 3, 4, 3, eobj.getRESOURCE(), eobj
						.getCLASS(), false);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_DESCRIPTION",
						eobj.getDESCRIPTION(), 5, 3, 5, 3, eobj.getRESOURCE(),
						eobj.getCLASS(), false);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_SYSTEM", eobj
						.getSYSTEM(), 6, 3, 6, 3, eobj.getRESOURCE(), eobj
						.getCLASS(), false);
				// kmap =
				// setFlags(sheet,wcf,kmap,"CF_HOSTTYPE",eobj.getHOSTTYPE(),3,3,3,3);
				// kmap =
				// setFlags(sheet,wcf,kmap,"CF_HOSTNUM",eobj.getHOSTNUM(),4,3,4,3);
				// kmap =
				// setFlags(sheet,wcf,kmap,"CF_DESCRIPTION",eobj.getDESCRIPTION(),5,3,5,3);
				// kmap =
				// setFlags(sheet,wcf,kmap,"CF_SYSTEM",eobj.getSYSTEM(),6,3,6,3);
				kmap = setFlags(sheet, wcf, kmap, "CF_HOSTNAME", eobj
						.getHOSTNAME(), 7, 3, 7, 3);
				kmap = setFlags(sheet, wcf, kmap, "CF_IP", eobj.getIP(), 8, 3,
						8, 3);
				writelines(sheet, wcf, eobj.getPRODUCT(), 9, row);
				writelines(sheet, wcf, eobj.getDEVEPROD(), 10, row);
				writelines(sheet, wcf, eobj.getSID(), 11, row);
				writeNumber(sheet, wcf, eobj.getTABLESPACE(), 12, row);
				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_FILESYSNAM", eobj
						.getFILESYSNAM(), 13, 3, 13, 3, eobj.getIP(), false);

				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_FILEAPPNUM", eobj
						.getFILEAPPNUM(), 14, 3, 14, 3, eobj.getIP(), eobj
						.getFILESYSNAM(), true);

				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_CPUUSED", eobj
						.getCPUUSED(), 15, 3, 15, 3, eobj.getIP(), false);
				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_MEM", eobj.getMEM(),
						16, 3, 16, 3, eobj.getIP(), true);

				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_FILEUSERD", eobj
						.getFILEUSERD(), 17, 3, 17, 3, eobj.getIP(), eobj
						.getFILESYSNAM(), true);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_FILEUSEPER", eobj
						.getFILEUSEPER(), 18, 3, 18, 3, eobj.getIP(), eobj
						.getFILESYSNAM(), false);

				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_SID1",
						eobj.getSID1(), 19, 3, 19, 3, eobj.getIP(), false);

				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_TABSPAUSED", eobj
						.getTABSPAUSED(), 20, 3, 20, 3, eobj.getIP(), eobj
						.getSID1(), true);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_TABSPAUSEPER",
						eobj.getTABSPAUSEPER(), 21, 3, 21, 3, eobj.getIP(),
						eobj.getSID1(), false);
				kmap = setFlagsByIpFile(sheet, wcf, kmap, "CF_SGA", eobj
						.getSGA(), 22, 3, 22, 3, eobj.getIP(), eobj.getSID1(),
						false);

				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_CPULEFT", eobj
						.getCPULEFT(), 23, 3, 23, 3, eobj.getIP(), false);
				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_MEMLEFT", eobj
						.getMEMLEFT(), 24, 3, 24, 3, eobj.getIP(), false);
				kmap = setFlagsByIp(sheet, wcf, kmap, "CF_STORAGE", eobj
						.getSTORAGE(), 25, 3, 25, 3, eobj.getIP(), false);

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

	// 简明汇总
	private HashMap setSumFlags(WritableSheet sheet, WritableCellFormat wcf,
			HashMap kmap, String key, String content, int scol, int srow,
			int ecol, int erow) throws RowsExceededException, WriteException {
		if (kmap.containsKey(key)) {
			String[] flags = (String[]) kmap.get(key);
			if (content != null && content.equals(flags[0])) {
				flags[4] = "" + (Integer.parseInt(flags[4]) + 1);
			} else {
				scol = Integer.parseInt(flags[1]);
				int nsrow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				int nerow = erow - 1;
				if (nsrow != nerow)
					sheet.mergeCells(scol, nsrow, ecol, nerow);

				Label labelCell = new Label(scol, nsrow, flags[0], wcf);
				sheet.addCell(labelCell);

				flags[0] = content;
				flags[2] = "" + srow;
				flags[4] = "" + erow;
				kmap.put(key, flags);
			}
		} else {
			String[] flags = new String[5];
			flags[0] = content;
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			kmap.put(key, flags);
		}
		return kmap;
	}

	private HashMap setFlags(WritableSheet sheet, WritableCellFormat wcf,
			HashMap kmap, String key, String content, int scol, int srow,
			int ecol, int erow) throws RowsExceededException, WriteException {
		if (kmap.containsKey(key)) {
			String[] flags = (String[]) kmap.get(key);
			if (content != null && content.equals(flags[0])) {
				flags[4] = "" + (Integer.parseInt(flags[4]) + 1);
			} else {
				scol = Integer.parseInt(flags[1]);
				srow = Integer.parseInt(flags[2]);
				ecol = Integer.parseInt(flags[3]);
				erow = Integer.parseInt(flags[4]);
				if (srow != erow)
					sheet.mergeCells(scol, srow, ecol, erow);

				Label labelCell = new Label(scol, srow, flags[0], wcf);
				sheet.addCell(labelCell);

				flags[0] = content;
				flags[2] = "" + (erow + 1);
				flags[4] = "" + (erow + 1);
				kmap.put(key, flags);
			}
		} else {
			String[] flags = new String[5];
			flags[0] = content;
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			kmap.put(key, flags);
		}
		return kmap;
	}

	private HashMap setFlagsByIp(WritableSheet sheet, WritableCellFormat wcf,
			HashMap imap, String key, Object content, int scol, int srow,
			int ecol, int erow, String ip, boolean isNum)
			throws RowsExceededException, WriteException {
		if (content == null)
			content = "";
		if (imap.containsKey(key)) {
			String[] flags = (String[]) imap.get(key);
			if (content.toString().equals(flags[0]) && ip.equals(flags[5])) {
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
				imap.put(key, flags);
			}
		} else {
			String[] flags = new String[6];
			flags[0] = content.toString();
			flags[1] = "" + scol;
			flags[2] = "" + srow;
			flags[3] = "" + ecol;
			flags[4] = "" + erow;
			flags[5] = ip;
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

	private String getDomainName(String skey) {
		String domainname = "";
		if (skey.equals("开发环境使用情况"))
			domainname = "研发中心(含商务项目的研发)";
		if (skey.equals("测试环境使用情况"))
			domainname = "测试中心";
		if (skey.equals("演示环境使用情况"))
			domainname = "演示环境";
		return domainname;
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
			logger.debug("写文件内容失败!" + e.getMessage());
		} finally {
			return sheet;
		}
	}
}
