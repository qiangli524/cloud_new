<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function resetForm(theForm){
		theForm.KEY.value = '';
		theForm.CFG_DESC.value = '';
		theForm.TYPE.value = '-1';
	}

    function searchRequest() {
		theForm.action = 'exportconfig_listExportConfig.do';
		theForm.submit();
 	}
 	
 	function list() {
		theForm.submit();
 	}
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
	        theForm.flag.value=0;
    		$.dialog({
    			id:'add',
    			title:'增加导出信息',
    			width: '800px',
    			height: '400px',
    			lock:true,
    			max: true,
    		    min: true,
    			content: 'url:exportconfig_addExportConfig.do'
    		});
        });
        
        $("[name='mod']").unbind().live("click",function(){ 
	        var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
			 	      couterNum = couterNum + 1 ;
			 	      theForm.ID.value = checkboxids[i].value;
		 	      }
		 	    } 
	 	    }
	 	    if(couterNum==0){
	 	    	alert("请勾选一条信息");
	 	    	return false ;
	 	    }else if(couterNum>1){
	 	    	 alert("一次只能修改一条信息");
	 	    	return false ;
	 	    }  
	 	    
	 	    theForm.flag.value=1;
    		$.dialog({
    			id:'mod',
    			title:'修改导出信息',
    			width: '800px',
    			height: '400px',
    			lock:true,
    			max: true,
    		    min: true,
    			content: 'url:exportconfig_modExportConfig.do?'+$("#theForm").serialize()
    		});
        });
        
    });
      
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      	couterNum = couterNum + 1 ;
 	      	theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选一条信息");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	alert("一次只能删除一条信息");
 	    	return false ;
 	    }
 	     if(confirm("确定要删除?")){
	 	    theForm.action = 'exportconfig_delExportConfig.do';
			theForm.submit();
		}
 	}
 	
 	function saveConfig(url){
 		$.ajax({
			  	type:"POST",
              	url:url,
			  	dataType: "json",
            	async: true,
            	cache: false,
	         	 success: function(data){
	          	  if(data!=-1){
		   				list();
					}else{
				}
            }
		});
 	}
</script>
</head>
  <style type="text/css">
		div.hidden{
		width:400px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
	div.hidden1{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<body onLoad="self.focus();document.theForm.KEY.focus()">
<div class="mainbody">
<s:form action="exportconfig_listExportConfig.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">导出配置管理</h2>
<%--			<div class="bord-1 pd-10">			--%>
<%--			<div class="clearfix mgt-10">--%>
<%--				<label class="vm">导出关键字：</label>--%>
<%--				<s:textfield name="theForm.KEY" cssClass="inpt-1 vm"--%>
<%--								id="KEY" maxlength="30"></s:textfield>--%>
<%--				<label class="mgl-20 vm">语句类型：</label>--%>
<%--				<s:select cssClass="select-1 vm" list="#{'-1':'--请选择--','1':'mysql','2':'oracle'}" --%>
<%--								name="theForm.STATUS" id="STATUS"></s:select>--%>
<%--				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>--%>
<%--				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>--%>
<%--			</div>--%>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" name = "add">新增</a>
				<a class="icon-modify" href="javascript:void(0)" value="修改" name = "mod" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
	
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">导出关键字</th>
				   <th onclick="sort(theTable,2,'string')">语句类型</th>
				   <th onclick="sort(theTable,3,'string')">导出语句</th>                
                   <th onclick="sort(theTable,4,'string')">说明</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td width="170"> 
			  			<div class="hidden1" title='<s:property value="#theBean.KEY"/>'>
			  				<s:property value="#theBean.KEY"/></a>
			  			</div> 
			  		</td>
			  		<td>
			  			<s:if test="#theBean.TYPE==1">
			  				mysql
			  			</s:if>
			  			<s:elseif test="#theBean.TYPE==2">
			  				oracle
			  			</s:elseif>
			  		</td>
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.VALUE"/>'>
			  				<s:property value="#theBean.VALUE"/></a>
			  			</div> 
			  		</td>
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.CFG_DESC"/>'>
			  				<s:property value="#theBean.CFG_DESC"/></a>
			  			</div> 
			  		</td> 
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
				<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		 </div>
</s:form>
</div>
</body>
