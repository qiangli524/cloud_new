<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'addSoftwareInfo.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改中间件！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条中间件信息");
 	    return false ;
 	    }
 	    theForm.action = 'middleware_modMiddleware.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除中间件信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条中间件信息");
 	    return false ;
 	    }
 	    theForm.action = 'middleware_delMiddleware.do'  
		theForm.submit();
 	}

 	$(function(){
     $("[name='checkboxid']").click(function(){
            var temp1=$(this).val();
            $("[name='checkboxid']").each(function(index){
               var temp2=$(this).val();
               if(temp2!=temp1){
            	      this.checked = false;
                   }
             });
         });
 	 	});

 	$(function(){
		$("[name=link]").unbind().live("click",function(){
			var middleid = $(this).attr("middleid");
			location.href = "middleware_downLoadFile.do?middleid="+middleid;
		});
	});	
 	
</script>
</head>
<body>
<s:form action="middleware_listMiddlewareInfo.do" method="post" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
		<div class="query">
			</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">中间件名称:</td>
                    <td>
                    	<s:textfield name="theForm.NAME" cssClass="txt" id="NAME"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
		          
				<!--<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>-->
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  		<th>选择</th>
				   <th onclick="sort(theTable,1,'string')">中间件编号</th>
				   <th onclick="sort(theTable,2,'string')">中间件名称</th>                
                   <th onclick="sort(theTable,3,'string')">中间件版本</th>
                   <th onclick="sort(theTable,4,'date')">上传时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td> <s:property value="#theBean.ID"/></td>
			  		<td> 
			  			<a href="javascript:;" middleid='<s:property value="#theBean.ID"/>' name="link">
			  				<s:property value="#theBean.NAME"/>
			  			</a>
			  		</td>
			  		<td> <s:property value="#theBean.VERSION"/></td>
			  		<td> <s:property value="#theBean.UPDATETIME"/></td>
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
