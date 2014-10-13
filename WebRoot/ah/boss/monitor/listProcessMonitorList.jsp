<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<title></title>
<style>
	a:hover{color:blue}
</style>
<script type="text/javaScript">
	//setInterval(reloadMonitorData, 10000);//10秒刷新一下数据
    //刷新监控数据
	function reloadMonitorData(){
		    $("#theForm").submit();
	     }
	     //$("#cluster_id").attr("value","");失效原因是页面还没加载完，找不到该元素
	$(function(){
	 	$("#search").click(function(){
	 	   $("#theForm").submit();
	 	});
	 	$("#resert").click(function(){
			$("#status").attr("value","");
	 	});
 	//刷新
 	$("[name='refresh']").click(function(){
        	$("#theForm").submit();
        });
 	//全选
 	$(function(){
	      $("[name='all']").click(function(){
	   	 	var currentState=$(this).attr("checked");
	 	 	if(currentState){
	 	 		$("[name='checkboxid']").attr("checked",true);
	 	 	}else{
	 	 		$("[name='checkboxid']").attr("checked",false);
	      	}
	       });
		});
	//启停
    $("[name='operprocess']").unbind().live("click",function(){
	     if($(":checkbox:checked").not("[name='all']").length==0){
	        		alert("请您至少选择一项来进行操作.");
	        		return false;
	        	}
        	var cluIds="";
        	var pNames="";
        	var operatorIds=0;
        	var length=0;
        	var oper=$(this).attr("oper");
        	var oper1="";
        	var ff=false;
        	var fq=false;
        	var fs=false;
        	if(oper == "start"){
        		oper1 = "启动";
        	}else{
        		oper1 = "停止";
        	}
        	if(confirm("确定"+oper1+"进程吗?")){
		    $(":checkbox:checked").not("[name='all']").each(function(index,data){
		    		cluIds+=$(this).attr("cluId")+",";
		    		pNames+=$(this).attr("pName")+",";
		    		//operatorIds+=$(this).attr("operatorId");
		    		length++;
		    		var stat =$(this).attr("stat");
		    		if(oper == "start" && stat != 3){
		    			fq=true;
		    		}
		    		if(oper == "stop" && stat == 3){
		    			fs=true;
		    		}
		    		if(stat == 4){
		    			ff= true;
		    		}
		    });
		    if(ff){
		    	alert("请勿选择正在检测中的进程！");
		    	return false;
		    }
		    //启动的前提是停止的，启停标识为1
		    if(fq){
		    	alert("请选择已停止的进程！");
		    	return false;
		    }
		    //停止的前提是进程时启动的，启停标识为0
		    if(fs){
		    	alert("请选择正在运行的进程！");
		    	return false;
		    }
		    mask('正在发送请求,请稍后....','0.5','0px');
		    $.ajax({
		 		type: "POST",
			    url: "bossProcessMonitor_operProcess.do",
			    dataType: "json",
			    data:{"cluIds":cluIds,"oper":oper,"pnames":pNames},
			    success : function(data){
					removeMask();
					theForm.submit();
			 }
		  });  
		  }	
     });     
});
	
</script>
</head>
<body style="overflow:auto;">
<s:form action="bossProcessMonitor_queryMonitorObjList.do" method="post"  cssStyle="theForm" id="theForm" name="theForm">
<s:hidden name="tobj.name" id="name"></s:hidden>
<s:hidden name="tobj.parent_name" id="parent_name"></s:hidden>
<s:hidden name="tobj.grand_name" id="grand_name"></s:hidden>
<s:hidden name="tobj.type" id="type"></s:hidden>       
        <div class="pd-10 bgcolor-1">
         <div class="bord-1 pd-20">
         	<div class="clearfix mgt-20">
         			<label class="vm">IP:</label>
         				<s:textfield name="obj.host_ip" id="host_ip"></s:textfield>
					<label class="mgl-20 vm">主备状态:</label>
						<s:select cssClass="select-1 vm" list="#{0:'--请选择--',1:'主',2:'备',3:'未运行'}"
							name="obj.status" id="status"></s:select>
					<label class="mgl-20 vm">启停标识:</label>
						<s:select cssClass="select-1 vm" list="#{'':'--请选择--','0':'允许启动','1':'禁止启动'}"
							name="obj.operator_id" id="operator_id"></s:select>
                    <span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询" /></span>
					<!-- <span class="ubtn-2 mgl-20"><input type="button" id = "resert" value="重置" /></span> -->
        </div>
		<div class="utt-2 mgt-10">
				<a name="operprocess" oper="start" class="icon-set vm" href="javascript:void(0)">启动</a>
				<a name="operprocess" oper="stop" class="icon-del vm" href="javascript:void(0)">停止</a>
				<a name="refresh" class="icon-release vm" href="javascript:void(0)">刷新</a>
		</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input type="checkbox" name="all"></th>
				   <th>集群</th>
				   <th>程序池</th>
				   <th>主机/用户</th>
				   <th>端口</th>
				   <th>进程名</th>
				   <th>启停标识</th>
				   <th>主备状态</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  	 	<td>
						<input type="checkbox" name="checkboxid" value='<s:property value="#theBean.uid"/>' cluId='<s:property value="#theBean.cluster_id"/>' pName='<s:property value="#theBean.program_name"/>'
							userId='<s:property value="#theBean.user_uid"/>' poolId='<s:property value="#theBean.app_pool"/>' stat='<s:property value="#theBean.status"/>' operatorId='<s:property value="#theBean.operator_id"/>'/>
					</td>
					<td>
			  			<s:property value="#theBean.cluster_id"/>
			  		</td>
			  		<td>
			  			<s:property value="#theBean.app_pool"/>
			  		</td>
			  		<td><s:property value="#theBean.host_ip"/>/<s:property value="#theBean.username"/></td>
			  		<td><s:property value="#theBean.running_port" default="-"/></td>
					<td><s:property value="#theBean.program_name"/></td>
			  		<td>
			  			<s:if test="#theBean.operator_id==0">
			  				<span style="color: blue">允许启动</span>
			  			</s:if>
			  			<s:else>
			  				<span style="color: #C0C0C0">禁止运行</span>
			  			</s:else>
			  		</td>		
			  		<td>
			  			<s:if test="#theBean.status==1">主</s:if>
				  		<s:elseif test="#theBean.status==2">备</s:elseif>
				  		<s:elseif test="#theBean.status==3">
				  			<span style="color: red">未运行<span/>
				  		</s:elseif>
				  		<s:elseif test="#theBean.status==4">
				  			<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中…
						</s:elseif>
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
</s:form>
</body>
</html:html>	
