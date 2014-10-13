<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
    <head>
        <title>My JSP 'charts.jsp' starting page</title>
        <script type="text/javascript" src="../jquery/jquery-1.8.2-min.js"></script>
        <script type="text/javascript" src="../highstock/highstock.js"></script>
        <script type="text/javascript">
           $(function() {
                $.getJSON('http://www.highcharts.com/samples/data/jsonp.php?filename=aapl-c.json&callback=?', function(data) {
                    // Create the chart
                    window.chart = new Highcharts.StockChart({
                        chart : {
                            renderTo : 'container'
                        },

                        rangeSelector : {
                            selected : 1
                        },

                        title : {
                            text : 'AAPL Stock Price'
                        },
			
                        series : [{
                                name : 'AAPL',
                                data : data,
                                tooltip: {
                                    valueDecimals: 2
                                }
                            },{
                                name : 'AAPL',
                                data : data,
                                tooltip: {
                                    valueDecimals: 2
                                }
                            },{
                                name : 'AAPL',
                                data : data,
                                tooltip: {
                                    valueDecimals: 2
                                }
                            }]
                    });
                });

            });
        </script>
    </head>

    <body>
        <div id="container"></div>
    </body>
</html>
