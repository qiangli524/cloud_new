// 文件上传
(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('#uploader'),
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        // 状态栏，包括进度和控制按钮
        $statusBar = $wrap.find( '.statusBar' ),
        // 所有文件的进度信息，key为file id
        percentages = {},
        $progress = $statusBar.find( '.progress' ).hide(),
      /*  // 检测是否已经安装flash，检测flash的版本
        flashVersion = ( function() {
            var flashVer = NaN;
            var ua = navigator.userAgent;

            if ( window.ActiveXObject ) {
                var swf = new ActiveXObject( 'ShockwaveFlash.ShockwaveFlash' );

                if ( swf ) {
                    flashVer = Number( swf.GetVariable( '$version' ).split(' ')[ 1 ].replace(/\,/g, '.').replace( /^(\d+\.\d+).*$/, '$1') );
                }
            }
            else {
                if ( navigator.plugins && navigator.plugins.length > 0 ) {
                    var swf = navigator.plugins[ 'Shockwave Flash' ];

                    if ( swf ) {
                        var arr = swf.description.split(' ');
                        for ( var i = 0, len = arr.length; i < len; i++ ) {
                            var ver = Number( arr[ i ] );

                            if ( !isNaN( ver ) ) {
                                flashVer = ver;
                                break;
                            }
                        }
                    }
                }
            }

            return flashVer;
        } )(),
        supportTransition = (function(){
            var s = document.createElement('p').style,
                r = 'transition' in s ||
                        'WebkitTransition' in s ||
                        'MozTransition' in s ||
                        'msTransition' in s ||
                        'OTransition' in s;
            s = null;
            return r;
        })(),*/
    uploader;
    
   if ( !WebUploader.Uploader.support() ) {
            alert( 'Web Uploader 不支持您的浏览器！');
            throw new Error( 'WebUploader does not support the browser you are using.' );
        }

	var uploader = WebUploader.create({

	    // swf文件路径
	    swf: 'publicCloud/pub-ui/js/webuploader-0.1.0-dist/Uploader.swf',
	
	    // 文件接收服务端。
	    server: 'images_upload.do',
	
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '#picker',
	    
	    // 开起分片上传。
	    chunked: true,
	   
	});	
	
	
	// 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
	    $list.append( '<div id="' + file.id + '" class="item">' +
	        '<h4 class="info">' + file.name + '</h4>' +
	        '<p class="state">等待上传...</p>' +
	    '</div>' );
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
	    var $li = $( '#'+file.id ),
	        $percent = $li.find('.progress .progress-bar');
	
	    // 避免重复创建
	    if ( !$percent.length ) {
	        $percent = $('<div class="progress progress-striped active">' +
	          '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	          '</div>' +
	        '</div>').appendTo( $li ).find('.progress-bar');
	    }
	
	    $li.find('p.state').text('上传中');
	
	    $percent.css( 'width', percentage * 100 + '%' );
	});
	
	uploader.onUploadBeforeSend = function( file, data ) {
		var md5blob = md5Blob(file.blob);
		var md5source = md5Blob(file.blob.getSource());
        data.md5 = file.md5 || '';
    };

	//文件成功、失败处理
	uploader.on( 'uploadSuccess', function( file ) {
	    $( '#'+file.id ).find('p.state').text('已上传');
	});
	
	uploader.on( 'uploadError', function( file ) {
	    $( '#'+file.id ).find('p.state').text('上传出错');
	});
	
	uploader.on( 'uploadComplete', function( file ) {
	    $( '#'+file.id ).find('.progress').fadeOut();
	});
	
	//秒传
	WebUploader.Uploader.register({
	    'before-send-file': 'preupload'
	}, {
	    preupload: function( file ) {
	        var me = this,
	            owner = this.owner,
	            server = me.options.server,
	            deferred = WebUploader.Deferred(),
	            blob = file.source.getSource();
	        md5Blob( blob )

	            // 如果读取出错了，则通过reject告诉webuploader文件上传出错。
	            .fail(function() {
	            	//alert("deferred.reject();");
	                deferred.reject();
	            })

	            // md5直接计算完成
	            .then(function( md5 ) {

	                // 与服务安验证
	                $.ajax(server, {
	                    dataType: 'json',
	                    data: {
	                        md5: ret
	                    },
	                    success: function( response ) {

	                        // 如果验证已经上传过
	                        if ( response.exist ) {
	                            owner.skipFile( file );

	                            console.log('文件重复，已跳过');
	                        }

	                        // 介绍此promise, webuploader接着往下走。
	                        deferred.resolve();
	                    }
	                });
	            });

	        return deferred.promise();
	    }
	});
	
	  //断点续传
	  WebUploader.Uploader.register({
	    'before-send': 'checkchunk'
	}, {
	    checkchunk: function( block ) {
	        var blob = block.blob.getSource(),
	            deferred = $.Deferred();
	        var me = this,
            owner = this.owner,
            server = me.options.server;
	        
	        // 这个肯定是异步的，需要通过FileReader读取blob后再算。
	        md5Blob( blob, function( error, ret ) {
	            // 读取md5出错的话，分片不能跳过。
	            if ( error ) {
	                deferred.resolve();
	            } else {

	                // 方案1
	                // 将md5结果通过ajax与服务端验证
	                $.ajax( server, {
	                    dataType: 'json',
	                    data: {
	                        md5: ret
	                    } }).then(function( response ) {

	                    // 根据md5与服务端匹配，如果重复，则跳过。
	                    if ( response.exist ) {
	                        deferred.reject();
	                    } else {
	                        deferred.resolve();
	                    }
	                });

	               /* // 方案二
	                // 在这个文件上传前，一次性把所有已成功的分片md5拿到。
	                // 在这里只需本地验证就ok
	                if ( hash[ ret ] ) {
	                    deferred.reject();
	                } else {
	                    deferred.resolve();
	                }*/
	            }
	        });

	        return deferred.promise();
	    }
	});
	
	  
	uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }

        if ( state === 'uploading' ) {
            $btn.text('暂停上传');
            $progress.show();
        } else {
            $btn.text('开始上传');
            $progress.hide();
        }
    });

    $btn.on( 'click', function() {
        if ( state === 'uploading' ) {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
	
    uploader.onUploadProgress = function( file, percentage ) {
        var $li = $('#'+file.id),
            $percent = $li.find('.progress span');

        $percent.css( 'width', percentage * 100 + '%' );
        percentages[ file.id ] = percentage;
        updateTotalProgress();
    };
    
    function updateTotalProgress() {
        var loaded = 0,
            total = 0,
            spans = $progress.children(),
            percent;

        $.each( percentages, function( k, v ) {
            total += v[ 0 ];
            loaded += v[ 0 ] * v[ 1 ];
        } );

        percent = total ? loaded / total : 0;

        spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
        spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
    }
    
    uploader.onFileQueued = function( file ) {
        var start = Date.now();
        
       /* Md5File( file, function( value ) {
            var $li = $('#'+file.id);
            file.md5 = value;

            console.log( value );

            $li.append( '<p class="log">md5:' + ((Date.now() - start)/1000).toFixed(2) + '秒</p>')
        });*/
        var me = this,
        owner = this.owner,
        server = me.options.server,
        deferred = WebUploader.Deferred(),
        blob = file.source.getSource();
        md5Blob( blob, function( error, ret ) {
            // 读取md5出错的话，分片不能跳过。
            if ( error ) {
                deferred.resolve();
            } else {
            	file.md5 = value;
            }
        });
        updateTotalProgress();
    };
    /*	
    var Md5File = (function(){
        return function( file, cb ) {
            var worker = new Worker( 'publicCloud/pub-ui/js/image/hashFile.js' );
            worker.onmessage = function( e ) {
                cb( e.data, file );
                worker.terminate();
            };
            worker.postMessage( file.source );
        }
    })();*/
    
    });  
})( jQuery );