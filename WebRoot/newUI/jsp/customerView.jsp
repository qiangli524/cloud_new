<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="../common/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title><bean:message key="msg.jsp.title" arg0="主页面" /></title>
     <%@ include file="../common/linkNew.jsp"%>
    <script type="text/javascript">
        $(function(){
            UID_subAccordion("div.accordion-1");
            UID_tabChange("click", "#tabLabel", "add");
            UID_tabChange("click", "#label1");
            UID_tabChange("click", "#label2");
        })
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
        <div class="sub">
             <%@ include file="../common_new/customerPhoto.jsp" %>
            <%@ include file="../common_new/customerLeft.jsp" %>
        </div>
        <div class="center ml10 mr10">
            <div class="borderT1 clearfix">&nbsp;</div>
            <div class="borderM1 clearfix clear">
                <table cellpadding="0" cellspacing="0" border="0" class="f12 line20 ml20 mr20">
                    <colgroup>
                        <col width="100">
                        <col width="120">
                        <col width="80">
                        <col width="100">
                        <col width="100">
                        <col width="104">
                    </colgroup>
                    <tr>
                    	<%List masinfoList=(List)request.getAttribute("masinfoList");
                    	 HashMap masinfoMap=(HashMap)masinfoList.get(0);
                    	  %>
                        <td class="tr">集团客户编码：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("ECID_TRUE").toString()%></td>
                        <td class="tr">MASID：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("MASID").toString()%></td>
                        <td class="tr">厂商：</td>
                        <td class="tl color06f"><%=masinfoMap.get("FACTORY_NAME").toString()%></td>
                    </tr>
                    <tr>
                        <td class="tr">开户时间：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("OPEN_DATE").toString()%></td>
                        <td class="tr">归属省：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("PROV_NAME").toString()%></td>
                        <td class="tr">归属地市：</td>
                        <td class="tl color06f "><%=masinfoMap.get("CITY_NAME").toString()%></td>
                    </tr>
                    <tr>
                        <td class="tr">行业：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("IND_NAME")==null?"":masinfoMap.get("IND_NAME").toString()%></td>
                        <td class="tr">版本：</td>
                        <td class="tl color06f custromInfo-borderR"><%=masinfoMap.get("VERSION")==null?"":masinfoMap.get("VERSION").toString()%></td>
                    	
                    </tr>
                  
                </table>
            </div>
            <div class="borderM1 custrom-plugin ">
                <div class="label1 bg01 f12 mb10 clear" id="label1">
                    <ul class="pl10 line20">
                        <li class="on">系统插件</li>
                        <li>企业插件</li>
                        <li>业务插件</li>
                    </ul>
                </div>
                <div class="con1">
                <div class="content1 label1_con min600">
                <!--    <div class="pl10 mt10 mb10 mb10">
                        <div class="searchBox-1">
                            <input type="text" class="fl" value=" ">
                            <button title="搜索">&nbsp;</button>
                        </div>
                    </div> --> 
                    <%List G_apinfo_list=(List)request.getAttribute("G_apinfo");
                    	if(!G_apinfo_list.isEmpty()){
                    		for(int i=0;i<G_apinfo_list.size();i++){
                    			HashMap G_apinfo_map=(HashMap)G_apinfo_list.get(i);
                    			String ap_id=G_apinfo_map.get("APID").toString();
                    			String ap_name=G_apinfo_map.get("APNAME").toString();
                    			String status=G_apinfo_map.get("STATUS").toString();
                    			
                    			String ap_version=G_apinfo_map.get("APVERSION")==null?"":G_apinfo_map.get("APVERSION").toString();
                    			String register_timer=G_apinfo_map.get("REGISTER_TIME")==null?"":G_apinfo_map.get("REGISTER_TIME").toString();
                    			
                    			%>
                    				<div class="plugin-1 f12 ml10 mb10">
                    					<ul>
                    						<li class="img"><img src="../newUI/images/pic-publish/icon_14.jpg" alt=""></li>
                    						<li><em><%=ap_name %></em>
                                				<p class="line20"><%=ap_id %>&nbsp;<%=status %></p>
                                				<%if(("已注册").equals(status)){
                                				%>
                                				<p>插件版本：<%=ap_version %></p>
                                				<p>注册时间：<%=register_timer %></p>
                                				
                                			 
                                			
                                			<%}%>
          
                            				</li>
                        					</ul>
                    				</div>
                    			<%
                    		}
                    	}
                    	else{
                        %>
							<p class="pd10 f12">暂无数据...</p>
						<%
						}
                    	
                     %>
                   
                </div>
                <div class="content1 label1_con min600"  style="display: none;">
                <!--    <div class="pl10 mt10 mb10 mb10">
                        <div class="searchBox-1">
                            <input type="text" class="fl" value=" ">
                            <button title="搜索">&nbsp;</button>
                        </div>
                    </div> --> 
                    <%List E_apinfo_list=(List)request.getAttribute("E_apinfo");
                    	if(!E_apinfo_list.isEmpty()){
                    		for(int i=0;i<E_apinfo_list.size();i++){
                    			HashMap E_apinfo_map=(HashMap)E_apinfo_list.get(i);
                    			String ap_id=E_apinfo_map.get("APID")==null?"":E_apinfo_map.get("APID").toString();
                    			String ap_name=E_apinfo_map.get("APNAME")==null?"":E_apinfo_map.get("APNAME").toString();
                    			String status=E_apinfo_map.get("STATUS")==null?"":E_apinfo_map.get("STATUS").toString();
                    			String ap_com_status=E_apinfo_map.get("AP_COM_STATUS")==null?"":E_apinfo_map.get("AP_COM_STATUS").toString();
                    			String ap_version=E_apinfo_map.get("APVERSION")==null?"":E_apinfo_map.get("APVERSION").toString();
                    			String register_timer=E_apinfo_map.get("REGISTER_TIME")==null?"":E_apinfo_map.get("REGISTER_TIME").toString();
                    			String ap_com=E_apinfo_map.get("AP_COM")==null?"":E_apinfo_map.get("AP_COM").toString();
                    			%>
                    				<div class="plugin-1 f12 ml10 mb10">
                    					<ul>
                    						<li class="img"><img src="../newUI/images/pic-publish/icon_14.jpg" alt=""></li>
                    						<li><em><%=ap_name %>(<%=ap_com%>)</em>
                                				<p class="line20"><%=ap_id %>&nbsp;<%=status %></p>
                                				<%if(("已注册").equals(status)){
                                				%>
                                				<p>插件版本：<%=ap_version %></p>
                                				<p>注册时间：<%=register_timer %></p>
                                				
                                				
                                				<%if(ap_com_status.contains(",")){
                                					String tmp[]=ap_com_status.split(",");
                                					%>
                                					<p>能力状态：<%=ap_com_status%></p>
                                					<%
                                				}else{
                                					if(("停止").equals(ap_com_status)){
                                						%>
                                							 <p>能力状态：<span class="plugin-state2"><%=ap_com_status %></span></p>
                                						<%
                                					}else{
                                					%>
                                						<p>能力状态：<span class="plugin-state1"><%=ap_com_status %></span></p>
                                					<%
                                					}
                                				
                                				} 
                                				} else{
                                					%>
                                					<p>能力状态：未使用</p>
                                					<%
                                				}%>
                            				</li>
                        					</ul>
                    				</div>
                    			<%
                    		}
                    	}
                    	else{
                        %>
							<p class="pd10 f12">暂无数据...</p>
						<%
						}
                     %>
                   
                </div>
                <div class="content1 label1_con min600"  style="display: none;">
                <!--    <div class="pl10 mt10 mb10 mb10">
                        <div class="searchBox-1">
                            <input type="text" class="fl" value=" ">
                            <button title="搜索">&nbsp;</button>
                        </div>
                    </div> --> 
                    <%List B_apinfo_list=(List)request.getAttribute("B_apinfo");
                    	if(!B_apinfo_list.isEmpty()){
                    		for(int i=0;i<B_apinfo_list.size();i++){
                    			HashMap B_apinfo_map=(HashMap)B_apinfo_list.get(i);
                    			String ap_id=B_apinfo_map.get("APID").toString();
                    			String ap_name=B_apinfo_map.get("APNAME").toString();
                    			String status=B_apinfo_map.get("STATUS").toString();
                    			String ap_com_status=B_apinfo_map.get("AP_COM_STATUS").toString();
                    			String ap_version=B_apinfo_map.get("APVERSION")==null?"":B_apinfo_map.get("APVERSION").toString();
                    			String register_timer=B_apinfo_map.get("REGISTER_TIME")==null?"":B_apinfo_map.get("REGISTER_TIME").toString();
                    			String ap_com=B_apinfo_map.get("AP_COM").toString();
                    			%>
                    				<div class="plugin-1 f12 ml10 mb10">
                    					<ul>
                    						<li class="img"><img src="../newUI/images/pic-publish/icon_14.jpg" alt=""></li>
                    						<li><em><%=ap_name %>(<%=ap_com %>)</em>
                                				<p class="line20"><%=ap_id %>&nbsp;<%=status %></p>
                                				<%if(("已注册").equals(status)){
                                				%>
                                				<p>插件版本：<%=ap_version %></p>
                                				<p>注册时间：<%=register_timer %></p>
                                				
                                					<%if(ap_com_status.contains(",")){
                                					String tmp[]=ap_com_status.split(",");
                                					%>
                                					<p>能力状态：<%=ap_com_status%></p>
                                					<%
                                				}else{
                                					if(("停止").equals(ap_com_status)){
                                						%>
                                							 <p>能力状态：<span class="plugin-state2"><%=ap_com_status %></span></p>
                                						<%
                                					}else{
                                					%>
                                						<p>能力状态：<span class="plugin-state1"><%=ap_com_status %></span></p>
                                					<%
                                					}
                                				
                                				} 
                                				} else{
                                					%>
                                					<p>能力状态：未使用</p>
                                					<%
                                				}%>
          
                            				</li>
                        					</ul>
                    				</div>
                    			<%
                    		}
                    	}
                    	else{
                        %>
							<p class="pd10 f12">暂无数据...</p>
						<%
						}
                    	
                     %>
                   
                </div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="borderB1">&nbsp;</div>
            </div>
            <%@ include file="../common_new/customer_right.jsp" %>
			</div>
			<%@ include file="../common_new/customer_down.jsp" %>
            </div>
</body>
</html>
