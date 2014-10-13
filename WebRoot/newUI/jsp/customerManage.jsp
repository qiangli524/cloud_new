<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
  	
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="Author" content="si-tech"/>
    <meta name="Keywords" content=""/>
    <meta name="Description" content=""/>
    <title>安徽移动私有云管理平台</title>
    <link rel="stylesheet" type="text/css" href="../newUI/css/reset.css" />
    <link rel="stylesheet" type="text/css" href="../newUI/css/common.css" />
    <link rel="stylesheet" type="text/css" href="../newUI/css/compons.css" />
    <link rel="stylesheet" type="text/css" href="../newUI/css/lhgdialog.css" />
    <link rel="stylesheet" type="text/css" href="../newUI/css/pages.css" />
    <script type="text/javascript" src="../newUI/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../newUI/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/correctPNG.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/modernizr-min.2.6.2.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/scrolltotop.js"></script>
    <script type="text/javascript" src="../newUI/js/plugin/jquery-lhgdialog.js"></script>
    <script type="text/javascript" src="../newUI/js/function.js"></script>
    <script type="text/javascript" src="../newUI/CIC_js/my_function.js"></script>
    <script type="text/javascript">
    	var flag = true;
        $(function(){
            UID_tabChange("click", "#tabLabel", "add");
			
			if($("#topGROUPNAME").val() != ""){
				$("#txtGROUPNAME").val($("#topGROUPNAME").val());
			}
			
			$("#jzdata").hide();
            
            $(window).scroll(function(){

            
            	if(($(window).height() + $(window).scrollTop()) >= $("body").height()){
            		
            		if(flag == true){
            		    $("#jzdata").show();
	            		flag = false;
	            		getJTKHData();
            		}

            	}
            });
            $("#PAGINATIONSTART").attr("value","10");
        });
        
        //异步取得集团客户数据
        function getJTKHData(){
        	var para = "";
       		para ="GROUPNAME="+ $("#topGROUPNAME").val() +"&FirstRownum="+$("#PAGINATIONSTART").val();
			//得到目前选择的分组名
			var addgroupname="";
			$("li").each(function(){
				if($(this).hasClass("on")){
					 addgroupname=$(this).find("a").text();
				}			
			});
			para+="&addgroupname="+addgroupname;
        	$.ajax({
			type: "POST",
			cache: "false",
			async: true,
			dataType: "text",
			url: "ajaxCustomerManager.do?"+para,
			data: "",
			success: function(msg) {
				if(msg == ""){
					return;
				}
					setTimeout("setListValue("+msg+")",1000);
				}
			});
        }
        
        
        //加载数据
        function setListValue(msg){
        	var data = eval(msg);
        	
				var n=0;
				$.each(data, function(i, object) {
					n++;
					var MASID = object.MASID;
					var GROUPNAME=object.GROUPNAME;
					var PIC_PATH = object.PIC_PATH;
					
					//数据加载
		        	var strHtml = "";
					strHtml +="<div class='customerBox-1 fl mr40 mb25'>";
					strHtml +="	<a href='./customerView.do?masid="+ MASID +"&pic_path="+<%=request.getContextPath()%>PIC_PATH+"&group_name="+GROUPNAME+"'><img src='"+ PIC_PATH +"' alt=''></a>";
					strHtml +="	<p class='f13 tc line15'><a href='./customerView.do?masid="+ MASID +"&pic_path="+<%=request.getContextPath()%>PIC_PATH+"&group_name="+GROUPNAME+"'>"+GROUPNAME+"</a></p>";
					strHtml +="</div>";

					//滚动条滚动时
					$("#jtkh").append(strHtml);
		        	$("#jzdata").hide();
		        	
		        	
				});
				var tmp_value=$("#PAGINATIONSTART").val();
				var num=parseInt(tmp_value);
				var lineNum=num+n;
				$("#PAGINATIONSTART").val(lineNum);
				flag = true;
				if(n == 0){
					$("#jzdata").hide();
				}
        }
    </script>
    <!--[if IE 6]>
    <script type="text/javascript">
        window.attachEvent("onload", correctPNG);
    </script>
    <![endif]-->
</head>
  
 <body>
 

<div class="wrap">
    <%@ include file="../common_new/customerHead.jsp" %>
    <div class="main fl clear tabLabel_con">
        <div id="jtkh" class="customerManage clearfix min400" >
			<%List ecinfoList=(List)request.getAttribute("ecinfoList");%>
			<%
				for(int i=0;i<ecinfoList.size();i++){
					HashMap ecinfo=(HashMap)ecinfoList.get(i);
					String pic_path=(String)ecinfo.get("PIC_PATH");
					String group_name=(String)ecinfo.get("GROUPNAME");
					String masid=(String)ecinfo.get("MASID");
					%>
						<div class="customerBox-1 fl mr40 mb25">
                		<a href="./customerView.do?masid=<%=masid %>&pic_path=<%=request.getContextPath()+pic_path%>&group_name=<%=group_name%>"><img src=<%=request.getContextPath()+pic_path%> alt=""></a>
                		<p class="f13 tc line15"><a href="./customerView.do?masid=<%=masid %>&pic_path=<%=request.getContextPath()+pic_path%>&group_name=<%=group_name%>"><%=group_name%></a></p>
            			</div>
					<%
				}
			%>
			
			 	<input type="hidden" id="PAGINATIONSTART" name="PAGINATIONSTART" value="<%=ecinfoList.size()%>" />
        
    </div>
    <div id="jzdata" class="loading-1 f14 mc mt10">正在加载，请稍后...</div>
    </div>
    <div id="yjz" class="footer f14 tc">
        京ICP备05002571&nbsp;|&nbsp;中国移动通信版权所有
    </div>
</div>
</body>
</html>
