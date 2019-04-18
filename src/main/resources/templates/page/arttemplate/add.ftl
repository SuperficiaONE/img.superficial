<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>添加模板</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">添加模板</h2>
        <div class="layui-form-item" id="templateType">
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板脚本</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 200px" name="templateScript"></textarea>
            </div>
        </div>
        <div class="layui-form-item " >
            <label class="layui-form-label">数据结构</label>
            <div class="layui-input-block" >
                <textarea class="layui-textarea" style="height: 100px;width: 200px" name="tempalteData"></textarea>
            </div>
        </div>

        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" >重置</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['jquery','form','layer'],function () {
        var layer = layui.layer;
        initSelects("/webapi/dict/formSelectList?dictTypes=templateType")
        $("#submit").click(function () {
            var templateType = $("select[name='templateType']").val();
            var templateScript = $("textarea[name='templateScript']").val();
            var tempalteData = $("textarea[name='tempalteData']").val();
            var data = {};
            data['templateType'] = templateType;
            data['templateScript'] = templateScript;
            data['tempalteData'] = tempalteData;
            post("/api/tbArtTemplate/add",data,function (res) {
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
