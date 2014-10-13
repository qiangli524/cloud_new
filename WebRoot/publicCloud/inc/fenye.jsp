<!--
			JSP页面自动分页。
 -->
<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8"
	import="com.sitech.basd.sxcloud.rsmu.web.util.page.*"%>
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
		int pageNum = 10;//默认显示10个页码
		prePage = curPage - 1;
		nextPage = curPage + 1;
		prePage = prePage <= 0 ? -1 : prePage;
		nextPage = nextPage > pageCount ? -1 : nextPage;
		
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
<%
	if ( prePage > 0 ){
%>
<a href="#" onClick="javascript:gotoPage(<%=prePage%>);return false;">上一页</a>
<%
	} else {
%>
<a href="#none" >上一页</a>
<%
	}
%>

	<%
	for (Integer p : pageNums) {
		if(p == curPage){
	%>
		<a href="javascript:gotoPage(<%=p%>);return false;" class="on1"><%=p%></a>
	<%
		}else{
	%>
		<a href="javascript:void(0);" onclick="javascript:gotoPage(<%=p%>);return false;"><%=p%></a>
	<%
		}
	}
	%>




	<%
		if ( nextPage > 0 ){
	%>
	<a href="javascript:void(0);"
	onClick="javascript:gotoPage(<%=nextPage%>);return false;">下一页</a>
	<%
		} else {
	%> 
		<a href="#none">下一页</a>
	<%
		}
	%> 共<%=pageCount%>页 到第
	<input type="text" id="gopage" name="gopage"
	onkeyup="this.value=this.value.replace(/\D/g,'')">页
	<a href="javascript:void(0);"
	onClick="javascript:go();return false;">GO</a>

	<input type="hidden" name="<%=Pagination.PAGINATOR_FLAG%>"	id="<%=Pagination.PAGINATOR_FLAG%>" value="false">
	<input type="hidden" name="FUNCSID"	value="<%=request.getParameter("FUNCSID")%>">
	<input type="hidden" name="<%=Pagination.PGSZIE%>"	id="<%=Pagination.PGSZIE%>" />
	<input type="hidden" id="pageCount" value="<%=pageCount%>"/>
	<input type="hidden" id="<%=Pagination.CURRENT_PAGE_NO_KEY%>" name="<%=Pagination.CURRENT_PAGE_NO_KEY%>"/>
<script>
<%String formname = request.getParameter("formId");
	if(formname==null || formname.length()<1){
  		formname="";
	}%>
	var formName = "<%=formname%>";
	var theForm;
	if(formName=="")
	{
		theForm = document.forms.item(0);
	}else{
		theForm = eval("document."+formName);
	}
 	
 	function go(){
 		var gopage = document.getElementById("gopage").value;
 		var pageCount = document.getElementById("pageCount").value;
 		if(null == gopage || "" == gopage){
 			alert("您要去哪一页？请填写页码！");
 			return;
 		}
	 	if(gopage > pageCount){
			alert("没有这一页，请重新输入！");
			return;
		}
		gotoPage(gopage);
 	}
 	   
 	function gotoPage(page){
		if( null != page && ""!= page) {
		  document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").value = page ;
		  document.getElementById("<%=Pagination.PAGINATOR_FLAG%>").value = "true";
 		  document.getElementById(formName).submit();
		}
	}

 	function showCurPage(){
 	try{
 		var length=document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").length;
	   if(length>0){
		 document.getElementById("<%=Pagination.CURRENT_PAGE_NO_KEY%>").selectedIndex=<%=(curPage-1)%>;
 		}
 	}catch(erro){}
	   
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