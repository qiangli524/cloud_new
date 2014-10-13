
/**
 * Filename: function.js
 * Description: mas system's function
 * Version: 1.0.0
 * Author: kevin.si-tech
 * UpdateBy: kevin 12-8-24 ����12:50
 */
$(function(){
    //placeholder������
    if(!Modernizr.input.placeholder){
        $('[placeholder]').live("focus", function() {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val("").css("color","#000000");
            }
        }).live("blur",function() {
			var input = $(this);
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
				input.css("color","#BEBEBE");
				input.val(input.attr('placeholder'));
			}
		}).blur();
    }
    //ʱ������
    setInterval(function(){$("#clientTime").html(UID_getMyTime)},1000);
})

//addbookmark
function UID_addBookmark(title,url) {
    if (window.sidebar) {
            window.sidebar.addPanel(title, url,"");
    } else if( document.all ) {
        try{
            window.external.AddFavorite( url, title);
        }catch(e){
            alert( "�����ղ�ʧ�ܣ���ʹ��Ctrl+D�������" );
        }
    } else {
        alert( "��ʹ��Ctrl+D�������" );
    }
}

//ʱ�亯��
function UID_getMyTime() {
    var date = new Date();
    var h = date.getHours();
    var m = date.getMinutes();
    var s = date.getSeconds();
    var timeStr = date.getFullYear() + '\u5E74' + (date.getMonth()+1) + '\u6708' + date.getDate() + '\u65E5';
    timeStr += h+":";
    timeStr += (m < 10 ? '0' : '') + m;
   //timeStr += (s < 10 ? '0' : '') + s;
    return timeStr;
}
//����ַ���Ч��
function UID_subAccordion(o){
    var _box = $(o),
        _box_dt = _box.find("dt");
    _box_dt.addClass("expand");
    _box_dt.next("dd").addClass("none");
    _box_dt.click(function(){
        if($(this).hasClass("expand")){
			$(this).siblings("dt").addClass("expand");
			$(this).siblings("dd").hide();
            $(this).removeClass("expand").next("dd").show();
        }else{
            $(this).addClass("expand").next("dd").hide();
        }
    });
    _box.find("a.expand").toggle(function(){
        $(this).addClass("collapse").next("ul").show();
    },function(){
        $(this).removeClass("collapse").next("ul").hide();
    });
    _box.find("li.flyPanel").hover(function(){
        $(this).find(".flyPanel_box").show();
    },function(){
        $(this).find(".flyPanel_box").hide();
    });
}
//tab�л�Ч��
function UID_tabChange(action, label, exclude){
    var label = label.replace("#",""),
        _label = $("#"+label).children("ul").find("li"),
        _con = $("."+label+"_con"),
        _action = action;
    var _kAction = function(o){
		o.bind(_action, function(e){
	        if (e && e.preventDefault) {e.preventDefault();}else{window.event.returnValue = false;}
	        if($(this).hasClass(exclude)){
				var _aInfo = $("#"+label).children("ul").find("li."+exclude).find("a");
	            //���������
				UID_dailog_3("��ӷ���", _aInfo.attr("href"), 360, true,groupVilidate);
	        }else if( $(this).attr("id") == "memuMore" ){
	            return false;
	            
	        }else if( !$(this).hasClass("on") ){
				$(this).addClass("on").siblings().removeClass("on");
				$("."+label+"_con").eq($(this).index()).show().siblings("."+label+"_con").hide();
			}else{//current channel
				return false;
			}

	    });

	}
	_kAction(_label);


    if($("#"+label).find("#memuMore").length){
        $("#memuMore").hover(function(){
            $(this).find("div.submenu").show();
        }, function(){
            $(this).find("div.submenu").hide();
        })

        $("#memuMore .submenu a").click(function(e){
            e.preventDefault();
            var _txt = $(this).text(),
                _url = $(this).attr("href"),
				_specialLabel,
				_specialCon;
				
            if($("#"+label).find(".specialLabel").length == 0){
				_specialLabel = $("<li class='specialLabel'><a href='"+ _url +"' onclick='UID_showOptMenu(this)'><span>"+ _txt +"</span></a><div class='optMenu none'><a href='javascript:void(0)' class='edit' onclick='UID_kActionEdit(\"addgroup.html\", \"����Ⱥ��\");'>�༭</a><a href='javascript:void(0)' class='del' onclick='UID_kActionDel(this, \""+label+"\", \""+_action+"\")'>ɾ��</a></div></li>");
				_specialCon = $("<div class='main clearfix tabLabel_con none'>5555555555555555555555555555555555555</div>");
				
                $("#memuMore").before(_specialLabel);
                $("."+label+"_con:last").after(_specialCon);
				_kAction(_specialLabel);
				_specialLabel.trigger(_action);
            }else{
                $("#"+label+" .specialLabel").siblings("li").removeClass("on");
                $("#"+label+" .specialLabel a:first").attr("href",_url).text(_txt);
                $("."+label+"_con:last").empty().html("77777777777777777777----------------").show().siblings("."+label+"_con").hide();

            }
        })
    }
}
function UID_showOptMenu(o){
	$(o).next(".optMenu").toggle();
	return false;
}
function UID_kActionEdit(url, title){
	//���������
	$.dialog({
		lock: true,
		content: "url:"+url,
		title: title,
		width: 400,
		height: 200
	});
	return false;
}
function UID_kActionDel(o, label, action){
	$(o).closest(".specialLabel").remove();
	$("."+label+"_con:last").remove();
	$("#"+label).children("ul").children("li").eq(0).trigger(action);
	return false;
}
function UID_addMember(){
	$("#addMember li.memberbox-1_on").each(function(){
		$(this).attr("class","memberbox-1");
	});
	var _left = $("#groupMember").position().left,
		 _top = $("#groupMember").position().top+$("#groupMember").height();
	$("#addMember").css({top:_top+2+"px", left:_left-30+"px"}).show();
	$("#addMember a.closeBtn_js").click(function(){
		$("#addMember").hide();
		$(this).unbind();
		$("#addMember li.memberbox-1").unbind();
	})
	$("#groupMember").html("\u4F60\u5DF2\u9009\u62E9<b>0</b>\u4E2A\u6210\u5458");
	//UID_addMemberItem();
}
function UID_addMemberItem(o){
	//$("#addMember li.memberbox-1").click(function(){
		var _val = parseInt($("#groupMember b").text()),
			_data = $("#groupMemberData").val(),
			_thisId = $(o).attr("id")+",";
			
		if($(o).hasClass("memberbox-1_on")){
			$(o).removeClass("memberbox-1_on");
			$("#groupMember b").text( --_val );
			$("#groupMemberData").val( _data.replace("/"+_thisId+"+/g", "") );
		}
		else{
			$(o).addClass("memberbox-1_on");
			$("#groupMember b").text( ++_val );
			$("#groupMemberData").val(_data? _data+_thisId : _thisId);
		}
	//})
	//$("#addMemberBar-all").click(function(){
		//$("#addMember li.memberbox-1").trigger("click");
	//})
}

//��ҳ���APP�������е�ȫѡЧ��
function UID_selectAll1(self, o){
    if($(self).is(":checked")){$(o).addClass("memberbox-1_on");}
	else{$(o).removeClass("memberbox-1_on");}
}

//������Ϣҳ���е���Ϣ����
function UID_showWarnDetail(o){
	var _parentTr = $(o).closest("tr"),
    	_detail = _parentTr.next("tr");
	_parentTr.siblings("tr.none").attr("data-none","none");
	
    if(_detail.is(":hidden")){
		_parentTr.siblings("tr[data-none='none']").addClass("none");
		_parentTr.siblings("tr[data-none!='none']").find("td a.collapse").attr("class","expand");
		$(o).attr("class","collapse");
        _detail.removeClass("none");
    }else{
		$(o).attr("class","expand");
        _detail.addClass("none");
    }
}
//Ӧ�ù��������л�
function UID_appSlider(o, l, r){
    var _o = $(o),//���DIV
        _w = _o.width(),
        _oChild = _o.find("ul"),
        _btnl = $(l),//��ť
        _btnr = $(r),//�Ұ�ť
        _ele = _o.find("li"),
        _eleW = _o.find("li:first").width(),
		_scrW=_eleW*7;//ÿ�ι����Ŀ�ȣ�������
        _flag =1;//ҳ��
    _oChild.css("width", _eleW*_ele.length+"px");
    _btnl.css({cursor:"default", opacity:"0.2"});
    if(_oChild.width() > _w){
        _btnl.click(function(){
            _btnr.css({cursor:"pointer", opacity:"1"});
			
            if(_flag <= 1){
                _flag = 1;
				_btnl.css({cursor:"default", opacity:"0.2"});				
                return false;
            }else{
                if(_flag ==1)_btnl.css({cursor:"default", opacity:"0.2"});
                _oChild.animate({"margin-left": _curV+_scrW+"px"}, 500);
				_curV=_curV+_scrW;
           }
		   _flag --;
        });
        _btnr.click(function(){
            _btnl.css({cursor:"pointer", opacity:"1"});      
           
            if(_flag >= _ele.length/7){
                _btnr.css({cursor:"default", opacity:"0.2"});
                _flag = _ele.length/7;
                return false;
            }else{
                if(_flag == _ele.length/7) _btnr.css({cursor:"default", opacity:"0.2"});
                _oChild.animate({"margin-left": -_scrW*_flag+"px"}, 500);
				_curV=-_scrW*_flag;
            }
			 _flag ++;
        });
    }else{
        _btnr.css({cursor:"default", opacity:"0.2"});
    }
}
//�����ϼܶ���
function picAnimate(){
	$(".picMove").animate({"margin-left":"-110px"},1000,function (){
		   $(".picMove li:first").appendTo(".picMove"); 
		   $(".picMove").css("margin-left","0")
	  });
	
}
$(function(){
	var picln = $(".picMove").children("li").length;//li �Ŀ��
   var picw = $(".picMove").eq(0).width()*picln;
   $(".picMove").width(picw);//ul�Ŀ��
	t=setInterval("picAnimate()",2000); //����ʱ��
	$(".picMove li").hover(function(){clearInterval(t)}, function(){t = setInterval("picAnimate()",2000);//��껬������ֹͣ���뿪ִ��
}) 
})
//������� �����ذ�ť
$(function(){
	$(".btn-02").live("click",function (){
		$(this).hide();
		$(this).siblings(".btn-03").css({"display":"block"});
		
	});   
})
//��ҳ���Ӧ�ã�ѡ�񷽷�
function UID_addApp(o){
	var currentPane = $(top.document.getElementById("main")).find("div.pane[currentpane='true']");
	if($(o).hasClass("on")){
		$(o).removeClass("on");
	}else{
		var currentPaneA = currentPane.children("a").length+$(o).siblings("li.on").length+1;
		if( currentPaneA<13 ){
			$(o).addClass("on");
		}else{
			alert("����Ӧ���ѳ����޶�����,��ˢ�º��ټ�����ӣ�");
		}
	}
}

//�������
function UID_slider1(o, oc, n){
	var _box = o? $(o) : $("#plugin-2-Box"),
		_boxWrap = _box.children("div"),
		_boxEle = oc? _box.find(oc) : _box.find("div.plugin-2"),
		_boxEleW = _boxEle.eq(0).outerWidth(true),
		_boxEleH = _boxEle.eq(0).outerHeight(true),
		_leftBtn = _box.prev("a.plugin-2-Box-leftBtn"),
		_rightBtn = _box.next("a.plugin-2-Box-rightBtn"),
        _flag = 0,
		_n = n ? n :3;
		
	_box.css({width:_boxEleW*_n+"px", height:_boxEleH+9+"px", "overflow":"hidden", "margin":"0px 0 -3px 5px"});
	_boxWrap.css({width:_boxEleW*_boxEle.length+"px"});
	
    _leftBtn.css({cursor:"default", opacity:"0.2"});
	if(_boxEle.length<=_n)_rightBtn.css({cursor:"default", opacity:"0.2"});
	
	_leftBtn.click(function(){
        _rightBtn.css({cursor:"pointer", opacity:"1"});
        --_flag;
        if(_flag < 0){
            _flag = 0;
            return false;
        }else{
            if(_flag == 0)_leftBtn.css({cursor:"default", opacity:"0.2"});
            _boxEle.eq(_flag).addClass("on").siblings().removeClass("on");
            _boxWrap.animate({"margin-left": -_boxEleW*_flag+"px"}, 500);
        }
		return false;
	})
	
	_rightBtn.click(function(){
        _leftBtn.css({cursor:"pointer", opacity:"1"});
        ++_flag;
        if(_flag > _boxEle.length-_n){
            _rightBtn.css({cursor:"default", opacity:"0.2"});
            _flag = _boxEle.length-_n;
            return false;
        }else{
            if(_flag == _boxEle.length-_n)_rightBtn.css({cursor:"default", opacity:"0.2"});
            _boxEle.eq(_flag).addClass("on").siblings().removeClass("on");
            _boxWrap.animate({"margin-left": -_boxEleW*_flag+"px"}, 500);
        }
		return false;
    });
	
	_boxEle.click(function(){
		var _oc = oc? oc.replace(".","") : "plugin-2";
		if($(this).find(".currentTip").length) $(this).find(".currentTip").show();
		$(this).siblings(oc).find(".currentTip").hide();
		$(this).addClass(_oc+"-click").siblings(oc).removeClass(_oc+"-click");
	})
	_boxEle.eq(0).trigger("click");
}


//�߼�������ʾ����
$(function(){
	$(".btnClick").click(function(){
		$(this).parents("table").next(".btnClickCtn").toggle();
	})
		$(".btnClick2").click(function(){
		$(this).parents("td").find(".btnClickCtn2").show();
	})
	$(".closeBtn").click(function(){
		$(this).parents(".box01").hide();
		})
})
//add group vilidate
function groupVilidate(){
	if($("#groupName").val()==""){
		alert("Ⱥ�����Ʋ���Ϊ�գ�");
		return false;
	}else if($("#groupMemberData").val()==""){
		alert("��ѡ��Ⱥ���Ա��");
		return false;
	}else if($("#groupName").val()!=""){
		var groupName=$("#groupName").val();
		var flag=true;
		//��ҪУ��Ⱥ�������Ƿ��Ѿ�����
		$.ajax({
			type: "POST",
			cache: "false",
			async: false,
			dataType: "text",
			url: "isGroupNameExist.do?groupName="+groupName,
			success: function(msg) {
				if(msg=="1"){
					alert("Ⱥ�������Ѵ��ڣ�");
					flag=false;
				}else{
					addGroup();
				}
			}
		});
		return flag;
	}
}

function UID_dailog_1(title, o, txt){
	var txt = txt || '�÷�������ǰΪ��������ģʽ����Ҫ�ȴ�Լ23���Ӳ��ܿ����������ȷ��Ҫ������';
	$.dialog({
		title:title,
	    content: txt,
		width:400,
		lock:true,
	    ok: function(){
			$("#process-1").show();
	    },
	    cancelVal: '\u5173\u95ED',
	    cancel: true /*Ϊtrue�ȼ���function(){}*/
	});
}
function UID_dailog_2(title, txt, icon, ok, cancel){
	var txt = txt || '\u5904\u7406\u4E2D...',
		okHandler = ok || true,
		cancelHandler = cancel || true,
		icon = icon+".gif";
	$.dialog({
		title:title,
	    content: txt,
		icon: icon,
		width:400,
		lock:true,
	    ok: okHandler,
	    cancelVal: '\u5173\u95ED',
	    cancel: cancelHandler /*Ϊtrue�ȼ���function(){}*/
	});
}
function UID_dailog_3(title, url, w, locked, ok, cancel){
	var txt = txt || '\u52A0\u8F7D\u4E2D...',
		okHandler = ok || true,
		cancelHandler = cancel || true,
		width = w || 400,
		dailog_3 = $.dialog({
			title: title,
			width: width,
			height:200,
			lock: locked,
		    ok: okHandler,
			okVal: '\u786E\u5B9A',
		    cancelVal: '\u53D6\u6D88',
		    cancel: cancelHandler /*Ϊtrue�ȼ���function(){}*/
		});
	$.ajax({
    	url: url,
		datatype: "text/html",
		type: "post",
	    success:function(data){
           
	        dailog_3.content(data);
	    },
	    cache:false
	});
}

function UID_dailog_4(title, url, w,h, locked, ok, cancel){
	var txt = txt || '\u52A0\u8F7D\u4E2D...',
		okHandler = ok || true,
		cancelHandler = cancel || true,
		width = w || 400,
		dailog_4 = $.dialog({
			title: title,
			width: width,
			height:h||200,
			lock: locked,
		    ok: okHandler,
			okVal: '\u786E\u5B9A',
		    cancelVal: '\u53D6\u6D88',
		    cancel: cancelHandler /*Ϊtrue�ȼ���function(){}*/
		});
	$.ajax({
    	url: url,
		datatype: "text/html",
		type: "post",
	    success:function(data){
	        dailog_4.content(data);
	    },
	    cache:false
	});
}
function addGroup(){
	
	var masids="";
	$("li").each(function(){
	  if($(this).hasClass("memberbox-1 memberbox-1_on")){
	  	masids+=$(this).attr("name")+",";
	  }
	});
		$("#addgroupForm").attr("action","addGroupItem.do?masids="+masids);
		$("#addgroupForm").submit();
}

//���ϵ�����չ������Ч��
$(function(){
	$(".tab-title").toggle(function(){
		$(this).find("b").addClass("unfold");
	    $(this).next("table").hide();
		},function(){
		$(this).find("b").removeClass("unfold");
		$(this).next("table").show();	
	})
})
$(function(){
	$(".btn-10").mousedown(function(){
		$(this).css("color","#0a6674")
		})
	$(".btn-10").mouseup(function(){
		$(this).css("color","#fff")
		})
	})
$(function(){
	$(".btnClick03").toggle(
		function(){
			$(".adv-search").show();
			$(this).text("�رո߼���ѯ");
		},function(){
			$(".adv-search").hide();
			$(this).text("�߼���ѯ");
			}
	)
	})
	
$(function(){
	$(".edit").click(function(){
		$txt0=$(this).parents("li").find("b").text();
		$(this).parents("li").find("span").hide();
		$(this).parents("li").find("input").show().focus();
		})

		$(".groupedit input").blur(function(){
			if($(this).val()!=""){
				var $txt=$(this).val();
				var $span=$(this).parents("li").find("span");
				$(this).hide();
				$span.show();
				$span.find("b").text($txt);
				var group_name=$("#group_name_temp").attr("value");
				editGroup(group_name,$txt);
				}
			else{
				$(this).hide();
				var $span=$(this).parents("li").find("span");
				$span.show();
				$span.find("b").text($txt0);
				}
			})
			
		$(".out-a").click(function(){
			//$(this).parents("dl").remove();
			})
		})


$(function(){
	$(".tip-btn").toggle(function(){
		$(this).parents(".table-tip").prev().find(".hide-tr").show();
		},function(){
		$(this).parents(".table-tip").prev().find(".hide-tr").hide();	
			})
	
	

})
//����
$(function(){
	var url = document.cookie.substring(document.cookie.indexOf("url=")+4,document.cookie.length);
	$("body").css("background-image", "url("+url+")");
})

//��ʾ�༭
$(function(){
	$(".userBox").hover(function(){
		$(this).find(".edit02,.del02").show();
					
	},
	function(){
		$(this).find(".edit02,.del02").hide();
	}); 
	
	$(".edit02").toggle(function(){
		$(this).text("���")
		$(this).parents(".userBox").find(".userInfo input").addClass("focus");	
			},function(){
		$(this).text("�༭")
		$(this).parents(".userBox").find(".userInfo input").removeClass("focus");		
				
				});
		
})

//������ۿ�
$(function(){
	$(".info1").click(function(){
		//$(this).next().toggleClass("dis");
		$(this).parents(".info").next().toggle(function(){
			$(this).next().slideUp(),$(this).next().slideDown();
		})
	})
})

//�Ķ�����
$(function(){
	$(".readMore").parent().toggle(function(){
	$(this).prev().prev().hide(); 
	$(this).prev().show(); 
	$(this).text("�����"); 
	$(this).css("color","#2c4a93"); 
},function(){
	$(this).prev().prev().show(); 
	$(this).prev().hide(); 
	$(this).text("�Ķ������");
	})
})
 
//�Ķ�����
function readmore(){
	$(function(){
	$(".readMore").parent().toggle(function(){
	$(this).prev().prev().hide();
	$(this).prev().show(); 
	$(this).text("�����"); 
},function(){
	$(this).prev().prev().show(); 
	$(this).prev().hide(); 
	$(this).text("�Ķ������");
	})
   })
}

//�޸ķ�������
$(document).ready(function(){
	setClickable2();
});
function setClickable2() {
		$(".webtake2 p").click(function() {
			var textarea = '<textarea rows="5" class="color666 pad5 f12" cols="26">'+$(".webtake2 p").html()+'</textarea>';
			var button	 = '<div><input type="button" value="�ύ" class="saveButton" /></div>';
			$(".webtake2 p").hide();
			$(this).after(textarea);
			$(this).hide();
			$("textarea").blur(function(){
				$(".webtake2 p").hide();						
				$(".webtake2 p").show().html($(this).val());
				$(this).remove();
				var CONTENT=$(this).val();
				if(CONTENT.length>140){
				  alert("�������Ϣ���ô���140���֣����������룡");
				}else{
				    CONTENT=encodeURI(CONTENT);
				    CONTENT=encodeURI(CONTENT);
				     var para="CONTENT="+CONTENT+"&TYPE=2";
				     $.ajax({
						type: "POST",
						cache: "false",
						async: true,
						dataType: "text",
						data:para,
						url: "addInformation_index.do",
						success: function(data) {
					}
				});
				}
			});
		})	
};
// �����Ŀ¼  zhangruic by 2013  0114
$(function(){
	$(".app-sidebar dd").hover(function(){
		$(this).find(".subcatalog").stop(false,true).fadeIn()
		},function(){
		$(this).find(".subcatalog").stop(false,true).fadeOut()
	})
})
//�����л�  zhangruic by 2013  0118
$(function(){
		$(".city-s").toggle(function(){
			$(this).next(".city-s-con").show();
		},function(){ 
			$(this).next(".city-s-con").hide();
			$(this).next(".city-d-con").hide();	
		});//.city-s-con  ��ʾ ����
		$(".city-s-con .btn-04").click(function(){
			$(this).parent(".city-s-con").hide();									
		});//���ȷ����ť city-s-con����
		$(".city-s-con .province").hover(function(){
			$(this).find(".city-d-con").stop(false,true).fadeIn();
			$(".city-d-con a").hasClass("on");
		},function(){
			$(this).find(".city-d-con").stop(false,true).fadeOut();
		});//ѡ����п� ��ʾ �����Ĭ��ȫѡ
		
		$(".city-d-con .checkAll").toggle(function(){
			$(this).parent().next("p").find("a").addClass("on");
			$(this).parents(".city-d-con").prev("a").addClass("on");
		},function(){ 
			$(this).parent().next("p").find("a").removeClass("on");
			$(this).parents(".city-d-con").prev("a").removeClass("on");
		});//ѡ�� ȫѡ ��ֵclass  on
		
		$(".city-d-con a").click(function(){
			if($(this).hasClass("on")){
				$(this).removeClass("on");
			}else{
				$(this).addClass("on");
			}
			if($(this).parents(".city-d-con").find("a.on").length>0){
				if(!$(this).parents(".city-d-con").prev("a").hasClass("on"))$(this).parents(".city-d-con").prev("a").addClass("on");
			}
			else{
				$(this).parents(".city-d-con").prev("a").removeClass("on");
			}
		});//���ѡ�������
		
});
