<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="java.net.URLDecoder"%>
<%	
	out.clear(); 
	out = pageContext.pushBody();

	//String FILE_NAME = new String(request.getParameter("FILE_NAME").getBytes("ISO-8859-1"),"UTF-8");
	request.setCharacterEncoding("UTF-8");
	String FILE_NAME = URLDecoder.decode(request.getParameter("FILE_NAME"),"utf-8");
	//String FILE_DIR = new String(request.getParameter("FILE_DIR").getBytes("ISO-8859-1"),"UTF-8");
	String FILE_DIR = URLDecoder.decode(request.getParameter("FILE_DIR"),"utf-8");
	response.reset();
	response.setContentType("application/octet-stream;");
	response.addHeader("Content-Disposition", "attachment;filename="
			+ java.net.URLEncoder.encode(FILE_NAME, "UTF-8")); 
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
