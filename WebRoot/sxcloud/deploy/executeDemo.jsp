<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.sitech.basd.sxcloud.util.ssh.SshConstants"%>
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html style="width:100%; height:100%; overflow:hidden">

<head>
<title>SSH SHOW</title>
<script type="text/javascript" src="../bomc/My97DatePicker/WdatePicker.js"></script>
<script language='javascript'>

<!--	$(function(){-->
<!--		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});-->
<!--		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});-->
<!---->
<!--	})-->
	var TEM_ID = <%= request.getAttribute("TEM_ID")%>;
    var reploy = new Array();
    var i=0;
    var flag = 0;
    function setStatus(){
   	//	 var theForm=document.getElementById("theForm");
     //   var start_time=theForm.STARTTIME.value;
    //    var end_time=theForm.ENDTIME.value;
   //    var checkboxids = document.getElementsByName("checkboxid");
    //   if(checkboxids!=null&&checkboxids.length>0){
     //      var videoid=checkboxids[0].value.split("|");
      //     $.getJSON("depcomm_reploy_process_time.do?videoid="+videoid[1]+"&&start_time="+start_time+"&&end_time="+end_time,{'time':new Date().toString()},function(data){
       //         reploy=data;
        //        foreach();
		 //   });
     //  }
     	var videoid = 1;
     	flag = flag + 1;
     	$.getJSON("depcomm_reploy_process_demo.do?flag=" + flag + "&TEM_ID=" + TEM_ID,{'time':new Date().toString()},function(data){
     		var result='';
     		for(j=0;j<data.length;j++){
     			if(data[j].FLAG==1){
     				data[j].CONTENT = '[root]# ' + data[j].CONTENT;
     			}
     			data[j].CONTENT = data[j].CONTENT + "</br>";
     			$("#cmd").append(data[j].CONTENT);
			}
     	});
    }
    <%-- 
    function foreach(){
        for(i;i<reploy.length;i++){
		  //$("#VIDEOID"+reploy[i].ID).html('<font color=white>'+reploy[i].VIDEOID+'</font>');
		  $("#CONTENT"+reploy[i].ID).html('<font color=white>'+reploy[i].CONTENT+'</font>');
		  if(reploy[i].FLAG=='1'){
		    $("#FLAG"+reploy[i].ID).html('<font color=white>输入命令</font>');
		  }else{
		    $("#FLAG"+reploy[i].ID).html('<font color=white>输出结果</font>');
		  }			      
	      $("#INSERTTIME"+reploy[i].ID).html('<font color=white>'+reploy[i].INSERTTIME+'</font>');
	      timer1(i,reploy.length); 
	      break;   
		}
    }
    --%>
</script>

<style type="text/css">
<!--
#shell { /*background-color: #F7F7F7;*/
	white-space: pre;
	font-size: 10pt;
	color: white;
	font-family: monospace;
}
body {
font-color:white;
background-color: black;
}
-->
</style>
</head>

<body>



<s:form action="reploy_process" method="post">
 <s:hidden name="theForm.ID"></s:hidden>
 <s:hidden name="theForm.VIDEOID"></s:hidden>
<div class="scrollbody">
            <div class="query-form">
            <%-- 
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">开始时间:</td>
                    <td>
                        <html:text name="theForm" property="STARTTIME" size="20"
									readonly="true" styleId="datepicker1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <td class="til">结束时间:</td>
                    <td>
                        <html:text name="theForm" property="ENDTIME" size="20"
									readonly="true" styleId="datepicker2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                    </td>
                    <td><input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" /></td>
                  </tr>
                </table>
                --%>
            </div><!--query-form end -->
			<table width="100%" class="" border="0" cellspacing="0">
			  	
			  <thead>
			  <tr>
<!--				   <td width="10%">bainhao:</td>-->
				   <!-- <th width="10%"><font color=white>录像编号</font></th>-->
				   <td width="62%"><font color=white>------------执行------------</font></td>
<!--				   <td width="10%"><font color=white>类型</font></td>-->
<!--				   <td align="center" width="18%"><font color=white>执行时间</font></td>-->
			  </tr>
			  		
			  </thead>
			  <tbody>
			  	<tr>
			  		<%-- 
						    <!-- <td><div id="VIDEOID<bean:write name="theBean" property="ID"/>"></div></td> -->
						    <td><div align="left" id="CONTENT<s:property value="#theBean.ID"/>"></div></td>
						    <td><div id="FLAG<s:property value="#theBean.ID"/>"></div></td>
                    <td><div align="left" id="INSERTTIME<s:property value="#theBean.ID"/>"></div></td>
                    --%>
<!--                    <td><s:property value="#theBean.ID"/></td>-->
                    <td width="40%">
                    	<div id="cmd">
                    	</div>
                    </td>
                </tr>
			  <%-- 
			   <logic:present name="theForm" property="resultList">
			      <logic:iterate id="theBean" name="theForm" property="resultList" indexId="status">
						<tr>
							
						</tr>
				</logic:iterate>
				</logic:present>
				--%>		  
			  </tbody>
			</table>
		
</div>
</s:form>

<script type="text/javascript">
timer();
 //	function timer(){
 //		var time=document.theForm.STARTTIME.value;
 //		if(time==null||time==0){
 //		}else{
 //       setTimeout("setStatus()",1500);    
 //		}
 //   }
 function timer(){
 	setStatus();
  	setTimeout("timer()",1500);  
 }
</script>
</body>
</html>