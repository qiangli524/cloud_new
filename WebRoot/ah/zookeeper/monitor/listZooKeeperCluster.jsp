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
    $("[name='registerZKCluster']").click(function(){
				$.dialog({
						id:'register',
						title:'注册',
						width: '600px',
						height: '400px',
					    lock:true,
						content: 'url:taoKeeperMonitor_zooKeeperRegisterPAGE.do?oper=register'
			    	});					
    });
    $("[name='editZKCluster']").click(function(){
    	var id = "";
    	var lenth = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
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
			id:'edit',
			title:'修改',
			width: '600px',
			height: '400px',
			cache:false,
			max: true,
		    min: true,
		    lock:true,
			content: 'url:taoKeeperMonitor_zooKeeperSettingsPAGE.do?oper=update'+"&clusterId="+id
		});
     });
    $("[name='delete']").click(function(){
    	var id = "";
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    	 });
	     $.ajax({
			type: "GET",
			url: "taoKeeperMonitor_deleteZKClusterAndAlarm.do?clusterId="+id+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				if(data != "1"){
					alert("删除失败！");
				}else{
					alert("删除成功！");
					$("#theForm").submit();
				}
		 }
		});  	
    });
    $(function(){
	    $(":checkbox").click(function(){
	          $(":checked").not(this).attr("checked",false);
	    });
	   });
});
	//保存
	function save(theForm,oper){
		if(oper == "register"){//注册
			$.ajax({
            type: "post",
            url: "taoKeeperMonitor_registerZooKeeperHandle.do?"+theForm,
            dataType: "json",
			cache:false,
            success : function(data){
				if(data != "1"){
					alert("保存失败！");
				}else{
					$("#theForm").submit();
				}
            }
          });
		}else{//修改
			$.ajax({
            type: "post",
            url: "taoKeeperMonitor_updateZooKeeperSettingsHandle.do?"+theForm,
            dataType: "json",
			cache:false,
            success : function(data){
				if(data != "1"){
					alert("保存失败！");
				}else{
					$("#theForm").submit();
				}
            }
          });
		}
     	 
     }
</script>
</head>
<body >
<s:form action="taoKeeperMonitor_listZookeeperCluster.do" method="post" cssClass="theForm" id="theForm" name="theForm">
        <div class="pd-20 bgcolor-1">
        	<h2 class="utt-1">Zookeeper集群</h2>  
            <div class="bord-1 pd-20">
	            <label class="vm">集群名称</label>
	            	<s:textfield name="obj.clusterName" id="clusterName"></s:textfield>
	            <span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询" /></span>
			<div class="utt-2 mgt-20">
                    <a class="icon-add" href="javascript:void(0)" name="registerZKCluster" id="registerZKCluster">注册</a>
                    <a class="icon-modify" href="javascript:void(0)" name="editZKCluster" id="editZKCluster">修改</a>
                    <a class="icon-del" href="javascript:void(0)" name="delete">删除</a>
            </div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th>集群名称</th>
				   <th>机器列表</th>
				   <th>端口</th>
				   <th>用户名</th>
				  <!--  <th>密码</th> -->
				   <th>描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="zkClusterList" id="theBean">
			  	<tr>
			  	 	<td>
						<input type="checkbox" name="checkboxid" value='<s:property value="#theBean.clusterId"/>' />
					</td>
					<td>
				  		<s:property value="#theBean.clusterName"/>
			  		</td>
			  		<td>
				  		<s:property value="#theBean.serverListStr"/>
			  		</td>
			  		<td><s:property value="#theBean.ssh_port"/></td>
			  		<td><s:property value="#theBean.ssh_username"/></td>
			  		<%-- <td><s:property value="#theBean.ssh_passwd"/></td> --%>
			  		<td align="center">
			  			<div style="padding:auto;" class="hidden" title='<s:property value="#theBean.description"/>' style="width: 15px;">
					  		<s:property value="#theBean.description"/>
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
