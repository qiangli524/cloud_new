<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/mindmap/css/js-mindmap.css" />
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/js/mindmap/css/js-mindmap-icon.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js" ></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-ui.min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/mindmap/js/raphael-min.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/mindmap/js/js-mindmap.js"></script>
	
	<script type="text/javascript">
		$(function(){
			var id = '<s:property value="tree.id" />';
			var url = "topo_generateTopo.do?tree.id="+id;
			 $.ajax({
					  type:"GET",
		              url:url,
		              data:"text",
		              dataType:"json",
		              async: false,
		              cache: false,
			          success: function(msg){
			        	  	var showlist = $("<ul></ul>");
      	                  	showall(msg.list, showlist);
      	                   $("#div_menu").append(showlist);
		             }
			});
		})
		
		//menu_list为json数据
        //parent为要组合成html的容器
         function showall(menu_list, parent) {
            for (var menu in menu_list) {
                 //如果有子节点，则遍历该子节点
                if (menu_list[menu].nodeList.length >0) {
                     //创建一个子节点li
                     var li = $("<li></li>");
                     //将li的文本设置好，并马上添加一个空白的ul子节点，并且将这个li添加到父亲节点中
                     var type = menu_list[menu].node_type;
                     	if(type ==0){//大数据平台
                    	 $(li).append("<a class='icon-cloud'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==1){//大数据中心
                    	 $(li).append("<a class='icon-dc'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==2){//hadoop集群
                    	$(li).append("<a class='icon-hadoop-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==3){//hbase集群
                    	$(li).append("<a class='icon-hbase-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==4){//hive集群
                    	$(li).append("<a class='icon-hive-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==5){//zookeeper集群
                    	$(li).append("<a class='icon-zookeeper-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==6){//impala集群
                    	$(li).append("<a class='icon-impala-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==7){//storm集群
                    	$(li).append("<a class='icon-storm-cluster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==8){//HDFS文件系统
                    	$(li).append("<a class='icon-hdfs'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==9){//mapReduce
                    	$(li).append("<a class='icon-mapreduce'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }else if(type==10){//服务节点
                    	var serice_type =  menu_list[menu].service_type;
                    	if(serice_type==1){//namenode服务
                    		$(li).append("<a class='icon-namenode'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==2){//datanode服务
                    		$(li).append("<a class='icon-datanode'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==3){//journalNode服务
                    		$(li).append("<a class='icon-journalnode'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==4){//nodeManager服务
                    		$(li).append("<a class='icon-nodemanager'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==5){//reduceManager服务
                    		$(li).append("<a class='icon-reducemanager'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==6){//hmaster服务
                    		$(li).append("<a class='icon-hmaster'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==7){//regionServer服务
                    		$(li).append("<a class='icon-regionserver'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==8){//thirftServer服务
                    		$(li).append("<a class='icon-thriftserver'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==9){//znode服务
                    		$(li).append("<a class='icon-znode'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==10){//hivexx服务
                    		$(li).append("<a class='icon-miniComputer'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}else if (serice_type==11){//impalaxx服务
                    		$(li).append("<a class='icon-miniComputer'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    	}
                    }else if(type==11){//主机节点
                    	$(li).append("<a class='icon-host'>"+menu_list[menu].name+"</a>").append("<ul></ul>").appendTo(parent);
                    }
                    
                    /// alert($(li).html());
                     //将空白的ul作为下一个递归遍历的父亲节点传入
                     showall(menu_list[menu].nodeList, $(li).children().eq(1));
                 }
                 //如果该节点没有子节点，则直接将该节点li以及文本创建好直接添加到父亲节点中
                else {
                	var type = menu_list[menu].node_type;
                    //$("<li></li>").append("<a  class='icon-miniComputer'>"+menu_list[menu].name+"</a>").appendTo(parent);
                    if(type ==0){//大数据平台
                    	$("<li></li>").append("<a  class='icon-cloud'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==1){//大数据中心
                	   $("<li></li>").append("<a  class='icon-dc'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==2){//hadoop集群
                	   $("<li></li>").append("<a  class='icon-hadoop-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==3){//hbase集群
                	   $("<li></li>").append("<a  class='icon-hbase-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==4){//hive集群
                	   $("<li></li>").append("<a  class='icon-hive-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==5){//zookeeper集群
                	   $("<li></li>").append("<a  class='icon-zookeeper-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==6){//impala集群
                	   $("<li></li>").append("<a  class='icon-impala-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==7){//storm集群
                	   $("<li></li>").append("<a  class='icon-storm-cluster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==8){//HDFS文件系统
                	   $("<li></li>").append("<a  class='icon-hdfs'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==9){//mapReduce
                	   $("<li></li>").append("<a  class='icon-mapreduce'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }else if(type==10){//服务节点
                   	var serice_type =  menu_list[menu].service_type;
                   	if(serice_type==1){//namenode服务
                   		$("<li></li>").append("<a  class='icon-namenode'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==2){//datanode服务
                   		$("<li></li>").append("<a  class='icon-datanode'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==3){//journalNode服务
                   		$("<li></li>").append("<a  class='icon-journalnode'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==4){//nodeManager服务
                   		$("<li></li>").append("<a  class='icon-nodemanager'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==5){//reduceManager服务
                   		$("<li></li>").append("<a  class='icon-recudemanager'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==6){//hmaster服务
                   		$("<li></li>").append("<a  class='icon-hmaster'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==7){//regionServer服务
                   		$("<li></li>").append("<a  class='icon-regionserver'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==8){//thirftServer服务
                   		$("<li></li>").append("<a  class='icon-thriftserver'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==9){//znode服务
                   		$("<li></li>").append("<a  class='icon-znode'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==10){//hivexx服务
                   		$("<li></li>").append("<a  class='icon-miniComputer'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}else if (serice_type==11){//impalaxx服务
                   		$("<li></li>").append("<a  class='icon-miniComputer'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   	}
                   }else if(type==11){//主机节点
                	   $("<li></li>").append("<a  class='icon-host'>"+menu_list[menu].name+"</a>").appendTo(parent);
                   }
                 }
             }
         }
	</script>
	<script type="text/javascript" src="/cloud/js/mindmap/js/js-mindmap-init.js"></script>
	<style type="text/css">
		body {
		  background:green;
		}
	</style>
</head>
<body id="div_menu" style="height: 100%">
</body>
</html>