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