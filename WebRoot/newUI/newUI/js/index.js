/**
 * Filename: index.js
 * Description: mas system index action
 * Version: 1.0.0
 * Author: kevin.si-tech
 * UpdateBy: kevin 12-8-14 上午11:43
 */

var kDialog;
$(function(){
    $("a.set").click(function(){
        $("div.setSkin").toggle();
		$("div.bullBoard").hide();
    })
	$("a.bulletin").click(function(){
        $("div.bullBoard").toggle();
		$("div.setSkin").hide();
    })
	$("div.bullBoard a.close").click(function(){
		$("a.bulletin").trigger("click");							  
	})
	
    $("a.collect").click(function(e){
        UID_addBookmark("Mobile MAS", "http://172.22.11.181/mas/demo");
        e.preventDefault();
    })
    
       indexGlobal = {
		main: $("#main"),
		mainChild: $("#main").children(".pane"),
		numTip: $("#numTip"),
		leftBtn: $("#arrow-l"),
		rightBtn: $("#arrow-r"),
		screenW: $(window).width(),
        screenH: $(window).height(),
		iconW:$("#main a.indexIcon:first").outerWidth(true),
		iconH:$("#main a.indexIcon:first").outerHeight(true),
		iconWNew:$("#main a.indexIconNew:first").outerWidth(true),
		iconHNew:$("#main a.indexIconNew:first").outerHeight(true)
	}
    UID_indexSlider(indexGlobal.main, indexGlobal.numTip, indexGlobal.leftBtn, indexGlobal.rightBtn);
    $(window).bind("resize", function(){UID_indexSlider(indexGlobal.main, indexGlobal.numTip, indexGlobal.leftBtn, indexGlobal.rightBtn)} );

    
//    UID_indexSlider("#main", "#numTip", "#arrow-l", "#arrow-r");
//    $(window).bind("resize", function(){UID_indexSlider("#main", "#numTip", "#arrow-l", "#arrow-r")} );

     $(document).bind("keyup.left", function(){indexGlobal.leftBtn.trigger("click");});
     $(document).bind("keyup.right", function(){indexGlobal.rightBtn.trigger("click");});

 //   $(document).bind("keyup.left",function(){$("#arrow-l").trigger("click");});
 //   $(document).bind("keyup.right",function(){$("#arrow-r").trigger("click");});
    $("a.indexAdd").click(function(){
		$(this).closest("div.pane").attr("currentPane","true").siblings("div.pane").attr("currentPane","false");
        UID_addIcon("../../jsp/addAppPage.jsp");
    })
	
//	var imageurl= $.cookie('background-image-url');
//	if(null != imageurl){
//		$("div.wrap").css("background-image", "url("+imageurl+")");
//	}
	
    //set background
    UID_setBackground("div.setSkin a");
})

function UID_indexSlider(o, t, l, r){
    var _screenW = indexGlobal.screenW,
    _screenH = indexGlobal.screenH,
    _main = o,
    _mainChild = indexGlobal.mainChild,
    _btnl = l,
    _btnr = r,
    _tip = t,
    _tipChild = _tip.children("li"),
    _flag = 0;

    _main.css("width", _screenW*_mainChild.length+"px");
    _mainChild.css({width:_screenW-240+"px", height: _screenH-235+"px"});
    _tipChild.eq(_flag).addClass("on").siblings().removeClass("on");
    _btnl.css({cursor:"default", opacity:"0.2"});
	if(_mainChild.length<2)_btnr.css({cursor:"default", opacity:"0.2"});

    _btnl.click(function(){
     //   _btnr.css({cursor:"pointer", opacity:"1"});
     //   --_flag;
       		if(_mainChild.length>1){
			_btnr.css({cursor:"pointer", opacity:"1"});
			--_flag;
			if(_flag < 0){
				_flag = 0;
				return false;
			}else{
				if(_flag == 0)_btnl.css({cursor:"default", opacity:"0.2"});
				_tipChild.eq(_flag).addClass("on").siblings().removeClass("on");
				_main.animate({"margin-left": -_screenW*_flag+"px"}, 500);
			}
		}

    });

    _btnr.click(function(){
     //   _btnl.css({cursor:"pointer", opacity:"1"});
    //    ++_flag;
		if(_mainChild.length>1){
			_btnl.css({cursor:"pointer", opacity:"1"});
			++_flag;
			if(_flag >= _mainChild.length){
				_btnr.css({cursor:"default", opacity:"0.2"});
				_flag = _mainChild.length-1;
				return false;
			}else{
				if(_flag == _mainChild.length-1)_btnr.css({cursor:"default", opacity:"0.2"});
				_tipChild.eq(_flag).addClass("on").siblings().removeClass("on");
				_main.animate({"margin-left": -_screenW*_flag+"px"}, 500);
			}
		}

    });

    _tipChild.click(function(){
        var _indexOf = $(this).index();
        _flag = _indexOf;
        $(this).addClass("on").siblings().removeClass("on");
        _main.animate({"margin-left": -_screenW*_flag+"px"}, 500);
    });

    //icon event
 //   UID_iconArray.init(_main, _mainChild, _mainChild.eq(0).children("a:first").outerHeight(true), _mainChild.eq(0).children("a:first").outerWidth(true));
    UID_iconArray.init(_main, _mainChild, indexGlobal.iconH,indexGlobal.iconW);
    UID_iconArray.init(_main, _mainChild, indexGlobal.iconHNew,indexGlobal.iconWNew);

}


var UID_iconArray = {
   // _rightMenu: '<ul id="indexMenu"><li><a href="#">打开应用</a></li><li><a href="#">移动应用到</a><ul><li><a href="#">桌面1</a></li><li><a href="#">桌面2</a></li><li><a href="#">桌面3</a></li><li><a href="#">桌面4</a></li></ul></li><li><a href="#">卸载应用</a></li></ul>',
//	 _rightMenu: '<ul id="indexMenu"><li><a href="#">打开应用</a></li><li><a href="#">卸载应用</a></li></ul>',
	    init: function(wrap, box, h, w){
	    	box.find("a.indexIcon").unbind();
	    	box.find("a.indexIconNew").unbind();
	        this.iconContextMenu(box, h, w);
	        this.iconContextMenuNew(box, h, w);
	        box.each(function(i, n){
	            var _list = $(n).children("a"),
                _n = parseInt($(n).height()/h);
	            for(var _i=0, len = _list.length; _i<len; _i++){
	                var _icon = $(_list[_i]);
	                _icon.css({position:"absolute", left: parseInt(_i/_n)*w+"px", top:_i%_n*h+"px"});
	            }
	        });
			if(wrap.is(":hidden")){
				setTimeout(function(){wrap.fadeIn(1000)}, 200);
			}

	    },

    maxIconLen: function(box,h,w){
        var y = parseInt(box.eq(0).height()/h),
            x = parseInt(box.eq(0).width()/w);
        return x*y;
    },

    iconContextMenu: function(box, h, w){
        $.contextMenu({
            selector: "a.indexIcon",
            callback: function(key, options) {
                switch(key){
                    case "fold1":
                        self.location=options.$trigger.context.href;
                        break;
                    case "fold2-key1":
                        if(box.eq(0).find("a").length < UID_iconArray.maxIconLen(box,h,w)){
                            this.insertBefore(box.eq(0).children("a.indexAdd"));
                            UID_iconArray.init(box,h,w);
                        };
                        break;
                    case "fold2-key2":
                        if(box.eq(1).find("a").length < UID_iconArray.maxIconLen(box,h,w)){
                            this.insertBefore(box.eq(1).children("a.indexAdd"));
                            UID_iconArray.init(box,h,w);
                        };
                        break;
                    case "fold2-key3":
                        if(box.eq(2).find("a").length < UID_iconArray.maxIconLen(box,h,w)){
                            this.insertBefore(box.eq(2).children("a.indexAdd"));
                            UID_iconArray.init(box,h,w);
                        };
                        break;
                    case "fold2-key4":
                        if(box.eq(3).find("a").length < UID_iconArray.maxIconLen(box,h,w)){
                            this.insertBefore(box.eq(3).children("a.indexAdd"));
                            UID_iconArray.init(box,h,w);
                        };
                        break;
                    case "fold2-key5":
                        if(box.eq(4).find("a").length < UID_iconArray.maxIconLen(box,h,w)){
                            this.insertBefore(box.eq(4).children("a.indexAdd"));
                            UID_iconArray.init(box,h,w);
                        };
                        break;
                    case "fold3":
                    	//根据模块ID删除模块
                    	var moudleID = $(this).attr("id");
                    	var twoMoudleID = moudleID.substr(2,8);
                    	if("00000000"==twoMoudleID){
                    		return false;
                    	}else{
                    		UID_delIconForModuleID(moudleID,$(this).find("img").attr("alt"));
							$(this).unbind().remove();
						}
                        break;
                    default :
                        break;
                }
            },
            items: {
                "fold1": {
                    "name": "打开应用"
                },
                /**"fold2": {
                    "name": "移动应用",
                    "items": {
                        "fold2-key1": {"name": "桌面1", disabled: function(key, opt) {
                            if($(this).parent(".pane").index()+1 == 1){
                                return !this.data('cutDisabled');
                            }
                        }},
                        "fold2-key2": {"name": "桌面2", disabled: function(key, opt) {
                            if($(this).parent(".pane").index()+1 == 2){
                                return !this.data('cutDisabled');
                            }
                        }},
                        "fold2-key3": {"name": "桌面3", disabled: function(key, opt) {
                            if($(this).parent(".pane").index()+1 == 3){
                                return !this.data('cutDisabled');
                            }
                        }},
                        "fold2-key4": {"name": "桌面4", disabled: function(key, opt) {
                            if($(this).parent(".pane").index()+1 == 4){
                                return !this.data('cutDisabled');
                            }
                        }},
                        "fold2-key5": {"name": "桌面5", disabled: function(key, opt) {
                            if($(this).parent(".pane").index()+1 == 5){
                                return !this.data('cutDisabled');
                            }
                        }}
                    }
                },**/
                "fold3": {
                    "name": "卸载应用"
                }
            }
        });
    },
    iconContextMenuNew: function(box, h, w){
        $.contextMenu({
            selector: "a.indexIconNew",
            callback: function(key, options) {
                switch(key){
                    case "fold1":
                       // window.open(options.$trigger.context.href);
                    	self.location=options.$trigger.context.href;
                        break;
                    default :
                        break;
                }
            },
            items: {
                "fold1": {
                    "name": "打开应用"
                }
            }
        });
    }
}

function UID_addIcon(url){
    kDialog = $.dialog({
    	id:'addApp',
        lock: true,
        content: 'url:'+'newUI/jsp'+url,
        title: '新增模块',
        width: 800,
        height: 400
		//okVal: '关闭',
		///ok: true
    });
}
//function UID_indexSetting(s, e){
//	var imageurl= $.cookie('background-image-url');
//	if(null != imageurl){
//		$("div.wrap").css("background-image", "url("+imageurl+")");
//	}

//	$(s).click(function(){//setting event
//       $(e).toggle();
//   })
//	UID_setBackground($(e).find("a"));
//}

//function UID_indexHlep(s, e){
//	var imageurl= $.cookie('background-image-url');
//	if(null != imageurl){
//		$("div.wrap").css("background-image", "url("+imageurl+")");
//	}

//	$(s).click(function(){//setting event
//        $(e).toggle();
//    })
//	UID_help($(e).find("a"));
//}

function UID_setBackground(o){
    var _o = $(o);
	_o.click(function(e){
        e.preventDefault();
        if($(this).hasClass("close")){
            $(this).closest(".setSkin").hide();
        }else{
            var _url = $(this).attr("href");
            if(_url == "" || _url == "#"){
                return false;
            }else{
                $("div.wrap").css("background-image", "url("+_url+")");
                setCookie("url",_url);
            }
        }
    })
}
function UID_help(o){
    var _o = $(o);
	_o.click(function(e){
        e.preventDefault();
        if($(this).hasClass("close")){
            $(this).closest(".myhelp").hide();
        }
    })
}

function UID_delIconForModuleID(module_id, module_name){
	$.ajax({
		type: "POST",
		cache: "false",
		async: true,
		dataType: "text",
		url: "newui_delMoudleToDesk.do?moduleId=" + module_id,
		success: function(data) {
			//console.log(data);
			$.dialog({
				title: "提示",
				content: "删除 <b>"+module_name+"应用成功！",
				width:260,
				lock:true,
				ok: function(){
					UID_iconArray.init(indexGlobal.main, indexGlobal.mainChild, indexGlobal.iconH,indexGlobal.iconW);
					UID_iconArray.init(indexGlobal.main, indexGlobal.mainChild, indexGlobal.iconHNew,indexGlobal.iconWNew);
				}
			});
		}
	});
}
//换肤
function setCookie(key,value){
	var today = new Date();
	var expireDay = new Date();
	var msPerMonth = 24*60*60*1000*365;
	expireDay.setTime( today.getTime() + msPerMonth );
	document.cookie = key+"="+value+";expires="+expireDay.toGMTString();
}

$(function(){
	var url = document.cookie.substring(document.cookie.indexOf("url=")+4,document.cookie.length);
	if(url.search(/images/)==-1){
		$("div.wrap").css("background-image", "url(../newUI/images/index-bg.jpg)");	
	}else{
		$("div.wrap").css("background-image", "url("+url+")");
	}
	
})
//修改发布内容
$(function(){
	$(".webtake").mouseover(function(){
  		$(".construction").css("display","block");
	});
	$(".webtake").mouseout(function(){
  		$(".construction").css("display","none");
	});
})
$(document).ready(function(){
	setClickable();	
});
function setClickable(){
		$(".construction").click(function() {
			if(!$("textarea").val()){
				var textarea = '<textarea rows="5" class="fill" cols="47">'+$(".webtake p").html()+'</textarea>';
			}
			//var button	 = '<div><input type="button" value="提交" class="saveButton" /></div>';
			
			$(".webtake p").hide();
			$(this).after(textarea);
			$(this).hide();
			$("textarea").blur(function(){
				$(".webtake p").show().html($(this).val());
				$(".construction").show();
				$(this).remove();
			});
		})	
		
};
