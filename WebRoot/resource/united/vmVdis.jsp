<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
 <script type="text/javascript">
     var poolUuid ='<%=request.getAttribute("poolUuid")%>';
     var vmUuid ='<%=request.getAttribute("vmUuid")%>';
     var hostUuid ='<%=request.getAttribute("hostUuid")%>';
     $("[vdiType='SYSTEM']").css("display","none");
      var xentool=$("#select").attr("xentool");
      if(xentool!=""){
      	 $("#select option").each(function(){
                 if(xentool==$(this).val()){
              	   $(this).attr("selected",true);
                    }
          	 });
          }

      var currentEdit; //当前对象
     $(function(){
        $("#select option").each(function(){
            $(this).not("[value^='sr~'],[value='0']").prepend("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
         });
         //当前选中的uuid
        var currentVdiUuid=$("#select option:selected").val();
        $("#tanchu").click(function(){
	     	mask('正在拆卸镜像....','0.5','50px');
			$.getJSON("xen_removeVdiForDVD.do?poolUuid="+poolUuid+"&vmUuid="+vmUuid,{'time':new Date().toString()}, function(data){
				if(data.responseCode==1){
                    $("#select option:eq(0)").attr("selected",true);
				     removeMask();
				     currentVdiUuid="0";
				}else{
					removeMask();
					$("select option[value='"+currentVdiUuid+"']").attr("selected",true);
					alert("拆卸镜像失败!");
				}
			});
         });
        $("#select").change(function(){
            var vdiUuid=$(this).val();
            if(vdiUuid=="0"){
            	mask('正在拆卸镜像....','0.5','50px');
    			$.getJSON("xen_removeVdiForDVD.do?poolUuid="+poolUuid+"&vmUuid="+vmUuid,{'time':new Date().toString()}, function(data){
    				if(data.responseCode==1){
    				     removeMask();
    				     currentVdiUuid=vdiUuid;
    				}else{
    					removeMask();
    					$("select option[value='"+currentVdiUuid+"']").attr("selected",true);
    					alert("拆卸镜像失败!");
    				}
    			});
    			return;
            }
            if(vdiUuid.split("~")[0]=="sr"){
                 alert("请选择一个有效的镜像文件!");
                 $("select option[value='"+currentVdiUuid+"']").attr("selected",true);
                 return;
                }
        	mask('正在装载镜像....','0.5','50px');
    		$.getJSON("xen_loadVdiForDVD.do?vdiUuid="+vdiUuid+"&poolUuid="+poolUuid+"&vmUuid="+vmUuid,{'time':new Date().toString()}, function(data){
    			if(data.responseCode==1){
    			     removeMask();
    			     currentVdiUuid=vdiUuid;
    			}else{
    				removeMask();
    				$("select option[value='"+currentVdiUuid+"']").attr("selected",true);
    				alert("装载镜像文件失败!");
    			}
    		});
            });
        $("[name='del']").unbind().live("click",function(){
               var $current=$(this);
               var powerState=$(this).attr("powerState");
               if(powerState!="Halted"){
                   alert("请先关闭虚拟机,在进行此操作!");
                   return false;
               }
           	if(confirm("删除存储会删除此存储上所有的文件, 确定删除当前存储吗?")==true){
   	            var vdiUuid=$(this).parent().siblings("td[vdiUuid]").attr("vdiUuid");
   	            mask('正在删除存储....','0.5','50px');
   	    		$.getJSON("xen_deleteVdiVm.do?vdiUuid="+vdiUuid+"&poolUuid="+poolUuid,{'time':new Date().toString()}, function(data){
   	    			if(data.responseCode==1){
   	    			     removeMask();
   	    			     $current.parent().parent().remove();
   	    			}else{
   	    				removeMask();
   	    				alert("删除存储失败!");
   	    			}
   	    		});
                }
            });
        $("[name='add']").click(function(){
    		$.dialog({
    			id:'vdi',
    			title:'添加存储',
    			width: '650px',
    			height: '330px',
    			content: 'url:xen_addVdiForVm.do?poolUuid='+poolUuid+'&vmUuid='+vmUuid+"&hostUuid="+hostUuid+"&oper=add"
    			});
            });

        $("[name='edit']").unbind().live("click",function(){
        	currentEdit=$(this);
            var powerState=$(this).attr("powerState");
            if(powerState!="Halted"){
                alert("请先关闭虚拟机,在进行此操作!");
                return false;
            }
            var vdiName=$(this).parent().siblings(":eq(0)").text();
            vdiName=trim(vdiName);
            var vdiUuid=$(this).parent().siblings(":eq(0)").attr("vdiUuid");
            var vdiSize=$(this).parent().siblings(":eq(1)").children("span").text();
            vdiSize=trim(vdiSize);
            var srUuid=$(this).parent().siblings(":eq(2)").attr("srUuid");
    		$.dialog({
    			id:'vdi',
    			title:'调整存储',
    			width: '650px',
    			height: '330px',
    			content: 'url:xen_addVdiForVm.do?poolUuid='+poolUuid+'&vmUuid='+vmUuid+'&hostUuid='+hostUuid+'&vdiName='+encodeURI(encodeURI(vdiName))+'&vdiSize='+vdiSize+'&srUuid='+srUuid+'&vdiUuid='+vdiUuid+'&oper=edit'
    			});
              });
     });
     
	 function addVdi(vdiName,vdiDesc,vdiSize,mate,srUuid){
		 mask('正在添加存储....','0.5','50px');
	 	 $.getJSON("xen_addVdiForVm2.do?vdiName="+encodeURI(encodeURI(vdiName))+"&vdiDesc="+encodeURI(encodeURI(vdiDesc))+"&vdiSize="+vdiSize+"&mate="+mate+"&srUuid="+srUuid+"&poolUuid="+poolUuid+"&vmUuid="+vmUuid,{'time':new Date().toString()}, function(data){
				if(data.responseCode==1){
				     removeMask();
				     var r=data.readOnly;
				     var readOnly="否";
				     if(r){
				    	 readOnly="是";
					 }
					 var s=data.srShared;
				     var srShared="否";
					 if(s){
						 srShared="是";
					 }
					 var tz="<a href='javascript:;' style='color: blue;margin-left: 5px' name='edit' powerState="+data.powerState+">调整</a>"
					 var sc= "<a href='javascript:;' style='color: blue;margin-right: 5px' name='del' vdiType="+data.vdiType+" powerState="+data.powerState+">删除</a>";
				     var content=$("<tr><td name='vdiName' vdiUuid="+data.vdiUuid+">"+data.vdiName+"</td><td><span>"+data.vdiSize+"</span></td><td srUuid="+data.srUuid+">"+data.srName+"</td><td>"+srShared+"</td><td>"+data.srHostName+"</td><td>"+readOnly+"</td><td>"+tz+"  "+sc+"</td></tr>");
					 $("#table tbody").prepend(content);
				}else{
					removeMask();
					alert("添加存储失败,详细信息请查看日志!");
				}
		});
	  }

	 function editVdi(vdiName,vdiDesc,vdiSize,mate,vdiUuid){
		 mask('正在调整存储....','0.5','50px');
	 	 $.getJSON("xen_editVdiForVm2.do?vdiName="+encodeURI(encodeURI(vdiName))+"&vdiDesc="+encodeURI(encodeURI(vdiDesc))+"&vdiSize="+vdiSize+"&mate="+mate+"&vdiUuid="+vdiUuid+"&poolUuid="+poolUuid,{'time':new Date().toString()}, function(data){
				if(data.responseCode==1){
				     removeMask();
				     currentEdit.parent().siblings(":eq(0)").text(data.vdiName);
				     currentEdit.parent().siblings(":eq(1)").children("span").text(data.vdiSize);
				}else{
					removeMask();
					alert("调整存储失败,详细信息请查看日志!");
				}
		});
	  }

    
 </script>
</head>

<body>
<s:form action="" method="post" id="theForm">
	<s:hidden name="theForm.store_uuid" id="store_uuid"/>
	<div class="blue-wrap noborder">
		 	<div class="table-head">
		 	 <ul class="btns" style="height: 30px;" >
<%--				<li><input type="button" class="thickbox btn-style02" value="添加" name="add"/></li>--%>
                <%-- <li><span style="font-style: normal">DVD 驱动器 ：</span>
                <s:select id="select" xentool="#request.xentool" list="theForm.vdiIsos" listKey="key" listValue="value" headerKey="0" headerValue="空" style="width: 600px;"></s:select>
                <a href="javascript:;" style="color: blue;" id="tanchu">弹出</a></li> --%>
			</ul>
			</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="table">
				<thead>
					<tr>
						<th>名称</th>
						<th>大小(GB)</th>
						<th>存储库(SR)</th>
						<th>共享(SR)</th>
						<th>存储库位置</th>
						<th>只读</th>
						<!-- <th>操作</th> -->
					</tr>
				</thead>
				<tbody>
					<s:iterator value="queryList" id="theBean">
						<tr>
							<td name="vdiName" vdiUuid=<s:property value="#theBean.vdiUuid"/>>
								<s:property value="#theBean.vdiName"/>
							</td>
							<td><span><s:property value = "@java.lang.Math@round(#theBean.vdiSize/(1024 * 1024 * 1024))"/></span> GB</td>
							<td srUuid=<s:property value="#theBean.srUuid"/>><s:property value="#theBean.srName"/></td>
							<td >
                            <s:if test="#theBean.srShared==true">是</s:if>
							<s:elseif test="#theBean.srShared==false">否</s:elseif>
                            </td>
							<td ><s:property value="#theBean.srHostName" /></td>
							<td>
							<s:if test="#theBean.readOnly==true">是</s:if>
							<s:elseif test="#theBean.readOnly==false">否</s:elseif>
							</td>
							<!-- <td>
							<a href="javascript:;" style="color: blue;margin-left: 5px" name="edit" powerState=<s:property value="#theBean.powerState"/>>调整</a>
							<a href="javascript:;" style="color: blue;margin-right: 5px" name="del" vdiType=<s:property value="#theBean.vdiType"/> powerState=<s:property value="#theBean.powerState"/>>删除</a>
							</td> -->
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>
</s:form>
</body>
