/**
 * @Filename: accordion.js
 * @Description: accordion event
 * @Version: 1.0.0
 * @Author: dujj.si-tech
 * @UpdateBy: wanggq 20121119
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <div class="accordion">
 *  <dl>
 *   <dt>title 1</dt>
 *	 <dd>content 1</dd>
 *	 <dt>title 2</dt>
 *	 <dd>content 2</dd>
 *	</dl>
 * </div>
 * $("div.accordion").UED_accordion();
 *
 */
(function($){
    $.fn.UED_accordion = function(option){
        var defaults = {
            //default actived first item
            active: 0,
            activeClass: "on",
            //collapsed self
            collapsible: false,
            //Trigger event
            event: "click",
            //only one to show
            onlyShow: true,
            //
            title: ">div.accordion-title",
            header: "> dl > dt",
            contentBox:"> dl > dd"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        function _clickHandler(o,option){
            var _o = $(o),
                _oCon = _o.next().filter(":first");

            if(_o.hasClass(option.activeClass) && option.collapsible){
                _o.removeClass(option.activeClass);
                _oCon.slideUp();
            }else if(!_o.hasClass(option.activeClass) && option.onlyShow){
                _o.siblings( "."+option.activeClass ).removeClass(option.activeClass).next().filter(":first").slideUp();
                _o.addClass(option.activeClass);
                _oCon.slideDown();
            }
        }
        function _init(o){
            var _o = o;
            var option = _o.data("option");

            _o.find(option.contentBox).hide();
            _o.find(option.header).removeClass(option.activeClass);

            if( option.active>=0 ){
                _o.find( option.header )
                    .eq( option.active ).addClass(option.activeClass)
                    .next().filter(":first").show();
            }

            if(option.height){
                var h = option.height;
                if(option.height=="fill"){
                    h = _o.parent().height();
                }
                var th = _o.find(option.title).outerHeight()||0;
                var hh = (function(){
                    var t = 0;
                    _o.find(option.header).each(function(){
                        t = t+$(this).outerHeight();
                    });
                    return t;
                })();
                _o.find(option.contentBox).height(h-hh-th);
            }

            _o.find(option.header)
                .bind(option.event, function(e){
                    _clickHandler(this,option);
                    e.preventDefault();
                })
        }

        //Interlace change color
        return this.each(function(i, n){
            var  _box = $(n),
                _rel =  strToJson( _box.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            _box.data("option",option);

            _init(_box);
        })
    }
    $(function(){
        $("*[data-role='ued-accordion']").UED_accordion();
    })
})(jQuery);

/**
 * Filename: reset.css
 * Description: init default browser's style
 * Version: 1.0.0
 * Author: VAIO.si-tech
 * UpdateBy: kevin 13-1-9 下午1:20
 */
(function($){
    $.fn.UED_advertise = function(option){
        var defaults = {
            // default shape
            shape: "banner"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };
        // banner plugin
        function advertise1(o){
            var _o = o,
                n = 0,
                _imgs = _o.find(".scroll-img>div"),
                _nums = _o.find(".scroll-num>li"),
                count = _imgs.length,
                _option = _o.data("option");
            _nums.eq(0).addClass("on");
            _imgs.eq(0).siblings().hide();
            _nums.click(function(){
                var i = $(this).index();
                n = i;
                _imgs.eq(i).fadeIn().siblings().hide();
                $(this).addClass("on").siblings().removeClass("on");
            });
            var t = setInterval(function(){
                _nums.eq(n).trigger('click');
                ++n >= count? n=0 : n=n;
            }, _option.speed || 3000);
            _o.hover(function(){clearInterval(t)}, function(){t = setInterval(function(){
                _nums.eq(n++).trigger('click');
                ++n >= count? n=0 : n=n;
            }, _option.speed || 3000);})
            _o.append('<i class="ued-icon ued-icon-cross btnClose"></i>');
            _o.find('i.btnClose').bind("click",function(){
                _o.fadeOut();
            })
        }
        // fixed advertise
        function advertise2(o){
            var _o = o,
                _option = _o.data("option");

            if(_option.width) _o.css("width",_option.width);
            if(_option.height) _o.css("height",_option.height);
            // IE6 fixed bug
            if($.browser.msie && $.browser.version<7.0) {
                $('html').attr("style","background-image:url(about:blank);");
                var _top = _option.top!=undefined? "top:expression(documentElement.scrollTop+"+parseInt(_option.top.replace("px"))+");" : "",
                    _right = _option.right!=undefined? "right:"+_option.right+";" : "",
                    _bottom = _option.bottom!=undefined? "bottom:expression(documentElement.scrollTop+documentElement.clientHeight-this.offsetWidth-"+parseInt(_option.bottom.replace("px"))+");" : "",
                    _left = _option.left!=undefined? "left:"+_option.left+");" : "";
                _o.attr("style",_top+ _right+_bottom+ _left);
            }else{  // !IE6
                if(_option.top!=undefined) _o.css({top:_option.top});
                if(_option.right!=undefined) _o.css({right:_option.right});
                if(_option.bottom!=undefined) _o.css({bottom:_option.bottom});
                if(_option.left!=undefined) _o.css({left:_option.left});
            }
            _o.append('<i class="ued-icon ued-icon-cross btnClose" style="position:absolute; right:0;top:0"></i>');
            _o.find('i.btnClose').bind("click",function(){
                _o.fadeOut();
            })
        }

        //Interlace change color
        return this.each(function(i, n){
            var _rel =  strToJson( $(n).attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            $(n).data("option",option);
            switch(option.shape){
                case "banner":
                    advertise1($(n));
                    break;
                case "advertiseFixed":
                    advertise2($(n));
                    break;
                default:
                    break;
            }
        });
    }

    $(function(){
        $("*[data-role='ued-advertise']").UED_advertise();
    })
})(jQuery);


/**
 * @Filename: ui.js
 * @Description: adWindow
 * @Version: 1.0.0
 * @Author: wangchen.si-tech
 * @UpdateBy: wangchen 20121105
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 *$(".ued-adWindow").UED_adWindow({width:256,height:185});
 *
 */

(function($){
    $.fn.UED_adWindow = function(option){
        var defaults = {
            width:256,
            height:187
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

        //Interlace change color
        return this.each(function(i,n){
            var elem = $(n),
                _rel =  strToJson( elem.attr("data-rel"));

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            setPos();
            $(window).resize(function(){
                setPos();
            })
            $(window).scroll(function() {
                setPos();
            })
            function setPos(){
                var TopY=0,//初始化元素距父元素的距离
                    _initY = $(window).scrollTop() + $(window).height();
                //elem.css({width:option.width+"px", height:option.height+"px", top:_initY-option.height+"px"});
                elem.css({width:option.width+"px", height:option.height+"px"});
                elem.slideDown();

                elem.find(".adWindow-close").click(function() {
                    if(TopY==0){
                        elem.slideUp();
                    }else{
                        $(".ued-adWindow").animate({top: TopY+height}, "slow", function() { $(".ued-adWindow").hide(); });
                    }
                });


                if($.browser.msie && $.browser.version < 7.0){
                    $("html").css("_background","url(about:blank)");
                    elem.css("top", $(window).scrollTop() + $(window).height() - $(".ued-adWindow").outerHeight());
                    // elem.css("bottom", 0);
                }


            }
        })
    }
    // auto init
    $(function(){
        $("*[data-role='ued-adWindow']").UED_adWindow({width:256,height:185});
    })
})(jQuery);

/**
 * @Filename: backToTop.js
 * @Description: backToTop
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121124
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * 引入此文件即可
 * 默认：当屏幕滚动超过一屏的时候出现“返回头部”
 *       文字标示
 *       如果是图片按钮,请传入容器
 *
 */
(function($){
    $.fn.UED_backToTop = function(option){
        var defaults = {
            speed: 600
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

        var $backToTopEle = $(this),
            _rel =  strToJson( $backToTopEle.attr("data-rel")),
        /* 合并默认参数和用户自定义参数 */
            options = $.extend(true,{},defaults,option,_rel);

        $backToTopEle.appendTo($("body"))
            .attr("title", options.title)
            .click(function() {
                $("html, body").animate({ scrollTop: 0 }, options.speed);
            });
        $backToTopFun = function() {
            var st = $(document).scrollTop(),
                winH = $(window).height();
            (st > winH)? $backToTopEle.fadeIn(): $backToTopEle.fadeOut();
            //IE6下的定位
            if (!window.XMLHttpRequest) {
                $backToTopEle.css("top", st + winH - $backToTopEle.height()-100 );
            }
        };
        $(window).bind("scroll", $backToTopFun);
    }

    $(function(){
        $("*[data-role='ued-backtoTop']").UED_backToTop();
    });
})(jQuery);

/*!
 * jQuery contextMenu - Plugin for simple contextMenu handling
 *
 * Version: git-master
 *
 * Authors: Rodney Rehm, Addy Osmani (patches for FF)
 * Web: http://medialize.github.com/jQuery-contextMenu/
 *
 * Licensed under
 *   MIT License http://www.opensource.org/licenses/mit-license
 *   GPL v3 http://opensource.org/licenses/GPL-3.0
 *
 */

(function($, undefined){

    // TODO: -
    // ARIA stuff: menuitem, menuitemcheckbox und menuitemradio
    // create <menu> structure if $.support[htmlCommand || htmlMenuitem] and !opt.disableNative

// determine html5 compatibility
    $.support.htmlMenuitem = ('HTMLMenuItemElement' in window);
    $.support.htmlCommand = ('HTMLCommandElement' in window);
    $.support.eventSelectstart = ("onselectstart" in document.documentElement);
    /* // should the need arise, test for css user-select
     $.support.cssUserSelect = (function(){
     var t = false,
     e = document.createElement('div');

     $.each('Moz|Webkit|Khtml|O|ms|Icab|'.split('|'), function(i, prefix) {
     var propCC = prefix + (prefix ? 'U' : 'u') + 'serSelect',
     prop = (prefix ? ('-' + prefix.toLowerCase() + '-') : '') + 'user-select';

     e.style.cssText = prop + ': text;';
     if (e.style[propCC] == 'text') {
     t = true;
     return false;
     }

     return true;
     });

     return t;
     })();
     */

    if (!$.ui || !$.ui.widget) {
        // duck punch $.cleanData like jQueryUI does to get that remove event
        // https://github.com/jquery/jquery-ui/blob/master/ui/jquery.ui.widget.js#L16-24
        var _cleanData = $.cleanData;
        $.cleanData = function( elems ) {
            for ( var i = 0, elem; (elem = elems[i]) != null; i++ ) {
                try {
                    $( elem ).triggerHandler( "remove" );
                    // http://bugs.jquery.com/ticket/8235
                } catch( e ) {}
            }
            _cleanData( elems );
        };
    }

    var // currently active contextMenu trigger
        $currentTrigger = null,
    // is contextMenu initialized with at least one menu?
        initialized = false,
    // window handle
        $win = $(window),
    // number of registered menus
        counter = 0,
    // mapping selector to namespace
        namespaces = {},
    // mapping namespace to options
        menus = {},
    // custom command type handlers
        types = {},
    // default values
        defaults = {
            // selector of contextMenu trigger
            selector: null,
            // where to append the menu to
            appendTo: null,
            // method to trigger context menu ["right", "left", "hover"]
            trigger: "right",
            // hide menu when mouse leaves trigger / menu elements
            autoHide: false,
            // ms to wait before showing a hover-triggered context menu
            delay: 200,
            // determine position to show menu at
            determinePosition: function($menu) {
                // position to the lower middle of the trigger element
                if ($.ui && $.ui.position) {
                    // .position() is provided as a jQuery UI utility
                    // (...and it won't work on hidden elements)
                    $menu.css('display', 'block').position({
                        my: "center top",
                        at: "center bottom",
                        of: this,
                        offset: "0 5",
                        collision: "fit"
                    }).css('display', 'none');
                } else {
                    // determine contextMenu position
                    var offset = this.offset();
                    offset.top += this.outerHeight();
                    offset.left += this.outerWidth() / 2 - $menu.outerWidth() / 2;
                    $menu.css(offset);
                }
            },
            // position menu
            position: function(opt, x, y) {
                var $this = this,
                    offset;
                // determine contextMenu position
                if (!x && !y) {
                    opt.determinePosition.call(this, opt.$menu);
                    return;
                } else if (x === "maintain" && y === "maintain") {
                    // x and y must not be changed (after re-show on command click)
                    offset = opt.$menu.position();
                } else {
                    // x and y are given (by mouse event)
                    offset = {top: y, left: x};
                }

                // correct offset if viewport demands it
                var bottom = $win.scrollTop() + $win.height(),
                    right = $win.scrollLeft() + $win.width(),
                    height = opt.$menu.height(),
                    width = opt.$menu.width();

                if (offset.top + height > bottom) {
                    offset.top -= height;
                }

                if (offset.left + width > right) {
                    offset.left -= width;
                }

                opt.$menu.css(offset);
            },
            // position the sub-menu
            positionSubmenu: function($menu) {
                if ($.ui && $.ui.position) {
                    // .position() is provided as a jQuery UI utility
                    // (...and it won't work on hidden elements)
                    $menu.css('display', 'block').position({
                        my: "left top",
                        at: "right top",
                        of: this,
                        collision: "flipfit fit"
                    }).css('display', '');
                } else {
                    // determine contextMenu position
                    var offset = {
                        top: 0,
                        left: this.outerWidth()
                    };
                    $menu.css(offset);
                }
            },
            // offset to add to zIndex
            zIndex: 1,
            // show hide animation settings
            animation: {
                duration: 50,
                show: 'slideDown',
                hide: 'slideUp'
            },
            // events
            events: {
                show: $.noop,
                hide: $.noop
            },
            // default callback
            callback: null,
            // list of contextMenu items
            items: {}
        },
    // mouse position for hover activation
        hoveract = {
            timer: null,
            pageX: null,
            pageY: null
        },
    // determine zIndex
        zindex = function($t) {
            var zin = 0,
                $tt = $t;

            while (true) {
                zin = Math.max(zin, parseInt($tt.css('z-index'), 10) || 0);
                $tt = $tt.parent();
                if (!$tt || !$tt.length || "html body".indexOf($tt.prop('nodeName').toLowerCase()) > -1 ) {
                    break;
                }
            }

            return zin;
        },
    // event handlers
        handle = {
            // abort anything
            abortevent: function(e){
                e.preventDefault();
                e.stopImmediatePropagation();
            },

            // contextmenu show dispatcher
            contextmenu: function(e) {
                var $this = $(this);

                // disable actual context-menu
                e.preventDefault();
                e.stopImmediatePropagation();

                // abort native-triggered events unless we're triggering on right click
                if (e.data.trigger != 'right' && e.originalEvent) {
                    return;
                }

                if (!$this.hasClass('context-menu-disabled')) {
                    // theoretically need to fire a show event at <menu>
                    // http://www.whatwg.org/specs/web-apps/current-work/multipage/interactive-elements.html#context-menus
                    // var evt = jQuery.Event("show", { data: data, pageX: e.pageX, pageY: e.pageY, relatedTarget: this });
                    // e.data.$menu.trigger(evt);

                    $currentTrigger = $this;
                    if (e.data.build) {
                        var built = e.data.build($currentTrigger, e);
                        // abort if build() returned false
                        if (built === false) {
                            return;
                        }

                        // dynamically build menu on invocation
                        e.data = $.extend(true, {}, defaults, e.data, built || {});

                        // abort if there are no items to display
                        if (!e.data.items || $.isEmptyObject(e.data.items)) {
                            // Note: jQuery captures and ignores errors from event handlers
                            if (window.console) {
                                (console.error || console.log)("No items specified to show in contextMenu");
                            }

                            throw new Error('No Items sepcified');
                        }

                        // backreference for custom command type creation
                        e.data.$trigger = $currentTrigger;

                        op.create(e.data);
                    }
                    // show menu
                    op.show.call($this, e.data, e.pageX, e.pageY);
                }
            },
            // contextMenu left-click trigger
            click: function(e) {
                e.preventDefault();
                e.stopImmediatePropagation();
                $(this).trigger($.Event("contextmenu", { data: e.data, pageX: e.pageX, pageY: e.pageY }));
            },
            // contextMenu right-click trigger
            mousedown: function(e) {
                // register mouse down
                var $this = $(this);

                // hide any previous menus
                if ($currentTrigger && $currentTrigger.length && !$currentTrigger.is($this)) {
                    $currentTrigger.data('contextMenu').$menu.trigger('contextmenu:hide');
                }

                // activate on right click
                if (e.button == 2) {
                    $currentTrigger = $this.data('contextMenuActive', true);
                }
            },
            // contextMenu right-click trigger
            mouseup: function(e) {
                // show menu
                var $this = $(this);
                if ($this.data('contextMenuActive') && $currentTrigger && $currentTrigger.length && $currentTrigger.is($this) && !$this.hasClass('context-menu-disabled')) {
                    e.preventDefault();
                    e.stopImmediatePropagation();
                    $currentTrigger = $this;
                    $this.trigger($.Event("contextmenu", { data: e.data, pageX: e.pageX, pageY: e.pageY }));
                }

                $this.removeData('contextMenuActive');
            },
            // contextMenu hover trigger
            mouseenter: function(e) {
                var $this = $(this),
                    $related = $(e.relatedTarget),
                    $document = $(document);

                // abort if we're coming from a menu
                if ($related.is('.context-menu-list') || $related.closest('.context-menu-list').length) {
                    return;
                }

                // abort if a menu is shown
                if ($currentTrigger && $currentTrigger.length) {
                    return;
                }

                hoveract.pageX = e.pageX;
                hoveract.pageY = e.pageY;
                hoveract.data = e.data;
                $document.on('mousemove.contextMenuShow', handle.mousemove);
                hoveract.timer = setTimeout(function() {
                    hoveract.timer = null;
                    $document.off('mousemove.contextMenuShow');
                    $currentTrigger = $this;
                    $this.trigger($.Event("contextmenu", { data: hoveract.data, pageX: hoveract.pageX, pageY: hoveract.pageY }));
                }, e.data.delay );
            },
            // contextMenu hover trigger
            mousemove: function(e) {
                hoveract.pageX = e.pageX;
                hoveract.pageY = e.pageY;
            },
            // contextMenu hover trigger
            mouseleave: function(e) {
                // abort if we're leaving for a menu
                var $related = $(e.relatedTarget);
                if ($related.is('.context-menu-list') || $related.closest('.context-menu-list').length) {
                    return;
                }

                try {
                    clearTimeout(hoveract.timer);
                } catch(e) {}

                hoveract.timer = null;
            },

            // click on layer to hide contextMenu
            layerClick: function(e) {
                var $this = $(this),
                    root = $this.data('contextMenuRoot'),
                    mouseup = false,
                    button = e.button,
                    x = e.pageX,
                    y = e.pageY,
                    target,
                    offset,
                    selectors;

                e.preventDefault();
                e.stopImmediatePropagation();

                setTimeout(function() {
                    var $window, hideshow, possibleTarget;
                    // test if we need to reposition the menu
                    if ((root.trigger == 'left' && button == 0) || (root.trigger == 'right' && button == 2)) {
                        if (document.elementFromPoint) {
                            root.$layer.hide();
                            target = document.elementFromPoint(x - $win.scrollLeft(), y - $win.scrollTop());
                            root.$layer.show();

                            if (root.$trigger.is(target) || root.$trigger.has(target).length) {
                                root.position.call(root.$trigger, root, x, y);
                                return;
                            }
                        } else {
                            offset = root.$trigger.offset();
                            $window = $(window);
                            // while this looks kinda awful, it's the best way to avoid
                            // unnecessarily calculating any positions
                            offset.top += $window.scrollTop();
                            if (offset.top <= e.pageY) {
                                offset.left += $window.scrollLeft();
                                if (offset.left <= e.pageX) {
                                    offset.bottom = offset.top + root.$trigger.outerHeight();
                                    if (offset.bottom >= e.pageY) {
                                        offset.right = offset.left + root.$trigger.outerWidth();
                                        if (offset.right >= e.pageX) {
                                            // reposition
                                            root.position.call(root.$trigger, root, x, y);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (target && target.length) {
                        target.contextMenu({x: x, y: y});
                    } else {
                        // TODO: it would be nice if we could prevent animations here
                        root.$menu.trigger('contextmenu:hide');
                    }
                }, 50);
            },
            // key handled :hover
            keyStop: function(e, opt) {
                if (!opt.isInput) {
                    e.preventDefault();
                }

                e.stopPropagation();
            },
            key: function(e) {
                var opt = $currentTrigger.data('contextMenu') || {};

                switch (e.keyCode) {
                    case 9:
                    case 38: // up
                        handle.keyStop(e, opt);
                        // if keyCode is [38 (up)] or [9 (tab) with shift]
                        if (opt.isInput) {
                            if (e.keyCode == 9 && e.shiftKey) {
                                e.preventDefault();
                                opt.$selected && opt.$selected.find('input, textarea, select').blur();
                                opt.$menu.trigger('prevcommand');
                                return;
                            } else if (e.keyCode == 38 && opt.$selected.find('input, textarea, select').prop('type') == 'checkbox') {
                                // checkboxes don't capture this key
                                e.preventDefault();
                                return;
                            }
                        } else if (e.keyCode != 9 || e.shiftKey) {
                            opt.$menu.trigger('prevcommand');
                            return;
                        }
                    // omitting break;

                    // case 9: // tab - reached through omitted break;
                    case 40: // down
                        handle.keyStop(e, opt);
                        if (opt.isInput) {
                            if (e.keyCode == 9) {
                                e.preventDefault();
                                opt.$selected && opt.$selected.find('input, textarea, select').blur();
                                opt.$menu.trigger('nextcommand');
                                return;
                            } else if (e.keyCode == 40 && opt.$selected.find('input, textarea, select').prop('type') == 'checkbox') {
                                // checkboxes don't capture this key
                                e.preventDefault();
                                return;
                            }
                        } else {
                            opt.$menu.trigger('nextcommand');
                            return;
                        }
                        break;

                    case 37: // left
                        handle.keyStop(e, opt);
                        if (opt.isInput || !opt.$selected || !opt.$selected.length) {
                            break;
                        }

                        if (!opt.$selected.parent().hasClass('context-menu-root')) {
                            var $parent = opt.$selected.parent().parent();
                            opt.$selected.trigger('contextmenu:blur');
                            opt.$selected = $parent;
                            return;
                        }
                        break;

                    case 39: // right
                        handle.keyStop(e, opt);
                        if (opt.isInput || !opt.$selected || !opt.$selected.length) {
                            break;
                        }

                        var itemdata = opt.$selected.data('contextMenu') || {};
                        if (itemdata.$menu && opt.$selected.hasClass('context-menu-submenu')) {
                            opt.$selected = null;
                            itemdata.$selected = null;
                            itemdata.$menu.trigger('nextcommand');
                            return;
                        }
                        break;

                    case 35: // end
                    case 36: // home
                        if (opt.$selected && opt.$selected.find('input, textarea, select').length) {
                            return;
                        } else {
                            (opt.$selected && opt.$selected.parent() || opt.$menu)
                                .children(':not(.disabled, .not-selectable)')[e.keyCode == 36 ? 'first' : 'last']()
                                .trigger('contextmenu:focus');
                            e.preventDefault();
                            return;
                        }
                        break;

                    case 13: // enter
                        handle.keyStop(e, opt);
                        if (opt.isInput) {
                            if (opt.$selected && !opt.$selected.is('textarea, select')) {
                                e.preventDefault();
                                return;
                            }
                            break;
                        }
                        opt.$selected && opt.$selected.trigger('mouseup');
                        return;

                    case 32: // space
                    case 33: // page up
                    case 34: // page down
                        // prevent browser from scrolling down while menu is visible
                        handle.keyStop(e, opt);
                        return;

                    case 27: // esc
                        handle.keyStop(e, opt);
                        opt.$menu.trigger('contextmenu:hide');
                        return;

                    default: // 0-9, a-z
                        var k = (String.fromCharCode(e.keyCode)).toUpperCase();
                        if (opt.accesskeys[k]) {
                            // according to the specs accesskeys must be invoked immediately
                            opt.accesskeys[k].$node.trigger(opt.accesskeys[k].$menu
                                ? 'contextmenu:focus'
                                : 'mouseup'
                            );
                            return;
                        }
                        break;
                }
                // pass event to selected item,
                // stop propagation to avoid endless recursion
                e.stopPropagation();
                opt.$selected && opt.$selected.trigger(e);
            },

            // select previous possible command in menu
            prevItem: function(e) {
                e.stopPropagation();
                var opt = $(this).data('contextMenu') || {};

                // obtain currently selected menu
                if (opt.$selected) {
                    var $s = opt.$selected;
                    opt = opt.$selected.parent().data('contextMenu') || {};
                    opt.$selected = $s;
                }

                var $children = opt.$menu.children(),
                    $prev = !opt.$selected || !opt.$selected.prev().length ? $children.last() : opt.$selected.prev(),
                    $round = $prev;

                // skip disabled
                while ($prev.hasClass('disabled') || $prev.hasClass('not-selectable')) {
                    if ($prev.prev().length) {
                        $prev = $prev.prev();
                    } else {
                        $prev = $children.last();
                    }
                    if ($prev.is($round)) {
                        // break endless loop
                        return;
                    }
                }

                // leave current
                if (opt.$selected) {
                    handle.itemMouseleave.call(opt.$selected.get(0), e);
                }

                // activate next
                handle.itemMouseenter.call($prev.get(0), e);

                // focus input
                var $input = $prev.find('input, textarea, select');
                if ($input.length) {
                    $input.focus();
                }
            },
            // select next possible command in menu
            nextItem: function(e) {
                e.stopPropagation();
                var opt = $(this).data('contextMenu') || {};

                // obtain currently selected menu
                if (opt.$selected) {
                    var $s = opt.$selected;
                    opt = opt.$selected.parent().data('contextMenu') || {};
                    opt.$selected = $s;
                }

                var $children = opt.$menu.children(),
                    $next = !opt.$selected || !opt.$selected.next().length ? $children.first() : opt.$selected.next(),
                    $round = $next;

                // skip disabled
                while ($next.hasClass('disabled') || $next.hasClass('not-selectable')) {
                    if ($next.next().length) {
                        $next = $next.next();
                    } else {
                        $next = $children.first();
                    }
                    if ($next.is($round)) {
                        // break endless loop
                        return;
                    }
                }

                // leave current
                if (opt.$selected) {
                    handle.itemMouseleave.call(opt.$selected.get(0), e);
                }

                // activate next
                handle.itemMouseenter.call($next.get(0), e);

                // focus input
                var $input = $next.find('input, textarea, select');
                if ($input.length) {
                    $input.focus();
                }
            },

            // flag that we're inside an input so the key handler can act accordingly
            focusInput: function(e) {
                var $this = $(this).closest('.context-menu-item'),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                root.$selected = opt.$selected = $this;
                root.isInput = opt.isInput = true;
            },
            // flag that we're inside an input so the key handler can act accordingly
            blurInput: function(e) {
                var $this = $(this).closest('.context-menu-item'),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                root.isInput = opt.isInput = false;
            },

            // :hover on menu
            menuMouseenter: function(e) {
                var root = $(this).data().contextMenuRoot;
                root.hovering = true;
            },
            // :hover on menu
            menuMouseleave: function(e) {
                var root = $(this).data().contextMenuRoot;
                if (root.$layer && root.$layer.is(e.relatedTarget)) {
                    root.hovering = false;
                }
            },

            // :hover done manually so key handling is possible
            itemMouseenter: function(e) {
                var $this = $(this),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                root.hovering = true;

                // abort if we're re-entering
                if (e && root.$layer && root.$layer.is(e.relatedTarget)) {
                    e.preventDefault();
                    e.stopImmediatePropagation();
                }

                // make sure only one item is selected
                (opt.$menu ? opt : root).$menu
                    .children('.hover').trigger('contextmenu:blur');

                if ($this.hasClass('disabled') || $this.hasClass('not-selectable')) {
                    opt.$selected = null;
                    return;
                }

                $this.trigger('contextmenu:focus');
            },
            // :hover done manually so key handling is possible
            itemMouseleave: function(e) {
                var $this = $(this),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                if (root !== opt && root.$layer && root.$layer.is(e.relatedTarget)) {
                    root.$selected && root.$selected.trigger('contextmenu:blur');
                    e.preventDefault();
                    e.stopImmediatePropagation();
                    root.$selected = opt.$selected = opt.$node;
                    return;
                }

                $this.trigger('contextmenu:blur');
            },
            // contextMenu item click
            itemClick: function(e) {
                var $this = $(this),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot,
                    key = data.contextMenuKey,
                    callback;

                // abort if the key is unknown or disabled or is a menu
                if (!opt.items[key] || $this.is('.disabled, .context-menu-submenu, .context-menu-separator, .not-selectable')) {
                    return;
                }

                e.preventDefault();
                e.stopImmediatePropagation();

                if ($.isFunction(root.callbacks[key]) && Object.prototype.hasOwnProperty.call(root.callbacks, key)) {
                    // item-specific callback
                    callback = root.callbacks[key];
                } else if ($.isFunction(root.callback)) {
                    // default callback
                    callback = root.callback;
                } else {
                    // no callback, no action
                    return;
                }

                // hide menu if callback doesn't stop that
                if (callback.call(root.$trigger, key, root) !== false) {
                    root.$menu.trigger('contextmenu:hide');
                } else if (root.$menu.parent().length) {
                    op.update.call(root.$trigger, root);
                }
            },
            // ignore click events on input elements
            inputClick: function(e) {
                e.stopImmediatePropagation();
            },

            // hide <menu>
            hideMenu: function(e, data) {
                var root = $(this).data('contextMenuRoot');
                op.hide.call(root.$trigger, root, data && data.force);
            },
            // focus <command>
            focusItem: function(e) {
                e.stopPropagation();
                var $this = $(this),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                $this.addClass('hover')
                    .siblings('.hover').trigger('contextmenu:blur');

                // remember selected
                opt.$selected = root.$selected = $this;

                // position sub-menu - do after show so dumb $.ui.position can keep up
                if (opt.$node) {
                    root.positionSubmenu.call(opt.$node, opt.$menu);
                }
            },
            // blur <command>
            blurItem: function(e) {
                e.stopPropagation();
                var $this = $(this),
                    data = $this.data(),
                    opt = data.contextMenu,
                    root = data.contextMenuRoot;

                $this.removeClass('hover');
                opt.$selected = null;
            }
        },
    // operations
        op = {
            show: function(opt, x, y) {
                var $trigger = $(this),
                    offset,
                    css = {};

                // hide any open menus
                $('#context-menu-layer').trigger('mousedown');

                // backreference for callbacks
                opt.$trigger = $trigger;

                // show event
                if (opt.events.show.call($trigger, opt) === false) {
                    $currentTrigger = null;
                    return;
                }

                // create or update context menu
                op.update.call($trigger, opt);

                // position menu
                opt.position.call($trigger, opt, x, y);

                // make sure we're in front
                if (opt.zIndex) {
                    css.zIndex = zindex($trigger) + opt.zIndex;
                }

                // add layer
                op.layer.call(opt.$menu, opt, css.zIndex);

                // adjust sub-menu zIndexes
                opt.$menu.find('ul').css('zIndex', css.zIndex + 1);

                // position and show context menu
                opt.$menu.css( css )[opt.animation.show](opt.animation.duration);
                // make options available and set state
                $trigger
                    .data('contextMenu', opt)
                    .addClass("context-menu-active");

                // register key handler
                $(document).off('keydown.contextMenu').on('keydown.contextMenu', handle.key);
                // register autoHide handler
                if (opt.autoHide) {
                    // mouse position handler
                    $(document).on('mousemove.contextMenuAutoHide', function(e) {
                        // need to capture the offset on mousemove,
                        // since the page might've been scrolled since activation
                        var pos = $trigger.offset();
                        pos.right = pos.left + $trigger.outerWidth();
                        pos.bottom = pos.top + $trigger.outerHeight();

                        if (opt.$layer && !opt.hovering && (!(e.pageX >= pos.left && e.pageX <= pos.right) || !(e.pageY >= pos.top && e.pageY <= pos.bottom))) {
                            // if mouse in menu...
                            opt.$menu.trigger('contextmenu:hide');
                        }
                    });
                }
            },
            hide: function(opt, force) {
                var $trigger = $(this);
                if (!opt) {
                    opt = $trigger.data('contextMenu') || {};
                }

                // hide event
                if (!force && opt.events && opt.events.hide.call($trigger, opt) === false) {
                    return;
                }

                // remove options and revert state
                $trigger
                    .removeData('contextMenu')
                    .removeClass("context-menu-active");

                if (opt.$layer) {
                    // keep layer for a bit so the contextmenu event can be aborted properly by opera
                    setTimeout((function($layer) {
                        return function(){
                            $layer.remove();
                        };
                    })(opt.$layer), 10);

                    try {
                        delete opt.$layer;
                    } catch(e) {
                        opt.$layer = null;
                    }
                }

                // remove handle
                $currentTrigger = null;
                // remove selected
                opt.$menu.find('.hover').trigger('contextmenu:blur');
                opt.$selected = null;
                // unregister key and mouse handlers
                //$(document).off('.contextMenuAutoHide keydown.contextMenu'); // http://bugs.jquery.com/ticket/10705
                $(document).off('.contextMenuAutoHide').off('keydown.contextMenu');
                // hide menu
                opt.$menu && opt.$menu[opt.animation.hide](opt.animation.duration, function (){
                    // tear down dynamically built menu after animation is completed.
                    if (opt.build) {
                        opt.$menu.remove();
                        $.each(opt, function(key, value) {
                            switch (key) {
                                case 'ns':
                                case 'selector':
                                case 'build':
                                case 'trigger':
                                    return true;

                                default:
                                    opt[key] = undefined;
                                    try {
                                        delete opt[key];
                                    } catch (e) {}
                                    return true;
                            }
                        });
                    }
                });
            },
            create: function(opt, root) {
                if (root === undefined) {
                    root = opt;
                }
                // create contextMenu
                opt.$menu = $('<ul class="context-menu-list ' + (opt.className || "") + '"></ul>').data({
                    'contextMenu': opt,
                    'contextMenuRoot': root
                });

                $.each(['callbacks', 'commands', 'inputs'], function(i,k){
                    opt[k] = {};
                    if (!root[k]) {
                        root[k] = {};
                    }
                });

                root.accesskeys || (root.accesskeys = {});

                // create contextMenu items
                $.each(opt.items, function(key, item){
                    var $t = $('<li class="context-menu-item ' + (item.className || "") +'"></li>'),
                        $label = null,
                        $input = null;

                    // iOS needs to see a click-event bound to an element to actually
                    // have the TouchEvents infrastructure trigger the click event
                    $t.on('click', $.noop);

                    item.$node = $t.data({
                        'contextMenu': opt,
                        'contextMenuRoot': root,
                        'contextMenuKey': key
                    });

                    // register accesskey
                    // NOTE: the accesskey attribute should be applicable to any element, but Safari5 and Chrome13 still can't do that
                    if (item.accesskey) {
                        var aks = splitAccesskey(item.accesskey);
                        for (var i=0, ak; ak = aks[i]; i++) {
                            if (!root.accesskeys[ak]) {
                                root.accesskeys[ak] = item;
                                item._name = item.name.replace(new RegExp('(' + ak + ')', 'i'), '<span class="context-menu-accesskey">$1</span>');
                                break;
                            }
                        }
                    }

                    if (typeof item == "string") {
                        $t.addClass('context-menu-separator not-selectable');
                    } else if (item.type && types[item.type]) {
                        // run custom type handler
                        types[item.type].call($t, item, opt, root);
                        // register commands
                        $.each([opt, root], function(i,k){
                            k.commands[key] = item;
                            if ($.isFunction(item.callback)) {
                                k.callbacks[key] = item.callback;
                            }
                        });
                    } else {
                        // add label for input
                        if (item.type == 'html') {
                            $t.addClass('context-menu-html not-selectable');
                        } else if (item.type) {
                            $label = $('<label></label>').appendTo($t);
                            $('<span></span>').html(item._name || item.name).appendTo($label);
                            $t.addClass('context-menu-input');
                            opt.hasTypes = true;
                            $.each([opt, root], function(i,k){
                                k.commands[key] = item;
                                k.inputs[key] = item;
                            });
                        } else if (item.items) {
                            item.type = 'sub';
                        }

                        switch (item.type) {
                            case 'text':
                                $input = $('<input type="text" value="1" name="context-menu-input-'+ key +'" value="">')
                                    .val(item.value || "").appendTo($label);
                                break;

                            case 'textarea':
                                $input = $('<textarea name="context-menu-input-'+ key +'"></textarea>')
                                    .val(item.value || "").appendTo($label);

                                if (item.height) {
                                    $input.height(item.height);
                                }
                                break;

                            case 'checkbox':
                                $input = $('<input type="checkbox" value="1" name="context-menu-input-'+ key +'" value="">')
                                    .val(item.value || "").prop("checked", !!item.selected).prependTo($label);
                                break;

                            case 'radio':
                                $input = $('<input type="radio" value="1" name="context-menu-input-'+ item.radio +'" value="">')
                                    .val(item.value || "").prop("checked", !!item.selected).prependTo($label);
                                break;

                            case 'select':
                                $input = $('<select name="context-menu-input-'+ key +'">').appendTo($label);
                                if (item.options) {
                                    $.each(item.options, function(value, text) {
                                        $('<option></option>').val(value).text(text).appendTo($input);
                                    });
                                    $input.val(item.selected);
                                }
                                break;

                            case 'sub':
                                $('<span></span>').html(item._name || item.name).appendTo($t);
                                item.appendTo = item.$node;
                                op.create(item, root);
                                $t.data('contextMenu', item).addClass('context-menu-submenu');
                                item.callback = null;
                                break;

                            case 'html':
                                $(item.html).appendTo($t);
                                break;

                            default:
                                $.each([opt, root], function(i,k){
                                    k.commands[key] = item;
                                    if ($.isFunction(item.callback)) {
                                        k.callbacks[key] = item.callback;
                                    }
                                });

                                $('<span></span>').html(item._name || item.name || "").appendTo($t);
                                break;
                        }

                        // disable key listener in <input>
                        if (item.type && item.type != 'sub' && item.type != 'html') {
                            $input
                                .on('focus', handle.focusInput)
                                .on('blur', handle.blurInput);

                            if (item.events) {
                                $input.on(item.events, opt);
                            }
                        }

                        // add icons
                        if (item.icon) {
                            $t.addClass("icon icon-" + item.icon);
                        }
                    }

                    // cache contained elements
                    item.$input = $input;
                    item.$label = $label;

                    // attach item to menu
                    $t.appendTo(opt.$menu);

                    // Disable text selection
                    if (!opt.hasTypes && $.support.eventSelectstart) {
                        // browsers support user-select: none,
                        // IE has a special event for text-selection
                        // browsers supporting neither will not be preventing text-selection
                        $t.on('selectstart.disableTextSelect', handle.abortevent);
                    }
                });
                // attach contextMenu to <body> (to bypass any possible overflow:hidden issues on parents of the trigger element)
                if (!opt.$node) {
                    opt.$menu.css('display', 'none').addClass('context-menu-root');
                }
                opt.$menu.appendTo(opt.appendTo || document.body);
            },
            resize: function($menu, nested) {
                // determine widths of submenus, as CSS won't grow them automatically
                // position:absolute within position:absolute; min-width:100; max-width:200; results in width: 100;
                // kinda sucks hard...

                // determine width of absolutely positioned element
                $menu.css({position: 'absolute', display: 'block'});
                // don't apply yet, because that would break nested elements' widths
                // add a pixel to circumvent word-break issue in IE9 - #80
                $menu.data('width', Math.ceil($menu.width()) + 1);
                // reset styles so they allow nested elements to grow/shrink naturally
                $menu.css({
                    position: 'static',
                    minWidth: '0px',
                    maxWidth: '100000px'
                });
                // identify width of nested menus
                $menu.find('> li > ul').each(function() {
                    op.resize($(this), true);
                });
                // reset and apply changes in the end because nested
                // elements' widths wouldn't be calculatable otherwise
                if (!nested) {
                    $menu.find('ul').andSelf().css({
                        position: '',
                        display: '',
                        minWidth: '',
                        maxWidth: ''
                    }).width(function() {
                            return $(this).data('width');
                        });
                }
            },
            update: function(opt, root) {
                var $trigger = this;
                if (root === undefined) {
                    root = opt;
                    op.resize(opt.$menu);
                }
                // re-check disabled for each item
                opt.$menu.children().each(function(){
                    var $item = $(this),
                        key = $item.data('contextMenuKey'),
                        item = opt.items[key],
                        disabled = ($.isFunction(item.disabled) && item.disabled.call($trigger, key, root)) || item.disabled === true;

                    // dis- / enable item
                    $item[disabled ? 'addClass' : 'removeClass']('disabled');

                    if (item.type) {
                        // dis- / enable input elements
                        $item.find('input, select, textarea').prop('disabled', disabled);

                        // update input states
                        switch (item.type) {
                            case 'text':
                            case 'textarea':
                                item.$input.val(item.value || "");
                                break;

                            case 'checkbox':
                            case 'radio':
                                item.$input.val(item.value || "").prop('checked', !!item.selected);
                                break;

                            case 'select':
                                item.$input.val(item.selected || "");
                                break;
                        }
                    }

                    if (item.$menu) {
                        // update sub-menu
                        op.update.call($trigger, item, root);
                    }
                });
            },
            layer: function(opt, zIndex) {
                // add transparent layer for click area
                // filter and background for Internet Explorer, Issue #23
                var $layer = opt.$layer = $('<div id="context-menu-layer" style="position:fixed; z-index:' + zIndex + '; top:0; left:0; opacity: 0; filter: alpha(opacity=0); background-color: #000;"></div>')
                    .css({height: $win.height(), width: $win.width(), display: 'block'})
                    .data('contextMenuRoot', opt)
                    .insertBefore(this)
                    .on('contextmenu', handle.abortevent)
                    .on('mousedown', handle.layerClick);

                // IE6 doesn't know position:fixed;
                if (!$.support.fixedPosition) {
                    $layer.css({
                        'position' : 'absolute',
                        'height' : $(document).height()
                    });
                }

                return $layer;
            }
        };

// split accesskey according to http://www.whatwg.org/specs/web-apps/current-work/multipage/editing.html#assigned-access-key
    function splitAccesskey(val) {
        var t = val.split(/\s+/),
            keys = [];

        for (var i=0, k; k = t[i]; i++) {
            k = k[0].toUpperCase(); // first character only
            // theoretically non-accessible characters should be ignored, but different systems, different keyboard layouts, ... screw it.
            // a map to look up already used access keys would be nice
            keys.push(k);
        }

        return keys;
    }

// handle contextMenu triggers
    $.fn.contextMenu = function(operation) {
        if (operation === undefined) {
            this.first().trigger('contextmenu');
        } else if (operation.x && operation.y) {
            this.first().trigger($.Event("contextmenu", {pageX: operation.x, pageY: operation.y}));
        } else if (operation === "hide") {
            var $menu = this.data('contextMenu').$menu;
            $menu && $menu.trigger('contextmenu:hide');
        } else if (operation === "destroy") {
            $.contextMenu("destroy", {context: this});
        } else if ($.isPlainObject(operation)) {
            operation.context = this;
            $.contextMenu("create", operation);
        } else if (operation) {
            this.removeClass('context-menu-disabled');
        } else if (!operation) {
            this.addClass('context-menu-disabled');
        }

        return this;
    };

// manage contextMenu instances
    $.contextMenu = function(operation, options) {
        if (typeof operation != 'string') {
            options = operation;
            operation = 'create';
        }

        if (typeof options == 'string') {
            options = {selector: options};
        } else if (options === undefined) {
            options = {};
        }

        // merge with default options
        var o = $.extend(true, {}, defaults, options || {});
        var $document = $(document);
        var $context = $document;
        var _hasContext = false;

        if (!o.context || !o.context.length) {
            o.context = document;
        } else {
            // you never know what they throw at you…
            $context = $(o.context).first();
            o.context = $context.get(0);
            _hasContext = o.context !== document;
        }

        switch (operation) {
            case 'create':
                // no selector no joy
                if (!o.selector) {
                    throw new Error('No selector specified');
                }
                // make sure internal classes are not bound to
                if (o.selector.match(/.context-menu-(list|item|input)($|\s)/)) {
                    throw new Error('Cannot bind to selector "' + o.selector + '" as it contains a reserved className');
                }
                if (!o.build && (!o.items || $.isEmptyObject(o.items))) {
                    throw new Error('No Items sepcified');
                }
                counter ++;
                o.ns = '.contextMenu' + counter;
                if (!_hasContext) {
                    namespaces[o.selector] = o.ns;
                }
                menus[o.ns] = o;

                // default to right click
                if (!o.trigger) {
                    o.trigger = 'right';
                }

                if (!initialized) {
                    // make sure item click is registered first
                    $document
                        .on({
                            'contextmenu:hide.contextMenu': handle.hideMenu,
                            'prevcommand.contextMenu': handle.prevItem,
                            'nextcommand.contextMenu': handle.nextItem,
                            'contextmenu.contextMenu': handle.abortevent,
                            'mouseenter.contextMenu': handle.menuMouseenter,
                            'mouseleave.contextMenu': handle.menuMouseleave
                        }, '.context-menu-list')
                        .on('mouseup.contextMenu', '.context-menu-input', handle.inputClick)
                        .on({
                            'mouseup.contextMenu': handle.itemClick,
                            'contextmenu:focus.contextMenu': handle.focusItem,
                            'contextmenu:blur.contextMenu': handle.blurItem,
                            'contextmenu.contextMenu': handle.abortevent,
                            'mouseenter.contextMenu': handle.itemMouseenter,
                            'mouseleave.contextMenu': handle.itemMouseleave
                        }, '.context-menu-item');

                    initialized = true;
                }

                // engage native contextmenu event
                $context
                    .on('contextmenu' + o.ns, o.selector, o, handle.contextmenu);

                if (_hasContext) {
                    // add remove hook, just in case
                    $context.on('remove' + o.ns, function() {
                        $(this).contextMenu("destroy");
                    });
                }

                switch (o.trigger) {
                    case 'hover':
                        $context
                            .on('mouseenter' + o.ns, o.selector, o, handle.mouseenter)
                            .on('mouseleave' + o.ns, o.selector, o, handle.mouseleave);
                        break;

                    case 'left':
                        $context.on('click' + o.ns, o.selector, o, handle.click);
                        break;
                    /*
                     default:
                     // http://www.quirksmode.org/dom/events/contextmenu.html
                     $document
                     .on('mousedown' + o.ns, o.selector, o, handle.mousedown)
                     .on('mouseup' + o.ns, o.selector, o, handle.mouseup);
                     break;
                     */
                }

                // create menu
                if (!o.build) {
                    op.create(o);
                }
                break;

            case 'destroy':
                var $visibleMenu;
                if (_hasContext) {
                    // get proper options
                    var context = o.context;
                    $.each(menus, function(ns, o) {
                        if (o.context !== context) {
                            return true;
                        }

                        $visibleMenu = $('.context-menu-list').filter(':visible');
                        if ($visibleMenu.length && $visibleMenu.data().contextMenuRoot.$trigger.is($(o.context).find(o.selector))) {
                            $visibleMenu.trigger('contextmenu:hide', {force: true});
                        }

                        try {
                            if (menus[o.ns].$menu) {
                                menus[o.ns].$menu.remove();
                            }

                            delete menus[o.ns];
                        } catch(e) {
                            menus[o.ns] = null;
                        }

                        $(o.context).off(o.ns);

                        return true;
                    });
                } else if (!o.selector) {
                    $document.off('.contextMenu .contextMenuAutoHide');
                    $.each(menus, function(ns, o) {
                        $(o.context).off(o.ns);
                    });

                    namespaces = {};
                    menus = {};
                    counter = 0;
                    initialized = false;

                    $('#context-menu-layer, .context-menu-list').remove();
                } else if (namespaces[o.selector]) {
                    $visibleMenu = $('.context-menu-list').filter(':visible');
                    if ($visibleMenu.length && $visibleMenu.data().contextMenuRoot.$trigger.is(o.selector)) {
                        $visibleMenu.trigger('contextmenu:hide', {force: true});
                    }

                    try {
                        if (menus[namespaces[o.selector]].$menu) {
                            menus[namespaces[o.selector]].$menu.remove();
                        }

                        delete menus[namespaces[o.selector]];
                    } catch(e) {
                        menus[namespaces[o.selector]] = null;
                    }

                    $document.off(namespaces[o.selector]);
                }
                break;

            case 'html5':
                // if <command> or <menuitem> are not handled by the browser,
                // or options was a bool true,
                // initialize $.contextMenu for them
                if ((!$.support.htmlCommand && !$.support.htmlMenuitem) || (typeof options == "boolean" && options)) {
                    $('menu[type="context"]').each(function() {
                        if (this.id) {
                            $.contextMenu({
                                selector: '[contextmenu=' + this.id +']',
                                items: $.contextMenu.fromMenu(this)
                            });
                        }
                    }).css('display', 'none');
                }
                break;

            default:
                throw new Error('Unknown operation "' + operation + '"');
        }

        return this;
    };

// import values into <input> commands
    $.contextMenu.setInputValues = function(opt, data) {
        if (data === undefined) {
            data = {};
        }

        $.each(opt.inputs, function(key, item) {
            switch (item.type) {
                case 'text':
                case 'textarea':
                    item.value = data[key] || "";
                    break;

                case 'checkbox':
                    item.selected = data[key] ? true : false;
                    break;

                case 'radio':
                    item.selected = (data[item.radio] || "") == item.value ? true : false;
                    break;

                case 'select':
                    item.selected = data[key] || "";
                    break;
            }
        });
    };

// export values from <input> commands
    $.contextMenu.getInputValues = function(opt, data) {
        if (data === undefined) {
            data = {};
        }

        $.each(opt.inputs, function(key, item) {
            switch (item.type) {
                case 'text':
                case 'textarea':
                case 'select':
                    data[key] = item.$input.val();
                    break;

                case 'checkbox':
                    data[key] = item.$input.prop('checked');
                    break;

                case 'radio':
                    if (item.$input.prop('checked')) {
                        data[item.radio] = item.value;
                    }
                    break;
            }
        });

        return data;
    };

// find <label for="xyz">
    function inputLabel(node) {
        return (node.id && $('label[for="'+ node.id +'"]').val()) || node.name;
    }

// convert <menu> to items object
    function menuChildren(items, $children, counter) {
        if (!counter) {
            counter = 0;
        }

        $children.each(function() {
            var $node = $(this),
                node = this,
                nodeName = this.nodeName.toLowerCase(),
                label,
                item;

            // extract <label><input>
            if (nodeName == 'label' && $node.find('input, textarea, select').length) {
                label = $node.text();
                $node = $node.children().first();
                node = $node.get(0);
                nodeName = node.nodeName.toLowerCase();
            }

            /*
             * <menu> accepts flow-content as children. that means <embed>, <canvas> and such are valid menu items.
             * Not being the sadistic kind, $.contextMenu only accepts:
             * <command>, <menuitem>, <hr>, <span>, <p> <input [text, radio, checkbox]>, <textarea>, <select> and of course <menu>.
             * Everything else will be imported as an html node, which is not interfaced with contextMenu.
             */

            // http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#concept-command
            switch (nodeName) {
                // http://www.whatwg.org/specs/web-apps/current-work/multipage/interactive-elements.html#the-menu-element
                case 'menu':
                    item = {name: $node.attr('label'), items: {}};
                    counter = menuChildren(item.items, $node.children(), counter);
                    break;

                // http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-a-element-to-define-a-command
                case 'a':
                // http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-button-element-to-define-a-command
                case 'button':
                    item = {
                        name: $node.text(),
                        disabled: !!$node.attr('disabled'),
                        callback: (function(){ return function(){ $node.click(); }; })()
                    };
                    break;

                // http://www.whatwg.org/specs/web-apps/current-work/multipage/commands.html#using-the-command-element-to-define-a-command

                case 'menuitem':
                case 'command':
                    switch ($node.attr('type')) {
                        case undefined:
                        case 'command':
                        case 'menuitem':
                            item = {
                                name: $node.attr('label'),
                                disabled: !!$node.attr('disabled'),
                                callback: (function(){ return function(){ $node.click(); }; })()
                            };
                            break;

                        case 'checkbox':
                            item = {
                                type: 'checkbox',
                                disabled: !!$node.attr('disabled'),
                                name: $node.attr('label'),
                                selected: !!$node.attr('checked')
                            };
                            break;

                        case 'radio':
                            item = {
                                type: 'radio',
                                disabled: !!$node.attr('disabled'),
                                name: $node.attr('label'),
                                radio: $node.attr('radiogroup'),
                                value: $node.attr('id'),
                                selected: !!$node.attr('checked')
                            };
                            break;

                        default:
                            item = undefined;
                    }
                    break;

                case 'hr':
                    item = '-------';
                    break;

                case 'input':
                    switch ($node.attr('type')) {
                        case 'text':
                            item = {
                                type: 'text',
                                name: label || inputLabel(node),
                                disabled: !!$node.attr('disabled'),
                                value: $node.val()
                            };
                            break;

                        case 'checkbox':
                            item = {
                                type: 'checkbox',
                                name: label || inputLabel(node),
                                disabled: !!$node.attr('disabled'),
                                selected: !!$node.attr('checked')
                            };
                            break;

                        case 'radio':
                            item = {
                                type: 'radio',
                                name: label || inputLabel(node),
                                disabled: !!$node.attr('disabled'),
                                radio: !!$node.attr('name'),
                                value: $node.val(),
                                selected: !!$node.attr('checked')
                            };
                            break;

                        default:
                            item = undefined;
                            break;
                    }
                    break;

                case 'select':
                    item = {
                        type: 'select',
                        name: label || inputLabel(node),
                        disabled: !!$node.attr('disabled'),
                        selected: $node.val(),
                        options: {}
                    };
                    $node.children().each(function(){
                        item.options[this.value] = $(this).text();
                    });
                    break;

                case 'textarea':
                    item = {
                        type: 'textarea',
                        name: label || inputLabel(node),
                        disabled: !!$node.attr('disabled'),
                        value: $node.val()
                    };
                    break;

                case 'label':
                    break;

                default:
                    item = {type: 'html', html: $node.clone(true)};
                    break;
            }

            if (item) {
                counter++;
                items['key' + counter] = item;
            }
        });

        return counter;
    }

// convert html5 menu
    $.contextMenu.fromMenu = function(element) {
        var $this = $(element),
            items = {};

        menuChildren(items, $this.children());

        return items;
    };

// make defaults accessible
    $.contextMenu.defaults = defaults;
    $.contextMenu.types = types;
// export internal functions - undocumented, for hacking only!
    $.contextMenu.handle = handle;
    $.contextMenu.op = op;

})(jQuery);


/**
 * @Filename: datetime.js
 * @Description: datetime
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121205
 * Copyright (c) 2012-2013 si-tech
 *
 * yy: 四位年份,如:2012
 * y : 两位年份,如:12
 * mm: 两位月份,不足在前补0,如:02
 * m : 一位月份,不补0,如:02
 * dd: 月份中的日,两位表示,不足补0,如:02
 * d : 月份中的日,不足补0,如:2
 * W : 星期的全称,如:星期一
 * w : 星期的略称,如:周一
 * HH: 24小时制小时,不足两位补0,如:08,13
 * H:  24小时制,不补0,如:8,13
 * hh: 12小时制,不足两位补0并且会在加上am或pm进行区分,如01:12:20 am
 * h : 12小时制,不补0并且会在加上am或pm进行区分,如1:12:20 am
 * MM: 分钟表示,不足两位补0,如:08
 * M:  分钟表示,不补0,如:8
 * ss: 秒表示,不足两位补0,如:08
 * s:  秒表示,不补0,如:8
 * SSS:表示毫秒
 *
 * @example
 * $("#span").UED_datetime();
 *
 */
(function($) {
    $.fn.UED_datetime = function(option){
        var defaults = {
            format: "yy-mm-dd W hh:MM:ss",
            morning: "\u4E0A\u5348",
            afterNoon: "\u4E0B\u5348",
            weekNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            weekNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
        };

        var datetime = {
            _init: function(o){
                setInterval(function(){datetime._setValue(o)}, 1);
            },
            _formatDate: function(date) {
                var dateStr = option.format;
                var showHour = dateStr.indexOf('h') != -1;
                dateStr = dateStr.replace(/yy/, date.getFullYear());
                dateStr = dateStr.replace(/y/, (date.getYear()).toString().substr(2));
                dateStr = dateStr.replace(/mm/, datetime._appendZero(date.getMonth()+1, 2));
                dateStr = dateStr.replace(/m/, date.getMonth()+1);
                dateStr = dateStr.replace(/dd/, datetime._appendZero(date.getDate(), 2));
                dateStr = dateStr.replace(/d/, date.getDate());
                dateStr = dateStr.replace(/W/, option.weekNames[date.getDay()]);
                dateStr = dateStr.replace(/w/, option.weekNamesShort[date.getDay()]);
                // 24小时制
                dateStr = dateStr.replace(/HH/, datetime._appendZero(date.getHours(), 2));
                dateStr = dateStr.replace(/H/, date.getHours());
                // 12小时制
                dateStr = dateStr.replace(/hh/, datetime._appendZero(date.getHours() > 12 ? (date.getHours() - 12) : date.getHours(), 2));
                dateStr = dateStr.replace(/h/, date.getHours() > 12 ? (date.getHours() - 12) : date.getHours());
                dateStr = dateStr.replace(/MM/, datetime._appendZero(date.getMinutes(), 2));
                dateStr = dateStr.replace(/M/, date.getMinutes());
                dateStr = dateStr.replace(/ss/, datetime._appendZero(date.getSeconds(), 2));
                dateStr = dateStr.replace(/s/, date.getSeconds());
                dateStr = dateStr.replace(/SSS/, datetime._appendZero(date.getMilliseconds(), 3));
                dateStr = dateStr.replace(/SS/, datetime._appendZero(date.getMilliseconds(), 2));
                dateStr = dateStr.replace(/S/, date.getMilliseconds());
                if (showHour) {
                    if (date.getHours() - 12 >= 0) {
                        dateStr = dateStr + option.afterNoon;
                    } else {
                        dateStr = dateStr + option.morning;
                    }
                }
                return dateStr;
            },
            _appendZero: function(value, length) {
                if (value) {
                    value = (value).toString();
                    if (value.length < length) {
                        for(var i = 0; i < length - value.length; i++) {
                            value = "0" + value;
                        }
                    }
                }
                return value;
            },
            _setValue: function(o) {
                var date = new Date();
                date = datetime._formatDate(date)
                if (o.nodeName == "INPUT" || o.nodeName == "TEXTAREA") {
                    $(o).val(date);
                } else {
                    $(o).text(date);
                }
            }
        }

        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        return this.each(function(i, n){
            var _rel = strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true, {}, defaults, option, _rel);

            datetime._init(n);
        })
    }

    // auto init plugin
    $(function(){
        $("*[data-role='ued-datetime']").UED_datetime();
    })
})(jQuery);


/*
 * JQuery zTree core 3.5.12
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2013-03-11
 */
(function($){
    var settings = {}, roots = {}, caches = {},
    //default consts of core
        _consts = {
            className: {
                BUTTON: "button",
                LEVEL: "level",
                ICO_LOADING: "ico_loading",
                SWITCH: "switch"
            },
            event: {
                NODECREATED: "ztree_nodeCreated",
                CLICK: "ztree_click",
                EXPAND: "ztree_expand",
                COLLAPSE: "ztree_collapse",
                ASYNC_SUCCESS: "ztree_async_success",
                ASYNC_ERROR: "ztree_async_error"
            },
            id: {
                A: "_a",
                ICON: "_ico",
                SPAN: "_span",
                SWITCH: "_switch",
                UL: "_ul"
            },
            line: {
                ROOT: "root",
                ROOTS: "roots",
                CENTER: "center",
                BOTTOM: "bottom",
                NOLINE: "noline",
                LINE: "line"
            },
            folder: {
                OPEN: "open",
                CLOSE: "close",
                DOCU: "docu"
            },
            node: {
                CURSELECTED: "curSelectedNode"
            }
        },
    //default setting of core
        _setting = {
            treeId: "",
            treeObj: null,
            view: {
                addDiyDom: null,
                autoCancelSelected: true,
                dblClickExpand: true,
                expandSpeed: "fast",
                fontCss: {},
                nameIsHTML: false,
                selectedMulti: true,
                showIcon: true,
                showLine: true,
                showTitle: true
            },
            data: {
                key: {
                    children: "children",
                    name: "name",
                    title: "",
                    url: "url"
                },
                simpleData: {
                    enable: false,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                },
                keep: {
                    parent: false,
                    leaf: false
                }
            },
            async: {
                enable: false,
                contentType: "application/x-www-form-urlencoded",
                type: "post",
                dataType: "text",
                url: "",
                autoParam: [],
                otherParam: [],
                dataFilter: null
            },
            callback: {
                beforeAsync:null,
                beforeClick:null,
                beforeDblClick:null,
                beforeRightClick:null,
                beforeMouseDown:null,
                beforeMouseUp:null,
                beforeExpand:null,
                beforeCollapse:null,
                beforeRemove:null,

                onAsyncError:null,
                onAsyncSuccess:null,
                onNodeCreated:null,
                onClick:null,
                onDblClick:null,
                onRightClick:null,
                onMouseDown:null,
                onMouseUp:null,
                onExpand:null,
                onCollapse:null,
                onRemove:null
            }
        },
    //default root of core
    //zTree use root to save full data
        _initRoot = function (setting) {
            var r = data.getRoot(setting);
            if (!r) {
                r = {};
                data.setRoot(setting, r);
            }
            r[setting.data.key.children] = [];
            r.expandTriggerFlag = false;
            r.curSelectedList = [];
            r.noSelection = true;
            r.createdNodes = [];
            r.zId = 0;
            r._ver = (new Date()).getTime();
        },
    //default cache of core
        _initCache = function(setting) {
            var c = data.getCache(setting);
            if (!c) {
                c = {};
                data.setCache(setting, c);
            }
            c.nodes = [];
            c.doms = [];
        },
    //default bindEvent of core
        _bindEvent = function(setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.bind(c.NODECREATED, function (event, treeId, node) {
                tools.apply(setting.callback.onNodeCreated, [event, treeId, node]);
            });

            o.bind(c.CLICK, function (event, srcEvent, treeId, node, clickFlag) {
                tools.apply(setting.callback.onClick, [srcEvent, treeId, node, clickFlag]);
            });

            o.bind(c.EXPAND, function (event, treeId, node) {
                tools.apply(setting.callback.onExpand, [event, treeId, node]);
            });

            o.bind(c.COLLAPSE, function (event, treeId, node) {
                tools.apply(setting.callback.onCollapse, [event, treeId, node]);
            });

            o.bind(c.ASYNC_SUCCESS, function (event, treeId, node, msg) {
                tools.apply(setting.callback.onAsyncSuccess, [event, treeId, node, msg]);
            });

            o.bind(c.ASYNC_ERROR, function (event, treeId, node, XMLHttpRequest, textStatus, errorThrown) {
                tools.apply(setting.callback.onAsyncError, [event, treeId, node, XMLHttpRequest, textStatus, errorThrown]);
            });
        },
        _unbindEvent = function(setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.unbind(c.NODECREATED)
                .unbind(c.CLICK)
                .unbind(c.EXPAND)
                .unbind(c.COLLAPSE)
                .unbind(c.ASYNC_SUCCESS)
                .unbind(c.ASYNC_ERROR);
        },
    //default event proxy of core
        _eventProxy = function(event) {
            var target = event.target,
                setting = data.getSetting(event.data.treeId),
                tId = "", node = null,
                nodeEventType = "", treeEventType = "",
                nodeEventCallback = null, treeEventCallback = null,
                tmp = null;

            if (tools.eqs(event.type, "mousedown")) {
                treeEventType = "mousedown";
            } else if (tools.eqs(event.type, "mouseup")) {
                treeEventType = "mouseup";
            } else if (tools.eqs(event.type, "contextmenu")) {
                treeEventType = "contextmenu";
            } else if (tools.eqs(event.type, "click")) {
                if (tools.eqs(target.tagName, "span") && target.getAttribute("treeNode"+ consts.id.SWITCH) !== null) {
                    tId = ($(target).parent("li").get(0) || $(target).parentsUntil("li").parent().get(0)).id;
                    nodeEventType = "switchNode";
                } else {
                    tmp = tools.getMDom(setting, target, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                    if (tmp) {
                        tId = ($(tmp).parent("li").get(0) || $(tmp).parentsUntil("li").parent().get(0)).id;
                        nodeEventType = "clickNode";
                    }
                }
            } else if (tools.eqs(event.type, "dblclick")) {
                treeEventType = "dblclick";
                tmp = tools.getMDom(setting, target, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                if (tmp) {
                    tId = ($(tmp).parent("li").get(0) || $(tmp).parentsUntil("li").parent().get(0)).id;
                    nodeEventType = "switchNode";
                }
            }
            if (treeEventType.length > 0 && tId.length == 0) {
                tmp = tools.getMDom(setting, target, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                if (tmp) {tId = ($(tmp).parent("li").get(0) || $(tmp).parentsUntil("li").parent().get(0)).id;}
            }
            // event to node
            if (tId.length>0) {
                node = data.getNodeCache(setting, tId);
                switch (nodeEventType) {
                    case "switchNode" :
                        if (!node.isParent) {
                            nodeEventType = "";
                        } else if (tools.eqs(event.type, "click")
                            || (tools.eqs(event.type, "dblclick") && tools.apply(setting.view.dblClickExpand, [setting.treeId, node], setting.view.dblClickExpand))) {
                            nodeEventCallback = handler.onSwitchNode;
                        } else {
                            nodeEventType = "";
                        }
                        break;
                    case "clickNode" :
                        nodeEventCallback = handler.onClickNode;
                        break;
                }
            }
            // event to zTree
            switch (treeEventType) {
                case "mousedown" :
                    treeEventCallback = handler.onZTreeMousedown;
                    break;
                case "mouseup" :
                    treeEventCallback = handler.onZTreeMouseup;
                    break;
                case "dblclick" :
                    treeEventCallback = handler.onZTreeDblclick;
                    break;
                case "contextmenu" :
                    treeEventCallback = handler.onZTreeContextmenu;
                    break;
            }
            var proxyResult = {
                stop: false,
                node: node,
                nodeEventType: nodeEventType,
                nodeEventCallback: nodeEventCallback,
                treeEventType: treeEventType,
                treeEventCallback: treeEventCallback
            };
            return proxyResult
        },
    //default init node of core
        _initNode = function(setting, level, n, parentNode, isFirstNode, isLastNode, openFlag) {
            if (!n) return;
            var r = data.getRoot(setting),
                childKey = setting.data.key.children;
            n.level = level;
            n.tId = setting.treeId + "_" + (++r.zId);
            n.parentTId = parentNode ? parentNode.tId : null;
            if (n[childKey] && n[childKey].length > 0) {
                if (typeof n.open == "string") n.open = tools.eqs(n.open, "true");
                n.open = !!n.open;
                n.isParent = true;
                n.zAsync = true;
            } else {
                n.open = false;
                if (typeof n.isParent == "string") n.isParent = tools.eqs(n.isParent, "true");
                n.isParent = !!n.isParent;
                n.zAsync = !n.isParent;
            }
            n.isFirstNode = isFirstNode;
            n.isLastNode = isLastNode;
            n.getParentNode = function() {return data.getNodeCache(setting, n.parentTId);};
            n.getPreNode = function() {return data.getPreNode(setting, n);};
            n.getNextNode = function() {return data.getNextNode(setting, n);};
            n.isAjaxing = false;
            data.fixPIdKeyValue(setting, n);
        },
        _init = {
            bind: [_bindEvent],
            unbind: [_unbindEvent],
            caches: [_initCache],
            nodes: [_initNode],
            proxys: [_eventProxy],
            roots: [_initRoot],
            beforeA: [],
            afterA: [],
            innerBeforeA: [],
            innerAfterA: [],
            zTreeTools: []
        },
    //method of operate data
        data = {
            addNodeCache: function(setting, node) {
                data.getCache(setting).nodes[data.getNodeCacheId(node.tId)] = node;
            },
            getNodeCacheId: function(tId) {
                return tId.substring(tId.lastIndexOf("_")+1);
            },
            addAfterA: function(afterA) {
                _init.afterA.push(afterA);
            },
            addBeforeA: function(beforeA) {
                _init.beforeA.push(beforeA);
            },
            addInnerAfterA: function(innerAfterA) {
                _init.innerAfterA.push(innerAfterA);
            },
            addInnerBeforeA: function(innerBeforeA) {
                _init.innerBeforeA.push(innerBeforeA);
            },
            addInitBind: function(bindEvent) {
                _init.bind.push(bindEvent);
            },
            addInitUnBind: function(unbindEvent) {
                _init.unbind.push(unbindEvent);
            },
            addInitCache: function(initCache) {
                _init.caches.push(initCache);
            },
            addInitNode: function(initNode) {
                _init.nodes.push(initNode);
            },
            addInitProxy: function(initProxy) {
                _init.proxys.push(initProxy);
            },
            addInitRoot: function(initRoot) {
                _init.roots.push(initRoot);
            },
            addNodesData: function(setting, parentNode, nodes) {
                var childKey = setting.data.key.children;
                if (!parentNode[childKey]) parentNode[childKey] = [];
                if (parentNode[childKey].length > 0) {
                    parentNode[childKey][parentNode[childKey].length - 1].isLastNode = false;
                    view.setNodeLineIcos(setting, parentNode[childKey][parentNode[childKey].length - 1]);
                }
                parentNode.isParent = true;
                parentNode[childKey] = parentNode[childKey].concat(nodes);
            },
            addSelectedNode: function(setting, node) {
                var root = data.getRoot(setting);
                if (!data.isSelectedNode(setting, node)) {
                    root.curSelectedList.push(node);
                }
            },
            addCreatedNode: function(setting, node) {
                if (!!setting.callback.onNodeCreated || !!setting.view.addDiyDom) {
                    var root = data.getRoot(setting);
                    root.createdNodes.push(node);
                }
            },
            addZTreeTools: function(zTreeTools) {
                _init.zTreeTools.push(zTreeTools);
            },
            exSetting: function(s) {
                $.extend(true, _setting, s);
            },
            fixPIdKeyValue: function(setting, node) {
                if (setting.data.simpleData.enable) {
                    node[setting.data.simpleData.pIdKey] = node.parentTId ? node.getParentNode()[setting.data.simpleData.idKey] : setting.data.simpleData.rootPId;
                }
            },
            getAfterA: function(setting, node, array) {
                for (var i=0, j=_init.afterA.length; i<j; i++) {
                    _init.afterA[i].apply(this, arguments);
                }
            },
            getBeforeA: function(setting, node, array) {
                for (var i=0, j=_init.beforeA.length; i<j; i++) {
                    _init.beforeA[i].apply(this, arguments);
                }
            },
            getInnerAfterA: function(setting, node, array) {
                for (var i=0, j=_init.innerAfterA.length; i<j; i++) {
                    _init.innerAfterA[i].apply(this, arguments);
                }
            },
            getInnerBeforeA: function(setting, node, array) {
                for (var i=0, j=_init.innerBeforeA.length; i<j; i++) {
                    _init.innerBeforeA[i].apply(this, arguments);
                }
            },
            getCache: function(setting) {
                return caches[setting.treeId];
            },
            getNextNode: function(setting, node) {
                if (!node) return null;
                var childKey = setting.data.key.children,
                    p = node.parentTId ? node.getParentNode() : data.getRoot(setting);
                for (var i=0, l=p[childKey].length-1; i<=l; i++) {
                    if (p[childKey][i] === node) {
                        return (i==l ? null : p[childKey][i+1]);
                    }
                }
                return null;
            },
            getNodeByParam: function(setting, nodes, key, value) {
                if (!nodes || !key) return null;
                var childKey = setting.data.key.children;
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i][key] == value) {
                        return nodes[i];
                    }
                    var tmp = data.getNodeByParam(setting, nodes[i][childKey], key, value);
                    if (tmp) return tmp;
                }
                return null;
            },
            getNodeCache: function(setting, tId) {
                if (!tId) return null;
                var n = caches[setting.treeId].nodes[data.getNodeCacheId(tId)];
                return n ? n : null;
            },
            getNodeName: function(setting, node) {
                var nameKey = setting.data.key.name;
                return "" + node[nameKey];
            },
            getNodeTitle: function(setting, node) {
                var t = setting.data.key.title === "" ? setting.data.key.name : setting.data.key.title;
                return "" + node[t];
            },
            getNodes: function(setting) {
                return data.getRoot(setting)[setting.data.key.children];
            },
            getNodesByParam: function(setting, nodes, key, value) {
                if (!nodes || !key) return [];
                var childKey = setting.data.key.children,
                    result = [];
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i][key] == value) {
                        result.push(nodes[i]);
                    }
                    result = result.concat(data.getNodesByParam(setting, nodes[i][childKey], key, value));
                }
                return result;
            },
            getNodesByParamFuzzy: function(setting, nodes, key, value) {
                if (!nodes || !key) return [];
                var childKey = setting.data.key.children,
                    result = [];
                value = value.toLowerCase();
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (typeof nodes[i][key] == "string" && nodes[i][key].toLowerCase().indexOf(value)>-1) {
                        result.push(nodes[i]);
                    }
                    result = result.concat(data.getNodesByParamFuzzy(setting, nodes[i][childKey], key, value));
                }
                return result;
            },
            getNodesByFilter: function(setting, nodes, filter, isSingle, invokeParam) {
                if (!nodes) return (isSingle ? null : []);
                var childKey = setting.data.key.children,
                    result = isSingle ? null : [];
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (tools.apply(filter, [nodes[i], invokeParam], false)) {
                        if (isSingle) {return nodes[i];}
                        result.push(nodes[i]);
                    }
                    var tmpResult = data.getNodesByFilter(setting, nodes[i][childKey], filter, isSingle, invokeParam);
                    if (isSingle && !!tmpResult) {return tmpResult;}
                    result = isSingle ? tmpResult : result.concat(tmpResult);
                }
                return result;
            },
            getPreNode: function(setting, node) {
                if (!node) return null;
                var childKey = setting.data.key.children,
                    p = node.parentTId ? node.getParentNode() : data.getRoot(setting);
                for (var i=0, l=p[childKey].length; i<l; i++) {
                    if (p[childKey][i] === node) {
                        return (i==0 ? null : p[childKey][i-1]);
                    }
                }
                return null;
            },
            getRoot: function(setting) {
                return setting ? roots[setting.treeId] : null;
            },
            getSetting: function(treeId) {
                return settings[treeId];
            },
            getSettings: function() {
                return settings;
            },
            getZTreeTools: function(treeId) {
                var r = this.getRoot(this.getSetting(treeId));
                return r ? r.treeTools : null;
            },
            initCache: function(setting) {
                for (var i=0, j=_init.caches.length; i<j; i++) {
                    _init.caches[i].apply(this, arguments);
                }
            },
            initNode: function(setting, level, node, parentNode, preNode, nextNode) {
                for (var i=0, j=_init.nodes.length; i<j; i++) {
                    _init.nodes[i].apply(this, arguments);
                }
            },
            initRoot: function(setting) {
                for (var i=0, j=_init.roots.length; i<j; i++) {
                    _init.roots[i].apply(this, arguments);
                }
            },
            isSelectedNode: function(setting, node) {
                var root = data.getRoot(setting);
                for (var i=0, j=root.curSelectedList.length; i<j; i++) {
                    if(node === root.curSelectedList[i]) return true;
                }
                return false;
            },
            removeNodeCache: function(setting, node) {
                var childKey = setting.data.key.children;
                if (node[childKey]) {
                    for (var i=0, l=node[childKey].length; i<l; i++) {
                        arguments.callee(setting, node[childKey][i]);
                    }
                }
                data.getCache(setting).nodes[data.getNodeCacheId(node.tId)] = null;
            },
            removeSelectedNode: function(setting, node) {
                var root = data.getRoot(setting);
                for (var i=0, j=root.curSelectedList.length; i<j; i++) {
                    if(node === root.curSelectedList[i] || !data.getNodeCache(setting, root.curSelectedList[i].tId)) {
                        root.curSelectedList.splice(i, 1);
                        i--;j--;
                    }
                }
            },
            setCache: function(setting, cache) {
                caches[setting.treeId] = cache;
            },
            setRoot: function(setting, root) {
                roots[setting.treeId] = root;
            },
            setZTreeTools: function(setting, zTreeTools) {
                for (var i=0, j=_init.zTreeTools.length; i<j; i++) {
                    _init.zTreeTools[i].apply(this, arguments);
                }
            },
            transformToArrayFormat: function (setting, nodes) {
                if (!nodes) return [];
                var childKey = setting.data.key.children,
                    r = [];
                if (tools.isArray(nodes)) {
                    for (var i=0, l=nodes.length; i<l; i++) {
                        r.push(nodes[i]);
                        if (nodes[i][childKey])
                            r = r.concat(data.transformToArrayFormat(setting, nodes[i][childKey]));
                    }
                } else {
                    r.push(nodes);
                    if (nodes[childKey])
                        r = r.concat(data.transformToArrayFormat(setting, nodes[childKey]));
                }
                return r;
            },
            transformTozTreeFormat: function(setting, sNodes) {
                var i,l,
                    key = setting.data.simpleData.idKey,
                    parentKey = setting.data.simpleData.pIdKey,
                    childKey = setting.data.key.children;
                if (!key || key=="" || !sNodes) return [];

                if (tools.isArray(sNodes)) {
                    var r = [];
                    var tmpMap = [];
                    for (i=0, l=sNodes.length; i<l; i++) {
                        tmpMap[sNodes[i][key]] = sNodes[i];
                    }
                    for (i=0, l=sNodes.length; i<l; i++) {
                        if (tmpMap[sNodes[i][parentKey]] && sNodes[i][key] != sNodes[i][parentKey]) {
                            if (!tmpMap[sNodes[i][parentKey]][childKey])
                                tmpMap[sNodes[i][parentKey]][childKey] = [];
                            tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);
                        } else {
                            r.push(sNodes[i]);
                        }
                    }
                    return r;
                }else {
                    return [sNodes];
                }
            }
        },
    //method of event proxy
        event = {
            bindEvent: function(setting) {
                for (var i=0, j=_init.bind.length; i<j; i++) {
                    _init.bind[i].apply(this, arguments);
                }
            },
            unbindEvent: function(setting) {
                for (var i=0, j=_init.unbind.length; i<j; i++) {
                    _init.unbind[i].apply(this, arguments);
                }
            },
            bindTree: function(setting) {
                var eventParam = {
                        treeId: setting.treeId
                    },
                    o = setting.treeObj;
                // for can't select text
                o.bind('selectstart', function(e){
                    var n = e.originalEvent.srcElement.nodeName.toLowerCase();
                    return (n === "input" || n === "textarea" );
                }).css({
                        "-moz-user-select":"-moz-none"
                    });
                o.bind('click', eventParam, event.proxy);
                o.bind('dblclick', eventParam, event.proxy);
                o.bind('mouseover', eventParam, event.proxy);
                o.bind('mouseout', eventParam, event.proxy);
                o.bind('mousedown', eventParam, event.proxy);
                o.bind('mouseup', eventParam, event.proxy);
                o.bind('contextmenu', eventParam, event.proxy);
            },
            unbindTree: function(setting) {
                var o = setting.treeObj;
                o.unbind('click', event.proxy)
                    .unbind('dblclick', event.proxy)
                    .unbind('mouseover', event.proxy)
                    .unbind('mouseout', event.proxy)
                    .unbind('mousedown', event.proxy)
                    .unbind('mouseup', event.proxy)
                    .unbind('contextmenu', event.proxy);
            },
            doProxy: function(e) {
                var results = [];
                for (var i=0, j=_init.proxys.length; i<j; i++) {
                    var proxyResult = _init.proxys[i].apply(this, arguments);
                    results.push(proxyResult);
                    if (proxyResult.stop) {
                        break;
                    }
                }
                return results;
            },
            proxy: function(e) {
                var setting = data.getSetting(e.data.treeId);
                if (!tools.uCanDo(setting, e)) return true;
                var results = event.doProxy(e),
                    r = true, x = false;
                for (var i=0, l=results.length; i<l; i++) {
                    var proxyResult = results[i];
                    if (proxyResult.nodeEventCallback) {
                        x = true;
                        r = proxyResult.nodeEventCallback.apply(proxyResult, [e, proxyResult.node]) && r;
                    }
                    if (proxyResult.treeEventCallback) {
                        x = true;
                        r = proxyResult.treeEventCallback.apply(proxyResult, [e, proxyResult.node]) && r;
                    }
                }
                return r;
            }
        },
    //method of event handler
        handler = {
            onSwitchNode: function (event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (node.open) {
                    if (tools.apply(setting.callback.beforeCollapse, [setting.treeId, node], true) == false) return true;
                    data.getRoot(setting).expandTriggerFlag = true;
                    view.switchNode(setting, node);
                } else {
                    if (tools.apply(setting.callback.beforeExpand, [setting.treeId, node], true) == false) return true;
                    data.getRoot(setting).expandTriggerFlag = true;
                    view.switchNode(setting, node);
                }
                return true;
            },
            onClickNode: function (event, node) {
                var setting = data.getSetting(event.data.treeId),
                    clickFlag = ( (setting.view.autoCancelSelected && event.ctrlKey) && data.isSelectedNode(setting, node)) ? 0 : (setting.view.autoCancelSelected && event.ctrlKey && setting.view.selectedMulti) ? 2 : 1;
                if (tools.apply(setting.callback.beforeClick, [setting.treeId, node, clickFlag], true) == false) return true;
                if (clickFlag === 0) {
                    view.cancelPreSelectedNode(setting, node);
                } else {
                    view.selectNode(setting, node, clickFlag === 2);
                }
                setting.treeObj.trigger(consts.event.CLICK, [event, setting.treeId, node, clickFlag]);
                return true;
            },
            onZTreeMousedown: function(event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeMouseDown, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onMouseDown, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeMouseup: function(event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeMouseUp, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onMouseUp, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeDblclick: function(event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeDblClick, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onDblClick, [event, setting.treeId, node]);
                }
                return true;
            },
            onZTreeContextmenu: function(event, node) {
                var setting = data.getSetting(event.data.treeId);
                if (tools.apply(setting.callback.beforeRightClick, [setting.treeId, node], true)) {
                    tools.apply(setting.callback.onRightClick, [event, setting.treeId, node]);
                }
                return (typeof setting.callback.onRightClick) != "function";
            }
        },
    //method of tools for zTree
        tools = {
            apply: function(fun, param, defaultValue) {
                if ((typeof fun) == "function") {
                    return fun.apply(zt, param?param:[]);
                }
                return defaultValue;
            },
            canAsync: function(setting, node) {
                var childKey = setting.data.key.children;
                return (setting.async.enable && node && node.isParent && !(node.zAsync || (node[childKey] && node[childKey].length > 0)));
            },
            clone: function (obj){
                if (obj === null) return null;
                var o = obj.constructor === Array ? [] : {};
                for(var i in obj){
                    o[i] = (obj[i] instanceof Date) ? new Date(obj[i].getTime()) : (typeof obj[i] === "object" ? arguments.callee(obj[i]) : obj[i]);
                }
                return o;
            },
            eqs: function(str1, str2) {
                return str1.toLowerCase() === str2.toLowerCase();
            },
            isArray: function(arr) {
                return Object.prototype.toString.apply(arr) === "[object Array]";
            },
            getMDom: function (setting, curDom, targetExpr) {
                if (!curDom) return null;
                while (curDom && curDom.id !== setting.treeId) {
                    for (var i=0, l=targetExpr.length; curDom.tagName && i<l; i++) {
                        if (tools.eqs(curDom.tagName, targetExpr[i].tagName) && curDom.getAttribute(targetExpr[i].attrName) !== null) {
                            return curDom;
                        }
                    }
                    curDom = curDom.parentNode;
                }
                return null;
            },
            uCanDo: function(setting, e) {
                return true;
            }
        },
    //method of operate ztree dom
        view = {
            addNodes: function(setting, parentNode, newNodes, isSilent) {
                if (setting.data.keep.leaf && parentNode && !parentNode.isParent) {
                    return;
                }
                if (!tools.isArray(newNodes)) {
                    newNodes = [newNodes];
                }
                if (setting.data.simpleData.enable) {
                    newNodes = data.transformTozTreeFormat(setting, newNodes);
                }
                if (parentNode) {
                    var target_switchObj = $("#" + parentNode.tId + consts.id.SWITCH),
                        target_icoObj = $("#" + parentNode.tId + consts.id.ICON),
                        target_ulObj = $("#" + parentNode.tId + consts.id.UL);

                    if (!parentNode.open) {
                        view.replaceSwitchClass(parentNode, target_switchObj, consts.folder.CLOSE);
                        view.replaceIcoClass(parentNode, target_icoObj, consts.folder.CLOSE);
                        parentNode.open = false;
                        target_ulObj.css({
                            "display": "none"
                        });
                    }

                    data.addNodesData(setting, parentNode, newNodes);
                    view.createNodes(setting, parentNode.level + 1, newNodes, parentNode);
                    if (!isSilent) {
                        view.expandCollapseParentNode(setting, parentNode, true);
                    }
                } else {
                    data.addNodesData(setting, data.getRoot(setting), newNodes);
                    view.createNodes(setting, 0, newNodes, null);
                }
            },
            appendNodes: function(setting, level, nodes, parentNode, initFlag, openFlag) {
                if (!nodes) return [];
                var html = [],
                    childKey = setting.data.key.children;
                for (var i = 0, l = nodes.length; i < l; i++) {
                    var node = nodes[i];
                    if (initFlag) {
                        var tmpPNode = (parentNode) ? parentNode: data.getRoot(setting),
                            tmpPChild = tmpPNode[childKey],
                            isFirstNode = ((tmpPChild.length == nodes.length) && (i == 0)),
                            isLastNode = (i == (nodes.length - 1));
                        data.initNode(setting, level, node, parentNode, isFirstNode, isLastNode, openFlag);
                        data.addNodeCache(setting, node);
                    }

                    var childHtml = [];
                    if (node[childKey] && node[childKey].length > 0) {
                        //make child html first, because checkType
                        childHtml = view.appendNodes(setting, level + 1, node[childKey], node, initFlag, openFlag && node.open);
                    }
                    if (openFlag) {

                        view.makeDOMNodeMainBefore(html, setting, node);
                        view.makeDOMNodeLine(html, setting, node);
                        data.getBeforeA(setting, node, html);
                        view.makeDOMNodeNameBefore(html, setting, node);
                        data.getInnerBeforeA(setting, node, html);
                        view.makeDOMNodeIcon(html, setting, node);
                        data.getInnerAfterA(setting, node, html);
                        view.makeDOMNodeNameAfter(html, setting, node);
                        data.getAfterA(setting, node, html);
                        if (node.isParent && node.open) {
                            view.makeUlHtml(setting, node, html, childHtml.join(''));
                        }
                        view.makeDOMNodeMainAfter(html, setting, node);
                        data.addCreatedNode(setting, node);
                    }
                }
                return html;
            },
            appendParentULDom: function(setting, node) {
                var html = [],
                    nObj = $("#" + node.tId),
                    ulObj = $("#" + node.tId + consts.id.UL),
                    childKey = setting.data.key.children,
                    childHtml = view.appendNodes(setting, node.level+1, node[childKey], node, false, true);
                view.makeUlHtml(setting, node, html, childHtml.join(''));
                if (!nObj.get(0) && !!node.parentTId) {
                    view.appendParentULDom(setting, node.getParentNode());
                    nObj = $("#" + node.tId);
                }
                if (ulObj.get(0)) {
                    ulObj.remove();
                }
                nObj.append(html.join(''));
            },
            asyncNode: function(setting, node, isSilent, callback) {
                var i, l;
                if (node && !node.isParent) {
                    tools.apply(callback);
                    return false;
                } else if (node && node.isAjaxing) {
                    return false;
                } else if (tools.apply(setting.callback.beforeAsync, [setting.treeId, node], true) == false) {
                    tools.apply(callback);
                    return false;
                }
                if (node) {
                    node.isAjaxing = true;
                    var icoObj = $("#" + node.tId + consts.id.ICON);
                    icoObj.attr({"style":"", "class":consts.className.BUTTON + " " + consts.className.ICO_LOADING});
                }

                var tmpParam = {};
                for (i = 0, l = setting.async.autoParam.length; node && i < l; i++) {
                    var pKey = setting.async.autoParam[i].split("="), spKey = pKey;
                    if (pKey.length>1) {
                        spKey = pKey[1];
                        pKey = pKey[0];
                    }
                    tmpParam[spKey] = node[pKey];
                }
                if (tools.isArray(setting.async.otherParam)) {
                    for (i = 0, l = setting.async.otherParam.length; i < l; i += 2) {
                        tmpParam[setting.async.otherParam[i]] = setting.async.otherParam[i + 1];
                    }
                } else {
                    for (var p in setting.async.otherParam) {
                        tmpParam[p] = setting.async.otherParam[p];
                    }
                }

                var _tmpV = data.getRoot(setting)._ver;
                $.ajax({
                    contentType: setting.async.contentType,
                    type: setting.async.type,
                    url: tools.apply(setting.async.url, [setting.treeId, node], setting.async.url),
                    data: tmpParam,
                    dataType: setting.async.dataType,
                    success: function(msg) {
                        if (_tmpV != data.getRoot(setting)._ver) {
                            return;
                        }
                        var newNodes = [];
                        try {
                            if (!msg || msg.length == 0) {
                                newNodes = [];
                            } else if (typeof msg == "string") {
                                newNodes = eval("(" + msg + ")");
                            } else {
                                newNodes = msg;
                            }
                        } catch(err) {
                            newNodes = msg;
                        }

                        if (node) {
                            node.isAjaxing = null;
                            node.zAsync = true;
                        }
                        view.setNodeLineIcos(setting, node);
                        if (newNodes && newNodes !== "") {
                            newNodes = tools.apply(setting.async.dataFilter, [setting.treeId, node, newNodes], newNodes);
                            view.addNodes(setting, node, !!newNodes ? tools.clone(newNodes) : [], !!isSilent);
                        } else {
                            view.addNodes(setting, node, [], !!isSilent);
                        }
                        setting.treeObj.trigger(consts.event.ASYNC_SUCCESS, [setting.treeId, node, msg]);
                        tools.apply(callback);
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        if (_tmpV != data.getRoot(setting)._ver) {
                            return;
                        }
                        if (node) node.isAjaxing = null;
                        view.setNodeLineIcos(setting, node);
                        setting.treeObj.trigger(consts.event.ASYNC_ERROR, [setting.treeId, node, XMLHttpRequest, textStatus, errorThrown]);
                    }
                });
                return true;
            },
            cancelPreSelectedNode: function (setting, node) {
                var list = data.getRoot(setting).curSelectedList;
                for (var i=0, j=list.length-1; j>=i; j--) {
                    if (!node || node === list[j]) {
                        $("#" + list[j].tId + consts.id.A).removeClass(consts.node.CURSELECTED);
                        if (node) {
                            data.removeSelectedNode(setting, node);
                            break;
                        }
                    }
                }
                if (!node) data.getRoot(setting).curSelectedList = [];
            },
            createNodeCallback: function(setting) {
                if (!!setting.callback.onNodeCreated || !!setting.view.addDiyDom) {
                    var root = data.getRoot(setting);
                    while (root.createdNodes.length>0) {
                        var node = root.createdNodes.shift();
                        tools.apply(setting.view.addDiyDom, [setting.treeId, node]);
                        if (!!setting.callback.onNodeCreated) {
                            setting.treeObj.trigger(consts.event.NODECREATED, [setting.treeId, node]);
                        }
                    }
                }
            },
            createNodes: function(setting, level, nodes, parentNode) {
                if (!nodes || nodes.length == 0) return;
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children,
                    openFlag = !parentNode || parentNode.open || !!$("#" + parentNode[childKey][0].tId).get(0);
                root.createdNodes = [];
                var zTreeHtml = view.appendNodes(setting, level, nodes, parentNode, true, openFlag);
                if (!parentNode) {
                    setting.treeObj.append(zTreeHtml.join(''));
                } else {
                    var ulObj = $("#" + parentNode.tId + consts.id.UL);
                    if (ulObj.get(0)) {
                        ulObj.append(zTreeHtml.join(''));
                    }
                }
                view.createNodeCallback(setting);
            },
            destroy: function(setting) {
                if (!setting) return;
                data.initCache(setting);
                data.initRoot(setting);
                event.unbindTree(setting);
                event.unbindEvent(setting);
                setting.treeObj.empty();
            },
            expandCollapseNode: function(setting, node, expandFlag, animateFlag, callback) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children;
                if (!node) {
                    tools.apply(callback, []);
                    return;
                }
                if (root.expandTriggerFlag) {
                    var _callback = callback;
                    callback = function(){
                        if (_callback) _callback();
                        if (node.open) {
                            setting.treeObj.trigger(consts.event.EXPAND, [setting.treeId, node]);
                        } else {
                            setting.treeObj.trigger(consts.event.COLLAPSE, [setting.treeId, node]);
                        }
                    };
                    root.expandTriggerFlag = false;
                }
                if (!node.open && node.isParent && ((!$("#" + node.tId + consts.id.UL).get(0)) || (node[childKey] && node[childKey].length>0 && !$("#" + node[childKey][0].tId).get(0)))) {
                    view.appendParentULDom(setting, node);
                    view.createNodeCallback(setting);
                }
                if (node.open == expandFlag) {
                    tools.apply(callback, []);
                    return;
                }
                var ulObj = $("#" + node.tId + consts.id.UL),
                    switchObj = $("#" + node.tId + consts.id.SWITCH),
                    icoObj = $("#" + node.tId + consts.id.ICON);

                if (node.isParent) {
                    node.open = !node.open;
                    if (node.iconOpen && node.iconClose) {
                        icoObj.attr("style", view.makeNodeIcoStyle(setting, node));
                    }

                    if (node.open) {
                        view.replaceSwitchClass(node, switchObj, consts.folder.OPEN);
                        view.replaceIcoClass(node, icoObj, consts.folder.OPEN);
                        if (animateFlag == false || setting.view.expandSpeed == "") {
                            ulObj.show();
                            tools.apply(callback, []);
                        } else {
                            if (node[childKey] && node[childKey].length > 0) {
                                ulObj.slideDown(setting.view.expandSpeed, callback);
                            } else {
                                ulObj.show();
                                tools.apply(callback, []);
                            }
                        }
                    } else {
                        view.replaceSwitchClass(node, switchObj, consts.folder.CLOSE);
                        view.replaceIcoClass(node, icoObj, consts.folder.CLOSE);
                        if (animateFlag == false || setting.view.expandSpeed == "" || !(node[childKey] && node[childKey].length > 0)) {
                            ulObj.hide();
                            tools.apply(callback, []);
                        } else {
                            ulObj.slideUp(setting.view.expandSpeed, callback);
                        }
                    }
                } else {
                    tools.apply(callback, []);
                }
            },
            expandCollapseParentNode: function(setting, node, expandFlag, animateFlag, callback) {
                if (!node) return;
                if (!node.parentTId) {
                    view.expandCollapseNode(setting, node, expandFlag, animateFlag, callback);
                    return;
                } else {
                    view.expandCollapseNode(setting, node, expandFlag, animateFlag);
                }
                if (node.parentTId) {
                    view.expandCollapseParentNode(setting, node.getParentNode(), expandFlag, animateFlag, callback);
                }
            },
            expandCollapseSonNode: function(setting, node, expandFlag, animateFlag, callback) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children,
                    treeNodes = (node) ? node[childKey]: root[childKey],
                    selfAnimateSign = (node) ? false : animateFlag,
                    expandTriggerFlag = data.getRoot(setting).expandTriggerFlag;
                data.getRoot(setting).expandTriggerFlag = false;
                if (treeNodes) {
                    for (var i = 0, l = treeNodes.length; i < l; i++) {
                        if (treeNodes[i]) view.expandCollapseSonNode(setting, treeNodes[i], expandFlag, selfAnimateSign);
                    }
                }
                data.getRoot(setting).expandTriggerFlag = expandTriggerFlag;
                view.expandCollapseNode(setting, node, expandFlag, animateFlag, callback );
            },
            makeDOMNodeIcon: function(html, setting, node) {
                var nameStr = data.getNodeName(setting, node),
                    name = setting.view.nameIsHTML ? nameStr : nameStr.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
                html.push("<span id='", node.tId, consts.id.ICON,
                    "' title='' treeNode", consts.id.ICON," class='", view.makeNodeIcoClass(setting, node),
                    "' style='", view.makeNodeIcoStyle(setting, node), "'></span><span id='", node.tId, consts.id.SPAN,
                    "'>",name,"</span>");
            },
            makeDOMNodeLine: function(html, setting, node) {
                html.push("<span id='", node.tId, consts.id.SWITCH,	"' title='' class='", view.makeNodeLineClass(setting, node), "' treeNode", consts.id.SWITCH,"></span>");
            },
            makeDOMNodeMainAfter: function(html, setting, node) {
                html.push("</li>");
            },
            makeDOMNodeMainBefore: function(html, setting, node) {
                html.push("<li id='", node.tId, "' class='", consts.className.LEVEL, node.level,"' tabindex='0' hidefocus='true' treenode>");
            },
            makeDOMNodeNameAfter: function(html, setting, node) {
                html.push("</a>");
            },
            makeDOMNodeNameBefore: function(html, setting, node) {
                var title = data.getNodeTitle(setting, node),
                    url = view.makeNodeUrl(setting, node),
                    fontcss = view.makeNodeFontCss(setting, node),
                    fontStyle = [];
                for (var f in fontcss) {
                    fontStyle.push(f, ":", fontcss[f], ";");
                }
                html.push("<a id='", node.tId, consts.id.A, "' class='", consts.className.LEVEL, node.level,"' treeNode", consts.id.A," onclick=\"", (node.click || ''),
                    "\" ", ((url != null && url.length > 0) ? "href='" + url + "'" : ""), " target='",view.makeNodeTarget(node),"' style='", fontStyle.join(''),
                    "'");
                if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle) && title) {html.push("title='", title.replace(/'/g,"&#39;").replace(/</g,'&lt;').replace(/>/g,'&gt;'),"'");}
                html.push(">");
            },
            makeNodeFontCss: function(setting, node) {
                var fontCss = tools.apply(setting.view.fontCss, [setting.treeId, node], setting.view.fontCss);
                return (fontCss && ((typeof fontCss) != "function")) ? fontCss : {};
            },
            makeNodeIcoClass: function(setting, node) {
                var icoCss = ["ico"];
                if (!node.isAjaxing) {
                    icoCss[0] = (node.iconSkin ? node.iconSkin + "_" : "") + icoCss[0];
                    if (node.isParent) {
                        icoCss.push(node.open ? consts.folder.OPEN : consts.folder.CLOSE);
                    } else {
                        icoCss.push(consts.folder.DOCU);
                    }
                }
                return consts.className.BUTTON + " " + icoCss.join('_');
            },
            makeNodeIcoStyle: function(setting, node) {
                var icoStyle = [];
                if (!node.isAjaxing) {
                    var icon = (node.isParent && node.iconOpen && node.iconClose) ? (node.open ? node.iconOpen : node.iconClose) : node.icon;
                    if (icon) icoStyle.push("background:url(", icon, ") 0 0 no-repeat;");
                    if (setting.view.showIcon == false || !tools.apply(setting.view.showIcon, [setting.treeId, node], true)) {
                        icoStyle.push("width:0px;height:0px;");
                    }
                }
                return icoStyle.join('');
            },
            makeNodeLineClass: function(setting, node) {
                var lineClass = [];
                if (setting.view.showLine) {
                    if (node.level == 0 && node.isFirstNode && node.isLastNode) {
                        lineClass.push(consts.line.ROOT);
                    } else if (node.level == 0 && node.isFirstNode) {
                        lineClass.push(consts.line.ROOTS);
                    } else if (node.isLastNode) {
                        lineClass.push(consts.line.BOTTOM);
                    } else {
                        lineClass.push(consts.line.CENTER);
                    }
                } else {
                    lineClass.push(consts.line.NOLINE);
                }
                if (node.isParent) {
                    lineClass.push(node.open ? consts.folder.OPEN : consts.folder.CLOSE);
                } else {
                    lineClass.push(consts.folder.DOCU);
                }
                return view.makeNodeLineClassEx(node) + lineClass.join('_');
            },
            makeNodeLineClassEx: function(node) {
                return consts.className.BUTTON + " " + consts.className.LEVEL + node.level + " " + consts.className.SWITCH + " ";
            },
            makeNodeTarget: function(node) {
                return (node.target || "_blank");
            },
            makeNodeUrl: function(setting, node) {
                var urlKey = setting.data.key.url;
                return node[urlKey] ? node[urlKey] : null;
            },
            makeUlHtml: function(setting, node, html, content) {
                html.push("<ul id='", node.tId, consts.id.UL, "' class='", consts.className.LEVEL, node.level, " ", view.makeUlLineClass(setting, node), "' style='display:", (node.open ? "block": "none"),"'>");
                html.push(content);
                html.push("</ul>");
            },
            makeUlLineClass: function(setting, node) {
                return ((setting.view.showLine && !node.isLastNode) ? consts.line.LINE : "");
            },
            removeChildNodes: function(setting, node) {
                if (!node) return;
                var childKey = setting.data.key.children,
                    nodes = node[childKey];
                if (!nodes) return;

                for (var i = 0, l = nodes.length; i < l; i++) {
                    data.removeNodeCache(setting, nodes[i]);
                }
                data.removeSelectedNode(setting);
                delete node[childKey];

                if (!setting.data.keep.parent) {
                    node.isParent = false;
                    node.open = false;
                    var tmp_switchObj = $("#" + node.tId + consts.id.SWITCH),
                        tmp_icoObj = $("#" + node.tId + consts.id.ICON);
                    view.replaceSwitchClass(node, tmp_switchObj, consts.folder.DOCU);
                    view.replaceIcoClass(node, tmp_icoObj, consts.folder.DOCU);
                    $("#" + node.tId + consts.id.UL).remove();
                } else {
                    $("#" + node.tId + consts.id.UL).empty();
                }
            },
            setFirstNode: function(setting, parentNode) {
                var childKey = setting.data.key.children, childLength = parentNode[childKey].length;
                if ( childLength > 0) {
                    parentNode[childKey][0].isFirstNode = true;
                }
            },
            setLastNode: function(setting, parentNode) {
                var childKey = setting.data.key.children, childLength = parentNode[childKey].length;
                if ( childLength > 0) {
                    parentNode[childKey][childLength - 1].isLastNode = true;
                }
            },
            removeNode: function(setting, node) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children,
                    parentNode = (node.parentTId) ? node.getParentNode() : root;

                node.isFirstNode = false;
                node.isLastNode = false;
                node.getPreNode = function() {return null;};
                node.getNextNode = function() {return null;};

                if (!data.getNodeCache(setting, node.tId)) {
                    return;
                }

                $("#" + node.tId).remove();
                data.removeNodeCache(setting, node);
                data.removeSelectedNode(setting, node);

                for (var i = 0, l = parentNode[childKey].length; i < l; i++) {
                    if (parentNode[childKey][i].tId == node.tId) {
                        parentNode[childKey].splice(i, 1);
                        break;
                    }
                }
                view.setFirstNode(setting, parentNode);
                view.setLastNode(setting, parentNode);

                var tmp_ulObj,tmp_switchObj,tmp_icoObj,
                    childLength = parentNode[childKey].length;

                //repair nodes old parent
                if (!setting.data.keep.parent && childLength == 0) {
                    //old parentNode has no child nodes
                    parentNode.isParent = false;
                    parentNode.open = false;
                    tmp_ulObj = $("#" + parentNode.tId + consts.id.UL);
                    tmp_switchObj = $("#" + parentNode.tId + consts.id.SWITCH);
                    tmp_icoObj = $("#" + parentNode.tId + consts.id.ICON);
                    view.replaceSwitchClass(parentNode, tmp_switchObj, consts.folder.DOCU);
                    view.replaceIcoClass(parentNode, tmp_icoObj, consts.folder.DOCU);
                    tmp_ulObj.css("display", "none");

                } else if (setting.view.showLine && childLength > 0) {
                    //old parentNode has child nodes
                    var newLast = parentNode[childKey][childLength - 1];
                    tmp_ulObj = $("#" + newLast.tId + consts.id.UL);
                    tmp_switchObj = $("#" + newLast.tId + consts.id.SWITCH);
                    tmp_icoObj = $("#" + newLast.tId + consts.id.ICON);
                    if (parentNode == root) {
                        if (parentNode[childKey].length == 1) {
                            //node was root, and ztree has only one root after move node
                            view.replaceSwitchClass(newLast, tmp_switchObj, consts.line.ROOT);
                        } else {
                            var tmp_first_switchObj = $("#" + parentNode[childKey][0].tId + consts.id.SWITCH);
                            view.replaceSwitchClass(parentNode[childKey][0], tmp_first_switchObj, consts.line.ROOTS);
                            view.replaceSwitchClass(newLast, tmp_switchObj, consts.line.BOTTOM);
                        }
                    } else {
                        view.replaceSwitchClass(newLast, tmp_switchObj, consts.line.BOTTOM);
                    }
                    tmp_ulObj.removeClass(consts.line.LINE);
                }
            },
            replaceIcoClass: function(node, obj, newName) {
                if (!obj || node.isAjaxing) return;
                var tmpName = obj.attr("class");
                if (tmpName == undefined) return;
                var tmpList = tmpName.split("_");
                switch (newName) {
                    case consts.folder.OPEN:
                    case consts.folder.CLOSE:
                    case consts.folder.DOCU:
                        tmpList[tmpList.length-1] = newName;
                        break;
                }
                obj.attr("class", tmpList.join("_"));
            },
            replaceSwitchClass: function(node, obj, newName) {
                if (!obj) return;
                var tmpName = obj.attr("class");
                if (tmpName == undefined) return;
                var tmpList = tmpName.split("_");
                switch (newName) {
                    case consts.line.ROOT:
                    case consts.line.ROOTS:
                    case consts.line.CENTER:
                    case consts.line.BOTTOM:
                    case consts.line.NOLINE:
                        tmpList[0] = view.makeNodeLineClassEx(node) + newName;
                        break;
                    case consts.folder.OPEN:
                    case consts.folder.CLOSE:
                    case consts.folder.DOCU:
                        tmpList[1] = newName;
                        break;
                }
                obj.attr("class", tmpList.join("_"));
                if (newName !== consts.folder.DOCU) {
                    obj.removeAttr("disabled");
                } else {
                    obj.attr("disabled", "disabled");
                }
            },
            selectNode: function(setting, node, addFlag) {
                if (!addFlag) {
                    view.cancelPreSelectedNode(setting);
                }
                $("#" + node.tId + consts.id.A).addClass(consts.node.CURSELECTED);
                data.addSelectedNode(setting, node);
            },
            setNodeFontCss: function(setting, treeNode) {
                var aObj = $("#" + treeNode.tId + consts.id.A),
                    fontCss = view.makeNodeFontCss(setting, treeNode);
                if (fontCss) {
                    aObj.css(fontCss);
                }
            },
            setNodeLineIcos: function(setting, node) {
                if (!node) return;
                var switchObj = $("#" + node.tId + consts.id.SWITCH),
                    ulObj = $("#" + node.tId + consts.id.UL),
                    icoObj = $("#" + node.tId + consts.id.ICON),
                    ulLine = view.makeUlLineClass(setting, node);
                if (ulLine.length==0) {
                    ulObj.removeClass(consts.line.LINE);
                } else {
                    ulObj.addClass(ulLine);
                }
                switchObj.attr("class", view.makeNodeLineClass(setting, node));
                if (node.isParent) {
                    switchObj.removeAttr("disabled");
                } else {
                    switchObj.attr("disabled", "disabled");
                }
                icoObj.removeAttr("style");
                icoObj.attr("style", view.makeNodeIcoStyle(setting, node));
                icoObj.attr("class", view.makeNodeIcoClass(setting, node));
            },
            setNodeName: function(setting, node) {
                var title = data.getNodeTitle(setting, node),
                    nObj = $("#" + node.tId + consts.id.SPAN);
                nObj.empty();
                if (setting.view.nameIsHTML) {
                    nObj.html(data.getNodeName(setting, node));
                } else {
                    nObj.text(data.getNodeName(setting, node));
                }
                if (tools.apply(setting.view.showTitle, [setting.treeId, node], setting.view.showTitle)) {
                    var aObj = $("#" + node.tId + consts.id.A);
                    aObj.attr("title", !title ? "" : title);
                }
            },
            setNodeTarget: function(node) {
                var aObj = $("#" + node.tId + consts.id.A);
                aObj.attr("target", view.makeNodeTarget(node));
            },
            setNodeUrl: function(setting, node) {
                var aObj = $("#" + node.tId + consts.id.A),
                    url = view.makeNodeUrl(setting, node);
                if (url == null || url.length == 0) {
                    aObj.removeAttr("href");
                } else {
                    aObj.attr("href", url);
                }
            },
            switchNode: function(setting, node) {
                if (node.open || !tools.canAsync(setting, node)) {
                    view.expandCollapseNode(setting, node, !node.open);
                } else if (setting.async.enable) {
                    if (!view.asyncNode(setting, node)) {
                        view.expandCollapseNode(setting, node, !node.open);
                        return;
                    }
                } else if (node) {
                    view.expandCollapseNode(setting, node, !node.open);
                }
            }
        };
    // zTree defind
    $.fn.zTree = {
        consts : _consts,
        _z : {
            tools: tools,
            view: view,
            event: event,
            data: data
        },
        getZTreeObj: function(treeId) {
            var o = data.getZTreeTools(treeId);
            return o ? o : null;
        },
        destroy: function(treeId) {
            if (!!treeId && treeId.length > 0) {
                view.destroy(data.getSetting(treeId));
            } else {
                for(var s in settings) {
                    view.destroy(settings[s]);
                }
            }
        },
        init: function(obj, zSetting, zNodes) {
            var setting = tools.clone(_setting);
            $.extend(true, setting, zSetting);
            setting.treeId = obj.attr("id");
            setting.treeObj = obj;
            setting.treeObj.empty();
            settings[setting.treeId] = setting;
            //For some older browser,(e.g., ie6)
            if(typeof document.body.style.maxHeight === "undefined") {
                setting.view.expandSpeed = "";
            }
            data.initRoot(setting);
            var root = data.getRoot(setting),
                childKey = setting.data.key.children;
            zNodes = zNodes ? tools.clone(tools.isArray(zNodes)? zNodes : [zNodes]) : [];
            if (setting.data.simpleData.enable) {
                root[childKey] = data.transformTozTreeFormat(setting, zNodes);
            } else {
                root[childKey] = zNodes;
            }

            data.initCache(setting);
            event.unbindTree(setting);
            event.bindTree(setting);
            event.unbindEvent(setting);
            event.bindEvent(setting);

            var zTreeTools = {
                setting : setting,
                addNodes : function(parentNode, newNodes, isSilent) {
                    if (!newNodes) return null;
                    if (!parentNode) parentNode = null;
                    if (parentNode && !parentNode.isParent && setting.data.keep.leaf) return null;
                    var xNewNodes = tools.clone(tools.isArray(newNodes)? newNodes: [newNodes]);
                    function addCallback() {
                        view.addNodes(setting, parentNode, xNewNodes, (isSilent==true));
                    }

                    if (tools.canAsync(setting, parentNode)) {
                        view.asyncNode(setting, parentNode, isSilent, addCallback);
                    } else {
                        addCallback();
                    }
                    return xNewNodes;
                },
                cancelSelectedNode : function(node) {
                    view.cancelPreSelectedNode(this.setting, node);
                },
                destroy : function() {
                    view.destroy(this.setting);
                },
                expandAll : function(expandFlag) {
                    expandFlag = !!expandFlag;
                    view.expandCollapseSonNode(this.setting, null, expandFlag, true);
                    return expandFlag;
                },
                expandNode : function(node, expandFlag, sonSign, focus, callbackFlag) {
                    if (!node || !node.isParent) return null;
                    if (expandFlag !== true && expandFlag !== false) {
                        expandFlag = !node.open;
                    }
                    callbackFlag = !!callbackFlag;

                    if (callbackFlag && expandFlag && (tools.apply(setting.callback.beforeExpand, [setting.treeId, node], true) == false)) {
                        return null;
                    } else if (callbackFlag && !expandFlag && (tools.apply(setting.callback.beforeCollapse, [setting.treeId, node], true) == false)) {
                        return null;
                    }
                    if (expandFlag && node.parentTId) {
                        view.expandCollapseParentNode(this.setting, node.getParentNode(), expandFlag, false);
                    }
                    if (expandFlag === node.open && !sonSign) {
                        return null;
                    }

                    data.getRoot(setting).expandTriggerFlag = callbackFlag;
                    if (sonSign) {
                        view.expandCollapseSonNode(this.setting, node, expandFlag, true, function() {
                            if (focus !== false) {try{$("#" + node.tId).focus().blur();}catch(e){}}
                        });
                    } else {
                        node.open = !expandFlag;
                        view.switchNode(this.setting, node);
                        if (focus !== false) {try{$("#" + node.tId).focus().blur();}catch(e){}}
                    }
                    return expandFlag;
                },
                getNodes : function() {
                    return data.getNodes(this.setting);
                },
                getNodeByParam : function(key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodeByParam(this.setting, parentNode?parentNode[this.setting.data.key.children]:data.getNodes(this.setting), key, value);
                },
                getNodeByTId : function(tId) {
                    return data.getNodeCache(this.setting, tId);
                },
                getNodesByParam : function(key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodesByParam(this.setting, parentNode?parentNode[this.setting.data.key.children]:data.getNodes(this.setting), key, value);
                },
                getNodesByParamFuzzy : function(key, value, parentNode) {
                    if (!key) return null;
                    return data.getNodesByParamFuzzy(this.setting, parentNode?parentNode[this.setting.data.key.children]:data.getNodes(this.setting), key, value);
                },
                getNodesByFilter: function(filter, isSingle, parentNode, invokeParam) {
                    isSingle = !!isSingle;
                    if (!filter || (typeof filter != "function")) return (isSingle ? null : []);
                    return data.getNodesByFilter(this.setting, parentNode?parentNode[this.setting.data.key.children]:data.getNodes(this.setting), filter, isSingle, invokeParam);
                },
                getNodeIndex : function(node) {
                    if (!node) return null;
                    var childKey = setting.data.key.children,
                        parentNode = (node.parentTId) ? node.getParentNode() : data.getRoot(this.setting);
                    for (var i=0, l = parentNode[childKey].length; i < l; i++) {
                        if (parentNode[childKey][i] == node) return i;
                    }
                    return -1;
                },
                getSelectedNodes : function() {
                    var r = [], list = data.getRoot(this.setting).curSelectedList;
                    for (var i=0, l=list.length; i<l; i++) {
                        r.push(list[i]);
                    }
                    return r;
                },
                isSelectedNode : function(node) {
                    return data.isSelectedNode(this.setting, node);
                },
                reAsyncChildNodes : function(parentNode, reloadType, isSilent) {
                    if (!this.setting.async.enable) return;
                    var isRoot = !parentNode;
                    if (isRoot) {
                        parentNode = data.getRoot(this.setting);
                    }
                    if (reloadType=="refresh") {
                        var childKey = this.setting.data.key.children;
                        for (var i = 0, l = parentNode[childKey] ? parentNode[childKey].length : 0; i < l; i++) {
                            data.removeNodeCache(setting, parentNode[childKey][i]);
                        }
                        data.removeSelectedNode(setting);
                        parentNode[childKey] = [];
                        if (isRoot) {
                            this.setting.treeObj.empty();
                        } else {
                            var ulObj = $("#" + parentNode.tId + consts.id.UL);
                            ulObj.empty();
                        }
                    }
                    view.asyncNode(this.setting, isRoot? null:parentNode, !!isSilent);
                },
                refresh : function() {
                    this.setting.treeObj.empty();
                    var root = data.getRoot(this.setting),
                        nodes = root[this.setting.data.key.children]
                    data.initRoot(this.setting);
                    root[this.setting.data.key.children] = nodes
                    data.initCache(this.setting);
                    view.createNodes(this.setting, 0, root[this.setting.data.key.children]);
                },
                removeChildNodes : function(node) {
                    if (!node) return null;
                    var childKey = setting.data.key.children,
                        nodes = node[childKey];
                    view.removeChildNodes(setting, node);
                    return nodes ? nodes : null;
                },
                removeNode : function(node, callbackFlag) {
                    if (!node) return;
                    callbackFlag = !!callbackFlag;
                    if (callbackFlag && tools.apply(setting.callback.beforeRemove, [setting.treeId, node], true) == false) return;
                    view.removeNode(setting, node);
                    if (callbackFlag) {
                        this.setting.treeObj.trigger(consts.event.REMOVE, [setting.treeId, node]);
                    }
                },
                selectNode : function(node, addFlag) {
                    if (!node) return;
                    if (tools.uCanDo(this.setting)) {
                        addFlag = setting.view.selectedMulti && addFlag;
                        if (node.parentTId) {
                            view.expandCollapseParentNode(this.setting, node.getParentNode(), true, false, function() {
                                try{$("#" + node.tId).focus().blur();}catch(e){}
                            });
                        } else {
                            try{$("#" + node.tId).focus().blur();}catch(e){}
                        }
                        view.selectNode(this.setting, node, addFlag);
                    }
                },
                transformTozTreeNodes : function(simpleNodes) {
                    return data.transformTozTreeFormat(this.setting, simpleNodes);
                },
                transformToArray : function(nodes) {
                    return data.transformToArrayFormat(this.setting, nodes);
                },
                updateNode : function(node, checkTypeFlag) {
                    if (!node) return;
                    var nObj = $("#" + node.tId);
                    if (nObj.get(0) && tools.uCanDo(this.setting)) {
                        view.setNodeName(this.setting, node);
                        view.setNodeTarget(node);
                        view.setNodeUrl(this.setting, node);
                        view.setNodeLineIcos(this.setting, node);
                        view.setNodeFontCss(this.setting, node);
                    }
                }
            }
            root.treeTools = zTreeTools;
            data.setZTreeTools(setting, zTreeTools);

            if (root[childKey] && root[childKey].length > 0) {
                view.createNodes(setting, 0, root[childKey]);
            } else if (setting.async.enable && setting.async.url && setting.async.url !== '') {
                view.asyncNode(setting);
            }
            return zTreeTools;
        }
    };

    var zt = $.fn.zTree,
        consts = zt.consts;
})(jQuery);
/*
 * JQuery zTree excheck 3.5.12
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2013-03-11
 */
(function($){
    //default consts of excheck
    var _consts = {
            event: {
                CHECK: "ztree_check"
            },
            id: {
                CHECK: "_check"
            },
            checkbox: {
                STYLE: "checkbox",
                DEFAULT: "chk",
                DISABLED: "disable",
                FALSE: "false",
                TRUE: "true",
                FULL: "full",
                PART: "part",
                FOCUS: "focus"
            },
            radio: {
                STYLE: "radio",
                TYPE_ALL: "all",
                TYPE_LEVEL: "level"
            }
        },
    //default setting of excheck
        _setting = {
            check: {
                enable: false,
                autoCheckTrigger: false,
                chkStyle: _consts.checkbox.STYLE,
                nocheckInherit: false,
                chkDisabledInherit: false,
                radioType: _consts.radio.TYPE_LEVEL,
                chkboxType: {
                    "Y": "ps",
                    "N": "ps"
                }
            },
            data: {
                key: {
                    checked: "checked"
                }
            },
            callback: {
                beforeCheck:null,
                onCheck:null
            }
        },
    //default root of excheck
        _initRoot = function (setting) {
            var r = data.getRoot(setting);
            r.radioCheckedList = [];
        },
    //default cache of excheck
        _initCache = function(treeId) {},
    //default bind event of excheck
        _bindEvent = function(setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.bind(c.CHECK, function (event, srcEvent, treeId, node) {
                tools.apply(setting.callback.onCheck, [!!srcEvent?srcEvent : event, treeId, node]);
            });
        },
        _unbindEvent = function(setting) {
            var o = setting.treeObj,
                c = consts.event;
            o.unbind(c.CHECK);
        },
    //default event proxy of excheck
        _eventProxy = function(e) {
            var target = e.target,
                setting = data.getSetting(e.data.treeId),
                tId = "", node = null,
                nodeEventType = "", treeEventType = "",
                nodeEventCallback = null, treeEventCallback = null;

            if (tools.eqs(e.type, "mouseover")) {
                if (setting.check.enable && tools.eqs(target.tagName, "span") && target.getAttribute("treeNode"+ consts.id.CHECK) !== null) {
                    tId = target.parentNode.id;
                    nodeEventType = "mouseoverCheck";
                }
            } else if (tools.eqs(e.type, "mouseout")) {
                if (setting.check.enable && tools.eqs(target.tagName, "span") && target.getAttribute("treeNode"+ consts.id.CHECK) !== null) {
                    tId = target.parentNode.id;
                    nodeEventType = "mouseoutCheck";
                }
            } else if (tools.eqs(e.type, "click")) {
                if (setting.check.enable && tools.eqs(target.tagName, "span") && target.getAttribute("treeNode"+ consts.id.CHECK) !== null) {
                    tId = target.parentNode.id;
                    nodeEventType = "checkNode";
                }
            }
            if (tId.length>0) {
                node = data.getNodeCache(setting, tId);
                switch (nodeEventType) {
                    case "checkNode" :
                        nodeEventCallback = _handler.onCheckNode;
                        break;
                    case "mouseoverCheck" :
                        nodeEventCallback = _handler.onMouseoverCheck;
                        break;
                    case "mouseoutCheck" :
                        nodeEventCallback = _handler.onMouseoutCheck;
                        break;
                }
            }
            var proxyResult = {
                stop: false,
                node: node,
                nodeEventType: nodeEventType,
                nodeEventCallback: nodeEventCallback,
                treeEventType: treeEventType,
                treeEventCallback: treeEventCallback
            };
            return proxyResult
        },
    //default init node of excheck
        _initNode = function(setting, level, n, parentNode, isFirstNode, isLastNode, openFlag) {
            if (!n) return;
            var checkedKey = setting.data.key.checked;
            if (typeof n[checkedKey] == "string") n[checkedKey] = tools.eqs(n[checkedKey], "true");
            n[checkedKey] = !!n[checkedKey];
            n.checkedOld = n[checkedKey];
            if (typeof n.nocheck == "string") n.nocheck = tools.eqs(n.nocheck, "true");
            n.nocheck = !!n.nocheck || (setting.check.nocheckInherit && parentNode && !!parentNode.nocheck);
            if (typeof n.chkDisabled == "string") n.chkDisabled = tools.eqs(n.chkDisabled, "true");
            n.chkDisabled = !!n.chkDisabled || (setting.check.chkDisabledInherit && parentNode && !!parentNode.chkDisabled);
            if (typeof n.halfCheck == "string") n.halfCheck = tools.eqs(n.halfCheck, "true");
            n.halfCheck = !!n.halfCheck;
            n.check_Child_State = -1;
            n.check_Focus = false;
            n.getCheckStatus = function() {return data.getCheckStatus(setting, n);};
        },
    //add dom for check
        _beforeA = function(setting, node, html) {
            var checkedKey = setting.data.key.checked;
            if (setting.check.enable) {
                data.makeChkFlag(setting, node);
                if (setting.check.chkStyle == consts.radio.STYLE && setting.check.radioType == consts.radio.TYPE_ALL && node[checkedKey] ) {
                    var r = data.getRoot(setting);
                    r.radioCheckedList.push(node);
                }
                html.push("<span ID='", node.tId, consts.id.CHECK, "' class='", view.makeChkClass(setting, node), "' treeNode", consts.id.CHECK, (node.nocheck === true?" style='display:none;'":""),"></span>");
            }
        },
    //update zTreeObj, add method of check
        _zTreeTools = function(setting, zTreeTools) {
            zTreeTools.checkNode = function(node, checked, checkTypeFlag, callbackFlag) {
                var checkedKey = this.setting.data.key.checked;
                if (node.chkDisabled === true) return;
                if (checked !== true && checked !== false) {
                    checked = !node[checkedKey];
                }
                callbackFlag = !!callbackFlag;

                if (node[checkedKey] === checked && !checkTypeFlag) {
                    return;
                } else if (callbackFlag && tools.apply(this.setting.callback.beforeCheck, [this.setting.treeId, node], true) == false) {
                    return;
                }
                if (tools.uCanDo(this.setting) && this.setting.check.enable && node.nocheck !== true) {
                    node[checkedKey] = checked;
                    var checkObj = $("#" + node.tId + consts.id.CHECK);
                    if (checkTypeFlag || this.setting.check.chkStyle === consts.radio.STYLE) view.checkNodeRelation(this.setting, node);
                    view.setChkClass(this.setting, checkObj, node);
                    view.repairParentChkClassWithSelf(this.setting, node);
                    if (callbackFlag) {
                        setting.treeObj.trigger(consts.event.CHECK, [null, setting.treeId, node]);
                    }
                }
            }

            zTreeTools.checkAllNodes = function(checked) {
                view.repairAllChk(this.setting, !!checked);
            }

            zTreeTools.getCheckedNodes = function(checked) {
                var childKey = this.setting.data.key.children;
                checked = (checked !== false);
                return data.getTreeCheckedNodes(this.setting, data.getRoot(setting)[childKey], checked);
            }

            zTreeTools.getChangeCheckedNodes = function() {
                var childKey = this.setting.data.key.children;
                return data.getTreeChangeCheckedNodes(this.setting, data.getRoot(setting)[childKey]);
            }

            zTreeTools.setChkDisabled = function(node, disabled, inheritParent, inheritChildren) {
                disabled = !!disabled;
                inheritParent = !!inheritParent;
                inheritChildren = !!inheritChildren;
                view.repairSonChkDisabled(this.setting, node, disabled, inheritChildren);
                view.repairParentChkDisabled(this.setting, node.getParentNode(), disabled, inheritParent);
            }

            var _updateNode = zTreeTools.updateNode;
            zTreeTools.updateNode = function(node, checkTypeFlag) {
                if (_updateNode) _updateNode.apply(zTreeTools, arguments);
                if (!node || !this.setting.check.enable) return;
                var nObj = $("#" + node.tId);
                if (nObj.get(0) && tools.uCanDo(this.setting)) {
                    var checkObj = $("#" + node.tId + consts.id.CHECK);
                    if (checkTypeFlag == true || this.setting.check.chkStyle === consts.radio.STYLE) view.checkNodeRelation(this.setting, node);
                    view.setChkClass(this.setting, checkObj, node);
                    view.repairParentChkClassWithSelf(this.setting, node);
                }
            }
        },
    //method of operate data
        _data = {
            getRadioCheckedList: function(setting) {
                var checkedList = data.getRoot(setting).radioCheckedList;
                for (var i=0, j=checkedList.length; i<j; i++) {
                    if(!data.getNodeCache(setting, checkedList[i].tId)) {
                        checkedList.splice(i, 1);
                        i--; j--;
                    }
                }
                return checkedList;
            },
            getCheckStatus: function(setting, node) {
                if (!setting.check.enable || node.nocheck || node.chkDisabled) return null;
                var checkedKey = setting.data.key.checked,
                    r = {
                        checked: node[checkedKey],
                        half: node.halfCheck ? node.halfCheck : (setting.check.chkStyle == consts.radio.STYLE ? (node.check_Child_State === 2) : (node[checkedKey] ? (node.check_Child_State > -1 && node.check_Child_State < 2) : (node.check_Child_State > 0)))
                    };
                return r;
            },
            getTreeCheckedNodes: function(setting, nodes, checked, results) {
                if (!nodes) return [];
                var childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked,
                    onlyOne = (checked && setting.check.chkStyle == consts.radio.STYLE && setting.check.radioType == consts.radio.TYPE_ALL);
                results = !results ? [] : results;
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i].nocheck !== true && nodes[i].chkDisabled !== true && nodes[i][checkedKey] == checked) {
                        results.push(nodes[i]);
                        if(onlyOne) {
                            break;
                        }
                    }
                    data.getTreeCheckedNodes(setting, nodes[i][childKey], checked, results);
                    if(onlyOne && results.length > 0) {
                        break;
                    }
                }
                return results;
            },
            getTreeChangeCheckedNodes: function(setting, nodes, results) {
                if (!nodes) return [];
                var childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked;
                results = !results ? [] : results;
                for (var i = 0, l = nodes.length; i < l; i++) {
                    if (nodes[i].nocheck !== true && nodes[i].chkDisabled !== true && nodes[i][checkedKey] != nodes[i].checkedOld) {
                        results.push(nodes[i]);
                    }
                    data.getTreeChangeCheckedNodes(setting, nodes[i][childKey], results);
                }
                return results;
            },
            makeChkFlag: function(setting, node) {
                if (!node) return;
                var childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked,
                    chkFlag = -1;
                if (node[childKey]) {
                    for (var i = 0, l = node[childKey].length; i < l; i++) {
                        var cNode = node[childKey][i];
                        var tmp = -1;
                        if (setting.check.chkStyle == consts.radio.STYLE) {
                            if (cNode.nocheck === true || cNode.chkDisabled === true) {
                                tmp = cNode.check_Child_State;
                            } else if (cNode.halfCheck === true) {
                                tmp = 2;
                            } else if (cNode[checkedKey]) {
                                tmp = 2;
                            } else {
                                tmp = cNode.check_Child_State > 0 ? 2:0;
                            }
                            if (tmp == 2) {
                                chkFlag = 2; break;
                            } else if (tmp == 0){
                                chkFlag = 0;
                            }
                        } else if (setting.check.chkStyle == consts.checkbox.STYLE) {
                            if (cNode.nocheck === true || cNode.chkDisabled === true) {
                                tmp = cNode.check_Child_State;
                            } else if (cNode.halfCheck === true) {
                                tmp = 1;
                            } else if (cNode[checkedKey] ) {
                                tmp = (cNode.check_Child_State === -1 || cNode.check_Child_State === 2) ? 2 : 1;
                            } else {
                                tmp = (cNode.check_Child_State > 0) ? 1 : 0;
                            }
                            if (tmp === 1) {
                                chkFlag = 1; break;
                            } else if (tmp === 2 && chkFlag > -1 && i > 0 && tmp !== chkFlag) {
                                chkFlag = 1; break;
                            } else if (chkFlag === 2 && tmp > -1 && tmp < 2) {
                                chkFlag = 1; break;
                            } else if (tmp > -1) {
                                chkFlag = tmp;
                            }
                        }
                    }
                }
                node.check_Child_State = chkFlag;
            }
        },
    //method of event proxy
        _event = {

        },
    //method of event handler
        _handler = {
            onCheckNode: function (event, node) {
                if (node.chkDisabled === true) return false;
                var setting = data.getSetting(event.data.treeId),
                    checkedKey = setting.data.key.checked;
                if (tools.apply(setting.callback.beforeCheck, [setting.treeId, node], true) == false) return true;
                node[checkedKey] = !node[checkedKey];
                view.checkNodeRelation(setting, node);
                var checkObj = $("#" + node.tId + consts.id.CHECK);
                view.setChkClass(setting, checkObj, node);
                view.repairParentChkClassWithSelf(setting, node);
                setting.treeObj.trigger(consts.event.CHECK, [event, setting.treeId, node]);
                return true;
            },
            onMouseoverCheck: function(event, node) {
                if (node.chkDisabled === true) return false;
                var setting = data.getSetting(event.data.treeId),
                    checkObj = $("#" + node.tId + consts.id.CHECK);
                node.check_Focus = true;
                view.setChkClass(setting, checkObj, node);
                return true;
            },
            onMouseoutCheck: function(event, node) {
                if (node.chkDisabled === true) return false;
                var setting = data.getSetting(event.data.treeId),
                    checkObj = $("#" + node.tId + consts.id.CHECK);
                node.check_Focus = false;
                view.setChkClass(setting, checkObj, node);
                return true;
            }
        },
    //method of tools for zTree
        _tools = {

        },
    //method of operate ztree dom
        _view = {
            checkNodeRelation: function(setting, node) {
                var pNode, i, l,
                    childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked,
                    r = consts.radio;
                if (setting.check.chkStyle == r.STYLE) {
                    var checkedList = data.getRadioCheckedList(setting);
                    if (node[checkedKey]) {
                        if (setting.check.radioType == r.TYPE_ALL) {
                            for (i = checkedList.length-1; i >= 0; i--) {
                                pNode = checkedList[i];
                                pNode[checkedKey] = false;
                                checkedList.splice(i, 1);

                                view.setChkClass(setting, $("#" + pNode.tId + consts.id.CHECK), pNode);
                                if (pNode.parentTId != node.parentTId) {
                                    view.repairParentChkClassWithSelf(setting, pNode);
                                }
                            }
                            checkedList.push(node);
                        } else {
                            var parentNode = (node.parentTId) ? node.getParentNode() : data.getRoot(setting);
                            for (i = 0, l = parentNode[childKey].length; i < l; i++) {
                                pNode = parentNode[childKey][i];
                                if (pNode[checkedKey] && pNode != node) {
                                    pNode[checkedKey] = false;
                                    view.setChkClass(setting, $("#" + pNode.tId + consts.id.CHECK), pNode);
                                }
                            }
                        }
                    } else if (setting.check.radioType == r.TYPE_ALL) {
                        for (i = 0, l = checkedList.length; i < l; i++) {
                            if (node == checkedList[i]) {
                                checkedList.splice(i, 1);
                                break;
                            }
                        }
                    }

                } else {
                    if (node[checkedKey] && (!node[childKey] || node[childKey].length==0 || setting.check.chkboxType.Y.indexOf("s") > -1)) {
                        view.setSonNodeCheckBox(setting, node, true);
                    }
                    if (!node[checkedKey] && (!node[childKey] || node[childKey].length==0 || setting.check.chkboxType.N.indexOf("s") > -1)) {
                        view.setSonNodeCheckBox(setting, node, false);
                    }
                    if (node[checkedKey] && setting.check.chkboxType.Y.indexOf("p") > -1) {
                        view.setParentNodeCheckBox(setting, node, true);
                    }
                    if (!node[checkedKey] && setting.check.chkboxType.N.indexOf("p") > -1) {
                        view.setParentNodeCheckBox(setting, node, false);
                    }
                }
            },
            makeChkClass: function(setting, node) {
                var checkedKey = setting.data.key.checked,
                    c = consts.checkbox, r = consts.radio,
                    fullStyle = "";
                if (node.chkDisabled === true) {
                    fullStyle = c.DISABLED;
                } else if (node.halfCheck) {
                    fullStyle = c.PART;
                } else if (setting.check.chkStyle == r.STYLE) {
                    fullStyle = (node.check_Child_State < 1)? c.FULL:c.PART;
                } else {
                    fullStyle = node[checkedKey] ? ((node.check_Child_State === 2 || node.check_Child_State === -1) ? c.FULL:c.PART) : ((node.check_Child_State < 1)? c.FULL:c.PART);
                }
                var chkName = setting.check.chkStyle + "_" + (node[checkedKey] ? c.TRUE : c.FALSE) + "_" + fullStyle;
                chkName = (node.check_Focus && node.chkDisabled !== true) ? chkName + "_" + c.FOCUS : chkName;
                return consts.className.BUTTON + " " + c.DEFAULT + " " + chkName;
            },
            repairAllChk: function(setting, checked) {
                if (setting.check.enable && setting.check.chkStyle === consts.checkbox.STYLE) {
                    var checkedKey = setting.data.key.checked,
                        childKey = setting.data.key.children,
                        root = data.getRoot(setting);
                    for (var i = 0, l = root[childKey].length; i<l ; i++) {
                        var node = root[childKey][i];
                        if (node.nocheck !== true && node.chkDisabled !== true) {
                            node[checkedKey] = checked;
                        }
                        view.setSonNodeCheckBox(setting, node, checked);
                    }
                }
            },
            repairChkClass: function(setting, node) {
                if (!node) return;
                data.makeChkFlag(setting, node);
                if (node.nocheck !== true) {
                    var checkObj = $("#" + node.tId + consts.id.CHECK);
                    view.setChkClass(setting, checkObj, node);
                }
            },
            repairParentChkClass: function(setting, node) {
                if (!node || !node.parentTId) return;
                var pNode = node.getParentNode();
                view.repairChkClass(setting, pNode);
                view.repairParentChkClass(setting, pNode);
            },
            repairParentChkClassWithSelf: function(setting, node) {
                if (!node) return;
                var childKey = setting.data.key.children;
                if (node[childKey] && node[childKey].length > 0) {
                    view.repairParentChkClass(setting, node[childKey][0]);
                } else {
                    view.repairParentChkClass(setting, node);
                }
            },
            repairSonChkDisabled: function(setting, node, chkDisabled, inherit) {
                if (!node) return;
                var childKey = setting.data.key.children;
                if (node.chkDisabled != chkDisabled) {
                    node.chkDisabled = chkDisabled;
                }
                view.repairChkClass(setting, node);
                if (node[childKey] && inherit) {
                    for (var i = 0, l = node[childKey].length; i < l; i++) {
                        var sNode = node[childKey][i];
                        view.repairSonChkDisabled(setting, sNode, chkDisabled, inherit);
                    }
                }
            },
            repairParentChkDisabled: function(setting, node, chkDisabled, inherit) {
                if (!node) return;
                if (node.chkDisabled != chkDisabled && inherit) {
                    node.chkDisabled = chkDisabled;
                }
                view.repairChkClass(setting, node);
                view.repairParentChkDisabled(setting, node.getParentNode(), chkDisabled, inherit);
            },
            setChkClass: function(setting, obj, node) {
                if (!obj) return;
                if (node.nocheck === true) {
                    obj.hide();
                } else {
                    obj.show();
                }
                obj.removeClass();
                obj.addClass(view.makeChkClass(setting, node));
            },
            setParentNodeCheckBox: function(setting, node, value, srcNode) {
                var childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked,
                    checkObj = $("#" + node.tId + consts.id.CHECK);
                if (!srcNode) srcNode = node;
                data.makeChkFlag(setting, node);
                if (node.nocheck !== true && node.chkDisabled !== true) {
                    node[checkedKey] = value;
                    view.setChkClass(setting, checkObj, node);
                    if (setting.check.autoCheckTrigger && node != srcNode) {
                        setting.treeObj.trigger(consts.event.CHECK, [null, setting.treeId, node]);
                    }
                }
                if (node.parentTId) {
                    var pSign = true;
                    if (!value) {
                        var pNodes = node.getParentNode()[childKey];
                        for (var i = 0, l = pNodes.length; i < l; i++) {
                            if ((pNodes[i].nocheck !== true && pNodes[i].chkDisabled !== true && pNodes[i][checkedKey])
                                || ((pNodes[i].nocheck === true || pNodes[i].chkDisabled === true) && pNodes[i].check_Child_State > 0)) {
                                pSign = false;
                                break;
                            }
                        }
                    }
                    if (pSign) {
                        view.setParentNodeCheckBox(setting, node.getParentNode(), value, srcNode);
                    }
                }
            },
            setSonNodeCheckBox: function(setting, node, value, srcNode) {
                if (!node) return;
                var childKey = setting.data.key.children,
                    checkedKey = setting.data.key.checked,
                    checkObj = $("#" + node.tId + consts.id.CHECK);
                if (!srcNode) srcNode = node;

                var hasDisable = false;
                if (node[childKey]) {
                    for (var i = 0, l = node[childKey].length; i < l && node.chkDisabled !== true; i++) {
                        var sNode = node[childKey][i];
                        view.setSonNodeCheckBox(setting, sNode, value, srcNode);
                        if (sNode.chkDisabled === true) hasDisable = true;
                    }
                }

                if (node != data.getRoot(setting) && node.chkDisabled !== true) {
                    if (hasDisable && node.nocheck !== true) {
                        data.makeChkFlag(setting, node);
                    }
                    if (node.nocheck !== true && node.chkDisabled !== true) {
                        node[checkedKey] = value;
                        if (!hasDisable) node.check_Child_State = (node[childKey] && node[childKey].length > 0) ? (value ? 2 : 0) : -1;
                    } else {
                        node.check_Child_State = -1;
                    }
                    view.setChkClass(setting, checkObj, node);
                    if (setting.check.autoCheckTrigger && node != srcNode && node.nocheck !== true && node.chkDisabled !== true) {
                        setting.treeObj.trigger(consts.event.CHECK, [null, setting.treeId, node]);
                    }
                }

            }
        },

        _z = {
            tools: _tools,
            view: _view,
            event: _event,
            data: _data
        };
    $.extend(true, $.fn.zTree.consts, _consts);
    $.extend(true, $.fn.zTree._z, _z);

    var zt = $.fn.zTree,
        tools = zt._z.tools,
        consts = zt.consts,
        view = zt._z.view,
        data = zt._z.data,
        event = zt._z.event;

    data.exSetting(_setting);
    data.addInitBind(_bindEvent);
    data.addInitUnBind(_unbindEvent);
    data.addInitCache(_initCache);
    data.addInitNode(_initNode);
    data.addInitProxy(_eventProxy);
    data.addInitRoot(_initRoot);
    data.addBeforeA(_beforeA);
    data.addZTreeTools(_zTreeTools);

    var _createNodes = view.createNodes;
    view.createNodes = function(setting, level, nodes, parentNode) {
        if (_createNodes) _createNodes.apply(view, arguments);
        if (!nodes) return;
        view.repairParentChkClassWithSelf(setting, parentNode);
    }
    var _removeNode = view.removeNode;
    view.removeNode = function(setting, node) {
        var parentNode = node.getParentNode();
        if (_removeNode) _removeNode.apply(view, arguments);
        if (!node || !parentNode) return;
        view.repairChkClass(setting, parentNode);
        view.repairParentChkClass(setting, parentNode);
    }

    var _appendNodes = view.appendNodes;
    view.appendNodes = function(setting, level, nodes, parentNode, initFlag, openFlag) {
        var html = "";
        if (_appendNodes) {
            html = _appendNodes.apply(view, arguments);
        }
        if (parentNode) {
            data.makeChkFlag(setting, parentNode);
        }
        return html;
    }
})(jQuery);
/*
 * JQuery zTree exedit 3.5.12
 * http://zTree.me/
 *
 * Copyright (c) 2010 Hunter.z
 *
 * Licensed same as jquery - MIT License
 * http://www.opensource.org/licenses/mit-license.php
 *
 * email: hunter.z@263.net
 * Date: 2013-03-11
 */
(function($){
    //default consts of exedit
    var _consts = {
            event: {
                DRAG: "ztree_drag",
                DROP: "ztree_drop",
                REMOVE: "ztree_remove",
                RENAME: "ztree_rename"
            },
            id: {
                EDIT: "_edit",
                INPUT: "_input",
                REMOVE: "_remove"
            },
            move: {
                TYPE_INNER: "inner",
                TYPE_PREV: "prev",
                TYPE_NEXT: "next"
            },
            node: {
                CURSELECTED_EDIT: "curSelectedNode_Edit",
                TMPTARGET_TREE: "tmpTargetzTree",
                TMPTARGET_NODE: "tmpTargetNode"
            }
        },
    //default setting of exedit
        _setting = {
            edit: {
                enable: false,
                editNameSelectAll: false,
                showRemoveBtn: true,
                showRenameBtn: true,
                removeTitle: "remove",
                renameTitle: "rename",
                drag: {
                    autoExpandTrigger: false,
                    isCopy: true,
                    isMove: true,
                    prev: true,
                    next: true,
                    inner: true,
                    minMoveSize: 5,
                    borderMax: 10,
                    borderMin: -5,
                    maxShowNodeNum: 5,
                    autoOpenTime: 500
                }
            },
            view: {
                addHoverDom: null,
                removeHoverDom: null
            },
            callback: {
                beforeDrag:null,
                beforeDragOpen:null,
                beforeDrop:null,
                beforeEditName:null,
                beforeRename:null,
                onDrag:null,
                onDrop:null,
                onRename:null
            }
        },
    //default root of exedit
        _initRoot = function (setting) {
            var r = data.getRoot(setting);
            r.curEditNode = null;
            r.curEditInput = null;
            r.curHoverNode = null;
            r.dragFlag = 0;
            r.dragNodeShowBefore = [];
            r.dragMaskList = new Array();
            r.showHoverDom = true;
        },
    //default cache of exedit
        _initCache = function(treeId) {},
    //default bind event of exedit
        _bindEvent = function(setting) {
            var o = setting.treeObj;
            var c = consts.event;
            o.bind(c.RENAME, function (event, treeId, treeNode) {
                tools.apply(setting.callback.onRename, [event, treeId, treeNode]);
            });

            o.bind(c.REMOVE, function (event, treeId, treeNode) {
                tools.apply(setting.callback.onRemove, [event, treeId, treeNode]);
            });

            o.bind(c.DRAG, function (event, srcEvent, treeId, treeNodes) {
                tools.apply(setting.callback.onDrag, [srcEvent, treeId, treeNodes]);
            });

            o.bind(c.DROP, function (event, srcEvent, treeId, treeNodes, targetNode, moveType, isCopy) {
                tools.apply(setting.callback.onDrop, [srcEvent, treeId, treeNodes, targetNode, moveType, isCopy]);
            });
        },
        _unbindEvent = function(setting) {
            var o = setting.treeObj;
            var c = consts.event;
            o.unbind(c.RENAME);
            o.unbind(c.REMOVE);
            o.unbind(c.DRAG);
            o.unbind(c.DROP);
        },
    //default event proxy of exedit
        _eventProxy = function(e) {
            var target = e.target,
                setting = data.getSetting(e.data.treeId),
                relatedTarget = e.relatedTarget,
                tId = "", node = null,
                nodeEventType = "", treeEventType = "",
                nodeEventCallback = null, treeEventCallback = null,
                tmp = null;

            if (tools.eqs(e.type, "mouseover")) {
                tmp = tools.getMDom(setting, target, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                if (tmp) {
                    tId = tmp.parentNode.id;
                    nodeEventType = "hoverOverNode";
                }
            } else if (tools.eqs(e.type, "mouseout")) {
                tmp = tools.getMDom(setting, relatedTarget, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                if (!tmp) {
                    tId = "remove";
                    nodeEventType = "hoverOutNode";
                }
            } else if (tools.eqs(e.type, "mousedown")) {
                tmp = tools.getMDom(setting, target, [{tagName:"a", attrName:"treeNode"+consts.id.A}]);
                if (tmp) {
                    tId = tmp.parentNode.id;
                    nodeEventType = "mousedownNode";
                }
            }
            if (tId.length>0) {
                node = data.getNodeCache(setting, tId);
                switch (nodeEventType) {
                    case "mousedownNode" :
                        nodeEventCallback = _handler.onMousedownNode;
                        break;
                    case "hoverOverNode" :
                        nodeEventCallback = _handler.onHoverOverNode;
                        break;
                    case "hoverOutNode" :
                        nodeEventCallback = _handler.onHoverOutNode;
                        break;
                }
            }
            var proxyResult = {
                stop: false,
                node: node,
                nodeEventType: nodeEventType,
                nodeEventCallback: nodeEventCallback,
                treeEventType: treeEventType,
                treeEventCallback: treeEventCallback
            };
            return proxyResult
        },
    //default init node of exedit
        _initNode = function(setting, level, n, parentNode, isFirstNode, isLastNode, openFlag) {
            if (!n) return;
            n.isHover = false;
            n.editNameFlag = false;
        },
    //update zTreeObj, add method of edit
        _zTreeTools = function(setting, zTreeTools) {
            zTreeTools.cancelEditName = function(newName) {
                var root = data.getRoot(setting),
                    nameKey = setting.data.key.name,
                    node = root.curEditNode;
                if (!root.curEditNode) return;
                view.cancelCurEditNode(setting, newName?newName:node[nameKey]);
            }
            zTreeTools.copyNode = function(targetNode, node, moveType, isSilent) {
                if (!node) return null;
                if (targetNode && !targetNode.isParent && setting.data.keep.leaf && moveType === consts.move.TYPE_INNER) return null;
                var newNode = tools.clone(node);
                if (!targetNode) {
                    targetNode = null;
                    moveType = consts.move.TYPE_INNER;
                }
                if (moveType == consts.move.TYPE_INNER) {
                    function copyCallback() {
                        view.addNodes(setting, targetNode, [newNode], isSilent);
                    }

                    if (tools.canAsync(setting, targetNode)) {
                        view.asyncNode(setting, targetNode, isSilent, copyCallback);
                    } else {
                        copyCallback();
                    }
                } else {
                    view.addNodes(setting, targetNode.parentNode, [newNode], isSilent);
                    view.moveNode(setting, targetNode, newNode, moveType, false, isSilent);
                }
                return newNode;
            }
            zTreeTools.editName = function(node) {
                if (!node || !node.tId || node !== data.getNodeCache(setting, node.tId)) return;
                if (node.parentTId) view.expandCollapseParentNode(setting, node.getParentNode(), true);
                view.editNode(setting, node)
            }
            zTreeTools.moveNode = function(targetNode, node, moveType, isSilent) {
                if (!node) return node;
                if (targetNode && !targetNode.isParent && setting.data.keep.leaf && moveType === consts.move.TYPE_INNER) {
                    return null;
                } else if (targetNode && ((node.parentTId == targetNode.tId && moveType == consts.move.TYPE_INNER) || $("#" + node.tId).find("#" + targetNode.tId).length > 0)) {
                    return null;
                } else if (!targetNode) {
                    targetNode = null;
                }
                function moveCallback() {
                    view.moveNode(setting, targetNode, node, moveType, false, isSilent);
                }
                if (tools.canAsync(setting, targetNode) && moveType === consts.move.TYPE_INNER) {
                    view.asyncNode(setting, targetNode, isSilent, moveCallback);
                } else {
                    moveCallback();
                }
                return node;
            }
            zTreeTools.setEditable = function(editable) {
                setting.edit.enable = editable;
                return this.refresh();
            }
        },
    //method of operate data
        _data = {
            setSonNodeLevel: function(setting, parentNode, node) {
                if (!node) return;
                var childKey = setting.data.key.children;
                node.level = (parentNode)? parentNode.level + 1 : 0;
                if (!node[childKey]) return;
                for (var i = 0, l = node[childKey].length; i < l; i++) {
                    if (node[childKey][i]) data.setSonNodeLevel(setting, node, node[childKey][i]);
                }
            }
        },
    //method of event proxy
        _event = {

        },
    //method of event handler
        _handler = {
            onHoverOverNode: function(event, node) {
                var setting = data.getSetting(event.data.treeId),
                    root = data.getRoot(setting);
                if (root.curHoverNode != node) {
                    _handler.onHoverOutNode(event);
                }
                root.curHoverNode = node;
                view.addHoverDom(setting, node);
            },
            onHoverOutNode: function(event, node) {
                var setting = data.getSetting(event.data.treeId),
                    root = data.getRoot(setting);
                if (root.curHoverNode && !data.isSelectedNode(setting, root.curHoverNode)) {
                    view.removeTreeDom(setting, root.curHoverNode);
                    root.curHoverNode = null;
                }
            },
            onMousedownNode: function(eventMouseDown, _node) {
                var i,l,
                    setting = data.getSetting(eventMouseDown.data.treeId),
                    root = data.getRoot(setting);
                //right click can't drag & drop
                if (eventMouseDown.button == 2 || !setting.edit.enable || (!setting.edit.drag.isCopy && !setting.edit.drag.isMove)) return true;

                //input of edit node name can't drag & drop
                var target = eventMouseDown.target,
                    _nodes = data.getRoot(setting).curSelectedList,
                    nodes = [];
                if (!data.isSelectedNode(setting, _node)) {
                    nodes = [_node];
                } else {
                    for (i=0, l=_nodes.length; i<l; i++) {
                        if (_nodes[i].editNameFlag && tools.eqs(target.tagName, "input") && target.getAttribute("treeNode"+consts.id.INPUT) !== null) {
                            return true;
                        }
                        nodes.push(_nodes[i]);
                        if (nodes[0].parentTId !== _nodes[i].parentTId) {
                            nodes = [_node];
                            break;
                        }
                    }
                }

                view.editNodeBlur = true;
                view.cancelCurEditNode(setting, null, true);


                var doc = $(document), curNode, tmpArrow, tmpTarget,
                    isOtherTree = false,
                    targetSetting = setting,
                    preNode, nextNode,
                    preTmpTargetNodeId = null,
                    preTmpMoveType = null,
                    tmpTargetNodeId = null,
                    moveType = consts.move.TYPE_INNER,
                    mouseDownX = eventMouseDown.clientX,
                    mouseDownY = eventMouseDown.clientY,
                    startTime = (new Date()).getTime();

                if (tools.uCanDo(setting)) {
                    doc.bind("mousemove", _docMouseMove);
                }
                function _docMouseMove(event) {
                    //avoid start drag after click node
                    if (root.dragFlag == 0 && Math.abs(mouseDownX - event.clientX) < setting.edit.drag.minMoveSize
                        && Math.abs(mouseDownY - event.clientY) < setting.edit.drag.minMoveSize) {
                        return true;
                    }
                    var i, l, tmpNode, tmpDom, tmpNodes,
                        childKey = setting.data.key.children;
                    $("body").css("cursor", "pointer");

                    if (root.dragFlag == 0) {
                        if (tools.apply(setting.callback.beforeDrag, [setting.treeId, nodes], true) == false) {
                            _docMouseUp(event);
                            return true;
                        }

                        for (i=0, l=nodes.length; i<l; i++) {
                            if (i==0) {
                                root.dragNodeShowBefore = [];
                            }
                            tmpNode = nodes[i];
                            if (tmpNode.isParent && tmpNode.open) {
                                view.expandCollapseNode(setting, tmpNode, !tmpNode.open);
                                root.dragNodeShowBefore[tmpNode.tId] = true;
                            } else {
                                root.dragNodeShowBefore[tmpNode.tId] = false;
                            }
                        }

                        root.dragFlag = 1;
                        root.showHoverDom = false;
                        tools.showIfameMask(setting, true);

                        //sort
                        var isOrder = true, lastIndex = -1;
                        if (nodes.length>1) {
                            var pNodes = nodes[0].parentTId ? nodes[0].getParentNode()[childKey] : data.getNodes(setting);
                            tmpNodes = [];
                            for (i=0, l=pNodes.length; i<l; i++) {
                                if (root.dragNodeShowBefore[pNodes[i].tId] !== undefined) {
                                    if (isOrder && lastIndex > -1 && (lastIndex+1) !== i) {
                                        isOrder = false;
                                    }
                                    tmpNodes.push(pNodes[i]);
                                    lastIndex = i;
                                }
                                if (nodes.length === tmpNodes.length) {
                                    nodes = tmpNodes;
                                    break;
                                }
                            }
                        }
                        if (isOrder) {
                            preNode = nodes[0].getPreNode();
                            nextNode = nodes[nodes.length-1].getNextNode();
                        }

                        //set node in selected
                        curNode = $("<ul class='zTreeDragUL'></ul>");
                        for (i=0, l=nodes.length; i<l; i++) {
                            tmpNode = nodes[i];
                            tmpNode.editNameFlag = false;
                            view.selectNode(setting, tmpNode, i>0);
                            view.removeTreeDom(setting, tmpNode);

                            tmpDom = $("<li id='"+ tmpNode.tId +"_tmp'></li>");
                            tmpDom.append($("#" + tmpNode.tId + consts.id.A).clone());
                            tmpDom.css("padding", "0");
                            tmpDom.children("#" + tmpNode.tId + consts.id.A).removeClass(consts.node.CURSELECTED);
                            curNode.append(tmpDom);
                            if (i == setting.edit.drag.maxShowNodeNum-1) {
                                tmpDom = $("<li id='"+ tmpNode.tId +"_moretmp'><a>  ...  </a></li>");
                                curNode.append(tmpDom);
                                break;
                            }
                        }
                        curNode.attr("id", nodes[0].tId + consts.id.UL + "_tmp");
                        curNode.addClass(setting.treeObj.attr("class"));
                        curNode.appendTo("body");

                        tmpArrow = $("<span class='tmpzTreeMove_arrow'></span>");
                        tmpArrow.attr("id", "zTreeMove_arrow_tmp");
                        tmpArrow.appendTo("body");

                        setting.treeObj.trigger(consts.event.DRAG, [event, setting.treeId, nodes]);
                    }

                    if (root.dragFlag == 1) {
                        if (tmpTarget && tmpArrow.attr("id") == event.target.id && tmpTargetNodeId && (event.clientX + doc.scrollLeft()+2) > ($("#" + tmpTargetNodeId + consts.id.A, tmpTarget).offset().left)) {
                            var xT = $("#" + tmpTargetNodeId + consts.id.A, tmpTarget);
                            event.target = (xT.length > 0) ? xT.get(0) : event.target;
                        } else if (tmpTarget) {
                            tmpTarget.removeClass(consts.node.TMPTARGET_TREE);
                            if (tmpTargetNodeId) $("#" + tmpTargetNodeId + consts.id.A, tmpTarget).removeClass(consts.node.TMPTARGET_NODE + "_" + consts.move.TYPE_PREV)
                                .removeClass(consts.node.TMPTARGET_NODE + "_" + _consts.move.TYPE_NEXT).removeClass(consts.node.TMPTARGET_NODE + "_" + _consts.move.TYPE_INNER);
                        }
                        tmpTarget = null;
                        tmpTargetNodeId = null;

                        //judge drag & drop in multi ztree
                        isOtherTree = false;
                        targetSetting = setting;
                        var settings = data.getSettings();
                        for (var s in settings) {
                            if (settings[s].treeId && settings[s].edit.enable && settings[s].treeId != setting.treeId
                                && (event.target.id == settings[s].treeId || $(event.target).parents("#" + settings[s].treeId).length>0)) {
                                isOtherTree = true;
                                targetSetting = settings[s];
                            }
                        }

                        var docScrollTop = doc.scrollTop(),
                            docScrollLeft = doc.scrollLeft(),
                            treeOffset = targetSetting.treeObj.offset(),
                            scrollHeight = targetSetting.treeObj.get(0).scrollHeight,
                            scrollWidth = targetSetting.treeObj.get(0).scrollWidth,
                            dTop = (event.clientY + docScrollTop - treeOffset.top),
                            dBottom = (targetSetting.treeObj.height() + treeOffset.top - event.clientY - docScrollTop),
                            dLeft = (event.clientX + docScrollLeft - treeOffset.left),
                            dRight = (targetSetting.treeObj.width() + treeOffset.left - event.clientX - docScrollLeft),
                            isTop = (dTop < setting.edit.drag.borderMax && dTop > setting.edit.drag.borderMin),
                            isBottom = (dBottom < setting.edit.drag.borderMax && dBottom > setting.edit.drag.borderMin),
                            isLeft = (dLeft < setting.edit.drag.borderMax && dLeft > setting.edit.drag.borderMin),
                            isRight = (dRight < setting.edit.drag.borderMax && dRight > setting.edit.drag.borderMin),
                            isTreeInner = dTop > setting.edit.drag.borderMin && dBottom > setting.edit.drag.borderMin && dLeft > setting.edit.drag.borderMin && dRight > setting.edit.drag.borderMin,
                            isTreeTop = (isTop && targetSetting.treeObj.scrollTop() <= 0),
                            isTreeBottom = (isBottom && (targetSetting.treeObj.scrollTop() + targetSetting.treeObj.height()+10) >= scrollHeight),
                            isTreeLeft = (isLeft && targetSetting.treeObj.scrollLeft() <= 0),
                            isTreeRight = (isRight && (targetSetting.treeObj.scrollLeft() + targetSetting.treeObj.width()+10) >= scrollWidth);

                        if (event.target.id && targetSetting.treeObj.find("#" + event.target.id).length > 0) {
                            //get node <li> dom
                            var targetObj = event.target;
                            while (targetObj && targetObj.tagName && !tools.eqs(targetObj.tagName, "li") && targetObj.id != targetSetting.treeId) {
                                targetObj = targetObj.parentNode;
                            }

                            var canMove = true;
                            //don't move to self or children of self
                            for (i=0, l=nodes.length; i<l; i++) {
                                tmpNode = nodes[i];
                                if (targetObj.id === tmpNode.tId) {
                                    canMove = false;
                                    break;
                                } else if ($("#" + tmpNode.tId).find("#" + targetObj.id).length > 0) {
                                    canMove = false;
                                    break;
                                }
                            }
                            if (canMove) {
                                if (event.target.id &&
                                    (event.target.id == (targetObj.id + consts.id.A) || $(event.target).parents("#" + targetObj.id + consts.id.A).length > 0)) {
                                    tmpTarget = $(targetObj);
                                    tmpTargetNodeId = targetObj.id;
                                }
                            }
                        }

                        //the mouse must be in zTree
                        tmpNode = nodes[0];
                        if (isTreeInner && (event.target.id == targetSetting.treeId || $(event.target).parents("#" + targetSetting.treeId).length>0)) {
                            //judge mouse move in root of ztree
                            if (!tmpTarget && (event.target.id == targetSetting.treeId || isTreeTop || isTreeBottom || isTreeLeft || isTreeRight) && (isOtherTree || (!isOtherTree && tmpNode.parentTId))) {
                                tmpTarget = targetSetting.treeObj;
                            }
                            //auto scroll top
                            if (isTop) {
                                targetSetting.treeObj.scrollTop(targetSetting.treeObj.scrollTop()-10);
                            } else if (isBottom)  {
                                targetSetting.treeObj.scrollTop(targetSetting.treeObj.scrollTop()+10);
                            }
                            if (isLeft) {
                                targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()-10);
                            } else if (isRight) {
                                targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()+10);
                            }
                            //auto scroll left
                            if (tmpTarget && tmpTarget != targetSetting.treeObj && tmpTarget.offset().left < targetSetting.treeObj.offset().left) {
                                targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()+ tmpTarget.offset().left - targetSetting.treeObj.offset().left);
                            }
                        }

                        curNode.css({
                            "top": (event.clientY + docScrollTop + 3) + "px",
                            "left": (event.clientX + docScrollLeft + 3) + "px"
                        });

                        var dX = 0;
                        var dY = 0;
                        if (tmpTarget && tmpTarget.attr("id")!=targetSetting.treeId) {
                            var tmpTargetNode = tmpTargetNodeId == null ? null: data.getNodeCache(targetSetting, tmpTargetNodeId),
                                isCopy = (event.ctrlKey && setting.edit.drag.isMove && setting.edit.drag.isCopy) || (!setting.edit.drag.isMove && setting.edit.drag.isCopy),
                                isPrev = !!(preNode && tmpTargetNodeId === preNode.tId),
                                isNext = !!(nextNode && tmpTargetNodeId === nextNode.tId),
                                isInner = (tmpNode.parentTId && tmpNode.parentTId == tmpTargetNodeId),
                                canPrev = (isCopy || !isNext) && tools.apply(targetSetting.edit.drag.prev, [targetSetting.treeId, nodes, tmpTargetNode], !!targetSetting.edit.drag.prev),
                                canNext = (isCopy || !isPrev) && tools.apply(targetSetting.edit.drag.next, [targetSetting.treeId, nodes, tmpTargetNode], !!targetSetting.edit.drag.next),
                                canInner = (isCopy || !isInner) && !(targetSetting.data.keep.leaf && !tmpTargetNode.isParent) && tools.apply(targetSetting.edit.drag.inner, [targetSetting.treeId, nodes, tmpTargetNode], !!targetSetting.edit.drag.inner);
                            if (!canPrev && !canNext && !canInner) {
                                tmpTarget = null;
                                tmpTargetNodeId = "";
                                moveType = consts.move.TYPE_INNER;
                                tmpArrow.css({
                                    "display":"none"
                                });
                                if (window.zTreeMoveTimer) {
                                    clearTimeout(window.zTreeMoveTimer);
                                    window.zTreeMoveTargetNodeTId = null
                                }
                            } else {
                                var tmpTargetA = $("#" + tmpTargetNodeId + consts.id.A, tmpTarget),
                                    tmpNextA = tmpTargetNode.isLastNode ? null : $("#" + tmpTargetNode.getNextNode().tId + consts.id.A, tmpTarget.next()),
                                    tmpTop = tmpTargetA.offset().top,
                                    tmpLeft = tmpTargetA.offset().left,
                                    prevPercent = canPrev ? (canInner ? 0.25 : (canNext ? 0.5 : 1) ) : -1,
                                    nextPercent = canNext ? (canInner ? 0.75 : (canPrev ? 0.5 : 0) ) : -1,
                                    dY_percent = (event.clientY + docScrollTop - tmpTop)/tmpTargetA.height();
                                if ((prevPercent==1 ||dY_percent<=prevPercent && dY_percent>=-.2) && canPrev) {
                                    dX = 1 - tmpArrow.width();
                                    dY = tmpTop - tmpArrow.height()/2;
                                    moveType = consts.move.TYPE_PREV;
                                } else if ((nextPercent==0 || dY_percent>=nextPercent && dY_percent<=1.2) && canNext) {
                                    dX = 1 - tmpArrow.width();
                                    dY = (tmpNextA == null || (tmpTargetNode.isParent && tmpTargetNode.open)) ? (tmpTop + tmpTargetA.height() - tmpArrow.height()/2) : (tmpNextA.offset().top - tmpArrow.height()/2);
                                    moveType = consts.move.TYPE_NEXT;
                                }else {
                                    dX = 5 - tmpArrow.width();
                                    dY = tmpTop;
                                    moveType = consts.move.TYPE_INNER;
                                }
                                tmpArrow.css({
                                    "display":"block",
                                    "top": dY + "px",
                                    "left": (tmpLeft + dX) + "px"
                                });
                                tmpTargetA.addClass(consts.node.TMPTARGET_NODE + "_" + moveType);

                                if (preTmpTargetNodeId != tmpTargetNodeId || preTmpMoveType != moveType) {
                                    startTime = (new Date()).getTime();
                                }
                                if (tmpTargetNode && tmpTargetNode.isParent && moveType == consts.move.TYPE_INNER) {
                                    var startTimer = true;
                                    if (window.zTreeMoveTimer && window.zTreeMoveTargetNodeTId !== tmpTargetNode.tId) {
                                        clearTimeout(window.zTreeMoveTimer);
                                        window.zTreeMoveTargetNodeTId = null;
                                    } else if (window.zTreeMoveTimer && window.zTreeMoveTargetNodeTId === tmpTargetNode.tId) {
                                        startTimer = false;
                                    }
                                    if (startTimer) {
                                        window.zTreeMoveTimer = setTimeout(function() {
                                            if (moveType != consts.move.TYPE_INNER) return;
                                            if (tmpTargetNode && tmpTargetNode.isParent && !tmpTargetNode.open && (new Date()).getTime() - startTime > targetSetting.edit.drag.autoOpenTime
                                                && tools.apply(targetSetting.callback.beforeDragOpen, [targetSetting.treeId, tmpTargetNode], true)) {
                                                view.switchNode(targetSetting, tmpTargetNode);
                                                if (targetSetting.edit.drag.autoExpandTrigger) {
                                                    targetSetting.treeObj.trigger(consts.event.EXPAND, [targetSetting.treeId, tmpTargetNode]);
                                                }
                                            }
                                        }, targetSetting.edit.drag.autoOpenTime+50);
                                        window.zTreeMoveTargetNodeTId = tmpTargetNode.tId;
                                    }
                                }
                            }
                        } else {
                            moveType = consts.move.TYPE_INNER;
                            if (tmpTarget && tools.apply(targetSetting.edit.drag.inner, [targetSetting.treeId, nodes, null], !!targetSetting.edit.drag.inner)) {
                                tmpTarget.addClass(consts.node.TMPTARGET_TREE);
                            } else {
                                tmpTarget = null;
                            }
                            tmpArrow.css({
                                "display":"none"
                            });
                            if (window.zTreeMoveTimer) {
                                clearTimeout(window.zTreeMoveTimer);
                                window.zTreeMoveTargetNodeTId = null;
                            }
                        }
                        preTmpTargetNodeId = tmpTargetNodeId;
                        preTmpMoveType = moveType;
                    }
                    return false;
                }

                doc.bind("mouseup", _docMouseUp);
                function _docMouseUp(event) {
                    if (window.zTreeMoveTimer) {
                        clearTimeout(window.zTreeMoveTimer);
                        window.zTreeMoveTargetNodeTId = null;
                    }
                    preTmpTargetNodeId = null;
                    preTmpMoveType = null;
                    doc.unbind("mousemove", _docMouseMove);
                    doc.unbind("mouseup", _docMouseUp);
                    doc.unbind("selectstart", _docSelect);
                    $("body").css("cursor", "auto");
                    if (tmpTarget) {
                        tmpTarget.removeClass(consts.node.TMPTARGET_TREE);
                        if (tmpTargetNodeId) $("#" + tmpTargetNodeId + consts.id.A, tmpTarget).removeClass(consts.node.TMPTARGET_NODE + "_" + consts.move.TYPE_PREV)
                            .removeClass(consts.node.TMPTARGET_NODE + "_" + _consts.move.TYPE_NEXT).removeClass(consts.node.TMPTARGET_NODE + "_" + _consts.move.TYPE_INNER);
                    }
                    tools.showIfameMask(setting, false);

                    root.showHoverDom = true;
                    if (root.dragFlag == 0) return;
                    root.dragFlag = 0;

                    var i, l, tmpNode;
                    for (i=0, l=nodes.length; i<l; i++) {
                        tmpNode = nodes[i];
                        if (tmpNode.isParent && root.dragNodeShowBefore[tmpNode.tId] && !tmpNode.open) {
                            view.expandCollapseNode(setting, tmpNode, !tmpNode.open);
                            delete root.dragNodeShowBefore[tmpNode.tId];
                        }
                    }

                    if (curNode) curNode.remove();
                    if (tmpArrow) tmpArrow.remove();

                    var isCopy = (event.ctrlKey && setting.edit.drag.isMove && setting.edit.drag.isCopy) || (!setting.edit.drag.isMove && setting.edit.drag.isCopy);
                    if (!isCopy && tmpTarget && tmpTargetNodeId && nodes[0].parentTId && tmpTargetNodeId==nodes[0].parentTId && moveType == consts.move.TYPE_INNER) {
                        tmpTarget = null;
                    }
                    if (tmpTarget) {
                        var dragTargetNode = tmpTargetNodeId == null ? null: data.getNodeCache(targetSetting, tmpTargetNodeId);
                        if (tools.apply(setting.callback.beforeDrop, [targetSetting.treeId, nodes, dragTargetNode, moveType, isCopy], true) == false) return;
                        var newNodes = isCopy ? tools.clone(nodes) : nodes;

                        function dropCallback() {
                            if (isOtherTree) {
                                if (!isCopy) {
                                    for(var i=0, l=nodes.length; i<l; i++) {
                                        view.removeNode(setting, nodes[i]);
                                    }
                                }
                                if (moveType == consts.move.TYPE_INNER) {
                                    view.addNodes(targetSetting, dragTargetNode, newNodes);
                                } else {
                                    view.addNodes(targetSetting, dragTargetNode.getParentNode(), newNodes);
                                    if (moveType == consts.move.TYPE_PREV) {
                                        for (i=0, l=newNodes.length; i<l; i++) {
                                            view.moveNode(targetSetting, dragTargetNode, newNodes[i], moveType, false);
                                        }
                                    } else {
                                        for (i=-1, l=newNodes.length-1; i<l; l--) {
                                            view.moveNode(targetSetting, dragTargetNode, newNodes[l], moveType, false);
                                        }
                                    }
                                }
                            } else {
                                if (isCopy && moveType == consts.move.TYPE_INNER) {
                                    view.addNodes(targetSetting, dragTargetNode, newNodes);
                                } else {
                                    if (isCopy) {
                                        view.addNodes(targetSetting, dragTargetNode.getParentNode(), newNodes);
                                    }
                                    if (moveType != consts.move.TYPE_NEXT) {
                                        for (i=0, l=newNodes.length; i<l; i++) {
                                            view.moveNode(targetSetting, dragTargetNode, newNodes[i], moveType, false);
                                        }
                                    } else {
                                        for (i=-1, l=newNodes.length-1; i<l; l--) {
                                            view.moveNode(targetSetting, dragTargetNode, newNodes[l], moveType, false);
                                        }
                                    }
                                }
                            }
                            for (i=0, l=newNodes.length; i<l; i++) {
                                view.selectNode(targetSetting, newNodes[i], i>0);
                            }
                            $("#" + newNodes[0].tId).focus().blur();

                            setting.treeObj.trigger(consts.event.DROP, [event, targetSetting.treeId, newNodes, dragTargetNode, moveType, isCopy]);
                        }

                        if (moveType == consts.move.TYPE_INNER && tools.canAsync(targetSetting, dragTargetNode)) {
                            view.asyncNode(targetSetting, dragTargetNode, false, dropCallback);
                        } else {
                            dropCallback();
                        }

                    } else {
                        for (i=0, l=nodes.length; i<l; i++) {
                            view.selectNode(targetSetting, nodes[i], i>0);
                        }
                        setting.treeObj.trigger(consts.event.DROP, [event, setting.treeId, nodes, null, null, null]);
                    }
                }

                doc.bind("selectstart", _docSelect);
                function _docSelect() {
                    return false;
                }

                //Avoid FireFox's Bug
                //If zTree Div CSS set 'overflow', so drag node outside of zTree, and event.target is error.
                if(eventMouseDown.preventDefault) {
                    eventMouseDown.preventDefault();
                }
                return true;
            }
        },
    //method of tools for zTree
        _tools = {
            getAbs: function (obj) {
                var oRect = obj.getBoundingClientRect();
                return [oRect.left,oRect.top]
            },
            inputFocus: function(inputObj) {
                if (inputObj.get(0)) {
                    inputObj.focus();
                    tools.setCursorPosition(inputObj.get(0), inputObj.val().length);
                }
            },
            inputSelect: function(inputObj) {
                if (inputObj.get(0)) {
                    inputObj.focus();
                    inputObj.select();
                }
            },
            setCursorPosition: function(obj, pos){
                if(obj.setSelectionRange) {
                    obj.focus();
                    obj.setSelectionRange(pos,pos);
                } else if (obj.createTextRange) {
                    var range = obj.createTextRange();
                    range.collapse(true);
                    range.moveEnd('character', pos);
                    range.moveStart('character', pos);
                    range.select();
                }
            },
            showIfameMask: function(setting, showSign) {
                var root = data.getRoot(setting);
                //clear full mask
                while (root.dragMaskList.length > 0) {
                    root.dragMaskList[0].remove();
                    root.dragMaskList.shift();
                }
                if (showSign) {
                    //show mask
                    var iframeList = $("iframe");
                    for (var i = 0, l = iframeList.length; i < l; i++) {
                        var obj = iframeList.get(i),
                            r = tools.getAbs(obj),
                            dragMask = $("<div id='zTreeMask_" + i + "' class='zTreeMask' style='top:" + r[1] + "px; left:" + r[0] + "px; width:" + obj.offsetWidth + "px; height:" + obj.offsetHeight + "px;'></div>");
                        dragMask.appendTo("body");
                        root.dragMaskList.push(dragMask);
                    }
                }
            }
        },
    //method of operate ztree dom
        _view = {
            addEditBtn: function(setting, node) {
                if (node.editNameFlag || $("#" + node.tId + consts.id.EDIT).length > 0) {
                    return;
                }
                if (!tools.apply(setting.edit.showRenameBtn, [setting.treeId, node], setting.edit.showRenameBtn)) {
                    return;
                }
                var aObj = $("#" + node.tId + consts.id.A),
                    editStr = "<span class='" + consts.className.BUTTON + " edit' id='" + node.tId + consts.id.EDIT + "' title='"+tools.apply(setting.edit.renameTitle, [setting.treeId, node], setting.edit.renameTitle)+"' treeNode"+consts.id.EDIT+" style='display:none;'></span>";
                aObj.append(editStr);

                $("#" + node.tId + consts.id.EDIT).bind('click',
                    function() {
                        if (!tools.uCanDo(setting) || tools.apply(setting.callback.beforeEditName, [setting.treeId, node], true) == false) return false;
                        view.editNode(setting, node);
                        return false;
                    }
                ).show();
            },
            addRemoveBtn: function(setting, node) {
                if (node.editNameFlag || $("#" + node.tId + consts.id.REMOVE).length > 0) {
                    return;
                }
                if (!tools.apply(setting.edit.showRemoveBtn, [setting.treeId, node], setting.edit.showRemoveBtn)) {
                    return;
                }
                var aObj = $("#" + node.tId + consts.id.A),
                    removeStr = "<span class='" + consts.className.BUTTON + " remove' id='" + node.tId + consts.id.REMOVE + "' title='"+tools.apply(setting.edit.removeTitle, [setting.treeId, node], setting.edit.removeTitle)+"' treeNode"+consts.id.REMOVE+" style='display:none;'></span>";
                aObj.append(removeStr);

                $("#" + node.tId + consts.id.REMOVE).bind('click',
                    function() {
                        if (!tools.uCanDo(setting) || tools.apply(setting.callback.beforeRemove, [setting.treeId, node], true) == false) return false;
                        view.removeNode(setting, node);
                        setting.treeObj.trigger(consts.event.REMOVE, [setting.treeId, node]);
                        return false;
                    }
                ).bind('mousedown',
                    function(eventMouseDown) {
                        return true;
                    }
                ).show();
            },
            addHoverDom: function(setting, node) {
                if (data.getRoot(setting).showHoverDom) {
                    node.isHover = true;
                    if (setting.edit.enable) {
                        view.addEditBtn(setting, node);
                        view.addRemoveBtn(setting, node);
                    }
                    tools.apply(setting.view.addHoverDom, [setting.treeId, node]);
                }
            },
            cancelCurEditNode: function (setting, forceName) {
                var root = data.getRoot(setting),
                    nameKey = setting.data.key.name,
                    node = root.curEditNode;

                if (node) {
                    var inputObj = root.curEditInput;
                    var newName = forceName ? forceName:inputObj.val();
                    if (!forceName && tools.apply(setting.callback.beforeRename, [setting.treeId, node, newName], true) === false) {
                        return false;
                    } else {
                        node[nameKey] = newName ? newName:inputObj.val();
                        if (!forceName) {
                            setting.treeObj.trigger(consts.event.RENAME, [setting.treeId, node]);
                        }
                    }
                    var aObj = $("#" + node.tId + consts.id.A);
                    aObj.removeClass(consts.node.CURSELECTED_EDIT);
                    inputObj.unbind();
                    view.setNodeName(setting, node);
                    node.editNameFlag = false;
                    root.curEditNode = null;
                    root.curEditInput = null;
                    view.selectNode(setting, node, false);
                }
                root.noSelection = true;
                return true;
            },
            editNode: function(setting, node) {
                var root = data.getRoot(setting);
                view.editNodeBlur = false;
                if (data.isSelectedNode(setting, node) && root.curEditNode == node && node.editNameFlag) {
                    setTimeout(function() {tools.inputFocus(root.curEditInput);}, 0);
                    return;
                }
                var nameKey = setting.data.key.name;
                node.editNameFlag = true;
                view.removeTreeDom(setting, node);
                view.cancelCurEditNode(setting);
                view.selectNode(setting, node, false);
                $("#" + node.tId + consts.id.SPAN).html("<input type=text class='rename' id='" + node.tId + consts.id.INPUT + "' treeNode" + consts.id.INPUT + " >");
                var inputObj = $("#" + node.tId + consts.id.INPUT);
                inputObj.attr("value", node[nameKey]);
                if (setting.edit.editNameSelectAll) {
                    tools.inputSelect(inputObj);
                } else {
                    tools.inputFocus(inputObj);
                }

                inputObj.bind('blur', function(event) {
                    if (!view.editNodeBlur) {
                        view.cancelCurEditNode(setting);
                    }
                }).bind('keydown', function(event) {
                        if (event.keyCode=="13") {
                            view.editNodeBlur = true;
                            view.cancelCurEditNode(setting, null, true);
                        } else if (event.keyCode=="27") {
                            view.cancelCurEditNode(setting, node[nameKey]);
                        }
                    }).bind('click', function(event) {
                        return false;
                    }).bind('dblclick', function(event) {
                        return false;
                    });

                $("#" + node.tId + consts.id.A).addClass(consts.node.CURSELECTED_EDIT);
                root.curEditInput = inputObj;
                root.noSelection = false;
                root.curEditNode = node;
            },
            moveNode: function(setting, targetNode, node, moveType, animateFlag, isSilent) {
                var root = data.getRoot(setting),
                    childKey = setting.data.key.children;
                if (targetNode == node) return;
                if (setting.data.keep.leaf && targetNode && !targetNode.isParent && moveType == consts.move.TYPE_INNER) return;
                var oldParentNode = (node.parentTId ? node.getParentNode(): root),
                    targetNodeIsRoot = (targetNode === null || targetNode == root);
                if (targetNodeIsRoot && targetNode === null) targetNode = root;
                if (targetNodeIsRoot) moveType = consts.move.TYPE_INNER;
                var targetParentNode = (targetNode.parentTId ? targetNode.getParentNode() : root);

                if (moveType != consts.move.TYPE_PREV && moveType != consts.move.TYPE_NEXT) {
                    moveType = consts.move.TYPE_INNER;
                }

                if (moveType == consts.move.TYPE_INNER) {
                    if (targetNodeIsRoot) {
                        //parentTId of root node is null
                        node.parentTId = null;
                    } else {
                        if (!targetNode.isParent) {
                            targetNode.isParent = true;
                            targetNode.open = !!targetNode.open;
                            view.setNodeLineIcos(setting, targetNode);
                        }
                        node.parentTId = targetNode.tId;
                    }
                }

                //move node Dom
                var targetObj, target_ulObj;
                if (targetNodeIsRoot) {
                    targetObj = setting.treeObj;
                    target_ulObj = targetObj;
                } else {
                    if (!isSilent && moveType == consts.move.TYPE_INNER) {
                        view.expandCollapseNode(setting, targetNode, true, false);
                    } else if (!isSilent) {
                        view.expandCollapseNode(setting, targetNode.getParentNode(), true, false);
                    }
                    targetObj = $("#" + targetNode.tId);
                    target_ulObj = $("#" + targetNode.tId + consts.id.UL);
                    if (!!targetObj.get(0) && !target_ulObj.get(0)) {
                        var ulstr = [];
                        view.makeUlHtml(setting, targetNode, ulstr, '');
                        targetObj.append(ulstr.join(''));
                    }
                    target_ulObj = $("#" + targetNode.tId + consts.id.UL);
                }
                var nodeDom = $("#" + node.tId);
                if (!nodeDom.get(0)) {
                    nodeDom = view.appendNodes(setting, node.level, [node], null, false, true).join('');
                } else if (!targetObj.get(0)) {
                    nodeDom.remove();
                }
                if (target_ulObj.get(0) && moveType == consts.move.TYPE_INNER) {
                    target_ulObj.append(nodeDom);
                } else if (targetObj.get(0) && moveType == consts.move.TYPE_PREV) {
                    targetObj.before(nodeDom);
                } else if (targetObj.get(0) && moveType == consts.move.TYPE_NEXT) {
                    targetObj.after(nodeDom);
                }

                //repair the data after move
                var i,l,
                    tmpSrcIndex = -1,
                    tmpTargetIndex = 0,
                    oldNeighbor = null,
                    newNeighbor = null,
                    oldLevel = node.level;
                if (node.isFirstNode) {
                    tmpSrcIndex = 0;
                    if (oldParentNode[childKey].length > 1 ) {
                        oldNeighbor = oldParentNode[childKey][1];
                        oldNeighbor.isFirstNode = true;
                    }
                } else if (node.isLastNode) {
                    tmpSrcIndex = oldParentNode[childKey].length -1;
                    oldNeighbor = oldParentNode[childKey][tmpSrcIndex - 1];
                    oldNeighbor.isLastNode = true;
                } else {
                    for (i = 0, l = oldParentNode[childKey].length; i < l; i++) {
                        if (oldParentNode[childKey][i].tId == node.tId) {
                            tmpSrcIndex = i;
                            break;
                        }
                    }
                }
                if (tmpSrcIndex >= 0) {
                    oldParentNode[childKey].splice(tmpSrcIndex, 1);
                }
                if (moveType != consts.move.TYPE_INNER) {
                    for (i = 0, l = targetParentNode[childKey].length; i < l; i++) {
                        if (targetParentNode[childKey][i].tId == targetNode.tId) tmpTargetIndex = i;
                    }
                }
                if (moveType == consts.move.TYPE_INNER) {
                    if (!targetNode[childKey]) targetNode[childKey] = new Array();
                    if (targetNode[childKey].length > 0) {
                        newNeighbor = targetNode[childKey][targetNode[childKey].length - 1];
                        newNeighbor.isLastNode = false;
                    }
                    targetNode[childKey].splice(targetNode[childKey].length, 0, node);
                    node.isLastNode = true;
                    node.isFirstNode = (targetNode[childKey].length == 1);
                } else if (targetNode.isFirstNode && moveType == consts.move.TYPE_PREV) {
                    targetParentNode[childKey].splice(tmpTargetIndex, 0, node);
                    newNeighbor = targetNode;
                    newNeighbor.isFirstNode = false;
                    node.parentTId = targetNode.parentTId;
                    node.isFirstNode = true;
                    node.isLastNode = false;

                } else if (targetNode.isLastNode && moveType == consts.move.TYPE_NEXT) {
                    targetParentNode[childKey].splice(tmpTargetIndex + 1, 0, node);
                    newNeighbor = targetNode;
                    newNeighbor.isLastNode = false;
                    node.parentTId = targetNode.parentTId;
                    node.isFirstNode = false;
                    node.isLastNode = true;

                } else {
                    if (moveType == consts.move.TYPE_PREV) {
                        targetParentNode[childKey].splice(tmpTargetIndex, 0, node);
                    } else {
                        targetParentNode[childKey].splice(tmpTargetIndex + 1, 0, node);
                    }
                    node.parentTId = targetNode.parentTId;
                    node.isFirstNode = false;
                    node.isLastNode = false;
                }
                data.fixPIdKeyValue(setting, node);
                data.setSonNodeLevel(setting, node.getParentNode(), node);

                //repair node what been moved
                view.setNodeLineIcos(setting, node);
                view.repairNodeLevelClass(setting, node, oldLevel)

                //repair node's old parentNode dom
                if (!setting.data.keep.parent && oldParentNode[childKey].length < 1) {
                    //old parentNode has no child nodes
                    oldParentNode.isParent = false;
                    oldParentNode.open = false;
                    var tmp_ulObj = $("#" + oldParentNode.tId + consts.id.UL),
                        tmp_switchObj = $("#" + oldParentNode.tId + consts.id.SWITCH),
                        tmp_icoObj = $("#" + oldParentNode.tId + consts.id.ICON);
                    view.replaceSwitchClass(oldParentNode, tmp_switchObj, consts.folder.DOCU);
                    view.replaceIcoClass(oldParentNode, tmp_icoObj, consts.folder.DOCU);
                    tmp_ulObj.css("display", "none");

                } else if (oldNeighbor) {
                    //old neigbor node
                    view.setNodeLineIcos(setting, oldNeighbor);
                }

                //new neigbor node
                if (newNeighbor) {
                    view.setNodeLineIcos(setting, newNeighbor);
                }

                //repair checkbox / radio
                if (!!setting.check && setting.check.enable && view.repairChkClass) {
                    view.repairChkClass(setting, oldParentNode);
                    view.repairParentChkClassWithSelf(setting, oldParentNode);
                    if (oldParentNode != node.parent)
                        view.repairParentChkClassWithSelf(setting, node);
                }

                //expand parents after move
                if (!isSilent) {
                    view.expandCollapseParentNode(setting, node.getParentNode(), true, animateFlag);
                }
            },
            removeEditBtn: function(node) {
                $("#" + node.tId + consts.id.EDIT).unbind().remove();
            },
            removeRemoveBtn: function(node) {
                $("#" + node.tId + consts.id.REMOVE).unbind().remove();
            },
            removeTreeDom: function(setting, node) {
                node.isHover = false;
                view.removeEditBtn(node);
                view.removeRemoveBtn(node);
                tools.apply(setting.view.removeHoverDom, [setting.treeId, node]);
            },
            repairNodeLevelClass: function(setting, node, oldLevel) {
                if (oldLevel === node.level) return;
                var liObj = $("#" + node.tId),
                    aObj = $("#" + node.tId + consts.id.A),
                    ulObj = $("#" + node.tId + consts.id.UL),
                    oldClass = consts.className.LEVEL + oldLevel,
                    newClass = consts.className.LEVEL + node.level;
                liObj.removeClass(oldClass);
                liObj.addClass(newClass);
                aObj.removeClass(oldClass);
                aObj.addClass(newClass);
                ulObj.removeClass(oldClass);
                ulObj.addClass(newClass);
            }
        },

        _z = {
            tools: _tools,
            view: _view,
            event: _event,
            data: _data
        };
    $.extend(true, $.fn.zTree.consts, _consts);
    $.extend(true, $.fn.zTree._z, _z);

    var zt = $.fn.zTree,
        tools = zt._z.tools,
        consts = zt.consts,
        view = zt._z.view,
        data = zt._z.data,
        event = zt._z.event;

    data.exSetting(_setting);
    data.addInitBind(_bindEvent);
    data.addInitUnBind(_unbindEvent);
    data.addInitCache(_initCache);
    data.addInitNode(_initNode);
    data.addInitProxy(_eventProxy);
    data.addInitRoot(_initRoot);
    data.addZTreeTools(_zTreeTools);

    var _cancelPreSelectedNode = view.cancelPreSelectedNode;
    view.cancelPreSelectedNode = function (setting, node) {
        var list = data.getRoot(setting).curSelectedList;
        for (var i=0, j=list.length; i<j; i++) {
            if (!node || node === list[i]) {
                view.removeTreeDom(setting, list[i]);
                if (node) break;
            }
        }
        if (_cancelPreSelectedNode) _cancelPreSelectedNode.apply(view, arguments);
    }

    var _createNodes = view.createNodes;
    view.createNodes = function(setting, level, nodes, parentNode) {
        if (_createNodes) {
            _createNodes.apply(view, arguments);
        }
        if (!nodes) return;
        if (view.repairParentChkClassWithSelf) {
            view.repairParentChkClassWithSelf(setting, parentNode);
        }
    }

    var _makeNodeUrl = view.makeNodeUrl;
    view.makeNodeUrl = function(setting, node) {
        return setting.edit.enable ? null : (_makeNodeUrl.apply(view, arguments));
    }

    var _removeNode = view.removeNode;
    view.removeNode = function(setting, node) {
        var root = data.getRoot(setting);
        if (root.curEditNode === node) root.curEditNode = null;
        if (_removeNode) {
            _removeNode.apply(view, arguments);
        }
    }

    var _selectNode = view.selectNode;
    view.selectNode = function(setting, node, addFlag) {
        var root = data.getRoot(setting);
        if (data.isSelectedNode(setting, node) && root.curEditNode == node && node.editNameFlag) {
            return false;
        }
        if (_selectNode) _selectNode.apply(view, arguments);
        view.addHoverDom(setting, node);
        return true;
    }

    var _uCanDo = tools.uCanDo;
    tools.uCanDo = function(setting, e) {
        var root = data.getRoot(setting);
        if (e && (tools.eqs(e.type, "mouseover") || tools.eqs(e.type, "mouseout") || tools.eqs(e.type, "mousedown") || tools.eqs(e.type, "mouseup"))) {
            return true;
        }
        return (!root.curEditNode) && (_uCanDo ? _uCanDo.apply(view, arguments) : true);
    }
})(jQuery);


(function($){
    $.fn.UED_tree = function(option){
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
        return this.each(function(i, n){
            var  _box = $(n),
                _rel =  strToJson( _box.attr("data-rel"));
            if(!_box.attr("id")){_box.attr("id","zt"+Math.round(Math.random()*10000))}

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},option,_rel,{
                data: {
                    simpleData: {enable: true}
                },
                callback: {
                    onClick: function(event, treeId, treeNode, clickFlag){
                        window.dealTreeAction && dealTreeAction(treeId,treeNode.id||treeNode.tId,treeNode.isParent,treeNode.name);
                    }
                }
            });
            var nodes = [
                { id:1, pId:0, name:"父节点1 - 展开", open:true},
                { id:11, pId:1, name:"父节点11 - 折叠"},
                { id:111, pId:11, name:"叶子节点111"},
                { id:112, pId:11, name:"叶子节点112"},
                { id:113, pId:11, name:"叶子节点113"},
                { id:114, pId:11, name:"叶子节点114"},
                { id:12, pId:1, name:"父节点12 - 折叠"},
                { id:121, pId:12, name:"叶子节点121"},
                { id:122, pId:12, name:"叶子节点122"},
                { id:123, pId:12, name:"叶子节点123"},
                { id:124, pId:12, name:"叶子节点124"},
                { id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
                { id:2, pId:0, name:"父节点2 - 折叠"},
                { id:21, pId:2, name:"父节点21 - 展开", open:true},
                { id:211, pId:21, name:"叶子节点211"},
                { id:212, pId:21, name:"叶子节点212"},
                { id:213, pId:21, name:"叶子节点213"},
                { id:214, pId:21, name:"叶子节点214"},
                { id:22, pId:2, name:"父节点22 - 折叠"},
                { id:221, pId:22, name:"叶子节点221"},
                { id:222, pId:22, name:"叶子节点222"},
                { id:223, pId:22, name:"叶子节点223"},
                { id:224, pId:22, name:"叶子节点224"},
                { id:23, pId:2, name:"父节点23 - 折叠"},
                { id:231, pId:23, name:"叶子节点231"},
                { id:232, pId:23, name:"叶子节点232"},
                { id:233, pId:23, name:"叶子节点233"},
                { id:234, pId:23, name:"叶子节点234"},
                { id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
            ];
            if(_box.attr("data-url")){
                //data-url支持两种格式，一种是url地址，第二种是function（匿名function必须使用闭包形式），该function必须返回一个url
                var url;
                try{
                    url = eval(_box.attr("data-url"));
                }catch(err){
                    url =_box.attr("data-url");
                };

                $.ajax({ //发送一次ajax请求，验证地址是否有效
                    type: "POST",
                    url: url,
                    complete: function (XMLHttpRequest, textStatus) {
                        if(textStatus=="success"){
                            option = $.extend(option,{
                                async: {
                                    enable: true,
                                    url:url,
                                    autoParam:["id=nodeId", "name","level"], //与hanqc侧对接，提供4个参数nodeId、name、level、treeId
                                    otherParam:{
                                        //即时获取最新id
                                        "treeId":function(){return _box.attr("id")||0;}
                                    }
                                }
                            });
                            $.fn.zTree.init(_box,option);
                        }else{
                            $.fn.zTree.init(_box,option,nodes);
                        }
                    }
                });
            }else if(_box.attr("data-zNodes")){
                var nodes = window[_box.attr("data-zNodes")] || []
                nodes && $.fn.zTree.init(_box,option,nodes);
            }else{
                $.fn.zTree.init(_box,option,nodes);
            }
        });

    };
    $(function(){
        $("*[data-role='ued_tree']").UED_tree();
    })
})(jQuery);


/**
 * @Filename: navigation.js
 * @Description: navigation event
 * @Version: 1.0.0
 * @Author: dujj.si-tech
 * @UpdateBy: wanggq 20121106
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $("*[data-role='ued-nav']").UED_nav();
 *
 */
(function($){
    $.fn.UED_nav = function(option){
        var defaults = {
            //child menu element
            childMenu: "ul",
            //current navigation's class
            activeClass: "on"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        /* 合并默认参数和用户自定义参数 */
        var option = $.extend(true,{},defaults,option);
        //Interlace change color
        return this.each(function(i,n){
            var  _this = $(n),
                _rel = strToJson( _this.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            _this.find(">ul>li").hover(function(){
                $(this).addClass(option.activeClass);
            },function(){
                $(this).removeClass(option.activeClass);
            });
        })
    }

    $(function(){
        $("*[data-role='ued-nav']").UED_nav();
    })
})(jQuery);

/**
 * @Filename: panel.js
 * @Description: 工具面板
 * @Version: 1.0.0
 * @Author: wangyq.si-tech
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <div class="ued-panel">
 * <h3>工具栏面板<span><a class="tool minimize"></a><a class="tool maximize"></a><a class="tool open"></a><a class="tool close"></a></span></h3>
 * <div>
 * <p>panel</p>
 * </div>
 * </div>
 *
 */
(function($){
    $.fn.UED_panel = function(option){
        var defaults = {
            open:true,
            //双击标题折叠展开
            headerCollapse: false,
            //标题和工具按钮
            header: ">h3",
            content: ".ued-content",
            min:".min",
            max:".max",
            restore:".restore",
            collapse:".collapse",
            expand:".expand",
            close:".close"
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        function _init(o){
            var _o = o;
            var option = _o.data("option");
            var header = _o.find(option.header);
            var content = _o.find(option.content);

            if( option.open != true ){
                _o.find(option.content).hide();
            }

            header.delegate(option.max,"click",function(){
                var w = $(window).width()-4,h = $(window).height()-header.height(),top = $(window).scrollTop();
                _o.css({position:"absolute",top:top,left:1,width:w,zIndex:9999});
                content.height(h);
                $(this).removeClass(option.max.substr(1)).addClass(option.restore.substr(1));
            });
            header.delegate(option.restore,"click",function(){
                _o.removeAttr("style");
                content.removeAttr("style");
                $(this).removeClass(option.restore.substr(1)).addClass(option.max.substr(1));
            });
            header.delegate(option.collapse,"click",function(){
                content.slideUp("fast");
                $(this).removeClass(option.collapse.substr(1)).addClass(option.expand.substr(1));
            });
            header.delegate(option.expand,"click",function(){
                content.slideDown("fast");
                $(this).removeClass(option.expand.substr(1)).addClass(option.collapse.substr(1));
            });
            header.delegate(option.close,"click",function(){
                _o.remove();
            });

        }

        return this.each(function(i, n){
            var  _box = $(n),
                _rel =  strToJson( _box.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);

            _box.data("option",option);

            _init(_box);
        })
    }
    $(function(){
        $("*[data-role='ued-panel']").UED_panel();
    })
})(jQuery);

/**
 * @Filename: passwordStrength.js
 * @Description: password Strength test
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121227
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $("input.password").UED_passwStrength();
 *
 *
 */
(function($){
    $.fn.UED_passwStrength = function(option){
        var defaults = {
            targetDiv : '#passwordStrengthDiv'
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
        // core: get your password strength
        function getPasswordStrength(H){
            var D=(H.length);
            if(D>5){
                D=5
            }
            var F=H.replace(/[0-9]/g,"");
            var G=(H.length-F.length);
            if(G>3){G=3}
            var A=H.replace(/\W/g,"");
            var C=(H.length-A.length);
            if(C>3){C=3}
            var B=H.replace(/[A-Z]/g,"");
            var I=(H.length-B.length);
            if(I>3){I=3}
            var E=((D*10)-20)+(G*10)+(C*15)+(I*10);
            if(E<0){E=0}
            if(E>100){E=100}
            return E
        }
        // Prompter show
        function showPrompter(s,t){
            t.text(s);
        }

        return this.each(function(i,n){
            var  _o = $(n),
                _rel =  strToJson( _o.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            _o.data("option", $.extend( true, {}, defaults, option , _rel));

            _o.bind("keyup",function(e){
                var _value = _o.val();
                var strength = getPasswordStrength(_value);
                showPrompter(strength, $(_o.data("option").targetDiv));
            })
        })
    }

    $(function(){
        $("*[data-role='ued-passwStrength']").UED_passwStrength();
    })
})(jQuery);

/**
 * @Filename: placeholder.js
 * @Description: placeholder event
 * @Version: 1.0.0
 * @Author: hanlx.si-tech
 * @UpdateBy:
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <input type="text" class="placeholder" data-rel="" />
 * $("input.placeholder").UED_placeholder();
 *
 */
(function($){
    $.fn.UED_placeholder = function(option){
        var defaults = {
            value:"&#x8BF7;&#x8F93;&#x5165;...",//设置文本框的默认文本
            color:"#999999", //设置默认文字颜色
            fontSize:"12px", //设置默认文字大小
            fontWeight:"normal", //设置默认文字粗细
            focusBgColor:"#FCF405" //文本框获取焦点时背景颜色
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
        function _init(o){
            var _o = o;
            var option = _o.data("option");
            if(option.value){
                var val = option.value;
                _o.val(val).css({"color":option.color,"font-size":option.fontSize,"font-weight":option.fontWeight});
                _o.focus(function(){
                    if(_o.val()==val){
                        _o.val('').css({"color":"","font-size":"","font-weight":""});
                    }
                    _o.css({"background-color":option.focusBgColor});
                }).blur(function(){
                        if(!_o.val()){
                            _o.val(val).css({"background-color":"","color":option.color,"font-size":option.fontSize,"font-weight":option.fontWeight});
                        }else{
                            _o.css({"background-color":"","color":"","font-size":"","font-weight":""});
                        }
                    })
            }
        }

        return this.each(function(i,n){
            var _this = $(n),
                _rel =  strToJson(_this.attr("data-rel"));
            var options = $.extend(true, {}, defaults, option, _rel);
            _this.data("option",options);
            _init(_this);
        })
    }

    $(function(){
        $("*[data-role='ued-placeholder']").UED_placeholder();
    });
})(jQuery);


/*
 * @Filename: progressbar.js
 * @Description: progressBar plugin
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20130118
 * Copyright (c) 2013-2014 si-tech
 * build on http://t.wits.sg
 *
 example:
 $(".someclass").UED_progressBar();
 $("#progressbar").UED_progressBar();
 $("#progressbar").UED_progressBar({val:45});							// percentage
 $("#progressbar").UED_progressBar({showText: false });			// percentage with config
 $("#progressbar").UED_progressBar({val:45, showText: false });		// percentage with config
 */
(function($) {
    $.fn.UED_progressBar = function (option) {
        var uedSkin = function (){
            var uedSkin = $.cookie&&$.cookie("UED_skin")!='undefined'&&$.cookie("UED_skin")!=null?$.cookie("UED_skin"):"skin1";
            return uedSkin;
        }();
        var defaults = {
            val:null,
            steps:20, // steps taken to reach target
            stepDuration:20,
            max:100, // Upon 100% i'd assume, but configurable
            showText:true, // show text with percentage in next to the progressbar? - default : true
            textFormat:'percentage', // Or otherwise, set to 'fraction'
            width:120, // Width of the progressbar - don't forget to adjust your image too!!!												// Image to use in the progressbar. Can be a single image too: 'images/progressbg_green.gif'
            height:12, // Height of the progressbar - don't forget to adjust your image too!!!
            callback:null, // Calls back with the config object that has the current percentage, target percentage, current image, etc
            boxImage:'../css/'+uedSkin+'/images/progressbar/progressbar.gif', // boxImage : image around the progress bar
            barImage:{
                0:'../css/'+uedSkin+'/images/progressbar/progressbg_red.gif',
                30:'../css/'+uedSkin+'/images/progressbar/progressbg_orange.gif',
                70:'../css/'+uedSkin+'/images/progressbar/progressbg_green.gif'
            },
            // Internal use
            running_value:0,
            value:0,
            image:null
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };
        return this.each(function (i,n) {
            var _this = $(n),
                _rel =  strToJson( _this.attr("data-rel") );
            /* 合并默认参数和用户自定义参数 */
            var option = $.extend(true,{},defaults,option,_rel);
            var argvalue = option.val;
            var argconfig = option;
            construct (_this, argvalue,argconfig,option)
        });
        function construct (o,argvalue,argconfig,option) {
            var pb = this;
            var config = this.config;

            if (argvalue != null && this.bar != null && this.config != null) {
                this.config.value = parseInt(argvalue)
                if (argconfig != null)
                    pb.config = $.extend(this.config, argconfig);
                config = pb.config;
            } else {
                var $this = o;
                var config = $.extend({},defaults, argconfig);
                config.id = $this.attr('id') ? $this.attr('id') : Math.ceil(Math.random() * 100000);	// random id, if none provided

                if (argvalue == null)
                    argvalue = $this.html().replace("%", "")	// parse percentage

                config.value = parseInt(argvalue);
                config.running_value = 0;
                config.image = getBarImage(config);

                var numeric = ['steps', 'stepDuration', 'max', 'width', 'height', 'running_value', 'value'];
                for (var i = 0; i < numeric.length; i++)
                    config[numeric[i]] = parseInt(config[numeric[i]]);

                $this.html("");
                var bar = document.createElement('img');
                var text = document.createElement('span');
                var $bar = $(bar);
                var $text = $(text);
                pb.bar = $bar;

                $bar.attr('id', config.id + "_pbImage");
                $text.attr('id', config.id + "_pbText");
                $text.html(getText(config));
                $bar.attr('title', getText(config));
                $bar.attr('alt', getText(config));
                $bar.attr('src', config.boxImage);
                $bar.attr('width', config.width);
                $bar.css("width", config.width + "px");
                $bar.css("height", config.height + "px");
                $bar.css("background-image", "url(" + config.image + ")");
                $bar.css("background-position", ((config.width * -1)) + 'px 50%');
                $bar.css("padding", "0");
                $bar.css("margin", "0");
                $this.append($bar);
                $this.append($text);
            }

            function getPercentage(config) {
                return config.running_value * 100 / config.max;
            }

            function getBarImage(config) {
                var image = config.barImage;
                if (typeof(config.barImage) == 'object') {
                    for (var i in config.barImage) {
                        if (config.running_value >= parseInt(i)) {
                            image = config.barImage[i];
                        } else {
                            break;
                        }
                    }
                }
                return image;
            }

            function getText(config) {
                if (config.showText) {
                    if (config.textFormat == 'percentage') {
                        return " " + Math.round(config.running_value) + "%";
                    } else if (config.textFormat == 'fraction') {
                        return " " + config.running_value + '/' + config.max;
                    }
                }
            }

            config.increment = Math.round((config.value - config.running_value) / config.steps);
            if (config.increment < 0)
                config.increment *= -1;
            if (config.increment < 1)
                config.increment = 1;

            var t = setInterval(function () {
                var pixels = config.width / 100;			// Define how many pixels go into 1%

                if (config.running_value > config.value) {
                    if (config.running_value - config.increment < config.value) {
                        config.running_value = config.value;
                    } else {
                        config.running_value -= config.increment;
                    }
                }
                else if (config.running_value < config.value) {
                    if (config.running_value + config.increment > config.value) {
                        config.running_value = config.value;
                    } else {
                        config.running_value += config.increment;
                    }
                }

                if (config.running_value == config.value)
                    clearInterval(t);

                var $bar = $("#" + config.id + "_pbImage");
                var $text = $("#" + config.id + "_pbText");
                var image = getBarImage(config);
                if (image != config.image) {
                    $bar.css("background-image", "url(" + image + ")");
                    config.image = image;
                }
                $bar.css("background-position", (((config.width * -1)) + (getPercentage(config) * pixels)) + 'px 50%');
                $bar.attr('title', getText(config));
                $text.html(getText(config));

                if (config.callback != null && typeof(config.callback) == 'function')
                    config.callback(config);

                pb.config = config;
            }, config.stepDuration);
        };
    }
    $(function(){
        $("*[data-role='ued-progressbar']").UED_progressBar();
    })
})(jQuery);


// Thanks to http://www.visualjquery.com/rating/rating_redux.html

;if(window.jQuery)
    (function($){
        // IE6 Background Image Fix
        if ($.browser.msie) try { document.execCommand("BackgroundImageCache", false, true)} catch(e) { };

        // plugin initialization
        $.fn.rating = function(options){
            if(this.length==0) return this; // quick fail

            // Handle API methods
            if(typeof arguments[0]=='string'){
                // Perform API methods on individual elements
                if(this.length>1){
                    var args = arguments;
                    return this.each(function(){
                        $.fn.rating.apply($(this), args);
                    });
                };
                // Invoke API method handler
                $.fn.rating[arguments[0]].apply(this, $.makeArray(arguments).slice(1) || []);
                // Quick exit...
                return this;
            };

            var options = $.extend(true, {}, $.fn.rating.options, options);

            // Allow multiple controls with the same name by making each call unique
            $.fn.rating.calls++;

            // loop through each matched element
            this
                .not('.star-rating-applied')
                .addClass('star-rating-applied')
                .each(function(){
                    // Load control parameters / find context / etc
                    var control, input = $(this);
                    var eid = (this.name || 'unnamed-rating').replace(/\[|\]/g, '_').replace(/^\_+|\_+$/g,'');
                    var context = $(this.form || document.body);

                    // FIX: http://code.google.com/p/jquery-star-rating-plugin/issues/detail?id=23
                    var raters = context.data('rating');
                    if(!raters || raters.call!=$.fn.rating.calls) raters = { count:0, call:$.fn.rating.calls };
                    var rater = raters[eid];

                    // if rater is available, verify that the control still exists
                    if(rater) control = rater.data('rating');

                    if(rater && control){// save a byte!
                        // add star to control if rater is available and the same control still exists
                        control.count++;
                    }// save a byte!
                    else{
                        // create new control if first star or control element was removed/replaced

                        // Initialize options for this rater
                        control = $.extend(
                            {}/* new object */,
                            options || {} /* current call options */,
                            ($.metadata? input.metadata(): ($.meta?input.data():null)) || {}, /* metadata options */
                            { count:0, stars: [], inputs: [] }
                        );

                        // increment number of rating controls
                        control.serial = raters.count++;

                        // create rating element
                        rater = $('<span class="star-rating-control"/>');
                        input.before(rater);

                        // Mark element for initialization (once all stars are ready)
                        rater.addClass('rating-to-be-drawn');

                        // Accept readOnly setting from 'disabled' property
                        if(input.attr('disabled') || input.hasClass('disabled')) control.readOnly = true;

                        // Accept required setting from class property (class='required')
                        if(input.hasClass('required')) control.required = true;

                        // Create 'cancel' button
                        rater.append(
                            control.cancel = $('<div class="rating-cancel"><a title="' + control.cancel + '">' + control.cancelValue + '</a></div>')
                                .mouseover(function(){
                                    $(this).rating('drain');
                                    $(this).addClass('star-rating-hover');
                                    //$(this).rating('focus');
                                })
                                .mouseout(function(){
                                    $(this).rating('draw');
                                    $(this).removeClass('star-rating-hover');
                                    //$(this).rating('blur');
                                })
                                .click(function(){
                                    $(this).rating('select');
                                })
                                .data('rating', control)
                        );

                    }; // first element of group

                    // insert rating star
                    var star = $('<div class="star-rating rater-'+ control.serial +'"><a title="' + (this.title || this.value) + '">' + this.value + '</a></div>');
                    rater.append(star);

                    // inherit attributes from input element
                    if(this.id) star.attr('id', this.id);
                    if(this.className) star.addClass(this.className);

                    // Half-stars?
                    if(control.half) control.split = 2;

                    // Prepare division control
                    if(typeof control.split=='number' && control.split>0){
                        var stw = ($.fn.width ? star.width() : 0) || control.starWidth;
                        var spi = (control.count % control.split), spw = Math.floor(stw/control.split);
                        star
                            // restrict star's width and hide overflow (already in CSS)
                            .width(spw)
                            // move the star left by using a negative margin
                            // this is work-around to IE's stupid box model (position:relative doesn't work)
                            .find('a').css({ 'margin-left':'-'+ (spi*spw) +'px' })
                    };

                    // readOnly?
                    if(control.readOnly || !control.enable){ //save a byte!
                        // Mark star as readOnly so user can customize display
                        star.addClass('star-rating-readonly');
                    }  //save a byte!
                    else{ //save a byte!
                        // Enable hover css effects
                        star.addClass('star-rating-live')
                            // Attach mouse events
                            .mouseover(function(){
                                $(this).rating('fill');
                                $(this).rating('focus');
                            })
                            .mouseout(function(){
                                $(this).rating('draw');
                                $(this).rating('blur');
                            })
                            .click(function(){
                                $(this).rating('select');
                            });
                    }; //save a byte!

                    $(this).rating("enable",control.enable);

                    // set current selection
                    if(this.checked)	control.current = star;

                    // set current select for links
                    if(this.nodeName=="A"){
                        if($(this).hasClass('selected'))
                            control.current = star;
                    };

                    // hide input element
                    input.hide();

                    // backward compatibility, form element to plugin
                    input.change(function(){
                        $(this).rating('select');
                    });

                    // attach reference to star to input element and vice-versa
                    star.data('rating.input', input.data('rating.star', star));

                    // store control information in form (or body when form not available)
                    control.stars[control.stars.length] = star[0];
                    control.inputs[control.inputs.length] = input[0];
                    control.rater = raters[eid] = rater;
                    control.context = context;

                    input.data('rating', control);
                    rater.data('rating', control);
                    star.data('rating', control);
                    context.data('rating', raters);
                }); // each element

            // Initialize ratings (first draw)
            $('.rating-to-be-drawn').rating('draw').removeClass('rating-to-be-drawn');

            return this; // don't break the chain...
        };

        $.extend($.fn.rating, {
            // Used to append a unique serial number to internal control ID
            // each time the plugin is invoked so same name controls can co-exist
            calls: 0,

            focus: function(){
                var control = this.data('rating'); if(!control) return this;
                if(!control.focus) return this; // quick fail if not required
                // find data for event
                var input = $(this).data('rating.input') || $( this.tagName=='INPUT' ? this : null );
                // focus handler, as requested by focusdigital.co.uk
                if(control.focus) control.focus.apply(input[0], [input.val(), $('a', input.data('rating.star'))[0]]);
            }, // $.fn.rating.focus

            blur: function(){
                var control = this.data('rating'); if(!control) return this;
                if(!control.blur) return this; // quick fail if not required
                // find data for event
                var input = $(this).data('rating.input') || $( this.tagName=='INPUT' ? this : null );
                // blur handler, as requested by focusdigital.co.uk
                if(control.blur) control.blur.apply(input[0], [input.val(), $('a', input.data('rating.star'))[0]]);
            }, // $.fn.rating.blur

            fill: function(){ // fill to the current mouse position.
                var control = this.data('rating'); if(!control) return this;
                // do not execute when control is in read-only mode
                if(control.readOnly) return;
                // Reset all stars and highlight them up to this element
                this.rating('drain');
                this.prevAll().andSelf().filter('.rater-'+ control.serial).addClass('star-rating-hover');
            },// $.fn.rating.fill

            drain: function() { // drain all the stars.
                var control = this.data('rating'); if(!control) return this;
                // do not execute when control is in read-only mode
                if(control.readOnly || !control.enable) return;
                // Reset all stars
                control.rater.children().filter('.rater-'+ control.serial).removeClass('star-rating-on').removeClass('star-rating-hover');
            },// $.fn.rating.drain

            draw: function(){ // set value and stars to reflect current selection
                var control = this.data('rating'); if(!control) return this;
                // Clear all stars
                this.rating('drain');
                // Set control value
                if(control.current){
                    control.current.data('rating.input').attr('checked','checked');
                    control.current.prevAll().andSelf().filter('.rater-'+ control.serial).addClass('star-rating-on');
                }
                else
                    $(control.inputs).removeAttr('checked');
                // Show/hide 'cancel' button
                control.cancel[control.readOnly || control.required || !control.enable?'hide':'show']();
                // Add/remove read-only classes to remove hand pointer
                this.siblings()[control.readOnly|| !control.enable?'addClass':'removeClass']('star-rating-readonly');
            },// $.fn.rating.draw

            select: function(value,wantCallBack){ // select a value

                // ***** MODIFICATION *****
                // Thanks to faivre.thomas - http://code.google.com/p/jquery-star-rating-plugin/issues/detail?id=27
                //
                // ***** LIST OF MODIFICATION *****
                // ***** added Parameter wantCallBack : false if you don't want a callback. true or undefined if you want postback to be performed at the end of this method'
                // ***** recursive calls to this method were like : ... .rating('select') it's now like .rating('select',undefined,wantCallBack); (parameters are set.)
                // ***** line which is calling callback
                // ***** /LIST OF MODIFICATION *****

                var control = this.data('rating'); if(!control) return this;
                // do not execute when control is in read-only mode
                if(control.readOnly) return;
                // clear selection
                control.current = null;
                // programmatically (based on user input)
                if(typeof value!='undefined'){
                    // select by index (0 based)
                    if(typeof value=='number')
                        return $(control.stars[value]).rating('select',undefined,wantCallBack);
                    // select by literal value (must be passed as a string
                    if(typeof value=='string')
                    //return
                        $.each(control.stars, function(){
                            if($(this).data('rating.input').val()==value) $(this).rating('select',undefined,wantCallBack);
                        });
                }
                else
                    control.current = this[0].tagName=='INPUT' ?
                        this.data('rating.star') :
                        (this.is('.rater-'+ control.serial) ? this : null);

                // Update rating control state
                this.data('rating', control);
                // Update display
                this.rating('draw');
                // find data for event
                var input = $( control.current ? control.current.data('rating.input') : null );
                // click callback, as requested here: http://plugins.jquery.com/node/1655

                // **** MODIFICATION *****
                // Thanks to faivre.thomas - http://code.google.com/p/jquery-star-rating-plugin/issues/detail?id=27
                //
                //old line doing the callback :
                //if(control.callback) control.callback.apply(input[0], [input.val(), $('a', control.current)[0]]);// callback event
                //
                //new line doing the callback (if i want :)
                if((wantCallBack ||wantCallBack == undefined) && control.callback) control.callback.apply(input[0], [input.val(), $('a', control.current)[0]]);// callback event
                //to ensure retro-compatibility, wantCallBack must be considered as true by default
                // **** /MODIFICATION *****

            },// $.fn.rating.select

            readOnly: function(toggle, disable){ // make the control read-only (still submits value)
                var control = this.data('rating');
                if(!control) return this;
                // setread-only status
                control.readOnly = toggle || toggle==undefined ? true : false;
                // enable/disable control value submission
                if(disable)  $(control.inputs).attr("disabled", "disabled");
                else  $(control.inputs).removeAttr("disabled");
                // Update rating control state
                this.data('rating', control);
                // Update display
                this.rating('draw');
            },// $.fn.rating.readOnly

            enable: function(value){ // make read/write and submit value
                if(value) this.rating('readOnly', false, false);
                else this.rating('readOnly', true, true);
            }// $.fn.rating.select

        });

        // Default Settings
        $.fn.rating.options = {
            cancel: '\u91CD\u7F6E\u8BC4\u5206',   // advisory title for the 'cancel' link
            cancelValue: '',           // value to submit when user click the 'cancel' link
            split: 0,                  // split the star into how many parts?

            // Width of star image in case the plugin can't work it out. This can happen if
            // the jQuery.dimensions plugin is not available OR the image is hidden at installation
            starWidth: 16,//,

            //NB.: These don't need to be pre-defined (can be undefined/null) so let's save some code!
            //half:     false,         // just a shortcut to control.split = 2
            required: false,         // disables the 'cancel' button so user can only select one of the specified values
            //readOnly: false,         // disable rating plugin interaction/ values cannot be changed
            enable: true  //rating plugin enable or disable
            //focus:    function(){},  // executed when stars are focused
            //blur:     function(){},  // executed when stars are focused
            //callback: function(){},  // executed when a star is clicked
        };

        $.fn.UED_rating = function(){
            function strToJson(str){
                str = str? str:"";
                return  eval( "({" + str + "})" );
            }

            return this.each(function(i,n){
                var _this = $(n),
                    _rel =  strToJson(_this.attr("data-rel"));
                _this.find('input[type=radio].star').rating(_rel);
            })
        };

        $(function(){
            $("*[data-role='ued-rating']").UED_rating();
        });

    })(jQuery);


/**
 * @Filename: scrollText.js
 * @Description: scrollText
 * @Version: 1.0.0
 * @Author: wangchen.si-tech
 * @UpdateBy: wanggq 20121109
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $(".ued-scrollText").UED_scrollText({per:2});
 *
 *
 */
(function($){
    $.fn.UED_scrollText = function(option){
        var defaults = {
            anim: "seesaw",
            speed: 600,
            interval: 2000,
            per: 1
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        var uedAnimate = {
            init:function(o){
                var _animation = $(o).data("option").anim;
                switch(_animation){
                    case "seesaw":
                        uedAnimate.seesaw(o);
                        break;
                    case "fadeToggle":
                        uedAnimate.fadeToggle(o);
                        break;
                    default :
                        break;
                }
            },

            // up and down
            seesaw: function(o){
                var  _o = $(o).children("ul"),
                    _oChild = _o.find("li"),
                    _oLiH = _oChild.eq(0).outerHeight(true),
                    _oChildL = _oChild.length,
                    _option = $(o).data("option");

                $(o).css({height: _oLiH * _option.per+"px", marginTop:"0px"});

                var timer = setInterval(function(){ _show(); }, _option.interval);
                _oChild.hover(function(){
                    _o.stop();
                    clearInterval(timer);
                },function(){
                    timer = setInterval(function(){ _show(); }, _option.interval);
                })

                function _show(){
                    _o.animate({
                        marginTop: -_oLiH * _option.per+"px"
                    }, _option.speed, function(){
                        _o.css({marginTop: "0px"}).find("li:lt("+_option.per+")").appendTo(_o);
                    });
                }
            },
            // fadeIn fadeOut
            fadeToggle:function(o){
                var  _o = $(o).children("ul"),
                    _oChild = _o.find("li"),
                    _oLiH = _oChild.eq(0).outerHeight(true),
                    _oChildL = _oChild.length,
                    _option = $(o).data("option");

                $(o).css({height: _oLiH * _option.per+"px"});
                _oChild.hide();

                var timer = setInterval(function(){ _show(); }, _option.interval);
                _oChild.hover(function(){
                    _o.stop();
                    clearInterval(timer);
                },function(){
                    timer = setInterval(function(){ _show(); }, _option.interval);
                })

                function _show(){
                    _o.find("li:lt("+_option.per+")").appendTo(_o).fadeIn(_option.speed).siblings("li").fadeOut();
                }
            }
        }

        //Interlace change color
        return this.each(function(i,n){
            var  _o = $(n).children("ul"),
                _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            $(n).data("option", $.extend( true, {}, defaults, option , _rel));

            uedAnimate.init(n);
        })
    }

    $(function(){
        $("*[data-role='ued-scrollText']").UED_scrollText();
    })
})(jQuery);

/**
 * @Filename: table.js
 * @Description: table interlaced color change
 * @Version: 1.0.0
 * @Author: dujj.si-tech
 * @UpdateBy: wanggq 20121105
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * $("table").UED_tableInterlace();
 *
 */
(function($){
    $.fn.UED_tableInterlace = function(option){
        var defaults = {
            //tr odd class
            oddClass: "ued-tr-odd",
            //tr even class
            evenClass: "ued-tr-even",
            //tr hover class
            trHover: "ued-tr-hover",
            //tr click event
            trClick: function(){}
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        };

        //Interlace change color
        return this.each(function(i,n){
            var _tbody = $(n).children("tbody").length>0?$(n).children("tbody"):$(n),
                _tr = _tbody.children("tr"),
                _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true,{},defaults,option , _rel);

            for(var i=0; i<_tr.length; i++){
                var _class;
                if(i%2==0)_class = option.evenClass;
                else _class = option.oddClass;
                _tr.eq(i).addClass(_class);
            }
            //tr hover event
            _tr
                .hover(function(){
                    $(this).addClass(option.trHover);
                },function(){
                    $(this).removeClass(option.trHover);
                })
                .click(option.trClick);
        })
    }
    $(function(){
        $("*[data-role='ued-tableInterlace']").UED_tableInterlace();
    })
})(jQuery);

/**
 * @Filename: tabs.js
 * @Description: tab
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121206
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 *
 *
 */
(function($){
    $.fn.UED_tabs = function(option){
        var defaults = {
            activeClass: "active",
            activeIndex: 0,
            trigger: "click"
        };

        function _setActive(o){
            var  _o = $(o),
                _targetConId = _o.children("a").attr("href");


            var oDiv=_o.parent('ul').parent('div.title-bg').next('div.tab-content');
            /*
             *  href="javascript:;" || href="javascript:void(0);" ||  href="#"
             *  href="#Id"
             *              {   has 'target'
             *  href= url   {
             *              {   no  'target'
             */
            if(_targetConId==="#" || _targetConId==="javascript:;" || _targetConId==="javascript:void(0);"){
                _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
                var i=_o.index();
                oDiv.find('div.tab-pane').eq(i).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
            }else{
                if(oDiv.find($(_targetConId)).length==1 ){
                    _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
                    $(_targetConId).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
                }else if(oDiv.find($(_targetConId)).length==0){
                    if(_o.children("a").attr("target")){
                    }else{
                        _o.addClass(option.activeClass).siblings("li").removeClass(option.activeClass);
                        var i=_o.index();
                        oDiv.find('div.tab-pane').eq(i).addClass(option.activeClass).siblings(".tab-pane").removeClass(option.activeClass);
                        //href='#无效的字符串'
                        var reg=/^#\w{1,}$/g;
                        if(reg.test(_targetConId)){
                        }
                        else{
                            $.ajax({
                                url:_targetConId,
                                type:'GET',
                                dataType:"html",
                                success:function(html){
                                    oDiv.find('div.tab-pane').eq(i).empty().append(html);
                                },
                                beforeSend: function(){
                                    oDiv.find('div.tab-pane').eq(i).empty().append('正在加载中...')
                                },
                                error:function(){
                                    oDiv.find('div.tab-pane').eq(i).empty().append('加载失败，请检查请求链接，或者稍后重试!')
                                }
                            })
                        }
                    }
                }else{
                    alert("页面中id为"+_targetConId+"的div存在"+oDiv.find($(_targetConId)).length+"个，页面中id是唯一的，请检查后重新配置！");
                }
            }

        }
        function _init(_ul, _li){
            if(_li.hasClass(option.activeClass)){
                _setActive( _ul.find("li."+option.activeClass+":first") );
            }else if( option.activeIndex >= 0 ){
                _setActive( _li.eq(option.activeIndex) );
            }
        }
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        //Interlace change color
        return this.each(function(i, n){
            var  _ul = $(n).children("div.title-bg").children("ul:first"),
                _li = _ul.children("li"),
                _rel =  strToJson( $(n).attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend(true, {}, defaults, option, _rel);

            // init
            _init(_ul, _li);

            // event
            _li.bind(option.trigger?option.trigger:option.trigger, function(e){
                _setActive($(this));
                if($(this).children("a").attr("target")==undefined){
                    e.preventDefault();
                }
            })
        })
    }

    // auto init plugin
    $(function(){
        $("*[data-role='ued-tabs']").UED_tabs();
    })
})(jQuery);

/**
 * @Filename: wordCount.js
 * @Description: The weibo input word count
 * @Version: 1.0.0
 * @Author: wanggq.si-tech
 * @UpdateBy: wanggq 20121122
 * Copyright (c) 2012-2013 si-tech
 *
 * @example
 * <textarea type="text" name="weibo" id="weibo"></textarea>
 *
 * $("#weibo").UED_wordCount();
 *
 */
(function($){
    $.fn.UED_wordCount = function(option){
        var defaults = {
            //max word length
            maxLength: 140,
            prompt1: "\u8FD8\u53EF\u4EE5\u8F93\u5165",
            prompt2: "\u8D85\u51FA",
            symbol: ""
        };
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }

        //Interlace change color
        return this.each(function(i,n){
            var _Ele = $(n),
                _value = "", // value
                _surplus = 0, //还能输入字数
                _rel =  strToJson( _Ele.attr("data-rel") );

            /* 合并默认参数和用户自定义参数 */
            option = $.extend( true, {}, defaults, option, _rel );

            var _tipEle = $(option.symbol);

            _Ele
                .keyup(function() {
                    _value = $(this).val();
                    _surplus = option.maxLength - _value.length;
                    var prompt = (_surplus >= 0? option.prompt1+_surplus : option.prompt2+Math.abs(_surplus))+"\u5B57";
                    if(_tipEle.length){
                        _tipEle.text(prompt);
                    }
                });
        })
    }
    $(function(){
        $("*[data-role='ued-wordCount']").UED_wordCount();
    })
})(jQuery);

/*
 Highcharts JS v3.0.5 (2013-08-23)

 (c) 2009-2013 Torstein Hønsi

 License: www.highcharts.com/license
*/
(function(){function s(a,b){var c;a||(a={});for(c in b)a[c]=b[c];return a}function x(){var a,b=arguments.length,c={},d=function(a,b){var c,h;typeof a!=="object"&&(a={});for(h in b)b.hasOwnProperty(h)&&(c=b[h],a[h]=c&&typeof c==="object"&&Object.prototype.toString.call(c)!=="[object Array]"&&typeof c.nodeType!=="number"?d(a[h]||{},c):b[h]);return a};for(a=0;a<b;a++)c=d(c,arguments[a]);return c}function A(a,b){return parseInt(a,b||10)}function ea(a){return typeof a==="string"}function T(a){return typeof a===
"object"}function Ha(a){return Object.prototype.toString.call(a)==="[object Array]"}function qa(a){return typeof a==="number"}function na(a){return R.log(a)/R.LN10}function fa(a){return R.pow(10,a)}function ga(a,b){for(var c=a.length;c--;)if(a[c]===b){a.splice(c,1);break}}function t(a){return a!==v&&a!==null}function w(a,b,c){var d,e;if(ea(b))t(c)?a.setAttribute(b,c):a&&a.getAttribute&&(e=a.getAttribute(b));else if(t(b)&&T(b))for(d in b)a.setAttribute(d,b[d]);return e}function ia(a){return Ha(a)?
a:[a]}function p(){var a=arguments,b,c,d=a.length;for(b=0;b<d;b++)if(c=a[b],typeof c!=="undefined"&&c!==null)return c}function L(a,b){if(ra&&b&&b.opacity!==v)b.filter="alpha(opacity="+b.opacity*100+")";s(a.style,b)}function U(a,b,c,d,e){a=z.createElement(a);b&&s(a,b);e&&L(a,{padding:0,border:S,margin:0});c&&L(a,c);d&&d.appendChild(a);return a}function ha(a,b){var c=function(){};c.prototype=new a;s(c.prototype,b);return c}function za(a,b,c,d){var e=M.lang,a=+a||0,f=b===-1?(a.toString().split(".")[1]||
"").length:isNaN(b=O(b))?2:b,b=c===void 0?e.decimalPoint:c,d=d===void 0?e.thousandsSep:d,e=a<0?"-":"",c=String(A(a=O(a).toFixed(f))),g=c.length>3?c.length%3:0;return e+(g?c.substr(0,g)+d:"")+c.substr(g).replace(/(\d{3})(?=\d)/g,"$1"+d)+(f?b+O(a-c).toFixed(f).slice(2):"")}function Aa(a,b){return Array((b||2)+1-String(a).length).join(0)+a}function Bb(a,b,c){var d=a[b];a[b]=function(){var a=Array.prototype.slice.call(arguments);a.unshift(d);return c.apply(this,a)}}function Ba(a,b){for(var c="{",d=!1,
e,f,g,h,i,j=[];(c=a.indexOf(c))!==-1;){e=a.slice(0,c);if(d){f=e.split(":");g=f.shift().split(".");i=g.length;e=b;for(h=0;h<i;h++)e=e[g[h]];if(f.length)f=f.join(":"),g=/\.([0-9])/,h=M.lang,i=void 0,/f$/.test(f)?(i=(i=f.match(g))?i[1]:-1,e=za(e,i,h.decimalPoint,f.indexOf(",")>-1?h.thousandsSep:"")):e=Xa(f,e)}j.push(e);a=a.slice(c+1);c=(d=!d)?"}":"{"}j.push(a);return j.join("")}function lb(a){return R.pow(10,P(R.log(a)/R.LN10))}function mb(a,b,c,d){var e,c=p(c,1);e=a/c;b||(b=[1,2,2.5,5,10],d&&d.allowDecimals===
!1&&(c===1?b=[1,2,5,10]:c<=0.1&&(b=[1/c])));for(d=0;d<b.length;d++)if(a=b[d],e<=(b[d]+(b[d+1]||b[d]))/2)break;a*=c;return a}function Cb(a,b){var c=b||[[Db,[1,2,5,10,20,25,50,100,200,500]],[nb,[1,2,5,10,15,30]],[Ya,[1,2,5,10,15,30]],[Qa,[1,2,3,4,6,8,12]],[sa,[1,2]],[Za,[1,2]],[Ra,[1,2,3,4,6]],[ta,null]],d=c[c.length-1],e=H[d[0]],f=d[1],g;for(g=0;g<c.length;g++)if(d=c[g],e=H[d[0]],f=d[1],c[g+1]&&a<=(e*f[f.length-1]+H[c[g+1][0]])/2)break;e===H[ta]&&a<5*e&&(f=[1,2,5]);e===H[ta]&&a<5*e&&(f=[1,2,5]);c=
mb(a/e,f,d[0]===ta?lb(a/e):1);return{unitRange:e,count:c,unitName:d[0]}}function Eb(a,b,c,d){var e=[],f={},g=M.global.useUTC,h,i=new Date(b),j=a.unitRange,k=a.count;if(t(b)){j>=H[nb]&&(i.setMilliseconds(0),i.setSeconds(j>=H[Ya]?0:k*P(i.getSeconds()/k)));if(j>=H[Ya])i[Fb](j>=H[Qa]?0:k*P(i[ob]()/k));if(j>=H[Qa])i[Gb](j>=H[sa]?0:k*P(i[pb]()/k));if(j>=H[sa])i[qb](j>=H[Ra]?1:k*P(i[Sa]()/k));j>=H[Ra]&&(i[Hb](j>=H[ta]?0:k*P(i[$a]()/k)),h=i[ab]());j>=H[ta]&&(h-=h%k,i[Ib](h));if(j===H[Za])i[qb](i[Sa]()-i[rb]()+
p(d,1));b=1;h=i[ab]();for(var d=i.getTime(),l=i[$a](),m=i[Sa](),o=g?0:(864E5+i.getTimezoneOffset()*6E4)%864E5;d<c;)e.push(d),j===H[ta]?d=bb(h+b*k,0):j===H[Ra]?d=bb(h,l+b*k):!g&&(j===H[sa]||j===H[Za])?d=bb(h,l,m+b*k*(j===H[sa]?1:7)):d+=j*k,b++;e.push(d);n(sb(e,function(a){return j<=H[Qa]&&a%H[sa]===o}),function(a){f[a]=sa})}e.info=s(a,{higherRanks:f,totalRange:j*k});return e}function Jb(){this.symbol=this.color=0}function Kb(a,b){var c=a.length,d,e;for(e=0;e<c;e++)a[e].ss_i=e;a.sort(function(a,c){d=
b(a,c);return d===0?a.ss_i-c.ss_i:d});for(e=0;e<c;e++)delete a[e].ss_i}function Ia(a){for(var b=a.length,c=a[0];b--;)a[b]<c&&(c=a[b]);return c}function ua(a){for(var b=a.length,c=a[0];b--;)a[b]>c&&(c=a[b]);return c}function Ja(a,b){for(var c in a)a[c]&&a[c]!==b&&a[c].destroy&&a[c].destroy(),delete a[c]}function Ta(a){cb||(cb=U(Ca));a&&cb.appendChild(a);cb.innerHTML=""}function ja(a,b){var c="Highcharts error #"+a+": www.highcharts.com/errors/"+a;if(b)throw c;else N.console&&console.log(c)}function ka(a){return parseFloat(a.toPrecision(14))}
function Ka(a,b){Da=p(a,b.animation)}function Lb(){var a=M.global.useUTC,b=a?"getUTC":"get",c=a?"setUTC":"set";bb=a?Date.UTC:function(a,b,c,g,h,i){return(new Date(a,b,p(c,1),p(g,0),p(h,0),p(i,0))).getTime()};ob=b+"Minutes";pb=b+"Hours";rb=b+"Day";Sa=b+"Date";$a=b+"Month";ab=b+"FullYear";Fb=c+"Minutes";Gb=c+"Hours";qb=c+"Date";Hb=c+"Month";Ib=c+"FullYear"}function va(){}function La(a,b,c,d){this.axis=a;this.pos=b;this.type=c||"";this.isNew=!0;!c&&!d&&this.addLabel()}function tb(a,b){this.axis=a;if(b)this.options=
b,this.id=b.id}function Mb(a,b,c,d,e,f){var g=a.chart.inverted;this.axis=a;this.isNegative=c;this.options=b;this.x=d;this.total=null;this.points={};this.stack=e;this.percent=f==="percent";this.alignOptions={align:b.align||(g?c?"left":"right":"center"),verticalAlign:b.verticalAlign||(g?"middle":c?"bottom":"top"),y:p(b.y,g?4:c?14:-6),x:p(b.x,g?c?-6:6:0)};this.textAlign=b.textAlign||(g?c?"right":"left":"center")}function db(){this.init.apply(this,arguments)}function ub(){this.init.apply(this,arguments)}
function vb(a,b){this.init(a,b)}function wb(a,b){this.init(a,b)}function xb(){this.init.apply(this,arguments)}var v,z=document,N=window,R=Math,u=R.round,P=R.floor,wa=R.ceil,r=R.max,C=R.min,O=R.abs,W=R.cos,ca=R.sin,Ma=R.PI,Ua=Ma*2/360,Ea=navigator.userAgent,Nb=N.opera,ra=/msie/i.test(Ea)&&!Nb,eb=z.documentMode===8,fb=/AppleWebKit/.test(Ea),gb=/Firefox/.test(Ea),Ob=/(Mobile|Android|Windows Phone)/.test(Ea),xa="http://www.w3.org/2000/svg",Z=!!z.createElementNS&&!!z.createElementNS(xa,"svg").createSVGRect,
Ub=gb&&parseInt(Ea.split("Firefox/")[1],10)<4,$=!Z&&!ra&&!!z.createElement("canvas").getContext,Va,hb=z.documentElement.ontouchstart!==v,Pb={},yb=0,cb,M,Xa,Da,zb,H,ya=function(){},Fa=[],Ca="div",S="none",Qb="rgba(192,192,192,"+(Z?1.0E-4:0.002)+")",Db="millisecond",nb="second",Ya="minute",Qa="hour",sa="day",Za="week",Ra="month",ta="year",Rb="stroke-width",bb,ob,pb,rb,Sa,$a,ab,Fb,Gb,qb,Hb,Ib,aa={};N.Highcharts=N.Highcharts?ja(16,!0):{};Xa=function(a,b,c){if(!t(b)||isNaN(b))return"Invalid date";var a=
p(a,"%Y-%m-%d %H:%M:%S"),d=new Date(b),e,f=d[pb](),g=d[rb](),h=d[Sa](),i=d[$a](),j=d[ab](),k=M.lang,l=k.weekdays,d=s({a:l[g].substr(0,3),A:l[g],d:Aa(h),e:h,b:k.shortMonths[i],B:k.months[i],m:Aa(i+1),y:j.toString().substr(2,2),Y:j,H:Aa(f),I:Aa(f%12||12),l:f%12||12,M:Aa(d[ob]()),p:f<12?"AM":"PM",P:f<12?"am":"pm",S:Aa(d.getSeconds()),L:Aa(u(b%1E3),3)},Highcharts.dateFormats);for(e in d)for(;a.indexOf("%"+e)!==-1;)a=a.replace("%"+e,typeof d[e]==="function"?d[e](b):d[e]);return c?a.substr(0,1).toUpperCase()+
a.substr(1):a};Jb.prototype={wrapColor:function(a){if(this.color>=a)this.color=0},wrapSymbol:function(a){if(this.symbol>=a)this.symbol=0}};H=function(){for(var a=0,b=arguments,c=b.length,d={};a<c;a++)d[b[a++]]=b[a];return d}(Db,1,nb,1E3,Ya,6E4,Qa,36E5,sa,864E5,Za,6048E5,Ra,26784E5,ta,31556952E3);zb={init:function(a,b,c){var b=b||"",d=a.shift,e=b.indexOf("C")>-1,f=e?7:3,g,b=b.split(" "),c=[].concat(c),h,i,j=function(a){for(g=a.length;g--;)a[g]==="M"&&a.splice(g+1,0,a[g+1],a[g+2],a[g+1],a[g+2])};e&&
(j(b),j(c));a.isArea&&(h=b.splice(b.length-6,6),i=c.splice(c.length-6,6));if(d<=c.length/f)for(;d--;)c=[].concat(c).splice(0,f).concat(c);a.shift=0;if(b.length)for(a=c.length;b.length<a;)d=[].concat(b).splice(b.length-f,f),e&&(d[f-6]=d[f-2],d[f-5]=d[f-1]),b=b.concat(d);h&&(b=b.concat(h),c=c.concat(i));return[b,c]},step:function(a,b,c,d){var e=[],f=a.length;if(c===1)e=d;else if(f===b.length&&c<1)for(;f--;)d=parseFloat(a[f]),e[f]=isNaN(d)?a[f]:c*parseFloat(b[f]-d)+d;else e=b;return e}};(function(a){N.HighchartsAdapter=
N.HighchartsAdapter||a&&{init:function(b){var c=a.fx,d=c.step,e,f=a.Tween,g=f&&f.propHooks;e=a.cssHooks.opacity;a.extend(a.easing,{easeOutQuad:function(a,b,c,d,e){return-d*(b/=e)*(b-2)+c}});a.each(["cur","_default","width","height","opacity"],function(a,b){var e=d,k,l;b==="cur"?e=c.prototype:b==="_default"&&f&&(e=g[b],b="set");(k=e[b])&&(e[b]=function(c){c=a?c:this;l=c.elem;return l.attr?l.attr(c.prop,b==="cur"?v:c.now):k.apply(this,arguments)})});Bb(e,"get",function(a,b,c){return b.attr?b.opacity||
0:a.call(this,b,c)});e=function(a){var c=a.elem,d;if(!a.started)d=b.init(c,c.d,c.toD),a.start=d[0],a.end=d[1],a.started=!0;c.attr("d",b.step(a.start,a.end,a.pos,c.toD))};f?g.d={set:e}:d.d=e;this.each=Array.prototype.forEach?function(a,b){return Array.prototype.forEach.call(a,b)}:function(a,b){for(var c=0,d=a.length;c<d;c++)if(b.call(a[c],a[c],c,a)===!1)return c};a.fn.highcharts=function(){var a="Chart",b=arguments,c,d;ea(b[0])&&(a=b[0],b=Array.prototype.slice.call(b,1));c=b[0];if(c!==v)c.chart=c.chart||
{},c.chart.renderTo=this[0],new Highcharts[a](c,b[1]),d=this;c===v&&(d=Fa[w(this[0],"data-highcharts-chart")]);return d}},getScript:a.getScript,inArray:a.inArray,adapterRun:function(b,c){return a(b)[c]()},grep:a.grep,map:function(a,c){for(var d=[],e=0,f=a.length;e<f;e++)d[e]=c.call(a[e],a[e],e,a);return d},offset:function(b){return a(b).offset()},addEvent:function(b,c,d){a(b).bind(c,d)},removeEvent:function(b,c,d){var e=z.removeEventListener?"removeEventListener":"detachEvent";z[e]&&b&&!b[e]&&(b[e]=
function(){});a(b).unbind(c,d)},fireEvent:function(b,c,d,e){var f=a.Event(c),g="detached"+c,h;!ra&&d&&(delete d.layerX,delete d.layerY);s(f,d);b[c]&&(b[g]=b[c],b[c]=null);a.each(["preventDefault","stopPropagation"],function(a,b){var c=f[b];f[b]=function(){try{c.call(f)}catch(a){b==="preventDefault"&&(h=!0)}}});a(b).trigger(f);b[g]&&(b[c]=b[g],b[g]=null);e&&!f.isDefaultPrevented()&&!h&&e(f)},washMouseEvent:function(a){var c=a.originalEvent||a;if(c.pageX===v)c.pageX=a.pageX,c.pageY=a.pageY;return c},
animate:function(b,c,d){var e=a(b);if(!b.style)b.style={};if(c.d)b.toD=c.d,c.d=1;e.stop();c.opacity!==v&&b.attr&&(c.opacity+="px");e.animate(c,d)},stop:function(b){a(b).stop()}}})(N.jQuery);var X=N.HighchartsAdapter,F=X||{};X&&X.init.call(X,zb);var ib=F.adapterRun,Vb=F.getScript,oa=F.inArray,n=F.each,sb=F.grep,Wb=F.offset,Na=F.map,J=F.addEvent,ba=F.removeEvent,K=F.fireEvent,Sb=F.washMouseEvent,Ab=F.animate,Wa=F.stop,F={enabled:!0,x:0,y:15,style:{color:"#666",cursor:"default",fontSize:"11px",lineHeight:"14px"}};
M={colors:"#6c98f1,#6bc213,#8bbc21,#910000,#1aadce,#492970,#f28f43,#77a1e5,#c42525,#0d233a".split(","),symbols:["circle","diamond","square","triangle","triangle-down"],lang:{loading:"Loading...",months:"January,February,March,April,May,June,July,August,September,October,November,December".split(","),shortMonths:"Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec".split(","),weekdays:"Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday".split(","),decimalPoint:".",numericSymbols:"k,M,G,T,P,E".split(","),
resetZoom:"Reset zoom",resetZoomTitle:"Reset zoom level 1:1",thousandsSep:","},global:{useUTC:!0,canvasToolsURL:"http://code.highcharts.com/3.0.5/modules/canvas-tools.js",VMLRadialGradientURL:"http://code.highcharts.com/3.0.5/gfx/vml-radial-gradient.png"},chart:{borderColor:"#4572A7",borderRadius:5,defaultSeriesType:"line",ignoreHiddenSeries:!0,spacingTop:10,spacingRight:10,spacingBottom:15,spacingLeft:10,style:{fontFamily:'"Lucida Grande", "Lucida Sans Unicode", Verdana, Arial, Helvetica, sans-serif',
fontSize:"12px"},backgroundColor:"#FFFFFF",plotBorderColor:"#C0C0C0",resetZoomButton:{theme:{zIndex:20},position:{align:"right",x:-10,y:10}}},title:{text:"Chart title",align:"center",margin:15,style:{color:"#274b6d",fontSize:"12px"}},subtitle:{text:"",align:"center",style:{color:"#4d759e"}},plotOptions:{line:{allowPointSelect:!1,showCheckbox:!1,animation:{duration:1E3},events:{},lineWidth:2,marker:{enabled:!0,lineWidth:0,radius:4,lineColor:"#FFFFFF",states:{hover:{enabled:!0},select:{fillColor:"#FFFFFF",
lineColor:"#000000",lineWidth:2}}},point:{events:{}},dataLabels:x(F,{align:"center",enabled:!1,formatter:function(){return this.y===null?"":za(this.y,-1)},verticalAlign:"bottom",y:0}),cropThreshold:300,pointRange:0,showInLegend:!0,states:{hover:{marker:{}},select:{marker:{}}},stickyTracking:!0}},labels:{style:{position:"absolute",color:"#3E576F"}},legend:{enabled:!0,align:"center",layout:"horizontal",labelFormatter:function(){return this.name},borderWidth:1,borderColor:"#909090",borderRadius:5,navigation:{activeColor:"#274b6d",
inactiveColor:"#CCC"},shadow:!1,itemStyle:{cursor:"pointer",color:"#274b6d",fontSize:"12px"},itemHoverStyle:{color:"#000"},itemHiddenStyle:{color:"#CCC"},itemCheckboxStyle:{position:"absolute",width:"13px",height:"13px"},symbolWidth:16,symbolPadding:5,verticalAlign:"bottom",x:0,y:0,title:{style:{fontWeight:"bold"}}},loading:{labelStyle:{fontWeight:"bold",position:"relative",top:"1em"},style:{position:"absolute",backgroundColor:"white",opacity:0.5,textAlign:"center"}},tooltip:{enabled:!0,animation:Z,
backgroundColor:"rgba(255, 255, 255, .85)",borderWidth:1,borderRadius:3,dateTimeLabelFormats:{millisecond:"%A, %b %e, %H:%M:%S.%L",second:"%A, %b %e, %H:%M:%S",minute:"%A, %b %e, %H:%M",hour:"%A, %b %e, %H:%M",day:"%A, %b %e, %Y",week:"Week from %A, %b %e, %Y",month:"%B %Y",year:"%Y"},headerFormat:'<span style="font-size: 10px">{point.key}</span><br/>',pointFormat:'<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b><br/>',shadow:!0,snap:Ob?25:10,style:{color:"#333333",cursor:"default",
fontSize:"12px",padding:"8px",whiteSpace:"nowrap"}},credits:{enabled:0,text:"Highcharts.com",href:"http://www.highcharts.com",position:{align:"right",x:-10,verticalAlign:"bottom",y:-5},style:{cursor:"pointer",color:"#909090",fontSize:"9px"}}};var Y=M.plotOptions,X=Y.line;Lb();var pa=function(a){var b=[],c,d;(function(a){a&&a.stops?d=Na(a.stops,function(a){return pa(a[1])}):(c=/rgba\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]?(?:\.[0-9]+)?)\s*\)/.exec(a))?b=[A(c[1]),A(c[2]),
A(c[3]),parseFloat(c[4],10)]:(c=/#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(a))?b=[A(c[1],16),A(c[2],16),A(c[3],16),1]:(c=/rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(a))&&(b=[A(c[1]),A(c[2]),A(c[3]),1])})(a);return{get:function(c){var f;d?(f=x(a),f.stops=[].concat(f.stops),n(d,function(a,b){f.stops[b]=[f.stops[b][0],a.get(c)]})):f=b&&!isNaN(b[0])?c==="rgb"?"rgb("+b[0]+","+b[1]+","+b[2]+")":c==="a"?b[3]:"rgba("+b.join(",")+")":a;return f},brighten:function(a){if(d)n(d,
function(b){b.brighten(a)});else if(qa(a)&&a!==0){var c;for(c=0;c<3;c++)b[c]+=A(a*255),b[c]<0&&(b[c]=0),b[c]>255&&(b[c]=255)}return this},rgba:b,setOpacity:function(a){b[3]=a;return this}}};va.prototype={init:function(a,b){this.element=b==="span"?U(b):z.createElementNS(xa,b);this.renderer=a;this.attrSetters={}},opacity:1,animate:function(a,b,c){b=p(b,Da,!0);Wa(this);if(b){b=x(b);if(c)b.complete=c;Ab(this,a,b)}else this.attr(a),c&&c()},attr:function(a,b){var c,d,e,f,g=this.element,h=g.nodeName.toLowerCase(),
i=this.renderer,j,k=this.attrSetters,l=this.shadows,m,o,q=this;ea(a)&&t(b)&&(c=a,a={},a[c]=b);if(ea(a))c=a,h==="circle"?c={x:"cx",y:"cy"}[c]||c:c==="strokeWidth"&&(c="stroke-width"),q=w(g,c)||this[c]||0,c!=="d"&&c!=="visibility"&&(q=parseFloat(q));else{for(c in a)if(j=!1,d=a[c],e=k[c]&&k[c].call(this,d,c),e!==!1){e!==v&&(d=e);if(c==="d")d&&d.join&&(d=d.join(" ")),/(NaN| {2}|^$)/.test(d)&&(d="M 0 0");else if(c==="x"&&h==="text")for(e=0;e<g.childNodes.length;e++)f=g.childNodes[e],w(f,"x")===w(g,"x")&&
w(f,"x",d);else if(this.rotation&&(c==="x"||c==="y"))o=!0;else if(c==="fill")d=i.color(d,g,c);else if(h==="circle"&&(c==="x"||c==="y"))c={x:"cx",y:"cy"}[c]||c;else if(h==="rect"&&c==="r")w(g,{rx:d,ry:d}),j=!0;else if(c==="translateX"||c==="translateY"||c==="rotation"||c==="verticalAlign"||c==="scaleX"||c==="scaleY")j=o=!0;else if(c==="stroke")d=i.color(d,g,c);else if(c==="dashstyle")if(c="stroke-dasharray",d=d&&d.toLowerCase(),d==="solid")d=S;else{if(d){d=d.replace("shortdashdotdot","3,1,1,1,1,1,").replace("shortdashdot",
"3,1,1,1").replace("shortdot","1,1,").replace("shortdash","3,1,").replace("longdash","8,3,").replace(/dot/g,"1,3,").replace("dash","4,3,").replace(/,$/,"").split(",");for(e=d.length;e--;)d[e]=A(d[e])*p(a["stroke-width"],this["stroke-width"]);d=d.join(",")}}else if(c==="width")d=A(d);else if(c==="align")c="text-anchor",d={left:"start",center:"middle",right:"end"}[d];else if(c==="title")e=g.getElementsByTagName("title")[0],e||(e=z.createElementNS(xa,"title"),g.appendChild(e)),e.textContent=d;c==="strokeWidth"&&
(c="stroke-width");if(c==="stroke-width"||c==="stroke"){this[c]=d;if(this.stroke&&this["stroke-width"])w(g,"stroke",this.stroke),w(g,"stroke-width",this["stroke-width"]),this.hasStroke=!0;else if(c==="stroke-width"&&d===0&&this.hasStroke)g.removeAttribute("stroke"),this.hasStroke=!1;j=!0}this.symbolName&&/^(x|y|width|height|r|start|end|innerR|anchorX|anchorY)/.test(c)&&(m||(this.symbolAttr(a),m=!0),j=!0);if(l&&/^(width|height|visibility|x|y|d|transform|cx|cy|r)$/.test(c))for(e=l.length;e--;)w(l[e],
c,c==="height"?r(d-(l[e].cutHeight||0),0):d);if((c==="width"||c==="height")&&h==="rect"&&d<0)d=0;this[c]=d;c==="text"?(d!==this.textStr&&delete this.bBox,this.textStr=d,this.added&&i.buildText(this)):j||w(g,c,d)}o&&this.updateTransform()}return q},addClass:function(a){var b=this.element,c=w(b,"class")||"";c.indexOf(a)===-1&&w(b,"class",c+" "+a);return this},symbolAttr:function(a){var b=this;n("x,y,r,start,end,width,height,innerR,anchorX,anchorY".split(","),function(c){b[c]=p(a[c],b[c])});b.attr({d:b.renderer.symbols[b.symbolName](b.x,
b.y,b.width,b.height,b)})},clip:function(a){return this.attr("clip-path",a?"url("+this.renderer.url+"#"+a.id+")":S)},crisp:function(a,b,c,d,e){var f,g={},h={},i,a=a||this.strokeWidth||this.attr&&this.attr("stroke-width")||0;i=u(a)%2/2;h.x=P(b||this.x||0)+i;h.y=P(c||this.y||0)+i;h.width=P((d||this.width||0)-2*i);h.height=P((e||this.height||0)-2*i);h.strokeWidth=a;for(f in h)this[f]!==h[f]&&(this[f]=g[f]=h[f]);return g},css:function(a){var b=this.element,c=a&&a.width&&b.nodeName.toLowerCase()==="text",
d,e="",f=function(a,b){return"-"+b.toLowerCase()};if(a&&a.color)a.fill=a.color;this.styles=a=s(this.styles,a);$&&c&&delete a.width;if(ra&&!Z)c&&delete a.width,L(this.element,a);else{for(d in a)e+=d.replace(/([A-Z])/g,f)+":"+a[d]+";";w(b,"style",e)}c&&this.added&&this.renderer.buildText(this);return this},on:function(a,b){var c=this.element;if(hb&&a==="click")c.ontouchstart=function(a){a.preventDefault();b.call(c,a)};c["on"+a]=b;return this},setRadialReference:function(a){this.element.radialReference=
a;return this},translate:function(a,b){return this.attr({translateX:a,translateY:b})},invert:function(){this.inverted=!0;this.updateTransform();return this},htmlCss:function(a){var b=this.element;if(b=a&&b.tagName==="SPAN"&&a.width)delete a.width,this.textWidth=b,this.updateTransform();this.styles=s(this.styles,a);L(this.element,a);return this},htmlGetBBox:function(){var a=this.element,b=this.bBox;if(!b){if(a.nodeName==="text")a.style.position="absolute";b=this.bBox={x:a.offsetLeft,y:a.offsetTop,
width:a.offsetWidth,height:a.offsetHeight}}return b},htmlUpdateTransform:function(){if(this.added){var a=this.renderer,b=this.element,c=this.translateX||0,d=this.translateY||0,e=this.x||0,f=this.y||0,g=this.textAlign||"left",h={left:0,center:0.5,right:1}[g],i=g&&g!=="left",j=this.shadows;L(b,{marginLeft:c,marginTop:d});j&&n(j,function(a){L(a,{marginLeft:c+1,marginTop:d+1})});this.inverted&&n(b.childNodes,function(c){a.invertChild(c,b)});if(b.tagName==="SPAN"){var k,l,j=this.rotation,m;k=0;var o=1,
q=0,la;m=A(this.textWidth);var y=this.xCorr||0,V=this.yCorr||0,r=[j,g,b.innerHTML,this.textWidth].join(",");if(r!==this.cTT){t(j)&&(k=j*Ua,o=W(k),q=ca(k),this.setSpanRotation(j,q,o));k=p(this.elemWidth,b.offsetWidth);l=p(this.elemHeight,b.offsetHeight);if(k>m&&/[ \-]/.test(b.textContent||b.innerText))L(b,{width:m+"px",display:"block",whiteSpace:"normal"}),k=m;m=a.fontMetrics(b.style.fontSize).b;y=o<0&&-k;V=q<0&&-l;la=o*q<0;y+=q*m*(la?1-h:h);V-=o*m*(j?la?h:1-h:1);i&&(y-=k*h*(o<0?-1:1),j&&(V-=l*h*(q<
0?-1:1)),L(b,{textAlign:g}));this.xCorr=y;this.yCorr=V}L(b,{left:e+y+"px",top:f+V+"px"});if(fb)l=b.offsetHeight;this.cTT=r}}else this.alignOnAdd=!0},setSpanRotation:function(a){var b={};b[ra?"-ms-transform":fb?"-webkit-transform":gb?"MozTransform":Nb?"-o-transform":""]=b.transform="rotate("+a+"deg)";L(this.element,b)},updateTransform:function(){var a=this.translateX||0,b=this.translateY||0,c=this.scaleX,d=this.scaleY,e=this.inverted,f=this.rotation;e&&(a+=this.attr("width"),b+=this.attr("height"));
a=["translate("+a+","+b+")"];e?a.push("rotate(90) scale(-1,1)"):f&&a.push("rotate("+f+" "+(this.x||0)+" "+(this.y||0)+")");(t(c)||t(d))&&a.push("scale("+p(c,1)+" "+p(d,1)+")");a.length&&w(this.element,"transform",a.join(" "))},toFront:function(){var a=this.element;a.parentNode.appendChild(a);return this},align:function(a,b,c){var d,e,f,g,h={};e=this.renderer;f=e.alignedObjects;if(a){if(this.alignOptions=a,this.alignByTranslate=b,!c||ea(c))this.alignTo=d=c||"renderer",ga(f,this),f.push(this),c=null}else a=
this.alignOptions,b=this.alignByTranslate,d=this.alignTo;c=p(c,e[d],e);d=a.align;e=a.verticalAlign;f=(c.x||0)+(a.x||0);g=(c.y||0)+(a.y||0);if(d==="right"||d==="center")f+=(c.width-(a.width||0))/{right:1,center:2}[d];h[b?"translateX":"x"]=u(f);if(e==="bottom"||e==="middle")g+=(c.height-(a.height||0))/({bottom:1,middle:2}[e]||1);h[b?"translateY":"y"]=u(g);this[this.placed?"animate":"attr"](h);this.placed=!0;this.alignAttr=h;return this},getBBox:function(){var a=this.bBox,b=this.renderer,c,d=this.rotation;
c=this.element;var e=this.styles,f=d*Ua;if(!a){if(c.namespaceURI===xa||b.forExport){try{a=c.getBBox?s({},c.getBBox()):{width:c.offsetWidth,height:c.offsetHeight}}catch(g){}if(!a||a.width<0)a={width:0,height:0}}else a=this.htmlGetBBox();if(b.isSVG){b=a.width;c=a.height;if(ra&&e&&e.fontSize==="11px"&&c.toPrecision(3)==="22.7")a.height=c=14;if(d)a.width=O(c*ca(f))+O(b*W(f)),a.height=O(c*W(f))+O(b*ca(f))}this.bBox=a}return a},show:function(){return this.attr({visibility:"visible"})},hide:function(){return this.attr({visibility:"hidden"})},
fadeOut:function(a){var b=this;b.animate({opacity:0},{duration:a||150,complete:function(){b.hide()}})},add:function(a){var b=this.renderer,c=a||b,d=c.element||b.box,e=d.childNodes,f=this.element,g=w(f,"zIndex"),h;if(a)this.parentGroup=a;this.parentInverted=a&&a.inverted;this.textStr!==void 0&&b.buildText(this);if(g)c.handleZ=!0,g=A(g);if(c.handleZ)for(c=0;c<e.length;c++)if(a=e[c],b=w(a,"zIndex"),a!==f&&(A(b)>g||!t(g)&&t(b))){d.insertBefore(f,a);h=!0;break}h||d.appendChild(f);this.added=!0;K(this,
"add");return this},safeRemoveChild:function(a){var b=a.parentNode;b&&b.removeChild(a)},destroy:function(){var a=this,b=a.element||{},c=a.shadows,d=a.renderer.isSVG&&b.nodeName==="SPAN"&&b.parentNode,e,f;b.onclick=b.onmouseout=b.onmouseover=b.onmousemove=b.point=null;Wa(a);if(a.clipPath)a.clipPath=a.clipPath.destroy();if(a.stops){for(f=0;f<a.stops.length;f++)a.stops[f]=a.stops[f].destroy();a.stops=null}a.safeRemoveChild(b);for(c&&n(c,function(b){a.safeRemoveChild(b)});d&&d.childNodes.length===0;)b=
d.parentNode,a.safeRemoveChild(d),d=b;a.alignTo&&ga(a.renderer.alignedObjects,a);for(e in a)delete a[e];return null},shadow:function(a,b,c){var d=[],e,f,g=this.element,h,i,j,k;if(a){i=p(a.width,3);j=(a.opacity||0.15)/i;k=this.parentInverted?"(-1,-1)":"("+p(a.offsetX,1)+", "+p(a.offsetY,1)+")";for(e=1;e<=i;e++){f=g.cloneNode(0);h=i*2+1-2*e;w(f,{isShadow:"true",stroke:a.color||"black","stroke-opacity":j*e,"stroke-width":h,transform:"translate"+k,fill:S});if(c)w(f,"height",r(w(f,"height")-h,0)),f.cutHeight=
h;b?b.element.appendChild(f):g.parentNode.insertBefore(f,g);d.push(f)}this.shadows=d}return this}};var Ga=function(){this.init.apply(this,arguments)};Ga.prototype={Element:va,init:function(a,b,c,d){var e=location,f,g;f=this.createElement("svg").attr({version:"1.1"});g=f.element;a.appendChild(g);a.innerHTML.indexOf("xmlns")===-1&&w(g,"xmlns",xa);this.isSVG=!0;this.box=g;this.boxWrapper=f;this.alignedObjects=[];this.url=(gb||fb)&&z.getElementsByTagName("base").length?e.href.replace(/#.*?$/,"").replace(/([\('\)])/g,
"\\$1").replace(/ /g,"%20"):"";this.createElement("desc").add().element.appendChild(z.createTextNode("Created with Highcharts 3.0.5"));this.defs=this.createElement("defs").add();this.forExport=d;this.gradients={};this.setSize(b,c,!1);var h;if(gb&&a.getBoundingClientRect)this.subPixelFix=b=function(){L(a,{left:0,top:0});h=a.getBoundingClientRect();L(a,{left:wa(h.left)-h.left+"px",top:wa(h.top)-h.top+"px"})},b(),J(N,"resize",b)},isHidden:function(){return!this.boxWrapper.getBBox().width},destroy:function(){var a=
this.defs;this.box=null;this.boxWrapper=this.boxWrapper.destroy();Ja(this.gradients||{});this.gradients=null;if(a)this.defs=a.destroy();this.subPixelFix&&ba(N,"resize",this.subPixelFix);return this.alignedObjects=null},createElement:function(a){var b=new this.Element;b.init(this,a);return b},draw:function(){},buildText:function(a){for(var b=a.element,c=this,d=c.forExport,e=p(a.textStr,"").toString().replace(/<(b|strong)>/g,'<span style="font-weight:bold">').replace(/<(i|em)>/g,'<span style="font-style:italic">').replace(/<a/g,
"<span").replace(/<\/(b|strong|i|em|a)>/g,"</span>").split(/<br.*?>/g),f=b.childNodes,g=/style="([^"]+)"/,h=/href="(http[^"]+)"/,i=w(b,"x"),j=a.styles,k=j&&j.width&&A(j.width),l=j&&j.lineHeight,m=f.length;m--;)b.removeChild(f[m]);k&&!a.added&&this.box.appendChild(b);e[e.length-1]===""&&e.pop();n(e,function(e,f){var m,p=0,e=e.replace(/<span/g,"|||<span").replace(/<\/span>/g,"</span>|||");m=e.split("|||");n(m,function(e){if(e!==""||m.length===1){var o={},n=z.createElementNS(xa,"tspan"),r;g.test(e)&&
(r=e.match(g)[1].replace(/(;| |^)color([ :])/,"$1fill$2"),w(n,"style",r));h.test(e)&&!d&&(w(n,"onclick",'location.href="'+e.match(h)[1]+'"'),L(n,{cursor:"pointer"}));e=(e.replace(/<(.|\n)*?>/g,"")||" ").replace(/&lt;/g,"<").replace(/&gt;/g,">");if(e!==" "&&(n.appendChild(z.createTextNode(e)),p?o.dx=0:o.x=i,w(n,o),!p&&f&&(!Z&&d&&L(n,{display:"block"}),w(n,"dy",l||c.fontMetrics(/px$/.test(n.style.fontSize)?n.style.fontSize:j.fontSize).h,fb&&n.offsetHeight)),b.appendChild(n),p++,k))for(var e=e.replace(/([^\^])-/g,
"$1- ").split(" "),t,u=[];e.length||u.length;)delete a.bBox,t=a.getBBox().width,o=t>k,!o||e.length===1?(e=u,u=[],e.length&&(n=z.createElementNS(xa,"tspan"),w(n,{dy:l||16,x:i}),r&&w(n,"style",r),b.appendChild(n),t>k&&(k=t))):(n.removeChild(n.firstChild),u.unshift(e.pop())),e.length&&n.appendChild(z.createTextNode(e.join(" ").replace(/- /g,"-")))}})})},button:function(a,b,c,d,e,f,g,h){var i=this.label(a,b,c,null,null,null,null,null,"button"),j=0,k,l,m,o,q,n,a={x1:0,y1:0,x2:0,y2:1},e=x({"stroke-width":1,
stroke:"#CCCCCC",fill:{linearGradient:a,stops:[[0,"#FEFEFE"],[1,"#F6F6F6"]]},r:2,padding:5,style:{color:"black"}},e);m=e.style;delete e.style;f=x(e,{stroke:"#68A",fill:{linearGradient:a,stops:[[0,"#FFF"],[1,"#ACF"]]}},f);o=f.style;delete f.style;g=x(e,{stroke:"#68A",fill:{linearGradient:a,stops:[[0,"#9BD"],[1,"#CDF"]]}},g);q=g.style;delete g.style;h=x(e,{style:{color:"#CCC"}},h);n=h.style;delete h.style;J(i.element,ra?"mouseover":"mouseenter",function(){j!==3&&i.attr(f).css(o)});J(i.element,ra?"mouseout":
"mouseleave",function(){j!==3&&(k=[e,f,g][j],l=[m,o,q][j],i.attr(k).css(l))});i.setState=function(a){(i.state=j=a)?a===2?i.attr(g).css(q):a===3&&i.attr(h).css(n):i.attr(e).css(m)};return i.on("click",function(){j!==3&&d.call(i)}).attr(e).css(s({cursor:"default"},m))},crispLine:function(a,b){a[1]===a[4]&&(a[1]=a[4]=u(a[1])-b%2/2);a[2]===a[5]&&(a[2]=a[5]=u(a[2])+b%2/2);return a},path:function(a){var b={fill:S};Ha(a)?b.d=a:T(a)&&s(b,a);return this.createElement("path").attr(b)},circle:function(a,b,c){a=
T(a)?a:{x:a,y:b,r:c};return this.createElement("circle").attr(a)},arc:function(a,b,c,d,e,f){if(T(a))b=a.y,c=a.r,d=a.innerR,e=a.start,f=a.end,a=a.x;a=this.symbol("arc",a||0,b||0,c||0,c||0,{innerR:d||0,start:e||0,end:f||0});a.r=c;return a},rect:function(a,b,c,d,e,f){e=T(a)?a.r:e;e=this.createElement("rect").attr({rx:e,ry:e,fill:S});return e.attr(T(a)?a:e.crisp(f,a,b,r(c,0),r(d,0)))},setSize:function(a,b,c){var d=this.alignedObjects,e=d.length;this.width=a;this.height=b;for(this.boxWrapper[p(c,!0)?"animate":
"attr"]({width:a,height:b});e--;)d[e].align()},g:function(a){var b=this.createElement("g");return t(a)?b.attr({"class":"highcharts-"+a}):b},image:function(a,b,c,d,e){var f={preserveAspectRatio:S};arguments.length>1&&s(f,{x:b,y:c,width:d,height:e});f=this.createElement("image").attr(f);f.element.setAttributeNS?f.element.setAttributeNS("http://www.w3.org/1999/xlink","href",a):f.element.setAttribute("hc-svg-href",a);return f},symbol:function(a,b,c,d,e,f){var g,h=this.symbols[a],h=h&&h(u(b),u(c),d,e,
f),i=/^url\((.*?)\)$/,j,k;if(h)g=this.path(h),s(g,{symbolName:a,x:b,y:c,width:d,height:e}),f&&s(g,f);else if(i.test(a))k=function(a,b){a.element&&(a.attr({width:b[0],height:b[1]}),a.alignByTranslate||a.translate(u((d-b[0])/2),u((e-b[1])/2)))},j=a.match(i)[1],a=Pb[j],g=this.image(j).attr({x:b,y:c}),g.isImg=!0,a?k(g,a):(g.attr({width:0,height:0}),U("img",{onload:function(){k(g,Pb[j]=[this.width,this.height])},src:j}));return g},symbols:{circle:function(a,b,c,d){var e=0.166*c;return["M",a+c/2,b,"C",
a+c+e,b,a+c+e,b+d,a+c/2,b+d,"C",a-e,b+d,a-e,b,a+c/2,b,"Z"]},square:function(a,b,c,d){return["M",a,b,"L",a+c,b,a+c,b+d,a,b+d,"Z"]},triangle:function(a,b,c,d){return["M",a+c/2,b,"L",a+c,b+d,a,b+d,"Z"]},"triangle-down":function(a,b,c,d){return["M",a,b,"L",a+c,b,a+c/2,b+d,"Z"]},diamond:function(a,b,c,d){return["M",a+c/2,b,"L",a+c,b+d/2,a+c/2,b+d,a,b+d/2,"Z"]},arc:function(a,b,c,d,e){var f=e.start,c=e.r||c||d,g=e.end-0.001,d=e.innerR,h=e.open,i=W(f),j=ca(f),k=W(g),g=ca(g),e=e.end-f<Ma?0:1;return["M",a+
c*i,b+c*j,"A",c,c,0,e,1,a+c*k,b+c*g,h?"M":"L",a+d*k,b+d*g,"A",d,d,0,e,0,a+d*i,b+d*j,h?"":"Z"]}},clipRect:function(a,b,c,d){var e="highcharts-"+yb++,f=this.createElement("clipPath").attr({id:e}).add(this.defs),a=this.rect(a,b,c,d,0).add(f);a.id=e;a.clipPath=f;return a},color:function(a,b,c){var d=this,e,f=/^rgba/,g,h,i,j,k,l,m,o=[];a&&a.linearGradient?g="linearGradient":a&&a.radialGradient&&(g="radialGradient");if(g){c=a[g];h=d.gradients;j=a.stops;b=b.radialReference;Ha(c)&&(a[g]=c={x1:c[0],y1:c[1],
x2:c[2],y2:c[3],gradientUnits:"userSpaceOnUse"});g==="radialGradient"&&b&&!t(c.gradientUnits)&&(c=x(c,{cx:b[0]-b[2]/2+c.cx*b[2],cy:b[1]-b[2]/2+c.cy*b[2],r:c.r*b[2],gradientUnits:"userSpaceOnUse"}));for(m in c)m!=="id"&&o.push(m,c[m]);for(m in j)o.push(j[m]);o=o.join(",");h[o]?a=h[o].id:(c.id=a="highcharts-"+yb++,h[o]=i=d.createElement(g).attr(c).add(d.defs),i.stops=[],n(j,function(a){f.test(a[1])?(e=pa(a[1]),k=e.get("rgb"),l=e.get("a")):(k=a[1],l=1);a=d.createElement("stop").attr({offset:a[0],"stop-color":k,
"stop-opacity":l}).add(i);i.stops.push(a)}));return"url("+d.url+"#"+a+")"}else return f.test(a)?(e=pa(a),w(b,c+"-opacity",e.get("a")),e.get("rgb")):(b.removeAttribute(c+"-opacity"),a)},text:function(a,b,c,d){var e=M.chart.style,f=$||!Z&&this.forExport;if(d&&!this.forExport)return this.html(a,b,c);b=u(p(b,0));c=u(p(c,0));a=this.createElement("text").attr({x:b,y:c,text:a}).css({fontFamily:e.fontFamily,fontSize:e.fontSize});f&&a.css({position:"absolute"});a.x=b;a.y=c;return a},html:function(a,b,c){var d=
M.chart.style,e=this.createElement("span"),f=e.attrSetters,g=e.element,h=e.renderer;f.text=function(a){a!==g.innerHTML&&delete this.bBox;g.innerHTML=a;return!1};f.x=f.y=f.align=function(a,b){b==="align"&&(b="textAlign");e[b]=a;e.htmlUpdateTransform();return!1};e.attr({text:a,x:u(b),y:u(c)}).css({position:"absolute",whiteSpace:"nowrap",fontFamily:d.fontFamily,fontSize:d.fontSize});e.css=e.htmlCss;if(h.isSVG)e.add=function(a){var b,c=h.box.parentNode,d=[];if(a){if(b=a.div,!b){for(;a;)d.push(a),a=a.parentGroup;
n(d.reverse(),function(a){var d;b=a.div=a.div||U(Ca,{className:w(a.element,"class")},{position:"absolute",left:(a.translateX||0)+"px",top:(a.translateY||0)+"px"},b||c);d=b.style;s(a.attrSetters,{translateX:function(a){d.left=a+"px"},translateY:function(a){d.top=a+"px"},visibility:function(a,b){d[b]=a}})})}}else b=c;b.appendChild(g);e.added=!0;e.alignOnAdd&&e.htmlUpdateTransform();return e};return e},fontMetrics:function(a){var a=A(a||11),a=a<24?a+4:u(a*1.2),b=u(a*0.8);return{h:a,b:b}},label:function(a,
b,c,d,e,f,g,h,i){function j(){var a,b;a=p.element.style;V=(Oa===void 0||E===void 0||q.styles.textAlign)&&p.getBBox();q.width=(Oa||V.width||0)+2*da+jb;q.height=(E||V.height||0)+2*da;w=da+o.fontMetrics(a&&a.fontSize).b;if(A){if(!y)a=u(-r*da),b=h?-w:0,q.box=y=d?o.symbol(d,a,b,q.width,q.height):o.rect(a,b,q.width,q.height,0,kb[Rb]),y.add(q);y.isImg||y.attr(x({width:q.width,height:q.height},kb));kb=null}}function k(){var a=q.styles,a=a&&a.textAlign,b=jb+da*(1-r),c;c=h?0:w;if(t(Oa)&&(a==="center"||a===
"right"))b+={center:0.5,right:1}[a]*(Oa-V.width);(b!==p.x||c!==p.y)&&p.attr({x:b,y:c});p.x=b;p.y=c}function l(a,b){y?y.attr(a,b):kb[a]=b}function m(){p.add(q);q.attr({text:a,x:b,y:c});y&&t(e)&&q.attr({anchorX:e,anchorY:f})}var o=this,q=o.g(i),p=o.text("",0,0,g).attr({zIndex:1}),y,V,r=0,da=3,jb=0,Oa,E,G,I,B=0,kb={},w,g=q.attrSetters,A;J(q,"add",m);g.width=function(a){Oa=a;return!1};g.height=function(a){E=a;return!1};g.padding=function(a){t(a)&&a!==da&&(da=a,k());return!1};g.paddingLeft=function(a){t(a)&&
a!==jb&&(jb=a,k());return!1};g.align=function(a){r={left:0,center:0.5,right:1}[a];return!1};g.text=function(a,b){p.attr(b,a);j();k();return!1};g[Rb]=function(a,b){A=!0;B=a%2/2;l(b,a);return!1};g.stroke=g.fill=g.r=function(a,b){b==="fill"&&(A=!0);l(b,a);return!1};g.anchorX=function(a,b){e=a;l(b,a+B-G);return!1};g.anchorY=function(a,b){f=a;l(b,a-I);return!1};g.x=function(a){q.x=a;a-=r*((Oa||V.width)+da);G=u(a);q.attr("translateX",G);return!1};g.y=function(a){I=q.y=u(a);q.attr("translateY",I);return!1};
var z=q.css;return s(q,{css:function(a){if(a){var b={},a=x(a);n("fontSize,fontWeight,fontFamily,color,lineHeight,width,textDecoration,textShadow".split(","),function(c){a[c]!==v&&(b[c]=a[c],delete a[c])});p.css(b)}return z.call(q,a)},getBBox:function(){return{width:V.width+2*da,height:V.height+2*da,x:V.x-da,y:V.y-da}},shadow:function(a){y&&y.shadow(a);return q},destroy:function(){ba(q,"add",m);ba(q.element,"mouseenter");ba(q.element,"mouseleave");p&&(p=p.destroy());y&&(y=y.destroy());va.prototype.destroy.call(q);
q=o=j=k=l=m=null}})}};Va=Ga;var D;if(!Z&&!$){Highcharts.VMLElement=D={init:function(a,b){var c=["<",b,' filled="f" stroked="f"'],d=["position: ","absolute",";"],e=b===Ca;(b==="shape"||e)&&d.push("left:0;top:0;width:1px;height:1px;");d.push("visibility: ",e?"hidden":"visible");c.push(' style="',d.join(""),'"/>');if(b)c=e||b==="span"||b==="img"?c.join(""):a.prepVML(c),this.element=U(c);this.renderer=a;this.attrSetters={}},add:function(a){var b=this.renderer,c=this.element,d=b.box,d=a?a.element||a:d;
a&&a.inverted&&b.invertChild(c,d);d.appendChild(c);this.added=!0;this.alignOnAdd&&!this.deferUpdateTransform&&this.updateTransform();K(this,"add");return this},updateTransform:va.prototype.htmlUpdateTransform,setSpanRotation:function(a,b,c){L(this.element,{filter:a?["progid:DXImageTransform.Microsoft.Matrix(M11=",c,", M12=",-b,", M21=",b,", M22=",c,", sizingMethod='auto expand')"].join(""):S})},pathToVML:function(a){for(var b=a.length,c=[],d;b--;)if(qa(a[b]))c[b]=u(a[b]*10)-5;else if(a[b]==="Z")c[b]=
"x";else if(c[b]=a[b],a.isArc&&(a[b]==="wa"||a[b]==="at"))d=a[b]==="wa"?1:-1,c[b+5]===c[b+7]&&(c[b+7]-=d),c[b+6]===c[b+8]&&(c[b+8]-=d);return c.join(" ")||"x"},attr:function(a,b){var c,d,e,f=this.element||{},g=f.style,h=f.nodeName,i=this.renderer,j=this.symbolName,k,l=this.shadows,m,o=this.attrSetters,q=this;ea(a)&&t(b)&&(c=a,a={},a[c]=b);if(ea(a))c=a,q=c==="strokeWidth"||c==="stroke-width"?this.strokeweight:this[c];else for(c in a)if(d=a[c],m=!1,e=o[c]&&o[c].call(this,d,c),e!==!1&&d!==null){e!==
v&&(d=e);if(j&&/^(x|y|r|start|end|width|height|innerR|anchorX|anchorY)/.test(c))k||(this.symbolAttr(a),k=!0),m=!0;else if(c==="d"){d=d||[];this.d=d.join(" ");f.path=d=this.pathToVML(d);if(l)for(e=l.length;e--;)l[e].path=l[e].cutOff?this.cutOffPath(d,l[e].cutOff):d;m=!0}else if(c==="visibility"){if(l)for(e=l.length;e--;)l[e].style[c]=d;h==="DIV"&&(d=d==="hidden"?"-999em":0,eb||(g[c]=d?"visible":"hidden"),c="top");g[c]=d;m=!0}else if(c==="zIndex")d&&(g[c]=d),m=!0;else if(oa(c,["x","y","width","height"])!==
-1)this[c]=d,c==="x"||c==="y"?c={x:"left",y:"top"}[c]:d=r(0,d),this.updateClipping?(this[c]=d,this.updateClipping()):g[c]=d,m=!0;else if(c==="class"&&h==="DIV")f.className=d;else if(c==="stroke")d=i.color(d,f,c),c="strokecolor";else if(c==="stroke-width"||c==="strokeWidth")f.stroked=d?!0:!1,c="strokeweight",this[c]=d,qa(d)&&(d+="px");else if(c==="dashstyle")(f.getElementsByTagName("stroke")[0]||U(i.prepVML(["<stroke/>"]),null,null,f))[c]=d||"solid",this.dashstyle=d,m=!0;else if(c==="fill")if(h===
"SPAN")g.color=d;else{if(h!=="IMG")f.filled=d!==S?!0:!1,d=i.color(d,f,c,this),c="fillcolor"}else if(c==="opacity")m=!0;else if(h==="shape"&&c==="rotation")this[c]=f.style[c]=d,f.style.left=-u(ca(d*Ua)+1)+"px",f.style.top=u(W(d*Ua))+"px";else if(c==="translateX"||c==="translateY"||c==="rotation")this[c]=d,this.updateTransform(),m=!0;else if(c==="text")this.bBox=null,f.innerHTML=d,m=!0;m||(eb?f[c]=d:w(f,c,d))}return q},clip:function(a){var b=this,c;a?(c=a.members,ga(c,b),c.push(b),b.destroyClip=function(){ga(c,
b)},a=a.getCSS(b)):(b.destroyClip&&b.destroyClip(),a={clip:eb?"inherit":"rect(auto)"});return b.css(a)},css:va.prototype.htmlCss,safeRemoveChild:function(a){a.parentNode&&Ta(a)},destroy:function(){this.destroyClip&&this.destroyClip();return va.prototype.destroy.apply(this)},on:function(a,b){this.element["on"+a]=function(){var a=N.event;a.target=a.srcElement;b(a)};return this},cutOffPath:function(a,b){var c,a=a.split(/[ ,]/);c=a.length;if(c===9||c===11)a[c-4]=a[c-2]=A(a[c-2])-10*b;return a.join(" ")},
shadow:function(a,b,c){var d=[],e,f=this.element,g=this.renderer,h,i=f.style,j,k=f.path,l,m,o,q;k&&typeof k.value!=="string"&&(k="x");m=k;if(a){o=p(a.width,3);q=(a.opacity||0.15)/o;for(e=1;e<=3;e++){l=o*2+1-2*e;c&&(m=this.cutOffPath(k.value,l+0.5));j=['<shape isShadow="true" strokeweight="',l,'" filled="false" path="',m,'" coordsize="10 10" style="',f.style.cssText,'" />'];h=U(g.prepVML(j),null,{left:A(i.left)+p(a.offsetX,1),top:A(i.top)+p(a.offsetY,1)});if(c)h.cutOff=l+1;j=['<stroke color="',a.color||
"black",'" opacity="',q*e,'"/>'];U(g.prepVML(j),null,null,h);b?b.element.appendChild(h):f.parentNode.insertBefore(h,f);d.push(h)}this.shadows=d}return this}};D=ha(va,D);var ma={Element:D,isIE8:Ea.indexOf("MSIE 8.0")>-1,init:function(a,b,c){var d,e;this.alignedObjects=[];d=this.createElement(Ca);e=d.element;e.style.position="relative";a.appendChild(d.element);this.isVML=!0;this.box=e;this.boxWrapper=d;this.setSize(b,c,!1);if(!z.namespaces.hcv)z.namespaces.add("hcv","urn:schemas-microsoft-com:vml"),
z.createStyleSheet().cssText="hcv\\:fill, hcv\\:path, hcv\\:shape, hcv\\:stroke{ behavior:url(#default#VML); display: inline-block; } "},isHidden:function(){return!this.box.offsetWidth},clipRect:function(a,b,c,d){var e=this.createElement(),f=T(a);return s(e,{members:[],left:(f?a.x:a)+1,top:(f?a.y:b)+1,width:(f?a.width:c)-1,height:(f?a.height:d)-1,getCSS:function(a){var b=a.element,c=b.nodeName,a=a.inverted,d=this.top-(c==="shape"?b.offsetTop:0),e=this.left,b=e+this.width,f=d+this.height,d={clip:"rect("+
u(a?e:d)+"px,"+u(a?f:b)+"px,"+u(a?b:f)+"px,"+u(a?d:e)+"px)"};!a&&eb&&c==="DIV"&&s(d,{width:b+"px",height:f+"px"});return d},updateClipping:function(){n(e.members,function(a){a.css(e.getCSS(a))})}})},color:function(a,b,c,d){var e=this,f,g=/^rgba/,h,i,j=S;a&&a.linearGradient?i="gradient":a&&a.radialGradient&&(i="pattern");if(i){var k,l,m=a.linearGradient||a.radialGradient,o,q,p,y,r,t="",a=a.stops,u,v=[],s=function(){h=['<fill colors="'+v.join(",")+'" opacity="',p,'" o:opacity2="',q,'" type="',i,'" ',
t,'focus="100%" method="any" />'];U(e.prepVML(h),null,null,b)};o=a[0];u=a[a.length-1];o[0]>0&&a.unshift([0,o[1]]);u[0]<1&&a.push([1,u[1]]);n(a,function(a,b){g.test(a[1])?(f=pa(a[1]),k=f.get("rgb"),l=f.get("a")):(k=a[1],l=1);v.push(a[0]*100+"% "+k);b?(p=l,y=k):(q=l,r=k)});if(c==="fill")if(i==="gradient")c=m.x1||m[0]||0,a=m.y1||m[1]||0,o=m.x2||m[2]||0,m=m.y2||m[3]||0,t='angle="'+(90-R.atan((m-a)/(o-c))*180/Ma)+'"',s();else{var j=m.r,E=j*2,G=j*2,I=m.cx,B=m.cy,x=b.radialReference,w,j=function(){x&&(w=
d.getBBox(),I+=(x[0]-w.x)/w.width-0.5,B+=(x[1]-w.y)/w.height-0.5,E*=x[2]/w.width,G*=x[2]/w.height);t='src="'+M.global.VMLRadialGradientURL+'" size="'+E+","+G+'" origin="0.5,0.5" position="'+I+","+B+'" color2="'+r+'" ';s()};d.added?j():J(d,"add",j);j=y}else j=k}else if(g.test(a)&&b.tagName!=="IMG")f=pa(a),h=["<",c,' opacity="',f.get("a"),'"/>'],U(this.prepVML(h),null,null,b),j=f.get("rgb");else{j=b.getElementsByTagName(c);if(j.length)j[0].opacity=1,j[0].type="solid";j=a}return j},prepVML:function(a){var b=
this.isIE8,a=a.join("");b?(a=a.replace("/>",' xmlns="urn:schemas-microsoft-com:vml" />'),a=a.indexOf('style="')===-1?a.replace("/>",' style="display:inline-block;behavior:url(#default#VML);" />'):a.replace('style="','style="display:inline-block;behavior:url(#default#VML);')):a=a.replace("<","<hcv:");return a},text:Ga.prototype.html,path:function(a){var b={coordsize:"10 10"};Ha(a)?b.d=a:T(a)&&s(b,a);return this.createElement("shape").attr(b)},circle:function(a,b,c){var d=this.symbol("circle");if(T(a))c=
a.r,b=a.y,a=a.x;d.isCircle=!0;return d.attr({x:a,y:b,width:2*c,height:2*c})},g:function(a){var b;a&&(b={className:"highcharts-"+a,"class":"highcharts-"+a});return this.createElement(Ca).attr(b)},image:function(a,b,c,d,e){var f=this.createElement("img").attr({src:a});arguments.length>1&&f.attr({x:b,y:c,width:d,height:e});return f},rect:function(a,b,c,d,e,f){var g=this.symbol("rect");g.r=T(a)?a.r:e;return g.attr(T(a)?a:g.crisp(f,a,b,r(c,0),r(d,0)))},invertChild:function(a,b){var c=b.style;L(a,{flip:"x",
left:A(c.width)-1,top:A(c.height)-1,rotation:-90})},symbols:{arc:function(a,b,c,d,e){var f=e.start,g=e.end,h=e.r||c||d,c=e.innerR,d=W(f),i=ca(f),j=W(g),k=ca(g);if(g-f===0)return["x"];f=["wa",a-h,b-h,a+h,b+h,a+h*d,b+h*i,a+h*j,b+h*k];e.open&&!c&&f.push("e","M",a,b);f.push("at",a-c,b-c,a+c,b+c,a+c*j,b+c*k,a+c*d,b+c*i,"x","e");f.isArc=!0;return f},circle:function(a,b,c,d,e){e&&e.isCircle&&(a-=c/2,b-=d/2);return["wa",a,b,a+c,b+d,a+c,b+d/2,a+c,b+d/2,"e"]},rect:function(a,b,c,d,e){var f=a+c,g=b+d,h;!t(e)||
!e.r?f=Ga.prototype.symbols.square.apply(0,arguments):(h=C(e.r,c,d),f=["M",a+h,b,"L",f-h,b,"wa",f-2*h,b,f,b+2*h,f-h,b,f,b+h,"L",f,g-h,"wa",f-2*h,g-2*h,f,g,f,g-h,f-h,g,"L",a+h,g,"wa",a,g-2*h,a+2*h,g,a+h,g,a,g-h,"L",a,b+h,"wa",a,b,a+2*h,b+2*h,a,b+h,a+h,b,"x","e"]);return f}}};Highcharts.VMLRenderer=D=function(){this.init.apply(this,arguments)};D.prototype=x(Ga.prototype,ma);Va=D}var Tb;if($)Highcharts.CanVGRenderer=D=function(){xa="http://www.w3.org/1999/xhtml"},D.prototype.symbols={},Tb=function(){function a(){var a=
b.length,d;for(d=0;d<a;d++)b[d]();b=[]}var b=[];return{push:function(c,d){b.length===0&&Vb(d,a);b.push(c)}}}(),Va=D;La.prototype={addLabel:function(){var a=this.axis,b=a.options,c=a.chart,d=a.horiz,e=a.categories,f=a.series[0]&&a.series[0].names,g=this.pos,h=b.labels,i=a.tickPositions,d=d&&e&&!h.step&&!h.staggerLines&&!h.rotation&&c.plotWidth/i.length||!d&&(c.optionsMarginLeft||c.chartWidth*0.33),j=g===i[0],k=g===i[i.length-1],f=e?p(e[g],f&&f[g],g):g,e=this.label,i=i.info,l;a.isDatetimeAxis&&i&&(l=
b.dateTimeLabelFormats[i.higherRanks[g]||i.unitName]);this.isFirst=j;this.isLast=k;b=a.labelFormatter.call({axis:a,chart:c,isFirst:j,isLast:k,dateTimeLabelFormat:l,value:a.isLog?ka(fa(f)):f});g=d&&{width:r(1,u(d-2*(h.padding||10)))+"px"};g=s(g,h.style);if(t(e))e&&e.attr({text:b}).css(g);else{d={align:a.labelAlign};if(qa(h.rotation))d.rotation=h.rotation;this.label=t(b)&&h.enabled?c.renderer.text(b,0,0,h.useHTML).attr(d).css(g).add(a.labelGroup):null}},getLabelSize:function(){var a=this.label,b=this.axis;
return a?(this.labelBBox=a.getBBox())[b.horiz?"height":"width"]:0},getLabelSides:function(){var a=this.axis,b=this.labelBBox.width,a=b*{left:0,center:0.5,right:1}[a.labelAlign]-a.options.labels.x;return[-a,b-a]},handleOverflow:function(a,b){var c=!0,d=this.axis,e=d.chart,f=this.isFirst,g=this.isLast,h=b.x,i=d.reversed,j=d.tickPositions;if(f||g){var k=this.getLabelSides(),l=k[0],k=k[1],e=e.plotLeft,m=e+d.len,j=(d=d.ticks[j[a+(f?1:-1)]])&&d.label.xy&&d.label.xy.x+d.getLabelSides()[f?0:1];f&&!i||g&&
i?h+l<e&&(h=e-l,d&&h+k>j&&(c=!1)):h+k>m&&(h=m-k,d&&h+l<j&&(c=!1));b.x=h}return c},getPosition:function(a,b,c,d){var e=this.axis,f=e.chart,g=d&&f.oldChartHeight||f.chartHeight;return{x:a?e.translate(b+c,null,null,d)+e.transB:e.left+e.offset+(e.opposite?(d&&f.oldChartWidth||f.chartWidth)-e.right-e.left:0),y:a?g-e.bottom+e.offset-(e.opposite?e.height:0):g-e.translate(b+c,null,null,d)-e.transB}},getLabelPosition:function(a,b,c,d,e,f,g,h){var i=this.axis,j=i.transA,k=i.reversed,l=i.staggerLines,m=i.chart.renderer.fontMetrics(e.style.fontSize).b,
o=e.rotation,a=a+e.x-(f&&d?f*j*(k?-1:1):0),b=b+e.y-(f&&!d?f*j*(k?1:-1):0);o&&i.side===2&&(b-=m-m*W(o*Ua));!t(e.y)&&!o&&(b+=m-c.getBBox().height/2);l&&(b+=g/(h||1)%l*(i.labelOffset/l));return{x:a,y:b}},getMarkPath:function(a,b,c,d,e,f){return f.crispLine(["M",a,b,"L",a+(e?0:-c),b+(e?c:0)],d)},render:function(a,b,c){var d=this.axis,e=d.options,f=d.chart.renderer,g=d.horiz,h=this.type,i=this.label,j=this.pos,k=e.labels,l=this.gridLine,m=h?h+"Grid":"grid",o=h?h+"Tick":"tick",q=e[m+"LineWidth"],n=e[m+
"LineColor"],y=e[m+"LineDashStyle"],r=e[o+"Length"],m=e[o+"Width"]||0,t=e[o+"Color"],u=e[o+"Position"],o=this.mark,s=k.step,w=!0,E=d.tickmarkOffset,G=this.getPosition(g,j,E,b),I=G.x,G=G.y,B=g&&I===d.pos+d.len||!g&&G===d.pos?-1:1,x=d.staggerLines;this.isActive=!0;if(q){j=d.getPlotLinePath(j+E,q*B,b,!0);if(l===v){l={stroke:n,"stroke-width":q};if(y)l.dashstyle=y;if(!h)l.zIndex=1;if(b)l.opacity=0;this.gridLine=l=q?f.path(j).attr(l).add(d.gridGroup):null}if(!b&&l&&j)l[this.isNew?"attr":"animate"]({d:j,
opacity:c})}if(m&&r)u==="inside"&&(r=-r),d.opposite&&(r=-r),b=this.getMarkPath(I,G,r,m*B,g,f),o?o.animate({d:b,opacity:c}):this.mark=f.path(b).attr({stroke:t,"stroke-width":m,opacity:c}).add(d.axisGroup);if(i&&!isNaN(I))i.xy=G=this.getLabelPosition(I,G,i,g,k,E,a,s),this.isFirst&&!this.isLast&&!p(e.showFirstLabel,1)||this.isLast&&!this.isFirst&&!p(e.showLastLabel,1)?w=!1:!x&&g&&k.overflow==="justify"&&!this.handleOverflow(a,G)&&(w=!1),s&&a%s&&(w=!1),w&&!isNaN(G.y)?(G.opacity=c,i[this.isNew?"attr":
"animate"](G),this.isNew=!1):i.attr("y",-9999)},destroy:function(){Ja(this,this.axis)}};tb.prototype={render:function(){var a=this,b=a.axis,c=b.horiz,d=(b.pointRange||0)/2,e=a.options,f=e.label,g=a.label,h=e.width,i=e.to,j=e.from,k=t(j)&&t(i),l=e.value,m=e.dashStyle,o=a.svgElem,q=[],n,y=e.color,u=e.zIndex,s=e.events,v=b.chart.renderer;b.isLog&&(j=na(j),i=na(i),l=na(l));if(h){if(q=b.getPlotLinePath(l,h),d={stroke:y,"stroke-width":h},m)d.dashstyle=m}else if(k){if(j=r(j,b.min-d),i=C(i,b.max+d),q=b.getPlotBandPath(j,
i,e),d={fill:y},e.borderWidth)d.stroke=e.borderColor,d["stroke-width"]=e.borderWidth}else return;if(t(u))d.zIndex=u;if(o)q?o.animate({d:q},null,o.onGetPath):(o.hide(),o.onGetPath=function(){o.show()});else if(q&&q.length&&(a.svgElem=o=v.path(q).attr(d).add(),s))for(n in e=function(b){o.on(b,function(c){s[b].apply(a,[c])})},s)e(n);if(f&&t(f.text)&&q&&q.length&&b.width>0&&b.height>0){f=x({align:c&&k&&"center",x:c?!k&&4:10,verticalAlign:!c&&k&&"middle",y:c?k?16:10:k?6:-4,rotation:c&&!k&&90},f);if(!g)a.label=
g=v.text(f.text,0,0,f.useHTML).attr({align:f.textAlign||f.align,rotation:f.rotation,zIndex:u}).css(f.style).add();b=[q[1],q[4],p(q[6],q[1])];q=[q[2],q[5],p(q[7],q[2])];c=Ia(b);k=Ia(q);g.align(f,!1,{x:c,y:k,width:ua(b)-c,height:ua(q)-k});g.show()}else g&&g.hide();return a},destroy:function(){ga(this.axis.plotLinesAndBands,this);delete this.axis;Ja(this)}};Mb.prototype={destroy:function(){Ja(this,this.axis)},setTotal:function(a){this.cum=this.total=a},addValue:function(a){this.setTotal(ka(this.total+
a))},render:function(a){var b=this.options,c=b.format,c=c?Ba(c,this):b.formatter.call(this);this.label?this.label.attr({text:c,visibility:"hidden"}):this.label=this.axis.chart.renderer.text(c,0,0,b.useHTML).css(b.style).attr({align:this.textAlign,rotation:b.rotation,visibility:"hidden"}).add(a)},cacheExtremes:function(a,b){this.points[a.index]=b},setOffset:function(a,b){var c=this.axis,d=c.chart,e=d.inverted,f=this.isNegative,g=c.translate(this.percent?100:this.total,0,0,0,1),c=c.translate(0),c=O(g-
c),h=d.xAxis[0].translate(this.x)+a,i=d.plotHeight,f={x:e?f?g:g-c:h,y:e?i-h-b:f?i-g-c:i-g,width:e?c:b,height:e?b:c};if(e=this.label)e.align(this.alignOptions,null,f),f=e.alignAttr,e.attr({visibility:this.options.crop===!1||d.isInsidePlot(f.x,f.y)?Z?"inherit":"visible":"hidden"})}};db.prototype={defaultOptions:{dateTimeLabelFormats:{millisecond:"%H:%M:%S.%L",second:"%H:%M:%S",minute:"%H:%M",hour:"%H:%M",day:"%e. %b",week:"%e. %b",month:"%b '%y",year:"%Y"},endOnTick:!1,gridLineColor:"#C0C0C0",labels:F,
lineColor:"#C0D0E0",lineWidth:1,minPadding:0.01,maxPadding:0.01,minorGridLineColor:"#E0E0E0",minorGridLineWidth:1,minorTickColor:"#A0A0A0",minorTickLength:2,minorTickPosition:"outside",startOfWeek:1,startOnTick:!1,tickColor:"#C0D0E0",tickLength:5,tickmarkPlacement:"between",tickPixelInterval:100,tickPosition:"outside",tickWidth:1,title:{align:"middle",style:{color:"#4d759e",fontWeight:"bold"}},type:"linear"},defaultYAxisOptions:{endOnTick:!0,gridLineWidth:1,tickPixelInterval:72,showLastLabel:!0,labels:{x:-8,
y:3},lineWidth:0,maxPadding:0.05,minPadding:0.05,startOnTick:!0,tickWidth:0,title:{rotation:270,text:"Values"},stackLabels:{enabled:!1,formatter:function(){return za(this.total,-1)},style:F.style}},defaultLeftAxisOptions:{labels:{x:-8,y:null},title:{rotation:270}},defaultRightAxisOptions:{labels:{x:8,y:null},title:{rotation:90}},defaultBottomAxisOptions:{labels:{x:0,y:14},title:{rotation:0}},defaultTopAxisOptions:{labels:{x:0,y:-5},title:{rotation:0}},init:function(a,b){var c=b.isX;this.horiz=a.inverted?
!c:c;this.xOrY=(this.isXAxis=c)?"x":"y";this.opposite=b.opposite;this.side=this.horiz?this.opposite?0:2:this.opposite?1:3;this.setOptions(b);var d=this.options,e=d.type;this.labelFormatter=d.labels.formatter||this.defaultLabelFormatter;this.userOptions=b;this.minPixelPadding=0;this.chart=a;this.reversed=d.reversed;this.zoomEnabled=d.zoomEnabled!==!1;this.categories=d.categories||e==="category";this.isLog=e==="logarithmic";this.isDatetimeAxis=e==="datetime";this.isLinked=t(d.linkedTo);this.tickmarkOffset=
this.categories&&d.tickmarkPlacement==="between"?0.5:0;this.ticks={};this.minorTicks={};this.plotLinesAndBands=[];this.alternateBands={};this.len=0;this.minRange=this.userMinRange=d.minRange||d.maxZoom;this.range=d.range;this.offset=d.offset||0;this.stacks={};this.oldStacks={};this.stackExtremes={};this.min=this.max=null;var f,d=this.options.events;oa(this,a.axes)===-1&&(a.axes.push(this),a[c?"xAxis":"yAxis"].push(this));this.series=this.series||[];if(a.inverted&&c&&this.reversed===v)this.reversed=
!0;this.removePlotLine=this.removePlotBand=this.removePlotBandOrLine;for(f in d)J(this,f,d[f]);if(this.isLog)this.val2lin=na,this.lin2val=fa},setOptions:function(a){this.options=x(this.defaultOptions,this.isXAxis?{}:this.defaultYAxisOptions,[this.defaultTopAxisOptions,this.defaultRightAxisOptions,this.defaultBottomAxisOptions,this.defaultLeftAxisOptions][this.side],x(M[this.isXAxis?"xAxis":"yAxis"],a))},update:function(a,b){var c=this.chart,a=c.options[this.xOrY+"Axis"][this.options.index]=x(this.userOptions,
a);this.destroy(!0);this._addedPlotLB=!1;this.init(c,s(a,{events:v}));c.isDirtyBox=!0;p(b,!0)&&c.redraw()},remove:function(a){var b=this.chart,c=this.xOrY+"Axis";n(this.series,function(a){a.remove(!1)});ga(b.axes,this);ga(b[c],this);b.options[c].splice(this.options.index,1);n(b[c],function(a,b){a.options.index=b});this.destroy();b.isDirtyBox=!0;p(a,!0)&&b.redraw()},defaultLabelFormatter:function(){var a=this.axis,b=this.value,c=a.categories,d=this.dateTimeLabelFormat,e=M.lang.numericSymbols,f=e&&
e.length,g,h=a.options.labels.format,a=a.isLog?b:a.tickInterval;if(h)g=Ba(h,this);else if(c)g=b;else if(d)g=Xa(d,b);else if(f&&a>=1E3)for(;f--&&g===v;)c=Math.pow(1E3,f+1),a>=c&&e[f]!==null&&(g=za(b/c,-1)+e[f]);g===v&&(g=b>=1E3?za(b,0):za(b,-1));return g},getSeriesExtremes:function(){var a=this,b=a.chart;a.hasVisibleSeries=!1;a.dataMin=a.dataMax=null;a.stackExtremes={};a.buildStacks();n(a.series,function(c){if(c.visible||!b.options.chart.ignoreHiddenSeries){var d=c.options,e;e=d.threshold;a.hasVisibleSeries=
!0;a.isLog&&e<=0&&(e=null);if(a.isXAxis){if(e=c.xData,e.length)a.dataMin=C(p(a.dataMin,e[0]),Ia(e)),a.dataMax=r(p(a.dataMax,e[0]),ua(e))}else{d=d.stacking;a.usePercentage=d==="percent";if(a.usePercentage)a.dataMin=0,a.dataMax=99;c.getExtremes();d=c.dataMax;c=c.dataMin;if(!a.usePercentage&&t(c)&&t(d))a.dataMin=C(p(a.dataMin,c),c),a.dataMax=r(p(a.dataMax,d),d);if(t(e))if(a.dataMin>=e)a.dataMin=e,a.ignoreMinPadding=!0;else if(a.dataMax<e)a.dataMax=e,a.ignoreMaxPadding=!0}}})},translate:function(a,b,
c,d,e,f){var g=this.len,h=1,i=0,j=d?this.oldTransA:this.transA,d=d?this.oldMin:this.min,k=this.minPixelPadding,e=(this.options.ordinal||this.isLog&&e)&&this.lin2val;if(!j)j=this.transA;c&&(h*=-1,i=g);this.reversed&&(h*=-1,i-=h*g);b?(a=a*h+i,a-=k,a=a/j+d,e&&(a=this.lin2val(a))):(e&&(a=this.val2lin(a)),f==="between"&&(f=0.5),a=h*(a-d)*j+i+h*k+(qa(f)?j*f*this.pointRange:0));return a},toPixels:function(a,b){return this.translate(a,!1,!this.horiz,null,!0)+(b?0:this.pos)},toValue:function(a,b){return this.translate(a-
(b?0:this.pos),!0,!this.horiz,null,!0)},getPlotLinePath:function(a,b,c,d){var e=this.chart,f=this.left,g=this.top,h,i,j,a=this.translate(a,null,null,c),k=c&&e.oldChartHeight||e.chartHeight,l=c&&e.oldChartWidth||e.chartWidth,m;h=this.transB;c=i=u(a+h);h=j=u(k-a-h);if(isNaN(a))m=!0;else if(this.horiz){if(h=g,j=k-this.bottom,c<f||c>f+this.width)m=!0}else if(c=f,i=l-this.right,h<g||h>g+this.height)m=!0;return m&&!d?null:e.renderer.crispLine(["M",c,h,"L",i,j],b||0)},getPlotBandPath:function(a,b){var c=
this.getPlotLinePath(b),d=this.getPlotLinePath(a);d&&c?d.push(c[4],c[5],c[1],c[2]):d=null;return d},getLinearTickPositions:function(a,b,c){for(var d,b=ka(P(b/a)*a),c=ka(wa(c/a)*a),e=[];b<=c;){e.push(b);b=ka(b+a);if(b===d)break;d=b}return e},getLogTickPositions:function(a,b,c,d){var e=this.options,f=this.len,g=[];if(!d)this._minorAutoInterval=null;if(a>=0.5)a=u(a),g=this.getLinearTickPositions(a,b,c);else if(a>=0.08)for(var f=P(b),h,i,j,k,l,e=a>0.3?[1,2,4]:a>0.15?[1,2,4,6,8]:[1,2,3,4,5,6,7,8,9];f<
c+1&&!l;f++){i=e.length;for(h=0;h<i&&!l;h++)j=na(fa(f)*e[h]),j>b&&(!d||k<=c)&&g.push(k),k>c&&(l=!0),k=j}else if(b=fa(b),c=fa(c),a=e[d?"minorTickInterval":"tickInterval"],a=p(a==="auto"?null:a,this._minorAutoInterval,(c-b)*(e.tickPixelInterval/(d?5:1))/((d?f/this.tickPositions.length:f)||1)),a=mb(a,null,lb(a)),g=Na(this.getLinearTickPositions(a,b,c),na),!d)this._minorAutoInterval=a/5;if(!d)this.tickInterval=a;return g},getMinorTickPositions:function(){var a=this.options,b=this.tickPositions,c=this.minorTickInterval,
d=[],e;if(this.isLog){e=b.length;for(a=1;a<e;a++)d=d.concat(this.getLogTickPositions(c,b[a-1],b[a],!0))}else if(this.isDatetimeAxis&&a.minorTickInterval==="auto")d=d.concat(Eb(Cb(c),this.min,this.max,a.startOfWeek)),d[0]<this.min&&d.shift();else for(b=this.min+(b[0]-this.min)%c;b<=this.max;b+=c)d.push(b);return d},adjustForMinRange:function(){var a=this.options,b=this.min,c=this.max,d,e=this.dataMax-this.dataMin>=this.minRange,f,g,h,i,j;if(this.isXAxis&&this.minRange===v&&!this.isLog)t(a.min)||t(a.max)?
this.minRange=null:(n(this.series,function(a){i=a.xData;for(g=j=a.xIncrement?1:i.length-1;g>0;g--)if(h=i[g]-i[g-1],f===v||h<f)f=h}),this.minRange=C(f*5,this.dataMax-this.dataMin));if(c-b<this.minRange){var k=this.minRange;d=(k-c+b)/2;d=[b-d,p(a.min,b-d)];if(e)d[2]=this.dataMin;b=ua(d);c=[b+k,p(a.max,b+k)];if(e)c[2]=this.dataMax;c=Ia(c);c-b<k&&(d[0]=c-k,d[1]=p(a.min,c-k),b=ua(d))}this.min=b;this.max=c},setAxisTranslation:function(a){var b=this.max-this.min,c=0,d,e=0,f=0,g=this.linkedParent,h=this.transA;
if(this.isXAxis)g?(e=g.minPointOffset,f=g.pointRangePadding):n(this.series,function(a){var g=a.pointRange,h=a.options.pointPlacement,l=a.closestPointRange;g>b&&(g=0);c=r(c,g);e=r(e,ea(h)?0:g/2);f=r(f,h==="on"?0:g);!a.noSharedTooltip&&t(l)&&(d=t(d)?C(d,l):l)}),g=this.ordinalSlope&&d?this.ordinalSlope/d:1,this.minPointOffset=e*=g,this.pointRangePadding=f*=g,this.pointRange=C(c,b),this.closestPointRange=d;if(a)this.oldTransA=h;this.translationSlope=this.transA=h=this.len/(b+f||1);this.transB=this.horiz?
this.left:this.bottom;this.minPixelPadding=h*e},setTickPositions:function(a){var b=this,c=b.chart,d=b.options,e=b.isLog,f=b.isDatetimeAxis,g=b.isXAxis,h=b.isLinked,i=b.options.tickPositioner,j=d.maxPadding,k=d.minPadding,l=d.tickInterval,m=d.minTickInterval,o=d.tickPixelInterval,q=b.categories;h?(b.linkedParent=c[g?"xAxis":"yAxis"][d.linkedTo],c=b.linkedParent.getExtremes(),b.min=p(c.min,c.dataMin),b.max=p(c.max,c.dataMax),d.type!==b.linkedParent.options.type&&ja(11,1)):(b.min=p(b.userMin,d.min,b.dataMin),
b.max=p(b.userMax,d.max,b.dataMax));if(e)!a&&C(b.min,p(b.dataMin,b.min))<=0&&ja(10,1),b.min=ka(na(b.min)),b.max=ka(na(b.max));if(b.range&&(b.userMin=b.min=r(b.min,b.max-b.range),b.userMax=b.max,a))b.range=null;b.beforePadding&&b.beforePadding();b.adjustForMinRange();if(!q&&!b.usePercentage&&!h&&t(b.min)&&t(b.max)&&(c=b.max-b.min)){if(!t(d.min)&&!t(b.userMin)&&k&&(b.dataMin<0||!b.ignoreMinPadding))b.min-=c*k;if(!t(d.max)&&!t(b.userMax)&&j&&(b.dataMax>0||!b.ignoreMaxPadding))b.max+=c*j}b.tickInterval=
b.min===b.max||b.min===void 0||b.max===void 0?1:h&&!l&&o===b.linkedParent.options.tickPixelInterval?b.linkedParent.tickInterval:p(l,q?1:(b.max-b.min)*o/(b.len||1));g&&!a&&n(b.series,function(a){a.processData(b.min!==b.oldMin||b.max!==b.oldMax)});b.setAxisTranslation(!0);b.beforeSetTickPositions&&b.beforeSetTickPositions();if(b.postProcessTickInterval)b.tickInterval=b.postProcessTickInterval(b.tickInterval);if(b.pointRange)b.tickInterval=r(b.pointRange,b.tickInterval);if(!l&&b.tickInterval<m)b.tickInterval=
m;if(!f&&!e&&!l)b.tickInterval=mb(b.tickInterval,null,lb(b.tickInterval),d);b.minorTickInterval=d.minorTickInterval==="auto"&&b.tickInterval?b.tickInterval/5:d.minorTickInterval;b.tickPositions=a=d.tickPositions?[].concat(d.tickPositions):i&&i.apply(b,[b.min,b.max]);if(!a)(b.max-b.min)/b.tickInterval>2*b.len&&ja(19,!0),a=f?(b.getNonLinearTimeTicks||Eb)(Cb(b.tickInterval,d.units),b.min,b.max,d.startOfWeek,b.ordinalPositions,b.closestPointRange,!0):e?b.getLogTickPositions(b.tickInterval,b.min,b.max):
b.getLinearTickPositions(b.tickInterval,b.min,b.max),b.tickPositions=a;if(!h)e=a[0],f=a[a.length-1],h=b.minPointOffset||0,d.startOnTick?b.min=e:b.min-h>e&&a.shift(),d.endOnTick?b.max=f:b.max+h<f&&a.pop(),a.length===1&&(b.min-=0.001,b.max+=0.001)},setMaxTicks:function(){var a=this.chart,b=a.maxTicks||{},c=this.tickPositions,d=this._maxTicksKey=[this.xOrY,this.pos,this.len].join("-");if(!this.isLinked&&!this.isDatetimeAxis&&c&&c.length>(b[d]||0)&&this.options.alignTicks!==!1)b[d]=c.length;a.maxTicks=
b},adjustTickAmount:function(){var a=this._maxTicksKey,b=this.tickPositions,c=this.chart.maxTicks;if(c&&c[a]&&!this.isDatetimeAxis&&!this.categories&&!this.isLinked&&this.options.alignTicks!==!1){var d=this.tickAmount,e=b.length;this.tickAmount=a=c[a];if(e<a){for(;b.length<a;)b.push(ka(b[b.length-1]+this.tickInterval));this.transA*=(e-1)/(a-1);this.max=b[b.length-1]}if(t(d)&&a!==d)this.isDirty=!0}},setScale:function(){var a=this.stacks,b,c,d,e;this.oldMin=this.min;this.oldMax=this.max;this.oldAxisLength=
this.len;this.setAxisSize();e=this.len!==this.oldAxisLength;n(this.series,function(a){if(a.isDirtyData||a.isDirty||a.xAxis.isDirty)d=!0});if(e||d||this.isLinked||this.forceRedraw||this.userMin!==this.oldUserMin||this.userMax!==this.oldUserMax){if(!this.isXAxis)for(b in a)for(c in a[b])a[b][c].total=null;this.forceRedraw=!1;this.getSeriesExtremes();this.setTickPositions();this.oldUserMin=this.userMin;this.oldUserMax=this.userMax;if(!this.isDirty)this.isDirty=e||this.min!==this.oldMin||this.max!==this.oldMax}else if(!this.isXAxis){if(this.oldStacks)a=
this.stacks=this.oldStacks;for(b in a)for(c in a[b])a[b][c].cum=a[b][c].total}this.setMaxTicks()},setExtremes:function(a,b,c,d,e){var f=this,g=f.chart,c=p(c,!0),e=s(e,{min:a,max:b});K(f,"setExtremes",e,function(){f.userMin=a;f.userMax=b;f.isDirtyExtremes=!0;c&&g.redraw(d)})},zoom:function(a,b){this.allowZoomOutside||(t(this.dataMin)&&a<=this.dataMin&&(a=v),t(this.dataMax)&&b>=this.dataMax&&(b=v));this.displayBtn=a!==v||b!==v;this.setExtremes(a,b,!1,v,{trigger:"zoom"});return!0},setAxisSize:function(){var a=
this.chart,b=this.options,c=b.offsetLeft||0,d=b.offsetRight||0,e=this.horiz,f,g;this.left=g=p(b.left,a.plotLeft+c);this.top=f=p(b.top,a.plotTop);this.width=c=p(b.width,a.plotWidth-c+d);this.height=b=p(b.height,a.plotHeight);this.bottom=a.chartHeight-b-f;this.right=a.chartWidth-c-g;this.len=r(e?c:b,0);this.pos=e?g:f},getExtremes:function(){var a=this.isLog;return{min:a?ka(fa(this.min)):this.min,max:a?ka(fa(this.max)):this.max,dataMin:this.dataMin,dataMax:this.dataMax,userMin:this.userMin,userMax:this.userMax}},
getThreshold:function(a){var b=this.isLog,c=b?fa(this.min):this.min,b=b?fa(this.max):this.max;c>a||a===null?a=c:b<a&&(a=b);return this.translate(a,0,1,0,1)},addPlotBand:function(a){this.addPlotBandOrLine(a,"plotBands")},addPlotLine:function(a){this.addPlotBandOrLine(a,"plotLines")},addPlotBandOrLine:function(a,b){var c=(new tb(this,a)).render(),d=this.userOptions;b&&(d[b]=d[b]||[],d[b].push(a));this.plotLinesAndBands.push(c);return c},autoLabelAlign:function(a){a=(p(a,0)-this.side*90+720)%360;return a>
15&&a<165?"right":a>195&&a<345?"left":"center"},getOffset:function(){var a=this,b=a.chart,c=b.renderer,d=a.options,e=a.tickPositions,f=a.ticks,g=a.horiz,h=a.side,i=b.inverted?[1,0,3,2][h]:h,j,k=0,l,m=0,o=d.title,q=d.labels,la=0,y=b.axisOffset,u=b.clipOffset,s=[-1,1,1,-1][h],w,x=1,A=p(q.maxStaggerLines,5),E,G,I,B;a.hasData=j=a.hasVisibleSeries||t(a.min)&&t(a.max)&&!!e;a.showAxis=b=j||p(d.showEmpty,!0);a.staggerLines=a.horiz&&q.staggerLines;if(!a.axisGroup)a.gridGroup=c.g("grid").attr({zIndex:d.gridZIndex||
1}).add(),a.axisGroup=c.g("axis").attr({zIndex:d.zIndex||2}).add(),a.labelGroup=c.g("axis-labels").attr({zIndex:q.zIndex||7}).add();if(j||a.isLinked){a.labelAlign=p(q.align||a.autoLabelAlign(q.rotation));n(e,function(b){f[b]?f[b].addLabel():f[b]=new La(a,b)});if(a.horiz&&!a.staggerLines&&A&&!q.rotation){for(w=a.reversed?[].concat(e).reverse():e;x<A;){j=[];E=!1;for(q=0;q<w.length;q++)G=w[q],I=(I=f[G].label&&f[G].label.bBox)?I.width:0,B=q%x,I&&(G=a.translate(G),j[B]!==v&&G<j[B]&&(E=!0),j[B]=G+I);if(E)x++;
else break}if(x>1)a.staggerLines=x}n(e,function(b){if(h===0||h===2||{1:"left",3:"right"}[h]===a.labelAlign)la=r(f[b].getLabelSize(),la)});if(a.staggerLines)la*=a.staggerLines,a.labelOffset=la}else for(w in f)f[w].destroy(),delete f[w];if(o&&o.text&&o.enabled!==!1){if(!a.axisTitle)a.axisTitle=c.text(o.text,0,0,o.useHTML).attr({zIndex:7,rotation:o.rotation||0,align:o.textAlign||{low:"left",middle:"center",high:"right"}[o.align]}).css(o.style).add(a.axisGroup),a.axisTitle.isNew=!0;if(b)k=a.axisTitle.getBBox()[g?
"height":"width"],m=p(o.margin,g?5:10),l=o.offset;a.axisTitle[b?"show":"hide"]()}a.offset=s*p(d.offset,y[h]);a.axisTitleMargin=p(l,la+m+(h!==2&&la&&s*d.labels[g?"y":"x"]));y[h]=r(y[h],a.axisTitleMargin+k+s*a.offset);u[i]=r(u[i],P(d.lineWidth/2)*2)},getLinePath:function(a){var b=this.chart,c=this.opposite,d=this.offset,e=this.horiz,f=this.left+(c?this.width:0)+d;this.lineTop=d=b.chartHeight-this.bottom-(c?this.height:0)+d;c&&(a*=-1);return b.renderer.crispLine(["M",e?this.left:f,e?d:this.top,"L",e?
b.chartWidth-this.right:f,e?d:b.chartHeight-this.bottom],a)},getTitlePosition:function(){var a=this.horiz,b=this.left,c=this.top,d=this.len,e=this.options.title,f=a?b:c,g=this.opposite,h=this.offset,i=A(e.style.fontSize||12),d={low:f+(a?0:d),middle:f+d/2,high:f+(a?d:0)}[e.align],b=(a?c+this.height:b)+(a?1:-1)*(g?-1:1)*this.axisTitleMargin+(this.side===2?i:0);return{x:a?d:b+(g?this.width:0)+h+(e.x||0),y:a?b-(g?this.height:0)+h:d+(e.y||0)}},render:function(){var a=this,b=a.chart,c=b.renderer,d=a.options,
e=a.isLog,f=a.isLinked,g=a.tickPositions,h=a.axisTitle,i=a.stacks,j=a.ticks,k=a.minorTicks,l=a.alternateBands,m=d.stackLabels,o=d.alternateGridColor,q=a.tickmarkOffset,p=d.lineWidth,y,r=b.hasRendered&&t(a.oldMin)&&!isNaN(a.oldMin);y=a.hasData;var u=a.showAxis,s,w;n([j,k,l],function(a){for(var b in a)a[b].isActive=!1});if(y||f)if(a.minorTickInterval&&!a.categories&&n(a.getMinorTickPositions(),function(b){k[b]||(k[b]=new La(a,b,"minor"));r&&k[b].isNew&&k[b].render(null,!0);k[b].render(null,!1,1)}),
g.length&&(n(g.slice(1).concat([g[0]]),function(b,c){c=c===g.length-1?0:c+1;if(!f||b>=a.min&&b<=a.max)j[b]||(j[b]=new La(a,b)),r&&j[b].isNew&&j[b].render(c,!0),j[b].render(c,!1,1)}),q&&a.min===0&&(j[-1]||(j[-1]=new La(a,-1,null,!0)),j[-1].render(-1))),o&&n(g,function(b,c){if(c%2===0&&b<a.max)l[b]||(l[b]=new tb(a)),s=b+q,w=g[c+1]!==v?g[c+1]+q:a.max,l[b].options={from:e?fa(s):s,to:e?fa(w):w,color:o},l[b].render(),l[b].isActive=!0}),!a._addedPlotLB)n((d.plotLines||[]).concat(d.plotBands||[]),function(b){a.addPlotBandOrLine(b)}),
a._addedPlotLB=!0;n([j,k,l],function(a){var c,d,e=[],f=Da?Da.duration||500:0,g=function(){for(d=e.length;d--;)a[e[d]]&&!a[e[d]].isActive&&(a[e[d]].destroy(),delete a[e[d]])};for(c in a)if(!a[c].isActive)a[c].render(c,!1,0),a[c].isActive=!1,e.push(c);a===l||!b.hasRendered||!f?g():f&&setTimeout(g,f)});if(p)y=a.getLinePath(p),a.axisLine?a.axisLine.animate({d:y}):a.axisLine=c.path(y).attr({stroke:d.lineColor,"stroke-width":p,zIndex:7}).add(a.axisGroup),a.axisLine[u?"show":"hide"]();if(h&&u)h[h.isNew?
"attr":"animate"](a.getTitlePosition()),h.isNew=!1;if(m&&m.enabled){var x,E,d=a.stackTotalGroup;if(!d)a.stackTotalGroup=d=c.g("stack-labels").attr({visibility:"visible",zIndex:6}).add();d.translate(b.plotLeft,b.plotTop);for(x in i)for(E in c=i[x],c)c[E].render(d)}a.isDirty=!1},removePlotBandOrLine:function(a){for(var b=this.plotLinesAndBands,c=this.options,d=this.userOptions,e=b.length;e--;)b[e].id===a&&b[e].destroy();n([c.plotLines||[],d.plotLines||[],c.plotBands||[],d.plotBands||[]],function(b){for(e=
b.length;e--;)b[e].id===a&&ga(b,b[e])})},setTitle:function(a,b){this.update({title:a},b)},redraw:function(){var a=this.chart.pointer;a.reset&&a.reset(!0);this.render();n(this.plotLinesAndBands,function(a){a.render()});n(this.series,function(a){a.isDirty=!0})},buildStacks:function(){this.isXAxis||n(this.series,function(a){a.setStackedPoints()})},setCategories:function(a,b){this.update({categories:a},b)},destroy:function(a){var b=this,c=b.stacks,d,e=b.plotLinesAndBands;a||ba(b);for(d in c)Ja(c[d]),
c[d]=null;n([b.ticks,b.minorTicks,b.alternateBands],function(a){Ja(a)});for(a=e.length;a--;)e[a].destroy();n("stackTotalGroup,axisLine,axisGroup,gridGroup,labelGroup,axisTitle".split(","),function(a){b[a]&&(b[a]=b[a].destroy())})}};ub.prototype={init:function(a,b){var c=b.borderWidth,d=b.style,e=A(d.padding);this.chart=a;this.options=b;this.crosshairs=[];this.now={x:0,y:0};this.isHidden=!0;this.label=a.renderer.label("",0,0,b.shape,null,null,b.useHTML,null,"tooltip").attr({padding:e,fill:b.backgroundColor,
"stroke-width":c,r:b.borderRadius,zIndex:8}).css(d).css({padding:0}).hide().add();$||this.label.shadow(b.shadow);this.shared=b.shared},destroy:function(){n(this.crosshairs,function(a){a&&a.destroy()});if(this.label)this.label=this.label.destroy();clearTimeout(this.hideTimer);clearTimeout(this.tooltipTimeout)},move:function(a,b,c,d){var e=this,f=e.now,g=e.options.animation!==!1&&!e.isHidden;s(f,{x:g?(2*f.x+a)/3:a,y:g?(f.y+b)/2:b,anchorX:g?(2*f.anchorX+c)/3:c,anchorY:g?(f.anchorY+d)/2:d});e.label.attr(f);
if(g&&(O(a-f.x)>1||O(b-f.y)>1))clearTimeout(this.tooltipTimeout),this.tooltipTimeout=setTimeout(function(){e&&e.move(a,b,c,d)},32)},hide:function(){var a=this,b;clearTimeout(this.hideTimer);if(!this.isHidden)b=this.chart.hoverPoints,this.hideTimer=setTimeout(function(){a.label.fadeOut();a.isHidden=!0},p(this.options.hideDelay,500)),b&&n(b,function(a){a.setState()}),this.chart.hoverPoints=null},hideCrosshairs:function(){n(this.crosshairs,function(a){a&&a.hide()})},getAnchor:function(a,b){var c,d=this.chart,
e=d.inverted,f=d.plotTop,g=0,h=0,i,a=ia(a);c=a[0].tooltipPos;this.followPointer&&b&&(b.chartX===v&&(b=d.pointer.normalize(b)),c=[b.chartX-d.plotLeft,b.chartY-f]);c||(n(a,function(a){i=a.series.yAxis;g+=a.plotX;h+=(a.plotLow?(a.plotLow+a.plotHigh)/2:a.plotY)+(!e&&i?i.top-f:0)}),g/=a.length,h/=a.length,c=[e?d.plotWidth-h:g,this.shared&&!e&&a.length>1&&b?b.chartY-f:e?d.plotHeight-g:h]);return Na(c,u)},getPosition:function(a,b,c){var d=this.chart,e=d.plotLeft,f=d.plotTop,g=d.plotWidth,h=d.plotHeight,
i=p(this.options.distance,12),j=c.plotX,c=c.plotY,d=j+e+(d.inverted?i:-a-i),k=c-b+f+15,l;d<7&&(d=e+r(j,0)+i);d+a>e+g&&(d-=d+a-(e+g),k=c-b+f-i,l=!0);k<f+5&&(k=f+5,l&&c>=k&&c<=k+b&&(k=c+f+i));k+b>f+h&&(k=r(f,f+h-b-i));return{x:d,y:k}},defaultFormatter:function(a){var b=this.points||ia(this),c=b[0].series,d;d=[c.tooltipHeaderFormatter(b[0])];n(b,function(a){c=a.series;d.push(c.tooltipFormatter&&c.tooltipFormatter(a)||a.point.tooltipFormatter(c.tooltipOptions.pointFormat))});d.push(a.options.footerFormat||
"");return d.join("")},refresh:function(a,b){var c=this.chart,d=this.label,e=this.options,f,g,h={},i,j=[];i=e.formatter||this.defaultFormatter;var h=c.hoverPoints,k,l=e.crosshairs,m=this.shared;clearTimeout(this.hideTimer);this.followPointer=ia(a)[0].series.tooltipOptions.followPointer;g=this.getAnchor(a,b);f=g[0];g=g[1];m&&(!a.series||!a.series.noSharedTooltip)?(c.hoverPoints=a,h&&n(h,function(a){a.setState()}),n(a,function(a){a.setState("hover");j.push(a.getLabelConfig())}),h={x:a[0].category,y:a[0].y},
h.points=j,a=a[0]):h=a.getLabelConfig();i=i.call(h,this);h=a.series;i===!1?this.hide():(this.isHidden&&(Wa(d),d.attr("opacity",1).show()),d.attr({text:i}),k=e.borderColor||a.color||h.color||"#606060",d.attr({stroke:k}),this.updatePosition({plotX:f,plotY:g}),this.isHidden=!1);if(l){l=ia(l);for(d=l.length;d--;)if(m=a.series,e=m[d?"yAxis":"xAxis"],l[d]&&e)if(h=d?p(a.stackY,a.y):a.x,e.isLog&&(h=na(h)),m.modifyValue&&(h=m.modifyValue(h)),e=e.getPlotLinePath(h,1),this.crosshairs[d])this.crosshairs[d].attr({d:e,
visibility:"visible"});else{h={"stroke-width":l[d].width||1,stroke:l[d].color||"#C0C0C0",zIndex:l[d].zIndex||2};if(l[d].dashStyle)h.dashstyle=l[d].dashStyle;this.crosshairs[d]=c.renderer.path(e).attr(h).add()}}K(c,"tooltipRefresh",{text:i,x:f+c.plotLeft,y:g+c.plotTop,borderColor:k})},updatePosition:function(a){var b=this.chart,c=this.label,c=(this.options.positioner||this.getPosition).call(this,c.width,c.height,a);this.move(u(c.x),u(c.y),a.plotX+b.plotLeft,a.plotY+b.plotTop)}};vb.prototype={init:function(a,
b){var c=$?"":b.chart.zoomType,d=a.inverted,e;this.options=b;this.chart=a;this.zoomX=e=/x/.test(c);this.zoomY=c=/y/.test(c);this.zoomHor=e&&!d||c&&d;this.zoomVert=c&&!d||e&&d;this.pinchDown=[];this.lastValidTouch={};if(b.tooltip.enabled)a.tooltip=new ub(a,b.tooltip);this.setDOMEvents()},normalize:function(a){var b,c,d,a=a||N.event;if(!a.target)a.target=a.srcElement;a=Sb(a);d=a.touches?a.touches.item(0):a;this.chartPosition=b=Wb(this.chart.container);d.pageX===v?(c=r(a.x,a.clientX-b.left),b=a.y):(c=
d.pageX-b.left,b=d.pageY-b.top);return s(a,{chartX:u(c),chartY:u(b)})},getCoordinates:function(a){var b={xAxis:[],yAxis:[]};n(this.chart.axes,function(c){b[c.isXAxis?"xAxis":"yAxis"].push({axis:c,value:c.toValue(a[c.horiz?"chartX":"chartY"])})});return b},getIndex:function(a){var b=this.chart;return b.inverted?b.plotHeight+b.plotTop-a.chartY:a.chartX-b.plotLeft},runPointActions:function(a){var b=this.chart,c=b.series,d=b.tooltip,e,f=b.hoverPoint,g=b.hoverSeries,h,i,j=b.chartWidth,k=this.getIndex(a);
if(d&&this.options.tooltip.shared&&(!g||!g.noSharedTooltip)){e=[];h=c.length;for(i=0;i<h;i++)if(c[i].visible&&c[i].options.enableMouseTracking!==!1&&!c[i].noSharedTooltip&&c[i].tooltipPoints.length&&(b=c[i].tooltipPoints[k],b.series))b._dist=O(k-b.clientX),j=C(j,b._dist),e.push(b);for(h=e.length;h--;)e[h]._dist>j&&e.splice(h,1);if(e.length&&e[0].clientX!==this.hoverX)d.refresh(e,a),this.hoverX=e[0].clientX}if(g&&g.tracker){if((b=g.tooltipPoints[k])&&b!==f)b.onMouseOver(a)}else d&&d.followPointer&&
!d.isHidden&&(a=d.getAnchor([{}],a),d.updatePosition({plotX:a[0],plotY:a[1]}))},reset:function(a){var b=this.chart,c=b.hoverSeries,d=b.hoverPoint,e=b.tooltip,b=e&&e.shared?b.hoverPoints:d;(a=a&&e&&b)&&ia(b)[0].plotX===v&&(a=!1);if(a)e.refresh(b);else{if(d)d.onMouseOut();if(c)c.onMouseOut();e&&(e.hide(),e.hideCrosshairs());this.hoverX=null}},scaleGroups:function(a,b){var c=this.chart,d;n(c.series,function(e){d=a||e.getPlotBox();e.xAxis&&e.xAxis.zoomEnabled&&(e.group.attr(d),e.markerGroup&&(e.markerGroup.attr(d),
e.markerGroup.clip(b?c.clipRect:null)),e.dataLabelsGroup&&e.dataLabelsGroup.attr(d))});c.clipRect.attr(b||c.clipBox)},pinchTranslateDirection:function(a,b,c,d,e,f,g){var h=this.chart,i=a?"x":"y",j=a?"X":"Y",k="chart"+j,l=a?"width":"height",m=h["plot"+(a?"Left":"Top")],o,q,p=1,n=h.inverted,r=h.bounds[a?"h":"v"],u=b.length===1,t=b[0][k],s=c[0][k],w=!u&&b[1][k],v=!u&&c[1][k],x,c=function(){!u&&O(t-w)>20&&(p=O(s-v)/O(t-w));q=(m-s)/p+t;o=h["plot"+(a?"Width":"Height")]/p};c();b=q;b<r.min?(b=r.min,x=!0):
b+o>r.max&&(b=r.max-o,x=!0);x?(s-=0.8*(s-g[i][0]),u||(v-=0.8*(v-g[i][1])),c()):g[i]=[s,v];n||(f[i]=q-m,f[l]=o);f=n?1/p:p;e[l]=o;e[i]=b;d[n?a?"scaleY":"scaleX":"scale"+j]=p;d["translate"+j]=f*m+(s-f*t)},pinch:function(a){var b=this,c=b.chart,d=b.pinchDown,e=c.tooltip&&c.tooltip.options.followTouchMove,f=a.touches,g=f.length,h=b.lastValidTouch,i=b.zoomHor||b.pinchHor,j=b.zoomVert||b.pinchVert,k=i||j,l=b.selectionMarker,m={},o={};(e||k)&&a.preventDefault();Na(f,function(a){return b.normalize(a)});if(a.type===
"touchstart")n(f,function(a,b){d[b]={chartX:a.chartX,chartY:a.chartY}}),h.x=[d[0].chartX,d[1]&&d[1].chartX],h.y=[d[0].chartY,d[1]&&d[1].chartY],n(c.axes,function(a){if(a.zoomEnabled){var b=c.bounds[a.horiz?"h":"v"],d=a.minPixelPadding,e=a.toPixels(a.dataMin),f=a.toPixels(a.dataMax),g=C(e,f),e=r(e,f);b.min=C(a.pos,g-d);b.max=r(a.pos+a.len,e+d)}});else if(d.length){if(!l)b.selectionMarker=l=s({destroy:ya},c.plotBox);i&&b.pinchTranslateDirection(!0,d,f,m,l,o,h);j&&b.pinchTranslateDirection(!1,d,f,m,
l,o,h);b.hasPinched=k;b.scaleGroups(m,o);!k&&e&&g===1&&this.runPointActions(b.normalize(a))}},dragStart:function(a){var b=this.chart;b.mouseIsDown=a.type;b.cancelClick=!1;b.mouseDownX=this.mouseDownX=a.chartX;b.mouseDownY=this.mouseDownY=a.chartY},drag:function(a){var b=this.chart,c=b.options.chart,d=a.chartX,e=a.chartY,f=this.zoomHor,g=this.zoomVert,h=b.plotLeft,i=b.plotTop,j=b.plotWidth,k=b.plotHeight,l,m=this.mouseDownX,o=this.mouseDownY;d<h?d=h:d>h+j&&(d=h+j);e<i?e=i:e>i+k&&(e=i+k);this.hasDragged=
Math.sqrt(Math.pow(m-d,2)+Math.pow(o-e,2));if(this.hasDragged>10){l=b.isInsidePlot(m-h,o-i);if(b.hasCartesianSeries&&(this.zoomX||this.zoomY)&&l&&!this.selectionMarker)this.selectionMarker=b.renderer.rect(h,i,f?1:j,g?1:k,0).attr({fill:c.selectionMarkerFill||"rgba(69,114,167,0.25)",zIndex:7}).add();this.selectionMarker&&f&&(d-=m,this.selectionMarker.attr({width:O(d),x:(d>0?0:d)+m}));this.selectionMarker&&g&&(d=e-o,this.selectionMarker.attr({height:O(d),y:(d>0?0:d)+o}));l&&!this.selectionMarker&&c.panning&&
b.pan(a,c.panning)}},drop:function(a){var b=this.chart,c=this.hasPinched;if(this.selectionMarker){var d={xAxis:[],yAxis:[],originalEvent:a.originalEvent||a},e=this.selectionMarker,f=e.x,g=e.y,h;if(this.hasDragged||c)n(b.axes,function(a){if(a.zoomEnabled){var b=a.horiz,c=a.toValue(b?f:g),b=a.toValue(b?f+e.width:g+e.height);!isNaN(c)&&!isNaN(b)&&(d[a.xOrY+"Axis"].push({axis:a,min:C(c,b),max:r(c,b)}),h=!0)}}),h&&K(b,"selection",d,function(a){b.zoom(s(a,c?{animation:!1}:null))});this.selectionMarker=
this.selectionMarker.destroy();c&&this.scaleGroups()}if(b)L(b.container,{cursor:b._cursor}),b.cancelClick=this.hasDragged>10,b.mouseIsDown=this.hasDragged=this.hasPinched=!1,this.pinchDown=[]},onContainerMouseDown:function(a){a=this.normalize(a);a.preventDefault&&a.preventDefault();this.dragStart(a)},onDocumentMouseUp:function(a){this.drop(a)},onDocumentMouseMove:function(a){var b=this.chart,c=this.chartPosition,d=b.hoverSeries,a=Sb(a);c&&d&&!this.inClass(a.target,"highcharts-tracker")&&!b.isInsidePlot(a.pageX-
c.left-b.plotLeft,a.pageY-c.top-b.plotTop)&&this.reset()},onContainerMouseLeave:function(){this.reset();this.chartPosition=null},onContainerMouseMove:function(a){var b=this.chart,a=this.normalize(a);a.returnValue=!1;b.mouseIsDown==="mousedown"&&this.drag(a);(this.inClass(a.target,"highcharts-tracker")||b.isInsidePlot(a.chartX-b.plotLeft,a.chartY-b.plotTop))&&!b.openMenu&&this.runPointActions(a)},inClass:function(a,b){for(var c;a;){if(c=w(a,"class"))if(c.indexOf(b)!==-1)return!0;else if(c.indexOf("highcharts-container")!==
-1)return!1;a=a.parentNode}},onTrackerMouseOut:function(a){var b=this.chart.hoverSeries;if(b&&!b.options.stickyTracking&&!this.inClass(a.toElement||a.relatedTarget,"highcharts-tooltip"))b.onMouseOut()},onContainerClick:function(a){var b=this.chart,c=b.hoverPoint,d=b.plotLeft,e=b.plotTop,f=b.inverted,g,h,i,a=this.normalize(a);a.cancelBubble=!0;if(!b.cancelClick)c&&this.inClass(a.target,"highcharts-tracker")?(g=this.chartPosition,h=c.plotX,i=c.plotY,s(c,{pageX:g.left+d+(f?b.plotWidth-i:h),pageY:g.top+
e+(f?b.plotHeight-h:i)}),K(c.series,"click",s(a,{point:c})),b.hoverPoint&&c.firePointEvent("click",a)):(s(a,this.getCoordinates(a)),b.isInsidePlot(a.chartX-d,a.chartY-e)&&K(b,"click",a))},onContainerTouchStart:function(a){var b=this.chart;a.touches.length===1?(a=this.normalize(a),b.isInsidePlot(a.chartX-b.plotLeft,a.chartY-b.plotTop)?(this.runPointActions(a),this.pinch(a)):this.reset()):a.touches.length===2&&this.pinch(a)},onContainerTouchMove:function(a){(a.touches.length===1||a.touches.length===
2)&&this.pinch(a)},onDocumentTouchEnd:function(a){this.drop(a)},setDOMEvents:function(){var a=this,b=a.chart.container,c;this._events=c=[[b,"onmousedown","onContainerMouseDown"],[b,"onmousemove","onContainerMouseMove"],[b,"onclick","onContainerClick"],[b,"mouseleave","onContainerMouseLeave"],[z,"mousemove","onDocumentMouseMove"],[z,"mouseup","onDocumentMouseUp"]];hb&&c.push([b,"ontouchstart","onContainerTouchStart"],[b,"ontouchmove","onContainerTouchMove"],[z,"touchend","onDocumentTouchEnd"]);n(c,
function(b){a["_"+b[2]]=function(c){a[b[2]](c)};b[1].indexOf("on")===0?b[0][b[1]]=a["_"+b[2]]:J(b[0],b[1],a["_"+b[2]])})},destroy:function(){var a=this;n(a._events,function(b){b[1].indexOf("on")===0?b[0][b[1]]=null:ba(b[0],b[1],a["_"+b[2]])});delete a._events;clearInterval(a.tooltipTimeout)}};wb.prototype={init:function(a,b){var c=this,d=b.itemStyle,e=p(b.padding,8),f=b.itemMarginTop||0;this.options=b;if(b.enabled)c.baseline=A(d.fontSize)+3+f,c.itemStyle=d,c.itemHiddenStyle=x(d,b.itemHiddenStyle),
c.itemMarginTop=f,c.padding=e,c.initialItemX=e,c.initialItemY=e-5,c.maxItemWidth=0,c.chart=a,c.itemHeight=0,c.lastLineHeight=0,c.render(),J(c.chart,"endResize",function(){c.positionCheckboxes()})},colorizeItem:function(a,b){var c=this.options,d=a.legendItem,e=a.legendLine,f=a.legendSymbol,g=this.itemHiddenStyle.color,c=b?c.itemStyle.color:g,h=b?a.color:g,g=a.options&&a.options.marker,i={stroke:h,fill:h},j;d&&d.css({fill:c,color:c});e&&e.attr({stroke:h});if(f){if(g&&f.isMarker)for(j in g=a.convertAttribs(g),
g)d=g[j],d!==v&&(i[j]=d);f.attr(i)}},positionItem:function(a){var b=this.options,c=b.symbolPadding,b=!b.rtl,d=a._legendItemPos,e=d[0],d=d[1],f=a.checkbox;a.legendGroup&&a.legendGroup.translate(b?e:this.legendWidth-e-2*c-4,d);if(f)f.x=e,f.y=d},destroyItem:function(a){var b=a.checkbox;n(["legendItem","legendLine","legendSymbol","legendGroup"],function(b){a[b]&&(a[b]=a[b].destroy())});b&&Ta(a.checkbox)},destroy:function(){var a=this.group,b=this.box;if(b)this.box=b.destroy();if(a)this.group=a.destroy()},
positionCheckboxes:function(a){var b=this.group.alignAttr,c,d=this.clipHeight||this.legendHeight;if(b)c=b.translateY,n(this.allItems,function(e){var f=e.checkbox,g;f&&(g=c+f.y+(a||0)+3,L(f,{left:b.translateX+e.legendItemWidth+f.x-20+"px",top:g+"px",display:g>c-6&&g<c+d-6?"":S}))})},renderTitle:function(){var a=this.padding,b=this.options.title,c=0;if(b.text){if(!this.title)this.title=this.chart.renderer.label(b.text,a-3,a-4,null,null,null,null,null,"legend-title").attr({zIndex:1}).css(b.style).add(this.group);
a=this.title.getBBox();c=a.height;this.offsetWidth=a.width;this.contentGroup.attr({translateY:c})}this.titleHeight=c},renderItem:function(a){var B;var b=this,c=b.chart,d=c.renderer,e=b.options,f=e.layout==="horizontal",g=e.symbolWidth,h=e.symbolPadding,i=b.itemStyle,j=b.itemHiddenStyle,k=b.padding,l=f?p(e.itemDistance,8):0,m=!e.rtl,o=e.width,q=e.itemMarginBottom||0,n=b.itemMarginTop,y=b.initialItemX,u=a.legendItem,t=a.series||a,s=t.options,w=s.showCheckbox,v=e.useHTML;if(!u&&(a.legendGroup=d.g("legend-item").attr({zIndex:1}).add(b.scrollGroup),
t.drawLegendSymbol(b,a),a.legendItem=u=d.text(e.labelFormat?Ba(e.labelFormat,a):e.labelFormatter.call(a),m?g+h:-h,b.baseline,v).css(x(a.visible?i:j)).attr({align:m?"left":"right",zIndex:2}).add(a.legendGroup),(v?u:a.legendGroup).on("mouseover",function(){a.setState("hover");u.css(b.options.itemHoverStyle)}).on("mouseout",function(){u.css(a.visible?i:j);a.setState()}).on("click",function(b){var c=function(){a.setVisible()},b={browserEvent:b};a.firePointEvent?a.firePointEvent("legendItemClick",b,c):
K(a,"legendItemClick",b,c)}),b.colorizeItem(a,a.visible),s&&w))a.checkbox=U("input",{type:"checkbox",checked:a.selected,defaultChecked:a.selected},e.itemCheckboxStyle,c.container),J(a.checkbox,"click",function(b){K(a,"checkboxClick",{checked:b.target.checked},function(){a.select()})});d=u.getBBox();B=a.legendItemWidth=e.itemWidth||g+h+d.width+l+(w?20:0),e=B;b.itemHeight=g=d.height;if(f&&b.itemX-y+e>(o||c.chartWidth-2*k-y))b.itemX=y,b.itemY+=n+b.lastLineHeight+q,b.lastLineHeight=0;b.maxItemWidth=r(b.maxItemWidth,
e);b.lastItemY=n+b.itemY+q;b.lastLineHeight=r(g,b.lastLineHeight);a._legendItemPos=[b.itemX,b.itemY];f?b.itemX+=e:(b.itemY+=n+g+q,b.lastLineHeight=g);b.offsetWidth=o||r((f?b.itemX-y-l:e)+k,b.offsetWidth)},render:function(){var a=this,b=a.chart,c=b.renderer,d=a.group,e,f,g,h,i=a.box,j=a.options,k=a.padding,l=j.borderWidth,m=j.backgroundColor;a.itemX=a.initialItemX;a.itemY=a.initialItemY;a.offsetWidth=0;a.lastItemY=0;if(!d)a.group=d=c.g("legend").attr({zIndex:7}).add(),a.contentGroup=c.g().attr({zIndex:1}).add(d),
a.scrollGroup=c.g().add(a.contentGroup);a.renderTitle();e=[];n(b.series,function(a){var b=a.options;b.showInLegend&&!t(b.linkedTo)&&(e=e.concat(a.legendItems||(b.legendType==="point"?a.data:a)))});Kb(e,function(a,b){return(a.options&&a.options.legendIndex||0)-(b.options&&b.options.legendIndex||0)});j.reversed&&e.reverse();a.allItems=e;a.display=f=!!e.length;n(e,function(b){a.renderItem(b)});g=j.width||a.offsetWidth;h=a.lastItemY+a.lastLineHeight+a.titleHeight;h=a.handleOverflow(h);if(l||m){g+=k;h+=
k;if(i){if(g>0&&h>0)i[i.isNew?"attr":"animate"](i.crisp(null,null,null,g,h)),i.isNew=!1}else a.box=i=c.rect(0,0,g,h,j.borderRadius,l||0).attr({stroke:j.borderColor,"stroke-width":l||0,fill:m||S}).add(d).shadow(j.shadow),i.isNew=!0;i[f?"show":"hide"]()}a.legendWidth=g;a.legendHeight=h;n(e,function(b){a.positionItem(b)});f&&d.align(s({width:g,height:h},j),!0,"spacingBox");b.isResizing||this.positionCheckboxes()},handleOverflow:function(a){var b=this,c=this.chart,d=c.renderer,e=this.options,f=e.y,f=
c.spacingBox.height+(e.verticalAlign==="top"?-f:f)-this.padding,g=e.maxHeight,h=this.clipRect,i=e.navigation,j=p(i.animation,!0),k=i.arrowSize||12,l=this.nav;e.layout==="horizontal"&&(f/=2);g&&(f=C(f,g));if(a>f&&!e.useHTML){this.clipHeight=c=f-20-this.titleHeight;this.pageCount=wa(a/c);this.currentPage=p(this.currentPage,1);this.fullHeight=a;if(!h)h=b.clipRect=d.clipRect(0,0,9999,0),b.contentGroup.clip(h);h.attr({height:c});if(!l)this.nav=l=d.g().attr({zIndex:1}).add(this.group),this.up=d.symbol("triangle",
0,0,k,k).on("click",function(){b.scroll(-1,j)}).add(l),this.pager=d.text("",15,10).css(i.style).add(l),this.down=d.symbol("triangle-down",0,0,k,k).on("click",function(){b.scroll(1,j)}).add(l);b.scroll(0);a=f}else if(l)h.attr({height:c.chartHeight}),l.hide(),this.scrollGroup.attr({translateY:1}),this.clipHeight=0;return a},scroll:function(a,b){var c=this.pageCount,d=this.currentPage+a,e=this.clipHeight,f=this.options.navigation,g=f.activeColor,h=f.inactiveColor,f=this.pager,i=this.padding;d>c&&(d=
c);if(d>0)b!==v&&Ka(b,this.chart),this.nav.attr({translateX:i,translateY:e+7+this.titleHeight,visibility:"visible"}),this.up.attr({fill:d===1?h:g}).css({cursor:d===1?"default":"pointer"}),f.attr({text:d+"/"+this.pageCount}),this.down.attr({x:18+this.pager.getBBox().width,fill:d===c?h:g}).css({cursor:d===c?"default":"pointer"}),e=-C(e*(d-1),this.fullHeight-e+i)+1,this.scrollGroup.animate({translateY:e}),f.attr({text:d+"/"+c}),this.currentPage=d,this.positionCheckboxes(e)}};xb.prototype={init:function(a,
b){var c,d=a.series;a.series=null;c=x(M,a);c.series=a.series=d;var d=c.chart,e=d.margin,e=T(e)?e:[e,e,e,e];this.optionsMarginTop=p(d.marginTop,e[0]);this.optionsMarginRight=p(d.marginRight,e[1]);this.optionsMarginBottom=p(d.marginBottom,e[2]);this.optionsMarginLeft=p(d.marginLeft,e[3]);e=d.events;this.bounds={h:{},v:{}};this.callback=b;this.isResizing=0;this.options=c;this.axes=[];this.series=[];this.hasCartesianSeries=d.showAxes;var f=this,g;f.index=Fa.length;Fa.push(f);d.reflow!==!1&&J(f,"load",
function(){f.initReflow()});if(e)for(g in e)J(f,g,e[g]);f.xAxis=[];f.yAxis=[];f.animation=$?!1:p(d.animation,!0);f.pointCount=0;f.counters=new Jb;f.firstRender()},initSeries:function(a){var b=this.options.chart;(b=aa[a.type||b.type||b.defaultSeriesType])||ja(17,!0);b=new b;b.init(this,a);return b},addSeries:function(a,b,c){var d,e=this;a&&(b=p(b,!0),K(e,"addSeries",{options:a},function(){d=e.initSeries(a);e.isDirtyLegend=!0;e.linkSeries();b&&e.redraw(c)}));return d},addAxis:function(a,b,c,d){var e=
b?"xAxis":"yAxis",f=this.options;new db(this,x(a,{index:this[e].length,isX:b}));f[e]=ia(f[e]||{});f[e].push(a);p(c,!0)&&this.redraw(d)},isInsidePlot:function(a,b,c){var d=c?b:a,a=c?a:b;return d>=0&&d<=this.plotWidth&&a>=0&&a<=this.plotHeight},adjustTickAmounts:function(){this.options.chart.alignTicks!==!1&&n(this.axes,function(a){a.adjustTickAmount()});this.maxTicks=null},redraw:function(a){var b=this.axes,c=this.series,d=this.pointer,e=this.legend,f=this.isDirtyLegend,g,h,i=this.isDirtyBox,j=c.length,
k=j,l=this.renderer,m=l.isHidden(),o=[];Ka(a,this);m&&this.cloneRenderTo();for(this.layOutTitles();k--;)if(a=c[k],a.options.stacking&&(g=!0,a.isDirty)){h=!0;break}if(h)for(k=j;k--;)if(a=c[k],a.options.stacking)a.isDirty=!0;n(c,function(a){a.isDirty&&a.options.legendType==="point"&&(f=!0)});if(f&&e.options.enabled)e.render(),this.isDirtyLegend=!1;g&&this.getStacks();if(this.hasCartesianSeries){if(!this.isResizing)this.maxTicks=null,n(b,function(a){a.setScale()});this.adjustTickAmounts();this.getMargins();
n(b,function(a){a.isDirty&&(i=!0)});n(b,function(a){if(a.isDirtyExtremes)a.isDirtyExtremes=!1,o.push(function(){K(a,"afterSetExtremes",a.getExtremes())});(i||g)&&a.redraw()})}i&&this.drawChartBox();n(c,function(a){a.isDirty&&a.visible&&(!a.isCartesian||a.xAxis)&&a.redraw()});d&&d.reset&&d.reset(!0);l.draw();K(this,"redraw");m&&this.cloneRenderTo(!0);n(o,function(a){a.call()})},showLoading:function(a){var b=this.options,c=this.loadingDiv,d=b.loading;if(!c)this.loadingDiv=c=U(Ca,{className:"highcharts-loading"},
s(d.style,{zIndex:10,display:S}),this.container),this.loadingSpan=U("span",null,d.labelStyle,c);this.loadingSpan.innerHTML=a||b.lang.loading;if(!this.loadingShown)L(c,{opacity:0,display:"",left:this.plotLeft+"px",top:this.plotTop+"px",width:this.plotWidth+"px",height:this.plotHeight+"px"}),Ab(c,{opacity:d.style.opacity},{duration:d.showDuration||0}),this.loadingShown=!0},hideLoading:function(){var a=this.options,b=this.loadingDiv;b&&Ab(b,{opacity:0},{duration:a.loading.hideDuration||100,complete:function(){L(b,
{display:S})}});this.loadingShown=!1},get:function(a){var b=this.axes,c=this.series,d,e;for(d=0;d<b.length;d++)if(b[d].options.id===a)return b[d];for(d=0;d<c.length;d++)if(c[d].options.id===a)return c[d];for(d=0;d<c.length;d++){e=c[d].points||[];for(b=0;b<e.length;b++)if(e[b].id===a)return e[b]}return null},getAxes:function(){var a=this,b=this.options,c=b.xAxis=ia(b.xAxis||{}),b=b.yAxis=ia(b.yAxis||{});n(c,function(a,b){a.index=b;a.isX=!0});n(b,function(a,b){a.index=b});c=c.concat(b);n(c,function(b){new db(a,
b)});a.adjustTickAmounts()},getSelectedPoints:function(){var a=[];n(this.series,function(b){a=a.concat(sb(b.points||[],function(a){return a.selected}))});return a},getSelectedSeries:function(){return sb(this.series,function(a){return a.selected})},getStacks:function(){var a=this;n(a.yAxis,function(a){if(a.stacks&&a.hasVisibleSeries)a.oldStacks=a.stacks});n(a.series,function(b){if(b.options.stacking&&(b.visible===!0||a.options.chart.ignoreHiddenSeries===!1))b.stackKey=b.type+p(b.options.stack,"")})},
showResetZoom:function(){var a=this,b=M.lang,c=a.options.chart.resetZoomButton,d=c.theme,e=d.states,f=c.relativeTo==="chart"?null:"plotBox";this.resetZoomButton=a.renderer.button(b.resetZoom,null,null,function(){a.zoomOut()},d,e&&e.hover).attr({align:c.position.align,title:b.resetZoomTitle}).add().align(c.position,!1,f)},zoomOut:function(){var a=this;K(a,"selection",{resetSelection:!0},function(){a.zoom()})},zoom:function(a){var b,c=this.pointer,d=!1,e;!a||a.resetSelection?n(this.axes,function(a){b=
a.zoom()}):n(a.xAxis.concat(a.yAxis),function(a){var e=a.axis,h=e.isXAxis;if(c[h?"zoomX":"zoomY"]||c[h?"pinchX":"pinchY"])b=e.zoom(a.min,a.max),e.displayBtn&&(d=!0)});e=this.resetZoomButton;if(d&&!e)this.showResetZoom();else if(!d&&T(e))this.resetZoomButton=e.destroy();b&&this.redraw(p(this.options.chart.animation,a&&a.animation,this.pointCount<100))},pan:function(a,b){var c=this,d=c.hoverPoints,e;d&&n(d,function(a){a.setState()});n(b==="xy"?[1,0]:[1],function(b){var d=a[b?"chartX":"chartY"],h=c[b?
"xAxis":"yAxis"][0],i=c[b?"mouseDownX":"mouseDownY"],j=(h.pointRange||0)/2,k=h.getExtremes(),l=h.toValue(i-d,!0)+j,i=h.toValue(i+c[b?"plotWidth":"plotHeight"]-d,!0)-j;h.series.length&&l>C(k.dataMin,k.min)&&i<r(k.dataMax,k.max)&&(h.setExtremes(l,i,!1,!1,{trigger:"pan"}),e=!0);c[b?"mouseDownX":"mouseDownY"]=d});e&&c.redraw(!1);L(c.container,{cursor:"move"})},setTitle:function(a,b){var f;var c=this,d=c.options,e;e=d.title=x(d.title,a);f=d.subtitle=x(d.subtitle,b),d=f;n([["title",a,e],["subtitle",b,d]],
function(a){var b=a[0],d=c[b],e=a[1],a=a[2];d&&e&&(c[b]=d=d.destroy());a&&a.text&&!d&&(c[b]=c.renderer.text(a.text,0,0,a.useHTML).attr({align:a.align,"class":"highcharts-"+b,zIndex:a.zIndex||4}).css(a.style).add())});c.layOutTitles()},layOutTitles:function(){var a=0,b=this.title,c=this.subtitle,d=this.options,e=d.title,d=d.subtitle,f=this.spacingBox.width-44;if(b&&(b.css({width:(e.width||f)+"px"}).align(s({y:15},e),!1,"spacingBox"),!e.floating&&!e.verticalAlign))a=b.getBBox().height,a>=18&&a<=25&&
(a=15);c&&(c.css({width:(d.width||f)+"px"}).align(s({y:a+e.margin},d),!1,"spacingBox"),!d.floating&&!d.verticalAlign&&(a=wa(a+c.getBBox().height)));this.titleOffset=a},getChartSize:function(){var a=this.options.chart,b=this.renderToClone||this.renderTo;this.containerWidth=ib(b,"width");this.containerHeight=ib(b,"height");this.chartWidth=r(0,a.width||this.containerWidth||600);this.chartHeight=r(0,p(a.height,this.containerHeight>19?this.containerHeight:200))},cloneRenderTo:function(a){var b=this.renderToClone,
c=this.container;a?b&&(this.renderTo.appendChild(c),Ta(b),delete this.renderToClone):(c&&c.parentNode===this.renderTo&&this.renderTo.removeChild(c),this.renderToClone=b=this.renderTo.cloneNode(0),L(b,{position:"absolute",top:"-9999px",display:"block"}),z.body.appendChild(b),c&&b.appendChild(c))},getContainer:function(){var a,b=this.options.chart,c,d,e;this.renderTo=a=b.renderTo;e="highcharts-"+yb++;if(ea(a))this.renderTo=a=z.getElementById(a);a||ja(13,!0);c=A(w(a,"data-highcharts-chart"));!isNaN(c)&&
Fa[c]&&Fa[c].destroy();w(a,"data-highcharts-chart",this.index);a.innerHTML="";a.offsetWidth||this.cloneRenderTo();this.getChartSize();c=this.chartWidth;d=this.chartHeight;this.container=a=U(Ca,{className:"highcharts-container"+(b.className?" "+b.className:""),id:e},s({overflow:"hidden",width:c+"px",height:d+"px",textAlign:"left",lineHeight:"normal",zIndex:0,"-webkit-tap-highlight-color":"rgba(0,0,0,0)"},b.style),this.renderToClone||a);this._cursor=a.style.cursor;this.renderer=
b.forExport?new Ga(a,c,d,!0):new Va(a,c,d);$&&this.renderer.create(this,a,c,d)},getMargins:function(){var a=this.options.chart,b=a.spacingTop,c=a.spacingRight,d=a.spacingBottom,a=a.spacingLeft,e,f=this.legend,g=this.optionsMarginTop,h=this.optionsMarginLeft,i=this.optionsMarginRight,j=this.optionsMarginBottom,k=this.options.legend,l=p(k.margin,10),m=k.x,o=k.y,q=k.align,u=k.verticalAlign,y=this.titleOffset;this.resetMargins();e=this.axisOffset;if(y&&!t(g))this.plotTop=r(this.plotTop,y+this.options.title.margin+
b);if(f.display&&!k.floating)if(q==="right"){if(!t(i))this.marginRight=r(this.marginRight,f.legendWidth-m+l+c)}else if(q==="left"){if(!t(h))this.plotLeft=r(this.plotLeft,f.legendWidth+m+l+a)}else if(u==="top"){if(!t(g))this.plotTop=r(this.plotTop,f.legendHeight+o+l+b)}else if(u==="bottom"&&!t(j))this.marginBottom=r(this.marginBottom,f.legendHeight-o+l+d);this.extraBottomMargin&&(this.marginBottom+=this.extraBottomMargin);this.extraTopMargin&&(this.plotTop+=this.extraTopMargin);this.hasCartesianSeries&&
n(this.axes,function(a){a.getOffset()});t(h)||(this.plotLeft+=e[3]);t(g)||(this.plotTop+=e[0]);t(j)||(this.marginBottom+=e[2]);t(i)||(this.marginRight+=e[1]);this.setChartSize()},initReflow:function(){function a(a){var g=c.width||ib(d,"width"),h=c.height||ib(d,"height"),a=a?a.target:N;if(!b.hasUserSize&&g&&h&&(a===N||a===z)){if(g!==b.containerWidth||h!==b.containerHeight)clearTimeout(e),b.reflowTimeout=e=setTimeout(function(){if(b.container)b.setSize(g,h,!1),b.hasUserSize=null},100);b.containerWidth=
g;b.containerHeight=h}}var b=this,c=b.options.chart,d=b.renderTo,e;J(N,"resize",a);J(b,"destroy",function(){ba(N,"resize",a)})},setSize:function(a,b,c){var d=this,e,f,g;d.isResizing+=1;g=function(){d&&K(d,"endResize",null,function(){d.isResizing-=1})};Ka(c,d);d.oldChartHeight=d.chartHeight;d.oldChartWidth=d.chartWidth;if(t(a))d.chartWidth=e=r(0,u(a)),d.hasUserSize=!!e;if(t(b))d.chartHeight=f=r(0,u(b));L(d.container,{width:e+"px",height:f+"px"});d.setChartSize(!0);d.renderer.setSize(e,f,c);d.maxTicks=
null;n(d.axes,function(a){a.isDirty=!0;a.setScale()});n(d.series,function(a){a.isDirty=!0});d.isDirtyLegend=!0;d.isDirtyBox=!0;d.getMargins();d.redraw(c);d.oldChartHeight=null;K(d,"resize");Da===!1?g():setTimeout(g,Da&&Da.duration||500)},setChartSize:function(a){var b=this.inverted,c=this.renderer,d=this.chartWidth,e=this.chartHeight,f=this.options.chart,g=f.spacingTop,h=f.spacingRight,i=f.spacingBottom,j=f.spacingLeft,k=this.clipOffset,l,m,o,q;this.plotLeft=l=u(this.plotLeft);this.plotTop=m=u(this.plotTop);
this.plotWidth=o=r(0,u(d-l-this.marginRight));this.plotHeight=q=r(0,u(e-m-this.marginBottom));this.plotSizeX=b?q:o;this.plotSizeY=b?o:q;this.plotBorderWidth=f.plotBorderWidth||0;this.spacingBox=c.spacingBox={x:j,y:g,width:d-j-h,height:e-g-i};this.plotBox=c.plotBox={x:l,y:m,width:o,height:q};d=2*P(this.plotBorderWidth/2);b=wa(r(d,k[3])/2);c=wa(r(d,k[0])/2);this.clipBox={x:b,y:c,width:P(this.plotSizeX-r(d,k[1])/2-b),height:P(this.plotSizeY-r(d,k[2])/2-c)};a||n(this.axes,function(a){a.setAxisSize();
a.setAxisTranslation()})},resetMargins:function(){var a=this.options.chart,b=a.spacingRight,c=a.spacingBottom,d=a.spacingLeft;this.plotTop=p(this.optionsMarginTop,a.spacingTop);this.marginRight=p(this.optionsMarginRight,b);this.marginBottom=p(this.optionsMarginBottom,c);this.plotLeft=p(this.optionsMarginLeft,d);this.axisOffset=[0,0,0,0];this.clipOffset=[0,0,0,0]},drawChartBox:function(){var a=this.options.chart,b=this.renderer,c=this.chartWidth,d=this.chartHeight,e=this.chartBackground,f=this.plotBackground,
g=this.plotBorder,h=this.plotBGImage,i=a.borderWidth||0,j=a.backgroundColor,k=a.plotBackgroundColor,l=a.plotBackgroundImage,m=a.plotBorderWidth||0,o,q=this.plotLeft,p=this.plotTop,n=this.plotWidth,r=this.plotHeight,u=this.plotBox,t=this.clipRect,s=this.clipBox;o=i+(a.shadow?8:0);if(i||j)if(e)e.animate(e.crisp(null,null,null,c-o,d-o));else{e={fill:j||S};if(i)e.stroke=a.borderColor,e["stroke-width"]=i;this.chartBackground=b.rect(o/2,o/2,c-o,d-o,a.borderRadius,i).attr(e).add().shadow(a.shadow)}if(k)f?
f.animate(u):this.plotBackground=b.rect(q,p,n,r,0).attr({fill:k}).add().shadow(a.plotShadow);if(l)h?h.animate(u):this.plotBGImage=b.image(l,q,p,n,r).add();t?t.animate({width:s.width,height:s.height}):this.clipRect=b.clipRect(s);if(m)g?g.animate(g.crisp(null,q,p,n,r)):this.plotBorder=b.rect(q,p,n,r,0,-m).attr({stroke:a.plotBorderColor,"stroke-width":m,zIndex:1}).add();this.isDirtyBox=!1},propFromSeries:function(){var a=this,b=a.options.chart,c,d=a.options.series,e,f;n(["inverted","angular","polar"],
function(g){c=aa[b.type||b.defaultSeriesType];f=a[g]||b[g]||c&&c.prototype[g];for(e=d&&d.length;!f&&e--;)(c=aa[d[e].type])&&c.prototype[g]&&(f=!0);a[g]=f})},linkSeries:function(){var a=this,b=a.series;n(b,function(a){a.linkedSeries.length=0});n(b,function(b){var d=b.options.linkedTo;if(ea(d)&&(d=d===":previous"?a.series[b.index-1]:a.get(d)))d.linkedSeries.push(b),b.linkedParent=d})},render:function(){var a=this,b=a.axes,c=a.renderer,d=a.options,e=d.labels,f=d.credits,g;a.setTitle();a.legend=new wb(a,
d.legend);a.getStacks();n(b,function(a){a.setScale()});a.getMargins();a.maxTicks=null;n(b,function(a){a.setTickPositions(!0);a.setMaxTicks()});a.adjustTickAmounts();a.getMargins();a.drawChartBox();a.hasCartesianSeries&&n(b,function(a){a.render()});if(!a.seriesGroup)a.seriesGroup=c.g("series-group").attr({zIndex:3}).add();n(a.series,function(a){a.translate();a.setTooltipPoints();a.render()});e.items&&n(e.items,function(b){var d=s(e.style,b.style),f=A(d.left)+a.plotLeft,g=A(d.top)+a.plotTop+12;delete d.left;
delete d.top;c.text(b.html,f,g).attr({zIndex:2}).css(d).add()});if(f.enabled&&!a.credits)g=f.href,a.credits=c.text(f.text,0,0).on("click",function(){if(g)location.href=g}).attr({align:f.position.align,zIndex:8}).css(f.style).add().align(f.position);a.hasRendered=!0},destroy:function(){var a=this,b=a.axes,c=a.series,d=a.container,e,f=d&&d.parentNode;K(a,"destroy");Fa[a.index]=v;a.renderTo.removeAttribute("data-highcharts-chart");ba(a);for(e=b.length;e--;)b[e]=b[e].destroy();for(e=c.length;e--;)c[e]=
c[e].destroy();n("title,subtitle,chartBackground,plotBackground,plotBGImage,plotBorder,seriesGroup,clipRect,credits,pointer,scroller,rangeSelector,legend,resetZoomButton,tooltip,renderer".split(","),function(b){var c=a[b];c&&c.destroy&&(a[b]=c.destroy())});if(d)d.innerHTML="",ba(d),f&&Ta(d);for(e in a)delete a[e]},isReadyToRender:function(){var a=this;return!Z&&N==N.top&&z.readyState!=="complete"||$&&!N.canvg?($?Tb.push(function(){a.firstRender()},a.options.global.canvasToolsURL):z.attachEvent("onreadystatechange",
function(){z.detachEvent("onreadystatechange",a.firstRender);z.readyState==="complete"&&a.firstRender()}),!1):!0},firstRender:function(){var a=this,b=a.options,c=a.callback;if(a.isReadyToRender())a.getContainer(),K(a,"init"),a.resetMargins(),a.setChartSize(),a.propFromSeries(),a.getAxes(),n(b.series||[],function(b){a.initSeries(b)}),a.linkSeries(),K(a,"beforeRender"),a.pointer=new vb(a,b),a.render(),a.renderer.draw(),c&&c.apply(a,[a]),n(a.callbacks,function(b){b.apply(a,[a])}),a.cloneRenderTo(!0),
K(a,"load")}};xb.prototype.callbacks=[];var Pa=function(){};Pa.prototype={init:function(a,b,c){this.series=a;this.applyOptions(b,c);this.pointAttr={};if(a.options.colorByPoint&&(b=a.options.colors||a.chart.options.colors,this.color=this.color||b[a.colorCounter++],a.colorCounter===b.length))a.colorCounter=0;a.chart.pointCount++;return this},applyOptions:function(a,b){var c=this.series,d=c.pointValKey,a=Pa.prototype.optionsToObject.call(this,a);s(this,a);this.options=this.options?s(this.options,a):
a;if(d)this.y=this[d];if(this.x===v&&c)this.x=b===v?c.autoIncrement():b;return this},optionsToObject:function(a){var b,c=this.series,d=c.pointArrayMap||["y"],e=d.length,f=0,g=0;if(typeof a==="number"||a===null)b={y:a};else if(Ha(a)){b={};if(a.length>e){c=typeof a[0];if(c==="string")b.name=a[0];else if(c==="number")b.x=a[0];f++}for(;g<e;)b[d[g++]]=a[f++]}else if(typeof a==="object"){b=a;if(a.dataLabels)c._hasPointLabels=!0;if(a.marker)c._hasPointMarkers=!0}return b},destroy:function(){var a=this.series.chart,
b=a.hoverPoints,c;a.pointCount--;if(b&&(this.setState(),ga(b,this),!b.length))a.hoverPoints=null;if(this===a.hoverPoint)this.onMouseOut();if(this.graphic||this.dataLabel)ba(this),this.destroyElements();this.legendItem&&a.legend.destroyItem(this);for(c in this)this[c]=null},destroyElements:function(){for(var a="graphic,dataLabel,dataLabelUpper,group,connector,shadowGroup".split(","),b,c=6;c--;)b=a[c],this[b]&&(this[b]=this[b].destroy())},getLabelConfig:function(){return{x:this.category,y:this.y,key:this.name||
this.category,series:this.series,point:this,percentage:this.percentage,total:this.total||this.stackTotal}},select:function(a,b){var c=this,d=c.series,e=d.chart,a=p(a,!c.selected);c.firePointEvent(a?"select":"unselect",{accumulate:b},function(){c.selected=c.options.selected=a;d.options.data[oa(c,d.data)]=c.options;c.setState(a&&"select");b||n(e.getSelectedPoints(),function(a){if(a.selected&&a!==c)a.selected=a.options.selected=!1,d.options.data[oa(a,d.data)]=a.options,a.setState(""),a.firePointEvent("unselect")})})},
onMouseOver:function(a){var b=this.series,c=b.chart,d=c.tooltip,e=c.hoverPoint;if(e&&e!==this)e.onMouseOut();this.firePointEvent("mouseOver");d&&(!d.shared||b.noSharedTooltip)&&d.refresh(this,a);this.setState("hover");c.hoverPoint=this},onMouseOut:function(){var a=this.series.chart,b=a.hoverPoints;if(!b||oa(this,b)===-1)this.firePointEvent("mouseOut"),this.setState(),a.hoverPoint=null},tooltipFormatter:function(a){var b=this.series,c=b.tooltipOptions,d=p(c.valueDecimals,""),e=c.valuePrefix||"",f=
c.valueSuffix||"";n(b.pointArrayMap||["y"],function(b){b="{point."+b;if(e||f)a=a.replace(b+"}",e+b+"}"+f);a=a.replace(b+"}",b+":,."+d+"f}")});return Ba(a,{point:this,series:this.series})},update:function(a,b,c){var d=this,e=d.series,f=d.graphic,g,h=e.data,i=e.chart,j=e.options,b=p(b,!0);d.firePointEvent("update",{options:a},function(){d.applyOptions(a);if(T(a)&&(e.getAttribs(),f))a.marker&&a.marker.symbol?d.graphic=f.destroy():f.attr(d.pointAttr[e.state]);g=oa(d,h);e.xData[g]=d.x;e.yData[g]=e.toYData?
e.toYData(d):d.y;e.zData[g]=d.z;j.data[g]=d.options;e.isDirty=e.isDirtyData=i.isDirtyBox=!0;j.legendType==="point"&&i.legend.destroyItem(d);b&&i.redraw(c)})},remove:function(a,b){var c=this,d=c.series,e=d.chart,f,g=d.data;Ka(b,e);a=p(a,!0);c.firePointEvent("remove",null,function(){f=oa(c,g);g.splice(f,1);d.options.data.splice(f,1);d.xData.splice(f,1);d.yData.splice(f,1);d.zData.splice(f,1);c.destroy();d.isDirty=!0;d.isDirtyData=!0;a&&e.redraw()})},firePointEvent:function(a,b,c){var d=this,e=this.series.options;
(e.point.events[a]||d.options&&d.options.events&&d.options.events[a])&&this.importEvents();a==="click"&&e.allowPointSelect&&(c=function(a){d.select(null,a.ctrlKey||a.metaKey||a.shiftKey)});K(this,a,b,c)},importEvents:function(){if(!this.hasImportedEvents){var a=x(this.series.options.point,this.options).events,b;this.events=a;for(b in a)J(this,b,a[b]);this.hasImportedEvents=!0}},setState:function(a){var b=this.plotX,c=this.plotY,d=this.series,e=d.options.states,f=Y[d.type].marker&&d.options.marker,
g=f&&!f.enabled,h=f&&f.states[a],i=h&&h.enabled===!1,j=d.stateMarkerGraphic,k=this.marker||{},l=d.chart,m=this.pointAttr,a=a||"";if(!(a===this.state||this.selected&&a!=="select"||e[a]&&e[a].enabled===!1||a&&(i||g&&!h.enabled))){if(this.graphic)e=f&&this.graphic.symbolName&&m[a].r,this.graphic.attr(x(m[a],e?{x:b-e,y:c-e,width:2*e,height:2*e}:{}));else{if(a&&h)e=h.radius,k=k.symbol||d.symbol,j&&j.currentSymbol!==k&&(j=j.destroy()),j?j.attr({x:b-e,y:c-e}):(d.stateMarkerGraphic=j=l.renderer.symbol(k,
b-e,c-e,2*e,2*e).attr(m[a]).add(d.markerGroup),j.currentSymbol=k);if(j)j[a&&l.isInsidePlot(b,c)?"show":"hide"]()}this.state=a}}};var Q=function(){};Q.prototype={isCartesian:!0,type:"line",pointClass:Pa,sorted:!0,requireSorting:!0,pointAttrToOptions:{stroke:"lineColor","stroke-width":"lineWidth",fill:"fillColor",r:"radius"},colorCounter:0,init:function(a,b){var c,d,e=a.series;this.chart=a;this.options=b=this.setOptions(b);this.linkedSeries=[];this.bindAxes();s(this,{name:b.name,state:"",pointAttr:{},
visible:b.visible!==!1,selected:b.selected===!0});if($)b.animation=!1;d=b.events;for(c in d)J(this,c,d[c]);if(d&&d.click||b.point&&b.point.events&&b.point.events.click||b.allowPointSelect)a.runTrackerClick=!0;this.getColor();this.getSymbol();this.setData(b.data,!1);if(this.isCartesian)a.hasCartesianSeries=!0;e.push(this);this._i=e.length-1;Kb(e,function(a,b){return p(a.options.index,a._i)-p(b.options.index,a._i)});n(e,function(a,b){a.index=b;a.name=a.name||"Series "+(b+1)})},bindAxes:function(){var a=
this,b=a.options,c=a.chart,d;a.isCartesian&&n(["xAxis","yAxis"],function(e){n(c[e],function(c){d=c.options;if(b[e]===d.index||b[e]!==v&&b[e]===d.id||b[e]===v&&d.index===0)c.series.push(a),a[e]=c,c.isDirty=!0});a[e]||ja(18,!0)})},autoIncrement:function(){var a=this.options,b=this.xIncrement,b=p(b,a.pointStart,0);this.pointInterval=p(this.pointInterval,a.pointInterval,1);this.xIncrement=b+this.pointInterval;return b},getSegments:function(){var a=-1,b=[],c,d=this.points,e=d.length;if(e)if(this.options.connectNulls){for(c=
e;c--;)d[c].y===null&&d.splice(c,1);d.length&&(b=[d])}else n(d,function(c,g){c.y===null?(g>a+1&&b.push(d.slice(a+1,g)),a=g):g===e-1&&b.push(d.slice(a+1,g+1))});this.segments=b},setOptions:function(a){var b=this.chart.options,c=b.plotOptions,d=c[this.type];this.userOptions=a;a=x(d,c.series,a);this.tooltipOptions=x(b.tooltip,a.tooltip);d.marker===null&&delete a.marker;return a},getColor:function(){var a=this.options,b=this.userOptions,c=this.chart.options.colors,d=this.chart.counters,e;e=a.color||Y[this.type].color;
if(!e&&!a.colorByPoint)t(b._colorIndex)?a=b._colorIndex:(b._colorIndex=d.color,a=d.color++),e=c[a];this.color=e;d.wrapColor(c.length)},getSymbol:function(){var a=this.userOptions,b=this.options.marker,c=this.chart,d=c.options.symbols,c=c.counters;this.symbol=b.symbol;if(!this.symbol)t(a._symbolIndex)?a=a._symbolIndex:(a._symbolIndex=c.symbol,a=c.symbol++),this.symbol=d[a];if(/^url/.test(this.symbol))b.radius=0;c.wrapSymbol(d.length)},drawLegendSymbol:function(a){var b=this.options,c=b.marker,d=a.options,
e;e=d.symbolWidth;var f=this.chart.renderer,g=this.legendGroup,a=a.baseline-u(f.fontMetrics(d.itemStyle.fontSize).b*0.3);if(b.lineWidth){d={"stroke-width":b.lineWidth};if(b.dashStyle)d.dashstyle=b.dashStyle;this.legendLine=f.path(["M",0,a,"L",e,a]).attr(d).add(g)}if(c&&c.enabled)b=c.radius,this.legendSymbol=e=f.symbol(this.symbol,e/2-b,a-b,2*b,2*b).add(g),e.isMarker=!0},addPoint:function(a,b,c,d){var e=this.options,f=this.data,g=this.graph,h=this.area,i=this.chart,j=this.xData,k=this.yData,l=this.zData,
m=this.names,o=g&&g.shift||0,q=e.data;Ka(d,i);c&&n([g,h,this.graphNeg,this.areaNeg],function(a){if(a)a.shift=o+1});if(h)h.isArea=!0;b=p(b,!0);d={series:this};this.pointClass.prototype.applyOptions.apply(d,[a]);j.push(d.x);k.push(this.toYData?this.toYData(d):d.y);l.push(d.z);if(m)m[d.x]=d.name;q.push(a);e.legendType==="point"&&this.generatePoints();c&&(f[0]&&f[0].remove?f[0].remove(!1):(f.shift(),j.shift(),k.shift(),l.shift(),q.shift()));this.isDirtyData=this.isDirty=!0;b&&(this.getAttribs(),i.redraw())},
setData:function(a,b){var c=this.points,d=this.options,e=this.chart,f=null,g=this.xAxis,h=g&&g.categories&&!g.categories.length?[]:null,i;this.xIncrement=null;this.pointRange=g&&g.categories?1:d.pointRange;this.colorCounter=0;var j=[],k=[],l=[],m=a?a.length:[];i=p(d.turboThreshold,1E3);var o=this.pointArrayMap,o=o&&o.length,q=!!this.toYData;if(i&&m>i){for(i=0;f===null&&i<m;)f=a[i],i++;if(qa(f)){f=p(d.pointStart,0);d=p(d.pointInterval,1);for(i=0;i<m;i++)j[i]=f,k[i]=a[i],f+=d;this.xIncrement=f}else if(Ha(f))if(o)for(i=
0;i<m;i++)d=a[i],j[i]=d[0],k[i]=d.slice(1,o+1);else for(i=0;i<m;i++)d=a[i],j[i]=d[0],k[i]=d[1];else ja(12)}else for(i=0;i<m;i++)if(a[i]!==v&&(d={series:this},this.pointClass.prototype.applyOptions.apply(d,[a[i]]),j[i]=d.x,k[i]=q?this.toYData(d):d.y,l[i]=d.z,h&&d.name))h[d.x]=d.name;ea(k[0])&&ja(14,!0);this.data=[];this.options.data=a;this.xData=j;this.yData=k;this.zData=l;this.names=h;for(i=c&&c.length||0;i--;)c[i]&&c[i].destroy&&c[i].destroy();if(g)g.minRange=g.userMinRange;this.isDirty=this.isDirtyData=
e.isDirtyBox=!0;p(b,!0)&&e.redraw(!1)},remove:function(a,b){var c=this,d=c.chart,a=p(a,!0);if(!c.isRemoving)c.isRemoving=!0,K(c,"remove",null,function(){c.destroy();d.isDirtyLegend=d.isDirtyBox=!0;d.linkSeries();a&&d.redraw(b)});c.isRemoving=!1},processData:function(a){var b=this.xData,c=this.yData,d=b.length,e;e=0;var f,g,h=this.xAxis,i=this.options,j=i.cropThreshold,k=this.isCartesian;if(k&&!this.isDirty&&!h.isDirty&&!this.yAxis.isDirty&&!a)return!1;if(k&&this.sorted&&(!j||d>j||this.forceCrop))if(a=
h.min,h=h.max,b[d-1]<a||b[0]>h)b=[],c=[];else if(b[0]<a||b[d-1]>h)e=this.cropData(this.xData,this.yData,a,h),b=e.xData,c=e.yData,e=e.start,f=!0;for(h=b.length-1;h>=0;h--)d=b[h]-b[h-1],d>0&&(g===v||d<g)?g=d:d<0&&this.requireSorting&&ja(15);this.cropped=f;this.cropStart=e;this.processedXData=b;this.processedYData=c;if(i.pointRange===null)this.pointRange=g||1;this.closestPointRange=g},cropData:function(a,b,c,d){var e=a.length,f=0,g=e,h;for(h=0;h<e;h++)if(a[h]>=c){f=r(0,h-1);break}for(;h<e;h++)if(a[h]>
d){g=h+1;break}return{xData:a.slice(f,g),yData:b.slice(f,g),start:f,end:g}},generatePoints:function(){var a=this.options.data,b=this.data,c,d=this.processedXData,e=this.processedYData,f=this.pointClass,g=d.length,h=this.cropStart||0,i,j=this.hasGroupedData,k,l=[],m;if(!b&&!j)b=[],b.length=a.length,b=this.data=b;for(m=0;m<g;m++)i=h+m,j?l[m]=(new f).init(this,[d[m]].concat(ia(e[m]))):(b[i]?k=b[i]:a[i]!==v&&(b[i]=k=(new f).init(this,a[i],d[m])),l[m]=k);if(b&&(g!==(c=b.length)||j))for(m=0;m<c;m++)if(m===
h&&!j&&(m+=g),b[m])b[m].destroyElements(),b[m].plotX=v;this.data=b;this.points=l},setStackedPoints:function(){if(this.options.stacking&&!(this.visible!==!0&&this.chart.options.chart.ignoreHiddenSeries!==!1)){var a=this.processedXData,b=this.processedYData,c=b.length,d=this.options,e=d.threshold,f=d.stack,d=d.stacking,g=this.stackKey,h="-"+g,i=this.yAxis,j=i.stacks,k=i.oldStacks,l=i.stackExtremes,m,o,q,p,n;for(q=0;q<c;q++){p=a[q];n=b[q];o=(m=this.negStacks&&n<e)?h:g;typeof n==="number"&&!l[g]&&(l[g]=
{dataMin:n,dataMax:n});j[o]||(j[o]={});if(!j[o][p])k[o]&&k[o][p]?(j[o][p]=k[o][p],j[o][p].total=null):j[o][p]=new Mb(i,i.options.stackLabels,m,p,f,d);o=j[o][p];m=o.total;o.addValue(n||0);o.cacheExtremes(this,[m,m+(n||0)]);if(typeof n==="number")l[g].dataMin=C(l[g].dataMin,o.total,n),l[g].dataMax=r(l[g].dataMax,o.total,n)}i.oldStacks={}}},getExtremes:function(){var a=this.yAxis,b=this.stackKey,c,d,e=this.options,f=a.isLog?null:e.threshold,g=this.processedXData,h=this.processedYData,i=h.length,j=[],
k=0,l=this.xAxis.getExtremes(),m=l.min,l=l.max,o;if(e.stacking)c=a.stackExtremes[b],d=c.dataMin,c=c.dataMax,d=C(d,p(f,d)),c=r(c,p(f,c));if(!t(d)||!t(c)){for(b=0;b<i;b++)if(o=g[b],f=h[b],e=f!==null&&f!==v&&(!a.isLog||f.length||f>0),o=this.getExtremesFromAll||this.cropped||(g[b+1]||o)>=m&&(g[b-1]||o)<=l,e&&o)if(e=f.length)for(;e--;)f[e]!==null&&(j[k++]=f[e]);else j[k++]=f;d=p(d,Ia(j));c=p(c,ua(j))}this.dataMin=d;this.dataMax=c},translate:function(){this.processedXData||this.processData();this.generatePoints();
for(var a=this.options,b=a.stacking,c=this.xAxis,d=c.categories,e=this.yAxis,f=this.points,g=f.length,h=!!this.modifyValue,i=a.pointPlacement,j=i==="between"||qa(i),k=a.threshold,a=0;a<g;a++){var l=f[a],m=l.x,o=l.y,q=l.low,n=e.stacks[(this.negStacks&&o<k?"-":"")+this.stackKey],r;if(e.isLog&&o<=0)l.y=o=null;l.plotX=c.translate(m,0,0,0,1,i);if(b&&this.visible&&n&&n[m])n=n[m],r=n.total,n.cum=q=n.cum-o,o=q+o,n.cum===0&&(q=p(k,e.min)),e.isLog&&q<=0&&(q=null),b==="percent"&&(q=r?q*100/r:0,o=r?o*100/r:0),
l.percentage=r?l.y*100/r:0,l.total=l.stackTotal=r,l.stackY=o,n.setOffset(this.pointXOffset||0,this.barW||0);l.yBottom=t(q)?e.translate(q,0,1,0,1):null;h&&(o=this.modifyValue(o,l));l.plotY=typeof o==="number"&&o!==Infinity?e.translate(o,0,1,0,1):v;l.clientX=j?c.translate(m,0,0,0,1):l.plotX;l.negative=l.y<(k||0);l.category=d&&d[l.x]!==v?d[l.x]:l.x}this.getSegments()},setTooltipPoints:function(a){var b=[],c,d,e=(c=this.xAxis)?c.tooltipLen||c.len:this.chart.plotSizeX,f,g,h,i=[];if(this.options.enableMouseTracking!==
!1){if(a)this.tooltipPoints=null;n(this.segments||this.points,function(a){b=b.concat(a)});c&&c.reversed&&(b=b.reverse());this.orderTooltipPoints&&this.orderTooltipPoints(b);a=b.length;for(h=0;h<a;h++){f=b[h];g=b[h+1];c=b[h-1]?d+1:0;for(d=b[h+1]?C(r(0,P((f.clientX+(g?g.wrappedClientX||g.clientX:e))/2)),e):e;c>=0&&c<=d;)i[c++]=f}this.tooltipPoints=i}},tooltipHeaderFormatter:function(a){var b=this.tooltipOptions,c=b.xDateFormat,d=b.dateTimeLabelFormats,e=this.xAxis,f=e&&e.options.type==="datetime",b=
b.headerFormat,e=e&&e.closestPointRange,g;if(f&&!c)if(e)for(g in H){if(H[g]>=e){c=d[g];break}}else c=d.day;f&&c&&qa(a.key)&&(b=b.replace("{point.key}","{point.key:"+c+"}"));return Ba(b,{point:a,series:this})},onMouseOver:function(){var a=this.chart,b=a.hoverSeries;if(b&&b!==this)b.onMouseOut();this.options.events.mouseOver&&K(this,"mouseOver");this.setState("hover");a.hoverSeries=this},onMouseOut:function(){var a=this.options,b=this.chart,c=b.tooltip,d=b.hoverPoint;if(d)d.onMouseOut();this&&a.events.mouseOut&&
K(this,"mouseOut");c&&!a.stickyTracking&&(!c.shared||this.noSharedTooltip)&&c.hide();this.setState();b.hoverSeries=null},animate:function(a){var b=this,c=b.chart,d=c.renderer,e;e=b.options.animation;var f=c.clipBox,g=c.inverted,h;if(e&&!T(e))e=Y[b.type].animation;h="_sharedClip"+e.duration+e.easing;if(a)a=c[h],e=c[h+"m"],a||(c[h]=a=d.clipRect(s(f,{width:0})),c[h+"m"]=e=d.clipRect(-99,g?-c.plotLeft:-c.plotTop,99,g?c.chartWidth:c.chartHeight)),b.group.clip(a),b.markerGroup.clip(e),b.sharedClipKey=h;
else{if(a=c[h])a.animate({width:c.plotSizeX},e),c[h+"m"].animate({width:c.plotSizeX+99},e);b.animate=null;b.animationTimeout=setTimeout(function(){b.afterAnimate()},e.duration)}},afterAnimate:function(){var a=this.chart,b=this.sharedClipKey,c=this.group;c&&this.options.clip!==!1&&(c.clip(a.clipRect),this.markerGroup.clip());setTimeout(function(){b&&a[b]&&(a[b]=a[b].destroy(),a[b+"m"]=a[b+"m"].destroy())},100)},drawPoints:function(){var a,b=this.points,c=this.chart,d,e,f,g,h,i,j,k,l=this.options.marker,
m,o=this.markerGroup;if(l.enabled||this._hasPointMarkers)for(f=b.length;f--;)if(g=b[f],d=P(g.plotX),e=g.plotY,k=g.graphic,i=g.marker||{},a=l.enabled&&i.enabled===v||i.enabled,m=c.isInsidePlot(u(d),e,c.inverted),a&&e!==v&&!isNaN(e)&&g.y!==null)if(a=g.pointAttr[g.selected?"select":""],h=a.r,i=p(i.symbol,this.symbol),j=i.indexOf("url")===0,k)k.attr({visibility:m?Z?"inherit":"visible":"hidden"}).animate(s({x:d-h,y:e-h},k.symbolName?{width:2*h,height:2*h}:{}));else{if(m&&(h>0||j))g.graphic=c.renderer.symbol(i,
d-h,e-h,2*h,2*h).attr(a).add(o)}else if(k)g.graphic=k.destroy()},convertAttribs:function(a,b,c,d){var e=this.pointAttrToOptions,f,g,h={},a=a||{},b=b||{},c=c||{},d=d||{};for(f in e)g=e[f],h[f]=p(a[g],b[f],c[f],d[f]);return h},getAttribs:function(){var a=this,b=a.options,c=Y[a.type].marker?b.marker:b,d=c.states,e=d.hover,f,g=a.color,h={stroke:g,fill:g},i=a.points||[],j=[],k,l=a.pointAttrToOptions,m=b.negativeColor,o;b.marker?(e.radius=e.radius||c.radius+2,e.lineWidth=e.lineWidth||c.lineWidth+1):e.color=
e.color||pa(e.color||g).brighten(e.brightness).get();j[""]=a.convertAttribs(c,h);n(["hover","select"],function(b){j[b]=a.convertAttribs(d[b],j[""])});a.pointAttr=j;for(g=i.length;g--;){h=i[g];if((c=h.options&&h.options.marker||h.options)&&c.enabled===!1)c.radius=0;if(h.negative&&m)h.color=h.fillColor=m;f=b.colorByPoint||h.color;if(h.options)for(o in l)t(c[l[o]])&&(f=!0);if(f){c=c||{};k=[];d=c.states||{};f=d.hover=d.hover||{};if(!b.marker)f.color=pa(f.color||h.color).brighten(f.brightness||e.brightness).get();
k[""]=a.convertAttribs(s({color:h.color},c),j[""]);k.hover=a.convertAttribs(d.hover,j.hover,k[""]);k.select=a.convertAttribs(d.select,j.select,k[""]);if(h.negative&&b.marker&&m)k[""].fill=k.hover.fill=k.select.fill=a.convertAttribs({fillColor:m}).fill}else k=j;h.pointAttr=k}},update:function(a,b){var c=this.chart,d=this.type,a=x(this.userOptions,{animation:!1,index:this.index,pointStart:this.xData[0]},{data:this.options.data},a);this.remove(!1);s(this,aa[a.type||d].prototype);this.init(c,a);p(b,!0)&&
c.redraw(!1)},destroy:function(){var a=this,b=a.chart,c=/AppleWebKit\/533/.test(Ea),d,e,f=a.data||[],g,h,i;K(a,"destroy");ba(a);n(["xAxis","yAxis"],function(b){if(i=a[b])ga(i.series,a),i.isDirty=i.forceRedraw=!0});a.legendItem&&a.chart.legend.destroyItem(a);for(e=f.length;e--;)(g=f[e])&&g.destroy&&g.destroy();a.points=null;clearTimeout(a.animationTimeout);n("area,graph,dataLabelsGroup,group,markerGroup,tracker,graphNeg,areaNeg,posClip,negClip".split(","),function(b){a[b]&&(d=c&&b==="group"?"hide":
"destroy",a[b][d]())});if(b.hoverSeries===a)b.hoverSeries=null;ga(b.series,a);for(h in a)delete a[h]},drawDataLabels:function(){var a=this,b=a.options.dataLabels,c=a.points,d,e,f,g;if(b.enabled||a._hasPointLabels)a.dlProcessOptions&&a.dlProcessOptions(b),g=a.plotGroup("dataLabelsGroup","data-labels",a.visible?"visible":"hidden",b.zIndex||6),e=b,n(c,function(c){var i,j=c.dataLabel,k,l,m=c.connector,o=!0;d=c.options&&c.options.dataLabels;i=e.enabled||d&&d.enabled;if(j&&!i)c.dataLabel=j.destroy();else if(i){b=
x(e,d);i=b.rotation;k=c.getLabelConfig();f=b.format?Ba(b.format,k):b.formatter.call(k,b);b.style.color=p(b.color,b.style.color,a.color,"black");if(j)if(t(f))j.attr({text:f}),o=!1;else{if(c.dataLabel=j=j.destroy(),m)c.connector=m.destroy()}else if(t(f)){j={fill:b.backgroundColor,stroke:b.borderColor,"stroke-width":b.borderWidth,r:b.borderRadius||0,rotation:i,padding:b.padding,zIndex:1};for(l in j)j[l]===v&&delete j[l];j=c.dataLabel=a.chart.renderer[i?"text":"label"](f,0,-999,null,null,null,b.useHTML).attr(j).css(b.style).add(g).shadow(b.shadow)}j&&
a.alignDataLabel(c,j,b,null,o)}})},alignDataLabel:function(a,b,c,d,e){var f=this.chart,g=f.inverted,h=p(a.plotX,-999),i=p(a.plotY,-999),a=b.getBBox(),d=s({x:g?f.plotWidth-i:h,y:u(g?f.plotHeight-h:i),width:0,height:0},d);s(c,{width:a.width,height:a.height});c.rotation?(d={align:c.align,x:d.x+c.x+d.width/2,y:d.y+c.y+d.height/2},b[e?"attr":"animate"](d)):(b.align(c,null,d),d=b.alignAttr);b.attr({visibility:c.crop===!1||f.isInsidePlot(d.x,d.y)&&f.isInsidePlot(d.x+a.width,d.y+a.height)?f.renderer.isSVG?
"inherit":"visible":"hidden"})},getSegmentPath:function(a){var b=this,c=[],d=b.options.step;n(a,function(e,f){var g=e.plotX,h=e.plotY,i;b.getPointSpline?c.push.apply(c,b.getPointSpline(a,e,f)):(c.push(f?"L":"M"),d&&f&&(i=a[f-1],d==="right"?c.push(i.plotX,h):d==="center"?c.push((i.plotX+g)/2,i.plotY,(i.plotX+g)/2,h):c.push(g,i.plotY)),c.push(e.plotX,e.plotY))});return c},getGraphPath:function(){var a=this,b=[],c,d=[];n(a.segments,function(e){c=a.getSegmentPath(e);e.length>1?b=b.concat(c):d.push(e[0])});
a.singlePoints=d;return a.graphPath=b},drawGraph:function(){var a=this,b=this.options,c=[["graph",b.lineColor||this.color]],d=b.lineWidth,e=b.dashStyle,f=this.getGraphPath(),g=b.negativeColor;g&&c.push(["graphNeg",g]);n(c,function(c,g){var j=c[0],k=a[j];if(k)Wa(k),k.animate({d:f});else if(d&&f.length){k={stroke:c[1],"stroke-width":d,zIndex:1};if(e)k.dashstyle=e;a[j]=a.chart.renderer.path(f).attr(k).add(a.group).shadow(!g&&b.shadow)}})},clipNeg:function(){var a=this.options,b=this.chart,c=b.renderer,
d=a.negativeColor||a.negativeFillColor,e,f=this.graph,g=this.area,h=this.posClip,i=this.negClip;e=b.chartWidth;var j=b.chartHeight,k=r(e,j),l=this.yAxis;if(d&&(f||g)){d=u(l.toPixels(a.threshold||0,!0));a={x:0,y:0,width:k,height:d};k={x:0,y:d,width:k,height:k};if(b.inverted)a.height=k.y=b.plotWidth-d,c.isVML&&(a={x:b.plotWidth-d-b.plotLeft,y:0,width:e,height:j},k={x:d+b.plotLeft-e,y:0,width:b.plotLeft+d,height:e});l.reversed?(b=k,e=a):(b=a,e=k);h?(h.animate(b),i.animate(e)):(this.posClip=h=c.clipRect(b),
this.negClip=i=c.clipRect(e),f&&this.graphNeg&&(f.clip(h),this.graphNeg.clip(i)),g&&(g.clip(h),this.areaNeg.clip(i)))}},invertGroups:function(){function a(){var a={width:b.yAxis.len,height:b.xAxis.len};n(["group","markerGroup"],function(c){b[c]&&b[c].attr(a).invert()})}var b=this,c=b.chart;if(b.xAxis)J(c,"resize",a),J(b,"destroy",function(){ba(c,"resize",a)}),a(),b.invertGroups=a},plotGroup:function(a,b,c,d,e){var f=this[a],g=!f;g&&(this[a]=f=this.chart.renderer.g(b).attr({visibility:c,zIndex:d||
0.1}).add(e));f[g?"attr":"animate"](this.getPlotBox());return f},getPlotBox:function(){return{translateX:this.xAxis?this.xAxis.left:this.chart.plotLeft,translateY:this.yAxis?this.yAxis.top:this.chart.plotTop,scaleX:1,scaleY:1}},render:function(){var a=this.chart,b,c=this.options,d=c.animation&&!!this.animate&&a.renderer.isSVG,e=this.visible?"visible":"hidden",f=c.zIndex,g=this.hasRendered,h=a.seriesGroup;b=this.plotGroup("group","series",e,f,h);this.markerGroup=this.plotGroup("markerGroup","markers",
e,f,h);d&&this.animate(!0);this.getAttribs();b.inverted=this.isCartesian?a.inverted:!1;this.drawGraph&&(this.drawGraph(),this.clipNeg());this.drawDataLabels();this.drawPoints();this.options.enableMouseTracking!==!1&&this.drawTracker();a.inverted&&this.invertGroups();c.clip!==!1&&!this.sharedClipKey&&!g&&b.clip(a.clipRect);d?this.animate():g||this.afterAnimate();this.isDirty=this.isDirtyData=!1;this.hasRendered=!0},redraw:function(){var a=this.chart,b=this.isDirtyData,c=this.group,d=this.xAxis,e=this.yAxis;
c&&(a.inverted&&c.attr({width:a.plotWidth,height:a.plotHeight}),c.animate({translateX:p(d&&d.left,a.plotLeft),translateY:p(e&&e.top,a.plotTop)}));this.translate();this.setTooltipPoints(!0);this.render();b&&K(this,"updatedData")},setState:function(a){var b=this.options,c=this.graph,d=this.graphNeg,e=b.states,b=b.lineWidth,a=a||"";if(this.state!==a)this.state=a,e[a]&&e[a].enabled===!1||(a&&(b=e[a].lineWidth||b+1),c&&!c.dashstyle&&(a={"stroke-width":b},c.attr(a),d&&d.attr(a)))},setVisible:function(a,
b){var c=this,d=c.chart,e=c.legendItem,f,g=d.options.chart.ignoreHiddenSeries,h=c.visible;f=(c.visible=a=c.userOptions.visible=a===v?!h:a)?"show":"hide";n(["group","dataLabelsGroup","markerGroup","tracker"],function(a){if(c[a])c[a][f]()});if(d.hoverSeries===c)c.onMouseOut();e&&d.legend.colorizeItem(c,a);c.isDirty=!0;c.options.stacking&&n(d.series,function(a){if(a.options.stacking&&a.visible)a.isDirty=!0});n(c.linkedSeries,function(b){b.setVisible(a,!1)});if(g)d.isDirtyBox=!0;b!==!1&&d.redraw();K(c,
f)},show:function(){this.setVisible(!0)},hide:function(){this.setVisible(!1)},select:function(a){this.selected=a=a===v?!this.selected:a;if(this.checkbox)this.checkbox.checked=a;K(this,a?"select":"unselect")},drawTracker:function(){var a=this,b=a.options,c=b.trackByArea,d=[].concat(c?a.areaPath:a.graphPath),e=d.length,f=a.chart,g=f.pointer,h=f.renderer,i=f.options.tooltip.snap,j=a.tracker,k=b.cursor,l=k&&{cursor:k},k=a.singlePoints,m,o=function(){if(f.hoverSeries!==a)a.onMouseOver()};if(e&&!c)for(m=
e+1;m--;)d[m]==="M"&&d.splice(m+1,0,d[m+1]-i,d[m+2],"L"),(m&&d[m]==="M"||m===e)&&d.splice(m,0,"L",d[m-2]+i,d[m-1]);for(m=0;m<k.length;m++)e=k[m],d.push("M",e.plotX-i,e.plotY,"L",e.plotX+i,e.plotY);j?j.attr({d:d}):(a.tracker=h.path(d).attr({"stroke-linejoin":"round",visibility:a.visible?"visible":"hidden",stroke:Qb,fill:c?Qb:S,"stroke-width":b.lineWidth+(c?0:2*i),zIndex:2}).add(a.group),n([a.tracker,a.markerGroup],function(a){a.addClass("highcharts-tracker").on("mouseover",o).on("mouseout",function(a){g.onTrackerMouseOut(a)}).css(l);
if(hb)a.on("touchstart",o)}))}};F=ha(Q);aa.line=F;Y.area=x(X,{threshold:0});F=ha(Q,{type:"area",getSegments:function(){var a=[],b=[],c=[],d=this.xAxis,e=this.yAxis,f=e.stacks[this.stackKey],g={},h,i,j=this.points,k,l,m;if(this.options.stacking&&!this.cropped){for(l=0;l<j.length;l++)g[j[l].x]=j[l];for(m in f)c.push(+m);c.sort(function(a,b){return a-b});n(c,function(a){g[a]?b.push(g[a]):(h=d.translate(a),k=f[a].percent?f[a].total?f[a].cum*100/f[a].total:0:f[a].cum,i=e.toPixels(k,!0),b.push({y:null,
plotX:h,clientX:h,plotY:i,yBottom:i,onMouseOver:ya}))});b.length&&a.push(b)}else Q.prototype.getSegments.call(this),a=this.segments;this.segments=a},getSegmentPath:function(a){var b=Q.prototype.getSegmentPath.call(this,a),c=[].concat(b),d,e=this.options;b.length===3&&c.push("L",b[1],b[2]);if(e.stacking&&!this.closedStacks)for(d=a.length-1;d>=0;d--)d<a.length-1&&e.step&&c.push(a[d+1].plotX,a[d].yBottom),c.push(a[d].plotX,a[d].yBottom);else this.closeSegment(c,a);this.areaPath=this.areaPath.concat(c);
return b},closeSegment:function(a,b){var c=this.yAxis.getThreshold(this.options.threshold);a.push("L",b[b.length-1].plotX,c,"L",b[0].plotX,c)},drawGraph:function(){this.areaPath=[];Q.prototype.drawGraph.apply(this);var a=this,b=this.areaPath,c=this.options,d=c.negativeColor,e=c.negativeFillColor,f=[["area",this.color,c.fillColor]];(d||e)&&f.push(["areaNeg",d,e]);n(f,function(d){var e=d[0],f=a[e];f?f.animate({d:b}):a[e]=a.chart.renderer.path(b).attr({fill:p(d[2],pa(d[1]).setOpacity(p(c.fillOpacity,
0.75)).get()),zIndex:0}).add(a.group)})},drawLegendSymbol:function(a,b){b.legendSymbol=this.chart.renderer.rect(0,a.baseline-11,a.options.symbolWidth,12,2).attr({zIndex:3}).add(b.legendGroup)}});aa.area=F;Y.spline=x(X);D=ha(Q,{type:"spline",getPointSpline:function(a,b,c){var d=b.plotX,e=b.plotY,f=a[c-1],g=a[c+1],h,i,j,k;if(f&&g){a=f.plotY;j=g.plotX;var g=g.plotY,l;h=(1.5*d+f.plotX)/2.5;i=(1.5*e+a)/2.5;j=(1.5*d+j)/2.5;k=(1.5*e+g)/2.5;l=(k-i)*(j-d)/(j-h)+e-k;i+=l;k+=l;i>a&&i>e?(i=r(a,e),k=2*e-i):i<
a&&i<e&&(i=C(a,e),k=2*e-i);k>g&&k>e?(k=r(g,e),i=2*e-k):k<g&&k<e&&(k=C(g,e),i=2*e-k);b.rightContX=j;b.rightContY=k}c?(b=["C",f.rightContX||f.plotX,f.rightContY||f.plotY,h||d,i||e,d,e],f.rightContX=f.rightContY=null):b=["M",d,e];return b}});aa.spline=D;Y.areaspline=x(Y.area);ma=F.prototype;D=ha(D,{type:"areaspline",closedStacks:!0,getSegmentPath:ma.getSegmentPath,closeSegment:ma.closeSegment,drawGraph:ma.drawGraph,drawLegendSymbol:ma.drawLegendSymbol});aa.areaspline=D;Y.column=x(X,{borderColor:"#FFFFFF",
borderWidth:1,borderRadius:0,groupPadding:0.2,marker:null,pointPadding:0.1,minPointLength:0,cropThreshold:50,pointRange:null,states:{hover:{brightness:0.1,shadow:!1},select:{color:"#C0C0C0",borderColor:"#000000",shadow:!1}},dataLabels:{align:null,verticalAlign:null,y:null},stickyTracking:!1,threshold:0});D=ha(Q,{type:"column",pointAttrToOptions:{stroke:"borderColor","stroke-width":"borderWidth",fill:"color",r:"borderRadius"},trackerGroups:["group","dataLabelsGroup"],negStacks:!0,init:function(){Q.prototype.init.apply(this,
arguments);var a=this,b=a.chart;b.hasRendered&&n(b.series,function(b){if(b.type===a.type)b.isDirty=!0})},getColumnMetrics:function(){var a=this,b=a.options,c=a.xAxis,d=a.yAxis,e=c.reversed,f,g={},h,i=0;b.grouping===!1?i=1:n(a.chart.series,function(b){var c=b.options,e=b.yAxis;if(b.type===a.type&&b.visible&&d.len===e.len&&d.pos===e.pos)c.stacking?(f=b.stackKey,g[f]===v&&(g[f]=i++),h=g[f]):c.grouping!==!1&&(h=i++),b.columnIndex=h});var c=C(O(c.transA)*(c.ordinalSlope||b.pointRange||c.closestPointRange||
1),c.len),j=c*b.groupPadding,k=(c-2*j)/i,l=b.pointWidth,b=t(l)?(k-l)/2:k*b.pointPadding,l=p(l,k-2*b);return a.columnMetrics={width:l,offset:b+(j+((e?i-(a.columnIndex||0):a.columnIndex)||0)*k-c/2)*(e?-1:1)}},translate:function(){var a=this.chart,b=this.options,c=b.borderWidth,d=this.yAxis,e=this.translatedThreshold=d.getThreshold(b.threshold),f=p(b.minPointLength,5),b=this.getColumnMetrics(),g=b.width,h=this.barW=wa(r(g,1+2*c)),i=this.pointXOffset=b.offset,j=-(c%2?0.5:0),k=c%2?0.5:1;a.renderer.isVML&&
a.inverted&&(k+=1);Q.prototype.translate.apply(this);n(this.points,function(a){var b=C(r(-999,a.plotY),d.len+999),c=p(a.yBottom,e),n=a.plotX+i,t=h,s=C(b,c),w,b=r(b,c)-s;O(b)<f&&f&&(b=f,s=u(O(s-e)>f?c-f:e-(d.translate(a.y,0,1,0,1)<=e?f:0)));a.barX=n;a.pointWidth=g;c=O(n)<0.5;t=u(n+t)+j;n=u(n)+j;t-=n;w=O(s)<0.5;b=u(s+b)+k;s=u(s)+k;b-=s;c&&(n+=1,t-=1);w&&(s-=1,b+=1);a.shapeType="rect";a.shapeArgs={x:n,y:s,width:t,height:b}})},getSymbol:ya,drawLegendSymbol:F.prototype.drawLegendSymbol,drawGraph:ya,drawPoints:function(){var a=
this,b=a.options,c=a.chart.renderer,d;n(a.points,function(e){var f=e.plotY,g=e.graphic;if(f!==v&&!isNaN(f)&&e.y!==null)d=e.shapeArgs,g?(Wa(g),g.animate(x(d))):e.graphic=c[e.shapeType](d).attr(e.pointAttr[e.selected?"select":""]).add(a.group).shadow(b.shadow,null,b.stacking&&!b.borderRadius);else if(g)e.graphic=g.destroy()})},drawTracker:function(){var a=this,b=a.chart,c=b.pointer,d=a.options.cursor,e=d&&{cursor:d},f=function(c){var d=c.target,e;if(b.hoverSeries!==a)a.onMouseOver();for(;d&&!e;)e=d.point,
d=d.parentNode;if(e!==v&&e!==b.hoverPoint)e.onMouseOver(c)};n(a.points,function(a){if(a.graphic)a.graphic.element.point=a;if(a.dataLabel)a.dataLabel.element.point=a});if(!a._hasTracking)n(a.trackerGroups,function(b){if(a[b]&&(a[b].addClass("highcharts-tracker").on("mouseover",f).on("mouseout",function(a){c.onTrackerMouseOut(a)}).css(e),hb))a[b].on("touchstart",f)}),a._hasTracking=!0},alignDataLabel:function(a,b,c,d,e){var f=this.chart,g=f.inverted,h=a.dlBox||a.shapeArgs,i=a.below||a.plotY>p(this.translatedThreshold,
f.plotSizeY),j=p(c.inside,!!this.options.stacking);if(h&&(d=x(h),g&&(d={x:f.plotWidth-d.y-d.height,y:f.plotHeight-d.x-d.width,width:d.height,height:d.width}),!j))g?(d.x+=i?0:d.width,d.width=0):(d.y+=i?d.height:0,d.height=0);c.align=p(c.align,!g||j?"center":i?"right":"left");c.verticalAlign=p(c.verticalAlign,g||j?"middle":i?"top":"bottom");Q.prototype.alignDataLabel.call(this,a,b,c,d,e)},animate:function(a){var b=this.yAxis,c=this.options,d=this.chart.inverted,e={};if(Z)a?(e.scaleY=0.001,a=C(b.pos+
b.len,r(b.pos,b.toPixels(c.threshold))),d?e.translateX=a-b.len:e.translateY=a,this.group.attr(e)):(e.scaleY=1,e[d?"translateX":"translateY"]=b.pos,this.group.animate(e,this.options.animation),this.animate=null)},remove:function(){var a=this,b=a.chart;b.hasRendered&&n(b.series,function(b){if(b.type===a.type)b.isDirty=!0});Q.prototype.remove.apply(a,arguments)}});aa.column=D;Y.bar=x(Y.column);ma=ha(D,{type:"bar",inverted:!0});aa.bar=ma;Y.scatter=x(X,{lineWidth:0,tooltip:{headerFormat:'<span style="font-size: 10px; color:{series.color}">{series.name}</span><br/>',
pointFormat:"x: <b>{point.x}</b><br/>y: <b>{point.y}</b><br/>",followPointer:!0},stickyTracking:!1});ma=ha(Q,{type:"scatter",sorted:!1,requireSorting:!1,noSharedTooltip:!0,trackerGroups:["markerGroup"],drawTracker:D.prototype.drawTracker,setTooltipPoints:ya});aa.scatter=ma;Y.pie=x(X,{borderColor:"#FFFFFF",borderWidth:1,center:[null,null],clip:!1,colorByPoint:!0,dataLabels:{distance:30,enabled:!0,formatter:function(){return this.point.name}},ignoreHiddenPoint:!0,legendType:"point",marker:null,size:null,
showInLegend:!1,slicedOffset:10,states:{hover:{brightness:0.1,shadow:!1}},stickyTracking:!1,tooltip:{followPointer:!0}});X={type:"pie",isCartesian:!1,pointClass:ha(Pa,{init:function(){Pa.prototype.init.apply(this,arguments);var a=this,b;if(a.y<0)a.y=null;s(a,{visible:a.visible!==!1,name:p(a.name,"Slice")});b=function(b){a.slice(b.type==="select")};J(a,"select",b);J(a,"unselect",b);return a},setVisible:function(a){var b=this,c=b.series,d=c.chart,e;b.visible=b.options.visible=a=a===v?!b.visible:a;c.options.data[oa(b,
c.data)]=b.options;e=a?"show":"hide";n(["graphic","dataLabel","connector","shadowGroup"],function(a){if(b[a])b[a][e]()});b.legendItem&&d.legend.colorizeItem(b,a);if(!c.isDirty&&c.options.ignoreHiddenPoint)c.isDirty=!0,d.redraw()},slice:function(a,b,c){var d=this.series;Ka(c,d.chart);p(b,!0);this.sliced=this.options.sliced=a=t(a)?a:!this.sliced;d.options.data[oa(this,d.data)]=this.options;a=a?this.slicedTranslation:{translateX:0,translateY:0};this.graphic.animate(a);this.shadowGroup&&this.shadowGroup.animate(a)}}),
requireSorting:!1,noSharedTooltip:!0,trackerGroups:["group","dataLabelsGroup"],pointAttrToOptions:{stroke:"borderColor","stroke-width":"borderWidth",fill:"color"},getColor:ya,animate:function(a){var b=this,c=b.points,d=b.startAngleRad;if(!a)n(c,function(a){var c=a.graphic,a=a.shapeArgs;c&&(c.attr({r:b.center[3]/2,start:d,end:d}),c.animate({r:a.r,start:a.start,end:a.end},b.options.animation))}),b.animate=null},setData:function(a,b){Q.prototype.setData.call(this,a,!1);this.processData();this.generatePoints();
p(b,!0)&&this.chart.redraw()},generatePoints:function(){var a,b=0,c,d,e,f=this.options.ignoreHiddenPoint;Q.prototype.generatePoints.call(this);c=this.points;d=c.length;for(a=0;a<d;a++)e=c[a],b+=f&&!e.visible?0:e.y;this.total=b;for(a=0;a<d;a++)e=c[a],e.percentage=b>0?e.y/b*100:0,e.total=b},getCenter:function(){var a=this.options,b=this.chart,c=2*(a.slicedOffset||0),d,e=b.plotWidth-2*c,f=b.plotHeight-2*c,b=a.center,a=[p(b[0],"50%"),p(b[1],"50%"),a.size||"100%",a.innerSize||0],g=C(e,f),h;return Na(a,
function(a,b){h=/%$/.test(a);d=b<2||b===2&&h;return(h?[e,f,g,g][b]*A(a)/100:a)+(d?c:0)})},translate:function(a){this.generatePoints();var b=0,c=this.options,d=c.slicedOffset,e=d+c.borderWidth,f,g,h,i=this.startAngleRad=Ma/180*((c.startAngle||0)%360-90),j=this.points,k=2*Ma,l=c.dataLabels.distance,c=c.ignoreHiddenPoint,m,n=j.length,p;if(!a)this.center=a=this.getCenter();this.getX=function(b,c){h=R.asin((b-a[1])/(a[2]/2+l));return a[0]+(c?-1:1)*W(h)*(a[2]/2+l)};for(m=0;m<n;m++){p=j[m];f=u((i+b*k)*1E3)/
1E3;if(!c||p.visible)b+=p.percentage/100;g=u((i+b*k)*1E3)/1E3;p.shapeType="arc";p.shapeArgs={x:a[0],y:a[1],r:a[2]/2,innerR:a[3]/2,start:f,end:g};h=(g+f)/2;h>0.75*k&&(h-=2*Ma);p.slicedTranslation={translateX:u(W(h)*d),translateY:u(ca(h)*d)};f=W(h)*a[2]/2;g=ca(h)*a[2]/2;p.tooltipPos=[a[0]+f*0.7,a[1]+g*0.7];p.half=h<k/4?0:1;p.angle=h;e=C(e,l/2);p.labelPos=[a[0]+f+W(h)*l,a[1]+g+ca(h)*l,a[0]+f+W(h)*e,a[1]+g+ca(h)*e,a[0]+f,a[1]+g,l<0?"center":p.half?"right":"left",h]}this.setTooltipPoints()},drawGraph:null,
drawPoints:function(){var a=this,b=a.chart.renderer,c,d,e=a.options.shadow,f,g;if(e&&!a.shadowGroup)a.shadowGroup=b.g("shadow").add(a.group);n(a.points,function(h){d=h.graphic;g=h.shapeArgs;f=h.shadowGroup;if(e&&!f)f=h.shadowGroup=b.g("shadow").add(a.shadowGroup);c=h.sliced?h.slicedTranslation:{translateX:0,translateY:0};f&&f.attr(c);d?d.animate(s(g,c)):h.graphic=d=b.arc(g).setRadialReference(a.center).attr(h.pointAttr[h.selected?"select":""]).attr({"stroke-linejoin":"round"}).attr(c).add(a.group).shadow(e,
f);h.visible===!1&&h.setVisible(!1)})},drawDataLabels:function(){var a=this,b=a.data,c,d=a.chart,e=a.options.dataLabels,f=p(e.connectorPadding,10),g=p(e.connectorWidth,1),h=d.plotWidth,d=d.plotHeight,i,j,k=p(e.softConnector,!0),l=e.distance,m=a.center,o=m[2]/2,q=m[1],t=l>0,s,w,v,x,A=[[],[]],z,E,G,I,B,C=[0,0,0,0],H=function(a,b){return b.y-a.y},K=function(a,b){a.sort(function(a,c){return a.angle!==void 0&&(c.angle-a.angle)*b})};if(a.visible&&(e.enabled||a._hasPointLabels)){Q.prototype.drawDataLabels.apply(a);
n(b,function(a){a.dataLabel&&A[a.half].push(a)});for(I=0;!x&&b[I];)x=b[I]&&b[I].dataLabel&&(b[I].dataLabel.getBBox().height||21),I++;for(I=2;I--;){var b=[],L=[],F=A[I],J=F.length,D;K(F,I-0.5);if(l>0){for(B=q-o-l;B<=q+o+l;B+=x)b.push(B);w=b.length;if(J>w){c=[].concat(F);c.sort(H);for(B=J;B--;)c[B].rank=B;for(B=J;B--;)F[B].rank>=w&&F.splice(B,1);J=F.length}for(B=0;B<J;B++){c=F[B];v=c.labelPos;c=9999;var N,M;for(M=0;M<w;M++)N=O(b[M]-v[1]),N<c&&(c=N,D=M);if(D<B&&b[B]!==null)D=B;else for(w<J-B+D&&b[B]!==
null&&(D=w-J+B);b[D]===null;)D++;L.push({i:D,y:b[D]});b[D]=null}L.sort(H)}for(B=0;B<J;B++){c=F[B];v=c.labelPos;s=c.dataLabel;G=c.visible===!1?"hidden":"visible";c=v[1];if(l>0){if(w=L.pop(),D=w.i,E=w.y,c>E&&b[D+1]!==null||c<E&&b[D-1]!==null)E=c}else E=c;z=e.justify?m[0]+(I?-1:1)*(o+l):a.getX(D===0||D===b.length-1?c:E,I);s._attr={visibility:G,align:v[6]};s._pos={x:z+e.x+({left:f,right:-f}[v[6]]||0),y:E+e.y-10};s.connX=z;s.connY=E;if(this.options.size===null)w=s.width,z-w<f?C[3]=r(u(w-z+f),C[3]):z+w>
h-f&&(C[1]=r(u(z+w-h+f),C[1])),E-x/2<0?C[0]=r(u(-E+x/2),C[0]):E+x/2>d&&(C[2]=r(u(E+x/2-d),C[2]))}}if(ua(C)===0||this.verifyDataLabelOverflow(C))this.placeDataLabels(),t&&g&&n(this.points,function(b){i=b.connector;v=b.labelPos;if((s=b.dataLabel)&&s._pos)G=s._attr.visibility,z=s.connX,E=s.connY,j=k?["M",z+(v[6]==="left"?5:-5),E,"C",z,E,2*v[2]-v[4],2*v[3]-v[5],v[2],v[3],"L",v[4],v[5]]:["M",z+(v[6]==="left"?5:-5),E,"L",v[2],v[3],"L",v[4],v[5]],i?(i.animate({d:j}),i.attr("visibility",G)):b.connector=i=
a.chart.renderer.path(j).attr({"stroke-width":g,stroke:e.connectorColor||b.color||"#606060",visibility:G}).add(a.group);else if(i)b.connector=i.destroy()})}},verifyDataLabelOverflow:function(a){var b=this.center,c=this.options,d=c.center,e=c=c.minSize||80,f;d[0]!==null?e=r(b[2]-r(a[1],a[3]),c):(e=r(b[2]-a[1]-a[3],c),b[0]+=(a[3]-a[1])/2);d[1]!==null?e=r(C(e,b[2]-r(a[0],a[2])),c):(e=r(C(e,b[2]-a[0]-a[2]),c),b[1]+=(a[0]-a[2])/2);e<b[2]?(b[2]=e,this.translate(b),n(this.points,function(a){if(a.dataLabel)a.dataLabel._pos=
null}),this.drawDataLabels()):f=!0;return f},placeDataLabels:function(){n(this.points,function(a){var a=a.dataLabel,b;if(a)(b=a._pos)?(a.attr(a._attr),a[a.moved?"animate":"attr"](b),a.moved=!0):a&&a.attr({y:-999})})},alignDataLabel:ya,drawTracker:D.prototype.drawTracker,drawLegendSymbol:F.prototype.drawLegendSymbol,getSymbol:ya};X=ha(Q,X);aa.pie=X;s(Highcharts,{Axis:db,Chart:xb,Color:pa,Legend:wb,Pointer:vb,Point:Pa,Tick:La,Tooltip:ub,Renderer:Va,Series:Q,SVGElement:va,SVGRenderer:Ga,arrayMin:Ia,
arrayMax:ua,charts:Fa,dateFormat:Xa,format:Ba,pathAnim:zb,getOptions:function(){return M},hasBidiBug:Ub,isTouchDevice:Ob,numberFormat:za,seriesTypes:aa,setOptions:function(a){M=x(M,a);Lb();return M},addEvent:J,removeEvent:ba,createElement:U,discardElement:Ta,css:L,each:n,extend:s,map:Na,merge:x,pick:p,splat:ia,extendClass:ha,pInt:A,wrap:Bb,svg:Z,canvas:$,vml:!Z&&!$,product:"Highcharts",version:"3.0.5"})})();

(function($){
    $.fn.UED_chart = function(option){
        function strToJson(str){
            str = str? str:"";
            return  eval( "({" + str + "})" );
        }
        return this.each(function(i, n){
            var  _box = $(n),
                _rel =  strToJson( _box.attr("data-rel"));

            /* 合并默认参数和用户自定义参数 */
            var def = {
                chart: {
                    plotShadow: false,
                    spacingTop:0,
                    spacingBottom:0,
                    spacingLeft:0,
                    spacingRight:0
                },
                title: {
                    text: '网站用户数量',
                    margin:5
                },
                xAxis: {
                    categories: ['1月', '2月', '3月', '4月', '5月', '6月']
                },
                yAxis: {
                    title: {
                        text: '用户数量'
                    }
                },
                legend:{
                    margin:5,
                    padding:5
                },
                series: [
                    {
                        type: 'line',
                        name: '2011',
                        data: [["1月",6350], ["2月",7120], ["3月",6937], ["4月",7302], ["5月",7349], ["6月",7129]]
                    },
                    {
                        type: 'line',
                        name: '2012',
                        data: [["1月",5350], ["2月",6120], ["3月",5937], ["4月",7302], ["5月",8349], ["6月",6129]]
                    }
                ]
            };

            var option = $.extend(true,def,option,_rel);

            if(option.series.length==2&&option.series[0]["type"]=="pie"){
                option.series.pop();
            }

            _box.highcharts(option);
        });

    };
    $(function(){
        $("*[data-role='ued-chart']").UED_chart();
    })
})(jQuery);
