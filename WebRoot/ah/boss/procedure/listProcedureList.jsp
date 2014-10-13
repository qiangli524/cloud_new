<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<title></title>
	<style type="text/css">
		div.hidden{
		width:150px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
		}
</style>

<script type="text/javaScript">
$(function(){
 	$("#search").click(function(){
 	   $("#theForm").submit();
 	});
 	$("#resert").click(function(){
 	  	$("#cluster_id").attr("value","");
 	  	$("#app_pool").attr("value","");
 	  	$("#operator_id").attr("value","");
 	  	$("#host_ip").attr("value","");
 	});
    $("[name='addProcedure']").click(function(){
				$.dialog({
						id:'add',
						title:'添加',
						width: '650px',
						height: '390px',
					    lock:true,
						content: 'url:bossProcedure_addProcedure.do?oper=add'
			    	});					
    });
    $("[name='editProcedure']").click(function(){
    	var id = "";
    	var lenth = 0;
    	var status = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    		status += $(this).attr("status");
    	 });
    	 if(status == 2){
    		alert("该程序已卸载，无法修改！");
    		return false;
    	}
    	if(id==null || id ==''){
			alert('请先选择一项进行修改');
			return false;
		}else if(lenth>1){
			alert('只能选择一项进行修改');
			return false;
		}
    	$.dialog({
			id:'edit',
			title:'修改任务',
			width: '600px',
			height: '400px',
			cache:false,
			max: true,
		    min: true,
		    lock:true,
			content: 'url:bossProcedure_addProcedure.do?oper=edit'+'&obj.uid='+id
		});
     });
    $("[name='unload']").click(function(){
    	var id = "";
    	var lenth = 0;
    	var status = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    		status += $(this).attr("status");
    	 });
    	 if(status == 2){
    		alert("该程序已卸载！");
    		return false;
    	}
    	 if(lenth==0){
	 	    alert("请先选择一项进行卸载!");
	 	    return false ;
	 	   } else if(lenth>1){
    		alert("请选择一项卸载");
    		return false;
    	}
	     $.ajax({
			type: "GET",
			url: "bossProcedure_unloadProcedure.do?obj.uid="+id,
			dataType: "json",
			success : function(data){
				if(data == "-1"){
					alert("卸载失败！");
				}else{
					alert("卸载成功！");
					$("#theForm").submit();
				}
		 }
		});  	
    });
});
	//保存
	function saveProcedure(theForm){
     	 $.ajax({
            type: "GET",
            url: "bossProcedure_saveProcedure.do?"+theForm,
            dataType: "json",
			cache:false,
            success : function(data){
				if(data == "-1"){
					alert("保存失败！");
				}else{
					$("#theForm").submit();
				}
            }
          });
     }
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="bossProcedure_queryProcedureList.do" method="post" cssClass="theForm" id="theForm" name="theForm">
        <div class="pd-20 bgcolor-1">
        	<h2 class="utt-1">进程注册</h2>  
            <div class="bord-1 pd-20">
	            <label class="vm">集群名称</label>
							<s:select cssStyle="width:120px" cssClass="select-1 vm" list="obj.clusterList" id="cluster_id" headerKey="" headerValue="---请选择---" 
								listKey="nodeId" listValue="nodeName" name="obj.cluster_id" value="obj.cluster_id">
							</s:select>
				<label class="mgl-10 vm">程序池</label>
							<s:select cssStyle="width:120px" cssClass="select-1 vm" list="obj.poolList" id="app_pool" headerKey="" headerValue="---请选择---" 
								listKey="nodeId" listValue="nodeName" name="obj.app_pool" value="obj.app_pool">
							</s:select>
				<label class="mgl-10 vm">IP</label>
							<s:textfield cssStyle="width:120px" name="obj.host_ip" id="host_ip"></s:textfield>
				<label class="mgl-10 vm">启停标识</label>
							<s:select cssStyle="width:120px" cssClass="select-1 vm" list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"
									name="obj.operator_id" id="operator_id"></s:select>
				<label class="mgl-10 vm">安装状态</label>
							<s:select cssStyle="width:120px" cssClass="select-1 vm" list="#{'':'--请选择--','1':'安装完成','2':'已卸载'}"
									name="obj.install_status" id="install_status"></s:select>
	            <span class="ubtn-1 mgl-10"><input type="button" id="search" value="查询" /></span>
				<span class="ubtn-2 mgl-10"><input type="button" id = "resert" value="重置" /></span>      
			<div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" name="addProcedure" id="addProcedure">新增</a>
                    <a class="icon-modify" href="javascript:void(0)" name="editProcedure" id="editProcedure">修改</a>
                    <a class="icon-del" href="javascript:void(0)" name="unload">卸载</a>
            </div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th>集群</th>
				   <th>程序池</th>
				   <th>部署主机</th>
				   <th>进程标识</th>
				   <th>安装状态</th>
				   <th>备注</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="bossProcedureObjList" id="theBean">
			  	<tr>
			  	 	<td>
						<input type="checkbox" name="checkboxid" value='<s:property value="#theBean.uid"/>' pid='<s:property value="#theBean.process_id"/>'
							status='<s:property value="#theBean.install_status"/>'/>
					</td>
					<td>
				  		<s:property value="#theBean.nodename"/>
			  		</td>
			  		<td>
				  		<s:property value="#theBean.app_pool"/>
			  		</td>
			  		<td><s:property value="#theBean.host_ip"/></td>
			  		<td><s:property value="#theBean.program_name"/></td>
			  		<td>
			  			<s:if test="#theBean.install_status==1">完成</s:if>
				  		<s:elseif test="#theBean.install_status==2">
				  			<span style="color: red">已卸载<span/>
				  		</s:elseif>
			  		</td>
			  		<td align="center">
			  			<div style="padding:auto;" class="hidden" title='<s:property value="#theBean.note"/>' >
					  		<s:property value="#theBean.note"/>
					  	</div>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
                    <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm"/>
            </div>
            </div>
	</div>
</s:form>
</body>
</html:html>	
