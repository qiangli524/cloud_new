<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<script type="text/javascript">
	function resetForm(){
		obj.PROJECT_NAME.value ='';
		obj.PROJECT_LEADER.value ='';
	}

    function searchRequest() { 
		obj.submit();
 	}
 	function list() { 
		obj.submit();
 	}
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
    		$.dialog({
    			id:'add',
    			title:'新增项目信息',
    			width: '600px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:departproject_addDepartProject.do'
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
		 	    }
		 	    if(couterNum==0){
			 	    alert("请勾选需要修改的信息！");
			 	    return false ;
		 	    }else if(couterNum>1){
			 	    alert("一次只能修改一条信息");
			 	    return false ;
		 	    }
		 	    var id = $("[name='checkboxid']:checked").attr("value");
	    		$.dialog({
	    			id:'mod',
	    			title:'修改项目信息',
	    			width: '600px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:departproject_modDepartProject.do?obj.ID='+id
	    			});
	     });
           
			$("[name='his']").unbind().live("click",function(){ 
    			$.dialog({
	    			id:'mod',
	    			title:'项目历史信息',
	    			width: '800px',
	    			height: '550px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:departproject_listDepartProjectHis.do'
    			});
             });
		 
		});  
		
 	function delRequest() { 
 		var couterNum = 0;
 		var strIds = "";
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
	 	    for(var i=0;i<checkboxids.length;i++){
	 	      if(checkboxids[i].checked){
	 	      	couterNum = couterNum + 1 ;
	 	      	if (couterNum == 1) {
	 	      	  strIds = checkboxids[i].value;
	 	      	} else {
	 	      		strIds += "','" + checkboxids[i].value;
	 	      	} 
	 	      }
 	    	}
 	    } 
 	    if(couterNum == 0){
 	    	alert("请勾选需要删除的信息！");
 	   	 	return false ;
 	    } else {
 	        if(confirm("您确定要删除么？")){ 
 	            obj.STRIDS.value = strIds; 
 	        	obj.action = 'departproject_delDepartProject.do'  
				obj.submit();
 	        }
 	    }
 	}
 	
 	function listExp(){  
	   /*  var name = obj.PROJECT_NAME.value;
	    var leader = obj.PROJECT_LEADER.value; */
	    exportForm.action ="departproject_exportProjectExcel.do?obj.depart_id=" + $("#departId").attr("value")+"&obj.PROJECT_LEADERNAME="+encodeURI(encodeURI($("#PROJECT_LEADER").attr("value")))+"&obj.PROJECT_NAME="+encodeURI(encodeURI($("#PROJECT_NAME").attr("value")));
	    exportForm.submit();
	}
	//添加虚拟机信息
	$(function(){
		  $("[name='addvm']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("project_id");
    		$.dialog({
    			id:'vm',
    			title:'虚拟机信息',
    			width: '750px',
    			height: '400px',
    			max: true,
    		    min: true,
    			lock:true,
    			content: 'url:departproject_addVM.do?vmHostObj.PROJECT_ID='+id
    			});
              });
	})
	//展示虚拟机列表
	$(function(){
		  $("[name='showvm']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("project_id");
    		$.dialog({
    			id:'vm',
    			title:'虚拟机信息',
    			width: '750px',
    			height: '400px',
    		    lock:true,
    			content: 'url:departproject_showVM.do?vmHostObj.PROJECT_ID='+id
    			});
              });
	})
		//删除虚拟机列表
	$(function(){
		  $("[name='delvm']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("project_id");
    		$.dialog({
    			id:'vm',
    			title:'虚拟机信息',
    			width: '750px',
    			height: '400px',
    		    lock:true,
    			content: 'url:departproject_delVM.do?vmHostObj.PROJECT_ID='+id
    			});
              });
	})
		//虚拟机资源使用率
	$(function(){
		/*
	      $("[name='down']").click(function(){
	      	var file_dir = '';//文件路径
	      	var file_name = '';//文件名
            var con = '';
            currentEdit=$(this);
            var path = currentEdit.attr("path");
			if(path.indexOf("\\")>0){
				con = "\\";
				arr = path.split("\\");
			}else{
				con = "/";
				arr = path.split("/");
			}
            for(var i =0 ;i<arr.length-1;i++){
            	file_dir = file_dir+arr[i]+con;
            }
            path = encodeURI(encodeURI($.trim(path)));
            file_name = encodeURI(encodeURI(arr[arr.length-1]));*/
			//location.href = "<%=request.getContextPath()%>/common/loadfile2.jsp?FILE_DIR=" + path + "&FILE_NAME=" + file_name;	
        //  });
	      
		$("[name='down']").unbind().live("click",function(){
			var proid = $(this).attr("proid");
			location.href = "departproject_downLoadBasisFile.do?proid="+proid;
		});
		
		  $("[name='usedvm']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("project_id");
    		$.dialog({
    			id:'vm',
    			title:'虚拟机资源分配率',
    			width: '375px',
    			height: '390px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:departproject_vmResourceRate.do?vmHostObj.PROJECT_ID='+id
    			});
              });
	})
	
	
	//插入到项目和虚拟机关系表中
	function selectAddVM(project_id,vhuuid,connID){
		   url='departproject_saveRelation.do?vmHostObj.VH_UUID='+vhuuid+'&vmHostObj.connectId='+connID+'&vmHostObj.PROJECT_ID='+project_id;
			$.ajax({
			      type: "get",
			      url: url,
				  async: false,
			      dataType: "json",
			      success : function(data){
						searchRequest();
				  }
			});
	}
	//删除关系表中的虚拟机
	function selectDelVM(project_id,vhuuid,connID){
		   url='departproject_delRelation.do?vmHostObj.VH_UUID='+vhuuid+'&vmHostObj.connectId='+connID+'&vmHostObj.PROJECT_ID='+project_id;
			$.ajax({
			      type: "get",
			      url: url,
				  async: false,
			      dataType: "json",
			      success : function(data){
						searchRequest();
				  }
			});
	}
	function showproject(str){
		   url='departproject_saveDepartProject.do?';
			$.ajax({
			      type: "post",
			      url: url,
				  data : str,
				  async: false,
			      dataType: "json",
			      success : function(data){
				  }
			});
			searchRequest();
	}
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
<body onLoad="self.focus();document.obj.PROJECT_NAME.focus()">
<div class="mainbody">
<s:form action="departproject_listDepartProject.do" id="obj" method="post" cssClass="obj">
	<s:hidden name="obj.ID" id="ID"></s:hidden>
	<s:hidden name="obj.flag" id="flag"></s:hidden>
	<s:hidden name="obj.DEPART_ID" id="departId"/>
	<s:hidden name="obj.STRIDS" id="STRIDS"></s:hidden>
	 <div class="pd-20 bgcolor-1">
	<h2 class="utt-1">项目预算管理</h2>
	<div class="bord-1 pd-10">			
	<div class="clearfix mgt-10">
		<label class="vm">项目名称：</label>		
		<s:textfield name="obj.PROJECT_NAME" cssClass="txt" id="PROJECT_NAME"></s:textfield>
		<label class="vm">项目负责人：</label>	
		<s:textfield name="obj.PROJECT_LEADERNAME" cssClass="txt" id="PROJECT_LEADER"></s:textfield>	
		<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
		<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>	
	</div>
	<div class="utt-2 mgt-20">
		<a class="icon-add" href="javascript:void(0)" name="add" >新增</a>
		<a class="icon-modify" href="javascript:void(0)" name="mod"  >修改</a>
		<a class="icon-del" href="javascript:void(0)" onclick = "delRequest();return false;" >删除</a>
		<a class="icon-release" href="javascript:void(0)" name="his" >历史查询</a>
		<a class="icon-export" href="javascript:void(0)" onclick="listExp();return false;" >导出</a>
	</div>	
	
	<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
		<thead>		
			<tr>
			   <th>选择</th>
			   <th onclick="sort(theTable,1,'string')">项目编号</th>
			   <th onclick="sort(theTable,2,'string')">项目名称</th>
			   <th onclick="sort(theTable,3,'string')">所属部门</th>
			    <th onclick="sort(theTable,4,'int')">预分配CPU</th>                
                  <th onclick="sort(theTable,5,'int')">预分配内存</th>
                  <th onclick="sort(theTable,6,'int')">预分配存储</th>
                  <th onclick="sort(theTable,7,'int')">预分配IP</th>
                  <th onclick="sort(theTable,8,'int')">分配率</th>
                  <th onclick="sort(theTable,9,'string')">项目负责人</th>
                  <th onclick="sort(theTable,12,'string')">虚拟机个数</th>
                  <th onclick="sort(theTable,13,'string')">分配资源</th>
                  <th>附件</th>
            </tr>
		</thead>
		<tbody>
			   <s:iterator value="resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.ID'/>"/></td>
					<td><s:property value="#theBean.PROJECT_NO" /></td>
					<td><s:property value="#theBean.PROJECT_NAME" /></td>
				    <td><s:property value="#theBean.dEPART_NAME" /></td>
					<td><s:property value="#theBean.CPU_COUNT" />核</td>
					<td><fmt:formatNumber value="${(theBean.MEMORY_SIZE) /1024}" pattern="#,###.##" type="number"/>G</td>
					<td><fmt:formatNumber value="${(theBean.STORAGE_SIZE) /1024/1024}" pattern="#,###.##" type="number"/>T</td>
					<td><s:property value="#theBean.IP_COUNT" /></td> 
					<td><a href="javascript:;" project_id='<s:property value="#theBean.ID"/>' name="usedvm">分配率</a></td>
					<td><s:property value="#theBean.PROJECT_LEADERNAME" /></td>
					<td>
					<s:if test="#theBean.vmCount!=0">
					<a href="javascript:;" project_id='<s:property value="#theBean.ID"/>' name="showvm">
						<s:property value="#theBean.vmCount" />
					</a>
					</s:if>
					<s:else>
						0
					</s:else>
					</td>
					<td>
						<a href="javascript:;" project_id='<s:property value="#theBean.ID"/>' name="addvm">分配</a>
						<a href="javascript:;"  project_id='<s:property value="#theBean.ID"/>' name="delvm">删除</a>
					</td>
					<td>
						<s:if test="#theBean.basis ==null || #theBean.basis =='' ">
							暂无
						</s:if>
						<s:else>
							<a href="javascript:;" proid='<s:property value="#theBean.ID"/>' name="down">
			  					下载
			  				</a>
						</s:else>
					</td>
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>
		</div>	
</s:form>
</div>
<s:form id="exportForm" method="post"  target="hidden_frame"></s:form>
<iframe id="hidden_frame" name="hidden_frame" style="display:none"></iframe>
</body>
