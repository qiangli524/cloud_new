<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../sxcloud/common/link.jsp"%>
<%@ include file="../sxcloud/common/taglib.jsp"%>
<%@ include file="../sxcloud/common/view.jsp"%>
<%
String excel_export_jsp = request.getContextPath() + "/excel/excel_export.jsp";
%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	//查询
	function searchRequest() {
		obj.submit();
	}
	//重置
	function resetForm(){
		$("#alarm_kpi").attr("value","");
		$("#alarm_level").attr("value","");
	}
	//添加
   function addRequest() {
	    $.dialog({
			id:'add',
			title:'添加指标',
			height:'380px',
			width:'550px',
			lock:true,
			content:'url:alarm/editAlarmConfig.jsp'
		});
	}
	//修改
   function modRequest(){
	    var lenth = 0;
	    var id = '';
	    $('[name=checkboxId]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
	    if(id==null || id == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("一次只能修改一条信息");
	    return false ;
	    }
	   $.dialog({
			id:'update',
			title:'修改信息',
			lock:true,
			height:'380px',
			width:'550px',
			content:'url:alarmAction_editAlarmThreshold.do?obj.id='+id
		});
   }
	//删除,支持批量
   function delRequest(){
		var ids='';
		var lenth=0;
		$('[name=checkboxId]:checkbox:checked').each(function(){
	   		ids =ids+$(this).val()+",";
	   		lenth +=1;
	   	 });
	   		if(ids == null || ids ==""){
	   			alert("请勾选一条信息！");
				return false;
			}
	   		$.dialog.confirm('你确定要删除吗？', function(){
				var url = "alarmAction_delete.do?obj.id="+ids;
				$.ajax({
			  		type:"POST",
	          		url:url,
	          		cache:false,
	          		success: function(msg){
	          			searchRequest();	
	          		}
				});
	   		});
	}
	/** 添加或修改后调用该页面的保存方法  **/
	function saveAlarmThreshold(obj){
		$.ajax({
            type: "GET",
            url: "alarmAction_saveAlarmThreshold.do?"+obj,
            dataType: "json",
			cache:false,
            success : function(data){
	            $("#obj").submit();
            }
          });
	}
	function selAll(a){
		if(a.checked){
			$("input[name='checkboxId']").each(function(){
			$(this).attr("checked",true);
			});
		}else{
			$("input[name='checkboxId']").each(function(){
			$(this).attr("checked",false);
			});
		}
	}
	//导出
<%-- 	$(function(){
		$("#export").click(function(){
	  		var url="<%=excel_export_jsp%>?key=alarm_deploy";
	  		exportForm.action =url;
	  	  	$("#exportForm").submit();
    	});
	}) --%>
		
	
</script>
<style type="text/css">
	.divmatnrdesc{
		width: 100px;
		height:100%;
		overflow: hidden;
		white-space: nowrap;
		text-overflow:ellipsis;
		}
		
</style>
</head>
<body>
<s:form action="alarmAction_listAlarmThreshold.do" method="post" cssClass="obj" id="obj">
<s:hidden name="obj.id" id="id"></s:hidden>
<div class="pop-body scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">指标名称:</td>
                    <td>
						<s:textfield name="obj.alarm_kpi" id="alarm_kpi" ></s:textfield>
                    </td>
                    <td class="til">指标级别:</td>
                   	<td>
                   		<select name="obj.alarm_level" id="alarm_level" >
                   			<option value="">--请选择--</option>
                   			<option value="0">严重告警</option>
                   			<option value="1">主要告警</option>
                   			<option value="2">次要告警</option>
                   			<option value="3">不确定告警</option>
                   		</select>
                   	</td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm()" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div>
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest()" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest()" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest()" /></li>
				<!--  <li><input type="button" class="thickbox btn-style02" value="导出" id="export" /></li>-->
			</ul>
			<jsp:include page="../sxcloud/inc/Pagination.jsp?formId=obj" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>全选 <input type="checkbox" onclick="selAll(this);"/></th>
				   <th>告警指标</th>
				   <th>指标名称</th>
				   <th>指标描述</th>
                   <th>告警实体类型</th>
                   <th>判断符号</th>
                   <th>告警阀值</th>
                   <th>告警级别</th>
                   <th>不可告警时间开始值</th>
                   <th>不可告警时间结束值</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxId" id="id" type="checkbox" value="<s:property value='#theBean.id'/>"/></td>
			  		<td ><s:property value="#theBean.alarm_kpi"/></td>
			  		<td><s:property value="#theBean.alarm_name"/></td>
			  		<td align="center">
			  			<div class="divmatnrdesc" title="<s:property value='#theBean.alarm_desc'/>">
			  				<s:property value="#theBean.alarm_desc"/>
			  			</div>
			  		</td>
			  		<td >
				  		<s:if test="#theBean.alarm_type == 6">
				  			Hadoop告警
				  		</s:if>
				  		<s:elseif test="#theBean.alarm_type == 1">
							虚拟机告警
	  					</s:elseif>
				  		<s:elseif test="#theBean.alarm_type == 2">
							物理主机告警
	  					</s:elseif>
	  					<s:else>
				  			—
				  		</s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.alarm_trigger == 0">
				  			小于
				  		</s:if>
				  		<s:elseif test="#theBean.alarm_trigger == 1">
							等于
	  					</s:elseif>
				  		<s:elseif test="#theBean.alarm_trigger == 2">
							大于
	  					</s:elseif>
	  					<s:else>
				  			—
				  		</s:else>
	  				</td>
	  				<td>
			  			<s:property value="#theBean.alarm_threshold"/>
			  			<s:if test="#theBean.alarm_unit == 0">
				  			%
				  		</s:if>
				  		<s:elseif test="#theBean.alarm_unit == 1">
							次
	  					</s:elseif>
				  		<s:elseif test="#theBean.alarm_unit == 2">
							小时
	  					</s:elseif>
	  					<s:elseif test="#theBean.alarm_unit == 3">
							分钟
	  					</s:elseif>
	  					<s:elseif test="#theBean.alarm_unit == 4">
							秒
	  					</s:elseif>
	  					<s:elseif test="#theBean.alarm_unit == 5">
							天
	  					</s:elseif>
	  					
			  		</td>
			  		<td >
			  			<s:if test="#theBean.alarm_level == 0">
			  				<font color="red">严重告警</>
			  			</s:if>
			  			<s:elseif test="#theBean.alarm_level == 1">
							主要告警
  						</s:elseif>
  						<s:elseif test="#theBean.alarm_level == 2">
							次要告警
  						</s:elseif>
  						<s:elseif test="#theBean.alarm_level == 3">
							不确定告警
  						</s:elseif>
  						<s:else>
			  				—
			  			</s:else>
			  		</td>
			  		<td width="10px;"><s:property value="#theBean.start_time"/></td>
			  		<td width="10px;"><s:property value="#theBean.end_time"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
    </div>
</div>
</s:form>
<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
</html:html>