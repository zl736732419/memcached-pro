/**
 * Created by zhenglian on 2016/7/9.
 */
(function($) {

    var $content = $('.main');

    var myChart = echarts.init($content[0]);

    var data = [];
    var times = [];
    
    function prepareData() {
    	data.push(1);
    	times.push(getCurrentTimeStr());
    };
    
    prepareData();
    
    function getCurrentTimeStr() {
        var now = new Date();
    	return [now.getHours(), now.getMinutes(), now.getSeconds()].join(':');
    }
    
    function randomData() {
        data.push(Math.random());
        times.push(getCurrentTimeStr());
    }

    option = {
        title: {
            text: 'memcached求余算法与一致性hash算法命中率比较'
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (params) {
                params = params[0];
                var date = new Date(params.name);
                return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
            },
            axisPointer: {
                animation: false
            }
        },
        xAxis: {
            type: 'category',
            data: times,
            splitLine: {
                show: false
            }
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, '100%'],
            splitLine: {
                show: false
            }
        },
        series: [{
            name: '模拟数据',
            type: 'line',
            showSymbol: false,
            hoverAnimation: false,
            data: data
        }]
    };
    
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    function getData() {
    	var rate = null;
    	$.ajax({
    		cache:false,
    		type: 'post',
    		dataType: 'json',
    		async: false,
    		url: "MemcachedServlet",
    		success: function(data) {
    			rate = data;
    		}
    	});
    	
    	var time = getCurrentTimeStr();
    	data.push(rate);
    	times.push(time);
    }
    
    var app = {};
    app.timeTicket = setInterval(function () {
    	if(data.length == 10) {
    		data.shift();
            times.shift();
    	}
    	
    	getData();
    	
        myChart.setOption({
        	xAxis: {
        		data: times
        	},
            series: [{
                data: data
            }]
        });
    }, 3000);

    


})(jQuery);