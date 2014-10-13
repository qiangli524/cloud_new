<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">

	function resetForm(theForm){
		theForm.VH_NAME.value = '';
		theForm.VH_STAT.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'virtual_addVirtualInfo.do' 
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
 	    theForm.action = 'virtual_modVirtualInfo.do' 
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
 	    if(confirm("确定要删除该虚拟机吗?")==true){
 	    	theForm.action = 'virtual_delVirtualInfo.do'  
			theForm.submit();
 	    }
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
 	    theForm.action = 'virtual_adjustVirtualInfo.do' 
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
 	    theForm.action = 'virtual_moveVirtualInfo.do' 
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
 		theForm.action = 'virtual_addMemInfo.do' 
		theForm.submit();
 	}
 	function StartAndStopVirtual(ID,PARAM) {
 	        if(PARAM=='OK'){
 	          if(confirm("确定要启动该虚拟机吗！")==true)
		      {
		       $.getJSON("virtual_StartAndStopVirtual.do?VH_ID_IBM="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       }); 
		      }
 	        }else{
 	          if(confirm("确定要停止该虚拟机吗！")==true)
		      {
		       $.getJSON("virtual_StartAndStopVirtual.do?VH_ID_IBM="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       });
		      }
 	        }
 	}
 	 function synchVirtualInfo(VH_ID_IBM){
  	
  		theForm.VH_ID_IBM.value=VH_ID_IBM;
  			var url = "virtual_checkVirtualIfSynch.do?id="+VH_ID_IBM+"&Date"+(new Date());
  			$.getJSON(url,function(data){
  				if(data=="NO"){
  					alert("虚拟机数据已经同步");
  				}else if(data=="YES"){
  					theForm.action = "virtual_synchVirtualInfo.do?id="+VH_ID_IBM; 
	  				theForm.submit();
  				}
  			}
  		)
    
  }
 	function emptyRequest(){
 	} 
</script>
</head>
<body>
<s:form action="virtual_listVirtualInfo.do" method="post" id="theForm">
<s:hidden name="theForm.VH_ID" id="VH_ID"></s:hidden>
<s:hidden name="theForm.VH_ID_IBM" id="VH_ID_IBM"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">虚拟机名称:</td>
                    <td>
                    	<s:textfield name="theForm.VH_NAME" id="VH_NAME" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">虚拟机状态:</td>
                    <td>
                    	<s:select list="#{'ATTEMPTED':'已尝试','DRAFT':'草稿','UNKNOWN':'未知','STOPPED':'停止','OK':'确定','EXECUTING':'正在部署','FAILED':'失败','DELETING':'正在删除','BANNED':'已禁止','ERROR':'错误','CANCELED':'已放弃'}" name="theForm.VH_STAT" id="VH_STAT" headerKey="" headerValue="-请选择-"></s:select>
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
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
				<li><input type="button" class="btn-style02-75" value="调整大小" onclick = "adjustRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02-75" value="移至项目" onclick = "moveRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02-75" value="添加存储器" onclick = "addmemRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">虚拟机名称</th>
				   <th onclick="sort(theTable,2,'string')">虚拟机描述</th>                
                   <th onclick="sort(theTable,3,'string')">虚拟机状态</th>
                   <th>操作</th>         
                   <th>查看虚拟机</th>
                   <%--  
                   <th>监控同步</th>--%>
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.VH_ID_IBM'/>"/></td>
							<td><s:property value='#theBean.VH_NAME'/></td>
							
							<td>
							<s:if test="#theBean.VH_DESC==null">无</s:if>
							<s:else>
							<s:property value='#theBean.VH_DESC'/>
							</s:else>
							</td>
							<td>
							<div id="divstartstop<s:property value='#theBean.VH_ID_IBM'/>">
							<s:if test="#theBean.VH_STAT=='ATTEMPTED'">已尝试</s:if>
							<s:if test="#theBean.VH_STAT=='DRAFT'">草稿</s:if>
							<s:if test="#theBean.VH_STAT=='UNKNOWN'">未知</s:if>
							<s:if test="#theBean.VH_STAT=='STOPPED'">停止</s:if>
							<s:if test="#theBean.VH_STAT=='OK'">确定</s:if>
							<s:if test="#theBean.VH_STAT=='EXECUTING'">正在部署</s:if>
							<s:if test="#theBean.VH_STAT=='FAILED'">失败</s:if>
							<s:if test="#theBean.VH_STAT=='DELETING'">正在删除</s:if>
							<s:if test="#theBean.VH_STAT=='ERROR'">错误</s:if>
							<s:if test="#theBean.VH_STAT=='CANCELED'">已放弃</s:if>
							<s:if test="#theBean.VH_STAT=='BANNED'">禁止</s:if>
							  </div>
							</td>
							
							<td>
							<div id="div_an<s:property value='#theBean.VH_ID_IBM'/>">
								<s:if test="#theBean.VH_STAT=='STOPPED'">
									<input type="button" class="thickbox btn-style02"
										value="启动"
										onclick="StartAndStopVirtual('<s:property value='#theBean.VH_ID_IBM'/>','OK');return false;" />
								</s:if>
								<s:if test="#theBean.VH_STAT=='OK'">
									<input type="button" class="thickbox btn-style02"
										value="停止"
										onclick="StartAndStopVirtual('<s:property value='#theBean.VH_ID_IBM'/>','STOPPED');return false;" />
								</s:if>
								</div>
							</td>
		
							<td>
							<a href="virtual_checkVirtualInfo.do?VH_ID=<s:property value='#theBean.VH_ID_IBM'/>">
							查看虚拟机
							</a>
							</td>
							<%--
							<td>
							<a href="javascript:synchVirtualInfo('<s:property value='#theBean.VH_ID_IBM'/>')">
							监控同步
							</a>
							--%>
						</tr>
				</s:iterator>	  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
<script>
 	timer();
 	function timer(){
        setStatus();
        setTimeout("timer()",10000);    
    }
   
    function setStatus(){
        var str="";
        var checkboxids = document.getElementsByName("checkboxid");
        if(checkboxids!=null&&checkboxids.length>0){
          for(var i=0;i<checkboxids.length;i++){
          	if (checkboxids[i].value.split("|")[0] == '') {
          		continue;
          	}
            if(i==checkboxids.length-1){
              str+=checkboxids[i].value.split("|")[0];
            }else{
              str+=checkboxids[i].value.split("|")[0]+',';
            }
          }
          $.getJSON("virtual_Virtual_Flag.do?VH_ID_IBM="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
		      $("#div_an"+data[j].VH_ID_IBM).html(data[j].START_STOP_FLAG_NAME);
			  $("#divstartstop"+data[j].VH_ID_IBM).html(data[j].VH_STAT);
			}
		   });          
        }
    }
</script>
</body>
