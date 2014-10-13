<!--
			JSP页面自动分页。
 -->
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="com.sitech.ssd.bpm.common.*"%>
	
<%
		String path = request.getContextPath();
		Pagination thePage = (Pagination) request.getAttribute(Pagination.PAGER_KEY);
		if (thePage == null){
	thePage = new Pagination(0);
		}
	
		int pageSize = thePage.getPageSize();

		int totalCount = thePage.getTotalCount();

		int pageCount = thePage.getPageCount();

		int curPage = thePage.getCurrentPageNo();

		int prePage = 0;
		int nextPage = 0;
		prePage = curPage - 1;
		nextPage = curPage + 1;
		prePage = prePage <= 0 ? -1 : prePage;
		nextPage = nextPage > pageCount ? -1 : nextPage;
		
		int pageNum=10; //总是显示10条页数
		List<Integer> pageNums = new ArrayList<Integer>();
		int start;
		if(curPage < pageNum){
			 start = 1;
		}else{
		     start = curPage - 10 + 1;
		}
		int num = start;
		for (int i = start; i <= pageCount && i <start + pageNum; i++) {
		     pageNums.add(new Integer(i));
		}
%>
<div class="page-l">
 共&nbsp;<span class="blue"><%=totalCount%></span>&nbsp;条记录
          	<%=curPage%>/<%=pageCount%>页
          
          <span>|</span>
	每页<font><%--<%=pageSize%>--%> 
           <select id="pageSize" onchange="changePageSize(this.value)">
           		<%out.print(pageSize); %>
           		<%
           			if(pageSize==10){
           		%>
           			<option value="10" selected="selected">10</option>
           			<option value="30">30</option>
           			<option value="50">50</option>
           		<%
           			}else if(pageSize==30){
           		%>
           			<option value="10">10</option>
           			<option value="30" selected="selected">30</option>
           			<option value="50">50</option>
           		<%
           			}else if(pageSize==50){
           		%>
           			<option value="10">10</option>
           			<option value="30">30</option>
           			<option value="50" selected="selected">50</option>
           		<%
           			}
           		%>
           		
           </select> 
          	</font>条记录
           </div>
		<%
	if ( prePage > 0 ){
	%>
			  <div class="page-r"><a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">首页</a>&nbsp;<a href="#" class="a-1" onClick="javascript:gotoPage(<%=prePage%>);return false;">前一页</a>
		<%
		} else {
		%>
			<div class="page-r"><a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">首页</a>
		<%
		}
		%>
          <%
        	 for (Integer p : pageNums) {
          		if(p==curPage){
          %>
          		<a class="a-1 on" href="javascript:void(0);"><%=p%></a>&nbsp;
          <%			
          			
          		}else{
          %>
               <a class="a-1" href="javascript:void(0);" onClick="javascript:gotoPage(<%=p%>);return false;"><%=p%></a>&nbsp;
           <%
           		}
           }
           %>
	<%
           if ( nextPage > 0 ){
           %>
		<a href="javascript:void(0);" class="a-1" onClick="javascript:gotoPage(<%=nextPage%>);return false;">后一页</a>&nbsp;<a href="#" onclick="javascript:gotoPage(<%=pageCount%>);return false;">尾页</a>
	<%
	} else {
	%>
		<a href="#" onclick="javascript:gotoPage(<%=pageCount%>);return false;">尾页</a>
	<%
	}
	%>
	&nbsp;
	跳转到<select id="<%=Pagination.CURRENT_PAGE_NO_KEY%>" name="<%=Pagination.CURRENT_PAGE_NO_KEY%>" onChange="gotoPage(this.value);">
          <%
          for (int i = 0; i < pageCount; i++) {
          %>
               <option value="<%=i+1%>" <%if(i+1==curPage){%> selected <%}%>><%=i + 1%></option>
           <%
           }
           %>
           </select>页
           
     </div>
           
          
<input type="hidden" name="<%=Pagination.PAGINATOR_FLAG%>" id="<%=Pagination.PAGINATOR_FLAG%>" value="false">
<input type="hidden" name="FUNCSID" value="<%=request.getParameter("FUNCSID")%>">
<input type="hidden" name="<%=Pagination.PGSZIE%>" id="<%=Pagination.PGSZIE%>"/>
<script>
<%
	String formname = request.getParameter("formId");
	if(formname==null || formname.length()<1){
  		formname="";
	}
%>
	var formName = "<%=formname%>";
	var theForm;
	if(formName=="")
	   theForm = document.forms.item(0);
	else
 	   theForm = eval("document."+formName);
 	function gotoPage(page){
		if( null != page ) {
		  document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").value = page ;
		  document.getElementById("<%=Pagination.PAGINATOR_FLAG%>").value = "true";
		  var pageSize = document.getElementById("pageSize").options[window.document.getElementById("pageSize").selectedIndex].text;
		  document.getElementById("<%=Pagination.PGSZIE%>").value = pageSize;
 		  document.getElementById(formName).submit();
		}
	}

 	function showCurPage(){
	   var length=document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").length;
	   if(length>0){
		 document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").selectedIndex=<%=(curPage-1)%>;
 		}
 	}
	
	function reloadCurPage(){
		var curPage = document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").value;
		if ( curPage != "" ){
			gotoPage(curPage);
		}else{
			gotoPage(1);
		}
		return true;
	}
	showCurPage();
	
	function changePageSize(size){
		document.getElementById("<%=Pagination.PGSZIE%>").value = size;
		document.getElementById(formName).submit();
	}
</script>

