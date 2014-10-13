<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:set name="toJSON" value=":[@com.sitech.basd.yicloud.util.JSONUtil@toJSON(#this)]"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
    mxBasePath = "<%=application.getContextPath()%>/graph";
    mxLanguage = 'zh';
    $(".btn-dialog-cancel").live("click",function(){//当点击对话框中的取消按钮时，关闭该对话框
    	$(".btn-submit").unbind("click");//取消确定的绑定事件，否则会执行多次
      	$(this).parents(".ui-dialog-content").dialog("close");
    });
    $.extend({
        /**
		 * 将复杂js对象转换成url参数
		 */
        param: function( a, traditional ) {
            var s = [],
            add = function( key, value ) {
                // If value is a function, invoke it and return its value
                value = jQuery.isFunction( value ) ? value() : value;
                s[ s.length ] = encodeURIComponent( key ) + "=" + encodeURIComponent( value==null?"":value );
            };
            
            // Set traditional to true for jQuery <= 1.3.2 behavior.
            if ( traditional === undefined ) {
                traditional = jQuery.ajaxSettings.traditional;
            }

            // If an array was passed in, assume that it is an array of form elements.
            if ( jQuery.isArray( a ) || ( a.jquery && !jQuery.isPlainObject( a ) ) ) {
                // Serialize the form elements
                jQuery.each( a, function() {
                    add( this.name, this.value );
                });

            } else {
                // If traditional, encode the "old" way (the way 1.3.2 or older
                // did it), otherwise encode params recursively.
                for ( var prefix in a ) {
                    buildParams( prefix, a[ prefix ], traditional, add );
                }
            }

            // Return the resulting serialization
            return s.join( "&" ).replace( /%20/g, "+" );
        },
        /**
         * 在构建正则表达式之前，将字符串中的关键字符替换，以免影响正则表达式的含义
         */
        preRegexp:function(str){
            return str.replace(/[\"\'\\\*\+\?\.\,\|\&\^\$\(\)\{\}\[\]]/g,function(c){
                return "\\"+c;
            });
        }
    });
    function buildParams( prefix, obj, traditional, add ) {
        if ( jQuery.isArray( obj ) ) {
            // Serialize array item.
            jQuery.each( obj, function( i, v ) {
                if ( traditional || /\[\]$/.test( prefix ) ) {
                    add( prefix, v );
                } else {
                    buildParams( prefix + ( typeof v === "object" || jQuery.isArray(v) ? "[" + i + "]": "" ) , v, traditional, add );
                }
            });

        } else if ( !traditional && obj != null && typeof obj === "object" ) {
            // Serialize object item.
            for ( var name in obj ) {
                buildParams( prefix + "['" + encodeURIComponent(name) + "']", obj[ name ], traditional, add );
            //buildParams( prefix + "." + name, obj[ name ], traditional, add );
            }
        } else {
            // Serialize scalar item.
            add( prefix, obj );
        }
    }
</script>
<script type="text/javascript" src="../js/mxClient.min.js"></script>
<link type="text/css" rel="stylesheet" href="../js/jquery-ui/css/jquery-ui-1.8.15.custom.css">
<link type="text/css" rel="stylesheet" href="../js/jqueryui-patch.css">
<script async="true" type="text/javascript" src="../js/jquery-ui/jquery-ui-1.8.15.custom.js"></script>
<style type="text/css" media="screen">
    div.base {
        position: absolute;
        overflow: hidden;
        white-space: nowrap;
        font-family: Arial;
        font-size: 12px;
    }
    div.base#graph {
        border-style: solid;
        border-color: #F2F2F2;
        border-width: 1px;
        background: url('images/background.jpg') center 150px no-repeat white;
    }
    .mxWindow {
        text-align: left;
    }
</style>