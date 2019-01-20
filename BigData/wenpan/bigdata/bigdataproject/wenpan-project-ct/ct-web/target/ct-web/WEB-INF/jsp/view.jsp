<%@page pageEncoding='UTF-8'%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>用户通话信息统计数据</title>

    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="/jquery/echarts.min.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM,图标组件容器 -->
<div id="main" style="width: 600px;height:400px;"></div>
<div id="main1" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '用户通话信息的统计'
        },
        tooltip: {},
        legend: {
            data:['通话时长']
        },
        //横坐标
        xAxis: {
            data: [
                <c:forEach items="${calllogs}" var="calllog">
                ${calllog.dateid},
                </c:forEach>
            ]
        },
        yAxis: {},
        series: [{
            name: '通话时长',
            type: 'bar',
            //纵坐标
            data: [
                <c:forEach items="${calllogs}" var="calllog">
                ${calllog.sumcall},
                </c:forEach>
            ]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<script src="/jquery/jquery-3.2.0.min.js"></script>
<script src="/bootstrap/js/bootstrap.min.js"></script>
<script src="/jquery/echarts.min.js"></script>

</body>
</html>