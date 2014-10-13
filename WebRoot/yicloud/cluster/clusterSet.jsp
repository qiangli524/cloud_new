<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>

<head>
    <title></title>
    <script type="text/javascript">
	   function configHA(){
	   		 //var ha=window.parent.document.getElementById("right"); 
	   		// ha.contentWindow.goConfigHA(); 
	   		window.parent.configHA();
	   }
	   
	   function configDRS(){
	   		window.parent.configDRS();
	   }
	   
	   function configGroup(){
	   		window.parent.drsGroup();
	   }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
        <div>
            <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
                <tr>
                    <td  align="left">
                        <a href="javascript:configHA();">vSphere HA</a>
                    </td>
                </tr>
                <tr>
                    <td align="left">
                       <a href="javascript:configDRS();">vSphere DRS</a>
                    </td>
                </tr>
                <tr>
                    <td align="right">
                       <a href="javascript:configGroup();">DRS组管理器</a>
                    </td>
                </tr>
				
            </table>
        </div>
    </s:form>
</body>