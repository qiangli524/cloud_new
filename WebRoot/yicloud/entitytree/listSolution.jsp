<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<%
	String eventId = (String)request.getAttribute("eventId");
%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	
	function getSelect() {
		var TEM_ID = theForm.TEM_ID.value;
		if(theForm.TEM_ID.value==0){
			return false;
		}else{
		//	theForm.action = 'tree_getSolutionInfo.do?TEM_ID='+TEM_ID;
			$("#iptr").empty();
			$("#porttr").empty();
			$("#userNametr").empty();
			$("#pwdtr").empty();
			var url = "tree_getSolutionInfo.do?TEM_ID="+TEM_ID + "&Date" + (new Date());
			if(TEM_ID==1){
				$.getJSON(url,function(data){
					name = decodeURI(decodeURI(data.temName));
					desc = decodeURI(decodeURI(data.temDesc));
					//方案名称
					$("#temName").html(name);
					//方案描述
					$("#temDesc").html(desc);
				//CPU
					$("#iptr").append("<td id='cputd'>CPU(个)</td>");
					$("#iptr").append('<td><s:textfield name="theForm.cpu" cssClass="txt" id="cpu"></s:textfield></td>');
					$("#cpu").attr("value",data.cpu);
					$("#cputd").attr("class","til");	
				//内存，memory
					$("#porttr").append("<td id='memtd'>内存(MB)</td>");
					$("#porttr").append('<td><s:textfield name="theForm.mem" cssClass="txt" id="mem"></s:textfield></td>');
					$("#mem").attr("value",data.mem);
					$("#memtd").attr("class","til");
				//存储
					$("#userNametr").append("<td id='storagetd'>存储(GB)</td>");
					$("#userNametr").append('<td><s:textfield name="theForm.STORAGE" cssClass="txt" id="STORAGE"></s:textfield></td>');
					$("#STORAGE").attr("value",data.storage);
					$("#storagetd").attr("class","til");
					document.getElementById("confirm").style.display="";
					document.getElementById("exeshell").style.display="none";	
				});
			}else if(TEM_ID==2){
				$.getJSON(url,function(data){
					name = decodeURI(decodeURI(data.temName));
					desc = decodeURI(decodeURI(data.temDesc));
					//方案名称
					$("#temName").html(name);
					//方案描述
					$("#temDesc").html(desc);
					//clone的个数
					$("#iptr").append("<td id='numtd'>要克隆的虚拟机个数</td>");
					$("#iptr").append('<td><s:textfield name="theForm.CLONE_NUM" cssClass="txt" id="CLONE_NUM"></s:textfield></td>');
					$("#CLONE_NUM").attr("value",data.num);
					$("#numtd").attr("class","til");
					document.getElementById("confirm").style.display="";
					document.getElementById("exeshell").style.display="none";	
				});
			}else if(TEM_ID==3){
				$.getJSON(url,function(data){
				name = decodeURI(decodeURI(data.temName));
				desc = decodeURI(decodeURI(data.temDesc));
				//方案名称
				$("#temName").html(name);
				//方案描述
				$("#temDesc").html(desc);
				//ip地址
				$("#iptr").append("<td id='iptd'>IP地址</td>");
				$("#iptr").append('<td><s:textfield name="theForm.ip" cssClass="txt" id="ip"></s:textfield></td>');
				$("#ip").attr("value",data.ip);
				$("#iptd").attr("class","til");	
				//端口
				$("#porttr").append("<td id='porttd'>端口</td>");
				$("#porttr").append('<td><s:textfield name="theForm.port" cssClass="txt" id="port"></s:textfield></td>');
				$("#port").attr("value",data.port);
				$("#porttd").attr("class","til");		
				//用户名
				$("#userNametr").append("<td id='nametd'>用户名</td>");
				$("#userNametr").append('<td><s:textfield name="theForm.userName" cssClass="txt" id="userName"></s:textfield></td>');
				$("#userName").attr("value","root");
				$("#nametd").attr("class","til");
				//密码
				$("#pwdtr").append("<td id='pwdtd'>密码</td>");
				$("#pwdtr").append('<td><input type="password" name="theForm.password" id="password" class="txt"/></td>');
				$("#password").attr("value","111111");
				$("#pwdtd").attr("class","til");	
				//执行
			//	$("#commit").append('<input type="button" class="thickbox btn-style02" value="提交"id="confirm" onclick="javascript:realizeSolution();" />');
				document.getElementById("confirm").style.display="";
				document.getElementById("exeshell").style.display="none";			
				});
			}else{
				$.getJSON(url,function(data){
					name = decodeURI(decodeURI(data.temName));
					desc = decodeURI(decodeURI(data.temDesc));
					//方案名称
					$("#temName").html(name);
					//方案描述
					$("#temDesc").html(desc);
					$("#iptr").append('<td id="shelltd">脚本命令</td>');
					$("#iptr").append('<td><s:textarea id="shell" cols="77" rows="10" cssStyle="background:black;color:white"></s:textarea></td>');
					$("#shell").attr("value",data.command);
				});
				document.getElementById("confirm").style.display="none";
				document.getElementById("exeshell").style.display="";
			}
		//	theForm.submit();
		}
    }
	
    function realizeSolution(){
    	var TEM_ID = theForm.TEM_ID.value;
    	theForm.action="tree_realizeSolution.do?TEM_ID="+TEM_ID+"&eventId="+<%=eventId%>;
    	theForm.submit();
    }
    function execute(){
    	var TEM_ID = theForm.TEM_ID.value;
    	window.open("depcomm_executeDemo.do?TEM_ID=" + TEM_ID,"execute",'width=500,height=400');
    }
</script>
</head>
<body>
<s:form action="yvm_dataCenterInfo" method="post" id="theForm">
  <div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
			<tr>
				<td>
					监控告警信息
				</td>
				<td></td>
			<tr>
			<tr>
				<td class="til">
					告警位置
				</td>
				<td>
					<s:property value= "#request.location"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					告警时间
				</td>
				<td>
					<s:property value= "#request.time"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					告警内容
				</td>
				<td>
					<s:property value="#request.content"/>
				</td>
			</tr>
			<tr>
				<td>
					解决方案
				</td>
				<td></td>
			<tr>
			<tr>
				<td class="til" width="20%">
					请选择解决方案
				</td>
				<td>
				<s:select list="theForm.temList" listKey="TEM_ID" listValue="TEM_NAME" 
				onchange="getSelect()" id="TEM_ID" name="theForm.TEM_ID" headerKey="0" headerValue="请选择"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%">
					方案名称
				</td>
				<td id="temName">
					暂无
				</td>
			</tr>
			<tr>
				<td class="til">
					方案描述
				</td>
				<td id="temDesc">
					暂无
				</td>
			</tr>
			<%if(((String)request.getAttribute("oper")).equals("1")){ %>
			<tr>
				<td class="til">
					CPU(个) 
				</td>
				<td>
					<s:if test="theForm.cpu==null">暂无</s:if>
					<s:else>
					<s:textfield name="theForm.cpu"></s:textfield>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til">
					内存(MB)
				</td>
				<td>
					<s:if test="theForm.mem==null">暂无</s:if>
					<s:else>
					<s:textfield name="theForm.mem"></s:textfield>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til">
					存储(GB)
				</td>
				<td>
					<s:if test="theForm.STORAGE==null">暂无</s:if>
					<s:else>
					<s:textfield name="theForm.STORAGE"></s:textfield>
					</s:else>
				</td>
			</tr>
			<%} else if(((String)request.getAttribute("oper")).equals("2")){%>
				<tr>
				<td class="til">
					要克隆的虚拟机个数
				</td>
				<td>
					<s:if test="theForm.CLONE_NUM==null">暂无</s:if>
					<s:else>
					<s:textfield name="theForm.CLONE_NUM"></s:textfield>
					</s:else>
				</td>
			</tr>
			<%} %>
			<tr id="iptr">	
			</tr>
			<tr id="porttr"></tr>
			<tr id="userNametr"></tr>
			<tr id="pwdtr"></tr>
			<s:if test="#request.oper==3">
				<tr>
					<td class="til">
						IP地址
					</td>
					<td>
						<s:textfield name="theForm.ip" cssClass="txt"  id="ip" value="172.21.1.104"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						端口
					</td>
					<td>
						<s:textfield name="theForm.port" value="22" cssClass="txt" id="port" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						用户名
					</td>
					<td>
						<s:textfield name="theForm.userName" id="userName" cssClass="txt" value="root"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						 密码
					</td>
					<td>
						<input type="password" name="theForm.password" value="${theForm.password}" class="txt"/>
					</td>
				</tr>
				</s:if>
			<s:if test="#request.oper==4">
				<s:iterator value="theForm.resultList" id="theBean">
					<tr>
						<td>shell脚本命令</td>
						<td><s:property value="#theBean.COMMAND"/></td>
					</tr>
				</s:iterator>
			</s:if>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
								id="confirm"
							onclick="javascript:realizeSolution();" style="display:none"/>		
						<input type="button" class="thickbox btn-style02" value="执行"
								id="exeshell"
							onclick="javascript:execute();" style="display:none"/>
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.close();" />
					</td>
			</tr>
			</table>
		</div>
		</div>
</s:form>
</body>