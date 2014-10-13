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
            boxImage:'../pub-ui/css/'+uedSkin+'/images/progressbar/progressbar.gif', // boxImage : image around the progress bar
            barImage:{
                0:'../pub-ui/css/'+uedSkin+'/images/progressbar/progressbg_green.gif',
                30:'../pub-ui/css/'+uedSkin+'/images/progressbar/progressbg_orange.gif',
                70:'../pub-ui/css/'+uedSkin+'/images/progressbar/progressbg_red.gif'
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