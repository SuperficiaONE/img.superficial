<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>生成token</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include  "/commonCss.ftl">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<form class="layui-form" onsubmit="return false;">
    <div style="margin-left: auto;margin-right: auto;margin-top: 30px; width: 45%; border-radius: 20px;background-color: rgba(100,100,100,0.2);padding-bottom: 20px">
        <h2 style="text-align: center;margin-bottom: 20px;padding-top: 10px;">测试发请求</h2>
        <div class="layui-form-item">
            <label class="layui-form-label">请求路径:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="uri"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请求参数:</label>
            <div class="layui-input-inline">
                <input class="layui-input" style="height: 30px;" name="data"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">请求方式:</label>
            <div class="layui-input-inline">
                <select name="type" lay-verify="">
                    <option value="0">GET</option>
                    <option value="1">POST</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item" style="padding-bottom: 20px;">
            <div class="layui-input-block">
                <button id="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            </div>
        </div>
    </div>
</form>

  <#include  "/commonJS.ftl">
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['jquery', 'form', 'layer'], function () {
        var layer = layui.layer;
        $("#submit").click(function () {
            var type = $("select[name='type']").val()
            var data = $("input[name='data']").val()
            var uri = $("input[name='uri']").val()
            if(uri==undefined || uri==""){
                layer.open({
                    title:'错误',
                    content:"uri 没有值"
                        }
                )
                return
            }
            if(data==undefined){
                data=""
            }
            if(type==1){
                // 发送post请求
                post(uri+"?"+data,null,function (res) {
                    console.log(res)
                })
            }else {
                // 发送get请求
                get(uri+"?"+data,function (res) {
                    console.log(res)
                })
            }
        })
    })
</script>

</body>
</html>

</html>
