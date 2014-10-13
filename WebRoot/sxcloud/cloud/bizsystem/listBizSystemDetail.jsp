<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	var xmlHttp; 
	var check;
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	function getSelect() {
    	createXmlHttp();
    	var APP_TYPE=document.getElementById("APP_TYPE").value;
    	xmlHttp.open("GET", "ajax_getAppIp.do?APP_TYPE="+APP_TYPE, false);     
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
    	if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.getElementById("VH_ID");
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(thisForm){
		
		if(thisForm.SYS_NAME.value.length == 0){
			alert("请输入应用名称！");
			return false;
		}
	    thisForm.submit();
	}
	
	
	 $(function(){
		 $("[name='app_list']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var SYS_ID = $(this).attr("SYS_ID");
    		$.dialog({
    			id:'app',
    			title:'基准应用',
    			width: '650px',
    			height: '330px',
    			max: true,
    		    min: true,
    			content: 'url:bizsystem_getHostByBusi.do?SYS_ID='+SYS_ID+'&app='+1
    			});
              });
              
              
            $("[name='example_list']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var SYS_ID = $(this).attr("SYS_ID");
    		$.dialog({
    			id:'example',
    			title:'部署实例',
    			width: '1000px',
    			height: '500px',
    			max: true,
    		    min: true,
    			content: 'url:dep_listDeployExample.do'
    			});
              });
           });
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="bizsystem_saveBizSystem.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
			<table id="theTable" width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td onclick="sort(theTable,0,'int')" class="til">
						基准应用个数 <font color="red"></font>
					</td>
					<td>
						<s:if test="theForm.app_num!=0">
					   <a href="javascript:;" name="app_list" SYS_ID="<s:property value="theForm.SYS_ID"/>"><s:property value="theForm.app_num" /></a>               
						</s:if>
						<s:else>0</s:else>
					</td>
				</tr>
				<tr>
					<td onclick="sort(theTable,1,'int')" class="til">
						部署实例个数<font color="red"></font>
					</td>
					<td>
						<s:if test="theForm.example_num!=0">
						<a href="javascript:;" name="example_list" SYS_ID="<s:property value="theForm.SYS_ID"/>"><s:property value="theForm.example_num"/></a>
						</s:if>
						<s:else>
							0
						</s:else>
					</td>
				</tr>
				
			</table>
</s:form>
</body>
