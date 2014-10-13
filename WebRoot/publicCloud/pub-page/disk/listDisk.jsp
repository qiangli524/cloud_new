<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控制中心</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/default.css" type="text/css" />
<script type="text/javascript"
        src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/publicCloud/pub-ui/js/common.js"></script>

<script type="text/javascript">
	$(function(){
		$("#checkAll").click(function(){
			 $('input[name="bb"]').prop("checked",this.checked);
			 var num = $('input:checkbox[name="bb"]:checked').size();
				if(num>=1){
					$('.aa').removeClass("gray_button").addClass("blue_button");
				}else{
					$('.aa').removeClass("blue_button").addClass("gray_button");
				}
		 })
		//检索
		$("#searchForm").click(function(){
			$("#theForm").submit();
		});
		
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
			  	$("#theForm").submit();
		     }
		}
		
		 //重命名点击事件
		 $(".btn-w8").click(function(){
			 var newnameInput = $(this).parent().parent().find(".input-compile");
			 
			 if(newnameInput.val() == null || newnameInput.val().trim() == ""){
				 alertMsg("请填写名称");
				 return;
			 }
			 var oldnameP = $(this).parent().parent().siblings("p");
			 var objId =  $(this).attr("objId");
			 var params = {
				 "obj.id":objId,
				 "obj.name":newnameInput.val()
			 };
			 
			 $.ajax({
				url:"disk_updateDisk.do",
				type:"POST",
				data:params,
				dataType:"json",
				success:function(data){
					if(data.ret != -1){
					     mask("重命名成功！","success",3000);
					     oldnameP.text(newnameInput.val());
					}else{
						 mask("重命名失败！","error",3000);
					}
				}
			});	
			 
			 $(this).parents(".compile-con").hide();
		 });
		
		//创建一块虚拟磁盘
		/*
		$("#addDisk").click(function(){
			var url = "disk_addDisk.do?obj.oper=add";
			showWindow("选择云主机",url,1400);
		});*/
			
		//删除一款虚拟磁盘
		$("#delDisk").click(function(){
			var diskType = 1;
			var count = 0;
			var diskId = "";
			$("input:checkbox[name='bb']:checked").each(function(){
				diskType = $(this).attr("diskType");
				diskId += $(this).attr("diskId");
				count ++;
			});
			if (count > 1) {
				alertMsg("一次只能删除一块磁盘");
				return false;
			}
			if (diskType != 2) {
				alertMsg("该磁盘不是数据盘，不可删除");
				return false;
			}
			var url = "disk_deleteDisk.do?obj.id="+diskId;
			if (confirm("删除后该磁盘将会被销毁，请先备份好您的数据。你确定要删除该虚拟磁盘吗？")) {
				mask("正在删除虚拟磁盘……","info");
				$.ajax({
					type:'post',
					url:url,
					dataType:'text',
					success:function(msg){
						removeMask();
						if (msg == -1) {
							alertMsg("虚拟磁盘删除失败");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});
		
		//卸载虚拟磁盘
		$("#UninstallDisk").click(function(){
			var diskType = 1;
			var count = 0;
			var diskId = "";
			var $td = '';
			$("input:checkbox[name='bb']:checked").each(function(){
				diskType = $(this).attr("diskType");
				diskId += $(this).attr("diskId");
				count ++;
				$td = $(this);
			});
			if (count > 1) {
				alertMsg("一次只能卸载一块磁盘");
				return false;
			}
			if (diskType != 2) {
				alertMsg("该磁盘不是数据盘，无法卸载");
				return false;
			}
			var url = "disk_unInstallDisk.do?obj.id="+diskId;
			if (confirm("虚拟磁盘卸载后可以再次挂载使用，你确定要卸载该虚拟磁盘吗？")) {
		//		mask("正在卸载虚拟磁盘，请稍候","0.7","0px");
				$td.parent().siblings("#mount_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />'+'卸载中');
				$.ajax({
					type:'post',
					url:url,
					dataType:'text',
					success:function(msg){
			//			removeMask();
						if (msg == -1) {
							alertMsg("该虚拟磁盘卸载失败");
						} 
						$("#theForm").submit();
					}
				});
			}
		});
		
		//挂载虚拟磁盘
		$("#mountDisk").click(function(){
			var count = 0;
			var diskId = "";
			var vmNum = 0;
			$("input:checkbox[name='bb']:checked").each(function(){
				diskId += $(this).attr("diskId");
				count ++;
				vmNum = $(this).attr("vmNum");
			});
			if (count > 1) {
				alertMsg("一次只能挂载一块磁盘");
				return false;
			}
			if (vmNum > 0) {
				alertMsg("该磁盘已经被挂载到其他虚拟机，不能再次挂载，若一定需要挂载，请先从别的虚拟机上卸载");
				return false;
			}
			
			$.dialog({
				id:'mountDisk',
				title:'选择云主机',
				width: '800px',
				height: '500px',
    		    lock:true,
				content: 'url:disk_addDisk.do?obj.oper=mount&obj.id='+diskId
			});
			
		});
	});
	
	function selectVM(url){
	//	mask("正在挂载虚拟磁盘，请稍候","0.7","0px");
		$("input:checkbox[name='bb']:checked").each(function(){
			$(this).parent().siblings("#mount_state").html('<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" />'+'挂载中');
		});
		$.ajax({
			type:'get',
			dataType:'text',
			url:url,
			success:function(msg){
	//			removeMask();
				if (msg == -1) {
					alertMsg("挂载失败")
				} 
				$("#theForm").submit();
			}
		})
	}
</script>
</head>

<body>
<!--container star-->
<div class="container">
	<!--col-c7 star-->
	<div class="col-c7">
    	<!--left star-->
    	<jsp:include page="../order.jsp" />
        <!--left end-->
        <!--main-c1 star-->
      <div class="main-c1 fr">
            <h2 class="title-common6"><a href="#" class="home"></a><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title_disk.gif" width="65" height="65" />磁盘</h2>
            <p class="p-con4">
				硬盘 (Volume) 为主机提供块存储设备，它独立于主机的生命周期而存在，可以被连接到任意运行中的主机上。注意，硬盘附加到主机上后，您还需要登陆到您的主
				机的操作系统中去初始化该硬盘。当然，也可以从主机上卸载硬盘、并转至其他主机。
            </p>
            <div class="pd-15">
            <form id="theForm" action="<%=request.getContextPath() %>/disk_listDisk.do?type=<s:property value="type"/>" method="post">
            	<div class="col-c8">
                	<ul class="operate-list">
                    	<%--<li class="first">
                        	<a href="javascript:;" class="add" id="addDisk"></a>
                        </li>  --%><%--
                        <li class="gray_button aa">
                        	<a href="javascript:;" class="close" id="delDisk">删除</a>
                        </li>
                        --%><li class="gray_button aa">
                        	<a href="javascript:;" class="mount" id="mountDisk">挂载</a>
                        </li>
                        <li class="gray_button aa">
                        	<a href="javascript:;" class="uninstall" id="UninstallDisk">卸载</a>
                        </li>
                        <li class="search">
                        	<input type="text" class="text-1" placeholder="磁盘名称" size="22" name="obj.name" value="<s:property value='obj.name'/>"/>
                            <input type="button" class="search-btn" id="searchForm"/>
                        </li>
                        
                    </ul>
                	<table border="0" width="100%" class="table-f4 ">
                    	<tr>
                        	<th><input type="checkbox" class="vhid" id="checkAll"/></th>
                            <th>磁盘名称</th>
                            <th>容量</th>
                            <%--<th>挂载主机台数</th>
                            --%>
                            <th>应用主机</th>
                            <th>磁盘类型</th>
                            <th>磁盘状态</th>
                            <th>创建时间</th>
                        </tr>
                        <s:iterator value="resultList" id="theBean">
                        	<tr>
                        		<td>
                        			<input type="checkbox" name="bb" class="code"
                        				diskId='<s:property value="#theBean.id"/>'
                        				vmNum='<s:property value="#theBean.vmNum" />'
                        				diskType='<s:property value="#theBean.disk_type"/>'/>
                        		</td>
                        		<td>
                        			<div class="compile js_compile">
	                                	<p><s:property value="#theBean.name" /></p>
	                                    <div class="compile-con">
	                                    	<h3 class="fs14">编辑名称：</h3>
	                                        <div class="mgt-10"><input type="text" maxlength="20" class="input-compile" value='<s:property value="#theBean.name" />' /></div>
	                                        <div><a class="btn-w8" href="javascript:;" objId='<s:property value="#theBean.id" />'>提交</a><a class="btn-w9 js_abrogate" href="javascript:void(0);">取消</a></div>
	                                    </div>
	                                </div>
                        			
                        		</td>
                        		<td>
                        			<span class="font-blue">
                        				<s:property value="@java.lang.Math@round(#theBean.capacityInMB/1024)"/>G
                        			</span>
                        		</td>
                        		<%--<td>
                        			<span class="font-blue"><s:property value="#theBean.vmNum"/>台</span>
                        		</td>
                        		--%>
                        		<td>
                        			<span class="font-blue"><s:property value="#theBean.vm_name"/></span>
                        		</td>
                        		<td>
                        			<span class="font-blue">
                        				<s:if test="#theBean.disk_type==1">系统盘</s:if>
                        				<s:elseif test="#theBean.disk_type==2">数据盘</s:elseif>
                        				<s:else>未知</s:else>
                        			</span>
                        		</td>
                        		<td id="mount_state">
                        			<s:if test="#theBean.vm_name==null||#theBean.vm_name==''">
                        				<img src="<%=request.getContextPath()%>/sxcloud/images/downed.png" alt="未挂载" />未挂载
                        			</s:if>
                        			<s:else>
                        				<img src="<%=request.getContextPath()%>/sxcloud/images/uped.png" alt="已挂载" />已挂载
                        			</s:else>
                            	</td>
                        		<td>
                        			<span class="font-blue"><s:property value="#theBean.create_time"/></span>
                        		</td>
                        	</tr>
                        </s:iterator>
                    </table>
                    <div class="page mgt34-b25">
                   		<jsp:include page="../../inc/fenye.jsp?formId=theForm" />
                    </div>
                </div>
                </form>
            </div>
        </div>
        <!--main-c1 end-->
        <div class="clear"></div>
    </div>
    <!--col-c7 end--> 
</div>
<!--container end-->
<!--版权 star-->
<div class="copy">
	<div class="copy-con ac">
		<p class="pdt-30">?2012中国电信云计算分公司版权所有 京ICP备 12022551号  增值电信业务经营许可证A2.B1.B2-20090001</p>
	</div>
</div>
<!--版权 end-->
</body>

</html>