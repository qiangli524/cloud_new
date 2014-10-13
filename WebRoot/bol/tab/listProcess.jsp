<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
    <script type="text/javascript">
   $(function(){
	   $("#searchForm").click(function(){
		   $("#theForm").submit();
	   });
	   
	   $("#resetForm").click(function(){
		   $("#pname").val("");
		   $("#pstatus").val("-1");
	   });
	   
	   var host = $("#host").val();
	   $("[name='startProcess']").unbind().live("click",function(){
		   var pid = $(this).parent().attr("pid");
		   var pname = $(this).parent().attr("pname");
		   if (confirm("你确定要启动该线程吗？")) {
				$.ajax({
					type:'post',
					url:'boltask_dealBolTask.do?pid='+pid+'&pname='+pname+'&host='+host+'&op=0',
					success:function(msg){
						if (msg == -1) {
							alert("进程开启失败！");
						} else {
							alert("进程进入待开启状态，请稍后刷新当前页查看进程状态");
						}
					}
				});
		   }
	   });
	   $("[name='stopProcess']").unbind().live("click",function(){
		   var pid = $(this).parent().attr("pid");
		   var pname = $(this).parent().attr("pname");
		   if (confirm("你确定要停止该线程吗？")) {
			   $.ajax({
				    type:'post',
					url:'boltask_dealBolTask.do?pid='+pid+'&pname='+pname+'&host='+host+'&op=1',
					success:function(msg){
						if (msg == -1) {
							alert("进程停止失败！");
						} else {
							alert("进程进入待停止状态，请稍后刷新当前页查看进程状态");
						}
					}
				});
		   }
	   });
	   $("[name='recoveryProcess']").unbind().live("click",function(){
		   var pid = $(this).parent().attr("pid");
		   var pname = $(this).parent().attr("pname");
		   if (confirm("你确定要激活该线程吗？")) {
			   $.ajax({
				    type:'post',
					url:'boltask_dealBolTask.do?pid='+pid+'&pname='+pname+'&host='+host+'&op=3',
					success:function(msg){
						if (msg == -1) {
							alert("进程激活失败！");
						} else {
							alert("进程进入待激活状态，请稍后刷新当前页查看进程状态");
						}
					}
				});
		   }
	   });
	   $("[name='hangupProcess']").unbind().live("click",function(){
		   var pid = $(this).parent().attr("pid");
		   var pname = $(this).parent().attr("pname");
		   if (confirm("你确定要挂起该线程吗？")) {
			   $.ajax({
				    type:'post',
					url:'boltask_dealBolTask.do?pid='+pid+'&pname='+pname+'&host='+host+'&op=2',
					success:function(msg){
						if (msg == -1) {
							alert("进程挂起失败！");
						} else {
							alert("进程进入待挂起状态，请稍后刷新当前页查看进程状态");
						}
					}
				});
		   }
	   });
	   
	   $("[name='showResource']").unbind().live("click",function(){
		   var pid = $(this).parent().attr("pid");
		   var type = $(this).parent().attr("type");
		   $.dialog({
			    id:'showResource',
				title:'查看进程所占用资源',
				width: '1000px',
				height: '600px',
   		    	lock:true,
				content: 'url:bolresource_listResourceForProcess.do?pid='+pid+'&type='+type
		   });
	   });
	   
	   $("#refreshForm").click(function(){
		   $("#theForm").submit();
	   });
   });
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="bolresource_listProcess.do" method="post" id="theForm" cssStyle="theForm">
    <s:hidden name="host" id="host"></s:hidden>
    <s:hidden name="type" id="type"></s:hidden>
	<div class="box on">
		<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">进程名称:</td>
		                  		<td>
		                  			<s:textfield name="bolResourceObj.NAME" id="pname"></s:textfield> 
		                  		</td>
		                  		<td class="til">进程状态:</td>
		                  		<td>
		                  			<s:select list="#{'-1':'请选择','0':'就绪态','1':'运营态','2':'挂起态','3':'停止态','4':'故障态'}" name="bolResourceObj.STATUS" id="pstatus"></s:select>
		                  		</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
			</div>
      <div class="blue-wrap noborder">
      				<div class="table-head">
      					<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="刷新" id="refreshForm" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
       <div class="table-ct" >
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead id="table">
					<tr>
						<th onclick="sort(theTable,0,'string')">进程名称</th>
						<th onclick="sort(theTable,1,'string')">进程标识</th>
						<th onclick="sort(theTable,2,'string')">进程状态</th>
						<th onclick="sort(theTable,3,'string')">积压状态</th>
						<th onclick="sort(theTable,4,'string')">进程位置</th>
						<th onclick="sort(theTable,5,'string')">所属主机</th>
						<th onclick="sort(theTable,6,'string')">操作</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr >
                			<td pid='<s:property value="#theBean.PROG" />' type='<s:property value="#theBean.TYPE" />'>
                				<s:if test="#theBean.STATUS == 3 || #theBean.STATUS == 4">
		                			<s:property value="#theBean.NAME"></s:property>
                				</s:if>
                				<s:else>
	                				<a href="javascript:;" name="showResource">
		                				<s:property value="#theBean.NAME"></s:property>
	                				</a>
                				</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.ID"/>
                			</td>
                			<td>
                				<s:if test="#theBean.STATUS==0">
									<font color="blue">就绪态</font>
								</s:if>
								<s:elseif test="#theBean.STATUS==1">
									<font color="blue">运营态</font>
								</s:elseif>
								<s:elseif test="#theBean.STATUS==2">
									<font color="orange">挂起态</font>
								</s:elseif>
								<s:elseif test="#theBean.STATUS==3">
									<font color="red">停止态</font>
								</s:elseif>
								<s:elseif test="#theBean.STATUS==4">
									<font color="red">故障态</font>
								</s:elseif>
                			</td>
                			<td>
                				<s:if test="#theBean.BUSY == 0">
                					未积压
                				</s:if>
                				<s:elseif test="#theBean.BUSY == 1">
									<font color="orange">积压</font>
                				</s:elseif>
                				<s:else>
                					<font color="red">未知</font>
                				</s:else>
                			</td>
                			<td>
                				<s:property value="#theBean.LOC"/>
                			</td>
                			<td>
                				<s:property value="#theBean.HOST"/>
                			</td>
                			<td pid='<s:property value="#theBean.ID"/>' pname='<s:property value="#theBean.NAME"/>'>
                				<s:if test="#theBean.STATUS == 0">
                					<a href="javascript:;" name="startProcess">启动</a>
                				</s:if>
                				<s:elseif test="#theBean.STATUS == 1">
                					<a href="javascript:;" name="hangupProcess">挂起</a>
                					<a href="javascript:;" name="stopProcess">停止</a>
                				</s:elseif>
                				<s:elseif test="#theBean.STATUS == 2">
                					<a href="javascript:;" name="recoveryProcess">激活</a>
                					<a href="javascript:;" name="stopProcess">停止</a>
                				</s:elseif>
                				<s:elseif test="#theBean.STATUS == 3">
                					<a href="javascript:;" name="startProcess">启动</a>
                				</s:elseif>
                				<s:elseif test="#theBean.STATUS == 4">
                					<a href="javascript:;" name="stopProcess">停止</a>
                				</s:elseif>
                			</td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
       </div>
    </div>
    </s:form>
</body>