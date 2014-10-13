 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
	var xmlHttp;
	//ajax
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();                  
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	    }
	}
	function search(){
		document.theForm.submit();
	}
	function submitForm(command){
	
		str = "";
		j=0;
		var length =  document.getElementsByName("theForm.TEMP_TYPE_ID").length;
		
			if (length == 0 ){
		  		alert("当前页中没有记录，不能进行修改，删除")
	          		return;
	    		}
			if(document.theForm.TEMP_TYPE_ID.length>0){
			var aa = document.getElementsByName('theForm.TEMP_TYPE_ID');
				for(i=0;i<aa.length;i++){
		 			if(aa[i].checked){
		 				j++;
		 			}
				}
			}
			else{
				if(document.theForm.TEMP_TYPE_ID.checked){
					j++;
				}
			}
			
			
			if(j==0){
				alert("请选择您需要删除或者修改的条目!");
			}
			else{
				if(command=="delete"){
					if(confirm('确定要删除吗？')){
					document.theForm.action = "file_helpFileDelete.do";
					document.theForm.submit();
					return true;
					}
					return false;
				}
				else{
					if((j==1) && (command=="update")){
						document.theForm.action="file_helpFileUpdate.do";
						document.theForm.submit();
						return true;
					} 
					else{
						alert("一次只能修改一条记录!");
					}
				}
			}
	}

	function addSub(thisForm)
	{
		 thisForm.action = "file_helpFileAdd.do";
	     thisForm.submit();
	}
	function checkAll(e, itemName)
	{
	  var aa = document.getElementsByName(itemName);
	  for (var i=0; i<aa.length; i++)
	   aa[i].checked = e.checked;
	}
	
	
	function reset1(){
		document.theForm.FILE_NAME.value = '';
		document.theForm.TYPE_ID.value = '';
	}
	
	$(function(){
		$("[name='link']").unbind().live("click",function(){
			var fileid = $(this).attr("fileid");
			location.href = "file_downLoadFile.do?fileid="+fileid;
		});
	});
 	</script>
</head>
<body>
<s:form action="file_showHelpFileList.do" method="post" id="theForm">

<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">文件名称:</td>
				    <td>
				    	<s:textfield name="theForm.FILE_NAME" id="FILE_NAME" cssClass="txt"></s:textfield>
				    </td>
				    <td class="til">文件类型选择:</td>
				    <td>
						<s:select list="theForm.FILETYPELIST" name="theForm.TYPE_ID" id="TYPE_ID" listKey="TYPE_ID" listValue="TYPE_NAME" headerKey="" headerValue="-请选择-"></s:select>
				    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:search()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:reset1();" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addSub(document.getElementById('theForm'));return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "submitForm('update');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "submitForm('delete');return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input type="checkbox" onclick="checkAll(this,'TEMP_TYPE_ID')"/></th>
				   <th onclick="sort(theTable,1,'string')">文件编号</th>
				   <th onclick="sort(theTable,2,'string')">文件名称</th>
				   <th onclick="sort(theTable,3,'string')">文件描述</th>
				   <th onclick="sort(theTable,4,'string')">文件类型名</th>
				 <!--   <th>所在目录</th>  -->
				  <th onclick="sort(theTable,5,'float')">当前版本</th> 
				 <!--   <th>历史版本数</th>  --> 
				   <th onclick="sort(theTable,6,'string')">上传者</th>
				   <th onclick="sort(theTable,7,'date')">上传时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input type="checkbox" value=<s:property value="#theBean.FILE_ID"/> name="theForm.TEMP_TYPE_ID" id="TEMP_TYPE_ID"/></td>
			  		<td><s:property value="#theBean.FILE_ID"/> </td>
			  		<td class="tabletd" nowrap style="text-align:left" >
			  			<a href="javascript:;" fileid='<s:property value="#theBean.FILE_ID"/>' name="link">
			  				<s:property value="#theBean.FILE_NAME"/> 
			  			</a>
		    		</td>
		    		
			  		<td><s:property value="#theBean.FILE_DESC"/> </td>
			  		<td><s:property value="#theBean.LIST_NAME"/> </td>
			  		<td><s:property value="#theBean.VERSION"/>
			  		</td>
			  <!-- 		<td>
			  			<s:if test="#theBean.NUM==0">
			  				<s:property value="#theBean.NUM"/>
			  			</s:if>
			  			<s:else>
			  				<a href="javascript:void(0)" onclick="window.open('showHelpFileHisList.do?FILE_ID=<s:property value="#theBean.FILE_ID"/>','历史版本','height=500, width=800,top=100, left=200')">
			    				<s:property value="#theBean.NUM"/>
			    			</a>
			  			</s:else>
			  		</td>
			  		 -->
			  		
			  		<td><s:property value="#theBean.UPDATE_USR"/> </td>
			  		<td><s:property value="#theBean.UPDATE_DATE"/> </td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
