<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	var xmlHttp;
	//ajax
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
	function submitForm(command){
	str = "";
	j=0;
	var length =  document.getElementsByName("TYPE_IDS").length;
	
	if(command=="search"){
			if(document.theForm.SEARCH_KEYWORD.value==""){
				alert("请输入关键字!");
			}
			else{
				document.theForm.ISMP_JSP_COMMAND.value=command;
				document.theForm.submit();
				return true;
			}
	}
	else{	
		if (length == 0 ){
	  		alert("当前页中没有记录，不能进行修改，删除")
          		return;
    		}
		if(document.theForm.TYPE_IDS.length>0){
			for(i=0;i<document.theForm.TYPE_IDS.length;i++){
	 			if(document.theForm.TYPE_IDS[i].checked){
	 				str+=document.theForm.TYPE_IDS[i].value;
	 				j++;
	 			}
			}
		}
		else{
			if(document.theForm.TYPE_IDS.checked){
				str+=document.theForm.TYPE_IDS.value;
				j++;
			}
		}
		if(j==0){
			alert("请选择您需要删除或者修改的条目!");
		}
		else{
			if(command=="delete"){
				if(confirm("您确定要删除么？")){
					document.theForm.ISMP_JSP_COMMAND.value=command;
					theForm.action="feedback_delFeedBackType.do";
					theForm.submit();
					return true;
				}
			}
			else{
		
				if((j==1) && ((command=="modify"))){
					document.theForm.ISMP_JSP_COMMAND.value=command;
					theForm.action="feedback_editFeedBackType.do";
					theForm.submit();
					return true;
				}
				else{
					alert("一次只能修改一条记录!");
				}
			}
		}
	}
}
function addSub()
{
	document.theForm.ISMP_JSP_COMMAND.value = "add";
	 document.theForm.action = "feedback_editFeedBackType.do";
	 theForm.submit();
}

function selectall()
{

  

    
    document.frmSYS_MODULE.all.checked = document.frmSYS_MODULE.all.checked|0;

    if (length == 0 ){
          return;
    }
    if (length ==1 )
    {
       document.frmSYS_MODULE.MODULE_ID.checked=document.frmSYS_MODULE.all.checked ;
    }
   
    if (length>1)
    {
      for (var i = 0; i < length; i++)
       {
        document.frmSYS_MODULE.MODULE_ID[i].checked=document.frmSYS_MODULE.all.checked;         
       }
    }
}
	function search() {
		theForm.action="feedback_loadListFeedBackType.do";
		theForm.submit();
	}
	
	function resetForm(theForm){
	    theForm.TYPE_NAME.value= '';
	}
 	</script>
</head>
<body>
<s:form action="feedback_loadListFeedBackType.do" method="post" id="theForm">
  <s:hidden  name="theForm.ISMP_JSP_COMMAND" value="add" id="ISMP_JSP_COMMAND"/>
  <s:hidden name="theForm.TYPE_ID" id="TYPE_ID"></s:hidden>
  <input type="hidden"  name ="type" value=""/>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">文件名称:</td>
				    <td>
				        <s:textfield styleClass="button" name ="theForm.TYPE_NAME"  size="20" id="TYPE_NAME"/>
				    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:search()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addSub();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "submitForm('modify');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "submitForm('delete');return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input type="checkbox"  onclick="selectAll();"></th>
    			   <th onclick="sort(theTable,1,'string')">类型ID</th>
    			   <th onclick="sort(theTable,2,'string')">类型名称</th>
    			   <th onclick="sort(theTable,3,'string')">类型描述</th>
    			   <th onclick="sort(theTable,4,'string')">是否有效</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList" >
						<tr>
							<td><INPUT type=checkbox value=<s:property value="#theBean.TYPE_ID" /> name="theForm.TYPE_IDS" id="TYPE_IDS"></td>
							<td><s:text name="#theBean.TYPE_ID"/></td>
							<td><s:text name="#theBean.TYPE_NAME" /></td>
							<td><s:text name="#theBean.TYPE_DESC"/></td>
							<td>
								<s:if test="#theBean.ENABLE==1">有效</s:if>
								<s:if test="#theBean.ENABLE==0">无效</s:if>
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>