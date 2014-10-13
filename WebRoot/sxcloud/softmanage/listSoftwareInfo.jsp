<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function catchSysSoftware(softpath,appid,tarName){
		var url = "software_catchSysSoftware.do?softpath="+softpath+"&appid="+appid+"&tarName="+tarName;
			bar("starttips","开始捕获...");
			 $.ajax({
				  type:"post",
	              dataType:"json",
	              cache: false,
	              url:url,
		          success:function(msg){
		        	  if(msg=='success'){
		        	  	barEnd("starttips","捕获成功!",'5');
		        	  	window.location.reload();
		        	  }else{
		        	  	barEnd("starttips","捕获失败，原因" + msg,'10');
		        	  }
		        	  
		          }
			});
	}
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
	function barEnd(idstr,contents,min){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(min);
	}

 		$(function(){
 			var bizid = $("#bizid").val();
 			if (bizid != null && "" != bizid && "null" != bizid) {
 				$("#upsoft").show();
 				$("#catchsoft").show();
 			}
 			$("#upsoft").click(function(){
 				$.dialog({
 					title:'软件包上传',
 					width:'870px',
 					height:'420px',
 					id:'uploadSoftware',
 					lock:true,
 					content:'url:software_uploadSoftware.do?bizid='+bizid
 				});
 			});
 			
 			$("#catchsoft").click(function(){
 				$.dialog({
 					title:'软件包捕获',
 					width:'600px',
 					height:'320px',
 					id:'catchSoftware',
 					lock:true,
 					content:'url:software_catchSoftware.do?bizid='+bizid
 				});
 			});
 			
 			$("#createVersion").click(function(){
 				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行创建版本");
					return false;
				}
 				var versionname = $("[name='checkboxid']:checked").attr("versionname");//包名
 	       		var versionno = $("[name='checkboxid']:checked").attr("versionno");//版本号
 	       		var appid = $("[name='checkboxid']:checked").attr("appid");//应用ID
 	       		var softparth = $("[name='checkboxid']:checked").attr("softparth");//包的路径
 	       		var softid = $("[name='checkboxid']:checked").attr("softid");//软件包ID
 	       	    
 	       		var params="theForm.NAME="+versionname+"&theForm.APPID="+appid+"&theForm.SOFRTPARTH="+softparth+"&theForm.SOFTID="+softid;
 	       		
 				$.dialog({
 					title:'创建版本',
 					width:'800px',
 					height:'320px',
 					id:'fastCreateVersion',
 					content:"url:software_fastCreateVersion.do?bizid="+bizid+"&"+params
 				});
 			});
 			
 			$("#searchForm").click(function(){
 				$("#theForm").submit();
 			});
 			
 			$("#resetForm").click(function(){
 				$("#NAME").val("");
 				$("#PROVIDERS").val("");
 			});
 			
 			//$("[name=link]").unbind().live("click",function(){
 			//	var file_dir =$(this).attr("filedir");
			//	file_dir = encodeURI(encodeURI(file_dir));
			//	var file_name = $(this).attr("file_name");
			//	file_name = encodeURI(encodeURI(file_name));
			//	location.href = "<%=request.getContextPath()%>/common/loadfile2.jsp?FILE_DIR=" + file_dir + "&FILE_NAME=" + file_name;	
 		//	});
 			
 			$("[name=link]").unbind().live("click",function(){
 				var softid = $(this).attr("softid");
 				location.href = "software_downLoadSoftwareRar.do?softid="+softid;
 			});
 			
 			$("#edit").unbind().live("click",function(){
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行修改");
					return false;
				}
				var softid = "";
				$(":checkbox:checked").each(function(){
					softid+=$(this).attr("softid");
				});

				$.dialog({
	        		id:'editsoftware',
	        		title:'修改软件信息',
	        		width: '900px',
	    			height: '400px',
	    		    lock:true,
	    		    content:'url:software_modSoftwareInfo.do?softid='+softid
	        	});
			});
 			
 			$("#delete").unbind().live("click",function(){
 				if (!confirm("你确定删除吗？"));
 				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项删除");
					return false;
				}
				var softid = "";
				$(":checkbox:checked").each(function(){
					softid+=$(this).attr("softid");
				});
				
				var url = "software_delSoftwareInfo.do?softid="+softid;
				$.ajax({
					type:'post',
					url:url,
					success:function(msg){
						if (msg == 0) {
							alert("删除失败");
						} else{
	            			$("#theForm").submit();
	            		}
					}
				});
			});
 		});
 		
 		$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		})
		
		function saveSoftware(theform){
	       	 $.ajax({
	            type: "post",
	            url: "software_saveSoftwareInfo.do?"+theform,
	            dataType: "json",
	            success : function(data){
		            $("#theForm").submit();
	              }
	          });
	       }
 		function listForm(){
 			$("#theForm").submit();
 		}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="software_listSoftwareInfo" method="post" cssClass="theForm" id="theForm">
<s:hidden name="bizid" id="bizid"></s:hidden>
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">软件管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">软件名称：</label>
				<s:textfield name="tbBusiSoftwareInfoObj.NAME" id="NAME" cssClass="inpt-1 vm"></s:textfield>
				<label class="mgl-20 vm">软件提供者：</label>
				 <s:textfield name="tbBusiSoftwareInfoObj.PROVIDERS" id="PROVIDERS" cssClass="inpt-1 vm"></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" id="searchForm" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-modify" href="javascript:void(0)" id="edit" >修改</a>
				<a class="icon-del" href="javascript:void(0)" id="delete" >删除</a>
				<a class="icon-del" href="javascript:void(0)" id="upsoft" >上传</a>
				<a class="icon-del" href="javascript:void(0)" id="catchsoft" >捕获</a>
				<a class="icon-add" href="javascript:void(0)" id="createVersion" >快速创建版本</a>
			</div>
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th>软件编号:</th>
				   <th onclick="sort(theTable,1,'string')">软件名称</th>                
                   <th onclick="sort(theTable,2,'int')">软件大小</th>
                   <th onclick="sort(theTable,3,'string')">软件提供者</th>
                   <th onclick="sort(theTable,4,'string')">软件制作厂家</th>
                   <th onclick="sort(theTable,5,'date')">上传时间</th>
                    <th onclick="sort(theTable,6,'String')">上传是否成功</th>
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="resultList" id="theBean">
			  		<tr>
			  			<%--<td><input name="checkboxid" type="checkbox" softid="<s:property value="#theBean.ID"/>"/></td>
			  			--%>
			  			<td width="50">
			  			<input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>" versionname='<s:property value="#theBean.NAME"/>'
			  			versionno='<s:property value="#theBean.VERSION"/>' appid='<s:property value="#theBean.APPID"/>' softparth='<s:property value="#theBean.SOFTPARTH"/>' softid='<s:property value="#theBean.ID"/>'/>
			  			</td>
			  			
			  			<td>
			  				<%--<a href="javascript:;" filedir=<s:property value="#theBean.SOFTPARTH"/> file_name=<s:property value="#theBean.NAME"/> id="download" name="link">
								<s:property value="#theBean.NAME"/>
							</a>--%>
							<a href="javascript:;" softid='<s:property value="#theBean.ID"/>' name="link">
			  					<s:property value="#theBean.NAME"/>
			  				</a>
			  			</td>
			  			<td><s:property value="#theBean.SOFTWARE_SIZE"/>M</td>
			  			<td><s:property value="#theBean.PROVIDERS"/></td>
			  			<td><s:property value="#theBean.MANUFACTURERS"/></td>
			  			<td><s:property value="#theBean.UPDATETIME"/></td>
			  			
			  			
			  			<s:if test="#theBean.comparevalue=='true'">
						<td><p style="color:green">成功</p></td>			  			
			  		   </s:if>
			  			<s:else>
			  			  <td><p style="color:red">失败</p></td>	
			  			</s:else>
			  		</tr>
			  	</s:iterator>
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
	</s:form>
</div>	
</body>
