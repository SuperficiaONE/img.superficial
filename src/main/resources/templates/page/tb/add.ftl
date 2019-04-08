<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加表含义</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加表含义</h2>
        <div class="layui-form-item" id="tbType">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">添加的字段名/表名</label>
            <div class="layui-input-inline" >
                <input class="layui-input" style="height: 30px;" name="tbName"/>
            </div>
        </div>
        <div class="layui-form-item " >
            <label class="layui-form-label">添加的含义</label>
            <div class="layui-input-inline" >
                <input class="layui-input" style="height: 30px;" name="tbMean"/>
            </div>
        </div>

        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" onclick="window.location.reload()">重置</button>
            </div>
        </div>
    </div>
</form>

<script src="/static/layui/layui.js" charset="utf-8"></script>
<script src="/static/jquery/jquery-1.9.1.min.js" charset="utf-8"></script>
<script src="/static/jquery/base.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
     layui.use(['jquery','form','layer'],function () {
         var layer = layui.layer;
         initSelects("/webapi/dict/formSelectList?dictTypes=tbType")
         $("#submit").click(function () {
             var tbType = $("select[name='tbType']").val();
             var tbName = $("input[name='tbName']").val();
             var tbMean = $("input[name='tbMean']").val();
             var data = {};
             data['tbType'] = tbType;
             data['tbName'] = tbName;
             data['tbMean'] = tbMean;
             post("/api/tb/add",data,function (res) {
                 if(res.state == 1){
                     layer.msg(res.msg)
                 }else {
                     layer.open({
                         title: '提示'
                         , content: res.msg
                     });
                 }
             })
         })
     })
</script>

</body>
</html>

</html>
