<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-ui.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
	
    var oper = '<%=request.getAttribute("oper")%>';
    var appid = '<%=request.getAttribute("appid")%>';
   	var api = frameElement.api;
	var w = api.opener;
    //创建配置文件
	$(function(){
		 
		 if("add"==oper){
			 api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addorder,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }else{
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:addorder,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		 }
	
	
	    function Trim(str){
			return str.replace(/^\s+|\s+$/g,"");
		}

		 function addorder(){
			var effect_time=$("#effect_time").val();
		    if(effect_time==null||Trim(effect_time)==""){
			       alert("有效时间不能为空！");
			       return false  ;
			}
		    var versionTarPath=$("#versionTarPath").val();
		    if (versionTarPath==null ||Trim(versionTarPath)=="") {
				alert("上线包版本不能为空");
				return false;
			}
			
			//版本id
    	    var versionids=$("#versionid").val();
    	    
    	    //任务
    	    var $trTasks=$("#tasktable").find("tr").not(":eq(0)");
    	    var taskTactiStrs="";
    	    var flag=false;
    	    $trTasks.each(function(){
	    	    var exampleids=$(this).find("td:eq(1)").attr("exampleid");//实例id
	            var example=$(this).find("[name='example']").text();
			    if(example==0){
				    alert("你好：任务上必须添加实例，请检查。");
				    flag=true;
				    return false;
				 }
	   	        var executelevel=$(this).find("td:eq(1)").attr("executelevel");//实例id
	   	        taskTactiStrs+=exampleids+"~"+executelevel;
    	    });
    	    //条件不满足则跳出
    	    if(flag){
    	      return false;
    	    }
     		w.quickSaveOrder(effect_time,versionTarPath,versionids,taskTactiStrs);
		 }
		 
		 
		 $("#versionTarPath").click(function(){
			 w.$.dialog({
      			id:'add1',
      			title:'从版本中选取',
      			width: '750px',
      			height: '470px',
      		    lock:true,
      		    parent:api,
      			content: 'url:fileversion_queryFileVersionByAppid.do?appid='+appid+'&userType=quickaddtask'
  	    	});
		 });
	});
	
    //新建任务
    $(function(){
    	$("#createTask").click(function(){
    		$("#tabs").append($("#tabs").find("tr:last").clone())
    	});
    	$("#delTask").click(function(){
    		$(":checkbox:checked").parents("tr").remove();
    	});
    	
    })
    
    //任务实例
   	$("[name='selectHost']").unbind().live("click",function(){
		var appid=$("#appid").val();
		var num=$(this).parent().next().attr("num");
		var exampleid=$(this).parent().next().attr("exampleid");//当前此任务被选中的id
		var otherExampleid="";
		$(this).parent().parent().siblings("tr").find("[name='ips']").each(function(obj){
			otherExampleid+=$(this).attr("exampleid");
		});
  		w.$.dialog({
   			id:'addHostIp',
   			title:'添加实例',
   			width: '750px',
   			height: '470px',
   		    lock:true,
   		    parent:api,
   			content: 'url:treetask_listDeployExample.do?userType=quickorder&num='+num+'&appid='+appid+"&exampleid="+exampleid+"&otherExampleid="+otherExampleid+"&executelevel="+$("#executelevel").val()
    		});
	 });
    
    //任务的添加实例
    function addExample(exampleid,executelevel,num){
        $("#exampleid").val(exampleid);//这样可以通过标签把值传回actin
        $("#executelevel").val(executelevel);
        var idarrays=exampleid.split(",");
        var temp="已选择:<label name=\"example\">"+(idarrays.length-1)+"</label>个";
        $("[name='ips'][num='"+num+"']").attr("exampleid",exampleid);
        $("[name='ips'][num='"+num+"']").attr("executelevel",executelevel);
        $("[name='ips'][num='"+num+"']").empty();
        $("[name='ips'][num='"+num+"']").append(temp);
    }
    
    
    
    //上线包版本
    function addFile(versionids,path){
    	$("#versionTarPath").val(path);
    	$("#versionid").val(versionids);
    }
    
        //页面初始化后的方法
    $(function(){
    	var exampleid=$("#exampleid").val();
    	var executelevel=$("#executelevel").val();
    	var temp="";
    	if(exampleid!=null&&exampleid!='undefine'){
    		var idarrays=exampleid.split(",");
    		temp="已选择:<label name=\"example\">"+(idarrays.length-1)+"</label>个";
    	}else{
            temp="已选择:<label name=\"example\">0</label>个";
    	}
    	$("[name='ips']").empty();
   	$("[name='ips']").append(temp);
    });
        
    //任务相关
    $(function(){
    	$("#createTask").click(function(){
    	    //选择实例的唯一标示
    		var num=$("#tasktable").find("[name='ips']").filter(":last").attr("num");
    		var clone=$("#clonetr").clone().show().find("[name='ips']").attr("num",Number(num)+1).parent();
    		$("#tasktable").append(clone);
    	});
    	
    	$("[name='deltask']").unbind().live("click",function(){
    		$(this).parent().parent().remove();
    	});
    	
    	$("[task='task_type']").unbind().live("change",function(){
    	     var currSelectValue = $(this).val();
    	     $("[task='task_type']").find("[value='"+currSelectValue+"']").attr("selected",true);
    	});
    });
    
    
</script>

</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	    <s:hidden name="orderObj.ID" id="orderid"></s:hidden>
	    <s:hidden name="orderObj.VERSIONID" id="versionid"></s:hidden>
	    
	     <s:hidden name="taskObj.ID" id="taskid"></s:hidden>
	    <s:hidden name="appId" id="appid"></s:hidden>
	    <s:hidden name="exampleid" id="exampleid"></s:hidden>
	    <s:hidden name="executelevel" id="executelevel"></s:hidden>
	    
	    <div class="pd-20 bgcolor-1">
	    <h2 class="utt-1">订单</h2>
		<div class="bord-1 pd-10">			
		<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
		    <tr>
			    <td class="til">
			       有效时间:<font color="red">*</font>
			    </td>
			    <td align="left">
			  	 <input id="effect_time" style="txt" type="text" name="orderObj.EFFECT_TIME" size="20"  class="Wdate"
			   		onFocus="WdatePicker({minDate:'new Date()',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			   		value="${orderObj.EFFECT_TIME }"/>
			   		<!-- 
			   			 <s:textfield name="orderObj.EFFECT_TIME" id="effect_time" size="20" readonly="true" cssStyle="txt" class="Wdate" 
			   			 onFocus="WdatePicker({minDate:'new Date()',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></s:textfield>
			   		 -->
			    </td>
			</tr>
			<tr>
				<td class="til">上线包版本<font color="red">*</font></td>
				<td align="left">
					<s:textfield name="orderObj.versionPath" id="versionTarPath" style="border: 1px solid #9DBCD9;width:335px;height:20px;" readonly="readonly"></s:textfield>
				</td>
			</tr>
				<!--  -->
			<tr>
				<td class="til">
					文件备份 <font color="red">*</font></td>
				</td>
				<td align="left">
					<s:if test="orderObj.backupType==1">
						<input type="radio" name="orderObj.backupType" value="0"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1" checked="checked"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2"> 无备份</input> &nbsp;&nbsp;
					</s:if>
					<s:elseif test="orderObj.backupType==2">
						<input type="radio" name="orderObj.backupType" value="0"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2" checked="checked"> 无备份</input> &nbsp;&nbsp;
					</s:elseif>
					<s:else>
						<input type="radio" name="orderObj.backupType" value="0" checked="checked"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2"> 无备份</input> &nbsp;&nbsp;
					</s:else>
				</td>
			</tr>
<!-- 			自动生成许可证比编号 -->
		</table>
		</div>
	</div>
 <div class="pd-20 bgcolor-1">
   <h2 class="utt-1">任务</h2>
    <div class="bord-1 pd-10">	
     <a href="javascript:;" id="createTask">增加任务</a> <a href="javascript:;" id="delTask">删除任务</a>
       	<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="tasktable">
	       	<tr style="display: none" id="clonetr">
			    <td class="til">
			       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">选择实例:</a><font color="red">*</font>
			    </td>
			    <td name="ips" num="0" exampleid="" executelevel="">
			    </td>
				<td>
					<a href="javascript:;" name="deltask">删除</a>
				</td>
			</tr>
			
			<tr>
			    <td class="til">
			       <a href="javascript:;" name="selectHost" style="color: blue;text-decoration: underline;">选择实例:</a><font color="red">*</font>
			    </td>
			    <td name="ips" num="1" exampleid="" executelevel="">
			    </td>
				<td>
					<a href="javascript:;" name="deltask">删除</a>
				</td>
			</tr>
		</table>
	</div>
</div>
		
	</s:form>
</body>

</html:html>
