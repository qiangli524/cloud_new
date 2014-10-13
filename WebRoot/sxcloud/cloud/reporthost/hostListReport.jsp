<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.reportvirtual.ReportTreeObj" %>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.resource.TbCloud2HostInfoObj" %>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link_report.jsp"%>
<head>
	<title>物理服务器报表</title>
	<script type="text/javascript" src="/sxcloud/js/FusionCharts.js">    </script>
<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
</script>
</head>
<body leftmargin="0" topmargin="0" class="pop-body scrollbody" >
<s:form action="reporthost_getHostListReport.do" method="post" cssClass="theForm" id="theForm">
 <!--  <div nowrap="true" id="divTree1" style = "float:left;width: 17%;OVERFLOW-Y:scroll; OVERFLOW-X:scroll; height: 100%"></div>-->
<div nowrap="true" id="divTree1" style = "float:left;width: 17%"></div>
<script language="JavaScript">
<%
	ReportTreeObj [] reportTreeObjArray = (ReportTreeObj[])request.getAttribute("reportTreeObjArray");
	String listHost = (String)request.getAttribute("listHost");
%>
/**
 *开始构建树
 */
var tree1=new alai_tree_wx(divTree1)

var root=tree1.root
var n1=root.add("主机报表","report.gif")
<%
int jj = 0 ;
String eq_id = "" ;
String tag = "" ;
if (reportTreeObjArray != null ) {
for (int i = 0;i < reportTreeObjArray.length; i ++){
   if(reportTreeObjArray[i] != null){
  String mName = reportTreeObjArray[i].getMName();
%>
		  var n11 = n1.add("<%=mName%>","host.gif")	
<%
  TbCloud2HostInfoObj[] virs = reportTreeObjArray[i].getHostObjs();
  int HostCount = 0;
  if(virs != null && virs.length > 0){
    HostCount = virs.length;
  }
  if(HostCount > 0){
  for(int j = 0 ; j < virs.length ; j ++ ){
  TbCloud2HostInfoObj tempvObj =  virs[j];
  String v_name = tempvObj.getEq_name() +"(" + tempvObj.getEq_ip() + ")" ;
  String tag1 = mName + ">>" + v_name;
  if(jj==0){
   eq_id = tempvObj.getEq_id();
   tag = mName + ">>" + v_name;
  jj++;
  }
%>
var n111 = n11.add("<a href = 'reporthost_getHostCurrentReportView.do?eq_id=" +"<%=tempvObj.getEq_id()%>&tag=<%=tag1%>"+"' target='xxxx'>" + "<%=v_name%>" + "</a>","host.gif")
<%--          var n111 = n11.addLink("getCurrentReportView.do" + "?hy_id =<%=tempvObj.getVH_ID()%>","<%=v_name%>","","server.gif");--%>
<%
	}
  }
}
}
%>
<%}%>

tree1.target="mainMessage"
/*收缩目录树*/
tree1.expandToTier(2)
var isRun = false;
tree1.oncheck=function changeCheck(srcNode)
{
   if (isRun) return;
       isRun = true;
       changeChildrenCheck(srcNode);
       isRun =false;
}
</script>   
<div style = "float:left;height: 100%;width: 80%; ">
<iframe src="reporthost_listHostReport.do?FUNID=report" name = "xxxx" width="900" height="650" frameborder="0"></iframe>
</div> 
</s:form>
</body>
