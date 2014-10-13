<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/link.jsp"%>
<%@ include file="/common/view.jsp"%>
<%@ page import="com.sitech.basd.workflow.web.resworkflow.form.ResourceWorkflowForm"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.VH_NAME.value = '';
		theForm.VH_STAT.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'addVirtualInfo.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.VH_ID_IBM.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条功能信息");
 	    return false ;
 	    }
 	    theForm.flag.value=1;
 	    theForm.action = 'modVirtualInfo.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.VH_ID_IBM.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'delVirtualInfo.do'  
		theForm.submit();
 	}
 	function adjustRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.VH_ID_IBM.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条功能信息");
 	    return false ;
 	    }
 	    theForm.flag.value=1;
 	    theForm.action = 'adjustVirtualInfo.do' 
		theForm.submit();
 	}
 	function moveRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.VH_ID_IBM.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条功能信息");
 	    return false ;
 	    }
 	    theForm.flag.value=1;
 	    theForm.action = 'moveVirtualInfo.do' 
		theForm.submit();
 	}
 	function addmemRequest(){
 		 var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.VH_ID_IBM.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条功能信息");
 	    return false ;
 	    }
 	    theForm.flag.value=1;
 		theForm.action = 'addMemInfo.do' 
		theForm.submit();
 	}
 	function StartAndStopVirtual(ID,PARAM) {
 	        if(PARAM=='OK'){
 	          if(confirm("确定要启动该虚拟机吗！")==true)
		      {
		       $.getJSON("StartAndStopVirtual.do?VH_ID_IBM="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       }); 
		      }
 	        }else{
 	          if(confirm("确定要停止该虚拟机吗！")==true)
		      {
		       $.getJSON("StartAndStopVirtual.do?VH_ID_IBM="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       });
		      }
 	        }
 	}
 	function emptyRequest(){
 	} 
 	function showDetials(needNum,taskId,processNode){ 
				var theForm = document.getElementById('theForm');
		   		theForm.action = 'dealResourceDeploy.do?needNum=' +needNum+'&taskId='+taskId+'&processNode='+processNode; 
		   		theForm.submit();
		   }
</script>
</head>
<body>
<html:form action="myResourceOrder" method="post" styleId="theForm">
 <bean:define id="theForm" name="resourceWorkflowForm"/>
 <html:hidden name="theForm" property="NEED_NUMBERS" />
 <html:hidden name="theForm" property="FLOW_TYPE" />
<div class="scrollbody">
	<div class="query">
	<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	              <!--<tr>
                    <td class="til">工单编号:</td>
                    <td>
						<html:text name="theForm" property="NEED_NUMBERS"  styleClass="txt" />
                    </td>
                    </tr>-->
		
	</div>
	<div class="box on">
         <div class="query-form">
               <table width="100%" class="querytable" border="0">
                 <tr>
                    <td class="til">虚拟机名称:</td>
                    <td>
						<html:text name="theForm" property="VH_NAME"  styleClass="txt" />
                    </td>
                    <td class="til">虚拟机状态:</td>
                    <td>
                        <html:select property="VH_STAT" name="theForm">
	                        <html:option value="">-请选择-</html:option>
	                        <html:option value="0">已尝试</html:option>
	                        <html:option value="2">未知</html:option>
	                        <html:option value="3">停止</html:option>
	                        <html:option value="4">确定</html:option>
	                        <html:option value="5">正在部署</html:option>
	                        <html:option value="6">已禁止</html:option>
	                        <html:option value="7">新建</html:option>
                        </html:select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
		    <!-- 
				<li><input type="button" class="thickbox btn-style02" value="创建" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="捕获" onclick = "emptyRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="停止" onclick = "emptyRequest();return false;" /></li>
			 -->
				<!--  <li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
				<li><input type="button" class="btn-style02-75" value="调整大小" onclick = "adjustRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02-75" value="移至项目" onclick = "moveRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02-75" value="添加存储器" onclick = "addmemRequest();return false;" /></li>-->
			</ul>
			<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">工单编号</th>
				   <th onclick="sort(theTable,1,'string')">虚拟机名称</th>
				   <th onclick="sort(theTable,2,'string')">虚拟机描述</th>                
                   <th onclick="sort(theTable,3,'string')">虚拟机状态</th>
                    <th>操作</th>
                    <th>查看虚拟机</th>
			  </tr>
			  </thead>
			  <tbody>
			   <logic:present name="theForm" property="tbVirtualList">
			      <logic:iterate id="theBean" name="theForm" property="tbVirtualList" >
						<tr>
							<td>
							<a href="javascript:showDetials('<bean:write name="theBean" property="NEED_NUMBERS"/>','<bean:write name="theBean" property="TASK_ID"/>','<bean:write name="theBean" property="PROCESS_NODE"/>')"><bean:write name="theBean" property="NEED_NUMBERS"/></a>
							</td>
							<td><bean:write name="theBean" property="VH_NAME"/></td>
							<td>
							<logic:notEmpty name="theBean" property="VH_DESC"><bean:write name="theBean" property="VH_DESC"/>
							</logic:notEmpty>
							<logic:empty name="theBean" property="VH_DESC">无</logic:empty>
							</td>
							<td>
							 
							<div id="divstartstop<bean:write name="theBean" property="VH_ID_IBM"/>">
							  <logic:equal name="theBean" property="VH_STAT" value="ATTEMPTED">已尝试</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="DRAFT">草稿</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="UNKNOWN">未知</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="STOPPED">停止</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="OK">确定</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="EXECUTING">正在部署</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="FAILED">失败</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="DELETING">正在删除</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="ERROR">错误</logic:equal>
							  <logic:equal name="theBean" property="VH_STAT" value="CANCELED">已放弃</logic:equal>
							   <logic:equal name="theBean" property="VH_STAT" value="BANNED">禁止</logic:equal>
							  </div>
							  
							</td>
							<td>
							<div id="div_an<bean:write name="theBean" property="VH_ID_IBM"/>">
								<logic:equal name="theBean" property="VH_STAT"
									value="STOPPED">
									<input type="button" class="thickbox btn-style02"
										value="启动"
										onclick="StartAndStopVirtual('<bean:write name="theBean" property="VH_ID_IBM"/>','OK');return false;" />
								</logic:equal>
								
								<logic:equal name="theBean" property="VH_STAT"
									value="OK">
									<input type="button" class="thickbox btn-style02"
										value="停止"
										onclick="StartAndStopVirtual('<bean:write name="theBean" property="VH_ID_IBM"/>','STOPPED');return false;" />
								</logic:equal>
								<!--<logic:equal name="theBean" property="VH_STAT"
									value="ERROR">
									<input type="button" class="thickbox btn-style02"
										value="删除"
										onclick="StartAndStopVirtual('<bean:write name="theBean" property="VH_ID_IBM"/>','0');return false;" />
								</logic:equal>
								--></div>
							</td>
							<td>
							<a href="/checkVirtualInfo.do?VH_ID=<bean:write name="theBean" property="VH_ID_IBM"/>">
							查看虚拟机
							</a>
							</td>
						</tr>
				</logic:iterate>
				</logic:present>		  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    <!--box end -->
    </div>
    </div>
</html:form>
<script>
 	
 //timer();
 //	function timer(){
  //      setStatus();
  //      setTimeout("timer()",10000);    
  //  }
   
    function setStatus(){
        var str="";
        var checkboxids = document.getElementsByName("checkboxid");
        if(checkboxids!=null&&checkboxids.length>0){
          for(var i=0;i<checkboxids.length;i++){
            if(i==checkboxids.length-1){
              str+=checkboxids[i].value.split("|")[0];
            }else{
              str+=checkboxids[i].value.split("|")[0]+',';
            }
          }
          $.getJSON("Virtual_Flag.do?VH_ID_IBM="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
		      $("#div_an"+data[j].VH_ID_IBM).html(data[j].START_STOP_FLAG_NAME);
			  $("#divstartstop"+data[j].VH_ID_IBM).html(data[j].VH_STAT);
			}
		   });          
        }
    }
</script>
</body>
</html:html>
