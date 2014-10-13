<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function resetForm(theForm){
		theForm.NAME.value ='';
		theForm.STATUS.value = '';
		theForm.DESCRIBTION.value='';
	}
    
    function searchRequest() {
		theForm.submit();
 	} 
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
	    	theForm.flag.value = 0;
	    	var sysid = theForm.SYSID.value;
    		$.dialog({
    			id:'add',
    			title:'新增版本信息',
    			width: '800px',
    			height: '200px',
    			max: true,
    		    min: true,
    			content: 'url:fileversion_addFileVersion.do?sysid='+sysid
    			});
              });
              
        $("[name='mod']").unbind().live("click",function(){
            	var couterNum = 0;
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      	couterNum = couterNum + 1 ; 
		 	      }
		 	    }
		 	    theForm.flag.value = 1;
		 	    }
		 	    if(couterNum==0){
			 	    alert("请勾选需要修改的信息！");
			 	    return false ;
		 	    }else if(couterNum>1){
			 	    alert("一次只能修改一条信息");
			 	    return false ;
		 	    }
		 	    theForm.ID.value=$("[name='checkboxid']:checked").attr("value");
		 	    
    		$.dialog({
    			id:'mod',
    			title:'修改版本信息',
    			width: '800px',
    			height: '200px',
    			max: true,
    		    min: true,
    			content: 'url:fileversion_modFileVersion.do?'+$("#theForm").serialize()
    			}); 
				
              });
              
           $("[name='del']").unbind().live("click",function(){
            	var couterNum = 0;
		 		var strIds = "";
		 		var fileDelFlag = 0;
		 		var locations = "";
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
			 	    for(var i=0;i<checkboxids.length;i++){
			 	      if(checkboxids[i].checked){
			 	      	couterNum = couterNum + 1 ;
			 	      	if (couterNum == 1) {
			 	      	  strIds = checkboxids[i].value;
			 	      	  locations = $(checkboxids[i]).attr("location");
			 	      	  
			 	      	} else {
			 	      		strIds += "','" + checkboxids[i].value;
			 	      		locations = ";" + $(checkboxids[i]).attr("location");
			 	      	} 
			 	      }
		 	    	}
		 	    } 
		 	    if(couterNum == 0){
		 	    	alert("请勾选需要删除的信息！");
		 	   	 	return false ;
		 	    } else {
		 	        if(confirm("您确定要删除么？")){ 
		 	            if(confirm("请确定是否彻底删除服务器上的版本文件？")) {
		 	               fileDelFlag = 0;
		 	            } else
		 	            {
		 	               fileDelFlag = 1;
		 	            }
		 	        }
		 	    } 
		 	    theForm.STRIDS.value = strIds;
				$.ajax({
					type:"GET",
					url:"fileversion_delFileVersion.do?"+ $("#theForm").serialize() +"&fileDelFlag=" + fileDelFlag +"&locations=" +locations,
					dataType:"json",
					success :function(data){
						if (data.result == 1)
						{
							alert("版本主机IP串配置信息有误，请确认！"); 
							return false;
						} else if (data.result == 2) {
							alert("插入版本信息历史表出错！"); 
							return false;
						} else if (data.result == 3) {
							alert("删除版本信息出错！"); 
							return false;
						} else {
							alert("删除成功！");
		                  	$("#theForm").submit();
						}
					}
				});
			});	
			
			$("[name='versioncheck']").unbind().live("click",function(){
            	 var couterNum = 0;
		 		 var locations = "";
		 	     var checkboxids = document.getElementsByName("checkboxid");
		 	     if(checkboxids!=null&&checkboxids.length>0){
			 	    for(var i=0;i<checkboxids.length;i++){
			 	      if(checkboxids[i].checked){
			 	      	couterNum = couterNum + 1 ;
			 	      	if (couterNum == 1) {
			 	      	  locations = $(checkboxids[i]).attr("location");
			 	      	} else {
			 	      		locations = ";" + $(checkboxids[i]).attr("location");
			 	      	} 
			 	      }
		 	    	}
		 	    } 
		 	    if(couterNum == 0){
		 	    	alert("请勾选需要检测的信息！");
		 	   	 	return false;
		 	    } else if (couterNum > 1) {
		 	        alert("一次只能勾选一条！");
		 	   	 	return false; 
		 	    }
		 	    $.ajax({
					type:"POST",
					url:"fileversion_checkFileVersion.do",
					data:{"locations":locations},
					dataType:"json",
					success :function(data){
						if (data.result == 1)
						{
							alert("版本不存在！"); 
							return false;
						} else {
							alert("版本存在！");
						}
					}
				});
          	});
          	
 			$("[name='versionhis']").unbind().live("click",function(){
				var sysid = $("#SYSID").val();
 				$.dialog({
	    			id:'versionhis',
	    			title:'版本历史信息',
	    			width: '1000px',
	    			height: '600px',
	    			max: true,
	    		    min: true,
	    			content: 'url:fileversion_queryFileVersionHisList.do?sysid='+sysid
    			});
          	});
		});
		
</script>
  <style type="text/css">
		div.hidden{
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
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.NAME.focus()">
<s:form action="fileversion_queryFileVersionList.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.STRIDS" id="STRIDS"></s:hidden>
	<s:hidden name="theForm.SYSID" id="SYSID"></s:hidden>
 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">文件版本管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                <label class="vm">版本包：</label>
						<s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
                    <label class="mgl-20 vm">版本状态:</label>
						<s:select cssClass="select-1 vm" id="STATUS" name="theForm.STATUS" list="#{'':'--请选择--','0':'未使用','1':'已使用','2':'版本错误'}"></s:select>
                    <label class="vm">版本描述：</label>
						<s:textfield name="theForm.DESCRIBTION" cssClass="txt" id="DESCRIBTION"></s:textfield>
                    <span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input id="resetForm" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
	
	<div class="utt-2 mgt-20">
				<a name="add" class="icon-add" href="javascript:void(0)" >新增</a>
				<a name="mod" class="icon-modify" href="javascript:void(0)">修改</a>
				<a name="del" class="icon-del" href="javascript:void(0)"  >删除</a>
				<a name="versioncheck" class="icon-check" href="javascript:void(0)" >版本监测</a>
				<a name="versionhis" class="icon-occupy" href="javascript:void(0)" >历史查询</a>
			</div>
		<div >
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">版本包 </th>
				   <th onclick="sort(theTable,2,'string')">所属应用</th>
				   <th onclick="sort(theTable,3,'float')">版本号</th>                
                   <th onclick="sort(theTable,4,'string')">版本状态</th>
                   <th onclick="sort(theTable,5,'string')">版本包路径</th>
                   <th onclick="sort(theTable,6,'string')">版本描述</th>
                   <th onclick="sort(theTable,7,'date')">创建时间</th>
                   <th onclick="sort(theTable,8,'string')">创建人</th>
                   <th onclick="sort(theTable,9,'date')">使用时间</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="theForm.fileVersionList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.ID'/>" 
					location='<s:property value="#theBean.LOCATION"/>' /></td>
					<td><s:property value="#theBean.NAME" /></td>
					<td><s:property value="#theBean.APPNAME" /></td>
					<td><s:property value="#theBean.NO" /></td>
					<td>
			  			<s:if test="#theBean.STATUS==0">
			  				未使用
			  			</s:if>
			  			<s:elseif test="#theBean.STATUS==1">
			  				已使用
			  			</s:elseif>
			  			<s:elseif test="#theBean.STATUS==2">
			  				版本错误
			  			</s:elseif>
			  		</td>
			  		<td width="170"> 
			  			<div class="hidden" title='<s:property value="#theBean.LOCATION"/>'>
			  				<s:property value="#theBean.LOCATION"/></a>
			  			</div> 
			  		</td>
			  		<td width="170"> 
			  			<div class="hidden" title='<s:property value="#theBean.DESCRIBTION"/>'>
			  				<s:property value="#theBean.DESCRIBTION"/></a>
			  			</div> 
			  		</td>
					<td><s:property value="#theBean.CREATED_TIME" /></td>
					<td><s:property value="#theBean.CREATED_USER" /></td> 
					<td><s:property value="#theBean.USED_TIME" /></td>
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
