<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
	//查询
	function searchRequest() {
		obj.submit();
	}
	//重置
	function resetForm(){
		$("#serviceName").attr("value","");
		$("#description").attr("value","");
		$("#kpi_id").attr("value","");
	}
	//添加
   function addRequest() {
	    $.dialog({
			id:'add',
			title:'增加信息',
			height:'450px',
			width:'550px',
			lock:true,
			content:'url:relation_addServiceKpiRelation.do'
		});
	}
	//修改
   function modRequest(){
	   var lenth = 0;
	    var name = '';
	    $('[name=checkboxId]:checkbox:checked').each(function(){
	    	name +=$(this).val();
	   		lenth +=1;
	   	 });
	    if(name==null || name == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("一次只能修改一条信息");
	    	return false ;
	    }
	   var serviceName=$("input['name=checkboxId']:checked").val();
	   var kpiId=$("input['name=checkboxId']:checked").parent().attr("kpiId");
	   $.dialog({
			id:'update',
			title:'修改信息',
			height:'750px',
			width:'700px',
			lock:true,
			content:'url:relation_updServiceKpiRelation.do?obj.service_name='+serviceName+'&obj.kpi_id='+kpiId
		});
   }
	//删除
   function delRequest(){
	    var name = '';
		var lenth = 0;
		$('[name=checkboxId]:checkbox:checked').each(function(){
			name +=$(this).val();
	  		lenth +=1;
	   	});
   		if(name==null || name ==''){
   			alert("请勾选一条信息！");
			return false;
		}else if(lenth>1){
			alert('只能选择一项进行删除');
			return false;
		}
		var serviceName=$("input['name=checkboxId']:checked").val();
		var kpiId=$("input['name=checkboxId']:checked").parent().attr("kpiId");
	   		$.dialog.confirm('你确定要删除吗？', function(){
				var url = "relation_delServiceKpiRelation.do?obj.service_name="+serviceName+"&obj.kpi_id="+kpiId;
				$.ajax({
			  		type:"POST",
	          		url:url,
	          		async: false,
	          		cache: false,
	          		success: function(data){
	          			obj.submit();
	          		}
				});
	   		});
	}
	
	function saveServiceKpiRelation(serviceName,kpi_id){
		$.ajax({
            type:"POST",
            url: "relation_saveServiceKpiRelation.do?obj.service_name="+serviceName+"&kpi_ids="+kpi_id,
			async:false,
			cache:false,
            success : function(data){
				obj.submit();
              }
          });
	}
	
	function editServiceKpiRelation(serviceName,old_kpiId,new_kpiId){
		$.ajax({
            type:"POST",
            url: "relation_editServiceKpiRelation.do?obj.service_name="+serviceName+"&obj.old_kpiId="+old_kpiId+"&kpi_ids="+new_kpiId,
			async:false,
			cache:false,
            success : function(data){
				obj.submit();
              }
          });
	}
	
	
	function importServiceKpi(){
		$.dialog({
			id:'import',
			title:'批量导入',
			width: '380px',
			height: '200px',
		    lock:true,
			content: 'url:relation_importServiceKpi.do'
    		});
	}
</script>
</head>
<style type="text/css">
	div.hidden{
		width:250px;
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
<body class="pop-body scrollbody">
<s:form action="relation_queryServiceKpiRelationList.do" method="post" cssClass="obj" id="obj">
 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">服务与KPI关系</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                  <label class="vm">服务名称：</label>
						<s:textfield name="obj.service_name" id="serviceName" maxlength="100"></s:textfield>
                  <label class="vm">KPI名字：</label>
						<s:textfield name="obj.kpi_id" id="kpi_id" maxlength="100"></s:textfield>
                  <label class="vm">KPI描述：</label>
						<s:textfield name="obj.description" id="description" maxlength="100"></s:textfield>
             <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
		<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">增加</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
				<a class="icon-export" href="javascript:void(0)" onclick="importServiceKpi();return false;" >导入</a>
			</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">服务名称</th>
				   <th onclick="sort(theTable,2,'string')">KPI名称</th> 
				   <th onclick="sort(theTable,3,'string')">KPI描述</th>               
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td kpiId='<s:property value="#theBean.kpi_id"/>' width="5%">
			  			<input name="checkboxId" id="id" type="checkbox" value="<s:property value="#theBean.service_name"/>"/>
			  		</td>
			  		<td width="15%"><s:property value="#theBean.service_name"/></td>
			  		<td width="40%" align="center">
			  			<div class="hidden" title="<s:property value='#theBean.kpi_id'/>">
			  				<s:property value="#theBean.kpi_id"/>
			  			</div>
			  		</td>
			  		<td width="40%" align="center">
			  			<div class="hidden" title="<s:property value='#theBean.description'/>">
			  				<s:property value="#theBean.description"/>
			  			</div>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		</div>
    </div>
</div>
</s:form>
</body>
</html:html>