<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<title></title>
 <style type="text/css">
		div.hidden{
		width:200px;
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
<script type="text/javascript">
	function searchRequest(){
		theForm.submit();
	}
	$(function(){
			//单选
			$(function(){
			    $(":checkbox").click(function(){
			          $(":checked").not(this).attr("checked",false);
			    });
			});
			$("#execute").click(function(){
				var id='';
     			var lenth=0;
				$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 	});
	        	 if(id==null || id ==''){
					alert('请先选择一项执行');
					return false;
				 }else if(lenth>1){
					alert('只能选择一项执行');
					return false;
				}
				mask('正在执行脚本 ,请稍后....','0.5','0px');
				$.ajax({
			         type: "get",
			         url: "script_execute.do?id="+id+"&data="+new Date(),
			         dataType: "json",
					 cache:false,
			         success : function(data){
			 			removeMask();
			 			//alert(data.result);
			 			//将结果放在该页面，供弹出框读取显示
			 			$("#resultMessage").attr("value",data.result);
						$.dialog({
			    			id:'alert',
			    			title:'执行结果',
			    			width: '600px',
			    			height: '350px',
			    			cache:false,
			    			max: true,
			    		    min: true,
			    			content: 'url:script_alert.do'
			    			});
		        	 }
				});
			});
			$("#query").click(function(){
	    			if($("#query-form").is(":visible")){
	    				$("#query-form").slideUp("slow");
	    			}else{
	    				$("#query-form").slideDown("slow");
	    			}
	    	});
	    	$("#reset").click(function(){
	    			clear();
	    	});
	    	$("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加脚本信息',
    			width: '500px',
    			height: '350px',
    			max: true,
    		    min: true,
    			content: 'url:script_edit.do'
    			});
              });
              
            $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id='';
     		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行修改');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行修改');
				return false;
			}
    		$.dialog({
    			id:'vdi',
    			title:'修改脚本信息',
    			width: '600px',
    			height: '350px',
    			max: true,
    		    min: true,
    			content: 'url:script_edit.do?id='+id
    			});
              });
     });
     function clear(){
     	$("#name").attr("value","");
     	$("#type").attr("value","-1");
     	$("#app_type").attr("value","");
     }  
     function deleteScript(){
     	var id='';
     	var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			if(confirm('确定删除?')){
				theForm.action="script_delete.do?id="+id;
				theForm.submit();
			}
	}   
	function getResult(){
		var result = $("#resultMessage").val();
		alert(result);
		return result;
	}
</script>
</head>
<body >
<div class="mainbody">
<s:form action="script_list.do" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
		<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
		<s:hidden name="theForm.IPADDRESS" id="IPADDRESS"></s:hidden>
		<s:hidden id="resultMessage"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">脚本管理</h2>
				<div class="bord-1 pd-20">
					<div class="clearfix mgt-10">
						<label class="vm">脚本名称：</label>
							<s:textfield name="theForm.name"  id="name"></s:textfield>
						<label class="mgl-20 vm">应用类型：</label>
							<s:select cssClass="select-1 vm"  list="#{'':'--请选择--','0':'Boss云化'}" name="theForm.app_type" id="app_type" ></s:select>
						<label class="mgl-20 vm">操作类型：</label>
							<s:select cssClass="select-1 vm" list="#{'0':'通用','1':'启用','2':'停止','3':'备份','4':'其他'}" name="theForm.type" id="type" headerKey="-1" headerValue="--请选择--"></s:select>
						<span class="ubtn-1 mgl-20"><input type="button"
								value = "查询" onclick = "javascript:searchRequest()" />
						<span class="ubtn-2 mgl-20"><input type="button"
								value="重置" id="reset" />
					</div>
					<div class="utt-2 mgt-20">
							<a class="icon-add" href="javascript:void(0)"
								value="增加" name="add" >增加</a>
							<a class="icon-modify" href="javascript:void(0)"
								value="修改" name="mod">修改</a> 
							<a class="icon-del" href="javascript:void(0)" 
								value="删除" onclick = "javascript:deleteScript();">删除</a>
							<a class="icon-set" href="javascript:void(0)" 
								value="执行" id="execute">执行</a>
					</div>
					<table id="theTable" width="100%" class="blue-table sorttable "
						border="0" cellspacing="0">
						<thead>
							<tr>
									<th>选择</th>
									<th onclick="sort(theTable,1,'string')">脚本名称</th>
									<th onclick="sort(theTable,2,'string')">主机/用户</th>
									<th onclick="sort(theTable,3,'string')">类型</th>
									<th onclick="sort(theTable,4,'string')">脚本路径</th>
									<th onclick="sort(theTable,5,'string')">参数</th>
									<th onclick="sort(theTable,6,'string')">级别</th>
									<th onclick="sort(theTable,7,'int')">执行次数</th>
									<th onclick="sort(theTable,8,'string')">添加人</th>
									<th onclick="sort(theTable,9,'string')">修改人</th>
									<!--  
									<th>首次执行时间</th>
									<th>最后执行时间</th>
									<th>建立时间</th>
									<th>修改时间</th>
									-->
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean" >
								<tr>
					  				<td> <input type="checkbox"   value="<s:property value='#theBean.id'/>" name="theForm.id"/> </td>
					  				<td> <s:property value="#theBean.name"/></td>
					  				<td> <s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
					  				<td>
					  					<s:if test="#theBean.type==0">通用</s:if>
					  					<s:elseif test="#theBean.type==1">启动</s:elseif>
					  					<s:elseif test="#theBean.type==2">停止</s:elseif>
					  					<s:elseif test="#theBean.type==3">备份</s:elseif>
					  					<s:else>其他</s:else>
					  				</td>
					  				<td align="center"> 
					  					<div style="padding:auto;"  class="hidden" title='<s:property value="#theBean.path"/>'>
		                				<s:property value="#theBean.path"/></div> 
		                			</td>
		                			<td align="center"> 
		                				<div style="padding:auto;"  class="hidden" title='<s:property value="#theBean.params"/>'>
		                					<s:property value="#theBean.params"/>
		                				</div> 
		                			</td>
		                			<td> <s:property value="#theBean.grade"/> </td>
					  				<td> <s:property value="#theBean.count"/> </td>
					  				<td> <s:property value="#theBean.upload_person"/> </td>
					  				<td> <s:property value="#theBean.update_person"/> </td>
				  				</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10"><!-- 分页 -->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
			</div>
</s:form>
</div>
</body>
