<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	
	
		$("[name='detail']").click(function(){
		       	currentEdit=$(this);
		        	var DEVICE_ID=$(this).attr("DEVICE_ID");
		    		$.dialog({
		    			id:'vdi',
		    			title:'详细信息',
		    			width: '890px',
		   				height: '550px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:condevice_getDeviceInfo.do?DEVICE_ID='+DEVICE_ID
		    			});
		              });  
		             
		$("[name='manager']").click(function(){
		        	currentEdit=$(this);
		        	var DEVICE_ID=$(this).attr("DEVICE_ID");
		    		$.dialog({
		    			id:'vdi',
		    			title:'详细信息',
		    			width: '890px',
		    			height: '570px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:configure_queryConfigureObj.do?DEVICE_ID='+DEVICE_ID
		    			});
		              });   
	})
	function updateManager(DEVICE_ID){
		theForm.action = 'condevice_modDevice.do?DEVICE_ID='+DEVICE_ID;
		theForm.submit();
	
	}
	
	function resetForm(theForm){
		theForm.DEVICE_NAME.value = '';
		theForm.DEVICE_NAME_EN.value = '';
		theForm.DEVICE_TYPE.value = '';
		theForm.USE_RES.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'condevice_addDevice.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEVICE_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'condevice_modDevice.do';
		theForm.submit();
 	}
 	function delRequest() {
 	
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEVICE_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = "condevice_delDevice.do";  
		theForm.submit();
 	}
 	
 	function getDeviceInfo(device_id){
 		var device_id = device_id;
 		/*
 		$.getJSON("deviceinfo_getDeviceInfo.do?device_id="+device_id,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'     余量:'+data.freeSpace+'GB');
			alert(data.capacity);
		});
		*/
 	}
 	
</script>
	<script type="text/javascript">


function canel(){
document.getElementById("mdiv").style.display="none";
}

	function listDetails(device_id){ 
		var device_id = device_id;
		window.open("condevice_getDeviceInfo.do?device_id="+device_id,"listDetails",'height=250,width=550,top=500,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
	}
	/*
	$(function() {
		$("#import").uploadify({
            height        : 19,
			swf           : '<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify.swf',
			width         : 50,
			displayData    : 'percentage',//有speed和percentage两种，一个显示速度，一个显示完成百分比 
            auto          : true, 
            cancel : '<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/uploadify-cancel.png',
            buttonText : '&nbsp;&nbsp;&nbsp;导入',
            buttonClass  : 'thickbox btn-style02',
            fileSizeLimit :'5024000k',
            multi : false,
            queueSizeLimit : 1,
            hideButton:false,
            onQueueFull : function() {
            	alert("一次只能上传单个文件");
            },
            onSelect : function(file) {
            document.getElementById('import').select();//IE下调用select可以选择选择的路径内容，其他浏览器不行
 			 alert(document.selection.createRange().text);
            	//theForm.action ='condevice_importData.do';
            	//theForm.submit();
            }
		});
		
		//$('#import').uploadify('upload','*');
	})
	*/
	$(function(){
		$("#export").click(function(){
					bar(2,"正在导出导出月报表");
					$.ajax({
			  type:"GET",
              url:"condevice_exportData.do",
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                barEnd(2,"成功导出月报表");
              }
			});
		}); 
		
		$("#device").click(function(){
				bar(1,"正在导出设备报表");
					$.ajax({
			  type:"GET",
              url:"condevice_exportDeviceTable.do",
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                barEnd(1,"成功导出设备报表");
              }
			});
		}); 
		$("#import").click(function(){
			var path = $("#upload").val();
			path=encodeURI(encodeURI(path));
			theForm.action="condevice_importData.do?path="+path;
			theForm.submit();
		}); 
		
	});	
	
	
	
	function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
	}
        </script>
</head>
<body>
	<s:form action="condevice_listDevice.do" id="theForm" method="post"
		cssClass="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								设备名称
							</td>
							<td>
								<s:textfield name="theForm.DEVICE_NAME" cssClass="txt"
									id="DEVICE_NAME"></s:textfield>
							</td>
						
							<td class="til">
								物理IP:
							</td>
							<td>
								<s:textfield name="theForm.IP_PHYSICS" cssClass="txt"
									id="IP_PHYSICS"></s:textfield>
							</td>
							<td class="til">
								归属域:
							</td>
							<td>
								<s:textfield name="theForm.USE_DOMAN" cssClass="txt"
									id="USE_DOMAN"></s:textfield>
							</td>
							
							<td class="til">
								资源池:
							</td>
							<td>
								<s:textfield name="theForm.USE_RES" cssClass="txt"
									id="USE_RES"></s:textfield>
							</td>
							
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head" >
						<ul class="btns">
						
							<li>
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;" />
							</li>
							
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
							<!-- 
							<li>
								<input type="file"  id="import" name="import" />
							</li>
							 -->
							<li>
								<input type="button" class="btn-style02-100" value="导出月报表" name="export" id="export"/>
								
							</li>
							<li>
								<input type="button" class="btn-style02-100" value="导出设备信息"
									 name="device" id="device"/>
							</li>
							<li><input type="button" class="thickbox btn-style02" value="导入" id="import"/></li>
							<li><s:file id="upload" style="width:65px;"></s:file></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										设备名称
									</th>
								<!-- 
									<th>
										英文名称
									</th>
									<th>
										设备编码
									</th>
								 -->
									<th>
										设备类型
									</th>
									<!-- 
									<th>
										设备描述
									</th>
									 -->
									<th>
										设备状态
									</th>
									<th>机房</th>
									<!-- 
									<th>主机类型</th>
									<th>操作系统</th>
									 -->
									<th>主机名</th> 
									<th>物理IP</th> 
									<th>归属域</th> 
									<th>资源池</th> 
									<th>
										设备详细
									</th>
									<th>
										环境使用信息
									</th>
									
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr id="<s:property value='#theBean.DEVICE_ID'/>">
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.DEVICE_ID'/>"
												 />
										</td>
										<td>
											<s:property value="#theBean.DEVICE_NAME" />
										</td>
										<!-- 
										<td>
											<s:property value="#theBean.DEVICE_NAME_EN" />
										</td>
										
										<td>
											<s:property value="#theBean.DEVICE_CODE" />
										</td>
										-->
										<td>
											<s:property value="#theBean.TYPE_NAME" />
										</td>
										<!-- 
										<td>
											<s:property value="#theBean.DEVICE_DESC" />
										</td>
										-->
										<td>
											<s:if test="#theBean.STATUSE==1">正常</s:if>
											<s:elseif test="#theBean.STATUSE==2">不正常</s:elseif>
										</td>
										
										<td><s:property value="#theBean.MECH_ROOM"/></td>
										<!-- 
								  		<td><s:property value="#theBean.CAPITAL_TYPE"/></td>
								  		<td><s:property value="#theBean.SYS_SYSTEM"/></td>
								  		-->
								  		
								  		<td><s:property value="#theBean.SYS_HOSTNAME"/></td>
										<td><s:property value="#theBean.IP_PHYSICS"/></td>
										<td><s:property value="#theBean.USE_DOMAN"/></td>
										<td><s:property value="#theBean.USE_RES"/></td>
										
										<td>
											<a href="javascript:;" DEVICE_ID='<s:property value="#theBean.DEVICE_ID" />' name="detail">详细信息</a>
										</td>
										<td>
											<a href="javascript:;" DEVICE_ID='<s:property value="#theBean.DEVICE_ID" />' name="manager">环境使用信息</a>
										</td>
										
										
									
									</tr>
								</s:iterator>
							</tbody>
						</table>
						</div>
						
					</div>
				</div>
		</div>

	</s:form>

</body>

