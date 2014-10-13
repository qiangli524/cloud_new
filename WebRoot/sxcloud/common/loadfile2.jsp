<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@page import="java.io.*"%>
<%@page import="com.enterprisedt.net.ftp.ssh.SSHFTPClient,com.enterprisedt.net.ftp.FTPTransferType"%>
<%@page import="com.sitech.basd.cloud.util.CommonMethod"%>
<%@page import="com.sitech.basd.bomc.util.DesEncrypterIsmp"%>
<jsp:directive.page import="java.net.URLEncoder"/>
<%	
	out.clear(); 
	out = pageContext.pushBody();

	String FILE_NAME = (String)request.getParameter("FILE_NAME");
	String FILE_DIR = new String(request.getParameter("FILE_DIR").getBytes("ISO-8859-1"),"gb2312");
	response.reset();
	response.setContentType("application/octet-stream;");
	response.addHeader("Content-Disposition", "attachment;filename="
			+ java.net.URLEncoder.encode(FILE_NAME, "utf-8")); 

	FileInputStream in = new FileInputStream(FILE_DIR);
	OutputStream os = response.getOutputStream();
	try {
		int readBytes = 0;
		byte buffer[] = new byte[1024];
		while ((readBytes = in.read(buffer, 0, 1024)) > 0) {

			os.write(buffer, 0, readBytes);
		}
			
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		os.flush();
		os.close();
		in.close();
	}

%>
