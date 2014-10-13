<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ page
	import="com.sitech.basd.sxcloud.util.ssh.FileChannel,com.sitech.basd.sxcloud.util.ssh.SshConnection,com.sitech.basd.sxcloud.util.ssh.SshSession,com.sitech.basd.sxcloud.util.ssh.SshConstants,org.apache.commons.logging.Log,org.apache.commons.logging.LogFactory,java.util.*,com.sshtools.j2ssh.sftp.SftpFile"%>

	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<style type="text/css">
			.pop-body{background-color:#f0f0f0;}
			#upTable td{font-size:13px;}
			.btn{width:60px;}
			.txtWidth{width:200px;}
			.txtInput{width:500px;}
			#showList td{font-size:13px;}
		</style>
		
		<script type="text/javascript">

			
			//改变目录
			function changeDirect(val1){
			
			    if(val1==""){
			    	alert("目录不能为空!");
			    	return false;
			    }
				document.getElementById("theForm").<%=SshConstants.PARAMETER_ACTION%>.value="<%=SshConstants.ACTION_CHANGE_DIRECTORY%>";
				document.getElementById("theForm").<%=SshConstants.PARAMETER_DIRECTORY%>.value=val1;
				document.getElementById("theForm").submit();
			}
			
			//文件上传
			function fileUpload(){
			
			    document.getElementById("uploadFile").<%=SshConstants.PARAMETER_ACTION%>.value="<%=SshConstants.ACTION_UPLOAD%>";
			    document.getElementById("uploadFile").target="_blank";
				document.getElementById("uploadFile").submit();
			
			}
			
			//回车事件
			function SubmitKeyClick(obj, evt) {
		
		        evt = (evt) ? evt : ((window.event) ? window.event : "")
		        keyCode = evt.keyCode ? evt.keyCode : (evt.which ? evt.which : evt.charCode);
		        if (keyCode == 13) {
		            event.keyCode=9;  
		            
					document.getElementById("zd").onclick(); 
					
					return;
		        }

		    } 

		</script>
	</head>
	
	<%
		Log log = LogFactory
		.getLog("com.ericdaugherty.sshwebproxy.jsp.file-jsp");

		String connectionInfo = String.valueOf(request.getAttribute(SshConstants.PARAMETER_CONNECTION ));

		String channelId = SshConstants.FILE_CHANNEL;
		log.debug("Displaying file page for: " + connectionInfo + " "
				+ channelId);

		SshSession sshSession = new SshSession(session);

		log.debug("Getting Connection and Channel");
		SshConnection sshConnection = sshSession
				.getSshConnection(connectionInfo);
		FileChannel fileChannel = null;
		boolean valid = false;
		if (sshConnection != null) {
			fileChannel = sshConnection.getFileChannel(channelId);
			if (fileChannel != null) {
				valid = true;
			}
		}
	%>
	<body>

		<%

		if (!valid) {
		%>
		非法的通道
		<a href="loginout.do">
			返回
		</a>
		<%
		} else {
		%>

		当前目录:
		<%=fileChannel.encodeHTML(fileChannel
								.getCurrentDirectory())%>
			点击文件可以下载。
			<br />
			<%
				
			
			   List directoryListing = null;
			   String msg ="";
				try
				        {
					directoryListing = fileChannel
					.getCurrentDirectoryListing();
				}catch (Exception e)
		        {
					e.printStackTrace();
		        }
					 String errorMessage = sshSession.getErrorMessage();
					if (directoryListing == null) {
					out.println("错误原因:");
			%>

			<a href="#" onclick="changeDirect('<%=request.getSession().getAttribute("path")%>')">
				返回
			</a>
			
			<%
			}else if(errorMessage != null && errorMessage.length() > 0){
			 sshSession.setErrorMessage( null );
			 out.println("错误原因:"+errorMessage);
			%>
			<a href="#" onclick="changeDirect('<%=request.getSession().getAttribute("path")%>')">
				返回
			</a>
			
			<%
			
			} else {
				//获取当前目录

				request.getSession().setAttribute("path",fileChannel.getCurrentDirectory());
				
			%>
		<hr />

		<div style="height:400px;overflow-y:auto">
		<table class="pop-table" cellpadding="0" cellspacing="0" width="65%" id="showList">
			<tr>
				<td>
					名称
				</td>
				<td>
					大小
				</td>
				<td>
					修改时间
				</td>
				<td>
					属性
				</td>
			</tr>
			<%
						SftpFile file;
						List listDir= new LinkedList();
						List listFile = new LinkedList();
						HashMap mapALL = new HashMap();
						for (int index = 0; index < directoryListing.size(); index++) {
					        file = (SftpFile) directoryListing.get(index);
					        if (file.isDirectory()) {
						        listDir.add(file.getFilename());
						        mapALL.put(file.getFilename(),file);
					       } else {
						        listFile.add(file.getFilename());
						        mapALL.put(file.getFilename(),file);
					       }
					   }
						directoryListing.clear();
						String[] strDir = (String[])listDir.toArray(new String[]{});
						java.util.Arrays.sort(strDir);
						for(int i = 0;i<listDir.size();i++){
						directoryListing.add(strDir[i]);
						}
						String[] strFile = (String[])listFile.toArray(new String[]{});
						java.util.Arrays.sort(strFile);
						for(int i = 0;i<listFile.size();i++){
						directoryListing.add(strFile[i]);
						}
						// Handle it as a directory
						for (int index = 0; index < directoryListing.size() ; index++) {
						file = (SftpFile)mapALL.get(directoryListing.get(index));
						String fileName1 = file.getFilename();

				        byte[] str1 = fileName1.getBytes();
				        String fileName = new String(str1,"UTF-8");
				        
					    if (file.isDirectory()) {
			%>

			<tr>
				<td>

					<%
					if (".".equals(fileName)) {
					%>
					
					<a href="#" onclick="changeDirect('<%=fileName%>')">
						<img src="sxcloud/cresources/default/images/refresh.png" align="left" border="0">
						<%=fileName%>
					</a>
					<%
					} else if ("..".equals(fileName)) {
					%>
					
					<a href="#" onclick="changeDirect('<%=fileName%>')">
					<img src="sxcloud/cresources/default/images/taxis_up.gif" align="left" border="0">	
						<%=fileName%>
					</a>
					<%
					} else {
					%>
					<img src="sxcloud/cresources/default/images/folder.gif" align="left" border="0">
					<a href="#" onclick="changeDirect('<%=fileName%>')">
						<%=fileName%>
						
					</a>
					<%
					}
					%>
				</td>

				<td>
					<%=file.getAttributes().getSize()%>
				</td>

				<td>
					<%=file.getAttributes().getModTimeString()%>
				</td>
				<td>
					<%=file.getAttributes()
											.getPermissionsString()%>
				</td>

			</tr>
			<%
					}
					// Handle it as a file.
					else {
			%>
			<tr>
				<td>
					<img src="sxcloud/cresources/default/images/ico_new.gif" align="left" border="0">
					<a
						href="depvideo_<%=SshConstants.FILE_DOWN%>?<%=SshConstants.PARAMETER_ACTION%>=<%=SshConstants.ACTION_DOWNLOAD%>&<%=SshConstants.PARAMETER_FILENAME%>=<%=java.net.URLEncoder.encode(fileName,"UTF-8")%>"><%=fileName%>
					</a>
				</td>
				<td>
					<%=file.getAttributes().getSize()%>
				</td>

				<td>
					<%=file.getAttributes().getModTimeString()%>
				</td>
				<td>
					<%=file.getAttributes()
											.getPermissionsString()%>
				</td>
			</tr>
			<%
						}
						}
					
			%>
		</table>
		</div>
		<hr />
		 
		<s:form action="depvideo_sshFile" method="post" id="uploadFile">
		<s:token>
		
			<input type="hidden" name="<%=SshConstants.PARAMETER_ACTION%>"
				value="" />
			<input type="hidden" name="<%=SshConstants.PARAMETER_CONNECTION%>"
				value="<%=connectionInfo%>" />
			<input type="hidden" name="<%=SshConstants.FILE_CHANNEL%>"
				value="<%=channelId%>" />
			<table class="pop-table" cellpadding="0" cellspacing="0"  align="left" id="upTable">
				<tr>
					<td>
						目录:
					</td>
					<td>
						<input type="text" name="toDirecry"
							class="txtWidth" onkeydown="return SubmitKeyClick(this,event);return false;" />
					</td>
					<td>
						<input type="button"  value="转到" class="btn" id="zd" onclick="changeDirect(document.getElementById('toDirecry').value);return false;"/>
						 <input type="button" value="文件上传" class="btn" onclick="fileUpload();return false;" target="_blank"/>
					</td>

				</tr>
				
				
			</table>
			</s:token>
		</s:form>
		
	   		
		
		<%
		
		}}
		%>
		 
	<s:form action="depvideo_sshFile" method="post" id="theForm">
		<s:token>

			<input type="hidden" name="<%=SshConstants.PARAMETER_CONNECTION%>" value="<%=connectionInfo%>" />
		
			<input type="hidden" name="<%=SshConstants.PARAMETER_ACTION%>" value="" />
			
			<input type="hidden" name="<%=SshConstants.PARAMETER_FILENAME%>" value="" />
			
			<input type="hidden" name="<%=SshConstants.PARAMETER_DIRECTORY%>" value="" />
		
			
		</s:token>
		</s:form>

	</body>
