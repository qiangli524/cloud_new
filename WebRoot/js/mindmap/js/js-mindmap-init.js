// load the mindmap
        $(document).ready(function() {
            // enable the mindmap in the body
            $('body').mindmap();

            // add the data to the mindmap
            var root = $('body>ul>li').get(0).mynode = $('body').addRootNode($('body>ul>li>a').text(), {
                href:'/',
                url:'/',
                css:$('body>ul>li>a').attr('class'),
                onclick:function(node) {
                    $(node.obj.activeNode.content).each(function() {
                        this.hide();
                    });
                }
            });
            $('body>ul>li').hide();
            var addLI = function() {
                var parentnode = $(this).parents('li').get(0);
                if (typeof(parentnode)=='undefined') parentnode=root;
                    else parentnode=parentnode.mynode;
                
                this.mynode = $('body').addNode(parentnode, $('a:eq(0)',this).text(), {
//                    href:$('a:eq(0)',this).text().toLowerCase(),
                    href:$('a:eq(0)',this).attr('href'),
                    css:$('a:eq(0)',this).attr('class'),
                    onclick:function(node) {
                        $(node.obj.activeNode.content).each(function() {
                            this.hide();
                        });
                        $(node.content).each(function() {
                            this.show();
                        });
                        $("p").remove();
                        var html = "<p>"+node.name+"</p>";
                        $('body').append(html);
                    }
                });
                $(this).hide();
                $('>ul>li', this).each(addLI);
            };
            $('body>ul>li>ul').each(function() { 
                $('>li', this).each(addLI);
            });
        
        });   