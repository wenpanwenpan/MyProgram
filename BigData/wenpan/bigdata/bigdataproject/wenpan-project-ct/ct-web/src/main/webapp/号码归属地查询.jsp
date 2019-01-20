<%@page pageEncoding='UTF-8'%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>查询号码归属地信息</title>

</head>
<body>
<div align="center" style="align-content: center">
    <form style="align-content: center">
        <input type="text" class="form-control" id="tel" placeholder="请输入电话号码">
        <button type="button" id="chaxun" class="btn btn-default" >查询</button>

        <div id="msg" style="align-content: center" align="center">

        </div>
    </form>
</div>

<script src="/jquery/jquery-3.2.0.min.js"></script>
<script>
    $("#chaxun").click(function () {
        var tel = $("#tel").val();
        $.ajax({
            url:"https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=" + tel,
            type:"get",
            dataType:'JSONP',
            success:function(result){
                //取得服务器端返回的值
                $("#msg").empty();
                $("#msg").append("<h4></h4>").append("所属省份：").append(result.province);
                $("#msg").append("<h4></h4>").append("号码归属：").append(result.carrier);
                $("#msg").append("<h4></h4>").append("运营商  ：").append(result.catName);
                $("#msg").append("<h4></h4>").append("电话号码：").append(result.telString);
            }
        });
    });

</script>
</body>
</html>